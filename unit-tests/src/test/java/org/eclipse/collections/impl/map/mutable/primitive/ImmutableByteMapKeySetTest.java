/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import org.eclipse.collections.api.set.primitive.ByteSet;
import org.eclipse.collections.api.set.primitive.ImmutableByteSet;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.AbstractImmutableByteHashSetTestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ImmutableByteSet} created from the freeze() method.
 */
public class ImmutableByteMapKeySetTest extends AbstractImmutableByteHashSetTestCase
{
    protected static ByteArrayList generateCollisions()
    {
        ByteArrayList collisions = new ByteArrayList();
        ImmutableByteMapKeySet set = (ImmutableByteMapKeySet) new ByteShortHashMap().keySet().freeze();
        for (byte i = 2; collisions.size() <= 10; i++)
        {
            if (set.spreadAndMask(i) == set.spreadAndMask((byte) 2))
            {
                collisions.add(i);
            }
        }
        return collisions;
    }

    @Override
    protected ImmutableByteSet classUnderTest()
    {
        return (ImmutableByteSet) ByteShortHashMap.newWithKeysValues((byte) 1, (short) -1, (byte) 2, (short) 2, (byte) 3, (short) 4).keySet().freeze();
    }

    @Override
    protected ImmutableByteSet newWith(byte... elements)
    {
        ByteShortHashMap byteByteHashMap = new ByteShortHashMap();
        for (byte element : elements)
        {
            byteByteHashMap.put(element, element);
        }
        return (ImmutableByteSet) byteByteHashMap.keySet().freeze();
    }

    @Test
    @Override
    public void contains()
    {
        super.contains();
        byte collision1 = ImmutableByteMapKeySetTest.generateCollisions().getFirst();
        byte collision2 = ImmutableByteMapKeySetTest.generateCollisions().get(1);
        ByteShortHashMap byteShortHashMap = ByteShortHashMap.newWithKeysValues(collision1, (short) 0, collision2, (short) 0);
        byteShortHashMap.removeKey(collision2);
        ByteSet byteSet = byteShortHashMap.keySet().freeze();
        Assert.assertTrue(byteSet.contains(collision1));
        Assert.assertFalse(byteSet.contains(collision2));
    }
}
