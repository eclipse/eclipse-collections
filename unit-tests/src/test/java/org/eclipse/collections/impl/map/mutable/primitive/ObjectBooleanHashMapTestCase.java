/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import java.lang.reflect.Field;
import java.util.BitSet;
import java.util.Iterator;

import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction0;
import org.eclipse.collections.api.block.function.primitive.BooleanToBooleanFunction;
import org.eclipse.collections.api.map.primitive.MutableObjectBooleanMap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public abstract class ObjectBooleanHashMapTestCase extends AbstractMutableObjectBooleanMapTestCase
{
    private final MutableObjectBooleanMap<String> map = this.classUnderTest();

    private final Class<?> targetClass = this.getTargetClass();

    protected abstract Class<?> getTargetClass();

    protected abstract <T> MutableObjectBooleanMap<T> newMapWithInitialCapacity(int size);

    @Test
    public void defaultInitialCapacity() throws Exception
    {
        Field keys = this.targetClass.getDeclaredField("keys");
        keys.setAccessible(true);
        Field values = this.targetClass.getDeclaredField("values");
        values.setAccessible(true);

        MutableObjectBooleanMap<String> hashMap = this.getEmptyMap();
        Assert.assertEquals(16L, ((Object[]) keys.get(hashMap)).length);
        Assert.assertEquals(64L, ((BitSet) values.get(hashMap)).size());
    }

    @Test
    public void newWithInitialCapacity() throws Exception
    {
        Field keys = this.targetClass.getDeclaredField("keys");
        keys.setAccessible(true);
        Field values = this.targetClass.getDeclaredField("values");
        values.setAccessible(true);

        MutableObjectBooleanMap<String> hashMap = this.newMapWithInitialCapacity(3);
        Assert.assertEquals(8L, ((Object[]) keys.get(hashMap)).length);
        Assert.assertEquals(64L, ((BitSet) values.get(hashMap)).size());

        MutableObjectBooleanMap<String> hashMap2 = this.newMapWithInitialCapacity(15);
        Assert.assertEquals(32L, ((Object[]) keys.get(hashMap2)).length);
        Assert.assertEquals(64L, ((BitSet) values.get(hashMap)).size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void newWithInitialCapacity_negative_throws()
    {
        this.newMapWithInitialCapacity(-1);
    }

    @Test
    public void newMap() throws Exception
    {
        Field keys = this.targetClass.getDeclaredField("keys");
        keys.setAccessible(true);
        Field values = this.targetClass.getDeclaredField("values");
        values.setAccessible(true);

        MutableObjectBooleanMap<String> hashMap = this.getEmptyMap();
        Assert.assertEquals(16L, ((Object[]) keys.get(hashMap)).length);
        Assert.assertEquals(64L, ((BitSet) values.get(hashMap)).size());
        Assert.assertEquals(this.getEmptyMap(), hashMap);
    }

    @Test
    public void removeKeyIfAbsent()
    {
        MutableObjectBooleanMap<String> map0 = this.newWithKeysValues("0", false, "1", true);
        Assert.assertTrue(map0.removeKeyIfAbsent("1", false));
        Assert.assertEquals(this.newWithKeysValues("0", false), map0);
        Assert.assertFalse(map0.removeKeyIfAbsent("0", true));
        Assert.assertEquals(this.getEmptyMap(), map0);
        Assert.assertFalse(map0.removeKeyIfAbsent("1", false));
        Assert.assertTrue(map0.removeKeyIfAbsent("0", true));

        MutableObjectBooleanMap<String> map1 = this.newWithKeysValues("0", true, "1", false);
        Assert.assertTrue(map1.removeKeyIfAbsent("0", false));
        Assert.assertEquals(this.newWithKeysValues("1", false), map1);
        Assert.assertFalse(map1.removeKeyIfAbsent("1", true));
        Assert.assertEquals(this.getEmptyMap(), map1);
        Assert.assertFalse(map1.removeKeyIfAbsent("0", false));
        Assert.assertTrue(map1.removeKeyIfAbsent("1", true));

        Assert.assertTrue(this.map.removeKeyIfAbsent("5", true));
        Assert.assertEquals(this.newWithKeysValues("0", true, "1", true, "2", false), this.map);
        Assert.assertTrue(this.map.removeKeyIfAbsent("0", false));
        Assert.assertEquals(this.newWithKeysValues("1", true, "2", false), this.map);
        Assert.assertTrue(this.map.removeKeyIfAbsent("1", false));
        Assert.assertEquals(this.newWithKeysValues("2", false), this.map);
        Assert.assertFalse(this.map.removeKeyIfAbsent("2", true));
        Assert.assertEquals(this.getEmptyMap(), this.map);
        Assert.assertFalse(this.map.removeKeyIfAbsent("0", false));
        Assert.assertFalse(this.map.removeKeyIfAbsent("1", false));
        Assert.assertTrue(this.map.removeKeyIfAbsent("2", true));
        Assert.assertEquals(this.getEmptyMap(), this.map);
        Verify.assertEmpty(this.map);

        this.map.put(null, true);

        Assert.assertTrue(this.map.get(null));
        Assert.assertTrue(this.map.removeKeyIfAbsent(null, false));
        Assert.assertFalse(this.map.get(null));
    }

    @Test
    public void putWithRehash() throws Exception
    {
        ObjectBooleanHashMap<Integer> hashMap = ObjectBooleanHashMap.newMap();
        for (int i = 2; i < 10; i++)
        {
            Assert.assertFalse(hashMap.containsKey(i));
            hashMap.put(i, (i & 1) == 0);
        }

        Field keys = ObjectBooleanHashMap.class.getDeclaredField("keys");
        Field values = ObjectBooleanHashMap.class.getDeclaredField("values");
        keys.setAccessible(true);
        values.setAccessible(true);
        Assert.assertEquals(16L, ((Object[]) keys.get(hashMap)).length);
        Assert.assertEquals(64L, ((BitSet) values.get(hashMap)).size());
        Verify.assertSize(8, hashMap);
        for (int i = 2; i < 10; i++)
        {
            Assert.assertTrue(hashMap.containsKey(i));
        }

        Assert.assertTrue(hashMap.containsValue(false));
        Assert.assertTrue(hashMap.containsValue(true));
        hashMap.put(10, true);
        Assert.assertEquals(32L, ((Object[]) keys.get(hashMap)).length);
        Assert.assertEquals(64L, ((BitSet) values.get(hashMap)).size());

        for (int i = 11; i < 75; i++)
        {
            Assert.assertFalse(String.valueOf(i), hashMap.containsKey(i));
            hashMap.put(i, (i & 1) == 0);
        }
        Assert.assertEquals(256L, ((Object[]) keys.get(hashMap)).length);
        Assert.assertEquals(256L, ((BitSet) values.get(hashMap)).size());
    }

    @Test
    public void getIfAbsentPut()
    {
        ObjectBooleanHashMap<Integer> map1 = ObjectBooleanHashMap.newMap();
        Assert.assertTrue(map1.getIfAbsentPut(0, true));
        Assert.assertTrue(map1.getIfAbsentPut(0, false));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(0, true), map1);
        Assert.assertTrue(map1.getIfAbsentPut(1, true));
        Assert.assertTrue(map1.getIfAbsentPut(1, false));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(0, true, 1, true), map1);

        ObjectBooleanHashMap<Integer> map2 = ObjectBooleanHashMap.newMap();
        Assert.assertFalse(map2.getIfAbsentPut(1, false));
        Assert.assertFalse(map2.getIfAbsentPut(1, true));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(1, false), map2);
        Assert.assertFalse(map2.getIfAbsentPut(0, false));
        Assert.assertFalse(map2.getIfAbsentPut(0, true));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(0, false, 1, false), map2);

        ObjectBooleanHashMap<Integer> map3 = ObjectBooleanHashMap.newMap();
        Assert.assertTrue(map3.getIfAbsentPut(null, true));
        Assert.assertTrue(map3.getIfAbsentPut(null, false));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(null, true), map3);
    }

    @Test
    public void updateValue()
    {
        BooleanToBooleanFunction flip = value -> !value;

        ObjectBooleanHashMap<Integer> map1 = ObjectBooleanHashMap.newMap();
        Assert.assertTrue(map1.updateValue(0, false, flip));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(0, true), map1);
        Assert.assertFalse(map1.updateValue(0, false, flip));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(0, false), map1);
        Assert.assertFalse(map1.updateValue(1, true, flip));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(0, false, 1, false), map1);
        Assert.assertTrue(map1.updateValue(1, true, flip));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(0, false, 1, true), map1);

        ObjectBooleanHashMap<Integer> map2 = ObjectBooleanHashMap.newMap();
        Assert.assertTrue(map2.updateValue(1, false, flip));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(1, true), map2);
        Assert.assertFalse(map2.updateValue(1, false, flip));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(1, false), map2);
        Assert.assertFalse(map2.updateValue(0, true, flip));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(0, false, 1, false), map2);
        Assert.assertTrue(map2.updateValue(0, true, flip));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(0, true, 1, false), map2);

        ObjectBooleanHashMap<Integer> map3 = ObjectBooleanHashMap.newMap();
        Assert.assertFalse(map3.updateValue(null, true, flip));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(null, false), map3);
        Assert.assertTrue(map3.updateValue(null, true, flip));
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(null, true), map3);
    }

    @Override
    @Test
    public void withKeysValues()
    {
        super.withKeysValues();
        ObjectBooleanHashMap<Integer> hashMap0 = new ObjectBooleanHashMap<Integer>().withKeysValues(1, true, 2, false);
        ObjectBooleanHashMap<Integer> hashMap1 = new ObjectBooleanHashMap<Integer>().withKeysValues(1, false, 2, false, 3, true);
        ObjectBooleanHashMap<Integer> hashMap2 = new ObjectBooleanHashMap<Integer>().withKeysValues(1, true, 2, true, 3, false, 4, false);
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(1, true, 2, false), hashMap0);
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(1, false, 2, false, 3, true), hashMap1);
        Assert.assertEquals(ObjectBooleanHashMap.newWithKeysValues(1, true, 2, true, 3, false, 4, false), hashMap2);
    }

    @Test
    public void injectInto()
    {
        ObjectBooleanHashMap<Integer> hashMap0 = new ObjectBooleanHashMap<Integer>().withKeysValues(1, true, 2, true, 3, false, 4, false);

        Integer total = hashMap0.injectInto(Integer.valueOf(0), (result, value) -> {
            if (value)
            {
                return result + 2;
            }

            return result;
        });

        Assert.assertEquals(Integer.valueOf(4), total);
    }

    @Test
    public void put_every_slot()
    {
        ObjectBooleanHashMap<String> hashMap = ObjectBooleanHashMap.newMap();
        for (int each = 2; each < 100; each++)
        {
            Assert.assertFalse(hashMap.get(String.valueOf(each)));
            hashMap.put(String.valueOf(each), each % 2 == 0);
            Assert.assertEquals(each % 2 == 0, hashMap.get(String.valueOf(each)));
            hashMap.remove(String.valueOf(each));
            Assert.assertFalse(hashMap.get(String.valueOf(each)));
        }
    }

    @Test
    public void remove_iterator_every_slot()
    {
        ObjectBooleanHashMap<String> hashMap = ObjectBooleanHashMap.newMap();
        for (int each = 2; each < 100; each++)
        {
            Assert.assertFalse(hashMap.get(String.valueOf(each)));
            hashMap.put(String.valueOf(each), false);
            Iterator<String> iterator = hashMap.keySet().iterator();
            Assert.assertTrue(iterator.hasNext());
            Assert.assertEquals(String.valueOf(each), iterator.next());
            iterator.remove();
            Assert.assertFalse(hashMap.get(String.valueOf(each)));
        }
    }

    @Test
    public void getIfAbsentPut_every_slot()
    {
        ObjectBooleanHashMap<String> hashMap = ObjectBooleanHashMap.newMap();
        for (int each = 2; each < 100; each++)
        {
            Assert.assertFalse(hashMap.get(String.valueOf(each)));
            hashMap.getIfAbsentPut(String.valueOf(each), each % 2 == 0);
            Assert.assertEquals(each % 2 == 0, hashMap.get(String.valueOf(each)));
        }
    }

    @Test
    public void getIfAbsentPutWith_every_slot()
    {
        BooleanFunction<String> functionLength = String::isEmpty;

        MutableObjectBooleanMap<String> hashMap = this.getEmptyMap();

        for (int each = 2; each < 100; each++)
        {
            Assert.assertFalse(hashMap.get(String.valueOf(each)));
            Assert.assertTrue(hashMap.getIfAbsentPutWith(String.valueOf(each), functionLength, ""));
            Assert.assertTrue(hashMap.get(String.valueOf(each)));
        }
    }

    @Test
    public void getIfAbsentPutWithKey_every_slot()
    {
        BooleanFunction<Integer> function = (Integer each) -> each % 2 == 0;

        MutableObjectBooleanMap<Integer> hashMap = this.getEmptyMap();

        for (int each = 2; each < 100; each++)
        {
            Assert.assertFalse(hashMap.get(each));
            Assert.assertEquals(each % 2 == 0, hashMap.getIfAbsentPutWithKey(each, function));
            Assert.assertEquals(each % 2 == 0, hashMap.get(each));
        }
    }

    @Test
    public void getIfAbsentPut_Function_every_slot()
    {
        BooleanFunction0 factory = () -> true;

        MutableObjectBooleanMap<String> hashMap = this.getEmptyMap();

        for (int each = 2; each < 100; each++)
        {
            Assert.assertFalse(hashMap.get(String.valueOf(each)));
            Assert.assertTrue(hashMap.getIfAbsentPut(String.valueOf(each), factory));
            Assert.assertTrue(hashMap.get(String.valueOf(each)));
        }
    }

    @Test
    public void updateValue_every_slot()
    {
        BooleanToBooleanFunction function = (boolean value) -> !value;

        ObjectBooleanHashMap<String> hashMap = new ObjectBooleanHashMap<>();

        for (int each = 2; each < 100; each++)
        {
            Assert.assertFalse(hashMap.get(String.valueOf(each)));
            Assert.assertEquals(each % 2 != 0, hashMap.updateValue(String.valueOf(each), each % 2 == 0, function));
            Assert.assertEquals(each % 2 != 0, hashMap.get(String.valueOf(each)));
        }
    }
}
