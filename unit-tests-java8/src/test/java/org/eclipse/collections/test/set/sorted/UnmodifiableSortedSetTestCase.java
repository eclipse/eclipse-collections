/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.set.sorted;

import org.eclipse.collections.test.FixedSizeCollectionTestCase;
import org.junit.jupiter.api.Test;

public interface UnmodifiableSortedSetTestCase extends FixedSizeCollectionTestCase, SortedSetTestCase
{
    @Override
    @Test
    default void Iterable_remove()
    {
        FixedSizeCollectionTestCase.super.Iterable_remove();
    }

    @Override
    @Test
    default void Collection_add()
    {
        FixedSizeCollectionTestCase.super.Collection_add();
    }
}
