/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bag.mutable;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.test.UnmodifiableMutableCollectionTestCase;
import org.junit.Test;

public interface UnmodifiableBagTestCase extends UnmodifiableMutableCollectionTestCase, MutableBagTestCase
{
    @Override
    @Test
    default void Iterable_remove()
    {
        UnmodifiableMutableCollectionTestCase.super.Iterable_remove();
    }

    @Override
    @Test
    default void MutableBag_addOccurrences_throws()
    {
        Verify.assertThrows(
                UnsupportedOperationException.class,
                () -> this.newWith(1, 2, 2, 3, 3, 3).addOccurrences(4, -1));
    }

    @Override
    @Test
    default void MutableBag_removeOccurrences_throws()
    {
        Verify.assertThrows(
                UnsupportedOperationException.class,
                () -> this.newWith(1, 2, 2, 3, 3, 3).removeOccurrences(4, -1));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    default void MutableBag_addOccurrences()
    {
        MutableBag<Integer> mutableBag = this.newWith(1, 2, 2, 3, 3, 3);
        mutableBag.addOccurrences(4, 4);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    default void MutableBag_removeOccurrences()
    {
        MutableBag<Integer> mutableBag = this.newWith(1, 2, 2, 3, 3, 3);
        mutableBag.removeOccurrences(4, 4);
    }
}
