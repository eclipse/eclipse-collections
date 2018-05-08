/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.ImmutableBagIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.MutableBagIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.partition.PartitionMutableCollection;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.collection.mutable.AbstractCollectionTestCase;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Iterables;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

public abstract class MutableBagTestCase extends AbstractCollectionTestCase
{
    @Override
    protected abstract <T> MutableBagIterable<T> newWith(T... littleElements);

    protected abstract <T> MutableBagIterable<T> newWithOccurrences(ObjectIntPair<T>... elementsWithOccurrences);

    @Test
    @Override
    public void equalsAndHashCode()
    {
        super.equalsAndHashCode();
        Assert.assertNotEquals(this.newWith(1, 1, 2, 3), this.newWith(1, 2, 2, 3));
        Verify.assertEqualsAndHashCode(this.newWith(null, null, 2, 3), this.newWith(null, 2, null, 3));
        Assert.assertEquals(this.newWith(1, 1, 2, 3).toMapOfItemToCount().hashCode(), this.newWith(1, 1, 2, 3).hashCode());
        Assert.assertEquals(this.newWith(null, null, 2, 3).toMapOfItemToCount().hashCode(), this.newWith(null, null, 2, 3).hashCode());
    }

    @Test
    public void toStringOfItemToCount()
    {
        Assert.assertEquals("{}", this.newWith().toStringOfItemToCount());
        Assert.assertEquals("{1=3}", this.newWith(1, 1, 1).toStringOfItemToCount());
        String actual = this.newWith(1, 2, 2).toStringOfItemToCount();
        Assert.assertTrue("{1=1, 2=2}".equals(actual) || "{2=2, 1=1}".equals(actual));
    }

