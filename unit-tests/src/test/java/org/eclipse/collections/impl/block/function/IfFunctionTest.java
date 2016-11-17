/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.junit.Assert;
import org.junit.Test;

public class IfFunctionTest
{
    @Test
    public void iterate()
    {
        UnifiedMap<Integer, Integer> map = UnifiedMap.newMap(5);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);

        IfFunction<Integer, Integer> function = new IfFunction<>(
                IntegerPredicates.isEven(),
                (Integer ignored) -> 1,
                (Integer ignored) -> 0);
        MutableList<Integer> result = map.valuesView().collect(function).toList();

        Assert.assertEquals(FastList.newListWith(0, 1, 0, 1, 0), result);
    }

    @Test
    public void testIf()
    {
        IfFunction<Integer, Boolean> function = new IfFunction<>(
                Predicates.greaterThan(5),
                (Integer ignored) -> true);

        Assert.assertTrue(function.valueOf(10));
    }

    @Test
    public void ifElse()
    {
        IfFunction<Integer, Boolean> function = new IfFunction<>(
                Predicates.greaterThan(5),
                (Integer ignored) -> true,
                (Integer ignored) -> false);

        Assert.assertFalse(function.valueOf(1));
    }
}
