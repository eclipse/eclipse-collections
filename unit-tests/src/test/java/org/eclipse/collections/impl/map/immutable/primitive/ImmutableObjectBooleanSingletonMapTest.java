/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable.primitive;

import java.util.NoSuchElementException;

import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.map.primitive.ImmutableObjectBooleanMap;
import org.eclipse.collections.api.map.primitive.ObjectBooleanMap;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectBooleanHashMap;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link ImmutableObjectBooleanSingletonMap}.
 */
public class ImmutableObjectBooleanSingletonMapTest extends AbstractImmutableObjectBooleanMapTestCase
{
    @Override
    protected ImmutableObjectBooleanMap<String> classUnderTest()
    {
        return ObjectBooleanHashMap.newWithKeysValues("1", true).toImmutable();
    }

    @Test
    public void newWithKeyValue()
    {
        ImmutableObjectBooleanMap<String> map1 = this.classUnderTest();
        ImmutableObjectBooleanMap<String> expected = ObjectBooleanHashMap.newWithKeysValues("1", true, "3", true).toImmutable();
        assertEquals(expected, map1.newWithKeyValue("3", true));
        assertNotSame(map1, map1.newWithKeyValue("3", true));
        assertEquals(this.classUnderTest(), map1);
    }

    @Test
    public void newWithoutKeyValue()
    {
        ImmutableObjectBooleanMap<String> map1 = this.classUnderTest();
        ImmutableObjectBooleanMap<String> expected1 = this.newWithKeysValues("1", true);
        assertEquals(expected1, map1.newWithoutKey("2"));
        assertEquals(this.classUnderTest(), map1);

        ImmutableObjectBooleanMap<String> expected2 = this.getEmptyMap();
        assertEquals(expected2, map1.newWithoutKey("1"));
        assertNotSame(map1, map1.newWithoutKey("1"));
        assertEquals(this.classUnderTest(), map1);
    }

    @Test
    public void newWithoutAllKeys()
    {
        ImmutableObjectBooleanMap<String> map1 = this.classUnderTest();
        ImmutableObjectBooleanMap<String> expected1 = this.newWithKeysValues("1", true);
        assertEquals(expected1, map1.newWithoutAllKeys(FastList.newListWith("2", "3")));
        assertNotSame(map1, map1.newWithoutAllKeys(FastList.newListWith("2", "3")));
        assertEquals(this.classUnderTest(), map1);

        ImmutableObjectBooleanMap<String> expected2 = this.getEmptyMap();
        assertEquals(expected2, map1.newWithoutAllKeys(FastList.newListWith("1", "3")));
        assertNotSame(map1, map1.newWithoutAllKeys(FastList.newListWith("1", "3")));
        assertEquals(this.classUnderTest(), map1);
    }

    @Override
    @Test
    public void containsKey()
    {
        assertFalse(this.classUnderTest().containsKey("0"));
        assertTrue(this.classUnderTest().containsKey("1"));
        assertFalse(this.classUnderTest().containsKey("2"));
        assertFalse(this.classUnderTest().containsKey("3"));
        assertFalse(this.classUnderTest().containsKey(null));
    }

    @Override
    @Test
    public void containsValue()
    {
        assertFalse(this.classUnderTest().containsValue(false));
        assertTrue(this.classUnderTest().containsValue(true));
    }

    @Override
    @Test
    public void detectIfNone()
    {
        boolean detect = this.classUnderTest().detectIfNone(value -> true, false);
        assertTrue(detect);

        boolean detect1 = this.classUnderTest().detectIfNone(value -> false, false);
        assertFalse(detect1);
    }

    @Override
    @Test
    public void getIfAbsent()
    {
        assertTrue(this.classUnderTest().getIfAbsent("0", true));
        assertTrue(this.classUnderTest().getIfAbsent("1", false));
        assertFalse(this.classUnderTest().getIfAbsent("2", false));
        assertFalse(this.classUnderTest().getIfAbsent("5", false));
        assertTrue(this.classUnderTest().getIfAbsent("5", true));

        assertTrue(this.classUnderTest().getIfAbsent(null, true));
        assertFalse(this.classUnderTest().getIfAbsent(null, false));
    }

