/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable.primitive;

import java.lang.reflect.Field;
import java.util.BitSet;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link BooleanArrayList}.
 */
public class BooleanArrayListTest extends AbstractBooleanListTestCase
{
    private final BooleanArrayList list = this.classUnderTest();

    @Override
    protected final BooleanArrayList classUnderTest()
    {
        return BooleanArrayList.newListWith(true, false, true);
    }

    @Override
    protected BooleanArrayList newWith(boolean... elements)
    {
        return BooleanArrayList.newListWith(elements);
    }

    @Test
    public void testBooleanArrayListWithInitialCapacity() throws Exception
    {
        BooleanArrayList arrayList = new BooleanArrayList(7);
        Verify.assertEmpty(arrayList);
        Field items = BooleanArrayList.class.getDeclaredField("items");
        items.setAccessible(true);
        Assert.assertEquals(64L, ((BitSet) items.get(arrayList)).size());
        BooleanArrayList arrayList1 = new BooleanArrayList(64);
        Assert.assertEquals(64L, ((BitSet) items.get(arrayList1)).size());
        BooleanArrayList arrayList2 = new BooleanArrayList(65);
        Assert.assertEquals(128L, ((BitSet) items.get(arrayList2)).size());
    }

    @Test
    public void addAtIndexAtCapacity() throws Exception
    {
        BooleanArrayList listWithCapacity = new BooleanArrayList(64);
        for (int i = 0; i < 64; i++)
        {
            listWithCapacity.add((i & 1) == 0);
        }
        listWithCapacity.addAtIndex(64, true);
        Field items = BooleanArrayList.class.getDeclaredField("items");
        items.setAccessible(true);
        Assert.assertEquals(128L, ((BitSet) items.get(listWithCapacity)).size());
    }

    @Override
    @Test
    public void size()
    {
        super.size();
        Verify.assertSize(0, new BooleanArrayList());
        Verify.assertSize(0, new BooleanArrayList(1));
        Verify.assertSize(1, BooleanArrayList.newListWith(false));
        Verify.assertSize(3, this.list);
        Verify.assertSize(3, BooleanArrayList.newList(this.list));
    }

    @Override
    @Test
    public void with()
    {
        super.with();
        BooleanArrayList emptyList = new BooleanArrayList();
        BooleanArrayList arrayList = emptyList.with(true);
        BooleanArrayList arrayList0 = new BooleanArrayList().with(false, false);
        BooleanArrayList arrayList1 = new BooleanArrayList().with(true, true, false);
        BooleanArrayList arrayList2 = new BooleanArrayList().with(true, true, false, true);
        BooleanArrayList arrayList3 = new BooleanArrayList().with(true, true, false, true, false);
        Assert.assertSame(emptyList, arrayList);
        Assert.assertEquals(BooleanArrayList.newListWith(true), arrayList);
        Assert.assertEquals(BooleanArrayList.newListWith(false, false), arrayList0);
        Assert.assertEquals(BooleanArrayList.newListWith(true, true, false), arrayList1);
        Assert.assertEquals(BooleanArrayList.newListWith(true, true, false, true), arrayList2);
        Assert.assertEquals(BooleanArrayList.newListWith(true, true, false, true, false), arrayList3);
    }
}
