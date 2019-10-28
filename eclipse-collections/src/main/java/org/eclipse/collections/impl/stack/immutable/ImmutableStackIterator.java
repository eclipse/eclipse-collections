/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.immutable;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.stack.ImmutableStack;

class ImmutableStackIterator<T> implements Iterator<T>
{
    private ImmutableStack<T> immutableStack;

    ImmutableStackIterator(ImmutableStack<T> immutableStack)
    {
        this.immutableStack = immutableStack;
    }

    @Override
    public boolean hasNext()
    {
        return this.immutableStack.notEmpty();
    }

    @Override
    public T next()
    {
        if (!this.hasNext())
        {
            throw new NoSuchElementException();
        }

        T result = this.immutableStack.peek();
        this.immutableStack = this.immutableStack.pop();
        return result;
    }
}
