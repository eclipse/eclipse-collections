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

import java.util.concurrent.Executor;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.utility.ArrayIterate;

import static org.eclipse.collections.impl.factory.Iterables.iList;

/**
 * The ParallelArrayIterate class contains a parallel forEach algorithm that work with Java arrays.  The forEach
 * algorithm employs a batching fork and join approach approach.  All Collections that are not array based use
 * ParallelArrayIterate to parallelize, by converting themselves to an array using toArray().
 */
public final class ParallelArrayIterate
{
    private ParallelArrayIterate()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static <T, BT extends Procedure<? super T>> void forEach(
            T[] array,
            ProcedureFactory<BT> procedureFactory,
            Combiner<BT> combiner)
    {
        int taskCount = Math.max(ParallelIterate.DEFAULT_PARALLEL_TASK_COUNT, array.length / ParallelIterate.DEFAULT_MIN_FORK_SIZE);
        ParallelArrayIterate.forEach(array, procedureFactory, combiner, ParallelIterate.DEFAULT_MIN_FORK_SIZE, taskCount);
    }

    public static <T, BT extends Procedure<? super T>> void forEach(
            T[] array,
            ProcedureFactory<BT> procedureFactory,
            Combiner<BT> combiner,
            int minForkSize,
            int taskCount)
    {
        ParallelArrayIterate.forEachOn(array, procedureFactory, combiner, minForkSize, taskCount, ParallelIterate.EXECUTOR_SERVICE);
    }

    public static <T, BT extends Procedure<? super T>> void forEachOn(
            T[] array,
            ProcedureFactory<BT> procedureFactory,
            Combiner<BT> combiner,
            int minForkSize,
            int taskCount,
            Executor executor)
    {
        if (ArrayIterate.notEmpty(array))
        {
            int size = array.length;
            if (size < minForkSize)
            {
                BT procedure = procedureFactory.create();
                ArrayIterate.forEach(array, procedure);
                ParallelArrayIterate.combineSingleProcedure(combiner, procedure);
            }
            else
            {
                int threadCount = Math.min(size, taskCount);
                new ArrayProcedureFJTaskRunner<>(combiner, threadCount).executeAndCombine(executor, procedureFactory, array);
            }
        }
    }

    private static <T, BT extends Procedure<? super T>> void combineSingleProcedure(Combiner<BT> combiner, BT procedure)
    {
        if (combiner.useCombineOne())
        {
            combiner.combineOne(procedure);
        }
        else
        {
            combiner.combineAll(iList(procedure));
        }
    }
}
