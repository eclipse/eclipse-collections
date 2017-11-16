/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableBooleanCollection;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.partition.list.PartitionImmutableList;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.ObjectIntProcedures;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.block.function.NegativeIntervalFunction;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.collection.immutable.AbstractImmutableCollectionTestCase;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.ListIterate;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;

public abstract class AbstractImmutableListTestCase extends AbstractImmutableCollectionTestCase
{
    @Override
    protected abstract ImmutableList<Integer> classUnderTest();

    @Override
    protected <T> MutableList<T> newMutable()
    {
        return FastList.newList();
    }

    @Test
    public void equalsAndHashCode()
    {
        ImmutableList<Integer> immutable = this.classUnderTest();
        MutableList<Integer> mutable1 = FastList.newList(immutable);
        ImmutableList<Integer> immutable1 = mutable1.toImmutable();
        List<Integer> mutable2 = new LinkedList<>(mutable1);
        List<Integer> mutable3 = new ArrayList<>(mutable1);
        Verify.assertEqualsAndHashCode(mutable1, immutable);
        Verify.assertEqualsAndHashCode(immutable1, immutable);
        Verify.assertEqualsAndHashCode(mutable2, immutable);
        Verify.assertEqualsAndHashCode(mutable3, immutable);
        Verify.assertPostSerializedEqualsAndHashCode(immutable);
        Assert.assertNotEquals(immutable, UnifiedSet.newSet(mutable1));
        mutable1.add(null);
        mutable2.add(null);
        mutable3.add(null);
        Assert.assertNotEquals(mutable1, immutable);
        Assert.assertNotEquals(mutable2, immutable);
        Assert.assertNotEquals(mutable3, immutable);
        mutable1.remove(null);
        mutable2.remove(null);
        mutable3.remove(null);
        Verify.assertEqualsAndHashCode(mutable1, immutable);
        Verify.assertEqualsAndHashCode(mutable2, immutable);
        Verify.assertEqualsAndHashCode(mutable3, immutable);
        if (immutable.size() > 2)
        {
            mutable1.set(2, null);
            mutable2.set(2, null);
            mutable3.set(2, null);
            Assert.assertNotEquals(mutable1, immutable);
            Assert.assertNotEquals(mutable2, immutable);
            Assert.assertNotEquals(mutable3, immutable);
            mutable1.remove(2);
            mutable2.remove(2);
            mutable3.remove(2);
            Assert.assertNotEquals(mutable1, immutable);
            Assert.assertNotEquals(mutable2, immutable);
            Assert.assertNotEquals(mutable3, immutable);
        }
    }

    @Test
    public void contains()
    {
        ImmutableList<Integer> list = this.classUnderTest();
        for (int i = 1; i <= list.size(); i++)
        {
            Assert.assertTrue(list.contains(i));
        }
        Assert.assertFalse(list.contains(list.size() + 1));
    }

    @Test
    public void containsAll()
    {
        Assert.assertTrue(this.classUnderTest().containsAll(this.classUnderTest().toList()));
    }

    @Test
    public void containsAllArray()
    {
        Assert.assertTrue(this.classUnderTest().containsAllArguments(this.classUnderTest().toArray()));
    }

    @Test
    public void containsAllIterable()
    {
        Assert.assertTrue(this.classUnderTest().containsAllIterable(this.classUnderTest()));
    }

    @Test
    public void indexOf()
    {
        Assert.assertEquals(0, this.classUnderTest().indexOf(1));
        Assert.assertEquals(-1, this.classUnderTest().indexOf(null));
        ImmutableList<Integer> immutableList = this.classUnderTest().newWith(null);
        Assert.assertEquals(immutableList.size() - 1, immutableList.indexOf(null));
        Assert.assertEquals(-1, this.classUnderTest().indexOf(Integer.MAX_VALUE));
    }

