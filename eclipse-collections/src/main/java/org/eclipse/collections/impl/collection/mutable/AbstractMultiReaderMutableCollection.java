/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.stream.Stream;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.Function3;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.ByteFunction;
import org.eclipse.collections.api.block.function.primitive.CharFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleObjectToDoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.FloatObjectToFloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.IntObjectToIntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.function.primitive.LongObjectToLongFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableByteCollection;
import org.eclipse.collections.api.collection.primitive.MutableCharCollection;
import org.eclipse.collections.api.collection.primitive.MutableDoubleCollection;
import org.eclipse.collections.api.collection.primitive.MutableFloatCollection;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.collection.primitive.MutableLongCollection;
import org.eclipse.collections.api.collection.primitive.MutableShortCollection;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.procedure.MutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.NonMutatingAggregationProcedure;
import org.eclipse.collections.impl.factory.primitive.ObjectDoubleMaps;
import org.eclipse.collections.impl.factory.primitive.ObjectLongMaps;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;

/**
 * AbstractMultiReaderMutableCollection is a common abstraction that provides thread-safe collection behaviors.
 * Subclasses of this class must provide implementations of getDelegate() and getLock().
 */
public abstract class AbstractMultiReaderMutableCollection<T> implements MutableCollection<T>
{
    protected abstract MutableCollection<T> getDelegate();

    protected abstract ReadWriteLock getLock();

    protected void acquireWriteLock()
    {
        this.getLock().writeLock().lock();
    }

    protected void unlockWriteLock()
    {
        this.getLock().writeLock().unlock();
    }

    protected void acquireReadLock()
    {
        this.getLock().readLock().lock();
    }

    protected void unlockReadLock()
    {
        this.getLock().readLock().unlock();
    }

