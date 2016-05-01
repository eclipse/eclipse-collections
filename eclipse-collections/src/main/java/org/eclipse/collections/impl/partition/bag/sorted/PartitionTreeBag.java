/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.partition.bag.sorted;

import java.util.Comparator;

import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.partition.bag.sorted.PartitionImmutableSortedBag;
import org.eclipse.collections.api.partition.bag.sorted.PartitionMutableSortedBag;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;

/**
 * @since 4.2
 */
public class PartitionTreeBag<T> implements PartitionMutableSortedBag<T>
{
    private final MutableSortedBag<T> selected;
    private final MutableSortedBag<T> rejected;

    public PartitionTreeBag(Comparator<? super T> comparator)
    {
        this.selected = TreeBag.newBag(comparator);
        this.rejected = TreeBag.newBag(comparator);
    }

    @Override
    public MutableSortedBag<T> getSelected()
    {
        return this.selected;
    }

    @Override
    public MutableSortedBag<T> getRejected()
    {
        return this.rejected;
    }

    @Override
    public PartitionImmutableSortedBag<T> toImmutable()
    {
        return new PartitionImmutableSortedBagImpl<>(this);
    }
}
