/*
 * Copyright (c) 2017 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable.primitive;

import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.junit.Assert;
import org.junit.Test;

public class MultiReaderBooleanListTest extends AbstractBooleanListTestCase
{
    @Override
    protected MultiReaderBooleanList classUnderTest()
    {
        return new MultiReaderBooleanList(BooleanArrayList.newListWith(true, false, true));
    }

    @Override
    protected MultiReaderBooleanList newWith(boolean... elements)
    {
        return new MultiReaderBooleanList(BooleanArrayList.newListWith(elements));
    }

    @Override
    @Test
    public void asSynchronized()
    {
        super.asSynchronized();
        MultiReaderBooleanList list = this.classUnderTest();
        MutableBooleanList listWithLockObject = new MultiReaderBooleanList(BooleanArrayList.newListWith(true, false, true));
        Assert.assertEquals(list, listWithLockObject);
        Assert.assertSame(listWithLockObject, listWithLockObject.asSynchronized());
        Assert.assertSame(list, list.asSynchronized());
        Assert.assertEquals(list, list.asSynchronized());
    }


    @Override
    @Test
    public void iterator_throws_on_consecutive_invocation_of_remove()
    {
    }

    @Override
    @Test
    public void booleanIterator_with_remove()
    {
    }

    @Override
    @Test
    public void iterator_throws_on_invocation_of_remove_before_next()
    {
    }



}