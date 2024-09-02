/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import java.util.concurrent.atomic.LongAdder;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * Implementation of {@link Procedure} that holds on to the summation of elements seen so far,
 * determined by the {@link Function}.
 */
public class SumOfIntProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 2L;

    private final IntFunction<? super T> function;
    private final LongAdder result; // change from int to longAdder its more thread safe

    public SumOfIntProcedure(IntFunction<? super T> function)
    {
        this.function = function;
        this.result = new LongAdder();  // change
    }

    public long getResult()
    {
        return this.result.sum();
    } // getting the sum from LongAdder

    @Override
    public void value(T each)
    {
        this.result.add(this.function.intValueOf(each));
    }
}
// When you use it in a parallel stream, it should be less prone to overflow errors due to concurrent updates.
//  we can also adjust the number of threads based on system's capabilities by creating a new variable.