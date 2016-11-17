/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable.primitive;

import org.eclipse.collections.api.map.primitive.ImmutableObjectBooleanMap;
import org.eclipse.collections.impl.map.mutable.primitive.AbstractObjectBooleanMapTestCase;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectBooleanHashMap;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractImmutableObjectBooleanMapTestCase extends AbstractObjectBooleanMapTestCase
{
    @Override
    protected abstract ImmutableObjectBooleanMap<String> classUnderTest();

    @Override
    protected <T> ImmutableObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1)
    {
        return ObjectBooleanHashMap.newWithKeysValues(key1, value1).toImmutable();
    }

    @Override
    protected <T> ImmutableObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1, T key2, boolean value2)
    {
        return ObjectBooleanHashMap.newWithKeysValues(key1, value1, key2, value2).toImmutable();
    }

    @Override
    protected <T> ImmutableObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1, T key2, boolean value2, T key3, boolean value3)
    {
        return ObjectBooleanHashMap.newWithKeysValues(key1, value1, key2, value2, key3, value3).toImmutable();
    }

    @Override
    protected <T> ImmutableObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1, T key2, boolean value2, T key3, boolean value3, T key4, boolean value4)
    {
        return ObjectBooleanHashMap.newWithKeysValues(key1, value1, key2, value2, key3, value3, key4, value4).toImmutable();
    }

    @Override
    protected <T> ImmutableObjectBooleanMap<T> getEmptyMap()
    {
        return new ObjectBooleanHashMap<T>().toImmutable();
    }

    @Override
    @Test
    public void toImmutable()
    {
        super.toImmutable();
        ImmutableObjectBooleanMap<String> map = this.classUnderTest();
        Assert.assertSame(map, map.toImmutable());
    }
}
