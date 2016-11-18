/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bimap.immutable;

import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.test.ImmutableUnorderedIterableTestCase;
import org.eclipse.collections.test.bimap.UnsortedBiMapTestCase;

public interface ImmutableUnsortedBiMapTestCase extends UnsortedBiMapTestCase, ImmutableUnorderedIterableTestCase
{
    @Override
    <T> ImmutableBiMap<Object, T> newWith(T... elements);

    @Override
    default void Iterable_remove()
    {
        ImmutableUnorderedIterableTestCase.super.Iterable_remove();
    }

    @Override
    default void Iterable_next()
    {
        UnsortedBiMapTestCase.super.Iterable_next();
    }

    @Override
    default void RichIterable_toArray()
    {
        UnsortedBiMapTestCase.super.RichIterable_toArray();
    }
}
