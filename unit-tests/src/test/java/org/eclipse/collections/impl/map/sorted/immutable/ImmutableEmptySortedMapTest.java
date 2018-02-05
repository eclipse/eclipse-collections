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

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.sorted.ImmutableSortedMap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.SortedMaps;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ImmutableEmptySortedMap}.
 */
public class ImmutableEmptySortedMapTest extends ImmutableSortedMapTestCase
{
    @Override
    protected ImmutableSortedMap<Integer, String> classUnderTest()
    {
        return SortedMaps.immutable.of();
    }

    @Override
    protected ImmutableSortedMap<Integer, String> classUnderTest(Comparator<? super Integer> comparator)
    {
        return SortedMaps.immutable.of(comparator);
    }

    @Override
    protected <K, V> MapIterable<K, V> newMap()
    {
        return SortedMaps.immutable.of();
    }

    @Override
    protected <K, V> MapIterable<K, V> newMapWithKeyValue(K key1, V value1)
    {
        return SortedMaps.immutable.of(key1, value1);
    }

    @Override
    protected <K, V> MapIterable<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return SortedMaps.immutable.of(key1, value1, key2, value2);
    }

    @Override
    protected <K, V> MapIterable<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return SortedMaps.immutable.of(key1, value1, key2, value2, key3, value3);
    }

    @Override
    protected <K, V> MapIterable<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return SortedMaps.immutable.of(key1, value1, key2, value2, key3, value3, key4, value4);
    }

    @Override
    protected int size()
    {
        return 0;
    }

    @Override
    public void flipUniqueValues()
    {
        Verify.assertEmpty(this.classUnderTest().flipUniqueValues());
    }

    @Override
    @Test
    public void testToString()
    {
        ImmutableSortedMap<Integer, String> map = this.classUnderTest();
        Assert.assertEquals("{}", map.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void firstKey()
    {
        new ImmutableEmptySortedMap<>().firstKey();
    }

    @Test(expected = NoSuchElementException.class)
    public void lastKey()
    {
        new ImmutableEmptySortedMap<>().lastKey();
    }

    @Override
    @Test
    public void get()
    {
        // Cannot call super.get() as map is empty and present key behavior does not exist.

        // Absent key behavior
        ImmutableSortedMap<Integer, String> classUnderTest = this.classUnderTest();

        Integer absentKey = this.size() + 1;
        Assert.assertNull(classUnderTest.get(absentKey));

        String absentValue = String.valueOf(absentKey);
        Assert.assertFalse(classUnderTest.containsValue(absentValue));

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

        // Still unchanged
        Assert.assertEquals(this.equalUnifiedMap(), classUnderTest);
    }

    @Override
    @Test
    public void ifPresentApply()
    {
        super.ifPresentApply();

        Integer absentKey = this.size() + 1;

        ImmutableSortedMap<Integer, String> classUnderTest = this.classUnderTest();
        Assert.assertNull(classUnderTest.ifPresentApply(absentKey, Functions.getPassThru()));
    }

    @Override
    @Test
    public void notEmpty()
    {
        //Cannot call super.notEmpty() as map is empty.
        Assert.assertFalse(this.classUnderTest().notEmpty());
    }

    @Override
    @Test
    public void allSatisfy()
    {
        super.allSatisfy();

        ImmutableSortedMap<String, String> map = new ImmutableEmptySortedMap<>();

        Assert.assertTrue(map.allSatisfy(String.class::isInstance));
        Assert.assertTrue(map.allSatisfy("Monkey"::equals));
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        super.noneSatisfy();

        ImmutableSortedMap<String, String> map = new ImmutableEmptySortedMap<>();

        Assert.assertTrue(map.noneSatisfy(Integer.class::isInstance));
        Assert.assertTrue(map.noneSatisfy("Monkey"::equals));
    }

    @Override
    @Test
    public void anySatisfy()
    {
        super.anySatisfy();

        ImmutableSortedMap<String, String> map = new ImmutableEmptySortedMap<>();

        Assert.assertFalse(map.anySatisfy(String.class::isInstance));
        Assert.assertFalse(map.anySatisfy("Monkey"::equals));
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void max()
    {
        super.max();

        this.classUnderTest().max();
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void maxBy()
    {
        super.maxBy();

        this.classUnderTest().maxBy(Functions.getStringPassThru());
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void min()
    {
        super.min();

        this.classUnderTest().min();
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void minBy()
    {
        super.minBy();

        this.classUnderTest().minBy(Functions.getStringPassThru());
    }

    @Override
    @Test
    public void selectMap()
    {
        ImmutableSortedMap<Integer, String> map = this.classUnderTest();
        ImmutableSortedMap<Integer, String> actual = map.select((ignored1, ignored2) -> true);
        Verify.assertInstanceOf(ImmutableEmptySortedMap.class, actual);
        Assert.assertSame(ImmutableEmptySortedMap.INSTANCE, actual);

        ImmutableSortedMap<Integer, String> revMap = this.classUnderTest(Comparators.reverseNaturalOrder());
        ImmutableSortedMap<Integer, String> revActual = revMap.select((ignored1, ignored2) -> true);
        Verify.assertInstanceOf(ImmutableEmptySortedMap.class, revActual);
        Assert.assertSame(revMap.comparator(), revActual.comparator());
    }

    @Override
    @Test
    public void rejectMap()
    {
        ImmutableSortedMap<Integer, String> map = this.classUnderTest();
        ImmutableSortedMap<Integer, String> actual = map.reject((ignored1, ignored2) -> false);
        Verify.assertInstanceOf(ImmutableEmptySortedMap.class, actual);
        Assert.assertSame(ImmutableEmptySortedMap.INSTANCE, actual);

        ImmutableSortedMap<Integer, String> revMap = this.classUnderTest(Comparators.reverseNaturalOrder());
        ImmutableSortedMap<Integer, String> revActual = revMap.reject((ignored1, ignored2) -> true);
        Verify.assertInstanceOf(ImmutableEmptySortedMap.class, revActual);
        Assert.assertSame(revMap.comparator(), revActual.comparator());
    }

    @Override
    @Test
    public void collectMap()
    {
        ImmutableSortedMap<Integer, String> map = this.classUnderTest();
        ImmutableSortedMap<Integer, String> revMap = this.classUnderTest(Comparators.reverseNaturalOrder());

        Function2<Integer, String, Pair<Integer, String>> alwaysTrueFunction = Tuples::pair;
        ImmutableMap<Integer, String> collect = map.collect(alwaysTrueFunction);
        ImmutableMap<Integer, String> revCollect = revMap.collect(alwaysTrueFunction);

        Verify.assertEmpty(collect);
        Assert.assertSame(collect, revCollect);
    }

    /**
     * @since 9.1.
     */
    @Override
    @Test
    public void collectWithIndex()
    {
        ImmutableSortedMap<Integer, String> integers = this.classUnderTest();
        Assert.assertEquals(
                Lists.mutable.empty(),
                integers.collectWithIndex(PrimitiveTuples::pair));
    }

    /**
     * @since 9.1.
     */
    @Override
    @Test
    public void collectWithIndexWithTarget()
    {
        ImmutableSortedMap<Integer, String> integers = this.classUnderTest();
        Assert.assertEquals(
                Lists.mutable.empty(),
                integers.collectWithIndex(PrimitiveTuples::pair, Lists.mutable.empty()));
    }

    @Override
    @Test
    public void detect()
    {
        super.detect();

        ImmutableSortedMap<Integer, String> map = this.classUnderTest();
        Assert.assertNull(map.detect((ignored1, ignored2) -> true));
    }

    @Override
    @Test
    public void containsKey()
    {
        super.containsKey();

        ImmutableSortedMap<Integer, String> map = this.classUnderTest();
        ImmutableSortedMap<Integer, String> revMap = this.classUnderTest(Comparators.reverseNaturalOrder());
        Assert.assertFalse(map.containsKey(0));
        Assert.assertFalse(revMap.containsKey(1));
    }

    @Test
    public void values()
    {
        ImmutableEmptySortedMap<Integer, String> map = (ImmutableEmptySortedMap<Integer, String>)
                this.classUnderTest();
        ImmutableEmptySortedMap<Integer, String> revMap = (ImmutableEmptySortedMap<Integer, String>)
                this.classUnderTest(Comparators.reverseNaturalOrder());

        Verify.assertEmpty(map.values());
        Assert.assertSame(Lists.immutable.of(), map.values());

        Verify.assertEmpty(revMap.values());

        Assert.assertSame(Lists.immutable.of(), revMap.values());
    }

    @Override
    @Test
    public void serialization()
    {
        super.serialization();

        ImmutableSortedMap<Integer, String> map = this.classUnderTest();
        ImmutableSortedMap<Integer, String> deserialized = SerializeTestHelper.serializeDeserialize(map);
        Assert.assertSame(ImmutableEmptySortedMap.INSTANCE, map);
        Assert.assertSame(map, deserialized);

        ImmutableSortedMap<Integer, String> revMap = this.classUnderTest(Comparators.reverseNaturalOrder());
        ImmutableSortedMap<Integer, String> revDeserialized = SerializeTestHelper.serializeDeserialize(revMap);
        Verify.assertInstanceOf(ImmutableSortedMap.class, revDeserialized);
        Assert.assertNotNull(revDeserialized.comparator());
    }

    @Override
    @Test
    public void keyValuesView()
    {
        super.keyValuesView();

        Assert.assertTrue(this.classUnderTest().keyValuesView().isEmpty());
    }

    @Override
    @Test
    public void take()
    {
        Assert.assertEquals(this.classUnderTest(), this.classUnderTest().take(2));
    }

    @Override
    @Test
    public void drop()
    {
        Assert.assertEquals(this.classUnderTest(), this.classUnderTest().drop(2));
    }

    @Test
    public void getOnly()
    {
        Verify.assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOnly());
    }
}
