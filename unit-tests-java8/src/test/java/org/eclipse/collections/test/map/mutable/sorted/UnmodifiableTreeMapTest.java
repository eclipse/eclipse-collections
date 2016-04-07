/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map.mutable.sorted;

import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
import org.eclipse.collections.impl.map.sorted.mutable.UnmodifiableTreeMap;
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.eclipse.collections.test.UnmodifiableIterableTestCase;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNull;

@RunWith(Java8Runner.class)
public class UnmodifiableTreeMapTest implements MutableSortedMapIterableTestCase, UnmodifiableIterableTestCase
{
    @Override
    public <T> MutableSortedMap<Object, T> newWith(T... elements)
    {
        int i = elements.length;
        MutableSortedMap<Object, T> result = new TreeSortedMap<>(Comparators.reverseNaturalOrder());
        for (T each : elements)
        {
            assertNull(result.put(i, each));
            i--;
        }
        return UnmodifiableTreeMap.of(result);
    }

    @Override
    public void Iterable_remove()
    {
        UnmodifiableIterableTestCase.super.Iterable_remove();
    }
}
