/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;

public final class CollisionGeneratorUtil
{
    private CollisionGeneratorUtil()
    {
        //not meant to instantiate
    }

    public static ByteArrayList generateCollisions()
    {
        ByteArrayList collisions = new ByteArrayList();
        ImmutableByteMapKeySet set = (ImmutableByteMapKeySet) new ByteShortHashMap().keySet().freeze();

        Method m;
        try
        {
            m = set.getClass().getDeclaredMethod("spreadAndMask", byte.class);
            m.setAccessible(true);
        }
        catch (NoSuchMethodException ex)
        {
            throw new RuntimeException("Unable to find method: " + ex.getMessage());
        }

        try
        {
            for (byte b = 2; collisions.size() <= 10; b++)
            {
                if (m.invoke(set, b).equals(m.invoke(set, (byte) 2)))
                {
                    collisions.add(b);
                }
            }
        }
        catch (InvocationTargetException | IllegalAccessException ex)
        {
            throw new RuntimeException("Unable to invoke method: " + ex.getMessage());
        }

        return collisions;
    }
}
