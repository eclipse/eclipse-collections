/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction0;
import org.eclipse.collections.api.block.function.primitive.BooleanToBooleanFunction;
import org.eclipse.collections.api.block.function.primitive.BooleanToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectBooleanToObjectFunction;
import org.eclipse.collections.api.block.predicate.primitive.BooleanPredicate;
import org.eclipse.collections.api.block.predicate.primitive.ObjectBooleanPredicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.BooleanProcedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectBooleanProcedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.map.primitive.ImmutableObjectBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableObjectBooleanMap;
import org.eclipse.collections.api.map.primitive.ObjectBooleanMap;
import org.eclipse.collections.api.set.primitive.BooleanSet;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.api.tuple.primitive.ObjectBooleanPair;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.collection.mutable.primitive.SynchronizedBooleanCollection;
import org.eclipse.collections.impl.collection.mutable.primitive.UnmodifiableBooleanCollection;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.factory.primitive.ObjectBooleanMaps;
import org.eclipse.collections.impl.lazy.AbstractLazyIterable;
import org.eclipse.collections.impl.lazy.primitive.LazyBooleanIterableAdapter;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;

/**
 * @since 7.0.
 */
public class ObjectBooleanHashMapWithHashingStrategy<K> implements MutableObjectBooleanMap<K>, Externalizable
{
    public static final boolean EMPTY_VALUE = false;

    private static final long serialVersionUID = 1L;

    /**
     * @deprecated in 5.1.0.
     */
    @Deprecated
    private static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final int OCCUPIED_DATA_RATIO = 2;
    private static final int OCCUPIED_SENTINEL_RATIO = 4;
    private static final int DEFAULT_INITIAL_CAPACITY = 8;

    private static final Object NULL_KEY = new Object()
    {
        @Override
        public boolean equals(Object obj)
        {
            throw new RuntimeException("Possible corruption through unsynchronized concurrent modification.");
        }

        @Override
        public int hashCode()
        {
            throw new RuntimeException("Possible corruption through unsynchronized concurrent modification.");
        }

        @Override
        public String toString()
        {
            return "ObjectBooleanHashMapWithHashingStrategyWithHashingStrategy.NULL_KEY";
        }
    };

    private static final Object REMOVED_KEY = new Object()
    {
        @Override
        public boolean equals(Object obj)
        {
            throw new RuntimeException("Possible corruption through unsynchronized concurrent modification.");
        }

        @Override
        public int hashCode()
        {
            throw new RuntimeException("Possible corruption through unsynchronized concurrent modification.");
        }

        @Override
        public String toString()
        {
            return "ObjectBooleanHashMapWithHashingStrategy.REMOVED_KEY";
        }
    };

    private Object[] keys;
    private BitSet values;

    private int occupiedWithData;
    private int occupiedWithSentinels;

    private HashingStrategy<? super K> hashingStrategy;

    /**
     * @deprecated No argument default constructor used for serialization. Instantiating an ObjectBooleanHashMapWithHashingStrategy with
     * this constructor will have a null hashingStrategy and throw NullPointerException when used.
     */
    @Deprecated
    public ObjectBooleanHashMapWithHashingStrategy()
    {
    }

    public ObjectBooleanHashMapWithHashingStrategy(HashingStrategy<? super K> hashingStrategy)
    {
        this.hashingStrategy = hashingStrategy;
        this.allocateTable(DEFAULT_INITIAL_CAPACITY << 1);
    }

    public ObjectBooleanHashMapWithHashingStrategy(HashingStrategy<? super K> hashingStrategy, int initialCapacity)
    {
        if (initialCapacity < 0)
        {
            throw new IllegalArgumentException("initial capacity cannot be less than 0");
        }
        this.hashingStrategy = hashingStrategy;
        int capacity = this.smallestPowerOfTwoGreaterThan(this.fastCeil(initialCapacity * OCCUPIED_DATA_RATIO));
        this.allocateTable(capacity);
    }

    public ObjectBooleanHashMapWithHashingStrategy(HashingStrategy<? super K> hashingStrategy, ObjectBooleanMap<? extends K> map)
    {
        this(hashingStrategy, Math.max(map.size(), DEFAULT_INITIAL_CAPACITY));
        this.putAll(map);
    }

    public static <K> ObjectBooleanHashMapWithHashingStrategy<K> newMap(HashingStrategy<? super K> hashingStrategy)
    {
        return new ObjectBooleanHashMapWithHashingStrategy<K>(hashingStrategy);
    }

    public static <K> ObjectBooleanHashMapWithHashingStrategy<K> newMap(HashingStrategy<? super K> hashingStrategy, ObjectBooleanMap<K> map)
    {
        return new ObjectBooleanHashMapWithHashingStrategy<K>(hashingStrategy, map);
    }

    public static <K> ObjectBooleanHashMapWithHashingStrategy<K> newMap(ObjectBooleanHashMapWithHashingStrategy<K> map)
    {
        return new ObjectBooleanHashMapWithHashingStrategy<K>(map.hashingStrategy, map);
    }

    public static <K> ObjectBooleanHashMapWithHashingStrategy<K> newWithKeysValues(HashingStrategy<? super K> hashingStrategy, K key, boolean value)
    {
        return new ObjectBooleanHashMapWithHashingStrategy<K>(hashingStrategy, 1).withKeyValue(key, value);
    }

