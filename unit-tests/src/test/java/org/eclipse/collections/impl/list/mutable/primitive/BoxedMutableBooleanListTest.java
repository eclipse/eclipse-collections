/*
 * Copyright (c) 2023 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable.primitive;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class BoxedMutableBooleanListTest
{
    private BoxedMutableBooleanList classUnderTest()
    {
        return new BoxedMutableBooleanList(new BooleanArrayList(true, true));
    }

    @Test
    public void size()
    {
        BoxedMutableBooleanList list = this.classUnderTest();
        Verify.assertSize(2, list);
        list.add(false);
        Verify.assertSize(3, list);
    }

    @Test
    public void add()
    {
        BoxedMutableBooleanList list = this.classUnderTest();
        list.add(Boolean.FALSE);
        Assert.assertEquals(Lists.mutable.of(true, true, false), list);
        list.add(Boolean.TRUE);
        Assert.assertEquals(Lists.mutable.of(true, true, false, true), list);
    }

    @Test
    public void mutationOfOriginalList()
    {
        BooleanArrayList originalList = new BooleanArrayList(true, false);
        BoxedMutableBooleanList list = new BoxedMutableBooleanList(originalList);
        Assert.assertEquals(list, Lists.mutable.of(Boolean.TRUE, Boolean.FALSE));

        originalList.add(true);
        Assert.assertEquals(list, Lists.mutable.of(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE));

        originalList.remove(true);
        Assert.assertEquals(list, Lists.mutable.of(Boolean.FALSE, Boolean.TRUE));

        originalList.addAllAtIndex(1, false);
        Assert.assertEquals(list, Lists.mutable.of(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE));

        originalList.clear();
        Verify.assertEmpty(list);
    }

    @Test
    public void addAll()
    {
        BoxedMutableBooleanList list = this.classUnderTest();
        list.addAll(0, Lists.mutable.of(Boolean.FALSE, Boolean.FALSE));
        Assert.assertEquals(Lists.mutable.of(false, false, true, true), list);

        list.addAll(4, Lists.mutable.of(Boolean.FALSE, Boolean.TRUE));
        Assert.assertEquals(Lists.mutable.of(false, false, true, true, false, true), list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllIndexOutOfBounds()
    {
        this.classUnderTest().addAll(5, Lists.mutable.of(true));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllNegativeIndex()
    {
        this.classUnderTest().addAll(-2, Lists.mutable.of(true));
    }

    @Test
    public void clear()
    {
        BoxedMutableBooleanList list = this.classUnderTest();
        list.clear();
        Verify.assertEmpty(list);
    }

    @Test
    public void get()
    {
        BoxedMutableBooleanList list = this.classUnderTest();
        list.add(Boolean.FALSE);
        Assert.assertTrue(list.get(0));
        Assert.assertFalse(list.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBounds()
    {
        this.classUnderTest().get(2);
    }

    @Test
    public void set()
    {
        BoxedMutableBooleanList list = this.classUnderTest();
        list.set(0, Boolean.FALSE);
        Assert.assertEquals(Lists.mutable.of(false, true), list);

        list.set(1, Boolean.FALSE);
        Assert.assertEquals(Lists.mutable.of(false, false), list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setIndexOutOfBounds()
    {
        this.classUnderTest().set(2, Boolean.TRUE);
    }

    @Test
    public void addAtIndex()
    {
        BoxedMutableBooleanList list = this.classUnderTest();
        list.add(0, Boolean.FALSE);
        Assert.assertEquals(Lists.mutable.of(false, true, true), list);

        list.add(1, Boolean.TRUE);
        Assert.assertEquals(Lists.mutable.of(false, true, true, true), list);

        list.add(4, Boolean.FALSE);
        Assert.assertEquals(Lists.mutable.of(false, true, true, true, false), list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtIndexOutOfBounds()
    {
        this.classUnderTest().add(3, Boolean.FALSE);
    }

    @Test
    public void remove()
    {
        BoxedMutableBooleanList booleanList = this.classUnderTest();
        booleanList.add(Boolean.FALSE);
        Assert.assertTrue(booleanList.remove(0));
        Assert.assertFalse(booleanList.remove(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexOutOfBounds()
    {
        this.classUnderTest().remove(10);
    }

    @Test
    public void indexOf()
    {
        BoxedMutableBooleanList booleanList = this.classUnderTest();
        Assert.assertEquals(0, booleanList.indexOf(Boolean.TRUE));
        Assert.assertEquals(-1, booleanList.indexOf(Boolean.FALSE));
        Assert.assertEquals(-1, booleanList.indexOf("String"));
    }

    @Test
    public void lastIndexOf()
    {
        BoxedMutableBooleanList booleanList = this.classUnderTest();
        Assert.assertEquals(1, booleanList.lastIndexOf(Boolean.TRUE));
        Assert.assertEquals(-1, booleanList.lastIndexOf(Boolean.FALSE));
        Assert.assertEquals(-1, booleanList.lastIndexOf("String"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void subList()
    {
        this.classUnderTest().subList(0, 1);
    }
}
