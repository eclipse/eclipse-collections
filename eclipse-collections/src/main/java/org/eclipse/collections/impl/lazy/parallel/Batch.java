/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel;

import java.util.Comparator;

import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.block.procedure.DoubleSumResultHolder;

@Beta
public interface Batch<T>
{
    void forEach(Procedure<? super T> procedure);

    Batch<T> select(Predicate<? super T> predicate);

    <V> Batch<V> collect(Function<? super T, ? extends V> function);

    <V> Batch<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    int count(Predicate<? super T> predicate);

    String makeString(String separator);

    T min(Comparator<? super T> comparator);

    T max(Comparator<? super T> comparator);

    <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function);

    <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function);

    long sumOfInt(IntFunction<? super T> function);

    long sumOfLong(LongFunction<? super T> function);

    DoubleSumResultHolder sumOfFloat(FloatFunction<? super T> function);

    DoubleSumResultHolder sumOfDouble(DoubleFunction<? super T> function);
}

