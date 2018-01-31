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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link RandomAccessListAdapter}.
 */
public class RandomAccessListAdapterTest extends AbstractListTestCase
{
    @Override
    protected <T> RandomAccessListAdapter<T> newWith(T... littleElements)
    {
        return new RandomAccessListAdapter<>(Collections.synchronizedList(new ArrayList<>(FastList.newListWith(littleElements))));
    }

    @Override
    @Test
    public void asSynchronized()
    {
        super.asSynchronized();

        Verify.assertInstanceOf(SynchronizedMutableList.class, RandomAccessListAdapter.adapt(Collections.singletonList("1")).asSynchronized());
    }

    @Override
    @Test
    public void testClone()
    {
        MutableList<Integer> list = this.newWith(1, 2, 3);
        MutableList<Integer> list2 = list.clone();
        Verify.assertListsEqual(list, list2);
    }

    @Override
    @Test
    public void equalsAndHashCode()
    {
        super.equalsAndHashCode();

        MutableList<Integer> list1 = this.newWith(1, 2, 3);
        MutableList<Integer> list2 = this.newWith(1, 2, 3);
        MutableList<Integer> list3 = this.newWith(2, 3, 4);
        Assert.assertNotEquals(list1, null);
        Verify.assertEqualsAndHashCode(list1, list1);
        Verify.assertEqualsAndHashCode(list1, list2);
        Assert.assertNotEquals(list2, list3);
    }

    @Test
    @Override
    public void subList()
    {
        MutableList<String> list = this.newWith("A", "B", "C", "D");
        MutableList<String> sublist = list.subList(1, 3);
        Verify.assertEqualsAndHashCode(sublist, sublist);
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
        Verify.assertContainsAll(list, "A", "D");
    }

    @Override
    @Test
    public void newListWithSize()
    {
        super.newListWithSize();

        MutableList<Integer> collection = this.newWith(1, 2, 3);
        Verify.assertContainsAll(collection, 1, 2, 3);
        Verify.assertThrows(IllegalArgumentException.class, () -> new RandomAccessListAdapter<>(new LinkedList<>()));
    }

    @Override
    @Test
    public void serialization()
    {
        super.serialization();

        MutableList<Integer> collection = this.newWith(1, 2, 3, 4, 5);
        MutableList<Integer> deserializedCollection = SerializeTestHelper.serializeDeserialize(collection);
        Verify.assertSize(5, deserializedCollection);
        Verify.assertContainsAll(deserializedCollection, 1, 2, 3, 4, 5);
        Assert.assertEquals(collection, deserializedCollection);
    }

    @Override
    @Test
    public void forEachFromTo()
    {
        super.forEachFromTo();

        MutableList<Integer> result = Lists.mutable.of();
        MutableList<Integer> collection = this.newWith(1, 2, 3, 4);
        collection.forEach(2, 3, result::add);
        Verify.assertSize(2, result);
        Verify.assertContainsAll(result, 3, 4);
    }

    @Override
    @Test
    public void removeIf()
    {
        super.removeIf();

        MutableList<Integer> objects = this.newWith(1, 2, 3, null);
        Assert.assertTrue(objects.removeIf(Predicates.isNull()));
        Verify.assertSize(3, objects);
        Verify.assertContainsAll(objects, 1, 2, 3);
    }

    @Override
    @Test
    public void removeIfWith()
    {
        super.removeIf();

        MutableList<Integer> objects = this.newWith(1, 2, 3, null);
        Assert.assertTrue(objects.removeIfWith(Predicates2.isNull(), null));
        Verify.assertSize(3, objects);
        Verify.assertContainsAll(objects, 1, 2, 3);
    }

    @Test
    public void testRemoveIndex()
    {
        MutableList<Integer> objects = this.newWith(1, 2, 3);
        objects.remove(2);
        Verify.assertSize(2, objects);
        Verify.assertContainsAll(objects, 1, 2);
    }

    @Override
    @Test
    public void indexOf()
    {
        super.indexOf();

        MutableList<Integer> objects = this.newWith(1, 2, 3);
        Assert.assertEquals(1, objects.indexOf(2));
    }

    @Override
    @Test
    public void lastIndexOf()
    {
        super.lastIndexOf();

        MutableList<Integer> objects = this.newWith(1, 2, 3);
        Assert.assertEquals(1, objects.lastIndexOf(2));
    }

    @Test
    public void testSet()
    {
        MutableList<Integer> objects = this.newWith(1, 2, 3);
        Assert.assertEquals(Integer.valueOf(2), objects.set(1, 4));
        Verify.assertItemAtIndex(4, 1, objects);
    }

    @Test
    public void testAddAtIndex()
    {
        MutableList<Integer> objects = this.newWith(1, 2, 3);
        objects.add(0, 0);
        Verify.assertSize(4, objects);
        Verify.assertItemAtIndex(0, 0, objects);
    }

    @Test
    public void testAddAllAtIndex()
    {
        MutableList<Integer> objects = this.newWith(1, 2, 3);
        objects.addAll(0, Lists.fixedSize.of(0));
        Verify.assertSize(4, objects);
        Verify.assertItemAtIndex(0, 0, objects);
    }

    @Test
    public void testWithMethods()
    {
        Verify.assertContainsAll(this.newWith(1), 1);
        Verify.assertContainsAll(this.newWith(1).with(2), 1, 2);
        Verify.assertContainsAll(this.newWith(1).with(2, 3), 1, 2, 3);
        Verify.assertContainsAll(this.newWith(1).with(2, 3, 4), 1, 2, 3, 4);
        Verify.assertContainsAll(this.newWith(1).with(2, 3, 4, 5), 1, 2, 3, 4, 5);
    }

    @Override
    @Test
    public void newEmpty()
    {
        Verify.assertInstanceOf(MutableList.class, this.newWith().newEmpty());
    }

    @Test
    public void adaptNull()
    {
        Verify.assertThrows(NullPointerException.class, () -> new RandomAccessListAdapter<>(null));
        Verify.assertThrows(NullPointerException.class, () -> RandomAccessListAdapter.adapt(null));
    }

    @Test
    public void adapt()
    {
        Verify.assertInstanceOf(ArrayListAdapter.class, RandomAccessListAdapter.adapt(new ArrayList<>()));
    }
}
