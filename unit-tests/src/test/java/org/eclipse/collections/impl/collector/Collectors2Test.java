/*
 * Copyright (c) 2024 Goldman Sachs and others.
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
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.map.sorted.ImmutableSortedMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.partition.PartitionMutableCollection;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.stack.ImmutableStack;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.impl.factory.BiMaps;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.factory.SortedMaps;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.impl.factory.primitive.IntBags;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.partition.bag.PartitionHashBag;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class Collectors2Test
{
    public static final Interval SMALL_INTERVAL = Interval.oneTo(5);
    public static final Interval LARGE_INTERVAL = Interval.oneTo(20000);
    public static final Integer HALF_SIZE = Integer.valueOf(LARGE_INTERVAL.size() / 2);
    private final List<Integer> smallData = new ArrayList<>(SMALL_INTERVAL);
    private final List<Integer> bigData = new ArrayList<>(LARGE_INTERVAL);

    @Test
    public void makeString0()
    {
        assertEquals(
                SMALL_INTERVAL.makeString(),
                this.smallData.stream().collect(Collectors2.makeString()));
        assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.makeString()),
                this.smallData.stream().collect(Collectors2.makeString()));
        assertEquals(
                LARGE_INTERVAL.makeString(),
                this.bigData.stream().collect(Collectors2.makeString()));
        assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.makeString()),
                this.bigData.stream().collect(Collectors2.makeString()));
    }

    @Test
    public void makeString0Parallel()
    {
        assertEquals(
                SMALL_INTERVAL.makeString(),
                this.smallData.parallelStream().collect(Collectors2.makeString()));
        assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.makeString()),
                this.smallData.parallelStream().collect(Collectors2.makeString()));
        assertEquals(
                LARGE_INTERVAL.makeString(),
                this.bigData.parallelStream().collect(Collectors2.makeString()));
        assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.makeString()),
                this.bigData.parallelStream().collect(Collectors2.makeString()));
    }

    @Test
    public void makeString1()
    {
        assertEquals(
                SMALL_INTERVAL.makeString("/"),
                this.smallData.stream().collect(Collectors2.makeString("/")));
        assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.makeString("/")),
                this.smallData.stream().collect(Collectors2.makeString("/")));
        assertEquals(
                LARGE_INTERVAL.makeString("/"),
                this.bigData.stream().collect(Collectors2.makeString("/")));
        assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.makeString("/")),
                this.bigData.stream().collect(Collectors2.makeString("/")));
    }

    @Test
    public void makeString1Parallel()
    {
        assertEquals(
                SMALL_INTERVAL.makeString("/"),
                this.smallData.parallelStream().collect(Collectors2.makeString("/")));
        assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.makeString("/")),
                this.smallData.parallelStream().collect(Collectors2.makeString("/")));
        assertEquals(
                LARGE_INTERVAL.makeString("/"),
                this.bigData.parallelStream().collect(Collectors2.makeString("/")));
        assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.makeString("/")),
                this.bigData.parallelStream().collect(Collectors2.makeString("/")));
    }

    @Test
    public void makeString3()
    {
        assertEquals(
                SMALL_INTERVAL.makeString("[", "/", "]"),
                this.smallData.stream().collect(Collectors2.makeString("[", "/", "]")));
        assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.makeString("[", "/", "]")),
                this.smallData.stream().collect(Collectors2.makeString("[", "/", "]")));
        assertEquals(
                LARGE_INTERVAL.makeString("[", "/", "]"),
                this.bigData.stream().collect(Collectors2.makeString("[", "/", "]")));
        assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.makeString("[", "/", "]")),
                this.bigData.stream().collect(Collectors2.makeString("[", "/", "]")));
    }

    @Test
    public void makeString3Parallel()
    {
        assertEquals(
                SMALL_INTERVAL.makeString("[", "/", "]"),
                this.smallData.parallelStream().collect(Collectors2.makeString("[", "/", "]")));
        assertEquals(
                SMALL_INTERVAL.reduceInPlace(Collectors2.makeString("[", "/", "]")),
                this.smallData.parallelStream().collect(Collectors2.makeString("[", "/", "]")));
        assertEquals(
                LARGE_INTERVAL.makeString("[", "/", "]"),
                this.bigData.parallelStream().collect(Collectors2.makeString("[", "/", "]")));
        assertEquals(
                LARGE_INTERVAL.reduceInPlace(Collectors2.makeString("[", "/", "]")),
                this.bigData.parallelStream().collect(Collectors2.makeString("[", "/", "]")));
    }

    @Test
    public void toList()
    {
        MutableList<Integer> expected = SMALL_INTERVAL.toList();
        MutableList<Integer> actual = this.smallData.stream().collect(Collectors2.toList());
        assertEquals(expected, actual);
        MutableList<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toList());
        assertEquals(expected, actual2);
    }

    @Test
    public void toListParallel()
    {
        MutableList<Integer> expected = LARGE_INTERVAL.toList();
        MutableList<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toList());
        assertEquals(expected, actual);
        MutableList<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toList());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableList()
    {
        MutableList<Integer> expected = SMALL_INTERVAL.toList();
        ImmutableList<Integer> actual = this.smallData.stream().collect(Collectors2.toImmutableList());
        assertEquals(expected, actual);
        ImmutableList<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableList());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableListParallel()
    {
        MutableList<Integer> expected = LARGE_INTERVAL.toList();
        ImmutableList<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toImmutableList());
        assertEquals(expected, actual);
        ImmutableList<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableList());
        assertEquals(expected, actual2);
    }

    @Test
    public void toSet()
    {
        MutableSet<Integer> expected = SMALL_INTERVAL.toSet();
        MutableSet<Integer> actual = this.smallData.stream().collect(Collectors2.toSet());
        assertEquals(expected, actual);
        MutableSet<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toSet());
        assertEquals(expected, actual2);
    }

    @Test
    public void toSetParallel()
    {
        MutableSet<Integer> expected = LARGE_INTERVAL.toSet();
        MutableSet<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toSet());
        assertEquals(expected, actual);
        MutableSet<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toSet());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSet()
    {
        MutableSet<Integer> expected = SMALL_INTERVAL.toSet();
        ImmutableSet<Integer> actual = this.smallData.stream().collect(Collectors2.toImmutableSet());
        assertEquals(expected, actual);
        ImmutableSet<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSet());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSetParallel()
    {
        MutableSet<Integer> expected = LARGE_INTERVAL.toSet();
        ImmutableSet<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toImmutableSet());
        assertEquals(expected, actual);
        ImmutableSet<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSet());
        assertEquals(expected, actual2);
    }

    @Test
    public void toBag()
    {
        MutableBag<Integer> expected = SMALL_INTERVAL.toBag();
        MutableBag<Integer> actual = this.smallData.stream().collect(Collectors2.toBag());
        assertEquals(expected, actual);
        MutableBag<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toBag());
        assertEquals(expected, actual2);
    }

    @Test
    public void toBagParallel()
    {
        MutableBag<Integer> expected = LARGE_INTERVAL.toBag();
        MutableBag<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toBag());
        assertEquals(expected, actual);
        MutableBag<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toBag());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableBag()
    {
        MutableBag<Integer> expected = SMALL_INTERVAL.toBag();
        ImmutableBag<Integer> actual = this.smallData.stream().collect(Collectors2.toImmutableBag());
        assertEquals(expected, actual);
        ImmutableBag<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableBag());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableBagParallel()
    {
        MutableBag<Integer> expected = LARGE_INTERVAL.toBag();
        ImmutableBag<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toImmutableBag());
        assertEquals(expected, actual);
        ImmutableBag<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableBag());
        assertEquals(expected, actual2);
    }

    @Test
    public void toListMultimap()
    {
        Multimap<String, Integer> expected = SMALL_INTERVAL.groupBy(Object::toString);
        MutableListMultimap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toListMultimap(Object::toString));
        assertEquals(expected, actual);
        MutableListMultimap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toListMultimap(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toListMultimapParallel()
    {
        Multimap<String, Integer> expected = LARGE_INTERVAL.groupBy(Object::toString);
        MutableListMultimap<String, Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toListMultimap(Object::toString));
        assertEquals(expected, actual);
        MutableListMultimap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toListMultimap(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toListMultimap2()
    {
        Multimap<String, String> expected = SMALL_INTERVAL.collect(Object::toString).groupBy(Object::toString);
        MutableListMultimap<String, String> actual =
                this.smallData.stream().collect(Collectors2.toListMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual);
        MutableListMultimap<String, String> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toListMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toListMultimap2Parallel()
    {
        Multimap<String, String> expected = LARGE_INTERVAL.collect(Object::toString).groupBy(Object::toString);
        MutableListMultimap<String, String> actual =
                this.bigData.parallelStream().collect(Collectors2.toListMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual);
        MutableListMultimap<String, String> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toListMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSetMultimap()
    {
        MutableSetMultimap<String, Integer> expected = SMALL_INTERVAL.toSet().groupBy(Object::toString);
        MutableSetMultimap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toSetMultimap(Object::toString));
        assertEquals(expected, actual);
        MutableSetMultimap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSetMultimap(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSetMultimapParallel()
    {
        MutableSetMultimap<String, Integer> expected = LARGE_INTERVAL.toSet().groupBy(Object::toString);
        MutableSetMultimap<String, Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toSetMultimap(Object::toString));
        assertEquals(expected, actual);
        MutableSetMultimap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSetMultimap(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSetMultimap2()
    {
        MutableSetMultimap<String, String> expected =
                SMALL_INTERVAL.toSet().collect(Object::toString).groupBy(Object::toString);
        MutableSetMultimap<String, String> actual =
                this.smallData.stream().collect(Collectors2.toSetMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual);
        MutableSetMultimap<String, String> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSetMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSetMultimap2Parallel()
    {
        MutableSetMultimap<String, String> expected =
                LARGE_INTERVAL.toSet().collect(Object::toString).groupBy(Object::toString);
        MutableSetMultimap<String, String> actual =
                this.bigData.parallelStream().collect(Collectors2.toSetMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual);
        MutableSetMultimap<String, String> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSetMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toBagMultimap()
    {
        MutableBagMultimap<String, Integer> expected = SMALL_INTERVAL.toBag().groupBy(Object::toString);
        MutableBagMultimap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toBagMultimap(Object::toString));
        assertEquals(expected, actual);
        MutableBagMultimap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toBagMultimap(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toBagMultimapParallel()
    {
        MutableBagMultimap<String, Integer> expected = LARGE_INTERVAL.toBag().groupBy(Object::toString);
        MutableBagMultimap<String, Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toBagMultimap(Object::toString));
        assertEquals(expected, actual);
        MutableBagMultimap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toBagMultimap(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toBagMultimap2()
    {
        MutableBagMultimap<String, String> expected =
                SMALL_INTERVAL.toBag().collect(Object::toString).groupBy(Object::toString);
        MutableBagMultimap<String, String> actual =
                this.smallData.stream().collect(Collectors2.toBagMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual);
        MutableBagMultimap<String, String> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toBagMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toBagMultimap2Parallel()
    {
        MutableBagMultimap<String, String> expected =
                LARGE_INTERVAL.toBag().collect(Object::toString).groupBy(Object::toString);
        MutableBagMultimap<String, String> actual =
                this.bigData.parallelStream().collect(Collectors2.toBagMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual);
        MutableBagMultimap<String, String> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toBagMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void groupingByToBagMultimap()
    {
        Map<Integer, MutableBagMultimap<Integer, Integer>> expected = Interval.oneTo(100).stream().collect(
                Collectors.groupingBy(
                        each -> each % 2,
                        Collectors2.toBagMultimap(each -> each % 5)));
        Map<Integer, MutableBagMultimap<Integer, Integer>> actual = Interval.oneTo(100).reduceInPlace(
                Collectors.groupingBy(
                        each -> each % 2,
                        Collectors2.toBagMultimap(each -> each % 5)));
        assertEquals(expected, actual);
    }

    @Test
    public void groupingByPartition()
    {
        Map<Integer, PartitionMutableCollection<Integer>> expected = Interval.oneTo(100).stream().collect(
                Collectors.groupingBy(
                        each -> each % 2,
                        Collectors2.partition(each -> each % 5 == 0, PartitionHashBag::new)));
        Map<Integer, PartitionMutableCollection<Integer>> actual = Interval.oneTo(100).reduceInPlace(
                Collectors.groupingBy(
                        each -> each % 2,
                        Collectors2.partition(each -> each % 5 == 0, PartitionHashBag::new)));
        assertEquals(expected.get(0).getSelected(), actual.get(0).getSelected());
        assertEquals(expected.get(0).getRejected(), actual.get(0).getRejected());
    }

    @Test
    public void groupingByChunk()
    {
        Map<Integer, MutableList<MutableList<Integer>>> expected = Interval.oneTo(100).stream().collect(
                Collectors.groupingBy(each -> each % 2, Collectors2.chunk(10)));
        Map<Integer, MutableList<MutableList<Integer>>> actual = Interval.oneTo(100).reduceInPlace(
                Collectors.groupingBy(each -> each % 2, Collectors2.chunk(10)));
        assertEquals(expected, actual);
    }

    @Test
    public void groupingByCollectInt()
    {
        Map<Integer, MutableIntCollection> expected = Interval.oneTo(100).stream().collect(
                Collectors.groupingBy(each -> each % 2, Collectors2.collectInt(Integer::intValue, IntBags.mutable::empty)));
        Map<Integer, MutableIntCollection> actual = Interval.oneTo(100).reduceInPlace(
                Collectors.groupingBy(each -> each % 2, Collectors2.collectInt(Integer::intValue, IntBags.mutable::empty)));
        assertEquals(expected, actual);
    }

    @Test
    public void groupingBySumByInt()
    {
        Map<Integer, MutableObjectLongMap<Integer>> expected = Interval.oneTo(100).stream().collect(
                Collectors.groupingBy(each -> each % 2, Collectors2.sumByInt(each -> each % 5, Integer::intValue)));
        Map<Integer, MutableObjectLongMap<Integer>> actual = Interval.oneTo(100).reduceInPlace(
                Collectors.groupingBy(each -> each % 2, Collectors2.sumByInt(each -> each % 5, Integer::intValue)));
        assertEquals(expected, actual);
    }

    @Test
    public void toStack()
    {
        MutableStack<Integer> expected = Stacks.mutable.ofAll(SMALL_INTERVAL);
        MutableStack<Integer> actual = this.smallData.stream().collect(Collectors2.toStack());
        assertEquals(expected, actual);
        MutableStack<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toStack());
        assertEquals(expected, actual2);
    }

    @Test
    public void toStackParallel()
    {
        MutableStack<Integer> expected = Stacks.mutable.ofAll(LARGE_INTERVAL);
        MutableStack<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toStack());
        assertEquals(expected, actual);
        MutableStack<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toStack());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableStack()
    {
        MutableStack<Integer> expected = Stacks.mutable.ofAll(SMALL_INTERVAL);
        ImmutableStack<Integer> actual = this.smallData.stream().collect(Collectors2.toImmutableStack());
        assertEquals(expected, actual);
        ImmutableStack<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableStack());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableStackParallel()
    {
        MutableStack<Integer> expected = Stacks.mutable.ofAll(LARGE_INTERVAL);
        ImmutableStack<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toImmutableStack());
        assertEquals(expected, actual);
        ImmutableStack<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableStack());
        assertEquals(expected, actual2);
    }

    @Test
    public void toMap()
    {
        MutableMap<String, Integer> expected = SMALL_INTERVAL.toMap(Object::toString, i -> i);
        MutableMap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toMap(Object::toString, i -> i));
        assertEquals(expected, actual);
        MutableMap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toMap(Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toMapParallel()
    {
        MutableMap<String, Integer> expected = LARGE_INTERVAL.toMap(Object::toString, i -> i);
        MutableMap<String, Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toMap(Object::toString, i -> i));
        assertEquals(expected, actual);
        MutableMap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toMap(Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableMap()
    {
        MutableMap<String, Integer> expected = SMALL_INTERVAL.toMap(Object::toString, i -> i);
        ImmutableMap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toImmutableMap(Object::toString, i -> i));
        assertEquals(expected, actual);

        ImmutableMap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableMap(Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableMapParallel()
    {
        MutableMap<String, Integer> expected = LARGE_INTERVAL.toMap(Object::toString, i -> i);
        ImmutableMap<String, Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toImmutableMap(Object::toString, i -> i));
        assertEquals(expected, actual);
        ImmutableMap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableMap(Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toBiMap()
    {
        MutableBiMap<Object, Object> expected = SMALL_INTERVAL.injectInto(BiMaps.mutable.empty(), (mbm, e) ->
        {
            mbm.put(e.toString(), e);
            return mbm;
        });
        MutableBiMap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toBiMap(Object::toString, i -> i));
        assertEquals(expected, actual);
        MutableBiMap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toBiMap(Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toBiMapParallel()
    {
        MutableBiMap<Object, Object> expected = LARGE_INTERVAL.injectInto(BiMaps.mutable.empty(), (mbm, e) ->
        {
            mbm.put(e.toString(), e);
            return mbm;
        });
        MutableBiMap<String, Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toBiMap(Object::toString, i -> i));
        assertEquals(expected, actual);
        MutableBiMap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toBiMap(Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableBiMap()
    {
        MutableBiMap<Object, Object> expected = SMALL_INTERVAL.injectInto(BiMaps.mutable.empty(), (mbm, e) ->
        {
            mbm.put(e.toString(), e);
            return mbm;
        });
        ImmutableBiMap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toImmutableBiMap(Object::toString, i -> i));
        assertEquals(expected, actual);
        ImmutableBiMap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableBiMap(Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableBiMapParallel()
    {
        MutableBiMap<Object, Object> expected = LARGE_INTERVAL.injectInto(BiMaps.mutable.empty(), (mbm, e) ->
        {
            mbm.put(e.toString(), e);
            return mbm;
        });
        ImmutableBiMap<String, Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toImmutableBiMap(Object::toString, i -> i));
        assertEquals(expected, actual);
        ImmutableBiMap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableBiMap(Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedSet()
    {
        MutableSortedSet<Integer> expected = SMALL_INTERVAL.toSortedSet();
        MutableSortedSet<Integer> actual = this.smallData.stream().collect(Collectors2.toSortedSet());
        assertEquals(expected, actual);
        MutableSortedSet<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedSet());
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedSetParallel()
    {
        MutableSortedSet<Integer> expected = LARGE_INTERVAL.toSortedSet();
        MutableSortedSet<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toSortedSet());
        assertEquals(expected, actual);
        MutableSortedSet<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedSet());
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedSetBy()
    {
        MutableSortedSet<Integer> expected = SMALL_INTERVAL.toSortedSetBy(Object::toString);
        MutableSortedSet<Integer> actual = this.smallData.stream().collect(Collectors2.toSortedSetBy(Object::toString));
        assertEquals(expected, actual);
        MutableSortedSet<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedSetBy(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedSetByParallel()
    {
        MutableSortedSet<Integer> expected = LARGE_INTERVAL.toSortedSetBy(Object::toString);
        MutableSortedSet<Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toSortedSetBy(Object::toString));
        assertEquals(expected, actual);
        MutableSortedSet<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedSetBy(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedSet()
    {
        MutableSortedSet<Integer> expected = SMALL_INTERVAL.toSortedSet();
        ImmutableSortedSet<Integer> actual = this.smallData.stream().collect(Collectors2.toImmutableSortedSet());
        assertEquals(expected, actual);
        ImmutableSortedSet<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedSet());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedSetParallel()
    {
        MutableSortedSet<Integer> expected = LARGE_INTERVAL.toSortedSet();
        ImmutableSortedSet<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toImmutableSortedSet());
        assertEquals(expected, actual);
        ImmutableSortedSet<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedSet());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedSetBy()
    {
        MutableSortedSet<Integer> expected = SMALL_INTERVAL.toSortedSetBy(Object::toString);
        ImmutableSortedSet<Integer> actual = this.smallData.stream().collect(Collectors2.toImmutableSortedSetBy(Object::toString));
        assertEquals(expected, actual);
        ImmutableSortedSet<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedSetBy(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedSetByParallel()
    {
        MutableSortedSet<Integer> expected = LARGE_INTERVAL.toSortedSetBy(Object::toString);
        ImmutableSortedSet<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toImmutableSortedSetBy(Object::toString));
        assertEquals(expected, actual);
        ImmutableSortedSet<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedSetBy(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedBag()
    {
        MutableSortedBag<Integer> expected = SMALL_INTERVAL.toSortedBag();
        MutableSortedBag<Integer> actual = this.smallData.stream().collect(Collectors2.toSortedBag());
        assertEquals(expected, actual);
        MutableSortedBag<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedBag());
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedBagParallel()
    {
        MutableSortedBag<Integer> expected = LARGE_INTERVAL.toSortedBag();
        MutableSortedBag<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toSortedBag());
        assertEquals(expected, actual);
        MutableSortedBag<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedBag());
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedBagBy()
    {
        MutableSortedBag<Integer> expected = SMALL_INTERVAL.toSortedBagBy(Object::toString);
        MutableSortedBag<Integer> actual =
                this.smallData.stream().collect(Collectors2.toSortedBagBy(Object::toString));
        assertEquals(expected, actual);
        MutableSortedBag<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedBagBy(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedBagByParallel()
    {
        MutableSortedBag<Integer> expected = LARGE_INTERVAL.toSortedBagBy(Object::toString);
        MutableSortedBag<Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toSortedBagBy(Object::toString));
        assertEquals(expected, actual);
        MutableSortedBag<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedBagBy(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedBag()
    {
        MutableSortedBag<Integer> expected = SMALL_INTERVAL.toSortedBag();
        ImmutableSortedBag<Integer> actual = this.smallData.stream().collect(Collectors2.toImmutableSortedBag());
        assertEquals(expected, actual);
        ImmutableSortedBag<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedBag());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedBagParallel()
    {
        MutableSortedBag<Integer> expected = LARGE_INTERVAL.toSortedBag();
        ImmutableSortedBag<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toImmutableSortedBag());
        assertEquals(expected, actual);
        ImmutableSortedBag<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedBag());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedBagBy()
    {
        MutableSortedBag<Integer> expected = SMALL_INTERVAL.toSortedBagBy(Object::toString);
        ImmutableSortedBag<Integer> actual =
                this.smallData.stream().collect(Collectors2.toImmutableSortedBagBy(Object::toString));
        assertEquals(expected, actual);
        ImmutableSortedBag<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedBagBy(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedBagByParallel()
    {
        MutableSortedBag<Integer> expected = LARGE_INTERVAL.toSortedBagBy(Object::toString);
        ImmutableSortedBag<Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedBagBy(Object::toString));
        assertEquals(expected, actual);
        ImmutableSortedBag<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedBagBy(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedSetWithComparator()
    {
        MutableSortedSet<Integer> expected = SMALL_INTERVAL.toSortedSet(Comparator.reverseOrder());
        MutableSortedSet<Integer> actual =
                this.smallData.stream().collect(Collectors2.toSortedSet(Comparator.reverseOrder()));
        assertEquals(expected, actual);
        MutableSortedSet<Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedSet(Comparator.reverseOrder()));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedSetParallelWithComparator()
    {
        MutableSortedSet<Integer> expected = LARGE_INTERVAL.toSortedSet(Comparator.reverseOrder());
        MutableSortedSet<Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toSortedSet(Comparator.reverseOrder()));
        assertEquals(expected, actual);
        MutableSortedSet<Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedSet(Comparator.reverseOrder()));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedSetWithComparator()
    {
        MutableSortedSet<Integer> expected = SMALL_INTERVAL.toSortedSet(Comparator.reverseOrder());
        ImmutableSortedSet<Integer> actual =
                this.smallData.stream().collect(Collectors2.toImmutableSortedSet(Comparator.reverseOrder()));
        assertEquals(expected, actual);
        ImmutableSortedSet<Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedSet(Comparator.reverseOrder()));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedSetParallelWithComparator()
    {
        MutableSortedSet<Integer> expected = LARGE_INTERVAL.toSortedSet(Comparator.reverseOrder());
        ImmutableSortedSet<Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedSet(Comparator.reverseOrder()));
        assertEquals(expected, actual);
        ImmutableSortedSet<Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedSet(Comparator.reverseOrder()));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedBagWithComparator()
    {
        MutableSortedBag<Integer> expected = SMALL_INTERVAL.toSortedBag(Comparator.reverseOrder());
        MutableSortedBag<Integer> actual =
                this.smallData.stream().collect(Collectors2.toSortedBag(Comparator.reverseOrder()));
        assertEquals(expected, actual);
        MutableSortedBag<Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedBag(Comparator.reverseOrder()));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedBagParallelWithComparator()
    {
        MutableSortedBag<Integer> expected = LARGE_INTERVAL.toSortedBag(Comparator.reverseOrder());
        MutableSortedBag<Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toSortedBag(Comparator.reverseOrder()));
        assertEquals(expected, actual);
        MutableSortedBag<Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedBag(Comparator.reverseOrder()));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedBagWithComparator()
    {
        MutableSortedBag<Integer> expected = SMALL_INTERVAL.toSortedBag(Comparator.reverseOrder());
        ImmutableSortedBag<Integer> actual =
                this.smallData.stream().collect(Collectors2.toImmutableSortedBag(Comparator.reverseOrder()));
        assertEquals(expected, actual);
        ImmutableSortedBag<Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedBag(Comparator.reverseOrder()));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedBagParallelWithComparator()
    {
        MutableSortedBag<Integer> expected = LARGE_INTERVAL.toSortedBag(Comparator.reverseOrder());
        ImmutableSortedBag<Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedBag(Comparator.reverseOrder()));
        assertEquals(expected, actual);
        ImmutableSortedBag<Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedBag(Comparator.reverseOrder()));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedList()
    {
        MutableList<Integer> expected = SMALL_INTERVAL.toSortedList();
        MutableList<Integer> actual = this.smallData.stream().collect(Collectors2.toSortedList());
        assertEquals(expected, actual);
        MutableList<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedList());
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedListParallel()
    {
        MutableList<Integer> expected = LARGE_INTERVAL.toSortedList();
        MutableList<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toSortedList());
        assertEquals(expected, actual);
        MutableList<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedList());
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedListBy()
    {
        MutableList<Integer> expected = SMALL_INTERVAL.toSortedListBy(Object::toString);
        MutableList<Integer> actual = this.smallData.stream().collect(Collectors2.toSortedListBy(Object::toString));
        assertEquals(expected, actual);
        MutableList<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedListBy(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedListByParallel()
    {
        MutableList<Integer> expected = LARGE_INTERVAL.toSortedListBy(Object::toString);
        MutableList<Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toSortedListBy(Object::toString));
        assertEquals(expected, actual);
        MutableList<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedListBy(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedList()
    {
        MutableList<Integer> expected = SMALL_INTERVAL.toSortedList();
        ImmutableList<Integer> actual = this.smallData.stream().collect(Collectors2.toImmutableSortedList());
        assertEquals(expected, actual);
        ImmutableList<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedList());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedListParallel()
    {
        MutableList<Integer> expected = LARGE_INTERVAL.toSortedList();
        ImmutableList<Integer> actual = this.bigData.parallelStream().collect(Collectors2.toImmutableSortedList());
        assertEquals(expected, actual);
        ImmutableList<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedList());
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedListBy()
    {
        MutableList<Integer> expected = SMALL_INTERVAL.toSortedListBy(Object::toString);
        ImmutableList<Integer> actual = this.smallData.stream().collect(Collectors2.toImmutableSortedListBy(Object::toString));
        assertEquals(expected, actual);
        ImmutableList<Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedListBy(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedListByParallel()
    {
        MutableList<Integer> expected = LARGE_INTERVAL.toSortedListBy(Object::toString);
        ImmutableList<Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedListBy(Object::toString));
        assertEquals(expected, actual);
        ImmutableList<Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedListBy(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedListWithComparator()
    {
        MutableList<Integer> expected = SMALL_INTERVAL.toSortedList(Comparator.reverseOrder());
        MutableList<Integer> actual =
                this.smallData.stream().collect(Collectors2.toSortedList(Comparator.reverseOrder()));
        assertEquals(expected, actual);
        MutableList<Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedList(Comparator.reverseOrder()));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedListParallelWithComparator()
    {
        MutableList<Integer> expected = LARGE_INTERVAL.toSortedList(Comparator.reverseOrder());
        MutableList<Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toSortedList(Comparator.reverseOrder()));
        assertEquals(expected, actual);
        MutableList<Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedList(Comparator.reverseOrder()));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedMap()
    {
        MutableSortedMap<String, Integer> expected = SMALL_INTERVAL.toSortedMap(Object::toString, i -> i);
        MutableSortedMap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toSortedMap(Object::toString, i -> i));
        assertEquals(expected, actual);
        MutableSortedMap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedMap(Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedMapParallel()
    {
        MutableSortedMap<String, Integer> expected = LARGE_INTERVAL.toSortedMap(Object::toString, i -> i);
        MutableSortedMap<String, Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toSortedMap(Object::toString, i -> i));
        assertEquals(expected, actual);
        MutableSortedMap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedMap(Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedMapWithComparator()
    {
        MutableSortedMap<String, Integer> expected =
                SMALL_INTERVAL.toSortedMap(Comparator.reverseOrder(), Object::toString, i -> i);
        MutableSortedMap<String, Integer> actual = this.smallData
                .stream()
                .collect(Collectors2.toSortedMap(Comparator.reverseOrder(), Object::toString, i -> i));
        assertEquals(expected, actual);
        MutableSortedMap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedMap(Comparator.reverseOrder(),
                        Object::toString,
                        i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedMapParallelWithComparator()
    {
        MutableSortedMap<String, Integer> expected =
                LARGE_INTERVAL.toSortedMap(Comparator.reverseOrder(), Object::toString, i -> i);
        MutableSortedMap<String, Integer> actual = this.bigData
                .parallelStream()
                .collect(Collectors2.toSortedMap(Comparator.reverseOrder(), Object::toString, i -> i));
        assertEquals(expected, actual);
        MutableSortedMap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedMap(Comparator.reverseOrder(),
                        Object::toString,
                        i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedMapBy()
    {
        MutableSortedMap<String, Integer> expected =
                SMALL_INTERVAL.toSortedMapBy(Object::toString, Object::toString, i -> i);
        MutableSortedMap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toSortedMapBy(Object::toString, Object::toString, i -> i));
        assertEquals(expected, actual);
        MutableSortedMap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toSortedMapBy(Object::toString, Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toSortedMapByParallel()
    {
        MutableSortedMap<String, Integer> expected =
                LARGE_INTERVAL.toSortedMapBy(Object::toString, Object::toString, i -> i);
        MutableSortedMap<String, Integer> actual = this.bigData
                .parallelStream()
                .collect(Collectors2.toSortedMapBy(Object::toString, Object::toString, i -> i));
        assertEquals(expected, actual);
        MutableSortedMap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toSortedMapBy(Object::toString, Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedMap()
    {
        ImmutableSortedMap<String, Integer> expected =
                SMALL_INTERVAL.toSortedMap(Object::toString, i -> i).toImmutable();
        ImmutableSortedMap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toImmutableSortedMap(Object::toString, i -> i));
        assertEquals(expected, actual);
        ImmutableSortedMap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedMap(Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedMapParallel()
    {
        ImmutableSortedMap<String, Integer> expected =
                LARGE_INTERVAL.toSortedMap(Object::toString, i -> i).toImmutable();
        ImmutableSortedMap<String, Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedMap(Object::toString, i -> i));
        assertEquals(expected, actual);
        ImmutableSortedMap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedMap(Object::toString, i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedMapWithComparator()
    {
        ImmutableSortedMap<String, Integer> expected =
                SMALL_INTERVAL.toSortedMap(Comparator.reverseOrder(), Object::toString, i -> i).toImmutable();
        ImmutableSortedMap<String, Integer> actual = this.smallData
                .stream()
                .collect(Collectors2.toImmutableSortedMap(Comparator.reverseOrder(), Object::toString, i -> i));
        assertEquals(expected, actual);
        ImmutableSortedMap<String, Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedMap(
                Comparator.reverseOrder(),
                Object::toString,
                i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedMapParallelWithComparator()
    {
        ImmutableSortedMap<String, Integer> expected =
                LARGE_INTERVAL.toSortedMap(Comparator.reverseOrder(), Object::toString, i -> i).toImmutable();
        ImmutableSortedMap<String, Integer> actual = this.bigData
                .parallelStream()
                .collect(Collectors2.toImmutableSortedMap(Comparator.reverseOrder(), Object::toString, i -> i));
        assertEquals(expected, actual);
        ImmutableSortedMap<String, Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedMap(
                Comparator.reverseOrder(),
                Object::toString,
                i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedMapBy()
    {
        ImmutableSortedMap<String, Integer> expected =
                SMALL_INTERVAL.toSortedMapBy(Object::toString, Object::toString, i -> i).toImmutable();
        ImmutableSortedMap<String, Integer> actual = this.smallData
                .stream()
                .collect(Collectors2.toImmutableSortedMapBy(Object::toString, Object::toString, i -> i));
        assertEquals(expected, actual);
        ImmutableSortedMap<String, Integer> actual2 = SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedMapBy(
                Object::toString,
                Object::toString,
                i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedMapByParallel()
    {
        ImmutableSortedMap<String, Integer> expected =
                LARGE_INTERVAL.toSortedMapBy(Object::toString, Object::toString, i -> i).toImmutable();
        ImmutableSortedMap<String, Integer> actual = this.bigData
                .parallelStream()
                .collect(Collectors2.toImmutableSortedMapBy(Object::toString, Object::toString, i -> i));
        assertEquals(expected, actual);
        ImmutableSortedMap<String, Integer> actual2 = LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedMapBy(
                Object::toString,
                Object::toString,
                i -> i));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedListWithComparator()
    {
        MutableList<Integer> expected = SMALL_INTERVAL.toSortedList(Comparator.reverseOrder());
        ImmutableList<Integer> actual =
                this.smallData.stream().collect(Collectors2.toImmutableSortedList(Comparator.reverseOrder()));
        assertEquals(expected, actual);
        ImmutableList<Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedList(Comparator.reverseOrder()));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSortedListParallelWithComparator()
    {
        MutableList<Integer> expected = LARGE_INTERVAL.toSortedList(Comparator.reverseOrder());
        ImmutableList<Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toImmutableSortedList(Comparator.reverseOrder()));
        assertEquals(expected, actual);
        ImmutableList<Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSortedList(Comparator.reverseOrder()));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableListMultimap()
    {
        Multimap<String, Integer> expected = SMALL_INTERVAL.groupBy(Object::toString);
        ImmutableListMultimap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toImmutableListMultimap(Object::toString));
        assertEquals(expected, actual);
        ImmutableListMultimap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableListMultimap(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableListMultimapParallel()
    {
        Multimap<String, Integer> expected = LARGE_INTERVAL.groupBy(Object::toString);
        ImmutableListMultimap<String, Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toImmutableListMultimap(Object::toString));
        assertEquals(expected, actual);
        ImmutableListMultimap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableListMultimap(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableListMultimap2()
    {
        Multimap<String, String> expected = SMALL_INTERVAL.collect(Object::toString).groupBy(Object::toString);
        ImmutableListMultimap<String, String> actual = this.smallData
                .stream()
                .collect(Collectors2.toImmutableListMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual);
        ImmutableListMultimap<String, String> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableListMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableListMultimap2Parallel()
    {
        Multimap<String, String> expected = LARGE_INTERVAL.collect(Object::toString).groupBy(Object::toString);
        ImmutableListMultimap<String, String> actual = this.bigData
                .parallelStream()
                .collect(Collectors2.toImmutableListMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual);
        ImmutableListMultimap<String, String> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableListMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSetMultimap()
    {
        MutableSetMultimap<String, Integer> expected = SMALL_INTERVAL.toSet().groupBy(Object::toString);
        ImmutableSetMultimap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toImmutableSetMultimap(Object::toString));
        assertEquals(expected, actual);
        ImmutableSetMultimap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSetMultimap(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSetMultimapParallel()
    {
        MutableSetMultimap<String, Integer> expected = LARGE_INTERVAL.toSet().groupBy(Object::toString);
        ImmutableSetMultimap<String, Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toImmutableSetMultimap(Object::toString));
        assertEquals(expected, actual);
        ImmutableSetMultimap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSetMultimap(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSetMultimap2()
    {
        MutableSetMultimap<String, String> expected =
                SMALL_INTERVAL.toSet().collect(Object::toString).groupBy(Object::toString);
        ImmutableSetMultimap<String, String> actual =
                this.smallData.stream().collect(Collectors2.toImmutableSetMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual);
        ImmutableSetMultimap<String, String> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableSetMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableSetMultimap2Parallel()
    {
        MutableSetMultimap<String, String> expected =
                LARGE_INTERVAL.toSet().collect(Object::toString).groupBy(Object::toString);
        ImmutableSetMultimap<String, String> actual = this.bigData
                .parallelStream()
                .collect(Collectors2.toImmutableSetMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual);
        ImmutableSetMultimap<String, String> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableSetMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableBagMultimap()
    {
        MutableBagMultimap<String, Integer> expected = SMALL_INTERVAL.toBag().groupBy(Object::toString);
        ImmutableBagMultimap<String, Integer> actual =
                this.smallData.stream().collect(Collectors2.toImmutableBagMultimap(Object::toString));
        assertEquals(expected, actual);
        ImmutableBagMultimap<String, Integer> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableBagMultimap(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableBagMultimapParallel()
    {
        MutableBagMultimap<String, Integer> expected = LARGE_INTERVAL.toBag().groupBy(Object::toString);
        ImmutableBagMultimap<String, Integer> actual =
                this.bigData.parallelStream().collect(Collectors2.toImmutableBagMultimap(Object::toString));
        assertEquals(expected, actual);
        ImmutableBagMultimap<String, Integer> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableBagMultimap(Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableBagMultimap2()
    {
        MutableBagMultimap<String, String> expected =
                SMALL_INTERVAL.toBag().collect(Object::toString).groupBy(Object::toString);
        ImmutableBagMultimap<String, String> actual =
                this.smallData.stream().collect(Collectors2.toImmutableBagMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual);
        ImmutableBagMultimap<String, String> actual2 =
                SMALL_INTERVAL.reduceInPlace(Collectors2.toImmutableBagMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void toImmutableBagMultimap2Parallel()
    {
        MutableBagMultimap<String, String> expected =
                LARGE_INTERVAL.toBag().collect(Object::toString).groupBy(Object::toString);
        ImmutableBagMultimap<String, String> actual = this.bigData
                .parallelStream()
                .collect(Collectors2.toImmutableBagMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual);
        ImmutableBagMultimap<String, String> actual2 =
                LARGE_INTERVAL.reduceInPlace(Collectors2.toImmutableBagMultimap(Object::toString, Object::toString));
        assertEquals(expected, actual2);
    }

    @Test
    public void countBy()
    {
        Interval integers = Interval.oneTo(100);
        MutableBag<Integer> counts = integers.stream().collect(Collectors2.countBy(i -> i % 2));
        assertEquals(integers.countBy(i -> i % 2), counts);
        assertEquals(50, counts.occurrencesOf(0));
        assertEquals(50, counts.occurrencesOf(1));
    }

    @Test
    public void countByParallel()
    {
        Interval integers = Interval.oneTo(100000);
        MutableBag<Integer> counts = integers.parallelStream().collect(Collectors2.countBy(i -> i % 2));
        assertEquals(integers.countBy(i -> i % 2), counts);
        assertEquals(50000, counts.occurrencesOf(0));
        assertEquals(50000, counts.occurrencesOf(1));
    }

    @Test
    public void countByEach()
    {
        List<Interval> intervals = FastList.newListWith(
                Interval.evensFromTo(1, 100),
                Interval.oddsFromTo(1, 100));

        MutableBag<Integer> counts = intervals.stream().collect(Collectors2.countByEach(iv -> iv.collect(i -> i % 2)));

        assertEquals(Interval.oneTo(100).countBy(i -> i % 2), counts);
        assertEquals(50, counts.occurrencesOf(0));
        assertEquals(50, counts.occurrencesOf(1));
    }

    @Test
    public void countByEachParallel()
    {
        List<Interval> intervals = FastList.newListWith(
                Interval.evensFromTo(1, 100000),
                Interval.oddsFromTo(1, 100000));

        MutableBag<Integer> counts = intervals.parallelStream().collect(Collectors2.countByEach(iv -> iv.collect(i -> i % 2)));

        assertEquals(Interval.oneTo(100000).countBy(i -> i % 2), counts);
        assertEquals(50000, counts.occurrencesOf(0));
        assertEquals(50000, counts.occurrencesOf(1));
    }

    @Test
    public void groupByEach()
    {
        Function<Integer, Iterable<Integer>> groupByFunction =
                (Integer each) -> SMALL_INTERVAL.collect((Integer i) -> each * i);
        MutableListMultimap<Integer, Integer> products = this.smallData.stream()
                .collect(Collectors2.groupByEach(groupByFunction, Multimaps.mutable.list::empty));

        Verify.assertIterableSize(1, products.get(1));
        Verify.assertIterableSize(2, products.get(2));
        Verify.assertIterableSize(2, products.get(3));
        Verify.assertIterableSize(3, products.get(4));
        Verify.assertIterableSize(2, products.get(5));
        assertEquals(SMALL_INTERVAL.toList().groupByEach(groupByFunction), products);
    }

    @Test
    public void groupByEachParallel()
    {
        Function<Integer, Iterable<Integer>> groupByFunction =
                (Integer each) -> SMALL_INTERVAL.collect((Integer i) -> each * i);
        MutableListMultimap<Integer, Integer> products = this.smallData.parallelStream()
                .collect(Collectors2.groupByEach(groupByFunction, Multimaps.mutable.list::empty));

        Verify.assertIterableSize(1, products.get(1));
        Verify.assertIterableSize(2, products.get(2));
        Verify.assertIterableSize(2, products.get(3));
        Verify.assertIterableSize(3, products.get(4));
        Verify.assertIterableSize(2, products.get(5));
        assertEquals(SMALL_INTERVAL.toList().groupByEach(groupByFunction), products);
    }

    @Test
    public void groupByUniqueKey()
    {
        MutableMap<Integer, Integer> expectedMap = SMALL_INTERVAL.groupByUniqueKey(id -> id, Maps.mutable.empty());
        MutableMap<Integer, Integer> actualMap = SMALL_INTERVAL.stream().collect(Collectors2.groupByUniqueKey(id -> id, Maps.mutable::empty));
        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void groupByUniqueKey_throws_for_duplicate()
    {
        assertThrows(IllegalStateException.class, () -> SMALL_INTERVAL.stream().collect(Collectors2.groupByUniqueKey(id -> 1, Maps.mutable::empty)));
    }

    @Test
    public void groupByUniqueKey_parallelStream()
    {
        MutableMap<Integer, Integer> expectedMap = LARGE_INTERVAL.groupByUniqueKey(id -> id, Maps.mutable.empty());
        MutableMap<Integer, Integer> actualMap = LARGE_INTERVAL.parallelStream().collect(Collectors2.groupByUniqueKey(id -> id, Maps.mutable::empty));
        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void groupByUniqueKey_parallelStream_throws_for_duplicate()
    {
        assertThrows(IllegalStateException.class, () -> LARGE_INTERVAL.parallelStream().collect(Collectors2.groupByUniqueKey(id -> 1, Maps.mutable::empty)));
    }

    @Test
    public void groupByUniqueKey_parallelStream_duplicate_from_combiner()
    {
        assertThrows(IllegalStateException.class, () -> LARGE_INTERVAL.parallelStream().collect(Collectors2.groupByUniqueKey(id -> id == 15000 ? 1 : id, Maps.mutable::empty)));
    }

    @Test
    public void aggregateBy()
    {
        MutableMap<Integer, Integer> expectedMap = SMALL_INTERVAL.toList().aggregateBy(each -> each % 2, () -> 0, Integer::sum);
        MutableMap<Integer, Integer> actualMap = SMALL_INTERVAL.stream().collect(Collectors2.aggregateBy(each -> each % 2, () -> 0, Integer::sum, Maps.mutable::empty));
        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void aggregateBy_parallelStream()
    {
        MutableMap<Integer, Integer> expectedMap = LARGE_INTERVAL.toList().aggregateBy(each -> each % 2, () -> 0, Integer::sum);
        MutableMap<Integer, Integer> actualMap = LARGE_INTERVAL.parallelStream().collect(Collectors2.aggregateBy(each -> each % 2, () -> 0, Integer::sum, Maps.mutable::empty));
        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void aggregateBy_mutableSortedMap()
    {
        Verify.assertInstanceOf(MutableSortedMap.class, SMALL_INTERVAL.stream().collect(Collectors2.aggregateBy(each -> each % 2, () -> 0, Integer::sum, SortedMaps.mutable::empty)));
    }

    @Test
    public void aggregateBy_parallelStream_mutableSortedMap()
    {
        Verify.assertInstanceOf(MutableSortedMap.class, LARGE_INTERVAL.parallelStream().collect(Collectors2.aggregateBy(each -> each % 2, () -> 0, Integer::sum, SortedMaps.mutable::empty)));
    }
}
