/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable.primitive;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.NoSuchElementException;

import net.jcip.annotations.NotThreadSafe;
import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.BooleanBag;
import org.eclipse.collections.api.bag.primitive.ImmutableBooleanBag;
import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.block.function.primitive.BooleanToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectBooleanToObjectFunction;
import org.eclipse.collections.api.block.predicate.primitive.BooleanPredicate;
import org.eclipse.collections.api.block.procedure.primitive.BooleanIntProcedure;
import org.eclipse.collections.api.block.procedure.primitive.BooleanProcedure;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.set.primitive.BooleanSet;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.factory.primitive.BooleanBags;
import org.eclipse.collections.impl.lazy.primitive.LazyBooleanIterableAdapter;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;

/**
 * BooleanHashBag is similar to {@link HashBag}, and is memory-optimized for boolean primitives.
 *
 * @since 3.0.
 */
@NotThreadSafe
public final class BooleanHashBag implements MutableBooleanBag, Externalizable
{
    private static final long serialVersionUID = 1L;

    private int falseCount;
    private int trueCount;

    public BooleanHashBag()
    {
    }

    public BooleanHashBag(BooleanIterable iterable)
    {
        this();
        this.addAll(iterable);
    }

    public BooleanHashBag(boolean... elements)
    {
        this();
        this.addAll(elements);
    }

    public BooleanHashBag(BooleanHashBag bag)
    {
        this.falseCount = bag.falseCount;
        this.trueCount = bag.trueCount;
    }

    public static BooleanHashBag newBagWith(boolean... source)
    {
        BooleanHashBag result = new BooleanHashBag();
        result.addAll(source);
        return result;
    }

    public static BooleanHashBag newBag(BooleanIterable source)
    {
        if (source instanceof BooleanHashBag)
        {
            return new BooleanHashBag((BooleanHashBag) source);
        }

        return new BooleanHashBag(source);
    }

    private boolean containsFalse()
    {
        return this.falseCount > 0;
    }

    private boolean containsTrue()
    {
        return this.trueCount > 0;
    }

    public boolean isEmpty()
    {
        return !this.containsFalse() && !this.containsTrue();
    }

    public boolean notEmpty()
    {
        return this.containsFalse() || this.containsTrue();
    }

    public int size()
    {
        return this.falseCount + this.trueCount;
    }

    public int sizeDistinct()
    {
        return (this.containsFalse() ? 1 : 0) + (this.containsTrue() ? 1 : 0);
    }

    public void clear()
    {
        this.falseCount = 0;
        this.trueCount = 0;
    }

    public BooleanHashBag with(boolean element)
    {
        this.add(element);
        return this;
    }

    public BooleanHashBag with(boolean element1, boolean element2)
    {
        this.add(element1);
        this.add(element2);
        return this;
    }

    public BooleanHashBag with(boolean element1, boolean element2, boolean element3)
    {
        this.add(element1);
        this.add(element2);
        this.add(element3);
        return this;
    }

    public BooleanHashBag withAll(BooleanIterable iterable)
    {
        this.addAll(iterable);
        return this;
    }

    public BooleanHashBag without(boolean element)
    {
        this.remove(element);
        return this;
    }

    public BooleanHashBag withoutAll(BooleanIterable iterable)
    {
        this.removeAll(iterable);
        return this;
    }

    public MutableBooleanBag asUnmodifiable()
    {
        return new UnmodifiableBooleanBag(this);
    }

    public MutableBooleanBag asSynchronized()
    {
        return new SynchronizedBooleanBag(this);
    }

    public ImmutableBooleanBag toImmutable()
    {
        return BooleanBags.immutable.withAll(this);
    }

    public boolean contains(boolean value)
    {
        return value ? this.containsTrue() : this.containsFalse();
    }

    public boolean containsAll(boolean... source)
    {
        for (boolean each : source)
        {
            if (!this.contains(each))
            {
                return false;
            }
        }
        return true;
    }

    public boolean containsAll(BooleanIterable source)
    {
        return source.allSatisfy(this::contains);
    }

    public int occurrencesOf(boolean item)
    {
        return item ? this.trueCount : this.falseCount;
    }

    public void forEachWithOccurrences(BooleanIntProcedure procedure)
    {
        if (this.containsFalse())
        {
            procedure.value(false, this.falseCount);
        }
        if (this.containsTrue())
        {
            procedure.value(true, this.trueCount);
        }
    }

    public boolean add(boolean item)
    {
        if (item)
        {
            this.trueCount++;
        }
        else
        {
            this.falseCount++;
        }
        return true;
    }

    public boolean remove(boolean item)
    {
        if (item)
        {
            if (!this.containsTrue())
            {
                return false;
            }
            this.trueCount--;
        }
        else
        {
            if (!this.containsFalse())
            {
                return false;
            }
            this.falseCount--;
        }
        return true;
    }

