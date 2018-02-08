/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleObjectToDoubleFunction;
import org.eclipse.collections.api.block.function.primitive.IntObjectToIntFunction;
import org.eclipse.collections.api.block.function.primitive.LongObjectToLongFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.IntIntProcedure;
import org.eclipse.collections.api.block.procedure.primitive.IntObjectProcedure;
import org.eclipse.collections.api.block.procedure.primitive.IntProcedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.procedure.CollectProcedure;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.block.procedure.RejectProcedure;
import org.eclipse.collections.impl.block.procedure.SelectProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.lazy.AbstractLazyIterable;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.MutableListIterator;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

/**
 * An Interval is a range of integers that may be iterated over using a step value.  Interval
 * is an OO implementation of a for-loop.
 */
public final class Interval
        extends AbstractLazyIterable<Integer>
        implements List<Integer>, Serializable, RandomAccess
{
    private static final long serialVersionUID = 1L;

    private final int from;
    private final int to;
    private final int step;

    private Interval(int from, int to, int step)
    {
        this.from = from;
        this.to = to;
        this.step = step;
    }

    /**
     * This static {@code from} method allows Interval to act as a fluent builder for itself.
     * It works in conjunction with the instance methods {@link #to(int)} and {@link #by(int)}.
     * <p>
     * Usage Example:
     * <pre>
     * Interval interval1 = Interval.from(1).to(5);         // results in: 1, 2, 3, 4, 5.
     * Interval interval2 = Interval.from(1).to(10).by(2);  // results in: 1, 3, 5, 7, 9.
     * </pre>
     */
    public static Interval from(int newFrom)
    {
        return Interval.fromToBy(newFrom, newFrom, 1);
    }

    /**
     * This instance {@code to} method allows Interval to act as a fluent builder for itself.
     * It works in conjunction with the static method {@link #from(int)} and instance method {@link #by(int)}.
     * <p>
     * Usage Example:
     * <pre>
     * Interval interval1 = Interval.from(1).to(5);         // results in: 1, 2, 3, 4, 5.
     * Interval interval2 = Interval.from(1).to(10).by(2);  // results in: 1, 3, 5, 7, 9.
     * </pre>
     */
    public Interval to(int newTo)
    {
        return Interval.fromToBy(this.from, newTo, this.step);
    }

    /**
     * This instance {@code by} method allows Interval to act as a fluent builder for itself.
     * It works in conjunction with the static method {@link #from(int)} and instance method {@link #to(int)}.
     * <p>
     * Usage Example:
     * <pre>
     * Interval interval1 = Interval.from(1).to(5);         // results in: 1, 2, 3, 4, 5.
     * Interval interval2 = Interval.from(1).to(10).by(2);  // results in: 1, 3, 5, 7, 9.
     * </pre>
     */
    public Interval by(int newStep)
    {
        return Interval.fromToBy(this.from, this.to, newStep);
    }

    /**
     * Returns an Interval starting at zero.
     * <p>
     * Usage Example:
     * <pre>
     * Interval interval1 = Interval.zero().to(5);         // results in: 0, 1, 2, 3, 4, 5.
     * Interval interval2 = Interval.zero().to(10).by(2);  // results in: 0, 2, 4, 6, 8, 10.
     * </pre>
     */
    public static Interval zero()
    {
        return Interval.from(0);
    }

    /**
     * Returns an Interval starting from 1 to the specified count value with a step value of 1.
     */
    public static Interval oneTo(int count)
    {
        return Interval.oneToBy(count, 1);
    }

    /**
     * Returns an Interval starting from 1 to the specified count value with a step value of step.
     */
    public static Interval oneToBy(int count, int step)
    {
        if (count < 1)
        {
            throw new IllegalArgumentException("Only positive ranges allowed using oneToBy");
        }
        return Interval.fromToBy(1, count, step);
    }

    /**
     * Returns an Interval starting from 0 to the specified count value with a step value of 1.
     */
    public static Interval zeroTo(int count)
    {
        return Interval.zeroToBy(count, 1);
    }

    /**
     * Returns an Interval starting from 0 to the specified count value with a step value of step.
     */
    public static Interval zeroToBy(int count, int step)
    {
        return Interval.fromToBy(0, count, step);
    }

    /**
     * Returns an Interval starting from the value from to the specified value to with a step value of 1.
     */
    public static Interval fromTo(int from, int to)
    {
        if (from <= to)
        {
            return Interval.fromToBy(from, to, 1);
        }
        return Interval.fromToBy(from, to, -1);
    }

    /**
     * Returns an Interval representing the even values from the value from to the value to.
     */
    public static Interval evensFromTo(int from, int to)
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
        return Interval.fromToBy(from, to, to > from ? 2 : -2);
    }

    /**
     * Returns an Interval representing the odd values from the value from to the value to.
     */
    public static Interval oddsFromTo(int from, int to)
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
        return Interval.fromToBy(from, to, to > from ? 2 : -2);
    }

    /**
     * Returns an Set representing the Integer values from the value from to the value to.
     */
    public static MutableSet<Integer> toSet(int from, int to)
    {
        MutableSet<Integer> targetCollection = UnifiedSet.newSet();
        Interval.fromTo(from, to).forEach(CollectionAddProcedure.on(targetCollection));
        return targetCollection;
    }

    /**
     * Returns a MutableList representing the Integer values from the value from to the value to in reverse.
     */
    public static MutableList<Integer> toReverseList(int from, int to)
    {
        return Interval.fromTo(from, to).reverseThis().toList();
    }

    /**
     * Returns an Integer array with the values inclusively between from and to.
     */
    public static Integer[] toArray(int from, int to)
    {
        return Interval.fromTo(from, to).toArray();
    }

    public static Integer[] toReverseArray(int from, int to)
    {
        return Interval.fromTo(from, to).reverseThis().toArray();
    }

    /**
     * Returns an Interval for the range of integers inclusively between from and to with the specified
     * stepBy value.
     */
    public static Interval fromToBy(int from, int to, int stepBy)
    {
        if (stepBy == 0)
        {
            throw new IllegalArgumentException("Cannot use a step by of 0");
        }
        if (from > to && stepBy > 0 || from < to && stepBy < 0)
        {
            throw new IllegalArgumentException("Step by is incorrect for the range");
        }
        return new Interval(from, to, stepBy);
    }

    /**
     * Returns true if the Interval contains all of the specified int values.
     */
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

    /**
     * Returns true if the Interval contains none of the specified int values.
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

    @Override
    public boolean contains(Object object)
    {
        return object instanceof Integer && this.contains(((Integer) object).intValue());
    }

    /**
     * Returns true if the Interval contains the specified int value.
     */
    public boolean contains(int value)
    {
        return this.isWithinBoundaries(value) && (value - this.from) % this.step == 0;
    }

    private boolean isWithinBoundaries(int value)
    {
        return this.step > 0 && this.from <= value && value <= this.to
                || this.step < 0 && this.to <= value && value <= this.from;
    }

    /**
     * Returns the Number result of calculating factorial for the range.
     */
    public Number factorial()
    {
        this.failIfOutOfFactorialRange();
        return this.from == 0 ? Integer.valueOf(1) : this.product();
    }

    /**
     * Returns the Number result of calculating product for the range.
     */
    public Number product()
    {
        return this.bigIntegerProduct();
    }

    /**
     * Returns the BigInteger result of calculating product for the range.
     */
    private BigInteger bigIntegerProduct()
    {
        return this.injectInto(BigInteger.valueOf(1L), (result, each) -> result.multiply(BigInteger.valueOf(each.longValue())));
    }

    private void failIfOutOfFactorialRange()
    {
        if (this.from < 0 || this.step != 1)
        {
            throw new IllegalStateException("Cannot calculate factorial on negative ranges");
        }
    }

    public void forEachWithIndex(IntIntProcedure procedure)
    {
        int index = 0;
        if (this.goForward())
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
    public void forEachWithIndex(ObjectIntProcedure<? super Integer> objectIntProcedure)
    {
        this.forEachWithIndex((IntIntProcedure) objectIntProcedure::value);
    }

    public <P> void forEachWith(IntObjectProcedure<? super P> procedure, P parameter)
    {
        if (this.goForward())
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                procedure.value(i, parameter);
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                procedure.value(i, parameter);
            }
        }
    }

    public boolean goForward()
    {
        return this.from <= this.to && this.step > 0;
    }

    @Override
    public <P> void forEachWith(Procedure2<? super Integer, ? super P> procedure, P parameter)
    {
        this.forEachWith((IntObjectProcedure<P>) procedure::value, parameter);
    }

    public void forEach(IntProcedure procedure)
    {
        if (this.goForward())
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
    public void each(Procedure<? super Integer> procedure)
    {
        this.forEach((IntProcedure) procedure::value);
    }

    /**
     * This method executes a void procedure against an executor, passing the current index of the
     * interval.
     */
    public void forEach(Procedure<? super Integer> procedure, Executor executor)
    {
        CountDownLatch latch = new CountDownLatch(this.size());
        if (this.goForward())
        {
            // Iterates in forward direction because step value is negative
            for (int i = this.from; i <= this.to; i += this.step)
            {
                this.executeAndCountdown(procedure, executor, latch, i);
            }
        }
        else
        {
            // Iterates in reverse because step value is negative
            for (int i = this.from; i >= this.to; i += this.step)
            {
                this.executeAndCountdown(procedure, executor, latch, i);
            }
        }
        try
        {
            latch.await();
        }
        catch (InterruptedException e)
        {
            // do nothing here;
        }
    }

    private void executeAndCountdown(
            Procedure<? super Integer> procedure,
            Executor executor,
            CountDownLatch latch,
            Integer integer)
    {
        executor.execute(() -> {
            try
            {
                procedure.value(integer);
            }
            finally
            {
                latch.countDown();
            }
        });
    }

    /**
     * This method runs a runnable a specified number of times against on the current thread.
     */
    public void run(Runnable runnable)
    {
        if (this.goForward())
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                runnable.run();
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                runnable.run();
            }
        }
    }

    /**
     * This method runs a runnable a specified number of times against an executor.  The method is effectively
     * asynchronous because it does not wait for all of the runnables to finish.
     */
    public void run(Runnable runnable, Executor executor)
    {
        if (this.goForward())
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                executor.execute(runnable);
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                executor.execute(runnable);
            }
        }
    }

    @Override
    public <R> R injectInto(R injectValue, Function2<? super R, ? super Integer, ? extends R> function)
    {
        R result = injectValue;
        if (this.goForward())
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                result = function.value(result, i);
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                result = function.value(result, i);
            }
        }
        return result;
    }

    @Override
    public int injectInto(int injectedValue, IntObjectToIntFunction<? super Integer> function)
    {
        int result = injectedValue;
        if (this.goForward())
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                result = function.intValueOf(result, i);
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                result = function.intValueOf(result, i);
            }
        }
        return result;
    }

    @Override
    public long injectInto(long injectedValue, LongObjectToLongFunction<? super Integer> function)
    {
        long result = injectedValue;
        if (this.goForward())
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                result = function.longValueOf(result, i);
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                result = function.longValueOf(result, i);
            }
        }
        return result;
    }

    @Override
    public double injectInto(double injectedValue, DoubleObjectToDoubleFunction<? super Integer> function)
    {
        double result = injectedValue;
        if (this.goForward())
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                result = function.doubleValueOf(result, i);
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                result = function.doubleValueOf(result, i);
            }
        }
        return result;
    }

    public void reverseForEach(Procedure<? super Integer> procedure)
    {
        this.reverseThis().forEach(procedure);
    }

    public <R> R reverseInjectInto(R injectValue, Function2<R, Integer, R> function)
    {
        return this.reverseThis().injectInto(injectValue, function);
    }

    public <R extends Collection<Integer>> R addAllTo(R targetCollection)
    {
        this.forEach(CollectionAddProcedure.on(targetCollection));
        return targetCollection;
    }

    @Override
    public <T, R extends Collection<T>> R collect(
            Function<? super Integer, ? extends T> function,
            R target)
    {
        CollectProcedure<Integer, T> procedure = new CollectProcedure<>(function, target);
        this.forEach(procedure);
        return target;
    }

    @Override
    public <R extends Collection<Integer>> R select(Predicate<? super Integer> predicate, R target)
    {
        SelectProcedure<Integer> procedure = new SelectProcedure<>(predicate, target);
        this.forEach(procedure);
        return target;
    }

    @Override
    public <R extends Collection<Integer>> R reject(Predicate<? super Integer> predicate, R target)
    {
        RejectProcedure<Integer> procedure = new RejectProcedure<>(predicate, target);
        this.forEach(procedure);
        return target;
    }

    @Override
    public boolean equals(Object otherList)
    {
        if (otherList == this)
        {
            return true;
        }
        if (!(otherList instanceof List))
        {
            return false;
        }
        List<?> list = (List<?>) otherList;

        if (otherList instanceof RandomAccess)
        {
            if (this.size() != list.size())
            {
                return false;
            }
        }
        ListIterator<?> listIterator = ((List<?>) otherList).listIterator();
        if (this.goForward())
        {
            for (int i = this.from; i <= this.to && listIterator.hasNext(); i += this.step)
            {
                Object object = listIterator.next();
                if (this.intObjectEqual(i, object))
                {
                    return false;
                }
            }
        }
        else
        {
            for (int i = this.from; i >= this.to && listIterator.hasNext(); i += this.step)
            {
                Object object = listIterator.next();
                if (this.intObjectEqual(i, object))
                {
                    return false;
                }
            }
        }

        return !listIterator.hasNext();
    }

    private boolean intObjectEqual(int i, Object object)
    {
        return object == null || !(object instanceof Integer) || ((Integer) object).intValue() != i;
    }

    @Override
    public int hashCode()
    {
        int hashCode = 1;
        if (this.goForward())
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
     * Returns a new interval with the from and to values reversed and the step value negated.
     */
    public Interval reverseThis()
    {
        return Interval.fromToBy(this.to, this.from, -this.step);
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
    public Integer[] toArray()
    {
        Integer[] result = new Integer[this.size()];
        this.forEachWithIndex((ObjectIntProcedure<Integer>) (each, index) -> result[index] = each);
        return result;
    }

    /**
     * Converts the interval to an Integer array
     */
    public int[] toIntArray()
    {
        int[] result = new int[this.size()];
        this.forEachWithIndex((IntIntProcedure) (each, index) -> result[index] = each);
        return result;
    }

    @Override
    public String toString()
    {
        return "Interval from: " + this.from + " to: " + this.to + " step: " + this.step + " size: " + this.size();
    }

    @Override
    public Iterator<Integer> iterator()
    {
        return new IntegerIterator();
    }

    private class IntegerIterator implements Iterator<Integer>
    {
        private int current = Interval.this.from;

        @Override
        public boolean hasNext()
        {
            if (Interval.this.from <= Interval.this.to)
            {
                return this.current <= Interval.this.to;
            }
            return this.current >= Interval.this.to;
        }

        @Override
        public Integer next()
        {
            if (this.hasNext())
            {
                Integer result = this.current;
                this.current += Interval.this.step;
                return result;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Cannot remove a value from an Interval");
        }
    }

    @Override
    public Integer getFirst()
    {
        return this.from;
    }

    @Override
    public Integer getLast()
    {
        return this.locationAfterN(this.size() - 1);
    }

    public void forEach(Procedure<? super Integer> procedure, int startIndex, int endIndex)
    {
        this.checkBounds("startIndex", startIndex);
        this.checkBounds("endIndex", endIndex);

        if (startIndex <= endIndex)
        {
            for (int i = startIndex; i <= endIndex; i++)
            {
                procedure.value(this.locationAfterN(i));
            }
        }
        else
        {
            for (int i = startIndex; i >= endIndex; i--)
            {
                procedure.value(this.locationAfterN(i));
            }
        }
    }

    public void forEachWithIndex(ObjectIntProcedure<? super Integer> objectIntProcedure, int startIndex, int endIndex)
    {
        this.checkBounds("startIndex", startIndex);
        this.checkBounds("endIndex", endIndex);

        if (startIndex <= endIndex)
        {
            for (int i = startIndex; i <= endIndex; i++)
            {
                objectIntProcedure.value(this.locationAfterN(i), i);
            }
        }
        else
        {
            for (int i = startIndex; i >= endIndex; i--)
            {
                objectIntProcedure.value(this.locationAfterN(i), i);
            }
        }
    }

    @Override
    public Integer get(int index)
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
    public int indexOf(Object object)
    {
        if (!(object instanceof Integer))
        {
            return -1;
        }
        Integer value = (Integer) object;
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
    public int lastIndexOf(Object object)
    {
        return this.indexOf(object);
    }

    @Override
    public MutableList<Integer> toList()
    {
        FastList<Integer> list = FastList.newList(this.size());
        this.forEach(CollectionAddProcedure.on(list));
        return list;
    }

    @Override
    public MutableSet<Integer> toSet()
    {
        MutableSet<Integer> set = UnifiedSet.newSet(this.size());
        this.forEach(CollectionAddProcedure.on(set));
        return set;
    }

    @Override
    public MutableBag<Integer> toBag()
    {
        MutableBag<Integer> bag = HashBag.newBag(this.size());
        this.forEach(CollectionAddProcedure.on(bag));
        return bag;
    }

    @Override
    public boolean add(Integer integer)
    {
        throw new UnsupportedOperationException("Cannot call add() on " + this.getClass().getSimpleName());
    }

    @Override
    public boolean remove(Object o)
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    @Override
    @SuppressWarnings("TypeParameterExtendsFinalClass")
    public boolean addAll(Collection<? extends Integer> collection)
    {
        throw new UnsupportedOperationException("Cannot call addAll() on " + this.getClass().getSimpleName());
    }

    @Override
    @SuppressWarnings("TypeParameterExtendsFinalClass")
    public boolean addAll(int index, Collection<? extends Integer> collection)
    {
        throw new UnsupportedOperationException("Cannot call addAll() on " + this.getClass().getSimpleName());
    }

    @Override
    public boolean removeAll(Collection<?> collection)
    {
        throw new UnsupportedOperationException("Cannot call removeAll() on " + this.getClass().getSimpleName());
    }

    @Override
    public boolean retainAll(Collection<?> collection)
    {
        throw new UnsupportedOperationException("Cannot call retainAll() on " + this.getClass().getSimpleName());
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException("Cannot call clear() on " + this.getClass().getSimpleName());
    }

    @Override
    public Integer set(int index, Integer element)
    {
        throw new UnsupportedOperationException("Cannot call set() on " + this.getClass().getSimpleName());
    }

    @Override
    public void add(int index, Integer element)
    {
        throw new UnsupportedOperationException("Cannot call add() on " + this.getClass().getSimpleName());
    }

    @Override
    public Integer remove(int index)
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    @Override
    public ListIterator<Integer> listIterator()
    {
        return new MutableListIterator<>(this, 0);
    }

    @Override
    public ListIterator<Integer> listIterator(int index)
    {
        return new MutableListIterator<>(this, index);
    }

    @Override
    public Interval subList(int fromIndex, int toIndex)
    {
        return Interval.fromToBy(this.get(fromIndex), this.get(toIndex - 1), this.step);
    }

    @Override
    public LazyIterable<Integer> take(int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }

        if (count > 0 && this.notEmpty())
        {
            return Interval.fromToBy(this.from, this.locationAfterN(count - 1), this.step);
        }
        return Lists.immutable.<Integer>empty().asLazy();
    }

    @Override
    public LazyIterable<Integer> drop(int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }

        if (count >= this.size())
        {
            return Lists.immutable.<Integer>of().asLazy();
        }

        return Interval.fromToBy(this.locationAfterN(count), this.to, this.step);
    }

    @Override
    public LazyIterable<Integer> distinct()
    {
        return this;
    }
}
