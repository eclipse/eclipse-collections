/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.set.immutable.primitive;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.factory.set.primitive.ImmutableBooleanSetFactory;
import org.eclipse.collections.api.set.primitive.ImmutableBooleanSet;

/**
 * ImmutableBooleanSetFactoryImpl is a factory implementation which creates instances of type {@link ImmutableBooleanSet}.
 *
 * @since 4.0.
 */
public class ImmutableBooleanSetFactoryImpl implements ImmutableBooleanSetFactory
{
    public ImmutableBooleanSet empty()
    {
        return ImmutableBooleanEmptySet.INSTANCE;
    }

    public ImmutableBooleanSet of()
    {
        return this.empty();
    }

    public ImmutableBooleanSet with()
    {
        return this.empty();
    }

    public ImmutableBooleanSet of(boolean one)
    {
        return this.with(one);
    }

    public ImmutableBooleanSet with(boolean one)
    {
        return one ? ImmutableTrueSet.INSTANCE : ImmutableFalseSet.INSTANCE;
    }

    public ImmutableBooleanSet of(boolean... items)
    {
        return this.with(items);
    }

    public ImmutableBooleanSet with(boolean... items)
    {
        if (items == null || items.length == 0)
        {
            return this.with();
        }
        if (items.length == 1)
        {
            return this.with(items[0]);
        }
        ImmutableBooleanSet result = ImmutableBooleanEmptySet.INSTANCE;
        for (boolean item : items)
        {
            result = result.newWith(item);
        }
        return result;
    }

    public ImmutableBooleanSet ofAll(BooleanIterable items)
    {
        return this.withAll(items);
    }

    public ImmutableBooleanSet withAll(BooleanIterable items)
    {
        if (items instanceof ImmutableBooleanSet)
        {
            return (ImmutableBooleanSet) items;
        }
        return this.with(items.toArray());
    }
}
