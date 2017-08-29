/*
 * Copyright (c) 2016 Bhavana Hindupur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.strategy.mutable;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.factory.bag.strategy.MutableHashingStrategyBagFactory;
import org.eclipse.collections.impl.utility.Iterate;

public enum MutableHashingStrategyBagFactoryImpl implements MutableHashingStrategyBagFactory
{
    INSTANCE;

    @Override
    public <T> MutableBag<T> of(HashingStrategy<? super T> hashingStrategy)
    {
        return this.with(hashingStrategy);
    }

    @Override
    public <T> MutableBag<T> empty(HashingStrategy<? super T> hashingStrategy)
    {
        return this.with(hashingStrategy);
    }

    @Override
    public <T> MutableBag<T> with(HashingStrategy<? super T> hashingStrategy)
    {
        return HashBagWithHashingStrategy.newBagWith(hashingStrategy);
    }

    @Override
    public <T> MutableBag<T> of(HashingStrategy<? super T> hashingStrategy, T... items)
    {
        return this.with(hashingStrategy, items);
    }

    @Override
    public <T> MutableBag<T> with(HashingStrategy<? super T> hashingStrategy, T... items)
    {
        return HashBagWithHashingStrategy.newBagWith(hashingStrategy, items);
    }

    @Override
    public <T> MutableBag<T> ofAll(HashingStrategy<? super T> hashingStrategy, Iterable<? extends T> items)
    {
        return this.withAll(hashingStrategy, items);
    }

    @Override
    public <T> MutableBag<T> withAll(HashingStrategy<? super T> hashingStrategy, Iterable<? extends T> items)
    {
        if (Iterate.isEmpty(items))
        {
            return this.with(hashingStrategy);
        }
        return HashBagWithHashingStrategy.newBag(hashingStrategy, items);
    }
}
