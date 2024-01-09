/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable.primitive;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.map.primitive.ImmutableObjectBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableObjectBooleanMap;
import org.eclipse.collections.impl.factory.primitive.ObjectBooleanMaps;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectBooleanHashMap;
import org.junit.Assert;
import org.junit.Test;

public class ObjectBooleanMapFactoryTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(new ObjectBooleanHashMap<>(), ObjectBooleanMaps.mutable.of());
        Assert.assertEquals(ObjectBooleanMaps.mutable.of(), ObjectBooleanMaps.mutable.empty());
        Assert.assertEquals(ObjectBooleanMaps.mutable.empty().toImmutable(), ObjectBooleanMaps.immutable.empty());
        Assert.assertEquals(ObjectBooleanMaps.mutable.empty().toImmutable(), ObjectBooleanMaps.immutable.of());
        Assert.assertTrue(ObjectBooleanMaps.immutable.empty() instanceof ImmutableObjectBooleanEmptyMap);
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues("2", true).toImmutable(), ObjectBooleanMaps.immutable.of("2", true));
        Assert.assertTrue(ObjectBooleanMaps.immutable.of("2", true) instanceof ImmutableObjectBooleanSingletonMap);

        Assert.assertEquals(ObjectBooleanMaps.mutable.of("2", true), ObjectBooleanHashMap.newWithKeysValues("2", true));
        Assert.assertEquals(ObjectBooleanMaps.mutable.of("2", true, 3, false),
                ObjectBooleanHashMap.newWithKeysValues("2", true, 3, false));
        Assert.assertEquals(ObjectBooleanMaps.mutable.of("2", true, 3, false, 4, false),
                ObjectBooleanHashMap.newWithKeysValues("2", true, 3, false, 4, false));
        Assert.assertEquals(ObjectBooleanMaps.mutable.of("2", true, 3, false, 4, false, 5, true),
                ObjectBooleanHashMap.newWithKeysValues("2", true, 3, false, 4, false, 5, true));
    }

    @Test
    public void with()
    {
        Assert.assertEquals(ObjectBooleanMaps.mutable.with(), ObjectBooleanMaps.mutable.empty());
        Assert.assertEquals(ObjectBooleanMaps.mutable.with("2", false), ObjectBooleanHashMap.newWithKeysValues("2", false));
        Assert.assertEquals(ObjectBooleanMaps.mutable.with("2", true), ObjectBooleanHashMap.newWithKeysValues("2", true));
        Assert.assertEquals(ObjectBooleanMaps.mutable.with("2", true, 3, false),
                ObjectBooleanHashMap.newWithKeysValues("2", true, 3, false));
        Assert.assertEquals(ObjectBooleanMaps.mutable.with("2", true, 3, false, 4, false),
                ObjectBooleanHashMap.newWithKeysValues("2", true, 3, false, 4, false));
        Assert.assertEquals(ObjectBooleanMaps.mutable.with("2", true, 3, false, 4, false, 5, true),
                ObjectBooleanHashMap.newWithKeysValues("2", true, 3, false, 4, false, 5, true));
    }

    @Test
    public void ofAll()
    {
        Assert.assertEquals(ObjectBooleanMaps.mutable.empty(), ObjectBooleanMaps.mutable.ofAll(ObjectBooleanMaps.mutable.empty()));
        Assert.assertEquals(ObjectBooleanMaps.mutable.empty().toImmutable(), ObjectBooleanMaps.immutable.ofAll(ObjectBooleanMaps.mutable.empty()));
        Assert.assertSame(ObjectBooleanMaps.immutable.empty(), ObjectBooleanMaps.immutable.ofAll(ObjectBooleanMaps.immutable.empty()));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues("2", true), ObjectBooleanMaps.mutable.ofAll(ObjectBooleanHashMap.newWithKeysValues("2", true)));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues("2", true).toImmutable(), ObjectBooleanMaps.immutable.ofAll(ObjectBooleanHashMap.newWithKeysValues("2", true)));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues("2", true, "3", false).toImmutable(), ObjectBooleanMaps.immutable.ofAll(ObjectBooleanHashMap.newWithKeysValues("2", true, "3", false)));
    }

    @Test
    public void from()
    {
        Iterable<String> iterable = Lists.mutable.with("1", "2", "3");
        Assert.assertEquals(
                ObjectBooleanHashMap.newWithKeysValues("1", false, "2", true, "3", false),
                ObjectBooleanMaps.mutable.from(iterable, each -> each, each -> Integer.valueOf(each) % 2 == 0));
        Assert.assertTrue(ObjectBooleanMaps.mutable.from(iterable, each -> each, each -> Integer.valueOf(each) % 2 == 0) instanceof MutableObjectBooleanMap);
        Assert.assertEquals(
                ObjectBooleanHashMap.newWithKeysValues("1", false, "2", true, "3", false),
                ObjectBooleanMaps.immutable.from(iterable, each -> each, each -> Integer.valueOf(each) % 2 == 0));
        Assert.assertTrue(ObjectBooleanMaps.immutable.from(iterable, each -> each, each -> Integer.valueOf(each) % 2 == 0) instanceof ImmutableObjectBooleanMap);
    }
}
