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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.utility.ArrayIterate;

public final class BatchIterableProcedureFJTaskRunner<T, BT extends Procedure<? super T>>
{
    private final Function<BatchIterableProcedureFJTask<T, BT>, BT> procedureFunction = new ProcedureExtractor();
    private BatchIterableProcedureFJTask<T, BT>[] procedures;
    private Throwable error;
    private final Combiner<BT> combiner;
    private final int taskCount;
    private final BlockingQueue<BT> outputQueue;
    private final CountDownLatch latch;

    public BatchIterableProcedureFJTaskRunner(Combiner<BT> newCombiner, int taskCount)
    {
        this.combiner = newCombiner;
        this.taskCount = taskCount;
        if (this.combiner.useCombineOne())
        {
            this.outputQueue = new ArrayBlockingQueue<>(taskCount);
            this.latch = null;
        }
        else
        {
            this.latch = new CountDownLatch(taskCount);
            this.outputQueue = null;
        }
    }

    private void createAndExecuteTasks(Executor executor, ProcedureFactory<BT> procedureFactory, BatchIterable<T> batchIterable)
    {
        this.procedures = new BatchIterableProcedureFJTask[this.taskCount];
        int size = this.taskCount;
        for (int batchNumber = 0; batchNumber < size; batchNumber++)
        {
            BatchIterableProcedureFJTask<T, BT> procedureFJTask =
                    new BatchIterableProcedureFJTask<>(this, procedureFactory, batchIterable, batchNumber, this.taskCount);
            this.procedures[batchNumber] = procedureFJTask;
            executor.execute(procedureFJTask);
        }
    }

    public void setFailed(Throwable newError)
    {
        this.error = newError;
    }

    public void taskCompleted(BatchIterableProcedureFJTask<T, BT> task)
    {
        if (this.combiner.useCombineOne())
        {
            this.outputQueue.add(task.getProcedure());
        }
        else
        {
            this.latch.countDown();
        }
    }

    public void executeAndCombine(Executor executor, ProcedureFactory<BT> procedureFactory, BatchIterable<T> set)
    {
        this.createAndExecuteTasks(executor, procedureFactory, set);
        this.join();
        if (this.error != null)
        {
            throw new RuntimeException("One or more parallel tasks failed", this.error);
        }
        //don't combine until the lock is notified
        this.combineTasks();
    }

    private void join()
    {
        try
        {
            if (this.combiner.useCombineOne())
            {
                int remaingTaskCount = this.taskCount;
                while (remaingTaskCount > 0)
                {
                    this.combiner.combineOne(this.outputQueue.take());
                    remaingTaskCount--;
                }
            }
            else
            {
                this.latch.await();
            }
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException("Combine failed", e);
        }
    }

    private void combineTasks()
    {
        if (!this.combiner.useCombineOne())
        {
            this.combiner.combineAll(ArrayIterate.collect(this.procedures, this.procedureFunction));
        }
    }

    private final class ProcedureExtractor implements Function<BatchIterableProcedureFJTask<T, BT>, BT>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public BT valueOf(BatchIterableProcedureFJTask<T, BT> object)
        {
            return object.getProcedure();
        }
    }
}
