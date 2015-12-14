/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.collection.mutable;

import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.test.CollectionTestCase;
import org.eclipse.collections.test.IterableTestCase;
import org.eclipse.collections.test.RichIterableTestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

public interface MutableCollectionTestCase extends CollectionTestCase, RichIterableTestCase
{
    @Override
    <T> MutableCollection<T> newWith(T... elements);

    @Test
    default void MutableCollection_iterationOrder()
    {
        MutableCollection<Integer> injectIntoWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().injectIntoWith(
                0,
                (a, b, c) ->
                {
                    injectIntoWithIterationOrder.add(b);
                    return 0;
                },
                0);
        IterableTestCase.assertEquals(this.newMutableForFilter(4, 4, 4, 4, 3, 3, 3, 2, 2, 1), injectIntoWithIterationOrder);
    }

    // TODO try to make the return type of newWith and getInstanceUnderTest a generic parameter
    @Override
    default MutableCollection<Integer> getInstanceUnderTest()
    {
        return this.allowsDuplicates()
                ? this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1)
                : this.newWith(4, 3, 2, 1);
    }

    @Test
    default void MutableCollection_sanity_check()
    {
        String s = "";

        MutableCollection<String> collection = this.newWith();
        Assert.assertTrue(collection.add(s));
        IterableTestCase.assertEquals(this.allowsDuplicates(), collection.add(s));
        IterableTestCase.assertEquals(this.allowsDuplicates() ? 2 : 1, collection.size());
    }

    @Test
    default void MutableCollection_toImmutable()
    {
        assertThat(this.newWith(), instanceOf(MutableCollection.class));
        assertThat(this.newWith().toImmutable(), instanceOf(ImmutableCollection.class));
    }

    @Test
    default void MutableCollection_removeIf()
    {
        MutableCollection<Integer> collection1 = this.newWith(5, 5, 4, 4, 3, 3, 2, 2, 1, 1);
        Assert.assertTrue(collection1.removeIf(Predicates.cast(each -> each % 2 == 0)));
        IterableTestCase.assertEquals(this.getExpectedFiltered(5, 5, 3, 3, 1, 1), collection1);

        MutableCollection<Integer> collection2 = this.newWith(1, 2, 3);
        Assert.assertFalse(collection2.removeIf(Predicates.cast(each -> each > 4)));
        IterableTestCase.assertEquals(this.getExpectedFiltered(1, 2, 3), collection2);
        Assert.assertTrue(collection2.removeIf(Predicates.cast(each -> each > 0)));

        MutableCollection<Integer> collection3 = this.newWith();
        Assert.assertFalse(collection3.removeIf(Predicates.cast(each -> each % 2 == 0)));
        IterableTestCase.assertEquals(this.getExpectedFiltered(), collection3);

        MutableCollection<Integer> collection4 = this.newWith(2, 2, 4, 6);
        Assert.assertTrue(collection4.removeIf(Predicates.cast(each -> each % 2 == 0)));
        IterableTestCase.assertEquals(this.getExpectedFiltered(), collection4);
        Assert.assertFalse(collection4.removeIf(Predicates.cast(each -> each % 2 == 0)));
    }

    @Test
    default void MutableCollection_removeIfWith()
    {
        MutableCollection<Integer> collection1 = this.newWith(5, 5, 4, 4, 3, 3, 2, 2, 1, 1);
        Assert.assertTrue(collection1.removeIfWith(Predicates2.<Integer>in(), Lists.immutable.with(5, 3, 1)));
        IterableTestCase.assertEquals(this.getExpectedFiltered(4, 4, 2, 2), collection1);

        MutableCollection<Integer> collection2 = this.newWith(1, 2, 3);
        Assert.assertFalse(collection2.removeIfWith(Predicates2.<Integer>in(), Lists.immutable.with(4)));
        IterableTestCase.assertEquals(this.getExpectedFiltered(1, 2, 3), collection2);
        Assert.assertTrue(collection2.removeIfWith(Predicates2.<Integer>in(), Lists.immutable.with(1, 2, 3)));

        MutableCollection<Integer> collection3 = this.newWith();
        Assert.assertFalse(collection3.removeIfWith(Predicates2.<Integer>in(), Lists.immutable.with()));
        IterableTestCase.assertEquals(this.getExpectedFiltered(), collection3);

        MutableCollection<Integer> collection4 = this.newWith(2, 2, 4, 6);
        Assert.assertTrue(collection4.removeIfWith(Predicates2.greaterThan(), 1));
        IterableTestCase.assertEquals(this.getExpectedFiltered(), collection4);
        Assert.assertFalse(collection4.removeIfWith(Predicates2.greaterThan(), 1));
    }

    @Test
    default void MutableCollection_injectIntoWith()
    {
        MutableCollection<Integer> collection = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
        IterableTestCase.assertEquals(Integer.valueOf(81), collection.injectIntoWith(1, (a, b, c) -> a + b + c, 5));
    }
}
