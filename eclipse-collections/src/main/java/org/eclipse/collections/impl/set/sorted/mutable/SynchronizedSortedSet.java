/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.sorted.mutable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ExecutorService;

import org.eclipse.collections.api.LazyIterable;
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
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableByteList;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableFloatList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.list.primitive.MutableShortList;
import org.eclipse.collections.api.multimap.sortedset.MutableSortedSetMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.set.sorted.PartitionMutableSortedSet;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.set.sorted.ParallelSortedSetIterable;
import org.eclipse.collections.api.set.sorted.SortedSetIterable;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.collection.mutable.AbstractSynchronizedMutableCollection;
import org.eclipse.collections.impl.collection.mutable.SynchronizedCollectionSerializationProxy;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.lazy.parallel.set.sorted.SynchronizedParallelSortedSetIterable;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * A synchronized view of a {@link MutableSortedSet}. It is imperative that the user manually synchronize on the collection when iterating over it using the
 * standard JDK iterator or JDK 5 for loop, as per {@link Collections#synchronizedCollection(Collection)}.
 *
 * @see MutableSortedSet#asSynchronized()
 */
@ThreadSafe
public class SynchronizedSortedSet<T>
        extends AbstractSynchronizedMutableCollection<T>
        implements MutableSortedSet<T>, Serializable
{
    private static final long serialVersionUID = 2L;

    SynchronizedSortedSet(MutableSortedSet<T> set)
    {
        super(set);
    }

    SynchronizedSortedSet(MutableSortedSet<T> set, Object newLock)
    {
        super(set, newLock);
    }

    /**
     * This method will take a MutableSortedSet and wrap it directly in a SynchronizedSortedSet.  It will
     * take any other non-GS-collection and first adapt it will a SortedSetAdapter, and then return a
     * SynchronizedSortedSet that wraps the adapter.
     */
    public static <E, S extends SortedSet<E>> SynchronizedSortedSet<E> of(S set)
    {
        return new SynchronizedSortedSet<E>(SortedSetAdapter.adapt(set));
    }

    /**
     * This method will take a MutableSortedSet and wrap it directly in a SynchronizedSortedSet.  It will
     * take any other non-GS-collection and first adapt it will a SortedSetAdapter, and then return a
     * SynchronizedSortedSet that wraps the adapter.  Additionally, a developer specifies which lock to use
     * with the collection.
     */
    public static <E, S extends SortedSet<E>> MutableSortedSet<E> of(S set, Object lock)
    {
        return new SynchronizedSortedSet<E>(SortedSetAdapter.adapt(set), lock);
    }

    @Override
    @GuardedBy("getLock()")
    protected MutableSortedSet<T> getDelegate()
    {
        return (MutableSortedSet<T>) super.getDelegate();
    }

    public MutableSortedSet<T> with(T element)
    {
        this.add(element);
        return this;
    }

    public MutableSortedSet<T> without(T element)
    {
        this.remove(element);
        return this;
    }

    public MutableSortedSet<T> withAll(Iterable<? extends T> elements)
    {
        this.addAllIterable(elements);
        return this;
    }

    public MutableSortedSet<T> withoutAll(Iterable<? extends T> elements)
    {
        this.removeAllIterable(elements);
        return this;
    }

    public MutableSortedSet<T> newEmpty()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().newEmpty().asSynchronized();
        }
    }

    @Override
    public MutableSortedSet<T> clone()
    {
        synchronized (this.getLock())
        {
            return SynchronizedSortedSet.of(this.getDelegate().clone());
        }
    }

    protected Object writeReplace()
    {
        return new SynchronizedCollectionSerializationProxy<T>(this.getDelegate());
    }

    public Comparator<? super T> comparator()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().comparator();
        }
    }

    public int compareTo(SortedSetIterable<T> o)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().compareTo(o);
        }
    }

    public T first()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().first();
        }
    }

    public T last()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().last();
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

    public PartitionMutableSortedSet<T> partitionWhile(Predicate<? super T> predicate)
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

    public MutableSortedSet<T> takeWhile(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().takeWhile(predicate);
        }
    }

    public MutableSortedSet<T> dropWhile(Predicate<? super T> predicate)
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

    public MutableSortedSet<T> tap(Procedure<? super T> procedure)
    {
        synchronized (this.getLock())
        {
            this.forEach(procedure);
            return this;
        }
    }

    public MutableSortedSet<T> select(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().select(predicate);
        }
    }

    public <P> MutableSortedSet<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().selectWith(predicate, parameter);
        }
    }

    public MutableSortedSet<T> reject(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().reject(predicate);
        }
    }

    public <P> MutableSortedSet<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().rejectWith(predicate, parameter);
        }
    }

    public PartitionMutableSortedSet<T> partition(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().partition(predicate);
        }
    }

    public <P> PartitionMutableSortedSet<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().partitionWith(predicate, parameter);
        }
    }

    public <S> MutableSortedSet<S> selectInstancesOf(Class<S> clazz)
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

    public <V> MutableSortedSetMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().groupBy(function);
        }
    }

    public <V> MutableSortedSetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
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

    public MutableSortedSet<Pair<T, Integer>> zipWithIndex()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().zipWithIndex();
        }
    }

    public <R extends Set<T>> R unionInto(SetIterable<? extends T> set, R targetSet)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().unionInto(set, targetSet);
        }
    }

    public <R extends Set<T>> R intersectInto(SetIterable<? extends T> set, R targetSet)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().intersectInto(set, targetSet);
        }
    }

    public <R extends Set<T>> R differenceInto(SetIterable<? extends T> subtrahendSet, R targetSet)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().differenceInto(subtrahendSet, targetSet);
        }
    }

    public <R extends Set<T>> R symmetricDifferenceInto(SetIterable<? extends T> set, R targetSet)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().symmetricDifferenceInto(set, targetSet);
        }
    }

    public boolean isSubsetOf(SetIterable<? extends T> candidateSuperset)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().isSubsetOf(candidateSuperset);
        }
    }

    public boolean isProperSubsetOf(SetIterable<? extends T> candidateSuperset)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().isProperSubsetOf(candidateSuperset);
        }
    }

    public <B> LazyIterable<Pair<T, B>> cartesianProduct(SetIterable<B> set)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().cartesianProduct(set);
        }
    }

    public MutableSortedSet<T> union(SetIterable<? extends T> set)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().union(set);
        }
    }

    public MutableSortedSet<T> intersect(SetIterable<? extends T> set)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().intersect(set);
        }
    }

    public MutableSortedSet<T> difference(SetIterable<? extends T> subtrahendSet)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().difference(subtrahendSet);
        }
    }

    public MutableSortedSet<T> symmetricDifference(SetIterable<? extends T> setB)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().symmetricDifference(setB);
        }
    }

    public MutableSortedSet<SortedSetIterable<T>> powerSet()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().powerSet();
        }
    }

    public ParallelSortedSetIterable<T> asParallel(ExecutorService executorService, int batchSize)
    {
        return new SynchronizedParallelSortedSetIterable<T>(this.getDelegate().asParallel(executorService, batchSize), this);
    }

    public ImmutableSortedSet<T> toImmutable()
    {
        synchronized (this.getLock())
        {
            return SortedSets.immutable.withSortedSet(this.getDelegate());
        }
    }

    public MutableSortedSet<T> asUnmodifiable()
    {
        synchronized (this.getLock())
        {
            return UnmodifiableSortedSet.of(this);
        }
    }

    public MutableSortedSet<T> asSynchronized()
    {
        return this;
    }

    public MutableSortedSet<T> subSet(T fromElement, T toElement)
    {
        synchronized (this.getLock())
        {
            return SynchronizedSortedSet.of(this.getDelegate().subSet(fromElement, toElement), this.getLock());
        }
    }

    public MutableSortedSet<T> headSet(T toElement)
    {
        synchronized (this.getLock())
        {
            return SynchronizedSortedSet.of(this.getDelegate().headSet(toElement), this.getLock());
        }
    }

    public MutableSortedSet<T> tailSet(T fromElement)
    {
        synchronized (this.getLock())
        {
            return SynchronizedSortedSet.of(this.getDelegate().tailSet(fromElement), this.getLock());
        }
    }

    public void reverseForEach(Procedure<? super T> procedure)
    {
        synchronized (this.getLock())
        {
            this.getDelegate().reverseForEach(procedure);
        }
    }

    public LazyIterable<T> asReversed()
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".asReversed() not implemented yet");
    }

    public MutableSortedSet<T> toReversed()
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().toReversed();
        }
    }

    public int detectLastIndex(Predicate<? super T> predicate)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().detectLastIndex(predicate);
        }
    }

    public MutableSortedSet<T> take(int count)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().take(count);
        }
    }

    public MutableSortedSet<T> drop(int count)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().drop(count);
        }
    }
}
