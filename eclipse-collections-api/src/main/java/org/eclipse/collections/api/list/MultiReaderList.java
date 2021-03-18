/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.list;

import java.util.Comparator;
import java.util.Random;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.ByteFunction;
import org.eclipse.collections.api.block.function.primitive.CharFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * A MultiReaderList provides thread-safe iteration for a list through methods {@code withReadLockAndDelegate()} and {@code withWriteLockAndDelegate()}.
 *
 * @since 10.0.
 */
public interface MultiReaderList<T>
        extends MutableList<T>
{
    void withReadLockAndDelegate(Procedure<? super MutableList<T>> procedure);

    void withWriteLockAndDelegate(Procedure<? super MutableList<T>> procedure);

    @Override
    default MultiReaderList<T> sortThis(Comparator<? super T> comparator)
    {
        this.sort(comparator);
        return this;
    }

    @Override
    default MultiReaderList<T> sortThis()
    {
        return this.sortThis(null);
    }

    @Override
    <V extends Comparable<? super V>> MultiReaderList<T> sortThisBy(Function<? super T, ? extends V> function);

    @Override
    MultiReaderList<T> sortThisByInt(IntFunction<? super T> function);

    @Override
    MultiReaderList<T> sortThisByBoolean(BooleanFunction<? super T> function);

    @Override
    MultiReaderList<T> sortThisByChar(CharFunction<? super T> function);

    @Override
    MultiReaderList<T> sortThisByByte(ByteFunction<? super T> function);

    @Override
    MultiReaderList<T> sortThisByShort(ShortFunction<? super T> function);

    @Override
    MultiReaderList<T> sortThisByFloat(FloatFunction<? super T> function);

    @Override
    MultiReaderList<T> sortThisByLong(LongFunction<? super T> function);

    @Override
    MultiReaderList<T> sortThisByDouble(DoubleFunction<? super T> function);

    @Override
    MultiReaderList<T> reverseThis();

    @Override
    MultiReaderList<T> shuffleThis();

    @Override
    MultiReaderList<T> shuffleThis(Random random);
}
