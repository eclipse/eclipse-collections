/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.tuple;

import java.util.Map;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class TuplesTest
{
    @Test
    public void pairFrom()
    {
        Pair<String, String> pair = Tuples.pair("1", "2");
        Map.Entry<String, String> entry = pair.toEntry();
        Pair<String, String> pair2 = Tuples.pairFrom(entry);
        Assert.assertEquals(pair, pair2);
    }

    @Test
    public void pair()
    {
        Pair<String, String> pair = Tuples.pair("1", "2");
        Assert.assertEquals("1", pair.getOne());
        Assert.assertEquals("2", pair.getTwo());
    }

    @Test
    public void twin()
    {
        Twin<String> twin = Tuples.twin("1", "2");
        Assert.assertEquals("1", twin.getOne());
        Assert.assertEquals("2", twin.getTwo());
    }

    @Test
    public void equalsHashCode()
    {
        Twin<String> pair1 = Tuples.twin("1", "1");
        Pair<String, String> pair1a = Tuples.pair("1", "1");
        Pair<String, String> pair2 = Tuples.pair("2", "2");

        Verify.assertEqualsAndHashCode(pair1, pair1);
        Verify.assertEqualsAndHashCode(pair1, pair1a);
        Assert.assertNotEquals(pair1, pair2);
        Assert.assertNotEquals(pair1, new Object());
    }

    @Test
    public void putToMap()
    {
        Pair<String, Integer> pair = Tuples.pair("1", 2);
        MutableMap<String, Integer> map = UnifiedMap.newMap();
        pair.put(map);
        Verify.assertContainsAllKeyValues(map, "1", 2);
        Verify.assertSize(1, map);
    }

    @Test
    public void testToString()
    {
        Pair<String, String> pair1 = Tuples.pair("1", "1");

        Assert.assertEquals("1:1", pair1.toString());
    }

    @Test
    public void pairFunctions()
    {
        Integer two = 2;
        Pair<String, Integer> pair = Tuples.pair("One", two);
        Assert.assertEquals("One", ((Function<Pair<String, ?>, String>) Pair::getOne).valueOf(pair));
        Assert.assertSame(two, ((Function<Pair<?, Integer>, Integer>) Pair::getTwo).valueOf(pair));
    }

    @Test
    public void swap()
    {
        Pair<String, Integer> pair = Tuples.pair("One", 1);
        Pair<Integer, String> swappedPair = pair.swap();
        Pair<Integer, String> expectedPair = Tuples.pair(1, "One");
        Assert.assertEquals(Integer.valueOf(1), swappedPair.getOne());
        Assert.assertEquals("One", swappedPair.getTwo());
        Assert.assertEquals(expectedPair, swappedPair);

        Twin<String> twin = Tuples.twin("One", "1");
        Twin<String> swappedTwin = twin.swap();
        Twin<String> expectedTwin = Tuples.twin("1", "One");
        Assert.assertEquals("1", swappedTwin.getOne());
        Assert.assertEquals("One", swappedTwin.getTwo());
        Assert.assertEquals(expectedTwin, swappedTwin);
    }
}
