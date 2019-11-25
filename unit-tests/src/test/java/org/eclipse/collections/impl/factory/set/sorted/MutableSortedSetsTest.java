/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.set.sorted;

import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class MutableSortedSetsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(TreeSortedSet.newSet(), MutableSortedSet.of());
        Verify.assertInstanceOf(MutableSortedSet.class, MutableSortedSet.of());
        Assert.assertEquals(TreeSortedSet.newSetWith(1, 2), MutableSortedSet.of(1, 2, 2));
        Verify.assertInstanceOf(MutableSortedSet.class, MutableSortedSet.of(1, 2));
        Assert.assertEquals(TreeSortedSet.newSetWith(1, 2, 3, 4), MutableSortedSet.of(1, 2, 3, 4));
        Verify.assertInstanceOf(MutableSortedSet.class, MutableSortedSet.of(1, 2, 3, 4));
        Assert.assertEquals(TreeSortedSet.newSetWith(1, 2, 3, 4, 5, 6), MutableSortedSet.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(MutableSortedSet.class, MutableSortedSet.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(TreeSortedSet.newSetWith(1, 2, 3, 4, 5, 6, 7, 8), MutableSortedSet.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(MutableSortedSet.class, MutableSortedSet.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(TreeSortedSet.newSetWith(1, 2, 3, 4, 5, 6, 7, 8), MutableSortedSet.ofAll(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
        Verify.assertInstanceOf(MutableSortedSet.class, MutableSortedSet.ofAll(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(TreeSortedSet.newSet(Comparators.naturalOrder()), MutableSortedSet.of(Comparators.naturalOrder()));
        Verify.assertInstanceOf(MutableSortedSet.class, MutableSortedSet.of(Comparators.naturalOrder()));
        Assert.assertEquals(TreeSortedSet.newSetWith(8, 7, 6, 5, 4, 3, 2, 1), MutableSortedSet.ofAll(Comparators.reverseNaturalOrder(), FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
        Verify.assertInstanceOf(MutableSortedSet.class, MutableSortedSet.ofAll(Comparators.reverseNaturalOrder(), FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
    }
}
