/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.impl.lazy.parallel.list.ListBatch;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

public interface OrderedBatch<T> extends Batch<T>
{
    @Override
    OrderedBatch<T> select(Predicate<? super T> predicate);

    @Override
    <V> ListBatch<V> collect(Function<? super T, ? extends V> function);

    @Override
    <V> ListBatch<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    Batch<T> distinct(ConcurrentHashMap<T, Boolean> distinct);
}
