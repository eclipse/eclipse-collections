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

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.stack.primitive.ImmutableBooleanStack;
import org.eclipse.collections.api.stack.primitive.MutableBooleanStack;
import org.eclipse.collections.impl.factory.primitive.BooleanStacks;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.stack.mutable.primitive.BooleanArrayStack;
import org.eclipse.collections.impl.stack.primitive.AbstractBooleanStackTestCase;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Abstract JUnit test for {@link ImmutableBooleanStack}.
 */
public abstract class AbstractImmutableBooleanStackTestCase extends AbstractBooleanStackTestCase
{
    @Override
    protected abstract ImmutableBooleanStack classUnderTest();

    @Override
    protected ImmutableBooleanStack newWith(boolean... elements)
    {
        return BooleanStacks.immutable.of(elements);
    }

    @Override
    protected MutableBooleanStack newMutableCollectionWith(boolean... elements)
    {
        return BooleanArrayStack.newStackWith(elements);
    }

    @Override
    protected ImmutableBooleanStack newWithTopToBottom(boolean... elements)
    {
        return ImmutableBooleanArrayStack.newStackFromTopToBottom(elements);
    }

    protected ImmutableBooleanStack newWithIterableTopToBottom(BooleanIterable iterable)
    {
        return ImmutableBooleanArrayStack.newStackFromTopToBottom(iterable);
    }

    protected ImmutableBooleanStack newWithIterable(BooleanIterable iterable)
    {
        return ImmutableBooleanArrayStack.newStack(iterable);
    }

    @Test
    public void push()
    {
        ImmutableBooleanStack stack = this.classUnderTest();
        int size = stack.size();
        ImmutableBooleanStack modified = stack.push(true);
        assertTrue(modified.peek());
        Verify.assertSize(size + 1, modified);
        Verify.assertSize(size, stack);
        assertNotSame(modified, stack);
        assertEquals(this.classUnderTest(), stack);
    }

    @Test
    public void pop()
    {
        ImmutableBooleanStack stack = this.classUnderTest();
        int size = stack.size();
        ImmutableBooleanStack modified = stack.pop();
        assertEquals((this.classUnderTest().size() & 1) == 0, modified.peek());
        Verify.assertSize(size - 1, modified);
        Verify.assertSize(size, stack);
        assertNotSame(modified, stack);
        assertEquals(this.classUnderTest(), stack);
    }

    @Test
    public void popWithCount()
    {
        ImmutableBooleanStack stack = this.classUnderTest();
        ImmutableBooleanStack stack1 = stack.pop(0);
        assertSame(stack1, stack);
        assertEquals(this.classUnderTest(), stack);
        int size = stack.size();
        ImmutableBooleanStack modified = stack.pop(2);
        assertEquals((this.classUnderTest().size() & 1) != 0, modified.peek());
        Verify.assertSize(size - 2, modified);
        Verify.assertSize(size, stack);
        assertNotSame(modified, stack);
        assertEquals(this.classUnderTest(), stack);
    }

    @Test
    public void pop_with_negative_count_throws_exception()
    {
        assertThrows(IllegalArgumentException.class, () -> this.classUnderTest().pop(-1));
    }

    @Test
    public void pop_with_count_greater_than_stack_size_throws_exception()
    {
        assertThrows(IllegalArgumentException.class, () -> this.classUnderTest().pop(this.classUnderTest().size() + 1));
    }

    @Override
    @Test
    public void testToString()
    {
        assertEquals(this.createExpectedString("[", ", ", "]"), this.classUnderTest().toString());
    }

    @Override
    @Test
    public void makeString()
    {
        assertEquals(this.createExpectedString("", ", ", ""), this.classUnderTest().makeString());
        assertEquals(this.createExpectedString("", "|", ""), this.classUnderTest().makeString("|"));
        assertEquals(this.createExpectedString("{", "|", "}"), this.classUnderTest().makeString("{", "|", "}"));
    }

    @Override
    @Test
    public void appendString()
    {
        StringBuilder appendable1 = new StringBuilder();
        this.classUnderTest().appendString(appendable1);
        assertEquals(this.createExpectedString("", ", ", ""), appendable1.toString());

        StringBuilder appendable2 = new StringBuilder();
        this.classUnderTest().appendString(appendable2, "|");
        assertEquals(this.createExpectedString("", "|", ""), appendable2.toString());

        StringBuilder appendable3 = new StringBuilder();
        this.classUnderTest().appendString(appendable3, "{", "|", "}");
        assertEquals(this.createExpectedString("{", "|", "}"), appendable3.toString());
    }

    @Override
    @Test
    public void toList()
    {
        BooleanArrayList expected = BooleanArrayList.newListWith();
        this.classUnderTest().forEach(expected::add);
        assertEquals(expected, this.classUnderTest().toList());
    }

    @Override
    @Test
    public void toImmutable()
    {
        super.toImmutable();
        ImmutableBooleanStack expected = this.classUnderTest();
        assertSame(expected, expected.toImmutable());
    }
}
