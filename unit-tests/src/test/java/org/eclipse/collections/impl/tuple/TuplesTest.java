/*
 * Copyright (c) 2021 Goldman Sachs.
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
import org.eclipse.collections.api.tuple.Triple;
import org.eclipse.collections.api.tuple.Triplet;
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
    public void identicalTwin()
    {
        Twin<String> twin = Tuples.identicalTwin("1");
        Assert.assertEquals("1", twin.getOne());
        Assert.assertEquals("1", twin.getTwo());
        Assert.assertEquals(twin.getOne(), twin.getTwo());
    }

    @Test
    public void triple()
    {
        Triple<String, String, String> triple = Tuples.triple("1", "2", "3");
        Assert.assertEquals("1", triple.getOne());
        Assert.assertEquals("2", triple.getTwo());
        Assert.assertEquals("3", triple.getThree());
    }

    @Test
    public void triplet()
    {
        Triplet<String> triplet = Tuples.triplet("1", "2", "3");
        Assert.assertEquals("1", triplet.getOne());
        Assert.assertEquals("2", triplet.getTwo());
        Assert.assertEquals("3", triplet.getThree());
    }

    @Test
    public void identicalTriplet()
    {
        Triplet<String> triplet = Tuples.identicalTriplet("1");
        Assert.assertEquals("1", triplet.getOne());
        Assert.assertEquals("1", triplet.getTwo());
        Assert.assertEquals("1", triplet.getThree());
        Assert.assertEquals(triplet.getOne(), triplet.getTwo());
        Assert.assertEquals(triplet.getTwo(), triplet.getThree());
        Assert.assertEquals(triplet.getThree(), triplet.getOne());
    }

    @Test
    public void equalsHashCode()
    {
        Twin<String> pair1 = Tuples.twin("1", "1");
        Pair<String, String> pair1a = Tuples.pair("1", "1");
        Pair<String, String> pair2 = Tuples.pair("2", "2");
        Twin<String> pair3 = Tuples.identicalTwin("1");

        Verify.assertEqualsAndHashCode(pair1, pair1);
        Verify.assertEqualsAndHashCode(pair1, pair1a);
        Verify.assertEqualsAndHashCode(pair3, pair3);
        Verify.assertEqualsAndHashCode(pair1, pair3);
        Assert.assertNotEquals(pair1, pair2);
        Assert.assertNotEquals(pair1, new Object());
    }

    @Test
    public void equalsHashCodeTriple()
    {
        Triplet<String> triple1 = Tuples.triplet("1", "1", "1");
        Triple<String, String, String> triple1a = Tuples.triple("1", "1", "1");
        Triple<String, String, String> triple2 = Tuples.triple("2", "2", "2");
        Triplet<String> triple3 = Tuples.identicalTriplet("1");

        Verify.assertEqualsAndHashCode(triple1, triple1);
        Verify.assertEqualsAndHashCode(triple1, triple1a);
        Verify.assertEqualsAndHashCode(triple3, triple3);
        Verify.assertEqualsAndHashCode(triple1, triple3);
        Assert.assertNotEquals(triple1, triple2);
        Assert.assertNotEquals(triple1, new Object());
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

        Triple<String, String, String> triple = Tuples.triple("1", "2",  "3");
        Assert.assertEquals("1:2:3", triple.toString());

        Twin<String> identicalTwin = Tuples.identicalTwin("1");
        Assert.assertEquals("1:1", identicalTwin.toString());

        Triplet<String> identicalTriplet = Tuples.identicalTriplet("1");
        Assert.assertEquals("1:1:1", identicalTriplet.toString());
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

        Twin<String> identicalTwin = Tuples.identicalTwin("1");
        Twin<String> swappedIdenticalTwin = identicalTwin.swap();
        Twin<String> expectedIdenticalTwin = Tuples.identicalTwin("1");
        Assert.assertEquals("1", swappedIdenticalTwin.getOne());
        Assert.assertEquals("1", swappedIdenticalTwin.getTwo());
        Assert.assertEquals(expectedIdenticalTwin, swappedIdenticalTwin);
    }

    @Test
    public void reverse()
    {
        Triple<String, Integer, Boolean> triple = Tuples.triple("One",  2,  true);
        Triple<Boolean, Integer, String> reversedTriple = triple.reverse();
        Triple<Boolean, Integer, String> expectedTriple = Tuples.triple(true, 2, "One");
        Assert.assertEquals(true, reversedTriple.getOne());
        Assert.assertEquals(Integer.valueOf(2), reversedTriple.getTwo());
        Assert.assertEquals("One", reversedTriple.getThree());
        Assert.assertEquals(expectedTriple, reversedTriple);

        Triplet<String> triplet = Tuples.triplet("One", "2", "true");
        Triplet<String> reversedTriplet = triplet.reverse();
        Triplet<String> expectedTriplet = Tuples.triplet("true", "2", "One");
        Assert.assertEquals("true", reversedTriplet.getOne());
        Assert.assertEquals("2", reversedTriplet.getTwo());
        Assert.assertEquals("One", reversedTriplet.getThree());
        Assert.assertEquals(expectedTriplet, reversedTriplet);

        Triplet<String> identicalTriplet = Tuples.identicalTriplet("One");
        Triplet<String> reversedIdenticalTriplet = identicalTriplet.reverse();
        Triplet<String> expectedIdenticalTriplet = Tuples.identicalTriplet("One");
        Assert.assertEquals("One", reversedIdenticalTriplet.getOne());
        Assert.assertEquals("One", reversedIdenticalTriplet.getTwo());
        Assert.assertEquals("One", reversedIdenticalTriplet.getThree());
        Assert.assertEquals(expectedIdenticalTriplet, reversedIdenticalTriplet);
    }
}
