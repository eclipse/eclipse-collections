/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import java.util.Map;
import java.util.concurrent.Executor;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.utility.MapIterate;

/**
 * The ParallelMapIterate class contains parallel algorithms that work with Maps.
 * <p>
 * The forEachEntry algorithm employs a batching fork and join approach approach which does
 * not yet allow for specification of a Factory for the blocks or a Combiner for the results.
 * This means that forEachKeyValue can only support pure forking or forking with a shared
 * thread-safe data structure collecting results.
 */
public final class ParallelMapIterate
{
    private ParallelMapIterate()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    /**
     * A parallel form of forEachKeyValue.
     *
     * @see MapIterate#forEachKeyValue(Map, Procedure2)
     * @see ParallelIterate
     */
    public static <K, V> void forEachKeyValue(Map<K, V> map, Procedure2<? super K, ? super V> procedure2)
    {
        ParallelMapIterate.forEachKeyValue(map, procedure2, 2, map.size());
    }

    /**
     * A parallel form of forEachKeyValue.
     *
     * @see MapIterate#forEachKeyValue(Map, Procedure2)
     * @see ParallelIterate
     */
    public static <K, V> void forEachKeyValue(
            Map<K, V> map,
            Procedure2<? super K, ? super V> procedure,
            Executor executor)
    {
        ParallelMapIterate.forEachKeyValue(map, procedure, 2, map.size(), executor);
    }

    /**
     * A parallel form of forEachKeyValue.
     *
     * @see MapIterate#forEachKeyValue(Map, Procedure2)
     * @see ParallelIterate
     */
    public static <K, V> void forEachKeyValue(
            Map<K, V> map,
            Procedure2<? super K, ? super V> procedure,
            int minForkSize,
            int taskCount)
    {
        if (map.size() > minForkSize)
        {
            Procedure<Pair<K, V>> pairProcedure = new PairProcedure<>(procedure);
            ParallelIterate.forEach(MapIterate.toListOfPairs(map), new PassThruProcedureFactory<>(pairProcedure), new PassThruCombiner<>(), minForkSize, taskCount);
        }
        else
        {
            MapIterate.forEachKeyValue(map, procedure);
        }
    }

    /**
     * A parallel form of forEachKeyValue.
     *
     * @see MapIterate#forEachKeyValue(Map, Procedure2)
     * @see ParallelIterate
     */
    public static <K, V> void forEachKeyValue(
            Map<K, V> map,
            Procedure2<? super K, ? super V> procedure,
            int minForkSize,
            int taskCount,
            Executor executor)
    {
        if (map.size() > minForkSize)
        {
            Procedure<Pair<K, V>> pairProcedure = new PairProcedure<>(procedure);
            ParallelIterate.forEachInListOnExecutor(
                    MapIterate.toListOfPairs(map),
                    new PassThruProcedureFactory<>(pairProcedure),
                    new PassThruCombiner<>(),
                    minForkSize,
                    taskCount,
                    executor);
        }
        else
        {
            MapIterate.forEachKeyValue(map, procedure);
        }
    }

    private static final class PairProcedure<T1, T2> implements Procedure<Pair<T1, T2>>
    {
        private static final long serialVersionUID = 1L;

        private final Procedure2<? super T1, ? super T2> procedure;

        private PairProcedure(Procedure2<? super T1, ? super T2> procedure)
        {
            this.procedure = procedure;
        }

        @Override
        public void value(Pair<T1, T2> pair)
        {
            this.procedure.value(pair.getOne(), pair.getTwo());
        }
    }
}