    @Test
    public void toMapOfItemToCount()
    {
        MutableBagIterable<Integer> bag = this.newWith(1, 2, 2, 3, 3, 3);
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 1, 2, 2, 3, 3), bag.toMapOfItemToCount());
    }

    @Test
    public void add()
    {
        MutableBagIterable<Integer> bag = this.newWith();
        bag.add(1);
        bag.add(1);
        Verify.assertSize(2, bag);
        bag.add(1);
        Verify.assertSize(3, bag);
    }

    @Override
    @Test
    public void iterator()
    {
        MutableBagIterable<Integer> bag = this.newWith(1, 1, 2);
        MutableList<Integer> validate = Lists.mutable.of();
        for (Integer each : bag)
        {
            validate.add(each);
        }
        Assert.assertEquals(HashBag.newBagWith(1, 1, 2), HashBag.newBag(validate));

        Iterator<Integer> iterator = bag.iterator();
        MutableBagIterable<Integer> expected = this.newWith(1, 1, 2);
        Verify.assertThrows(IllegalStateException.class, iterator::remove);

        this.assertIteratorRemove(bag, iterator, expected);
        this.assertIteratorRemove(bag, iterator, expected);
        this.assertIteratorRemove(bag, iterator, expected);
        Verify.assertEmpty(bag);
        Assert.assertFalse(iterator.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) iterator::next);
    }

    private void assertIteratorRemove(MutableBagIterable<Integer> bag, Iterator<Integer> iterator, MutableBagIterable<Integer> expected)
    {
        Assert.assertTrue(iterator.hasNext());
        Integer first = iterator.next();
        iterator.remove();
        expected.remove(first);
        Assert.assertEquals(expected, bag);
        Verify.assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void iteratorRemove()
    {
        MutableBagIterable<Integer> bag = this.newWith(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4);
        Iterator<Integer> iterator = bag.iterator();
        iterator.next();
        iterator.next();
        Integer value = iterator.next();
        Integer value2 = iterator.next();
        Assert.assertNotEquals(value, value2);
        iterator.remove();
        Integer value3 = iterator.next();
        Assert.assertNotEquals(value, value3);
        iterator.remove();
        Integer value4 = iterator.next();
        Assert.assertNotEquals(value, value4);
        iterator.remove();
        Integer value5 = iterator.next();
        Assert.assertNotEquals(value, value5);
    }

    @Test
    public void iteratorRemove2()
    {
        MutableBagIterable<Integer> bag = this.newWith(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4);
        Iterator<Integer> iterator = bag.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.next();
        iterator.remove();
        Assert.assertEquals(4, bag.sizeDistinct());
        Assert.assertEquals(8, bag.size());
    }

    @Override
    @Test
    public void removeIf()
    {
        super.removeIf();

        MutableBagIterable<Integer> objects = this.newWith(4, 1, 3, 3, 2);
        Assert.assertTrue(objects.removeIf(Predicates.equal(2)));
        Assert.assertEquals(HashBag.newBagWith(1, 3, 3, 4), objects);
        Assert.assertTrue(objects.removeIf(Predicates.equal(3)));
        Assert.assertEquals(HashBag.newBagWith(1, 4), objects);
    }

    @Override
    @Test
    public void forEach()
    {
        MutableBagIterable<Integer> bag = this.newWith(1, 1, 2);
        MutableList<Integer> validate = Lists.mutable.of();
        bag.forEach(CollectionAddProcedure.on(validate));
        Assert.assertEquals(HashBag.newBagWith(1, 1, 2), HashBag.newBag(validate));
    }

    @Test
    public void forEachWithOccurrences()
    {
        MutableBagIterable<Integer> bag = this.newWith();
        bag.addOccurrences(1, 3);
        bag.addOccurrences(2, 2);
        bag.addOccurrences(3, 1);
        IntegerSum sum = new IntegerSum(0);
        bag.forEachWithOccurrences((each, index) -> sum.add(each * index));
        Assert.assertEquals(10, sum.getIntSum());
        bag.removeOccurrences(2, 1);
        IntegerSum sum2 = new IntegerSum(0);
        bag.forEachWithOccurrences((each, index) -> sum2.add(each * index));
        Assert.assertEquals(8, sum2.getIntSum());
        bag.removeOccurrences(1, 3);
        IntegerSum sum3 = new IntegerSum(0);
        bag.forEachWithOccurrences((each, index) -> sum3.add(each * index));
        Assert.assertEquals(5, sum3.getIntSum());
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithOccurrences()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        Bag<ObjectIntPair<Integer>> actual =
                bag.collectWithOccurrences(PrimitiveTuples::pair, Bags.mutable.empty());
        Bag<ObjectIntPair<Integer>> expected =
                Bags.immutable.with(
                        PrimitiveTuples.pair(Integer.valueOf(3), 3),
                        PrimitiveTuples.pair(Integer.valueOf(2), 2),
                        PrimitiveTuples.pair(Integer.valueOf(1), 1));
        Assert.assertEquals(expected, actual);

        Set<ObjectIntPair<Integer>> actual2 =
                bag.collectWithOccurrences(PrimitiveTuples::pair, Sets.mutable.empty());
        ImmutableSet<ObjectIntPair<Integer>> expected2 =
                Sets.immutable.with(
                        PrimitiveTuples.pair(Integer.valueOf(3), 3),
                        PrimitiveTuples.pair(Integer.valueOf(2), 2),
                        PrimitiveTuples.pair(Integer.valueOf(1), 1));
        Assert.assertEquals(expected2, actual2);
    }

    @Override
    @Test
    public void toImmutable()
    {
        super.toImmutable();
        Verify.assertInstanceOf(MutableBagIterable.class, this.newWith());
        Verify.assertInstanceOf(ImmutableBagIterable.class, this.newWith().toImmutable());
        Assert.assertFalse(this.newWith().toImmutable() instanceof MutableBagIterable);
    }

    @Test
    @Override
    public void getLast()
    {
        Assert.assertEquals(Integer.valueOf(1), this.newWith(1).getLast());
        Assert.assertEquals(Integer.valueOf(3), this.newWith(3).getLast());
    }

    @Test
    public void occurrencesOf()
    {
        MutableBagIterable<Integer> bag = this.newWith(1, 1, 2);
        Assert.assertEquals(2, bag.occurrencesOf(1));
        Assert.assertEquals(1, bag.occurrencesOf(2));
    }

    @Test
    public void addOccurrences()
    {
        MutableBagIterable<String> bag = this.newWith();
        Assert.assertEquals(0, bag.addOccurrences("0", 0));
        Assert.assertEquals(1, bag.addOccurrences("1", 1));
        Assert.assertEquals(1, bag.addOccurrences("1", 0));
        Assert.assertEquals(2, bag.addOccurrences("2", 2));
        MutableBagTestCase.assertBagsEqual(HashBag.newBagWith("1", "2", "2"), bag);
        Assert.assertEquals(1, bag.addOccurrences("1", 0));
        Assert.assertEquals(6, bag.addOccurrences("2", 4));
        Assert.assertEquals(1, bag.addOccurrences("3", 1));
        MutableBagTestCase.assertBagsEqual(HashBag.newBagWith("1", "2", "2", "2", "2", "2", "2", "3"), bag);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addOccurrences_throws()
    {
        this.newWith().addOccurrences(new Object(), -1);
    }

    @Test
    public void removeOccurrences()
    {
        MutableBagIterable<String> bag = this.newWith("betamax-tape", "betamax-tape");
        MutableBagIterable<String> expected = HashBag.newBag(bag);

        Assert.assertFalse(bag.removeOccurrences("dvd", 2));
        MutableBagTestCase.assertBagsEqual(expected, bag);

        Assert.assertFalse(bag.removeOccurrences("dvd", 0));
        MutableBagTestCase.assertBagsEqual(expected, bag);

        Assert.assertFalse(bag.removeOccurrences("betamax-tape", 0));
        MutableBagTestCase.assertBagsEqual(expected, bag);

        Assert.assertTrue(bag.removeOccurrences("betamax-tape", 1));
        MutableBagTestCase.assertBagsEqual(HashBag.newBagWith("betamax-tape"), bag);

        Assert.assertTrue(bag.removeOccurrences("betamax-tape", 10));
        MutableBagTestCase.assertBagsEqual(HashBag.<String>newBag(), bag);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeOccurrences_throws()
    {
        this.newWith().removeOccurrences(new Object(), -1);
    }

    @Test
    public void setOccurrences()
    {
        MutableBagIterable<String> bag = this.newWith();
        MutableBagIterable<String> expected = this.newWith("betamax-tape", "betamax-tape");

        Assert.assertTrue(bag.setOccurrences("betamax-tape", 2));
        MutableBagTestCase.assertBagsEqual(expected, bag);

        Assert.assertFalse(bag.setOccurrences("betamax-tape", 2));
        MutableBagTestCase.assertBagsEqual(expected, bag);

        Assert.assertFalse(bag.setOccurrences("dvd", 0));
        MutableBagTestCase.assertBagsEqual(expected, bag);

        Assert.assertTrue(bag.setOccurrences("betamax-tape", 3));
        MutableBagTestCase.assertBagsEqual(expected.with("betamax-tape"), bag);

        Assert.assertTrue(bag.setOccurrences("betamax-tape", 0));
        MutableBagTestCase.assertBagsEqual(HashBag.<String>newBag(), bag);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOccurrences_throws()
    {
        this.newWith().setOccurrences(new Object(), -1);
    }

    protected static void assertBagsEqual(Bag<?> expected, Bag<?> actual)
    {
        Assert.assertEquals(expected.toMapOfItemToCount(), actual.toMapOfItemToCount());
        Assert.assertEquals(expected.sizeDistinct(), actual.sizeDistinct());
        Assert.assertEquals(expected.size(), actual.size());
        Verify.assertEqualsAndHashCode(expected, actual);
    }

    @Test
    public void toSortedListWith()
    {
        Assert.assertEquals(
                FastList.newListWith(1, 2, 2, 3, 3, 3),
                this.newWith(3, 3, 3, 2, 2, 1).toSortedList());
    }

    @Override
    @Test
    public void toSet()
    {
        super.toSet();
        MutableBagIterable<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3), bag.toSet());
    }

    @Override
    @Test
    public void toList()
    {
        super.toList();
        MutableBagIterable<Integer> bag = this.newWith(1, 1, 1);
        Assert.assertEquals(FastList.newListWith(1, 1, 1), bag.toList());
    }

    @Override
    @Test
    public void removeObject()
    {
        super.removeObject();

        MutableBagIterable<String> bag = this.newWith("dakimakura", "dakimakura");
        Assert.assertFalse(bag.remove("Mr. T"));
        Assert.assertTrue(bag.remove("dakimakura"));
        Assert.assertTrue(bag.remove("dakimakura"));
        Assert.assertFalse(bag.remove("dakimakura"));
        MutableBagTestCase.assertBagsEqual(Bags.mutable.of(), bag);
    }

    @Override
    @Test
    public void asSynchronized()
    {
        Verify.assertInstanceOf(SynchronizedBag.class, this.newWith().asSynchronized());
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        Verify.assertInstanceOf(UnmodifiableBag.class, this.newWith().asUnmodifiable());
    }

    @Test
    public void serialization()
    {
        MutableBagIterable<String> bag = this.newWith("One", "Two", "Two", "Three", "Three", "Three");
        Verify.assertPostSerializedEqualsAndHashCode(bag);
    }

    @Override
    @Test
    public void partition()
    {
        super.partition();

        MutableBagIterable<Integer> integers = this.newWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        PartitionMutableCollection<Integer> result = integers.partition(IntegerPredicates.isEven());
        Assert.assertEquals(Iterables.iBag(2, 2, 4, 4, 4, 4), result.getSelected());
        Assert.assertEquals(Iterables.iBag(1, 3, 3, 3), result.getRejected());
    }

    @Override
    @Test
    public void partitionWith()
    {
        super.partitionWith();

        MutableBagIterable<Integer> integers = this.newWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        PartitionMutableCollection<Integer> result = integers.partitionWith(Predicates2.in(), integers.select(IntegerPredicates.isEven()));
        Assert.assertEquals(Iterables.iBag(2, 2, 4, 4, 4, 4), result.getSelected());
        Assert.assertEquals(Iterables.iBag(1, 3, 3, 3), result.getRejected());
    }

    @Test
    public void selectByOccurrences()
    {
        MutableBagIterable<Integer> integers = this.newWith(1, 1, 1, 1, 2, 2, 2, 3, 3, 4);
        Assert.assertEquals(Iterables.iBag(1, 1, 1, 1, 3, 3), integers.selectByOccurrences(IntPredicates.isEven()));
    }

    @Test
    public void selectDuplicates()
    {
        MutableBagIterable<Integer> integers = this.newWith(0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 5);
        Assert.assertEquals(Iterables.iBag(1, 1, 1, 1, 2, 2, 2, 3, 3), integers.selectDuplicates());
    }

    @Test
    public void topOccurrences()
    {
        MutableBagIterable<String> strings = this.newWithOccurrences(
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
        MutableList<ObjectIntPair<String>> top5 = strings.topOccurrences(5);
        Verify.assertSize(5, top5);
        Assert.assertEquals("ten", top5.getFirst().getOne());
        Assert.assertEquals(10, top5.getFirst().getTwo());
        Assert.assertEquals("six", top5.getLast().getOne());
        Assert.assertEquals(6, top5.getLast().getTwo());
        Verify.assertSize(0, this.newWith("one").topOccurrences(0));
        Verify.assertSize(0, this.newWith().topOccurrences(5));
        Verify.assertSize(3, this.newWith("one", "two", "three").topOccurrences(5));
        Verify.assertSize(3, this.newWith("one", "two", "three").topOccurrences(1));
        Verify.assertSize(3, this.newWith("one", "two", "three").topOccurrences(2));
        Verify.assertSize(3, this.newWith("one", "one", "two", "three").topOccurrences(2));
        Verify.assertSize(2, this.newWith("one", "one", "two", "two", "three").topOccurrences(1));
        Verify.assertSize(3, this.newWith(null, "one", "two").topOccurrences(5));
        Verify.assertSize(3, this.newWith(null, "one", "two").topOccurrences(1));
        Verify.assertSize(3, this.newWith("one", "one", "two", "two", "three", "three").topOccurrences(1));
        Verify.assertSize(0, this.newWith().topOccurrences(0));
        Verify.assertSize(0, this.newWith("one").topOccurrences(0));
        Verify.assertThrows(IllegalArgumentException.class, () -> this.newWith().topOccurrences(-1));
    }

    @Test
    public void bottomOccurrences()
    {
        MutableBagIterable<String> strings = this.newWithOccurrences(
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
        MutableList<ObjectIntPair<String>> bottom5 = strings.bottomOccurrences(5);
        Verify.assertSize(5, bottom5);
        Assert.assertEquals("one", bottom5.getFirst().getOne());
        Assert.assertEquals(1, bottom5.getFirst().getTwo());
        Assert.assertEquals("five", bottom5.getLast().getOne());
        Assert.assertEquals(5, bottom5.getLast().getTwo());
        Verify.assertSize(0, this.newWith("one").bottomOccurrences(0));
        Verify.assertSize(0, this.newWith().bottomOccurrences(5));
        Verify.assertSize(3, this.newWith("one", "two", "three").bottomOccurrences(5));
        Verify.assertSize(3, this.newWith("one", "two", "three").bottomOccurrences(1));
        Verify.assertSize(3, this.newWith("one", "two", "three").bottomOccurrences(2));
        Verify.assertSize(2, this.newWith("one", "one", "two", "three").bottomOccurrences(2));
        Verify.assertSize(3, this.newWith("one", "one", "two", "two", "three").bottomOccurrences(2));
        Verify.assertSize(3, this.newWith(null, "one", "two").bottomOccurrences(5));
        Verify.assertSize(3, this.newWith(null, "one", "two").bottomOccurrences(1));
        Verify.assertSize(3, this.newWith("one", "one", "two", "two", "three", "three").bottomOccurrences(1));
        Verify.assertSize(0, this.newWith().bottomOccurrences(0));
        Verify.assertSize(0, this.newWith("one").bottomOccurrences(0));
        Verify.assertThrows(IllegalArgumentException.class, () -> this.newWith().bottomOccurrences(-1));
    }

    @Test
    public void selectUnique()
    {
        MutableBag<String> bag = Bags.mutable.with("0", "1", "1", "1", "1", "2", "2", "2", "3", "3", "4", "5");
        MutableSet<String> expected = Sets.mutable.with("0", "4", "5");
        MutableSet<String> actual = bag.selectUnique();
        Assert.assertEquals(expected, actual);
    }
}
