/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel;

import java.util.Comparator;

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
import org.eclipse.collections.api.set.sorted.ParallelSortedSetIterable;
import org.eclipse.collections.impl.lazy.parallel.list.SynchronizedParallelListIterable;
import org.eclipse.collections.impl.lazy.parallel.set.SynchronizedParallelUnsortedSetIterable;
import org.eclipse.collections.impl.lazy.parallel.set.sorted.SynchronizedParallelSortedSetIterable;

public abstract class AbstractSynchronizedParallelIterable<T, PI extends ParallelIterable<T>> implements ParallelIterable<T>
{
    protected final PI delegate;
    protected final Object lock;

    protected AbstractSynchronizedParallelIterable(PI delegate, Object lock)
    {
        this.delegate = delegate;
        this.lock = lock;
    }

    protected <A> ParallelListIterable<A> wrap(ParallelListIterable<A> wrapped)
    {
        return new SynchronizedParallelListIterable<A>(wrapped, this.lock);
    }

    protected <A> ParallelUnsortedSetIterable<A> wrap(ParallelUnsortedSetIterable<A> wrapped)
    {
        return new SynchronizedParallelUnsortedSetIterable<A>(wrapped, this.lock);
    }

    protected <A> ParallelSortedSetIterable<A> wrap(ParallelSortedSetIterable<A> wrapped)
    {
        return new SynchronizedParallelSortedSetIterable<A>(wrapped, this.lock);
    }

    protected <A> ParallelIterable<A> wrap(ParallelIterable<A> wrapped)
    {
        return new SynchronizedParallelIterable<A>(wrapped, this.lock);
    }

