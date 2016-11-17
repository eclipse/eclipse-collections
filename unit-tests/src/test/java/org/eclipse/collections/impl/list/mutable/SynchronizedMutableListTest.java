/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link SynchronizedMutableList}.
 */
public class SynchronizedMutableListTest extends AbstractListTestCase
{
    @Override
    protected <T> MutableList<T> newWith(T... littleElements)
    {
        return new SynchronizedMutableList<>(FastList.newListWith(littleElements));
    }

    @Override
    @Test
    public void newEmpty()
    {
        super.newEmpty();

        Verify.assertInstanceOf(SynchronizedMutableList.class, this.newWith().newEmpty());
    }

    @Override
    @Test
    public void equalsAndHashCode()
    {
        super.equalsAndHashCode();
        Verify.assertPostSerializedEqualsAndHashCode(this.newWith(1, 2, 3));
        Verify.assertInstanceOf(SynchronizedMutableList.class, this.newWith(1, 2, 3));
    }

    @Override
    @Test
    public void testToString()
    {
        MutableList<Object> list = this.newWith(1, 2, 3);
        Assert.assertEquals("[1, 2, 3]", list.toString());
    }

    @Override
    @Test
    public void makeString()
    {
        Assert.assertEquals("1, 2, 3", this.newWith(1, 2, 3).makeString());
    }

    @Override
    @Test
    public void appendString()
    {
        Appendable builder = new StringBuilder();
        this.newWith(1, 2, 3).appendString(builder);
        Assert.assertEquals("1, 2, 3", builder.toString());
    }
}
