/*
 * Copyright (c) 2016 Goldman Sachs.
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
    @Override
    public int count(Predicate<? super T> predicate)
    {
        CountProcedure<T> procedure = new CountProcedure<>(predicate);
        this.forEach(procedure);
        return procedure.getCount();
    }

    @Override
    public String makeString(String separator)
    {
        StringBuilder stringBuilder = new StringBuilder();
        this.forEach(each -> {
            if (stringBuilder.length() != 0)
            {
                stringBuilder.append(separator);
            }
            stringBuilder.append(each);
        });
        return stringBuilder.toString();
    }

    @Override
    public T min(Comparator<? super T> comparator)
    {
        MinComparatorProcedure<T> procedure = new MinComparatorProcedure<>(comparator);
        this.forEach(procedure);
        return procedure.isVisitedAtLeastOnce() ? procedure.getResult() : null;
    }

    @Override
    public T max(Comparator<? super T> comparator)
    {
        MaxComparatorProcedure<T> procedure = new MaxComparatorProcedure<>(comparator);
        this.forEach(procedure);
        return procedure.isVisitedAtLeastOnce() ? procedure.getResult() : null;
    }

    @Override
    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        MinByProcedure<T, V> procedure = new MinByProcedure<>(function);
        this.forEach(procedure);
        return procedure.isVisitedAtLeastOnce() ? procedure.getResult() : null;
    }

    @Override
    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        MaxByProcedure<T, V> procedure = new MaxByProcedure<>(function);
        this.forEach(procedure);
        return procedure.isVisitedAtLeastOnce() ? procedure.getResult() : null;
    }

    @Override
    public long sumOfInt(IntFunction<? super T> function)
    {
        SumOfIntProcedure<T> procedure = new SumOfIntProcedure<>(function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    @Override
    public DoubleSumResultHolder sumOfFloat(FloatFunction<? super T> function)
    {
        SumOfFloatProcedure<T> procedure = new SumOfFloatProcedure<>(function);
        this.forEach(procedure);
        return procedure;
    }

    @Override
    public long sumOfLong(LongFunction<? super T> function)
    {
        SumOfLongProcedure<T> procedure = new SumOfLongProcedure<>(function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    @Override
    public DoubleSumResultHolder sumOfDouble(DoubleFunction<? super T> function)
    {
        SumOfDoubleProcedure<T> procedure = new SumOfDoubleProcedure<>(function);
        this.forEach(procedure);
        return procedure;
    }
}
