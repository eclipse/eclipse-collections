/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.set;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.UnsortedSetIterable;
import org.eclipse.collections.impl.factory.Sets;
import org.junit.Test;

import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertThat;

public interface UnsortedSetIterableTestCase extends SetIterableTestCase, TransformsToUnsortedSetTrait, UnsortedSetLikeTestTrait
{
    @Override
    <T> UnsortedSetIterable<T> newWith(T... elements);

    @Override
    default <T> UnsortedSetIterable<T> getExpectedTransformed(T... elements)
    {
        return Sets.immutable.with(elements);
    }

    @Override
    default <T> MutableSet<T> newMutableForTransform(T... elements)
    {
        return Sets.mutable.with(elements);
    }

    @Override
    @Test
    default void RichIterable_toString()
    {
        RichIterable<Integer> iterable = this.newWith(3, 2, 1);
        assertThat(iterable.toString(), isOneOf(
                "[3, 2, 1]",
                "[3, 1, 2]",
                "[2, 3, 1]",
                "[2, 1, 3]",
                "[1, 3, 2]",
                "[1, 2, 3]"));
    }

    @Override
    @Test
    default void RichIterable_toArray()
    {
        UnsortedSetLikeTestTrait.super.RichIterable_toArray();
    }
}
