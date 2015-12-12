/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bag;

import java.util.Iterator;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.test.RichIterableWithDuplicatesTestCase;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertThat;

public interface BagTestCase extends RichIterableWithDuplicatesTestCase
{
    @Override
    <T> Bag<T> newWith(T... elements);

    @Override
    default MutableCollection<Integer> expectedIterationOrder()
    {
        MutableCollection<Integer> forEach = this.newMutableForFilter();
        Bag<Integer> bag = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
        bag.forEachWithOccurrences((Integer each, int parameter) -> forEach.add(each));
        return forEach;
    }

    @Override
    default void RichIterable_iterator_iterationOrder()
    {
        MutableCollection<Integer> iterationOrder = this.newMutableForFilter();
        Iterator<Integer> iterator = this.getInstanceUnderTest().iterator();
        while (iterator.hasNext())
        {
            iterationOrder.add(iterator.next());
        }
        assertEquals(RichIterableWithDuplicatesTestCase.super.expectedIterationOrder(), iterationOrder);

        MutableCollection<Integer> forEachWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().forEachWith((each, param) -> forEachWithIterationOrder.add(each), null);
        assertEquals(RichIterableWithDuplicatesTestCase.super.expectedIterationOrder(), forEachWithIterationOrder);

        MutableCollection<Integer> forEachWithIndexIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().forEachWithIndex((each, index) -> forEachWithIndexIterationOrder.add(each));
        assertEquals(RichIterableWithDuplicatesTestCase.super.expectedIterationOrder(), forEachWithIndexIterationOrder);
    }

    @Test
    default void Bag_sizeDistinct()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        assertEquals(3, bag.sizeDistinct());
    }

    @Test
    default void Bag_occurrencesOf()
    {
        Bag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        assertEquals(0, bag.occurrencesOf(0));
        assertEquals(1, bag.occurrencesOf(1));
        assertEquals(2, bag.occurrencesOf(2));
        assertEquals(3, bag.occurrencesOf(3));
    }

    @Test
    default void Bag_toStringOfItemToCount()
    {
        assertEquals("{}", this.newWith().toStringOfItemToCount());
        assertThat(this.newWith(2, 2, 1).toStringOfItemToCount(), isOneOf("{1=1, 2=2}", "{2=2, 1=1}"));
    }
}
