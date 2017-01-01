/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable.primitive;

import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableByteList;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableFloatList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.list.primitive.MutableShortList;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link SynchronizedBooleanList}.
 */
public class SynchronizedBooleanListTest extends AbstractBooleanListTestCase
{
    @Override
    protected SynchronizedBooleanList classUnderTest()
    {
        return new SynchronizedBooleanList(BooleanArrayList.newListWith(true, false, true));
    }

    @Override
    protected SynchronizedBooleanList newWith(boolean... elements)
    {
        return new SynchronizedBooleanList(BooleanArrayList.newListWith(elements));
    }

    @Override
    @Test
    public void asSynchronized()
    {
        super.asSynchronized();
        SynchronizedBooleanList list = this.classUnderTest();
        MutableBooleanList listWithLockObject = new SynchronizedBooleanList(BooleanArrayList.newListWith(true, false, true), new Object()).asSynchronized();
        Assert.assertEquals(list, listWithLockObject);
        Assert.assertSame(listWithLockObject, listWithLockObject.asSynchronized());
        Assert.assertSame(list, list.asSynchronized());
        Assert.assertEquals(list, list.asSynchronized());
    }

    @Test
    public void collectByte()
    {
        SynchronizedBooleanList list = this.newWith(true, false, true);

        MutableByteList mutableByteList =
                list.collectByte(each -> each ? (byte) 1 : (byte) 0);

        Assert.assertEquals(ByteArrayList.newListWith((byte) 1, (byte) 0, (byte) 1), mutableByteList);
    }

    @Test
    public void collectShort()
    {
        SynchronizedBooleanList list = this.newWith(true, false, true);

        MutableShortList mutableShortList =
                list.collectShort(each -> each ? (short) 1 : (short) 0);

        Assert.assertEquals(ShortArrayList.newListWith((short) 1, (short) 0, (short) 1), mutableShortList);
    }

    @Test
    public void collectChar()
    {
        SynchronizedBooleanList list = this.newWith(true, false, true);

        MutableCharList mutableCharList =
                list.collectChar(each -> each ? (char) 1 : (char) 0);

        Assert.assertEquals(CharArrayList.newListWith((char) 1, (char) 0, (char) 1), mutableCharList);
    }

    @Test
    public void collectInt()
    {
        SynchronizedBooleanList list = this.newWith(true, false, true);

        MutableIntList mutableIntList =
                list.collectInt(each -> each ? 1 : 0);

        Assert.assertEquals(IntArrayList.newListWith(1, 0, 1), mutableIntList);
    }

    @Test
    public void collectLong()
    {
        SynchronizedBooleanList list = this.newWith(true, false, true);

        MutableLongList mutableLongList =
                list.collectLong(each -> each ? 1L : 0L);

        Assert.assertEquals(LongArrayList.newListWith(1L, 0L, 1L), mutableLongList);
    }

    @Test
    public void collectFloat()
    {
        SynchronizedBooleanList list = this.newWith(true, false, true);

        MutableFloatList mutableFloatList =
                list.collectFloat(each -> each ? 1.0f : 0.0f);

        Assert.assertEquals(FloatArrayList.newListWith(1.0f, 0.0f, 1.0f), mutableFloatList);
    }

    @Test
    public void collectDouble()
    {
        SynchronizedBooleanList list = this.newWith(true, false, true);

        MutableDoubleList mutableDoubleList =
                list.collectDouble(each -> each ? 1.0 : 0.0);

        Assert.assertEquals(DoubleArrayList.newListWith(1.0, 0.0, 1.0), mutableDoubleList);
    }

    @Test
    public void collectBoolean()
    {
        SynchronizedBooleanList list = this.newWith(true, false, true);

        MutableBooleanList mutableBooleanList = list.collectBoolean(each -> !each);

        Assert.assertEquals(BooleanArrayList.newListWith(false, true, false), mutableBooleanList);
    }
}
