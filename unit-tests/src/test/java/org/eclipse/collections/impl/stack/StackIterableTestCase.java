/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack;

import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.sorted.SortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.ObjectDoubleMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.partition.stack.PartitionStack;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.stack.StackIterable;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.AbstractRichIterableTestCase;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.block.function.NegativeIntervalFunction;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.ByteHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.CharHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.DoubleHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.FloatHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.LongHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.ShortHashSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.BooleanArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.ByteArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.CharArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.DoubleArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.FloatArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.IntArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.LongArrayStack;
import org.eclipse.collections.impl.stack.mutable.primitive.ShortArrayStack;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

public abstract class StackIterableTestCase
        extends AbstractRichIterableTestCase
{
    @Override
    protected <T> StackIterable<T> newWith(T... littleElements)
    {
        return this.newStackWith(littleElements);
    }

    protected abstract <T> StackIterable<T> newStackWith(T... elements);

    protected abstract <T> StackIterable<T> newStackFromTopToBottom(T... elements);

    protected abstract <T> StackIterable<T> newStackFromTopToBottom(Iterable<T> elements);

    protected abstract <T> StackIterable<T> newStack(Iterable<T> elements);

    @Test
    public void testNewStackFromTopToBottom()
    {
        Assert.assertEquals(
                this.newStackWith(3, 2, 1),
                this.newStackFromTopToBottom(1, 2, 3));
    }

    @Test(expected = EmptyStackException.class)
    public void peek_empty_throws()
    {
        this.newStackWith().peek();
    }

    @Test(expected = EmptyStackException.class)
    public void peek_int_empty_throws()
    {
        this.newStackWith().peek(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void peek_int_count_throws()
    {
        this.newStackWith(1, 2, 3).peek(4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void peek_int_neg_throws()
    {
        this.newStackWith(1, 2, 3).peek(-1);
    }

    @Test
    public void peek_illegal_arguments()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        Verify.assertThrows(IllegalArgumentException.class, () -> stack.peek(-1));

        Verify.assertThrows(IllegalArgumentException.class, () -> stack.peek(4));

        Assert.assertEquals(Lists.mutable.with(1, 2, 3), stack.peek(3));
    }

    @Test
    public void peek()
    {
        Assert.assertEquals("3", this.newStackWith("1", "2", "3").peek());
        Assert.assertEquals(Lists.mutable.with(), this.newStackWith("1", "2", "3").peek(0));
        Assert.assertEquals(Lists.mutable.with("3", "2"), this.newStackWith("1", "2", "3").peek(2));
    }

    @Test
    public void peekAt()
    {
        Assert.assertEquals("3", this.newStackWith("1", "2", "3").peekAt(0));
        Assert.assertEquals("2", this.newStackWith("1", "2", "3").peekAt(1));
        Assert.assertEquals("1", this.newStackWith("1", "2", "3").peekAt(2));
    }

    @Test
    public void peekAt_illegal_arguments()
    {
        StackIterable<String> stack = this.newStackWith("1", "2", "3");
        Verify.assertThrows(IllegalArgumentException.class, () -> stack.peekAt(stack.size()));
    }

    @Test
    public void size()
    {
        StackIterable<Integer> stack1 = this.newStackWith();
        Assert.assertEquals(0, stack1.size());

        StackIterable<Integer> stack2 = this.newStackWith(1, 2);
        Assert.assertEquals(2, stack2.size());
    }

    @Override
    @Test
    public void getFirst()
    {
        StackIterable<Integer> stack = this.newStackWith(1, 2, 3);
        Assert.assertEquals(Integer.valueOf(3), stack.getFirst());
        Assert.assertEquals(stack.peek(), stack.getFirst());
        Verify.assertThrows(EmptyStackException.class, () -> this.newStackWith().getFirst());
        StackIterable<Integer> stack2 = this.newStackFromTopToBottom(1, 2, 3);
        Assert.assertEquals(Integer.valueOf(1), stack2.getFirst());
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void getLast()
    {
        StackIterable<Integer> stack = this.newStackWith(1, 2, 3);
        Assert.assertEquals(Integer.valueOf(1), stack.getLast());
    }

    @Test
    public void containsAll()
    {
        StackIterable<Integer> stack = this.newStackWith(1, 2, 3, 4);
        Assert.assertTrue(stack.containsAll(Interval.oneTo(2)));
        Assert.assertFalse(stack.containsAll(Lists.mutable.with(1, 2, 5)));
    }

    @Test
    public void containsAllArguments()
    {
        StackIterable<Integer> stack = this.newStackWith(1, 2, 3, 4);
        Assert.assertTrue(stack.containsAllArguments(2, 1, 3));
        Assert.assertFalse(stack.containsAllArguments(2, 1, 3, 5));
    }

    @Override
    @Test
    public void collect()
    {
        StackIterable<Boolean> stack = this.newStackFromTopToBottom(Boolean.TRUE, Boolean.FALSE, null);
        CountingFunction<Object, String> function = CountingFunction.of(String::valueOf);
        Assert.assertEquals(
                this.newStackFromTopToBottom("true", "false", "null"),
                stack.collect(function));
        Assert.assertEquals(3, function.count);

        Assert.assertEquals(Lists.mutable.with("true", "false", "null"), stack.collect(String::valueOf, FastList.newList()));
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithIndex()
    {
        StackIterable<String> stack = this.newStackFromTopToBottom("4", "3", "2", "1");

        StackIterable<ObjectIntPair<String>> expected = this.newStackFromTopToBottom(
                PrimitiveTuples.pair("4", 0),
                PrimitiveTuples.pair("3", 1),
                PrimitiveTuples.pair("2", 2),
                PrimitiveTuples.pair("1", 3));
        Assert.assertEquals(expected, stack.collectWithIndex(PrimitiveTuples::pair));
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithIndexWithTarget()
    {
        StackIterable<String> stack = this.newStackFromTopToBottom("4", "3", "2", "1");

        MutableList<ObjectIntPair<String>> expected = Lists.mutable.with(
                PrimitiveTuples.pair("4", 0),
                PrimitiveTuples.pair("3", 1),
                PrimitiveTuples.pair("2", 2),
                PrimitiveTuples.pair("1", 3));
        Assert.assertEquals(expected, stack.collectWithIndex(PrimitiveTuples::pair, Lists.mutable.empty()));
    }

    @Override
    @Test
    public void collectBoolean()
    {
        StackIterable<String> stack = this.newStackFromTopToBottom("true", "nah", "TrUe", "false");
        Assert.assertEquals(
                BooleanArrayStack.newStackFromTopToBottom(true, false, true, false),
                stack.collectBoolean(Boolean::parseBoolean));
    }

    @Override
    @Test
    public void collectBooleanWithTarget()
    {
        BooleanHashSet target = new BooleanHashSet();
        StackIterable<String> stack = this.newStackFromTopToBottom("true", "nah", "TrUe", "false");
        BooleanHashSet result = stack.collectBoolean(Boolean::parseBoolean, target);
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false, true, false), result);
        Assert.assertSame("Target sent as parameter not returned", target, result);
    }

    @Override
    @Test
    public void collectByte()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        Assert.assertEquals(ByteArrayStack.newStackFromTopToBottom((byte) 1, (byte) 2, (byte) 3), stack.collectByte(PrimitiveFunctions.unboxIntegerToByte()));
    }

    @Override
    @Test
    public void collectByteWithTarget()
    {
        ByteHashSet target = new ByteHashSet();
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        ByteHashSet result = stack.collectByte(PrimitiveFunctions.unboxIntegerToByte(), target);
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 1, (byte) 2, (byte) 3), result);
        Assert.assertSame("Target sent as parameter not returned", target, result);
    }

    @Override
    @Test
    public void collectChar()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        Assert.assertEquals(CharArrayStack.newStackFromTopToBottom((char) 1, (char) 2, (char) 3), stack.collectChar(PrimitiveFunctions.unboxIntegerToChar()));
    }

    @Override
    @Test
    public void collectCharWithTarget()
    {
        CharHashSet target = new CharHashSet();
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        CharHashSet result = stack.collectChar(PrimitiveFunctions.unboxIntegerToChar(), target);
        Assert.assertEquals(CharHashSet.newSetWith((char) 1, (char) 2, (char) 3), result);
        Assert.assertSame("Target sent as parameter not returned", target, result);
    }

    @Override
    @Test
    public void collectDouble()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        Assert.assertEquals(DoubleArrayStack.newStackFromTopToBottom(1, 2, 3), stack.collectDouble(PrimitiveFunctions.unboxIntegerToDouble()));
    }

    @Override
    @Test
    public void collectDoubleWithTarget()
    {
        DoubleHashSet target = new DoubleHashSet();
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        DoubleHashSet result = stack.collectDouble(PrimitiveFunctions.unboxIntegerToDouble(), target);
        Assert.assertEquals(DoubleHashSet.newSetWith(1, 2, 3), result);
        Assert.assertSame("Target sent as parameter not returned", target, result);
    }

    @Override
    @Test
    public void collectFloat()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        Assert.assertEquals(FloatArrayStack.newStackFromTopToBottom(1, 2, 3), stack.collectFloat(PrimitiveFunctions.unboxIntegerToFloat()));
    }

    @Override
    @Test
    public void collectFloatWithTarget()
    {
        FloatHashSet target = new FloatHashSet();
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        FloatHashSet result = stack.collectFloat(PrimitiveFunctions.unboxIntegerToFloat(), target);
        Assert.assertEquals(FloatHashSet.newSetWith(1, 2, 3), result);
        Assert.assertSame("Target sent as parameter not returned", target, result);
    }

    @Override
    @Test
    public void collectInt()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        Assert.assertEquals(IntArrayStack.newStackFromTopToBottom(1, 2, 3), stack.collectInt(PrimitiveFunctions.unboxIntegerToInt()));
    }

    @Override
    @Test
    public void collectIntWithTarget()
    {
        IntHashSet target = new IntHashSet();
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        IntHashSet result = stack.collectInt(PrimitiveFunctions.unboxIntegerToInt(), target);
        Assert.assertEquals(IntHashSet.newSetWith(1, 2, 3), result);
        Assert.assertSame("Target sent as parameter not returned", target, result);
    }

    @Override
    @Test
    public void collectLong()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        Assert.assertEquals(LongArrayStack.newStackFromTopToBottom(1, 2, 3), stack.collectLong(PrimitiveFunctions.unboxIntegerToLong()));
    }

    @Override
    @Test
    public void collectLongWithTarget()
    {
        LongHashSet target = new LongHashSet();
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        LongHashSet result = stack.collectLong(PrimitiveFunctions.unboxIntegerToLong(), target);
        Assert.assertEquals(LongHashSet.newSetWith(1, 2, 3), result);
        Assert.assertSame("Target sent as parameter not returned", target, result);
    }

    @Override
    @Test
    public void collectShort()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        Assert.assertEquals(ShortArrayStack.newStackFromTopToBottom((short) 1, (short) 2, (short) 3), stack.collectShort(PrimitiveFunctions.unboxIntegerToShort()));
    }

    @Override
    @Test
    public void collectShortWithTarget()
    {
        ShortHashSet target = new ShortHashSet();
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        ShortHashSet result = stack.collectShort(PrimitiveFunctions.unboxIntegerToShort(), target);
        Assert.assertEquals(ShortHashSet.newSetWith((short) 1, (short) 2, (short) 3), result);
        Assert.assertSame("Target sent as parameter not returned", target, result);
    }

    @Override
    @Test
    public void collectIf()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3, 4, 5);

        CountingPredicate<Integer> predicate1 = CountingPredicate.of(Predicates.lessThan(3));
        CountingFunction<Object, String> function1 = CountingFunction.of(String::valueOf);
        Assert.assertEquals(
                this.newStackFromTopToBottom("1", "2"),
                stack.collectIf(predicate1, function1));
        Assert.assertEquals(5, predicate1.count);
        Assert.assertEquals(2, function1.count);

        CountingPredicate<Integer> predicate2 = CountingPredicate.of(Predicates.lessThan(3));
        CountingFunction<Object, String> function2 = CountingFunction.of(String::valueOf);
        Assert.assertEquals(
                Lists.mutable.with("1", "2"),
                stack.collectIf(predicate2, function2, FastList.newList()));
        Assert.assertEquals(5, predicate2.count);
        Assert.assertEquals(2, function2.count);
    }

    @Override
    @Test
    public void collectWith()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(3, 2, 1);
        Assert.assertEquals(
                ArrayStack.newStackFromTopToBottom(4, 3, 2),
                stack.collectWith(AddFunction.INTEGER, 1));
    }

    @Test
    public void collectWithTarget()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(3, 2, 1);
        Assert.assertEquals(
                Lists.mutable.with(4, 3, 2),
                stack.collectWith(AddFunction.INTEGER, 1, FastList.newList()));
    }

    @Override
    @Test
    public void flatCollect()
    {
        StackIterable<String> stack = this.newStackFromTopToBottom("1", "One", "2", "Two");

        CountingFunction<String, Iterable<Character>> function = CountingFunction.of(object -> {
            MutableList<Character> result = Lists.mutable.of();
            char[] chars = object.toCharArray();
            for (char aChar : chars)
            {
                result.add(Character.valueOf(aChar));
            }
            return result;
        });

        Assert.assertEquals(
                this.newStackFromTopToBottom('1', 'O', 'n', 'e', '2', 'T', 'w', 'o'),
                stack.flatCollect(function));
        Assert.assertEquals(4, function.count);

        Assert.assertEquals(
                Lists.mutable.with('1', 'O', 'n', 'e', '2', 'T', 'w', 'o'),
                stack.flatCollect(function, FastList.newList()));
    }

    @Override
    @Test
    public void select()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        CountingPredicate<Object> predicate = new CountingPredicate<>(Integer.valueOf(1)::equals);
        StackIterable<Integer> actual = stack.select(predicate);
        Assert.assertEquals(this.newStackFromTopToBottom(1), actual);
        Assert.assertEquals(3, predicate.count);
        Assert.assertEquals(
                this.newStackFromTopToBottom(2, 3),
                stack.select(Predicates.greaterThan(1)));
        Assert.assertEquals(
                Lists.mutable.with(2, 3),
                stack.select(Predicates.greaterThan(1), FastList.newList()));
    }

    @Override
    @Test
    public void selectInstancesOf()
    {
        StackIterable<Number> numbers = this.newStackFromTopToBottom(1, 2.0, 3, 4.0, 5);
        Assert.assertEquals(this.newStackFromTopToBottom(1, 3, 5), numbers.selectInstancesOf(Integer.class));
        Assert.assertEquals(this.<Number>newStackFromTopToBottom(1, 2.0, 3, 4.0, 5), numbers.selectInstancesOf(Number.class));
    }

    @Override
    @Test
    public void selectWith()
    {
        Assert.assertEquals(
                ArrayStack.newStackFromTopToBottom(2, 1),
                this.newStackFromTopToBottom(5, 4, 3, 2, 1).selectWith(Predicates2.lessThan(), 3));
    }

    @Test
    public void selectWithTarget()
    {
        Assert.assertEquals(
                UnifiedSet.newSetWith(2, 1),
                this.newStackFromTopToBottom(5, 4, 3, 2, 1).selectWith(Predicates2.lessThan(), 3, UnifiedSet.newSet()));
    }

    @Override
    @Test
    public void reject()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(3, 2, 1);
        CountingPredicate<Integer> predicate = new CountingPredicate<>(Predicates.greaterThan(2));
        Assert.assertEquals(
                this.newStackFromTopToBottom(2, 1),
                stack.reject(predicate));
        Assert.assertEquals(3, predicate.count);
        Assert.assertEquals(
                Lists.mutable.with(2, 1),
                stack.reject(Predicates.greaterThan(2), FastList.newList()));
    }

    @Override
    @Test
    public void rejectWith()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(3, 2, 1);
        Assert.assertEquals(
                this.newStackFromTopToBottom(2, 1),
                stack.rejectWith(Predicates2.greaterThan(), 2));
    }

    @Test
    public void rejectWithTarget()
    {
        Assert.assertEquals(
                UnifiedSet.newSetWith(5, 4, 3),
                this.newStackFromTopToBottom(5, 4, 3, 2, 1).rejectWith(Predicates2.lessThan(), 3, UnifiedSet.newSet()));
    }

    @Override
    @Test
    public void detect()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        CountingPredicate<Integer> predicate = new CountingPredicate<>(Predicates.lessThan(3));
        Assert.assertEquals(Integer.valueOf(1), stack.detect(predicate));
        Assert.assertEquals(1, predicate.count);
        Assert.assertNull(stack.detect(Integer.valueOf(4)::equals));
    }

    @Override
    @Test
    public void detectWith()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        CountingPredicate2<Integer, Integer> predicate = new CountingPredicate2<>(Predicates2.<Integer>lessThan());
        Assert.assertEquals(Integer.valueOf(1), stack.detectWith(predicate, 3));
        Assert.assertEquals(1, predicate.count);
        Assert.assertNull(stack.detectWith(Object::equals, Integer.valueOf(4)));
    }

    @Override
    @Test
    public void detectIfNone()
    {
        Function0<Integer> defaultResultFunction = new PassThruFunction0<>(-1);
        CountingPredicate<Integer> predicate = new CountingPredicate<>(Predicates.lessThan(3));
        Assert.assertEquals(
                Integer.valueOf(1),
                this.newStackFromTopToBottom(1, 2, 3, 4, 5).detectIfNone(predicate, defaultResultFunction));
        Assert.assertEquals(1, predicate.count);
        Assert.assertEquals(
                Integer.valueOf(-1),
                this.newStackWith(1, 2, 3, 4, 5).detectIfNone(Predicates.lessThan(-1), defaultResultFunction));
    }

    @Test
    public void detectWithIfNone()
    {
        Function0<Integer> defaultResultFunction = new PassThruFunction0<>(-1);
        CountingPredicate2<Integer, Integer> predicate = new CountingPredicate2<>(Predicates2.<Integer>lessThan());
        Assert.assertEquals(
                Integer.valueOf(1),
                this.newStackFromTopToBottom(1, 2, 3, 4, 5).detectWithIfNone(predicate, Integer.valueOf(3), defaultResultFunction));
        Assert.assertEquals(1, predicate.count);
        Assert.assertEquals(
                Integer.valueOf(-1),
                this.newStackWith(1, 2, 3, 4, 5).detectIfNone(Predicates.lessThan(-1), defaultResultFunction));
    }

    @Override
    @Test
    public void partition()
    {
        CountingPredicate<Integer> predicate = new CountingPredicate<>(Predicates.lessThan(3));
        PartitionStack<Integer> partition = this.newStackFromTopToBottom(1, 2, 3, 4, 5).partition(predicate);
        Assert.assertEquals(5, predicate.count);
        Assert.assertEquals(this.newStackFromTopToBottom(1, 2), partition.getSelected());
        Assert.assertEquals(this.newStackFromTopToBottom(3, 4, 5), partition.getRejected());
    }

    @Override
    @Test
    public void partitionWith()
    {
        PartitionStack<Integer> partition = this.newStackFromTopToBottom(1, 2, 3, 4, 5).partitionWith(Predicates2.lessThan(), 3);
        Assert.assertEquals(this.newStackFromTopToBottom(1, 2), partition.getSelected());
        Assert.assertEquals(this.newStackFromTopToBottom(3, 4, 5), partition.getRejected());
    }

    @Override
    @Test
    public void zip()
    {
        StackIterable<String> stack = this.newStackFromTopToBottom("7", "6", "5", "4", "3", "2", "1");
        List<Integer> interval = Interval.oneTo(7);

        StackIterable<Pair<String, Integer>> expected = this.newStackFromTopToBottom(
                Tuples.pair("7", 1),
                Tuples.pair("6", 2),
                Tuples.pair("5", 3),
                Tuples.pair("4", 4),
                Tuples.pair("3", 5),
                Tuples.pair("2", 6),
                Tuples.pair("1", 7));

        Assert.assertEquals(expected, stack.zip(interval));

        Assert.assertEquals(
                expected.toSet(),
                stack.zip(interval, UnifiedSet.newSet()));
    }

    @Override
    @Test
    public void zipWithIndex()
    {
        StackIterable<String> stack = this.newStackFromTopToBottom("4", "3", "2", "1");

        StackIterable<Pair<String, Integer>> expected = this.newStackFromTopToBottom(
                Tuples.pair("4", 0),
                Tuples.pair("3", 1),
                Tuples.pair("2", 2),
                Tuples.pair("1", 3));
        Assert.assertEquals(expected, stack.zipWithIndex());
        Assert.assertEquals(expected.toSet(), stack.zipWithIndex(UnifiedSet.newSet()));
    }

    @Override
    @Test
    public void count()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3, 4, 5);
        CountingPredicate<Integer> predicate = new CountingPredicate<>(Predicates.greaterThan(2));
        Assert.assertEquals(3, stack.count(predicate));
        Assert.assertEquals(5, predicate.count);
        Assert.assertEquals(0, stack.count(Predicates.greaterThan(6)));
    }

    @Override
    @Test
    public void countWith()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3, 4, 5);
        CountingPredicate2<Object, Object> predicate = new CountingPredicate2<>(Object::equals);
        Assert.assertEquals(1, stack.countWith(predicate, 1));
        Assert.assertEquals(5, predicate.count);
        Assert.assertNotEquals(2, stack.countWith(predicate, 4));
    }

    @Override
    @Test
    public void anySatisfy()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        CountingPredicate<Object> predicate = new CountingPredicate<>(Integer.valueOf(1)::equals);
        Assert.assertTrue(stack.anySatisfy(predicate));
        Assert.assertEquals(1, predicate.count);
        Assert.assertFalse(stack.anySatisfy(Integer.valueOf(4)::equals));
    }

    @Override
    @Test
    public void allSatisfy()
    {
        StackIterable<Integer> stack = this.newStackWith(3, 3, 3);
        CountingPredicate<Object> predicate = new CountingPredicate<>(Integer.valueOf(3)::equals);
        Assert.assertTrue(stack.allSatisfy(predicate));
        Assert.assertEquals(3, predicate.count);
        Assert.assertFalse(stack.allSatisfy(Integer.valueOf(2)::equals));
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        StackIterable<Integer> stack = this.newStackWith(3, 3, 3);
        CountingPredicate<Object> predicate = new CountingPredicate<>(Integer.valueOf(4)::equals);
        Assert.assertTrue(stack.noneSatisfy(predicate));
        Assert.assertEquals(3, predicate.count);
        Assert.assertTrue(stack.noneSatisfy(Integer.valueOf(2)::equals));
    }

    @Override
    @Test
    public void anySatisfyWith()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);
        CountingPredicate2<Object, Object> predicate = new CountingPredicate2<>(Object::equals);
        Assert.assertTrue(stack.anySatisfyWith(predicate, 1));
        Assert.assertEquals(1, predicate.count);
        Assert.assertFalse(stack.anySatisfyWith(Object::equals, 4));
    }

    @Override
    @Test
    public void allSatisfyWith()
    {
        StackIterable<Integer> stack = this.newStackWith(3, 3, 3);
        CountingPredicate2<Object, Object> predicate = new CountingPredicate2<>(Object::equals);
        Assert.assertTrue(stack.allSatisfyWith(predicate, 3));
        Assert.assertEquals(3, predicate.count);
        Assert.assertFalse(stack.allSatisfyWith(Object::equals, 2));
    }

    @Override
    @Test
    public void noneSatisfyWith()
    {
        StackIterable<Integer> stack = this.newStackWith(3, 3, 3);
        CountingPredicate2<Object, Object> predicate = new CountingPredicate2<>(Object::equals);
        Assert.assertTrue(stack.noneSatisfyWith(predicate, 4));
        Assert.assertEquals(3, predicate.count);
        Assert.assertTrue(stack.noneSatisfyWith(Object::equals, 2));
    }

    @Override
    @Test
    public void injectInto()
    {
        Assert.assertEquals(
                Integer.valueOf(10),
                this.newStackWith(1, 2, 3, 4).injectInto(Integer.valueOf(0), AddFunction.INTEGER));
        Assert.assertEquals(
                10,
                this.newStackWith(1, 2, 3, 4).injectInto(0, AddFunction.INTEGER_TO_INT));
        Assert.assertEquals(
                7.0,
                this.newStackWith(1.0, 2.0, 3.0).injectInto(1.0d, AddFunction.DOUBLE_TO_DOUBLE), 0.001);
        Assert.assertEquals(
                7,
                this.newStackWith(1, 2, 3).injectInto(1L, AddFunction.INTEGER_TO_LONG));
        Assert.assertEquals(
                7.0,
                this.newStackWith(1, 2, 3).injectInto(1.0f, AddFunction.INTEGER_TO_FLOAT), 0.001);
    }

    @Test
    public void sumOf()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3, 4);
        Assert.assertEquals(10, stack.sumOfInt(integer -> integer));
        Assert.assertEquals(10, stack.sumOfLong(Integer::longValue));
        Assert.assertEquals(10.0d, stack.sumOfDouble(Integer::doubleValue), 0.001);
        Assert.assertEquals(10.0f, stack.sumOfFloat(Integer::floatValue), 0.001);
    }

    @Test
    public void sumOfFloatConsistentRounding()
    {
        MutableList<Integer> list = Interval.oneTo(100_000).toList().shuffleThis();
        StackIterable<Integer> stack = this.newStackWith(list.toArray(new Integer[]{}));

        // The test only ensures the consistency/stability of rounding. This is not meant to test the "correctness" of the float calculation result.
        // Indeed the lower bits of this calculation result are always incorrect due to the information loss of original float values.
        Assert.assertEquals(
                1.082323233761663,
                stack.sumOfFloat(i -> 1.0f / (i.floatValue() * i.floatValue() * i.floatValue() * i.floatValue())),
                1.0e-15);
    }

    @Test
    public void sumOfDoubleConsistentRounding()
    {
        MutableList<Integer> list = Interval.oneTo(100_000).toList().shuffleThis();
        StackIterable<Integer> stack = this.newStackWith(list.toArray(new Integer[]{}));

        Assert.assertEquals(
                1.082323233711138,
                stack.sumOfDouble(i -> 1.0d / (i.doubleValue() * i.doubleValue() * i.doubleValue() * i.doubleValue())),
                1.0e-15);
    }

    @Override
    @Test
    public void sumByFloatConsistentRounding()
    {
        MutableList<Integer> group1 = Interval.oneTo(100_000).toList().shuffleThis();
        MutableList<Integer> group2 = Interval.fromTo(100_001, 200_000).toList().shuffleThis();
        MutableList<Integer> integers = Lists.mutable.withAll(group1);
        integers.addAll(group2);
        StackIterable<Integer> values = this.newStackWith(integers.toArray(new Integer[]{}));
        ObjectDoubleMap<Integer> result = values.sumByFloat(
                integer -> integer > 100_000 ? 2 : 1,
                integer -> {
                    Integer i = integer > 100_000 ? integer - 100_000 : integer;
                    return 1.0f / (i.floatValue() * i.floatValue() * i.floatValue() * i.floatValue());
                });

        // The test only ensures the consistency/stability of rounding. This is not meant to test the "correctness" of the float calculation result.
        // Indeed the lower bits of this calculation result are always incorrect due to the information loss of original float values.
        Assert.assertEquals(
                1.082323233761663,
                result.get(1),
                1.0e-15);

        Assert.assertEquals(
                1.082323233761663,
                result.get(2),
                1.0e-15);
    }

    @Override
    @Test
    public void sumByDoubleConsistentRounding()
    {
        MutableList<Integer> group1 = Interval.oneTo(100_000).toList().shuffleThis();
        MutableList<Integer> group2 = Interval.fromTo(100_001, 200_000).toList().shuffleThis();
        MutableList<Integer> integers = Lists.mutable.withAll(group1);
        integers.addAll(group2);
        StackIterable<Integer> values = this.newStackWith(integers.toArray(new Integer[]{}));
        ObjectDoubleMap<Integer> result = values.sumByDouble(
                integer -> integer > 100_000 ? 2 : 1,
                integer -> {
                    Integer i = integer > 100_000 ? integer - 100_000 : integer;
                    return 1.0d / (i.doubleValue() * i.doubleValue() * i.doubleValue() * i.doubleValue());
                });

        Assert.assertEquals(
                1.082323233711138,
                result.get(1),
                1.0e-15);

        Assert.assertEquals(
                1.082323233711138,
                result.get(2),
                1.0e-15);
    }

    @Override
    @Test
    public void max()
    {
        Assert.assertEquals(
                Integer.valueOf(4),
                this.newStackFromTopToBottom(4, 3, 2, 1).max());
        Assert.assertEquals(
                Integer.valueOf(1),
                this.newStackFromTopToBottom(4, 3, 2, 1).max(Comparators.reverseNaturalOrder()));
    }

    @Override
    @Test
    public void maxBy()
    {
        Assert.assertEquals(
                Integer.valueOf(3),
                this.newStackWith(1, 2, 3).maxBy(String::valueOf));
    }

    @Override
    @Test
    public void min()
    {
        Assert.assertEquals(
                Integer.valueOf(1),
                this.newStackWith(1, 2, 3, 4).min());
        Assert.assertEquals(
                Integer.valueOf(4),
                this.newStackWith(1, 2, 3, 4).min(Comparators.reverseNaturalOrder()));
    }

    @Override
    @Test
    public void minBy()
    {
        CountingFunction<Object, String> function = CountingFunction.of(String::valueOf);
        Assert.assertEquals(
                Integer.valueOf(1),
                this.newStackWith(1, 2, 3).minBy(function));
        Assert.assertEquals(3, function.count);
    }

    @Override
    @Test
    public void testToString()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(4, 3, 2, 1);
        Assert.assertEquals("[4, 3, 2, 1]", stack.toString());
    }

    @Override
    @Test
    public void makeString()
    {
        Assert.assertEquals("3, 2, 1", this.newStackFromTopToBottom(3, 2, 1).makeString());
        Assert.assertEquals("3~2~1", this.newStackFromTopToBottom(3, 2, 1).makeString("~"));
        Assert.assertEquals("[3/2/1]", this.newStackFromTopToBottom(3, 2, 1).makeString("[", "/", "]"));
    }

    @Override
    @Test
    public void appendString()
    {
        StackIterable<String> stack = this.newStackFromTopToBottom("3", "2", "1");
        Appendable appendable = new StringBuilder();

        stack.appendString(appendable);
        Assert.assertEquals("3, 2, 1", appendable.toString());

        Appendable appendable2 = new StringBuilder();
        stack.appendString(appendable2, "/");
        Assert.assertEquals("3/2/1", appendable2.toString());

        Appendable appendable3 = new StringBuilder();
        stack.appendString(appendable3, "[", "/", "]");
        Assert.assertEquals("[3/2/1]", appendable3.toString());
    }

    @Override
    @Test
    public void groupBy()
    {
        StackIterable<String> stack = this.newStackWith("1", "2", "3");
        ListMultimap<Boolean, String> expected = FastListMultimap.newMultimap(
                Tuples.pair(Boolean.TRUE, "3"),
                Tuples.pair(Boolean.FALSE, "2"),
                Tuples.pair(Boolean.TRUE, "1"));
        Assert.assertEquals(expected, stack.groupBy(object -> IntegerPredicates.isOdd().accept(Integer.parseInt(object))));
        Assert.assertEquals(expected, stack.groupBy(object -> IntegerPredicates.isOdd().accept(Integer.parseInt(object)), FastListMultimap.newMultimap()));
    }

    @Override
    @Test
    public void groupByEach()
    {
        StackIterable<Integer> stack = this.newStackFromTopToBottom(1, 2, 3);

        MutableMultimap<Integer, Integer> expected = FastListMultimap.newMultimap();
        stack.forEach(Procedures.cast(value -> expected.putAll(-value, Interval.fromTo(value, stack.size()))));

        Multimap<Integer, Integer> actual =
                stack.groupByEach(new NegativeIntervalFunction());
        Assert.assertEquals(expected, actual);

        Multimap<Integer, Integer> actualWithTarget =
                stack.groupByEach(new NegativeIntervalFunction(), FastListMultimap.newMultimap());
        Assert.assertEquals(expected, actualWithTarget);
    }

    @Override
    @Test
    public void groupByUniqueKey()
    {
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 1, 2, 2, 3, 3), this.newStackWith(1, 2, 3).groupByUniqueKey(id -> id));
    }

    @Test(expected = IllegalStateException.class)
    public void groupByUniqueKey_throws()
    {
        this.newStackWith(1, 2, 3).groupByUniqueKey(Functions.getFixedValue(1));
    }

    @Override
    @Test
    public void groupByUniqueKey_target()
    {
        MutableMap<Integer, Integer> integers = this.newStackWith(1, 2, 3).groupByUniqueKey(id -> id, UnifiedMap.newWithKeysValues(0, 0));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(0, 0, 1, 1, 2, 2, 3, 3), integers);
    }

    @Test(expected = IllegalStateException.class)
    public void groupByUniqueKey_target_throws()
    {
        this.newStackWith(1, 2, 3).groupByUniqueKey(id -> id, UnifiedMap.newWithKeysValues(2, 2));
    }

    @Override
    @Test
    public void chunk()
    {
        Verify.assertIterablesEqual(
                FastList.<RichIterable<String>>newListWith(
                        Lists.mutable.with("7", "6"),
                        Lists.mutable.with("5", "4"),
                        Lists.mutable.with("3", "2"),
                        Lists.mutable.with("1")),
                this.newStackFromTopToBottom("7", "6", "5", "4", "3", "2", "1").chunk(2));
    }

    @Override
    @Test
    public void chunk_single()
    {
        RichIterable<String> collection = this.newWith("1");
        RichIterable<RichIterable<String>> groups = collection.chunk(2);
        Verify.assertIterablesEqual(Lists.mutable.with(1), groups.collect(RichIterable::size));
    }

    @Override
    @Test
    public void chunk_large_size()
    {
        RichIterable<String> collection = this.newWith("1", "2", "3", "4", "5", "6", "7");
        Verify.assertIterablesEqual(collection, collection.chunk(10).getOnly());
    }

    @Override
    @Test
    public void tap()
    {
        MutableList<String> tapResult = Lists.mutable.of();
        StackIterable<String> stack = this.newStackWith("1", "2", "3", "4", "5");
        Assert.assertSame(stack, stack.tap(tapResult::add));
        Assert.assertEquals(stack.toList(), tapResult);
    }

    @Override
    @Test
    public void forEach()
    {
        StackIterable<String> stack = this.newStackWith("1", "2", "3", "4", "5");
        Appendable builder = new StringBuilder();
        Procedure<String> appendProcedure = Procedures.append(builder);
        stack.forEach(appendProcedure);
        Assert.assertEquals("54321", builder.toString());
    }

    @Override
    @Test
    public void forEachWith()
    {
        StackIterable<String> stack = this.newStackWith("1", "2", "3", "4");
        StringBuilder builder = new StringBuilder();
        stack.forEachWith((argument1, argument2) -> builder.append(argument1).append(argument2), 0);
        Assert.assertEquals("40302010", builder.toString());
    }

    @Override
    @Test
    public void forEachWithIndex()
    {
        StackIterable<String> stack = this.newStackFromTopToBottom("5", "4", "3", "2", "1");
        StringBuilder builder = new StringBuilder();
        stack.forEachWithIndex((each, index) -> builder.append(each).append(index));
        Assert.assertEquals("5041322314", builder.toString());
    }

    @Override
    @Test
    public void toList()
    {
        Assert.assertEquals(
                Lists.mutable.with(4, 3, 2, 1),
                this.newStackFromTopToBottom(4, 3, 2, 1).toList());
    }

    @Test
    public void toStack()
    {
        Assert.assertEquals(this.newStackFromTopToBottom(3, 2, 1), this.newStackFromTopToBottom(3, 2, 1).toStack());
    }

    @Test
    public void toSortedList()
    {
        Assert.assertEquals(
                Interval.oneTo(4),
                this.newStackFromTopToBottom(4, 3, 1, 2).toSortedList());
        Assert.assertEquals(
                Interval.fromTo(4, 1),
                this.newStackFromTopToBottom(4, 3, 1, 2).toSortedList(Collections.reverseOrder()));
    }

    @Override
    @Test
    public void toSortedListBy()
    {
        MutableList<Integer> list = FastList.newList(Interval.oneTo(10)).shuffleThis();
        Assert.assertEquals(
                Interval.oneTo(10),
                this.newStack(list).toSortedListBy(Functions.getIntegerPassThru()));
    }

    @Override
    @Test
    public void toSet()
    {
        Assert.assertEquals(UnifiedSet.newSetWith(4, 3, 2, 1),
                this.newStackWith(1, 2, 3, 4).toSet());
    }

    @Test
    public void toSortedSet()
    {
        MutableSortedSet<Integer> expected = TreeSortedSet.newSetWith(1, 2, 4, 5);
        StackIterable<Integer> stack = this.newStackWith(2, 1, 5, 4);

        Assert.assertEquals(expected, stack.toSortedSet());
        Assert.assertEquals(Lists.mutable.with(1, 2, 4, 5), stack.toSortedSet().toList());

        MutableSortedSet<Integer> reversed = stack.toSortedSet(Comparators.reverseNaturalOrder());
        Verify.assertSortedSetsEqual(reversed, stack.toSortedSet(Comparators.reverseNaturalOrder()));
        Assert.assertEquals(
                Lists.mutable.with(5, 4, 2, 1),
                stack.toSortedSet(Comparators.reverseNaturalOrder()).toList());
    }

    @Override
    @Test
    public void toSortedSetBy()
    {
        SetIterable<Integer> expected = UnifiedSet.newSetWith(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);

        StackIterable<Integer> stack = this.newStackWith(5, 2, 4, 3, 1, 6, 7, 8, 9, 10);
        Assert.assertEquals(
                expected,
                stack.toSortedSetBy(String::valueOf));
        Assert.assertEquals(
                Lists.mutable.with(1, 10, 2, 3, 4, 5, 6, 7, 8, 9),
                stack.toSortedSetBy(String::valueOf).toList());
    }

    @Override
    @Test
    public void toBag()
    {
        Assert.assertEquals(Bags.mutable.of("C", "B", "A"),
                this.newStackFromTopToBottom("C", "B", "A").toBag());
    }

    @Test
    public void toSortedBag()
    {
        SortedBag<Integer> expected = TreeBag.newBagWith(1, 2, 2, 4, 5);
        StackIterable<Integer> stack = this.newStackWith(2, 2, 1, 5, 4);

        Verify.assertSortedBagsEqual(expected, stack.toSortedBag());
        Assert.assertEquals(Lists.mutable.with(1, 2, 2, 4, 5), stack.toSortedBag().toList());

        SortedBag<Integer> expected2 = TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 1, 2, 2, 4, 5);
        Verify.assertSortedBagsEqual(expected2, stack.toSortedBag(Comparators.reverseNaturalOrder()));
        Assert.assertEquals(
                Lists.mutable.with(5, 4, 2, 2, 1),
                stack.toSortedBag(Comparators.reverseNaturalOrder()).toList());
    }

    @Override
    @Test
    public void toSortedBagBy()
    {
        SortedBag<Integer> expected = TreeBag.newBagWith(1, 2, 3, 3, 4, 5);

        StackIterable<Integer> stack = this.newStackWith(1, 2, 3, 3, 4, 5);
        Verify.assertSortedBagsEqual(
                expected,
                stack.toSortedBagBy(String::valueOf));
    }

    @Override
    @Test
    public void toMap()
    {
        Assert.assertEquals(UnifiedMap.newWithKeysValues("4", "4", "3", "3", "2", "2", "1", "1"),
                this.newStackFromTopToBottom(4, 3, 2, 1).toMap(String::valueOf, String::valueOf));
    }

    @Override
    @Test
    public void toSortedMap()
    {
        Assert.assertEquals(UnifiedMap.newWithKeysValues(3, "3", 2, "2", 1, "1"),
                this.newStackFromTopToBottom(3, 2, 1).toSortedMap(Functions.getIntegerPassThru(), String::valueOf));

        Assert.assertEquals(TreeSortedMap.newMapWith(Comparators.reverseNaturalOrder(), 3, "3", 2, "2", 1, "1"),
                this.newStackFromTopToBottom(3, 2, 1).toSortedMap(Comparators.reverseNaturalOrder(),
                        Functions.getIntegerPassThru(), String::valueOf));

        Assert.assertEquals(TreeSortedMap.newMapWith(Comparators.reverseNaturalOrder(), 3, "3", 2, "2", 1, "1"),
                this.newStackFromTopToBottom(3, 2, 1).toSortedMapBy(key -> -key,
                        Functions.getIntegerPassThru(), String::valueOf));
    }

    @Test
    public void asLazy()
    {
        Assert.assertEquals(Lists.mutable.with("3", "2", "1"),
                this.newStackFromTopToBottom("3", "2", "1").asLazy().toList());
    }

    @Override
    @Test
    public void toArray()
    {
        Assert.assertArrayEquals(new Object[]{4, 3, 2, 1}, this.newStackFromTopToBottom(4, 3, 2, 1).toArray());
        Assert.assertArrayEquals(new Integer[]{4, 3, 2, 1}, this.newStackFromTopToBottom(4, 3, 2, 1).toArray(new Integer[0]));
    }

    @Override
    @Test
    public void iterator()
    {
        StringBuilder builder = new StringBuilder();
        StackIterable<String> stack = this.newStackFromTopToBottom("5", "4", "3", "2", "1");
        for (String string : stack)
        {
            builder.append(string);
        }
        Assert.assertEquals("54321", builder.toString());
    }

    @Test
    public void testEquals()
    {
        StackIterable<Integer> stack1 = this.newStackFromTopToBottom(1, 2, 3, 4);
        StackIterable<Integer> stack2 = this.newStackFromTopToBottom(1, 2, 3, 4);
        StackIterable<Integer> stack3 = this.newStackFromTopToBottom(5, 2, 1, 4);
        StackIterable<Integer> stack4 = this.newStackFromTopToBottom(1, 2, 3);
        StackIterable<Integer> stack5 = this.newStackFromTopToBottom(1, 2, 3, 4, 5);
        StackIterable<Integer> stack6 = this.newStackFromTopToBottom(1, 2, 3, null);

        Verify.assertEqualsAndHashCode(stack1, stack2);
        Verify.assertPostSerializedEqualsAndHashCode(this.newStackWith(1, 2, 3, 4));
        Assert.assertNotEquals(stack1, stack3);
        Assert.assertNotEquals(stack1, stack4);
        Assert.assertNotEquals(stack1, stack5);
        Assert.assertNotEquals(stack1, stack6);

        Verify.assertPostSerializedEqualsAndHashCode(this.newStackWith(null, null, null));

        Assert.assertEquals(Stacks.mutable.of(), this.newStackWith());
    }

    @Test
    public void testHashCode()
    {
        StackIterable<Integer> stack1 = this.newStackWith(1, 2, 3, 5);
        StackIterable<Integer> stack2 = this.newStackWith(1, 2, 3, 4);
        Assert.assertNotEquals(stack1.hashCode(), stack2.hashCode());

        Assert.assertEquals(
                31 * 31 * 31 * 31 + 1 * 31 * 31 * 31 + 2 * 31 * 31 + 3 * 31 + 4,
                this.newStackFromTopToBottom(1, 2, 3, 4).hashCode());
        Assert.assertEquals(31 * 31 * 31, this.newStackFromTopToBottom(null, null, null).hashCode());

        Assert.assertNotEquals(this.newStackFromTopToBottom(1, 2, 3, 4).hashCode(), this.newStackFromTopToBottom(4, 3, 2, 1).hashCode());
    }

    @Override
    @Test
    public void aggregateByMutating()
    {
        Function0<AtomicInteger> valueCreator = AtomicInteger::new;
        StackIterable<Integer> collection = this.newStackWith(1, 1, 1, 2, 2, 3);
        MapIterable<String, AtomicInteger> aggregation = collection.aggregateInPlaceBy(String::valueOf, valueCreator, AtomicInteger::addAndGet);
        Assert.assertEquals(3, aggregation.get("1").intValue());
        Assert.assertEquals(4, aggregation.get("2").intValue());
        Assert.assertEquals(3, aggregation.get("3").intValue());
    }

    @Override
    @Test
    public void aggregateByNonMutating()
    {
        Function0<Integer> valueCreator = () -> 0;
        Function2<Integer, Integer, Integer> sumAggregator = (integer1, integer2) -> integer1 + integer2;
        StackIterable<Integer> collection = this.newStackWith(1, 1, 1, 2, 2, 3);
        MapIterable<String, Integer> aggregation = collection.aggregateBy(String::valueOf, valueCreator, sumAggregator);
        Assert.assertEquals(3, aggregation.get("1").intValue());
        Assert.assertEquals(4, aggregation.get("2").intValue());
        Assert.assertEquals(3, aggregation.get("3").intValue());
    }

    private static final class CountingPredicate<T>
            implements Predicate<T>
    {
        private static final long serialVersionUID = 1L;
        private final Predicate<T> predicate;
        private int count;

        private CountingPredicate(Predicate<T> predicate)
        {
            this.predicate = predicate;
        }

        private static <T> CountingPredicate<T> of(Predicate<T> predicate)
        {
            return new CountingPredicate<>(predicate);
        }

        @Override
        public boolean accept(T anObject)
        {
            this.count++;
            return this.predicate.accept(anObject);
        }
    }

    private static final class CountingPredicate2<T1, T2>
            implements Predicate2<T1, T2>
    {
        private static final long serialVersionUID = 1L;
        private final Predicate2<T1, T2> predicate;
        private int count;

        private CountingPredicate2(Predicate2<T1, T2> predicate)
        {
            this.predicate = predicate;
        }

        private static <T1, T2> CountingPredicate2<T1, T2> of(Predicate2<T1, T2> predicate)
        {
            return new CountingPredicate2<>(predicate);
        }

        @Override
        public boolean accept(T1 each, T2 parameter)
        {
            this.count++;
            return this.predicate.accept(each, parameter);
        }
    }

    private static final class CountingFunction<T, V>
            implements Function<T, V>
    {
        private static final long serialVersionUID = 1L;
        private int count;
        private final Function<T, V> function;

        private CountingFunction(Function<T, V> function)
        {
            this.function = function;
        }

        private static <T, V> CountingFunction<T, V> of(Function<T, V> function)
        {
            return new CountingFunction<>(function);
        }

        @Override
        public V valueOf(T object)
        {
            this.count++;
            return this.function.valueOf(object);
        }
    }
}
