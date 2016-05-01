/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.map.MutableMap;

/**
 * This procedure is used to apply an aggregate function like sum on a grouped set of data.  The values in the
 * grouping must be mutable using this procedure.  An example would be using an AtomicInteger which can be mutated
 * vs. an Integer which cannot.
 */
public final class MutatingAggregationProcedure<T, K, V> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;
    private final MutableMap<K, V> map;
    private final Function<? super T, ? extends K> groupBy;
    private final Function0<? extends V> zeroValueFactory;
    private final Procedure2<? super V, ? super T> mutatingAggregator;

    public MutatingAggregationProcedure(MutableMap<K, V> map, Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Procedure2<? super V, ? super T> mutatingAggregator)
    {
        this.map = map;
        this.groupBy = groupBy;
        this.zeroValueFactory = zeroValueFactory;
        this.mutatingAggregator = mutatingAggregator;
    }

    @Override
    public void value(T each)
    {
        K key = this.groupBy.valueOf(each);
        V value = this.map.getIfAbsentPut(key, this.zeroValueFactory);
        this.mutatingAggregator.value(value, each);
    }
}
