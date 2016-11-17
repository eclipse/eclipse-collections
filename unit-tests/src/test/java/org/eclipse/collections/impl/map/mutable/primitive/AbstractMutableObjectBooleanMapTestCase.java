/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction0;
import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableObjectBooleanMap;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractMutableObjectBooleanMapTestCase extends AbstractObjectBooleanMapTestCase
{
    protected final MutableObjectBooleanMap<String> map = this.classUnderTest();

    @Override
    protected abstract MutableObjectBooleanMap<String> classUnderTest();

    @Override
    protected abstract <T> MutableObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1);

    @Override
    protected abstract <T> MutableObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1, T key2, boolean value2);

    @Override
    protected abstract <T> MutableObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1, T key2, boolean value2, T key3, boolean value3);

    @Override
    protected abstract <T> MutableObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1, T key2, boolean value2, T key3, boolean value3, T key4, boolean value4);

    @Override
    protected abstract <T> MutableObjectBooleanMap<T> getEmptyMap();

    @Override
    public void get()
    {
        super.get();
        MutableObjectBooleanMap<String> map1 = this.classUnderTest();
        map1.put("0", false);
        Assert.assertFalse(map1.get("0"));

        map1.put("5", true);
        Assert.assertTrue(map1.get("5"));

        map1.put(null, true);
        Assert.assertTrue(map1.get(null));
    }

    @Override
    public void getIfAbsent()
    {
        super.getIfAbsent();
        MutableObjectBooleanMap<String> map1 = this.classUnderTest();
        map1.removeKey("0");
        Assert.assertTrue(map1.getIfAbsent("0", true));
        Assert.assertFalse(map1.getIfAbsent("0", false));

        map1.put("0", false);
        Assert.assertFalse(map1.getIfAbsent("0", true));

        map1.put("5", true);
        Assert.assertTrue(map1.getIfAbsent("5", false));

        map1.put(null, false);
        Assert.assertFalse(map1.getIfAbsent(null, true));
    }

    @Override
    public void getOrThrow()
    {
        super.getOrThrow();
        MutableObjectBooleanMap<String> map1 = this.classUnderTest();
        map1.removeKey("0");
        Verify.assertThrows(IllegalStateException.class, () -> map1.getOrThrow("0"));
        map1.put("0", false);
        Assert.assertFalse(map1.getOrThrow("0"));

        map1.put("5", true);
        Assert.assertTrue(map1.getOrThrow("5"));

        map1.put(null, false);
        Assert.assertFalse(map1.getOrThrow(null));
    }

    @Override
    public void containsKey()
    {
        super.containsKey();
        MutableObjectBooleanMap<String> map1 = this.classUnderTest();
        map1.removeKey("0");
        Assert.assertFalse(map1.containsKey("0"));
        Assert.assertFalse(map1.get("0"));
        map1.removeKey("0");
        Assert.assertFalse(map1.containsKey("0"));
        Assert.assertFalse(map1.get("0"));

        map1.removeKey("1");
        Assert.assertFalse(map1.containsKey("1"));
        Assert.assertFalse(map1.get("1"));

        map1.removeKey("2");
        Assert.assertFalse(map1.containsKey("2"));
        Assert.assertFalse(map1.get("2"));

        map1.removeKey("3");
        Assert.assertFalse(map1.containsKey("3"));
        Assert.assertFalse(map1.get("3"));
        map1.put(null, true);
        Assert.assertTrue(map1.containsKey(null));
        map1.removeKey(null);
        Assert.assertFalse(map1.containsKey(null));
    }

    @Override
    public void containsValue()
    {
        super.containsValue();
        this.classUnderTest().clear();

        this.classUnderTest().put("5", true);
        Assert.assertTrue(this.classUnderTest().containsValue(true));

        this.classUnderTest().put(null, false);
        Assert.assertTrue(this.classUnderTest().containsValue(false));
    }

    @Override
    public void size()
    {
        super.size();
        MutableObjectBooleanMap<Integer> hashMap1 = this.newWithKeysValues(1, true, 0, false);
        Verify.assertSize(2, hashMap1);
        hashMap1.removeKey(1);
        Verify.assertSize(1, hashMap1);
        hashMap1.removeKey(0);
        Verify.assertSize(0, hashMap1);
    }

    @Override
    public void contains()
    {
        super.contains();
        MutableObjectBooleanMap<String> map1 = this.classUnderTest();
        map1.clear();

        map1.put("5", true);
        Assert.assertTrue(map1.contains(true));

        map1.put(null, false);
        Assert.assertTrue(map1.contains(false));

        map1.removeKey("5");
        Assert.assertFalse(map1.contains(true));
        Assert.assertTrue(map1.contains(false));

        map1.removeKey(null);
        Assert.assertFalse(map1.contains(false));
    }

    @Override
    public void containsAll()
    {
        super.containsAll();
        MutableObjectBooleanMap<String> map1 = this.classUnderTest();
        map1.clear();

        map1.put("5", true);
        Assert.assertTrue(map1.containsAll(true));
        Assert.assertFalse(map1.containsAll(true, false));
        Assert.assertFalse(map1.containsAll(false, false));

        map1.put(null, false);
        Assert.assertTrue(map1.containsAll(false));
        Assert.assertTrue(map1.containsAll(true, false));

        map1.removeKey("5");
        Assert.assertFalse(map1.containsAll(true));
        Assert.assertFalse(map1.containsAll(true, false));
        Assert.assertTrue(map1.containsAll(false, false));

        map1.removeKey(null);
        Assert.assertFalse(map1.containsAll(false, true));
    }

    @Override
    public void containsAllIterable()
    {
        super.containsAllIterable();
        MutableObjectBooleanMap<String> map1 = this.classUnderTest();
        map1.clear();

        map1.put("5", true);
        Assert.assertTrue(map1.containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertFalse(map1.containsAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertFalse(map1.containsAll(BooleanArrayList.newListWith(false, false)));

        map1.put(null, false);
        Assert.assertTrue(map1.containsAll(BooleanArrayList.newListWith(false)));
        Assert.assertTrue(map1.containsAll(BooleanArrayList.newListWith(true, false)));

        map1.removeKey("5");
        Assert.assertFalse(map1.containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertFalse(map1.containsAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertTrue(map1.containsAll(BooleanArrayList.newListWith(false, false)));

        map1.removeKey(null);
        Assert.assertFalse(map1.containsAll(BooleanArrayList.newListWith(false, true)));
    }

    protected static MutableList<String> generateCollisions()
    {
        MutableList<String> collisions = FastList.newList();
        ObjectBooleanHashMap<String> hashMap = new ObjectBooleanHashMap<>();
        for (int each = 3; collisions.size() <= 10; each++)
        {
            if (hashMap.spread(String.valueOf(each)) == hashMap.spread(String.valueOf(3)))
            {
                collisions.add(String.valueOf(each));
            }
        }
        return collisions;
    }

    @Test
    public void clear()
    {
        MutableObjectBooleanMap<String> hashMap = this.getEmptyMap();
        hashMap.put("0", true);
        hashMap.clear();
        Assert.assertEquals(ObjectBooleanHashMap.newMap(), hashMap);

        hashMap.put("1", false);
        hashMap.clear();
        Assert.assertEquals(ObjectBooleanHashMap.newMap(), hashMap);

        hashMap.put(null, true);
        hashMap.clear();
        Assert.assertEquals(ObjectBooleanHashMap.newMap(), hashMap);
    }

    @Test
    public void removeKey()
    {
        MutableObjectBooleanMap<String> map0 = this.newWithKeysValues("0", true, "1", false);
        map0.removeKey("1");
        Assert.assertEquals(this.newWithKeysValues("0", true), map0);
        map0.removeKey("0");
        Assert.assertEquals(ObjectBooleanHashMap.newMap(), map0);

        MutableObjectBooleanMap<String> map1 = this.newWithKeysValues("0", false, "1", true);
        map1.removeKey("0");
        Assert.assertEquals(this.newWithKeysValues("1", true), map1);
        map1.removeKey("1");
        Assert.assertEquals(ObjectBooleanHashMap.newMap(), map1);

        this.map.removeKey("5");
        Assert.assertEquals(this.newWithKeysValues("0", true, "1", true, "2", false), this.map);
        this.map.removeKey("0");
        Assert.assertEquals(this.newWithKeysValues("1", true, "2", false), this.map);
        this.map.removeKey("1");
        Assert.assertEquals(this.newWithKeysValues("2", false), this.map);
        this.map.removeKey("2");
        Assert.assertEquals(ObjectBooleanHashMap.newMap(), this.map);
        this.map.removeKey("0");
        this.map.removeKey("1");
        this.map.removeKey("2");
        Assert.assertEquals(ObjectBooleanHashMap.newMap(), this.map);
        Verify.assertEmpty(this.map);

        this.map.put(null, true);
        Assert.assertTrue(this.map.get(null));
        this.map.removeKey(null);
        Assert.assertFalse(this.map.get(null));
    }

    @Test
    public void put()
    {
        this.map.put("0", false);
        this.map.put("1", false);
        this.map.put("2", true);
        ObjectBooleanHashMap<String> expected = ObjectBooleanHashMap.newWithKeysValues("0", false, "1", false, "2", true);
        Assert.assertEquals(expected, this.map);

        this.map.put("5", true);
        expected.put("5", true);
        Assert.assertEquals(expected, this.map);

        this.map.put(null, false);
        expected.put(null, false);
        Assert.assertEquals(expected, this.map);
    }

    @Test
    public void putDuplicateWithRemovedSlot()
    {
        String collision1 = generateCollisions().getFirst();
        String collision2 = generateCollisions().get(1);
        String collision3 = generateCollisions().get(2);
        String collision4 = generateCollisions().get(3);

        MutableObjectBooleanMap<String> hashMap = this.getEmptyMap();
        hashMap.put(collision1, true);
        hashMap.put(collision2, false);
        hashMap.put(collision3, true);
        Assert.assertFalse(hashMap.get(collision2));
        hashMap.removeKey(collision2);
        hashMap.put(collision4, false);
        Assert.assertEquals(this.newWithKeysValues(collision1, true, collision3, true, collision4, false), hashMap);

        MutableObjectBooleanMap<String> hashMap1 = this.getEmptyMap();
        hashMap1.put(collision1, false);
        hashMap1.put(collision2, false);
        hashMap1.put(collision3, true);
        Assert.assertFalse(hashMap1.get(collision1));
        hashMap1.removeKey(collision1);
        hashMap1.put(collision4, true);
        Assert.assertEquals(this.newWithKeysValues(collision2, false, collision3, true, collision4, true), hashMap1);

        MutableObjectBooleanMap<String> hashMap2 = this.getEmptyMap();
        hashMap2.put(collision1, true);
        hashMap2.put(collision2, true);
        hashMap2.put(collision3, false);
        Assert.assertFalse(hashMap2.get(collision3));
        hashMap2.removeKey(collision3);
        hashMap2.put(collision4, false);
        Assert.assertEquals(this.newWithKeysValues(collision1, true, collision2, true, collision4, false), hashMap2);

        MutableObjectBooleanMap<String> hashMap3 = this.getEmptyMap();
        hashMap3.put(collision1, true);
        hashMap3.put(collision2, true);
        hashMap3.put(collision3, false);
        Assert.assertTrue(hashMap3.get(collision2));
        Assert.assertFalse(hashMap3.get(collision3));
        hashMap3.removeKey(collision2);
        hashMap3.removeKey(collision3);
        hashMap3.put(collision4, false);
        Assert.assertEquals(this.newWithKeysValues(collision1, true, collision4, false), hashMap3);

        MutableObjectBooleanMap<String> hashMap4 = this.getEmptyMap();
        hashMap4.put(null, false);
        Assert.assertEquals(this.newWithKeysValues(null, false), hashMap4);
        hashMap4.put(null, true);
        Assert.assertEquals(this.newWithKeysValues(null, true), hashMap4);
    }

    @Test
    public void getIfAbsentPut_Function()
    {
        MutableObjectBooleanMap<Integer> map1 = this.getEmptyMap();
        Assert.assertTrue(map1.getIfAbsentPut(0, () -> true));
        BooleanFunction0 factoryThrows = () -> { throw new AssertionError(); };
        Assert.assertTrue(map1.getIfAbsentPut(0, factoryThrows));
        Assert.assertEquals(this.newWithKeysValues(0, true), map1);
        Assert.assertTrue(map1.getIfAbsentPut(1, () -> true));
        Assert.assertTrue(map1.getIfAbsentPut(1, factoryThrows));
        Assert.assertEquals(this.newWithKeysValues(0, true, 1, true), map1);

        MutableObjectBooleanMap<Integer> map2 = this.getEmptyMap();
        Assert.assertFalse(map2.getIfAbsentPut(1, () -> false));
        Assert.assertFalse(map2.getIfAbsentPut(1, factoryThrows));
        Assert.assertEquals(this.newWithKeysValues(1, false), map2);
        Assert.assertFalse(map2.getIfAbsentPut(0, () -> false));
        Assert.assertFalse(map2.getIfAbsentPut(0, factoryThrows));
        Assert.assertEquals(this.newWithKeysValues(0, false, 1, false), map2);

        MutableObjectBooleanMap<Integer> map3 = this.getEmptyMap();
        Assert.assertTrue(map3.getIfAbsentPut(null, () -> true));
        Assert.assertTrue(map3.getIfAbsentPut(null, factoryThrows));
        Assert.assertEquals(this.newWithKeysValues(null, true), map3);
    }

    @Test
    public void getIfAbsentPutWith()
    {
        BooleanFunction<String> functionLengthEven = string -> (string.length() & 1) == 0;

        MutableObjectBooleanMap<Integer> map1 = this.getEmptyMap();
        Assert.assertFalse(map1.getIfAbsentPutWith(0, functionLengthEven, "123456789"));
        BooleanFunction<String> functionThrows = string -> { throw new AssertionError(); };
        Assert.assertFalse(map1.getIfAbsentPutWith(0, functionThrows, "unused"));
        Assert.assertEquals(this.newWithKeysValues(0, false), map1);
        Assert.assertFalse(map1.getIfAbsentPutWith(1, functionLengthEven, "123456789"));
        Assert.assertFalse(map1.getIfAbsentPutWith(1, functionThrows, "unused"));
        Assert.assertEquals(this.newWithKeysValues(0, false, 1, false), map1);

        MutableObjectBooleanMap<Integer> map2 = this.getEmptyMap();
        Assert.assertTrue(map2.getIfAbsentPutWith(1, functionLengthEven, "1234567890"));
        Assert.assertTrue(map2.getIfAbsentPutWith(1, functionThrows, "unused0"));
        Assert.assertEquals(this.newWithKeysValues(1, true), map2);
        Assert.assertTrue(map2.getIfAbsentPutWith(0, functionLengthEven, "1234567890"));
        Assert.assertTrue(map2.getIfAbsentPutWith(0, functionThrows, "unused0"));
        Assert.assertEquals(this.newWithKeysValues(0, true, 1, true), map2);

        MutableObjectBooleanMap<Integer> map3 = this.getEmptyMap();
        Assert.assertFalse(map3.getIfAbsentPutWith(null, functionLengthEven, "123456789"));
        Assert.assertFalse(map3.getIfAbsentPutWith(null, functionThrows, "unused"));
        Assert.assertEquals(this.newWithKeysValues(null, false), map3);
    }

    @Test
    public void getIfAbsentPutWithKey()
    {
        BooleanFunction<Integer> function = anObject -> anObject == null || (anObject & 1) == 0;

        MutableObjectBooleanMap<Integer> map1 = this.getEmptyMap();
        Assert.assertTrue(map1.getIfAbsentPutWithKey(0, function));
        BooleanFunction<Integer> functionThrows = anObject -> { throw new AssertionError(); };
        Assert.assertTrue(map1.getIfAbsentPutWithKey(0, functionThrows));
        Assert.assertEquals(this.newWithKeysValues(0, true), map1);
        Assert.assertFalse(map1.getIfAbsentPutWithKey(1, function));
        Assert.assertFalse(map1.getIfAbsentPutWithKey(1, functionThrows));
        Assert.assertEquals(this.newWithKeysValues(0, true, 1, false), map1);

        MutableObjectBooleanMap<Integer> map2 = this.getEmptyMap();
        Assert.assertFalse(map2.getIfAbsentPutWithKey(1, function));
        Assert.assertFalse(map2.getIfAbsentPutWithKey(1, functionThrows));
        Assert.assertEquals(this.newWithKeysValues(1, false), map2);
        Assert.assertTrue(map2.getIfAbsentPutWithKey(0, function));
        Assert.assertTrue(map2.getIfAbsentPutWithKey(0, functionThrows));
        Assert.assertEquals(this.newWithKeysValues(0, true, 1, false), map2);

        MutableObjectBooleanMap<Integer> map3 = this.getEmptyMap();
        Assert.assertTrue(map3.getIfAbsentPutWithKey(null, function));
        Assert.assertTrue(map3.getIfAbsentPutWithKey(null, functionThrows));
        Assert.assertEquals(this.newWithKeysValues(null, true), map3);
    }

    @Test
    public void withKeysValues()
    {
        MutableObjectBooleanMap<Integer> emptyMap = this.getEmptyMap();
        MutableObjectBooleanMap<Integer> hashMap = emptyMap.withKeyValue(1, true);
        Assert.assertEquals(this.newWithKeysValues(1, true), hashMap);
        Assert.assertSame(emptyMap, hashMap);
    }

    @Test
    public void withoutKey()
    {
        MutableObjectBooleanMap<Integer> hashMap = this.newWithKeysValues(1, true, 2, true, 3, false, 4, false);
        MutableObjectBooleanMap<Integer> actual = hashMap.withoutKey(5);
        Assert.assertSame(hashMap, actual);
        Assert.assertEquals(this.newWithKeysValues(1, true, 2, true, 3, false, 4, false), actual);
        Assert.assertEquals(this.newWithKeysValues(1, true, 2, true, 3, false), hashMap.withoutKey(4));
        Assert.assertEquals(this.newWithKeysValues(1, true, 2, true), hashMap.withoutKey(3));
        Assert.assertEquals(this.newWithKeysValues(1, true), hashMap.withoutKey(2));
        Assert.assertEquals(ObjectBooleanHashMap.newMap(), hashMap.withoutKey(1));
        Assert.assertEquals(ObjectBooleanHashMap.newMap(), hashMap.withoutKey(1));
    }

    @Test
    public void withoutAllKeys()
    {
        MutableObjectBooleanMap<Integer> hashMap = this.newWithKeysValues(1, true, 2, true, 3, false, 4, false);
        MutableObjectBooleanMap<Integer> actual = hashMap.withoutAllKeys(FastList.newListWith(5, 6, 7));
        Assert.assertSame(hashMap, actual);
        Assert.assertEquals(this.newWithKeysValues(1, true, 2, true, 3, false, 4, false), actual);
        Assert.assertEquals(this.newWithKeysValues(1, true, 2, true), hashMap.withoutAllKeys(FastList.newListWith(5, 4, 3)));
        Assert.assertEquals(this.newWithKeysValues(1, true), hashMap.withoutAllKeys(FastList.newListWith(2)));
        Assert.assertEquals(ObjectBooleanHashMap.newMap(), hashMap.withoutAllKeys(FastList.newListWith(1)));
        Assert.assertEquals(ObjectBooleanHashMap.newMap(), hashMap.withoutAllKeys(FastList.newListWith(5, 6)));
    }

    @Test
    public void asUnmodifiable()
    {
        Verify.assertInstanceOf(UnmodifiableObjectBooleanMap.class, this.map.asUnmodifiable());
        Assert.assertEquals(new UnmodifiableObjectBooleanMap<>(this.map), this.map.asUnmodifiable());
    }

    @Test
    public void asSynchronized()
    {
        Verify.assertInstanceOf(SynchronizedObjectBooleanMap.class, this.map.asSynchronized());
        Assert.assertEquals(new SynchronizedObjectBooleanMap<>(this.map), this.map.asSynchronized());
    }

    @Test
    public void iterator_remove()
    {
        MutableObjectBooleanMap<String> map = this.classUnderTest();
        Verify.assertNotEmpty(map);
        MutableBooleanIterator booleanIterator = map.booleanIterator();

        while (booleanIterator.hasNext())
        {
            booleanIterator.next();
            booleanIterator.remove();
        }
        Verify.assertEmpty(map);
    }

    @Test
    public void iterator_throws_on_consecutive_invocation_of_remove()
    {
        MutableObjectBooleanMap<String> map = this.classUnderTest();
        Verify.assertNotEmpty(map);
        MutableBooleanIterator booleanIterator = map.booleanIterator();
        Assert.assertTrue(booleanIterator.hasNext());
        booleanIterator.next();
        booleanIterator.remove();
        Verify.assertThrows(IllegalStateException.class, booleanIterator::remove);
    }

    @Test
    public void iterator_throws_on_invocation_of_remove_before_next()
    {
        MutableObjectBooleanMap<String> map = this.classUnderTest();
        MutableBooleanIterator booleanIterator = map.booleanIterator();
        Assert.assertTrue(booleanIterator.hasNext());
        Verify.assertThrows(IllegalStateException.class, booleanIterator::remove);
    }
}
