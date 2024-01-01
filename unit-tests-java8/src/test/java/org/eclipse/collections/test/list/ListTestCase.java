/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.list;

import java.util.Iterator;
import java.util.List;

import org.eclipse.collections.test.CollectionTestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertThrows;

public interface ListTestCase extends CollectionTestCase
{
    @Override
    <T> List<T> newWith(T... elements);

    @Override
    default boolean allowsDuplicates()
    {
        return true;
    }

    @Test
    @Override
    default void Iterable_remove()
    {
        List<Integer> list = this.newWith(3, 3, 3, 2, 2, 1);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        assertEquals(this.newWith(3, 3, 2, 2, 1), list);
    }

    @Override
    default void Iterable_toString()
    {
        Iterable<Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);
        Assert.assertEquals("[3, 3, 3, 2, 2, 1]", iterable.toString());
    }

    @Test
    default void List_get()
    {
        List<Integer> list = this.newWith(1, 2, 3);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
    }

    @Test
    default void List_get_negative()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.newWith(1, 2, 3).get(-1));
    }

    @Test
    default void List_get_out_of_bounds()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.newWith(1, 2, 3).get(4));
    }

    @Test
    default void List_set()
    {
        List<Integer> list = this.newWith(3, 2, 1);
        assertEquals(3, list.set(0, 4));
        assertEquals(this.newWith(4, 2, 1), list);
        assertEquals(2, list.set(1, 4));
        assertEquals(this.newWith(4, 4, 1), list);
        assertEquals(1, list.set(2, 4));
        assertEquals(this.newWith(4, 4, 4), list);

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 4));
        assertEquals(this.newWith(4, 4, 4), list);
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, 4));
        assertEquals(this.newWith(4, 4, 4), list);
    }
}
