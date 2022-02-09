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

public interface FixedSizeCollectionTestCase extends CollectionTestCase, FixedSizeIterableTestCase
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
        assertThrows(UnsupportedOperationException.class, () -> this.newWith().remove(1));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1).remove(1));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2).remove(1));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).remove(1));

        assertThrows(UnsupportedOperationException.class, () -> this.newWith().removeAll(Lists.mutable.with(1, 2)));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1).removeAll(Lists.mutable.with(1, 2)));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2).removeAll(Lists.mutable.with(1, 2)));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).removeAll(Lists.mutable.with(1, 2)));
    }

    @Override
    @Test
    default void Collection_clear()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith().clear());
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1).clear());
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2).clear());
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).clear());
    }

    @Override
    @Test
    default void Iterable_remove()
    {
        FixedSizeIterableTestCase.super.Iterable_remove();
    }
}
