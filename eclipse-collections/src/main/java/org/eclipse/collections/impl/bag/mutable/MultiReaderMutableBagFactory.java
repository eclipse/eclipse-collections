/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.collections.api.bag.MultiReaderBag;
import org.eclipse.collections.api.factory.bag.MultiReaderBagFactory;

public enum MultiReaderMutableBagFactory implements MultiReaderBagFactory
{
    INSTANCE;

    @Override
    public <T> MultiReaderBag<T> empty()
    {
        return MultiReaderHashBag.newBag();
    }
    
    @SafeVarargs
    @Override
    public <T> MultiReaderBag<T> with(T... items)
    {
        return MultiReaderHashBag.newBagWith(items);
    }

    @Override
    public <T> MultiReaderBag<T> withAll(Iterable<? extends T> iterable)
    {
        return MultiReaderHashBag.newBag((Iterable<T>) iterable);
    }

    @Override
    public <T> MultiReaderBag<T> fromStream(Stream<? extends T> stream)
    {
        return stream.collect(Collectors.toCollection(MultiReaderHashBag::newBag));
    }
}
