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

import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.test.RichIterableUniqueTestCase;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public interface SetIterableTestCase extends RichIterableUniqueTestCase
{
    @Override
    <T> SetIterable<T> newWith(T... elements);

    @Override
    @Test
    default void RichIterable_toArray()
    {
        Object[] array = this.newWith(3, 2, 1).toArray();
        assertArrayEquals(new Object[]{3, 2, 1}, array);
    }

    @Test
    default void SetIterable_union()
    {
        SetIterable<Integer> union = this.newWith(3, 2, 1).union(this.newWith(5, 4, 3));
        assertEquals(this.newWith(5, 4, 3, 2, 1), union);
    }
}
