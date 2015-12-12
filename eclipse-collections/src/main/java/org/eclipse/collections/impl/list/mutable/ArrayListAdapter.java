/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.RandomAccess;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
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
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableByteList;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableFloatList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.list.primitive.MutableShortList;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.lazy.ReverseIterable;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.CharArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ShortArrayList;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.ArrayListIterate;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.internal.RandomAccessListIterate;

/**
 * This class provides a MutableList wrapper around a JDK Collections ArrayList instance.  All of the MutableList
 * interface methods are supported in addition to the JDK ArrayList methods.
 * <p>
 * To create a new wrapper around an existing ArrayList instance, use the {@link #adapt(ArrayList)} factory method.  To
 * create a new empty wrapper, use the {@link #newList()} or {@link #newList(int)} factory methods.
 */
public final class ArrayListAdapter<T>
        extends AbstractListAdapter<T>
        implements RandomAccess, Serializable
{
    private static final long serialVersionUID = 1L;
    private final ArrayList<T> delegate;

    private ArrayListAdapter(ArrayList<T> newDelegate)
    {
        if (newDelegate == null)
        {
            throw new NullPointerException("ArrayListAdapter may not wrap null");
        }
        this.delegate = newDelegate;
    }

    @Override
    protected ArrayList<T> getDelegate()
    {
        return this.delegate;
    }

    public static <E> ArrayListAdapter<E> newList()
    {
        return new ArrayListAdapter<E>(new ArrayList<E>());
    }

    public static <E> ArrayListAdapter<E> newList(int size)
    {
        return new ArrayListAdapter<E>(new ArrayList<E>(size));
    }

    public static <E> ArrayListAdapter<E> adapt(ArrayList<E> newDelegate)
    {
        return new ArrayListAdapter<E>(newDelegate);
    }

    public MutableList<T> asUnmodifiable()
    {
        return UnmodifiableMutableList.of(this);
    }

    public MutableList<T> asSynchronized()
    {
        return SynchronizedMutableList.of(this);
    }

    public ImmutableList<T> toImmutable()
    {
        return Lists.immutable.withAll(this);
    }

    @Override
    public ArrayListAdapter<T> clone()
    {
        return new ArrayListAdapter<T>((ArrayList<T>) this.delegate.clone());
    }

    public ArrayListAdapter<T> newEmpty()
    {
        return ArrayListAdapter.newList();
    }

    @Override
    public void forEach(Procedure<? super T> procedure)
    {
        this.each(procedure);
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        ArrayListIterate.forEach(this.delegate, procedure);
    }

    public void reverseForEach(Procedure<? super T> procedure)
    {
        ArrayListIterate.reverseForEach(this.delegate, procedure);
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        ArrayListIterate.forEachWithIndex(this.delegate, objectIntProcedure);
    }

    public void forEachWithIndex(int fromIndex, int toIndex, ObjectIntProcedure<? super T> objectIntProcedure)
    {
        ArrayListIterate.forEachWithIndex(this.delegate, fromIndex, toIndex, objectIntProcedure);
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return ArrayListIterate.detect(this.delegate, predicate);
    }

    @Override
    public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
    {
        T result = this.detect(predicate);
        return result == null ? function.value() : result;
    }

    @Override
    public int count(Predicate<? super T> predicate)
    {
        return ArrayListIterate.count(this.delegate, predicate);
    }

    public <S> boolean corresponds(OrderedIterable<S> other, Predicate2<? super T, ? super S> predicate)
    {
        return RandomAccessListIterate.corresponds(this.delegate, other, predicate);
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return ArrayListIterate.anySatisfy(this.delegate, predicate);
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return ArrayListIterate.allSatisfy(this.delegate, predicate);
    }

    @Override
    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return ArrayListIterate.noneSatisfy(this.delegate, predicate);
    }

    @Override
    public <IV> IV injectInto(IV injectedValue, Function2<? super IV, ? super T, ? extends IV> function)
    {
        return ArrayListIterate.injectInto(injectedValue, this.delegate, function);
    }

    public void forEach(int fromIndex, int toIndex, Procedure<? super T> procedure)
    {
        ArrayListIterate.forEach(this.delegate, fromIndex, toIndex, procedure);
    }

    public ArrayListAdapter<T> sortThis(Comparator<? super T> comparator)
    {
        Iterate.sortThis(this.delegate, comparator);
        return this;
    }

    public ArrayListAdapter<T> sortThis()
    {
        return this.sortThis(Comparators.naturalOrder());
    }

    public ArrayListAdapter<T> with(T element)
    {
        this.add(element);
        return this;
    }

    public ArrayListAdapter<T> with(T element1, T element2)
    {
        this.add(element1);
        this.add(element2);
        return this;
    }

    public ArrayListAdapter<T> with(T element1, T element2, T element3)
    {
        this.add(element1);
        this.add(element2);
        this.add(element3);
        return this;
    }

    public ArrayListAdapter<T> with(T... elements)
    {
        ArrayIterate.forEach(elements, CollectionAddProcedure.on(this.delegate));
        return this;
    }

    public ArrayListAdapter<T> without(T element)
    {
        this.remove(element);
        return this;
    }

    public ArrayListAdapter<T> withAll(Iterable<? extends T> elements)
    {
        this.addAllIterable(elements);
        return this;
    }

    public ArrayListAdapter<T> withoutAll(Iterable<? extends T> elements)
    {
        this.removeAllIterable(elements);
        return this;
    }

    private <E> ArrayListAdapter<E> wrap(ArrayList<E> list)
    {
        return ArrayListAdapter.adapt(list);
    }

    @Override
    public ArrayListAdapter<T> select(Predicate<? super T> predicate)
    {
        return this.wrap(ArrayListIterate.select(this.delegate, predicate));
    }

    @Override
    public ArrayListAdapter<T> reject(Predicate<? super T> predicate)
    {
        return this.wrap(ArrayListIterate.reject(this.delegate, predicate));
    }

    @Override
    public PartitionMutableList<T> partition(Predicate<? super T> predicate)
    {
        return ArrayListIterate.partition(this.delegate, predicate);
    }

    @Override
    public <P> PartitionMutableList<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return ArrayListIterate.partitionWith(this.getDelegate(), predicate, parameter);
    }

    @Override
    public <S> MutableList<S> selectInstancesOf(Class<S> clazz)
    {
        return ArrayListIterate.selectInstancesOf(this.delegate, clazz);
    }

    @Override
    public <V> ArrayListAdapter<V> collect(Function<? super T, ? extends V> function)
    {
        return this.wrap(ArrayListIterate.collect(this.delegate, function));
    }

    @Override
    public MutableBooleanList collectBoolean(final BooleanFunction<? super T> booleanFunction)
    {
        final BooleanArrayList result = new BooleanArrayList(this.size());
        this.forEach(new Procedure<T>()
        {
            public void value(T each)
            {
                result.add(booleanFunction.booleanValueOf(each));
            }
        });
        return result;
    }

    @Override
    public MutableByteList collectByte(final ByteFunction<? super T> byteFunction)
    {
        final ByteArrayList result = new ByteArrayList(this.size());
        this.forEach(new Procedure<T>()
        {
            public void value(T each)
            {
                result.add(byteFunction.byteValueOf(each));
            }
        });
        return result;
    }

    @Override
    public MutableCharList collectChar(final CharFunction<? super T> charFunction)
    {
        final CharArrayList result = new CharArrayList(this.size());
        this.forEach(new Procedure<T>()
        {
            public void value(T each)
            {
                result.add(charFunction.charValueOf(each));
            }
        });
        return result;
    }

    @Override
    public MutableDoubleList collectDouble(final DoubleFunction<? super T> doubleFunction)
    {
        final DoubleArrayList result = new DoubleArrayList(this.size());
        this.forEach(new Procedure<T>()
        {
            public void value(T each)
            {
                result.add(doubleFunction.doubleValueOf(each));
            }
        });
        return result;
    }

    @Override
    public MutableFloatList collectFloat(final FloatFunction<? super T> floatFunction)
    {
        final FloatArrayList result = new FloatArrayList(this.size());
        this.forEach(new Procedure<T>()
        {
            public void value(T each)
            {
                result.add(floatFunction.floatValueOf(each));
            }
        });
        return result;
    }

    @Override
    public MutableIntList collectInt(final IntFunction<? super T> intFunction)
    {
        final IntArrayList result = new IntArrayList(this.size());
        this.forEach(new Procedure<T>()
        {
            public void value(T each)
            {
                result.add(intFunction.intValueOf(each));
            }
        });
        return result;
    }

    @Override
    public MutableLongList collectLong(final LongFunction<? super T> longFunction)
    {
        final LongArrayList result = new LongArrayList(this.size());
        this.forEach(new Procedure<T>()
        {
            public void value(T each)
            {
                result.add(longFunction.longValueOf(each));
            }
        });
        return result;
    }

    @Override
    public MutableShortList collectShort(final ShortFunction<? super T> shortFunction)
    {
        final ShortArrayList result = new ShortArrayList(this.size());
        this.forEach(new Procedure<T>()
        {
            public void value(T each)
            {
                result.add(shortFunction.shortValueOf(each));
            }
        });
        return result;
    }

    @Override
    public <V> ArrayListAdapter<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.wrap(ArrayListIterate.flatCollect(this.delegate, function));
    }

    @Override
    public <V> ArrayListAdapter<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return this.wrap(ArrayListIterate.collectIf(this.delegate, predicate, function));
    }

    public int detectIndex(Predicate<? super T> predicate)
    {
        return ArrayListIterate.detectIndex(this.delegate, predicate);
    }

    public int detectLastIndex(Predicate<? super T> predicate)
    {
        return ArrayListIterate.detectLastIndex(this.delegate, predicate);
    }

    @Override
    public <V> FastListMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return ArrayListIterate.groupBy(this.delegate, function);
    }

    @Override
    public <V> FastListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return ArrayListIterate.groupByEach(this.delegate, function);
    }

    @Override
    public <P> ArrayListAdapter<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.wrap(ArrayListIterate.selectWith(this.delegate, predicate, parameter));
    }

    @Override
    public <P> ArrayListAdapter<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.wrap(ArrayListIterate.rejectWith(this.delegate, predicate, parameter));
    }

    @Override
    public <P, A> ArrayListAdapter<A> collectWith(Function2<? super T, ? super P, ? extends A> function, P parameter)
    {
        return this.wrap(ArrayListIterate.collectWith(this.delegate, function, parameter));
    }

    public ArrayListAdapter<T> distinct()
    {
        return this.wrap(ArrayListIterate.distinct(this.delegate));
    }

    public ArrayListAdapter<T> distinct(HashingStrategy<? super T> hashingStrategy)
    {
        return this.wrap(ArrayListIterate.distinct(this.delegate, hashingStrategy));
    }

    @Override
    public <S> MutableList<Pair<T, S>> zip(Iterable<S> that)
    {
        return ArrayListIterate.zip(this.delegate, that);
    }

    @Override
    public MutableList<Pair<T, Integer>> zipWithIndex()
    {
        return ArrayListIterate.zipWithIndex(this.delegate);
    }

    public MutableList<T> take(int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        return ArrayListIterate.take(this.delegate, count, FastList.<T>newList(Math.min(this.size(), count)));
    }

    public MutableList<T> takeWhile(Predicate<? super T> predicate)
    {
        return ArrayListIterate.takeWhile(this.delegate, predicate);
    }

    public MutableList<T> drop(int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        return ArrayListIterate.drop(this.delegate, count, FastList.<T>newList(this.size() - Math.min(this.size(), count)));
    }

    public MutableList<T> dropWhile(Predicate<? super T> predicate)
    {
        return ArrayListIterate.dropWhile(this.delegate, predicate);
    }

    public PartitionMutableList<T> partitionWhile(Predicate<? super T> predicate)
    {
        return ArrayListIterate.partitionWhile(this.delegate, predicate);
    }

    @Override
    public ReverseIterable<T> asReversed()
    {
        return ReverseIterable.adapt(this);
    }
}
