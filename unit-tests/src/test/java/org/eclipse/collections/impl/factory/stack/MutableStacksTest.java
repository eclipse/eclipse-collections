/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.stack;

import java.util.stream.Stream;

import org.eclipse.collections.api.stack.ImmutableStack;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class MutableStacksTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(ArrayStack.newStack(), MutableStack.of());
        Verify.assertInstanceOf(MutableStack.class, MutableStack.of());
        Assert.assertEquals(ArrayStack.newStackWith(1), MutableStack.of(1));
        Verify.assertInstanceOf(MutableStack.class, MutableStack.of(1));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2), MutableStack.of(1, 2));
        Verify.assertInstanceOf(MutableStack.class, MutableStack.of(1, 2));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3), MutableStack.of(1, 2, 3));
        Verify.assertInstanceOf(MutableStack.class, MutableStack.of(1, 2, 3));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4), MutableStack.of(1, 2, 3, 4));
        Verify.assertInstanceOf(MutableStack.class, MutableStack.of(1, 2, 3, 4));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4, 5), MutableStack.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(MutableStack.class, MutableStack.of(1, 2, 3, 4, 5));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4, 5, 6), MutableStack.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(MutableStack.class, MutableStack.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4, 5, 6, 7), MutableStack.of(1, 2, 3, 4, 5, 6, 7));
        Verify.assertInstanceOf(MutableStack.class, MutableStack.of(1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4, 5, 6, 7, 8), MutableStack.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(MutableStack.class, MutableStack.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4, 5, 6, 7, 8, 9), MutableStack.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Verify.assertInstanceOf(MutableStack.class, MutableStack.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), MutableStack.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Verify.assertInstanceOf(MutableStack.class, MutableStack.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Assert.assertEquals(ArrayStack.newStackWith(3, 2, 1), MutableStack.ofAll(ArrayStack.newStackWith(1, 2, 3)));
        Verify.assertInstanceOf(MutableStack.class, MutableStack.ofAll(ArrayStack.newStackWith(1, 2, 3)));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3), MutableStack.fromStream(Stream.of(1, 2, 3)));
        Verify.assertInstanceOf(MutableStack.class, MutableStack.fromStream(Stream.of(1, 2, 3)));
    }

    @SuppressWarnings("RedundantArrayCreation")
    @Test
    public void newStackWithArray()
    {
        ImmutableStack<String> stack = Stacks.immutable.of();
        Assert.assertEquals(stack = stack.push("1"), Stacks.immutable.of(new String[]{"1"}));
        Assert.assertEquals(stack = stack.push("2"), Stacks.immutable.of(new String[]{"1", "2"}));
        Assert.assertEquals(stack = stack.push("3"), Stacks.immutable.of(new String[]{"1", "2", "3"}));
        Assert.assertEquals(stack = stack.push("4"), Stacks.immutable.of(new String[]{"1", "2", "3", "4"}));
        Assert.assertEquals(stack = stack.push("5"), Stacks.immutable.of(new String[]{"1", "2", "3", "4", "5"}));
        Assert.assertEquals(stack = stack.push("6"), Stacks.immutable.of(new String[]{"1", "2", "3", "4", "5", "6"}));
        Assert.assertEquals(stack = stack.push("7"), Stacks.immutable.of(new String[]{"1", "2", "3", "4", "5", "6", "7"}));
        Assert.assertEquals(stack = stack.push("8"), Stacks.immutable.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}));
        Assert.assertEquals(stack = stack.push("9"), Stacks.immutable.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
        Assert.assertEquals(stack = stack.push("10"), Stacks.immutable.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        Assert.assertEquals(stack = stack.push("11"), Stacks.immutable.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
    }

    @Test
    public void newStackWithWithStack()
    {
        ArrayStack<Object> expected = ArrayStack.newStack();
        Assert.assertEquals(expected, MutableStack.ofAll(ArrayStack.newStack()));
        expected.push(1);
        Assert.assertEquals(ArrayStack.newStackWith(1), MutableStack.ofAll(expected));
        expected.push(2);
        Assert.assertEquals(ArrayStack.newStackWith(2, 1), MutableStack.ofAll(expected));
    }
}
