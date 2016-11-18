/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.set.immutable.sorted;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.test.collection.immutable.ImmutableCollectionTestCase;
import org.eclipse.collections.test.set.sorted.SortedSetIterableTestCase;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

public interface ImmutableSortedSetIterableTestCase extends SortedSetIterableTestCase, ImmutableCollectionTestCase
{
    @Override
    <T> ImmutableSortedSet<T> newWith(T... elements);

    @Override
    @Test
    default void ImmutableCollection_newWith()
    {
        ImmutableSortedSet<Integer> immutableCollection = this.newWith(3, 2, 1);
        ImmutableSortedSet<Integer> newWith = immutableCollection.newWith(4);

        assertEquals(this.newWith(4, 3, 2, 1).castToSortedSet(), newWith.castToSortedSet());
        assertNotSame(immutableCollection, newWith);
        assertThat(newWith, instanceOf(ImmutableSortedSet.class));

        ImmutableSortedSet<Integer> newWith2 = newWith.newWith(4);
        assertSame(newWith, newWith2);
        assertEquals(this.newWith(4, 3, 2, 1).castToSortedSet(), newWith2.castToSortedSet());
    }
}
