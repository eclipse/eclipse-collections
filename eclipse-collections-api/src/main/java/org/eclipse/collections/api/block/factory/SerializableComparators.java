/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.block.factory;

import org.eclipse.collections.api.block.SerializableComparator;
import org.eclipse.collections.api.block.comparator.FunctionComparator;
import org.eclipse.collections.api.block.function.Function;

/**
 * This class provides a minimal set of SerializableComparator methods for use in the API module.
 *
 * @since 11.1
 */
public final class SerializableComparators
{
    private static final SerializableComparator<?> NATURAL_ORDER_COMPARATOR = new NaturalOrderComparator<>();
    private static final SerializableComparator<?> REVERSE_NATURAL_ORDER_COMPARATOR = new ReverseComparator<>(NATURAL_ORDER_COMPARATOR);

    private SerializableComparators()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    /**
     * Uses the natural compareTo methods of the objects which will throw if there are any nulls.
     */
    public static <T> SerializableComparator<T> naturalOrder()
    {
        return (SerializableComparator<T>) NATURAL_ORDER_COMPARATOR;
    }

    /**
     * Uses the natural compareTo methods of the objects which will throw if there are any nulls.
     */
    public static <T> SerializableComparator<T> reverseNaturalOrder()
    {
        return (SerializableComparator<T>) REVERSE_NATURAL_ORDER_COMPARATOR;
    }

    /**
     * @param comparator original comparator whose order will be reversed
     * @return A comparator that reverses the order of any other Serializable Comparator.
     */
    public static <T> SerializableComparator<T> reverse(SerializableComparator<T> comparator)
    {
        if (comparator == null)
        {
            throw new NullPointerException();
        }
        return new ReverseComparator<>(comparator);
    }

    private static final class NaturalOrderComparator<T extends Comparable<T>> implements SerializableComparator<T>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(T o1, T o2)
        {
            if (o1 == null || o2 == null)
            {
                throw new NullPointerException();
            }
            return o1.compareTo(o2);
        }
    }

    private static final class ReverseComparator<T> implements SerializableComparator<T>
    {
        private static final long serialVersionUID = 1L;

        private final SerializableComparator<T> comparator;

        private ReverseComparator(SerializableComparator<T> comparator)
        {
            this.comparator = comparator;
        }

        @Override
        public int compare(T o1, T o2)
        {
            return this.comparator.compare(o2, o1);
        }
    }

    public static <T, V extends Comparable<? super V>> SerializableComparator<T> byFunction(Function<? super T, ? extends V> function)
    {
        return SerializableComparators.byFunction(function, SerializableComparators.naturalOrder());
    }

    public static <T, V> SerializableComparator<T> byFunction(Function<? super T, ? extends V> function, SerializableComparator<V> comparator)
    {
        return new FunctionComparator<>(function, comparator);
    }
}
