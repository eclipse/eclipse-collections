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

import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * CollectionAddProcedure adds elements to the specified collection when one of the block methods are called.
 */
public final class CollectionAddProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final Collection<T> collection;

    public CollectionAddProcedure(Collection<T> newCollection)
    {
        this.collection = newCollection;
    }

    public static <T> CollectionAddProcedure<T> on(Collection<T> newCollection)
    {
        return new CollectionAddProcedure<>(newCollection);
    }

    @Override
    public void value(T object)
    {
        this.collection.add(object);
    }

    public Collection<T> getResult()
    {
        return this.collection;
    }

    @Override
    public String toString()
    {
        return "Collection.add()";
    }
}
