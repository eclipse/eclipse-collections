/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.partition.bag;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.partition.bag.PartitionImmutableBag;

public class PartitionImmutableBagImpl<T> implements PartitionImmutableBag<T>
{
    private final ImmutableBag<T> selected;
    private final ImmutableBag<T> rejected;

    public PartitionImmutableBagImpl(PartitionHashBag<T> partitionHashBag)
    {
        this.selected = partitionHashBag.getSelected().toImmutable();
        this.rejected = partitionHashBag.getRejected().toImmutable();
    }

    @Override
    public ImmutableBag<T> getSelected()
    {
        return this.selected;
    }

    @Override
    public ImmutableBag<T> getRejected()
    {
        return this.rejected;
    }
}
