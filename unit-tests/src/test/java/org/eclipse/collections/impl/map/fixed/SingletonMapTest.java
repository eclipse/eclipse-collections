/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.fixed;

import java.util.Map;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.FixedSizeMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link SingletonMap}.
 */
public class SingletonMapTest extends AbstractMemoryEfficientMutableMapTest
{
    @Override
    protected MutableMap<String, String> classUnderTest()
    {
        return new SingletonMap<>("1", "One");
    }

    @Override
    protected MutableMap<String, Integer> mixedTypeClassUnderTest()
    {
        return new SingletonMap<>("1", 1);
    }

    @Override
    @Test
    public void containsValue()
    {
        Assert.assertTrue(this.classUnderTest().containsValue("One"));
    }

    @Override
    @Test
    public void forEachKeyValue()
    {
        MutableList<String> collection = Lists.mutable.of();
        MutableMap<Integer, String> map = new SingletonMap<>(1, "One");
        map.forEachKeyValue((key, value) -> collection.add(key + value));
        Assert.assertEquals(FastList.newListWith("1One"), collection);
    }

    @Override
    @Test
    public void nonUniqueWithKeyValue()
    {
        Twin<String> twin1 = Tuples.twin("1", "1");
        Twin<String> twin2 = Tuples.twin("1", "1");
        SingletonMap<Twin<String>, Twin<String>> map = new SingletonMap<>(twin1, twin1);
        map.withKeyValue(twin2, twin2);
        Assert.assertSame(map.getKey1(), twin1);
        Assert.assertSame(map.get(twin1), twin2);
    }

    @Override
    @Test
    public void withKeyValue()
    {
        MutableMap<Integer, String> map1 = new SingletonMap<>(1, "A").withKeyValue(2, "B");
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues(1, "A", 2, "B"), map1);
        Verify.assertInstanceOf(DoubletonMap.class, map1);

