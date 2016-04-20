/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable;

import net.jcip.annotations.Immutable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.factory.bag.MutableBagFactory;

@Immutable
public final class MutableBagFactoryImpl implements MutableBagFactory
{
    public <T> MutableBag<T> empty()
    {
        return HashBag.newBag();
    }

    public <T> MutableBag<T> of()
    {
        return this.empty();
    }

    public <T> MutableBag<T> with()
    {
        return this.empty();
    }

    public <T> MutableBag<T> of(T... elements)
    {
        return this.with(elements);
    }

    public <T> MutableBag<T> with(T... elements)
    {
        return HashBag.newBagWith(elements);
    }

    public <T> MutableBag<T> ofAll(Iterable<? extends T> items)
    {
        return this.withAll(items);
    }

    public <T> MutableBag<T> withAll(Iterable<? extends T> items)
    {
        return HashBag.newBag(items);
    }
}
