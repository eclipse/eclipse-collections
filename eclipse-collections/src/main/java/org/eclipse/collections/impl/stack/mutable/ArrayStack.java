/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.mutable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
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
import org.eclipse.collections.api.block.function.primitive.ObjectIntToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableByteCollection;
import org.eclipse.collections.api.collection.primitive.MutableCharCollection;
import org.eclipse.collections.api.collection.primitive.MutableDoubleCollection;
import org.eclipse.collections.api.collection.primitive.MutableFloatCollection;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.collection.primitive.MutableLongCollection;
import org.eclipse.collections.api.collection.primitive.MutableShortCollection;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.stack.PartitionMutableStack;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.stack.ImmutableStack;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.stack.StackIterable;
import org.eclipse.collections.api.stack.primitive.MutableBooleanStack;
import org.eclipse.collections.api.stack.primitive.MutableByteStack;
import org.eclipse.collections.api.stack.primitive.MutableCharStack;
import org.eclipse.collections.api.stack.primitive.MutableDoubleStack;
import org.eclipse.collections.api.stack.primitive.MutableFloatStack;
import org.eclipse.collections.api.stack.primitive.MutableIntStack;
import org.eclipse.collections.api.stack.primitive.MutableLongStack;
import org.eclipse.collections.api.stack.primitive.MutableShortStack;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.procedure.MutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.NonMutatingAggregationProcedure;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.partition.stack.PartitionArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.BooleanArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.ByteArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.CharArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.DoubleArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.FloatArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.IntArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.LongArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.ShortArrayStack;
import org.eclipse.collections.impl.utility.LazyIterate;

/**
 * ArrayStack is a MutableStack which contains a FastList of data. ArrayStack iterates from top to bottom (LIFO order).
 * It behaves like FastList in terms of runtime complexity. The method push() is amortized constant time like
 * FastList.add(). The backing data structure grows and shrinks by 50% at a time, and size is constant. ArrayStack does
 * not extend Vector, as does the Java Stack, which was one of the reasons to create this data structure.
 */
public class ArrayStack<T> implements MutableStack<T>, Externalizable
{
    private static final long serialVersionUID = 1L;
    private FastList<T> delegate;

    public ArrayStack()
    {
        this.delegate = FastList.newList();
    }

    public ArrayStack(int initialCapacity)
    {
        this.delegate = FastList.newList(initialCapacity);
    }

    public ArrayStack(Iterable<T> items)
    {
        this.delegate = FastList.newList(items);
    }

    public ArrayStack(T... items)
    {
        this.delegate = FastList.wrapCopy(items);
    }

    public static <T> ArrayStack<T> newStack()
    {
        return new ArrayStack<>();
    }

    public static <T> ArrayStack<T> newStack(Iterable<? extends T> items)
    {
        return new ArrayStack<>((Iterable<T>) items);
    }

    public static <T> ArrayStack<T> newStackWith(T... items)
    {
        return new ArrayStack<>(items);
    }

    public static <T> ArrayStack<T> newStackFromTopToBottom(T... items)
    {
        ArrayStack<T> stack = new ArrayStack<>(items.length);
        for (int i = items.length - 1; i >= 0; i--)
        {
            stack.push(items[i]);
        }
        return stack;
    }

    public static <T> ArrayStack<T> newStackFromTopToBottom(Iterable<? extends T> items)
    {
        ArrayStack<T> stack = ArrayStack.newStack();
        stack.delegate = FastList.newList(items).reverseThis();
        return stack;
    }

    public void push(T item)
    {
        this.delegate.add(item);
    }

    public T pop()
    {
        this.checkEmptyStack();
        return this.delegate.remove(this.delegate.size() - 1);
    }

    private void checkEmptyStack()
    {
        if (this.delegate.isEmpty())
        {
            throw new EmptyStackException();
        }
    }

