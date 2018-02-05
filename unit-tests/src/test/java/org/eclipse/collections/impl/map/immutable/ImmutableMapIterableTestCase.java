/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.fixed.ArrayAdapter;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

public abstract class ImmutableMapIterableTestCase
{
    /**
     * @return A map containing 1 => "1", 2 => "2", etc.
     */
    protected abstract ImmutableMapIterable<Integer, String> classUnderTest();

    /**
     * @return Size (and max key) of {@link #classUnderTest()}.
     */
    protected abstract int size();

    @Test
    public void equalsAndHashCode()
    {
        MutableMap<Integer, String> expected = this.equalUnifiedMap();
        Verify.assertEqualsAndHashCode(expected, this.classUnderTest());
        Verify.assertPostSerializedEqualsAndHashCode(this.classUnderTest());
    }

    @Test
    public void forEachKeyValue()
    {
        MutableSet<Integer> actualKeys = UnifiedSet.newSet();
        MutableSet<String> actualValues = UnifiedSet.newSet();

        this.classUnderTest().forEachKeyValue((key, value) -> {
            actualKeys.add(key);
            actualValues.add(value);
        });

        MutableSet<Integer> expectedKeys = this.expectedKeys();
        Assert.assertEquals(expectedKeys, actualKeys);

        MutableSet<String> expectedValues = expectedKeys.collect(String::valueOf);
        Assert.assertEquals(expectedValues, actualValues);
    }

    @Test
    public void forEachValue()
    {
        MutableSet<String> actualValues = UnifiedSet.newSet();
        this.classUnderTest().forEachValue(CollectionAddProcedure.on(actualValues));
        Assert.assertEquals(this.expectedValues(), actualValues);
    }

    @Test
    public void tap()
    {
        MutableList<String> tapResult = Lists.mutable.of();
        ImmutableMapIterable<Integer, String> map = this.classUnderTest();
        Assert.assertSame(map, map.tap(tapResult::add));
        Assert.assertEquals(map.toList(), tapResult);
    }

    @Test
    public void forEach()
    {
        MutableSet<String> actualValues = UnifiedSet.newSet();
        this.classUnderTest().forEach(CollectionAddProcedure.on(actualValues));
        Assert.assertEquals(this.expectedValues(), actualValues);
    }

    @Test
    public void flipUniqueValues()
    {
        ImmutableMapIterable<Integer, String> immutableMap = this.classUnderTest();
        Assert.assertEquals(Interval.oneTo(this.size()).toMap(String::valueOf, Functions.getIntegerPassThru()),
                immutableMap.flipUniqueValues());
    }

    @Test
    public void iterator()
    {
        MutableSet<String> actualValues = UnifiedSet.newSet();
        for (String eachValue : this.classUnderTest())
        {
            actualValues.add(eachValue);
        }
        Assert.assertEquals(this.expectedValues(), actualValues);
    }

