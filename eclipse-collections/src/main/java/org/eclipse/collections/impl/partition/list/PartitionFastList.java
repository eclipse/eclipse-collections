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

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.partition.list.PartitionImmutableList;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

public class PartitionFastList<T> implements PartitionMutableList<T>
{
    private final MutableList<T> selected = FastList.newList();
    private final MutableList<T> rejected = FastList.newList();

    @Override
    public MutableList<T> getSelected()
    {
        return this.selected;
    }

    @Override
    public MutableList<T> getRejected()
    {
        return this.rejected;
    }

    @Override
    public PartitionImmutableList<T> toImmutable()
    {
        return new PartitionImmutableListImpl<>(this);
    }
}
