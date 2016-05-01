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

import java.util.Comparator;

import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * Implementation of {@link Procedure} that holds on to the maximum element seen so far,
 * determined by the {@link Comparator}.
 */
public class MaxComparatorProcedure<T> extends ComparatorProcedure<T>
{
    private static final long serialVersionUID = 1L;

    public MaxComparatorProcedure(Comparator<? super T> comparator)
    {
        super(comparator);
    }

    @Override
    public void value(T each)
    {
        if (!this.visitedAtLeastOnce)
        {
            this.visitedAtLeastOnce = true;
            this.result = each;
        }
        else if (this.comparator.compare(each, this.result) > 0)
        {
            this.result = each;
        }
    }
}
