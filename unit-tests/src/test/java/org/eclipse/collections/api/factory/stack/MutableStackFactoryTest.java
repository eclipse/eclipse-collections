/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.stack;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Stacks;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MutableStackFactoryTest
{
    private final MutableStackFactory mutableStackFactory = Stacks.mutable;

    @Test
    public void with()
    {
        MutableStack<Object> stack = this.mutableStackFactory.with();
        assertNotNull(stack);
        Verify.assertEmpty(stack);
    }

    @Test
    public void of()
    {
        MutableStack<Integer> stack = this.mutableStackFactory.of();
        assertNotNull(stack);
        Verify.assertEmpty(stack);

        MutableStack<Integer> intStack = this.mutableStackFactory.of(1, 2, 3);
        Verify.assertSize(3, intStack);
        Verify.assertContainsAll(intStack, 1, 2, 3);
        assertEquals(3, (long) intStack.pop());
        assertEquals(2, (long) intStack.pop());
        assertEquals(1, (long) intStack.pop());
    }

    @Test
    public void ofAll()
    {
        MutableStack<Integer> intStack = this.mutableStackFactory.ofAll(Lists.mutable.of(4, 5));
        Verify.assertSize(2, intStack);
        assertEquals(5, (long) intStack.pop());
        assertEquals(4, (long) intStack.pop());
        Verify.assertEmpty(intStack);
    }

    @Test
    public void ofAllReversed()
    {
        MutableStack<Integer> intStack = this.mutableStackFactory.ofAllReversed(Lists.mutable.of(4, 5));
        Verify.assertSize(2, intStack);
        assertEquals(4, (long) intStack.pop());
        assertEquals(5, (long) intStack.pop());
        Verify.assertEmpty(intStack);
    }
}
