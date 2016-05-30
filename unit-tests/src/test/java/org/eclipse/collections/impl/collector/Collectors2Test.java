/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collector;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.partition.bag.PartitionMutableBag;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.partition.set.PartitionMutableSet;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.BiMaps;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.partition.bag.PartitionHashBag;
import org.eclipse.collections.impl.partition.list.PartitionFastList;
import org.eclipse.collections.impl.partition.set.PartitionUnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

public class Collectors2Test
{
    public static final Interval SMALL_INTERVAL = Interval.oneTo(5);
    public static final Interval LARGE_INTERVAL = Interval.oneTo(20000);
    public static final int HALF_SIZE = LARGE_INTERVAL.size() / 2;
    private final List<Integer> smallData = new ArrayList<Integer>(SMALL_INTERVAL);
    private final List<Integer> bigData = new ArrayList<Integer>(LARGE_INTERVAL);

    @Test
    public void makeString0()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.makeString(),
                this.smallData.stream().collect(Collectors2.makeString()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.makeString()),
                this.smallData.stream().collect(Collectors2.makeString()));
        Assert.assertEquals(
                LARGE_INTERVAL.makeString(),
                this.bigData.stream().collect(Collectors2.makeString()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.makeString()),
                this.bigData.stream().collect(Collectors2.makeString()));
    }

    @Test
    public void makeString0Parallel()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.makeString(),
                this.smallData.parallelStream().collect(Collectors2.makeString()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.makeString()),
                this.smallData.parallelStream().collect(Collectors2.makeString()));
        Assert.assertEquals(
                LARGE_INTERVAL.makeString(),
                this.bigData.parallelStream().collect(Collectors2.makeString()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.makeString()),
                this.bigData.parallelStream().collect(Collectors2.makeString()));
    }

    @Test
    public void makeString1()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.makeString("/"),
                this.smallData.stream().collect(Collectors2.makeString("/")));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.makeString("/")),
                this.smallData.stream().collect(Collectors2.makeString("/")));
        Assert.assertEquals(
                LARGE_INTERVAL.makeString("/"),
                this.bigData.stream().collect(Collectors2.makeString("/")));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.makeString("/")),
                this.bigData.stream().collect(Collectors2.makeString("/")));
    }

    @Test
    public void makeString1Parallel()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.makeString("/"),
                this.smallData.parallelStream().collect(Collectors2.makeString("/")));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.makeString("/")),
                this.smallData.parallelStream().collect(Collectors2.makeString("/")));
        Assert.assertEquals(
                LARGE_INTERVAL.makeString("/"),
                this.bigData.parallelStream().collect(Collectors2.makeString("/")));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.makeString("/")),
                this.bigData.parallelStream().collect(Collectors2.makeString("/")));
    }

    @Test
    public void makeString3()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.makeString("[", "/", "]"),
                this.smallData.stream().collect(Collectors2.makeString("[", "/", "]")));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.makeString("[", "/", "]")),
                this.smallData.stream().collect(Collectors2.makeString("[", "/", "]")));
        Assert.assertEquals(
                LARGE_INTERVAL.makeString("[", "/", "]"),
                this.bigData.stream().collect(Collectors2.makeString("[", "/", "]")));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.makeString("[", "/", "]")),
                this.bigData.stream().collect(Collectors2.makeString("[", "/", "]")));
    }

    @Test
    public void makeString3Parallel()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.makeString("[", "/", "]"),
                this.smallData.parallelStream().collect(Collectors2.makeString("[", "/", "]")));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.makeString("[", "/", "]")),
                this.smallData.parallelStream().collect(Collectors2.makeString("[", "/", "]")));
        Assert.assertEquals(
                LARGE_INTERVAL.makeString("[", "/", "]"),
                this.bigData.parallelStream().collect(Collectors2.makeString("[", "/", "]")));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.makeString("[", "/", "]")),
                this.bigData.parallelStream().collect(Collectors2.makeString("[", "/", "]")));
    }

    @Test
    public void toList()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toList(),
                this.smallData.stream().collect(Collectors2.toList()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toList()),
                this.smallData.stream().collect(Collectors2.toList()));
    }

    @Test
    public void toListParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toList(),
                this.bigData.parallelStream().collect(Collectors2.toList()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toList()),
                this.bigData.parallelStream().collect(Collectors2.toList()));
    }

    @Test
    public void toImmutableList()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toList(),
                this.smallData.stream().collect(Collectors2.toImmutableList()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toList()),
                this.smallData.stream().collect(Collectors2.toImmutableList()));
    }

    @Test
    public void toImmutableListParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toList(),
                this.bigData.parallelStream().collect(Collectors2.toImmutableList()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toList()),
                this.bigData.parallelStream().collect(Collectors2.toImmutableList()));
    }

    @Test
    public void toSet()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSet(),
                this.smallData.stream().collect(Collectors2.toSet()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSet()),
                this.smallData.stream().collect(Collectors2.toSet()));
    }

    @Test
    public void toSetParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSet(),
                this.bigData.parallelStream().collect(Collectors2.toSet()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSet()),
                this.bigData.parallelStream().collect(Collectors2.toSet()));
    }

    @Test
    public void toImmutableSet()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSet(),
                this.smallData.stream().collect(Collectors2.toImmutableSet()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSet()),
                this.smallData.stream().collect(Collectors2.toImmutableSet()));
    }

    @Test
    public void toImmutableSetParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSet(),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSet()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSet()),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSet()));
    }

    @Test
    public void toBag()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toBag(),
                this.smallData.stream().collect(Collectors2.toBag()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toBag()),
                this.smallData.stream().collect(Collectors2.toBag()));
    }

    @Test
    public void toBagParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toBag(),
                this.bigData.parallelStream().collect(Collectors2.toBag()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toBag()),
                this.bigData.parallelStream().collect(Collectors2.toBag()));
    }

    @Test
    public void toImmutableBag()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toBag(),
                this.smallData.stream().collect(Collectors2.toImmutableBag()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toBag()),
                this.smallData.stream().collect(Collectors2.toImmutableBag()));
    }

    @Test
    public void toImmutableBagParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toBag(),
                this.bigData.parallelStream().collect(Collectors2.toImmutableBag()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toBag()),
                this.bigData.parallelStream().collect(Collectors2.toImmutableBag()));
    }

    @Test
    public void toListMultimap()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.groupBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toListMultimap(Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toListMultimap(Object::toString)),
                this.smallData.stream().collect(Collectors2.toListMultimap(Object::toString)));
    }

    @Test
    public void toListMultimapParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.groupBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toListMultimap(Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toListMultimap(Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toListMultimap(Object::toString)));
    }

    @Test
    public void toListMultimap2()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.collect(Object::toString).groupBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toListMultimap(Object::toString, Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toListMultimap(Object::toString, Object::toString)),
                this.smallData.stream().collect(Collectors2.toListMultimap(Object::toString, Object::toString)));
    }

    @Test
    public void toListMultimap2Parallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.collect(Object::toString).groupBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toListMultimap(Object::toString, Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toListMultimap(Object::toString, Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toListMultimap(Object::toString, Object::toString)));
    }

    @Test
    public void toSetMultimap()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSet().groupBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toSetMultimap(Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSetMultimap(Object::toString)),
                this.smallData.stream().collect(Collectors2.toSetMultimap(Object::toString)));
    }

    @Test
    public void toSetMultimapParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSet().groupBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toSetMultimap(Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSetMultimap(Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toSetMultimap(Object::toString)));
    }

    @Test
    public void toSetMultimap2()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSet().collect(Object::toString).groupBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toSetMultimap(Object::toString, Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSetMultimap(Object::toString, Object::toString)),
                this.smallData.stream().collect(Collectors2.toSetMultimap(Object::toString, Object::toString)));
    }

    @Test
    public void toSetMultimap2Parallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSet().collect(Object::toString).groupBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toSetMultimap(Object::toString, Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSetMultimap(Object::toString, Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toSetMultimap(Object::toString, Object::toString)));
    }

    @Test
    public void toBagMultimap()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toBag().groupBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toBagMultimap(Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toBagMultimap(Object::toString)),
                this.smallData.stream().collect(Collectors2.toBagMultimap(Object::toString)));
    }

    @Test
    public void toBagMultimapParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toBag().groupBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toBagMultimap(Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toBagMultimap(Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toBagMultimap(Object::toString)));
    }

    @Test
    public void toBagMultimap2()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toBag().collect(Object::toString).groupBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toBagMultimap(Object::toString, Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toBagMultimap(Object::toString, Object::toString)),
                this.smallData.stream().collect(Collectors2.toBagMultimap(Object::toString, Object::toString)));
    }

    @Test
    public void toBagMultimap2Parallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toBag().collect(Object::toString).groupBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toBagMultimap(Object::toString, Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toBagMultimap(Object::toString, Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toBagMultimap(Object::toString, Object::toString)));
    }

    @Test
    public void toStack()
    {
        Assert.assertEquals(
                Stacks.mutable.ofAll(SMALL_INTERVAL),
                this.smallData.stream().collect(Collectors2.toStack()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toStack()),
                this.smallData.stream().collect(Collectors2.toStack()));
        Assert.assertEquals(
                SMALL_INTERVAL.toList().toStack(),
                SMALL_INTERVAL.reduceInPlace(Collectors2.toStack()));
    }

    @Test
    public void toStackParallel()
    {
        Assert.assertEquals(
                Stacks.mutable.ofAll(LARGE_INTERVAL),
                this.bigData.parallelStream().collect(Collectors2.toStack()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toStack()),
                this.bigData.parallelStream().collect(Collectors2.toStack()));
    }

    @Test
    public void toImmutableStack()
    {
        Assert.assertEquals(
                Stacks.mutable.ofAll(SMALL_INTERVAL),
                this.smallData.stream().collect(Collectors2.toImmutableStack()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toStack()),
                this.smallData.stream().collect(Collectors2.toImmutableStack()));
        Assert.assertEquals(
                SMALL_INTERVAL.toList().toStack().toImmutable(),
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableStack()));
    }

    @Test
    public void toImmutableStackParallel()
    {
        Assert.assertEquals(
                Stacks.mutable.ofAll(LARGE_INTERVAL),
                this.bigData.parallelStream().collect(Collectors2.toImmutableStack()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toStack()),
                this.bigData.parallelStream().collect(Collectors2.toImmutableStack()));
    }

    @Test
    public void toMap()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toMap(Object::toString, i -> i),
                this.smallData.stream().collect(Collectors2.toMap(Object::toString, i -> i)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toMap(Object::toString, i -> i)),
                this.smallData.stream().collect(Collectors2.toMap(Object::toString, i -> i)));
        Assert.assertEquals(
                SMALL_INTERVAL.stream().collect(Collectors.toMap(Object::toString, i -> i)),
                SMALL_INTERVAL.reduceInPlace(Collectors2.toMap(Object::toString, i -> i)));
    }

    @Test
    public void toMapParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toMap(Object::toString, i -> i),
                this.bigData.parallelStream().collect(Collectors2.toMap(Object::toString, i -> i)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toMap(Object::toString, i -> i)),
                this.bigData.parallelStream().collect(Collectors2.toMap(Object::toString, i -> i)));
    }

    @Test
    public void toImmutableMap()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toMap(Object::toString, i -> i),
                this.smallData.stream().collect(Collectors2.toImmutableMap(Object::toString, i -> i)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toMap(Object::toString, i -> i)),
                this.smallData.stream().collect(Collectors2.toImmutableMap(Object::toString, i -> i)));
        Assert.assertEquals(
                SMALL_INTERVAL.stream().collect(Collectors.toMap(Object::toString, i -> i)),
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableMap(Object::toString, i -> i)));
    }

    @Test
    public void toImmutableMapParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toMap(Object::toString, i -> i),
                this.bigData.parallelStream().collect(Collectors2.toImmutableMap(Object::toString, i -> i)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toMap(Object::toString, i -> i)),
                this.bigData.parallelStream().collect(Collectors2.toImmutableMap(Object::toString, i -> i)));
    }

    @Test
    public void toBiMap()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.injectInto(BiMaps.mutable.empty(), (mbm, e) ->
                {
                    mbm.put(e.toString(), e);
                    return mbm;
                }),
                this.smallData.stream().collect(Collectors2.toBiMap(Object::toString, i -> i)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toBiMap(Object::toString, i -> i)),
                this.smallData.stream().collect(Collectors2.toBiMap(Object::toString, i -> i)));
    }

    @Test
    public void toBiMapParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.injectInto(BiMaps.mutable.empty(), (mbm, e) ->
                {
                    mbm.put(e.toString(), e);
                    return mbm;
                }),
                this.bigData.parallelStream().collect(Collectors2.toBiMap(Object::toString, i -> i)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toBiMap(Object::toString, i -> i)),
                this.bigData.parallelStream().collect(Collectors2.toBiMap(Object::toString, i -> i)));
    }

    @Test
    public void toImmutableBiMap()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.injectInto(BiMaps.mutable.empty(), (mbm, e) ->
                {
                    mbm.put(e.toString(), e);
                    return mbm;
                }),
                this.smallData.stream().collect(Collectors2.toImmutableBiMap(Object::toString, i -> i)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toBiMap(Object::toString, i -> i)),
                this.smallData.stream().collect(Collectors2.toImmutableBiMap(Object::toString, i -> i)));
    }

    @Test
    public void toImmutableBiMapParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.injectInto(BiMaps.mutable.empty(), (mbm, e) ->
                {
                    mbm.put(e.toString(), e);
                    return mbm;
                }),
                this.bigData.parallelStream().collect(Collectors2.toImmutableBiMap(Object::toString, i -> i)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toBiMap(Object::toString, i -> i)),
                this.bigData.parallelStream().collect(Collectors2.toImmutableBiMap(Object::toString, i -> i)));
    }

    @Test
    public void toSortedSet()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedSet(),
                this.smallData.stream().collect(Collectors2.toSortedSet()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedSet()),
                this.smallData.stream().collect(Collectors2.toSortedSet()));
    }

    @Test
    public void toSortedSetParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedSet(),
                this.bigData.parallelStream().collect(Collectors2.toSortedSet()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedSet()),
                this.bigData.parallelStream().collect(Collectors2.toSortedSet()));
    }

    @Test
    public void toSortedSetBy()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedSetBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toSortedSetBy(Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedSetBy(Object::toString)),
                this.smallData.stream().collect(Collectors2.toSortedSetBy(Object::toString)));
    }

    @Test
    public void toSortedSetByParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedSetBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toSortedSetBy(Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedSetBy(Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toSortedSetBy(Object::toString)));
    }

    @Test
    public void toImmutableSortedSet()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedSet(),
                this.smallData.stream().collect(Collectors2.toImmutableSortedSet()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedSet()),
                this.smallData.stream().collect(Collectors2.toImmutableSortedSet()));
    }

    @Test
    public void toImmutableSortedSetParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedSet(),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedSet()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedSet()),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedSet()));
    }

    @Test
    public void toSortedBag()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedBag(),
                this.smallData.stream().collect(Collectors2.toSortedBag()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedBag()),
                this.smallData.stream().collect(Collectors2.toSortedBag()));
    }

    @Test
    public void toSortedBagParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedBag(),
                this.bigData.parallelStream().collect(Collectors2.toSortedBag()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedBag()),
                this.bigData.parallelStream().collect(Collectors2.toSortedBag()));
    }

    @Test
    public void toSortedBagBy()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedBagBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toSortedBagBy(Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedBagBy(Object::toString)),
                this.smallData.stream().collect(Collectors2.toSortedBagBy(Object::toString)));
    }

    @Test
    public void toSortedBagByParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedBagBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toSortedBagBy(Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedBagBy(Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toSortedBagBy(Object::toString)));
    }

    @Test
    public void toImmutableSortedBag()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedBag(),
                this.smallData.stream().collect(Collectors2.toImmutableSortedBag()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedBag()),
                this.smallData.stream().collect(Collectors2.toImmutableSortedBag()));
    }

    @Test
    public void toImmutableSortedBagParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedBag(),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedBag()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedBag()),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedBag()));
    }

    @Test
    public void toSortedSetWithComparator()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedSet(Comparator.reverseOrder()),
                this.smallData.stream().collect(Collectors2.toSortedSet(Comparator.reverseOrder())));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedSet(Comparator.reverseOrder())),
                this.smallData.stream().collect(Collectors2.toSortedSet(Comparator.reverseOrder())));
    }

    @Test
    public void toSortedSetParallelWithComparator()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedSet(Comparator.reverseOrder()),
                this.bigData.parallelStream().collect(Collectors2.toSortedSet(Comparator.reverseOrder())));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedSet(Comparator.reverseOrder())),
                this.bigData.parallelStream().collect(Collectors2.toSortedSet(Comparator.reverseOrder())));
    }

    @Test
    public void toImmutableSortedSetWithComparator()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedSet(Comparator.reverseOrder()),
                this.smallData.stream().collect(Collectors2.toImmutableSortedSet(Comparator.reverseOrder())));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedSet(Comparator.reverseOrder())),
                this.smallData.stream().collect(Collectors2.toImmutableSortedSet(Comparator.reverseOrder())));
    }

    @Test
    public void toImmutableSortedSetParallelWithComparator()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedSet(Comparator.reverseOrder()),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedSet(Comparator.reverseOrder())));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedSet(Comparator.reverseOrder())),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedSet(Comparator.reverseOrder())));
    }

    @Test
    public void toSortedBagWithComparator()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedBag(Comparator.reverseOrder()),
                this.smallData.stream().collect(Collectors2.toSortedBag(Comparator.reverseOrder())));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedBag(Comparator.reverseOrder())),
                this.smallData.stream().collect(Collectors2.toSortedBag()));
    }

    @Test
    public void toSortedBagParallelWithComparator()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedBag(Comparator.reverseOrder()),
                this.bigData.parallelStream().collect(Collectors2.toSortedBag(Comparator.reverseOrder())));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedBag(Comparator.reverseOrder())),
                this.bigData.parallelStream().collect(Collectors2.toSortedBag(Comparator.reverseOrder())));
    }

    @Test
    public void toImmutableSortedBagWithComparator()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedBag(Comparator.reverseOrder()),
                this.smallData.stream().collect(Collectors2.toImmutableSortedBag(Comparator.reverseOrder())));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedBag(Comparator.reverseOrder())),
                this.smallData.stream().collect(Collectors2.toImmutableSortedBag(Comparator.reverseOrder())));
    }

    @Test
    public void toImmutableSortedBagParallelWithComparator()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedBag(Comparator.reverseOrder()),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedBag(Comparator.reverseOrder())));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedBag(Comparator.reverseOrder())),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedBag(Comparator.reverseOrder())));
    }

    @Test
    public void toSortedList()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedList(),
                this.smallData.stream().collect(Collectors2.toSortedList()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedList()),
                this.smallData.stream().collect(Collectors2.toSortedList()));
    }

    @Test
    public void toSortedListParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedList(),
                this.bigData.parallelStream().collect(Collectors2.toSortedList()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedList()),
                this.bigData.parallelStream().collect(Collectors2.toSortedList()));
    }

    @Test
    public void toSortedListBy()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedListBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toSortedListBy(Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedListBy(Object::toString)),
                this.smallData.stream().collect(Collectors2.toSortedListBy(Object::toString)));
    }

    @Test
    public void toSortedListByParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedListBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toSortedListBy(Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedListBy(Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toSortedListBy(Object::toString)));
    }

    @Test
    public void toImmutableSortedList()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedList(),
                this.smallData.stream().collect(Collectors2.toImmutableSortedList()));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedList()),
                this.smallData.stream().collect(Collectors2.toImmutableSortedList()));
    }

    @Test
    public void toImmutableSortedListParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedList(),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedList()));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedList()),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedList()));
    }

    @Test
    public void toSortedListWithComparator()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedList(Comparator.reverseOrder()),
                this.smallData.stream().collect(Collectors2.toSortedList(Comparator.reverseOrder())));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedList(Comparator.reverseOrder())),
                this.smallData.stream().collect(Collectors2.toSortedList(Comparator.reverseOrder())));
    }

    @Test
    public void toSortedListParallelWithComparator()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedList(Comparator.reverseOrder()),
                this.bigData.parallelStream().collect(Collectors2.toSortedList(Comparator.reverseOrder())));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedList(Comparator.reverseOrder())),
                this.bigData.parallelStream().collect(Collectors2.toSortedList(Comparator.reverseOrder())));
    }

    @Test
    public void toImmutableSortedListWithComparator()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSortedList(Comparator.reverseOrder()),
                this.smallData.stream().collect(Collectors2.toImmutableSortedList(Comparator.reverseOrder())));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedList(Comparator.reverseOrder())),
                this.smallData.stream().collect(Collectors2.toImmutableSortedList(Comparator.reverseOrder())));
    }

    @Test
    public void toImmutableSortedListParallelWithComparator()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSortedList(Comparator.reverseOrder()),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedList(Comparator.reverseOrder())));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedList(Comparator.reverseOrder())),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedList(Comparator.reverseOrder())));
    }

    @Test
    public void toImmutableListMultimap()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.groupBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toImmutableListMultimap(Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableListMultimap(Object::toString)),
                this.smallData.stream().collect(Collectors2.toImmutableListMultimap(Object::toString)));
    }

    @Test
    public void toImmutableListMultimapParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.groupBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toImmutableListMultimap(Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableListMultimap(Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toImmutableListMultimap(Object::toString)));
    }

    @Test
    public void toImmutableListMultimap2()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.collect(Object::toString).groupBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toImmutableListMultimap(Object::toString, Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableListMultimap(Object::toString, Object::toString)),
                this.smallData.stream().collect(Collectors2.toImmutableListMultimap(Object::toString, Object::toString)));
    }

    @Test
    public void toImmutableListMultimap2Parallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.collect(Object::toString).groupBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toImmutableListMultimap(Object::toString, Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableListMultimap(Object::toString, Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toImmutableListMultimap(Object::toString, Object::toString)));
    }

    @Test
    public void toImmutableSetMultimap()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSet().groupBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toImmutableSetMultimap(Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSetMultimap(Object::toString)),
                this.smallData.stream().collect(Collectors2.toImmutableSetMultimap(Object::toString)));
    }

    @Test
    public void toImmutableSetMultimapParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSet().groupBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSetMultimap(Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSetMultimap(Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSetMultimap(Object::toString)));
    }

    @Test
    public void toImmutableSetMultimap2()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toSet().collect(Object::toString).groupBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toImmutableSetMultimap(Object::toString, Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSetMultimap(Object::toString, Object::toString)),
                this.smallData.stream().collect(Collectors2.toImmutableSetMultimap(Object::toString, Object::toString)));
    }

    @Test
    public void toImmutableSetMultimap2Parallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toSet().collect(Object::toString).groupBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSetMultimap(Object::toString, Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSetMultimap(Object::toString, Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toImmutableSetMultimap(Object::toString, Object::toString)));
    }

    @Test
    public void toImmutableBagMultimap()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toBag().groupBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toImmutableBagMultimap(Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableBagMultimap(Object::toString)),
                this.smallData.stream().collect(Collectors2.toImmutableBagMultimap(Object::toString)));
    }

    @Test
    public void toImmutableBagMultimapParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toBag().groupBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toImmutableBagMultimap(Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableBagMultimap(Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toImmutableBagMultimap(Object::toString)));
    }

    @Test
    public void toImmutableBagMultimap2()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.toBag().collect(Object::toString).groupBy(Object::toString),
                this.smallData.stream().collect(Collectors2.toImmutableBagMultimap(Object::toString, Object::toString)));
        Assert.assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableBagMultimap(Object::toString, Object::toString)),
                this.smallData.stream().collect(Collectors2.toImmutableBagMultimap(Object::toString, Object::toString)));
    }

    @Test
    public void toImmutableBagMultimap2Parallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toBag().collect(Object::toString).groupBy(Object::toString),
                this.bigData.parallelStream().collect(Collectors2.toImmutableBagMultimap(Object::toString, Object::toString)));
        Assert.assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableBagMultimap(Object::toString, Object::toString)),
                this.bigData.parallelStream().collect(Collectors2.toImmutableBagMultimap(Object::toString, Object::toString)));
    }

    @Test
    public void chunk()
    {
        MutableList<MutableList<Integer>> chunked0 = this.bigData.stream().collect(Collectors2.chunk(100));
        Assert.assertEquals(LARGE_INTERVAL.toList().chunk(100), chunked0);
        MutableList<MutableList<Integer>> chunked1 = this.bigData.stream().collect(Collectors2.chunk(333));
        Assert.assertEquals(LARGE_INTERVAL.toList().chunk(333), chunked1);
        MutableList<MutableList<Integer>> chunked2 = this.bigData.stream().collect(Collectors2.chunk(654));
        Assert.assertEquals(LARGE_INTERVAL.toList().chunk(654), chunked2);
        MutableList<MutableList<Integer>> chunked3 = this.smallData.stream().collect(Collectors2.chunk(SMALL_INTERVAL.size()));
        Assert.assertEquals(SMALL_INTERVAL.toList().chunk(SMALL_INTERVAL.size()), chunked3);
        MutableList<MutableList<Integer>> chunked4 = this.smallData.stream().collect(Collectors2.chunk(SMALL_INTERVAL.size() - 1));
        Assert.assertEquals(SMALL_INTERVAL.toList().chunk(SMALL_INTERVAL.size() - 1), chunked4);
        Verify.assertThrows(IllegalArgumentException.class, () -> Collectors2.chunk(0));
        Verify.assertThrows(IllegalArgumentException.class, () -> Collectors2.chunk(-10));
    }

    @Test
    public void chunkParallel()
    {
        MutableList<MutableList<Integer>> chunked = this.bigData.parallelStream().collect(Collectors2.chunk(100));
        Assert.assertTrue(chunked.size() > 1);
        Verify.assertAllSatisfy(chunked, each -> each.size() > 1 && each.size() <= 100);
    }

    @Test
    public void zip()
    {
        MutableList<Integer> integers1 = Interval.oneTo(10).toList();
        MutableList<Integer> integers2 = Interval.oneTo(10).toList().toReversed();
        Assert.assertEquals(
                integers1.zip(integers2),
                integers1.stream().collect(Collectors2.zip(integers2)));
        MutableList<Integer> integers3 = Interval.oneTo(9).toList().toReversed();
        Assert.assertEquals(
                integers1.zip(integers3),
                integers1.stream().collect(Collectors2.zip(integers3)));
        Assert.assertEquals(
                integers3.zip(integers1),
                integers3.stream().collect(Collectors2.zip(integers1)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void zipParallel()
    {
        MutableList<Integer> integers1 = Interval.oneTo(10).toList();
        MutableList<Integer> integers2 = Interval.oneTo(10).toList().toReversed();
        Assert.assertEquals(
                integers1.zip(integers2),
                integers1.parallelStream().collect(Collectors2.zip(integers2)));
    }

    @Test
    public void zipWithIndex()
    {
        MutableList<Integer> integers1 = Interval.oneTo(10).toList();
        Assert.assertEquals(
                integers1.zipWithIndex().collect(each -> PrimitiveTuples.pair(each.getOne(), each.getTwo().intValue())),
                integers1.stream().collect(Collectors2.zipWithIndex()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void zipWithIndexParallel()
    {
        MutableList<Integer> integers1 = Interval.oneTo(10).toList();
        Assert.assertEquals(
                integers1.zipWithIndex().collect(each -> PrimitiveTuples.pair(each.getOne(), each.getTwo().intValue())),
                integers1.parallelStream().collect(Collectors2.zipWithIndex()));
    }

    @Test
    public void sumByInt()
    {
        Assert.assertEquals(
                SMALL_INTERVAL.sumByInt(each -> Integer.valueOf(each % 2), Integer::intValue),
                SMALL_INTERVAL.stream().collect(Collectors2.sumByInt(each -> Integer.valueOf(each % 2), Integer::intValue))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.sumByInt(each -> Integer.valueOf(each % 2), Integer::intValue),
                LARGE_INTERVAL.stream().collect(Collectors2.sumByInt(each -> Integer.valueOf(each % 2), Integer::intValue))
        );
    }

    @Test
    public void sumByIntParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.sumByInt(each -> Integer.valueOf(each % 2), Integer::intValue),
                LARGE_INTERVAL.parallelStream().collect(Collectors2.sumByInt(each -> Integer.valueOf(each % 2), Integer::intValue))
        );
    }

    @Test
    public void sumByLong()
    {
        MutableList<Long> smallLongs = SMALL_INTERVAL.collect(Long::valueOf).toList();
        MutableList<Long> largeLongs = LARGE_INTERVAL.collect(Long::valueOf).toList();
        Assert.assertEquals(
                smallLongs.sumByLong(each -> Long.valueOf(each % 2), Long::longValue),
                smallLongs.stream().collect(Collectors2.sumByLong(each -> Long.valueOf(each % 2), Long::longValue))
        );
        Assert.assertEquals(
                largeLongs.sumByLong(each -> Long.valueOf(each % 2), Long::longValue),
                largeLongs.stream().collect(Collectors2.sumByLong(each -> Long.valueOf(each % 2), Long::longValue))
        );
    }

    @Test
    public void sumByLongParallel()
    {
        MutableList<Long> largeLongs = LARGE_INTERVAL.collect(Long::valueOf).toList();
        Assert.assertEquals(
                largeLongs.sumByLong(each -> Long.valueOf(each % 2), Long::longValue),
                largeLongs.parallelStream().collect(Collectors2.sumByLong(each -> Long.valueOf(each % 2), Long::longValue))
        );
    }

    @Test
    public void sumByFloat()
    {
        MutableList<Float> smallLongs = SMALL_INTERVAL.collect(Float::valueOf).toList();
        MutableList<Float> largeLongs = LARGE_INTERVAL.collect(Float::valueOf).toList();
        Assert.assertEquals(
                smallLongs.sumByFloat(each -> Float.valueOf(each % 2), Float::floatValue),
                smallLongs.stream().collect(Collectors2.sumByFloat(each -> Float.valueOf(each % 2), Float::floatValue))
        );
        Assert.assertEquals(
                largeLongs.sumByFloat(each -> Float.valueOf(each % 2), Float::floatValue),
                largeLongs.stream().collect(Collectors2.sumByFloat(each -> Float.valueOf(each % 2), Float::floatValue))
        );
    }

    @Test
    public void sumByFloatParallel()
    {
        MutableList<Float> largeLongs = LARGE_INTERVAL.collect(Float::valueOf).toList();
        Assert.assertEquals(
                largeLongs.sumByFloat(each -> Float.valueOf(each % 2), Float::floatValue),
                largeLongs.parallelStream().collect(Collectors2.sumByFloat(each -> Float.valueOf(each % 2), Float::floatValue))
        );
    }

    @Test
    public void sumByDouble()
    {
        MutableList<Double> smallLongs = SMALL_INTERVAL.collect(Double::valueOf).toList();
        MutableList<Double> largeLongs = LARGE_INTERVAL.collect(Double::valueOf).toList();
        Assert.assertEquals(
                smallLongs.sumByDouble(each -> Double.valueOf(each % 2), Double::doubleValue),
                smallLongs.stream().collect(Collectors2.sumByDouble(each -> Double.valueOf(each % 2), Double::doubleValue))
        );
        Assert.assertEquals(
                largeLongs.sumByDouble(each -> Double.valueOf(each % 2), Double::doubleValue),
                largeLongs.stream().collect(Collectors2.sumByDouble(each -> Double.valueOf(each % 2), Double::doubleValue))
        );
    }

    @Test
    public void sumByDoubleParallel()
    {
        MutableList<Double> largeLongs = LARGE_INTERVAL.collect(Double::valueOf).toList();
        Assert.assertEquals(
                largeLongs.sumByDouble(each -> Double.valueOf(each % 2), Double::doubleValue),
                largeLongs.parallelStream().collect(Collectors2.sumByDouble(each -> Double.valueOf(each % 2), Double::doubleValue))
        );
    }

    @Test
    public void select()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toList().select(IntegerPredicates.isEven()),
                this.bigData.stream().collect(Collectors2.select(IntegerPredicates.isEven(), Lists.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toSet().select(IntegerPredicates.isEven()),
                this.bigData.stream().collect(Collectors2.select(IntegerPredicates.isEven(), Sets.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toBag().select(IntegerPredicates.isEven()),
                this.bigData.stream().collect(Collectors2.select(IntegerPredicates.isEven(), Bags.mutable::empty))
        );
    }

    @Test
    public void selectParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toList().select(IntegerPredicates.isEven()),
                this.bigData.parallelStream().collect(Collectors2.select(IntegerPredicates.isEven(), Lists.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toSet().select(IntegerPredicates.isEven()),
                this.bigData.parallelStream().collect(Collectors2.select(IntegerPredicates.isEven(), Sets.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toBag().select(IntegerPredicates.isEven()),
                this.bigData.parallelStream().collect(Collectors2.select(IntegerPredicates.isEven(), Bags.mutable::empty))
        );
    }

    @Test
    public void selectWith()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toList().selectWith(Predicates2.greaterThan(), HALF_SIZE),
                this.bigData.stream()
                        .collect(Collectors2.selectWith(Predicates2.greaterThan(), HALF_SIZE, Lists.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toSet().selectWith(Predicates2.greaterThan(), HALF_SIZE),
                this.bigData.stream()
                        .collect(Collectors2.selectWith(Predicates2.greaterThan(), HALF_SIZE, Sets.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toBag().selectWith(Predicates2.greaterThan(), HALF_SIZE),
                this.bigData.stream()
                        .collect(Collectors2.selectWith(Predicates2.greaterThan(), HALF_SIZE, Bags.mutable::empty))
        );
    }

    @Test
    public void selectWithParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toList().selectWith(Predicates2.greaterThan(), HALF_SIZE),
                this.bigData.parallelStream()
                        .collect(Collectors2.selectWith(Predicates2.greaterThan(), HALF_SIZE, Lists.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toSet().selectWith(Predicates2.greaterThan(), HALF_SIZE),
                this.bigData.parallelStream()
                        .collect(Collectors2.selectWith(Predicates2.greaterThan(), HALF_SIZE, Sets.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toBag().selectWith(Predicates2.greaterThan(), HALF_SIZE),
                this.bigData.parallelStream()
                        .collect(Collectors2.selectWith(Predicates2.greaterThan(), HALF_SIZE, Bags.mutable::empty))
        );
    }

    @Test
    public void reject()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toList().reject(IntegerPredicates.isEven()),
                this.bigData.stream().collect(Collectors2.reject(IntegerPredicates.isEven(), Lists.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toSet().reject(IntegerPredicates.isEven()),
                this.bigData.stream().collect(Collectors2.reject(IntegerPredicates.isEven(), Sets.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toBag().reject(IntegerPredicates.isEven()),
                this.bigData.stream().collect(Collectors2.reject(IntegerPredicates.isEven(), Bags.mutable::empty))
        );
    }

    @Test
    public void rejectParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toList().reject(IntegerPredicates.isEven()),
                this.bigData.parallelStream().collect(Collectors2.reject(IntegerPredicates.isEven(), Lists.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toSet().reject(IntegerPredicates.isEven()),
                this.bigData.parallelStream().collect(Collectors2.reject(IntegerPredicates.isEven(), Sets.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toBag().reject(IntegerPredicates.isEven()),
                this.bigData.parallelStream().collect(Collectors2.reject(IntegerPredicates.isEven(), Bags.mutable::empty))
        );
    }

    @Test
    public void rejectWith()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toList().rejectWith(Predicates2.greaterThan(), HALF_SIZE),
                this.bigData.stream()
                        .collect(Collectors2.rejectWith(Predicates2.greaterThan(), HALF_SIZE, Lists.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toSet().rejectWith(Predicates2.greaterThan(), HALF_SIZE),
                this.bigData.stream()
                        .collect(Collectors2.rejectWith(Predicates2.greaterThan(), HALF_SIZE, Sets.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toBag().rejectWith(Predicates2.greaterThan(), HALF_SIZE),
                this.bigData.stream()
                        .collect(Collectors2.rejectWith(Predicates2.greaterThan(), HALF_SIZE, Bags.mutable::empty))
        );
    }

    @Test
    public void rejectWithParallel()
    {
        Assert.assertEquals(
                LARGE_INTERVAL.toList().rejectWith(Predicates2.greaterThan(), HALF_SIZE),
                this.bigData.parallelStream()
                        .collect(Collectors2.rejectWith(Predicates2.greaterThan(), HALF_SIZE, Lists.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toSet().rejectWith(Predicates2.greaterThan(), HALF_SIZE),
                this.bigData.parallelStream()
                        .collect(Collectors2.rejectWith(Predicates2.greaterThan(), HALF_SIZE, Sets.mutable::empty))
        );
        Assert.assertEquals(
                LARGE_INTERVAL.toBag().rejectWith(Predicates2.greaterThan(), HALF_SIZE),
                this.bigData.parallelStream()
                        .collect(Collectors2.rejectWith(Predicates2.greaterThan(), HALF_SIZE, Bags.mutable::empty))
        );
    }

    @Test
    public void partition()
    {
        PartitionMutableList<Integer> expectedList = LARGE_INTERVAL.toList().partition(IntegerPredicates.isEven());
        PartitionMutableList<Integer> actualList = this.bigData.stream()
                .collect(Collectors2.partition(IntegerPredicates.isEven(), PartitionFastList::new));
        Assert.assertEquals(expectedList.getSelected(), actualList.getSelected());
        Assert.assertEquals(expectedList.getRejected(), actualList.getRejected());
        PartitionMutableSet<Integer> expectedSet = LARGE_INTERVAL.toSet().partition(IntegerPredicates.isEven());
        PartitionMutableSet<Integer> actualSet = this.bigData.stream()
                .collect(Collectors2.partition(IntegerPredicates.isEven(), PartitionUnifiedSet::new));
        Assert.assertEquals(expectedSet.getSelected(), actualSet.getSelected());
        Assert.assertEquals(expectedSet.getRejected(), actualSet.getRejected());
        PartitionMutableBag<Integer> expectedBag = LARGE_INTERVAL.toBag().partition(IntegerPredicates.isEven());
        PartitionMutableBag<Integer> actualBag = this.bigData.stream()
                .collect(Collectors2.partition(IntegerPredicates.isEven(), PartitionHashBag::new));
        Assert.assertEquals(expectedBag.getSelected(), actualBag.getSelected());
        Assert.assertEquals(expectedBag.getRejected(), actualBag.getRejected());
    }

    @Test
    public void partitionParallel()
    {
        PartitionMutableList<Integer> expectedList = LARGE_INTERVAL.toList().partition(IntegerPredicates.isEven());
        PartitionMutableList<Integer> actualList = this.bigData.parallelStream()
                .collect(Collectors2.partition(IntegerPredicates.isEven(), PartitionFastList::new));
        Assert.assertEquals(expectedList.getSelected(), actualList.getSelected());
        Assert.assertEquals(expectedList.getRejected(), actualList.getRejected());
        PartitionMutableSet<Integer> expectedSet = LARGE_INTERVAL.toSet().partition(IntegerPredicates.isEven());
        PartitionMutableSet<Integer> actualSet = this.bigData.parallelStream()
                .collect(Collectors2.partition(IntegerPredicates.isEven(), PartitionUnifiedSet::new));
        Assert.assertEquals(expectedSet.getSelected(), actualSet.getSelected());
        Assert.assertEquals(expectedSet.getRejected(), actualSet.getRejected());
        PartitionMutableBag<Integer> expectedBag = LARGE_INTERVAL.toBag().partition(IntegerPredicates.isEven());
        PartitionMutableBag<Integer> actualBag = this.bigData.parallelStream()
                .collect(Collectors2.partition(IntegerPredicates.isEven(), PartitionHashBag::new));
        Assert.assertEquals(expectedBag.getSelected(), actualBag.getSelected());
        Assert.assertEquals(expectedBag.getRejected(), actualBag.getRejected());
    }

    @Test
    public void partitionWith()
    {
        PartitionMutableList<Integer> expectedList = LARGE_INTERVAL.toList().partitionWith(Predicates2.greaterThan(), HALF_SIZE);
        PartitionMutableList<Integer> actualList = this.bigData.stream()
                .collect(Collectors2.partitionWith(Predicates2.greaterThan(), HALF_SIZE, PartitionFastList::new));
        Assert.assertEquals(expectedList.getSelected(), actualList.getSelected());
        Assert.assertEquals(expectedList.getRejected(), actualList.getRejected());
        PartitionMutableSet<Integer> expectedSet = LARGE_INTERVAL.toSet().partitionWith(Predicates2.greaterThan(), HALF_SIZE);
        PartitionMutableSet<Integer> actualSet = this.bigData.stream()
                .collect(Collectors2.partitionWith(Predicates2.greaterThan(), HALF_SIZE, PartitionUnifiedSet::new));
        Assert.assertEquals(expectedSet.getSelected(), actualSet.getSelected());
        Assert.assertEquals(expectedSet.getRejected(), actualSet.getRejected());
        PartitionMutableBag<Integer> expectedBag = LARGE_INTERVAL.toBag().partitionWith(Predicates2.greaterThan(), HALF_SIZE);
        PartitionMutableBag<Integer> actualBag = this.bigData.stream()
                .collect(Collectors2.partitionWith(Predicates2.greaterThan(), HALF_SIZE, PartitionHashBag::new));
        Assert.assertEquals(expectedBag.getSelected(), actualBag.getSelected());
        Assert.assertEquals(expectedBag.getRejected(), actualBag.getRejected());
    }

    @Test
    public void partitionWithParallel()
    {
        PartitionMutableList<Integer> expectedList = LARGE_INTERVAL.toList().partitionWith(Predicates2.greaterThan(), HALF_SIZE);
        PartitionMutableList<Integer> actualList = this.bigData.parallelStream()
                .collect(Collectors2.partitionWith(Predicates2.greaterThan(), HALF_SIZE, PartitionFastList::new));
        Assert.assertEquals(expectedList.getSelected(), actualList.getSelected());
        Assert.assertEquals(expectedList.getRejected(), actualList.getRejected());
        PartitionMutableSet<Integer> expectedSet = LARGE_INTERVAL.toSet().partitionWith(Predicates2.greaterThan(), HALF_SIZE);
        PartitionMutableSet<Integer> actualSet = this.bigData.parallelStream()
                .collect(Collectors2.partitionWith(Predicates2.greaterThan(), HALF_SIZE, PartitionUnifiedSet::new));
        Assert.assertEquals(expectedSet.getSelected(), actualSet.getSelected());
        Assert.assertEquals(expectedSet.getRejected(), actualSet.getRejected());
        PartitionMutableBag<Integer> expectedBag = LARGE_INTERVAL.toBag().partitionWith(Predicates2.greaterThan(), HALF_SIZE);
        PartitionMutableBag<Integer> actualBag = this.bigData.parallelStream()
                .collect(Collectors2.partitionWith(Predicates2.greaterThan(), HALF_SIZE, PartitionHashBag::new));
        Assert.assertEquals(expectedBag.getSelected(), actualBag.getSelected());
        Assert.assertEquals(expectedBag.getRejected(), actualBag.getRejected());
    }
}
