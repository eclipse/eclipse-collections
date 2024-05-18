/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable;

import java.util.ListIterator;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertTrue(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertTrue(iterator.hasPrevious());
        assertEquals(Integer.valueOf(3), iterator.previous());
        assertEquals(Integer.valueOf(2), iterator.previous());
        assertEquals(Integer.valueOf(1), iterator.previous());
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
        assertEquals(Integer.valueOf(2), list.getOnly());
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
