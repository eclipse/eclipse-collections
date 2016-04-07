/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bag.mutable.sorted;

import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.bag.sorted.mutable.UnmodifiableSortedBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.eclipse.collections.test.IterableTestCase;
import org.eclipse.collections.test.bag.mutable.UnmodifiableBagIterableTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Java8Runner.class)
public class UnmodifiableSortedBagTest implements MutableSortedBagTestCase, UnmodifiableBagIterableTestCase
{
    @Override
    public void Iterable_remove()
    {
        UnmodifiableBagIterableTestCase.super.Iterable_remove();
    }

    @SafeVarargs
    @Override
    public final <T> MutableSortedBag<T> newWith(T... elements)
    {
        MutableSortedBag<T> result = new TreeBag<>(Comparators.reverseNaturalOrder());
        IterableTestCase.addAllTo(elements, result);
        return UnmodifiableSortedBag.of(result);
    }

    @Override
    @Test
    public void MutableBagIterable_addOccurrences_throws()
    {
        UnmodifiableBagIterableTestCase.super.MutableBagIterable_addOccurrences_throws();
    }

    @Override
    @Test
    public void MutableBagIterable_removeOccurrences_throws()
    {
        UnmodifiableBagIterableTestCase.super.MutableBagIterable_removeOccurrences_throws();
    }

    @Override
    public void MutableBagIterable_addOccurrences()
    {
        UnmodifiableBagIterableTestCase.super.MutableBagIterable_addOccurrences();
    }

    @Override
    public void MutableBagIterable_removeOccurrences()
    {
        UnmodifiableBagIterableTestCase.super.MutableBagIterable_removeOccurrences();
    }
}
