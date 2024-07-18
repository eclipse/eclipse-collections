/*
 * Copyright (c) 2021 Goldman Sachs.
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
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link ImmutableBooleanSingletonStack}.
 */
public class ImmutableBooleanSingletonStackTest extends AbstractImmutableBooleanStackTestCase
{
    @Override
    protected ImmutableBooleanStack classUnderTest()
    {
        return new ImmutableBooleanSingletonStack(true);
    }

    @Override
    @Test
    public void pop()
    {
        ImmutableBooleanStack stack = this.classUnderTest();
        ImmutableBooleanStack modified = stack.pop();
        Verify.assertEmpty(modified);
        Verify.assertSize(1, stack);
        assertNotSame(modified, stack);
        assertEquals(this.classUnderTest(), stack);
    }

    @Override
    @Test
    public void popWithCount()
    {
        ImmutableBooleanStack stack = this.classUnderTest();
        ImmutableBooleanStack stack1 = stack.pop(0);
        assertSame(stack1, stack);
        assertEquals(this.classUnderTest(), stack);
        ImmutableBooleanStack modified = stack.pop(1);
        Verify.assertEmpty(modified);
        Verify.assertSize(1, stack);
        assertNotSame(modified, stack);
        assertEquals(this.classUnderTest(), stack);
    }

    @Override
    @Test
    public void peek()
    {
        assertTrue(this.classUnderTest().peek());
        assertEquals(BooleanArrayList.newListWith(), this.classUnderTest().peek(0));
        assertEquals(BooleanArrayList.newListWith(true), this.classUnderTest().peek(1));
        assertThrows(IllegalArgumentException.class, () -> this.classUnderTest().peek(2));
    }

    @Override
    @Test
    public void testEquals()
    {
        ImmutableBooleanStack stack = this.classUnderTest();
        assertEquals(stack, stack);
        Verify.assertPostSerializedEqualsAndHashCode(stack);
        assertEquals(stack, BooleanArrayStack.newStackWith(true));
        assertNotEquals(stack, this.newWith(true, false));
        assertNotEquals(stack, BooleanArrayList.newListWith(true));
        assertEquals(stack, this.newWith(true));
        assertNotEquals(stack, this.newWith());
    }
}
