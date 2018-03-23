/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.primitive;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.LazyIntIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.block.function.primitive.IntToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectIntIntToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectIntToObjectFunction;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.procedure.primitive.IntIntProcedure;
import org.eclipse.collections.api.block.procedure.primitive.IntProcedure;
import org.eclipse.collections.api.iterator.IntIterator;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.tuple.primitive.IntIntPair;
import org.eclipse.collections.api.tuple.primitive.IntObjectPair;
import org.eclipse.collections.impl.bag.mutable.primitive.IntHashBag;
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.lazy.primitive.CollectIntToObjectIterable;
import org.eclipse.collections.impl.lazy.primitive.LazyIntIterableAdapter;
import org.eclipse.collections.impl.lazy.primitive.ReverseIntIterable;
import org.eclipse.collections.impl.lazy.primitive.SelectIntIterable;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * An IntInterval is a range of ints that may be iterated over using a step value.
 */
public final class IntInterval
        implements ImmutableIntList, Serializable
{
    private static final long serialVersionUID = 1L;

    private final int from;
    private final int to;
    private final int step;

    private IntInterval(int from, int to, int step)
    {
        this.from = from;
        this.to = to;
        this.step = step;
    }

    /**
     * This static {@code from} method allows IntInterval to act as a fluent builder for itself.
     * It works in conjunction with the instance methods {@link #to(int)} and {@link #by(int)}.
     * <p>
     * Usage Example:
     * <pre>
     * IntInterval interval1 = IntInterval.from(1).to(5);         // results in: 1, 2, 3, 4, 5.
     * IntInterval interval2 = IntInterval.from(1).to(10).by(2);  // results in: 1, 3, 5, 7, 9.
     * </pre>
     */
    public static IntInterval from(int newFrom)
    {
        return IntInterval.fromToBy(newFrom, newFrom, 1);
    }

    /**
     * This instance {@code to} method allows IntInterval to act as a fluent builder for itself.
     * It works in conjunction with the static method {@link #from(int)} and instance method {@link #by(int)}.
     * <p>
     * Usage Example:
     * <pre>
     * IntInterval interval1 = IntInterval.from(1).to(5);         // results in: 1, 2, 3, 4, 5.
     * IntInterval interval2 = IntInterval.from(1).to(10).by(2);  // results in: 1, 3, 5, 7, 9.
     * </pre>
     */
    public IntInterval to(int newTo)
    {
        return IntInterval.fromToBy(this.from, newTo, this.step);
    }

    /**
     * This instance {@code by} method allows IntInterval to act as a fluent builder for itself.
     * It works in conjunction with the static method {@link #from(int)} and instance method {@link #to(int)}.
     * <p>
     * Usage Example:
     * <pre>
     * IntInterval interval1 = IntInterval.from(1).to(5);         // results in: 1, 2, 3, 4, 5.
     * IntInterval interval2 = IntInterval.from(1).to(10).by(2);  // results in: 1, 3, 5, 7, 9.
     * </pre>
     */
    public IntInterval by(int newStep)
    {
        return IntInterval.fromToBy(this.from, this.to, newStep);
    }

    /**
     * Returns an IntInterval starting at zero.
     * <p>
     * Usage Example:
     * <pre>
     * IntInterval interval1 = IntInterval.zero().to(5);         // results in: 0, 1, 2, 3, 4, 5.
     * IntInterval interval2 = IntInterval.zero().to(10).by(2);  // results in: 0, 2, 4, 6, 8, 10.
     * </pre>
     */
    public static IntInterval zero()
    {
        return IntInterval.from(0);
    }

    /**
     * Returns an IntInterval starting from 1 to the specified count value with a step value of 1.
     */
    public static IntInterval oneTo(int count)
    {
        return IntInterval.oneToBy(count, 1);
    }

    /**
     * Returns an IntInterval starting from 1 to the specified count value with a step value of step.
     */
    public static IntInterval oneToBy(int count, int step)
    {
        if (count < 1)
        {
            throw new IllegalArgumentException("Only positive ranges allowed using oneToBy");
        }
        return IntInterval.fromToBy(1, count, step);
    }

    /**
     * Returns an IntInterval starting from 0 to the specified count value with a step value of 1.
     */
    public static IntInterval zeroTo(int count)
    {
        return IntInterval.zeroToBy(count, 1);
    }

    /**
     * Returns an IntInterval starting from 0 to the specified count value with a step value of step.
     */
    public static IntInterval zeroToBy(int count, int step)
    {
        return IntInterval.fromToBy(0, count, step);
    }

    /**
     * Returns an IntInterval starting from the value from to the specified value to with a step value of 1.
     */
    public static IntInterval fromTo(int from, int to)
    {
        if (from <= to)
        {
            return IntInterval.fromToBy(from, to, 1);
        }
        return IntInterval.fromToBy(from, to, -1);
    }

    /**
     * Returns an IntInterval representing the even values from the value from to the value to.
     */
    public static IntInterval evensFromTo(int from, int to)
    {
        if (from % 2 != 0)
        {
            if (from < to)
            {
                from++;
            }
            else
            {
                from--;
            }
        }
        if (to % 2 != 0)
        {
            if (to > from)
            {
                to--;
            }
            else
            {
                to++;
            }
        }
        return IntInterval.fromToBy(from, to, to > from ? 2 : -2);
    }

    /**
     * Returns an IntInterval representing the odd values from the value from to the value to.
     */
    public static IntInterval oddsFromTo(int from, int to)
    {
        if (from % 2 == 0)
        {
            if (from < to)
            {
                from++;
            }
            else
            {
                from--;
            }
        }
        if (to % 2 == 0)
        {
            if (to > from)
            {
                to--;
            }
            else
            {
                to++;
            }
        }
        return IntInterval.fromToBy(from, to, to > from ? 2 : -2);
    }

    /**
     * Returns an IntInterval for the range of integers inclusively between from and to with the specified
     * stepBy value.
     */
    public static IntInterval fromToBy(int from, int to, int stepBy)
    {
        if (stepBy == 0)
        {
            throw new IllegalArgumentException("Cannot use a step by of 0");
        }
        if (from > to && stepBy > 0 || from < to && stepBy < 0)
        {
            throw new IllegalArgumentException("Step by is incorrect for the range");
        }
        return new IntInterval(from, to, stepBy);
    }

    /**
     * Returns true if the IntInterval contains all of the specified int values.
     */
    @Override
    public boolean containsAll(int... values)
    {
        for (int value : values)
        {
            if (!this.contains(value))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(IntIterable source)
    {
        for (IntIterator iterator = source.intIterator(); iterator.hasNext(); )
        {
            if (!this.contains(iterator.next()))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the IntInterval contains none of the specified int values.
     */
    public boolean containsNone(int... values)
    {
        for (int value : values)
        {
            if (this.contains(value))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the IntInterval contains the specified int value.
     */
    @Override
    public boolean contains(int value)
    {
        return this.isWithinBoundaries(value) && (value - this.from) % this.step == 0;
    }

    private boolean isWithinBoundaries(int value)
    {
        return this.step > 0 && this.from <= value && value <= this.to
                || this.step < 0 && this.to <= value && value <= this.from;
    }

    @Override
    public void forEachWithIndex(IntIntProcedure procedure)
    {
        int index = 0;
        if (this.from <= this.to)
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                procedure.value(i, index++);
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                procedure.value(i, index++);
            }
        }
    }

    @Override
    public void forEach(IntProcedure procedure)
    {
        this.each(procedure);
    }

    /**
     * @since 7.0.
     */
    @Override
    public void each(IntProcedure procedure)
    {
        if (this.from <= this.to)
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                procedure.value(i);
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                procedure.value(i);
            }
        }
    }

    @Override
    public int count(IntPredicate predicate)
    {
        int count = 0;
        for (int i = 0; i < this.size(); i++)
        {
            if (predicate.accept(this.get(i)))
            {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean anySatisfy(IntPredicate predicate)
    {
        for (int i = 0; i < this.size(); i++)
        {
            if (predicate.accept(this.get(i)))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean allSatisfy(IntPredicate predicate)
    {
        for (int i = 0; i < this.size(); i++)
        {
            if (!predicate.accept(this.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean noneSatisfy(IntPredicate predicate)
    {
        for (int i = 0; i < this.size(); i++)
        {
            if (predicate.accept(this.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object otherList)
    {
        if (otherList == this)
        {
            return true;
        }
        if (!(otherList instanceof IntList))
        {
            return false;
        }
        IntList list = (IntList) otherList;
        if (this.size() != list.size())
        {
            return false;
        }
        if (this.from == this.to)
        {
            return this.from == list.get(0);
        }
        if (this.from < this.to)
        {
            int listIndex = 0;
            for (int i = this.from; i <= this.to; i += this.step)
            {
                if (i != list.get(listIndex++))
                {
                    return false;
                }
            }
        }
        else
        {
            int listIndex = 0;
            for (int i = this.from; i >= this.to; i += this.step)
            {
                if (i != list.get(listIndex++))
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hashCode = 1;
        if (this.from == this.to)
        {
            hashCode = 31 + this.from;
        }
        else if (this.from < this.to)
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                hashCode = 31 * hashCode + i;
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                hashCode = 31 * hashCode + i;
            }
        }
        return hashCode;
    }

    /**
     * Returns a new IntInterval with the from and to values reversed and the step value negated.
     */
    @Override
    public IntInterval toReversed()
    {
        return IntInterval.fromToBy(this.to, this.from, -this.step);
    }

    /**
     * @since 6.0
     */
    @Override
    public ImmutableIntList distinct()
    {
        return this;
    }

    @Override
    public ImmutableIntList subList(int fromIndex, int toIndex)
    {
        throw new UnsupportedOperationException("subList not yet implemented!");
    }

    /**
     * Calculates and returns the size of the interval.
     */
    @Override
    public int size()
    {
        return (this.to - this.from) / this.step + 1;
    }

    @Override
    public long dotProduct(IntList list)
    {
        if (this.size() != list.size())
        {
            throw new IllegalArgumentException("Lists used in dotProduct must be the same size");
        }
        long sum = 0L;
        for (int i = 0; i < this.size(); i++)
        {
            sum += (long) this.get(i) * list.get(i);
        }
        return sum;
    }

    @Override
    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    @Override
    public boolean notEmpty()
    {
        return !this.isEmpty();
    }

    @Override
    public String makeString()
    {
        return this.makeString(", ");
    }

    @Override
    public String makeString(String separator)
    {
        return this.makeString("", separator, "");
    }

    @Override
    public String makeString(String start, String separator, String end)
    {
        Appendable stringBuilder = new StringBuilder();
        this.appendString(stringBuilder, start, separator, end);
        return stringBuilder.toString();
    }

    @Override
    public void appendString(Appendable appendable)
    {
        this.appendString(appendable, ", ");
    }

    @Override
    public void appendString(Appendable appendable, String separator)
    {
        this.appendString(appendable, "", separator, "");
    }

    @Override
    public void appendString(
            Appendable appendable,
            String start,
            String separator,
            String end)
    {
        try
        {
            appendable.append(start);
            for (int i = 0; i < this.size(); i++)
            {
                if (i > 0)
                {
                    appendable.append(separator);
                }
                int value = this.get(i);
                appendable.append(String.valueOf(value));
            }
            appendable.append(end);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int[] toArray()
    {
        int[] result = new int[this.size()];
        this.forEachWithIndex((each, index) -> result[index] = each);
        return result;
    }

    @Override
    public <T> T injectInto(T injectedValue, ObjectIntToObjectFunction<? super T, ? extends T> function)
    {
        T result = injectedValue;
        if (this.from <= this.to)
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                result = function.valueOf(result, i);
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                result = function.valueOf(result, i);
            }
        }
        return result;
    }

    @Override
    public <T> T injectIntoWithIndex(T injectedValue, ObjectIntIntToObjectFunction<? super T, ? extends T> function)
    {
        T result = injectedValue;
        int index = 0;

        if (this.from <= this.to)
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                result = function.valueOf(result, i, index);
                index++;
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                result = function.valueOf(result, i, index);
                index++;
            }
        }
        return result;
    }

    @Override
    public RichIterable<IntIterable> chunk(int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Size for groups must be positive but was: " + size);
        }
        MutableList<IntIterable> result = Lists.mutable.empty();
        if (this.notEmpty())
        {
            int innerFrom = this.from;
            int lastUpdated = this.from;
            if (this.from <= this.to)
            {
                while ((lastUpdated + this.step) <= this.to)
                {
                    MutableIntList batch = IntLists.mutable.empty();
                    for (int i = innerFrom; i <= this.to && batch.size() < size; i += this.step)
                    {
                        batch.add(i);
                        lastUpdated = i;
                    }
                    result.add(batch);
                    innerFrom = lastUpdated + this.step;
                }
            }
            else
            {
                while ((lastUpdated + this.step) >= this.to)
                {
                    MutableIntList batch = IntLists.mutable.empty();
                    for (int i = innerFrom; i >= this.to && batch.size() < size; i += this.step)
                    {
                        batch.add(i);
                        lastUpdated = i;
                    }
                    result.add(batch);
                    innerFrom = lastUpdated + this.step;
                }
            }
        }
        return result;
    }

    @Override
    public String toString()
    {
        return this.makeString("[", ", ", "]");
    }

    @Override
    public IntIterator intIterator()
    {
        return new IntIntervalIterator();
    }

    @Override
    public int getFirst()
    {
        return this.from;
    }

    @Override
    public int getLast()
    {
        return this.locationAfterN(this.size() - 1);
    }

    @Override
    public int get(int index)
    {
        this.checkBounds("index", index);
        return this.locationAfterN(index);
    }

    private void checkBounds(String name, int index)
    {
        if (index < 0 || index >= this.size())
        {
            throw new IndexOutOfBoundsException(name + ": " + index + ' ' + this);
        }
    }

    private int locationAfterN(int index)
    {
        if (index <= 0)
        {
            return this.from;
        }
        if (this.step > 0)
        {
            return (int) Math.min((long) this.from + (long) this.step * (long) index, this.to);
        }
        return (int) Math.max((long) this.from + (long) this.step * (long) index, this.to);
    }

    @Override
    public int indexOf(int value)
    {
        if (!this.isWithinBoundaries(value))
        {
            return -1;
        }
        int diff = value - this.from;
        if (diff % this.step == 0)
        {
            return diff / this.step;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(int value)
    {
        return this.indexOf(value);
    }

    @Override
    public ImmutableIntList select(IntPredicate predicate)
    {
        return IntArrayList.newList(new SelectIntIterable(this, predicate)).toImmutable();
    }

    @Override
    public ImmutableIntList reject(IntPredicate predicate)
    {
        return IntArrayList.newList(new SelectIntIterable(this, IntPredicates.not(predicate))).toImmutable();
    }

    @Override
    public int detectIfNone(IntPredicate predicate, int ifNone)
    {
        return new SelectIntIterable(this, predicate).detectIfNone(predicate, ifNone);
    }

    @Override
    public <V> ImmutableList<V> collect(IntToObjectFunction<? extends V> function)
    {
        return new CollectIntToObjectIterable<V>(this, function).toList().toImmutable();
    }

    @Override
    public LazyIntIterable asReversed()
    {
        return ReverseIntIterable.adapt(this);
    }

    @Override
    public long sum()
    {
        long sum = 0L;
        for (IntIterator intIterator = this.intIterator(); intIterator.hasNext(); )
        {
            sum += intIterator.next();
        }
        return sum;
    }

    @Override
    public int max()
    {
        if (this.from >= this.to)
        {
            return this.getFirst();
        }
        return this.getLast();
    }

    @Override
    public int min()
    {
        if (this.from <= this.to)
        {
            return this.getFirst();
        }
        return this.getLast();
    }

    @Override
    public int minIfEmpty(int defaultValue)
    {
        return this.min();
    }

    @Override
    public int maxIfEmpty(int defaultValue)
    {
        return this.max();
    }

    @Override
    public double average()
    {
        return (double) this.sum() / (double) this.size();
    }

    @Override
    public double median()
    {
        int[] sortedArray = this.toSortedArray();
        int middleIndex = sortedArray.length >> 1;
        if (sortedArray.length > 1 && (sortedArray.length & 1) == 0)
        {
            int first = sortedArray[middleIndex];
            int second = sortedArray[middleIndex - 1];
            return ((double) first + (double) second) / 2.0;
        }
        return (double) sortedArray[middleIndex];
    }

    @Override
    public int binarySearch(int value)
    {
        if (this.step > 0 && this.from > value || this.step < 0 && this.from < value)
        {
            return -1;
        }

        if (this.step > 0 && this.to < value || this.step < 0 && this.to > value)
        {
            return -1 - this.size();
        }

        int diff = value - this.from;
        int index = diff / this.step;
        return diff % this.step == 0 ? index : (index + 2) * -1;
    }

    @Override
    public int[] toSortedArray()
    {
        int[] array = this.toArray();
        Arrays.sort(array);
        return array;
    }

    @Override
    public MutableIntList toList()
    {
        return IntArrayList.newList(this);
    }

    @Override
    public MutableIntList toSortedList()
    {
        return IntArrayList.newList(this).sortThis();
    }

    @Override
    public MutableIntSet toSet()
    {
        return IntHashSet.newSet(this);
    }

    @Override
    public MutableIntBag toBag()
    {
        return IntHashBag.newBag(this);
    }

    @Override
    public LazyIntIterable asLazy()
    {
        return new LazyIntIterableAdapter(this);
    }

    @Override
    public ImmutableIntList toImmutable()
    {
        return this;
    }

    @Override
    public ImmutableIntList newWith(int element)
    {
        return IntArrayList.newList(this).with(element).toImmutable();
    }

    @Override
    public ImmutableIntList newWithout(int element)
    {
        return IntArrayList.newList(this).without(element).toImmutable();
    }

    @Override
    public ImmutableIntList newWithAll(IntIterable elements)
    {
        return IntArrayList.newList(this).withAll(elements).toImmutable();
    }

    @Override
    public ImmutableIntList newWithoutAll(IntIterable elements)
    {
        return IntArrayList.newList(this).withoutAll(elements).toImmutable();
    }

    @Override
    public ImmutableList<IntIntPair> zipInt(IntIterable iterable)
    {
        int size = this.size();
        int othersize = iterable.size();
        MutableList<IntIntPair> target = Lists.mutable.withInitialCapacity(Math.min(size, othersize));
        IntIterator iterator = this.intIterator();
        IntIterator otherIterator = iterable.intIterator();
        for (int i = 0; i < size && otherIterator.hasNext(); i++)
        {
            target.add(PrimitiveTuples.pair(iterator.next(), otherIterator.next()));
        }
        return target.toImmutable();
    }

    @Override
    public <T> ImmutableList<IntObjectPair<T>> zip(Iterable<T> iterable)
    {
        int size = this.size();
        int othersize = Iterate.sizeOf(iterable);
        MutableList<IntObjectPair<T>> target = Lists.mutable.withInitialCapacity(Math.min(size, othersize));
        IntIterator iterator = this.intIterator();
        Iterator<T> otherIterator = iterable.iterator();
        for (int i = 0; i < size && otherIterator.hasNext(); i++)
        {
            target.add(PrimitiveTuples.pair(iterator.next(), otherIterator.next()));
        }
        return target.toImmutable();
    }

    private class IntIntervalIterator implements IntIterator
    {
        private int current = IntInterval.this.from;

        @Override
        public boolean hasNext()
        {
            if (IntInterval.this.from <= IntInterval.this.to)
            {
                return this.current <= IntInterval.this.to;
            }
            return this.current >= IntInterval.this.to;
        }

        @Override
        public int next()
        {
            if (this.hasNext())
            {
                int result = this.current;
                this.current += IntInterval.this.step;
                return result;
            }
            throw new NoSuchElementException();
        }
    }
}
