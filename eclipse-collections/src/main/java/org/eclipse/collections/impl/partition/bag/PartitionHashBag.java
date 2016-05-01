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

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.partition.bag.PartitionImmutableBag;
import org.eclipse.collections.api.partition.bag.PartitionMutableBag;
import org.eclipse.collections.impl.bag.mutable.HashBag;

public class PartitionHashBag<T> implements PartitionMutableBag<T>
{
    private final MutableBag<T> selected = HashBag.newBag();
    private final MutableBag<T> rejected = HashBag.newBag();

    @Override
    public MutableBag<T> getSelected()
    {
        return this.selected;
    }

    @Override
    public MutableBag<T> getRejected()
    {
        return this.rejected;
    }

    @Override
    public PartitionImmutableBag<T> toImmutable()
    {
        return new PartitionImmutableBagImpl<>(this);
    }
}
