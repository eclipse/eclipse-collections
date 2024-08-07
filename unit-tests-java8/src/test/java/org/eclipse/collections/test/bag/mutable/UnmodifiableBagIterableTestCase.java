/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bag.mutable;

import org.eclipse.collections.test.UnmodifiableMutableCollectionTestCase;
import org.eclipse.collections.test.bag.mutable.sorted.MutableBagIterableTestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface UnmodifiableBagIterableTestCase extends UnmodifiableMutableCollectionTestCase, MutableBagIterableTestCase
{
    @Override
    @Test
    default void MutableBagIterable_addOccurrences()
    {
        assertThrows(
                UnsupportedOperationException.class,
                () -> this.newWith(1, 2, 2, 3, 3, 3).addOccurrences(4, 4));

        assertThrows(
                UnsupportedOperationException.class,
                () -> this.newWith(1, 2, 2, 3, 3, 3).addOccurrences(4, -1));
    }

    @Override
    @Test
    default void MutableBagIterable_removeOccurrences()
    {
        assertThrows(
                UnsupportedOperationException.class,
                () -> this.newWith(1, 2, 2, 3, 3, 3).removeOccurrences(4, 4));

        assertThrows(
                UnsupportedOperationException.class,
                () -> this.newWith(1, 2, 2, 3, 3, 3).removeOccurrences(4, -1));
    }
}
