/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.bag;

import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * A MultiReaderBag provides thread-safe iteration for a bag through methods {@code withReadLockAndDelegate()} and {@code withWriteLockAndDelegate()}.
 *
 * @since 10.0.
 */
public interface MultiReaderBag<T>
        extends MutableBag<T>
{
    void withReadLockAndDelegate(Procedure<? super MutableBag<T>> procedure);

    void withWriteLockAndDelegate(Procedure<? super MutableBag<T>> procedure);

    @Override
    MultiReaderBag<T> newEmpty();

    @Override
    default MultiReaderBag<T> with(T element)
    {
        this.add(element);
        return this;
    }

    @Override
    default MultiReaderBag<T> without(T element)
    {
        this.remove(element);
        return this;
    }

    @Override
    default MultiReaderBag<T> withAll(Iterable<? extends T> elements)
    {
        this.addAllIterable(elements);
        return this;
    }

    @Override
    default MultiReaderBag<T> withoutAll(Iterable<? extends T> elements)
    {
        this.removeAllIterable(elements);
        return this;
    }

    @Override
    default MultiReaderBag<T> tap(Procedure<? super T> procedure)
    {
        this.forEach(procedure);
        return this;
    }
}