    public boolean addAll(boolean... source)
    {
        if (source.length < 1)
        {
            return false;
        }

        for (boolean each : source)
        {
            this.add(each);
        }
        return true;
    }

    public boolean addAll(BooleanIterable source)
    {
        if (source.isEmpty())
        {
            return false;
        }
        if (source instanceof BooleanBag)
        {
            BooleanBag otherBag = (BooleanBag) source;
            otherBag.forEachWithOccurrences(this::addOccurrences);
        }
        else
        {
            BooleanIterator iterator = source.booleanIterator();
            while (iterator.hasNext())
            {
                boolean each = iterator.next();
                this.add(each);
            }
        }
        return true;
    }

    public boolean removeAll(boolean... source)
    {
        if (source.length == 0)
        {
            return false;
        }
        int oldSize = this.size();
        for (boolean each : source)
        {
            if (each)
            {
                this.trueCount = 0;
            }
            else
            {
                this.falseCount = 0;
            }
        }
        return this.size() != oldSize;
    }

    public boolean removeAll(BooleanIterable source)
    {
        if (source.isEmpty())
        {
            return false;
        }
        int oldSize = this.size();
        if (source instanceof BooleanBag)
        {
            BooleanBag otherBag = (BooleanBag) source;
            otherBag.forEachWithOccurrences((each, occurrences) -> {
                if (each)
                {
                    BooleanHashBag.this.trueCount = 0;
                }
                else
                {
                    BooleanHashBag.this.falseCount = 0;
                }
            });
        }
        else
        {
            BooleanIterator iterator = source.booleanIterator();
            while (iterator.hasNext())
            {
                boolean each = iterator.next();
                if (each)
                {
                    this.trueCount = 0;
                }
                else
                {
                    this.falseCount = 0;
                }
            }
        }
        return this.size() != oldSize;
    }

    public boolean retainAll(BooleanIterable elements)
    {
        int oldSize = this.size();
        BooleanSet set = elements instanceof BooleanSet ? (BooleanSet) elements : elements.toSet();
        if (!set.contains(true) && this.containsTrue())
        {
            this.trueCount = 0;
        }
        if (!set.contains(false) && this.containsFalse())
        {
            this.falseCount = 0;
        }
        return this.size() != oldSize;
    }

    public boolean retainAll(boolean... source)
    {
        return this.retainAll(BooleanHashSet.newSetWith(source));
    }

    public void addOccurrences(boolean item, int occurrences)
    {
        if (occurrences < 0)
        {
            throw new IllegalArgumentException("Cannot add a negative number of occurrences");
        }
        if (occurrences > 0)
        {
            if (item)
            {
                this.trueCount += occurrences;
            }
            else
            {
                this.falseCount += occurrences;
            }
        }
    }

    public boolean removeOccurrences(boolean item, int occurrences)
    {
        if (occurrences < 0)
        {
            throw new IllegalArgumentException("Cannot remove a negative number of occurrences");
        }

        if (occurrences == 0)
        {
            return false;
        }

        if (item)
        {
            if (!this.containsTrue())
            {
                return false;
            }
            this.trueCount -= occurrences;
            if (this.trueCount < 0)
            {
                this.trueCount = 0;
            }
        }
        else
        {
            if (!this.containsFalse())
            {
                return false;
            }
            this.falseCount -= occurrences;
            if (this.falseCount < 0)
            {
                this.falseCount = 0;
            }
        }
        return true;
    }

    public void forEach(BooleanProcedure procedure)
    {
        this.each(procedure);
    }

    /**
     * @since 7.0.
     */
    public void each(BooleanProcedure procedure)
    {
        for (int i = 0; i < this.falseCount; i++)
        {
            procedure.value(false);
        }
        for (int i = 0; i < this.trueCount; i++)
        {
            procedure.value(true);
        }
    }

    public MutableBooleanBag select(final BooleanPredicate predicate)
    {
        final MutableBooleanBag result = new BooleanHashBag();
        this.forEachWithOccurrences((each, occurrences) -> {
            if (predicate.accept(each))
            {
                result.addOccurrences(each, occurrences);
            }
        });
        return result;
    }

    public MutableBooleanBag reject(final BooleanPredicate predicate)
    {
        final MutableBooleanBag result = new BooleanHashBag();
        this.forEachWithOccurrences((each, occurrences) -> {
            if (!predicate.accept(each))
            {
                result.addOccurrences(each, occurrences);
            }
        });
        return result;
    }

    public <T> T injectInto(T injectedValue, ObjectBooleanToObjectFunction<? super T, ? extends T> function)
    {
        T result = injectedValue;
        BooleanIterator it = this.booleanIterator();
        while (it.hasNext())
        {
            result = function.valueOf(result, it.next());
        }
        return result;
    }

    @Override
    public boolean equals(Object otherBag)
    {
        if (otherBag == this)
        {
            return true;
        }
        if (!(otherBag instanceof BooleanBag))
        {
            return false;
        }
        BooleanBag bag = (BooleanBag) otherBag;
        if (this.sizeDistinct() != bag.sizeDistinct())
        {
            return false;
        }

        return this.falseCount == bag.occurrencesOf(false) && this.trueCount == bag.occurrencesOf(true);
    }

