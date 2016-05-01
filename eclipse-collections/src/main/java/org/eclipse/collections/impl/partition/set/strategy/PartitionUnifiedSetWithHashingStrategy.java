/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.partition.set.strategy;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.partition.set.PartitionImmutableSet;
import org.eclipse.collections.api.partition.set.PartitionMutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.partition.set.PartitionImmutableSetImpl;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;

public class PartitionUnifiedSetWithHashingStrategy<T>
        implements PartitionMutableSet<T>
{
    private final MutableSet<T> selected;
    private final MutableSet<T> rejected;

    public PartitionUnifiedSetWithHashingStrategy(HashingStrategy<? super T> hashingStrategy)
    {
        this.selected = UnifiedSetWithHashingStrategy.newSet(hashingStrategy);
        this.rejected = UnifiedSetWithHashingStrategy.newSet(hashingStrategy);
    }

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
