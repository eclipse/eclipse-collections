/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map;

import org.junit.jupiter.api.Test;

public interface UnmodifiableMapIterableTestCase
        extends UnmodifiableMapTestCase, MapIterableTestCase
{
    @Test
    @Override
    default void Map_put()
    {
        UnmodifiableMapTestCase.super.Map_put();
    }

    @Test
    @Override
    default void Map_putAll()
    {
        UnmodifiableMapTestCase.super.Map_putAll();
    }

    @Test
    @Override
    default void Map_merge()
    {
        UnmodifiableMapTestCase.super.Map_merge();
    }

    @Test
    @Override
    default void Map_remove()
    {
        UnmodifiableMapTestCase.super.Map_remove();
    }

    @Test
    @Override
    default void Map_entrySet_remove()
    {
        UnmodifiableMapTestCase.super.Map_entrySet_remove();
    }

    @Test
    @Override
    default void Map_clear()
    {
        UnmodifiableMapTestCase.super.Map_clear();
    }
}
