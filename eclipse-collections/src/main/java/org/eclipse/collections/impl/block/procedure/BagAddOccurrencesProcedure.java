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

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;

/**
 * @since 5.0.
 */
public final class BagAddOccurrencesProcedure<T> implements ObjectIntProcedure<T>
{
    private static final long serialVersionUID = 1L;

    private final MutableBag<T> mutableBag;

    public BagAddOccurrencesProcedure(MutableBag<T> mutableBag)
    {
        this.mutableBag = mutableBag;
    }

    public static <T> BagAddOccurrencesProcedure<T> on(MutableBag<T> mutableBag)
    {
        return new BagAddOccurrencesProcedure<>(mutableBag);
    }

    @Override
    public void value(T each, int occurrences)
    {
        this.mutableBag.addOccurrences(each, occurrences);
    }

    public MutableBag<T> getResult()
    {
        return this.mutableBag;
    }

    @Override
    public String toString()
    {
        return "MutableBag.addOccurrences()";
    }
}
