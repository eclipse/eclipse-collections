/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel;

import java.util.Comparator;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.block.procedure.CountProcedure;
import org.eclipse.collections.impl.block.procedure.DoubleSumResultHolder;
import org.eclipse.collections.impl.block.procedure.MaxByProcedure;
import org.eclipse.collections.impl.block.procedure.MaxComparatorProcedure;
import org.eclipse.collections.impl.block.procedure.MinByProcedure;
import org.eclipse.collections.impl.block.procedure.MinComparatorProcedure;
import org.eclipse.collections.impl.block.procedure.SumOfDoubleProcedure;
import org.eclipse.collections.impl.block.procedure.SumOfFloatProcedure;
import org.eclipse.collections.impl.block.procedure.SumOfIntProcedure;
import org.eclipse.collections.impl.block.procedure.SumOfLongProcedure;

public abstract class AbstractBatch<T> implements Batch<T>
{
    public int count(Predicate<? super T> predicate)
    {
        CountProcedure<T> procedure = new CountProcedure<T>(predicate);
        this.forEach(procedure);
        return procedure.getCount();
    }

    public String makeString(final String separator)
    {
        final StringBuilder stringBuilder = new StringBuilder();
        this.forEach(new Procedure<T>()
        {
            public void value(T each)
            {
                if (stringBuilder.length() != 0)
                {
                    stringBuilder.append(separator);
                }
                stringBuilder.append(each);
            }
        });
        return stringBuilder.toString();
    }

    public T min(Comparator<? super T> comparator)
    {
        MinComparatorProcedure<T> procedure = new MinComparatorProcedure<T>(comparator);
        this.forEach(procedure);
        return procedure.isVisitedAtLeastOnce() ? procedure.getResult() : null;
    }

    public T max(Comparator<? super T> comparator)
    {
        MaxComparatorProcedure<T> procedure = new MaxComparatorProcedure<T>(comparator);
        this.forEach(procedure);
        return procedure.isVisitedAtLeastOnce() ? procedure.getResult() : null;
    }

    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        MinByProcedure<T, V> procedure = new MinByProcedure<T, V>(function);
        this.forEach(procedure);
        return procedure.isVisitedAtLeastOnce() ? procedure.getResult() : null;
    }

    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        MaxByProcedure<T, V> procedure = new MaxByProcedure<T, V>(function);
        this.forEach(procedure);
        return procedure.isVisitedAtLeastOnce() ? procedure.getResult() : null;
    }

    public long sumOfInt(IntFunction<? super T> function)
    {
        SumOfIntProcedure<T> procedure = new SumOfIntProcedure<T>(function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public DoubleSumResultHolder sumOfFloat(FloatFunction<? super T> function)
    {
        SumOfFloatProcedure<T> procedure = new SumOfFloatProcedure<T>(function);
        this.forEach(procedure);
        return procedure;
    }

    public long sumOfLong(LongFunction<? super T> function)
    {
        SumOfLongProcedure<T> procedure = new SumOfLongProcedure<T>(function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public DoubleSumResultHolder sumOfDouble(DoubleFunction<? super T> function)
    {
        SumOfDoubleProcedure<T> procedure = new SumOfDoubleProcedure<T>(function);
        this.forEach(procedure);
        return procedure;
    }
}
