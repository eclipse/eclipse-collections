/*
 * Copyright (c) 2022 The Bank of New York Mellon and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stream;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.eclipse.collections.api.bag.primitive.ImmutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.ImmutableIntBag;
import org.eclipse.collections.api.bag.primitive.ImmutableLongBag;
import org.eclipse.collections.api.bag.primitive.MutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.bag.primitive.MutableLongBag;
import org.eclipse.collections.api.list.primitive.ImmutableDoubleList;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.list.primitive.ImmutableLongList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.set.primitive.ImmutableDoubleSet;
import org.eclipse.collections.api.set.primitive.ImmutableIntSet;
import org.eclipse.collections.api.set.primitive.ImmutableLongSet;
import org.eclipse.collections.api.set.primitive.MutableDoubleSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.set.primitive.MutableLongSet;
import org.eclipse.collections.api.stack.primitive.ImmutableDoubleStack;
import org.eclipse.collections.api.stack.primitive.ImmutableIntStack;
import org.eclipse.collections.api.stack.primitive.ImmutableLongStack;
import org.eclipse.collections.api.stack.primitive.MutableDoubleStack;
import org.eclipse.collections.api.stack.primitive.MutableIntStack;
import org.eclipse.collections.api.stack.primitive.MutableLongStack;
import org.eclipse.collections.impl.factory.primitive.DoubleBags;
import org.eclipse.collections.impl.factory.primitive.DoubleLists;
import org.eclipse.collections.impl.factory.primitive.DoubleSets;
import org.eclipse.collections.impl.factory.primitive.DoubleStacks;
import org.eclipse.collections.impl.factory.primitive.IntBags;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.factory.primitive.IntStacks;
import org.eclipse.collections.impl.factory.primitive.LongBags;
import org.eclipse.collections.impl.factory.primitive.LongLists;
import org.eclipse.collections.impl.factory.primitive.LongSets;
import org.eclipse.collections.impl.factory.primitive.LongStacks;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.list.primitive.LongInterval;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @since 9.0
 */
public class PrimitiveStreamsTest
{
    @Test
    public void toIntList()
    {
        MutableIntList list = PrimitiveStreams.mIntList(IntStream.rangeClosed(1, 10));
        assertEquals(IntInterval.oneTo(10), list);
        assertEquals(IntLists.immutable.ofAll(IntStream.rangeClosed(1, 10)), list);
    }

    @Test
    public void toImmutableIntList()
    {
        ImmutableIntList list = PrimitiveStreams.iIntList(IntStream.rangeClosed(1, 10));
        assertEquals(IntInterval.oneTo(10), list);
        assertEquals(IntLists.mutable.ofAll(IntStream.rangeClosed(1, 10)), list);
    }

    @Test
    public void toIntSet()
    {
        MutableIntSet set = PrimitiveStreams.mIntSet(IntStream.rangeClosed(1, 10));
        assertEquals(IntInterval.oneTo(10).toSet(), set);
        assertEquals(IntSets.immutable.ofAll(IntStream.rangeClosed(1, 10)), set);
    }

    @Test
    public void toImmutableIntSet()
    {
        ImmutableIntSet set = PrimitiveStreams.iIntSet(IntStream.rangeClosed(1, 10));
        assertEquals(IntInterval.oneTo(10).toSet(), set);
        assertEquals(IntSets.mutable.ofAll(IntStream.rangeClosed(1, 10)), set);
    }

    @Test
    public void toIntBag()
    {
        MutableIntBag bag = PrimitiveStreams.mIntBag(IntStream.rangeClosed(1, 10));
        assertEquals(IntInterval.oneTo(10).toBag(), bag);
        assertEquals(IntBags.immutable.ofAll(IntStream.rangeClosed(1, 10)), bag);
    }

    @Test
    public void toImmutableIntBag()
    {
        ImmutableIntBag bag = PrimitiveStreams.iIntBag(IntStream.rangeClosed(1, 10));
        assertEquals(IntInterval.oneTo(10).toBag(), bag);
        assertEquals(IntBags.mutable.ofAll(IntStream.rangeClosed(1, 10)), bag);
    }

    @Test
    public void toEmptyImmutableIntBag()
    {
        ImmutableIntBag bag = PrimitiveStreams.iIntBag(IntStream.empty());
        assertEquals(IntBags.immutable.empty(), bag);
    }

