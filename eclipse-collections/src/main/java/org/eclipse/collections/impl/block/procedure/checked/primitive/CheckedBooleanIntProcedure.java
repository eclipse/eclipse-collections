/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.checked.primitive;

import org.eclipse.collections.api.block.procedure.primitive.BooleanIntProcedure;

/**
 * @since 4.0.
 */
public abstract class CheckedBooleanIntProcedure implements BooleanIntProcedure
{
    private static final long serialVersionUID = 1L;

    @Override
    public final void value(boolean item1, int item2)
    {
        try
        {
            this.safeValue(item1, item2);
        }
        catch (RuntimeException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Checked exception caught in BooleanIntProcedure", e);
        }
    }

    @SuppressWarnings("ProhibitedExceptionDeclared")
    public abstract void safeValue(boolean item1, int item2) throws Exception;
}
