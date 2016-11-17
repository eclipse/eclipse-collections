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

import static org.junit.Assert.assertArrayEquals;

public interface OrderedIterableWithDuplicatesTestCase extends RichIterableWithDuplicatesTestCase, OrderedIterableTestCase
{
    @Test
    default void OrderedIterable_toArray()
    {
        Object[] array = this.newWith(3, 3, 3, 2, 2, 1).toArray();
        assertArrayEquals(new Object[]{3, 3, 3, 2, 2, 1}, array);
    }
}
