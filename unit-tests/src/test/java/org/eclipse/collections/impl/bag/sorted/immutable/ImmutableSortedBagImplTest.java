/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.sorted.immutable;

import java.util.Comparator;

import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.impl.factory.SortedBags;

public class ImmutableSortedBagImplTest extends AbstractImmutableSortedBagTestCase
{
    @Override
    protected ImmutableSortedBag<Integer> classUnderTest()
    {
        return SortedBags.immutable.with(1, 1, 1, 2);
    }

    @Override
    protected <T> MutableCollection<T> newMutable()
    {
        return SortedBags.mutable.empty();
    }

    @Override
    protected ImmutableSortedBag<Integer> classUnderTest(Comparator<? super Integer> comparator)
    {
        return SortedBags.immutable.with(comparator, 1, 1, 1, 2);
    }

    @Override
    protected <T> ImmutableSortedBag<T> newWith(T... elements)
    {
        return SortedBags.immutable.with(elements);
    }

    @Override
    protected <T> ImmutableSortedBag<T> newWith(Comparator<? super T> comparator, T... elements)
    {
        return SortedBags.immutable.with(comparator, elements);
    }
}
