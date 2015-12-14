/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.immutable.primitive;

import org.eclipse.collections.api.stack.primitive.ImmutableBooleanStack;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.stack.mutable.primitive.BooleanArrayStack;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ImmutableBooleanArrayStack}.
 */
public class ImmutableBooleanArrayStackTest extends AbstractImmutableBooleanStackTestCase
{
    @Override
    protected ImmutableBooleanStack classUnderTest()
    {
        return ImmutableBooleanArrayStack.newStackWith(true, false, true, false);
    }

    @Test
    public void newWithIterable()
    {
        Assert.assertEquals(BooleanArrayStack.newStackWith(true, true, false), this.newWithIterable(BooleanArrayList.newListWith(true, true, false)));
    }

    @Test
    public void newWithTopToBottom()
    {
        Assert.assertEquals(BooleanArrayStack.newStackFromTopToBottom(true, true, false), this.newWithTopToBottom(true, true, false));
    }
}
