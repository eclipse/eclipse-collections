/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test;

import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public interface UnmodifiableCollectionTestCase extends CollectionTestCase, UnmodifiableIterableTestCase
{
    @Override
    @Test
    default void Collection_add()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).add(4));
    }

    @Override
    @Test
    default void Collection_remove_removeAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).remove(3));

        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).removeAll(Lists.mutable.with(3, 2)));
    }

    @Override
    @Test
    default void Collection_clear()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).clear());
    }

    @Override
    @Test
    default void Iterable_remove()
    {
        UnmodifiableIterableTestCase.super.Iterable_remove();
    }
}
