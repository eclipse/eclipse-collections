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

import org.eclipse.collections.impl.block.procedure.CountProcedure;

/**
 * Combines the results of a Collection of CountBlocks which each hold onto a filtered sum (count where) result.
 */
public final class CountCombiner<T>
        extends AbstractProcedureCombiner<CountProcedure<T>>
{
    private static final long serialVersionUID = 1L;
    private int count = 0;

    public CountCombiner()
    {
        super(true);
    }

    @Override
    public void combineOne(CountProcedure<T> procedure)
    {
        this.count += procedure.getCount();
    }

    public int getCount()
    {
        return this.count;
    }
}
