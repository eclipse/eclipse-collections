/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import java.io.IOException;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.block.function.primitive.BooleanToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectBooleanToObjectFunction;
import org.eclipse.collections.api.block.predicate.primitive.BooleanPredicate;
import org.eclipse.collections.api.block.procedure.primitive.BooleanProcedure;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.map.primitive.MutableBooleanValuesMap;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.collection.mutable.primitive.SynchronizedBooleanCollection;
import org.eclipse.collections.impl.collection.mutable.primitive.UnmodifiableBooleanCollection;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.BooleanBags;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.lazy.primitive.LazyBooleanIterableAdapter;
import org.eclipse.collections.impl.primitive.AbstractBooleanIterable;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;

public abstract class AbstractMutableBooleanValuesMap extends AbstractBooleanIterable implements MutableBooleanValuesMap
{
    protected abstract int getOccupiedWithData();

    protected abstract SentinelValues getSentinelValues();

    protected abstract void setSentinelValuesNull();

    protected abstract boolean getEmptyValue();

    protected abstract boolean getValueAtIndex(int index);

    protected abstract int getTableSize();

    protected abstract boolean isNonSentinelAtIndex(int index);

    protected void addEmptyKeyValue(boolean value)
    {
        this.getSentinelValues().containsZeroKey = true;
        this.getSentinelValues().zeroValue = value;
    }

    protected void removeEmptyKey()
    {
        if (this.getSentinelValues().containsOneKey)
        {
            this.getSentinelValues().containsZeroKey = false;
            this.getSentinelValues().zeroValue = this.getEmptyValue();
        }
        else
        {
            this.setSentinelValuesNull();
        }
    }

    protected void addRemovedKeyValue(boolean value)
    {
        this.getSentinelValues().containsOneKey = true;
        this.getSentinelValues().oneValue = value;
    }

    protected void removeRemovedKey()
    {
        if (this.getSentinelValues().containsZeroKey)
        {
            this.getSentinelValues().containsOneKey = false;
            this.getSentinelValues().oneValue = this.getEmptyValue();
        }
        else
        {
            this.setSentinelValuesNull();
        }
    }

    @Override
    public boolean contains(boolean value)
    {
        return this.containsValue(value);
    }

    @Override
    public boolean containsAll(BooleanIterable source)
    {
        return source.allSatisfy(this::contains);
    }

    @Override
    public int size()
    {
        return this.getOccupiedWithData() + (this.getSentinelValues() == null ? 0 : this.getSentinelValues().size());
    }

    @Override
    public boolean isEmpty()
    {
        return this.getOccupiedWithData() == 0 && (this.getSentinelValues() == null || this.getSentinelValues().size() == 0);
    }

    @Override
    public boolean notEmpty()
    {
        return this.getOccupiedWithData() != 0 || (this.getSentinelValues() != null && this.getSentinelValues().size() != 0);
    }

    @Override
    public void forEach(BooleanProcedure procedure)
    {
        this.each(procedure);
    }

    /**
     * @since 7.0.
     */
    @Override
    public void each(BooleanProcedure procedure)
    {
        this.forEachValue(procedure);
    }

    @Override
    public void forEachValue(BooleanProcedure procedure)
    {
        if (this.getSentinelValues() != null)
        {
            if (this.getSentinelValues().containsZeroKey)
            {
                procedure.value(this.getSentinelValues().zeroValue);
            }
            if (this.getSentinelValues().containsOneKey)
            {
                procedure.value(this.getSentinelValues().oneValue);
            }
        }
        for (int i = 0; i < this.getTableSize(); i++)
        {
            if (this.isNonSentinelAtIndex(i))
            {
                procedure.value(this.getValueAtIndex(i));
            }
        }
    }

    @Override
    public <V> V injectInto(V injectedValue, ObjectBooleanToObjectFunction<? super V, ? extends V> function)
    {
        V result = injectedValue;

        if (this.getSentinelValues() != null)
        {
            if (this.getSentinelValues().containsZeroKey)
            {
                result = function.valueOf(result, this.getSentinelValues().zeroValue);
            }
            if (this.getSentinelValues().containsOneKey)
            {
                result = function.valueOf(result, this.getSentinelValues().oneValue);
            }
        }

        for (int i = 0; i < this.getTableSize(); i++)
        {
            if (this.isNonSentinelAtIndex(i))
            {
                result = function.valueOf(result, this.getValueAtIndex(i));
            }
        }

        return result;
    }

