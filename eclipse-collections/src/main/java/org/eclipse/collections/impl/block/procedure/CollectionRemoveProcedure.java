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
 * CollectionRemoveProcedure removes element from the specified collection when one of the procedure methods are called.
 */
public final class CollectionRemoveProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final Collection<T> collection;

    public CollectionRemoveProcedure(Collection<T> newCollection)
    {
        this.collection = newCollection;
    }

    public static <T> CollectionRemoveProcedure<T> on(Collection<T> newCollection)
    {
        return new CollectionRemoveProcedure<>(newCollection);
    }

    @Override
    public void value(T object)
    {
        this.collection.remove(object);
    }

    public Collection<T> getResult()
    {
        return this.collection;
    }

    @Override
    public String toString()
    {
        return "Collection.remove()";
    }
}
