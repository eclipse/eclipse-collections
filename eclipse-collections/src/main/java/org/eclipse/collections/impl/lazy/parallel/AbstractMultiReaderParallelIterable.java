/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel;

import java.util.Comparator;
import java.util.concurrent.locks.ReadWriteLock;

import org.eclipse.collections.api.ParallelIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.lazy.parallel.list.MultiReaderParallelListIterable;
import org.eclipse.collections.impl.lazy.parallel.set.MultiReaderParallelUnsortedSetIterable;

public abstract class AbstractMultiReaderParallelIterable<T, PI extends ParallelIterable<T>> implements ParallelIterable<T>
{
    protected final PI delegate;
    protected final ReadWriteLock lock;

    protected AbstractMultiReaderParallelIterable(PI delegate, ReadWriteLock lock)
    {
        this.delegate = delegate;
        this.lock = lock;
    }

    protected <A> ParallelListIterable<A> wrap(ParallelListIterable<A> wrapped)
    {
        return new MultiReaderParallelListIterable<>(wrapped, this.lock);
    }

    protected <A> ParallelUnsortedSetIterable<A> wrap(ParallelUnsortedSetIterable<A> wrapped)
    {
        return new MultiReaderParallelUnsortedSetIterable<>(wrapped, this.lock);
    }

    protected <A> ParallelIterable<A> wrap(ParallelIterable<A> wrapped)
    {
        return new MultiReaderParallelIterable<>(wrapped, this.lock);
    }

    @Override
    public void forEach(Procedure<? super T> procedure)
    {
        this.lock.readLock().lock();
        try
        {
            this.delegate.forEach(procedure);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        this.lock.readLock().lock();
        try
        {
            this.delegate.forEachWith(procedure, parameter);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.detect(predicate);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.detectWith(predicate, parameter);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.detectIfNone(predicate, function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <P> T detectWithIfNone(Predicate2<? super T, ? super P> predicate, P parameter, Function0<? extends T> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.detectWithIfNone(predicate, parameter, function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public int count(Predicate<? super T> predicate)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.count(predicate);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <P> int countWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.countWith(predicate, parameter);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.anySatisfy(predicate);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.anySatisfyWith(predicate, parameter);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.allSatisfy(predicate);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.allSatisfyWith(predicate, parameter);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.noneSatisfy(predicate);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.noneSatisfyWith(predicate, parameter);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public MutableList<T> toList()
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toList();
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public MutableList<T> toSortedList()
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toSortedList();
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public MutableList<T> toSortedList(Comparator<? super T> comparator)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toSortedList(comparator);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <V extends Comparable<? super V>> MutableList<T> toSortedListBy(Function<? super T, ? extends V> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toSortedListBy(function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public MutableSet<T> toSet()
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toSet();
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public MutableSortedSet<T> toSortedSet()
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toSortedSet();
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public MutableSortedSet<T> toSortedSet(Comparator<? super T> comparator)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toSortedSet(comparator);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <V extends Comparable<? super V>> MutableSortedSet<T> toSortedSetBy(Function<? super T, ? extends V> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toSortedSetBy(function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public MutableBag<T> toBag()
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toBag();
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public MutableSortedBag<T> toSortedBag()
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toSortedBag();
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public MutableSortedBag<T> toSortedBag(Comparator<? super T> comparator)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toSortedBag(comparator);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <V extends Comparable<? super V>> MutableSortedBag<T> toSortedBagBy(Function<? super T, ? extends V> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toSortedBagBy(function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <NK, NV> MutableMap<NK, NV> toMap(Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toMap(keyFunction, valueFunction);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toSortedMap(keyFunction, valueFunction);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Comparator<? super NK> comparator, Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toSortedMap(comparator, keyFunction, valueFunction);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public Object[] toArray()
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toArray();
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <T1> T1[] toArray(T1[] target)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toArray(target);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public T min(Comparator<? super T> comparator)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.min(comparator);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public T max(Comparator<? super T> comparator)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.max(comparator);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public T min()
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.min();
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public T max()
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.max();
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.minBy(function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.maxBy(function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public long sumOfInt(IntFunction<? super T> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.sumOfInt(function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public double sumOfFloat(FloatFunction<? super T> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.sumOfFloat(function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public long sumOfLong(LongFunction<? super T> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.sumOfLong(function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public double sumOfDouble(DoubleFunction<? super T> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.sumOfDouble(function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public String makeString()
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.makeString();
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public String makeString(String separator)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.makeString(separator);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public String makeString(String start, String separator, String end)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.makeString(start, separator, end);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public void appendString(Appendable appendable)
    {
        this.lock.readLock().lock();
        try
        {
            this.delegate.appendString(appendable);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public void appendString(Appendable appendable, String separator)
    {
        this.lock.readLock().lock();
        try
        {
            this.delegate.appendString(appendable, separator);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        this.lock.readLock().lock();
        try
        {
            this.delegate.appendString(appendable, start, separator, end);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <V> MapIterable<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.groupByUniqueKey(function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <K, V> MapIterable<K, V> aggregateInPlaceBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Procedure2<? super V, ? super T> mutatingAggregator)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.aggregateInPlaceBy(groupBy, zeroValueFactory, mutatingAggregator);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <K, V> MapIterable<K, V> aggregateBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.aggregateBy(groupBy, zeroValueFactory, nonMutatingAggregator);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public String toString()
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.toString();
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }
}
