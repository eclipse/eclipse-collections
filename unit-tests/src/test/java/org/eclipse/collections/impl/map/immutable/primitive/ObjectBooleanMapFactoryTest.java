/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable.primitive;

import org.eclipse.collections.api.map.primitive.ImmutableObjectBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableObjectBooleanMap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.ObjectBooleanMaps;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectBooleanHashMap;
import org.junit.Assert;
import org.junit.Test;

public class ObjectBooleanMapFactoryTest
{
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
