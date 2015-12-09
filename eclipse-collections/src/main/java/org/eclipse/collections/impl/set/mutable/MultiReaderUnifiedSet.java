/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.set.mutable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
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
import org.eclipse.collections.api.block.procedure.Procedure;
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
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.set.PartitionMutableSet;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
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
import org.eclipse.collections.impl.collection.mutable.AbstractMultiReaderMutableCollection;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.lazy.parallel.set.MultiReaderParallelUnsortedSetIterable;
import org.eclipse.collections.impl.utility.LazyIterate;

import static org.eclipse.collections.impl.factory.Iterables.*;

/**
 * MultiReadUnifiedSet provides a thread-safe wrapper around a UnifiedSet, using a ReentrantReadWriteLock.  In order to
 * provide true thread-safety, MultiReaderFastList does not implement iterator() as all this method requires an external
 * lock to be taken to provide thread-safe iteration.  You can use an iterator() if you use the
 * withReadLockAndDelegate() or withWriteLockAndDelegate() methods.  Both of these methods take a parameter of type
 * Procedure<MutableSet>, and a wrapped version of the underlying Unified is returned.  This wrapper guarantees that no
 * external pointer can ever reference the underlying UnifiedSet outside of a locked procedure.  In the case of the read
 * lock method, an Unmodifiable version of the collection is offered, which will throw UnsupportedOperationExceptions on
 * any write methods like add or remove.
 */
