/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import static org.eclipse.collections.impl.test.Verify.assertContains;
import static org.eclipse.collections.impl.test.Verify.assertNotContains;
import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public interface CollectionTestCase extends IterableTestCase, CollisionsTestCase
{
    @Override
    <T> Collection<T> newWith(T... elements);

    @Test
    default void Collection_size()
    {
        if (this.allowsDuplicates())
        {
            Collection<Integer> collection = this.newWith(3, 3, 3, 2, 2, 1);
            assertThat(collection, hasSize(6));
        }
        else
        {
            Collection<Integer> collection = this.newWith(3, 2, 1);
            assertThat(collection, hasSize(3));
        }
        assertThat(this.newWith(), hasSize(0));
    }

    @Test
    default void Collection_contains()
    {
        Collection<Integer> collection = this.newWith(3, 2, 1);
        assertTrue(collection.contains(1));
        assertTrue(collection.contains(2));
        assertTrue(collection.contains(3));
        assertFalse(collection.contains(4));
        assertFalse(collection.contains(0));
        assertFalse(collection.contains(-1));
        assertFalse(collection.contains(Integer.MAX_VALUE));
        assertFalse(collection.contains(Integer.MIN_VALUE));
    }

    @Test
    default void Collection_add()
    {
        Collection<Integer> collection = this.newWith(3, 2, 1);
        assertTrue(collection.add(4));
        assertTrue(collection.contains(4));
        assertTrue(collection.contains(3));
        assertTrue(collection.contains(2));
        assertTrue(collection.contains(1));
        assertEquals(this.allowsDuplicates(), collection.add(4));
        assertTrue(collection.contains(4));
        if (this.allowsDuplicates())
        {
            assertEquals(this.newWith(3, 2, 1, 4, 4), collection);
        }
        else
        {
            assertEquals(this.newWith(3, 2, 1, 4), collection);
        }

        Collection<Integer> collection2 = this.newWith();
        for (Integer each : COLLISIONS)
        {
            assertFalse(collection2.contains(each));
            assertTrue(collection2.add(each));
            assertTrue(collection2.contains(each));
            assertEquals(this.allowsDuplicates(), collection2.add(each));
            assertTrue(collection2.contains(each));
        }
    }

    @Test
    default void Collection_remove()
    {
        {
            Collection<Integer> collection = this.newWith(3, 2, 1);
            assertFalse(collection.remove(4));
            assertEquals(this.newWith(3, 2, 1), collection);
            assertTrue(collection.remove(3));
            assertEquals(this.newWith(2, 1), collection);
            assertTrue(collection.remove(2));
            assertEquals(this.newWith(1), collection);
            assertTrue(collection.remove(1));
            assertEquals(this.newWith(), collection);
        }

        if (this.allowsDuplicates())
        {
            Collection<Integer> collection = this.newWith(3, 3, 3, 2, 2, 1);
            assertTrue(collection.remove(3));
            assertTrue(collection.remove(2));
            assertTrue(collection.remove(1));
            assertEquals(this.newWith(3, 3, 2), collection);

            assertTrue(collection.remove(3));
            assertTrue(collection.remove(2));
            assertFalse(collection.remove(1));
            assertEquals(this.newWith(3), collection);

            assertTrue(collection.remove(3));
            assertFalse(collection.remove(2));
            assertFalse(collection.remove(1));
            assertEquals(this.newWith(), collection);

            assertFalse(collection.remove(3));
            assertFalse(collection.remove(2));
            assertFalse(collection.remove(1));
            assertEquals(this.newWith(), collection);
        }

        Integer[] array = COLLISIONS.toArray(new Integer[]{});

        {
            Collection<Integer> collection = this.newWith(array);
            for (Integer each : COLLISIONS)
            {
                assertContains(each, collection);
                assertTrue(collection.remove(each));
                assertNotContains(each, collection);
                assertFalse(collection.remove(each));
                assertNotContains(each, collection);
            }
        }

        if (this.allowsDuplicates())
        {
            Collection<Integer> collection = this.newWith(CollectionTestCase.concat(array, array));
            for (Integer each : COLLISIONS)
            {
                assertContains(each, collection);
                assertTrue(collection.remove(each));
                assertTrue(collection.contains(each));
                assertTrue(collection.remove(each));
                assertNotContains(each, collection);
                assertFalse(collection.remove(each));
                assertNotContains(each, collection);
            }
        }
    }

    static <T> T[] concat(T[] first, T[] second)
    {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    @Test
    default void Collection_clear()
    {
        Collection<Integer> collection = this.newWith(1, 2, 3);
        assertThat(collection, is(not(empty())));
        collection.clear();
        assertThat(collection, is(empty()));
        assertThat(collection, hasSize(0));
        collection.clear();
        assertThat(collection, is(empty()));
    }
}
