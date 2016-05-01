/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function;

import java.util.Collection;
import java.util.Map;

import org.eclipse.collections.api.block.function.Function2;

/**
 * MaxSizeFunction compares the size of strings, collections, or maps to determine the max size.
 */
public final class MaxSizeFunction
{
    public static final Function2<Integer, String, Integer> STRING = new MaxSizeStringFunction();
    public static final Function2<Integer, Collection<?>, Integer> COLLECTION = new MaxSizeCollectionFunction();
    public static final Function2<Integer, Map<?, ?>, Integer> MAP = new MaxSizeMapFunction();

    private MaxSizeFunction()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    private static class MaxSizeStringFunction implements Function2<Integer, String, Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer value(Integer previousMax, String s)
        {
            return Math.max(previousMax, s.length());
        }
    }

    private static class MaxSizeCollectionFunction implements Function2<Integer, Collection<?>, Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer value(Integer previousMax, Collection<?> collection)
        {
            return Math.max(previousMax, collection.size());
        }
    }

    private static class MaxSizeMapFunction implements Function2<Integer, Map<?, ?>, Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer value(Integer previousMax, Map<?, ?> map)
        {
            return Math.max(previousMax, map.size());
        }
    }
}
