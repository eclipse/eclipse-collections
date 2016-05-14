/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.strategy.immutable;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.factory.set.strategy.ImmutableHashingStrategySetFactory;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.impl.utility.Iterate;

public enum ImmutableHashingStrategySetFactoryImpl implements ImmutableHashingStrategySetFactory
{
    INSTANCE;

    @Override
    public <T> ImmutableSet<T> of(HashingStrategy<? super T> hashingStrategy)
    {
        return this.with(hashingStrategy);
    }

    @Override
    public <T> ImmutableSet<T> with(HashingStrategy<? super T> hashingStrategy)
    {
        return new ImmutableEmptySetWithHashingStrategy<>(hashingStrategy);
    }

    @Override
    public <T> ImmutableSet<T> of(HashingStrategy<? super T> hashingStrategy, T... items)
    {
        return this.with(hashingStrategy, items);
    }

    @Override
    public <T> ImmutableSet<T> with(HashingStrategy<? super T> hashingStrategy, T... items)
    {
        if (items == null || items.length == 0)
        {
            return this.of(hashingStrategy);
        }

        return ImmutableUnifiedSetWithHashingStrategy.newSetWith(hashingStrategy, items);
    }

    @Override
    public <T> ImmutableSet<T> ofAll(HashingStrategy<? super T> hashingStrategy, Iterable<? extends T> items)
    {
        return this.withAll(hashingStrategy, items);
    }

    @Override
    public <T> ImmutableSet<T> withAll(HashingStrategy<? super T> hashingStrategy, Iterable<? extends T> items)
    {
        return this.with(hashingStrategy, (T[]) Iterate.toArray(items));
    }
}
