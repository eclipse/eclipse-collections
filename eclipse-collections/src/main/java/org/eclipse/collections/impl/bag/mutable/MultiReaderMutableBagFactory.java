/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.factory.bag.MutableBagFactory;

public enum MultiReaderMutableBagFactory implements MutableBagFactory
{
    INSTANCE;

    @Override
    public <T> MutableBag<T> empty()
    {
        return MultiReaderHashBag.newBag();
    }

    @Override
    public <T> MutableBag<T> of()
    {
        return this.empty();
    }

    @Override
    public <T> MutableBag<T> with()
    {
        return this.empty();
    }

    @Override
    public <T> MutableBag<T> of(T... items)
    {
        return this.with(items);
    }

    @Override
    public <T> MutableBag<T> with(T... items)
    {
        return MultiReaderHashBag.newBagWith(items);
    }

    @Override
    public <T> MutableBag<T> ofAll(Iterable<? extends T> iterable)
    {
        return this.withAll(iterable);
    }

    @Override
    public <T> MutableBag<T> withAll(Iterable<? extends T> iterable)
    {
        return MultiReaderHashBag.newBag((Iterable<T>) iterable);
    }
}
