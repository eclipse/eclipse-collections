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
import org.eclipse.collections.api.block.procedure.Procedure2;

/**
 * A conditional parameterized two argument procedure that effectively filters which objects should be used
 */
public final class IfProcedureWith<T, P>
        implements Procedure2<T, P>
{
    private static final long serialVersionUID = 1L;
    private final Procedure2<? super T, ? super P> procedure;
    private final Predicate<? super T> predicate;

    public IfProcedureWith(Predicate<? super T> newPredicate, Procedure2<? super T, ? super P> procedure)
    {
        this.predicate = newPredicate;
        this.procedure = procedure;
    }

    @Override
    public void value(T each, P parm)
    {
        if (this.predicate.accept(each))
        {
            this.procedure.value(each, parm);
        }
    }
}
