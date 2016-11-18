/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.collection.mutable;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.test.IterableTestCase;
import org.eclipse.collections.test.RichIterableUniqueTestCase;
import org.junit.Assert;
import org.junit.Test;

public interface MutableCollectionUniqueTestCase extends MutableCollectionTestCase, RichIterableUniqueTestCase
{
    @Override
    <T> MutableCollection<T> newWith(T... elements);

    @Override
    @Test
    default void MutableCollection_removeIf()
    {
        MutableCollection<Integer> collection1 = this.newWith(5, 4, 3, 2, 1);
        Assert.assertTrue(collection1.removeIf(Predicates.cast(each -> each % 2 == 0)));
        IterableTestCase.assertEquals(this.getExpectedFiltered(5, 3, 1), collection1);

        MutableCollection<Integer> collection2 = this.newWith(1, 2, 3, 4);
        Assert.assertFalse(collection2.removeIf(Predicates.equal(5)));
        Assert.assertTrue(collection2.removeIf(Predicates.greaterThan(0)));
        Assert.assertFalse(collection2.removeIf(Predicates.greaterThan(2)));

        MutableCollection<Integer> collection3 = this.newWith();
        Assert.assertFalse(collection3.removeIf(Predicates.equal(5)));

        Predicate<Object> predicate = null;
        Verify.assertThrows(NullPointerException.class, () -> this.newWith(7, 4, 5, 1).removeIf(predicate));
    }

    @Override
    @Test
    default void MutableCollection_removeIfWith()
    {
        MutableCollection<Integer> collection = this.newWith(5, 4, 3, 2, 1);
        collection.removeIfWith(Predicates2.<Integer>in(), Lists.immutable.with(5, 3, 1));
        IterableTestCase.assertEquals(this.getExpectedFiltered(4, 2), collection);

        MutableCollection<Integer> collection2 = this.newWith(1, 2, 3, 4);
        Assert.assertFalse(collection2.removeIf(Predicates.equal(5)));
        Assert.assertTrue(collection2.removeIf(Predicates.greaterThan(0)));
        Assert.assertFalse(collection2.removeIf(Predicates.greaterThan(2)));

        MutableCollection<Integer> collection3 = this.newWith();
        Assert.assertFalse(collection3.removeIf(Predicates.equal(5)));

        Verify.assertThrows(NullPointerException.class, () -> this.newWith(7, 4, 5, 1).removeIf(Predicates.cast(null)));
    }

    @Override
    @Test
    default void MutableCollection_injectIntoWith()
    {
        MutableCollection<Integer> collection = this.newWith(4, 3, 2, 1);
        IterableTestCase.assertEquals(Integer.valueOf(51), collection.injectIntoWith(1, (argument1, argument2, argument3) -> argument1 + argument2 + argument3, 10));
    }
}
