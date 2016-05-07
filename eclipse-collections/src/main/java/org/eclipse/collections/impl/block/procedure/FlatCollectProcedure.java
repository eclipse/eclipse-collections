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

import java.util.Collection;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * Applies a function to an object and adds the result to a target collection.
 */
public final class FlatCollectProcedure<T, V> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final Function<? super T, ? extends Iterable<V>> function;
    private final Collection<V> collection;

    public FlatCollectProcedure(
            Function<? super T, ? extends Iterable<V>> function,
            Collection<V> targetCollection)
    {
        this.function = function;
        this.collection = targetCollection;
    }

    @Override
    public void value(T object)
    {
        Iterate.addAllIterable(this.function.valueOf(object), this.collection);
    }

    public Collection<V> getCollection()
    {
        return this.collection;
    }
}
