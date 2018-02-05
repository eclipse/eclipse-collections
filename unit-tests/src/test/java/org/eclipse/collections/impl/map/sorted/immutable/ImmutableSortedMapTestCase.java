/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.sorted.immutable;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.sorted.ImmutableSortedMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.SortedMaps;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.fixed.ArrayAdapter;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.MapIterableTestCase;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ImmutableSortedMap}.
 */
public abstract class ImmutableSortedMapTestCase extends MapIterableTestCase
{
    private static final Comparator<? super Integer> REV_INT_COMPARATOR = Comparators.reverseNaturalOrder();

    /**
     * @return A map containing 1 => "1", 2 => "2", etc.
     */
    protected abstract ImmutableSortedMap<Integer, String> classUnderTest();

    protected abstract ImmutableSortedMap<Integer, String> classUnderTest(Comparator<? super Integer> comparator);

    /**
     * @return Size (and max key) of {@link #classUnderTest()}.
     */
    protected abstract int size();

    @Test
    public void castToSortedMap()
    {
        ImmutableSortedMap<Integer, String> immutable = this.classUnderTest();
        SortedMap<Integer, String> map = immutable.castToSortedMap();
        Assert.assertSame(immutable, map);
        Assert.assertEquals(immutable, new HashMap<>(map));

        ImmutableSortedMap<Integer, String> revImmutable = this.classUnderTest(REV_INT_COMPARATOR);
        SortedMap<Integer, String> revMap = revImmutable.castToSortedMap();
        Assert.assertSame(revImmutable, revMap);
        Assert.assertEquals(revImmutable, new HashMap<>(revMap));
    }

    @Override
    @Test
    public void toSortedMap()
    {
        super.toSortedMap();

        ImmutableSortedMap<Integer, String> immutable = this.classUnderTest();
        MutableSortedMap<Integer, String> map = immutable.toSortedMap();
        Assert.assertNotSame(immutable, map);
        Assert.assertEquals(immutable, map);

        ImmutableSortedMap<Integer, String> revImmutable = this.classUnderTest(REV_INT_COMPARATOR);
        MutableSortedMap<Integer, String> revMap = revImmutable.toSortedMap();
        Assert.assertNotSame(revImmutable, revMap);
        Assert.assertEquals(revImmutable, revMap);
    }

    @Override
    @Test
    public void equalsAndHashCode()
    {
        super.equalsAndHashCode();

        MutableMap<Integer, String> expected = this.equalUnifiedMap();
        MutableSortedMap<Integer, String> sortedMap = this.equalSortedMap();
        Verify.assertEqualsAndHashCode(expected, this.classUnderTest());
        Verify.assertEqualsAndHashCode(sortedMap, this.classUnderTest());
        Verify.assertEqualsAndHashCode(expected, this.classUnderTest(REV_INT_COMPARATOR));
        Verify.assertEqualsAndHashCode(sortedMap, this.classUnderTest(REV_INT_COMPARATOR));
    }

    @Override
    @Test
    public void forEachKeyValue()
    {
        super.forEachKeyValue();

        MutableList<Integer> actualKeys = Lists.mutable.empty();
        MutableList<String> actualValues = Lists.mutable.empty();

        this.classUnderTest().forEachKeyValue((key, value) -> {
            actualKeys.add(key);
            actualValues.add(value);
        });

        MutableList<Integer> expectedKeys = this.expectedKeys();
        Verify.assertListsEqual(expectedKeys, actualKeys);

        MutableList<String> expectedValues = expectedKeys.collect(String::valueOf);
        Verify.assertListsEqual(expectedValues, actualValues);

        MutableList<Integer> revActualKeys = Lists.mutable.empty();
        MutableList<String> revActualValues = Lists.mutable.empty();

        this.classUnderTest(REV_INT_COMPARATOR).forEachKeyValue((key, value) -> {
            revActualKeys.add(key);
            revActualValues.add(value);
        });

        MutableList<Integer> reverseKeys = expectedKeys.reverseThis();
        Verify.assertListsEqual(reverseKeys, revActualKeys);

        MutableList<String> reverseValues = expectedValues.reverseThis();
        Verify.assertListsEqual(reverseValues, revActualValues);
    }