    @Test
    public void toImmutableIntBagWithOneElement()
    {
        ImmutableIntBag bag = PrimitiveStreams.iIntBag(IntStream.rangeClosed(1, 1));
        assertEquals(IntInterval.oneTo(1).toBag(), bag);
        assertEquals(IntBags.mutable.ofAll(IntStream.rangeClosed(1, 1)), bag);
    }

    @Test
    public void toIntStack()
    {
        MutableIntStack stack = PrimitiveStreams.mIntStack(IntStream.rangeClosed(1, 10));
        assertEquals(IntStacks.mutable.withAll(IntInterval.oneTo(10)), stack);
        assertEquals(IntStacks.immutable.ofAll(IntStream.rangeClosed(1, 10)), stack);
    }

    @Test
    public void toImmutableIntStack()
    {
        ImmutableIntStack stack = PrimitiveStreams.iIntStack(IntStream.rangeClosed(1, 10));
        assertEquals(IntStacks.immutable.withAll(IntInterval.oneTo(10)), stack);
        assertEquals(IntStacks.mutable.ofAll(IntStream.rangeClosed(1, 10)), stack);
    }

    @Test
    public void toLongList()
    {
        MutableLongList list = PrimitiveStreams.mLongList(LongStream.rangeClosed(1, 10));
        assertEquals(IntInterval.oneTo(10).collectLong(i -> (long) i, LongLists.mutable.empty()), list);
        assertEquals(LongLists.immutable.ofAll(LongStream.rangeClosed(1, 10)), list);
    }

    @Test
    public void toImmutableLongList()
    {
        ImmutableLongList list = PrimitiveStreams.iLongList(LongStream.rangeClosed(1, 10));
        assertEquals(IntInterval.oneTo(10).collectLong(i -> (long) i, LongLists.mutable.empty()), list);
        assertEquals(LongLists.mutable.ofAll(LongStream.rangeClosed(1, 10)), list);
    }

    @Test
    public void toLongSet()
    {
        MutableLongSet set = PrimitiveStreams.mLongSet(LongStream.rangeClosed(1, 10));
        assertEquals(IntInterval.oneTo(10).collectLong(i -> (long) i, LongSets.mutable.empty()), set);
        assertEquals(LongSets.immutable.ofAll(LongStream.rangeClosed(1, 10)), set);
    }

    @Test
    public void toImmutableLongSet()
    {
        ImmutableLongSet set = PrimitiveStreams.iLongSet(LongStream.rangeClosed(1, 10));
        assertEquals(IntInterval.oneTo(10).collectLong(i -> (long) i, LongSets.mutable.empty()), set);
        assertEquals(LongSets.mutable.ofAll(LongStream.rangeClosed(1, 10)), set);
    }

    @Test
    public void toLongBag()
    {
        MutableLongBag bag = PrimitiveStreams.mLongBag(LongStream.rangeClosed(1, 10));
        assertEquals(IntInterval.oneTo(10).collectLong(i -> (long) i, LongBags.mutable.empty()), bag);
        assertEquals(LongBags.immutable.ofAll(LongStream.rangeClosed(1, 10)), bag);
    }

    @Test
    public void toImmutableLongBag()
    {
        ImmutableLongBag bag = PrimitiveStreams.iLongBag(LongStream.rangeClosed(1, 10));
        assertEquals(IntInterval.oneTo(10).collectLong(i -> (long) i, LongBags.mutable.empty()), bag);
        assertEquals(LongBags.mutable.ofAll(LongStream.rangeClosed(1, 10)), bag);
    }

    @Test
    public void toEmptyImmutableLongBag()
    {
        ImmutableLongBag bag = PrimitiveStreams.iLongBag(LongStream.empty());
        assertEquals(LongBags.immutable.empty(), bag);
    }

    @Test
    public void toImmutableLongBagWithOneElement()
    {
        ImmutableLongBag bag = PrimitiveStreams.iLongBag(LongStream.rangeClosed(1, 1));
        assertEquals(LongInterval.oneTo(1).toBag(), bag);
        assertEquals(LongBags.mutable.ofAll(LongStream.rangeClosed(1, 1)), bag);
    }

