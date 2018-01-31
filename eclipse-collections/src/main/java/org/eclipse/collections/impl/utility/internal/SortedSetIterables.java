/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.utility.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.set.sorted.SortedSetIterable;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * SortedSet algebra operations.
 */
public final class SortedSetIterables
{
    private SortedSetIterables()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static <T> MutableSortedSet<MutableSortedSet<T>> powerSet(SortedSet<T> set)
    {
        Comparator<? super T> comparator = set.comparator();
        MutableSortedSet<T> innerTree = TreeSortedSet.newSet(comparator);
        TreeSortedSet<MutableSortedSet<T>> sortedSetIterables = TreeSortedSet.newSet(Comparators.powerSet());
        MutableSortedSet<MutableSortedSet<T>> seed = sortedSetIterables.with(innerTree);

        return Iterate.injectInto(seed, set, (accumulator, element) -> accumulator.union(accumulator.collect(set1 -> {
            MutableSortedSet<T> newSet = set1.clone();
            newSet.add(element);
            return newSet;
        }).toSet()));
    }

    /**
     * Returns an Immutable version of powerset where the inner sets are also immutable.
     */
    public static <T> ImmutableSortedSet<ImmutableSortedSet<T>> immutablePowerSet(SortedSet<T> set)
    {
        return powerSet(set).collect(MutableSortedSet::toImmutable, TreeSortedSet.newSet(Comparators.powerSet())).toImmutable();
    }

    public static <T> int compare(SortedSetIterable<T> setA, SortedSetIterable<T> setB)
    {
        Iterator<T> itrA = setA.iterator();
        Iterator<T> itrB = setB.iterator();
        if (setA.comparator() != null)
        {
            Comparator<? super T> comparator = setA.comparator();
            while (itrA.hasNext())
            {
                if (itrB.hasNext())
                {
                    int val = comparator.compare(itrA.next(), itrB.next());
                    if (val != 0)
                    {
                        return val;
                    }
                }
                else
                {
                    return 1;
                }
            }
            return itrB.hasNext() ? -1 : 0;
        }

        while (itrA.hasNext())
        {
            if (itrB.hasNext())
            {
                int val = ((Comparable<T>) itrA.next()).compareTo(itrB.next());
                if (val != 0)
                {
                    return val;
                }
            }
            else
            {
                return 1;
            }
        }
        return itrB.hasNext() ? -1 : 0;
    }
}