    public void forEach(Procedure<? super T> procedure)
    {
        synchronized (this.lock)
        {
            this.delegate.forEach(procedure);
        }
    }

    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        synchronized (this.lock)
        {
            this.delegate.forEachWith(procedure, parameter);
        }
    }

    public T detect(Predicate<? super T> predicate)
    {
        synchronized (this.lock)
        {
            return this.delegate.detect(predicate);
        }
    }

    public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.lock)
        {
            return this.delegate.detectWith(predicate, parameter);
        }
    }

    public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
    {
        synchronized (this.lock)
        {
            return this.delegate.detectIfNone(predicate, function);
        }
    }

    public <P> T detectWithIfNone(Predicate2<? super T, ? super P> predicate, P parameter, Function0<? extends T> function)
    {
        synchronized (this.lock)
        {
            return this.delegate.detectWithIfNone(predicate, parameter, function);
        }
    }

    public int count(Predicate<? super T> predicate)
    {
        synchronized (this.lock)
        {
            return this.delegate.count(predicate);
        }
    }

    public <P> int countWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.lock)
        {
            return this.delegate.countWith(predicate, parameter);
        }
    }

    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        synchronized (this.lock)
        {
            return this.delegate.anySatisfy(predicate);
        }
    }

    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.lock)
        {
            return this.delegate.anySatisfyWith(predicate, parameter);
        }
    }

    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        synchronized (this.lock)
        {
            return this.delegate.allSatisfy(predicate);
        }
    }

    public <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.lock)
        {
            return this.delegate.allSatisfyWith(predicate, parameter);
        }
    }

    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        synchronized (this.lock)
        {
            return this.delegate.noneSatisfy(predicate);
        }
    }

    public <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.lock)
        {
            return this.delegate.noneSatisfyWith(predicate, parameter);
        }
    }

    public MutableList<T> toList()
    {
        synchronized (this.lock)
        {
            return this.delegate.toList();
        }
    }

    public MutableList<T> toSortedList()
    {
        synchronized (this.lock)
        {
            return this.delegate.toSortedList();
        }
    }

    public MutableList<T> toSortedList(Comparator<? super T> comparator)
    {
        synchronized (this.lock)
        {
            return this.delegate.toSortedList(comparator);
        }
    }

    public <V extends Comparable<? super V>> MutableList<T> toSortedListBy(Function<? super T, ? extends V> function)
    {
        synchronized (this.lock)
        {
            return this.delegate.toSortedListBy(function);
        }
    }

    public MutableSet<T> toSet()
    {
        synchronized (this.lock)
        {
            return this.delegate.toSet();
        }
    }

    public MutableSortedSet<T> toSortedSet()
    {
        synchronized (this.lock)
        {
            return this.delegate.toSortedSet();
        }
    }

    public MutableSortedSet<T> toSortedSet(Comparator<? super T> comparator)
    {
        synchronized (this.lock)
        {
            return this.delegate.toSortedSet(comparator);
        }
    }

    public <V extends Comparable<? super V>> MutableSortedSet<T> toSortedSetBy(Function<? super T, ? extends V> function)
    {
        synchronized (this.lock)
        {
            return this.delegate.toSortedSetBy(function);
        }
    }

    public MutableBag<T> toBag()
    {
        synchronized (this.lock)
        {
            return this.delegate.toBag();
        }
    }

    public MutableSortedBag<T> toSortedBag()
    {
        synchronized (this.lock)
        {
            return this.delegate.toSortedBag();
        }
    }

    public MutableSortedBag<T> toSortedBag(Comparator<? super T> comparator)
    {
        synchronized (this.lock)
        {
            return this.delegate.toSortedBag(comparator);
        }
    }

    public <V extends Comparable<? super V>> MutableSortedBag<T> toSortedBagBy(Function<? super T, ? extends V> function)
    {
        synchronized (this.lock)
        {
            return this.delegate.toSortedBagBy(function);
        }
    }

    public <NK, NV> MutableMap<NK, NV> toMap(Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction)
    {
        synchronized (this.lock)
        {
            return this.delegate.toMap(keyFunction, valueFunction);
        }
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction)
    {
        synchronized (this.lock)
        {
            return this.delegate.toSortedMap(keyFunction, valueFunction);
        }
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Comparator<? super NK> comparator, Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction)
    {
        synchronized (this.lock)
        {
            return this.delegate.toSortedMap(comparator, keyFunction, valueFunction);
        }
    }

    public Object[] toArray()
    {
        synchronized (this.lock)
        {
            return this.delegate.toArray();
        }
    }

    public <T1> T1[] toArray(T1[] target)
    {
        synchronized (this.lock)
        {
            return this.delegate.toArray(target);
        }
    }

    public T min(Comparator<? super T> comparator)
    {
        synchronized (this.lock)
        {
            return this.delegate.min(comparator);
        }
    }

    public T max(Comparator<? super T> comparator)
    {
        synchronized (this.lock)
        {
            return this.delegate.max(comparator);
        }
    }

    public T min()
    {
        synchronized (this.lock)
        {
            return this.delegate.min();
        }
    }

    public T max()
    {
        synchronized (this.lock)
        {
            return this.delegate.max();
        }
    }

    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        synchronized (this.lock)
        {
            return this.delegate.minBy(function);
        }
    }

    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        synchronized (this.lock)
        {
            return this.delegate.maxBy(function);
        }
    }

    public long sumOfInt(IntFunction<? super T> function)
    {
        synchronized (this.lock)
        {
            return this.delegate.sumOfInt(function);
        }
    }

    public double sumOfFloat(FloatFunction<? super T> function)
    {
        synchronized (this.lock)
        {
            return this.delegate.sumOfFloat(function);
        }
    }

    public long sumOfLong(LongFunction<? super T> function)
    {
        synchronized (this.lock)
        {
            return this.delegate.sumOfLong(function);
        }
    }

    public double sumOfDouble(DoubleFunction<? super T> function)
    {
        synchronized (this.lock)
        {
            return this.delegate.sumOfDouble(function);
        }
    }

    public String makeString()
    {
        synchronized (this.lock)
        {
            return this.delegate.makeString();
        }
    }

    public String makeString(String separator)
    {
        synchronized (this.lock)
        {
            return this.delegate.makeString(separator);
        }
    }

    public String makeString(String start, String separator, String end)
    {
        synchronized (this.lock)
        {
            return this.delegate.makeString(start, separator, end);
        }
    }

    public void appendString(Appendable appendable)
    {
        synchronized (this.lock)
        {
            this.delegate.appendString(appendable);
        }
    }

    public void appendString(Appendable appendable, String separator)
    {
        synchronized (this.lock)
        {
            this.delegate.appendString(appendable, separator);
        }
    }

    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        synchronized (this.lock)
        {
            this.delegate.appendString(appendable, start, separator, end);
        }
    }

    public <V> MapIterable<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        synchronized (this.lock)
        {
            return this.delegate.groupByUniqueKey(function);
        }
    }

    public <K, V> MapIterable<K, V> aggregateInPlaceBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Procedure2<? super V, ? super T> mutatingAggregator)
    {
        synchronized (this.lock)
        {
            return this.delegate.aggregateInPlaceBy(groupBy, zeroValueFactory, mutatingAggregator);
        }
    }

    public <K, V> MapIterable<K, V> aggregateBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        synchronized (this.lock)
        {
            return this.delegate.aggregateBy(groupBy, zeroValueFactory, nonMutatingAggregator);
        }
    }

    @Override
    public String toString()
    {
        synchronized (this.lock)
        {
            return this.delegate.toString();
        }
    }
}