    @Override
    @Test
    public void allSatisfy()
    {
        assertFalse(this.classUnderTest().allSatisfy(value1 -> false));

        assertTrue(this.classUnderTest().allSatisfy(value -> true));
    }

    @Override
    @Test
    public void reject()
    {
        assertEquals(this.classUnderTest(), this.classUnderTest().reject((object1, value3) -> false));

        assertEquals(this.getEmptyMap(), this.classUnderTest().reject((object, value2) -> true));

        assertEquals(new BooleanHashBag(), this.classUnderTest().reject(value1 -> true).toBag());

        assertEquals(BooleanHashBag.newBagWith(true), this.classUnderTest().reject(value -> false).toBag());
    }

    @Override
    @Test
    public void select()
    {
        assertEquals(this.classUnderTest(), this.classUnderTest().select((object1, value3) -> true));

        assertEquals(this.getEmptyMap(), this.classUnderTest().select((object, value2) -> false));

        assertEquals(new BooleanHashBag(), this.classUnderTest().select(value1 -> false).toBag());

        assertEquals(BooleanHashBag.newBagWith(true), this.classUnderTest().select(value -> true).toBag());
    }

    @Override
    @Test
    public void iterator()
    {
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Override
    @Test
    public void anySatisfy()
    {
        assertTrue(this.classUnderTest().anySatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.classUnderTest().anySatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.classUnderTest().anySatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        assertFalse(this.classUnderTest().anySatisfy(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
    }

    @Override
    @Test
    public void contains()
    {
        assertFalse(this.classUnderTest().contains(false));
        assertTrue(this.classUnderTest().contains(true));
    }

    @Override
    @Test
    public void getOrThrow()
    {
        assertTrue(this.classUnderTest().getOrThrow("1"));
        assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOrThrow("5"));
        assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOrThrow("0"));
        assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOrThrow(null));
    }

    @Override
    @Test
    public void get()
    {
        assertFalse(this.classUnderTest().get("0"));
        assertTrue(this.classUnderTest().get("1"));
        assertFalse(this.classUnderTest().get(null));
    }

    @Override
    @Test
    public void count()
    {
        assertEquals(1L, this.classUnderTest().count(value1 -> true));

        assertEquals(0L, this.classUnderTest().count(value -> false));
    }

    @Override
    @Test
    public void toBag()
    {
        assertEquals(BooleanHashBag.newBagWith(true), this.classUnderTest().toBag());
    }

    @Override
    @Test
    public void toSet()
    {
        assertEquals(BooleanHashSet.newSetWith(true), this.classUnderTest().toSet());
    }

    @Override
    @Test
    public void containsAll()
    {
        assertFalse(this.classUnderTest().containsAll(false, false));
        assertFalse(this.classUnderTest().containsAll(true, false));
        assertTrue(this.classUnderTest().containsAll(true));
        assertTrue(this.classUnderTest().containsAll());
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        assertFalse(this.classUnderTest().containsAll(BooleanArrayList.newListWith(false, false)));
        assertFalse(this.classUnderTest().containsAll(BooleanArrayList.newListWith(true, false)));
        assertTrue(this.classUnderTest().containsAll(BooleanArrayList.newListWith(true)));
        assertTrue(this.classUnderTest().containsAll(new BooleanArrayList()));
    }

    @Override
    @Test
    public void testEquals()
    {
        ObjectBooleanMap<String> map1 = this.newWithKeysValues("1", true);
        ObjectBooleanMap<String> map2 = this.newWithKeysValues("0", false);
        ObjectBooleanMap<String> map3 = this.newWithKeysValues("0", false, "1", true);

        assertNotEquals(this.classUnderTest(), map3);
        assertNotEquals(this.classUnderTest(), map2);
        Verify.assertEqualsAndHashCode(this.classUnderTest(), map1);
        Verify.assertPostSerializedEqualsAndHashCode(this.classUnderTest());
    }

    @Override
    @Test
    public void isEmpty()
    {
        Verify.assertNotEmpty(this.classUnderTest());
    }

    @Override
    @Test
    public void notEmpty()
    {
        assertTrue(this.classUnderTest().notEmpty());
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        assertFalse(this.classUnderTest().noneSatisfy(value1 -> true));

        assertTrue(this.classUnderTest().noneSatisfy(value -> false));
    }
}
