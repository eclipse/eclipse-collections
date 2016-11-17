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
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.Counter;

/**
 * A conditional ObjectIntProcedure that effectively filters which objects should be used
 */
public final class IfObjectIntProcedure<T>
        implements Procedure<T>
{
    private static final long serialVersionUID = 2L;

    private final Counter index = new Counter();
    private final ObjectIntProcedure<? super T> objectIntProcedure;
    private final Predicate<? super T> predicate;

    public IfObjectIntProcedure(Predicate<? super T> newPredicate, ObjectIntProcedure<? super T> objectIntProcedure)
    {
        this.predicate = newPredicate;
        this.objectIntProcedure = objectIntProcedure;
    }

    @Override
    public void value(T object)
    {
        if (this.predicate.accept(object))
        {
            this.objectIntProcedure.value(object, this.index.getCount());
            this.index.increment();
        }
    }
}
