/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.forkjoin;

import java.util.concurrent.ForkJoinTask;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.parallel.BatchIterable;
import org.eclipse.collections.impl.parallel.ProcedureFactory;

public class FJBatchIterableProcedureTask<T, PT extends Procedure<? super T>> extends ForkJoinTask<PT>
{
    private static final long serialVersionUID = 1L;

    private final ProcedureFactory<PT> procedureFactory;
    private PT procedure;
    private final BatchIterable<T> iterable;
    private final int sectionIndex;
    private final int sectionCount;
    private final FJBatchIterableProcedureRunner<T, PT> taskRunner;

    /**
     * Creates an array of ProcedureFJTasks wrapping Procedures created by the specified ProcedureFactory.
     */
    public FJBatchIterableProcedureTask(
            FJBatchIterableProcedureRunner<T, PT> newFJTaskRunner,
            ProcedureFactory<PT> newProcedureFactory, BatchIterable<T> iterable, int index, int count)
    {
        this.taskRunner = newFJTaskRunner;
        this.procedureFactory = newProcedureFactory;
        this.iterable = iterable;
        this.sectionIndex = index;
        this.sectionCount = count;
    }

    @Override
    protected boolean exec()
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
        return true;
    }

    @Override
    public PT getRawResult()
    {
        return this.procedure;
    }

    @Override
    protected void setRawResult(PT value)
    {
        throw new UnsupportedOperationException();
    }
}
