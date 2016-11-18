/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.set.immutable;

import java.util.Set;

import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.test.collection.immutable.ImmutableCollectionUniqueTestCase;
import org.eclipse.collections.test.set.UnsortedSetIterableTestCase;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

public interface ImmutableSetTestCase extends ImmutableCollectionUniqueTestCase, UnsortedSetIterableTestCase
{
    @Override
    <T> ImmutableSet<T> newWith(T... elements);

    @Override
    @Test
    default void Iterable_remove()
    {
        ImmutableCollectionUniqueTestCase.super.Iterable_remove();
    }

    @Override
    default void ImmutableCollection_newWith()
    {
        ImmutableCollection<Integer> immutableCollection = this.newWith(3, 2, 1);
        ImmutableCollection<Integer> newWith = immutableCollection.newWith(4);

        assertEquals(this.newWith(3, 2, 1, 4), newWith);
        assertNotSame(immutableCollection, newWith);
        assertThat(newWith, instanceOf(ImmutableCollection.class));

        ImmutableCollection<Integer> newWith2 = newWith.newWith(4);

        assertEquals(this.newWith(3, 2, 1, 4), newWith2);
        assertSame(newWith, newWith2);
    }

    @Test
    default void ImmutableSet_castToSet()
    {
        ImmutableSet<Integer> immutableSet = this.newWith(3, 2, 1);
        Set<Integer> set = immutableSet.castToSet();
        assertSame(immutableSet, set);
    }
}
