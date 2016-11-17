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
import org.eclipse.collections.impl.list.mutable.FastList;

/**
 * Applies a predicate to an object to determine if it should be added to a target fastList.
 *
 * @since 1.0
 */
public final class FastListSelectProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final Predicate<? super T> predicate;
    private final FastList<T> fastList;

    public FastListSelectProcedure(Predicate<? super T> newPredicate, FastList<T> targetCollection)
    {
        this.predicate = newPredicate;
        this.fastList = targetCollection;
    }

    @Override
    public void value(T object)
    {
        if (this.predicate.accept(object))
        {
            this.fastList.add(object);
        }
    }

    public FastList<T> getFastList()
    {
        return this.fastList;
    }
}