    @Test
    public void lastIndexOf()
    {
        Assert.assertEquals(0, this.classUnderTest().lastIndexOf(1));
        Assert.assertEquals(-1, this.classUnderTest().lastIndexOf(null));
        Assert.assertEquals(-1, this.classUnderTest().lastIndexOf(null));
        ImmutableList<Integer> immutableList = this.classUnderTest().newWith(null);
        Assert.assertEquals(immutableList.size() - 1, immutableList.lastIndexOf(null));
        Assert.assertEquals(-1, this.classUnderTest().lastIndexOf(Integer.MAX_VALUE));
    }

    @Test
    public void get()
    {
        ImmutableList<Integer> list = this.classUnderTest();
        Verify.assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size() + 1));
        Verify.assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    public void forEach()
    {
        MutableList<Integer> result = Lists.mutable.of();
        ImmutableList<Integer> collection = this.classUnderTest();
        collection.forEach(CollectionAddProcedure.on(result));
        Assert.assertEquals(collection, result);
    }

    @Test
    public void each()
    {
        MutableList<Integer> result = Lists.mutable.of();
        ImmutableList<Integer> collection = this.classUnderTest();
        collection.each(result::add);
        Assert.assertEquals(collection, result);
    }

    @Test
    public void reverseForEach()
    {
        MutableList<Integer> result = Lists.mutable.of();
        ImmutableList<Integer> list = this.classUnderTest();
        list.reverseForEach(result::add);
        Assert.assertEquals(ListIterate.reverseThis(FastList.newList(list)), result);
    }

    @Test
    public void reverseForEachWithIndex()
    {
        MutableList<Integer> expected = Lists.mutable.of();
        MutableList<Integer> result = Lists.mutable.of();
        ImmutableList<Integer> list = this.classUnderTest();
        list.reverseForEachWithIndex((each, index) -> Assert.assertEquals(each - 1, index));
        list.reverseForEachWithIndex((each, index) -> result.add(each + index));
        list.forEachWithIndex((each, index) -> expected.add(each + index));
        Assert.assertEquals(expected.reverseThis(), result);
    }

    @Test
    public void corresponds()
    {
        ImmutableList<Integer> integers1 = this.classUnderTest();
        ImmutableList<Integer> integers2 = this.classUnderTest().newWith(Integer.valueOf(1));
        Assert.assertFalse(integers1.corresponds(integers2, Predicates2.alwaysTrue()));

        ImmutableList<Integer> integers3 = integers1.collect(integer -> integer + 1);
        Assert.assertTrue(integers1.corresponds(integers3, Predicates2.lessThan()));
        Assert.assertFalse(integers1.corresponds(integers3, Predicates2.greaterThan()));
    }

    @Test
    public void forEachFromTo()
    {
        MutableList<Integer> result = Lists.mutable.of();
        MutableList<Integer> reverseResult = Lists.mutable.of();
        ImmutableList<Integer> list = this.classUnderTest();
        list.forEach(0, list.size() - 1, result::add);
        Assert.assertEquals(list, result);
        list.forEach(list.size() - 1, 0, reverseResult::add);
        Assert.assertEquals(ListIterate.reverseThis(FastList.newList(list)), reverseResult);

        Verify.assertThrows(IndexOutOfBoundsException.class, () -> list.forEach(-1, 0, result::add));
        Verify.assertThrows(IndexOutOfBoundsException.class, () -> list.forEach(0, -1, result::add));
    }

    @Test
    public void forEachWithIndexFromTo()
    {
        MutableList<Integer> result = Lists.mutable.of();
        MutableList<Integer> reverseResult = Lists.mutable.of();
        ImmutableList<Integer> list = this.classUnderTest();
        list.forEachWithIndex(0, list.size() - 1, ObjectIntProcedures.fromProcedure(result::add));
        Assert.assertEquals(list, result);
        list.forEachWithIndex(list.size() - 1, 0, ObjectIntProcedures.fromProcedure(reverseResult::add));
        Assert.assertEquals(ListIterate.reverseThis(FastList.newList(list)), reverseResult);

        Verify.assertThrows(IndexOutOfBoundsException.class, () -> list.forEachWithIndex(-1, 0, result::add));
        Verify.assertThrows(IndexOutOfBoundsException.class, () -> list.forEachWithIndex(0, -1, result::add));
    }

    @Test
    public void forEachWith()
    {
        MutableCollection<Integer> result = Lists.mutable.of();
        this.classUnderTest().forEachWith((argument1, argument2) -> result.add(argument1 + argument2), 0);
        Assert.assertEquals(this.classUnderTest(), result);
    }

    @Test
    public void forEachWithIndex()
    {
        ImmutableList<Integer> list = this.classUnderTest();
        MutableList<Integer> result = Lists.mutable.of();
        list.forEachWithIndex((object, index) -> result.add(object + index));
        result.forEachWithIndex((object, index) -> Assert.assertEquals(object, result.set(index, object - index)));
        Assert.assertEquals(list, result);
    }

    @Test
    public void detectIndex()
    {
        Assert.assertEquals(0, this.classUnderTest().detectIndex(integer -> integer == 1));
        Assert.assertEquals(-1, this.classUnderTest().detectIndex(integer -> integer == 0));
    }

    @Test
    public void detectLastIndex()
    {
        Assert.assertEquals(0, this.classUnderTest().detectLastIndex(integer -> integer == 1));
        Assert.assertEquals(-1, this.classUnderTest().detectLastIndex(integer -> integer == 0));
    }

    @Test
    public void select_target()
    {
        ImmutableCollection<Integer> integers = this.classUnderTest();
        Assert.assertEquals(integers, integers.select(Predicates.lessThan(integers.size() + 1), FastList.newList()));
        Verify.assertEmpty(integers.select(Predicates.greaterThan(integers.size()), FastList.newList()));
    }

    @Test
    public void reject_target()
    {
        ImmutableCollection<Integer> integers = this.classUnderTest();
        Verify.assertEmpty(integers.reject(Predicates.lessThan(integers.size() + 1), FastList.newList()));
        Assert.assertEquals(integers, integers.reject(Predicates.greaterThan(integers.size()), FastList.newList()));
    }

    @Test
    public void flatCollectWithTarget()
    {
        MutableCollection<String> actual = this.classUnderTest().flatCollect(integer -> Lists.fixedSize.of(String.valueOf(integer)), FastList.newList());

        ImmutableCollection<String> expected = this.classUnderTest().collect(String::valueOf);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void distinct()
    {
        ImmutableList<Integer> integers = this.classUnderTest();
        Assert.assertEquals(integers, integers.newWith(1).distinct());
        Assert.assertEquals(this.classUnderTest(), this.classUnderTest().distinct());
    }

    @Test
    public void distinctWithHashingStrategy()
    {
        ImmutableList<Integer> integers = this.classUnderTest();
        HashingStrategy<Integer> hashingStrategy = HashingStrategies.fromFunction(e -> e % 2);
        if (integers.size() > 1)
        {
            Assert.assertEquals(Lists.immutable.with(1, 2), integers.distinct(hashingStrategy));
        }
        else if (integers.size() == 1)
        {
            Assert.assertEquals(Lists.immutable.with(1), integers.distinct(hashingStrategy));
        }
        else
        {
            Assert.assertEquals(Lists.immutable.empty(), integers.distinct(hashingStrategy));
        }
    }

    /**
     * @since 9.0.
     */
    @Test
    public void distinctBy()
    {
        ImmutableList<Integer> integers = this.classUnderTest();
        if (integers.size() > 1)
        {
            Assert.assertEquals(Lists.immutable.with(1, 2), integers.distinctBy(e -> e % 2));
        }
        else if (integers.size() == 1)
        {
            Assert.assertEquals(Lists.immutable.with(1), integers.distinctBy(e -> e % 2));
        }
        else
        {
            Assert.assertEquals(Lists.immutable.empty(), integers.distinctBy(e -> e % 2));
        }
    }

    @Test
    public void zip()
    {
        ImmutableCollection<Integer> immutableCollection = this.classUnderTest();
        List<Object> nulls = Collections.nCopies(immutableCollection.size(), null);
        List<Object> nullsPlusOne = Collections.nCopies(immutableCollection.size() + 1, null);
        List<Object> nullsMinusOne = Collections.nCopies(immutableCollection.size() - 1, null);

        ImmutableCollection<Pair<Integer, Object>> pairs = immutableCollection.zip(nulls);
        Assert.assertEquals(immutableCollection, pairs.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne));
        Assert.assertEquals(nulls, pairs.collect((Function<Pair<?, Object>, Object>) Pair::getTwo));

        ImmutableCollection<Pair<Integer, Object>> pairsPlusOne = immutableCollection.zip(nullsPlusOne);
        Assert.assertEquals(immutableCollection, pairsPlusOne.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne));
        Assert.assertEquals(nulls, pairsPlusOne.collect((Function<Pair<?, Object>, Object>) Pair::getTwo));

        ImmutableCollection<Pair<Integer, Object>> pairsMinusOne = immutableCollection.zip(nullsMinusOne);
        Assert.assertEquals(immutableCollection.size() - 1, pairsMinusOne.size());
        Assert.assertTrue(immutableCollection.containsAllIterable(pairsMinusOne.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne)));

        Assert.assertEquals(immutableCollection.zip(nulls), immutableCollection.zip(nulls, FastList.newList()));
    }

    @Test
    public void zipWithIndex()
    {
        ImmutableCollection<Integer> immutableCollection = this.classUnderTest();
        ImmutableCollection<Pair<Integer, Integer>> pairs = immutableCollection.zipWithIndex();

        Assert.assertEquals(immutableCollection, pairs.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne));
        Assert.assertEquals(Interval.zeroTo(immutableCollection.size() - 1), pairs.collect((Function<Pair<?, Integer>, Integer>) Pair::getTwo));

        Assert.assertEquals(immutableCollection.zipWithIndex(), immutableCollection.zipWithIndex(FastList.newList()));
    }

    @Test
    public void chunk_large_size()
    {
        Assert.assertEquals(this.classUnderTest(), this.classUnderTest().chunk(10).getFirst());
        Verify.assertInstanceOf(ImmutableList.class, this.classUnderTest().chunk(10).getFirst());
    }

    @Test
    public void collectIfWithTarget()
    {
        ImmutableCollection<Integer> integers = this.classUnderTest();
        Assert.assertEquals(integers, integers.collectIf(Integer.class::isInstance, Functions.getIntegerPassThru(), FastList.newList()));
    }

    @Test
    public void toList()
    {
        ImmutableCollection<Integer> integers = this.classUnderTest();
        MutableList<Integer> list = integers.toList();
        Verify.assertEqualsAndHashCode(integers, list);
        Assert.assertNotSame(integers, list);
    }

    @Test
    public void toSortedListBy()
    {
        MutableList<Integer> mutableList = this.classUnderTest().toList();
        mutableList.shuffleThis();
        ImmutableList<Integer> immutableList = mutableList.toImmutable();
        MutableList<Integer> sortedList = immutableList.toSortedListBy(Functions.getIntegerPassThru());
        Assert.assertEquals(this.classUnderTest(), sortedList);
    }

    @Test
    public void removeAtIndex()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().castToList().remove(1));
    }

    @Test
    public void set()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().castToList().set(0, 1));
    }

    @Test
    public void addAtIndex()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().castToList().add(0, 1));
    }

    @Test
    public void addAllAtIndex()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().castToList().addAll(0, Lists.fixedSize.of()));
    }

    @Test
    public void subList()
    {
        Verify.assertListsEqual(Lists.immutable.of(1).castToList(),
                this.classUnderTest().castToList().subList(0, 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subListFromNegative()
    {
        this.classUnderTest().castToList().subList(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void subListFromGreaterThanTO()
    {
        this.classUnderTest().castToList().subList(1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subListToGreaterThanSize()
    {
        this.classUnderTest().castToList().subList(0, 100);
    }

    @Test
    public void listIterator()
    {
        ListIterator<Integer> it = this.classUnderTest().listIterator();
        Assert.assertFalse(it.hasPrevious());

        Verify.assertThrows(NoSuchElementException.class, (Runnable) it::previous);

        Assert.assertEquals(-1, it.previousIndex());
        Assert.assertEquals(0, it.nextIndex());
        it.next();
        Assert.assertEquals(1, it.nextIndex());

        Verify.assertThrows(UnsupportedOperationException.class, it::remove);

        Verify.assertThrows(UnsupportedOperationException.class, () -> it.add(null));

        Verify.assertThrows(UnsupportedOperationException.class, () -> it.set(null));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void listIterator_throwsNegative()
    {
        this.classUnderTest().listIterator(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void listIterator_throwsGreaterThanSize()
    {
        this.classUnderTest().listIterator(100);
    }

    @Test
    public void toStack()
    {
        MutableStack<Integer> stack = this.classUnderTest().toStack();
        Assert.assertEquals(stack.toSortedList().toReversed(), stack.toList());
    }

    @Test
    public void take()
    {
        ImmutableList<Integer> immutableList = this.classUnderTest();
        Assert.assertEquals(Lists.immutable.of(), immutableList.take(0));
        Assert.assertEquals(iList(1), immutableList.take(1));
        Assert.assertEquals(immutableList, immutableList.take(10));
        MutableList<Integer> mutableList = Lists.mutable.ofAll(immutableList);
        Assert.assertEquals(
                mutableList.take(mutableList.size() - 1),
                immutableList.take(immutableList.size() - 1));

        Assert.assertSame(immutableList, immutableList.take(immutableList.size()));
        Assert.assertSame(immutableList, immutableList.take(Integer.MAX_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void take_throws()
    {
        this.classUnderTest().take(-1);
    }

    @Test
    public void takeWhile()
    {
        Assert.assertEquals(
                iList(1),
                this.classUnderTest().takeWhile(Predicates.lessThan(2)));
    }

    @Test
    public void drop()
    {
        ImmutableList<Integer> immutableList = this.classUnderTest();
        Assert.assertSame(immutableList, immutableList.drop(0));
        MutableList<Integer> mutableList = Lists.mutable.ofAll(immutableList);
        Assert.assertEquals(mutableList.drop(1), immutableList.drop(1));

        if (mutableList.size() > 0)
        {
            Assert.assertEquals(
                    mutableList.drop(mutableList.size() - 1),
                    immutableList.drop(immutableList.size() - 1));
        }
        Assert.assertEquals(Lists.immutable.of(), immutableList.drop(10));
        Assert.assertEquals(Lists.immutable.of(), immutableList.drop(immutableList.size()));
        Assert.assertEquals(Lists.immutable.of(), immutableList.drop(Integer.MAX_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void drop_throws()
    {
        this.classUnderTest().drop(-1);
    }

    @Test
    public void dropWhile()
    {
        Assert.assertEquals(
                this.classUnderTest(),
                this.classUnderTest().dropWhile(Predicates.lessThan(0)));
        Assert.assertEquals(
                Lists.immutable.of(),
                this.classUnderTest().dropWhile(Predicates.greaterThan(0)));
    }

    @Test
    public void partitionWhile()
    {
        PartitionImmutableList<Integer> partitionAll = this.classUnderTest().partitionWhile(Predicates.greaterThan(0));
        Assert.assertEquals(this.classUnderTest(), partitionAll.getSelected());
        Assert.assertEquals(Lists.immutable.of(), partitionAll.getRejected());

        PartitionImmutableList<Integer> partitionNone = this.classUnderTest().partitionWhile(Predicates.lessThan(0));
        Assert.assertEquals(Lists.immutable.of(), partitionNone.getSelected());
        Assert.assertEquals(this.classUnderTest(), partitionNone.getRejected());
    }

    @Override
    @Test
    public void collectBoolean()
    {
        ImmutableCollection<Integer> integers = this.classUnderTest();
        ImmutableBooleanCollection immutableCollection = integers.collectBoolean(PrimitiveFunctions.integerIsPositive());
        Verify.assertSize(integers.size(), immutableCollection);
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithIndex()
    {
        RichIterable<ObjectIntPair<Integer>> pairs = this.classUnderTest()
                .collectWithIndex(PrimitiveTuples::pair);
        Assert.assertEquals(
                IntLists.mutable.withAll(IntInterval.zeroTo(pairs.size() - 1)),
                pairs.collectInt(ObjectIntPair::getTwo, IntLists.mutable.empty()));
        Assert.assertEquals(
                Lists.mutable.withAll(Interval.oneTo(pairs.size())),
                pairs.collect(ObjectIntPair::getOne, Lists.mutable.empty()));
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithIndexWithTarget()
    {
        RichIterable<ObjectIntPair<Integer>> pairs =
                this.classUnderTest().collectWithIndex(PrimitiveTuples::pair, Lists.mutable.empty());
        Assert.assertEquals(
                IntLists.mutable.withAll(IntInterval.zeroTo(pairs.size() - 1)),
                pairs.collectInt(ObjectIntPair::getTwo, IntLists.mutable.empty()));
        Assert.assertEquals(
                Lists.mutable.withAll(Interval.oneTo(pairs.size())),
                pairs.collect(ObjectIntPair::getOne, Lists.mutable.empty()));

        RichIterable<ObjectIntPair<Integer>> setOfPairs =
                this.classUnderTest().collectWithIndex(PrimitiveTuples::pair, Sets.mutable.empty());
        Assert.assertEquals(
                IntSets.mutable.withAll(IntInterval.zeroTo(pairs.size() - 1)),
                setOfPairs.collectInt(ObjectIntPair::getTwo, IntSets.mutable.empty()));
        Assert.assertEquals(
                Sets.mutable.withAll(Interval.oneTo(pairs.size())),
                setOfPairs.collect(ObjectIntPair::getOne, Sets.mutable.empty()));
    }

    @Test
    public void groupBy()
    {
        ImmutableList<Integer> list = this.classUnderTest();
        ImmutableListMultimap<Boolean, Integer> multimap =
                list.groupBy(integer -> IntegerPredicates.isOdd().accept(integer));

        MutableMap<Boolean, RichIterable<Integer>> actualMap = multimap.toMap();
        int halfSize = this.classUnderTest().size() / 2;
        boolean odd = this.classUnderTest().size() % 2 != 0;
        Assert.assertEquals(halfSize, Iterate.sizeOf(actualMap.getIfAbsent(false, FastList::new)));
        Assert.assertEquals(halfSize + (odd ? 1 : 0), Iterate.sizeOf(actualMap.getIfAbsent(true, FastList::new)));
    }

    @Test
    public void groupByEach()
    {
        ImmutableList<Integer> list = this.classUnderTest();

        MutableMultimap<Integer, Integer> expected = FastListMultimap.newMultimap();
        list.forEach(Procedures.cast(value -> expected.putAll(-value, Interval.fromTo(value, list.size()))));

        Multimap<Integer, Integer> actual = list.groupByEach(new NegativeIntervalFunction());
        Assert.assertEquals(expected, actual);

        Multimap<Integer, Integer> actualWithTarget =
                list.groupByEach(new NegativeIntervalFunction(), FastListMultimap.newMultimap());
        Assert.assertEquals(expected, actualWithTarget);
    }

    @Test
    public void asReversed()
    {
        Verify.assertIterablesEqual(this.classUnderTest().toList().toReversed(), this.classUnderTest().asReversed());
    }

    @Test
    public void toReversed()
    {
        ImmutableList<Integer> immutableList = this.classUnderTest();
        Assert.assertEquals(immutableList.toReversed().toReversed(), immutableList);
        if (immutableList.size() <= 1)
        {
            Assert.assertSame(immutableList.toReversed(), immutableList);
        }
        else
        {
            Assert.assertNotEquals(immutableList.toReversed(), immutableList);
        }
    }

    @Test
    public void toImmutable()
    {
        ImmutableList<Integer> integers = this.classUnderTest();
        ImmutableList<Integer> actual = integers.toImmutable();
        Assert.assertEquals(integers, actual);
        Assert.assertSame(integers, actual);
    }
}
