/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collector;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Optional;

import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * BigIntegerSummaryStatistics can be used to keep a rolling count, sum, min, max and average of BigInteger values.
 *
 * @see Collectors2#summarizingBigInteger(org.eclipse.collections.api.block.function.Function)
 * @since 8.1
 */
public class BigIntegerSummaryStatistics implements Procedure<BigInteger>
{
    private static final long serialVersionUID = 1L;
    private long count;
    private BigInteger sum = BigInteger.ZERO;
    private BigInteger min;
    private BigInteger max;

    @Override
    public void value(BigInteger each)
    {
        this.count++;
        if (each != null)
        {
            this.sum = this.sum.add(each);
            this.min = this.min == null ? each : this.min.min(each);
            this.max = this.max == null ? each : this.max.max(each);
        }
    }

    public long getCount()
    {
        return this.count;
    }

    public BigInteger getSum()
    {
        return this.sum;
    }

    public BigInteger getMin()
    {
        return this.min;
    }

    public Optional<BigInteger> getMinOptional()
    {
        return Optional.ofNullable(this.min);
    }

    public BigInteger getMax()
    {
        return this.max;
    }

    public Optional<BigInteger> getMaxOptional()
    {
        return Optional.ofNullable(this.max);
    }

    public BigDecimal getAverage(MathContext context)
    {
        return this.count == 0L ? BigDecimal.ZERO : new BigDecimal(this.getSum()).divide(BigDecimal.valueOf(this.count), context);
    }

    public BigDecimal getAverage()
    {
        return this.getAverage(MathContext.DECIMAL128);
    }

    public BigIntegerSummaryStatistics merge(BigIntegerSummaryStatistics summaryStatistics)
    {
        this.count += summaryStatistics.count;
        this.sum = this.sum.add(summaryStatistics.sum);
        if (summaryStatistics.min != null)
        {
            this.min = this.min == null ? summaryStatistics.min : this.min.min(summaryStatistics.min);
        }
        if (summaryStatistics.max != null)
        {
            this.max = this.max == null ? summaryStatistics.max : this.max.max(summaryStatistics.max);
        }
        return this;
    }
}
