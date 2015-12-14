/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable.primitive;

import org.eclipse.collections.api.list.primitive.ImmutableBooleanList;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ImmutableBooleanArrayList}.
 */
public class ImmutableBooleanArrayListTest extends AbstractImmutableBooleanListTestCase
{
    @Override
    protected ImmutableBooleanList classUnderTest()
    {
        return ImmutableBooleanArrayList.newListWith(true, false, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newCollection_throws_empty()
    {
        ImmutableBooleanArrayList.newListWith();
    }

    @Test(expected = IllegalArgumentException.class)
    public void newCollection_throws_single()
    {
        ImmutableBooleanArrayList.newListWith(true);
    }

    @Override
    @Test
    public void newCollection()
    {
        super.newCollection();
        Assert.assertEquals(BooleanArrayList.newListWith(true, false, true), ImmutableBooleanArrayList.newList(BooleanArrayList.newListWith(true, false, true)));
    }

    @Override
    @Test
    public void size()
    {
        super.size();
        Verify.assertSize(3, ImmutableBooleanArrayList.newList(BooleanArrayList.newListWith(true, false, true)));
        Verify.assertSize(3, BooleanLists.immutable.ofAll(ImmutableBooleanArrayList.newList(BooleanArrayList.newListWith(true, false, true))));
    }
}
