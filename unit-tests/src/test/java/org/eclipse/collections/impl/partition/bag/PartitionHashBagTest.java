/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.partition.bag;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.partition.bag.PartitionImmutableBag;
import org.eclipse.collections.api.partition.bag.PartitionMutableBag;
import org.junit.Assert;
import org.junit.Test;

public class PartitionHashBagTest
{
    @Test
    public void toImmutable()
    {
        PartitionMutableBag<Integer> partitionHashBag = new PartitionHashBag<>();
        MutableBag<Integer> selected = Bags.mutable.of(1, 2, 3);
        MutableBag<Integer> rejected = Bags.mutable.of(4, 5, 6);
        partitionHashBag.getSelected().withAll(selected);
        partitionHashBag.getRejected().withAll(rejected);

        PartitionImmutableBag<Integer> partitionImmutableBag = partitionHashBag.toImmutable();
        Assert.assertEquals(selected, partitionImmutableBag.getSelected());
        Assert.assertEquals(rejected, partitionImmutableBag.getRejected());
    }
}
