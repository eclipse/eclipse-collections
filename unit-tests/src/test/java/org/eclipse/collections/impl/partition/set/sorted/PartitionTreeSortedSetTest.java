/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.partition.set.sorted;

import org.eclipse.collections.api.factory.SortedSets;
import org.eclipse.collections.api.partition.set.sorted.PartitionImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.junit.Assert;
import org.junit.Test;

public class PartitionTreeSortedSetTest
{
    @Test
    public void toImmutable()
    {
        PartitionTreeSortedSet<Integer> partitionTreeSortedSet = new PartitionTreeSortedSet<>(Comparators.naturalOrder());
        MutableSortedSet<Integer> selected = SortedSets.mutable.of(1, 2, 3);
        MutableSortedSet<Integer> rejected = SortedSets.mutable.of(4, 5, 6);

        partitionTreeSortedSet.getSelected().addAll(selected);
        partitionTreeSortedSet.getRejected().addAll(rejected);
        PartitionImmutableSortedSet<Integer> immutableSortedSet = partitionTreeSortedSet.toImmutable();
        Assert.assertEquals(selected, immutableSortedSet.getSelected());
        Assert.assertEquals(rejected, immutableSortedSet.getRejected());
    }
}
