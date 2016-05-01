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

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.impl.block.procedure.CountProcedure;

public final class CountProcedureFactory<T> implements ProcedureFactory<CountProcedure<T>>
{
    private final Predicate<? super T> predicate;

    public CountProcedureFactory(Predicate<? super T> predicate)
    {
        this.predicate = predicate;
    }

    @Override
    public CountProcedure<T> create()
    {
        return new CountProcedure<>(this.predicate);
    }
}
