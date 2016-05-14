/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.sorted.mutable;

import java.util.Comparator;

import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.factory.bag.sorted.MutableSortedBagFactory;

public enum MutableSortedBagFactoryImpl implements MutableSortedBagFactory
{
    INSTANCE;

    @Override
    public <T> MutableSortedBag<T> empty()
    {
        return TreeBag.newBag();
    }

    @Override
    public <T> MutableSortedBag<T> empty(Comparator<? super T> comparator)
    {
        return TreeBag.newBag(comparator);
    }

    @Override
    public <T> MutableSortedBag<T> of()
    {
        return this.with();
    }

    @Override
    public <T> MutableSortedBag<T> with()
    {
        return TreeBag.newBag();
    }

    @Override
    public <T> MutableSortedBag<T> of(Comparator<? super T> comparator)
    {
        return this.with(comparator);
    }

    @Override
    public <T> MutableSortedBag<T> with(Comparator<? super T> comparator)
    {
        return TreeBag.newBag(comparator);
    }

    @Override
    public <T> MutableSortedBag<T> of(T... elements)
    {
        return this.with(elements);
    }

    @Override
    public <T> MutableSortedBag<T> with(T... elements)
    {
        return TreeBag.newBagWith(elements);
    }

    @Override
    public <T> MutableSortedBag<T> of(Comparator<? super T> comparator, T... elements)
    {
        return this.with(comparator, elements);
    }

    @Override
    public <T> MutableSortedBag<T> with(Comparator<? super T> comparator, T... elements)
    {
        return TreeBag.newBagWith(comparator, elements);
    }

    @Override
    public <T> MutableSortedBag<T> ofAll(Iterable<? extends T> items)
    {
        return this.withAll(items);
    }

    @Override
    public <T> MutableSortedBag<T> withAll(Iterable<? extends T> items)
    {
        return TreeBag.newBag(items);
    }

    @Override
    public <T> MutableSortedBag<T> ofAll(Comparator<? super T> comparator, Iterable<? extends T> items)
    {
        return this.withAll(comparator, items);
    }

    @Override
    public <T> MutableSortedBag<T> withAll(Comparator<? super T> comparator, Iterable<? extends T> items)
    {
        return TreeBag.newBag(comparator, items);
    }
}
