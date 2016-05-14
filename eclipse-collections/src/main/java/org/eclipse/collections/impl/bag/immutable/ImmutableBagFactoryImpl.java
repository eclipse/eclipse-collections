/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.immutable;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.factory.bag.ImmutableBagFactory;
import org.eclipse.collections.impl.utility.Iterate;

public enum ImmutableBagFactoryImpl implements ImmutableBagFactory
{
    INSTANCE;

    @Override
    public <T> ImmutableBag<T> empty()
    {
        return (ImmutableBag<T>) ImmutableEmptyBag.INSTANCE;
    }

    @Override
    public <T> ImmutableBag<T> of()
    {
        return this.empty();
    }

    @Override
    public <T> ImmutableBag<T> with()
    {
        return this.empty();
    }

    @Override
    public <T> ImmutableBag<T> of(T element)
    {
        return this.with(element);
    }

    @Override
    public <T> ImmutableBag<T> with(T element)
    {
        return new ImmutableSingletonBag<>(element);
    }

    @Override
    public <T> ImmutableBag<T> of(T... elements)
    {
        return this.with(elements);
    }

    @Override
    public <T> ImmutableBag<T> with(T... elements)
    {
        if (elements == null || elements.length == 0)
        {
            return this.empty();
        }
        if (elements.length == 1)
        {
            return this.of(elements[0]);
        }
        if (elements.length < ImmutableArrayBag.MAXIMUM_USEFUL_ARRAY_BAG_SIZE)
        {
            return ImmutableArrayBag.newBagWith(elements);
        }
        return ImmutableHashBag.newBagWith(elements);
    }

    @Override
    public <T> ImmutableBag<T> ofAll(Iterable<? extends T> items)
    {
        return this.withAll(items);
    }

    @Override
    public <T> ImmutableBag<T> withAll(Iterable<? extends T> items)
    {
        if (items instanceof ImmutableBag<?>)
        {
            return (ImmutableBag<T>) items;
        }
        if (items instanceof Bag<?>)
        {
            Bag<T> bag = (Bag<T>) items;
            if (bag.isEmpty())
            {
                return this.with();
            }
            if (bag.size() == 1)
            {
                return this.with(bag.getFirst());
            }
            if (bag.sizeDistinct() < ImmutableArrayBag.MAXIMUM_USEFUL_ARRAY_BAG_SIZE)
            {
                return ImmutableArrayBag.copyFrom(bag);
            }
            return new ImmutableHashBag<>(bag);
        }
        return this.of((T[]) Iterate.toArray(items));
    }
}
