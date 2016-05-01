/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;

/**
 * Contains factory methods for creating {@link ObjectIntProcedure} instances.
 */
public final class ObjectIntProcedures
{
    private ObjectIntProcedures()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static <T> ObjectIntProcedure<T> fromProcedure(Procedure<? super T> procedure)
    {
        return new ProcedureAdapter<>(procedure);
    }

    private static final class ProcedureAdapter<T> implements ObjectIntProcedure<T>
    {
        private static final long serialVersionUID = 1L;
        private final Procedure<? super T> procedure;

        private ProcedureAdapter(Procedure<? super T> procedure)
        {
            this.procedure = procedure;
        }

        @Override
        public void value(T each, int count)
        {
            this.procedure.value(each);
        }
    }
}
