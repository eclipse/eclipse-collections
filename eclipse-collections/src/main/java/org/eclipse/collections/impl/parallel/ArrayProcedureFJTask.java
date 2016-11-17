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
import org.eclipse.collections.impl.utility.ArrayIterate;

public final class ArrayProcedureFJTask<T, BT extends Procedure<? super T>> implements Runnable
{
    private final ProcedureFactory<BT> procedureFactory;
    private BT procedure;
    private final T[] array;
    private final int start;
    private final int end;
    private final ArrayProcedureFJTaskRunner<T, BT> taskRunner;

    public ArrayProcedureFJTask(
            ArrayProcedureFJTaskRunner<T, BT> newFJTaskRunner,
            ProcedureFactory<BT> procedureFactory,
            T[] newArray,
            int newIndex,
            int newSectionSize,
            boolean isLast)
    {
        this.taskRunner = newFJTaskRunner;
        this.procedureFactory = procedureFactory;
        this.array = newArray;
        this.start = newIndex * newSectionSize;
        this.end = isLast ? this.array.length : this.start + newSectionSize;
    }

    @Override
    public void run()
    {
        try
        {
            this.procedure = this.procedureFactory.create();
            ArrayIterate.forEach(this.array, this.start, this.end - 1, this.procedure);
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
