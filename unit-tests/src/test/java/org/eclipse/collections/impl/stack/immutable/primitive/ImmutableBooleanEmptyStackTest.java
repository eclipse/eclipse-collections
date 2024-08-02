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

import java.util.EmptyStackException;

import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.stack.primitive.ImmutableBooleanStack;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit test for {@link ImmutableBooleanEmptyStack}.
 */
public class ImmutableBooleanEmptyStackTest extends AbstractImmutableBooleanStackTestCase
{
    @Override
    protected ImmutableBooleanStack classUnderTest()
    {
        return ImmutableBooleanEmptyStack.INSTANCE;
    }

    @Override
    @Test
    public void pop()
    {
        assertThrows(EmptyStackException.class, () -> this.classUnderTest().pop());
    }

    @Override
    @Test
    public void pop_with_count_greater_than_stack_size_throws_exception()
    {
        assertThrows(EmptyStackException.class, () -> this.classUnderTest().pop(1));
    }

    @Override
    @Test
    public void popWithCount()
    {
        ImmutableBooleanStack stack = this.classUnderTest();
        ImmutableBooleanStack stack1 = stack.pop(0);
        assertSame(stack1, stack);
        assertEquals(this.classUnderTest(), stack);
    }

    @Override
    @Test
    public void booleanIterator()
    {
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        assertFalse(iterator.hasNext());
    }

    @Override
    @Test
    public void peek()
    {
        assertThrows(EmptyStackException.class, () -> this.classUnderTest().peek());
    }

    @Test
    public void peekWithCount()
    {
        assertEquals(BooleanArrayList.newListWith(), this.classUnderTest().peek(0));
        assertThrows(EmptyStackException.class, () -> this.classUnderTest().peek(1));
    }

    @Override
    @Test
    public void peek_at_index_equal_to_size_throws_exception()
    {
        assertThrows(EmptyStackException.class, () -> this.classUnderTest().peekAt(0));
    }

    @Override
    @Test
    public void peek_at_index_greater_than_size_throws_exception()
    {
        assertThrows(EmptyStackException.class, () -> this.classUnderTest().peekAt(1));
    }

    @Override
    @Test
    public void notEmpty()
    {
        assertFalse(this.newWith().notEmpty());
    }

    @Override
    @Test
    public void isEmpty()
    {
        Verify.assertEmpty(this.newWith());
    }

    @Test
    @Override
    public void testEquals()
    {
        super.testEquals();
        Verify.assertPostSerializedIdentity(this.classUnderTest());
    }
}
