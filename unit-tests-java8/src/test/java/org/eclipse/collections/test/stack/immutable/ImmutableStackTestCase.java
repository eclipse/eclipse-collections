/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.stack.immutable;

import java.util.EmptyStackException;
import java.util.Iterator;

import org.eclipse.collections.api.stack.ImmutableStack;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.test.stack.StackIterableTestCase;
import org.junit.Test;

import static org.eclipse.collections.impl.test.Verify.assertThrows;
import static org.eclipse.collections.test.IterableTestCase.assertEquals;

public interface ImmutableStackTestCase extends StackIterableTestCase
{
    @Override
    <T> ImmutableStack<T> newWith(T... elements);

    @Override
    @Test
    default void Iterable_remove()
    {
        ImmutableStack<Integer> stack = this.newWith(3, 3, 3, 2, 2, 1);
        Iterator<Integer> iterator = stack.iterator();
        iterator.next();
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

    @Test
    default void MutableStack_pop()
    {
        ImmutableStack<Integer> immutableStack = this.newWith(5, 1, 4, 2, 3);
        ImmutableStack<Integer> poppedStack = immutableStack.pop();
        assertEquals(Stacks.immutable.withReversed(1, 4, 2, 3), poppedStack);
        assertEquals(Stacks.immutable.withReversed(5, 1, 4, 2, 3), immutableStack);
    }

    @Test
    default void ImmutableStack_pop_throws()
    {
        ImmutableStack<Integer> immutableStack = this.newWith(5, 1, 4, 2, 3);
        ImmutableStack<Integer> emptyStack = immutableStack.pop().pop().pop().pop().pop();
        assertEquals(Stacks.immutable.with(), emptyStack);
        assertThrows(EmptyStackException.class, (Runnable) emptyStack::pop);
    }
}
