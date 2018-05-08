/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.sorted.immutable;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.sortedbag.ImmutableSortedBagMultimap;
import org.eclipse.collections.api.partition.bag.sorted.PartitionImmutableSortedBag;
import org.eclipse.collections.api.partition.bag.sorted.PartitionSortedBag;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.ByteHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.CharHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.DoubleHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.FloatHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.IntHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.LongHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.ShortHashBag;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.block.function.NegativeIntervalFunction;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.collection.immutable.AbstractImmutableCollectionTestCase;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.SortedBags;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.AddToList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.CharArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ShortArrayList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
import org.eclipse.collections.impl.multimap.bag.sorted.mutable.TreeBagMultimap;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractImmutableSortedBagTestCase extends AbstractImmutableCollectionTestCase
{
    @Override
    protected abstract ImmutableSortedBag<Integer> classUnderTest();

    protected abstract ImmutableSortedBag<Integer> classUnderTest(Comparator<? super Integer> comparator);

    protected <T> ImmutableSortedBag<T> newWithOccurrences(ObjectIntPair<T>... elementsWithOccurrences)
    {
        TreeBag<T> bag = TreeBag.newBag();
        for (int i = 0; i < elementsWithOccurrences.length; i++)
        {
            ObjectIntPair<T> itemToAdd = elementsWithOccurrences[i];
            bag.addOccurrences(itemToAdd.getOne(), itemToAdd.getTwo());
        }
        return bag.toImmutable();
    }

    protected abstract <T> ImmutableSortedBag<T> newWith(T... elements);

    protected abstract <T> ImmutableSortedBag<T> newWith(Comparator<? super T> comparator, T... elements);

    @Test(expected = NullPointerException.class)
    public void noSupportForNull()
    {
        this.classUnderTest().newWith(null);
    }

    @Test
    public void equalsAndHashCode()
    {
        ImmutableSortedBag<Integer> immutable = this.classUnderTest();
        MutableSortedBag<Integer> mutable = TreeBag.newBag(immutable);
        Verify.assertEqualsAndHashCode(mutable, immutable);
        Verify.assertPostSerializedEqualsAndHashCode(immutable);
        Assert.assertNotEquals(FastList.newList(mutable), immutable);

        ImmutableSortedBag<Integer> bag1 = SortedBags.immutable.of(1, 1, 1, 4);
        ImmutableSortedBag<Integer> bag2 = SortedBags.immutable.of(1, 1, 1, 3);
        Assert.assertNotEquals(bag1, bag2);
    }

    @Test
    public void compareTo()
    {
        Assert.assertEquals(-1, SortedBags.immutable.of(1, 1, 2, 2).compareTo(SortedBags.immutable.of(1, 1, 2, 2, 2)));
        Assert.assertEquals(0, SortedBags.immutable.of(1, 1, 2, 2).compareTo(SortedBags.immutable.of(1, 1, 2, 2)));
        Assert.assertEquals(1, SortedBags.immutable.of(1, 1, 2, 2, 2).compareTo(SortedBags.immutable.of(1, 1, 2, 2)));

        Assert.assertEquals(-1, SortedBags.immutable.of(1, 1, 2, 2).compareTo(SortedBags.immutable.of(1, 1, 3, 3)));
        Assert.assertEquals(1, SortedBags.immutable.of(1, 1, 3, 3).compareTo(SortedBags.immutable.of(1, 1, 2, 2)));

        Assert.assertEquals(1, SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 2, 2, 1, 1, 1).compareTo(SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 2, 2, 1, 1)));
        Assert.assertEquals(1, SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 2, 2).compareTo(SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 2, 2, 2)));
        Assert.assertEquals(0, SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 2, 2).compareTo(SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 2, 2)));
        Assert.assertEquals(-1, SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 2, 2, 2).compareTo(SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 2, 2)));

        Assert.assertEquals(1, SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 2, 2).compareTo(SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 3, 3)));
        Assert.assertEquals(-1, SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 3, 3).compareTo(SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 2, 2)));
    }

    @Test
    public void selectByOccurrences()
    {
        ImmutableSortedBag<Integer> ints = this.classUnderTest().selectByOccurrences(IntPredicates.isEven());
        Verify.assertAllSatisfy(ints, IntegerPredicates.isEven());

        ImmutableSortedBag<Integer> ints2 = this.classUnderTest().selectByOccurrences(IntPredicates.isOdd());
        Assert.assertEquals(ints2, this.classUnderTest());
    }

    @Test
    public void selectDuplicates()
    {
        Assert.assertEquals(
                Bags.immutable.with(1, 1, 1),
                this.classUnderTest().selectDuplicates());
    }

    @Test
    public void newWithTest()
    {
        ImmutableSortedBag<Integer> immutable = this.classUnderTest();
        immutable = immutable.newWith(4);

        // inserting at the beginning point (existing element)
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(1, 1, 1, 1, 2, 4), immutable.newWith(1));

        // inserting at the middle point (existing element)
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(1, 1, 1, 2, 2, 4), immutable.newWith(2));

        // inserting at the end point (existing element)
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(1, 1, 1, 2, 4, 4), immutable.newWith(4));

        // inserting at the beginning point (not existing element)
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(0, 1, 1, 1, 2, 4), immutable.newWith(0));

        // inserting at the middle point (not existing element)
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(1, 1, 1, 2, 3, 4), immutable.newWith(3));

        // inserting at the end point (not existing element)
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(1, 1, 1, 2, 4, 5), immutable.newWith(5));
    }

    @Test
    public void newWithout()
    {
        ImmutableSortedBag<Integer> immutable = this.classUnderTest();
        immutable = immutable.newWith(4);

        // removing at the beginning point (existing element)
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(1, 1, 2, 4), immutable.newWithout(1));

        // removing at the middle point (existing element)
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(1, 1, 1, 4), immutable.newWithout(2));

        // removing at the end point (existing element)
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(1, 1, 1, 2), immutable.newWithout(4));

        // removing at the beginning point (not existing element)
        Assert.assertEquals(immutable, immutable.newWithout(0));

        // removing at the middle point (not existing element)
        Assert.assertEquals(immutable, immutable.newWithout(3));

        // removing at the end point (not existing element)
        Assert.assertEquals(immutable, immutable.newWithout(5));
    }

    @Test
    public void newWithAll()
    {
        ImmutableSortedBag<Integer> bag = this.classUnderTest(Comparators.reverseNaturalOrder());
        ImmutableSortedBag<Integer> actualBag = bag.newWithAll(HashBag.newBagWith(3, 4));
        Assert.assertNotEquals(bag, actualBag);
        TreeBag<Integer> expectedBag = TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 4, 3, 2, 1, 1, 1);
        Verify.assertSortedBagsEqual(expectedBag,
                actualBag);
        Assert.assertSame(expectedBag.comparator(), actualBag.comparator());
    }

    @Test
    public void toStringOfItemToCount()
    {
        Assert.assertEquals("{}", SortedBags.immutable.empty().toStringOfItemToCount());
        Assert.assertEquals("{1=3, 2=1}", this.classUnderTest().toStringOfItemToCount());
        Assert.assertEquals("{2=1, 1=3}", this.classUnderTest(Comparator.reverseOrder()).toStringOfItemToCount());
    }

    @Test
    public void newWithoutAll()
    {
        ImmutableSortedBag<Integer> bag = this.classUnderTest();
        ImmutableSortedBag<Integer> withoutAll = bag.newWithoutAll(bag);

        Assert.assertEquals(SortedBags.immutable.<Integer>empty(), withoutAll);
        Assert.assertEquals(Bags.immutable.<Integer>empty(), withoutAll);

        ImmutableSortedBag<Integer> largeWithoutAll = bag.newWithoutAll(Interval.fromTo(101, 150));
        Assert.assertEquals(bag, largeWithoutAll);

        ImmutableSortedBag<Integer> largeWithoutAll2 = bag.newWithoutAll(HashBag.newBag(Interval.fromTo(151, 199)));
        Assert.assertEquals(bag, largeWithoutAll2);
    }

    @Test
    public void size()
    {
        ImmutableSortedBag<Object> empty = SortedBags.immutable.empty();
        Assert.assertEquals(0, empty.size());

        ImmutableSortedBag<?> empty2 = SortedBags.immutable.empty(Comparators.reverseNaturalOrder());
        Assert.assertEquals(0, empty2.size());

        ImmutableSortedBag<Integer> integers = SortedBags.immutable.of(Comparator.reverseOrder(), 1, 2, 3, 4, 4, 4);
        Assert.assertEquals(6, integers.size());

        ImmutableSortedBag<Integer> integers2 = SortedBags.immutable.of(1, 2, 3, 4, 4, 4);
        Assert.assertEquals(6, integers2.size());
    }

    @Test
    public void contains()
    {
        ImmutableSortedBag<Integer> bag1 = this.classUnderTest();

        Verify.assertContains(1, bag1);
        Verify.assertContains(2, bag1);
        Verify.assertNotContains(Integer.valueOf(bag1.size() + 1), bag1.toSortedBag());
    }

    @Test
    public void containsAllArray()
    {
        ImmutableSortedBag<Integer> bag1 = this.classUnderTest();

        Assert.assertTrue(bag1.containsAllArguments(bag1.toArray()));
    }

    @Test
    public void containsAllIterable()
    {
        ImmutableSortedBag<Integer> bag1 = this.classUnderTest();

        Assert.assertTrue(bag1.containsAllIterable(FastList.newListWith(1, 1, 1, 2)));
        Assert.assertFalse(bag1.containsAllIterable(FastList.newListWith(50, 1, 1, 2)));
    }

    @Test
    public void containsAll()
    {
        ImmutableSortedBag<Integer> bag1 = this.classUnderTest();

        Assert.assertTrue(bag1.containsAll(FastList.newListWith(1, 1, 1, 2)));
        Assert.assertFalse(bag1.containsAll(FastList.newListWith(50, 1, 1, 2)));
    }

    @Override
    @Test
    public void tap()
    {
        MutableList<Integer> tapResult = Lists.mutable.empty();
        ImmutableSortedBag<Integer> collection = this.classUnderTest();
        Assert.assertSame(collection, collection.tap(tapResult::add));
        Assert.assertEquals(collection.toList(), tapResult);
    }

    @Test
    public void forEach()
    {
        MutableBag<Integer> result = HashBag.newBag();
        ImmutableSortedBag<Integer> collection = this.classUnderTest();
        collection.forEach(CollectionAddProcedure.on(result));
        Assert.assertEquals(collection, result);
    }

    @Test
    public void forEachWith()
    {
        MutableList<Integer> result = Lists.mutable.empty();
        ImmutableSortedBag<Integer> bag = this.classUnderTest();
        bag.forEachWith((argument1, argument2) -> result.add(argument1 + argument2), 0);
        Verify.assertListsEqual(result, bag.toList());
    }

    @Test
    public void forEachWithIndex()
    {
        MutableList<Integer> result = Lists.mutable.empty();
        ImmutableSortedBag<Integer> bag = this.classUnderTest();
        bag.forEachWithIndex((object, index) -> result.add(object));
        Verify.assertListsEqual(result, bag.toList());
    }

    @Override
    public void toSortedSet()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        MutableSortedSet<Integer> set = integers.toSortedSet();
        Assert.assertEquals(SortedSets.immutable.of(1, 2), set);
    }

    @Override
    public void toSortedSetWithComparator()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Comparators.reverseNaturalOrder());
        MutableSortedSet<Integer> set = integers.toSortedSet();
        ImmutableSortedSet<Integer> expected = SortedSets.immutable.of(Comparators.reverseNaturalOrder(), 1, 2);
        Assert.assertEquals(expected, set);
        Assert.assertNotSame(expected.comparator(), set.comparator());

        ImmutableSortedBag<Integer> integers2 = this.classUnderTest(Comparators.reverseNaturalOrder());
        MutableSortedSet<Integer> set2 = integers2.toSortedSet(Comparators.reverseNaturalOrder());
        ImmutableSortedSet<Integer> expected2 = SortedSets.immutable.of(Comparators.reverseNaturalOrder(), 1, 2);
        Assert.assertEquals(expected2, set2);
        Assert.assertSame(expected2.comparator(), set2.comparator());
    }

    @Override
    @Test
    public void select()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Comparators.reverseNaturalOrder());
        Verify.assertIterableEmpty(integers.select(Predicates.greaterThan(integers.size())));
        TreeBag<Integer> expectedBag = TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 1, 1, 1, 2);
        ImmutableSortedBag<Integer> actualBag = integers.select(Predicates.lessThan(integers.size()));
        Verify.assertSortedBagsEqual(expectedBag, actualBag);
        Assert.assertSame(expectedBag.comparator(), actualBag.comparator());
    }

    @Override
    @Test
    public void selectWith()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Comparators.reverseNaturalOrder());
        Verify.assertIterableEmpty(integers.selectWith(Predicates2.greaterThan(), integers.size()));
        TreeBag<Integer> expectedBag = TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 1, 1, 1, 2);
        ImmutableSortedBag<Integer> actualBag = integers.selectWith(Predicates2.lessThan(), integers.size());
        Verify.assertSortedBagsEqual(expectedBag, actualBag);
        Assert.assertSame(expectedBag.comparator(), actualBag.comparator());
    }

    @Test
    public void selectToTarget()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Verify.assertListsEqual(integers.toList(),
                integers.select(Predicates.lessThan(integers.size() + 1), FastList.newList()));
        Verify.assertEmpty(
                integers.select(Predicates.greaterThan(integers.size()), FastList.newList()));
    }

    @Override
    @Test
    public void reject()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        Verify.assertEmpty(
                FastList.newList(integers.reject(Predicates.lessThan(integers.size() + 1))));
        Verify.assertSortedBagsEqual(integers,
                integers.reject(Predicates.greaterThan(integers.size())));
    }

    @Override
    @Test
    public void rejectWith()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Comparators.reverseNaturalOrder());
        Verify.assertIterableEmpty(integers.rejectWith(Predicates2.lessThanOrEqualTo(), integers.size()));
        TreeBag<Integer> expectedBag = TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 1, 1, 1, 2);
        ImmutableSortedBag<Integer> actualBag = integers.rejectWith(Predicates2.greaterThanOrEqualTo(), integers.size());
        Verify.assertSortedBagsEqual(expectedBag,
                actualBag);
        Assert.assertSame(expectedBag.comparator(), actualBag.comparator());
    }

    @Test
    public void rejectToTarget()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Verify.assertEmpty(
                integers.reject(Predicates.lessThan(integers.size() + 1), FastList.newList()));
        Verify.assertListsEqual(integers.toList(),
                integers.reject(Predicates.greaterThan(integers.size()), FastList.newList()));

        ImmutableSortedBag<Integer> integers2 = this.classUnderTest();
        Assert.assertEquals(HashBag.newBagWith(2),
                integers2.reject(each -> each == 1, new HashBag<>()));
    }

    @Override
    @Test
    public void selectInstancesOf()
    {
        ImmutableSortedBag<Integer> bag = this.classUnderTest(Collections.reverseOrder());
        Assert.assertEquals(bag, bag.selectInstancesOf(Integer.class));
        Verify.assertIterableEmpty(bag.selectInstancesOf(Double.class));
        Assert.assertSame(bag.comparator(), bag.selectInstancesOf(Integer.class).comparator());
        Assert.assertSame(bag.comparator(), bag.selectInstancesOf(Double.class).comparator());
    }

    @Override
    @Test
    public void partition()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        PartitionImmutableSortedBag<Integer> partition1 = integers.partition(Predicates.greaterThan(integers.size()));
        Verify.assertIterableEmpty(partition1.getSelected());
        Assert.assertEquals(integers, partition1.getRejected());
        Assert.assertSame(integers.comparator(), partition1.getSelected().comparator());
        Assert.assertSame(integers.comparator(), partition1.getRejected().comparator());

        PartitionImmutableSortedBag<Integer> partition2 = integers.partition(integer -> integer % 2 == 0);
        Verify.assertSortedBagsEqual(integers.select(integer -> integer % 2 == 0), partition2.getSelected());
        Verify.assertSortedBagsEqual(integers.reject(integer -> integer % 2 == 0), partition2.getRejected());
        Assert.assertSame(integers.comparator(), partition2.getSelected().comparator());
        Assert.assertSame(integers.comparator(), partition2.getRejected().comparator());
    }

    @Override
    @Test
    public void partitionWith()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        PartitionImmutableSortedBag<Integer> partition1 = integers.partitionWith(Predicates2.greaterThan(), integers.size());
        Verify.assertIterableEmpty(partition1.getSelected());
        Assert.assertEquals(integers, partition1.getRejected());
        Assert.assertSame(integers.comparator(), partition1.getSelected().comparator());
        Assert.assertSame(integers.comparator(), partition1.getRejected().comparator());

        PartitionImmutableSortedBag<Integer> partition2 = integers.partitionWith((integer, divisor) -> integer % divisor == 0, 2);
        Verify.assertSortedBagsEqual(integers.select(integer -> integer % 2 == 0), partition2.getSelected());
        Verify.assertSortedBagsEqual(integers.reject(integer -> integer % 2 == 0), partition2.getRejected());
        Assert.assertSame(integers.comparator(), partition2.getSelected().comparator());
        Assert.assertSame(integers.comparator(), partition2.getRejected().comparator());
    }

    @Test
    public void partitionWhile()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        PartitionSortedBag<Integer> partition1 = integers.partitionWhile(Predicates.greaterThan(integers.size()));
        Verify.assertIterableEmpty(partition1.getSelected());
        Assert.assertEquals(integers, partition1.getRejected());
        Assert.assertSame(integers.comparator(), partition1.getSelected().comparator());
        Assert.assertSame(integers.comparator(), partition1.getRejected().comparator());

        PartitionSortedBag<Integer> partition2 = integers.partitionWhile(Predicates.lessThanOrEqualTo(integers.size()));
        Assert.assertEquals(integers, partition2.getSelected());
        Verify.assertIterableEmpty(partition2.getRejected());
        Assert.assertSame(integers.comparator(), partition2.getSelected().comparator());
        Assert.assertSame(integers.comparator(), partition2.getRejected().comparator());
    }

    @Test
    public void takeWhile()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        ImmutableSortedBag<Integer> take1 = integers.takeWhile(Predicates.greaterThan(integers.size()));
        Verify.assertIterableEmpty(take1);
        Assert.assertSame(integers.comparator(), take1.comparator());

        ImmutableSortedBag<Integer> take2 = integers.takeWhile(Predicates.lessThanOrEqualTo(integers.size()));
        Assert.assertEquals(integers, take2);
        Assert.assertSame(integers.comparator(), take2.comparator());
    }

    @Test
    public void dropWhile()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        ImmutableSortedBag<Integer> drop1 = integers.dropWhile(Predicates.greaterThan(integers.size()));
        Assert.assertEquals(integers, drop1);
        Assert.assertEquals(Collections.<Integer>reverseOrder(), drop1.comparator());

        ImmutableSortedBag<Integer> drop2 = integers.dropWhile(Predicates.lessThanOrEqualTo(integers.size()));
        Verify.assertIterableEmpty(drop2);
        Assert.assertEquals(Collections.<Integer>reverseOrder(), drop2.comparator());
    }

    @Override
    @Test
    public void collect()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        Verify.assertListsEqual(integers.toList(), integers.collect(Functions.getIntegerPassThru()).castToList());
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithIndex()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        MutableList<ObjectIntPair<Integer>> expected = Lists.mutable.with(
                PrimitiveTuples.pair(Integer.valueOf(2), 0),
                PrimitiveTuples.pair(Integer.valueOf(1), 1),
                PrimitiveTuples.pair(Integer.valueOf(1), 2),
                PrimitiveTuples.pair(Integer.valueOf(1), 3));
        ImmutableList<ObjectIntPair<Integer>> actual = integers.collectWithIndex(PrimitiveTuples::pair);
        Assert.assertEquals(expected, actual);
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithIndexWithTarget()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        MutableList<ObjectIntPair<Integer>> expected = Lists.mutable.with(
                PrimitiveTuples.pair(Integer.valueOf(2), 0),
                PrimitiveTuples.pair(Integer.valueOf(1), 1),
                PrimitiveTuples.pair(Integer.valueOf(1), 2),
                PrimitiveTuples.pair(Integer.valueOf(1), 3));
        MutableList<ObjectIntPair<Integer>> actual =
                integers.collectWithIndex(PrimitiveTuples::pair, Lists.mutable.empty());
        Assert.assertEquals(expected, actual);
    }

    @Override
    @Test
    public void collectWith()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        Verify.assertListsEqual(integers.toList(), integers.collectWith((value, parameter) -> value / parameter, 1).castToList());
    }

    @Test
    public void collectToTarget()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Assert.assertEquals(HashBag.newBag(integers), integers.collect(Functions.getIntegerPassThru(), HashBag.newBag()));
        Verify.assertListsEqual(integers.toList(),
                integers.collect(Functions.getIntegerPassThru(), FastList.newList()));
    }

    @Override
    @Test
    public void flatCollect()
    {
        ImmutableList<String> actual = this.classUnderTest(Collections.reverseOrder()).flatCollect(integer -> Lists.fixedSize.of(String.valueOf(integer)));
        ImmutableList<String> expected = this.classUnderTest(Collections.reverseOrder()).collect(String::valueOf);
        Assert.assertEquals(expected, actual);
        Verify.assertListsEqual(expected.toList(), actual.toList());
    }

    @Test
    public void flatCollectWithTarget()
    {
        MutableBag<String> actual = this.classUnderTest().flatCollect(integer -> Lists.fixedSize.of(String.valueOf(integer)), HashBag.newBag());

        ImmutableList<String> expected = this.classUnderTest().collect(String::valueOf);
        Assert.assertEquals(expected.toBag(), actual);
    }

    @Test
    public void zip()
    {
        ImmutableSortedBag<Integer> immutableBag = this.classUnderTest(Collections.reverseOrder());
        List<Object> nulls = Collections.nCopies(immutableBag.size(), null);
        List<Object> nullsPlusOne = Collections.nCopies(immutableBag.size() + 1, null);
        List<Object> nullsMinusOne = Collections.nCopies(immutableBag.size() - 1, null);

        ImmutableList<Pair<Integer, Object>> pairs = immutableBag.zip(nulls);
        Assert.assertEquals(immutableBag.toList(), pairs.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne));
        Verify.assertListsEqual(FastList.newListWith(2, 1, 1, 1), pairs.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne).toList());
        Assert.assertEquals(FastList.newList(nulls), pairs.collect((Function<Pair<?, Object>, Object>) Pair::getTwo));

        ImmutableList<Pair<Integer, Object>> pairsPlusOne = immutableBag.zip(nullsPlusOne);
        Assert.assertEquals(immutableBag.toList(), pairsPlusOne.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne));
        Verify.assertListsEqual(FastList.newListWith(2, 1, 1, 1),
                pairsPlusOne.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne).castToList());
        Assert.assertEquals(FastList.newList(nulls), pairsPlusOne.collect((Function<Pair<?, Object>, Object>) Pair::getTwo));

        ImmutableList<Pair<Integer, Object>> pairsMinusOne = immutableBag.zip(nullsMinusOne);
        Verify.assertListsEqual(FastList.newListWith(2, 1, 1),
                pairsMinusOne.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne).castToList());
        Assert.assertEquals(immutableBag.zip(nulls), immutableBag.zip(nulls, FastList.newList()));
        Assert.assertEquals(immutableBag.zip(nulls).toBag(), immutableBag.zip(nulls, new HashBag<>()));

        FastList<Holder> holders = FastList.newListWith(new Holder(1), new Holder(2), new Holder(3));
        ImmutableList<Pair<Integer, Holder>> zipped = immutableBag.zip(holders);
        Verify.assertSize(3, zipped.castToList());
        AbstractImmutableSortedBagTestCase.Holder two = new Holder(-1);
        AbstractImmutableSortedBagTestCase.Holder two1 = new Holder(-1);
        Assert.assertEquals(Tuples.pair(10, two1), zipped.newWith(Tuples.pair(10, two)).getLast());
        Assert.assertEquals(Tuples.pair(1, new Holder(3)), this.classUnderTest().zip(holders.reverseThis()).getFirst());
    }

    @Test
    public void zipWithIndex()
    {
        ImmutableSortedBag<Integer> integers = SortedBags.immutable.of(Collections.reverseOrder(), 1, 3, 5, 5, 5, 2, 4);
        ImmutableSortedSet<Pair<Integer, Integer>> expected = TreeSortedSet.newSetWith(
                Tuples.pair(5, 0),
                Tuples.pair(5, 1),
                Tuples.pair(5, 2),
                Tuples.pair(4, 3),
                Tuples.pair(3, 4),
                Tuples.pair(2, 5),
                Tuples.pair(1, 6)).toImmutable();
        ImmutableSortedSet<Pair<Integer, Integer>> actual = integers.zipWithIndex();
        Assert.assertEquals(expected, actual);

        ImmutableSortedBag<Integer> integersNoComparator = SortedBags.immutable.of(1, 3, 5, 5, 5, 2, 4);
        ImmutableSortedSet<Pair<Integer, Integer>> expected2 = TreeSortedSet.newSetWith(
                Tuples.pair(1, 0),
                Tuples.pair(2, 1),
                Tuples.pair(3, 2),
                Tuples.pair(4, 3),
                Tuples.pair(5, 4),
                Tuples.pair(5, 5),
                Tuples.pair(5, 6)).toImmutable();
        ImmutableSortedSet<Pair<Integer, Integer>> actual2 = integersNoComparator.zipWithIndex();
        Assert.assertEquals(expected2, actual2);
    }

    @Override
    @Test(expected = IllegalArgumentException.class)
    public void chunk_zero_throws()
    {
        this.classUnderTest().chunk(0);
    }

    @Test
    public void chunk_large_size()
    {
        Assert.assertEquals(this.classUnderTest(), this.classUnderTest().chunk(10).getFirst());
        Verify.assertInstanceOf(ImmutableSortedBag.class, this.classUnderTest().chunk(10).getFirst());
    }

    @Override
    @Test
    public void detect()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Assert.assertEquals(Integer.valueOf(1), integers.detect(Predicates.equal(1)));
        Assert.assertNull(integers.detect(Predicates.equal(integers.size() + 1)));
    }

    @Override
    @Test
    public void detectWith()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Assert.assertEquals(Integer.valueOf(1), integers.detectWith(Object::equals, Integer.valueOf(1)));
        Assert.assertNull(integers.detectWith(Object::equals, Integer.valueOf(integers.size() + 1)));
    }

    @Override
    @Test
    public void detectWithIfNone()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Function0<Integer> function = new PassThruFunction0<>(integers.size() + 1);
        Integer sum = Integer.valueOf(integers.size() + 1);
        Assert.assertEquals(Integer.valueOf(1), integers.detectWithIfNone(Object::equals, Integer.valueOf(1), function));
        Assert.assertEquals(Integer.valueOf(integers.size() + 1), integers.detectWithIfNone(Object::equals, sum, function));
    }

    @Override
    @Test
    public void detectIfNone()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Function0<Integer> function = new PassThruFunction0<>(integers.size() + 1);
        Assert.assertEquals(Integer.valueOf(1), integers.detectIfNone(Predicates.equal(1), function));
        Assert.assertEquals(Integer.valueOf(integers.size() + 1), integers.detectIfNone(Predicates.equal(integers.size() + 1), function));
    }

    @Override
    @Test
    public void allSatisfy()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Assert.assertTrue(integers.allSatisfy(Integer.class::isInstance));
        Assert.assertFalse(integers.allSatisfy(Integer.valueOf(0)::equals));
    }

    @Override
    @Test
    public void anySatisfy()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Assert.assertFalse(integers.anySatisfy(String.class::isInstance));
        Assert.assertTrue(integers.anySatisfy(Integer.class::isInstance));
    }

    @Override
    @Test
    public void count()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Assert.assertEquals(integers.size(), integers.count(Integer.class::isInstance));
        Assert.assertEquals(0, integers.count(String.class::isInstance));
    }

    @Override
    @Test
    public void collectIf()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        ImmutableList<Integer> integers1 = integers.collectIf(Integer.class::isInstance, Functions.getIntegerPassThru());
        Verify.assertListsEqual(integers.toList(), integers1.toList());
    }

    @Test
    public void collectIfToTarget()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        HashBag<Integer> actual = integers.collectIf(Integer.class::isInstance, Functions.getIntegerPassThru(), HashBag.newBag());
        Assert.assertEquals(integers.toBag(), actual);
    }

    @Override
    @Test
    public void getFirst()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Assert.assertEquals(Integer.valueOf(1), integers.getFirst());
        ImmutableSortedBag<Integer> revInt = this.classUnderTest(Collections.reverseOrder());
        Assert.assertEquals(Integer.valueOf(2), revInt.getFirst());
    }

    @Override
    @Test
    public void getLast()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Assert.assertEquals(Integer.valueOf(2), integers.getLast());
        ImmutableSortedBag<Integer> revInt = this.classUnderTest(Collections.reverseOrder());
        Assert.assertEquals(Integer.valueOf(1), revInt.getLast());
    }

    @Override
    @Test
    public void isEmpty()
    {
        ImmutableSortedBag<Integer> bag = this.classUnderTest();
        Assert.assertFalse(bag.isEmpty());
        Assert.assertTrue(bag.notEmpty());
    }

    @Override
    @Test
    public void iterator()
    {
        ImmutableSortedBag<Integer> integers = SortedBags.immutable.of(1, 2, 3, 4);
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; iterator.hasNext(); i++)
        {
            Integer integer = iterator.next();
            Assert.assertEquals(i + 1, integer.intValue());
        }
        Verify.assertThrows(NoSuchElementException.class, (Runnable) iterator::next);
        Iterator<Integer> intItr = integers.iterator();
        intItr.next();
        Verify.assertThrows(UnsupportedOperationException.class, intItr::remove);
    }

    @Override
    @Test
    public void injectInto()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        Integer result = integers.injectInto(0, AddFunction.INTEGER);
        Assert.assertEquals(FastList.newList(integers).injectInto(0, AddFunction.INTEGER_TO_INT), result.intValue());
    }

    @Override
    @Test
    public void toArray()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        MutableList<Integer> copy = FastList.newList(integers);
        Assert.assertArrayEquals(integers.toArray(), copy.toArray());
        Assert.assertArrayEquals(integers.toArray(new Integer[integers.size()]), copy.toArray(new Integer[integers.size()]));
        Assert.assertArrayEquals(integers.toArray(new Integer[integers.size() - 1]), copy.toArray(new Integer[integers.size() - 1]));
        Assert.assertArrayEquals(integers.toArray(new Integer[integers.size() + 1]), copy.toArray(new Integer[integers.size() + 1]));
    }

    @Override
    @Test
    public void testToString()
    {
        Assert.assertEquals(FastList.newList(this.classUnderTest()).toString(), this.classUnderTest().toString());
    }

    @Override
    @Test
    public void makeString()
    {
        Assert.assertEquals(FastList.newList(this.classUnderTest()).makeString(), this.classUnderTest().makeString());
    }

    @Override
    @Test
    public void appendString()
    {
        Appendable builder = new StringBuilder();
        this.classUnderTest().appendString(builder);
        Assert.assertEquals(FastList.newList(this.classUnderTest()).makeString(), builder.toString());
    }

    @Test
    public void toList()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        MutableList<Integer> list = integers.toList();
        Verify.assertEqualsAndHashCode(FastList.newList(integers), list);
    }

    @Override
    @Test
    public void toSortedList()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        MutableList<Integer> copy = FastList.newList(integers);
        MutableList<Integer> list = integers.toSortedList(Collections.reverseOrder());
        Assert.assertEquals(copy.sortThis(Collections.reverseOrder()), list);
        MutableList<Integer> list2 = integers.toSortedList();
        Verify.assertListsEqual(copy.sortThis(), list2);
    }

    @Test
    public void toSortedListBy()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        MutableList<Integer> list = integers.toSortedListBy(String::valueOf);
        Assert.assertEquals(integers.toList(), list);
    }

    @Test
    public void toSortedBag()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Collections.reverseOrder());
        MutableSortedBag<Integer> bag = integers.toSortedBag();
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(1, 1, 1, 2), bag);
    }

    @Test
    public void toSortedBagWithComparator()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        MutableSortedBag<Integer> bag = integers.toSortedBag(Collections.reverseOrder());
        Assert.assertEquals(integers.toBag(), bag);
        Assert.assertEquals(integers.toSortedList(Comparators.reverseNaturalOrder()), bag.toList());
    }

    @Test
    public void toSortedBagBy()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        MutableSortedBag<Integer> bag = integers.toSortedBagBy(String::valueOf);
        Verify.assertSortedBagsEqual(TreeBag.newBag(integers), bag);
    }

    @Test
    public void toSortedMap()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        MutableSortedMap<Integer, String> map = integers.toSortedMap(Functions.getIntegerPassThru(), String::valueOf);
        Verify.assertMapsEqual(integers.toMap(Functions.getIntegerPassThru(), String::valueOf), map);
        Verify.assertListsEqual(FastList.newListWith(1, 2), map.keySet().toList());
    }

    @Test
    public void toSortedMap_with_comparator()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        MutableSortedMap<Integer, String> map = integers.toSortedMap(Comparators.reverseNaturalOrder(),
                Functions.getIntegerPassThru(), String::valueOf);
        Verify.assertMapsEqual(integers.toMap(Functions.getIntegerPassThru(), String::valueOf), map);
        Verify.assertListsEqual(FastList.newListWith(2, 1), map.keySet().toList());
    }

    @Test
    public void toSortedMapBy()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest();
        MutableSortedMap<Integer, String> map = integers.toSortedMapBy(key -> -key,
                Functions.getIntegerPassThru(), String::valueOf);
        Verify.assertMapsEqual(integers.toMap(Functions.getIntegerPassThru(), String::valueOf), map);
        Verify.assertListsEqual(FastList.newListWith(2, 1), map.keySet().toList());
    }

    @Override
    @Test
    public void forLoop()
    {
        ImmutableSortedBag<Integer> bag = this.classUnderTest();
        for (Integer each : bag)
        {
            Assert.assertNotNull(each);
        }
    }

    @Test
    public void toMapOfItemToCount()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Collections.reverseOrder(), 1, 2, 2, 3, 3, 3);
        MutableSortedMap<Integer, Integer> expected = TreeSortedMap.newMapWith(Collections.reverseOrder(), 1, 1, 2, 2, 3, 3);
        MutableSortedMap<Integer, Integer> actual = bag.toMapOfItemToCount();
        Assert.assertEquals(expected, actual);
        Assert.assertSame(bag.comparator(), actual.comparator());
    }

    @Override
    @Test
    public void min()
    {
        Assert.assertEquals(Integer.valueOf(2), this.classUnderTest().min(Comparators.reverseNaturalOrder()));
    }

    @Override
    @Test
    public void max()
    {
        Assert.assertEquals(Integer.valueOf(1), this.classUnderTest().max(Comparators.reverseNaturalOrder()));
    }

    @Override
    @Test
    public void min_without_comparator()
    {
        Assert.assertEquals(Integer.valueOf(1), this.classUnderTest().min());
    }

    @Override
    @Test
    public void max_without_comparator()
    {
        Assert.assertEquals(Integer.valueOf(2), this.classUnderTest().max());
    }

    @Override
    @Test
    public void minBy()
    {
        Assert.assertEquals(Integer.valueOf(1), this.classUnderTest().minBy(String::valueOf));
        Assert.assertEquals(Integer.valueOf(1), this.classUnderTest(Comparator.reverseOrder()).minBy(String::valueOf));
    }

    @Override
    @Test
    public void maxBy()
    {
        Assert.assertEquals(Integer.valueOf(2), this.classUnderTest().maxBy(String::valueOf));
    }

    @Test
    public void groupBy()
    {
        ImmutableSortedBag<Integer> undertest = this.classUnderTest(Comparators.reverseNaturalOrder());
        ImmutableSortedBagMultimap<Integer, Integer> actual = undertest.groupBy(Functions.getPassThru());
        ImmutableSortedBagMultimap<Integer, Integer> expected = TreeBag.newBag(undertest).groupBy(Functions.getPassThru()).toImmutable();
        Assert.assertEquals(expected, actual);
        Assert.assertSame(Comparators.reverseNaturalOrder(), actual.comparator());
    }

    @Test
    public void groupByEach()
    {
        ImmutableSortedBag<Integer> undertest = this.classUnderTest(Collections.reverseOrder());
        NegativeIntervalFunction function = new NegativeIntervalFunction();
        ImmutableSortedBagMultimap<Integer, Integer> actual = undertest.groupByEach(function);
        ImmutableSortedBagMultimap<Integer, Integer> expected = TreeBag.newBag(undertest).groupByEach(function).toImmutable();
        Assert.assertEquals(expected, actual);
        Assert.assertSame(Collections.reverseOrder(), actual.comparator());
    }

    @Test
    public void groupByWithTarget()
    {
        ImmutableSortedBag<Integer> undertest = this.classUnderTest(Comparators.reverseNaturalOrder());
        TreeBagMultimap<Integer, Integer> actual = undertest.groupBy(Functions.getPassThru(), TreeBagMultimap.newMultimap());
        TreeBagMultimap<Integer, Integer> expected = TreeBag.newBag(undertest).groupBy(Functions.getPassThru());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void groupByEachWithTarget()
    {
        ImmutableSortedBag<Integer> undertest = this.classUnderTest();
        NegativeIntervalFunction function = new NegativeIntervalFunction();
        TreeBagMultimap<Integer, Integer> actual = undertest.groupByEach(function, TreeBagMultimap.newMultimap());
        TreeBagMultimap<Integer, Integer> expected = TreeBag.newBag(undertest).groupByEach(function);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void groupByUniqueKey()
    {
        ImmutableSortedBag<Integer> bag1 = this.newWith(1, 2, 3);
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 1, 2, 2, 3, 3), bag1.groupByUniqueKey(id -> id));

        ImmutableSortedBag<Integer> bag2 = this.classUnderTest(Comparators.reverseNaturalOrder());
        Verify.assertThrows(IllegalStateException.class, () -> bag2.groupByUniqueKey(id -> id));
    }

    @Test
    public void groupByUniqueKey_target()
    {
        ImmutableSortedBag<Integer> bag1 = this.newWith(1, 2, 3);
        Assert.assertEquals(
                UnifiedMap.newWithKeysValues(0, 0, 1, 1, 2, 2, 3, 3),
                bag1.groupByUniqueKey(id -> id, UnifiedMap.newWithKeysValues(0, 0)));

        ImmutableSortedBag<Integer> bag2 = this.newWith(1, 2, 3);
        Verify.assertThrows(IllegalStateException.class, () -> bag2.groupByUniqueKey(id -> id, UnifiedMap.newWithKeysValues(2, 2)));
    }

    @Test
    public void distinct()
    {
        ImmutableSortedBag<Integer> bag1 = this.classUnderTest();
        Assert.assertEquals(SortedSets.immutable.of(1, 2), bag1.distinct());
        ImmutableSortedBag<Integer> bag2 = this.classUnderTest(Comparators.reverseNaturalOrder());
        ImmutableSortedSet<Integer> expected = SortedSets.immutable.of(Comparators.reverseNaturalOrder(), 1, 2);
        ImmutableSortedSet<Integer> actual = bag2.distinct();
        Assert.assertEquals(expected, actual);
        Assert.assertSame(expected.comparator(), actual.comparator());
    }

    @Test
    public void toStack()
    {
        ImmutableSortedBag<Integer> bag = this.classUnderTest(Comparators.reverseNaturalOrder());
        Assert.assertEquals(ArrayStack.newStackWith(2, 1, 1, 1), bag.toStack());
    }

    @Override
    @Test
    public void collectBoolean()
    {
        ImmutableSortedBag<String> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), "true", "nah", "TrUe");
        Assert.assertEquals(
                BooleanArrayList.newListWith(true, false, true),
                bag.collectBoolean(Boolean::parseBoolean));
    }

    @Override
    @Test
    public void collectByte()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                ByteArrayList.newListWith((byte) 3, (byte) 2, (byte) 1, (byte) 1, (byte) 1),
                bag.collectByte(PrimitiveFunctions.unboxIntegerToByte()));
    }

    @Override
    @Test
    public void collectChar()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                CharArrayList.newListWith((char) 3, (char) 2, (char) 1, (char) 1, (char) 1),
                bag.collectChar(PrimitiveFunctions.unboxIntegerToChar()));
    }

    @Override
    @Test
    public void collectDouble()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                DoubleArrayList.newListWith(3, 2, 1, 1, 1),
                bag.collectDouble(PrimitiveFunctions.unboxIntegerToDouble()));
    }

    @Override
    @Test
    public void collectFloat()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                FloatArrayList.newListWith(3, 2, 1, 1, 1),
                bag.collectFloat(PrimitiveFunctions.unboxIntegerToFloat()));
    }

    @Override
    @Test
    public void collectInt()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                IntArrayList.newListWith(3, 2, 1, 1, 1),
                bag.collectInt(PrimitiveFunctions.unboxIntegerToInt()));
    }

    @Override
    @Test
    public void collectLong()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                LongArrayList.newListWith(3, 2, 1, 1, 1),
                bag.collectLong(PrimitiveFunctions.unboxIntegerToLong()));
    }

    @Override
    @Test
    public void collectShort()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                ShortArrayList.newListWith((short) 3, (short) 2, (short) 1, (short) 1, (short) 1),
                bag.collectShort(PrimitiveFunctions.unboxIntegerToShort()));
    }

    @Test
    public void collectBoolean_target()
    {
        ImmutableSortedBag<String> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), "true", "nah", "TrUe");
        Assert.assertEquals(
                BooleanArrayList.newListWith(true, false, true),
                bag.collectBoolean(Boolean::parseBoolean, new BooleanArrayList()));

        ImmutableSortedBag<String> bag2 = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), "true", "nah", "TrUe");
        Assert.assertEquals(
                BooleanHashBag.newBagWith(true, false, true),
                bag2.collectBoolean(Boolean::parseBoolean, new BooleanHashBag()));
    }

    @Test
    public void collectByte_target()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                ByteArrayList.newListWith((byte) 3, (byte) 2, (byte) 1, (byte) 1, (byte) 1),
                bag.collectByte(PrimitiveFunctions.unboxIntegerToByte(), new ByteArrayList()));

        ImmutableSortedBag<Integer> bag2 = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                ByteHashBag.newBagWith((byte) 3, (byte) 2, (byte) 1, (byte) 1, (byte) 1),
                bag2.collectByte(PrimitiveFunctions.unboxIntegerToByte(), new ByteHashBag()));
    }

    @Test
    public void collectChar_target()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                CharArrayList.newListWith((char) 3, (char) 2, (char) 1, (char) 1, (char) 1),
                bag.collectChar(PrimitiveFunctions.unboxIntegerToChar(), new CharArrayList()));

        ImmutableSortedBag<Integer> bag2 = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                CharHashBag.newBagWith((char) 3, (char) 2, (char) 1, (char) 1, (char) 1),
                bag2.collectChar(PrimitiveFunctions.unboxIntegerToChar(), new CharHashBag()));
    }

    @Test
    public void collectDouble_target()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                DoubleArrayList.newListWith(3, 2, 1, 1, 1),
                bag.collectDouble(PrimitiveFunctions.unboxIntegerToDouble(), new DoubleArrayList()));

        ImmutableSortedBag<Integer> bag2 = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                DoubleHashBag.newBagWith(3, 2, 1, 1, 1),
                bag2.collectDouble(PrimitiveFunctions.unboxIntegerToDouble(), new DoubleHashBag()));
    }

    @Test
    public void collectFloat_target()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                FloatArrayList.newListWith(3, 2, 1, 1, 1),
                bag.collectFloat(PrimitiveFunctions.unboxIntegerToFloat(), new FloatArrayList()));

        ImmutableSortedBag<Integer> bag2 = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                FloatHashBag.newBagWith(3, 2, 1, 1, 1),
                bag2.collectFloat(PrimitiveFunctions.unboxIntegerToFloat(), new FloatHashBag()));
    }

    @Test
    public void collectInt_target()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                IntArrayList.newListWith(3, 2, 1, 1, 1),
                bag.collectInt(PrimitiveFunctions.unboxIntegerToInt(), new IntArrayList()));

        ImmutableSortedBag<Integer> bag2 = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                IntHashBag.newBagWith(3, 2, 1, 1, 1),
                bag2.collectInt(PrimitiveFunctions.unboxIntegerToInt(), new IntHashBag()));
    }

    @Test
    public void collectLong_target()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                LongArrayList.newListWith(3, 2, 1, 1, 1),
                bag.collectLong(PrimitiveFunctions.unboxIntegerToLong(), new LongArrayList()));

        ImmutableSortedBag<Integer> bag2 = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                LongHashBag.newBagWith(3, 2, 1, 1, 1),
                bag2.collectLong(PrimitiveFunctions.unboxIntegerToLong(), new LongHashBag()));
    }

    @Test
    public void collectShort_target()
    {
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                ShortArrayList.newListWith((short) 3, (short) 2, (short) 1, (short) 1, (short) 1),
                bag.collectShort(PrimitiveFunctions.unboxIntegerToShort(), new ShortArrayList()));

        ImmutableSortedBag<Integer> bag2 = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 1, 2, 3);
        Assert.assertEquals(
                ShortHashBag.newBagWith((short) 3, (short) 2, (short) 1, (short) 1, (short) 1),
                bag2.collectShort(PrimitiveFunctions.unboxIntegerToShort(), new ShortHashBag()));
    }

    @Test
    public void occurrencesOf()
    {
        ImmutableSortedBag<Integer> bag = this.classUnderTest();
        Assert.assertEquals(0, bag.occurrencesOf(5));
        Assert.assertEquals(3, bag.occurrencesOf(1));
        Assert.assertEquals(1, bag.occurrencesOf(2));
    }

    @Test
    public void toImmutable()
    {
        ImmutableSortedBag<Integer> bag = this.classUnderTest();
        ImmutableSortedBag<Integer> actual = bag.toImmutable();
        Assert.assertEquals(bag, actual);
        Assert.assertSame(bag, actual);
    }

    @Test
    public void forEachFromTo()
    {
        MutableSortedBag<Integer> integersMutable = SortedBags.mutable.of(Comparators.reverseNaturalOrder(), 4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
        ImmutableSortedBag<Integer> integers1 = integersMutable.toImmutable();

        MutableList<Integer> result = Lists.mutable.empty();
        integers1.forEach(5, 7, result::add);
        Assert.assertEquals(Lists.immutable.with(3, 3, 2), result);

        MutableList<Integer> result2 = Lists.mutable.empty();
        integers1.forEach(5, 5, result2::add);
        Assert.assertEquals(Lists.immutable.with(3), result2);

        MutableList<Integer> result3 = Lists.mutable.empty();
        integers1.forEach(0, 9, result3::add);
        Assert.assertEquals(Lists.immutable.with(4, 4, 4, 4, 3, 3, 3, 2, 2, 1), result3);

        Verify.assertThrows(IndexOutOfBoundsException.class, () -> integers1.forEach(-1, 0, result::add));
        Verify.assertThrows(IndexOutOfBoundsException.class, () -> integers1.forEach(0, -1, result::add));
        Verify.assertThrows(IllegalArgumentException.class, () -> integers1.forEach(7, 5, result::add));

        ImmutableSortedBag<Integer> integers2 = this.classUnderTest();
        MutableList<Integer> mutableList = Lists.mutable.of();
        integers2.forEach(0, integers2.size() - 1, mutableList::add);
        Assert.assertEquals(this.classUnderTest().toList(), mutableList);
    }

    @Test
    public void forEachWithIndexWithFromTo()
    {
        ImmutableSortedBag<Integer> integers1 = SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
        StringBuilder builder = new StringBuilder();
        integers1.forEachWithIndex(5, 7, (each, index) -> builder.append(each).append(index));
        Assert.assertEquals("353627", builder.toString());

        StringBuilder builder2 = new StringBuilder();
        integers1.forEachWithIndex(5, 5, (each, index) -> builder2.append(each).append(index));
        Assert.assertEquals("35", builder2.toString());

        StringBuilder builder3 = new StringBuilder();
        integers1.forEachWithIndex(0, 9, (each, index) -> builder3.append(each).append(index));
        Assert.assertEquals("40414243343536272819", builder3.toString());

        Verify.assertThrows(IndexOutOfBoundsException.class, () -> integers1.forEachWithIndex(-1, 0, new AddToList(Lists.mutable.empty())));
        Verify.assertThrows(IndexOutOfBoundsException.class, () -> integers1.forEachWithIndex(0, -1, new AddToList(Lists.mutable.empty())));
        Verify.assertThrows(IllegalArgumentException.class, () -> integers1.forEachWithIndex(7, 5, new AddToList(Lists.mutable.empty())));

        ImmutableSortedBag<Integer> integers2 = this.classUnderTest();
        MutableList<Integer> mutableList1 = Lists.mutable.of();
        integers2.forEachWithIndex(0, integers2.size() - 1, (each, index) -> mutableList1.add(each + index));
        MutableList<Integer> result = Lists.mutable.of();
        Lists.mutable.ofAll(integers2).forEachWithIndex(0, integers2.size() - 1, (each, index) -> result.add(each + index));
        Assert.assertEquals(result, mutableList1);
    }

    @Test
    public void topOccurrences()
    {
        ImmutableSortedBag<String> strings = this.newWithOccurrences(
                PrimitiveTuples.pair("one", 1),
                PrimitiveTuples.pair("two", 2),
                PrimitiveTuples.pair("three", 3),
                PrimitiveTuples.pair("four", 4),
                PrimitiveTuples.pair("five", 5),
                PrimitiveTuples.pair("six", 6),
                PrimitiveTuples.pair("seven", 7),
                PrimitiveTuples.pair("eight", 8),
                PrimitiveTuples.pair("nine", 9),
                PrimitiveTuples.pair("ten", 10));
        ListIterable<ObjectIntPair<String>> top5 = strings.topOccurrences(5);
        Assert.assertEquals(5, top5.size());
        Assert.assertEquals("ten", top5.getFirst().getOne());
        Assert.assertEquals(10, top5.getFirst().getTwo());
        Assert.assertEquals("six", top5.getLast().getOne());
        Assert.assertEquals(6, top5.getLast().getTwo());
        Assert.assertEquals(0, this.newWith().topOccurrences(5).size());
        Assert.assertEquals(3, this.newWith("one", "two", "three").topOccurrences(5).size());
        Assert.assertEquals(3, this.newWith("one", "two", "three").topOccurrences(1).size());
        Assert.assertEquals(3, this.newWith("one", "two", "three").topOccurrences(2).size());
        Assert.assertEquals(3, this.newWith("one", "one", "two", "three").topOccurrences(2).size());
        Assert.assertEquals(2, this.newWith("one", "one", "two", "two", "three").topOccurrences(1).size());
        Assert.assertEquals(3, this.newWith("one", "one", "two", "two", "three", "three").topOccurrences(1).size());
        Assert.assertEquals(0, this.newWith().topOccurrences(0).size());
        Assert.assertEquals(0, this.newWith("one").topOccurrences(0).size());
        Verify.assertThrows(IllegalArgumentException.class, () -> this.newWith().topOccurrences(-1));
    }

    @Test
    public void bottomOccurrences()
    {
        ImmutableSortedBag<String> strings = this.newWithOccurrences(
                PrimitiveTuples.pair("one", 1),
                PrimitiveTuples.pair("two", 2),
                PrimitiveTuples.pair("three", 3),
                PrimitiveTuples.pair("four", 4),
                PrimitiveTuples.pair("five", 5),
                PrimitiveTuples.pair("six", 6),
                PrimitiveTuples.pair("seven", 7),
                PrimitiveTuples.pair("eight", 8),
                PrimitiveTuples.pair("nine", 9),
                PrimitiveTuples.pair("ten", 10));
        ListIterable<ObjectIntPair<String>> bottom5 = strings.bottomOccurrences(5);
        Assert.assertEquals(5, bottom5.size());
        Assert.assertEquals("one", bottom5.getFirst().getOne());
        Assert.assertEquals(1, bottom5.getFirst().getTwo());
        Assert.assertEquals("five", bottom5.getLast().getOne());
        Assert.assertEquals(5, bottom5.getLast().getTwo());
        Assert.assertEquals(0, this.newWith().bottomOccurrences(5).size());
        Assert.assertEquals(3, this.newWith("one", "two", "three").topOccurrences(5).size());
        Assert.assertEquals(3, this.newWith("one", "two", "three").topOccurrences(1).size());
        Assert.assertEquals(3, this.newWith("one", "two", "three").topOccurrences(2).size());
        Assert.assertEquals(3, this.newWith("one", "one", "two", "three").topOccurrences(2).size());
        Assert.assertEquals(2, this.newWith("one", "one", "two", "two", "three").topOccurrences(1).size());
        Assert.assertEquals(3, this.newWith("one", "one", "two", "two", "three", "three").bottomOccurrences(1).size());
        Assert.assertEquals(0, this.newWith().bottomOccurrences(0).size());
        Assert.assertEquals(0, this.newWith("one").bottomOccurrences(0).size());
        Verify.assertThrows(IllegalArgumentException.class, () -> this.newWith().bottomOccurrences(-1));
    }

    @Test
    public void corresponds()
    {
        Assert.assertFalse(this.newWith(1, 2, 3, 4, 5).corresponds(this.newWith(1, 2, 3, 4), Predicates2.alwaysTrue()));

        ImmutableSortedBag<Integer> integers1 = this.newWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        MutableList<Integer> integers2 = FastList.newListWith(2, 3, 3, 4, 4, 4, 5, 5, 5, 5);
        Assert.assertTrue(integers1.corresponds(integers2, Predicates2.lessThan()));
        Assert.assertFalse(integers1.corresponds(integers2, Predicates2.greaterThan()));

        ImmutableSortedBag<Integer> integers3 = this.newWith(1, 2, 3, 4);
        MutableSortedSet<Integer> integers4 = SortedSets.mutable.of(2, 3, 4, 5);
        Assert.assertTrue(integers1.corresponds(integers2, Predicates2.lessThan()));
        Assert.assertFalse(integers3.corresponds(integers4, Predicates2.greaterThan()));
        Assert.assertTrue(integers3.corresponds(integers4, Predicates2.lessThan()));
    }

    @Test
    public void detectIndex()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Comparators.reverseNaturalOrder());
        Assert.assertEquals(0, integers.detectIndex(each -> each == 2));

        ImmutableSortedBag<Integer> integers2 = this.classUnderTest();
        Assert.assertEquals(-1, integers2.detectIndex(each -> each == 100));
    }

    @Test
    public void indexOf()
    {
        ImmutableSortedBag<Integer> integers = this.classUnderTest(Comparators.reverseNaturalOrder());
        Assert.assertEquals(1, integers.indexOf(1));
        Assert.assertEquals(1, integers.indexOf(1));
        Assert.assertEquals(0, integers.indexOf(2));
        Assert.assertEquals(-1, integers.indexOf(0));
        Assert.assertEquals(-1, integers.indexOf(5));
    }

    @Test
    public void take()
    {
        ImmutableSortedBag<Integer> integers1 = this.classUnderTest();
        Assert.assertEquals(SortedBags.immutable.empty(integers1.comparator()), integers1.take(0));
        Assert.assertSame(integers1.comparator(), integers1.take(0).comparator());
        Assert.assertEquals(this.newWith(integers1.comparator(), 1, 1, 1), integers1.take(3));
        Assert.assertSame(integers1.comparator(), integers1.take(3).comparator());
        Assert.assertEquals(this.newWith(integers1.comparator(), 1, 1, 1), integers1.take(integers1.size() - 1));

        ImmutableSortedBag<Integer> integers2 = this.newWith(Comparators.reverseNaturalOrder(), 3, 3, 3, 2, 2, 1);
        Assert.assertSame(integers2, integers2.take(integers2.size()));
        Assert.assertSame(integers2, integers2.take(10));
        Assert.assertSame(integers2, integers2.take(Integer.MAX_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void take_throws()
    {
        this.classUnderTest().take(-1);
    }

    @Test
    public void drop()
    {
        ImmutableSortedBag<Integer> integers1 = this.classUnderTest();
        Assert.assertSame(integers1, integers1.drop(0));
        Assert.assertEquals(this.newWith(integers1.comparator(), 2), integers1.drop(3));
        Assert.assertEquals(this.newWith(integers1.comparator(), 2), integers1.drop(integers1.size() - 1));
        Assert.assertEquals(SortedBags.immutable.empty(integers1.comparator()), integers1.drop(integers1.size()));
        Assert.assertEquals(SortedBags.immutable.empty(integers1.comparator()), integers1.drop(10));
        Assert.assertEquals(SortedBags.immutable.empty(integers1.comparator()), integers1.drop(Integer.MAX_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void drop_throws()
    {
        this.classUnderTest().drop(-1);
    }

    @Test
    public void selectUnique()
    {
        Comparator<Integer> comparator = Collections.reverseOrder();
        ImmutableSortedBag<Integer> bag = SortedBags.immutable.with(comparator, 5, 4, 3, 3, 2, 2, 2, 1, 1, 1, 1, 0);
        ImmutableSortedSet<Integer> expected = SortedSets.immutable.with(comparator, 5, 4, 0);
        ImmutableSortedSet<Integer> actual = bag.selectUnique();
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected.comparator(), actual.comparator());
    }

    private static final class Holder
    {
        private final int number;

        private Holder(int i)
        {
            this.number = i;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || this.getClass() != o.getClass())
            {
                return false;
            }

            Holder holder = (Holder) o;

            return this.number == holder.number;
        }

        @Override
        public int hashCode()
        {
            return this.number;
        }

        @Override
        public String toString()
        {
            return String.valueOf(this.number);
        }
    }
}