    @Override
    @Test
    public void forEachValue()
    {
        super.forEachValue();

        MutableList<String> actualValues = Lists.mutable.empty();
        this.classUnderTest().forEachValue(CollectionAddProcedure.on(actualValues));
        Verify.assertListsEqual(this.expectedValues(), actualValues);

        MutableList<String> revActualValues = Lists.mutable.empty();
        this.classUnderTest(REV_INT_COMPARATOR).forEachValue(CollectionAddProcedure.on(revActualValues));
        Verify.assertListsEqual(this.expectedValues().reverseThis(), revActualValues);
    }

    @Override
    @Test
    public void tap()
    {
        super.tap();

        MutableList<String> tapResult = Lists.mutable.empty();
        ImmutableSortedMap<Integer, String> map = this.classUnderTest();
        Assert.assertSame(map, map.tap(tapResult::add));
        Assert.assertEquals(map.toList(), tapResult);

        MutableList<String> revTapResult = Lists.mutable.empty();
        ImmutableSortedMap<Integer, String> revMap = this.classUnderTest(REV_INT_COMPARATOR);
        Assert.assertSame(revMap, revMap.tap(revTapResult::add));
        Assert.assertEquals(revMap.toList(), revTapResult);
    }

    @Override
    @Test
    public void forEach()
    {
        super.forEach();

        MutableList<String> actualValues = Lists.mutable.empty();
        this.classUnderTest().forEach(CollectionAddProcedure.on(actualValues));
        Verify.assertListsEqual(this.expectedValues(), actualValues);

        MutableList<String> revActualValues = Lists.mutable.empty();
        this.classUnderTest(REV_INT_COMPARATOR).forEach(CollectionAddProcedure.on(revActualValues));
        Verify.assertListsEqual(this.expectedValues().reverseThis(), revActualValues);
    }

    @Override
    @Test
    public void iterator()
    {
        super.iterator();

        MutableList<String> actualValues = Lists.mutable.empty();
        for (String eachValue : this.classUnderTest())
        {
            actualValues.add(eachValue);
        }
        Verify.assertListsEqual(this.expectedValues(), actualValues);

        MutableList<String> revActualValues = Lists.mutable.empty();
        for (String eachValue : this.classUnderTest(REV_INT_COMPARATOR))
        {
            revActualValues.add(eachValue);
        }
        Verify.assertListsEqual(this.expectedValues().reverseThis(), revActualValues);
    }

