/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.comparator;

import java.util.Comparator;

import org.eclipse.collections.api.block.SerializableComparator;
import org.eclipse.collections.api.block.function.Function;

/**
 * Simple {@link Comparator} that uses a {@link Function}
 * to select a value from the underlying object and compare it against a known value to determine ordering.
 */
public class FunctionComparator<T, V>
        implements SerializableComparator<T>
{
    private static final long serialVersionUID = 1L;
    private final Function<? super T, ? extends V> function;
    private final Comparator<V> comparator;

    public FunctionComparator(Function<? super T, ? extends V> function, Comparator<V> comparator)
    {
        this.function = function;
        this.comparator = comparator;
    }

    @Override
    public int compare(T o1, T o2)
    {
        V attrValue1 = this.function.valueOf(o1);
        V attrValue2 = this.function.valueOf(o2);
        return this.comparator.compare(attrValue1, attrValue2);
    }
}
