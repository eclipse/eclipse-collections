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

public final class ArrayProcedureFJTaskRunner<T, BT extends Procedure<? super T>>
{
    private final Function<ArrayProcedureFJTask<T, BT>, BT> procedureFunction = new ProcedureExtractor();
    private ArrayProcedureFJTask<T, BT>[] procedures;
    private Throwable error;
    private final CountDownLatch latch;
    private final Combiner<BT> combiner;
    private final BlockingQueue<BT> outputQueue;
    private final int taskCount;

    public ArrayProcedureFJTaskRunner(Combiner<BT> newCombiner, int newTaskCount)
    {
        this.combiner = newCombiner;
        this.taskCount = newTaskCount;
        if (this.combiner.useCombineOne())
        {
            this.outputQueue = new ArrayBlockingQueue<>(newTaskCount);
            this.latch = null;
        }
        else
        {
            this.latch = new CountDownLatch(newTaskCount);
            this.outputQueue = null;
        }
    }

    /**
     * Creates an array of ProcedureFJTasks wrapping Procedures created by the specified ProcedureFactory.
     */
    private void createAndExecuteTasks(Executor executor, ProcedureFactory<BT> procedureFactory, T[] array)
    {
        this.procedures = new ArrayProcedureFJTask[this.taskCount];
        int sectionSize = array.length / this.taskCount;
        int size = this.taskCount;
        for (int index = 0; index < size; index++)
        {
            ArrayProcedureFJTask<T, BT> procedureFJTask = new ArrayProcedureFJTask<>(this, procedureFactory, array, index,
                    sectionSize, index == this.taskCount - 1);
            this.procedures[index] = procedureFJTask;
            executor.execute(procedureFJTask);
        }
    }

    public void taskCompleted(ArrayProcedureFJTask<T, BT> task)
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

    private void join()
    {
        try
        {
            if (this.combiner.useCombineOne())
            {
                int remainingTaskCount = this.taskCount;
                while (remainingTaskCount > 0)
                {
                    this.combiner.combineOne(this.outputQueue.take());
                    remainingTaskCount--;
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

    public void executeAndCombine(Executor executor, ProcedureFactory<BT> procedureFactory, T[] array)
    {
        this.createAndExecuteTasks(executor, procedureFactory, array);
        this.join();
        if (this.error != null)
        {
            throw new RuntimeException("One or more parallel tasks failed", this.error);
        }
        //don't combine until the lock is notified
        this.combineTasks();
    }

    public void setFailed(Throwable newError)
    {
        this.error = newError;
    }

    private void combineTasks()
    {
        if (!this.combiner.useCombineOne())
        {
            this.combiner.combineAll(ArrayIterate.collect(this.procedures, this.procedureFunction));
        }
    }

    private final class ProcedureExtractor implements Function<ArrayProcedureFJTask<T, BT>, BT>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public BT valueOf(ArrayProcedureFJTask<T, BT> object)
        {
            return object.getProcedure();
        }
    }
}
