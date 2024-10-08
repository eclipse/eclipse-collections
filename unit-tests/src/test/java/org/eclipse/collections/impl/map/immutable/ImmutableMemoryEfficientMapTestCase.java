/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable;

import java.util.Collections;
import java.util.List;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.partition.PartitionImmutableCollection;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.block.function.NegativeIntervalFunction;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.math.Sum;
import org.eclipse.collections.impl.math.SumProcedure;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;
import static org.eclipse.collections.impl.factory.Iterables.iSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class ImmutableMemoryEfficientMapTestCase extends ImmutableMapTestCase
{
    protected abstract <K, V> ImmutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4);

    @Test
    public abstract void select();

    @Test
    public abstract void reject();

    @Test
    public abstract void detect();

    @Test
    public void collectValues()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");
        ImmutableMap<String, String> result = map.collectValues((argument1, argument2) -> new StringBuilder(argument2).reverse().toString());

        switch (map.size())
        {
            case 0:
                Verify.assertEmpty(result);
                break;
            case 1:
                Verify.assertContainsKeyValue("1", "enO", result);
                break;
            case 2:
                Verify.assertContainsAllKeyValues(result, "1", "enO", "2", "owT");
                break;
            case 3:
                Verify.assertContainsAllKeyValues(result, "1", "enO", "2", "owT", "3", "eerhT");
                break;
            case 4:
                Verify.assertContainsAllKeyValues(result, "1", "enO", "2", "owT", "3", "eerhT", "4", "ruoF");
                break;
            default:
                fail();
        }
    }

    @Test
    public void collect()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");
        ImmutableMap<Integer, String> result = map.collect((Function2<String, String, Pair<Integer, String>>) (argument1, argument2) -> Tuples.pair(Integer.valueOf(argument1), argument1 + ':' + new StringBuilder(argument2).reverse()));

        switch (map.size())
        {
            case 0:
                Verify.assertEmpty(result);
                break;
            case 1:
                Verify.assertContainsKeyValue(1, "1:enO", result);
                break;
            case 2:
                Verify.assertContainsAllKeyValues(result, 1, "1:enO", 2, "2:owT");
                break;
            case 3:
                Verify.assertContainsAllKeyValues(result, 1, "1:enO", 2, "2:owT", 3, "3:eerhT");
                break;
            case 4:
                Verify.assertContainsAllKeyValues(result, 1, "1:enO", 2, "2:owT", 3, "3:eerhT", 4, "4:ruoF");
                break;
            default:
                fail();
        }
    }

    @Test
    public void allSatisfy()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        assertTrue(map.allSatisfy(String.class::isInstance));
        assertFalse(map.allSatisfy("Monkey"::equals));
    }

    @Test
    public void anySatisfy()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        assertTrue(map.anySatisfy(String.class::isInstance));
        assertFalse(map.anySatisfy("Monkey"::equals));
    }

    @Test
    public void noneSatisfy()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        assertTrue(map.noneSatisfy(Integer.class::isInstance));
        assertTrue(map.noneSatisfy("Monkey"::equals));
    }

    @Test
    public void appendString()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        StringBuilder builder1 = new StringBuilder();
        map.appendString(builder1);
        String defaultString = builder1.toString();

        StringBuilder builder2 = new StringBuilder();
        map.appendString(builder2, "|");
        String delimitedString = builder2.toString();

        StringBuilder builder3 = new StringBuilder();
        map.appendString(builder3, "{", "|", "}");
        String wrappedString = builder3.toString();

        switch (map.size())
        {
            case 1:
                assertEquals("One", defaultString);
                assertEquals("One", delimitedString);
                assertEquals("{One}", wrappedString);
                break;
            case 2:
                assertEquals(8, defaultString.length());
                assertEquals(7, delimitedString.length());
                Verify.assertContains("|", delimitedString);
                assertEquals(9, wrappedString.length());
                Verify.assertContains("|", wrappedString);
                assertTrue(wrappedString.startsWith("{"));
                assertTrue(wrappedString.endsWith("}"));
                break;
            case 3:
                assertEquals(15, defaultString.length());
                assertEquals(13, delimitedString.length());
                Verify.assertContains("|", delimitedString);
                assertEquals(15, wrappedString.length());
                Verify.assertContains("|", wrappedString);
                assertTrue(wrappedString.startsWith("{"));
                assertTrue(wrappedString.endsWith("}"));
                break;
            case 4:
                assertEquals(21, defaultString.length());
                assertEquals(18, delimitedString.length());
                Verify.assertContains("|", delimitedString);
                assertEquals(20, wrappedString.length());
                Verify.assertContains("|", wrappedString);
                assertTrue(wrappedString.startsWith("{"));
                assertTrue(wrappedString.endsWith("}"));
                break;
            default:
                assertEquals("", defaultString);
                assertEquals("", delimitedString);
                assertEquals("{}", wrappedString);
                break;
        }
    }

    @Test
    public void toBag()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        MutableBag<String> bag = map.toBag();
        switch (map.size())
        {
            case 1:
                Verify.assertContains("One", bag);
                break;
            case 2:
                Verify.assertContainsAll(bag, "One", "Two");
                break;
            case 3:
                Verify.assertContainsAll(bag, "One", "Two", "Three");
                break;
            case 4:
                Verify.assertContainsAll(bag, "One", "Two", "Three", "Four");
                break;
            default:
                Verify.assertEmpty(bag);
                break;
        }
    }

    @Test
    public void asLazy()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        LazyIterable<String> lazy = map.asLazy();
        switch (map.size())
        {
            case 1:
                Verify.assertContains("One", lazy.toList());
                break;
            case 2:
                Verify.assertContainsAll(lazy.toList(), "One", "Two");
                break;
            case 3:
                Verify.assertContainsAll(lazy.toList(), "One", "Two", "Three");
                break;
            case 4:
                Verify.assertContainsAll(lazy.toList(), "One", "Two", "Three", "Four");
                break;
            default:
                Verify.assertEmpty(lazy.toList());
                break;
        }
    }

    @Test
    public void toList()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        MutableList<String> list = map.toList();
        switch (map.size())
        {
            case 1:
                Verify.assertContains("One", list);
                break;
            case 2:
                Verify.assertContainsAll(list, "One", "Two");
                break;
            case 3:
                Verify.assertContainsAll(list, "One", "Two", "Three");
                break;
            case 4:
                Verify.assertContainsAll(list, "One", "Two", "Three", "Four");
                break;
            default:
                Verify.assertEmpty(list);
                break;
        }
    }

    @Test
    public void toMapWithFunction()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "3", "Three", "4", "Four", "11", "Eleven");

        MutableMap<Integer, String> actual = map.toMap(String::length, String::valueOf);

        switch (map.size())
        {
            case 1:
                assertEquals(UnifiedMap.newWithKeysValues(3, "One"), actual);
                break;
            case 2:
                assertEquals(UnifiedMap.newWithKeysValues(3, "One", 5, "Three"), actual);
                break;
            case 3:
                assertEquals(UnifiedMap.newWithKeysValues(3, "One", 5, "Three", 4, "Four"), actual);
                break;
            case 4:
                assertEquals(UnifiedMap.newWithKeysValues(3, "One", 5, "Three", 4, "Four", 6, "Eleven"), actual);
                break;
            default:
                Verify.assertEmpty(actual);
                break;
        }
    }

    @Test
    public void toSet()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        MutableSet<String> set = map.toSet();
        switch (map.size())
        {
            case 1:
                Verify.assertContains("One", set);
                break;
            case 2:
                Verify.assertContainsAll(set, "One", "Two");
                break;
            case 3:
                Verify.assertContainsAll(set, "One", "Two", "Three");
                break;
            case 4:
                Verify.assertContainsAll(set, "One", "Two", "Three", "Four");
                break;
            default:
                Verify.assertEmpty(set);
                break;
        }
    }

    @Test
    public void toSortedList()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        MutableList<Integer> sorted = map.toSortedList();
        switch (map.size())
        {
            case 1:
                assertEquals(iList(1), sorted);
                break;
            case 2:
                assertEquals(iList(1, 2), sorted);
                break;
            case 3:
                assertEquals(iList(1, 2, 3), sorted);
                break;
            case 4:
                assertEquals(iList(1, 2, 3, 4), sorted);
                break;
            default:
                Verify.assertEmpty(sorted);
                break;
        }

        MutableList<Integer> reverse = map.toSortedList(Collections.reverseOrder());
        switch (map.size())
        {
            case 1:
                assertEquals(iList(1), reverse);
                break;
            case 2:
                assertEquals(iList(2, 1), reverse);
                break;
            case 3:
                assertEquals(iList(3, 2, 1), reverse);
                break;
            case 4:
                assertEquals(iList(4, 3, 2, 1), reverse);
                break;
            default:
                Verify.assertEmpty(reverse);
                break;
        }
    }

    @Test
    public void toSortedListBy()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        MutableList<Integer> list = map.toSortedListBy(String::valueOf);
        switch (map.size())
        {
            case 1:
                assertEquals(iList(1), list);
                break;
            case 2:
                assertEquals(iList(1, 2), list);
                break;
            case 3:
                assertEquals(iList(1, 2, 3), list);
                break;
            case 4:
                assertEquals(iList(1, 2, 3, 4), list);
                break;
            default:
                Verify.assertEmpty(list);
                break;
        }
    }

    @Test
    public void chunk()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        RichIterable<RichIterable<String>> chunks = map.chunk(2).toList();

        RichIterable<Integer> sizes = chunks.collect(RichIterable::size);

        switch (map.size())
        {
            case 1:
                assertEquals(iList(1), sizes);
                break;
            case 2:
                assertEquals(iList(2), sizes);
                break;
            case 3:
                assertEquals(iList(2, 1), sizes);
                break;
            case 4:
                assertEquals(iList(2, 2), sizes);
                break;
            default:
                assertEquals(0, chunks.size());
                break;
        }
    }

    @Test
    public void collect_value()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);
        MutableSet<String> collect = map.collect(Functions.getToString()).toSet();
        MutableSet<String> collectToTarget = map.collect(String::valueOf, UnifiedSet.newSet());

        switch (map.size())
        {
            case 1:
                Verify.assertContainsAll(collect, "1");
                Verify.assertContainsAll(collectToTarget, "1");
                break;
            case 2:
                Verify.assertContainsAll(collect, "1", "2");
                Verify.assertContainsAll(collectToTarget, "1", "2");
                break;
            case 3:
                Verify.assertContainsAll(collect, "1", "2", "3");
                Verify.assertContainsAll(collectToTarget, "1", "2", "3");
                break;
            case 4:
                Verify.assertContainsAll(collect, "1", "2", "3", "4");
                Verify.assertContainsAll(collectToTarget, "1", "2", "3", "4");
                break;
            default:
                Verify.assertEmpty(collect);
                break;
        }
    }

    @Test
    public void collectIf()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        MutableSet<String> collect = map.collectIf(Integer.class::isInstance, String::valueOf).toSet();
        MutableSet<String> collectToTarget = map.collectIf(Integer.class::isInstance, String::valueOf, UnifiedSet.newSet());

        switch (map.size())
        {
            case 1:
                Verify.assertContainsAll(collect, "1");
                Verify.assertContainsAll(collectToTarget, "1");
                break;
            case 2:
                Verify.assertContainsAll(collect, "1", "2");
                Verify.assertContainsAll(collectToTarget, "1", "2");
                break;
            case 3:
                Verify.assertContainsAll(collect, "1", "2", "3");
                Verify.assertContainsAll(collectToTarget, "1", "2", "3");
                break;
            case 4:
                Verify.assertContainsAll(collect, "1", "2", "3", "4");
                Verify.assertContainsAll(collectToTarget, "1", "2", "3", "4");
                break;
            default:
                Verify.assertEmpty(collect);
                break;
        }
    }

    @Test
    public void collectWith()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        MutableBag<Integer> collectWith = map.collectWith(AddFunction.INTEGER, 1, HashBag.newBag());

        switch (map.size())
        {
            case 1:
                assertEquals(Bags.mutable.of(2), collectWith);
                break;
            case 2:
                assertEquals(Bags.mutable.of(2, 3), collectWith);
                break;
            case 3:
                assertEquals(Bags.mutable.of(2, 3, 4), collectWith);
                break;
            case 4:
                assertEquals(Bags.mutable.of(2, 3, 4, 5), collectWith);
                break;
            default:
                Verify.assertEmpty(collectWith);
                break;
        }
    }

    @Test
    public void contains()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        switch (map.size())
        {
            case 1:
                assertTrue(map.contains("One"));
                assertFalse(map.contains("Two"));
                break;
            case 2:
                assertTrue(map.contains("Two"));
                assertFalse(map.contains("Three"));
                break;
            case 3:
                assertTrue(map.contains("Three"));
                assertFalse(map.contains("Four"));
                break;
            case 4:
                assertTrue(map.contains("Four"));
                assertFalse(map.contains("Five"));
                break;
            default:
                Verify.assertEmpty(map);
                break;
        }
    }

    @Test
    public void getFirst()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        if (map.isEmpty())
        {
            String value = map.getFirst();
            assertNull(value);
        }
        else
        {
            String value = map.getFirst();
            assertNotNull(value);
            Verify.assertContains(value, UnifiedSet.newSetWith("One", "Two", "Three", "Four"));
        }
    }

    @Test
    public void getLast()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        if (map.isEmpty())
        {
            String value = map.getLast();
            assertNull(value);
        }
        else
        {
            String value = map.getLast();
            assertNotNull(value);
            Verify.assertContains(value, UnifiedSet.newSetWith("One", "Two", "Three", "Four"));
        }
    }

    @Test
    public void containsAllIterable()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        switch (map.size())
        {
            case 1:
                assertTrue(map.containsAllIterable(iList("One")));
                break;
            case 2:
                assertTrue(map.containsAllIterable(iList("One", "Two")));
                break;
            case 3:
                assertTrue(map.containsAllIterable(iList("One", "Two", "Three")));
                break;
            case 4:
                assertTrue(map.containsAllIterable(iList("One", "Two", "Three", "Four")));
                break;
            default:
                Verify.assertEmpty(map);
                break;
        }
    }

    @Test
    public void containsAllArguments()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        switch (map.size())
        {
            case 1:
                assertTrue(map.containsAllArguments("One"));
                break;
            case 2:
                assertTrue(map.containsAllArguments("One", "Two"));
                break;
            case 3:
                assertTrue(map.containsAllArguments("One", "Two", "Three"));
                break;
            case 4:
                assertTrue(map.containsAllArguments("One", "Two", "Three", "Four"));
                break;
            default:
                Verify.assertEmpty(map);
                break;
        }
    }

    @Test
    public void count()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        int actual = map.count(Predicates.or("One"::equals, "Three"::equals));

        switch (map.size())
        {
            case 1:
                assertEquals(1, actual);
                break;
            case 2:
                assertEquals(1, actual);
                break;
            case 3:
                assertEquals(2, actual);
                break;
            case 4:
                assertEquals(2, actual);
                break;
            default:
                assertEquals(0, actual);
                break;
        }
    }

    @Test
    public void detect_value()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        if (map.isEmpty())
        {
            String resultNotFound = map.detect(ignored -> true);
            assertNull(resultNotFound);
        }
        else
        {
            String resultFound = map.detect("One"::equals);
            assertEquals("One", resultFound);

            String resultNotFound = map.detect("Five"::equals);
            assertNull(resultNotFound);
        }
    }

    @Test
    public void detectIfNone_value()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        if (map.isEmpty())
        {
            String resultNotFound = map.detectIfNone(ignored -> true, () -> "Zero");
            assertEquals("Zero", resultNotFound);
        }
        else
        {
            String resultNotFound = map.detectIfNone("Five"::equals, () -> "Zero");
            assertEquals("Zero", resultNotFound);

            String resultFound = map.detectIfNone("One"::equals, () -> "Zero");
            assertEquals("One", resultFound);
        }
    }

    @Test
    public void flatCollect()
    {
        ImmutableMap<Integer, Integer> map = this.newMapWithKeysValues(1, 1, 2, 2, 3, 3, 4, 4);

        if (map.isEmpty())
        {
            Function<Integer, Iterable<Object>> fail = each -> {
                throw new AssertionError();
            };
            assertEquals(Bags.immutable.empty(), map.flatCollect(fail));
            assertEquals(Bags.immutable.empty(), map.flatCollect(fail, HashBag.newBag()));
        }
        else
        {
            MutableBag<Integer> expected = Interval.oneTo(map.size()).flatCollect(Interval::oneTo).toBag();
            assertEquals(expected, map.flatCollect(Interval::oneTo));
            assertEquals(expected, map.flatCollect(Interval::oneTo, HashBag.newBag()));
        }
    }

    @Test
    public void groupBy()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        Function<Integer, Boolean> isOddFunction = object -> IntegerPredicates.isOdd().accept(object);

        Multimap<Boolean, Integer> expected;

        switch (map.size())
        {
            case 1:
                expected = FastListMultimap.newMultimap(Tuples.pair(Boolean.TRUE, 1));
                break;
            case 2:
                expected = FastListMultimap.newMultimap(Tuples.pair(Boolean.TRUE, 1), Tuples.pair(Boolean.FALSE, 2));
                break;
            case 3:
                expected = FastListMultimap.newMultimap(Tuples.pair(Boolean.TRUE, 1), Tuples.pair(Boolean.TRUE, 3), Tuples.pair(Boolean.FALSE, 2));
                break;
            case 4:
                expected = FastListMultimap.newMultimap(Tuples.pair(Boolean.TRUE, 1), Tuples.pair(Boolean.TRUE, 3), Tuples.pair(Boolean.FALSE, 2), Tuples.pair(Boolean.FALSE, 4));
                break;
            default:
                expected = FastListMultimap.newMultimap();
                break;
        }

        Multimap<Boolean, Integer> actual = map.groupBy(isOddFunction);
        assertEquals(HashBagMultimap.newMultimap(expected), HashBagMultimap.newMultimap(actual));

        Multimap<Boolean, Integer> actualFromTarget = map.groupBy(isOddFunction, FastListMultimap.newMultimap());
        assertEquals(HashBagMultimap.newMultimap(expected), HashBagMultimap.newMultimap(actualFromTarget));
    }

    @Test
    public void groupByEach()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        MutableMultimap<Integer, Integer> expected = FastListMultimap.newMultimap();
        for (int i = 1; i < map.size(); i++)
        {
            expected.putAll(-i, Interval.fromTo(i, map.size()));
        }

        NegativeIntervalFunction function = new NegativeIntervalFunction();
        Multimap<Integer, Integer> actual = map.groupByEach(function);
        expected.forEachKey(each -> {
            assertTrue(actual.containsKey(each));
            MutableList<Integer> values = actual.get(each).toList();
            Verify.assertNotEmpty(values);
            assertTrue(expected.get(each).containsAllIterable(values));
        });

        Multimap<Integer, Integer> actualFromTarget = map.groupByEach(function, FastListMultimap.newMultimap());
        expected.forEachKey(each -> {
            assertTrue(actualFromTarget.containsKey(each));
            MutableList<Integer> values = actualFromTarget.get(each).toList();
            Verify.assertNotEmpty(values);
            assertTrue(expected.get(each).containsAllIterable(values));
        });
    }

    @Test
    public void injectInto()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        Integer expectedInteger;
        IntegerSum expectedSum;

        switch (map.size())
        {
            case 1:
                expectedSum = new IntegerSum(1);
                expectedInteger = Integer.valueOf(1);
                break;
            case 2:
                expectedSum = new IntegerSum(3);
                expectedInteger = Integer.valueOf(3);
                break;
            case 3:
                expectedSum = new IntegerSum(6);
                expectedInteger = Integer.valueOf(6);
                break;
            case 4:
                expectedSum = new IntegerSum(10);
                expectedInteger = Integer.valueOf(10);
                break;
            default:
                expectedSum = new IntegerSum(0);
                expectedInteger = Integer.valueOf(0);
                break;
        }

        Integer actual = map.injectInto(0, AddFunction.INTEGER);
        assertEquals(expectedInteger, actual);

        Sum sum = map.injectInto(new IntegerSum(0), SumProcedure.number());
        assertEquals(expectedSum, sum);
    }

    @Test
    public void makeString()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        String defaultString = map.makeString();
        String delimitedString = map.makeString("|");
        String wrappedString = map.makeString("{", "|", "}");

        switch (map.size())
        {
            case 1:
                assertEquals("One", defaultString);
                assertEquals("One", delimitedString);
                assertEquals("{One}", wrappedString);
                break;
            case 2:
                assertEquals(8, defaultString.length());
                assertEquals(7, delimitedString.length());
                Verify.assertContains("|", delimitedString);
                assertEquals(9, wrappedString.length());
                Verify.assertContains("|", wrappedString);
                assertTrue(wrappedString.startsWith("{"));
                assertTrue(wrappedString.endsWith("}"));
                break;
            case 3:
                assertEquals(15, defaultString.length());
                assertEquals(13, delimitedString.length());
                Verify.assertContains("|", delimitedString);
                assertEquals(15, wrappedString.length());
                Verify.assertContains("|", wrappedString);
                assertTrue(wrappedString.startsWith("{"));
                assertTrue(wrappedString.endsWith("}"));
                break;
            case 4:
                assertEquals(21, defaultString.length());
                assertEquals(18, delimitedString.length());
                Verify.assertContains("|", delimitedString);
                assertEquals(20, wrappedString.length());
                Verify.assertContains("|", wrappedString);
                assertTrue(wrappedString.startsWith("{"));
                assertTrue(wrappedString.endsWith("}"));
                break;
            default:
                assertEquals("", defaultString);
                assertEquals("", delimitedString);
                assertEquals("{}", wrappedString);
                break;
        }
    }

    @Test
    public void min()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        assertEquals(Integer.valueOf(1), map.min());
        assertEquals(Integer.valueOf(1), map.min(Integer::compareTo));
    }

    @Test
    public void max()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        Integer max;

        switch (map.size())
        {
            case 1:
                max = Integer.valueOf(1);
                break;
            case 2:
                max = Integer.valueOf(2);
                break;
            case 3:
                max = Integer.valueOf(3);
                break;
            case 4:
                max = Integer.valueOf(4);
                break;
            default:
                max = Integer.valueOf(0);
                break;
        }

        assertEquals(max, map.max());
        assertEquals(max, map.max(Integer::compareTo));
    }

    @Test
    public void minBy()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        assertEquals(Integer.valueOf(1), map.minBy(String::valueOf));
    }

    @Test
    public void maxBy()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);
        assertEquals(Integer.valueOf(map.size()), map.maxBy(String::valueOf));
        Verify.assertContains(Integer.valueOf(map.size()), iList(1, 2, 3, 4));
    }

    @Test
    public void reject_value()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        MutableSet<Integer> rejected = map.reject(IntegerPredicates.isEven()).toSet();
        MutableSet<Integer> rejectedIntoTarget = map.reject(IntegerPredicates.isEven(), UnifiedSet.newSet());

        ImmutableSet<Integer> expected = this.expectReject(map.size());
        assertEquals(expected, rejected);
        assertEquals(expected, rejectedIntoTarget);
    }

    private ImmutableSet<Integer> expectReject(int size)
    {
        switch (size)
        {
            case 0:
                return iSet();
            case 1:
            case 2:
                return iSet(1);
            case 3:
            case 4:
                return iSet(1, 3);
            default:
                throw new AssertionError();
        }
    }

    @Test
    public void rejectWith_value()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        switch (map.size())
        {
            case 1:
                Verify.assertEmpty(map.rejectWith(Predicates2.lessThan(), 2, UnifiedSet.newSet()));
                break;
            case 2:
                Verify.assertContainsAll(map.rejectWith(Predicates2.lessThan(), 2, UnifiedSet.newSet()), 2);
                break;
            case 3:
                Verify.assertContainsAll(map.rejectWith(Predicates2.lessThan(), 2, UnifiedSet.newSet()), 2, 3);
                break;
            case 4:
                Verify.assertContainsAll(map.rejectWith(Predicates2.lessThan(), 2, UnifiedSet.newSet()), 2, 3, 4);
                break;
            default:
                Verify.assertEmpty(map);
                break;
        }
    }

    @Test
    public void select_value()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);
        ImmutableSet<Integer> expected = this.expectSelect(map.size());

        assertEquals(expected, map.select(IntegerPredicates.isEven()).toSet());
        assertEquals(expected, map.select(IntegerPredicates.isEven(), UnifiedSet.newSet()));
    }

    private ImmutableSet<Integer> expectSelect(int size)
    {
        switch (size)
        {
            case 0:
            case 1:
                return iSet();
            case 2:
            case 3:
                return iSet(2);
            case 4:
                return iSet(2, 4);
            default:
                throw new AssertionError();
        }
    }

    @Test
    public void selectWith_value()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        switch (map.size())
        {
            case 1:
                Verify.assertContainsAll(map.selectWith(Predicates2.lessThan(), 3, UnifiedSet.newSet()), 1);
                break;
            case 2:
            case 3:
            case 4:
                Verify.assertContainsAll(map.selectWith(Predicates2.lessThan(), 3, UnifiedSet.newSet()), 1, 2);
                break;
            default:
                Verify.assertEmpty(map);
                break;
        }
    }

    @Test
    public void partition_value()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);
        PartitionImmutableCollection<Integer> partition = map.partition(IntegerPredicates.isEven());

        assertEquals(this.expectSelect(map.size()), partition.getSelected().toSet());
        assertEquals(this.expectReject(map.size()), partition.getRejected().toSet());
    }

    @Test
    public void partitionWith_value()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);
        PartitionImmutableCollection<Integer> partition = map.partitionWith(Predicates2.in(), map.select(IntegerPredicates.isEven()));

        assertEquals(this.expectSelect(map.size()), partition.getSelected().toSet());
        assertEquals(this.expectReject(map.size()), partition.getRejected().toSet());
    }

    @Test
    public void toArray()
    {
        ImmutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4);

        Object[] array = map.toArray();
        Verify.assertSize(map.size(), array);

        Integer[] array2 = map.toArray(new Integer[0]);
        Verify.assertSize(map.size(), array2);

        Integer[] array3 = map.toArray(new Integer[map.size()]);
        Verify.assertSize(map.size(), array3);

        if (map.size() > 1)
        {
            Integer[] array4 = map.toArray(new Integer[map.size() - 1]);
            Verify.assertSize(map.size(), array4);
        }

        Integer[] array5 = map.toArray(new Integer[6]);
        Verify.assertSize(6, array5);
    }

    @Test
    public void zip()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        List<Object> nulls = Collections.nCopies(map.size(), null);
        List<Object> nullsPlusOne = Collections.nCopies(map.size() + 1, null);

        RichIterable<Pair<String, Object>> pairs = map.zip(nulls);
        assertEquals(
                map.toSet(),
                pairs.collect((Function<Pair<String, ?>, String>) Pair::getOne).toSet());
        assertEquals(
                nulls,
                pairs.collect((Function<Pair<?, Object>, Object>) Pair::getTwo, Lists.mutable.of()));

        RichIterable<Pair<String, Object>> pairsPlusOne = map.zip(nullsPlusOne);
        assertEquals(
                map.toSet(),
                pairsPlusOne.collect((Function<Pair<String, ?>, String>) Pair::getOne).toSet());
        assertEquals(nulls, pairsPlusOne.collect((Function<Pair<?, Object>, Object>) Pair::getTwo, Lists.mutable.of()));

        if (map.notEmpty())
        {
            List<Object> nullsMinusOne = Collections.nCopies(map.size() - 1, null);
            RichIterable<Pair<String, Object>> pairsMinusOne = map.zip(nullsMinusOne);
            assertEquals(map.size() - 1, pairsMinusOne.size());
        }

        assertEquals(
                map.zip(nulls).toSet(),
                map.zip(nulls, UnifiedSet.newSet()));
    }

    @Test
    public void zipWithIndex()
    {
        ImmutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three", "4", "Four");

        RichIterable<Pair<String, Integer>> pairs = map.zipWithIndex();

        assertEquals(
                map.toSet(),
                pairs.collect((Function<Pair<String, ?>, String>) Pair::getOne).toSet());
        if (map.notEmpty())
        {
            assertEquals(
                    Interval.zeroTo(map.size() - 1).toSet(),
                    pairs.collect((Function<Pair<?, Integer>, Integer>) Pair::getTwo, UnifiedSet.newSet()));
        }

        assertEquals(
                map.zipWithIndex().toSet(),
                map.zipWithIndex(UnifiedSet.newSet()));
    }
}
