/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import net.jcip.annotations.GuardedBy;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.HashingStrategy;
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
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableByteList;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableFloatList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.list.primitive.MutableShortList;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.collection.mutable.AbstractSynchronizedMutableCollection;
import org.eclipse.collections.impl.collection.mutable.SynchronizedCollectionSerializationProxy;
import org.eclipse.collections.impl.lazy.ReverseIterable;
import org.eclipse.collections.impl.lazy.parallel.list.SynchronizedParallelListIterable;

/**
 * A synchronized view of a {@link MutableList}. It is imperative that the user manually synchronize on the collection when iterating over it using the
 * standard JDK iterator or JDK 5 for loop, as per {@link Collections#synchronizedCollection(Collection)}.
 *
 * @see MutableList#asSynchronized()
 */
public class SynchronizedMutableList<T>
        extends AbstractSynchronizedMutableCollection<T>
        implements MutableList<T>, Serializable
{
    private static final long serialVersionUID = 2L;

    SynchronizedMutableList(MutableList<T> newCollection)
    {
        super(newCollection);
    }

    SynchronizedMutableList(MutableList<T> newCollection, Object newLock)
    {
        super(newCollection, newLock);
    }

    /**
     * This method will take a MutableList and wrap it directly in a SynchronizedMutableList.  It will
     * take any other non-GS-collection and first adapt it will a ListAdapter, and then return a
     * SynchronizedMutableList that wraps the adapter.
     */
    public static <E, L extends List<E>> SynchronizedMutableList<E> of(L list)
    {
        MutableList<E> mutableList =
                list instanceof MutableList ? (MutableList<E>) list : ListAdapter.adapt(list);
        return new SynchronizedMutableList<>(mutableList);
    }

    /**
     * This method will take a MutableList and wrap it directly in a SynchronizedMutableList.  It will
     * take any other non-GS-collection and first adapt it will a ListAdapter, and then return a
     * SynchronizedMutableList that wraps the adapter.  Additionally, a developer specifies which lock to use
     * with the collection.
     */
    public static <E, L extends List<E>> SynchronizedMutableList<E> of(L list, Object lock)
    {
        MutableList<E> mutableList =
                list instanceof MutableList ? (MutableList<E>) list : ListAdapter.adapt(list);
        return new SynchronizedMutableList<>(mutableList, lock);
    }

    @Override
    @GuardedBy("getLock()")
    protected MutableList<T> getDelegate()
    {
        return (MutableList<T>) super.getDelegate();
    }

    @Override
    public MutableList<T> with(T element)
    {
        this.add(element);
        return this;
    }

    @Override
    public MutableList<T> without(T element)
    {
        this.remove(element);
        return this;
    }

    @Override
    public MutableList<T> withAll(Iterable<? extends T> elements)
    {
        this.addAllIterable(elements);
        return this;
    }

    @Override
    public MutableList<T> withoutAll(Iterable<? extends T> elements)
    {
        this.removeAllIterable(elements);
        return this;
    }

    @Override
    public MutableList<T> newEmpty()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().newEmpty().asSynchronized();
        }
    }

    @Override
    public MutableList<T> clone()
    {
        synchronized (this.getLock())
        {
            return SynchronizedMutableList.of(this.getDelegate().clone());
        }
    }

    protected Object writeReplace()
    {
        return new SynchronizedCollectionSerializationProxy<>(this.getDelegate());
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().addAll(index, collection);
        }
    }

    @Override
    public T set(int index, T element)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().set(index, element);
        }
    }

    @Override
    public void add(int index, T element)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().add(index, element);
        }
    }

    @Override
    public T remove(int index)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().remove(index);
        }
    }

    @Override
    public MutableList<T> subList(int fromIndex, int toIndex)
    {
        synchronized (this.getLock())
        {
            return SynchronizedMutableList.of(this.getDelegate().subList(fromIndex, toIndex), this.getLock());
        }
    }

    @Override
    public T get(int index)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().get(index);
        }
    }

    @Override
    public int lastIndexOf(Object o)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().lastIndexOf(o);
        }
    }

    @Override
    public ListIterator<T> listIterator()
    {
        return this.getDelegate().listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index)
    {
        return this.getDelegate().listIterator(index);
    }

    @Override
    public ParallelListIterable<T> asParallel(ExecutorService executorService, int batchSize)
    {
        return new SynchronizedParallelListIterable<>(this.getDelegate().asParallel(executorService, batchSize), this.getLock());
    }

    @Override
    public int binarySearch(T key, Comparator<? super T> comparator)
    {
        synchronized (this.getLock())
        {
            return Collections.binarySearch(this, key, comparator);
        }
    }

    @Override
    public int binarySearch(T key)
    {
        synchronized (this.getLock())
        {
            return Collections.binarySearch((List<? extends Comparable<? super T>>) this, key);
        }
    }

    @Override
    public int indexOf(Object o)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().indexOf(o);
        }
    }

    @Override
    public MutableList<T> distinct()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().distinct();
        }
    }

    @Override
    public MutableList<T> distinct(HashingStrategy<? super T> hashingStrategy)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().distinct(hashingStrategy);
        }
    }

    @Override
    public <S> boolean corresponds(OrderedIterable<S> other, Predicate2<? super T, ? super S> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().corresponds(other, predicate);
        }
    }

    @Override
    public void forEach(int fromIndex, int toIndex, Procedure<? super T> procedure)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().forEach(fromIndex, toIndex, procedure);
        }
    }

    @Override
    public MutableList<T> takeWhile(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().takeWhile(predicate);
        }
    }

    @Override
    public void forEachWithIndex(int fromIndex, int toIndex, ObjectIntProcedure<? super T> objectIntProcedure)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().forEachWithIndex(fromIndex, toIndex, objectIntProcedure);
        }
    }

    @Override
    public MutableList<T> dropWhile(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().dropWhile(predicate);
        }
    }

    @Override
    public PartitionMutableList<T> partitionWhile(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().partitionWhile(predicate);
        }
    }

    @Override
    public int detectIndex(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().detectIndex(predicate);
        }
    }

    @Override
    public int detectLastIndex(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().detectLastIndex(predicate);
        }
    }

    @Override
    public MutableList<T> take(int count)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().take(count);
        }
    }

    @Override
    public MutableList<T> drop(int count)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().drop(count);
        }
    }

    @Override
    public void reverseForEach(Procedure<? super T> procedure)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().reverseForEach(procedure);
        }
    }

    @Override
    public MutableList<T> sortThis(Comparator<? super T> comparator)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().sortThis(comparator);
            return this;
        }
    }

    @Override
    public MutableList<T> sortThis()
    {
        synchronized (this.getLock())
        {
            this.getDelegate().sortThis();
            return this;
        }
    }

    @Override
    public <V extends Comparable<? super V>> MutableList<T> sortThisBy(Function<? super T, ? extends V> function)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().sortThisBy(function);
            return this;
        }
    }

    @Override
    public MutableList<T> sortThisByInt(IntFunction<? super T> function)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().sortThisByInt(function);
            return this;
        }
    }

    @Override
    public MutableList<T> sortThisByBoolean(BooleanFunction<? super T> function)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().sortThisByBoolean(function);
            return this;
        }
    }

    @Override
    public MutableList<T> sortThisByChar(CharFunction<? super T> function)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().sortThisByChar(function);
            return this;
        }
    }

    @Override
    public MutableList<T> sortThisByByte(ByteFunction<? super T> function)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().sortThisByByte(function);
            return this;
        }
    }

    @Override
    public MutableList<T> sortThisByShort(ShortFunction<? super T> function)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().sortThisByShort(function);
            return this;
        }
    }

    @Override
    public MutableList<T> sortThisByFloat(FloatFunction<? super T> function)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().sortThisByFloat(function);
            return this;
        }
    }

    @Override
    public MutableList<T> sortThisByLong(LongFunction<? super T> function)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().sortThisByLong(function);
            return this;
        }
    }

    @Override
    public MutableList<T> sortThisByDouble(DoubleFunction<? super T> function)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().sortThisByDouble(function);
            return this;
        }
    }

    @Override
    public MutableList<T> reverseThis()
    {
        synchronized (this.getLock())
        {
            this.getDelegate().reverseThis();
            return this;
        }
    }

    @Override
    public MutableList<T> shuffleThis()
    {
        synchronized (this.getLock())
        {
            this.getDelegate().shuffleThis();
            return this;
        }
    }

    @Override
    public MutableList<T> shuffleThis(Random rnd)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().shuffleThis(rnd);
            return this;
        }
    }

    @Override
    public LazyIterable<T> asReversed()
    {
        synchronized (this.getLock())
        {
            return ReverseIterable.adapt(this);
        }
    }

    @Override
    public MutableList<T> toReversed()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().toReversed();
        }
    }

    @Override
    public MutableStack<T> toStack()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().toStack();
        }
    }

    @Override
    public ImmutableList<T> toImmutable()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().toImmutable();
        }
    }

    @Override
    public MutableList<T> tap(Procedure<? super T> procedure)
    {
        synchronized (this.getLock())
        {
            this.forEach(procedure);
            return this;
        }
    }

    @Override
    public MutableList<T> select(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().select(predicate);
        }
    }

    @Override
    public <P> MutableList<T> selectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().selectWith(predicate, parameter);
        }
    }

    @Override
    public MutableList<T> reject(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().reject(predicate);
        }
    }

    @Override
    public <P> MutableList<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().rejectWith(predicate, parameter);
        }
    }

    @Override
    public PartitionMutableList<T> partition(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().partition(predicate);
        }
    }

    @Override
    public <P> PartitionMutableList<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().partitionWith(predicate, parameter);
        }
    }

    @Override
    public <S> MutableList<S> selectInstancesOf(Class<S> clazz)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().selectInstancesOf(clazz);
        }
    }

    @Override
    public MutableBooleanList collectBoolean(BooleanFunction<? super T> booleanFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectBoolean(booleanFunction);
        }
    }

    @Override
    public MutableByteList collectByte(ByteFunction<? super T> byteFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectByte(byteFunction);
        }
    }

    @Override
    public MutableCharList collectChar(CharFunction<? super T> charFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectChar(charFunction);
        }
    }

    @Override
    public MutableDoubleList collectDouble(DoubleFunction<? super T> doubleFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectDouble(doubleFunction);
        }
    }

    @Override
    public MutableFloatList collectFloat(FloatFunction<? super T> floatFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectFloat(floatFunction);
        }
    }

    @Override
    public MutableIntList collectInt(IntFunction<? super T> intFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectInt(intFunction);
        }
    }

    @Override
    public MutableLongList collectLong(LongFunction<? super T> longFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectLong(longFunction);
        }
    }

    @Override
    public MutableShortList collectShort(ShortFunction<? super T> shortFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectShort(shortFunction);
        }
    }

    @Override
    public <V> MutableList<V> collect(Function<? super T, ? extends V> function)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collect(function);
        }
    }

    @Override
    public <P, V> MutableList<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectWith(function, parameter);
        }
    }

    @Override
    public <V> MutableList<V> collectIf(
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectIf(predicate, function);
        }
    }

    @Override
    public <V> MutableList<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().flatCollect(function);
        }
    }

    @Override
    public <V> MutableListMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().groupBy(function);
        }
    }

    @Override
    public <V> MutableListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().groupByEach(function);
        }
    }

    @Override
    public <S> MutableList<Pair<T, S>> zip(Iterable<S> that)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().zip(that);
        }
    }

    @Override
    public MutableList<Pair<T, Integer>> zipWithIndex()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().zipWithIndex();
        }
    }

    @Override
    public MutableList<T> asUnmodifiable()
    {
        synchronized (this.getLock())
        {
            return UnmodifiableMutableList.of(this);
        }
    }

    @Override
    public MutableList<T> asSynchronized()
    {
        return this;
    }
}
