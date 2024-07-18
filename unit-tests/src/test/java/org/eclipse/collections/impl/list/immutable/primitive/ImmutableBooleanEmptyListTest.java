/*
 * Copyright (c) 2021 Goldman Sachs.
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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals(this.newWith(), newList);
        assertSame(emptyList, newList);
        assertEquals(this.newMutableCollectionWith(), emptyList);
    }

    @Override
    @Test
    public void get()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.classUnderTest().get(0));
    }

    @Override
    @Test
    public void getFirst()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.classUnderTest().getFirst());
    }

    @Override
    @Test
    public void getLast()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.classUnderTest().getLast());
    }

    @Override
    @Test
    public void indexOf()
    {
        assertEquals(-1L, this.classUnderTest().indexOf(true));
        assertEquals(-1L, this.classUnderTest().indexOf(false));
    }

    @Override
    @Test
    public void lastIndexOf()
    {
        assertEquals(-1L, this.classUnderTest().lastIndexOf(true));
        assertEquals(-1L, this.classUnderTest().lastIndexOf(false));
    }

    @Override
    @Test
    public void forEachWithIndex()
    {
        String[] sum = new String[2];
        sum[0] = "";
        this.classUnderTest().forEachWithIndex((each, index) -> sum[0] += index + ":" + each);
        assertEquals("", sum[0]);
    }

    @Override
    @Test
    public void toReversed()
    {
        assertEquals(this.classUnderTest(), this.classUnderTest().toReversed());
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
        assertFalse(this.classUnderTest().notEmpty());
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
        assertSame(iterable, booleanIterable);
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
        assertSame(iterable, booleanIterable);
    }

    @Override
    @Test
    public void testEquals()
    {
        Verify.assertEqualsAndHashCode(this.newMutableCollectionWith(), this.classUnderTest());
        Verify.assertPostSerializedIdentity(this.newWith());
        assertNotEquals(this.classUnderTest(), this.newWith(false, false, false, true));
        assertNotEquals(this.classUnderTest(), this.newWith(true));
    }
}
