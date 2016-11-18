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

import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.test.collection.mutable.MutableCollectionTestCase;
import org.junit.Test;

import static org.eclipse.collections.impl.test.Verify.assertThrows;
import static org.eclipse.collections.test.IterableTestCase.assertEquals;

public interface UnmodifiableMutableCollectionTestCase extends UnmodifiableCollectionTestCase, MutableCollectionTestCase
{
    @Test
    @Override
    default void Iterable_remove()
    {
        UnmodifiableCollectionTestCase.super.Iterable_remove();
    }

    @Override
    @Test
    default void MutableCollection_sanity_check()
    {
        // Cannot call add()

        String s = "";
        if (this.allowsDuplicates())
        {
            assertEquals(2, this.newWith(s, s).size());
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
        assertThrows(UnsupportedOperationException.class, () -> collection.removeIfWith(Predicates2.<Integer>in(), Lists.immutable.with(5, 3, 1)));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(7, 4, 5, 1).removeIfWith(null, this));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(9, 5, 1).removeIfWith(Predicates2.greaterThan(), 10));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(6, 4, 2).removeIfWith(Predicates2.greaterThan(), 2));
        assertThrows(UnsupportedOperationException.class, () -> this.<Integer>newWith().removeIfWith(Predicates2.greaterThan(), 2));
    }
}
