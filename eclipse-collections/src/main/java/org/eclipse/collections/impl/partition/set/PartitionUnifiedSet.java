/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.partition.set;

import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.partition.set.PartitionImmutableSet;
import org.eclipse.collections.api.partition.set.PartitionMutableSet;
import org.eclipse.collections.api.set.MutableSet;

public class PartitionUnifiedSet<T> implements PartitionMutableSet<T>
{
    private final MutableSet<T> selected = Sets.mutable.empty();
    private final MutableSet<T> rejected = Sets.mutable.empty();

    @Override
    public MutableSet<T> getSelected()
    {
        return this.selected;
    }

    @Override
    public MutableSet<T> getRejected()
    {
        return this.rejected;
    }

    @Override
    public PartitionImmutableSet<T> toImmutable()
    {
        return new PartitionImmutableSetImpl<>(this);
    }
}
