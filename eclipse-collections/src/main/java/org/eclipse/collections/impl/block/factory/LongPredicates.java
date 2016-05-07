/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory;

import org.eclipse.collections.api.block.function.Function;

public final class LongPredicates
{
    private static final Predicates<Long> IS_ODD = new LongIsOdd();
    private static final Predicates<Long> IS_EVEN = new LongIsEven();
    private static final Predicates<Long> IS_POSITIVE = new LongIsPositive();
    private static final Predicates<Long> IS_NEGATIVE = new LongIsNegative();
    private static final Predicates<Long> IS_ZERO = new LongIsZero();

    private LongPredicates()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static Predicates<Long> isOdd()
    {
        return IS_ODD;
    }

    public static Predicates<Long> isEven()
    {
        return IS_EVEN;
    }

    public static Predicates<Long> isPositive()
    {
        return IS_POSITIVE;
    }

    public static Predicates<Long> isNegative()
    {
        return IS_NEGATIVE;
    }

    public static Predicates<Long> isZero()
    {
        return IS_ZERO;
    }

    public static <T> Predicates<T> attributeIsEven(Function<T, Long> function)
    {
        return Predicates.attributePredicate(function, LongPredicates.isEven());
    }

    public static <T> Predicates<T> attributeIsOdd(Function<T, Long> function)
    {
        return Predicates.attributePredicate(function, LongPredicates.isOdd());
    }

    public static <T> Predicates<T> attributeIsZero(Function<T, Long> function)
    {
        return Predicates.attributePredicate(function, LongPredicates.isZero());
    }

    public static <T> Predicates<T> attributeIsPositive(Function<T, Long> function)
    {
        return Predicates.attributePredicate(function, LongPredicates.isPositive());
    }

    public static <T> Predicates<T> attributeIsNegative(Function<T, Long> function)
    {
        return Predicates.attributePredicate(function, LongPredicates.isNegative());
    }

    private static class LongIsOdd extends Predicates<Long>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Long l)
        {
            return l.longValue() % 2 != 0;
        }
    }

    private static class LongIsEven extends Predicates<Long>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Long l)
        {
            return l.longValue() % 2 == 0;
        }
    }

    private static class LongIsPositive extends Predicates<Long>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Long l)
        {
            return l.longValue() > 0;
        }
    }

    private static class LongIsNegative extends Predicates<Long>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Long l)
        {
            return l.longValue() < 0;
        }
    }

    private static class LongIsZero extends Predicates<Long>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Long l)
        {
            return l.longValue() == 0;
        }
    }
}
