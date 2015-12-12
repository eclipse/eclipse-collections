/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable;

import org.eclipse.collections.api.factory.set.MutableSetFactory;
import org.eclipse.collections.api.set.MutableSet;
import net.jcip.annotations.Immutable;

@Immutable
public final class MutableSetFactoryImpl implements MutableSetFactory
{
    public <T> MutableSet<T> empty()
    {
        return UnifiedSet.newSet();
    }

    public <T> MutableSet<T> of()
    {
        return this.empty();
    }

    public <T> MutableSet<T> with()
    {
        return this.empty();
    }

    public <T> MutableSet<T> ofInitialCapacity(int capacity)
    {
        return this.withInitialCapacity(capacity);
    }

    public <T> MutableSet<T> withInitialCapacity(int capacity)
    {
        return UnifiedSet.newSet(capacity);
    }

    public <T> MutableSet<T> of(T... items)
    {
        return this.with(items);
    }

    public <T> MutableSet<T> with(T... items)
    {
        return UnifiedSet.newSetWith(items);
    }

    public <T> MutableSet<T> ofAll(Iterable<? extends T> items)
    {
        return this.withAll(items);
    }

    public <T> MutableSet<T> withAll(Iterable<? extends T> items)
    {
        return UnifiedSet.newSet(items);
    }
}
