/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.predicate.primitive.ObjectIntPredicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableObjectIntMap;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.Counter;
import org.eclipse.collections.impl.block.factory.primitive.IntToIntFunctions;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectIntHashMap;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * A HashBag is a MutableBag which uses a Map as its underlying data store.  Each key in the Map represents some item,
 * and the value in the map represents the current number of occurrences of that item.
 *
 * @since 1.0
 */
public class HashBag<T>
        extends AbstractMutableBag<T>
        implements Externalizable
{
    private static final long serialVersionUID = 1L;

    private MutableObjectIntMap<T> items;
    private int size;

    public HashBag()
    {
        this.items = ObjectIntHashMap.newMap();
    }

    public HashBag(int size)
    {
        this.items = new ObjectIntHashMap<T>(size);
    }

    private HashBag(MutableObjectIntMap<T> map)
    {
        this.items = map;
        this.size = (int) map.sum();
    }

    public static <E> HashBag<E> newBag()
    {
        return new HashBag<E>();
    }

    public static <E> HashBag<E> newBag(int size)
    {
        return new HashBag<E>(size);
    }

    public static <E> HashBag<E> newBag(Bag<? extends E> source)
    {
        HashBag<E> result = HashBag.newBag(source.sizeDistinct());
        result.addAllBag(source);
        return result;
    }

    public static <E> HashBag<E> newBag(Iterable<? extends E> source)
    {
        if (source instanceof Bag)
        {
            return HashBag.newBag((Bag<E>) source);
        }
        return HashBag.newBagWith((E[]) Iterate.toArray(source));
    }

    public static <E> HashBag<E> newBagWith(E... elements)
    {
        HashBag<E> result = HashBag.newBag();
        ArrayIterate.addAllTo(elements, result);
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends T> source)
    {
        if (source instanceof Bag)
        {
            return this.addAllBag((Bag<T>) source);
        }
        return super.addAll(source);
    }

    private boolean addAllBag(Bag<? extends T> source)
    {
        source.forEachWithOccurrences(new ObjectIntProcedure<T>()
        {
            public void value(T each, int occurrences)
            {
                HashBag.this.addOccurrences(each, occurrences);
            }
        });
        return source.notEmpty();
    }

    public void addOccurrences(T item, int occurrences)
    {
        if (occurrences < 0)
        {
            throw new IllegalArgumentException("Cannot add a negative number of occurrences");
        }
        if (occurrences > 0)
        {
            this.items.updateValue(item, 0, IntToIntFunctions.add(occurrences));
            this.size += occurrences;
        }
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (!(other instanceof Bag))
        {
            return false;
        }
        final Bag<?> bag = (Bag<?>) other;
        if (this.sizeDistinct() != bag.sizeDistinct())
        {
            return false;
        }

        return this.items.keyValuesView().allSatisfy(new Predicate<ObjectIntPair<T>>()
        {
            public boolean accept(ObjectIntPair<T> each)
            {
                return bag.occurrencesOf(each.getOne()) == each.getTwo();
            }
        });
    }

    @Override
    public int hashCode()
    {
        final Counter counter = new Counter();
        this.items.forEachKeyValue(new ObjectIntProcedure<T>()
        {
            public void value(T item, int count)
            {
                counter.add((item == null ? 0 : item.hashCode()) ^ count);
            }
        });
        return counter.getCount();
    }

    @Override
    protected RichIterable<T> getKeysView()
    {
        return this.items.keysView();
    }

    public int sizeDistinct()
    {
        return this.items.size();
    }

    public int occurrencesOf(Object item)
    {
        return this.items.get(item);
    }

    public void forEachWithOccurrences(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        this.items.forEachKeyValue(objectIntProcedure);
    }

    public MutableBag<T> selectByOccurrences(final IntPredicate predicate)
    {
        MutableObjectIntMap<T> map = this.items.select(new ObjectIntPredicate<T>()
        {
            public boolean accept(T each, int occurrences)
            {
                return predicate.accept(occurrences);
            }
        });
        return new HashBag<T>(map);
    }

    public MutableMap<T, Integer> toMapOfItemToCount()
    {
        final MutableMap<T, Integer> map = UnifiedMap.newMap(this.items.size());
        this.forEachWithOccurrences(new ObjectIntProcedure<T>()
        {
            public void value(T item, int count)
            {
                map.put(item, count);
            }
        });
        return map;
    }

    public boolean remove(Object item)
    {
        int newValue = this.items.updateValue((T) item, 0, IntToIntFunctions.decrement());
        if (newValue <= 0)
        {
            this.items.removeKey((T) item);
            if (newValue == -1)
            {
                return false;
            }
        }
        this.size--;
        return true;
    }

    public void clear()
    {
        this.items.clear();
        this.size = 0;
    }

    @Override
    public boolean isEmpty()
    {
        return this.items.isEmpty();
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        ((ObjectIntHashMap<T>) this.items).writeExternal(out);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.items = new ObjectIntHashMap<T>();
        ((ObjectIntHashMap<T>) this.items).readExternal(in);
        this.size = (int) this.items.sum();
    }

    public void each(final Procedure<? super T> procedure)
    {
        this.items.forEachKeyValue(new ObjectIntProcedure<T>()
        {
            public void value(T key, int count)
            {
                for (int i = 0; i < count; i++)
                {
                    procedure.value(key);
                }
            }
        });
    }

    @Override
    public void forEachWithIndex(final ObjectIntProcedure<? super T> objectIntProcedure)
    {
        final Counter index = new Counter();
        this.items.forEachKeyValue(new ObjectIntProcedure<T>()
        {
            public void value(T key, int count)
            {
                for (int i = 0; i < count; i++)
                {
                    objectIntProcedure.value(key, index.getCount());
                    index.increment();
                }
            }
        });
    }

    @Override
    public <P> void forEachWith(final Procedure2<? super T, ? super P> procedure, final P parameter)
    {
        this.items.forEachKeyValue(new ObjectIntProcedure<T>()
        {
            public void value(T key, int count)
            {
                for (int i = 0; i < count; i++)
                {
                    procedure.value(key, parameter);
                }
            }
        });
    }

    public Iterator<T> iterator()
    {
        return new InternalIterator();
    }

    public boolean removeOccurrences(Object item, int occurrences)
    {
        if (occurrences < 0)
        {
            throw new IllegalArgumentException("Cannot remove a negative number of occurrences");
        }

        if (occurrences == 0)
        {
            return false;
        }

        int newValue = this.items.updateValue((T) item, 0, IntToIntFunctions.subtract(occurrences));

        if (newValue <= 0)
        {
            this.size -= occurrences + newValue;
            this.items.remove(item);
            return newValue + occurrences != 0;
        }

        this.size -= occurrences;
        return true;
    }

    public boolean setOccurrences(T item, int occurrences)
    {
        if (occurrences < 0)
        {
            throw new IllegalArgumentException("Cannot set a negative number of occurrences");
        }

        int originalOccurrences = this.items.get(item);

        if (originalOccurrences == occurrences)
        {
            return false;
        }

        if (occurrences == 0)
        {
            this.items.remove(item);
        }
        else
        {
            this.items.put(item, occurrences);
        }

        this.size -= originalOccurrences - occurrences;
        return true;
    }

    public HashBag<T> without(T element)
    {
        this.remove(element);
        return this;
    }

    public MutableBag<T> newEmpty()
    {
        return HashBag.newBag();
    }

    public HashBag<T> with(T element)
    {
        this.add(element);
        return this;
    }

    public HashBag<T> withAll(Iterable<? extends T> iterable)
    {
        this.addAllIterable(iterable);
        return this;
    }

    public HashBag<T> withoutAll(Iterable<? extends T> iterable)
    {
        this.removeAllIterable(iterable);
        return this;
    }

    public boolean removeIf(Predicate<? super T> predicate)
    {
        boolean changed = false;
        for (Iterator<T> iterator = this.items.keySet().iterator(); iterator.hasNext(); )
        {
            T key = iterator.next();
            if (predicate.accept(key))
            {
                this.size -= this.items.get(key);
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    public <P> boolean removeIfWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        boolean changed = false;
        for (Iterator<T> iterator = this.items.keySet().iterator(); iterator.hasNext(); )
        {
            T key = iterator.next();
            if (predicate.accept(key, parameter))
            {
                this.size -= this.items.get(key);
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    public boolean removeAllIterable(Iterable<?> iterable)
    {
        int oldSize = this.size;
        if (iterable instanceof Bag)
        {
            Bag<?> source = (Bag<?>) iterable;
            source.forEachWithOccurrences(new ObjectIntProcedure<Object>()
            {
                public void value(Object each, int parameter)
                {
                    int removed = HashBag.this.items.removeKeyIfAbsent((T) each, 0);
                    HashBag.this.size -= removed;
                }
            });
        }
        else
        {
            for (Object each : iterable)
            {
                int removed = this.items.removeKeyIfAbsent((T) each, 0);
                this.size -= removed;
            }
        }
        return this.size != oldSize;
    }

    public int size()
    {
        return this.size;
    }

    @Override
    public boolean contains(Object o)
    {
        return this.items.containsKey(o);
    }

    public <V> HashBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.groupBy(function, HashBagMultimap.<V, T>newMultimap());
    }

    public <V> HashBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.groupByEach(function, HashBagMultimap.<V, T>newMultimap());
    }

    public HashBag<T> with(T... elements)
    {
        this.addAll(Arrays.asList(elements));
        return this;
    }

    public HashBag<T> with(T element1, T element2)
    {
        this.add(element1);
        this.add(element2);
        return this;
    }

    public boolean add(T item)
    {
        this.items.updateValue(item, 0, IntToIntFunctions.increment());
        this.size++;
        return true;
    }

    public HashBag<T> with(T element1, T element2, T element3)
    {
        this.add(element1);
        this.add(element2);
        this.add(element3);
        return this;
    }

    private class InternalIterator implements Iterator<T>
    {
        private final Iterator<T> iterator = HashBag.this.items.keySet().iterator();

        private T currentItem;
        private int occurrences;
        private boolean canRemove;

        public boolean hasNext()
        {
            return this.occurrences > 0 || this.iterator.hasNext();
        }

        public T next()
        {
            if (this.occurrences == 0)
            {
                this.currentItem = this.iterator.next();
                this.occurrences = HashBag.this.occurrencesOf(this.currentItem);
            }
            this.occurrences--;
            this.canRemove = true;
            return this.currentItem;
        }

        public void remove()
        {
            if (!this.canRemove)
            {
                throw new IllegalStateException();
            }
            if (this.occurrences == 0)
            {
                this.iterator.remove();
                HashBag.this.size--;
            }
            else
            {
                HashBag.this.remove(this.currentItem);
            }
            this.canRemove = false;
        }
    }
}
