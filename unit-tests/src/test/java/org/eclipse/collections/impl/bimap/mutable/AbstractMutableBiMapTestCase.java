/*
 * Copyright (c) 2017 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.mutable;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.bimap.BiMap;
import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.IntegerWithCast;
import org.eclipse.collections.impl.factory.BiMaps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.map.mutable.MutableMapIterableTestCase;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractMutableBiMapTestCase extends MutableMapIterableTestCase
{
    public abstract MutableBiMap<Integer, Character> classUnderTest();

    public abstract MutableBiMap<Integer, Character> getEmptyMap();

    @Override
    protected abstract <K, V> MutableBiMap<K, V> newMap();

    @Override
    protected abstract <K, V> MutableBiMap<K, V> newMapWithKeyValue(K key, V value);

    @Override
    protected abstract <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2);

    @Override
    protected abstract <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3);

    @Override
    protected abstract <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4);

    public static void assertBiMapsEqual(BiMap<?, ?> expected, BiMap<?, ?> actual)
    {
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected.inverse(), actual.inverse());
    }

    @Test
    @Override
    public void flip()
    {
        Verify.assertEmpty(this.newMap().flip());

        MutableSetMultimap<Integer, String> expected = UnifiedSetMultimap.newMultimap();
        expected.put(1, "One");
        expected.put(2, "Two");
        expected.put(3, "Three");
        expected.put(4, "Four");

        Assert.assertEquals(
                expected,
                this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3, "Four", 4).flip());
    }

    @Test
    public void size()
    {
        Verify.assertSize(3, this.classUnderTest());
        Verify.assertSize(0, this.getEmptyMap());
    }

    @Test
    public void forcePut()
    {
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();
        Assert.assertNull(biMap.forcePut(4, 'd'));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, null, null, 'b', 3, 'c', 4, 'd'), biMap);
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, null, null, 'b', 3, 'c', 4, 'd'), biMap);

        Assert.assertNull(biMap.forcePut(1, null));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, null, null, 'b', 3, 'c', 4, 'd'), biMap);

        Assert.assertNull(biMap.forcePut(1, 'e'));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, 'e', null, 'b', 3, 'c', 4, 'd'), biMap);

        Assert.assertNull(biMap.forcePut(5, 'e'));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(5, 'e', null, 'b', 3, 'c', 4, 'd'), biMap);

        Assert.assertEquals(Character.valueOf('d'), biMap.forcePut(4, 'e'));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(4, 'e', null, 'b', 3, 'c'), biMap);

        HashBiMap<Integer, Character> actual = HashBiMap.newMap();
        actual.forcePut(1, null);
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, null), actual);
    }

    @Override
    @Test
    public void put()
    {
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();
        Assert.assertNull(biMap.put(4, 'd'));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, null, null, 'b', 3, 'c', 4, 'd'), biMap);
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, null, null, 'b', 3, 'c', 4, 'd'), biMap);

        Assert.assertNull(biMap.put(1, null));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, null, null, 'b', 3, 'c', 4, 'd'), biMap);

        Assert.assertNull(biMap.put(1, 'e'));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, 'e', null, 'b', 3, 'c', 4, 'd'), biMap);

        Verify.assertThrows(IllegalArgumentException.class, () -> biMap.put(5, 'e'));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, 'e', null, 'b', 3, 'c', 4, 'd'), biMap);

        Verify.assertThrows(IllegalArgumentException.class, () -> biMap.put(4, 'e'));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, 'e', null, 'b', 3, 'c', 4, 'd'), biMap);

        HashBiMap<Integer, Character> actual = HashBiMap.newMap();
        actual.put(1, null);
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, null), actual);
    }

    @Override
    @Test
    public void flipUniqueValues()
    {
        MutableBiMap<Integer, Character> map = this.classUnderTest();
        MutableBiMap<Character, Integer> result = map.flipUniqueValues();
        Assert.assertEquals(map.inverse(), result);
        Assert.assertNotSame(map.inverse(), result);
        result.put('d', 4);
        Assert.assertEquals(this.classUnderTest(), map);
    }

    @Test
    public void get()
    {
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();
        Assert.assertNull(biMap.get(1));
        Assert.assertEquals(Character.valueOf('b'), biMap.get(null));
        Assert.assertEquals(Character.valueOf('c'), biMap.get(3));
        Assert.assertNull(biMap.get(4));

        Assert.assertNull(biMap.put(4, 'd'));
        Assert.assertNull(biMap.get(1));
        Assert.assertEquals(Character.valueOf('b'), biMap.get(null));
        Assert.assertEquals(Character.valueOf('c'), biMap.get(3));
        Assert.assertEquals(Character.valueOf('d'), biMap.get(4));

        Assert.assertNull(biMap.put(1, null));
        Assert.assertNull(biMap.get(1));
        Assert.assertEquals(Character.valueOf('b'), biMap.get(null));
        Assert.assertEquals(Character.valueOf('c'), biMap.get(3));
        Assert.assertEquals(Character.valueOf('d'), biMap.get(4));

        Assert.assertNull(biMap.forcePut(1, 'e'));
        Assert.assertEquals(Character.valueOf('e'), biMap.get(1));
        Assert.assertEquals(Character.valueOf('b'), biMap.get(null));
        Assert.assertEquals(Character.valueOf('c'), biMap.get(3));
        Assert.assertEquals(Character.valueOf('d'), biMap.get(4));

        Assert.assertNull(biMap.forcePut(5, 'e'));
        Assert.assertNull(biMap.get(1));
        Assert.assertEquals(Character.valueOf('e'), biMap.get(5));
        Assert.assertEquals(Character.valueOf('b'), biMap.get(null));
        Assert.assertEquals(Character.valueOf('c'), biMap.get(3));
        Assert.assertEquals(Character.valueOf('d'), biMap.get(4));

        Assert.assertEquals(Character.valueOf('d'), biMap.forcePut(4, 'e'));
        Assert.assertNull(biMap.get(1));
        Assert.assertNull(biMap.get(5));
        Assert.assertEquals(Character.valueOf('b'), biMap.get(null));
        Assert.assertEquals(Character.valueOf('c'), biMap.get(3));
        Assert.assertEquals(Character.valueOf('e'), biMap.get(4));

        HashBiMap<Integer, Character> actual = HashBiMap.newMap();
        Assert.assertNull(actual.get(1));
        actual.put(1, null);
        Assert.assertNull(actual.get(1));
    }

    @Override
    @Test
    public void containsKey()
    {
        super.containsKey();

        MutableBiMap<Integer, Character> biMap = this.classUnderTest();

        Assert.assertTrue(biMap.containsKey(1));
        Assert.assertTrue(biMap.containsKey(null));
        Assert.assertTrue(biMap.containsKey(3));
        Assert.assertFalse(biMap.containsKey(4));

        Assert.assertNull(biMap.put(4, 'd'));
        Assert.assertTrue(biMap.containsKey(1));
        Assert.assertTrue(biMap.containsKey(null));
        Assert.assertTrue(biMap.containsKey(3));
        Assert.assertTrue(biMap.containsKey(4));

        Assert.assertNull(biMap.put(1, null));
        Assert.assertTrue(biMap.containsKey(1));
        Assert.assertTrue(biMap.containsKey(null));
        Assert.assertTrue(biMap.containsKey(3));
        Assert.assertTrue(biMap.containsKey(4));

        Assert.assertNull(biMap.forcePut(1, 'e'));
        Assert.assertTrue(biMap.containsKey(1));
        Assert.assertTrue(biMap.containsKey(null));
        Assert.assertTrue(biMap.containsKey(3));
        Assert.assertTrue(biMap.containsKey(4));

        Assert.assertNull(biMap.forcePut(5, 'e'));
        Assert.assertFalse(biMap.containsKey(1));
        Assert.assertTrue(biMap.containsKey(5));
        Assert.assertTrue(biMap.containsKey(null));
        Assert.assertTrue(biMap.containsKey(3));
        Assert.assertTrue(biMap.containsKey(4));

        Assert.assertEquals(Character.valueOf('d'), biMap.forcePut(4, 'e'));
        Assert.assertFalse(biMap.containsKey(1));
        Assert.assertTrue(biMap.containsKey(null));
        Assert.assertTrue(biMap.containsKey(3));
        Assert.assertTrue(biMap.containsKey(4));
        Assert.assertFalse(biMap.containsKey(5));

        HashBiMap<Integer, Character> actual = HashBiMap.newMap();
        actual.put(1, null);
        Assert.assertTrue(actual.containsKey(1));
        Assert.assertFalse(actual.containsKey(0));
    }

    @Override
    @Test
    public void containsValue()
    {
        super.containsValue();

        MutableBiMap<Integer, Character> biMap = this.classUnderTest();

        Assert.assertTrue(biMap.containsValue(null));
        Assert.assertTrue(biMap.containsValue('b'));
        Assert.assertTrue(biMap.containsValue('c'));
        Assert.assertFalse(biMap.containsValue('d'));

        Assert.assertNull(biMap.put(4, 'd'));
        Assert.assertTrue(biMap.containsValue(null));
        Assert.assertTrue(biMap.containsValue('b'));
        Assert.assertTrue(biMap.containsValue('c'));
        Assert.assertTrue(biMap.containsValue('d'));

        Assert.assertNull(biMap.put(1, null));
        Assert.assertTrue(biMap.containsValue(null));
        Assert.assertTrue(biMap.containsValue('b'));
        Assert.assertTrue(biMap.containsValue('c'));
        Assert.assertTrue(biMap.containsValue('d'));

        Assert.assertNull(biMap.forcePut(1, 'e'));
        Assert.assertTrue(biMap.containsValue('e'));
        Assert.assertFalse(biMap.containsValue(null));
        Assert.assertTrue(biMap.containsValue('b'));
        Assert.assertTrue(biMap.containsValue('c'));
        Assert.assertTrue(biMap.containsValue('d'));

        Assert.assertNull(biMap.forcePut(5, 'e'));
        Assert.assertFalse(biMap.containsValue(null));
        Assert.assertTrue(biMap.containsValue('e'));
        Assert.assertTrue(biMap.containsValue('b'));
        Assert.assertTrue(biMap.containsValue('c'));
        Assert.assertTrue(biMap.containsValue('d'));

        Assert.assertEquals(Character.valueOf('d'), biMap.forcePut(4, 'e'));
        Assert.assertFalse(biMap.containsValue(null));
        Assert.assertTrue(biMap.containsValue('e'));
        Assert.assertTrue(biMap.containsValue('b'));
        Assert.assertTrue(biMap.containsValue('c'));
        Assert.assertFalse(biMap.containsValue('d'));

        HashBiMap<Integer, Character> actual = HashBiMap.newMap();
        actual.put(1, null);
        Assert.assertTrue(actual.containsValue(null));
        Assert.assertFalse(actual.containsValue('\0'));
    }

    @Override
    @Test
    public void putAll()
    {
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();
        biMap.putAll(UnifiedMap.newMap());
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, null, null, 'b', 3, 'c'), biMap);

        biMap.putAll(UnifiedMap.newWithKeysValues(1, null, null, 'b', 3, 'c'));
        HashBiMap<Integer, Character> expected = HashBiMap.newWithKeysValues(1, null, null, 'b', 3, 'c');
        Assert.assertEquals(expected, biMap);

        biMap.putAll(UnifiedMap.newWithKeysValues(4, 'd', 5, 'e', 6, 'f'));
        expected.put(4, 'd');
        expected.put(5, 'e');
        expected.put(6, 'f');
        Assert.assertEquals(expected, biMap);
    }

    @Test
    public void remove()
    {
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();
        Assert.assertNull(biMap.remove(4));
        Verify.assertSize(3, biMap);
        Assert.assertNull(biMap.remove(1));
        Assert.assertNull(biMap.get(1));
        Assert.assertNull(biMap.inverse().get(null));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(null, 'b', 3, 'c'), biMap);

        Assert.assertEquals(Character.valueOf('b'), biMap.remove(null));
        Assert.assertNull(biMap.get(null));
        Assert.assertNull(biMap.inverse().get('b'));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(3, 'c'), biMap);

        Assert.assertEquals(Character.valueOf('c'), biMap.remove(3));
        Assert.assertNull(biMap.get(3));
        Assert.assertNull(biMap.inverse().get('c'));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newMap(), biMap);
        Verify.assertEmpty(biMap);

        Assert.assertNull(HashBiMap.newMap().remove(1));
    }

    @Override
    @Test
    public void clear()
    {
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();
        biMap.clear();
        Verify.assertEmpty(biMap);
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newMap(), biMap);
    }

    @Test
    public void testToString()
    {
        Assert.assertEquals("{}", this.getEmptyMap().toString());
        String actualString = HashBiMap.newWithKeysValues(1, null, 2, 'b').toString();
        Assert.assertTrue("{1=null, 2=b}".equals(actualString) || "{2=b, 1=null}".equals(actualString));
    }

    @Override
    @Test
    public void equalsAndHashCode()
    {
        super.equalsAndHashCode();

        MutableBiMap<Integer, Character> emptyMap = this.getEmptyMap();
        Verify.assertEqualsAndHashCode(UnifiedMap.newMap(), emptyMap);
        Assert.assertEquals(emptyMap, emptyMap);
        Verify.assertEqualsAndHashCode(UnifiedMap.newWithKeysValues(1, null, null, 'b', 3, 'c'), this.classUnderTest());
        Verify.assertEqualsAndHashCode(UnifiedMap.newWithKeysValues(null, 'b', 1, null, 3, 'c'), this.classUnderTest());
        Assert.assertNotEquals(HashBiMap.newWithKeysValues(null, 1, 'b', null, 'c', 3), this.classUnderTest());
        Verify.assertEqualsAndHashCode(HashBiMap.newWithKeysValues(null, 1, 'b', null, 'c', 3), this.classUnderTest().inverse());
    }

    @Override
    @Test
    public void nullCollisionWithCastInEquals()
    {
        MutableBiMap<IntegerWithCast, String> mutableMap = this.newMap();
        mutableMap.put(new IntegerWithCast(0), "Test 2");
        mutableMap.forcePut(new IntegerWithCast(0), "Test 3");
        mutableMap.put(null, "Test 1");
        Assert.assertEquals(
                this.newMapWithKeysValues(
                        new IntegerWithCast(0), "Test 3",
                        null, "Test 1"),
                mutableMap);
        Assert.assertEquals("Test 3", mutableMap.get(new IntegerWithCast(0)));
        Assert.assertEquals("Test 1", mutableMap.get(null));
    }

    @Override
    @Test
    public void iterator()
    {
        MutableSet<Character> expected = UnifiedSet.newSetWith(null, 'b', 'c');
        MutableSet<Character> actual = UnifiedSet.newSet();
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();
        Iterator<Character> iterator = biMap.iterator();
        Assert.assertTrue(iterator.hasNext());
        Verify.assertThrows(IllegalStateException.class, iterator::remove);
        Verify.assertSize(3, biMap);
        Verify.assertSize(3, biMap.inverse());
        for (int i = 0; i < 3; i++)
        {
            Assert.assertTrue(iterator.hasNext());
            actual.add(iterator.next());
        }
        Assert.assertEquals(expected, actual);
        Assert.assertFalse(iterator.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) iterator::next);

        Iterator<Character> iteratorRemove = biMap.iterator();

        Assert.assertTrue(iteratorRemove.hasNext());
        Character first = iteratorRemove.next();
        iteratorRemove.remove();
        MutableBiMap<Integer, Character> expectedMap = this.classUnderTest();
        expectedMap.inverse().remove(first);
        Assert.assertEquals(expectedMap, biMap);
        Assert.assertEquals(expectedMap.inverse(), biMap.inverse());
        Verify.assertSize(2, biMap);
        Verify.assertSize(2, biMap.inverse());

        Assert.assertTrue(iteratorRemove.hasNext());
        Character second = iteratorRemove.next();
        iteratorRemove.remove();
        expectedMap.inverse().remove(second);
        Assert.assertEquals(expectedMap, biMap);
        Assert.assertEquals(expectedMap.inverse(), biMap.inverse());
        Verify.assertSize(1, biMap);
        Verify.assertSize(1, biMap.inverse());

        Assert.assertTrue(iteratorRemove.hasNext());
        Character third = iteratorRemove.next();
        iteratorRemove.remove();
        expectedMap.inverse().remove(third);
        Assert.assertEquals(expectedMap, biMap);
        Assert.assertEquals(expectedMap.inverse(), biMap.inverse());
        Verify.assertEmpty(biMap);
        Verify.assertEmpty(biMap.inverse());

        Assert.assertFalse(iteratorRemove.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) iteratorRemove::next);
    }

    @Override
    @Test
    public void updateValueWith()
    {
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();
        Function2<Character, Boolean, Character> toUpperOrLowerCase = (character, parameter) -> parameter ? Character.toUpperCase(character) : Character.toLowerCase(character);
        Assert.assertEquals(Character.valueOf('D'), biMap.updateValueWith(4, () -> 'd', toUpperOrLowerCase, true));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, null, null, 'b', 3, 'c', 4, 'D'), biMap);
        Assert.assertEquals(Character.valueOf('B'), biMap.updateValueWith(null, () -> 'd', toUpperOrLowerCase, true));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, null, null, 'B', 3, 'c', 4, 'D'), biMap);
        Assert.assertEquals(Character.valueOf('d'), biMap.updateValueWith(4, () -> 'x', toUpperOrLowerCase, false));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, null, null, 'B', 3, 'c', 4, 'd'), biMap);
    }

    @Override
    @Test
    public void updateValue()
    {
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();
        Assert.assertEquals(Character.valueOf('D'), biMap.updateValue(4, () -> 'd', Character::toUpperCase));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, null, null, 'b', 3, 'c', 4, 'D'), biMap);
        Assert.assertEquals(Character.valueOf('B'), biMap.updateValue(null, () -> 'd', Character::toUpperCase));
        AbstractMutableBiMapTestCase.assertBiMapsEqual(HashBiMap.newWithKeysValues(1, null, null, 'B', 3, 'c', 4, 'D'), biMap);
    }

    @Override
    @Test
    public void updateValue_collisions()
    {
        // testing collisions not applicable here
    }

    @Override
    @Test
    public void updateValueWith_collisions()
    {
        // testing collisions not applicable here
    }

    @Override
    public void toImmutable()
    {
        ImmutableBiMap<Integer, Character> expectedImmutableBiMap = BiMaps.immutable.of(null, 'b', 1, null, 3, 'c');
        ImmutableBiMap<Integer, Character> characters = this.classUnderTest().toImmutable();
        Assert.assertEquals(expectedImmutableBiMap, characters);
    }

    @Test
    public void testClone()
    {
        MutableBiMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "Two");
        MutableBiMap<Integer, String> clone = map.clone();
        Assert.assertNotSame(map, clone);
        Verify.assertEqualsAndHashCode(map, clone);
    }

    @Test
    public void into()
    {
        MutableBiMap<Integer, Character> map = this.newMapWithKeysValues(1, 'a', 2, 'b');
        MutableSet<Character> target = Sets.mutable.of('c');
        map.into(target);
        Verify.assertSetsEqual(Sets.mutable.of('a', 'b', 'c'), target);
    }
}
