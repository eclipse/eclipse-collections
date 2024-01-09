/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.primitive;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.eclipse.collections.api.LazyLongIterable;
import org.eclipse.collections.api.LongIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.procedure.primitive.LongProcedure;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.iterator.LongIterator;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.ImmutableLongList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.tuple.primitive.LongLongPair;
import org.eclipse.collections.api.tuple.primitive.LongObjectPair;
import org.eclipse.collections.impl.ThrowingAppendable;
import org.eclipse.collections.impl.bag.mutable.primitive.LongHashBag;
import org.eclipse.collections.impl.block.factory.primitive.LongPredicates;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.factory.primitive.ByteLists;
import org.eclipse.collections.impl.factory.primitive.CharLists;
import org.eclipse.collections.impl.factory.primitive.DoubleLists;
import org.eclipse.collections.impl.factory.primitive.FloatLists;
import org.eclipse.collections.impl.factory.primitive.LongLists;
import org.eclipse.collections.impl.factory.primitive.ShortLists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.math.MutableLong;
import org.eclipse.collections.impl.set.mutable.primitive.LongHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

public class LongIntervalTest
{
    private final LongInterval longInterval = LongInterval.oneTo(3);

    @Test
    public void fromAndToAndBy()
    {
        LongInterval interval = LongInterval.from(1);
        LongInterval interval2 = interval.to(10);
        LongInterval interval3 = interval2.by(2);
        Verify.assertEqualsAndHashCode(interval, LongInterval.fromTo(1, 1));
        Verify.assertEqualsAndHashCode(interval2, LongInterval.fromTo(1, 10));
        Verify.assertEqualsAndHashCode(interval3, LongInterval.fromToBy(1, 10, 2));
        Verify.assertSize(Integer.MAX_VALUE, LongInterval.fromTo(Integer.MIN_VALUE + 1, -1));
        Verify.assertSize(Integer.MAX_VALUE, LongInterval.fromTo(1, Integer.MAX_VALUE));

        Assert.assertThrows(IllegalArgumentException.class, () -> LongInterval.fromTo(Integer.MIN_VALUE, Integer.MAX_VALUE));
        Assert.assertThrows(IllegalArgumentException.class, () -> LongInterval.fromTo(-1, Integer.MAX_VALUE));
        Assert.assertThrows(IllegalArgumentException.class, () -> LongInterval.fromToBy(Integer.MIN_VALUE, Integer.MAX_VALUE, 2));
        Assert.assertEquals(LongInterval.fromTo(Integer.MIN_VALUE + 1, -1).size(), LongInterval.oneTo(Integer.MAX_VALUE).size());

        Assert.assertEquals(LongLists.mutable.with(0), LongInterval.fromToBy(0, 2, 3));
        Assert.assertEquals(LongLists.mutable.with(0), LongInterval.fromToBy(0, -2, -3));
        Assert.assertEquals(LongLists.mutable.with(1_000_000_000), LongInterval.fromToBy(1_000_000_000, 2_000_000_000, 1_500_000_000));
        Assert.assertEquals(LongLists.mutable.with(-1_000_000_000), LongInterval.fromToBy(-1_000_000_000, -2_000_000_000, -1_500_000_000));
        Assert.assertEquals(LongLists.mutable.with(Integer.MIN_VALUE), LongInterval.fromToBy(Integer.MIN_VALUE, Integer.MIN_VALUE + 10, 20));
        Assert.assertEquals(LongLists.mutable.with(Integer.MAX_VALUE), LongInterval.fromToBy(Integer.MAX_VALUE, Integer.MAX_VALUE - 10, -20));
    }