    public static <K> ObjectBooleanHashMapWithHashingStrategy<K> newWithKeysValues(HashingStrategy<? super K> hashingStrategy, K key1, boolean value1, K key2, boolean value2)
    {
        return new ObjectBooleanHashMapWithHashingStrategy<K>(hashingStrategy, 2).withKeysValues(key1, value1, key2, value2);
    }

    public static <K> ObjectBooleanHashMapWithHashingStrategy<K> newWithKeysValues(HashingStrategy<? super K> hashingStrategy, K key1, boolean value1, K key2, boolean value2, K key3, boolean value3)
    {
        return new ObjectBooleanHashMapWithHashingStrategy<K>(hashingStrategy, 3).withKeysValues(key1, value1, key2, value2, key3, value3);
    }

    public static <K> ObjectBooleanHashMapWithHashingStrategy<K> newWithKeysValues(HashingStrategy<? super K> hashingStrategy, K key1, boolean value1, K key2, boolean value2, K key3, boolean value3, K key4, boolean value4)
    {
        return new ObjectBooleanHashMapWithHashingStrategy<K>(hashingStrategy, 4).withKeysValues(key1, value1, key2, value2, key3, value3, key4, value4);
    }

    private int smallestPowerOfTwoGreaterThan(int n)
    {
        return n > 1 ? Integer.highestOneBit(n - 1) << 1 : 1;
    }

