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

public final class IntegerPredicates
{
    private static final Predicates<Integer> IS_ODD = new IntegerIsOdd();
    private static final Predicates<Integer> IS_EVEN = new IntegerIsEven();
    private static final Predicates<Integer> IS_POSITIVE = new IntegerIsPositive();
    private static final Predicates<Integer> IS_NEGATIVE = new IntegerIsNegative();
    private static final Predicates<Integer> IS_ZERO = new IntegerIsZero();

    private IntegerPredicates()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static Predicates<Integer> isOdd()
    {
        return IS_ODD;
    }

    public static Predicates<Integer> isEven()
    {
        return IS_EVEN;
    }

    public static Predicates<Integer> isPositive()
    {
        return IS_POSITIVE;
    }

    public static Predicates<Integer> isNegative()
    {
        return IS_NEGATIVE;
    }

    public static Predicates<Integer> isZero()
    {
        return IS_ZERO;
    }

    public static <T> Predicates<T> attributeIsEven(Function<T, Integer> function)
    {
        return Predicates.attributePredicate(function, IntegerPredicates.isEven());
    }

    public static <T> Predicates<T> attributeIsOdd(Function<T, Integer> function)
    {
        return Predicates.attributePredicate(function, IntegerPredicates.isOdd());
    }

    public static <T> Predicates<T> attributeIsZero(Function<T, Integer> function)
    {
        return Predicates.attributePredicate(function, IntegerPredicates.isZero());
    }

    public static <T> Predicates<T> attributeIsPositive(Function<T, Integer> function)
    {
        return Predicates.attributePredicate(function, IntegerPredicates.isPositive());
    }

    public static <T> Predicates<T> attributeIsNegative(Function<T, Integer> function)
    {
        return Predicates.attributePredicate(function, IntegerPredicates.isNegative());
    }

    private static class IntegerIsPositive extends Predicates<Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Integer i)
        {
            return i.intValue() > 0;
        }
    }

    private static class IntegerIsNegative extends Predicates<Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Integer i)
        {
            return i.intValue() < 0;
        }
    }

    private static class IntegerIsZero extends Predicates<Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Integer i)
        {
            return i.intValue() == 0;
        }
    }

    private static class IntegerIsOdd extends Predicates<Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Integer i)
        {
            return i.intValue() % 2 != 0;
        }
    }

    private static class IntegerIsEven extends Predicates<Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Integer i)
        {
            return i.intValue() % 2 == 0;
        }
    }
}
