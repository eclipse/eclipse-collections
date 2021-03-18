/*
 * Copyright (c) 2021 Goldman Sachs and others.
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

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.factory.bag.MutableBagFactory;

public class MutableBagFactoryImpl implements MutableBagFactory
{
    public static final MutableBagFactory INSTANCE = new MutableBagFactoryImpl();

    @Override
    public <T> MutableBag<T> empty()
    {
        return HashBag.newBag();
    }

    @Override
    public <T> MutableBag<T> with(T... elements)
    {
        return HashBag.newBagWith(elements);
    }

    @Override
    public <T> MutableBag<T> withAll(Iterable<? extends T> items)
    {
        return HashBag.newBag(items);
    }

    @Override
    public <T> MutableBag<T> fromStream(Stream<? extends T> stream)
    {
        return stream.collect(Collectors.toCollection(HashBag::newBag));
    }
}
