/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test;

import org.junit.Test;

public interface NoIteratorTestCase extends RichIterableTestCase
{
    @Override
    @Test
    default void Iterable_remove()
    {
        // Not applicable
    }

    @Override
    @Test
    default void RichIterable_getFirst()
    {
        // Not applicable
    }

    @Override
    @Test
    default void RichIterable_getLast()
    {
        // Not applicable
    }

    @Override
    @Test
    default void RichIterable_getOnly()
    {
        // Not applicable
    }

    @Override
    @Test
    default void RichIterable_iterator_iterationOrder()
    {
        // Not applicable
    }

    @Override
    @Test
    default void Iterable_hasNext()
    {
        // Not applicable
    }

    @Override
    @Test
    default void Iterable_next()
    {
        // Not applicable
    }

    @Override
    @Test
    default void Iterable_next_throws_on_empty()
    {
        // Not applicable
    }

    @Override
    @Test
    default void Iterable_next_throws_at_end()
    {
        // Not applicable
    }
}