    protected void withReadLockRun(Runnable block)
    {
        this.acquireReadLock();
        try
        {
            block.run();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public boolean contains(Object item)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().contains(item);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public boolean containsAll(Collection<?> collection)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().containsAll(collection);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public boolean containsAllIterable(Iterable<?> source)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().containsAllIterable(source);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public boolean containsAllArguments(Object... elements)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().containsAllArguments(elements);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().noneSatisfy(predicate);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <P> boolean noneSatisfyWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().noneSatisfyWith(predicate, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().allSatisfy(predicate);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <P> boolean allSatisfyWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().allSatisfyWith(predicate, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().anySatisfy(predicate);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <P> boolean anySatisfyWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().anySatisfyWith(predicate, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <R extends Collection<T>> R into(R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().into(target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public MutableList<T> toList()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toList();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <NK, NV> MutableMap<NK, NV> toMap(
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toMap(keyFunction, valueFunction);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSortedMap(keyFunction, valueFunction);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Comparator<? super NK> comparator,
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSortedMap(comparator, keyFunction, valueFunction);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <KK extends Comparable<? super KK>, NK, NV> MutableSortedMap<NK, NV> toSortedMapBy(
            Function<? super NK, KK> sortBy,
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSortedMapBy(sortBy, keyFunction, valueFunction);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public LazyIterable<T> asLazy()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().asLazy();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public MutableSet<T> toSet()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSet();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public MutableBag<T> toBag()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toBag();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public MutableSortedBag<T> toSortedBag()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSortedBag();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public MutableSortedBag<T> toSortedBag(Comparator<? super T> comparator)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSortedBag(comparator);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V extends Comparable<? super V>> MutableSortedBag<T> toSortedBagBy(
            Function<? super T, ? extends V> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSortedBagBy(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public MutableList<T> toSortedList()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSortedList();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public MutableList<T> toSortedList(Comparator<? super T> comparator)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSortedList(comparator);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V extends Comparable<? super V>> MutableList<T> toSortedListBy(
            Function<? super T, ? extends V> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSortedListBy(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public MutableSortedSet<T> toSortedSet()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSortedSet();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public MutableSortedSet<T> toSortedSet(Comparator<? super T> comparator)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSortedSet(comparator);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V extends Comparable<? super V>> MutableSortedSet<T> toSortedSetBy(
            Function<? super T, ? extends V> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toSortedSetBy(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public int count(Predicate<? super T> predicate)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().count(predicate);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <P> int countWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().countWith(predicate, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().detect(predicate);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <P> T detectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().detectWith(predicate, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public Optional<T> detectOptional(Predicate<? super T> predicate)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().detectOptional(predicate);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <P> Optional<T> detectWithOptional(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().detectWithOptional(predicate, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public T detectIfNone(
            Predicate<? super T> predicate,
            Function0<? extends T> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().detectIfNone(predicate, function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <P> T detectWithIfNone(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            Function0<? extends T> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().detectWithIfNone(predicate, parameter, function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public T min(Comparator<? super T> comparator)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().min(comparator);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public T max(Comparator<? super T> comparator)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().max(comparator);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public Optional<T> minOptional(Comparator<? super T> comparator)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().minOptional(comparator);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public Optional<T> maxOptional(Comparator<? super T> comparator)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().maxOptional(comparator);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public T min()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().min();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public T max()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().max();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public Optional<T> minOptional()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().minOptional();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public Optional<T> maxOptional()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().maxOptional();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().minBy(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().maxBy(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V extends Comparable<? super V>> Optional<T> minByOptional(Function<? super T, ? extends V> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().minByOptional(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V extends Comparable<? super V>> Optional<T> maxByOptional(Function<? super T, ? extends V> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().maxByOptional(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public T getFirst()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().getFirst();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public T getLast()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().getLast();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public T getOnly()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().getOnly();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public boolean notEmpty()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().notEmpty();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <P> Twin<MutableList<T>> selectAndRejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().selectAndRejectWith(predicate, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V, R extends Collection<V>> R collect(
            Function<? super T, ? extends V> function,
            R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().collect(function, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <R extends MutableBooleanCollection> R collectBoolean(BooleanFunction<? super T> booleanFunction, R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().collectBoolean(booleanFunction, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <R extends MutableByteCollection> R collectByte(ByteFunction<? super T> byteFunction, R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().collectByte(byteFunction, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <R extends MutableCharCollection> R collectChar(CharFunction<? super T> charFunction, R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().collectChar(charFunction, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <R extends MutableDoubleCollection> R collectDouble(DoubleFunction<? super T> doubleFunction, R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().collectDouble(doubleFunction, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <R extends MutableFloatCollection> R collectFloat(FloatFunction<? super T> floatFunction, R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().collectFloat(floatFunction, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <R extends MutableIntCollection> R collectInt(IntFunction<? super T> intFunction, R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().collectInt(intFunction, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <R extends MutableLongCollection> R collectLong(LongFunction<? super T> longFunction, R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().collectLong(longFunction, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <R extends MutableShortCollection> R collectShort(ShortFunction<? super T> shortFunction, R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().collectShort(shortFunction, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V, R extends Collection<V>> R flatCollect(
            Function<? super T, ? extends Iterable<V>> function,
            R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().flatCollect(function, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V, R extends Collection<V>> R collectIf(
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function,
            R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().collectIf(predicate, function, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <P, V, R extends Collection<V>> R collectWith(
            Function2<? super T, ? super P, ? extends V> function,
            P parameter,
            R targetCollection)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().collectWith(function, parameter, targetCollection);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <P, R extends Collection<T>> R selectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            R targetCollection)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().selectWith(predicate, parameter, targetCollection);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <R extends Collection<T>> R reject(
            Predicate<? super T> predicate,
            R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().reject(predicate, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <P, R extends Collection<T>> R rejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            R targetCollection)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().rejectWith(predicate, parameter, targetCollection);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <R extends Collection<T>> R select(Predicate<? super T> predicate, R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().select(predicate, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <IV> IV injectInto(
            IV injectedValue,
            Function2<? super IV, ? super T, ? extends IV> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().injectInto(injectedValue, function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public int injectInto(int injectedValue, IntObjectToIntFunction<? super T> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().injectInto(injectedValue, function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public long injectInto(long injectedValue, LongObjectToLongFunction<? super T> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().injectInto(injectedValue, function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public double injectInto(double injectedValue, DoubleObjectToDoubleFunction<? super T> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().injectInto(injectedValue, function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public float injectInto(float injectedValue, FloatObjectToFloatFunction<? super T> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().injectInto(injectedValue, function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public long sumOfInt(IntFunction<? super T> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().sumOfInt(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public double sumOfFloat(FloatFunction<? super T> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().sumOfFloat(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public long sumOfLong(LongFunction<? super T> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().sumOfLong(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public double sumOfDouble(DoubleFunction<? super T> function)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().sumOfDouble(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V> MutableObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function)
    {
        MutableObjectLongMap<V> result = ObjectLongMaps.mutable.empty();
        return this.injectInto(result, PrimitiveFunctions.sumByIntFunction(groupBy, function));
    }

    @Override
    public <V> MutableObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function)
    {
        MutableObjectDoubleMap<V> result = ObjectDoubleMaps.mutable.empty();
        return this.injectInto(result, PrimitiveFunctions.sumByFloatFunction(groupBy, function));
    }

    @Override
    public <V> MutableObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function)
    {
        MutableObjectLongMap<V> result = ObjectLongMaps.mutable.empty();
        return this.injectInto(result, PrimitiveFunctions.sumByLongFunction(groupBy, function));
    }

    @Override
    public <V> MutableObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function)
    {
        MutableObjectDoubleMap<V> result = ObjectDoubleMaps.mutable.empty();
        return this.injectInto(result, PrimitiveFunctions.sumByDoubleFunction(groupBy, function));
    }

    @Override
    public <IV, P> IV injectIntoWith(
            IV injectValue,
            Function3<? super IV, ? super T, ? super P, ? extends IV> function,
            P parameter)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().injectIntoWith(injectValue, function, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public boolean removeIf(Predicate<? super T> predicate)
    {
        this.acquireWriteLock();
        try
        {
            return this.getDelegate().removeIf(predicate);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    @Override
    public <P> boolean removeIfWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        this.acquireWriteLock();
        try
        {
            return this.getDelegate().removeIfWith(predicate, parameter);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    @Override
    public boolean add(T item)
    {
        this.acquireWriteLock();
        try
        {
            return this.getDelegate().add(item);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> collection)
    {
        this.acquireWriteLock();
        try
        {
            return this.getDelegate().addAll(collection);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    @Override
    public boolean addAllIterable(Iterable<? extends T> iterable)
    {
        this.acquireWriteLock();
        try
        {
            return this.getDelegate().addAllIterable(iterable);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    @Override
    public void clear()
    {
        this.acquireWriteLock();
        try
        {
            this.getDelegate().clear();
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    @Override
    public boolean isEmpty()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().isEmpty();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    /**
     * This method is not supported directly on MultiReader collections because it is not thread-safe. If you would like
     * to use an iterator with a MultiReader collection, then you must do the following:
     * <p>
     * <pre>
     * multiReaderList.withReadLockAndDelegate(MutableList&lt;Person&gt; ->
     *     {
     *         Iterator it = people.iterator();
     *         ....
     *     });
     * </pre>
     * <p>
     * <pre>
     * final Collection jdkSet = new HashSet();
     * final boolean containsAll = new boolean[1];
     * multiReaderList.withReadLockAndDelegate(MutableList&lt;Person&gt; people ->
     *     {
     *         set.addAll(people); // addAll uses iterator() in AbstractCollection
     *         containsAll[0] = set.containsAll(people); // containsAll uses iterator() in AbstractCollection
     *     });
     * </pre>
     */
    @Override
    public Iterator<T> iterator()
    {
        throw new UnsupportedOperationException(
                "Iterator is not supported directly on MultiReader collections.  "
                        + "If you would like to use an iterator, you must either use withReadLockAndDelegate() or withWriteLockAndDelegate().");
    }

    /**
     * This method is not supported directly on MultiReader collections because it is not thread-safe. If you would like
     * to use a spliterator with a MultiReader collection, then you must protect the calls by calling either
     * withReadLockAndDelegate or withWriteLockAndDelegate.
     */
    @Override
    public Spliterator<T> spliterator()
    {
        throw new UnsupportedOperationException(
                "Spliterator is not supported directly on MultiReader collections.  "
                        + "If you would like to use an spliterator, you must either use withReadLockAndDelegate() or withWriteLockAndDelegate().");
    }

    /**
     * This method is not supported directly on MultiReader collections because it is not thread-safe. If you would like
     * to use stream with a MultiReader collection, then you must protect the calls by calling either
     * withReadLockAndDelegate or withWriteLockAndDelegate.
     */
    @Override
    public Stream<T> stream()
    {
        throw new UnsupportedOperationException(
                "Stream is not supported directly on MultiReader collections.  "
                        + "If you would like to use stream, you must either use withReadLockAndDelegate() or withWriteLockAndDelegate().");
    }

    /**
     * This method is not supported directly on MultiReader collections because it is not thread-safe. If you would like
     * to use parallelStream with a MultiReader collection, then you must protect the calls by calling either
     * withReadLockAndDelegate or withWriteLockAndDelegate.
     */
    @Override
    public Stream<T> parallelStream()
    {
        throw new UnsupportedOperationException(
                "parallelStream is not supported directly on MultiReader collections.  "
                        + "If you would like to use parallelStream, you must either use withReadLockAndDelegate() or withWriteLockAndDelegate().");
    }

    @Override
    public boolean remove(Object item)
    {
        this.acquireWriteLock();
        try
        {
            return this.getDelegate().remove(item);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    @Override
    public boolean removeAll(Collection<?> collection)
    {
        this.acquireWriteLock();
        try
        {
            return this.getDelegate().removeAll(collection);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    @Override
    public boolean removeAllIterable(Iterable<?> iterable)
    {
        this.acquireWriteLock();
        try
        {
            return this.getDelegate().removeAllIterable(iterable);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    @Override
    public boolean retainAll(Collection<?> collection)
    {
        this.acquireWriteLock();
        try
        {
            return this.getDelegate().retainAll(collection);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    @Override
    public boolean retainAllIterable(Iterable<?> iterable)
    {
        this.acquireWriteLock();
        try
        {
            return this.getDelegate().retainAllIterable(iterable);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    @Override
    public int size()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().size();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public Object[] toArray()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toArray();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <E> E[] toArray(E[] a)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toArray(a);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public void forEach(Procedure<? super T> procedure)
    {
        this.each(procedure);
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        this.acquireReadLock();
        try
        {
            this.getDelegate().forEach(procedure);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        this.acquireReadLock();
        try
        {
            this.getDelegate().forEachWith(procedure, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        this.acquireReadLock();
        try
        {
            this.getDelegate().forEachWithIndex(objectIntProcedure);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public String toString()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().toString();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public String makeString()
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().makeString();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public String makeString(String separator)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().makeString(separator);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public String makeString(String start, String separator, String end)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().makeString(start, separator, end);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public void appendString(Appendable appendable)
    {
        this.acquireReadLock();
        try
        {
            this.getDelegate().appendString(appendable);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public void appendString(Appendable appendable, String separator)
    {
        this.acquireReadLock();
        try
        {
            this.getDelegate().appendString(appendable, separator);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        this.acquireReadLock();
        try
        {
            this.getDelegate().appendString(appendable, start, separator, end);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V, R extends MutableMultimap<V, T>> R groupBy(
            Function<? super T, ? extends V> function,
            R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().groupBy(function, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V, R extends MutableMultimap<V, T>> R groupByEach(
            Function<? super T, ? extends Iterable<V>> function,
            R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().groupByEach(function, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <V, R extends MutableMap<V, T>> R groupByUniqueKey(
            Function<? super T, ? extends V> function,
            R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().groupByUniqueKey(function, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <S, R extends Collection<Pair<T, S>>> R zip(Iterable<S> that, R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().zip(that, target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <R extends Collection<Pair<T, Integer>>> R zipWithIndex(R target)
    {
        this.acquireReadLock();
        try
        {
            return this.getDelegate().zipWithIndex(target);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public <K, V> MutableMap<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new MutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map;
    }

    @Override
    public <K, V> MutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new NonMutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map;
    }

    protected abstract static class UntouchableMutableCollection<T>
            implements MutableCollection<T>
    {
        protected MutableCollection<T> delegate;

        @Override
        public boolean allSatisfy(Predicate<? super T> predicate)
        {
            return this.delegate.allSatisfy(predicate);
        }

        @Override
        public <P> boolean allSatisfyWith(
                Predicate2<? super T, ? super P> predicate,
                P parameter)
        {
            return this.delegate.allSatisfyWith(predicate, parameter);
        }

        @Override
        public boolean noneSatisfy(Predicate<? super T> predicate)
        {
            return this.delegate.noneSatisfy(predicate);
        }

        @Override
        public <P> boolean noneSatisfyWith(
                Predicate2<? super T, ? super P> predicate,
                P parameter)
        {
            return this.delegate.noneSatisfyWith(predicate, parameter);
        }

        @Override
        public boolean anySatisfy(Predicate<? super T> predicate)
        {
            return this.delegate.anySatisfy(predicate);
        }

        @Override
        public <P> boolean anySatisfyWith(
                Predicate2<? super T, ? super P> predicate,
                P parameter)
        {
            return this.delegate.anySatisfyWith(predicate, parameter);
        }

        @Override
        public <R extends Collection<T>> R into(R target)
        {
            return this.delegate.into(target);
        }

        @Override
        public MutableList<T> toList()
        {
            return this.delegate.toList();
        }

        @Override
        public <NK, NV> MutableMap<NK, NV> toMap(
                Function<? super T, ? extends NK> keyFunction,
                Function<? super T, ? extends NV> valueFunction)
        {
            return this.delegate.toMap(keyFunction, valueFunction);
        }

        @Override
        public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(
                Function<? super T, ? extends NK> keyFunction,
                Function<? super T, ? extends NV> valueFunction)
        {
            return this.delegate.toSortedMap(keyFunction, valueFunction);
        }

        @Override
        public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Comparator<? super NK> comparator,
                Function<? super T, ? extends NK> keyFunction,
                Function<? super T, ? extends NV> valueFunction)
        {
            return this.delegate.toSortedMap(comparator, keyFunction, valueFunction);
        }

        @Override
        public <KK extends Comparable<? super KK>, NK, NV> MutableSortedMap<NK, NV> toSortedMapBy(
                Function<? super NK, KK> sortBy,
                Function<? super T, ? extends NK> keyFunction,
                Function<? super T, ? extends NV> valueFunction)
        {
            return this.delegate.toSortedMapBy(sortBy, keyFunction, valueFunction);
        }

        @Override
        public MutableSet<T> toSet()
        {
            return this.delegate.toSet();
        }

        @Override
        public MutableBag<T> toBag()
        {
            return this.delegate.toBag();
        }

        @Override
        public MutableSortedBag<T> toSortedBag()
        {
            return this.delegate.toSortedBag();
        }

        @Override
        public MutableSortedBag<T> toSortedBag(Comparator<? super T> comparator)
        {
            return this.delegate.toSortedBag(comparator);
        }

        @Override
        public <V extends Comparable<? super V>> MutableSortedBag<T> toSortedBagBy(Function<? super T, ? extends V> function)
        {
            return this.delegate.toSortedBagBy(function);
        }

        @Override
        public MutableList<T> toSortedList()
        {
            return this.delegate.toSortedList();
        }

        @Override
        public MutableList<T> toSortedList(Comparator<? super T> comparator)
        {
            return this.delegate.toSortedList(comparator);
        }

        @Override
        public <V extends Comparable<? super V>> MutableList<T> toSortedListBy(Function<? super T, ? extends V> function)
        {
            return this.delegate.toSortedListBy(function);
        }

        @Override
        public MutableSortedSet<T> toSortedSet()
        {
            return this.delegate.toSortedSet();
        }

        @Override
        public MutableSortedSet<T> toSortedSet(Comparator<? super T> comparator)
        {
            return this.delegate.toSortedSet(comparator);
        }

        @Override
        public <V extends Comparable<? super V>> MutableSortedSet<T> toSortedSetBy(Function<? super T, ? extends V> function)
        {
            return this.delegate.toSortedSetBy(function);
        }

        @Override
        public <V, R extends Collection<V>> R collect(
                Function<? super T, ? extends V> function,
                R target)
        {
            return this.delegate.collect(function, target);
        }

        @Override
        public <V, R extends Collection<V>> R flatCollect(
                Function<? super T, ? extends Iterable<V>> function,
                R target)
        {
            return this.delegate.flatCollect(function, target);
        }

        @Override
        public <V, R extends Collection<V>> R collectIf(
                Predicate<? super T> predicate,
                Function<? super T, ? extends V> function,
                R target)
        {
            return this.delegate.collectIf(predicate, function, target);
        }

        @Override
        public <P, V, R extends Collection<V>> R collectWith(
                Function2<? super T, ? super P, ? extends V> function,
                P parameter,
                R targetCollection)
        {
            return this.delegate.collectWith(function, parameter, targetCollection);
        }

        @Override
        public <V, R extends MutableMultimap<V, T>> R groupBy(
                Function<? super T, ? extends V> function,
                R target)
        {
            return this.delegate.groupBy(function, target);
        }

        @Override
        public <V, R extends MutableMultimap<V, T>> R groupByEach(
                Function<? super T, ? extends Iterable<V>> function,
                R target)
        {
            return this.delegate.groupByEach(function, target);
        }

        @Override
        public <V, R extends MutableMap<V, T>> R groupByUniqueKey(
                Function<? super T, ? extends V> function,
                R target)
        {
            return this.delegate.groupByUniqueKey(function, target);
        }

        @Override
        public int count(Predicate<? super T> predicate)
        {
            return this.delegate.count(predicate);
        }

        @Override
        public <P> int countWith(Predicate2<? super T, ? super P> predicate, P parameter)
        {
            return this.delegate.countWith(predicate, parameter);
        }

        @Override
        public T detect(Predicate<? super T> predicate)
        {
            return this.delegate.detect(predicate);
        }

        @Override
        public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
        {
            return this.delegate.detectWith(predicate, parameter);
        }

        @Override
        public Optional<T> detectOptional(Predicate<? super T> predicate)
        {
            return this.delegate.detectOptional(predicate);
        }

        @Override
        public <P> Optional<T> detectWithOptional(Predicate2<? super T, ? super P> predicate, P parameter)
        {
            return this.delegate.detectWithOptional(predicate, parameter);
        }

        @Override
        public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
        {
            return this.delegate.detectIfNone(predicate, function);
        }

        @Override
        public <P> T detectWithIfNone(
                Predicate2<? super T, ? super P> predicate,
                P parameter,
                Function0<? extends T> function)
        {
            return this.delegate.detectWithIfNone(predicate, parameter, function);
        }

        @Override
        public T min(Comparator<? super T> comparator)
        {
            return this.delegate.min(comparator);
        }

        @Override
        public T max(Comparator<? super T> comparator)
        {
            return this.delegate.max(comparator);
        }

        @Override
        public T min()
        {
            return this.delegate.min();
        }

        @Override
        public T max()
        {
            return this.delegate.max();
        }

        @Override
        public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
        {
            return this.delegate.minBy(function);
        }

        @Override
        public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
        {
            return this.delegate.maxBy(function);
        }

        @Override
        public T getFirst()
        {
            return this.delegate.getFirst();
        }

        @Override
        public T getLast()
        {
            return this.delegate.getLast();
        }

        @Override
        public T getOnly()
        {
            return this.delegate.getOnly();
        }

        @Override
        public <IV> IV injectInto(
                IV injectedValue,
                Function2<? super IV, ? super T, ? extends IV> function)
        {
            return this.delegate.injectInto(injectedValue, function);
        }

        @Override
        public int injectInto(int injectedValue, IntObjectToIntFunction<? super T> function)
        {
            return this.delegate.injectInto(injectedValue, function);
        }

        @Override
        public long injectInto(long injectedValue, LongObjectToLongFunction<? super T> function)
        {
            return this.delegate.injectInto(injectedValue, function);
        }

        @Override
        public double injectInto(double injectedValue, DoubleObjectToDoubleFunction<? super T> function)
        {
            return this.delegate.injectInto(injectedValue, function);
        }

        @Override
        public float injectInto(float injectedValue, FloatObjectToFloatFunction<? super T> function)
        {
            return this.delegate.injectInto(injectedValue, function);
        }

        @Override
        public long sumOfInt(IntFunction<? super T> function)
        {
            return this.delegate.sumOfInt(function);
        }

        @Override
        public double sumOfFloat(FloatFunction<? super T> function)
        {
            return this.delegate.sumOfFloat(function);
        }

        @Override
        public long sumOfLong(LongFunction<? super T> function)
        {
            return this.delegate.sumOfLong(function);
        }

        @Override
        public double sumOfDouble(DoubleFunction<? super T> function)
        {
            return this.delegate.sumOfDouble(function);
        }

        @Override
        public <V> MutableObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function)
        {
            return this.delegate.sumByInt(groupBy, function);
        }

        @Override
        public <V> MutableObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function)
        {
            return this.delegate.sumByFloat(groupBy, function);
        }

        @Override
        public <V> MutableObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function)
        {
            return this.delegate.sumByLong(groupBy, function);
        }

        @Override
        public <V> MutableObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function)
        {
            return this.delegate.sumByDouble(groupBy, function);
        }

        @Override
        public <IV, P> IV injectIntoWith(
                IV injectValue,
                Function3<? super IV, ? super T, ? super P, ? extends IV> function,
                P parameter)
        {
            return this.delegate.injectIntoWith(injectValue, function, parameter);
        }

        @Override
        public boolean notEmpty()
        {
            return this.delegate.notEmpty();
        }

        @Override
        public <R extends Collection<T>> R reject(Predicate<? super T> predicate, R target)
        {
            return this.delegate.reject(predicate, target);
        }

        @Override
        public <P, R extends Collection<T>> R rejectWith(
                Predicate2<? super T, ? super P> predicate,
                P parameter,
                R targetCollection)
        {
            return this.delegate.rejectWith(predicate, parameter, targetCollection);
        }

        @Override
        public boolean removeIf(Predicate<? super T> predicate)
        {
            return this.delegate.removeIf(predicate);
        }

        @Override
        public <P> boolean removeIfWith(
                Predicate2<? super T, ? super P> predicate,
                P parameter)
        {
            return this.delegate.removeIfWith(predicate, parameter);
        }

        @Override
        public <R extends Collection<T>> R select(Predicate<? super T> predicate, R target)
        {
            return this.delegate.select(predicate, target);
        }

        @Override
        public <P> Twin<MutableList<T>> selectAndRejectWith(
                Predicate2<? super T, ? super P> predicate,
                P parameter)
        {
            return this.delegate.selectAndRejectWith(predicate, parameter);
        }

        @Override
        public <P, R extends Collection<T>> R selectWith(
                Predicate2<? super T, ? super P> predicate,
                P parameter,
                R targetCollection)
        {
            return this.delegate.selectWith(predicate, parameter, targetCollection);
        }

        @Override
        public boolean add(T o)
        {
            return this.delegate.add(o);
        }

        @Override
        public boolean addAll(Collection<? extends T> collection)
        {
            return this.delegate.addAll(collection);
        }

        @Override
        public boolean addAllIterable(Iterable<? extends T> iterable)
        {
            return this.delegate.addAllIterable(iterable);
        }

        @Override
        public void clear()
        {
            this.delegate.clear();
        }

        @Override
        public boolean contains(Object o)
        {
            return this.delegate.contains(o);
        }

        @Override
        public boolean containsAll(Collection<?> collection)
        {
            return this.delegate.containsAll(collection);
        }

        @Override
        public boolean containsAllIterable(Iterable<?> source)
        {
            return this.delegate.containsAllIterable(source);
        }

        @Override
        public boolean containsAllArguments(Object... elements)
        {
            return this.delegate.containsAllArguments(elements);
        }

        @Override
        public boolean equals(Object o)
        {
            return this.delegate.equals(o);
        }

        @Override
        public int hashCode()
        {
            return this.delegate.hashCode();
        }

        @Override
        public boolean isEmpty()
        {
            return this.delegate.isEmpty();
        }

        @Override
        public boolean remove(Object o)
        {
            return this.delegate.remove(o);
        }

        @Override
        public boolean removeAll(Collection<?> collection)
        {
            return this.delegate.removeAll(collection);
        }

        @Override
        public boolean removeAllIterable(Iterable<?> iterable)
        {
            return this.delegate.removeAllIterable(iterable);
        }

        @Override
        public boolean retainAll(Collection<?> collection)
        {
            return this.delegate.retainAll(collection);
        }

        @Override
        public boolean retainAllIterable(Iterable<?> iterable)
        {
            return this.delegate.retainAllIterable(iterable);
        }

        @Override
        public int size()
        {
            return this.delegate.size();
        }

        @Override
        public Object[] toArray()
        {
            return this.delegate.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a)
        {
            return this.delegate.toArray(a);
        }

        @Override
        public void forEach(Procedure<? super T> procedure)
        {
            this.each(procedure);
        }

        @Override
        public void each(Procedure<? super T> procedure)
        {
            this.delegate.forEach(procedure);
        }

        @Override
        public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
        {
            this.delegate.forEachWith(procedure, parameter);
        }

        @Override
        public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
        {
            this.delegate.forEachWithIndex(objectIntProcedure);
        }

        @Override
        public String toString()
        {
            return this.delegate.toString();
        }

        @Override
        public String makeString()
        {
            return this.delegate.makeString();
        }

        @Override
        public String makeString(String separator)
        {
            return this.delegate.makeString(separator);
        }

        @Override
        public String makeString(String start, String separator, String end)
        {
            return this.delegate.makeString(start, separator, end);
        }

        @Override
        public void appendString(Appendable appendable)
        {
            this.delegate.appendString(appendable);
        }

        @Override
        public void appendString(Appendable appendable, String separator)
        {
            this.delegate.appendString(appendable, separator);
        }

        @Override
        public void appendString(Appendable appendable, String start, String separator, String end)
        {
            this.delegate.appendString(appendable, start, separator, end);
        }

        @Override
        public <S, R extends Collection<Pair<T, S>>> R zip(Iterable<S> that, R target)
        {
            return this.delegate.zip(that, target);
        }

        @Override
        public <R extends Collection<Pair<T, Integer>>> R zipWithIndex(R target)
        {
            return this.delegate.zipWithIndex(target);
        }

        @Override
        public RichIterable<RichIterable<T>> chunk(int size)
        {
            return this.delegate.chunk(size);
        }

        @Override
        public <K, V> MutableMap<K, V> aggregateInPlaceBy(
                Function<? super T, ? extends K> groupBy,
                Function0<? extends V> zeroValueFactory,
                Procedure2<? super V, ? super T> mutatingAggregator)
        {
            MutableMap<K, V> map = UnifiedMap.newMap();
            this.each(each -> {
                K key = groupBy.valueOf(each);
                V value = map.getIfAbsentPut(key, zeroValueFactory);
                mutatingAggregator.value(value, each);
            });
            return map;
        }

        @Override
        public <K, V> MutableMap<K, V> aggregateBy(
                Function<? super T, ? extends K> groupBy,
                Function0<? extends V> zeroValueFactory,
                Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
        {
            MutableMap<K, V> map = UnifiedMap.newMap();
            this.each(each -> {
                K key = groupBy.valueOf(each);
                V value = map.getIfAbsentPut(key, zeroValueFactory);
                map.put(key, nonMutatingAggregator.value(value, each));
            });
            return map;
        }
    }
}
