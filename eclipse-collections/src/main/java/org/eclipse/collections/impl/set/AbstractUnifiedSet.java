/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.Function3;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.ByteFunction;
import org.eclipse.collections.api.block.function.primitive.CharFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.Pool;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.set.UnsortedSetIterable;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.api.set.primitive.MutableByteSet;
import org.eclipse.collections.api.set.primitive.MutableCharSet;
import org.eclipse.collections.api.set.primitive.MutableDoubleSet;
import org.eclipse.collections.api.set.primitive.MutableFloatSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.set.primitive.MutableLongSet;
import org.eclipse.collections.api.set.primitive.MutableShortSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.collection.mutable.AbstractMutableCollection;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.parallel.BatchIterable;
import org.eclipse.collections.impl.set.mutable.SynchronizedMutableSet;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.mutable.UnmodifiableMutableSet;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.ByteHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.CharHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.DoubleHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.FloatHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.LongHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.ShortHashSet;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.internal.MutableCollectionIterate;
import org.eclipse.collections.impl.utility.internal.SetIterables;

public abstract class AbstractUnifiedSet<T>
        extends AbstractMutableCollection<T>
        implements MutableSet<T>, Pool<T>, BatchIterable<T>
{
    protected static final float DEFAULT_LOAD_FACTOR = 0.75f;

    protected static final int DEFAULT_INITIAL_CAPACITY = 8;

    protected float loadFactor = DEFAULT_LOAD_FACTOR;

    protected int maxSize;

    protected abstract Object[] getTable();

    protected abstract void allocateTable(int sizeToAllocate);

    protected abstract void rehash(int newCapacity);

    protected abstract T detect(Predicate<? super T> predicate, int start, int end);

    protected abstract Optional<T> detectOptional(Predicate<? super T> predicate, int start, int end);

    @Override
    @SuppressWarnings("AbstractMethodOverridesAbstractMethod")
    public abstract MutableSet<T> clone();

    public abstract MutableSet<T> newEmpty(int size);

    protected abstract boolean shortCircuit(
            Predicate<? super T> predicate,
            boolean expected,
            boolean onShortCircuit,
            boolean atEnd,
            int start,
            int end);

    protected abstract <P> boolean shortCircuitWith(
            Predicate2<? super T, ? super P> predicate2,
            P parameter,
            boolean expected,
            boolean onShortCircuit,
            boolean atEnd);

    protected int init(int initialCapacity)
    {
        int capacity = 1;
        while (capacity < initialCapacity)
        {
            capacity <<= 1;
        }

        return this.allocate(capacity);
    }

    protected int allocate(int capacity)
    {
        this.allocateTable(capacity);
        this.computeMaxSize(capacity);

        return capacity;
    }

    protected void computeMaxSize(int capacity)
    {
        // need at least one free slot for open addressing
        this.maxSize = Math.min(capacity - 1, (int) (capacity * this.loadFactor));
    }

    protected void rehash()
    {
        this.rehash(this.getTable().length << 1);
    }

    protected boolean shortCircuit(
            Predicate<? super T> predicate,
            boolean expected,
            boolean onShortCircuit,
            boolean atEnd)
    {
        return this.shortCircuit(predicate, expected, onShortCircuit, atEnd, 0, this.getTable().length);
    }

    @Override
    public int getBatchCount(int batchSize)
    {
        return Math.max(1, this.getTable().length / batchSize);
    }

    @Override
    public <V> UnifiedSet<V> collect(Function<? super T, ? extends V> function)
    {
        return this.collect(function, UnifiedSet.newSet());
    }

    @Override
    public MutableBooleanSet collectBoolean(BooleanFunction<? super T> booleanFunction)
    {
        return this.collectBoolean(booleanFunction, new BooleanHashSet());
    }

    @Override
    public MutableByteSet collectByte(ByteFunction<? super T> byteFunction)
    {
        return this.collectByte(byteFunction, new ByteHashSet());
    }

    @Override
    public MutableCharSet collectChar(CharFunction<? super T> charFunction)
    {
        return this.collectChar(charFunction, new CharHashSet());
    }

    @Override
    public MutableDoubleSet collectDouble(DoubleFunction<? super T> doubleFunction)
    {
        return this.collectDouble(doubleFunction, new DoubleHashSet());
    }

    @Override
    public MutableFloatSet collectFloat(FloatFunction<? super T> floatFunction)
    {
        return this.collectFloat(floatFunction, new FloatHashSet());
    }

    @Override
    public MutableIntSet collectInt(IntFunction<? super T> intFunction)
    {
        return this.collectInt(intFunction, new IntHashSet());
    }

    @Override
    public MutableLongSet collectLong(LongFunction<? super T> longFunction)
    {
        return this.collectLong(longFunction, new LongHashSet());
    }

    @Override
    public MutableShortSet collectShort(ShortFunction<? super T> shortFunction)
    {
        return this.collectShort(shortFunction, new ShortHashSet());
    }

    @Override
    public <V> UnifiedSet<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.flatCollect(function, UnifiedSet.newSet());
    }

    @Override
    public <P, A> UnifiedSet<A> collectWith(Function2<? super T, ? super P, ? extends A> function, P parameter)
    {
        return this.collectWith(function, parameter, UnifiedSet.newSet());
    }

    @Override
    public <V> UnifiedSet<V> collectIf(
            Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return this.collectIf(predicate, function, UnifiedSet.newSet());
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return this.detect(predicate, 0, this.getTable().length);
    }

    @Override
    public Optional<T> detectOptional(Predicate<? super T> predicate)
    {
        return this.detectOptional(predicate, 0, this.getTable().length);
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return this.shortCircuit(predicate, true, true, false);
    }

    @Override
    public <P> boolean anySatisfyWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        return this.shortCircuitWith(predicate, parameter, true, true, false);
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return this.shortCircuit(predicate, false, false, true);
    }

    @Override
    public <P> boolean allSatisfyWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        return this.shortCircuitWith(predicate, parameter, false, false, true);
    }

    @Override
    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return this.shortCircuit(predicate, true, false, true);
    }

    @Override
    public <P> boolean noneSatisfyWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        return this.shortCircuitWith(predicate, parameter, true, false, true);
    }

    @Override
    public <IV, P> IV injectIntoWith(
            IV injectValue,
            Function3<? super IV, ? super T, ? super P, ? extends IV> function,
            P parameter)
    {
        return this.injectInto(injectValue, (argument1, argument2) -> function.value(argument1, argument2, parameter));
    }

    @Override
    public MutableSet<T> asUnmodifiable()
    {
        return UnmodifiableMutableSet.of(this);
    }

    @Override
    public MutableSet<T> asSynchronized()
    {
        return SynchronizedMutableSet.of(this);
    }

    @Override
    public boolean removeAllIterable(Iterable<?> iterable)
    {
        boolean changed = false;
        for (Object each : iterable)
        {
            changed |= this.remove(each);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> collection)
    {
        return this.retainAllIterable(collection);
    }

    @Override
    public <V> MutableMap<V, T> groupByUniqueKey(
            Function<? super T, ? extends V> function)
    {
        return this.groupByUniqueKey(function, UnifiedMap.newMap(this.size()));
    }

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Override
    @Deprecated
    public <S> MutableSet<Pair<T, S>> zip(Iterable<S> that)
    {
        if (that instanceof Collection || that instanceof RichIterable)
        {
            int thatSize = Iterate.sizeOf(that);
            UnifiedSet<Pair<T, S>> target = UnifiedSet.newSet(Math.min(this.size(), thatSize));
            return this.zip(that, target);
        }
        return this.zip(that, UnifiedSet.newSet());
    }

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Override
    @Deprecated
    public MutableSet<Pair<T, Integer>> zipWithIndex()
    {
        return this.zipWithIndex(UnifiedSet.newSet(this.size()));
    }

    @Override
    public RichIterable<RichIterable<T>> chunk(int size)
    {
        return MutableCollectionIterate.chunk(this, size);
    }

    @Override
    public MutableSet<T> union(SetIterable<? extends T> set)
    {
        return SetIterables.unionInto(this, set, this.newEmpty());
    }

    @Override
    public <R extends Set<T>> R unionInto(SetIterable<? extends T> set, R targetSet)
    {
        return SetIterables.unionInto(this, set, targetSet);
    }

    @Override
    public MutableSet<T> intersect(SetIterable<? extends T> set)
    {
        return SetIterables.intersectInto(this, set, this.newEmpty());
    }

    @Override
    public <R extends Set<T>> R intersectInto(SetIterable<? extends T> set, R targetSet)
    {
        return SetIterables.intersectInto(this, set, targetSet);
    }

    @Override
    public MutableSet<T> difference(SetIterable<? extends T> subtrahendSet)
    {
        return SetIterables.differenceInto(this, subtrahendSet, this.newEmpty());
    }

    @Override
    public <R extends Set<T>> R differenceInto(SetIterable<? extends T> subtrahendSet, R targetSet)
    {
        return SetIterables.differenceInto(this, subtrahendSet, targetSet);
    }

    @Override
    public MutableSet<T> symmetricDifference(SetIterable<? extends T> setB)
    {
        return SetIterables.symmetricDifferenceInto(this, setB, this.newEmpty());
    }

    @Override
    public <R extends Set<T>> R symmetricDifferenceInto(SetIterable<? extends T> set, R targetSet)
    {
        return SetIterables.symmetricDifferenceInto(this, set, targetSet);
    }

    @Override
    public boolean isSubsetOf(SetIterable<? extends T> candidateSuperset)
    {
        return SetIterables.isSubsetOf(this, candidateSuperset);
    }

    @Override
    public boolean isProperSubsetOf(SetIterable<? extends T> candidateSuperset)
    {
        return SetIterables.isProperSubsetOf(this, candidateSuperset);
    }

    @Override
    public MutableSet<UnsortedSetIterable<T>> powerSet()
    {
        return (MutableSet<UnsortedSetIterable<T>>) (MutableSet<?>) SetIterables.powerSet(this);
    }

    @Override
    public <B> LazyIterable<Pair<T, B>> cartesianProduct(SetIterable<B> set)
    {
        return SetIterables.cartesianProduct(this, set);
    }
}
