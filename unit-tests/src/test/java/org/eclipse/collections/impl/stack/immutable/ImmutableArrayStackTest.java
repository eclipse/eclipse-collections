/*
 * Copyright (c) 2022 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.immutable;

import java.util.EmptyStackException;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.stack.ImmutableStack;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableArrayStackTest extends ImmutableStackTestCase
{
    @Override
    protected <T> ImmutableStack<T> newStackWith(T... elements)
    {
        return Stacks.immutable.of(elements);
    }

    @Override
    protected <T> ImmutableStack<T> newStackFromTopToBottom(T... elements)
    {
        return Stacks.immutable.ofReversed(elements);
    }

    @Override
    protected <T> ImmutableStack<T> newStackFromTopToBottom(Iterable<T> elements)
    {
        return Stacks.immutable.ofAllReversed(elements);
    }

    @Override
    protected <T> ImmutableStack<T> newStack(Iterable<T> elements)
    {
        return Stacks.immutable.ofAll(elements);
    }

    @Override
    @Test
    public void testEquals()
    {
        super.testEquals();
        assertEquals(ImmutableArrayStack.newStack(), ArrayStack.newStackWith());
        assertNotEquals(this.newStackWith(4, 5, 6), ArrayStack.newStackWith(1, 2, 3));
    }

    @Test
    public void push()
    {
        ImmutableStack<Integer> stack = this.newStackWith(1, 2, 3);
        ImmutableStack<Integer> modifiedStack = stack.push(4);
        assertEquals(this.newStackWith(1, 2, 3, 4), modifiedStack);
        assertNotSame(modifiedStack, stack);
        assertEquals(this.newStackWith(1, 2, 3), stack);
        modifiedStack.push(5);
        assertEquals(this.newStackWith(1, 2, 3), stack);

        ImmutableStack<Integer> stack1 = this.newStackWith();
        ImmutableStack<Integer> modifiedStack1 = stack1.push(1);
        assertEquals(this.newStackWith(1), modifiedStack1);
        assertNotSame(modifiedStack1, stack1);
        assertEquals(this.newStackWith(), stack1);
        modifiedStack1.push(5);
        assertEquals(this.newStackWith(), stack1);
    }

    @Test
    public void pop()
    {
        assertThrows(EmptyStackException.class, () -> this.newStackWith().pop());

        ImmutableStack<Integer> stack = this.newStackWith(1, 2, 3);
        ImmutableStack<Integer> modifiedStack = stack.pop();
        assertEquals(this.newStackWith(1, 2), modifiedStack);
        assertNotSame(modifiedStack, stack);
        assertEquals(this.newStackWith(1, 2, 3), stack);

        ImmutableStack<Integer> stack1 = this.newStackWith(1);
        ImmutableStack<Integer> modifiedStack1 = stack1.pop();
        assertEquals(this.newStackWith(), modifiedStack1);
        assertNotSame(modifiedStack1, stack1);
        assertEquals(this.newStackWith(1), stack1);
    }

    @Test
    public void popCount()
    {
        assertThrows(EmptyStackException.class, () -> this.newStackWith().pop(1));

        assertEquals(this.newStackWith(), this.newStackWith().pop(0));

        ImmutableStack<Integer> stack = this.newStackWith(1, 2, 3);
        ImmutableStack<Integer> modifiedStack = stack.pop(1);
        assertEquals(this.newStackWith(1, 2), modifiedStack);
        assertNotSame(modifiedStack, stack);
        assertNotSame(this.newStackWith(1, 2, 3), stack);

        ImmutableStack<Integer> stack1 = this.newStackWith(1);
        assertThrows(IllegalArgumentException.class, () -> stack1.pop(2));
        ImmutableStack<Integer> modifiedStack1 = stack1.pop(1);
        assertEquals(this.newStackWith(), modifiedStack1);
        assertNotSame(modifiedStack1, stack1);
        assertEquals(this.newStackWith(1), stack1);
    }

    @Test
    public void peekAndPop()
    {
        assertThrows(EmptyStackException.class, () -> this.newStackWith().pop());

        ImmutableStack<Integer> stack = this.newStackWith(1, 2, 3);
        Pair<Integer, ImmutableStack<Integer>> elementAndStack = stack.peekAndPop();
        Integer poppedElement = elementAndStack.getOne();
        ImmutableStack<Integer> modifiedStack = elementAndStack.getTwo();
        assertEquals(3, poppedElement.intValue());
        assertEquals(this.newStackWith(1, 2), modifiedStack);
        assertNotSame(modifiedStack, stack);
        assertEquals(this.newStackWith(1, 2, 3), stack);

        ImmutableStack<Integer> stack1 = this.newStackWith(1);
        Pair<Integer, ImmutableStack<Integer>> elementAndStack1 = stack1.peekAndPop();
        Integer poppedElement1 = elementAndStack1.getOne();
        ImmutableStack<Integer> modifiedStack1 = elementAndStack1.getTwo();
        assertEquals(1, poppedElement1.intValue());
        assertEquals(this.newStackWith(), modifiedStack1);
        assertNotSame(modifiedStack1, stack1);
        assertEquals(this.newStackWith(1), stack1);
    }

    @Test
    public void peekAndPopCount()
    {
        assertThrows(EmptyStackException.class, () -> this.newStackWith().peekAndPop(1));

        assertEquals(this.newStackWith(), this.newStackWith().peekAndPop(0).getTwo());

        ImmutableStack<Integer> stack = this.newStackWith(1, 2, 3);
        Pair<ListIterable<Integer>, ImmutableStack<Integer>> elementsAndStack = stack.peekAndPop(1);
        ListIterable<Integer> poppedElements = elementsAndStack.getOne();
        ImmutableStack<Integer> modifiedStack = elementsAndStack.getTwo();
        assertEquals(Lists.fixedSize.of(3), poppedElements);
        assertEquals(this.newStackWith(1, 2), modifiedStack);
        assertNotSame(modifiedStack, stack);
        assertNotSame(this.newStackWith(1, 2, 3), stack);

        ImmutableStack<Integer> stack1 = this.newStackWith(1);
        assertThrows(IllegalArgumentException.class, () -> stack1.pop(2));
        Pair<ListIterable<Integer>, ImmutableStack<Integer>> elementsAndStack1 = stack1.peekAndPop(1);
        ListIterable<Integer> poppedElements1 = elementsAndStack1.getOne();
        ImmutableStack<Integer> modifiedStack1 = elementsAndStack1.getTwo();
        assertEquals(Lists.fixedSize.of(1), poppedElements1);
        assertEquals(this.newStackWith(), modifiedStack1);
        assertNotSame(modifiedStack1, stack1);
        assertEquals(this.newStackWith(1), stack1);
    }
}
