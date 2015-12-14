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

import java.util.SortedSet;

import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.IntegerWithCast;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractUnifiedSetTestCase extends AbstractMutableSetTestCase
{
    @Test
    public void addOnObjectWithCastInEquals()
    {
        if (this.newWith() instanceof SortedSet)
        {
            return;
        }
        MutableSet<IntegerWithCast> mutableSet = this.newWith(new IntegerWithCast(0));
        Assert.assertFalse(mutableSet.add(new IntegerWithCast(0)));
        Assert.assertTrue(mutableSet.add(null));
        Assert.assertFalse(mutableSet.add(null));
    }

    @Test
    public void retainAllFromKeySet_null_collision()
    {
        IntegerWithCast key = new IntegerWithCast(0);
        MutableSet<IntegerWithCast> mutableSet = this.newWith(null, key);

        Assert.assertFalse(mutableSet.retainAll(FastList.newListWith(key, null)));

        Assert.assertEquals(
                this.newWith(null, key),
                mutableSet);
    }

    @Test
    public void rehash_null_collision()
    {
        MutableSet<IntegerWithCast> mutableMap = this.newWith((IntegerWithCast) null);

        for (int i = 0; i < 1000; i++)
        {
            mutableMap.add(new IntegerWithCast(i));
        }
    }
}
