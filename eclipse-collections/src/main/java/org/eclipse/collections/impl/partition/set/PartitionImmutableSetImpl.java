/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.partition.set;

import org.eclipse.collections.api.partition.set.PartitionImmutableSet;
import org.eclipse.collections.api.partition.set.PartitionMutableSet;
import org.eclipse.collections.api.set.ImmutableSet;

public class PartitionImmutableSetImpl<T> implements PartitionImmutableSet<T>
{
    private final ImmutableSet<T> selected;
    private final ImmutableSet<T> rejected;

    public PartitionImmutableSetImpl(PartitionMutableSet<T> mutablePartition)
    {
        this.selected = mutablePartition.getSelected().toImmutable();
        this.rejected = mutablePartition.getRejected().toImmutable();
    }

    @Override
    public ImmutableSet<T> getSelected()
    {
        return this.selected;
    }

    @Override
    public ImmutableSet<T> getRejected()
    {
        return this.rejected;
    }
}
