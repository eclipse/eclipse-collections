/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ImmutableMap}.
 */
public abstract class ImmutableMapTestCase extends ImmutableMapIterableTestCase
{
    @Override
    protected abstract ImmutableMap<Integer, String> classUnderTest();

    @Test
    public void castToMap()
    {
        ImmutableMap<Integer, String> immutable = this.classUnderTest();
        Map<Integer, String> map = immutable.castToMap();
        Assert.assertSame(immutable, map);
        Assert.assertEquals(immutable, new HashMap<>(map));
    }

    @Test
    public void toMap()
    {
        ImmutableMap<Integer, String> immutable = this.classUnderTest();
        MutableMap<Integer, String> map = immutable.toMap();
        Assert.assertNotSame(immutable, map);
        Assert.assertEquals(immutable, map);
    }

    @Test
    public void entrySet()
    {
        ImmutableMap<Integer, String> immutable = this.classUnderTest();
        Map<Integer, String> map = new HashMap<>(immutable.castToMap());
        Assert.assertEquals(immutable.size(), immutable.castToMap().entrySet().size());
        Assert.assertEquals(map.entrySet(), immutable.castToMap().entrySet());
    }
}
