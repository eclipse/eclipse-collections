/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.block.procedure.SelectProcedure;

public final class SelectProcedureFactory<T> implements ProcedureFactory<SelectProcedure<T>>
{
    private final Predicate<? super T> predicate;
    private final int collectionSize;

    public SelectProcedureFactory(Predicate<? super T> newPredicate, int newInitialCapacity)
    {
        this.predicate = newPredicate;
        this.collectionSize = newInitialCapacity;
    }

    @Override
    public SelectProcedure<T> create()
    {
        return new SelectProcedure<>(this.predicate, Lists.mutable.withInitialCapacity(this.collectionSize));
    }
}
