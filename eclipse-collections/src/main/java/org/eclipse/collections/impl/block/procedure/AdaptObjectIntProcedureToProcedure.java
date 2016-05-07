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

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.Counter;

public final class AdaptObjectIntProcedureToProcedure<V> implements Procedure<V>
{
    private static final long serialVersionUID = 1L;

    private final Counter index;
    private final ObjectIntProcedure<? super V> objectIntProcedure;

    public AdaptObjectIntProcedureToProcedure(ObjectIntProcedure<? super V> objectIntProcedure)
    {
        this.objectIntProcedure = objectIntProcedure;
        this.index = new Counter();
    }

    @Override
    public void value(V each)
    {
        this.objectIntProcedure.value(each, this.index.getCount());
        this.index.increment();
    }
}