    private int fastCeil(float v)
    {
        int possibleResult = (int) v;
        if (v - possibleResult > 0.0F)
        {
            possibleResult++;
        }
        return possibleResult;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (!(obj instanceof ObjectBooleanMap))
        {
            return false;
        }

        ObjectBooleanMap<K> other = (ObjectBooleanMap<K>) obj;

        if (this.size() != other.size())
        {
            return false;
        }

        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]) && (!other.containsKey(this.toNonSentinel(this.keys[i])) || this.values.get(i) != other.getOrThrow(this.toNonSentinel(this.keys[i]))))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int result = 0;

        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]))
            {
                result += this.hashingStrategy.computeHashCode(this.toNonSentinel(this.keys[i])) ^ (this.values.get(i) ? 1231 : 1237);
            }
        }
        return result;
    }

    @Override
    public String toString()
    {
        StringBuilder appendable = new StringBuilder();

        appendable.append("{");

        boolean first = true;

        for (int i = 0; i < this.keys.length; i++)
        {
            Object key = this.keys[i];
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(key))
            {
                if (!first)
                {
                    appendable.append(", ");
                }
                appendable.append(this.toNonSentinel(key)).append("=").append(this.values.get(i));
                first = false;
            }
        }
        appendable.append("}");

        return appendable.toString();
    }

    public int size()
    {
        return this.occupiedWithData;
    }

    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    public boolean notEmpty()
    {
        return this.size() != 0;
    }

    public String makeString()
    {
        return this.makeString(", ");
    }

    public String makeString(String separator)
    {
        return this.makeString("", separator, "");
    }

    public String makeString(String start, String separator, String end)
    {
        Appendable stringBuilder = new StringBuilder();
        this.appendString(stringBuilder, start, separator, end);
        return stringBuilder.toString();
    }

    public void appendString(Appendable appendable)
    {
        this.appendString(appendable, ", ");
    }

    public void appendString(Appendable appendable, String separator)
    {
        this.appendString(appendable, "", separator, "");
    }

    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        try
        {
            appendable.append(start);

            boolean first = true;

            for (int i = 0; i < this.keys.length; i++)
            {
                Object key = this.keys[i];
                if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(key))
                {
                    if (!first)
                    {
                        appendable.append(separator);
                    }
                    appendable.append(String.valueOf(this.values.get(i)));
                    first = false;
                }
            }
            appendable.append(end);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public MutableBooleanIterator booleanIterator()
    {
        return new InternalBooleanIterator();
    }

    public boolean[] toArray()
    {
        boolean[] result = new boolean[this.size()];
        int index = 0;
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]))
            {
                result[index] = this.values.get(i);
                index++;
            }
        }
        return result;
    }

    public boolean contains(boolean value)
    {
        return this.containsValue(value);
    }

    public boolean containsAll(boolean... source)
    {
        for (boolean item : source)
        {
            if (!this.containsValue(item))
            {
                return false;
            }
        }
        return true;
    }

    public boolean containsAll(BooleanIterable source)
    {
        return this.containsAll(source.toArray());
    }

    public void forEach(BooleanProcedure procedure)
    {
        this.forEachValue(procedure);
    }

    public void each(BooleanProcedure procedure)
    {
        this.forEach(procedure);
    }

    public boolean detectIfNone(BooleanPredicate predicate, boolean ifNone)
    {
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]) && predicate.accept(this.values.get(i)))
            {
                return this.values.get(i);
            }
        }
        return ifNone;
    }

    public MutableBooleanCollection select(BooleanPredicate predicate)
    {
        MutableBooleanList result = BooleanLists.mutable.empty();
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]) && predicate.accept(this.values.get(i)))
            {
                result.add(this.values.get(i));
            }
        }
        return result;
    }

    public int count(BooleanPredicate predicate)
    {
        int count = 0;
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]) && predicate.accept(this.values.get(i)))
            {
                count++;
            }
        }
        return count;
    }

    public MutableBooleanCollection reject(BooleanPredicate predicate)
    {
        MutableBooleanList result = BooleanLists.mutable.empty();
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]) && !predicate.accept(this.values.get(i)))
            {
                result.add(this.values.get(i));
            }
        }
        return result;
    }

    public boolean anySatisfy(BooleanPredicate predicate)
    {
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]) && predicate.accept(this.values.get(i)))
            {
                return true;
            }
        }
        return false;
    }

    public boolean allSatisfy(BooleanPredicate predicate)
    {
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]) && !predicate.accept(this.values.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    public <V> MutableCollection<V> collect(BooleanToObjectFunction<? extends V> function)
    {
        MutableList<V> result = FastList.newList(this.size());
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]))
            {
                result.add(function.valueOf(this.values.get(i)));
            }
        }
        return result;
    }

    public boolean noneSatisfy(BooleanPredicate predicate)
    {
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]) && predicate.accept(this.values.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    public MutableBooleanList toList()
    {
        MutableBooleanList result = new BooleanArrayList(this.size());
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]))
            {
                result.add(this.values.get(i));
            }
        }
        return result;
    }

    public MutableBooleanSet toSet()
    {
        MutableBooleanSet result = new BooleanHashSet();
        for (int i = 0; result.size() < 2 && i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]))
            {
                result.add(this.values.get(i));
            }
        }
        return result;
    }

    public MutableBooleanBag toBag()
    {
        MutableBooleanBag result = new BooleanHashBag();
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]))
            {
                result.add(this.values.get(i));
            }
        }
        return result;
    }

    public LazyBooleanIterable asLazy()
    {
        return new LazyBooleanIterableAdapter(this);
    }

    public <V> V injectInto(V injectedValue, ObjectBooleanToObjectFunction<? super V, ? extends V> function)
    {
        V result = injectedValue;

        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]))
            {
                result = function.valueOf(result, this.values.get(i));
            }
        }

        return result;
    }

    public void clear()
    {
        this.occupiedWithData = 0;
        this.occupiedWithSentinels = 0;
        Arrays.fill(this.keys, null);
        this.values.clear();
    }

    public void put(K key, boolean value)
    {
        int index = this.probe(key);

        if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[index]) && this.nullSafeEquals(this.toNonSentinel(this.keys[index]), key))
        {
            // key already present in map
            this.values.set(index, value);
            return;
        }

        this.addKeyValueAtIndex(key, value, index);
    }

    public void putAll(ObjectBooleanMap<? extends K> map)
    {
        map.forEachKeyValue(new ObjectBooleanProcedure<K>()
        {
            public void value(K each, boolean parameter)
            {
                ObjectBooleanHashMapWithHashingStrategy.this.put(each, parameter);
            }
        });
    }

    public void removeKey(K key)
    {
        int index = this.probe(key);
        this.removeKeyAtIndex(key, index);
    }

    public void remove(Object key)
    {
        this.removeKey((K) key);
    }

    private void removeKeyAtIndex(K key, int index)
    {
        if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[index]) && this.nullSafeEquals(this.toNonSentinel(this.keys[index]), key))
        {
            this.keys[index] = REMOVED_KEY;
            this.values.set(index, EMPTY_VALUE);
            this.occupiedWithData--;
            this.occupiedWithSentinels++;
        }
    }

    public boolean removeKeyIfAbsent(K key, boolean value)
    {
        int index = this.probe(key);
        if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[index]) && this.nullSafeEquals(this.toNonSentinel(this.keys[index]), key))
        {
            this.keys[index] = REMOVED_KEY;
            boolean oldValue = this.values.get(index);
            this.values.set(index, EMPTY_VALUE);
            this.occupiedWithData--;
            this.occupiedWithSentinels++;

            return oldValue;
        }
        return value;
    }

    public boolean getIfAbsentPut(K key, boolean value)
    {
        int index = this.probe(key);
        if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[index]) && this.nullSafeEquals(this.toNonSentinel(this.keys[index]), key))
        {
            return this.values.get(index);
        }
        this.addKeyValueAtIndex(key, value, index);
        return value;
    }

    public boolean getIfAbsentPut(K key, BooleanFunction0 function)
    {
        int index = this.probe(key);
        if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[index]) && this.nullSafeEquals(this.toNonSentinel(this.keys[index]), key))
        {
            return this.values.get(index);
        }
        boolean value = function.value();
        this.addKeyValueAtIndex(key, value, index);
        return value;
    }

    public boolean getIfAbsentPutWithKey(K key, BooleanFunction<? super K> function)
    {
        int index = this.probe(key);
        if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[index]) && this.nullSafeEquals(this.toNonSentinel(this.keys[index]), key))
        {
            return this.values.get(index);
        }
        boolean value = function.booleanValueOf(key);
        this.addKeyValueAtIndex(key, value, index);
        return value;
    }

    public <P> boolean getIfAbsentPutWith(K key, BooleanFunction<? super P> function, P parameter)
    {
        int index = this.probe(key);
        if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[index]) && this.nullSafeEquals(this.toNonSentinel(this.keys[index]), key))
        {
            return this.values.get(index);
        }
        boolean value = function.booleanValueOf(parameter);
        this.addKeyValueAtIndex(key, value, index);
        return value;
    }

    public boolean updateValue(K key, boolean initialValueIfAbsent, BooleanToBooleanFunction function)
    {
        int index = this.probe(key);
        if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[index]) && this.nullSafeEquals(this.toNonSentinel(this.keys[index]), key))
        {
            this.values.set(index, function.valueOf(this.values.get(index)));
            return this.values.get(index);
        }
        boolean value = function.valueOf(initialValueIfAbsent);
        this.addKeyValueAtIndex(key, value, index);
        return value;
    }

    public ObjectBooleanHashMapWithHashingStrategy<K> withKeyValue(K key1, boolean value1)
    {
        this.put(key1, value1);
        return this;
    }

    public ObjectBooleanHashMapWithHashingStrategy<K> withoutKey(K key)
    {
        this.removeKey(key);
        return this;
    }

    public ObjectBooleanHashMapWithHashingStrategy<K> withoutAllKeys(Iterable<? extends K> keys)
    {
        for (K key : keys)
        {
            this.removeKey(key);
        }
        return this;
    }

    public MutableObjectBooleanMap<K> asUnmodifiable()
    {
        return new UnmodifiableObjectBooleanMap<K>(this);
    }

    public MutableObjectBooleanMap<K> asSynchronized()
    {
        return new SynchronizedObjectBooleanMap<K>(this);
    }

    public boolean get(Object key)
    {
        return this.getIfAbsent(key, EMPTY_VALUE);
    }

    public boolean getOrThrow(Object key)
    {
        int index = this.probe(key);
        if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[index]))
        {
            return this.values.get(index);
        }
        throw new IllegalStateException("Key " + key + " not present.");
    }

    public boolean getIfAbsent(Object key, boolean ifAbsent)
    {
        int index = this.probe(key);
        if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[index]) && this.nullSafeEquals(this.toNonSentinel(this.keys[index]), key))
        {
            return this.values.get(index);
        }
        return ifAbsent;
    }

    public boolean containsKey(Object key)
    {
        int index = this.probe(key);
        return ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[index]) && this.nullSafeEquals(this.toNonSentinel(this.keys[index]), key);
    }

    public boolean containsValue(boolean value)
    {
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]) && this.values.get(i) == value)
            {
                return true;
            }
        }
        return false;
    }

    public void forEachValue(BooleanProcedure procedure)
    {
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]))
            {
                procedure.value(this.values.get(i));
            }
        }
    }

    public void forEachKey(Procedure<? super K> procedure)
    {
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]))
            {
                procedure.value(this.toNonSentinel(this.keys[i]));
            }
        }
    }

    public void forEachKeyValue(ObjectBooleanProcedure<? super K> procedure)
    {
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]))
            {
                procedure.value(this.toNonSentinel(this.keys[i]), this.values.get(i));
            }
        }
    }

    public ObjectBooleanHashMapWithHashingStrategy<K> select(ObjectBooleanPredicate<? super K> predicate)
    {
        ObjectBooleanHashMapWithHashingStrategy<K> result = ObjectBooleanHashMapWithHashingStrategy.newMap(this.hashingStrategy);
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]) && predicate.accept(this.toNonSentinel(this.keys[i]), this.values.get(i)))
            {
                result.put(this.toNonSentinel(this.keys[i]), this.values.get(i));
            }
        }
        return result;
    }

    public ObjectBooleanHashMapWithHashingStrategy<K> reject(ObjectBooleanPredicate<? super K> predicate)
    {
        ObjectBooleanHashMapWithHashingStrategy<K> result = ObjectBooleanHashMapWithHashingStrategy.newMap(this.hashingStrategy);
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]) && !predicate.accept(this.toNonSentinel(this.keys[i]), this.values.get(i)))
            {
                result.put(this.toNonSentinel(this.keys[i]), this.values.get(i));
            }
        }
        return result;
    }

    public ImmutableObjectBooleanMap<K> toImmutable()
    {
        return ObjectBooleanMaps.immutable.withAll(this);
    }

    public Set<K> keySet()
    {
        return new KeySet();
    }

    public MutableBooleanCollection values()
    {
        return new ValuesCollection();
    }

    public LazyIterable<K> keysView()
    {
        return new KeysView();
    }

    public RichIterable<ObjectBooleanPair<K>> keyValuesView()
    {
        return new KeyValuesView();
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.hashingStrategy);
        out.writeInt(this.size());
        for (int i = 0; i < this.keys.length; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.keys[i]))
            {
                out.writeObject(this.toNonSentinel(this.keys[i]));
                out.writeBoolean(this.values.get(i));
            }
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.hashingStrategy = (HashingStrategy<? super K>) in.readObject();
        int size = in.readInt();
        int capacity = this.smallestPowerOfTwoGreaterThan(this.fastCeil(size * OCCUPIED_DATA_RATIO));
        this.allocateTable(capacity);

        for (int i = 0; i < size; i++)
        {
            this.put((K) in.readObject(), in.readBoolean());
        }
    }

    // exposed for testing
    int probe(Object element)
    {
        int index = this.spread(element);

        int removedIndex = -1;
        if (ObjectBooleanHashMapWithHashingStrategy.isRemovedKey(this.keys[index]))
        {
            removedIndex = index;
        }

        else if (this.keys[index] == null || this.nullSafeEquals(this.toNonSentinel(this.keys[index]), element))
        {
            return index;
        }

        int nextIndex = index;
        int probe = 17;

        // loop until an empty slot is reached
        while (true)
        {
            // Probe algorithm: 17*n*(n+1)/2 where n = no. of collisions
            nextIndex += probe;
            probe += 17;
            nextIndex &= this.keys.length - 1;

            if (ObjectBooleanHashMapWithHashingStrategy.isRemovedKey(this.keys[nextIndex]))
            {
                if (removedIndex == -1)
                {
                    removedIndex = nextIndex;
                }
            }
            else if (this.nullSafeEquals(this.toNonSentinel(this.keys[nextIndex]), element))
            {
                return nextIndex;
            }
            else if (this.keys[nextIndex] == null)
            {
                return removedIndex == -1 ? nextIndex : removedIndex;
            }
        }
    }

    private static boolean isRemovedKey(Object key)
    {
        return key == REMOVED_KEY;
    }

    private boolean nullSafeEquals(K key, Object other)
    {
        if (key == null)
        {
            if (other == null)
            {
                return true;
            }
        }
        else if (key != NULL_KEY && other != null)
        {
            if (this.hashingStrategy.equals(key, this.toNonSentinel(other)))
            {
                return true;
            }
        }
        return false;
    }

    private K toNonSentinel(Object key)
    {
        return key == NULL_KEY ? null : (K) key;
    }

    // exposed for testing
    int spread(Object element)
    {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        K nonSentinelKey = this.toNonSentinel(element);
        int h = nonSentinelKey == null ? 0 : this.hashingStrategy.computeHashCode(nonSentinelKey);
        h ^= h >>> 20 ^ h >>> 12;
        h ^= h >>> 7 ^ h >>> 4;
        return h & (this.keys.length - 1);
    }

    private void addKeyValueAtIndex(K key, boolean value, int index)
    {
        if (this.keys[index] == REMOVED_KEY)
        {
            --this.occupiedWithSentinels;
        }
        this.keys[index] = ObjectBooleanHashMapWithHashingStrategy.toSentinelIfNull(key);
        this.values.set(index, value);
        ++this.occupiedWithData;
        if (this.occupiedWithData + this.occupiedWithSentinels > this.maxOccupiedWithData())
        {
            this.rehashAndGrow();
        }
    }

    private static Object toSentinelIfNull(Object key)
    {
        return key == null ? NULL_KEY : key;
    }

    private int maxOccupiedWithData()
    {
        int capacity = this.keys.length;
        // need at least one free slot for open addressing
        return Math.min(capacity - 1, capacity / OCCUPIED_DATA_RATIO);
    }

    private void rehashAndGrow()
    {
        this.rehash(this.keys.length << 1);
    }

    private void rehash(int newCapacity)
    {
        int oldLength = this.keys.length;
        Object[] old = this.keys;
        BitSet oldValues = this.values;
        this.allocateTable(newCapacity);
        this.occupiedWithData = 0;
        this.occupiedWithSentinels = 0;

        for (int i = 0; i < oldLength; i++)
        {
            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(old[i]))
            {
                this.put(this.toNonSentinel(old[i]), oldValues.get(i));
            }
        }
    }

    private void allocateTable(int sizeToAllocate)
    {
        this.keys = new Object[sizeToAllocate];
        this.values = new BitSet(sizeToAllocate);
    }

    private static <K> boolean isNonSentinel(K key)
    {
        return key != null && !ObjectBooleanHashMapWithHashingStrategy.isRemovedKey(key);
    }

    private int maxOccupiedWithSentinels()
    {
        return this.keys.length / OCCUPIED_SENTINEL_RATIO;
    }

    public ObjectBooleanHashMapWithHashingStrategy<K> withKeysValues(K key1, boolean value1, K key2, boolean value2)
    {
        this.put(key1, value1);
        this.put(key2, value2);
        return this;
    }

    public ObjectBooleanHashMapWithHashingStrategy<K> withKeysValues(K key1, boolean value1, K key2, boolean value2, K key3, boolean value3)
    {
        this.put(key1, value1);
        this.put(key2, value2);
        this.put(key3, value3);
        return this;
    }

    public ObjectBooleanHashMapWithHashingStrategy<K> withKeysValues(K key1, boolean value1, K key2, boolean value2, K key3, boolean value3, K key4, boolean value4)
    {
        this.put(key1, value1);
        this.put(key2, value2);
        this.put(key3, value3);
        this.put(key4, value4);
        return this;
    }

    private class KeySet implements Set<K>
    {
        @Override
        public boolean equals(Object obj)
        {
            if (obj instanceof Set)
            {
                Set<?> other = (Set<?>) obj;
                if (other.size() == this.size())
                {
                    return this.containsAll(other);
                }
            }
            return false;
        }

        @Override
        public int hashCode()
        {
            int hashCode = 0;
            Object[] table = ObjectBooleanHashMapWithHashingStrategy.this.keys;
            for (int i = 0; i < table.length; i++)
            {
                Object key = table[i];
                if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(key))
                {
                    K nonSentinelKey = ObjectBooleanHashMapWithHashingStrategy.this.toNonSentinel(key);
                    hashCode += nonSentinelKey == null ? 0 : ObjectBooleanHashMapWithHashingStrategy.this.hashingStrategy.computeHashCode(nonSentinelKey);
                }
            }
            return hashCode;
        }

        public int size()
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.size();
        }

        public boolean isEmpty()
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.isEmpty();
        }

        public boolean contains(Object o)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.containsKey(o);
        }

        public Object[] toArray()
        {
            int size = ObjectBooleanHashMapWithHashingStrategy.this.size();
            Object[] result = new Object[size];
            this.copyKeys(result);
            return result;
        }

        public <T> T[] toArray(T[] array)
        {
            T[] result = array;
            int size = ObjectBooleanHashMapWithHashingStrategy.this.size();
            if (result.length < size)
            {
                result = (T[]) Array.newInstance(result.getClass().getComponentType(), size);
            }
            this.copyKeys(result);
            if (size < result.length)
            {
                result[size] = null;
            }
            return result;
        }

        public boolean add(K key)
        {
            throw new UnsupportedOperationException("Cannot call add() on " + this.getClass().getSimpleName());
        }

        public boolean remove(Object key)
        {
            int oldSize = ObjectBooleanHashMapWithHashingStrategy.this.size();
            ObjectBooleanHashMapWithHashingStrategy.this.removeKey((K) key);
            return oldSize != ObjectBooleanHashMapWithHashingStrategy.this.size();
        }

        public boolean containsAll(Collection<?> collection)
        {
            for (Object aCollection : collection)
            {
                if (!ObjectBooleanHashMapWithHashingStrategy.this.containsKey(aCollection))
                {
                    return false;
                }
            }
            return true;
        }

        public boolean addAll(Collection<? extends K> collection)
        {
            throw new UnsupportedOperationException("Cannot call addAll() on " + this.getClass().getSimpleName());
        }

        public boolean retainAll(Collection<?> collection)
        {
            int oldSize = ObjectBooleanHashMapWithHashingStrategy.this.size();
            Iterator<K> iterator = this.iterator();
            while (iterator.hasNext())
            {
                K next = iterator.next();
                if (!collection.contains(next))
                {
                    iterator.remove();
                }
            }
            return oldSize != ObjectBooleanHashMapWithHashingStrategy.this.size();
        }

        public boolean removeAll(Collection<?> collection)
        {
            int oldSize = ObjectBooleanHashMapWithHashingStrategy.this.size();
            for (Object object : collection)
            {
                ObjectBooleanHashMapWithHashingStrategy.this.removeKey((K) object);
            }
            return oldSize != ObjectBooleanHashMapWithHashingStrategy.this.size();
        }

        public void clear()
        {
            ObjectBooleanHashMapWithHashingStrategy.this.clear();
        }

        public Iterator<K> iterator()
        {
            return new KeySetIterator();
        }

        private void copyKeys(Object[] result)
        {
            int count = 0;
            for (int i = 0; i < ObjectBooleanHashMapWithHashingStrategy.this.keys.length; i++)
            {
                Object key = ObjectBooleanHashMapWithHashingStrategy.this.keys[i];
                if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(key))
                {
                    result[count++] = ObjectBooleanHashMapWithHashingStrategy.this.keys[i];
                }
            }
        }
    }

    private class KeySetIterator implements Iterator<K>
    {
        private int count;
        private int position;
        private K currentKey;
        private boolean isCurrentKeySet;

        public boolean hasNext()
        {
            return this.count < ObjectBooleanHashMapWithHashingStrategy.this.size();
        }

        public K next()
        {
            if (!this.hasNext())
            {
                throw new NoSuchElementException();
            }
            this.count++;
            Object[] keys = ObjectBooleanHashMapWithHashingStrategy.this.keys;
            while (!ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(keys[this.position]))
            {
                this.position++;
            }
            this.currentKey = (K) ObjectBooleanHashMapWithHashingStrategy.this.keys[this.position];
            this.isCurrentKeySet = true;
            this.position++;
            return ObjectBooleanHashMapWithHashingStrategy.this.toNonSentinel(this.currentKey);
        }

        public void remove()
        {
            if (!this.isCurrentKeySet)
            {
                throw new IllegalStateException();
            }

            this.isCurrentKeySet = false;
            this.count--;

            if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(this.currentKey))
            {
                int index = this.position - 1;
                ObjectBooleanHashMapWithHashingStrategy.this.removeKeyAtIndex(ObjectBooleanHashMapWithHashingStrategy.this.toNonSentinel(this.currentKey), index);
            }
            else
            {
                ObjectBooleanHashMapWithHashingStrategy.this.removeKey(this.currentKey);
            }
        }
    }

    private class ValuesCollection implements MutableBooleanCollection
    {
        public boolean add(boolean element)
        {
            throw new UnsupportedOperationException("Cannot call add() on " + this.getClass().getSimpleName());
        }

        public boolean addAll(boolean... source)
        {
            throw new UnsupportedOperationException("Cannot call addAll() on " + this.getClass().getSimpleName());
        }

        public boolean addAll(BooleanIterable source)
        {
            throw new UnsupportedOperationException("Cannot call addAll() on " + this.getClass().getSimpleName());
        }

        public boolean remove(boolean element)
        {
            for (int i = 0; i < ObjectBooleanHashMapWithHashingStrategy.this.values.size(); i++)
            {
                if (ObjectBooleanHashMapWithHashingStrategy.this.values.get(i) == element && ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[i]))
                {
                    ObjectBooleanHashMapWithHashingStrategy.this.removeKey(ObjectBooleanHashMapWithHashingStrategy.this.toNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[i]));
                    return true;
                }
            }
            return false;
        }

        public boolean removeAll(BooleanIterable source)
        {
            int oldSize = ObjectBooleanHashMapWithHashingStrategy.this.size();

            BooleanIterator iterator = source.booleanIterator();
            while (iterator.hasNext())
            {
                this.remove(iterator.next());
            }
            return oldSize != ObjectBooleanHashMapWithHashingStrategy.this.size();
        }

        public boolean removeAll(boolean... source)
        {
            int oldSize = ObjectBooleanHashMapWithHashingStrategy.this.size();

            for (boolean item : source)
            {
                this.remove(item);
            }
            return oldSize != ObjectBooleanHashMapWithHashingStrategy.this.size();
        }

        public boolean retainAll(BooleanIterable source)
        {
            int oldSize = ObjectBooleanHashMapWithHashingStrategy.this.size();
            final BooleanSet sourceSet = source instanceof BooleanSet ? (BooleanSet) source : source.toSet();
            ObjectBooleanHashMapWithHashingStrategy<K> retained = ObjectBooleanHashMapWithHashingStrategy.this.select(new ObjectBooleanPredicate<K>()
            {
                public boolean accept(K object, boolean value)
                {
                    return sourceSet.contains(value);
                }
            });
            if (retained.size() != oldSize)
            {
                ObjectBooleanHashMapWithHashingStrategy.this.keys = retained.keys;
                ObjectBooleanHashMapWithHashingStrategy.this.values = retained.values;
                ObjectBooleanHashMapWithHashingStrategy.this.occupiedWithData = retained.occupiedWithData;
                ObjectBooleanHashMapWithHashingStrategy.this.occupiedWithSentinels = retained.occupiedWithSentinels;
                return true;
            }
            return false;
        }

        public boolean retainAll(boolean... source)
        {
            return this.retainAll(BooleanHashSet.newSetWith(source));
        }

        public void clear()
        {
            ObjectBooleanHashMapWithHashingStrategy.this.clear();
        }

        public MutableBooleanCollection with(boolean element)
        {
            throw new UnsupportedOperationException("Cannot call with() on " + this.getClass().getSimpleName());
        }

        public MutableBooleanCollection without(boolean element)
        {
            throw new UnsupportedOperationException("Cannot call without() on " + this.getClass().getSimpleName());
        }

        public MutableBooleanCollection withAll(BooleanIterable elements)
        {
            throw new UnsupportedOperationException("Cannot call withAll() on " + this.getClass().getSimpleName());
        }

        public MutableBooleanCollection withoutAll(BooleanIterable elements)
        {
            throw new UnsupportedOperationException("Cannot call withoutAll() on " + this.getClass().getSimpleName());
        }

        public MutableBooleanCollection asUnmodifiable()
        {
            return UnmodifiableBooleanCollection.of(this);
        }

        public MutableBooleanCollection asSynchronized()
        {
            return SynchronizedBooleanCollection.of(this);
        }

        public ImmutableBooleanCollection toImmutable()
        {
            return BooleanLists.immutable.withAll(this);
        }

        public MutableBooleanCollection select(BooleanPredicate predicate)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.select(predicate);
        }

        public MutableBooleanCollection reject(BooleanPredicate predicate)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.reject(predicate);
        }

        public MutableBooleanIterator booleanIterator()
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.booleanIterator();
        }

        public boolean[] toArray()
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.toArray();
        }

        public <V> MutableCollection<V> collect(BooleanToObjectFunction<? extends V> function)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.collect(function);
        }

        public boolean contains(boolean value)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.containsValue(value);
        }

        public boolean containsAll(boolean... source)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.containsAll(source);
        }

        public boolean containsAll(BooleanIterable source)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.containsAll(source);
        }

        public void forEach(BooleanProcedure procedure)
        {
            ObjectBooleanHashMapWithHashingStrategy.this.forEach(procedure);
        }

        public void each(BooleanProcedure procedure)
        {
            ObjectBooleanHashMapWithHashingStrategy.this.forEach(procedure);
        }

        public boolean detectIfNone(BooleanPredicate predicate, boolean ifNone)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.detectIfNone(predicate, ifNone);
        }

        public int count(BooleanPredicate predicate)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.count(predicate);
        }

        public boolean anySatisfy(BooleanPredicate predicate)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.anySatisfy(predicate);
        }

        public boolean allSatisfy(BooleanPredicate predicate)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.allSatisfy(predicate);
        }

        public boolean noneSatisfy(BooleanPredicate predicate)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.noneSatisfy(predicate);
        }

        public MutableBooleanList toList()
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.toList();
        }

        public MutableBooleanSet toSet()
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.toSet();
        }

        public MutableBooleanBag toBag()
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.toBag();
        }

        public LazyBooleanIterable asLazy()
        {
            return new LazyBooleanIterableAdapter(this);
        }

        public <T> T injectInto(T injectedValue, ObjectBooleanToObjectFunction<? super T, ? extends T> function)
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.injectInto(injectedValue, function);
        }

        public int size()
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.size();
        }

        public boolean isEmpty()
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.isEmpty();
        }

        public boolean notEmpty()
        {
            return ObjectBooleanHashMapWithHashingStrategy.this.notEmpty();
        }

        public String makeString()
        {
            return this.makeString(", ");
        }

        public String makeString(String separator)
        {
            return this.makeString("", separator, "");
        }

        public String makeString(String start, String separator, String end)
        {
            Appendable stringBuilder = new StringBuilder();
            this.appendString(stringBuilder, start, separator, end);
            return stringBuilder.toString();
        }

        public void appendString(Appendable appendable)
        {
            this.appendString(appendable, ", ");
        }

        public void appendString(Appendable appendable, String separator)
        {
            this.appendString(appendable, "", separator, "");
        }

        public void appendString(Appendable appendable, String start, String separator, String end)
        {
            try
            {
                appendable.append(start);

                boolean first = true;

                for (int i = 0; i < ObjectBooleanHashMapWithHashingStrategy.this.keys.length; i++)
                {
                    Object key = ObjectBooleanHashMapWithHashingStrategy.this.keys[i];
                    if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(key))
                    {
                        if (!first)
                        {
                            appendable.append(separator);
                        }
                        appendable.append(String.valueOf(ObjectBooleanHashMapWithHashingStrategy.this.values.get(i)));
                        first = false;
                    }
                }
                appendable.append(end);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private class InternalBooleanIterator implements MutableBooleanIterator
    {
        private int count;
        private int position;

        public boolean hasNext()
        {
            return this.count != ObjectBooleanHashMapWithHashingStrategy.this.size();
        }

        public boolean next()
        {
            if (!this.hasNext())
            {
                throw new NoSuchElementException();
            }

            Object[] keys = ObjectBooleanHashMapWithHashingStrategy.this.keys;
            while (!ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(keys[this.position]))
            {
                this.position++;
            }
            boolean result = ObjectBooleanHashMapWithHashingStrategy.this.values.get(this.position);
            this.count++;
            this.position++;
            return result;
        }

        public void remove()
        {
            if (this.position == 0 || !ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[this.position - 1]))
            {
                throw new IllegalStateException();
            }
            ObjectBooleanHashMapWithHashingStrategy.this.remove(ObjectBooleanHashMapWithHashingStrategy.this.keys[this.position - 1]);
            this.count--;
        }
    }

    private class KeysView extends AbstractLazyIterable<K>
    {
        public void each(Procedure<? super K> procedure)
        {
            ObjectBooleanHashMapWithHashingStrategy.this.forEachKey(procedure);
        }

        @Override
        public void forEachWithIndex(ObjectIntProcedure<? super K> objectIntProcedure)
        {
            int index = 0;
            for (int i = 0; i < ObjectBooleanHashMapWithHashingStrategy.this.keys.length; i++)
            {
                if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[i]))
                {
                    objectIntProcedure.value(ObjectBooleanHashMapWithHashingStrategy.this.toNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[i]), index);
                    index++;
                }
            }
        }

        @Override
        public <P> void forEachWith(Procedure2<? super K, ? super P> procedure, P parameter)
        {
            for (int i = 0; i < ObjectBooleanHashMapWithHashingStrategy.this.keys.length; i++)
            {
                if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[i]))
                {
                    procedure.value(ObjectBooleanHashMapWithHashingStrategy.this.toNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[i]), parameter);
                }
            }
        }

        public Iterator<K> iterator()
        {
            return new InternalKeysViewIterator<K>();
        }

        public class InternalKeysViewIterator<K> implements Iterator<K>
        {
            private int count;
            private int position;

            public boolean hasNext()
            {
                return this.count != ObjectBooleanHashMapWithHashingStrategy.this.size();
            }

            public K next()
            {
                if (!this.hasNext())
                {
                    throw new NoSuchElementException();
                }

                Object[] keys = ObjectBooleanHashMapWithHashingStrategy.this.keys;
                while (!ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(keys[this.position]))
                {
                    this.position++;
                }
                K result = (K) ObjectBooleanHashMapWithHashingStrategy.this.keys[this.position];
                this.count++;
                this.position++;
                return result;
            }

            public void remove()
            {
                throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
            }
        }
    }

    private class KeyValuesView extends AbstractLazyIterable<ObjectBooleanPair<K>>
    {
        public void each(Procedure<? super ObjectBooleanPair<K>> procedure)
        {
            for (int i = 0; i < ObjectBooleanHashMapWithHashingStrategy.this.keys.length; i++)
            {
                if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[i]))
                {
                    procedure.value(PrimitiveTuples.pair(ObjectBooleanHashMapWithHashingStrategy.this.toNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[i]), ObjectBooleanHashMapWithHashingStrategy.this.values.get(i)));
                }
            }
        }

        @Override
        public void forEachWithIndex(ObjectIntProcedure<? super ObjectBooleanPair<K>> objectIntProcedure)
        {
            int index = 0;
            for (int i = 0; i < ObjectBooleanHashMapWithHashingStrategy.this.keys.length; i++)
            {
                if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[i]))
                {
                    objectIntProcedure.value(PrimitiveTuples.pair(ObjectBooleanHashMapWithHashingStrategy.this.toNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[i]), ObjectBooleanHashMapWithHashingStrategy.this.values.get(i)), index);
                    index++;
                }
            }
        }

        @Override
        public <P> void forEachWith(Procedure2<? super ObjectBooleanPair<K>, ? super P> procedure, P parameter)
        {
            for (int i = 0; i < ObjectBooleanHashMapWithHashingStrategy.this.keys.length; i++)
            {
                if (ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[i]))
                {
                    procedure.value(PrimitiveTuples.pair(ObjectBooleanHashMapWithHashingStrategy.this.toNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[i]), ObjectBooleanHashMapWithHashingStrategy.this.values.get(i)), parameter);
                }
            }
        }

        public Iterator<ObjectBooleanPair<K>> iterator()
        {
            return new InternalKeyValuesIterator();
        }

        public class InternalKeyValuesIterator implements Iterator<ObjectBooleanPair<K>>
        {
            private int count;
            private int position;

            public boolean hasNext()
            {
                return this.count != ObjectBooleanHashMapWithHashingStrategy.this.size();
            }

            public ObjectBooleanPair<K> next()
            {
                if (!this.hasNext())
                {
                    throw new NoSuchElementException();
                }

                Object[] keys = ObjectBooleanHashMapWithHashingStrategy.this.keys;
                while (!ObjectBooleanHashMapWithHashingStrategy.isNonSentinel(keys[this.position]))
                {
                    this.position++;
                }
                ObjectBooleanPair<K> result = PrimitiveTuples.pair(ObjectBooleanHashMapWithHashingStrategy.this.toNonSentinel(ObjectBooleanHashMapWithHashingStrategy.this.keys[this.position]), ObjectBooleanHashMapWithHashingStrategy.this.values.get(this.position));
                this.count++;
                this.position++;
                return result;
            }

            public void remove()
            {
                throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
            }
        }
    }
}
