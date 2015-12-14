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

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.list.primitive.ImmutableBooleanList;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableBooleanEmptyListTest extends AbstractImmutableBooleanListTestCase
{
    @Override
    protected ImmutableBooleanList classUnderTest()
    {
        return ImmutableBooleanEmptyList.INSTANCE;
    }

    @Override
    @Test
    public void newWithout()
    {
        ImmutableBooleanList emptyList = this.newWith();
        ImmutableBooleanList newList = emptyList.newWithout(true);
        Assert.assertEquals(this.newWith(), newList);
        Assert.assertSame(emptyList, newList);
        Assert.assertEquals(this.newMutableCollectionWith(), emptyList);
    }

    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void get()
    {
        this.classUnderTest().get(0);
    }

    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void getFirst()
    {
        this.classUnderTest().getFirst();
    }

    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void getLast()
    {
        this.classUnderTest().getLast();
    }

    @Override
    @Test
    public void indexOf()
    {
        Assert.assertEquals(-1L, this.classUnderTest().indexOf(true));
        Assert.assertEquals(-1L, this.classUnderTest().indexOf(false));
    }

    @Override
    @Test
    public void lastIndexOf()
    {
        Assert.assertEquals(-1L, this.classUnderTest().lastIndexOf(true));
        Assert.assertEquals(-1L, this.classUnderTest().lastIndexOf(false));
    }

    @Override
    @Test
    public void forEachWithIndex()
    {
        String[] sum = new String[2];
        sum[0] = "";
        this.classUnderTest().forEachWithIndex((each, index) -> sum[0] += index + ":" + each);
        Assert.assertEquals("", sum[0]);
    }

    @Override
    @Test
    public void toReversed()
    {
        Assert.assertEquals(this.classUnderTest(), this.classUnderTest().toReversed());
    }

    @Override
    @Test
    public void isEmpty()
    {
        Verify.assertEmpty(this.classUnderTest());
    }

    @Override
    @Test
    public void notEmpty()
    {
        Assert.assertFalse(this.classUnderTest().notEmpty());
    }

    @Override
    @Test
    public void select()
    {
        super.select();
        BooleanIterable iterable = this.classUnderTest();
        Verify.assertEmpty(iterable.select(BooleanPredicates.isTrue()));
        BooleanIterable booleanIterable = iterable.select(BooleanPredicates.isFalse());
        Verify.assertEmpty(booleanIterable);
        Assert.assertSame(iterable, booleanIterable);
    }

    @Override
    @Test
    public void reject()
    {
        super.reject();
        BooleanIterable iterable = this.classUnderTest();
        Verify.assertEmpty(iterable.reject(BooleanPredicates.isTrue()));
        BooleanIterable booleanIterable = iterable.reject(BooleanPredicates.isFalse());
        Verify.assertEmpty(booleanIterable);
        Assert.assertSame(iterable, booleanIterable);
    }

    @Override
    @Test
    public void testEquals()
    {
        Verify.assertEqualsAndHashCode(this.newMutableCollectionWith(), this.classUnderTest());
        Verify.assertPostSerializedIdentity(this.newWith());
        Assert.assertNotEquals(this.classUnderTest(), this.newWith(false, false, false, true));
        Assert.assertNotEquals(this.classUnderTest(), this.newWith(true));
    }
}
