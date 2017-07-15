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

import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.partition.bag.sorted.PartitionImmutableSortedBag;
import org.eclipse.collections.api.partition.bag.sorted.PartitionSortedBag;

/**
 * @since 4.2
 */
public class PartitionImmutableSortedBagImpl<T> implements PartitionImmutableSortedBag<T>
{
    private final ImmutableSortedBag<T> selected;
    private final ImmutableSortedBag<T> rejected;

    public PartitionImmutableSortedBagImpl(PartitionSortedBag<T> partitionImmutableSortedBag)
    {
        this.selected = partitionImmutableSortedBag.getSelected().toImmutable();
        this.rejected = partitionImmutableSortedBag.getRejected().toImmutable();
    }

    @Override
    public ImmutableSortedBag<T> getSelected()
    {
        return this.selected;
    }

    @Override
    public ImmutableSortedBag<T> getRejected()
    {
        return this.rejected;
    }
}
