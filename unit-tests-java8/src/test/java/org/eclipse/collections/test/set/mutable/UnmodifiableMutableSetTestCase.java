/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.set.mutable;

import org.eclipse.collections.test.UnmodifiableMutableCollectionTestCase;
import org.eclipse.collections.test.set.UnmodifiableSetTestCase;
import org.junit.Test;

public interface UnmodifiableMutableSetTestCase extends UnmodifiableMutableCollectionTestCase, UnmodifiableSetTestCase, MutableSetTestCase
{
    @Override
    default boolean allowsRemove()
    {
        return false;
    }

    @Override
    @Test
    default void Iterable_remove()
    {
        UnmodifiableSetTestCase.super.Iterable_remove();
    }

    @Override
    @Test
    default void MutableCollection_removeIf()
    {
        UnmodifiableMutableCollectionTestCase.super.MutableCollection_removeIf();
    }

    @Override
    @Test
    default void MutableCollection_removeIfWith()
    {
        UnmodifiableMutableCollectionTestCase.super.MutableCollection_removeIfWith();
    }
}
