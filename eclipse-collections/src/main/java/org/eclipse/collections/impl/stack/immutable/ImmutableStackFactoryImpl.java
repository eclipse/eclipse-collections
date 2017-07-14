/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.immutable;

import org.eclipse.collections.api.factory.stack.ImmutableStackFactory;
import org.eclipse.collections.api.stack.ImmutableStack;

public enum ImmutableStackFactoryImpl implements ImmutableStackFactory
{
    INSTANCE;

    @Override
    public <T> ImmutableStack<T> empty()
    {
        return ImmutableArrayStack.newStack();
    }

    @Override
    public <T> ImmutableStack<T> of()
    {
        return this.empty();
    }

    @Override
    public <T> ImmutableStack<T> with()
    {
        return this.empty();
    }

    @Override
    public <T> ImmutableStack<T> of(T element)
    {
        return this.with(element);
    }

    @Override
    public <T> ImmutableStack<T> with(T element)
    {
        return ImmutableArrayStack.newStackWith(element);
    }

    @Override
    public <T> ImmutableStack<T> of(T... elements)
    {
        return this.with(elements);
    }

    @Override
    public <T> ImmutableStack<T> with(T... elements)
    {
        return ImmutableArrayStack.newStackWith(elements);
    }

    @Override
    public <T> ImmutableStack<T> ofAll(Iterable<? extends T> items)
    {
        return this.withAll(items);
    }

    @Override
    public <T> ImmutableStack<T> withAll(Iterable<? extends T> items)
    {
        return ImmutableArrayStack.newStack(items);
    }

    @Override
    public <T> ImmutableStack<T> ofReversed(T... elements)
    {
        return this.withReversed(elements);
    }

    @Override
    public <T> ImmutableStack<T> withReversed(T... elements)
    {
        return ImmutableArrayStack.newStackFromTopToBottom(elements);
    }

    @Override
    public <T> ImmutableStack<T> ofAllReversed(Iterable<? extends T> items)
    {
        return this.withAllReversed(items);
    }

    @Override
    public <T> ImmutableStack<T> withAllReversed(Iterable<? extends T> items)
    {
        return ImmutableArrayStack.newStackFromTopToBottom(items);
    }
}
