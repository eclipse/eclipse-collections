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
import java.util.Iterator;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.bag.primitive.MutableByteBag;
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.bag.primitive.MutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.MutableFloatBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.bag.primitive.MutableLongBag;
import org.eclipse.collections.api.bag.primitive.MutableShortBag;
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
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
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
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.PartitionMutableBag;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.collection.mutable.AbstractMultiReaderMutableCollection;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Iterables;
import org.eclipse.collections.impl.utility.LazyIterate;

/**
 * MultiReaderHashBag provides a thread-safe wrapper around a HashBag, using a ReentrantReadWriteLock. In order to
 * provide true thread-safety, MultiReaderHashBag does not implement iterator() as this method requires an external lock
 * to be taken to provide thread-safe iteration. All of these methods are available however, if you use the
 * withReadLockAndDelegate() or withWriteLockAndDelegate() methods. Both of these methods take a parameter of type
 * Procedure<MutableBag>, and a wrapped version of the underlying HashBag is returned. This wrapper guarantees that
 * no external pointer can ever reference the underlying HashBag outside of a locked procedure. In the case of the
 * read lock method, an Unmodifiable version of the collection is offered, which will throw UnsupportedOperationExceptions
 * on any write methods like add or remove.
 */
public final class MultiReaderHashBag<T>
        extends AbstractMultiReaderMutableCollection<T>
        implements Externalizable, MutableBag<T>
{
    private static final long serialVersionUID = 1L;

    private transient ReadWriteLock lock;
    private MutableBag<T> delegate;

    /**
     * @deprecated Empty default constructor used for serialization.
     */
    @SuppressWarnings("UnusedDeclaration")
    @Deprecated
    public MultiReaderHashBag()
    {
        // For Externalizable use only
    }

    private MultiReaderHashBag(MutableBag<T> newDelegate)
    {
        this(newDelegate, new ReentrantReadWriteLock());
    }

    private MultiReaderHashBag(MutableBag<T> newDelegate, ReadWriteLock newLock)
    {
        this.lock = newLock;
        this.delegate = newDelegate;
    }

    public static <T> MultiReaderHashBag<T> newBag()
    {
        return new MultiReaderHashBag<>(HashBag.<T>newBag());
    }

    public static <T> MultiReaderHashBag<T> newBag(int capacity)
    {
        return new MultiReaderHashBag<>(HashBag.<T>newBag(capacity));
    }

    public static <T> MultiReaderHashBag<T> newBag(Iterable<T> iterable)
    {
        return new MultiReaderHashBag<>(HashBag.newBag(iterable));
    }

    public static <T> MultiReaderHashBag<T> newBagWith(T... elements)
    {
        return new MultiReaderHashBag<>(HashBag.newBagWith(elements));
    }

    @Override
    protected MutableBag<T> getDelegate()
    {
        return this.delegate;
    }

    @Override
    protected ReadWriteLock getLock()
    {
        return this.lock;
    }

    UntouchableMutableBag<T> asReadUntouchable()
    {
        return new UntouchableMutableBag<>(this.delegate.asUnmodifiable());
    }

    UntouchableMutableBag<T> asWriteUntouchable()
    {
        return new UntouchableMutableBag<>(this.delegate);
    }

    public void withReadLockAndDelegate(Procedure<MutableBag<T>> procedure)
    {
        this.acquireReadLock();
        try
        {
            UntouchableMutableBag<T> bag = this.asReadUntouchable();
            procedure.value(bag);
            bag.becomeUseless();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public void withWriteLockAndDelegate(Procedure<MutableBag<T>> procedure)
    {
        this.acquireWriteLock();
        try
        {
            UntouchableMutableBag<T> bag = this.asWriteUntouchable();
            procedure.value(bag);
            bag.becomeUseless();
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    public MutableBag<T> asSynchronized()
    {
        this.acquireReadLock();
        try
        {
            return SynchronizedBag.of(this);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableBag<T> asUnmodifiable()
    {
        this.acquireReadLock();
        try
        {
            return UnmodifiableBag.of(this);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public ImmutableBag<T> toImmutable()
    {
        this.acquireReadLock();
        try
        {
            return Bags.immutable.withAll(this.delegate);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public int addOccurrences(T item, int occurrences)
    {
        this.acquireWriteLock();
        try
        {
            return this.delegate.addOccurrences(item, occurrences);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    public boolean removeOccurrences(Object item, int occurrences)
    {
        this.acquireWriteLock();
        try
        {
            return this.delegate.removeOccurrences(item, occurrences);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    public boolean setOccurrences(T item, int occurrences)
    {
        this.acquireWriteLock();
        try
        {
            return this.delegate.setOccurrences(item, occurrences);
        }
        finally
        {
            this.unlockWriteLock();
        }
    }

    public int occurrencesOf(Object item)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.occurrencesOf(item);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public int sizeDistinct()
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.sizeDistinct();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <V> MutableBag<V> collect(Function<? super T, ? extends V> function)
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

    public MutableBooleanBag collectBoolean(BooleanFunction<? super T> booleanFunction)
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

    public MutableByteBag collectByte(ByteFunction<? super T> byteFunction)
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

    public MutableCharBag collectChar(CharFunction<? super T> charFunction)
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

    public MutableDoubleBag collectDouble(DoubleFunction<? super T> doubleFunction)
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

    public MutableFloatBag collectFloat(FloatFunction<? super T> floatFunction)
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

    public MutableIntBag collectInt(IntFunction<? super T> intFunction)
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

    public MutableLongBag collectLong(LongFunction<? super T> longFunction)
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

    public MutableShortBag collectShort(ShortFunction<? super T> shortFunction)
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

    public <V> MutableBag<V> flatCollect(
            Function<? super T, ? extends Iterable<V>> function)
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

    public MutableList<ObjectIntPair<T>> topOccurrences(int count)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.topOccurrences(count);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public MutableList<ObjectIntPair<T>> bottomOccurrences(int count)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.bottomOccurrences(count);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <V> MutableBag<V> collectIf(
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

    public <P, V> MutableBag<V> collectWith(
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

    public MutableBag<T> newEmpty()
    {
        return MultiReaderHashBag.newBag();
    }

    public MutableBag<T> reject(Predicate<? super T> predicate)
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

    public <P> MutableBag<T> rejectWith(
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

    public MutableBag<T> tap(Procedure<? super T> procedure)
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

    public MutableBag<T> select(Predicate<? super T> predicate)
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

    public <P> MutableBag<T> selectWith(
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

    public MutableBag<T> selectByOccurrences(IntPredicate predicate)
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.selectByOccurrences(predicate);
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <S> MutableBag<S> selectInstancesOf(Class<S> clazz)
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

    public PartitionMutableBag<T> partition(Predicate<? super T> predicate)
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

    public <P> PartitionMutableBag<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
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

    public MutableBag<T> with(T element)
    {
        this.add(element);
        return this;
    }

    public MutableBag<T> without(T element)
    {
        this.remove(element);
        return this;
    }

    public MutableBag<T> withAll(Iterable<? extends T> elements)
    {
        this.addAllIterable(elements);
        return this;
    }

    public MutableBag<T> withoutAll(Iterable<? extends T> elements)
    {
        this.removeAllIterable(elements);
        return this;
    }

    public MutableMap<T, Integer> toMapOfItemToCount()
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.toMapOfItemToCount();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public String toStringOfItemToCount()
    {
        this.acquireReadLock();
        try
        {
            return this.delegate.toStringOfItemToCount();
        }
        finally
        {
            this.unlockReadLock();
        }
    }

    public <V> MutableBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
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

    public <V> MutableBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
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
    public <S> MutableBag<Pair<T, S>> zip(Iterable<S> that)
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

    public void forEachWithOccurrences(ObjectIntProcedure<? super T> procedure)
    {
        this.acquireReadLock();
        try
        {
            this.delegate.forEachWithOccurrences(procedure);
        }
        finally
        {
            this.unlockReadLock();
        }
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
        this.delegate = (MutableBag<T>) in.readObject();
        this.lock = new ReentrantReadWriteLock();
    }

    //Exposed for testing

    static final class UntouchableMutableBag<T>
            extends UntouchableMutableCollection<T>
            implements MutableBag<T>
    {
        private final MutableList<UntouchableIterator<T>> requestedIterators = Iterables.mList();

        private UntouchableMutableBag(MutableBag<T> newDelegate)
        {
            this.delegate = newDelegate;
        }

        public void becomeUseless()
        {
            this.delegate = null;
            this.requestedIterators.each(UntouchableIterator<T>::becomeUseless);
        }

        public MutableBag<T> with(T element)
        {
            this.add(element);
            return this;
        }

        public MutableBag<T> without(T element)
        {
            this.remove(element);
            return this;
        }

        public MutableBag<T> withAll(Iterable<? extends T> elements)
        {
            this.addAllIterable(elements);
            return this;
        }

        public MutableBag<T> withoutAll(Iterable<? extends T> elements)
        {
            this.removeAllIterable(elements);
            return this;
        }

        public MutableBag<T> asSynchronized()
        {
            throw new UnsupportedOperationException("cannot wrap an UntouchableMutableBag");
        }

        public MutableBag<T> asUnmodifiable()
        {
            throw new UnsupportedOperationException("cannot wrap an UntouchableMutableBag");
        }

        public ImmutableBag<T> toImmutable()
        {
            return Bags.immutable.withAll(this.getDelegate());
        }

        public LazyIterable<T> asLazy()
        {
            return LazyIterate.adapt(this);
        }

        public Iterator<T> iterator()
        {
            UntouchableIterator<T> iterator = new UntouchableIterator<>(this.delegate.iterator());
            this.requestedIterators.add(iterator);
            return iterator;
        }

        public int addOccurrences(T item, int occurrences)
        {
            return this.getDelegate().addOccurrences(item, occurrences);
        }

        public boolean removeOccurrences(Object item, int occurrences)
        {
            return this.getDelegate().removeOccurrences(item, occurrences);
        }

        public boolean setOccurrences(T item, int occurrences)
        {
            return this.getDelegate().setOccurrences(item, occurrences);
        }

        public int occurrencesOf(Object item)
        {
            return this.getDelegate().occurrencesOf(item);
        }

        public int sizeDistinct()
        {
            return this.getDelegate().sizeDistinct();
        }

        public <V> MutableBag<V> collect(Function<? super T, ? extends V> function)
        {
            return this.getDelegate().collect(function);
        }

        public MutableBooleanBag collectBoolean(BooleanFunction<? super T> booleanFunction)
        {
            return this.getDelegate().collectBoolean(booleanFunction);
        }

        public <R extends MutableBooleanCollection> R collectBoolean(BooleanFunction<? super T> booleanFunction, R target)
        {
            return this.getDelegate().collectBoolean(booleanFunction, target);
        }

        public MutableByteBag collectByte(ByteFunction<? super T> byteFunction)
        {
            return this.getDelegate().collectByte(byteFunction);
        }

        public <R extends MutableByteCollection> R collectByte(ByteFunction<? super T> byteFunction, R target)
        {
            return this.getDelegate().collectByte(byteFunction, target);
        }

        public MutableCharBag collectChar(CharFunction<? super T> charFunction)
        {
            return this.getDelegate().collectChar(charFunction);
        }

        public <R extends MutableCharCollection> R collectChar(CharFunction<? super T> charFunction, R target)
        {
            return this.getDelegate().collectChar(charFunction, target);
        }

        public MutableDoubleBag collectDouble(DoubleFunction<? super T> doubleFunction)
        {
            return this.getDelegate().collectDouble(doubleFunction);
        }

        public <R extends MutableDoubleCollection> R collectDouble(DoubleFunction<? super T> doubleFunction, R target)
        {
            return this.getDelegate().collectDouble(doubleFunction, target);
        }

        public MutableFloatBag collectFloat(FloatFunction<? super T> floatFunction)
        {
            return this.getDelegate().collectFloat(floatFunction);
        }

        public <R extends MutableFloatCollection> R collectFloat(FloatFunction<? super T> floatFunction, R target)
        {
            return this.getDelegate().collectFloat(floatFunction, target);
        }

        public MutableIntBag collectInt(IntFunction<? super T> intFunction)
        {
            return this.getDelegate().collectInt(intFunction);
        }

        public <R extends MutableIntCollection> R collectInt(IntFunction<? super T> intFunction, R target)
        {
            return this.getDelegate().collectInt(intFunction, target);
        }

        public MutableLongBag collectLong(LongFunction<? super T> longFunction)
        {
            return this.getDelegate().collectLong(longFunction);
        }

        public <R extends MutableLongCollection> R collectLong(LongFunction<? super T> longFunction, R target)
        {
            return this.getDelegate().collectLong(longFunction, target);
        }

        public MutableShortBag collectShort(ShortFunction<? super T> shortFunction)
        {
            return this.getDelegate().collectShort(shortFunction);
        }

        public <R extends MutableShortCollection> R collectShort(ShortFunction<? super T> shortFunction, R target)
        {
            return this.getDelegate().collectShort(shortFunction, target);
        }

        public <V> MutableBag<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
        {
            return this.getDelegate().flatCollect(function);
        }

        public MutableList<ObjectIntPair<T>> topOccurrences(int count)
        {
            return this.getDelegate().topOccurrences(count);
        }

        public MutableList<ObjectIntPair<T>> bottomOccurrences(int count)
        {
            return this.getDelegate().bottomOccurrences(count);
        }

        public <V> MutableBag<V> collectIf(
                Predicate<? super T> predicate,
                Function<? super T, ? extends V> function)
        {
            return this.getDelegate().collectIf(predicate, function);
        }

        public <P, V> MutableBag<V> collectWith(
                Function2<? super T, ? super P, ? extends V> function,
                P parameter)
        {
            return this.getDelegate().collectWith(function, parameter);
        }

        public <V> MutableBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
        {
            return this.getDelegate().groupBy(function);
        }

        public <V> MutableBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
        {
            return this.getDelegate().groupByEach(function);
        }

        public <V> MutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
        {
            return this.getDelegate().groupByUniqueKey(function);
        }

        public MutableBag<T> newEmpty()
        {
            return this.getDelegate().newEmpty();
        }

        public MutableBag<T> reject(Predicate<? super T> predicate)
        {
            return this.getDelegate().reject(predicate);
        }

        public <P> MutableBag<T> rejectWith(
                Predicate2<? super T, ? super P> predicate,
                P parameter)
        {
            return this.getDelegate().rejectWith(predicate, parameter);
        }

        public MutableBag<T> tap(Procedure<? super T> procedure)
        {
            this.forEach(procedure);
            return this;
        }

        public MutableBag<T> select(Predicate<? super T> predicate)
        {
            return this.getDelegate().select(predicate);
        }

        public <P> MutableBag<T> selectWith(
                Predicate2<? super T, ? super P> predicate,
                P parameter)
        {
            return this.getDelegate().selectWith(predicate, parameter);
        }

        public MutableBag<T> selectByOccurrences(IntPredicate predicate)
        {
            return this.getDelegate().selectByOccurrences(predicate);
        }

        public <S> MutableBag<S> selectInstancesOf(Class<S> clazz)
        {
            return this.getDelegate().selectInstancesOf(clazz);
        }

        public void forEachWithOccurrences(ObjectIntProcedure<? super T> procedure)
        {
            this.getDelegate().forEachWithOccurrences(procedure);
        }

        public PartitionMutableBag<T> partition(Predicate<? super T> predicate)
        {
            return this.getDelegate().partition(predicate);
        }

        public <P> PartitionMutableBag<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
        {
            return this.getDelegate().partitionWith(predicate, parameter);
        }

        /**
         * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
         */
        @Deprecated
        public <S> MutableBag<Pair<T, S>> zip(Iterable<S> that)
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

        public MutableMap<T, Integer> toMapOfItemToCount()
        {
            return this.getDelegate().toMapOfItemToCount();
        }

        public String toStringOfItemToCount()
        {
            return this.getDelegate().toStringOfItemToCount();
        }

        private MutableBag<T> getDelegate()
        {
            return (MutableBag<T>) this.delegate;
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
}
