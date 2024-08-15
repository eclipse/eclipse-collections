/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test;

import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.test.collection.mutable.MutableCollectionTestCase;
import org.junit.jupiter.api.Test;

import static org.eclipse.collections.test.IterableTestCase.assertIterablesEqual;
import static org.junit.jupiter.api.Assertions.assertThrows;

public interface UnmodifiableMutableCollectionTestCase extends FixedSizeCollectionTestCase, MutableCollectionTestCase
{
    @Test
    @Override
    default void Iterable_remove()
    {
        FixedSizeCollectionTestCase.super.Iterable_remove();
    }

    @Override
    @Test
    default void MutableCollection_sanity_check()
    {
        // Cannot call add()

        String s = "";
        if (this.allowsDuplicates())
        {
            assertIterablesEqual(2, this.newWith(s, s).size());
        }
        else
        {
            assertThrows(IllegalStateException.class, () -> this.newWith(s, s));
        }
    }

    @Override
    @Test
    default void MutableCollection_removeIf()
    {
        MutableCollection<Integer> collection = this.newWith(5, 4, 3, 2, 1);
        assertThrows(UnsupportedOperationException.class, () -> collection.removeIf(Predicates.cast(each -> each % 2 == 0)));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(7, 4, 5, 1).removeIf(Predicates.cast(null)));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(9, 5, 1).removeIf(Predicates.cast(each -> each % 2 == 0)));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(6, 4, 2).removeIf(Predicates.cast(each -> each % 2 == 0)));
        assertThrows(UnsupportedOperationException.class, () -> this.<Integer>newWith().removeIf(Predicates.cast(each -> each % 2 == 0)));
    }

    @Override
    @Test
    default void MutableCollection_removeIfWith()
    {
        MutableCollection<Integer> collection = this.newWith(5, 4, 3, 2, 1);
        assertThrows(UnsupportedOperationException.class, () -> Boolean.valueOf(collection.removeIfWith(Predicates2.in(), Lists.immutable.with(5, 3, 1))));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(7, 4, 5, 1).removeIfWith(null, this));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(9, 5, 1).removeIfWith(Predicates2.greaterThan(), 10));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(6, 4, 2).removeIfWith(Predicates2.greaterThan(), 2));
        assertThrows(UnsupportedOperationException.class, () -> this.<Integer>newWith().removeIfWith(Predicates2.greaterThan(), 2));
    }
}