    @Test
    public void fromAndToAndByWithLongs()
    {
        long maxint = Integer.MAX_VALUE;
        long minint = Integer.MIN_VALUE;
        LongInterval interval = LongInterval.from(maxint + 1);
        LongInterval interval2 = interval.to(maxint + 10);
        LongInterval interval3 = interval2.by(2);
        Verify.assertEqualsAndHashCode(interval, LongInterval.fromTo(maxint + 1, maxint + 1));
        Verify.assertEqualsAndHashCode(interval2, LongInterval.fromTo(maxint + 1, maxint + 10));
        Verify.assertEqualsAndHashCode(interval3, LongInterval.fromToBy(maxint + 1, maxint + 10, 2));

        Assert.assertEquals(LongLists.mutable.with(maxint, maxint * 2, maxint * 3), LongInterval.fromToBy(maxint, maxint * 3L, maxint));
        Assert.assertEquals(
                LongLists.mutable.with(minint, minint + maxint, minint + maxint * 2, minint + maxint * 3),
                LongInterval.fromToBy(minint, maxint * 2, maxint));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromToBy_throws_step_size_zero()
    {
        LongInterval.fromToBy(0, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromToBy_throws_on_illegal_step()
    {
        LongInterval.fromToBy(5, 0, 1);
    }

    @Test
    public void fromToBy_with_same_start_and_end_with_negative_step()
    {
        MutableLongList integers = LongInterval.fromToBy(2, 2, -2).toList();

        Verify.assertEquals(1, integers.size());
        Verify.assertEquals(2, integers.getFirst());
    }

    @Test
    public void fromToBy_with_same_start_and_end_with_negative_step2()
    {
        MutableLongList integers = LongInterval.fromToBy(2, 2, -1).toList();

        Verify.assertEquals(1, integers.size());
        Verify.assertEquals(2, integers.getFirst());
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneToBy_throws_step_size_zero()
    {
        LongInterval.oneToBy(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneToBy_throws_count_size_zero()
    {
        LongInterval.oneToBy(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroToBy_throws_step_size_zero()
    {
        LongInterval.zeroToBy(0, 0);
    }

    @Test
    public void equalsAndHashCode()
    {
        LongInterval interval1 = LongInterval.oneTo(5);
        LongInterval interval2 = LongInterval.oneTo(5);
        LongInterval interval3 = LongInterval.zeroTo(5);
        Verify.assertPostSerializedEqualsAndHashCode(interval1);
        Verify.assertEqualsAndHashCode(interval1, interval2);
        Assert.assertNotEquals(interval1, interval3);
        Assert.assertNotEquals(interval3, interval1);

        Verify.assertEqualsAndHashCode(LongInterval.fromToBy(1, 5, 2), LongInterval.fromToBy(1, 6, 2));
        Verify.assertEqualsAndHashCode(LongArrayList.newListWith(1, 2, 3), LongInterval.fromTo(1, 3));
        Verify.assertEqualsAndHashCode(LongArrayList.newListWith(3, 2, 1), LongInterval.fromTo(3, 1));

        Assert.assertNotEquals(LongArrayList.newListWith(1, 2, 3, 4), LongInterval.fromTo(1, 3));
        Assert.assertNotEquals(LongArrayList.newListWith(1, 2, 4), LongInterval.fromTo(1, 3));
        Assert.assertNotEquals(LongArrayList.newListWith(3, 2, 0), LongInterval.fromTo(3, 1));

        Verify.assertEqualsAndHashCode(LongArrayList.newListWith(-1, -2, -3), LongInterval.fromTo(-1, -3));

        Verify.assertEqualsAndHashCode(LongArrayList.newListWith(1), LongInterval.fromToBy(1, 1, 1));
        Verify.assertEqualsAndHashCode(LongArrayList.newListWith(1), LongInterval.fromToBy(1, 1, 2));
        Verify.assertEqualsAndHashCode(LongArrayList.newListWith(-1), LongInterval.fromToBy(-1, -1, -1));
        Verify.assertEqualsAndHashCode(LongArrayList.newListWith(-1), LongInterval.fromToBy(-1, -1, -2));

        LongInterval interval4 = LongInterval.fromTo(-1, -1_000);
        LongInterval interval5 = LongInterval.fromTo(-1, -1_000);
        LongInterval interval6 = LongInterval.fromTo(0, -999);
        Verify.assertPostSerializedEqualsAndHashCode(interval4);
        Verify.assertEqualsAndHashCode(interval4, interval5);
        Assert.assertNotEquals(interval4, interval6);
        Assert.assertNotEquals(interval6, interval4);
    }

    @Test
    public void sumLongInterval()
    {
        Assert.assertEquals(15, (int) LongInterval.oneTo(5).sum());
    }

    @Test
    public void maxLongInterval()
    {
        long value = LongInterval.oneTo(5).max();
        Assert.assertEquals(5L, value);
    }

    @Test
    public void iterator()
    {
        LongIterator iterator = this.longInterval.longIterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(1L, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(2L, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(3L, iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator_throws()
    {
        LongIterator iterator = this.longInterval.longIterator();
        while (iterator.hasNext())
        {
            iterator.next();
        }

        iterator.next();
    }

    @Test
    public void forEach()
    {
        long[] sum = new long[1];
        this.longInterval.forEach(each -> sum[0] += each);

        Assert.assertEquals(6L, sum[0]);
    }

    @Test
    public void each()
    {
        MutableLongList list1 = LongLists.mutable.empty();
        LongInterval interval1 = LongInterval.oneTo(5);
        interval1.each(list1::add);
        Assert.assertEquals(list1, interval1);
        LongInterval interval2 = LongInterval.fromTo(5, 1);
        MutableLongList list2 = LongLists.mutable.empty();
        interval2.each(list2::add);
        Assert.assertEquals(list2, interval2);
    }

    @Test
    public void injectInto()
    {
        LongInterval longInterval1 = LongInterval.oneTo(3);
        MutableLong result = longInterval1.injectInto(new MutableLong(0), MutableLong::add);
        Assert.assertEquals(new MutableLong(6), result);
        LongInterval longInterval2 = LongInterval.fromTo(3, 1);
        MutableLong result2 = longInterval2.injectInto(new MutableLong(0), MutableLong::add);
        Assert.assertEquals(new MutableLong(6), result2);
    }

    @Test
    public void injectIntoWithIndex()
    {
        LongInterval interval1 = LongInterval.oneTo(3);
        MutableLong result1 = this.longInterval.injectIntoWithIndex(new MutableLong(0), (object, value, index) -> object.add(value * interval1.get(index)));
        Assert.assertEquals(new MutableLong(14), result1);
        LongInterval interval2 = LongInterval.fromTo(3, 1);
        MutableLong result2 = interval2.injectIntoWithIndex(new MutableLong(0), (object, value, index) -> object.add(value * this.longInterval.get(index)));
        Assert.assertEquals(new MutableLong(10), result2);
    }

    @Test
    public void injectIntoOnFromToBySameStartEndNegativeStepInterval()
    {
        LongInterval interval = LongInterval.fromToBy(2, 2, -2);

        Assert.assertEquals(new MutableLong(0), interval.injectInto(new MutableLong(-2), MutableLong::add));
    }

    @Test
    public void chunk()
    {
        LongInterval interval1 = LongInterval.fromToBy(0, 5, 1);
        MutableList<LongInterval> expected1 = Lists.mutable.with(
                LongInterval.fromToBy(0, 1, 1),
                LongInterval.fromToBy(2, 3, 1),
                LongInterval.fromToBy(4, 5, 1));
        Assert.assertEquals(expected1, interval1.chunk(2));

        LongInterval interval2 = LongInterval.fromToBy(0, -5, -1);
        MutableList<LongInterval> expected2 = Lists.mutable.with(
                LongInterval.fromToBy(0, -1, -1),
                LongInterval.fromToBy(-2, -3, -1),
                LongInterval.fromToBy(-4, -5, -1));
        Assert.assertEquals(expected2, interval2.chunk(2));

        LongInterval interval3 = LongInterval.fromToBy(0, 6, 1);
        MutableList<LongInterval> expected3 = Lists.mutable.with(
                LongInterval.fromToBy(0, 1, 1),
                LongInterval.fromToBy(2, 3, 1),
                LongInterval.fromToBy(4, 5, 1),
                LongInterval.fromToBy(6, 6, 1));
        Assert.assertEquals(expected3, interval3.chunk(2));

        LongInterval interval4 = LongInterval.fromToBy(0, -6, -1);
        MutableList<LongInterval> expected4 = Lists.mutable.with(
                LongInterval.fromToBy(0, -1, -1),
                LongInterval.fromToBy(-2, -3, -1),
                LongInterval.fromToBy(-4, -5, -1),
                LongInterval.fromToBy(-6, -6, -1));
        RichIterable<LongIterable> actual4 = interval4.chunk(2);
        Assert.assertEquals(expected4, actual4);

        LongInterval interval5 = LongInterval.fromToBy(0, 6, 1);
        MutableList<LongInterval> expected5 = Lists.mutable.with(LongInterval.fromToBy(0, 6, 1));
        Assert.assertEquals(expected5, interval5.chunk(7));

        LongInterval interval6 = LongInterval.fromToBy(0, -6, -1);
        MutableList<LongInterval> expected6 = Lists.mutable.with(LongInterval.fromToBy(0, -6, -1));
        Assert.assertEquals(expected6, interval6.chunk(7));

        LongInterval interval7 = LongInterval.fromToBy(0, 6, 1);
        MutableList<LongInterval> expected7 = Lists.mutable.with(LongInterval.fromToBy(0, 6, 1));
        Assert.assertEquals(expected7, interval7.chunk(8));

        LongInterval interval8 = LongInterval.fromToBy(0, -6, -1);
        MutableList<LongInterval> expected8 = Lists.mutable.with(LongInterval.fromToBy(0, -6, -1));
        Assert.assertEquals(expected8, interval8.chunk(8));

        LongInterval interval9 = LongInterval.fromToBy(0, 9, 4);
        MutableList<LongIterable> expected9 = Lists.mutable.with(
                LongLists.mutable.with(0, 4),
                LongLists.mutable.with(8));
        Assert.assertEquals(expected9, interval9.chunk(2));

        LongInterval interval10 = LongInterval.fromToBy(0, -9, -4);
        MutableList<LongIterable> expected10 = Lists.mutable.with(
                LongLists.mutable.with(0, -4),
                LongLists.mutable.with(-8));
        Assert.assertEquals(expected10, interval10.chunk(2));

        LongInterval interval11 = LongInterval.fromToBy(0, 5, 3);
        MutableList<LongIterable> expected11 = Lists.mutable.with(LongLists.mutable.with(0, 3));
        Assert.assertEquals(expected11, interval11.chunk(3));

        LongInterval interval12 = LongInterval.fromToBy(0, -5, -3);
        MutableList<LongIterable> expected12 = Lists.mutable.with(LongLists.mutable.with(0, -3));
        Assert.assertEquals(expected12, interval12.chunk(3));

        Assert.assertThrows(IllegalArgumentException.class, () -> interval12.chunk(0));
        Assert.assertThrows(IllegalArgumentException.class, () -> interval12.chunk(-1));
    }

    @Test
    public void size()
    {
        Verify.assertSize(3, this.longInterval);
        // Positive Ranges
        Verify.assertSize(10, LongInterval.zeroTo(9));
        Verify.assertSize(2_000_000_000, LongInterval.oneTo(2_000_000_000));
        Verify.assertSize(200_000_000, LongInterval.oneTo(2_000_000_000).by(10));
        Verify.assertSize(2_000_000_000, LongInterval.fromTo(2_000_000_000, 1).by(-1));
        Verify.assertSize(500_000_000, LongInterval.oneTo(2_000_000_000).by(4));
        Verify.assertSize(222_222_223, LongInterval.oneTo(2_000_000_000).by(9));
        Verify.assertSize(2, LongInterval.fromToBy(Integer.MAX_VALUE - 10, Integer.MAX_VALUE, 8));

        // Negative Ranges
        Verify.assertSize(10, LongInterval.fromTo(0, -9));
        Verify.assertSize(2_000_000_000, LongInterval.fromTo(-1, -2_000_000_000));
        Verify.assertSize(200_000_000, LongInterval.fromTo(-1, -2_000_000_000).by(-10));
        Verify.assertSize(2_000_000_000, LongInterval.fromTo(-2_000_000_000, -1).by(1));
        Verify.assertSize(500_000_000, LongInterval.fromTo(-1, -2_000_000_000).by(-4));
        Verify.assertSize(222_222_223, LongInterval.fromTo(-1, -2_000_000_000).by(-9));
        // Overlapping Ranges
        Verify.assertSize(21, LongInterval.fromTo(10, -10));
        Verify.assertSize(5, LongInterval.fromTo(10, -10).by(-5));
        Verify.assertSize(5, LongInterval.fromTo(-10, 10).by(5));
        Verify.assertSize(2_000_000_001, LongInterval.fromTo(1_000_000_000, -1_000_000_000));
        Verify.assertSize(200_000_001, LongInterval.fromTo(1_000_000_000, -1_000_000_000).by(-10));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void subList()
    {
        this.longInterval.subList(0, 1);
    }

    @Test
    public void dotProduct()
    {
        LongInterval interval = LongInterval.oneTo(3);
        Assert.assertEquals(14, this.longInterval.dotProduct(interval));
    }

    @Test(expected = IllegalArgumentException.class)
    public void dotProduct_throwsOnListsOfDifferentSizes()
    {
        LongInterval interval = LongInterval.oneTo(4);
        this.longInterval.dotProduct(interval);
    }

    @Test
    public void empty()
    {
        Assert.assertTrue(this.longInterval.notEmpty());
        Assert.assertFalse(this.longInterval.isEmpty());
        Verify.assertNotEmpty(this.longInterval);
    }

    @Test
    public void count()
    {
        Assert.assertEquals(2L, LongInterval.zeroTo(2).count(LongPredicates.greaterThan(0)));

        int count = LongInterval.fromToBy(Integer.MAX_VALUE - 10, Integer.MAX_VALUE, 8).count(LongPredicates.greaterThan(0));
        Assert.assertEquals(2, count);
    }

    @Test
    public void anySatisfy()
    {
        Assert.assertTrue(LongInterval.fromTo(-1, 2).anySatisfy(LongPredicates.greaterThan(0)));
        Assert.assertFalse(LongInterval.oneTo(2).anySatisfy(LongPredicates.equal(0)));
    }

    @Test
    public void allSatisfy()
    {
        Assert.assertFalse(LongInterval.zeroTo(2).allSatisfy(LongPredicates.greaterThan(0)));
        Assert.assertTrue(LongInterval.oneTo(3).allSatisfy(LongPredicates.greaterThan(0)));
    }

    @Test
    public void noneSatisfy()
    {
        Assert.assertFalse(LongInterval.zeroTo(2).noneSatisfy(LongPredicates.isEven()));
        Assert.assertTrue(LongInterval.evensFromTo(2, 10).noneSatisfy(LongPredicates.isOdd()));
    }

    @Test
    public void select()
    {
        Verify.assertSize(3, this.longInterval.select(LongPredicates.lessThan(4)));
        Verify.assertSize(2, this.longInterval.select(LongPredicates.lessThan(3)));
    }

    @Test
    public void reject()
    {
        Verify.assertSize(0, this.longInterval.reject(LongPredicates.lessThan(4)));
        Verify.assertSize(1, this.longInterval.reject(LongPredicates.lessThan(3)));
    }

    @Test
    public void detectIfNone()
    {
        Assert.assertEquals(1L, this.longInterval.detectIfNone(LongPredicates.lessThan(4), 0));
        Assert.assertEquals(0L, this.longInterval.detectIfNone(LongPredicates.greaterThan(3), 0));
    }

    @Test
    public void collect()
    {
        Assert.assertEquals(FastList.newListWith(0L, 1L, 2L), this.longInterval.collect(parameter -> parameter - 1).toList());
    }

    @Test
    public void lazyCollectPrimitives()
    {
        Assert.assertEquals(BooleanLists.immutable.of(false, true, false), LongInterval.oneTo(3).asLazy().collectBoolean(e -> e % 2 == 0).toList());
        Assert.assertEquals(CharLists.immutable.of((char) 2, (char) 3, (char) 4), LongInterval.oneTo(3).asLazy().collectChar(e -> (char) (e + 1)).toList());
        Assert.assertEquals(ByteLists.immutable.of((byte) 2, (byte) 3, (byte) 4), LongInterval.oneTo(3).asLazy().collectByte(e -> (byte) (e + 1)).toList());
        Assert.assertEquals(ShortLists.immutable.of((short) 2, (short) 3, (short) 4), LongInterval.oneTo(3).asLazy().collectShort(e -> (short) (e + 1)).toList());
        Assert.assertEquals(LongLists.immutable.of(2, 3, 4), LongInterval.oneTo(3).asLazy().collectLong(e -> e + 1).toList());
        Assert.assertEquals(FloatLists.immutable.of(2.0f, 3.0f, 4.0f), LongInterval.oneTo(3).asLazy().collectFloat(e -> (float) (e + 1)).toList());
        Assert.assertEquals(LongLists.immutable.of(2L, 3L, 4L), LongInterval.oneTo(3).asLazy().collectLong(e -> (long) (e + 1)).toList());
        Assert.assertEquals(DoubleLists.immutable.of(2.0, 3.0, 4.0), LongInterval.oneTo(3).asLazy().collectDouble(e -> (double) (e + 1)).toList());
    }

    @Test
    public void binarySearch()
    {
        LongInterval interval1 = LongInterval.oneTo(3);
        Assert.assertEquals(-1, interval1.binarySearch(-1));
        Assert.assertEquals(-1, interval1.binarySearch(0));
        Assert.assertEquals(0, interval1.binarySearch(1));
        Assert.assertEquals(1, interval1.binarySearch(2));
        Assert.assertEquals(2, interval1.binarySearch(3));
        Assert.assertEquals(-4, interval1.binarySearch(4));
        Assert.assertEquals(-4, interval1.binarySearch(5));

        LongInterval interval2 = LongInterval.fromTo(7, 17).by(3);
        Assert.assertEquals(0, interval2.binarySearch(7));
        Assert.assertEquals(1, interval2.binarySearch(10));
        Assert.assertEquals(2, interval2.binarySearch(13));
        Assert.assertEquals(3, interval2.binarySearch(16));
        Assert.assertEquals(-1, interval2.binarySearch(6));
        Assert.assertEquals(-2, interval2.binarySearch(8));
        Assert.assertEquals(-2, interval2.binarySearch(9));
        Assert.assertEquals(-3, interval2.binarySearch(12));
        Assert.assertEquals(-4, interval2.binarySearch(15));
        Assert.assertEquals(-5, interval2.binarySearch(17));
        Assert.assertEquals(-5, interval2.binarySearch(19));

        LongInterval interval3 = LongInterval.fromTo(-21, -11).by(5);
        Assert.assertEquals(-1, interval3.binarySearch(-22));
        Assert.assertEquals(0, interval3.binarySearch(-21));
        Assert.assertEquals(-2, interval3.binarySearch(-17));
        Assert.assertEquals(1, interval3.binarySearch(-16));
        Assert.assertEquals(-3, interval3.binarySearch(-15));
        Assert.assertEquals(2, interval3.binarySearch(-11));
        Assert.assertEquals(-4, interval3.binarySearch(-9));

        LongInterval interval4 = LongInterval.fromTo(50, 30).by(-10);
        Assert.assertEquals(-1, interval4.binarySearch(60));
        Assert.assertEquals(0, interval4.binarySearch(50));
        Assert.assertEquals(-2, interval4.binarySearch(45));
        Assert.assertEquals(1, interval4.binarySearch(40));
        Assert.assertEquals(-3, interval4.binarySearch(35));
        Assert.assertEquals(2, interval4.binarySearch(30));
        Assert.assertEquals(-4, interval4.binarySearch(25));

        LongInterval interval5 = LongInterval.fromTo(-30, -50).by(-10);
        Assert.assertEquals(-1, interval5.binarySearch(-20));
        Assert.assertEquals(0, interval5.binarySearch(-30));
        Assert.assertEquals(-2, interval5.binarySearch(-35));
        Assert.assertEquals(1, interval5.binarySearch(-40));
        Assert.assertEquals(-3, interval5.binarySearch(-47));
        Assert.assertEquals(2, interval5.binarySearch(-50));
        Assert.assertEquals(-4, interval5.binarySearch(-65));

        LongInterval interval6 = LongInterval.fromTo(27, -30).by(-9);
        Assert.assertEquals(-1, interval6.binarySearch(30));
        Assert.assertEquals(0, interval6.binarySearch(27));
        Assert.assertEquals(-2, interval6.binarySearch(20));
        Assert.assertEquals(1, interval6.binarySearch(18));
        Assert.assertEquals(-3, interval6.binarySearch(15));
        Assert.assertEquals(2, interval6.binarySearch(9));
        Assert.assertEquals(-4, interval6.binarySearch(2));
        Assert.assertEquals(3, interval6.binarySearch(0));
        Assert.assertEquals(-5, interval6.binarySearch(-7));
        Assert.assertEquals(4, interval6.binarySearch(-9));
        Assert.assertEquals(-6, interval6.binarySearch(-12));
        Assert.assertEquals(5, interval6.binarySearch(-18));
        Assert.assertEquals(-7, interval6.binarySearch(-23));
        Assert.assertEquals(6, interval6.binarySearch(-27));
        Assert.assertEquals(-8, interval6.binarySearch(-28));
        Assert.assertEquals(-8, interval6.binarySearch(-30));

        LongInterval interval7 = LongInterval.fromTo(-1, 1).by(1);
        Assert.assertEquals(-1, interval7.binarySearch(-2));
        Assert.assertEquals(0, interval7.binarySearch(-1));
        Assert.assertEquals(1, interval7.binarySearch(0));
        Assert.assertEquals(2, interval7.binarySearch(1));
        Assert.assertEquals(-4, interval7.binarySearch(2));
    }

    @Test
    public void max()
    {
        Assert.assertEquals(9, LongInterval.oneTo(9).max());
        Assert.assertEquals(5, LongInterval.fromTo(5, 1).max());
        Assert.assertEquals(1, LongInterval.fromTo(-5, 1).max());
        Assert.assertEquals(1, LongInterval.fromTo(1, -5).max());
    }

    @Test
    public void min()
    {
        Assert.assertEquals(1, LongInterval.oneTo(9).min());
        Assert.assertEquals(1, LongInterval.fromTo(5, 1).min());
        Assert.assertEquals(-5, LongInterval.fromTo(-5, 1).min());
        Assert.assertEquals(-5, LongInterval.fromTo(1, -5).min());
    }

    @Test
    public void minIfEmpty()
    {
        Assert.assertEquals(1, LongInterval.oneTo(9).minIfEmpty(0));
    }

    @Test
    public void maxIfEmpty()
    {
        Assert.assertEquals(9, LongInterval.oneTo(9).maxIfEmpty(0));
    }

    @Test
    public void sum()
    {
        Assert.assertEquals(10L, LongInterval.oneTo(4).sum());
    }

    @Test
    public void sumLong()
    {
        // checks there is no overflow the during calculation of sum() as long as the final result is <= Long.MAX_VALUE

        Assert.assertEquals(Long.MAX_VALUE, LongInterval.fromTo(Long.MAX_VALUE, Long.MAX_VALUE).sum());

        long l = Long.MAX_VALUE / 5L;
        long expectedSum = 0;

        for (long i = 0L; i < 4L; i++)
        {
            expectedSum += l + i;

            long actualSum = LongInterval.fromTo(l, l + i).sum();

            Assert.assertEquals("interval size : " + (i + 1), expectedSum, actualSum);
            Assert.assertTrue(actualSum > 0L);
        }
    }

    @Test
    public void average()
    {
        Assert.assertEquals(2.5, LongInterval.oneTo(4).average(), 0.0);
    }

    @Test
    public void median()
    {
        Assert.assertEquals(2.5, LongInterval.oneTo(4).median(), 0.0);
        Assert.assertEquals(3.0, LongInterval.oneTo(5).median(), 0.0);
    }

    @Test
    public void toArray()
    {
        Assert.assertArrayEquals(new long[]{1, 2, 3, 4}, LongInterval.oneTo(4).toArray());
    }

    @Test
    public void toList()
    {
        Assert.assertEquals(LongArrayList.newListWith(1, 2, 3, 4), LongInterval.oneTo(4).toList());
    }

    @Test
    public void toSortedList()
    {
        Assert.assertEquals(LongArrayList.newListWith(1, 2, 3, 4), LongInterval.oneTo(4).toReversed().toSortedList());
    }

    @Test
    public void toSet()
    {
        Assert.assertEquals(LongHashSet.newSetWith(1, 2, 3, 4), LongInterval.oneTo(4).toSet());
    }

    @Test
    public void toBag()
    {
        Assert.assertEquals(LongHashBag.newBagWith(1, 2, 3, 4), LongInterval.oneTo(4).toBag());
    }

    @Test
    public void asLazy()
    {
        Assert.assertEquals(LongInterval.oneTo(5).toSet(), LongInterval.oneTo(5).asLazy().toSet());
        Verify.assertInstanceOf(LazyLongIterable.class, LongInterval.oneTo(5).asLazy());
    }

    @Test
    public void toSortedArray()
    {
        Assert.assertArrayEquals(new long[]{1, 2, 3, 4}, LongInterval.fromTo(4, 1).toSortedArray());
    }

    @Test
    public void testEquals()
    {
        LongInterval list1 = LongInterval.oneTo(4);
        LongInterval list2 = LongInterval.oneTo(4);
        LongInterval list3 = LongInterval.fromTo(4, 1);
        LongInterval list4 = LongInterval.fromTo(5, 8);
        LongInterval list5 = LongInterval.fromTo(5, 7);

        Verify.assertEqualsAndHashCode(list1, list2);
        Verify.assertPostSerializedEqualsAndHashCode(list1);
        Assert.assertNotEquals(list1, list3);
        Assert.assertNotEquals(list1, list4);
        Assert.assertNotEquals(list1, list5);
    }

    @Test
    public void testHashCode()
    {
        Assert.assertEquals(FastList.newListWith(1, 2, 3).hashCode(), LongInterval.oneTo(3).hashCode());
    }

    @Test
    public void testToString()
    {
        Assert.assertEquals("[1, 2, 3]", this.longInterval.toString());
    }

    @Test
    public void makeString()
    {
        Assert.assertEquals("1, 2, 3", this.longInterval.makeString());
        Assert.assertEquals("1/2/3", this.longInterval.makeString("/"));
        Assert.assertEquals(this.longInterval.toString(), this.longInterval.makeString("[", ", ", "]"));
    }

    @Test
    public void appendString()
    {
        StringBuilder appendable2 = new StringBuilder();
        this.longInterval.appendString(appendable2);
        Assert.assertEquals("1, 2, 3", appendable2.toString());
        StringBuilder appendable3 = new StringBuilder();
        this.longInterval.appendString(appendable3, "/");
        Assert.assertEquals("1/2/3", appendable3.toString());
        StringBuilder appendable4 = new StringBuilder();
        this.longInterval.appendString(appendable4, "[", ", ", "]");
        Assert.assertEquals(this.longInterval.toString(), appendable4.toString());
    }

    @Test
    public void appendStringThrows()
    {
        Assert.assertThrows(
                RuntimeException.class,
                () -> this.longInterval.appendString(new ThrowingAppendable()));
        Assert.assertThrows(
                RuntimeException.class,
                () -> this.longInterval
                        .appendString(new ThrowingAppendable(), ", "));
        Assert.assertThrows(
                RuntimeException.class,
                () -> this.longInterval
                        .appendString(new ThrowingAppendable(), "[", ", ", "]"));
    }

    @Test
    public void toReversed()
    {
        LongInterval forward = LongInterval.oneTo(5);
        LongInterval reverse = forward.toReversed();
        Assert.assertEquals(LongArrayList.newListWith(5, 4, 3, 2, 1), reverse);
    }

    @Test
    public void evens()
    {
        LongInterval interval = LongInterval.evensFromTo(0, 10);
        int[] evens = {0, 2, 4, 6, 8, 10};
        int[] odds = {1, 3, 5, 7, 9};
        this.assertLongIntervalContainsAll(interval, evens);
        this.denyLongIntervalContainsAny(interval, odds);
        Assert.assertEquals(6, interval.size());

        LongInterval reverseLongInterval = LongInterval.evensFromTo(10, 0);
        this.assertLongIntervalContainsAll(reverseLongInterval, evens);
        this.denyLongIntervalContainsAny(reverseLongInterval, odds);
        Assert.assertEquals(6, reverseLongInterval.size());

        LongInterval negativeLongInterval = LongInterval.evensFromTo(-5, 5);
        int[] negativeEvens = {-4, -2, 0, 2, 4};
        int[] negativeOdds = {-3, -1, 1, 3};
        this.assertLongIntervalContainsAll(negativeLongInterval, negativeEvens);
        this.denyLongIntervalContainsAny(negativeLongInterval, negativeOdds);
        Assert.assertEquals(5, negativeLongInterval.size());

        LongInterval reverseNegativeLongInterval = LongInterval.evensFromTo(5, -5);
        this.assertLongIntervalContainsAll(reverseNegativeLongInterval, negativeEvens);
        this.denyLongIntervalContainsAny(reverseNegativeLongInterval, negativeOdds);
        Assert.assertEquals(5, reverseNegativeLongInterval.size());
    }

    private void assertLongIntervalContainsAll(LongInterval interval, int[] expectedValues)
    {
        for (int value : expectedValues)
        {
            Assert.assertTrue(interval.contains(value));
        }
    }

    private void denyLongIntervalContainsAny(LongInterval interval, int[] expectedValues)
    {
        for (int value : expectedValues)
        {
            Assert.assertFalse(interval.contains(value));
        }
    }

    @Test
    public void odds()
    {
        LongInterval interval1 = LongInterval.oddsFromTo(0, 10);
        Assert.assertTrue(interval1.containsAll(1, 3, 5, 7, 9));
        Assert.assertTrue(interval1.containsNone(2, 4, 6, 8));
        Assert.assertEquals(5, interval1.size());

        LongInterval reverseLongInterval1 = LongInterval.oddsFromTo(10, 0);
        Assert.assertTrue(reverseLongInterval1.containsAll(1, 3, 5, 7, 9));
        Assert.assertTrue(reverseLongInterval1.containsNone(0, 2, 4, 6, 8, 10));
        Assert.assertEquals(5, reverseLongInterval1.size());

        LongInterval interval2 = LongInterval.oddsFromTo(-5, 5);
        Assert.assertTrue(interval2.containsAll(-5, -3, -1, 1, 3, 5));
        Assert.assertTrue(interval2.containsNone(-4, -2, 0, 2, 4));
        Assert.assertEquals(6, interval2.size());

        LongInterval reverseLongInterval2 = LongInterval.oddsFromTo(5, -5);
        Assert.assertTrue(reverseLongInterval2.containsAll(-5, -3, -1, 1, 3, 5));
        Assert.assertTrue(reverseLongInterval2.containsNone(-4, -2, 0, 2, 4));
        Assert.assertEquals(6, reverseLongInterval2.size());
    }

    @Test
    public void intervalSize()
    {
        Assert.assertEquals(100, LongInterval.fromTo(1, 100).size());
        Assert.assertEquals(50, LongInterval.fromToBy(1, 100, 2).size());
        Assert.assertEquals(34, LongInterval.fromToBy(1, 100, 3).size());
        Assert.assertEquals(25, LongInterval.fromToBy(1, 100, 4).size());
        Assert.assertEquals(20, LongInterval.fromToBy(1, 100, 5).size());
        Assert.assertEquals(17, LongInterval.fromToBy(1, 100, 6).size());
        Assert.assertEquals(15, LongInterval.fromToBy(1, 100, 7).size());
        Assert.assertEquals(13, LongInterval.fromToBy(1, 100, 8).size());
        Assert.assertEquals(12, LongInterval.fromToBy(1, 100, 9).size());
        Assert.assertEquals(10, LongInterval.fromToBy(1, 100, 10).size());
        Assert.assertEquals(11, LongInterval.fromTo(0, 10).size());
        Assert.assertEquals(1, LongInterval.zero().size());
        Assert.assertEquals(11, LongInterval.fromTo(0, -10).size());
        Assert.assertEquals(3, LongInterval.evensFromTo(2, -2).size());
        Assert.assertEquals(2, LongInterval.oddsFromTo(2, -2).size());
        Assert.assertEquals(1, LongInterval.fromToBy(1_000_000_000, 2_000_000_000, 1_500_000_000).size());
        Assert.assertEquals(1, LongInterval.fromToBy(-1_000_000_000, -2_000_000_000, -1_500_000_000).size());
    }

    @Test
    public void contains()
    {
        Assert.assertTrue(LongInterval.zero().contains(0));
        Assert.assertTrue(LongInterval.oneTo(5).containsAll(1, 5));
        Assert.assertTrue(LongInterval.oneTo(5).containsNone(6, 7));
        Assert.assertFalse(LongInterval.oneTo(5).containsAll(1, 6));
        Assert.assertFalse(LongInterval.oneTo(5).containsNone(1, 6));
        Assert.assertFalse(LongInterval.oneTo(5).contains(0));
        Assert.assertTrue(LongInterval.fromTo(-1, -5).containsAll(-1, -5));
        Assert.assertFalse(LongInterval.fromTo(-1, -5).contains(1));

        Assert.assertTrue(LongInterval.zero().contains(Integer.valueOf(0)));
        Assert.assertFalse(LongInterval.oneTo(5).contains(Integer.valueOf(0)));
        Assert.assertFalse(LongInterval.fromTo(-1, -5).contains(Integer.valueOf(1)));

        LongInterval bigLongInterval = LongInterval.fromToBy(Integer.MIN_VALUE, Integer.MAX_VALUE, 1_000_000);
        Assert.assertTrue(bigLongInterval.contains(Integer.MIN_VALUE + 1_000_000));
        Assert.assertFalse(bigLongInterval.contains(Integer.MIN_VALUE + 1_000_001));
        Assert.assertTrue(bigLongInterval.contains(Integer.MIN_VALUE + (1_000_000 * 10)));
        Assert.assertFalse(bigLongInterval.contains(Integer.MIN_VALUE + (1_000_001 * 10)));
        Assert.assertTrue(bigLongInterval.contains(Integer.MIN_VALUE + (1_000_000 * 100)));
        Assert.assertFalse(bigLongInterval.contains(Integer.MIN_VALUE + (1_000_001 * 100)));
        Assert.assertTrue(
                LongInterval.fromToBy(1_000_000_000, 2_000_000_000, 1_500_000_000)
                        .contains(1_000_000_000));
        Assert.assertTrue(
                LongInterval.fromToBy(-1_000_000_000, -2_000_000_000, -1_500_000_000)
                        .contains(-1_000_000_000));

        int minValue = -1_000_000_000;
        int maxValue = 1_000_000_000;
        LongInterval largeInterval = LongInterval.fromToBy(minValue, maxValue, 10);

        Assert.assertTrue(largeInterval.containsAll(
                maxValue - 10,
                maxValue - 100,
                maxValue - 1000,
                maxValue - 10000));
        Assert.assertTrue(largeInterval.contains(minValue + 10));
    }

    @Test
    public void largeReverseUnderflowTest()
    {
        LongInterval reverse = LongInterval.fromToBy(Integer.MAX_VALUE, Integer.MIN_VALUE + 10, -10);
        Assert.assertFalse(reverse.contains(Integer.MIN_VALUE + 10));
        Assert.assertEquals(Integer.MAX_VALUE, reverse.getFirst());
        int expectedLast = -2_147_483_633;
        Assert.assertEquals(expectedLast, reverse.getLast());
        Assert.assertTrue(reverse.contains(Integer.MAX_VALUE));
        Assert.assertTrue(reverse.contains(7));
        Assert.assertTrue(reverse.contains(-3));
        Assert.assertTrue(reverse.contains(expectedLast));
        Assert.assertTrue(reverse.contains(expectedLast + 1000));
        Assert.assertEquals(214_748_364, reverse.indexOf(7));
        Assert.assertEquals(214_748_365, reverse.indexOf(-3));
        Assert.assertEquals(429_496_728, reverse.indexOf(expectedLast));
        Assert.assertEquals(429_496_728, reverse.lastIndexOf(expectedLast));
        Assert.assertEquals(429_496_728, reverse.binarySearch(expectedLast));
        int expectedAtIndex300Million = -852_516_353;
        Assert.assertTrue(reverse.contains(expectedAtIndex300Million));
        Assert.assertEquals(300_000_000, reverse.indexOf(expectedAtIndex300Million));
        Assert.assertEquals(300_000_000, reverse.lastIndexOf(expectedAtIndex300Million));
        Assert.assertEquals(300_000_000, reverse.binarySearch(expectedAtIndex300Million));
        int expectedAtIndex400Million = -1_852_516_353;
        Assert.assertTrue(reverse.contains(expectedAtIndex400Million));
        Assert.assertEquals(400_000_000, reverse.indexOf(expectedAtIndex400Million));
        Assert.assertEquals(400_000_000, reverse.lastIndexOf(expectedAtIndex400Million));
        Assert.assertEquals(400_000_000, reverse.binarySearch(expectedAtIndex400Million));
    }

    @Test
    public void forwardOverflowTest()
    {
        int from = Integer.MAX_VALUE - 10;
        int second = Integer.MAX_VALUE - 2;
        long expected = (long) from + (long) second;
        LongInterval interval = LongInterval.fromToBy(from, Integer.MAX_VALUE, 8);
        Assert.assertEquals(2, interval.size());
        Assert.assertEquals(LongLists.mutable.with(from, second), interval);
        Assert.assertEquals(1, interval.count(each -> each == second));
        MutableLong result = new MutableLong();
        interval.forEach(result::add);
        Assert.assertEquals(expected, result.longValue());
        result.clear();
        interval.forEachWithIndex((each, index) -> result.add(each + index));
        Assert.assertEquals(expected + 1L, result.longValue());
        Assert.assertEquals(expected, interval.injectInto(new MutableLong(), MutableLong::add).longValue());
        Assert.assertEquals(
                expected + 1L,
                interval
                        .injectIntoWithIndex(new MutableLong(), (value, each, index) -> value.add(each + index))
                        .longValue());
    }

    @Test
    public void reverseOverflowTest()
    {
        int from = Integer.MIN_VALUE + 10;
        int second = Integer.MIN_VALUE + 2;
        long expected = (long) from + (long) second;
        LongInterval interval = LongInterval.fromToBy(from, Integer.MIN_VALUE, -8);
        Assert.assertEquals(2, interval.size());
        Assert.assertEquals(LongLists.mutable.with(from, second), interval);
        Assert.assertEquals(1, interval.count(each -> each == second));
        Assert.assertEquals(expected, interval.sum());
        MutableLong result = new MutableLong();
        interval.forEach(result::add);
        Assert.assertEquals(expected, result.longValue());
        result.clear();
        interval.forEachWithIndex((each, index) -> result.add(each + index));
        Assert.assertEquals(expected + 1L, result.longValue());
        Assert.assertEquals(expected, interval.injectInto(new MutableLong(), MutableLong::add).longValue());
        Assert.assertEquals(
                expected + 1L,
                interval
                        .injectIntoWithIndex(new MutableLong(), (value, each, index) -> value.add(each + index))
                        .longValue());
    }

    @Test
    public void intervalIterator()
    {
        LongInterval zero = LongInterval.zero();
        LongIterator zeroIterator = zero.longIterator();
        Assert.assertTrue(zeroIterator.hasNext());
        Assert.assertEquals(0, zeroIterator.next());
        Assert.assertFalse(zeroIterator.hasNext());
        LongInterval oneToFive = LongInterval.oneTo(5);
        LongIterator oneToFiveIterator = oneToFive.longIterator();
        for (int i = 1; i < 6; i++)
        {
            Assert.assertTrue(oneToFiveIterator.hasNext());
            Assert.assertEquals(i, oneToFiveIterator.next());
        }
        Assert.assertThrows(NoSuchElementException.class, oneToFiveIterator::next);
        LongInterval threeToNegativeThree = LongInterval.fromTo(3, -3);
        LongIterator threeToNegativeThreeIterator = threeToNegativeThree.longIterator();
        for (int i = 3; i > -4; i--)
        {
            Assert.assertTrue(threeToNegativeThreeIterator.hasNext());
            Assert.assertEquals(i, threeToNegativeThreeIterator.next());
        }
        Assert.assertThrows(NoSuchElementException.class, threeToNegativeThreeIterator::next);
    }

    @Test
    public void forEachWithIndex()
    {
        IntegerSum sum = new IntegerSum(0);
        LongInterval.oneTo(5).forEachWithIndex((each, index) -> sum.add(each + index));
        Assert.assertEquals(25, sum.getIntSum());
        IntegerSum zeroSum = new IntegerSum(0);
        LongInterval.fromTo(0, -4).forEachWithIndex((each, index) -> zeroSum.add(each + index));
        Assert.assertEquals(0, zeroSum.getIntSum());
    }

    @Test
    public void forEach_with_same_start_and_end_with_negative_step()
    {
        MutableLong counter = new MutableLong(0);

        LongInterval interval = LongInterval.fromToBy(2, 2, -2);
        interval.forEach((LongProcedure) each -> counter.add(1));

        Assert.assertEquals(1, counter.toLong().intValue());
    }

    @Test
    public void getFirst()
    {
        Assert.assertEquals(10, LongInterval.fromTo(10, -10).by(-5).getFirst());
        Assert.assertEquals(-10, LongInterval.fromTo(-10, 10).by(5).getFirst());
        Assert.assertEquals(0, LongInterval.zero().getFirst());
    }

    @Test
    public void getLast()
    {
        Assert.assertEquals(-10, LongInterval.fromTo(10, -10).by(-5).getLast());
        Assert.assertEquals(-10, LongInterval.fromTo(10, -12).by(-5).getLast());
        Assert.assertEquals(10, LongInterval.fromTo(-10, 10).by(5).getLast());
        Assert.assertEquals(10, LongInterval.fromTo(-10, 12).by(5).getLast());
        Assert.assertEquals(0, LongInterval.zero().getLast());
    }

    @Test
    public void indexOf()
    {
        LongInterval interval = LongInterval.fromTo(-10, 12).by(5);
        Assert.assertEquals(0, interval.indexOf(-10));
        Assert.assertEquals(1, interval.indexOf(-5));
        Assert.assertEquals(2, interval.indexOf(0));
        Assert.assertEquals(3, interval.indexOf(5));
        Assert.assertEquals(4, interval.indexOf(10));

        Assert.assertEquals(-1, interval.indexOf(-15));
        Assert.assertEquals(-1, interval.indexOf(-11));
        Assert.assertEquals(-1, interval.indexOf(-9));
        Assert.assertEquals(-1, interval.indexOf(11));
        Assert.assertEquals(-1, interval.indexOf(15));

        LongInterval backwardsLongInterval = LongInterval.fromTo(10, -12).by(-5);
        Assert.assertEquals(0, backwardsLongInterval.indexOf(10));
        Assert.assertEquals(1, backwardsLongInterval.indexOf(5));
        Assert.assertEquals(2, backwardsLongInterval.indexOf(0));
        Assert.assertEquals(3, backwardsLongInterval.indexOf(-5));
        Assert.assertEquals(4, backwardsLongInterval.indexOf(-10));

        Assert.assertEquals(-1, backwardsLongInterval.indexOf(15));
        Assert.assertEquals(-1, backwardsLongInterval.indexOf(11));
        Assert.assertEquals(-1, backwardsLongInterval.indexOf(9));
        Assert.assertEquals(-1, backwardsLongInterval.indexOf(-11));
        Assert.assertEquals(-1, backwardsLongInterval.indexOf(-15));
    }

    @Test
    public void lastIndexOf()
    {
        LongInterval interval = LongInterval.fromTo(-10, 12).by(5);
        Assert.assertEquals(0, interval.lastIndexOf(-10));
        Assert.assertEquals(1, interval.lastIndexOf(-5));
        Assert.assertEquals(2, interval.lastIndexOf(0));
        Assert.assertEquals(3, interval.lastIndexOf(5));
        Assert.assertEquals(4, interval.lastIndexOf(10));

        Assert.assertEquals(-1, interval.lastIndexOf(-15));
        Assert.assertEquals(-1, interval.lastIndexOf(-11));
        Assert.assertEquals(-1, interval.lastIndexOf(-9));
        Assert.assertEquals(-1, interval.lastIndexOf(11));
        Assert.assertEquals(-1, interval.lastIndexOf(15));

        LongInterval backwardsLongInterval = LongInterval.fromTo(10, -12).by(-5);
        Assert.assertEquals(0, backwardsLongInterval.lastIndexOf(10));
        Assert.assertEquals(1, backwardsLongInterval.lastIndexOf(5));
        Assert.assertEquals(2, backwardsLongInterval.lastIndexOf(0));
        Assert.assertEquals(3, backwardsLongInterval.lastIndexOf(-5));
        Assert.assertEquals(4, backwardsLongInterval.lastIndexOf(-10));

        Assert.assertEquals(-1, backwardsLongInterval.lastIndexOf(15));
        Assert.assertEquals(-1, backwardsLongInterval.lastIndexOf(11));
        Assert.assertEquals(-1, backwardsLongInterval.lastIndexOf(9));
        Assert.assertEquals(-1, backwardsLongInterval.lastIndexOf(-11));
        Assert.assertEquals(-1, backwardsLongInterval.lastIndexOf(-15));
    }

    @Test
    public void get()
    {
        LongInterval interval = LongInterval.fromTo(-10, 12).by(5);
        Assert.assertEquals(-10, interval.get(0));
        Assert.assertEquals(-5, interval.get(1));
        Assert.assertEquals(0, interval.get(2));
        Assert.assertEquals(5, interval.get(3));
        Assert.assertEquals(10, interval.get(4));

        Assert.assertThrows(IndexOutOfBoundsException.class, () -> interval.get(-1));
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> interval.get(5));
    }

    @Test
    public void containsAll()
    {
        Assert.assertTrue(LongInterval.fromTo(1, 3).containsAll(1, 2, 3));
        Assert.assertFalse(LongInterval.fromTo(1, 3).containsAll(1, 2, 4));
    }

    @Test
    public void containsAllIterable()
    {
        Assert.assertTrue(LongInterval.fromTo(1, 3).containsAll(LongInterval.fromTo(1, 3)));
        Assert.assertFalse(LongInterval.fromTo(1, 3).containsAll(LongInterval.fromTo(1, 4)));
    }

    @Test
    public void distinct()
    {
        Assert.assertSame(this.longInterval, this.longInterval.distinct());
    }

    @Test
    public void asReversed()
    {
        MutableLongList list = LongLists.mutable.empty();
        list.addAll(this.longInterval.asReversed());
        Assert.assertEquals(LongLists.mutable.with(3, 2, 1), list);
    }

    @Test
    public void zip()
    {
        LongInterval interval = LongInterval.oneTo(3);
        ImmutableList<LongObjectPair<String>> zipped = interval.zip(interval.collect(Long::toString));
        ImmutableList<LongObjectPair<String>> zippedLazy = interval.zip(interval.asLazy().collect(Long::toString));
        ImmutableList<LongObjectPair<String>> expected = Lists.immutable.with(
                PrimitiveTuples.pair(1L, "1"),
                PrimitiveTuples.pair(2L, "2"),
                PrimitiveTuples.pair(3L, "3"));
        Assert.assertEquals(expected, zipped);
        Assert.assertEquals(expected, zippedLazy);
        Verify.assertEmpty(interval.zip(Lists.mutable.empty()));
        Assert.assertEquals(Lists.immutable.with(PrimitiveTuples.pair(1L, "1")), interval.zip(Lists.mutable.with("1")));
    }

    @Test
    public void zipInt()
    {
        LongInterval interval = LongInterval.oneTo(3);
        ImmutableList<LongLongPair> zipped = interval.zipLong(interval.toReversed());
        ImmutableList<LongLongPair> zippedLazy = interval.zipLong(interval.asReversed());
        ImmutableList<LongLongPair> expected = Lists.immutable.with(
                PrimitiveTuples.pair(1L, 3L),
                PrimitiveTuples.pair(2L, 2L),
                PrimitiveTuples.pair(3L, 1L));
        Assert.assertEquals(expected, zipped);
        Assert.assertEquals(expected, zippedLazy);
        Verify.assertEmpty(interval.zipLong(LongLists.mutable.empty()));
        Assert.assertEquals(Lists.immutable.with(PrimitiveTuples.pair(1L, 3L)), interval.zipLong(LongLists.mutable.with(3)));
    }

    @Test
    public void primitiveStream()
    {
        Assert.assertEquals(Lists.mutable.of(1L, 2L, 3L, 4L), LongInterval.oneTo(4L).primitiveStream().boxed().collect(Collectors.toList()));
        Assert.assertEquals(Lists.mutable.of(0L, 2L, 4L), LongInterval.fromToBy(0L, 5L, 2L).primitiveStream().boxed().collect(Collectors.toList()));
        Assert.assertEquals(Lists.mutable.of(5L, 3L, 1L), LongInterval.fromToBy(5L, 0L, -2L).primitiveStream().boxed().collect(Collectors.toList()));
        Assert.assertEquals(Lists.mutable.of(10L, 15L, 20L, 25L, 30L), LongInterval.fromToBy(10L, 30L, 5L).primitiveStream().boxed().collect(Collectors.toList()));
        Assert.assertEquals(Lists.mutable.of(30L, 25L, 20L, 15L, 10L), LongInterval.fromToBy(30L, 10L, -5L).primitiveStream().boxed().collect(Collectors.toList()));
    }

    @Test
    public void primitiveParallelStream()
    {
        Assert.assertEquals(Lists.mutable.of(1L, 2L, 3L, 4L), LongInterval.oneTo(4).primitiveParallelStream().boxed().collect(Collectors.toList()));
        Assert.assertEquals(Lists.mutable.of(0L, 2L, 4L), LongInterval.fromToBy(0, 5, 2).primitiveParallelStream().boxed().collect(Collectors.toList()));
        Assert.assertEquals(Lists.mutable.of(5L, 3L, 1L, -1L, -3L), LongInterval.fromToBy(5, -4, -2).primitiveParallelStream().boxed().collect(Collectors.toList()));
        Assert.assertEquals(Lists.mutable.of(10L, 15L, 20L, 25L, 30L), LongInterval.fromToBy(10, 30, 5).primitiveParallelStream().boxed().collect(Collectors.toList()));
        Assert.assertEquals(Lists.mutable.of(30L, 25L, 20L, 15L, 10L), LongInterval.fromToBy(30, 10, -5).primitiveParallelStream().boxed().collect(Collectors.toList()));
        Assert.assertEquals(Lists.mutable.of(-1L, 10L, 21L, 32L, 43L, 54L, 65L, 76L, 87L, 98L), LongInterval.fromToBy(-1, 100, 11).primitiveParallelStream().boxed().collect(Collectors.toList()));
    }

    @Test
    public void toImmutable()
    {
        LongInterval interval = LongInterval.oneTo(5);
        Assert.assertSame(interval, interval.toImmutable());
    }

    @Test
    public void newWith()
    {
        LongInterval interval = LongInterval.oneTo(4);
        ImmutableLongList list = interval.newWith(5);
        Assert.assertNotSame(interval, list);
        Assert.assertEquals(LongInterval.oneTo(5), list);
    }

    @Test
    public void newWithout()
    {
        LongInterval interval = LongInterval.oneTo(5);
        ImmutableLongList list = interval.newWithout(5);
        Assert.assertNotSame(interval, list);
        Assert.assertEquals(LongInterval.oneTo(4), list);
    }

    @Test
    public void newWithAll()
    {
        LongInterval interval = LongInterval.oneTo(2);
        ImmutableLongList list = interval.newWithAll(LongInterval.fromTo(3, 5));
        Assert.assertNotSame(interval, list);
        Assert.assertEquals(LongInterval.oneTo(5), list);
    }

    @Test
    public void newWithoutAll()
    {
        LongInterval interval = LongInterval.oneTo(5);
        ImmutableLongList list = interval.newWithoutAll(LongInterval.fromTo(3, 5));
        Assert.assertNotSame(interval, list);
        Assert.assertEquals(LongInterval.oneTo(2), list);
    }
}
