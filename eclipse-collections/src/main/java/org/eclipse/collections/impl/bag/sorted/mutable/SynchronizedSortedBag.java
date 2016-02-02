/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.sorted.mutable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.bag.ParallelBag;
import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.bag.sorted.SortedBag;
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
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableByteList;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableFloatList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.list.primitive.MutableShortList;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.sortedbag.MutableSortedBagMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.sorted.PartitionMutableSortedBag;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.collection.mutable.AbstractSynchronizedMutableCollection;
import org.eclipse.collections.impl.collection.mutable.SynchronizedCollectionSerializationProxy;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;

/**
 * A synchronized view of a {@link MutableSortedBag}. It is imperative that the user manually synchronize on the collection when iterating over it using the
 * standard JDK iterator or JDK 5 for loop, as per {@link Collections#synchronizedCollection(Collection)}.
 *
 * @see MutableSortedBag#asSynchronized()
 */
@ThreadSafe
public class SynchronizedSortedBag<T>
        extends AbstractSynchronizedMutableCollection<T>
        implements MutableSortedBag<T>, Serializable
{
    private static final long serialVersionUID = 2L;

    SynchronizedSortedBag(MutableSortedBag<T> bag)
    {
        super(bag);
    }

    SynchronizedSortedBag(MutableSortedBag<T> bag, Object newLock)
    {
        super(bag, newLock);
    }

    public static <E> SynchronizedSortedBag<E> of(MutableSortedBag<E> bag)
    {
        return new SynchronizedSortedBag<E>(bag);
    }

    public static <E> MutableSortedBag<E> of(MutableSortedBag<E> bag, Object lock)
    {
        return new SynchronizedSortedBag<E>(bag, lock);
    }

    @Override
    @GuardedBy("getLock()")
    protected MutableSortedBag<T> getDelegate()
    {
        return (MutableSortedBag<T>) super.getDelegate();
    }

    public MutableSortedBag<T> with(T element)
    {
        this.add(element);
        return this;
    }

    public MutableSortedBag<T> without(T element)
    {
        this.remove(element);
        return this;
    }

    public MutableSortedBag<T> withAll(Iterable<? extends T> elements)
    {
        this.addAllIterable(elements);
        return this;
    }

    public MutableSortedBag<T> withoutAll(Iterable<? extends T> elements)
    {
        this.removeAllIterable(elements);
        return this;
    }

    public MutableSortedBag<T> newEmpty()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().newEmpty().asSynchronized();
        }
    }

    @Override
    public MutableSortedBag<T> clone()
    {
        synchronized (this.getLock())
        {
            return SynchronizedSortedBag.of(this.getDelegate().clone());
        }
    }

    public Comparator<? super T> comparator()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().comparator();
        }
    }

    public int compareTo(SortedBag<T> o)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().compareTo(o);
        }
    }

    protected Object writeReplace()
    {
        return new SynchronizedCollectionSerializationProxy<T>(this.getDelegate());
    }

    public int addOccurrences(T item, int occurrences)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().addOccurrences(item, occurrences);
        }
    }

    public boolean removeOccurrences(Object item, int occurrences)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().removeOccurrences(item, occurrences);
        }
    }

    public boolean setOccurrences(T item, int occurrences)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().setOccurrences(item, occurrences);
        }
    }

    public MutableList<ObjectIntPair<T>> topOccurrences(int count)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().topOccurrences(count);
        }
    }

    public MutableSortedBag<T> selectByOccurrences(IntPredicate predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().selectByOccurrences(predicate);
        }
    }

    public MutableList<ObjectIntPair<T>> bottomOccurrences(int count)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().bottomOccurrences(count);
        }
    }

    public MutableSortedMap<T, Integer> toMapOfItemToCount()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().toMapOfItemToCount();
        }
    }

    public void forEachWithOccurrences(ObjectIntProcedure<? super T> procedure)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().forEachWithOccurrences(procedure);
        }
    }

    public int occurrencesOf(Object item)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().occurrencesOf(item);
        }
    }

    public int sizeDistinct()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().sizeDistinct();
        }
    }

    public String toStringOfItemToCount()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().toStringOfItemToCount();
        }
    }

    public int indexOf(Object object)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().indexOf(object);
        }
    }

    public MutableStack<T> toStack()
    {
        synchronized (this.getLock())
        {
            return ArrayStack.newStack(this);
        }
    }

    public PartitionMutableSortedBag<T> partitionWhile(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().partitionWhile(predicate);
        }
    }

    public MutableSortedSet<T> distinct()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().distinct();
        }
    }

    public MutableSortedBag<T> takeWhile(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().takeWhile(predicate);
        }
    }

    public MutableSortedBag<T> dropWhile(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().dropWhile(predicate);
        }
    }

    public <S> boolean corresponds(OrderedIterable<S> other, Predicate2<? super T, ? super S> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().corresponds(other, predicate);
        }
    }

    public void forEach(int startIndex, int endIndex, Procedure<? super T> procedure)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().forEach(startIndex, endIndex, procedure);
        }
    }

    public void forEachWithIndex(int fromIndex, int toIndex, ObjectIntProcedure<? super T> objectIntProcedure)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().forEachWithIndex(fromIndex, toIndex, objectIntProcedure);
        }
    }

    public int detectIndex(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().detectIndex(predicate);
        }
    }

    public MutableSortedBag<T> tap(Procedure<? super T> procedure)
    {
        synchronized (this.getLock())
        {
            this.forEach(procedure);
            return this;
        }
    }

    public MutableSortedBag<T> select(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().select(predicate);
        }
    }

    public <P> MutableSortedBag<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().selectWith(predicate, parameter);
        }
    }

    public MutableSortedBag<T> reject(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().reject(predicate);
        }
    }

    public <P> MutableSortedBag<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().rejectWith(predicate, parameter);
        }
    }

    public PartitionMutableSortedBag<T> partition(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().partition(predicate);
        }
    }

    public <P> PartitionMutableSortedBag<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().partitionWith(predicate, parameter);
        }
    }

    public MutableBooleanList collectBoolean(BooleanFunction<? super T> booleanFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectBoolean(booleanFunction);
        }
    }

    public MutableByteList collectByte(ByteFunction<? super T> byteFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectByte(byteFunction);
        }
    }

    public MutableCharList collectChar(CharFunction<? super T> charFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectChar(charFunction);
        }
    }

    public MutableDoubleList collectDouble(DoubleFunction<? super T> doubleFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectDouble(doubleFunction);
        }
    }

    public MutableFloatList collectFloat(FloatFunction<? super T> floatFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectFloat(floatFunction);
        }
    }

    public MutableIntList collectInt(IntFunction<? super T> intFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectInt(intFunction);
        }
    }

    public MutableLongList collectLong(LongFunction<? super T> longFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectLong(longFunction);
        }
    }

    public MutableShortList collectShort(ShortFunction<? super T> shortFunction)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectShort(shortFunction);
        }
    }

    public <S> MutableSortedBag<S> selectInstancesOf(Class<S> clazz)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().selectInstancesOf(clazz);
        }
    }

    public <V> MutableList<V> collect(Function<? super T, ? extends V> function)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collect(function);
        }
    }

    public MutableSortedSet<Pair<T, Integer>> zipWithIndex()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().zipWithIndex();
        }
    }

    public <P, V> MutableList<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectWith(function, parameter);
        }
    }

    public <V> MutableList<V> collectIf(
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().collectIf(predicate, function);
        }
    }

    public <V> MutableList<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().flatCollect(function);
        }
    }

    public <V> MutableSortedBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().groupBy(function);
        }
    }

    public <V> MutableSortedBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().groupByEach(function);
        }
    }

    public <S> MutableList<Pair<T, S>> zip(Iterable<S> that)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().zip(that);
        }
    }

    public MutableSortedBag<T> asUnmodifiable()
    {
        synchronized (this.getLock())
        {
            return UnmodifiableSortedBag.of(this);
        }
    }

    public MutableSortedBag<T> asSynchronized()
    {
        return this;
    }

    public ImmutableSortedBag<T> toImmutable()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().toImmutable();
        }
    }

    public MutableSortedBag<T> drop(int count)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().drop(count);
        }
    }

    public MutableSortedBag<T> take(int count)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().take(count);
        }
    }

    public MutableSortedBag<T> toReversed()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().toReversed();
        }
    }

    public void reverseForEach(Procedure<? super T> procedure)
    {
        synchronized (this.lock)
        {
            this.getDelegate().reverseForEach(procedure);
        }
    }

    public LazyIterable<T> asReversed()
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".asReversed() not implemented yet");
    }

    public int detectLastIndex(Predicate<? super T> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().detectLastIndex(predicate);
        }
    }

    public ParallelBag<T> asParallel(ExecutorService executorService, int batchSize)
    {
        throw new UnsupportedOperationException("asParallel() method is not supported for " + this.getClass().getSimpleName() + '.');
    }
}
