/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.list;

import java.util.List;

import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.test.CollectionTestCase;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;

public interface ListTestCase extends CollectionTestCase
{
    @Override
    <T> List<T> newWith(T... elements);

    @Test
    default void List_get()
    {
        List<Integer> list = this.newWith(1, 2, 3);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    default void List_get_negative()
    {
        this.newWith(1, 2, 3).get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    default void List_get_out_of_bounds()
    {
        this.newWith(1, 2, 3).get(4);
    }

    @Test
    default void List_set()
    {
        List<Integer> list = this.newWith(1, 2, 3);
        assertEquals(Integer.valueOf(2), list.set(1, 4));
        assertEquals(Lists.immutable.with(1, 4, 3), list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    default void List_set_negative()
    {
        List<Integer> list = this.newWith(1, 2, 3);
        assertEquals(Integer.valueOf(2), list.set(-1, 4));
        assertEquals(Lists.immutable.with(1, 4, 3), list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    default void List_set_out_of_bounds()
    {
        List<Integer> list = this.newWith(1, 2, 3);
        assertEquals(Integer.valueOf(2), list.set(4, 4));
        assertEquals(Lists.immutable.with(1, 4, 3), list);
    }
}
