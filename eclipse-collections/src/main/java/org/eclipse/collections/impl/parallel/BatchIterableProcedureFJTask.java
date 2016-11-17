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

import org.eclipse.collections.api.block.procedure.Procedure;

public final class BatchIterableProcedureFJTask<T, BT extends Procedure<? super T>> implements Runnable
{
    private final ProcedureFactory<BT> procedureFactory;
    private BT procedure;
    private final BatchIterable<T> iterable;
    private final int sectionIndex;
    private final int sectionCount;
    private final BatchIterableProcedureFJTaskRunner<T, BT> taskRunner;

    /**
     * Creates an array of ProcedureFJTasks wrapping Procedures created by the specified ProcedureFactory.
     */
    public BatchIterableProcedureFJTask(
            BatchIterableProcedureFJTaskRunner<T, BT> newFJTaskRunner,
            ProcedureFactory<BT> procedureFactory,
            BatchIterable<T> iterable,
            int index,
            int count)
    {
        this.taskRunner = newFJTaskRunner;
        this.procedureFactory = procedureFactory;
        this.iterable = iterable;
        this.sectionIndex = index;
        this.sectionCount = count;
    }

    @Override
    public void run()
    {
        try
        {
            this.procedure = this.procedureFactory.create();
            this.iterable.batchForEach(this.procedure, this.sectionIndex, this.sectionCount);
        }
        catch (Throwable newError)
        {
            this.taskRunner.setFailed(newError);
        }
        finally
        {
            this.taskRunner.taskCompleted(this);
        }
    }

    public BT getProcedure()
    {
        return this.procedure;
    }
}
