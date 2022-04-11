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

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.block.procedure.CollectIfProcedure;

public final class CollectIfProcedureFactory<T, V> implements ProcedureFactory<CollectIfProcedure<T, V>>
{
    private final int collectionSize;
    private final Function<? super T, V> function;
    private final Predicate<? super T> predicate;

    public CollectIfProcedureFactory(
            Function<? super T, V> function,
            Predicate<? super T> predicate,
            int newTaskSize)
    {
        this.collectionSize = newTaskSize;
        this.function = function;
        this.predicate = predicate;
    }

    @Override
    public CollectIfProcedure<T, V> create()
    {
        return new CollectIfProcedure<>(Lists.mutable.withInitialCapacity(this.collectionSize), this.function, this.predicate);
    }
}