    @Test
    public void iteratorThrows()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> {
            Iterator<String> iterator = this.classUnderTest().iterator();
            iterator.remove();
        });
    }

    @Override
    @Test
    public void forEachKey()
    {
        super.forEachKey();

        MutableList<Integer> actualKeys = Lists.mutable.empty();
        this.classUnderTest().forEachKey(CollectionAddProcedure.on(actualKeys));
        Verify.assertListsEqual(this.expectedKeys(), actualKeys);

        MutableList<Integer> revActualKeys = Lists.mutable.empty();
        this.classUnderTest(REV_INT_COMPARATOR).forEachKey(CollectionAddProcedure.on(revActualKeys));
        Verify.assertListsEqual(this.expectedKeys().reverseThis(), revActualKeys);
    }

    @Test
    public void get()
    {
        // Absent key behavior
        ImmutableSortedMap<Integer, String> classUnderTest = this.classUnderTest();

        Integer absentKey = this.size() + 1;
        Assert.assertNull(classUnderTest.get(absentKey));

        String absentValue = String.valueOf(absentKey);
        Assert.assertFalse(classUnderTest.containsValue(absentValue));

        // Present key behavior
        Assert.assertEquals("1", classUnderTest.get(1));

        // Still unchanged
        Assert.assertEquals(this.equalUnifiedMap(), classUnderTest);
    }

    @Override
    @Test
    public void getIfAbsent()
    {
        super.getIfAbsent();

        Integer absentKey = this.size() + 1;
        String absentValue = String.valueOf(absentKey);

        // Absent key behavior
        ImmutableSortedMap<Integer, String> classUnderTest = this.classUnderTest();
        Assert.assertEquals(absentValue, classUnderTest.getIfAbsent(absentKey, new PassThruFunction0<>(absentValue)));

        // Present key behavior
        Assert.assertEquals("1", classUnderTest.getIfAbsent(1, new PassThruFunction0<>(absentValue)));

        // Still unchanged
        Assert.assertEquals(this.equalUnifiedMap(), classUnderTest);
    }

    @Test
    public void getIfAbsentValue()
    {
        Integer absentKey = this.size() + 1;
        String absentValue = String.valueOf(absentKey);

        // Absent key behavior
        ImmutableSortedMap<Integer, String> classUnderTest = this.classUnderTest();
        Assert.assertEquals(absentValue, classUnderTest.getIfAbsentValue(absentKey, absentValue));

        // Present key behavior
        Assert.assertEquals("1", classUnderTest.getIfAbsentValue(1, absentValue));

        // Still unchanged
        Assert.assertEquals(this.equalUnifiedMap(), classUnderTest);
    }

    @Override
    @Test
    public void getIfAbsentWith()
    {
        super.getIfAbsentWith();

        Integer absentKey = this.size() + 1;
        String absentValue = String.valueOf(absentKey);

        // Absent key behavior
        ImmutableSortedMap<Integer, String> classUnderTest = this.classUnderTest();
        Assert.assertEquals(absentValue, classUnderTest.getIfAbsentWith(absentKey, String::valueOf, absentValue));

        // Present key behavior
        Assert.assertEquals("1", classUnderTest.getIfAbsentWith(1, String::valueOf, absentValue));

        // Still unchanged
        Assert.assertEquals(this.equalUnifiedMap(), classUnderTest);
    }

    @Override
    @Test
    public void forEachWith()
    {
        super.forEachWith();

        Object actualParameter = new Object();

        MutableList<String> actualValues = Lists.mutable.empty();
        MutableList<Object> actualParameters = Lists.mutable.empty();

        this.classUnderTest().forEachWith((eachValue, parameter) -> {
            actualValues.add(eachValue);
            actualParameters.add(parameter);
        }, actualParameter);

        Verify.assertListsEqual(this.expectedKeys().collect(String::valueOf), actualValues);
        Verify.assertListsEqual(Collections.nCopies(this.size(), actualParameter), actualParameters);

        MutableList<String> revActualValues = Lists.mutable.empty();
        MutableList<Object> revActualParameters = Lists.mutable.empty();

        this.classUnderTest(REV_INT_COMPARATOR).forEachWith((eachValue, parameter) -> {
            revActualValues.add(eachValue);
            revActualParameters.add(parameter);
        }, actualParameter);

        Verify.assertListsEqual(this.expectedKeys().collect(String::valueOf).reverseThis(), revActualValues);
        Verify.assertListsEqual(Collections.nCopies(this.size(), actualParameter), revActualParameters);
    }

    @Override
    @Test
    public void forEachWithIndex()
    {
        super.forEachWithIndex();

        MutableList<String> actualValues = Lists.mutable.empty();
        MutableList<Integer> actualIndices = Lists.mutable.empty();

        this.classUnderTest().forEachWithIndex((eachValue, index) -> {
            actualValues.add(eachValue);
            actualIndices.add(index);
        });

        Verify.assertListsEqual(this.expectedKeys().collect(String::valueOf), actualValues);
        Verify.assertListsEqual(this.expectedIndices(), actualIndices);

        MutableList<String> revActualValues = Lists.mutable.empty();
        MutableList<Integer> revActualIndices = Lists.mutable.empty();

        this.classUnderTest(REV_INT_COMPARATOR).forEachWithIndex((eachValue, index) -> {
            revActualValues.add(eachValue);
            revActualIndices.add(index);
        });

        Verify.assertListsEqual(this.expectedKeys().collect(String::valueOf).reverseThis(), revActualValues);
        Verify.assertListsEqual(this.expectedIndices(), revActualIndices);
    }

    @Override
    @Test
    public void valuesView()
    {
        super.valuesView();

        MutableList<String> actualValues = Lists.mutable.empty();
        for (String eachValue : this.classUnderTest().valuesView())
        {
            actualValues.add(eachValue);
        }
        MutableList<String> expectedValues = this.expectedValues();
        Verify.assertListsEqual(expectedValues, actualValues);

        MutableList<String> revActualValues = Lists.mutable.empty();
        for (String eachValue : this.classUnderTest(REV_INT_COMPARATOR).valuesView())
        {
            revActualValues.add(eachValue);
        }
        Verify.assertListsEqual(this.expectedValues().reverseThis(), revActualValues);
    }

    @Override
    @Test
    public void keysView()
    {
        super.keysView();

        MutableList<Integer> actualKeys = Lists.mutable.empty();
        for (Integer eachKey : this.classUnderTest().keysView())
        {
            actualKeys.add(eachKey);
        }
        Verify.assertListsEqual(this.expectedKeys(), actualKeys);

        MutableList<Integer> revActualKeys = Lists.mutable.empty();
        for (Integer eachKey : this.classUnderTest(REV_INT_COMPARATOR).keysView())
        {
            revActualKeys.add(eachKey);
        }
        Verify.assertListsEqual(this.expectedKeys().reverseThis(), revActualKeys);
    }

    @Test
    public void putAll()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> ((Map<Integer, String>) this.classUnderTest()).putAll(null));
    }

    @Test
    public void clear()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> ((Map<Integer, String>) this.classUnderTest()).clear());
    }

    @Test
    public void entrySet()
    {
        ImmutableSortedMap<Integer, String> immutableSortedMap = this.classUnderTest();
        Map<Integer, String> map = new HashMap<>(immutableSortedMap.castToSortedMap());
        Assert.assertEquals(map.entrySet(), immutableSortedMap.castToSortedMap().entrySet());

        Set<Map.Entry<Integer, String>> entries = immutableSortedMap.castToSortedMap().entrySet();
        MutableList<Map.Entry<Integer, String>> entriesList = FastList.newList(entries);
        MutableList<Map.Entry<Integer, String>> sortedEntryList = entriesList.toSortedListBy(Functions.getKeyFunction());
        Assert.assertEquals(sortedEntryList, entriesList);
    }

    @Override
    @Test
    public void selectMap()
    {
        super.selectMap();

        ImmutableSortedMap<Integer, String> map = this.classUnderTest();
        ImmutableSortedMap<Integer, String> select = map.select((argument1, argument2) -> argument1 < this.size());
        Verify.assertListsEqual(Interval.oneTo(this.size() - 1), select.keysView().toList());
        Verify.assertListsEqual(Interval.oneTo(this.size() - 1).collect(String::valueOf).toList(),
                select.valuesView().toList());

        ImmutableSortedMap<Integer, String> revMap = this.classUnderTest(REV_INT_COMPARATOR);
        ImmutableSortedMap<Integer, String> revSelect = revMap.select((argument1, argument2) -> argument1 < this.size());
        Verify.assertListsEqual(Interval.oneTo(this.size() - 1).reverseThis(), revSelect.keysView().toList());
        Verify.assertListsEqual(Interval.oneTo(this.size() - 1).collect(String::valueOf).toList().reverseThis(),
                revSelect.valuesView().toList());
    }

    @Override
    @Test
    public void rejectMap()
    {
        super.rejectMap();

        ImmutableSortedMap<Integer, String> map = this.classUnderTest();
        ImmutableSortedMap<Integer, String> reject = map.reject((argument1, argument2) -> argument1 == 1);
        Verify.assertListsEqual(Interval.fromTo(2, this.size()), reject.keysView().toList());
        Verify.assertListsEqual(Interval.fromTo(2, this.size()).collect(String::valueOf).toList(),
                reject.valuesView().toList());

        ImmutableSortedMap<Integer, String> revMap = this.classUnderTest(REV_INT_COMPARATOR);
        ImmutableSortedMap<Integer, String> revReject = revMap.reject((argument1, argument2) -> argument1 == 1);
        Verify.assertListsEqual(Interval.fromTo(2, this.size()).reverseThis(), revReject.keysView().toList());
        Verify.assertListsEqual(Interval.fromTo(2, this.size()).collect(String::valueOf).toList().reverseThis(),
                revReject.valuesView().toList());
    }

    @Override
    @Test
    public void collectMap()
    {
        super.collectMap();

        ImmutableSortedMap<Integer, String> map = this.classUnderTest();
        Function2<Integer, String, Pair<String, Integer>> function = (Integer argument1, String argument2) -> Tuples.pair(argument2, argument1);
        ImmutableMap<String, Integer> collect = map.collect(function);
        Verify.assertSetsEqual(Interval.oneTo(this.size()).collect(String::valueOf).toSet(), collect.keysView().toSet());
        Verify.assertSetsEqual(Interval.oneTo(this.size()).toSet(), collect.valuesView().toSet());

        ImmutableSortedMap<Integer, String> revMap = this.classUnderTest(REV_INT_COMPARATOR);
        ImmutableMap<String, Integer> revCollect = revMap.collect(function);
        Verify.assertSetsEqual(Interval.oneTo(this.size()).collect(String::valueOf).toSet(), revCollect.keysView().toSet());
        Verify.assertSetsEqual(Interval.oneTo(this.size()).toSet(), revCollect.valuesView().toSet());
    }

    @Override
    @Test
    public void collectValues()
    {
        super.collectValues();

        ImmutableSortedMap<Integer, String> map = this.classUnderTest();
        ImmutableSortedMap<Integer, Integer> result = map.collectValues((argument1, argument2) -> argument1);
        Verify.assertListsEqual(result.keysView().toList(), result.valuesView().toList());

        ImmutableSortedMap<Integer, String> revMap = this.classUnderTest(REV_INT_COMPARATOR);
        ImmutableSortedMap<Integer, Integer> revResult = revMap.collectValues((argument1, argument2) -> argument1);

        Verify.assertListsEqual(revResult.keysView().toList(), revResult.valuesView().toList());
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithIndex()
    {
        ImmutableSortedMap<Integer, String> integers = this.classUnderTest();
        Assert.assertEquals(
                Lists.mutable.with(
                        PrimitiveTuples.pair("1", 0),
                        PrimitiveTuples.pair("2", 1),
                        PrimitiveTuples.pair("3", 2),
                        PrimitiveTuples.pair("4", 3)),
                integers.collectWithIndex(PrimitiveTuples::pair));
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithIndexWithTarget()
    {
        ImmutableSortedMap<Integer, String> integers = this.classUnderTest();
        Assert.assertEquals(
                Lists.mutable.with(
                        PrimitiveTuples.pair("1", 0),
                        PrimitiveTuples.pair("2", 1),
                        PrimitiveTuples.pair("3", 2),
                        PrimitiveTuples.pair("4", 3)),
                integers.collectWithIndex(PrimitiveTuples::pair, Lists.mutable.empty()));
    }

    @Test
    public void newWithKeyValue()
    {
        ImmutableSortedMap<Integer, String> immutable = this.classUnderTest();
        ImmutableSortedMap<Integer, String> immutable2 = immutable.newWithKeyValue(Integer.MAX_VALUE, Integer.toString(Integer.MAX_VALUE));
        Verify.assertSize(immutable.size() + 1, immutable2.castToSortedMap());
    }

    @Test
    public void newWithAllKeyValuePairs()
    {
        ImmutableSortedMap<Integer, String> immutable = this.classUnderTest();
        ImmutableSortedMap<Integer, String> immutable2 = immutable.newWithAllKeyValueArguments(
                Tuples.pair(Integer.MAX_VALUE, Integer.toString(Integer.MAX_VALUE)),
                Tuples.pair(Integer.MIN_VALUE, Integer.toString(Integer.MIN_VALUE)));
        Verify.assertSize(immutable.size() + 2, immutable2.castToSortedMap());
    }

    @Test
    public void newWithAllKeyValues()
    {
        ImmutableSortedMap<Integer, String> immutable = this.classUnderTest();
        ImmutableSortedMap<Integer, String> immutable2 = immutable.newWithAllKeyValues(ArrayAdapter.newArrayWith(
                Tuples.pair(Integer.MAX_VALUE, Integer.toString(Integer.MAX_VALUE)),
                Tuples.pair(Integer.MIN_VALUE, Integer.toString(Integer.MIN_VALUE))));
        Verify.assertSize(immutable.size() + 2, immutable2.castToSortedMap());
    }

    @Test
    public void newWithoutKey()
    {
        ImmutableSortedMap<Integer, String> immutable = this.classUnderTest();
        ImmutableSortedMap<Integer, String> immutable3 = immutable.newWithoutKey(Integer.MAX_VALUE);
        Verify.assertSize(immutable.size(), immutable3.castToSortedMap());
    }

    @Test
    public void newWithoutAllKeys()
    {
        ImmutableSortedMap<Integer, String> immutable = this.classUnderTest();
        ImmutableSortedMap<Integer, String> immutable2 = immutable.newWithoutAllKeys(immutable.keysView());
        ImmutableSortedMap<Integer, String> immutable3 = immutable.newWithoutAllKeys(Lists.immutable.of());
        Assert.assertEquals(immutable, immutable3);
        Assert.assertEquals(Maps.immutable.of(), immutable2);
    }

    @Test
    public void toImmutable()
    {
        ImmutableSortedMap<Integer, String> immutableSortedMap = this.classUnderTest();
        Assert.assertSame(immutableSortedMap, immutableSortedMap.toImmutable());
    }

    @Test
    public void put()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> ((Map<Integer, String>) this.classUnderTest()).put(null, null));
    }

    @Test
    public void remove()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> ((Map<Integer, String>) this.classUnderTest()).remove(null));
    }

    @Test
    public abstract void testToString();

    @Test
    public void take()
    {
        ImmutableSortedMap<Integer, String> strings1 = this.classUnderTest();
        Assert.assertEquals(SortedMaps.immutable.of(strings1.comparator()), strings1.take(0));
        Assert.assertSame(strings1.comparator(), strings1.take(0).comparator());
        Assert.assertEquals(SortedMaps.immutable.of(strings1.comparator(), 1, "1", 2, "2", 3, "3"), strings1.take(3));
        Assert.assertSame(strings1.comparator(), strings1.take(3).comparator());
        Assert.assertEquals(SortedMaps.immutable.of(strings1.comparator(), 1, "1", 2, "2", 3, "3"), strings1.take(strings1.size() - 1));

        ImmutableSortedMap<Integer, String> strings2 = this.classUnderTest(Comparators.reverseNaturalOrder());
        Assert.assertSame(strings2, strings2.take(strings2.size()));
        Assert.assertSame(strings2, strings2.take(10));
        Assert.assertSame(strings2, strings2.take(Integer.MAX_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void take_throws()
    {
        this.classUnderTest().take(-1);
    }

    @Test
    public void drop()
    {
        ImmutableSortedMap<Integer, String> strings1 = this.classUnderTest();
        Assert.assertSame(strings1, strings1.drop(0));
        Assert.assertSame(strings1.comparator(), strings1.drop(0).comparator());
        Assert.assertEquals(SortedMaps.immutable.of(strings1.comparator(), 4, "4"), strings1.drop(3));
        Assert.assertSame(strings1.comparator(), strings1.drop(3).comparator());
        Assert.assertEquals(SortedMaps.immutable.of(strings1.comparator(), 4, "4"), strings1.drop(strings1.size() - 1));

        ImmutableSortedMap<Integer, String> expectedMap = SortedMaps.immutable.of(Comparators.reverseNaturalOrder());
        ImmutableSortedMap<Integer, String> strings2 = this.classUnderTest(Comparators.reverseNaturalOrder());
        Assert.assertEquals(expectedMap, strings2.drop(strings2.size()));
        Assert.assertEquals(expectedMap, strings2.drop(10));
        Assert.assertEquals(expectedMap, strings2.drop(Integer.MAX_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void drop_throws()
    {
        this.classUnderTest().drop(-1);
    }

    protected MutableMap<Integer, String> equalUnifiedMap()
    {
        MutableMap<Integer, String> expected = UnifiedMap.newMap();
        for (int i = 1; i <= this.size(); i++)
        {
            expected.put(i, String.valueOf(i));
        }
        return expected;
    }

    protected MutableSortedMap<Integer, String> equalSortedMap()
    {
        MutableSortedMap<Integer, String> expected = TreeSortedMap.newMap();
        for (int i = 1; i <= this.size(); i++)
        {
            expected.put(i, String.valueOf(i));
        }
        return expected;
    }

    private MutableList<String> expectedValues()
    {
        return this.expectedKeys().collect(String::valueOf);
    }

    private MutableList<Integer> expectedKeys()
    {
        if (this.size() == 0)
        {
            return Lists.mutable.empty();
        }
        return Interval.oneTo(this.size()).toList();
    }

    private MutableList<Integer> expectedIndices()
    {
        if (this.size() == 0)
        {
            return Lists.mutable.empty();
        }
        return Interval.zeroTo(this.size() - 1).toList();
    }
}