    public ListIterable<T> pop(int count)
    {
        this.checkNegativeCount(count);
        MutableList<T> result = FastList.newList(count);
        if (this.checkZeroCount(count))
        {
            return result;
        }
        this.checkEmptyStack();
        this.checkSizeLessThanCount(count);
        while (count > 0)
        {
            result.add(this.pop());
            count--;
        }
        return result;
    }

    public <R extends Collection<T>> R pop(int count, R targetCollection)
    {
        this.checkNegativeCount(count);
        if (this.checkZeroCount(count))
        {
            return targetCollection;
        }
        this.checkEmptyStack();
        this.checkSizeLessThanCount(count);
        while (count > 0)
        {
            targetCollection.add(this.pop());
            count--;
        }
        return targetCollection;
    }

    public <R extends MutableStack<T>> R pop(int count, R targetStack)
    {
        this.checkNegativeCount(count);
        if (this.checkZeroCount(count))
        {
            return targetStack;
        }
        this.checkEmptyStack();
        this.checkSizeLessThanCount(count);
        while (count > 0)
        {
            targetStack.push(this.pop());
            count--;
        }
        return targetStack;
    }

    public void clear()
    {
        this.delegate.clear();
    }

    private boolean checkZeroCount(int count)
    {
        return count == 0;
    }

    public T peek()
    {
        this.checkEmptyStack();
        return this.delegate.getLast();
    }

    public ListIterable<T> peek(int count)
    {
        this.checkNegativeCount(count);
        if (this.checkZeroCount(count))
        {
            return FastList.newList();
        }
        this.checkEmptyStack();
        this.checkSizeLessThanCount(count);

        FastList<T> result = FastList.newList(count);
        for (int i = 0; i < count; i++)
        {
            result.add(this.delegate.get(this.delegate.size() - (i + 1)));
        }
        return result;
    }

    public T peekAt(int index)
    {
        this.checkNegativeCount(index);
        this.checkEmptyStack();
        this.checkSizeLessThanOrEqualToIndex(index);
        return this.delegate.get(this.delegate.size() - 1 - index);
    }

    public int size()
    {
        return this.delegate.size();
    }

    public boolean isEmpty()
    {
        return this.delegate.isEmpty();
    }

    public boolean notEmpty()
    {
        return this.delegate.notEmpty();
    }

    public T getFirst()
    {
        return this.peek();
    }

    public T getLast()
    {
        throw new UnsupportedOperationException("Cannot call getLast() on " + this.getClass().getSimpleName());
    }

    public boolean contains(Object object)
    {
        return this.delegate.asReversed().contains(object);
    }

    public boolean containsAllIterable(Iterable<?> source)
    {
        return this.delegate.asReversed().containsAllIterable(source);
    }

    public boolean containsAll(Collection<?> source)
    {
        return this.delegate.asReversed().containsAll(source);
    }

    public boolean containsAllArguments(Object... elements)
    {
        return this.delegate.asReversed().containsAllArguments(elements);
    }

    public <V> ArrayStack<V> collect(Function<? super T, ? extends V> function)
    {
        return ArrayStack.newStackFromTopToBottom(this.delegate.asReversed().collect(function));
    }

    public MutableBooleanStack collectBoolean(BooleanFunction<? super T> booleanFunction)
    {
        return BooleanArrayStack.newStackFromTopToBottom(this.delegate.asReversed().collectBoolean(booleanFunction));
    }

    public <R extends MutableBooleanCollection> R collectBoolean(BooleanFunction<? super T> booleanFunction, R target)
    {
        return this.delegate.asReversed().collectBoolean(booleanFunction, target);
    }

    public MutableByteStack collectByte(ByteFunction<? super T> byteFunction)
    {
        return ByteArrayStack.newStackFromTopToBottom(this.delegate.asReversed().collectByte(byteFunction));
    }

    public <R extends MutableByteCollection> R collectByte(ByteFunction<? super T> byteFunction, R target)
    {
        return this.delegate.asReversed().collectByte(byteFunction, target);
    }

