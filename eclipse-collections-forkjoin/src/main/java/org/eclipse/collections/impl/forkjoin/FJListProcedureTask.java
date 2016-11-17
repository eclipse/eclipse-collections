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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.impl.parallel.ProcedureFactory;
import org.eclipse.collections.impl.utility.ArrayListIterate;
import org.eclipse.collections.impl.utility.ListIterate;

public class FJListProcedureTask<T, PT extends Procedure<? super T>> extends ForkJoinTask<PT>
{
    private static final long serialVersionUID = 1L;

    private final ProcedureFactory<PT> procedureFactory;
    private PT procedure;
    private final List<T> list;
    private final int start;
    private final int end;
    private final FJListProcedureRunner<T, PT> taskRunner;

    /**
     * Creates an array of ProcedureFJTasks wrapping Procedures created by the specified ProcedureFactory.
     */
    public FJListProcedureTask(
            FJListProcedureRunner<T, PT> newFJTaskRunner, ProcedureFactory<PT> newProcedureFactory,
            List<T> list, int index, int sectionSize, boolean isLast)
    {
        this.taskRunner = newFJTaskRunner;
        this.procedureFactory = newProcedureFactory;
        this.list = list;
        this.start = index * sectionSize;
        this.end = isLast ? this.list.size() - 1 : this.start + sectionSize - 1;
    }

    @Override
    protected boolean exec()
    {
        try
        {
            this.procedure = this.procedureFactory.create();
            if (this.list instanceof ListIterable)
            {
                ((ListIterable<T>) this.list).forEach(this.start, this.end, this.procedure);
            }
            else if (this.list instanceof ArrayList)
            {
                ArrayListIterate.forEach((ArrayList<T>) this.list, this.start, this.end, this.procedure);
            }
            else
            {
                ListIterate.forEach(this.list, this.start, this.end, this.procedure);
            }
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