    @Test
    public void toLongStack()
    {
        MutableLongStack stack = PrimitiveStreams.mLongStack(LongStream.rangeClosed(1, 10));
        assertEquals(LongStacks.mutable.ofAll(IntInterval.oneTo(10).asLazy().collectLong(i -> (long) i)), stack);
        assertEquals(LongStacks.immutable.ofAll(LongStream.rangeClosed(1, 10)), stack);
    }

    @Test
    public void toImmutableLongStack()
    {
        ImmutableLongStack stack = PrimitiveStreams.iLongStack(LongStream.rangeClosed(1, 10));
        assertEquals(LongStacks.mutable.ofAll(IntInterval.oneTo(10).asLazy().collectLong(i -> (long) i)), stack);
        assertEquals(LongStacks.mutable.ofAll(LongStream.rangeClosed(1, 10)), stack);
    }

    @Test
    public void toDoubleList()
    {
        MutableDoubleList list = PrimitiveStreams.mDoubleList(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0));
        assertEquals(DoubleLists.mutable.ofAll(IntInterval.oneTo(10).asLazy().collectDouble(i -> (double) i)), list);
        assertEquals(DoubleLists.immutable.ofAll(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)), list);
    }

    @Test
    public void toImmutableDoubleList()
    {
        ImmutableDoubleList list = PrimitiveStreams.iDoubleList(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0));
        assertEquals(DoubleLists.mutable.ofAll(IntInterval.oneTo(10).asLazy().collectDouble(i -> (double) i)), list);
        assertEquals(DoubleLists.mutable.ofAll(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)), list);
    }

    @Test
    public void toDoubleSet()
    {
        MutableDoubleSet set = PrimitiveStreams.mDoubleSet(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0));
        assertEquals(DoubleSets.mutable.ofAll(IntInterval.oneTo(10).asLazy().collectDouble(i -> (double) i)), set);
        assertEquals(DoubleSets.immutable.ofAll(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)), set);
    }

    @Test
    public void toImmutableDoubleSet()
    {
        ImmutableDoubleSet set = PrimitiveStreams.iDoubleSet(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0));
        assertEquals(DoubleSets.mutable.ofAll(IntInterval.oneTo(10).asLazy().collectDouble(i -> (double) i)), set);
        assertEquals(DoubleSets.mutable.ofAll(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)), set);
    }

    @Test
    public void toDoubleBag()
    {
        MutableDoubleBag bag = PrimitiveStreams.mDoubleBag(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0));
        assertEquals(DoubleBags.mutable.ofAll(IntInterval.oneTo(10).asLazy().collectDouble(i -> (double) i)), bag);
        assertEquals(DoubleBags.immutable.ofAll(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)), bag);
    }

    @Test
    public void toImmutableDoubleBag()
    {
        ImmutableDoubleBag bag = PrimitiveStreams.iDoubleBag(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0));
        assertEquals(DoubleBags.mutable.ofAll(IntInterval.oneTo(10).asLazy().collectDouble(i -> (double) i)), bag);
        assertEquals(DoubleBags.mutable.ofAll(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)), bag);
    }

    @Test
    public void toEmptyImmutableDoubleBag()
    {
        ImmutableDoubleBag bag = PrimitiveStreams.iDoubleBag(DoubleStream.empty());
        assertEquals(DoubleBags.immutable.empty(), bag);
    }

    @Test
    public void toImmutableDoubleBagWithOneElement()
    {
        ImmutableDoubleBag bag = PrimitiveStreams.iDoubleBag(DoubleStream.of(1.0));
        assertEquals(DoubleBags.mutable.ofAll(DoubleStream.of(1.0)), bag);
    }

    @Test
    public void toDoubleStack()
    {
        MutableDoubleStack stack = PrimitiveStreams.mDoubleStack(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0));
        assertEquals(DoubleStacks.mutable.ofAll(IntInterval.oneTo(10).asLazy().collectDouble(i -> (double) i)), stack);
        assertEquals(DoubleStacks.immutable.ofAll(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)), stack);
    }

    @Test
    public void toImmutableDoubleStack()
    {
        ImmutableDoubleStack stack = PrimitiveStreams.iDoubleStack(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0));
        assertEquals(DoubleStacks.mutable.ofAll(IntInterval.oneTo(10).asLazy().collectDouble(i -> (double) i)), stack);
        assertEquals(DoubleStacks.mutable.ofAll(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)), stack);
    }
}
