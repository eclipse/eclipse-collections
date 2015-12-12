/*
 * Copyright (c) 2015 Goldman Sachs.
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
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(expected, map1.newWithKeyValue("3", true));
        Assert.assertNotSame(map1, map1.newWithKeyValue("3", true));
        Assert.assertEquals(this.classUnderTest(), map1);
    }

    @Test
    public void newWithoutKeyValue()
    {
        ImmutableObjectBooleanMap<String> map1 = this.classUnderTest();
        ImmutableObjectBooleanMap<String> expected1 = this.newWithKeysValues("1", true);
        Assert.assertEquals(expected1, map1.newWithoutKey("2"));
        Assert.assertEquals(this.classUnderTest(), map1);

        ImmutableObjectBooleanMap<String> expected2 = this.getEmptyMap();
        Assert.assertEquals(expected2, map1.newWithoutKey("1"));
        Assert.assertNotSame(map1, map1.newWithoutKey("1"));
        Assert.assertEquals(this.classUnderTest(), map1);
    }

    @Test
    public void newWithoutAllKeys()
    {
        ImmutableObjectBooleanMap<String> map1 = this.classUnderTest();
        ImmutableObjectBooleanMap<String> expected1 = this.newWithKeysValues("1", true);
        Assert.assertEquals(expected1, map1.newWithoutAllKeys(FastList.newListWith("2", "3")));
        Assert.assertNotSame(map1, map1.newWithoutAllKeys(FastList.newListWith("2", "3")));
        Assert.assertEquals(this.classUnderTest(), map1);

        ImmutableObjectBooleanMap<String> expected2 = this.getEmptyMap();
        Assert.assertEquals(expected2, map1.newWithoutAllKeys(FastList.newListWith("1", "3")));
        Assert.assertNotSame(map1, map1.newWithoutAllKeys(FastList.newListWith("1", "3")));
        Assert.assertEquals(this.classUnderTest(), map1);
    }

    @Override
    @Test
    public void containsKey()
    {
        Assert.assertFalse(this.classUnderTest().containsKey("0"));
        Assert.assertTrue(this.classUnderTest().containsKey("1"));
        Assert.assertFalse(this.classUnderTest().containsKey("2"));
        Assert.assertFalse(this.classUnderTest().containsKey("3"));
        Assert.assertFalse(this.classUnderTest().containsKey(null));
    }

    @Override
    @Test
    public void containsValue()
    {
        Assert.assertFalse(this.classUnderTest().containsValue(false));
        Assert.assertTrue(this.classUnderTest().containsValue(true));
    }

    @Override
    @Test
    public void detectIfNone()
    {
        boolean detect = this.classUnderTest().detectIfNone(value -> true, false);
        Assert.assertTrue(detect);

        boolean detect1 = this.classUnderTest().detectIfNone(value -> false, false);
        Assert.assertFalse(detect1);
    }

    @Override
    @Test
    public void getIfAbsent()
    {
        Assert.assertTrue(this.classUnderTest().getIfAbsent("0", true));
        Assert.assertTrue(this.classUnderTest().getIfAbsent("1", false));
        Assert.assertFalse(this.classUnderTest().getIfAbsent("2", false));
        Assert.assertFalse(this.classUnderTest().getIfAbsent("5", false));
        Assert.assertTrue(this.classUnderTest().getIfAbsent("5", true));

        Assert.assertTrue(this.classUnderTest().getIfAbsent(null, true));
        Assert.assertFalse(this.classUnderTest().getIfAbsent(null, false));
    }

    @Override
    @Test
    public void allSatisfy()
    {
        Assert.assertFalse(this.classUnderTest().allSatisfy(value1 -> false));

        Assert.assertTrue(this.classUnderTest().allSatisfy(value -> true));
    }

    @Override
    @Test
    public void reject()
    {
        Assert.assertEquals(this.classUnderTest(), this.classUnderTest().reject((object1, value3) -> false));

        Assert.assertEquals(this.getEmptyMap(), this.classUnderTest().reject((object, value2) -> true));

        Assert.assertEquals(new BooleanHashBag(), this.classUnderTest().reject(value1 -> true).toBag());

        Assert.assertEquals(BooleanHashBag.newBagWith(true), this.classUnderTest().reject(value -> false).toBag());
    }

    @Override
    @Test
    public void select()
    {
        Assert.assertEquals(this.classUnderTest(), this.classUnderTest().select((object1, value3) -> true));

        Assert.assertEquals(this.getEmptyMap(), this.classUnderTest().select((object, value2) -> false));

        Assert.assertEquals(new BooleanHashBag(), this.classUnderTest().select(value1 -> false).toBag());

        Assert.assertEquals(BooleanHashBag.newBagWith(true), this.classUnderTest().select(value -> true).toBag());
    }

    @Override
    @Test
    public void iterator()
    {
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) iterator::next);
    }

    @Override
    @Test
    public void anySatisfy()
    {
        Assert.assertTrue(this.classUnderTest().anySatisfy(BooleanPredicates.isTrue()));
        Assert.assertFalse(this.classUnderTest().anySatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.classUnderTest().anySatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        Assert.assertFalse(this.classUnderTest().anySatisfy(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
    }

    @Override
    @Test
    public void contains()
    {
        Assert.assertFalse(this.classUnderTest().contains(false));
        Assert.assertTrue(this.classUnderTest().contains(true));
    }

    @Override
    @Test
    public void getOrThrow()
    {
        Assert.assertTrue(this.classUnderTest().getOrThrow("1"));
        Verify.assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOrThrow("5"));
        Verify.assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOrThrow("0"));
        Verify.assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOrThrow(null));
    }

    @Override
    @Test
    public void get()
    {
        Assert.assertFalse(this.classUnderTest().get("0"));
        Assert.assertTrue(this.classUnderTest().get("1"));
        Assert.assertFalse(this.classUnderTest().get(null));
    }

    @Override
    @Test
    public void count()
    {
        Assert.assertEquals(1L, this.classUnderTest().count(value1 -> true));

        Assert.assertEquals(0L, this.classUnderTest().count(value -> false));
    }

    @Override
    @Test
    public void toBag()
    {
        Assert.assertEquals(BooleanHashBag.newBagWith(true), this.classUnderTest().toBag());
    }

    @Override
    @Test
    public void toSet()
    {
        Assert.assertEquals(BooleanHashSet.newSetWith(true), this.classUnderTest().toSet());
    }

    @Override
    @Test
    public void containsAll()
    {
        Assert.assertFalse(this.classUnderTest().containsAll(false, false));
        Assert.assertFalse(this.classUnderTest().containsAll(true, false));
        Assert.assertTrue(this.classUnderTest().containsAll(true));
        Assert.assertTrue(this.classUnderTest().containsAll());
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        Assert.assertFalse(this.classUnderTest().containsAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertFalse(this.classUnderTest().containsAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertTrue(this.classUnderTest().containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertTrue(this.classUnderTest().containsAll(new BooleanArrayList()));
    }

    @Override
    @Test
    public void testEquals()
    {
        ObjectBooleanMap<String> map1 = this.newWithKeysValues("1", true);
        ObjectBooleanMap<String> map2 = this.newWithKeysValues("0", false);
        ObjectBooleanMap<String> map3 = this.newWithKeysValues("0", false, "1", true);

        Assert.assertNotEquals(this.classUnderTest(), map3);
        Assert.assertNotEquals(this.classUnderTest(), map2);
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
        Assert.assertTrue(this.classUnderTest().notEmpty());
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        Assert.assertFalse(this.classUnderTest().noneSatisfy(value1 -> true));

        Assert.assertTrue(this.classUnderTest().noneSatisfy(value -> false));
    }
}