    @Override
    public RichIterable<BooleanIterable> chunk(int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Size for groups must be positive but was: " + size);
        }
        MutableList<BooleanIterable> result = Lists.mutable.empty();
        if (this.notEmpty())
        {
            BooleanIterator iterator = this.booleanIterator();
            while (iterator.hasNext())
            {
                MutableBooleanBag batch = BooleanBags.mutable.empty();
                for (int i = 0; i < size && iterator.hasNext(); i++)
                {
                    batch.add(iterator.next());
                }
                result.add(batch);
            }
        }
        return result;
    }

    @Override
    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        try
        {
            appendable.append(start);

            boolean first = true;

            if (this.getSentinelValues() != null)
            {
                if (this.getSentinelValues().containsZeroKey)
                {
                    appendable.append(String.valueOf(this.getSentinelValues().zeroValue));
                    first = false;
                }
                if (this.getSentinelValues().containsOneKey)
                {
                    if (!first)
                    {
                        appendable.append(separator);
                    }
                    appendable.append(String.valueOf(this.getSentinelValues().oneValue));
                    first = false;
                }
            }
            for (int i = 0; i < this.getTableSize(); i++)
            {
                if (this.isNonSentinelAtIndex(i))
                {
                    if (!first)
                    {
                        appendable.append(separator);
                    }
                    appendable.append(String.valueOf(this.getValueAtIndex(i)));
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

    @Override
    public MutableBooleanBag select(BooleanPredicate predicate)
    {
        MutableBooleanBag result = BooleanBags.mutable.empty();

        if (this.getSentinelValues() != null)
        {
            if (this.getSentinelValues().containsZeroKey && predicate.accept(this.getSentinelValues().zeroValue))
            {
                result.add(this.getSentinelValues().zeroValue);
            }
            if (this.getSentinelValues().containsOneKey && predicate.accept(this.getSentinelValues().oneValue))
            {
                result.add(this.getSentinelValues().oneValue);
            }
        }
        for (int i = 0; i < this.getTableSize(); i++)
        {
            if (this.isNonSentinelAtIndex(i) && predicate.accept(this.getValueAtIndex(i)))
            {
                result.add(this.getValueAtIndex(i));
            }
        }

        return result;
    }

    @Override
    public MutableBooleanBag reject(BooleanPredicate predicate)
    {
        MutableBooleanBag result = BooleanBags.mutable.empty();
        if (this.getSentinelValues() != null)
        {
            if (this.getSentinelValues().containsZeroKey && !predicate.accept(this.getSentinelValues().zeroValue))
            {
                result.add(this.getSentinelValues().zeroValue);
            }
            if (this.getSentinelValues().containsOneKey && !predicate.accept(this.getSentinelValues().oneValue))
            {
                result.add(this.getSentinelValues().oneValue);
            }
        }
        for (int i = 0; i < this.getTableSize(); i++)
        {
            if (this.isNonSentinelAtIndex(i) && !predicate.accept(this.getValueAtIndex(i)))
            {
                result.add(this.getValueAtIndex(i));
            }
        }
        return result;
    }

    @Override
    public <V> MutableBag<V> collect(BooleanToObjectFunction<? extends V> function)
    {
        MutableBag<V> target = HashBag.newBag(this.size());
        if (this.getSentinelValues() != null)
        {
            if (this.getSentinelValues().containsZeroKey)
            {
                target.add(function.valueOf(this.getSentinelValues().zeroValue));
            }
            if (this.getSentinelValues().containsOneKey)
            {
                target.add(function.valueOf(this.getSentinelValues().oneValue));
            }
        }
        for (int i = 0; i < this.getTableSize(); i++)
        {
            if (this.isNonSentinelAtIndex(i))
            {
                target.add(function.valueOf(this.getValueAtIndex(i)));
            }
        }
        return target;
    }

    @Override
    public boolean detectIfNone(BooleanPredicate predicate, boolean value)
    {
        if (this.getSentinelValues() != null)
        {
            if (this.getSentinelValues().containsZeroKey && predicate.accept(this.getSentinelValues().zeroValue))
            {
                return this.getSentinelValues().zeroValue;
            }
            if (this.getSentinelValues().containsOneKey && predicate.accept(this.getSentinelValues().oneValue))
            {
                return this.getSentinelValues().oneValue;
            }
        }
        for (int i = 0; i < this.getTableSize(); i++)
        {
            if (this.isNonSentinelAtIndex(i) && predicate.accept(this.getValueAtIndex(i)))
            {
                return this.getValueAtIndex(i);
            }
        }
        return value;
    }

    @Override
    public int count(BooleanPredicate predicate)
    {
        int count = 0;
        if (this.getSentinelValues() != null)
        {
            if (this.getSentinelValues().containsZeroKey && predicate.accept(this.getSentinelValues().zeroValue))
            {
                count++;
            }
            if (this.getSentinelValues().containsOneKey && predicate.accept(this.getSentinelValues().oneValue))
            {
                count++;
            }
        }
        for (int i = 0; i < this.getTableSize(); i++)
        {
            if (this.isNonSentinelAtIndex(i) && predicate.accept(this.getValueAtIndex(i)))
            {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean anySatisfy(BooleanPredicate predicate)
    {
        if (this.getSentinelValues() != null)
        {
            if (this.getSentinelValues().containsZeroKey && predicate.accept(this.getSentinelValues().zeroValue))
            {
                return true;
            }
            if (this.getSentinelValues().containsOneKey && predicate.accept(this.getSentinelValues().oneValue))
            {
                return true;
            }
        }
        for (int i = 0; i < this.getTableSize(); i++)
        {
            if (this.isNonSentinelAtIndex(i) && predicate.accept(this.getValueAtIndex(i)))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean allSatisfy(BooleanPredicate predicate)
    {
        if (this.getSentinelValues() != null)
        {
            if (this.getSentinelValues().containsZeroKey && !predicate.accept(this.getSentinelValues().zeroValue))
            {
                return false;
            }
            if (this.getSentinelValues().containsOneKey && !predicate.accept(this.getSentinelValues().oneValue))
            {
                return false;
            }
        }
        for (int i = 0; i < this.getTableSize(); i++)
        {
            if (this.isNonSentinelAtIndex(i) && !predicate.accept(this.getValueAtIndex(i)))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean noneSatisfy(BooleanPredicate predicate)
    {
        return !this.anySatisfy(predicate);
    }

    @Override
    public boolean[] toArray()
    {
        boolean[] array = new boolean[this.size()];
        int index = 0;

        if (this.getSentinelValues() != null)
        {
            if (this.getSentinelValues().containsZeroKey)
            {
                array[index] = this.getSentinelValues().zeroValue;
                index++;
            }
            if (this.getSentinelValues().containsOneKey)
            {
                array[index] = this.getSentinelValues().oneValue;
                index++;
            }
        }
        for (int i = 0; i < this.getTableSize(); i++)
        {
            if (this.isNonSentinelAtIndex(i))
            {
                array[index] = this.getValueAtIndex(i);
                index++;
            }
        }

        return array;
    }

    protected static class SentinelValues extends AbstractSentinelValues
    {
        protected boolean zeroValue;
        protected boolean oneValue;

        public boolean containsValue(boolean value)
        {
            boolean valueEqualsZeroValue = this.containsZeroKey && this.zeroValue == value;
            boolean valueEqualsOneValue = this.containsOneKey && this.oneValue == value;
            return valueEqualsZeroValue || valueEqualsOneValue;
        }
    }

    protected abstract class AbstractBooleanValuesCollection implements MutableBooleanCollection
    {
        @Override
        public void clear()
        {
            AbstractMutableBooleanValuesMap.this.clear();
        }

        @Override
        public MutableBooleanCollection select(BooleanPredicate predicate)
        {
            return AbstractMutableBooleanValuesMap.this.select(predicate);
        }

        @Override
        public MutableBooleanCollection reject(BooleanPredicate predicate)
        {
            return AbstractMutableBooleanValuesMap.this.reject(predicate);
        }

        @Override
        public boolean detectIfNone(BooleanPredicate predicate, boolean ifNone)
        {
            return AbstractMutableBooleanValuesMap.this.detectIfNone(predicate, ifNone);
        }

        @Override
        public <V> MutableCollection<V> collect(BooleanToObjectFunction<? extends V> function)
        {
            return AbstractMutableBooleanValuesMap.this.collect(function);
        }

        @Override
        public <T> T injectInto(T injectedValue, ObjectBooleanToObjectFunction<? super T, ? extends T> function)
        {
            return AbstractMutableBooleanValuesMap.this.injectInto(injectedValue, function);
        }

        @Override
        public RichIterable<BooleanIterable> chunk(int size)
        {
            return AbstractMutableBooleanValuesMap.this.chunk(size);
        }

        @Override
        public MutableBooleanCollection with(boolean element)
        {
            throw new UnsupportedOperationException("Cannot call with() on " + this.getClass().getSimpleName());
        }

        @Override
        public MutableBooleanCollection without(boolean element)
        {
            throw new UnsupportedOperationException("Cannot call without() on " + this.getClass().getSimpleName());
        }

        @Override
        public MutableBooleanCollection withAll(BooleanIterable elements)
        {
            throw new UnsupportedOperationException("Cannot call withAll() on " + this.getClass().getSimpleName());
        }

        @Override
        public MutableBooleanCollection withoutAll(BooleanIterable elements)
        {
            throw new UnsupportedOperationException("Cannot call withoutAll() on " + this.getClass().getSimpleName());
        }

        @Override
        public MutableBooleanCollection asUnmodifiable()
        {
            return UnmodifiableBooleanCollection.of(this);
        }

        @Override
        public MutableBooleanCollection asSynchronized()
        {
            return SynchronizedBooleanCollection.of(this);
        }

        @Override
        public ImmutableBooleanCollection toImmutable()
        {
            return BooleanLists.immutable.withAll(this);
        }

        @Override
        public boolean contains(boolean value)
        {
            return AbstractMutableBooleanValuesMap.this.containsValue(value);
        }

        @Override
        public boolean containsAll(boolean... source)
        {
            return AbstractMutableBooleanValuesMap.this.containsAll(source);
        }

        @Override
        public boolean containsAll(BooleanIterable source)
        {
            return AbstractMutableBooleanValuesMap.this.containsAll(source);
        }

        @Override
        public MutableBooleanList toList()
        {
            return AbstractMutableBooleanValuesMap.this.toList();
        }

        @Override
        public MutableBooleanSet toSet()
        {
            return AbstractMutableBooleanValuesMap.this.toSet();
        }

        @Override
        public MutableBooleanBag toBag()
        {
            return AbstractMutableBooleanValuesMap.this.toBag();
        }

        @Override
        public LazyBooleanIterable asLazy()
        {
            return new LazyBooleanIterableAdapter(this);
        }

        @Override
        public boolean isEmpty()
        {
            return AbstractMutableBooleanValuesMap.this.isEmpty();
        }

        @Override
        public boolean notEmpty()
        {
            return AbstractMutableBooleanValuesMap.this.notEmpty();
        }

        @Override
        public String makeString()
        {
            return AbstractMutableBooleanValuesMap.this.makeString();
        }

        @Override
        public String makeString(String separator)
        {
            return AbstractMutableBooleanValuesMap.this.makeString(separator);
        }

        @Override
        public String makeString(String start, String separator, String end)
        {
            return AbstractMutableBooleanValuesMap.this.makeString(start, separator, end);
        }

        @Override
        public void appendString(Appendable appendable)
        {
            AbstractMutableBooleanValuesMap.this.appendString(appendable);
        }

        @Override
        public void appendString(Appendable appendable, String separator)
        {
            AbstractMutableBooleanValuesMap.this.appendString(appendable, separator);
        }

        @Override
        public void forEach(BooleanProcedure procedure)
        {
            this.each(procedure);
        }

        @Override
        public void each(BooleanProcedure procedure)
        {
            AbstractMutableBooleanValuesMap.this.forEach(procedure);
        }

        @Override
        public int count(BooleanPredicate predicate)
        {
            return AbstractMutableBooleanValuesMap.this.count(predicate);
        }

        @Override
        public boolean anySatisfy(BooleanPredicate predicate)
        {
            return AbstractMutableBooleanValuesMap.this.anySatisfy(predicate);
        }

        @Override
        public boolean allSatisfy(BooleanPredicate predicate)
        {
            return AbstractMutableBooleanValuesMap.this.allSatisfy(predicate);
        }

        @Override
        public boolean noneSatisfy(BooleanPredicate predicate)
        {
            return AbstractMutableBooleanValuesMap.this.noneSatisfy(predicate);
        }

        @Override
        public boolean add(boolean element)
        {
            throw new UnsupportedOperationException("Cannot call add() on " + this.getClass().getSimpleName());
        }

        @Override
        public boolean addAll(boolean... source)
        {
            throw new UnsupportedOperationException("Cannot call addAll() on " + this.getClass().getSimpleName());
        }

        @Override
        public boolean addAll(BooleanIterable source)
        {
            throw new UnsupportedOperationException("Cannot call addAll() on " + this.getClass().getSimpleName());
        }

        @Override
        public boolean removeAll(BooleanIterable source)
        {
            int oldSize = AbstractMutableBooleanValuesMap.this.size();

            BooleanIterator iterator = source.booleanIterator();
            while (iterator.hasNext())
            {
                this.remove(iterator.next());
            }
            return oldSize != AbstractMutableBooleanValuesMap.this.size();
        }

        @Override
        public boolean removeAll(boolean... source)
        {
            int oldSize = AbstractMutableBooleanValuesMap.this.size();

            for (boolean item : source)
            {
                this.remove(item);
            }
            return oldSize != AbstractMutableBooleanValuesMap.this.size();
        }

        @Override
        public boolean retainAll(boolean... source)
        {
            return this.retainAll(BooleanHashSet.newSetWith(source));
        }

        @Override
        public int size()
        {
            return AbstractMutableBooleanValuesMap.this.size();
        }

        @Override
        public boolean[] toArray()
        {
            return AbstractMutableBooleanValuesMap.this.toArray();
        }
    }
}
