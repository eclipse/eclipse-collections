/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.set.immutable.sorted;

import org.eclipse.collections.api.factory.SortedSets;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.test.IterableTestCase;

public class ImmutableTreeSetTest implements ImmutableSortedSetIterableTestCase
{
    @SafeVarargs
    @Override
    public final <T> ImmutableSortedSet<T> newWith(T... elements)
    {
        MutableSortedSet<T> result = SortedSets.mutable.with(Comparators.reverseNaturalOrder());
        IterableTestCase.addAllTo(elements, result);
        return result.toImmutable();
    }
}
