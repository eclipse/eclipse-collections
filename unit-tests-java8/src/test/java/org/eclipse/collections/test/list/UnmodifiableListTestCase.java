/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.list;

import org.junit.Test;

import static org.junit.Assert.assertThrows;

public interface UnmodifiableListTestCase extends FixedSizeListTestCase
{
    @Override
    @Test
    default void List_set()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1).set(0, 4));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2).set(0, 4));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).set(0, 4));

        assertThrows(UnsupportedOperationException.class, () -> this.newWith().set(-1, 4));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).set(4, 4));
    }
}
