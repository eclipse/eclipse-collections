/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.partition.list;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.partition.list.PartitionImmutableList;

public class PartitionImmutableListImpl<T> implements PartitionImmutableList<T>
{
    private final ImmutableList<T> selected;
    private final ImmutableList<T> rejected;

    public PartitionImmutableListImpl(ImmutableList<T> selected, ImmutableList<T> rejected)
    {
        this.selected = selected;
        this.rejected = rejected;
    }

    public PartitionImmutableListImpl(PartitionFastList<T> partitionFastList)
    {
        this(partitionFastList.getSelected().toImmutable(), partitionFastList.getRejected().toImmutable());
    }

    @Override
    public ImmutableList<T> getSelected()
    {
        return this.selected;
    }

    @Override
    public ImmutableList<T> getRejected()
    {
        return this.rejected;
    }
}
