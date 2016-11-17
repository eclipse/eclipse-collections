/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.stack.mutable;

import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.test.UnmodifiableIterableTestCase;
import org.junit.Test;

public interface UnmodifiableMutableStackTestCase extends MutableStackTestCase, UnmodifiableIterableTestCase
{
    @Override
    @Test
    default void Iterable_remove()
    {
        UnmodifiableIterableTestCase.super.Iterable_remove();
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    default void MutableStack_pop()
    {
        MutableStack<Integer> mutableStack = this.newWith(5, 1, 4, 2, 3);
        mutableStack.pop();
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    default void MutableStack_pop_throws()
    {
        MutableStack<Integer> mutableStack = this.newWith();
        mutableStack.pop();
    }
}