    @Override
    public int hashCode()
    {
        int result = 0;

        if (this.containsFalse())
        {
            result = 1237 ^ this.falseCount;
        }
        if (this.containsTrue())
        {
            result += 1231 ^ this.trueCount;
        }

        return result;
    }

    @Override
    public String toString()
    {
        return this.makeString("[", ", ", "]");
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

    public void appendString(
            Appendable appendable,
            String start,
            String separator,
            String end)
    {
        try
        {
            appendable.append(start);
            boolean firstItem = true;
            for (int i = 0; i < this.falseCount; i++)
            {
                if (!firstItem)
                {
                    appendable.append(separator);
                }
                appendable.append("false");
                firstItem = false;
            }
            for (int i = 0; i < this.trueCount; i++)
            {
                if (!firstItem)
                {
                    appendable.append(separator);
                }
                appendable.append("true");
                firstItem = false;
            }
            appendable.append(end);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public int count(BooleanPredicate predicate)
    {
        int result = 0;
        if (this.containsFalse() && predicate.accept(false))
        {
            result = this.falseCount;
        }
        if (this.containsTrue() && predicate.accept(true))
        {
            result += this.trueCount;
        }

        return result;
    }

    public boolean anySatisfy(BooleanPredicate predicate)
    {
        return this.containsFalse() && predicate.accept(false) || this.containsTrue() && predicate.accept(true);
    }

    public boolean allSatisfy(BooleanPredicate predicate)
    {
        return (!this.containsFalse() || predicate.accept(false)) && (!this.containsTrue() || predicate.accept(true));
    }

    public boolean noneSatisfy(BooleanPredicate predicate)
    {
        return (!this.containsFalse() || !predicate.accept(false)) && (!this.containsTrue() || !predicate.accept(true));
    }

    public boolean detectIfNone(BooleanPredicate predicate, boolean ifNone)
    {
        if (this.containsFalse() && predicate.accept(false))
        {
            return false;
        }

        if (this.containsTrue() && predicate.accept(true))
        {
            return true;
        }

        return ifNone;
    }

    public <V> MutableBag<V> collect(BooleanToObjectFunction<? extends V> function)
    {
        HashBag<V> result = HashBag.newBag();
        if (this.containsFalse())
        {
            result.addOccurrences(function.valueOf(false), this.falseCount);
        }
        if (this.containsTrue())
        {
            result.addOccurrences(function.valueOf(true), this.trueCount);
        }
        return result;
    }

    public boolean[] toArray()
    {
        final boolean[] array = new boolean[this.size()];
        final int[] index = {0};

        this.forEachWithOccurrences((each, occurrences) -> {
            for (int i = 0; i < occurrences; i++)
            {
                array[index[0]] = each;
                index[0]++;
            }
        });
        return array;
    }

    public MutableBooleanList toList()
    {
        return BooleanArrayList.newList(this);
    }

    public MutableBooleanSet toSet()
    {
        return BooleanHashSet.newSet(this);
    }

    public MutableBooleanBag toBag()
    {
        return BooleanHashBag.newBag(this);
    }

    public LazyBooleanIterable asLazy()
    {
        return new LazyBooleanIterableAdapter(this);
    }

    public MutableBooleanIterator booleanIterator()
    {
        return new InternalIterator(this.falseCount, this.trueCount);
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeInt(this.falseCount);
        out.writeInt(this.trueCount);
    }

    public void readExternal(ObjectInput in) throws IOException
    {
        this.falseCount = in.readInt();
        this.trueCount = in.readInt();
    }

    private final class InternalIterator implements MutableBooleanIterator
    {
        private int internalFalseCount;
        private int internalTrueCount;
        private boolean lastBooleanValue;
        private boolean removedAlready = true;

        private InternalIterator(int falseCount, int trueCount)
        {
            this.internalFalseCount = falseCount;
            this.internalTrueCount = trueCount;
        }

        public boolean hasNext()
        {
            return this.internalFalseCount > 0 || this.internalTrueCount > 0;
        }

        public boolean next()
        {
            this.removedAlready = false;
            if (this.internalFalseCount > 0)
            {
                this.internalFalseCount--;
                this.lastBooleanValue = false;
                return false;
            }
            if (this.internalTrueCount > 0)
            {
                this.internalTrueCount--;
                this.lastBooleanValue = true;
                return true;
            }
            throw new NoSuchElementException("next() called, but the iterator is exhausted");
        }

        public void remove()
        {
            if (this.removedAlready)
            {
                throw new IllegalStateException();
            }
            if (this.lastBooleanValue)
            {
                BooleanHashBag.this.trueCount--;
            }
            else
            {
                BooleanHashBag.this.falseCount--;
            }
            this.removedAlready = true;
        }
    }
}
