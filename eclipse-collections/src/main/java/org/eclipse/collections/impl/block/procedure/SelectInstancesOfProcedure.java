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
 * Calls {@link Class#isInstance(Object)} on an object to determine if it should be added to a target collection.
 *
 * @since 2.0
 */
public final class SelectInstancesOfProcedure<T> implements Procedure<Object>
{
    private static final long serialVersionUID = 1L;

    private final Class<T> clazz;
    private final Collection<T> collection;

    public SelectInstancesOfProcedure(Class<T> clazz, Collection<T> targetCollection)
    {
        this.clazz = clazz;
        this.collection = targetCollection;
    }

    @Override
    public void value(Object object)
    {
        if (this.clazz.isInstance(object))
        {
            this.collection.add((T) object);
        }
    }

    public Collection<T> getCollection()
    {
        return this.collection;
    }
}
