/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable;

import java.util.ListIterator;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableSubListTest extends AbstractImmutableListTestCase
{
    @Override
    protected ImmutableList<Integer> classUnderTest()
    {
        return Lists.immutable.of(0, 1, 2, 3, 4, 5, 6, 7).subList(1, 5);
    }

    @Test
    public void testSubListListIterator()
    {
        ImmutableList<Integer> subList = this.classUnderTest();
        ListIterator<Integer> iterator = subList.listIterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals(Integer.valueOf(3), iterator.previous());
        Assert.assertEquals(Integer.valueOf(2), iterator.previous());
        Assert.assertEquals(Integer.valueOf(1), iterator.previous());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSubListListIteratorSet_throws()
    {
        ImmutableList<Integer> subList = this.classUnderTest();
        ListIterator<Integer> iterator = subList.listIterator();
        iterator.set(4);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSubListListIteratorRemove_throws()
    {
        ImmutableList<Integer> subList = this.classUnderTest();
        ListIterator<Integer> iterator = subList.listIterator();
        iterator.remove();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSubListListIteratorAdd_throws()
    {
        ImmutableList<Integer> subList = this.classUnderTest();
        ListIterator<Integer> iterator = subList.listIterator();
        iterator.add(4);
    }

    @Test
    public void getOnly()
    {
        ImmutableList<Integer> list = Lists.immutable.of(1, 2, 3, 4, 5).subList(1, 2);
        Assert.assertEquals(Integer.valueOf(2), list.getOnly());
    }

    @Test(expected = IllegalStateException.class)
    public void getOnly_exception_when_empty()
    {
        ImmutableList<Integer> list = Lists.immutable.of(1, 2, 3, 4, 5).subList(1, 1);
        list.getOnly();
    }

    @Test(expected = IllegalStateException.class)
    public void getOnly_exception_when_multiple_items()
    {
        ImmutableList<Integer> list = Lists.immutable.of(1, 2, 3, 4, 5).subList(1, 3);
        list.getOnly();
    }
}
