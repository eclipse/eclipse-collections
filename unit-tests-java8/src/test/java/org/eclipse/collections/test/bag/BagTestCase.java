/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bag;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.ByteHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.CharHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.DoubleHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.FloatHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.IntHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.LongHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.ShortHashBag;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.CharArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ShortArrayList;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.eclipse.collections.test.RichIterableWithDuplicatesTestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public interface BagTestCase extends RichIterableWithDuplicatesTestCase
{
    @Override
    <T> Bag<T> newWith(T... elements);

    @Override
    default MutableCollection<Integer> expectedIterationOrder()
    {
        MutableCollection<Integer> forEach = this.newMutableForFilter();
        Bag<Integer> bag = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
        bag.forEachWithOccurrences((Integer each, int parameter) -> forEach.add(each));
        return forEach;
    }

    @Override
    default void RichIterable_flatCollectInt()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        IntHashBag result = new IntHashBag();
        bag.flatCollectInt(IntArrayList::newListWith, result);
        assertEquals(IntHashBag.newBagWith(3, 3, 3, 2, 2, 1), result);
    }

    @Override
    default void RichIterable_flatCollectChar()
    {
        Bag<Character> bag = this.newWith('3', '3', '3', '2', '2', '1');
        CharHashBag result = new CharHashBag();
        bag.flatCollectChar(CharArrayList::newListWith, result);
        assertEquals(CharHashBag.newBagWith('3', '3', '3', '2', '2', '1'), result);
    }

    @Override
    default void RichIterable_flatCollectLong()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        LongHashBag result = new LongHashBag();
        bag.flatCollectLong(LongArrayList::newListWith, result);
        assertEquals(LongHashBag.newBagWith(3, 3, 3, 2, 2, 1), result);
    }

    @Override
    default void RichIterable_flatCollectFloat()
    {
        Bag<Float> bag = this.newWith((float) 3, (float) 3, (float) 3, (float) 2, (float) 2, (float) 1);
        FloatHashBag result = new FloatHashBag();
        bag.flatCollectFloat(FloatArrayList::newListWith, result);
        assertEquals(FloatHashBag.newBagWith((float) 3, (float) 3, (float) 3, (float) 2, (float) 2, (float) 1), result);
    }

    @Override
    default void RichIterable_flatCollectDouble()
    {
        Bag<Double> bag = this.newWith((double) 3, (double) 3, (double) 3, (double) 2, (double) 2, (double) 1);
        DoubleHashBag result = new DoubleHashBag();
        bag.flatCollectDouble(DoubleArrayList::newListWith, result);
        assertEquals(DoubleHashBag.newBagWith((double) 3, (double) 3, (double) 3, (double) 2, (double) 2, (double) 1), result);
    }

    @Override
    default void RichIterable_flatCollectShort()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        ShortHashBag result = new ShortHashBag();
        bag.flatCollectShort(each -> ShortArrayList.newListWith(each.shortValue()), result);
        assertEquals(ShortHashBag.newBagWith((short) 3, (short) 3, (short) 3, (short) 2, (short) 2, (short) 1), result);
    }

    @Override
    default void RichIterable_flatCollectByte()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        ByteHashBag result = new ByteHashBag();
        bag.flatCollectByte(each -> ByteArrayList.newListWith(each.byteValue()), result);
        assertEquals(ByteHashBag.newBagWith((byte) 3, (byte) 3, (byte) 3, (byte) 2, (byte) 2, (byte) 1), result);
    }

    @Override
    default void RichIterable_flatCollectBoolean()
    {
        Bag<Boolean> bag = this.newWith(true, false, false, false);
        BooleanHashBag result = new BooleanHashBag();
        bag.flatCollectBoolean(BooleanArrayList::newListWith, result);
        assertEquals(BooleanHashBag.newBagWith(true, false, false, false), result);
    }

    @Override
    default void RichIterable_iterator_iterationOrder()
    {
        MutableCollection<Integer> iterationOrder = this.newMutableForFilter();
        Iterator<Integer> iterator = this.getInstanceUnderTest().iterator();
        while (iterator.hasNext())
        {
            iterationOrder.add(iterator.next());
        }
        assertEquals(RichIterableWithDuplicatesTestCase.super.expectedIterationOrder(), iterationOrder);

        MutableCollection<Integer> forEachWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().forEachWith((each, param) -> forEachWithIterationOrder.add(each), null);
        assertEquals(RichIterableWithDuplicatesTestCase.super.expectedIterationOrder(), forEachWithIterationOrder);

        MutableCollection<Integer> forEachWithIndexIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().forEachWithIndex((each, index) -> forEachWithIndexIterationOrder.add(each));
        assertEquals(RichIterableWithDuplicatesTestCase.super.expectedIterationOrder(), forEachWithIndexIterationOrder);
    }

    @Test
    default void Bag_anySatisfyWithOccurrences()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        assertTrue(bag.anySatisfyWithOccurrences((object, value) -> object.equals(3) && value == 3));
        assertTrue(bag.anySatisfyWithOccurrences((object, value) -> object.equals(2) && value == 2));
        assertTrue(bag.anySatisfyWithOccurrences((object, value) -> object.equals(3)));

        assertFalse(bag.anySatisfyWithOccurrences((object, value) -> object.equals(2) && value == 5));
        assertFalse(bag.anySatisfyWithOccurrences((object, value) -> object.equals(1) && value == 7));
        assertFalse(bag.anySatisfyWithOccurrences((object, value) -> object.equals(10)));
    }

    @Test
    default void Bag_noneSatisfyWithOccurrences()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        assertTrue(bag.noneSatisfyWithOccurrences((object, value) -> object.equals(3) && value == 1));
        assertTrue(bag.noneSatisfyWithOccurrences((object, value) -> object.equals(30)));
        assertFalse(bag.noneSatisfyWithOccurrences((object, value) -> object.equals(3) && value == 3));
        assertTrue(bag.noneSatisfyWithOccurrences((object, value) -> object.equals(1) && value == 0));
        assertFalse(bag.noneSatisfyWithOccurrences((object, value) -> object.equals(1) && value == 1));
        assertFalse(bag.noneSatisfyWithOccurrences((object, value) -> object.equals(2)));
    }

    @Test
    default void Bag_allSatisfyWithOccurrences()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3);
        assertTrue(bag.allSatisfyWithOccurrences((object, value) -> object.equals(3) && value == 3));
        assertTrue(bag.allSatisfyWithOccurrences((object, value) -> object.equals(3)));
        assertFalse(bag.allSatisfyWithOccurrences((object, value) -> object.equals(4) && value == 3));
        bag = this.newWith(3, 3, 3, 1);
        assertFalse(bag.allSatisfyWithOccurrences((object, value) -> object.equals(3) && value == 3));
        assertFalse(bag.allSatisfyWithOccurrences((object, value) -> object.equals(1) && value == 3));
        assertTrue(bag.allSatisfyWithOccurrences((object, value) -> object.equals(3) || object.equals(1)));
        assertFalse(bag.allSatisfyWithOccurrences((object, value) -> object.equals(300) || object.equals(1)));
    }

    @Test
    default void Bag_detectWithOccurrences()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        assertEquals(3, bag.detectWithOccurrences((object, value) -> object.equals(3) && value == 3));
        assertEquals(3, bag.detectWithOccurrences((object, value) -> object.equals(3)));
        assertEquals(1, bag.detectWithOccurrences((object, value) -> object.equals(1) && value == 1));
        assertNull(bag.detectWithOccurrences((object, value) -> object.equals(1) && value == 10));
        assertNull(bag.detectWithOccurrences((object, value) -> object.equals(10) && value == 5));
        assertNull(bag.detectWithOccurrences((object, value) -> object.equals(100)));
    }

    @Test
    default void Bag_sizeDistinct()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        assertEquals(3, bag.sizeDistinct());
    }

    @Test
    default void Bag_occurrencesOf()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        assertEquals(0, bag.occurrencesOf(0));
        assertEquals(1, bag.occurrencesOf(1));
        assertEquals(2, bag.occurrencesOf(2));
        assertEquals(3, bag.occurrencesOf(3));
    }

    @Test
    default void Bag_toStringOfItemToCount()
    {
        assertEquals("{}", this.newWith().toStringOfItemToCount());
        assertThat(this.newWith(2, 2, 1).toStringOfItemToCount(), isOneOf("{1=1, 2=2}", "{2=2, 1=1}"));
    }

    /**
     * @since 9.1.
     */
    @Test
    default void Bag_collectWithOccurrences()
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
}