    @Test
    public void iteratorThrows()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> {
            Iterator<String> iterator = this.classUnderTest().iterator();
            iterator.remove();
        });
    }

    @Test
    public void forEachKey()
    {
        MutableSet<Integer> actualKeys = UnifiedSet.newSet();
        this.classUnderTest().forEachKey(CollectionAddProcedure.on(actualKeys));
        Assert.assertEquals(this.expectedKeys(), actualKeys);
    }

    @Test
    public void get()
    {
        // Absent key behavior
        ImmutableMapIterable<Integer, String> classUnderTest = this.classUnderTest();

        Integer absentKey = this.size() + 1;
        Assert.assertNull(classUnderTest.get(absentKey));

        String absentValue = String.valueOf(absentKey);
        Assert.assertFalse(classUnderTest.containsValue(absentValue));

        // Present key behavior
        Assert.assertEquals("1", classUnderTest.get(1));

        // Still unchanged
        Assert.assertEquals(this.equalUnifiedMap(), classUnderTest);
    }

    @Test
    public void getIfAbsent_function()
    {
        Integer absentKey = this.size() + 1;
        String absentValue = String.valueOf(absentKey);

        // Absent key behavior
        ImmutableMapIterable<Integer, String> classUnderTest = this.classUnderTest();
        Assert.assertEquals(absentValue, classUnderTest.getIfAbsent(absentKey, new PassThruFunction0<>(absentValue)));

        // Present key behavior
        Assert.assertEquals("1", classUnderTest.getIfAbsent(1, new PassThruFunction0<>(absentValue)));

        // Still unchanged
        Assert.assertEquals(this.equalUnifiedMap(), classUnderTest);
    }

    @Test
    public void getIfAbsent()
    {
        Integer absentKey = this.size() + 1;
        String absentValue = String.valueOf(absentKey);

        // Absent key behavior
        ImmutableMapIterable<Integer, String> classUnderTest = this.classUnderTest();
        Assert.assertEquals(absentValue, classUnderTest.getIfAbsentValue(absentKey, absentValue));

        // Present key behavior
        Assert.assertEquals("1", classUnderTest.getIfAbsentValue(1, absentValue));

        // Still unchanged
        Assert.assertEquals(this.equalUnifiedMap(), classUnderTest);
    }

    @Test
    public void getIfAbsentWith()
    {
        Integer absentKey = this.size() + 1;
        String absentValue = String.valueOf(absentKey);

        // Absent key behavior
        ImmutableMapIterable<Integer, String> classUnderTest = this.classUnderTest();
        Assert.assertEquals(absentValue, classUnderTest.getIfAbsentWith(absentKey, String::valueOf, absentValue));

        // Present key behavior
        Assert.assertEquals("1", classUnderTest.getIfAbsentWith(1, String::valueOf, absentValue));

        // Still unchanged
        Assert.assertEquals(this.equalUnifiedMap(), classUnderTest);
    }

    @Test
    public void ifPresentApply()
    {
        Integer absentKey = this.size() + 1;

        ImmutableMapIterable<Integer, String> classUnderTest = this.classUnderTest();
        Assert.assertNull(classUnderTest.ifPresentApply(absentKey, Functions.getPassThru()));
        Assert.assertEquals("1", classUnderTest.ifPresentApply(1, Functions.getPassThru()));
    }

    @Test
    public void notEmpty()
    {
        Assert.assertTrue(this.classUnderTest().notEmpty());
    }

    @Test
    public void forEachWith()
    {
        Object actualParameter = new Object();

        MutableSet<String> actualValues = UnifiedSet.newSet();
        MutableList<Object> actualParameters = Lists.mutable.of();

        this.classUnderTest().forEachWith((eachValue, parameter) -> {
            actualValues.add(eachValue);
            actualParameters.add(parameter);
        }, actualParameter);

        Assert.assertEquals(this.expectedKeys().collect(String::valueOf), actualValues);
        Assert.assertEquals(Collections.nCopies(this.size(), actualParameter), actualParameters);
    }

    @Test
    public void forEachWithIndex()
    {
        MutableSet<String> actualValues = UnifiedSet.newSet();
        MutableList<Integer> actualIndices = Lists.mutable.of();

        this.classUnderTest().forEachWithIndex((eachValue, index) -> {
            actualValues.add(eachValue);
            actualIndices.add(index);
        });

        Assert.assertEquals(this.expectedKeys().collect(String::valueOf), actualValues);
        Assert.assertEquals(this.expectedIndices(), actualIndices);
    }

    @Test
    public void keyValuesView()
    {
        MutableSet<Integer> actualKeys = UnifiedSet.newSet();
        MutableSet<String> actualValues = UnifiedSet.newSet();

        for (Pair<Integer, String> entry : this.classUnderTest().keyValuesView())
        {
            actualKeys.add(entry.getOne());
            actualValues.add(entry.getTwo());
        }

        MutableSet<Integer> expectedKeys = this.expectedKeys();
        Assert.assertEquals(expectedKeys, actualKeys);

        MutableSet<String> expectedValues = expectedKeys.collect(String::valueOf);
        Assert.assertEquals(expectedValues, actualValues);
    }

    @Test
    public void valuesView()
    {
        MutableSet<String> actualValues = UnifiedSet.newSet();
        for (String eachValue : this.classUnderTest().valuesView())
        {
            actualValues.add(eachValue);
        }
        MutableSet<String> expectedValues = this.expectedValues();
        Assert.assertEquals(expectedValues, actualValues);
    }

    @Test
    public void keysView()
    {
        MutableSet<Integer> actualKeys = UnifiedSet.newSet();
        for (Integer eachKey : this.classUnderTest().keysView())
        {
            actualKeys.add(eachKey);
        }
        Assert.assertEquals(this.expectedKeys(), actualKeys);
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

    protected MutableMap<Integer, String> equalUnifiedMap()
    {
        MutableMap<Integer, String> expected = UnifiedMap.newMap();
        for (int i = 1; i <= this.size(); i++)
        {
            expected.put(i, String.valueOf(i));
        }
        return expected;
    }

    private MutableSet<String> expectedValues()
    {
        return this.expectedKeys().collect(String::valueOf);
    }

    private MutableSet<Integer> expectedKeys()
    {
        if (this.size() == 0)
        {
            return UnifiedSet.newSet();
        }
        return Interval.oneTo(this.size()).toSet();
    }

    private List<Integer> expectedIndices()
    {
        if (this.size() == 0)
        {
            return Lists.mutable.of();
        }
        return Interval.zeroTo(this.size() - 1);
    }

    @Test
    public void newWithKeyValue()
    {
        ImmutableMapIterable<Integer, String> immutable = this.classUnderTest();
        ImmutableMapIterable<Integer, String> immutable2 = immutable.newWithKeyValue(Integer.MAX_VALUE, Integer.toString(Integer.MAX_VALUE));
        Verify.assertSize(immutable.size() + 1, immutable2);
    }

    @Test
    public void newWithAllKeyValuePairs()
    {
        ImmutableMapIterable<Integer, String> immutable = this.classUnderTest();
        ImmutableMapIterable<Integer, String> immutable2 = immutable.newWithAllKeyValueArguments(
                Tuples.pair(Integer.MAX_VALUE, Integer.toString(Integer.MAX_VALUE)),
                Tuples.pair(Integer.MIN_VALUE, Integer.toString(Integer.MIN_VALUE)));
        Verify.assertSize(immutable.size() + 2, immutable2);
    }

    @Test
    public void newWithAllKeyValues()
    {
        ImmutableMapIterable<Integer, String> immutable = this.classUnderTest();
        ImmutableMapIterable<Integer, String> immutable2 = immutable.newWithAllKeyValues(ArrayAdapter.newArrayWith(
                Tuples.pair(Integer.MAX_VALUE, Integer.toString(Integer.MAX_VALUE)),
                Tuples.pair(Integer.MIN_VALUE, Integer.toString(Integer.MIN_VALUE))));
        Verify.assertSize(immutable.size() + 2, immutable2);
    }

    @Test
    public void newWithoutKey()
    {
        ImmutableMapIterable<Integer, String> immutable = this.classUnderTest();
        ImmutableMapIterable<Integer, String> immutable3 = immutable.newWithoutKey(Integer.MAX_VALUE);
        Verify.assertSize(immutable.size(), immutable3);
    }

    @Test
    public void newWithoutKeys()
    {
        ImmutableMapIterable<Integer, String> immutable = this.classUnderTest();
        ImmutableMapIterable<Integer, String> immutable2 = immutable.newWithoutAllKeys(immutable.keysView());
        ImmutableMapIterable<Integer, String> immutable3 = immutable.newWithoutAllKeys(Lists.immutable.of());
        Assert.assertEquals(immutable, immutable3);
        Assert.assertEquals(Maps.immutable.of(), immutable2);
    }
}