public final class MultiReaderUnifiedSet<T>
        extends AbstractMultiReaderMutableCollection<T>
        implements RandomAccess, Externalizable, MutableSet<T>
{
    private static final long serialVersionUID = 1L;

    private transient ReadWriteLock lock;
    private MutableSet<T> delegate;

    /**
     * @deprecated Empty default constructor used for serialization.
     */
    @SuppressWarnings("UnusedDeclaration")
    @Deprecated
    public MultiReaderUnifiedSet()
    {
        // For Externalizable use only
    }

    private MultiReaderUnifiedSet(MutableSet<T> newDelegate)
    {
        this(newDelegate, new ReentrantReadWriteLock());
    }

    private MultiReaderUnifiedSet(MutableSet<T> newDelegate, ReadWriteLock newLock)
    {
        this.lock = newLock;
        this.delegate = newDelegate;
    }

    public static <T> MultiReaderUnifiedSet<T> newSet()
    {
        return new MultiReaderUnifiedSet<T>(UnifiedSet.<T>newSet());
    }

    public static <T> MultiReaderUnifiedSet<T> newSet(int capacity)
    {
        return new MultiReaderUnifiedSet<T>(UnifiedSet.<T>newSet(capacity));
    }

    public static <T> MultiReaderUnifiedSet<T> newSet(Iterable<T> iterable)
    {
        return new MultiReaderUnifiedSet<T>(UnifiedSet.newSet(iterable));
    }

    public static <T> MultiReaderUnifiedSet<T> newSetWith(T... elements)
    {
        return new MultiReaderUnifiedSet<T>(UnifiedSet.newSetWith(elements));
    }

    @Override
    protected MutableSet<T> getDelegate()
    {
        return this.delegate;
    }

    @Override
    protected ReadWriteLock getLock()
    {
        return this.lock;
    }

    // Exposed for testing

    UntouchableMutableSet<T> asReadUntouchable()
    {
        return new UntouchableMutableSet<T>(this.delegate.asUnmodifiable());
    }

    // Exposed for testing

    UntouchableMutableSet<T> asWriteUntouchable()
    {
        return new UntouchableMutableSet<T>(this.delegate);
    }

    public void withReadLockAndDelegate(Procedure<MutableSet<T>> procedure)
    {
        this.acquireReadLock();
        try
        {
            UntouchableMutableSet<T> untouchableSet = this.asReadUntouchable();
            procedure.value(untouchableSet);
            untouchableSet.becomeUseless();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public void withWriteLockAndDelegate(Procedure<MutableSet<T>> procedure)
    {
        this.acquireWriteLock();
        try
        {
            UntouchableMutableSet<T> untouchableSet = this.asWriteUntouchable();
            procedure.value(untouchableSet);
            untouchableSet.becomeUseless();
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    public MutableSet<T> asSynchronized()
    {
        this.acquireReadLock();
        try
        {
            return SynchronizedMutableSet.of(this);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public ImmutableSet<T> toImmutable()
    {
        this.acquireReadLock();
        try
        {
            return Sets.immutable.withAll(this.delegate);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableSet<T> asUnmodifiable()
    {
        this.acquireReadLock();
        try
        {
            return UnmodifiableMutableSet.of(this);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public MutableSet<T> clone()
    {
        this.acquireReadLock();
        try
        {
            return new MultiReaderUnifiedSet<T>(this.delegate.clone());
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <V> MutableSet<V> collect(Function<? super T, ? extends V> function)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.collect(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableBooleanSet collectBoolean(BooleanFunction<? super T> booleanFunction)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.collectBoolean(booleanFunction);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableByteSet collectByte(ByteFunction<? super T> byteFunction)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.collectByte(byteFunction);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableCharSet collectChar(CharFunction<? super T> charFunction)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.collectChar(charFunction);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableDoubleSet collectDouble(DoubleFunction<? super T> doubleFunction)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.collectDouble(doubleFunction);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableFloatSet collectFloat(FloatFunction<? super T> floatFunction)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.collectFloat(floatFunction);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableIntSet collectInt(IntFunction<? super T> intFunction)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.collectInt(intFunction);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableLongSet collectLong(LongFunction<? super T> longFunction)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.collectLong(longFunction);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableShortSet collectShort(ShortFunction<? super T> shortFunction)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.collectShort(shortFunction);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <V> MutableSet<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.flatCollect(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <V> MutableSet<V> collectIf(
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.collectIf(predicate, function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <P, V> MutableSet<V> collectWith(
            Function2<? super T, ? super P, ? extends V> function,
            P parameter)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.collectWith(function, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableSet<T> newEmpty()
    {
        return MultiReaderUnifiedSet.newSet();
    }

    public MutableSet<T> reject(Predicate<? super T> predicate)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.reject(predicate);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <P> MutableSet<T> rejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.rejectWith(predicate, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableSet<T> tap(Procedure<? super T> procedure)
    {
        this.acquireReadLock();
        try
        {
            this.forEach(procedure);
            return this;
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableSet<T> select(Predicate<? super T> predicate)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.select(predicate);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <P> MutableSet<T> selectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.selectWith(predicate, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public PartitionMutableSet<T> partition(Predicate<? super T> predicate)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.partition(predicate);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <P> PartitionMutableSet<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.partitionWith(predicate, parameter);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <S> MutableSet<S> selectInstancesOf(Class<S> clazz)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.selectInstancesOf(clazz);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableSet<T> with(T element)
    {
        this.add(element);
        return this;
    }

    public MutableSet<T> without(T element)
    {
        this.remove(element);
        return this;
    }

    public MutableSet<T> withAll(Iterable<? extends T> elements)
    {
        this.addAllIterable(elements);
        return this;
    }

    public MutableSet<T> withoutAll(Iterable<? extends T> elements)
    {
        this.removeAllIterable(elements);
        return this;
    }

    @Override
    public boolean equals(Object o)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.equals(o);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    @Override
    public int hashCode()
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.hashCode();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.delegate);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.delegate = (MutableSet<T>) in.readObject();
        this.lock = new ReentrantReadWriteLock();
    }

    // Exposed for testing

    static final class UntouchableMutableSet<T>
            extends UntouchableMutableCollection<T>
            implements MutableSet<T>
    {
        private final MutableList<UntouchableIterator<T>> requestedIterators = mList();

        private UntouchableMutableSet(MutableSet<T> newDelegate)
        {
            this.delegate = newDelegate;
        }

        public void becomeUseless()
        {
            this.delegate = null;
            this.requestedIterators.forEach(new Procedure<UntouchableIterator<T>>()
            {
                public void value(UntouchableIterator<T> each)
                {
                    each.becomeUseless();
                }
            });
        }

        /**
         * This must be implemented this way to guarantee a reference to the delegate doesn't escape.
         */
        public MutableSet<T> with(T element)
        {
            this.add(element);
            return this;
        }

        public MutableSet<T> without(T element)
        {
            this.remove(element);
            return this;
        }

        public MutableSet<T> withAll(Iterable<? extends T> elements)
        {
            this.addAllIterable(elements);
            return this;
        }

        public MutableSet<T> withoutAll(Iterable<? extends T> elements)
        {
            this.removeAllIterable(elements);
            return this;
        }

        public MutableSet<T> asSynchronized()
        {
            throw new UnsupportedOperationException("Cannot call asSynchronized() on " + this.getClass().getSimpleName());
        }

        public MutableSet<T> asUnmodifiable()
        {
            throw new UnsupportedOperationException("Cannot call asUnmodifiable() on " + this.getClass().getSimpleName());
        }

        public ImmutableSet<T> toImmutable()
        {
            return Sets.immutable.withAll(this.getDelegate());
        }

        public LazyIterable<T> asLazy()
        {
            return LazyIterate.adapt(this);
        }

        @Override
        public MutableSet<T> clone()
        {
            return this.getDelegate().clone();
        }

        public <V> MutableSet<V> collect(Function<? super T, ? extends V> function)
        {
            return this.getDelegate().collect(function);
        }

        public MutableBooleanSet collectBoolean(BooleanFunction<? super T> booleanFunction)
        {
            return this.getDelegate().collectBoolean(booleanFunction);
        }

        public <R extends MutableBooleanCollection> R collectBoolean(BooleanFunction<? super T> booleanFunction, R target)
        {
            return this.getDelegate().collectBoolean(booleanFunction, target);
        }

        public MutableByteSet collectByte(ByteFunction<? super T> byteFunction)
        {
            return this.getDelegate().collectByte(byteFunction);
        }

        public <R extends MutableByteCollection> R collectByte(ByteFunction<? super T> byteFunction, R target)
        {
            return this.getDelegate().collectByte(byteFunction, target);
        }

        public MutableCharSet collectChar(CharFunction<? super T> charFunction)
        {
            return this.getDelegate().collectChar(charFunction);
        }

        public <R extends MutableCharCollection> R collectChar(CharFunction<? super T> charFunction, R target)
        {
            return this.getDelegate().collectChar(charFunction, target);
        }

        public MutableDoubleSet collectDouble(DoubleFunction<? super T> doubleFunction)
        {
            return this.getDelegate().collectDouble(doubleFunction);
        }

        public <R extends MutableDoubleCollection> R collectDouble(DoubleFunction<? super T> doubleFunction, R target)
        {
            return this.getDelegate().collectDouble(doubleFunction, target);
        }

        public MutableFloatSet collectFloat(FloatFunction<? super T> floatFunction)
        {
            return this.getDelegate().collectFloat(floatFunction);
        }

        public <R extends MutableFloatCollection> R collectFloat(FloatFunction<? super T> floatFunction, R target)
        {
            return this.getDelegate().collectFloat(floatFunction, target);
        }

        public MutableIntSet collectInt(IntFunction<? super T> intFunction)
        {
            return this.getDelegate().collectInt(intFunction);
        }

        public <R extends MutableIntCollection> R collectInt(IntFunction<? super T> intFunction, R target)
        {
            return this.getDelegate().collectInt(intFunction, target);
        }

        public MutableLongSet collectLong(LongFunction<? super T> longFunction)
        {
            return this.getDelegate().collectLong(longFunction);
        }

        public <R extends MutableLongCollection> R collectLong(LongFunction<? super T> longFunction, R target)
        {
            return this.getDelegate().collectLong(longFunction, target);
        }

        public MutableShortSet collectShort(ShortFunction<? super T> shortFunction)
        {
            return this.getDelegate().collectShort(shortFunction);
        }

        public <R extends MutableShortCollection> R collectShort(ShortFunction<? super T> shortFunction, R target)
        {
            return this.getDelegate().collectShort(shortFunction, target);
        }

        public <V> MutableSet<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
        {
            return this.getDelegate().flatCollect(function);
        }

        public <V> MutableSet<V> collectIf(
                Predicate<? super T> predicate,
                Function<? super T, ? extends V> function)
        {
            return this.getDelegate().collectIf(predicate, function);
        }

        public <P, V> MutableSet<V> collectWith(
                Function2<? super T, ? super P, ? extends V> function,
                P parameter)
        {
            return this.getDelegate().collectWith(function, parameter);
        }

        public <V> MutableSetMultimap<V, T> groupBy(
                Function<? super T, ? extends V> function)
        {
            return this.getDelegate().groupBy(function);
        }

        public <V> MutableSetMultimap<V, T> groupByEach(
                Function<? super T, ? extends Iterable<V>> function)
        {
            return this.getDelegate().groupByEach(function);
        }

        public <V> MutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
        {
            return this.getDelegate().groupByUniqueKey(function);
        }

        public MutableSet<T> newEmpty()
        {
            return this.getDelegate().newEmpty();
        }

        public MutableSet<T> reject(Predicate<? super T> predicate)
        {
            return this.getDelegate().reject(predicate);
        }

        public <P> MutableSet<T> rejectWith(
                Predicate2<? super T, ? super P> predicate,
                P parameter)
        {
            return this.getDelegate().rejectWith(predicate, parameter);
        }

        public MutableSet<T> tap(Procedure<? super T> procedure)
        {
            this.forEach(procedure);
            return this;
        }

        public MutableSet<T> select(Predicate<? super T> predicate)
        {
            return this.getDelegate().select(predicate);
        }

        public <P> MutableSet<T> selectWith(
                Predicate2<? super T, ? super P> predicate,
                P parameter)
        {
            return this.getDelegate().selectWith(predicate, parameter);
        }

        public PartitionMutableSet<T> partition(Predicate<? super T> predicate)
        {
            return this.getDelegate().partition(predicate);
        }

        public <P> PartitionMutableSet<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
        {
            return this.getDelegate().partitionWith(predicate, parameter);
        }

        public <S> MutableSet<S> selectInstancesOf(Class<S> clazz)
        {
            return this.getDelegate().selectInstancesOf(clazz);
        }

        public Iterator<T> iterator()
        {
            UntouchableIterator<T> iterator = new UntouchableIterator<T>(this.delegate.iterator());
            this.requestedIterators.add(iterator);
            return iterator;
        }

        /**
         * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
         */
        @Deprecated
        public <S> MutableSet<Pair<T, S>> zip(Iterable<S> that)
        {
            return this.getDelegate().zip(that);
        }

        /**
         * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
         */
        @Deprecated
        public MutableSet<Pair<T, Integer>> zipWithIndex()
        {
            return this.getDelegate().zipWithIndex();
        }

        public MutableSet<T> union(SetIterable<? extends T> set)
        {
            return this.getDelegate().union(set);
        }

        public <R extends Set<T>> R unionInto(SetIterable<? extends T> set, R targetSet)
        {
            return this.getDelegate().unionInto(set, targetSet);
        }

        public MutableSet<T> intersect(SetIterable<? extends T> set)
        {
            return this.getDelegate().intersect(set);
        }

        public <R extends Set<T>> R intersectInto(SetIterable<? extends T> set, R targetSet)
        {
            return this.getDelegate().intersectInto(set, targetSet);
        }

        public MutableSet<T> difference(SetIterable<? extends T> subtrahendSet)
        {
            return this.getDelegate().difference(subtrahendSet);
        }

        public <R extends Set<T>> R differenceInto(SetIterable<? extends T> subtrahendSet, R targetSet)
        {
            return this.getDelegate().differenceInto(subtrahendSet, targetSet);
        }

        public MutableSet<T> symmetricDifference(SetIterable<? extends T> setB)
        {
            return this.getDelegate().symmetricDifference(setB);
        }

        public <R extends Set<T>> R symmetricDifferenceInto(SetIterable<? extends T> set, R targetSet)
        {
            return this.getDelegate().symmetricDifferenceInto(set, targetSet);
        }

        public boolean isSubsetOf(SetIterable<? extends T> candidateSuperset)
        {
            return this.getDelegate().isSubsetOf(candidateSuperset);
        }

        public boolean isProperSubsetOf(SetIterable<? extends T> candidateSuperset)
        {
            return this.getDelegate().isProperSubsetOf(candidateSuperset);
        }

        public MutableSet<UnsortedSetIterable<T>> powerSet()
        {
            return this.getDelegate().powerSet();
        }

        public <B> LazyIterable<Pair<T, B>> cartesianProduct(SetIterable<B> set)
        {
            return this.getDelegate().cartesianProduct(set);
        }

        public ParallelUnsortedSetIterable<T> asParallel(ExecutorService executorService, int batchSize)
        {
            return this.getDelegate().asParallel(executorService, batchSize);
        }

        private MutableSet<T> getDelegate()
        {
            return (MutableSet<T>) this.delegate;
        }
    }

    private static final class UntouchableIterator<T>
            implements Iterator<T>
    {
        private Iterator<T> delegate;

        private UntouchableIterator(Iterator<T> newDelegate)
        {
            this.delegate = newDelegate;
        }

        public boolean hasNext()
        {
            return this.delegate.hasNext();
        }

        public T next()
        {
            return this.delegate.next();
        }

        public void remove()
        {
            this.delegate.remove();
        }

        public void becomeUseless()
        {
            this.delegate = null;
        }
    }

    public <V> MutableSetMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.groupBy(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <V> MutableSetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.groupByEach(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <V> MutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.groupByUniqueKey(function);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Deprecated
    public <S> MutableSet<Pair<T, S>> zip(Iterable<S> that)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.zip(that);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Deprecated
    public MutableSet<Pair<T, Integer>> zipWithIndex()
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.zipWithIndex();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public RichIterable<RichIterable<T>> chunk(int size)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.chunk(size);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableSet<T> union(SetIterable<? extends T> set)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.union(set);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <R extends Set<T>> R unionInto(SetIterable<? extends T> set, R targetSet)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.unionInto(set, targetSet);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableSet<T> intersect(SetIterable<? extends T> set)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.intersect(set);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <R extends Set<T>> R intersectInto(SetIterable<? extends T> set, R targetSet)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.intersectInto(set, targetSet);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableSet<T> difference(SetIterable<? extends T> subtrahendSet)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.difference(subtrahendSet);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <R extends Set<T>> R differenceInto(SetIterable<? extends T> subtrahendSet, R targetSet)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.differenceInto(subtrahendSet, targetSet);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableSet<T> symmetricDifference(SetIterable<? extends T> setB)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.symmetricDifference(setB);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <R extends Set<T>> R symmetricDifferenceInto(SetIterable<? extends T> set, R targetSet)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.symmetricDifferenceInto(set, targetSet);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public boolean isSubsetOf(SetIterable<? extends T> candidateSuperset)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.isSubsetOf(candidateSuperset);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public boolean isProperSubsetOf(SetIterable<? extends T> candidateSuperset)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.isProperSubsetOf(candidateSuperset);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableSet<UnsortedSetIterable<T>> powerSet()
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.powerSet();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <B> LazyIterable<Pair<T, B>> cartesianProduct(SetIterable<B> set)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.cartesianProduct(set);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public ParallelUnsortedSetIterable<T> asParallel(ExecutorService executorService, int batchSize)
    {
        return new MultiReaderParallelUnsortedSetIterable<T>(this.delegate.asParallel(executorService, batchSize), this.lock);
    }
}
