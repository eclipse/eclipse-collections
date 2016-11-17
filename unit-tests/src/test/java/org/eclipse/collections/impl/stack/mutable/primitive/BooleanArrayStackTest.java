/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.mutable.primitive;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.stack.primitive.MutableBooleanStack;
import org.eclipse.collections.impl.collection.mutable.primitive.AbstractMutableBooleanStackTestCase;
import org.eclipse.collections.impl.factory.primitive.BooleanStacks;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link BooleanArrayStack}.
 */
public class BooleanArrayStackTest extends AbstractMutableBooleanStackTestCase
{
    @Override
    protected MutableBooleanStack classUnderTest()
    {
        return BooleanStacks.mutable.with(true, false, true, false);
    }

    @Override
    protected MutableBooleanStack newWith(boolean... elements)
    {
        return BooleanStacks.mutable.of(elements);
    }

    @Override
    protected MutableBooleanStack newMutableCollectionWith(boolean... elements)
    {
        return BooleanArrayStack.newStackWith(elements);
    }

    @Override
    protected RichIterable<Object> newObjectCollectionWith(Object... elements)
    {
        return ArrayStack.newStackWith(elements);
    }

    @Override
    protected MutableBooleanStack newWithTopToBottom(boolean... elements)
    {
        return BooleanArrayStack.newStackFromTopToBottom(elements);
    }

    @Override
    protected MutableBooleanStack newWithIterableTopToBottom(BooleanIterable iterable)
    {
        return BooleanStacks.mutable.ofAllReversed(iterable);
    }

    @Override
    protected MutableBooleanStack newWithIterable(BooleanIterable iterable)
    {
        return BooleanStacks.mutable.ofAll(iterable);
    }

    @Test
    public void testPushPopAndPeek()
    {
        BooleanArrayStack stack = BooleanArrayStack.newStackFromTopToBottom();
        stack.push(true);
        Assert.assertTrue(stack.peek());
        Assert.assertEquals(BooleanArrayStack.newStackFromTopToBottom(true), stack);

        stack.push(false);
        Assert.assertFalse(stack.peek());
        Assert.assertEquals(BooleanArrayStack.newStackFromTopToBottom(false, true), stack);

        stack.push(true);
        Assert.assertTrue(stack.peek());
        Assert.assertEquals(BooleanArrayStack.newStackFromTopToBottom(true, false, true), stack);

        Assert.assertFalse(stack.peekAt(1));
        Assert.assertTrue(stack.pop());
        Assert.assertFalse(stack.peek());
        Assert.assertFalse(stack.pop());
        Assert.assertTrue(stack.peek());
        Assert.assertTrue(stack.pop());

        BooleanArrayStack stack2 = BooleanArrayStack.newStackFromTopToBottom(true, false, true, false, true);
        stack2.pop(2);
        Assert.assertEquals(BooleanArrayStack.newStackFromTopToBottom(true, false, true), stack2);
        Assert.assertEquals(BooleanArrayList.newListWith(true, false), stack2.peek(2));

        BooleanArrayStack stack8 = BooleanArrayStack.newStackFromTopToBottom(false, true, false, true);
        Verify.assertEmpty(stack8.pop(0));
        Assert.assertEquals(BooleanArrayStack.newStackFromTopToBottom(false, true, false, true), stack8);
        Assert.assertEquals(new BooleanArrayList(), stack8.peek(0));

        BooleanArrayStack stack9 = BooleanArrayStack.newStackFromTopToBottom();
        Assert.assertEquals(new BooleanArrayList(), stack9.pop(0));
        Assert.assertEquals(new BooleanArrayList(), stack9.peek(0));
    }
}
