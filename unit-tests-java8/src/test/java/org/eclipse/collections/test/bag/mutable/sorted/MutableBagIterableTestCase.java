/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bag.mutable.sorted;

import org.eclipse.collections.api.bag.MutableBagIterable;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.test.collection.mutable.MutableCollectionTestCase;
import org.junit.Test;

public interface MutableBagIterableTestCase extends MutableCollectionTestCase
{
    @Override
    <T> MutableBagIterable<T> newWith(T... elements);

    @Test
    default void MutableBag_addOccurrences_throws()
    {
        Verify.assertThrows(
                IllegalArgumentException.class,
                () -> this.newWith(1, 2, 2, 3, 3, 3).addOccurrences(4, -1));
    }

    @Test
    default void MutableBag_removeOccurrences_throws()
    {
        Verify.assertThrows(
                IllegalArgumentException.class,
                () -> this.newWith(1, 2, 2, 3, 3, 3).removeOccurrences(4, -1));
    }
}
