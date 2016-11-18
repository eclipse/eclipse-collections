/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.list.immutable;

import java.util.List;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.test.collection.immutable.ImmutableCollectionTestCase;
import org.eclipse.collections.test.list.ListIterableTestCase;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public interface ImmutableListTestCase extends ImmutableCollectionTestCase, ListIterableTestCase
{
    @Override
    <T> ImmutableList<T> newWith(T... elements);

    @Test
    default void ImmutableList_castToList()
    {
        ImmutableList<Integer> immutableList = this.newWith(3, 3, 3, 2, 2, 1);
        List<Integer> list = immutableList.castToList();
        assertSame(immutableList, list);
    }
}
