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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.impl.utility.ArrayListIterate;
import org.eclipse.collections.impl.utility.ListIterate;

public final class ProcedureFJTask<T, BT extends Procedure<? super T>> implements Runnable
{
    private final ProcedureFactory<BT> procedureFactory;
    private BT procedure;
    private final List<T> list;
    private final int start;
    private final int end;
    private final ProcedureFJTaskRunner<T, BT> taskRunner;

    /**
     * Creates an array of ProcedureFJTasks wrapping Procedures created by the specified ProcedureFactory.
     */
    public ProcedureFJTask(
            ProcedureFJTaskRunner<T, BT> newFJTaskRunner,
            ProcedureFactory<BT> newProcedureFactory,
            List<T> list,
            int index,
            int sectionSize,
            boolean isLast)
    {
        this.taskRunner = newFJTaskRunner;
        this.procedureFactory = newProcedureFactory;
        this.list = list;
        this.start = index * sectionSize;
        this.end = isLast ? this.list.size() : this.start + sectionSize;
    }

    @Override
    public void run()
    {
        try
        {
            this.procedure = this.procedureFactory.create();
            int stop = this.end - 1;
            if (this.list instanceof ListIterable)
            {
                ((ListIterable<T>) this.list).forEach(this.start, stop, this.procedure);
            }
            else if (this.list instanceof ArrayList)
            {
                ArrayListIterate.forEach((ArrayList<T>) this.list, this.start, stop, this.procedure);
            }
            else
            {
                ListIterate.forEach(this.list, this.start, stop, this.procedure);
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
    }

    public BT getProcedure()
    {
        return this.procedure;
    }
}
