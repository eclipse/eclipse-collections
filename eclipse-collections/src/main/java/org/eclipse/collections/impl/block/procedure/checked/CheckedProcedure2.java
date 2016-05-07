/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.checked;

import org.eclipse.collections.api.block.procedure.Procedure2;

public abstract class CheckedProcedure2<T, P> implements Procedure2<T, P>, ThrowingProcedure2<T, P>
{
    private static final long serialVersionUID = 1L;

    @Override
    public void value(T item, P parameter)
    {
        try
        {
            this.safeValue(item, parameter);
        }
        catch (RuntimeException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Checked exception caught in Predicate", e);
        }
    }
}
