/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test for {@link ArrayListAdapter}.
 */
public class ArrayListAdapterTest extends AbstractListTestCase
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ArrayListAdapterTest.class);

    @Override
    protected <T> ArrayListAdapter<T> newWith(T... littleElements)
    {
        return ArrayListAdapter.<T>newList().with(littleElements);
    }

    @Override
    @Test
    public void testClone()
    {
        super.testClone();

        MutableList<Integer> list = this.newWith(1, 2, 3);
        MutableList<Integer> list2 = list.clone();
        Verify.assertListsEqual(list, list2);
    }

    @Override
    @Test
    public void newListWithSize()
    {
        super.newListWithSize();

        MutableList<Integer> objects = ArrayListAdapter.<Integer>newList(4).with(1, 2, 3);
        Assert.assertEquals(1, objects.indexOf(2));
    }

    @Override
    @Test
    public void removeIf()
    {
        super.removeIf();

        MutableList<Integer> objects = this.newWith(1, 2, 3, null);
        objects.removeIf(Predicates.isNull());
        Verify.assertSize(3, objects);
        Verify.assertContainsAll(objects, 1, 2, 3);
    }

    @Test
    public void testBAOSSize()
    {
        MutableList<Integer> mutableArrayList = ArrayListAdapter.newList();

        ByteArrayOutputStream stream1 = SerializeTestHelper.getByteArrayOutputStream(mutableArrayList);
        LOGGER.info("ArrayListAdapter size: {}", stream1.size());
        LOGGER.info("{}", stream1);
        Assert.assertTrue(stream1.size() > 0);

        List<Integer> arrayList = new ArrayList<>();
        ByteArrayOutputStream stream2 = SerializeTestHelper.getByteArrayOutputStream(arrayList);
        LOGGER.info("ArrayList size: {}", stream2.size());
        LOGGER.info("{}", stream2);
        Assert.assertTrue(stream2.size() > 0);
    }

    @Test
    public void testWithMethods()
    {
        Verify.assertContainsAll(ArrayListAdapter.newList().with(1), 1);
        Verify.assertContainsAll(ArrayListAdapter.newList().with(1, 2), 1, 2);
        Verify.assertContainsAll(ArrayListAdapter.newList().with(1, 2, 3), 1, 2, 3);
        Verify.assertContainsAll(ArrayListAdapter.newList().with(1, 2, 3, 4), 1, 2, 3, 4);
    }

    @Override
    @Test
    public void newEmpty()
    {
        Verify.assertInstanceOf(ArrayListAdapter.class, ArrayListAdapter.newList().newEmpty());
    }

    @Test
    public void testForEachWithFromToWithCommandoPatternOptimization()
    {
        MutableList<Integer> result2 = Lists.mutable.of();
        // Requires list of 100+ elements to engage commando pattern optimization
        ArrayListAdapter.adapt(new ArrayList<>(Interval.oneTo(200))).forEach(99, 199, CollectionAddProcedure.on(result2));
        Verify.assertSize(101, result2);
    }

    @Test
    public void testForEachWithIndexWithFromToWithCommandoPatternOptimization()
    {
        MutableList<Integer> result2 = Lists.mutable.of();
        // Requires list of 100+ elements to engage commando pattern optimization
        ArrayListAdapter.adapt(new ArrayList<>(Interval.oneTo(200))).forEachWithIndex(99, 199, new AddToList(result2));
        Verify.assertSize(101, result2);
    }

    @Test
    @Override
    public void subList()
    {
        // Not serializable

        MutableList<String> list = this.newWith("A", "B", "C", "D");
        MutableList<String> sublist = list.subList(1, 3);
        Verify.assertSize(2, sublist);
        Verify.assertContainsAll(sublist, "B", "C");
        sublist.add("X");
        Verify.assertSize(3, sublist);
        Verify.assertContainsAll(sublist, "B", "C", "X");
        Verify.assertSize(5, list);
        Verify.assertContainsAll(list, "A", "B", "C", "X", "D");
        sublist.remove("X");
        Verify.assertContainsAll(sublist, "B", "C");
        Verify.assertContainsAll(list, "A", "B", "C", "D");
        Assert.assertEquals("C", sublist.set(1, "R"));
        Verify.assertContainsAll(sublist, "B", "R");
        Verify.assertContainsAll(list, "A", "B", "R", "D");
        sublist.addAll(Arrays.asList("W", "G"));
        Verify.assertContainsAll(sublist, "B", "R", "W", "G");
        Verify.assertContainsAll(list, "A", "B", "R", "W", "G", "D");
        sublist.clear();
        Verify.assertEmpty(sublist);
        Assert.assertFalse(sublist.remove("X"));
        Verify.assertEmpty(sublist);
        Verify.assertContainsAll(list, "A", "D");
    }

    @Test
    public void adapt_null()
    {
        Verify.assertThrows(NullPointerException.class, () -> ArrayListAdapter.adapt(null));
    }
}
