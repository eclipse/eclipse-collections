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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.block.function.checked.CheckedFunction0;
import org.eclipse.collections.impl.block.function.checked.ThrowingFunction0;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;

public final class Functions0
{
    private static final TrueFunction TRUE_FUNCTION = new TrueFunction();
    private static final FalseFunction FALSE_FUNCTION = new FalseFunction();
    private static final NewFastListFunction<?> NEW_FAST_LIST_FUNCTION = new NewFastListFunction<>();
    private static final NewUnifiedSetFunction<?> NEW_UNIFIED_SET_FUNCTION = new NewUnifiedSetFunction<>();
    private static final NewHashBagFunction<?> NEW_HASH_BAG_FUNCTION = new NewHashBagFunction<>();
    private static final NewUnifiedMapFunction<?, ?> NEW_UNIFIED_MAP_FUNCTION = new NewUnifiedMapFunction<>();
    private static final NullFunction<?> NULL_FUNCTION = new NullFunction<>();
    private static final AtomicIntegerZeroFunction ATOMIC_INTEGER_ZERO = new AtomicIntegerZeroFunction();
    private static final AtomicLongZeroFunction ATOMIC_LONG_ZERO = new AtomicLongZeroFunction();
    private static final IntegerZeroFunction INTEGER_ZERO = new IntegerZeroFunction();
    private static final BigDecimalZeroFunction BIG_DECIMAL_ZERO = new BigDecimalZeroFunction();
    private static final BigIntegerZeroFunction BIG_INTEGER_ZERO = new BigIntegerZeroFunction();

    private Functions0()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    /**
     * @since 6.0
     */
    public static Function0<Boolean> getTrue()
    {
        return TRUE_FUNCTION;
    }

    /**
     * @since 6.0
     */
    public static Function0<Boolean> getFalse()
    {
        return FALSE_FUNCTION;
    }

    public static <T> Function0<MutableList<T>> newFastList()
    {
        return (Function0<MutableList<T>>) (Function0<?>) NEW_FAST_LIST_FUNCTION;
    }

    public static <T> Function0<MutableSet<T>> newUnifiedSet()
    {
        return (Function0<MutableSet<T>>) (Function0<?>) NEW_UNIFIED_SET_FUNCTION;
    }

    public static <T> Function0<MutableBag<T>> newHashBag()
    {
        return (Function0<MutableBag<T>>) (Function0<?>) NEW_HASH_BAG_FUNCTION;
    }

    public static <K, V> Function0<MutableMap<K, V>> newUnifiedMap()
    {
        return (Function0<MutableMap<K, V>>) (Function0<?>) NEW_UNIFIED_MAP_FUNCTION;
    }

    /**
     * Allows a lambda or anonymous inner class that needs to throw a checked exception to be safely wrapped as a
     * Function that will throw a RuntimeException, wrapping the checked exception that is the cause.
     */
    public static <T> Function0<T> throwing(ThrowingFunction0<T> throwingFunction0)
    {
        return new ThrowingFunction0Adapter<>(throwingFunction0);
    }

    /**
     * Allows a lambda or anonymous inner class that needs to throw a checked exception to be safely wrapped as a
     * Function0 that will throw a user specified RuntimeException based on the provided function. The function
     * is passed the current element and the checked exception that was thrown as context arguments.
     */
    public static <T> Function0<T> throwing(
            ThrowingFunction0<T> throwingFunction0,
            Function<? super Throwable, ? extends RuntimeException> rethrow)
    {
        return () ->
        {
            try
            {
                return throwingFunction0.safeValue();
            }
            catch (RuntimeException e)
            {
                throw e;
            }
            catch (Throwable t)
            {
                throw rethrow.valueOf(t);
            }
        };
    }

    public static <T> Function0<T> nullValue()
    {
        return (Function0<T>) NULL_FUNCTION;
    }

    public static <T> Function0<T> value(T t)
    {
        return new PassThruFunction0<>(t);
    }

    public static Function0<Integer> zeroInteger()
    {
        return INTEGER_ZERO;
    }

    public static Function0<AtomicInteger> zeroAtomicInteger()
    {
        return ATOMIC_INTEGER_ZERO;
    }

    public static Function0<AtomicLong> zeroAtomicLong()
    {
        return ATOMIC_LONG_ZERO;
    }

    /**
     * @since 6.0
     */
    public static Function0<BigDecimal> zeroBigDecimal()
    {
        return BIG_DECIMAL_ZERO;
    }

    /**
     * @since 6.0
     */
    public static Function0<BigInteger> zeroBigInteger()
    {
        return BIG_INTEGER_ZERO;
    }

    private static final class NewFastListFunction<T> implements Function0<MutableList<T>>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public MutableList<T> value()
        {
            return Lists.mutable.empty();
        }
    }

    private static final class NewUnifiedMapFunction<K, V> implements Function0<MutableMap<K, V>>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public MutableMap<K, V> value()
        {
            return Maps.mutable.empty();
        }
    }

    private static final class NewUnifiedSetFunction<T> implements Function0<MutableSet<T>>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public MutableSet<T> value()
        {
            return Sets.mutable.empty();
        }
    }

    private static final class NewHashBagFunction<T> implements Function0<MutableBag<T>>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public MutableBag<T> value()
        {
            return Bags.mutable.empty();
        }
    }

    private static final class NullFunction<T> implements Function0<T>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public T value()
        {
            return null;
        }
    }

    private static final class IntegerZeroFunction implements Function0<Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer value()
        {
            return Integer.valueOf(0);
        }
    }

    private static final class AtomicIntegerZeroFunction implements Function0<AtomicInteger>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public AtomicInteger value()
        {
            return new AtomicInteger(0);
        }
    }

    private static final class AtomicLongZeroFunction implements Function0<AtomicLong>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public AtomicLong value()
        {
            return new AtomicLong(0);
        }
    }

    private static final class ThrowingFunction0Adapter<T> extends CheckedFunction0<T>
    {
        private static final long serialVersionUID = 1L;
        private final ThrowingFunction0<T> throwingFunction0;

        private ThrowingFunction0Adapter(ThrowingFunction0<T> throwingFunction0)
        {
            this.throwingFunction0 = throwingFunction0;
        }

        @Override
        public T safeValue() throws Exception
        {
            return this.throwingFunction0.safeValue();
        }
    }

    private static final class BigDecimalZeroFunction implements Function0<BigDecimal>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public BigDecimal value()
        {
            return BigDecimal.ZERO;
        }
    }

    private static final class BigIntegerZeroFunction implements Function0<BigInteger>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public BigInteger value()
        {
            return BigInteger.ZERO;
        }
    }

    private static final class TrueFunction implements Function0<Boolean>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Boolean value()
        {
            return Boolean.TRUE;
        }
    }

    private static final class FalseFunction implements Function0<Boolean>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Boolean value()
        {
            return Boolean.FALSE;
        }
    }
}
