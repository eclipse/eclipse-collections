/*
 * Copyright (c) 2017 Goldman Sachs and others.
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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.RandomAccess;
import java.util.concurrent.ExecutorService;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.Function3;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.ByteFunction;
import org.eclipse.collections.api.block.function.primitive.CharFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
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
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.collection.mutable.AbstractMutableCollection;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.impl.lazy.ReverseIterable;
import org.eclipse.collections.impl.lazy.parallel.list.ListIterableParallelIterable;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.ListIterate;
import org.eclipse.collections.impl.utility.OrderedIterate;

public abstract class AbstractMutableList<T>
        extends AbstractMutableCollection<T>
        implements MutableList<T>
{
    private static final IntObjectToIntFunction<?> HASH_CODE_FUNCTION = (hashCode, item) -> 31 * hashCode + (item == null ? 0 : item.hashCode());

    @Override
    public MutableList<T> clone()
    {
        try
        {
            return (MutableList<T>) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new AssertionError(e);
        }
    }

    @Override
    public boolean equals(Object that)
    {
        return this == that || (that instanceof List && ListIterate.equals(this, (List<?>) that));
    }

    @Override
    public int hashCode()
    {
        // Optimize injectInto in subclasses if necessary
        return this.injectInto(1, (IntObjectToIntFunction<T>) HASH_CODE_FUNCTION);
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        ListIterate.forEach(this, procedure);
    }

    @Override
    public void reverseForEach(Procedure<? super T> procedure)
    {
        if (this.notEmpty())
        {
            this.forEach(this.size() - 1, 0, procedure);
        }
    }

    @Override
    public void reverseForEachWithIndex(ObjectIntProcedure<? super T> procedure)
    {
        if (this.notEmpty())
        {
            this.forEachWithIndex(this.size() - 1, 0, procedure);
        }
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        ListIterate.forEachWithIndex(this, objectIntProcedure);
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        ListIterate.forEachWith(this, procedure, parameter);
    }

    @Override
    public <S, R extends Collection<Pair<T, S>>> R zip(Iterable<S> that, R target)
    {
        return ListIterate.zip(this, that, target);
    }

    @Override
    public <R extends Collection<Pair<T, Integer>>> R zipWithIndex(R target)
    {
        return ListIterate.zipWithIndex(this, target);
    }

    @Override
    public void forEachWithIndex(int fromIndex, int toIndex, ObjectIntProcedure<? super T> objectIntProcedure)
    {
        ListIterate.forEachWithIndex(this, fromIndex, toIndex, objectIntProcedure);
    }

    @Override
    public MutableList<T> select(Predicate<? super T> predicate)
    {
        return this.select(predicate, this.newEmpty());
    }

    @Override
    public <R extends Collection<T>> R select(Predicate<? super T> predicate, R target)
    {
        return ListIterate.select(this, predicate, target);
    }

    @Override
    public <P> MutableList<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.selectWith(predicate, parameter, this.newEmpty());
    }

    @Override
    public <P, R extends Collection<T>> R selectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            R target)
    {
        return ListIterate.selectWith(this, predicate, parameter, target);
    }

    @Override
    public MutableList<T> reject(Predicate<? super T> predicate)
    {
        return this.reject(predicate, this.newEmpty());
    }

    @Override
    public <R extends Collection<T>> R reject(Predicate<? super T> predicate, R target)
    {
        return ListIterate.reject(this, predicate, target);
    }

    @Override
    public <P> MutableList<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.rejectWith(predicate, parameter, this.newEmpty());
    }

    @Override
    public <P, R extends Collection<T>> R rejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            R target)
    {
        return ListIterate.rejectWith(this, predicate, parameter, target);
    }

    @Override
    public <P> Twin<MutableList<T>> selectAndRejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        return ListIterate.selectAndRejectWith(this, predicate, parameter);
    }

    @Override
    public PartitionMutableList<T> partition(Predicate<? super T> predicate)
    {
        return ListIterate.partition(this, predicate);
    }

    @Override
    public <P> PartitionMutableList<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return ListIterate.partitionWith(this, predicate, parameter);
    }

    @Override
    public <S> MutableList<S> selectInstancesOf(Class<S> clazz)
    {
        return ListIterate.selectInstancesOf(this, clazz);
    }

    @Override
    public boolean removeIf(Predicate<? super T> predicate)
    {
        return ListIterate.removeIf(this, predicate);
    }

    @Override
    public <P> boolean removeIfWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return ListIterate.removeIfWith(this, predicate, parameter);
    }

    @Override
    public <V> MutableList<V> collect(Function<? super T, ? extends V> function)
    {
        return this.collect(function, FastList.newList());
    }

    @Override
    public MutableBooleanList collectBoolean(BooleanFunction<? super T> booleanFunction)
    {
        return ListIterate.collectBoolean(this, booleanFunction);
    }

    @Override
    public MutableByteList collectByte(ByteFunction<? super T> byteFunction)
    {
        return ListIterate.collectByte(this, byteFunction);
    }

    @Override
    public MutableCharList collectChar(CharFunction<? super T> charFunction)
    {
        return ListIterate.collectChar(this, charFunction);
    }

    @Override
    public MutableDoubleList collectDouble(DoubleFunction<? super T> doubleFunction)
    {
        return ListIterate.collectDouble(this, doubleFunction);
    }

    @Override
    public MutableFloatList collectFloat(FloatFunction<? super T> floatFunction)
    {
        return ListIterate.collectFloat(this, floatFunction);
    }

    @Override
    public MutableIntList collectInt(IntFunction<? super T> intFunction)
    {
        return ListIterate.collectInt(this, intFunction);
    }

    @Override
    public MutableLongList collectLong(LongFunction<? super T> longFunction)
    {
        return ListIterate.collectLong(this, longFunction);
    }

    @Override
    public MutableShortList collectShort(ShortFunction<? super T> shortFunction)
    {
        return ListIterate.collectShort(this, shortFunction);
    }

    @Override
    public <V, R extends Collection<V>> R collect(Function<? super T, ? extends V> function, R target)
    {
        return ListIterate.collect(this, function, target);
    }

    @Override
    public <V> MutableList<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.flatCollect(function, FastList.newList());
    }

    @Override
    public <V, R extends Collection<V>> R flatCollect(
            Function<? super T, ? extends Iterable<V>> function, R target)
    {
        return ListIterate.flatCollect(this, function, target);
    }

    @Override
    public <P, V> MutableList<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.collectWith(function, parameter, FastList.newList());
    }

    @Override
    public <P, A, R extends Collection<A>> R collectWith(
            Function2<? super T, ? super P, ? extends A> function, P parameter, R target)
    {
        return ListIterate.collectWith(this, function, parameter, target);
    }

    @Override
    public <V> MutableList<V> collectIf(
            Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return this.collectIf(predicate, function, FastList.newList());
    }

    @Override
    public <V, R extends Collection<V>> R collectIf(
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function,
            R target)
    {
        return ListIterate.collectIf(this, predicate, function, target);
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return ListIterate.detect(this, predicate);
    }

    @Override
    public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return ListIterate.detectWith(this, predicate, parameter);
    }

    @Override
    public Optional<T> detectOptional(Predicate<? super T> predicate)
    {
        return ListIterate.detectOptional(this, predicate);
    }

    @Override
    public <P> Optional<T> detectWithOptional(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return ListIterate.detectWithOptional(this, predicate, parameter);
    }

    @Override
    public int detectIndex(Predicate<? super T> predicate)
    {
        return ListIterate.detectIndex(this, predicate);
    }

    @Override
    public int detectLastIndex(Predicate<? super T> predicate)
    {
        return ListIterate.detectLastIndex(this, predicate);
    }

    @Override
    public T min(Comparator<? super T> comparator)
    {
        return ListIterate.min(this, comparator);
    }

    @Override
    public T max(Comparator<? super T> comparator)
    {
        return ListIterate.max(this, comparator);
    }

    @Override
    public T min()
    {
        return ListIterate.min(this);
    }

    @Override
    public T max()
    {
        return ListIterate.max(this);
    }

    @Override
    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        return ListIterate.minBy(this, function);
    }

    @Override
    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        return ListIterate.maxBy(this, function);
    }

    @Override
    public int count(Predicate<? super T> predicate)
    {
        return ListIterate.count(this, predicate);
    }

    @Override
    public <P> int countWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return ListIterate.countWith(this, predicate, parameter);
    }

    @Override
    public <S> boolean corresponds(OrderedIterable<S> other, Predicate2<? super T, ? super S> predicate)
    {
        return OrderedIterate.corresponds(this, other, predicate);
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return ListIterate.anySatisfy(this, predicate);
    }

    @Override
    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return ListIterate.anySatisfyWith(this, predicate, parameter);
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return ListIterate.allSatisfy(this, predicate);
    }

    @Override
    public <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return ListIterate.allSatisfyWith(this, predicate, parameter);
    }

    @Override
    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return ListIterate.noneSatisfy(this, predicate);
    }

    @Override
    public <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return ListIterate.noneSatisfyWith(this, predicate, parameter);
    }

    @Override
    public <IV> IV injectInto(IV injectedValue, Function2<? super IV, ? super T, ? extends IV> function)
    {
        return ListIterate.injectInto(injectedValue, this, function);
    }

    @Override
    public int injectInto(int injectedValue, IntObjectToIntFunction<? super T> function)
    {
        return ListIterate.injectInto(injectedValue, this, function);
    }

    @Override
    public float injectInto(float injectedValue, FloatObjectToFloatFunction<? super T> function)
    {
        return ListIterate.injectInto(injectedValue, this, function);
    }

    @Override
    public MutableList<T> distinct()
    {
        return ListIterate.distinct(this);
    }

    @Override
    public MutableList<T> distinct(HashingStrategy<? super T> hashingStrategy)
    {
        return ListIterate.distinct(this, hashingStrategy);
    }

    /**
     * @since 9.0
     */
    @Override
    public <V> MutableList<T> distinctBy(Function<? super T, ? extends V> function)
    {
        return this.distinct(HashingStrategies.fromFunction(function));
    }

    @Override
    public long sumOfInt(IntFunction<? super T> function)
    {
        return ListIterate.sumOfInt(this, function);
    }

    @Override
    public long sumOfLong(LongFunction<? super T> function)
    {
        return ListIterate.sumOfLong(this, function);
    }

    @Override
    public double sumOfFloat(FloatFunction<? super T> function)
    {
        return ListIterate.sumOfFloat(this, function);
    }

    @Override
    public double sumOfDouble(DoubleFunction<? super T> function)
    {
        return ListIterate.sumOfDouble(this, function);
    }

    @Override
    public long injectInto(long injectedValue, LongObjectToLongFunction<? super T> function)
    {
        return ListIterate.injectInto(injectedValue, this, function);
    }

    @Override
    public <IV, P> IV injectIntoWith(
            IV injectValue, Function3<? super IV, ? super T, ? super P, ? extends IV> function, P parameter)
    {
        return ListIterate.injectIntoWith(injectValue, this, function, parameter);
    }

    @Override
    public MutableList<T> toList()
    {
        return FastList.newList(this);
    }

    @Override
    public MutableList<T> toSortedList()
    {
        return this.toSortedList(Comparators.naturalOrder());
    }

    @Override
    public MutableList<T> toSortedList(Comparator<? super T> comparator)
    {
        return this.toList().sortThis(comparator);
    }

    @Override
    public MutableSet<T> toSet()
    {
        return UnifiedSet.newSet(this);
    }

    @Override
    public MutableStack<T> toStack()
    {
        return Stacks.mutable.withAll(this);
    }

    @Override
    public MutableList<T> asUnmodifiable()
    {
        return UnmodifiableMutableList.of(this);
    }

    @Override
    public ImmutableList<T> toImmutable()
    {
        return Lists.immutable.withAll(this);
    }

    @Override
    public MutableList<T> asSynchronized()
    {
        return SynchronizedMutableList.of(this);
    }

    @Override
    public MutableList<T> sortThis(Comparator<? super T> comparator)
    {
        if (this.size() < 10)
        {
            if (comparator == null)
            {
                this.insertionSort();
            }
            else
            {
                this.insertionSort(comparator);
            }
        }
        else
        {
            this.defaultSort(comparator);
        }
        return this;
    }

    /**
     * Override in subclasses where it can be optimized.
     */
    protected void defaultSort(Comparator<? super T> comparator)
    {
        Collections.sort(this, comparator);
    }

    private void insertionSort(Comparator<? super T> comparator)
    {
        for (int i = 0; i < this.size(); i++)
        {
            for (int j = i; j > 0 && comparator.compare(this.get(j - 1), this.get(j)) > 0; j--)
            {
                Collections.swap(this, j, j - 1);
            }
        }
    }

    private void insertionSort()
    {
        for (int i = 0; i < this.size(); i++)
        {
            for (int j = i; j > 0 && ((Comparable<T>) this.get(j - 1)).compareTo(this.get(j)) > 0; j--)
            {
                Collections.swap(this, j, j - 1);
            }
        }
    }

    @Override
    public MutableList<T> sortThis()
    {
        return this.sortThis(Comparators.naturalOrder());
    }

    @Override
    public <V extends Comparable<? super V>> MutableList<T> sortThisBy(Function<? super T, ? extends V> function)
    {
        return this.sortThis(Comparators.byFunction(function));
    }

    @Override
    public MutableList<T> sortThisByInt(IntFunction<? super T> function)
    {
        return this.sortThis(Functions.toIntComparator(function));
    }

    @Override
    public MutableList<T> sortThisByBoolean(BooleanFunction<? super T> function)
    {
        return this.sortThis(Functions.toBooleanComparator(function));
    }

    @Override
    public MutableList<T> sortThisByChar(CharFunction<? super T> function)
    {
        return this.sortThis(Functions.toCharComparator(function));
    }

    @Override
    public MutableList<T> sortThisByByte(ByteFunction<? super T> function)
    {
        return this.sortThis(Functions.toByteComparator(function));
    }

    @Override
    public MutableList<T> sortThisByShort(ShortFunction<? super T> function)
    {
        return this.sortThis(Functions.toShortComparator(function));
    }

    @Override
    public MutableList<T> sortThisByFloat(FloatFunction<? super T> function)
    {
        return this.sortThis(Functions.toFloatComparator(function));
    }

    @Override
    public MutableList<T> sortThisByLong(LongFunction<? super T> function)
    {
        return this.sortThis(Functions.toLongComparator(function));
    }

    @Override
    public MutableList<T> sortThisByDouble(DoubleFunction<? super T> function)
    {
        return this.sortThis(Functions.toDoubleComparator(function));
    }

    @Override
    public MutableList<T> newEmpty()
    {
        return Lists.mutable.empty();
    }

    @Override
    public MutableList<T> tap(Procedure<? super T> procedure)
    {
        this.forEach(procedure);
        return this;
    }

    @Override
    public void forEach(int from, int to, Procedure<? super T> procedure)
    {
        ListIterate.forEach(this, from, to, procedure);
    }

    @Override
    public int indexOf(Object object)
    {
        for (int i = 0; i < this.size(); i++)
        {
            if (Comparators.nullSafeEquals(this.get(i), object))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object)
    {
        for (int i = this.size() - 1; i >= 0; i--)
        {
            if (Comparators.nullSafeEquals(this.get(i), object))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new MutableIterator<>(this);
    }

    @Override
    public ListIterator<T> listIterator()
    {
        return this.listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index)
    {
        if (index < 0 || index > this.size())
        {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        return new MutableListIterator<>(this, index);
    }

    @Override
    public MutableList<T> toReversed()
    {
        return FastList.newList(this).reverseThis();
    }

    @Override
    public MutableList<T> reverseThis()
    {
        Collections.reverse(this);
        return this;
    }

    @Override
    public MutableList<T> shuffleThis()
    {
        Collections.shuffle(this);
        return this;
    }

    @Override
    public MutableList<T> shuffleThis(Random rnd)
    {
        Collections.shuffle(this, rnd);
        return this;
    }

    @Override
    public MutableList<T> subList(int fromIndex, int toIndex)
    {
        return new SubList<>(this, fromIndex, toIndex);
    }

    protected static class SubList<T>
            extends AbstractMutableList<T>
            implements Serializable, RandomAccess
    {
        // Not important since it uses writeReplace()
        private static final long serialVersionUID = 1L;

        private final MutableList<T> original;
        private final int offset;
        private int size;

        protected SubList(AbstractMutableList<T> list, int fromIndex, int toIndex)
        {
            if (fromIndex < 0)
            {
                throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
            }
            if (toIndex > list.size())
            {
                throw new IndexOutOfBoundsException("toIndex = " + toIndex);
            }
            if (fromIndex > toIndex)
            {
                throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ')');
            }
            this.original = list;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
        }

        @Override
        public MutableList<T> toReversed()
        {
            return FastList.newList(this).reverseThis();
        }

        protected Object writeReplace()
        {
            return FastList.newList(this);
        }

        @Override
        public boolean add(T o)
        {
            this.original.add(this.offset + this.size, o);
            this.size++;
            return true;
        }

        @Override
        public T set(int index, T element)
        {
            this.checkIfOutOfBounds(index);
            return this.original.set(index + this.offset, element);
        }

        @Override
        public T get(int index)
        {
            this.checkIfOutOfBounds(index);
            return this.original.get(index + this.offset);
        }

        @Override
        public int size()
        {
            return this.size;
        }

        @Override
        public void add(int index, T element)
        {
            this.checkIfOutOfBounds(index);
            this.original.add(index + this.offset, element);
            this.size++;
        }

        @Override
        public T remove(int index)
        {
            this.checkIfOutOfBounds(index);
            T result = this.original.remove(index + this.offset);
            this.size--;
            return result;
        }

        @Override
        public void clear()
        {
            for (Iterator<T> iterator = this.iterator(); iterator.hasNext(); )
            {
                iterator.next();
                iterator.remove();
            }
        }

        @Override
        public boolean addAll(Collection<? extends T> collection)
        {
            return this.addAll(this.size, collection);
        }

        @Override
        public boolean addAll(int index, Collection<? extends T> collection)
        {
            if (index < 0 || index > this.size)
            {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
            }
            int cSize = collection.size();
            if (cSize == 0)
            {
                return false;
            }
            this.original.addAll(this.offset + index, collection);
            this.size += cSize;
            return true;
        }

        @Override
        public Iterator<T> iterator()
        {
            return this.listIterator();
        }

        @Override
        public ListIterator<T> listIterator(int index)
        {
            if (index < 0 || index > this.size)
            {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
            }

            return new ListIterator<T>()
            {
                private final ListIterator<T> listIterator = SubList.this.original.listIterator(index + SubList.this.offset);

                public boolean hasNext()
                {
                    return this.nextIndex() < SubList.this.size;
                }

                public T next()
                {
                    if (this.hasNext())
                    {
                        return this.listIterator.next();
                    }
                    throw new NoSuchElementException();
                }

                public boolean hasPrevious()
                {
                    return this.previousIndex() >= 0;
                }

                public T previous()
                {
                    if (this.hasPrevious())
                    {
                        return this.listIterator.previous();
                    }
                    throw new NoSuchElementException();
                }

                public int nextIndex()
                {
                    return this.listIterator.nextIndex() - SubList.this.offset;
                }

                public int previousIndex()
                {
                    return this.listIterator.previousIndex() - SubList.this.offset;
                }

                public void remove()
                {
                    this.listIterator.remove();
                    SubList.this.size--;
                }

                public void set(T o)
                {
                    this.listIterator.set(o);
                }

                public void add(T o)
                {
                    this.listIterator.add(o);
                    SubList.this.size++;
                }
            };
        }

        @Override
        public MutableList<T> subList(int fromIndex, int toIndex)
        {
            return new SubList<>(this, fromIndex, toIndex);
        }

        private void checkIfOutOfBounds(int index)
        {
            if (index >= this.size || index < 0)
            {
                throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size);
            }
        }

        // Weird implementation of clone() is ok on final classes

        @Override
        public MutableList<T> clone()
        {
            return new FastList<>(this);
        }

        @Override
        public T getFirst()
        {
            return this.isEmpty() ? null : this.original.get(this.offset);
        }

        @Override
        public T getLast()
        {
            return this.isEmpty() ? null : this.original.get(this.offset + this.size - 1);
        }

        @Override
        public MutableStack<T> toStack()
        {
            return ArrayStack.newStack(this);
        }

        @Override
        public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
        {
            ListIterate.forEachWithIndex(this, objectIntProcedure);
        }

        @Override
        public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
        {
            ListIterate.forEachWith(this, procedure, parameter);
        }
    }

    @Override
    public boolean contains(Object object)
    {
        return this.indexOf(object) > -1;
    }

    @Override
    public boolean containsAll(Collection<?> source)
    {
        return Iterate.allSatisfyWith(source, Predicates2.in(), this);
    }

    @Override
    public boolean removeAll(Collection<?> collection)
    {
        int currentSize = this.size();
        this.removeIfWith(Predicates2.in(), collection);
        return currentSize != this.size();
    }

    @Override
    public boolean retainAll(Collection<?> collection)
    {
        int currentSize = this.size();
        this.removeIfWith(Predicates2.notIn(), collection);
        return currentSize != this.size();
    }

    @Override
    public T getFirst()
    {
        return ListIterate.getFirst(this);
    }

    @Override
    public T getLast()
    {
        return ListIterate.getLast(this);
    }

    @Override
    public void appendString(Appendable appendable, String separator)
    {
        this.appendString(appendable, "", separator, "");
    }

    @Override
    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        ListIterate.appendString(this, appendable, start, separator, end);
    }

    @Override
    public <V> FastListMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return ListIterate.groupBy(this, function);
    }

    @Override
    public <V> FastListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return ListIterate.groupByEach(this, function);
    }

    @Override
    public <K> MutableMap<K, T> groupByUniqueKey(Function<? super T, ? extends K> function)
    {
        return ListIterate.groupByUniqueKey(this, function);
    }

    @Override
    public <S> MutableList<Pair<T, S>> zip(Iterable<S> that)
    {
        return ListIterate.zip(this, that);
    }

    @Override
    public MutableList<Pair<T, Integer>> zipWithIndex()
    {
        return ListIterate.zipWithIndex(this);
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
    public ReverseIterable<T> asReversed()
    {
        return ReverseIterable.adapt(this);
    }

    @Override
    public ParallelListIterable<T> asParallel(ExecutorService executorService, int batchSize)
    {
        return new ListIterableParallelIterable<>(this, executorService, batchSize);
    }

    @Override
    public int binarySearch(T key, Comparator<? super T> comparator)
    {
        return Collections.binarySearch(this, key, comparator);
    }

    @Override
    public RichIterable<RichIterable<T>> chunk(int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Size for groups must be positive but was: " + size);
        }

        if (this instanceof RandomAccess)
        {
            MutableList<RichIterable<T>> result = Lists.mutable.empty();
            int i = 0;
            while (i < this.size())
            {
                MutableList<T> batch = new FastList<>(Math.min(size, this.size() - i));

                for (int j = 0; j < size && i < this.size(); j++)
                {
                    batch.add(this.get(i));
                    i++;
                }
                result.add(batch);
            }
            return result;
        }

        return super.chunk(size);
    }

    @Override
    public MutableList<T> take(int count)
    {
        return ListIterate.take(this, count);
    }

    @Override
    public MutableList<T> takeWhile(Predicate<? super T> predicate)
    {
        return ListIterate.takeWhile(this, predicate);
    }

    @Override
    public MutableList<T> drop(int count)
    {
        return ListIterate.drop(this, count);
    }

    @Override
    public MutableList<T> dropWhile(Predicate<? super T> predicate)
    {
        return ListIterate.dropWhile(this, predicate);
    }

    @Override
    public PartitionMutableList<T> partitionWhile(Predicate<? super T> predicate)
    {
        return ListIterate.partitionWhile(this, predicate);
    }
}
