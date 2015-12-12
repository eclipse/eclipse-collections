/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.list;

import java.util.List;

import org.eclipse.collections.test.UnmodifiableCollectionTestCase;
import org.junit.Test;

public interface UnmodifiableListTestCase extends UnmodifiableCollectionTestCase, ListTestCase
{
    @Override
    @Test(expected = UnsupportedOperationException.class)
    default void List_set()
    {
        List<Integer> list = this.newWith(1, 2, 3);
        list.set(1, 4);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    default void List_set_negative()
    {
        List<Integer> list = this.newWith(1, 2, 3);
        list.set(-1, 4);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    default void List_set_out_of_bounds()
    {
        List<Integer> list = this.newWith(1, 2, 3);
        list.set(4, 4);
    }
}
