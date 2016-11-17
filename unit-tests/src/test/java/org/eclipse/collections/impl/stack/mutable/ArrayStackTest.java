/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.mutable;

import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.impl.factory.Stacks;

/**
 * JUnit test for {@link ArrayStack}.
 */
public class ArrayStackTest extends MutableStackTestCase
{
    @Override
    protected <T> MutableStack<T> newStackWith(T... elements)
    {
        return Stacks.mutable.of(elements);
    }

    @Override
    protected <T> MutableStack<T> newStackFromTopToBottom(T... elements)
    {
        return Stacks.mutable.ofReversed(elements);
    }

    @Override
    protected <T> MutableStack<T> newStackFromTopToBottom(Iterable<T> elements)
    {
        return Stacks.mutable.ofAllReversed(elements);
    }

    @Override
    protected <T> MutableStack<T> newStack(Iterable<T> elements)
    {
        return Stacks.mutable.ofAll(elements);
    }
}
