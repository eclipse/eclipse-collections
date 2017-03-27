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

import java.util.Comparator;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.Function3;
import org.eclipse.collections.impl.block.function.checked.CheckedFunction2;
import org.eclipse.collections.impl.block.function.checked.ThrowingFunction2;

/**
 * Contains factory methods for creating {@link Function2} instances.
 */
public final class Functions2
{
    private static final Function2<Integer, Integer, Integer> INTEGER_ADDITION = new IntegerAddition();

    private Functions2()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static <T, V, P> Function2<T, P, V> fromFunction(Function<? super T, ? extends V> function)
    {
        return new FunctionAdapter<>(function);
    }

    /**
     * Allows a lambda or anonymous inner class that needs to throw a checked exception to be safely wrapped as a
     * Function2 that will throw a RuntimeException, wrapping the checked exception that is the cause.
     */
    public static <T, V, P> Function2<T, P, V> throwing(ThrowingFunction2<T, P, V> throwingFunction2)
    {
        return new ThrowingFunction2Adapter<>(throwingFunction2);
    }

    /**
     * Allows a lambda or anonymous inner class that needs to throw a checked exception to be safely wrapped as a
     * Function2 that will throw a user specified RuntimeException based on the provided function. The function
     * is passed the current element and the checked exception that was thrown as context arguments.
     */
    public static <T1, T2, V> Function2<T1, T2, V> throwing(
            ThrowingFunction2<T1, T2, V> throwingFunction2,
            Function3<T1, T2, ? super Throwable, ? extends RuntimeException> rethrow)
    {
        return (one, two) ->
        {
            try
            {
                return throwingFunction2.safeValue(one, two);
            }
            catch (RuntimeException e)
            {
                throw e;
            }
            catch (Throwable t)
            {
                throw rethrow.value(one, two, t);
            }
        };
    }

    public static Function2<Integer, Integer, Integer> integerAddition()
    {
        return INTEGER_ADDITION;
    }

    public static <T> Function2<T, T, T> min(Comparator<? super T> comparator)
    {
        return new MinFunction2<>(comparator);
    }

    public static <T> Function2<T, T, T> max(Comparator<? super T> comparator)
    {
        return new MaxFunction2<>(comparator);
    }

    public static <T, V extends Comparable<? super V>> Function2<T, T, T> minBy(Function<? super T, ? extends V> function)
    {
        return new MinByFunction2<>(function);
    }

    public static <T, V extends Comparable<? super V>> Function2<T, T, T> maxBy(Function<? super T, ? extends V> function)
    {
        return new MaxByFunction2<>(function);
    }

    private static final class FunctionAdapter<T, P, V> implements Function2<T, P, V>
    {
        private static final long serialVersionUID = 1L;
        private final Function<? super T, ? extends V> function;

        private FunctionAdapter(Function<? super T, ? extends V> function)
        {
            this.function = function;
        }

        @Override
        public V value(T each, P parameter)
        {
            return this.function.valueOf(each);
        }
    }

    private static class IntegerAddition implements Function2<Integer, Integer, Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer value(Integer aggregate, Integer value)
        {
            return aggregate + value;
        }
    }

    private static final class ThrowingFunction2Adapter<T, P, V> extends CheckedFunction2<T, P, V>
    {
        private static final long serialVersionUID = 1L;
        private final ThrowingFunction2<T, P, V> throwingFunction2;

        private ThrowingFunction2Adapter(ThrowingFunction2<T, P, V> throwingFunction2)
        {
            this.throwingFunction2 = throwingFunction2;
        }

        @Override
        public V safeValue(T argument1, P argument2) throws Exception
        {
            return this.throwingFunction2.safeValue(argument1, argument2);
        }
    }

    private static final class MinFunction2<T> implements Function2<T, T, T>
    {
        private static final long serialVersionUID = 1L;
        private final Comparator<? super T> comparator;

        private MinFunction2(Comparator<? super T> comparator)
        {
            this.comparator = comparator;
        }

        @Override
        public T value(T argument1, T argument2)
        {
            return this.comparator.compare(argument1, argument2) > 0 ? argument2 : argument1;
        }
    }

    private static final class MaxFunction2<T> implements Function2<T, T, T>
    {
        private static final long serialVersionUID = 1L;
        private final Comparator<? super T> comparator;

        private MaxFunction2(Comparator<? super T> comparator)
        {
            this.comparator = comparator;
        }

        @Override
        public T value(T argument1, T argument2)
        {
            return this.comparator.compare(argument1, argument2) < 0 ? argument2 : argument1;
        }
    }

    private static final class MinByFunction2<T, V extends Comparable<? super V>> implements Function2<T, T, T>
    {
        private static final long serialVersionUID = 1L;
        private final Function<? super T, ? extends V> function;

        private MinByFunction2(Function<? super T, ? extends V> function)
        {
            this.function = function;
        }

        @Override
        public T value(T argument1, T argument2)
        {
            V first = this.function.valueOf(argument1);
            V second = this.function.valueOf(argument2);
            return first.compareTo(second) > 0 ? argument2 : argument1;
        }
    }

    private static final class MaxByFunction2<T, V extends Comparable<? super V>> implements Function2<T, T, T>
    {
        private static final long serialVersionUID = 1L;
        private final Function<? super T, ? extends V> function;

        private MaxByFunction2(Function<? super T, ? extends V> function)
        {
            this.function = function;
        }

        @Override
        public T value(T argument1, T argument2)
        {
            V first = this.function.valueOf(argument1);
            V second = this.function.valueOf(argument2);
            return first.compareTo(second) < 0 ? argument2 : argument1;
        }
    }
}
