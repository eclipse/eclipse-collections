/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.set;

import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * A MultiReaderSet provides thread-safe iteration for a set through methods {@code withReadLockAndDelegate()} and {@code withWriteLockAndDelegate()}.
 *
 * @since 10.0.
 */
public interface MultiReaderSet<T>
        extends MutableSet<T>
{
    void withReadLockAndDelegate(Procedure<? super MutableSet<T>> procedure);

    void withWriteLockAndDelegate(Procedure<? super MutableSet<T>> procedure);

    @Override
    MultiReaderSet<T> newEmpty();

    @Override
    MultiReaderSet<T> clone();

    @Override
    default MultiReaderSet<T> with(T element)
    {
        this.add(element);
        return this;
    }

    @Override
    default MultiReaderSet<T> without(T element)
    {
        this.remove(element);
        return this;
    }

    @Override
    default MultiReaderSet<T> withAll(Iterable<? extends T> elements)
    {
        this.addAllIterable(elements);
        return this;
    }

    @Override
    default MultiReaderSet<T> withoutAll(Iterable<? extends T> elements)
    {
        this.removeAllIterable(elements);
        return this;
    }

    @Override
    default MultiReaderSet<T> tap(Procedure<? super T> procedure)
    {
        this.forEach(procedure);
        return this;
    }
}
