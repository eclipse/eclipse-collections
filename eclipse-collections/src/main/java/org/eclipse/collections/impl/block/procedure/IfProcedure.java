/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * IfProcedure allows developers to evaluate the specified procedure only when either predicate returns true.
 * If the result of evaluating the predicate is false, and the developer has specified that there
 * is an elseProcedure, then the elseProcedure is evaluated.
 */
public final class IfProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final Predicate<? super T> predicate;
    private final Procedure<? super T> procedure;
    private final Procedure<? super T> elseProcedure;

    public IfProcedure(
            Predicate<? super T> predicate,
            Procedure<? super T> procedure,
            Procedure<? super T> elseProcedure)
    {
        this.predicate = predicate;
        this.procedure = procedure;
        this.elseProcedure = elseProcedure;
    }

    public IfProcedure(Predicate<? super T> predicate, Procedure<? super T> procedure)
    {
        this(predicate, procedure, null);
    }

    @Override
    public void value(T object)
    {
        if (this.predicate.accept(object))
        {
            this.procedure.value(object);
        }
        else
        {
            if (this.elseProcedure != null)
            {
                this.elseProcedure.value(object);
            }
        }
    }

    @Override
    public String toString()
    {
        return "new IfProcedure(" + this.predicate + ", " + this.procedure + ", " + this.elseProcedure + ')';
    }
}
