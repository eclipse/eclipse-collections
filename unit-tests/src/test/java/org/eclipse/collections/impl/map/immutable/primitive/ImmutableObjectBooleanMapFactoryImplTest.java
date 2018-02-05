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

import org.eclipse.collections.impl.factory.primitive.ObjectBooleanMaps;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectBooleanHashMap;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableObjectBooleanMapFactoryImplTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(new ObjectBooleanHashMap<String>().toImmutable(), ObjectBooleanMaps.immutable.of());
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues("1", true).toImmutable(), ObjectBooleanMaps.immutable.of("1", true));
    }

    @Test
    public void with()
    {
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues("1", true).toImmutable(), ObjectBooleanMaps.immutable.with("1", true));
    }

    @Test
    public void ofAll()
    {
        Assert.assertEquals(new ObjectBooleanHashMap<>().toImmutable(), ObjectBooleanMaps.immutable.ofAll(ObjectBooleanMaps.immutable.of()));
    }

    @Test
    public void withAll()
    {
        Assert.assertEquals(new ObjectBooleanHashMap<>().toImmutable(), ObjectBooleanMaps.immutable.withAll(ObjectBooleanMaps.immutable.of()));
    }
}