    public MutableCharStack collectChar(CharFunction<? super T> charFunction)
    {
        return CharArrayStack.newStackFromTopToBottom(this.delegate.asReversed().collectChar(charFunction));
    }

    public <R extends MutableCharCollection> R collectChar(CharFunction<? super T> charFunction, R target)
    {
        return this.delegate.asReversed().collectChar(charFunction, target);
    }

    public MutableDoubleStack collectDouble(DoubleFunction<? super T> doubleFunction)
    {
        return DoubleArrayStack.newStackFromTopToBottom(this.delegate.asReversed().collectDouble(doubleFunction));
    }

    public <R extends MutableDoubleCollection> R collectDouble(DoubleFunction<? super T> doubleFunction, R target)
    {
        return this.delegate.asReversed().collectDouble(doubleFunction, target);
    }

    public MutableFloatStack collectFloat(FloatFunction<? super T> floatFunction)
    {
        return FloatArrayStack.newStackFromTopToBottom(this.delegate.asReversed().collectFloat(floatFunction));
    }

    public <R extends MutableFloatCollection> R collectFloat(FloatFunction<? super T> floatFunction, R target)
    {
        return this.delegate.asReversed().collectFloat(floatFunction, target);
    }

    public MutableIntStack collectInt(IntFunction<? super T> intFunction)
    {
        return IntArrayStack.newStackFromTopToBottom(this.delegate.asReversed().collectInt(intFunction));
    }

    public <R extends MutableIntCollection> R collectInt(IntFunction<? super T> intFunction, R target)
    {
        return this.delegate.asReversed().collectInt(intFunction, target);
    }

    public MutableLongStack collectLong(LongFunction<? super T> longFunction)
    {
        return LongArrayStack.newStackFromTopToBottom(this.delegate.asReversed().collectLong(longFunction));
    }

    public <R extends MutableLongCollection> R collectLong(LongFunction<? super T> longFunction, R target)
    {
        return this.delegate.asReversed().collectLong(longFunction, target);
    }

    public MutableShortStack collectShort(ShortFunction<? super T> shortFunction)
    {
        return ShortArrayStack.newStackFromTopToBottom(this.delegate.asReversed().collectShort(shortFunction));
    }

    public <R extends MutableShortCollection> R collectShort(ShortFunction<? super T> shortFunction, R target)
    {
        return this.delegate.asReversed().collectShort(shortFunction, target);
    }

    public <V, R extends Collection<V>> R collect(Function<? super T, ? extends V> function, R target)
    {
        return this.delegate.asReversed().collect(function, target);
    }

