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
 * MinSizeFunction compares the size of strings, collections, or maps to determine the min size.
 */
public final class MinSizeFunction
{
    public static final Function2<Integer, String, Integer> STRING = new MinSizeStringFunction();
    public static final Function2<Integer, Collection<?>, Integer> COLLECTION = new MinSizeCollectionFunction();
    public static final Function2<Integer, Map<?, ?>, Integer> MAP = new MinSizeMapFunction();

    private MinSizeFunction()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    private static class MinSizeStringFunction implements Function2<Integer, String, Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer value(Integer previousMin, String s)
        {
            return Math.min(previousMin, s.length());
        }
    }

    private static class MinSizeCollectionFunction implements Function2<Integer, Collection<?>, Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer value(Integer previousMin, Collection<?> collection)
        {
            return Math.min(previousMin, collection.size());
        }
    }

    private static class MinSizeMapFunction implements Function2<Integer, Map<?, ?>, Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer value(Integer previousMin, Map<?, ?> map)
        {
            return Math.min(previousMin, map.size());
        }
    }
}
