/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import org.eclipse.collections.api.factory.stack.ImmutableStackFactory;
import org.eclipse.collections.api.stack.ImmutableStack;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class StacksTest
{
    @Test
    public void immutables()
    {
        ImmutableStackFactory stackFactory = Stacks.immutable;
        Assert.assertEquals(ArrayStack.newStack(), stackFactory.of());
        Verify.assertInstanceOf(ImmutableStack.class, stackFactory.of());
        Assert.assertEquals(ArrayStack.newStackWith(1), stackFactory.of(1));
        Verify.assertInstanceOf(ImmutableStack.class, stackFactory.of(1));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2), stackFactory.of(1, 2));
        Verify.assertInstanceOf(ImmutableStack.class, stackFactory.of(1, 2));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3), stackFactory.of(1, 2, 3));
        Verify.assertInstanceOf(ImmutableStack.class, stackFactory.of(1, 2, 3));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4), stackFactory.of(1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableStack.class, stackFactory.of(1, 2, 3, 4));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4, 5), stackFactory.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(ImmutableStack.class, stackFactory.of(1, 2, 3, 4, 5));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4, 5, 6), stackFactory.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableStack.class, stackFactory.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4, 5, 6, 7), stackFactory.of(1, 2, 3, 4, 5, 6, 7));
        Verify.assertInstanceOf(ImmutableStack.class, stackFactory.of(1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4, 5, 6, 7, 8), stackFactory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableStack.class, stackFactory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4, 5, 6, 7, 8, 9), stackFactory.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Verify.assertInstanceOf(ImmutableStack.class, stackFactory.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Assert.assertEquals(ArrayStack.newStackWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), stackFactory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Verify.assertInstanceOf(ImmutableStack.class, stackFactory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Assert.assertEquals(ArrayStack.newStackWith(3, 2, 1), stackFactory.ofAll(ArrayStack.newStackWith(1, 2, 3)));
        Verify.assertInstanceOf(ImmutableStack.class, stackFactory.ofAll(ArrayStack.newStackWith(1, 2, 3)));
    }

    @Test
    public void emptyStack()
    {
        Assert.assertTrue(Stacks.immutable.of().isEmpty());
    }

    @Test
    public void newStackWith()
    {
        ImmutableStack<String> stack = Stacks.immutable.of();
        Assert.assertEquals(stack, Stacks.immutable.of(stack.toArray()));
        Assert.assertEquals(stack = stack.push("1"), Stacks.immutable.of("1"));
        Assert.assertEquals(stack = stack.push("2"), Stacks.immutable.of("1", "2"));
        Assert.assertEquals(stack = stack.push("3"), Stacks.immutable.of("1", "2", "3"));
        Assert.assertEquals(stack = stack.push("4"), Stacks.immutable.of("1", "2", "3", "4"));
        Assert.assertEquals(stack = stack.push("5"), Stacks.immutable.of("1", "2", "3", "4", "5"));
        Assert.assertEquals(stack = stack.push("6"), Stacks.immutable.of("1", "2", "3", "4", "5", "6"));
        Assert.assertEquals(stack = stack.push("7"), Stacks.immutable.of("1", "2", "3", "4", "5", "6", "7"));
        Assert.assertEquals(stack = stack.push("8"), Stacks.immutable.of("1", "2", "3", "4", "5", "6", "7", "8"));
        Assert.assertEquals(stack = stack.push("9"), Stacks.immutable.of("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Assert.assertEquals(stack = stack.push("10"), Stacks.immutable.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        Assert.assertEquals(stack = stack.push("11"), Stacks.immutable.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
        Assert.assertEquals(stack = stack.push("12"), Stacks.immutable.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
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
    public void newStackWithStack()
    {
        ImmutableStack<String> stack = Stacks.immutable.of();
        ArrayStack<String> arrayStack = ArrayStack.newStackWith("1");
        Assert.assertEquals(stack = stack.push("1"), arrayStack.toImmutable());
        arrayStack.push("2");
        Assert.assertEquals(stack = stack.push("2"), arrayStack.toImmutable());
        arrayStack.push("3");
        Assert.assertEquals(stack = stack.push("3"), arrayStack.toImmutable());
        arrayStack.push("4");
        Assert.assertEquals(stack = stack.push("4"), arrayStack.toImmutable());
        arrayStack.push("5");
        Assert.assertEquals(stack = stack.push("5"), arrayStack.toImmutable());
        arrayStack.push("6");
        Assert.assertEquals(stack = stack.push("6"), arrayStack.toImmutable());
        arrayStack.push("7");
        Assert.assertEquals(stack = stack.push("7"), arrayStack.toImmutable());
        arrayStack.push("8");
        Assert.assertEquals(stack = stack.push("8"), arrayStack.toImmutable());
        arrayStack.push("9");
        Assert.assertEquals(stack = stack.push("9"), arrayStack.toImmutable());
        arrayStack.push("10");
        Assert.assertEquals(stack = stack.push("10"), arrayStack.toImmutable());
        arrayStack.push("11");
        Assert.assertEquals(stack = stack.push("11"), arrayStack.toImmutable());
    }

    @Test
    public void newStackWithWithStack()
    {
        ArrayStack<Object> expected = ArrayStack.newStack();
        Assert.assertEquals(expected, Stacks.mutable.ofAll(ArrayStack.newStack()));
        expected.push(1);
        Assert.assertEquals(ArrayStack.newStackWith(1), Stacks.mutable.ofAll(expected));
        expected.push(2);
        Assert.assertEquals(ArrayStack.newStackWith(2, 1), Stacks.mutable.ofAll(expected));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(Stacks.class);
    }
}
