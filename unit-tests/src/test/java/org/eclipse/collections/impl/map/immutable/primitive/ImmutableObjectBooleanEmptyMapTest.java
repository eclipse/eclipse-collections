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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link ImmutableObjectBooleanEmptyMap}.
 */
public class ImmutableObjectBooleanEmptyMapTest extends AbstractImmutableObjectBooleanMapTestCase
{
    @Override
    protected ImmutableObjectBooleanMap<String> classUnderTest()
    {
        return (ImmutableObjectBooleanMap<String>) ImmutableObjectBooleanEmptyMap.INSTANCE;
    }

    @Test
    public void newWithKeyValue()
    {
        ImmutableObjectBooleanMap<String> map1 = this.classUnderTest();
        ImmutableObjectBooleanMap<String> expected = ObjectBooleanHashMap.newWithKeysValues("3", true).toImmutable();
        assertEquals(expected, map1.newWithKeyValue("3", true));
        assertNotSame(map1, map1.newWithKeyValue("3", true));
        assertEquals(this.classUnderTest(), map1);
    }

    @Test
    public void newWithoutKeyValue()
    {
        ImmutableObjectBooleanMap<String> map1 = this.classUnderTest();
        ImmutableObjectBooleanMap<String> expected1 = this.getEmptyMap();
        assertEquals(expected1, map1.newWithoutKey("2"));
        assertSame(map1, map1.newWithoutKey("2"));
        assertEquals(this.classUnderTest(), map1);
    }

    @Test
    public void newWithoutAllKeys()
    {
        ImmutableObjectBooleanMap<String> map1 = this.classUnderTest();
        ImmutableObjectBooleanMap<String> expected1 = this.getEmptyMap();
        assertEquals(expected1, map1.newWithoutAllKeys(FastList.newListWith("2", "3")));
        assertSame(map1, map1.newWithoutAllKeys(FastList.newListWith("2", "3")));
        assertEquals(this.classUnderTest(), map1);
    }

    @Override
    @Test
    public void containsKey()
    {
        assertFalse(this.classUnderTest().containsKey("0"));
        assertFalse(this.classUnderTest().containsKey("1"));
        assertFalse(this.classUnderTest().containsKey("2"));
        assertFalse(this.classUnderTest().containsKey("3"));
        assertFalse(this.classUnderTest().containsKey(null));
    }

    @Override
    @Test
    public void containsValue()
    {
        assertFalse(this.classUnderTest().containsValue(true));
        assertFalse(this.classUnderTest().containsValue(false));
    }

    @Override
    @Test
    public void detectIfNone()
    {
        boolean detect = this.classUnderTest().detectIfNone(value -> true, false);
        assertFalse(detect);
    }

    @Override
    @Test
    public void getIfAbsent()
    {
        assertTrue(this.classUnderTest().getIfAbsent("0", true));
        assertTrue(this.classUnderTest().getIfAbsent("1", true));
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
        assertTrue(this.classUnderTest().allSatisfy(value -> false));
    }

    @Override
    @Test
    public void anySatisfy()
    {
        assertFalse(this.classUnderTest().anySatisfy(value -> true));
    }

    @Override
    @Test
    public void reject()
    {
        assertEquals(this.classUnderTest(), this.classUnderTest().reject((object, value1) -> false));

        assertEquals(new BooleanHashBag(), this.classUnderTest().reject(value -> false).toBag());
    }

    @Override
    @Test
    public void select()
    {
        assertEquals(this.classUnderTest(), this.classUnderTest().select((object, value1) -> true));

        assertEquals(new BooleanHashBag(), this.classUnderTest().select(value -> true).toBag());
    }

    @Override
    @Test
    public void iterator()
    {
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Override
    @Test
    public void contains()
    {
        assertFalse(this.classUnderTest().contains(true));
        assertFalse(this.classUnderTest().contains(false));
    }

    @Override
    @Test
    public void getOrThrow()
    {
        assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOrThrow("5"));
        assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOrThrow("0"));
        assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOrThrow(null));
    }

    @Override
    @Test
    public void get()
    {
        assertFalse(this.classUnderTest().get("0"));
        assertFalse(this.classUnderTest().get("1"));
        assertFalse(this.classUnderTest().get(null));
    }

    @Override
    @Test
    public void count()
    {
        assertEquals(0L, this.classUnderTest().count(value -> true));
    }

    @Override
    @Test
    public void toBag()
    {
        assertEquals(BooleanHashBag.newBagWith(), this.classUnderTest().toBag());
    }

    @Override
    @Test
    public void toSet()
    {
        assertEquals(BooleanHashSet.newSetWith(), this.classUnderTest().toSet());
    }

    @Override
    @Test
    public void containsAll()
    {
        assertFalse(this.classUnderTest().containsAll(true, false));
        assertFalse(this.classUnderTest().containsAll(true, true));
        assertTrue(this.classUnderTest().containsAll());
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        assertFalse(this.classUnderTest().containsAll(BooleanArrayList.newListWith(true, false)));
        assertFalse(this.classUnderTest().containsAll(BooleanArrayList.newListWith(true, true)));
        assertTrue(this.classUnderTest().containsAll(new BooleanArrayList()));
    }

    @Override
    @Test
    public void testEquals()
    {
        ObjectBooleanMap<String> map1 = this.newWithKeysValues("0", true, "1", false, null, true);
        ObjectBooleanMap<String> map2 = this.getEmptyMap();

        assertNotEquals(this.classUnderTest(), map1);
        Verify.assertEqualsAndHashCode(this.classUnderTest(), map2);
        Verify.assertPostSerializedIdentity(this.classUnderTest());
    }

    @Override
    @Test
    public void isEmpty()
    {
        Verify.assertEmpty(this.classUnderTest());
    }

    @Override
    @Test
    public void notEmpty()
    {
        assertFalse(this.classUnderTest().notEmpty());
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        assertTrue(this.classUnderTest().noneSatisfy(value -> true));
    }
}
