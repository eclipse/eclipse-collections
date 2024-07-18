/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable.primitive;

import org.eclipse.collections.api.set.primitive.ByteSet;
import org.eclipse.collections.api.set.primitive.ImmutableByteSet;
import org.eclipse.collections.impl.map.mutable.primitive.ByteByteHashMap;
import org.eclipse.collections.impl.set.immutable.primitive.AbstractImmutableByteHashSetTestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link ImmutableByteSet} created from the freeze() method.
 */
public class ImmutableByteByteMapKeySetTest extends AbstractImmutableByteHashSetTestCase
{
    @Override
    protected ImmutableByteSet classUnderTest()
    {
        return (ImmutableByteSet) ByteByteHashMap.newWithKeysValues((byte) 1, (byte) -1, (byte) 2, (byte) 2, (byte) 3, (byte) 4).keySet().freeze();
    }

    @Override
    protected ImmutableByteSet newWith(byte... elements)
    {
        ByteByteHashMap byteByteHashMap = new ByteByteHashMap();
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
        byte collision1 = AbstractImmutableByteHashSetTestCase.generateCollisions().getFirst();
        byte collision2 = AbstractImmutableByteHashSetTestCase.generateCollisions().get(1);
        ByteByteHashMap byteByteHashMap = ByteByteHashMap.newWithKeysValues(collision1, (byte) 0, collision2, (byte) 0);
        byteByteHashMap.removeKey(collision2);
        ByteSet byteSet = byteByteHashMap.keySet().freeze();
        assertTrue(byteSet.contains(collision1));
        assertFalse(byteSet.contains(collision2));
    }
}
