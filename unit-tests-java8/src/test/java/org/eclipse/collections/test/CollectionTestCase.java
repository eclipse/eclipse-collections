/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.test;

import java.util.Collection;

import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public interface CollectionTestCase extends IterableTestCase
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
    }

    @Test
    default void Collection_add()
    {
        Collection<Integer> collection = this.newWith(3, 2, 1);
        assertTrue(collection.add(4));
        assertEquals(this.allowsDuplicates(), collection.add(4));
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