        MutableMap<Integer, String> map2 = new SingletonMap<>(1, "A");
        MutableMap<Integer, String> map2with = map2.withKeyValue(1, "AA");
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues(1, "AA"), map2with);
        Assert.assertSame(map2, map2with);
    }

    @Override
    @Test
    public void withAllKeyValueArguments()
    {
        MutableMap<Integer, String> map1 = new SingletonMap<>(1, "A").withAllKeyValueArguments(
                Tuples.pair(1, "AA"), Tuples.pair(2, "B"));
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues(1, "AA", 2, "B"), map1);
        Verify.assertInstanceOf(DoubletonMap.class, map1);

        MutableMap<Integer, String> map2 = new SingletonMap<>(1, "A");
        MutableMap<Integer, String> map2with = map2.withAllKeyValueArguments(Tuples.pair(1, "AA"));
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues(1, "AA"), map2with);
        Assert.assertSame(map2, map2with);
    }

    @Override
    @Test
    public void withoutKey()
    {
        MutableMap<Integer, String> map = new SingletonMap<>(1, "A");
        MutableMap<Integer, String> mapWithout1 = map.withoutKey(2);
        Assert.assertSame(map, mapWithout1);
        MutableMap<Integer, String> mapWithout2 = map.withoutKey(1);
        Verify.assertMapsEqual(UnifiedMap.newMap(), mapWithout2);
        Verify.assertInstanceOf(EmptyMap.class, mapWithout2);
    }

    @Override
    @Test
    public void withoutAllKeys()
    {
        MutableMap<Integer, String> map = new SingletonMap<>(1, "A");
        MutableMap<Integer, String> mapWithout1 = map.withoutAllKeys(FastList.newListWith(2, 3));
        Assert.assertSame(map, mapWithout1);
        MutableMap<Integer, String> mapWithout2 = map.withoutAllKeys(FastList.newListWith(1, 2));
        Verify.assertMapsEqual(UnifiedMap.newMap(), mapWithout2);
        Verify.assertInstanceOf(EmptyMap.class, mapWithout2);
    }

    @Override
    @Test
    public void forEachValue()
    {
        MutableList<String> collection = Lists.mutable.of();
        MutableMap<Integer, String> map = new SingletonMap<>(1, "1");
        map.forEachValue(CollectionAddProcedure.on(collection));
        Assert.assertEquals(FastList.newListWith("1"), collection);
    }

    @Override
    @Test
    public void forEach()
    {
        MutableList<String> collection = Lists.mutable.of();
        MutableMap<Integer, String> map = new SingletonMap<>(1, "1");
        map.forEach(CollectionAddProcedure.on(collection));
        Assert.assertEquals(FastList.newListWith("1"), collection);
    }

    @Override
    @Test
    public void iterator()
    {
        MutableList<String> collection = Lists.mutable.of();
        MutableMap<Integer, String> map = new SingletonMap<>(1, "1");
        for (String eachValue : map)
        {
            collection.add(eachValue);
        }
        Assert.assertEquals(FastList.newListWith("1"), collection);
    }

    @Override
    @Test
    public void forEachKey()
    {
        MutableList<Integer> collection = Lists.mutable.of();
        MutableMap<Integer, String> map = new SingletonMap<>(1, "1");
        map.forEachKey(CollectionAddProcedure.on(collection));
        Assert.assertEquals(FastList.newListWith(1), collection);
    }

    @Override
    @Test
    public void getIfAbsentPut()
    {
        MutableMap<Integer, String> map = new SingletonMap<>(1, "1");
        Verify.assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPut(4, new PassThruFunction0<>("4")));
        Assert.assertEquals("1", map.getIfAbsentPut(1, new PassThruFunction0<>("1")));
    }

    @Override
    @Test
    public void getIfAbsentPutWith()
    {
        MutableMap<Integer, String> map = new SingletonMap<>(1, "1");
        Verify.assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPutWith(4, String::valueOf, 4));
        Assert.assertEquals("1", map.getIfAbsentPutWith(1, String::valueOf, 1));
    }

    @Override
    @Test
    public void getIfAbsent_function()
    {
        MutableMap<Integer, String> map = new SingletonMap<>(1, "1");
        Assert.assertNull(map.get(4));
        Assert.assertEquals("4", map.getIfAbsent(4, new PassThruFunction0<>("4")));
        Assert.assertEquals("1", map.getIfAbsent(1, new PassThruFunction0<>("1")));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, "1"), map);
    }

    @Override
    @Test
    public void getIfAbsent()
    {
        MutableMap<Integer, String> map = new SingletonMap<>(1, "1");
        Assert.assertNull(map.get(4));
        Assert.assertEquals("4", map.getIfAbsentValue(4, "4"));
        Assert.assertEquals("1", map.getIfAbsentValue(1, "1"));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, "1"), map);
    }

    @Override
    @Test
    public void getIfAbsentWith()
    {
        MutableMap<Integer, String> map = new SingletonMap<>(1, "1");
        Assert.assertNull(map.get(4));
        Assert.assertEquals("4", map.getIfAbsentWith(4, String::valueOf, 4));
        Assert.assertEquals("1", map.getIfAbsentWith(1, String::valueOf, 1));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, "1"), map);
    }

    @Override
    @Test
    public void ifPresentApply()
    {
        MutableMap<Integer, String> map = new SingletonMap<>(1, "1");
        Assert.assertNull(map.ifPresentApply(4, Functions.getPassThru()));
        Assert.assertEquals("1", map.ifPresentApply(1, Functions.getPassThru()));
    }

    @Override
    @Test
    public void notEmpty()
    {
        Assert.assertTrue(new SingletonMap<>(1, "1").notEmpty());
    }

    @Override
    @Test
    public void forEachWith()
    {
        MutableList<Integer> result = Lists.mutable.of();
        MutableMap<Integer, Integer> map = new SingletonMap<>(1, 1);
        map.forEachWith((argument1, argument2) -> result.add(argument1 + argument2), 10);
        Assert.assertEquals(FastList.newListWith(11), result);
    }

    @Override
    @Test
    public void forEachWithIndex()
    {
        MutableList<String> result = Lists.mutable.of();
        MutableMap<Integer, String> map = new SingletonMap<>(1, "One");
        map.forEachWithIndex((value, index) -> {
            result.add(value);
            result.add(String.valueOf(index));
        });
        Assert.assertEquals(FastList.newListWith("One", "0"), result);
    }

    @Override
    @Test
    public void entrySet()
    {
        MutableList<String> result = Lists.mutable.of();
        MutableMap<Integer, String> map = new SingletonMap<>(1, "One");
        for (Map.Entry<Integer, String> entry : map.entrySet())
        {
            result.add(entry.getValue());
        }
        Assert.assertEquals(FastList.newListWith("One"), result);
    }

    @Override
    @Test
    public void values()
    {
        MutableList<String> result = Lists.mutable.of();
        MutableMap<Integer, String> map = new SingletonMap<>(1, "One");
        for (String value : map.values())
        {
            result.add(value);
        }
        Assert.assertEquals(FastList.newListWith("One"), result);
    }

    @Override
    @Test
    public void keySet()
    {
        MutableList<Integer> result = Lists.mutable.of();
        MutableMap<Integer, String> map = new SingletonMap<>(1, "One");
        for (Integer key : map.keySet())
        {
            result.add(key);
        }
        Assert.assertEquals(FastList.newListWith(1), result);
    }

    @Override
    @Test
    public void testToString()
    {
        Assert.assertEquals("{1=One}", new SingletonMap<>(1, "One").toString());
        Assert.assertEquals("{1=null}", new SingletonMap<Integer, String>(1, null).toString());
        Assert.assertEquals("{null=One}", new SingletonMap<Integer, String>(null, "One").toString());
    }

    @Override
    @Test
    public void testEqualsAndHashCode()
    {
        Verify.assertEqualsAndHashCode(
                UnifiedMap.newWithKeysValues("1", "One"),
                this.classUnderTest());
    }

    @Override
    @Test
    public void asLazyKeys()
    {
        MutableList<Integer> keys = Maps.fixedSize.of(1, 1).keysView().toSortedList();
        Assert.assertEquals(FastList.newListWith(1), keys);
    }

    @Override
    @Test
    public void asLazyValues()
    {
        MutableList<Integer> values = Maps.fixedSize.of(1, 1).valuesView().toSortedList();
        Assert.assertEquals(FastList.newListWith(1), values);
    }

    @Override
    @Test
    public void select()
    {
        MutableMap<String, String> map = this.classUnderTest();

        MutableMap<String, String> empty = map.select((ignored1, ignored2) -> false);
        Verify.assertInstanceOf(EmptyMap.class, empty);

        MutableMap<String, String> full = map.select((ignored1, ignored2) -> true);
        Verify.assertInstanceOf(SingletonMap.class, full);
        Assert.assertEquals(map, full);
    }

    @Override
    @Test
    public void reject()
    {
        MutableMap<String, String> map = this.classUnderTest();

        MutableMap<String, String> empty = map.reject((ignored1, ignored2) -> true);
        Verify.assertInstanceOf(EmptyMap.class, empty);

        MutableMap<String, String> full = map.reject((ignored1, ignored2) -> false);
        Verify.assertInstanceOf(SingletonMap.class, full);
        Assert.assertEquals(map, full);
    }

    @Override
    @Test
    public void detect()
    {
        MutableMap<String, String> map = this.classUnderTest();

        Pair<String, String> actual = map.detect((ignored1, ignored2) -> true);
        Assert.assertEquals(Tuples.pair("1", "One"), actual);

        Assert.assertNull(map.detect((ignored1, ignored2) -> false));
    }

    @Test
    public void flipUniqueValues()
    {
        MutableMap<String, String> flip = new SingletonMap<>("1", "One").flipUniqueValues();
        Verify.assertInstanceOf(SingletonMap.class, flip);
        Assert.assertEquals(UnifiedMap.newWithKeysValues("One", "1"), flip);
    }

    @Test
    public void getOnly()
    {
        String only = this.classUnderTest().getOnly();
        Assert.assertEquals("One", only);
    }

    @Override
    protected <K, V> FixedSizeMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return new SingletonMap<>(key1, value1);
    }

    @Override
    protected <K, V> FixedSizeMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return new SingletonMap<>(key1, value1);
    }
}