    public <P, V> ArrayStack<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return ArrayStack.newStackFromTopToBottom(this.delegate.asReversed().collectWith(function, parameter).toList());
    }

    public <V> ArrayStack<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return ArrayStack.newStackFromTopToBottom(this.delegate.asReversed().collectIf(predicate, function).toList());
    }

    public <V, R extends Collection<V>> R collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function, R target)
    {
        return this.delegate.asReversed().collectIf(predicate, function, target);
    }

    public <P, V, R extends Collection<V>> R collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter, R targetCollection)
    {
        return this.delegate.asReversed().collectWith(function, parameter, targetCollection);
    }

    public <V> ArrayStack<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return ArrayStack.newStackFromTopToBottom(this.delegate.asReversed().flatCollect(function).toList());
    }

    public <V, R extends Collection<V>> R flatCollect(Function<? super T, ? extends Iterable<V>> function, R target)
    {
        return this.delegate.asReversed().flatCollect(function, target);
    }

    public ArrayStack<T> select(Predicate<? super T> predicate)
    {
        return ArrayStack.newStackFromTopToBottom(this.delegate.asReversed().select(predicate).toList());
    }

    public <P> ArrayStack<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.select(Predicates.bind(predicate, parameter));
    }

    public <R extends Collection<T>> R select(Predicate<? super T> predicate, R target)
    {
        return this.delegate.asReversed().select(predicate, target);
    }

    public <S> ArrayStack<S> selectInstancesOf(Class<S> clazz)
    {
        return ArrayStack.newStackFromTopToBottom(this.delegate.asReversed().selectInstancesOf(clazz).toList());
    }

    public <P, R extends Collection<T>> R selectWith(Predicate2<? super T, ? super P> predicate, P parameter, R targetCollection)
    {
        return this.delegate.asReversed().selectWith(predicate, parameter, targetCollection);
    }

    public ArrayStack<T> reject(Predicate<? super T> predicate)
    {
        return ArrayStack.newStackFromTopToBottom(this.delegate.asReversed().reject(predicate).toList());
    }

    public <R extends Collection<T>> R reject(Predicate<? super T> predicate, R target)
    {
        return this.delegate.asReversed().reject(predicate, target);
    }

    public <P> ArrayStack<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.reject(Predicates.bind(predicate, parameter));
    }

    public <P, R extends Collection<T>> R rejectWith(Predicate2<? super T, ? super P> predicate, P parameter, R targetCollection)
    {
        return this.delegate.asReversed().rejectWith(predicate, parameter, targetCollection);
    }

    public T detect(Predicate<? super T> predicate)
    {
        return this.delegate.asReversed().detect(predicate);
    }

    public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.delegate.asReversed().detectWith(predicate, parameter);
    }

    public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
    {
        return this.delegate.asReversed().detectIfNone(predicate, function);
    }

    public <P> T detectWithIfNone(Predicate2<? super T, ? super P> predicate, P parameter, Function0<? extends T> function)
    {
        return this.delegate.asReversed().detectWithIfNone(predicate, parameter, function);
    }

    public PartitionMutableStack<T> partition(Predicate<? super T> predicate)
    {
        PartitionArrayStack<T> partitionMutableStack = new PartitionArrayStack<>();
        this.delegate.asReversed().forEach(new PartitionArrayStack.PartitionProcedure<>(predicate, partitionMutableStack));
        return partitionMutableStack;
    }

    public <P> PartitionMutableStack<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        PartitionArrayStack<T> partitionMutableStack = new PartitionArrayStack<>();
        this.delegate.asReversed().forEach(new PartitionArrayStack.PartitionPredicate2Procedure<>(predicate, parameter, partitionMutableStack));
        return partitionMutableStack;
    }

    public <S> ArrayStack<Pair<T, S>> zip(Iterable<S> that)
    {
        return ArrayStack.newStackFromTopToBottom(this.delegate.asReversed().zip(that).toList());
    }

    public <S, R extends Collection<Pair<T, S>>> R zip(Iterable<S> that, R target)
    {
        return this.delegate.asReversed().zip(that, target);
    }

    public ArrayStack<Pair<T, Integer>> zipWithIndex()
    {
        int maxIndex = this.delegate.size() - 1;
        Interval indicies = Interval.fromTo(0, maxIndex);

        return ArrayStack.newStackFromTopToBottom(this.delegate.asReversed().zip(indicies).toList());
    }

    public <R extends Collection<Pair<T, Integer>>> R zipWithIndex(R target)
    {
        return this.delegate.asReversed().zipWithIndex(target);
    }

    public int count(Predicate<? super T> predicate)
    {
        return this.delegate.asReversed().count(predicate);
    }

    public <P> int countWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.delegate.asReversed().countWith(predicate, parameter);
    }

    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return this.delegate.asReversed().anySatisfy(predicate);
    }

    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.delegate.asReversed().anySatisfyWith(predicate, parameter);
    }

    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return this.delegate.asReversed().allSatisfy(predicate);
    }

    public <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.delegate.asReversed().allSatisfyWith(predicate, parameter);
    }

    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return this.delegate.asReversed().noneSatisfy(predicate);
    }

    public <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.delegate.asReversed().noneSatisfyWith(predicate, parameter);
    }

    public <IV> IV injectInto(IV injectedValue, Function2<? super IV, ? super T, ? extends IV> function)
    {
        return this.delegate.asReversed().injectInto(injectedValue, function);
    }

    public int injectInto(int injectedValue, IntObjectToIntFunction<? super T> intObjectToIntFunction)
    {
        return this.delegate.asReversed().injectInto(injectedValue, intObjectToIntFunction);
    }

    public long injectInto(long injectedValue, LongObjectToLongFunction<? super T> longObjectToLongFunction)
    {
        return this.delegate.asReversed().injectInto(injectedValue, longObjectToLongFunction);
    }

    public double injectInto(double injectedValue, DoubleObjectToDoubleFunction<? super T> doubleObjectToDoubleFunction)
    {
        return this.delegate.asReversed().injectInto(injectedValue, doubleObjectToDoubleFunction);
    }

    public float injectInto(float injectedValue, FloatObjectToFloatFunction<? super T> floatObjectToFloatFunction)
    {
        return this.delegate.asReversed().injectInto(injectedValue, floatObjectToFloatFunction);
    }

    public long sumOfInt(IntFunction<? super T> intFunction)
    {
        return this.delegate.asReversed().sumOfInt(intFunction);
    }

    public double sumOfFloat(FloatFunction<? super T> floatFunction)
    {
        return this.delegate.asReversed().sumOfFloat(floatFunction);
    }

    public long sumOfLong(LongFunction<? super T> longFunction)
    {
        return this.delegate.asReversed().sumOfLong(longFunction);
    }

    public double sumOfDouble(DoubleFunction<? super T> doubleFunction)
    {
        return this.delegate.asReversed().sumOfDouble(doubleFunction);
    }

    public <V> MutableObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function)
    {
        return (MutableObjectLongMap<V>) this.delegate.asReversed().sumByInt(groupBy, function);
    }

    public <V> MutableObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function)
    {
        return (MutableObjectDoubleMap<V>) this.delegate.asReversed().sumByFloat(groupBy, function);
    }

    public <V> MutableObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function)
    {
        return (MutableObjectLongMap<V>) this.delegate.asReversed().sumByLong(groupBy, function);
    }

    public <V> MutableObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function)
    {
        return (MutableObjectDoubleMap<V>) this.delegate.asReversed().sumByDouble(groupBy, function);
    }

    public T max()
    {
        return this.delegate.asReversed().max();
    }

    public T max(Comparator<? super T> comparator)
    {
        return this.delegate.asReversed().max(comparator);
    }

    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        return this.delegate.asReversed().maxBy(function);
    }

    public T min()
    {
        return this.delegate.asReversed().min();
    }

    public T min(Comparator<? super T> comparator)
    {
        return this.delegate.asReversed().min(comparator);
    }

    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        return this.delegate.asReversed().minBy(function);
    }

    public String makeString()
    {
        return this.delegate.asReversed().makeString();
    }

    public String makeString(String separator)
    {
        return this.delegate.asReversed().makeString(separator);
    }

    public String makeString(String start, String separator, String end)
    {
        return this.delegate.asReversed().makeString(start, separator, end);
    }

    public void appendString(Appendable appendable)
    {
        this.delegate.asReversed().appendString(appendable);
    }

    public void appendString(Appendable appendable, String separator)
    {
        this.delegate.asReversed().appendString(appendable, separator);
    }

    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        this.delegate.asReversed().appendString(appendable, start, separator, end);
    }

    public <V> MutableListMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.groupBy(function, FastListMultimap.newMultimap());
    }

    public <V, R extends MutableMultimap<V, T>> R groupBy(Function<? super T, ? extends V> function, R target)
    {
        return this.delegate.asReversed().groupBy(function, target);
    }

    public <V> MutableListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.groupByEach(function, FastListMultimap.newMultimap());
    }

    public <V, R extends MutableMultimap<V, T>> R groupByEach(Function<? super T, ? extends Iterable<V>> function, R target)
    {
        return this.delegate.asReversed().groupByEach(function, target);
    }

    public <V> MutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        return this.groupByUniqueKey(function, UnifiedMap.newMap());
    }

    public <V, R extends MutableMap<V, T>> R groupByUniqueKey(Function<? super T, ? extends V> function, R target)
    {
        return this.delegate.asReversed().groupByUniqueKey(function, target);
    }

    public RichIterable<RichIterable<T>> chunk(int size)
    {
        return this.delegate.asReversed().chunk(size);
    }

    public ArrayStack<T> tap(Procedure<? super T> procedure)
    {
        this.forEach(procedure);
        return this;
    }

    public void forEach(Procedure<? super T> procedure)
    {
        this.each(procedure);
    }

    public void each(Procedure<? super T> procedure)
    {
        this.delegate.reverseForEach(procedure);
    }

    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        this.delegate.asReversed().forEachWith(procedure, parameter);
    }

    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        this.delegate.asReversed().forEachWithIndex(objectIntProcedure);
    }

    public <R extends Collection<T>> R into(R target)
    {
        return this.delegate.asReversed().into(target);
    }

    public MutableList<T> toList()
    {
        return this.delegate.asReversed().toList();
    }

    public MutableList<T> toSortedList()
    {
        return this.delegate.asReversed().toSortedList();
    }

    public MutableList<T> toSortedList(Comparator<? super T> comparator)
    {
        return this.delegate.asReversed().toSortedList(comparator);
    }

    public <V extends Comparable<? super V>> MutableList<T> toSortedListBy(Function<? super T, ? extends V> function)
    {
        return this.delegate.asReversed().toSortedListBy(function);
    }

    public MutableSet<T> toSet()
    {
        return this.delegate.asReversed().toSet();
    }

    public MutableSortedSet<T> toSortedSet()
    {
        return this.delegate.asReversed().toSortedSet();
    }

    public MutableSortedSet<T> toSortedSet(Comparator<? super T> comparator)
    {
        return this.delegate.asReversed().toSortedSet(comparator);
    }

    public MutableStack<T> toStack()
    {
        return ArrayStack.newStackFromTopToBottom(this);
    }

    public ImmutableStack<T> toImmutable()
    {
        return Stacks.immutable.withAll(this.delegate);
    }

    public <V extends Comparable<? super V>> MutableSortedSet<T> toSortedSetBy(Function<? super T, ? extends V> function)
    {
        return this.delegate.asReversed().toSortedSetBy(function);
    }

    public MutableBag<T> toBag()
    {
        return this.delegate.asReversed().toBag();
    }

    public MutableSortedBag<T> toSortedBag()
    {
        return this.delegate.asReversed().toSortedBag();
    }

    public MutableSortedBag<T> toSortedBag(Comparator<? super T> comparator)
    {
        return this.delegate.asReversed().toSortedBag(comparator);
    }

    public <V extends Comparable<? super V>> MutableSortedBag<T> toSortedBagBy(Function<? super T, ? extends V> function)
    {
        return this.delegate.asReversed().toSortedBagBy(function);
    }

    public <NK, NV> MutableMap<NK, NV> toMap(Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction)
    {
        return this.delegate.asReversed().toMap(keyFunction, valueFunction);
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction)
    {
        return this.delegate.asReversed().toSortedMap(keyFunction, valueFunction);
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Comparator<? super NK> comparator, Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction)
    {
        return this.delegate.asReversed().toSortedMap(comparator, keyFunction, valueFunction);
    }

    public LazyIterable<T> asLazy()
    {
        return LazyIterate.adapt(this);
    }

    public MutableStack<T> asUnmodifiable()
    {
        return UnmodifiableStack.of(this);
    }

    public MutableStack<T> asSynchronized()
    {
        return SynchronizedStack.of(this);
    }

    public Object[] toArray()
    {
        return this.delegate.asReversed().toArray();
    }

    public <T> T[] toArray(T[] a)
    {
        return this.delegate.asReversed().toArray(a);
    }

    public Iterator<T> iterator()
    {
        return this.delegate.asReversed().iterator();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof StackIterable<?>))
        {
            return false;
        }

        StackIterable<?> that = (StackIterable<?>) o;

        if (that instanceof ArrayStack<?>)
        {
            return this.delegate.equals(((ArrayStack<?>) that).delegate);
        }
        Iterator<T> thisIterator = this.iterator();
        Iterator<?> thatIterator = that.iterator();
        while (thisIterator.hasNext() && thatIterator.hasNext())
        {
            if (!Comparators.nullSafeEquals(thisIterator.next(), thatIterator.next()))
            {
                return false;
            }
        }
        return !thisIterator.hasNext() && !thatIterator.hasNext();
    }

    @Override
    public String toString()
    {
        return this.delegate.asReversed().makeString("[", ", ", "]");
    }

    @Override
    public int hashCode()
    {
        int hashCode = 1;
        for (int i = this.delegate.size() - 1; i >= 0; i--)
        {
            T each = this.delegate.get(i);
            hashCode = 31 * hashCode + (each == null ? 0 : each.hashCode());
        }
        return hashCode;
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeInt(this.size());
        for (T each : this.delegate.asReversed())
        {
            out.writeObject(each);
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        int size = in.readInt();
        T[] array = (T[]) new Object[size];
        for (int i = size - 1; i >= 0; i--)
        {
            array[i] = (T) in.readObject();
        }
        this.delegate = FastList.newListWith(array);
    }

    private void checkSizeLessThanCount(int count)
    {
        if (this.delegate.size() < count)
        {
            throw new IllegalArgumentException("Count must be less than size: Count = " + count + " Size = " + this.delegate.size());
        }
    }

    private void checkSizeLessThanOrEqualToIndex(int index)
    {
        if (this.delegate.size() <= index)
        {
            throw new IllegalArgumentException("Count must be less than size: Count = " + index + " Size = " + this.delegate.size());
        }
    }

    private void checkNegativeCount(int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be positive but was " + count);
        }
    }

    public <K, V> MutableMap<K, V> aggregateInPlaceBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Procedure2<? super V, ? super T> mutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new MutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map;
    }

    public <K, V> MutableMap<K, V> aggregateBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new NonMutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map;
    }

    public MutableStack<T> takeWhile(Predicate<? super T> predicate)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".takeWhile() not implemented yet");
    }

    public MutableStack<T> dropWhile(Predicate<? super T> predicate)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".dropWhile() not implemented yet");
    }

    public PartitionMutableStack<T> partitionWhile(Predicate<? super T> predicate)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".partitionWhile() not implemented yet");
    }

    public MutableStack<T> distinct()
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".distinct() not implemented yet");
    }

    public int indexOf(Object object)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".indexOf() not implemented yet");
    }

    public <S> boolean corresponds(OrderedIterable<S> other, Predicate2<? super T, ? super S> predicate)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".corresponds() not implemented yet");
    }

    public boolean hasSameElements(OrderedIterable<T> other)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".hasSameElements() not implemented yet");
    }

    public void forEach(int startIndex, int endIndex, Procedure<? super T> procedure)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".forEach() not implemented yet");
    }

    public void forEachWithIndex(int fromIndex, int toIndex, ObjectIntProcedure<? super T> objectIntProcedure)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".forEachWithIndex() not implemented yet");
    }

    public <V> MutableStack<V> collectWithIndex(ObjectIntToObjectFunction<? super T, ? extends V> function)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".collectWithIndex() not implemented yet");
    }

    public int detectIndex(Predicate<? super T> predicate)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".detectIndex() not implemented yet");
    }
}
