/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.strategy.mutable;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.factory.set.strategy.MutableHashingStrategySetFactory;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.utility.Iterate;

public enum MutableHashingStrategySetFactoryImpl implements MutableHashingStrategySetFactory
{
    INSTANCE;

    @Override
    public <T> MutableSet<T> of(HashingStrategy<? super T> hashingStrategy)
    {
        return this.with(hashingStrategy);
    }

    @Override
    public <T> MutableSet<T> with(HashingStrategy<? super T> hashingStrategy)
    {
        return UnifiedSetWithHashingStrategy.newSetWith(hashingStrategy);
    }

    @Override
    public <T> MutableSet<T> of(HashingStrategy<? super T> hashingStrategy, T... items)
    {
        return this.with(hashingStrategy, items);
    }

    @Override
    public <T> MutableSet<T> with(HashingStrategy<? super T> hashingStrategy, T... items)
    {
        return UnifiedSetWithHashingStrategy.newSetWith(hashingStrategy, items);
    }

    @Override
    public <T> MutableSet<T> ofAll(HashingStrategy<? super T> hashingStrategy, Iterable<? extends T> items)
    {
        return this.withAll(hashingStrategy, items);
    }

    @Override
    public <T> MutableSet<T> withAll(HashingStrategy<? super T> hashingStrategy, Iterable<? extends T> items)
    {
        if (Iterate.isEmpty(items))
        {
            return this.with(hashingStrategy);
        }
        return UnifiedSetWithHashingStrategy.newSet(hashingStrategy, items);
    }
}
