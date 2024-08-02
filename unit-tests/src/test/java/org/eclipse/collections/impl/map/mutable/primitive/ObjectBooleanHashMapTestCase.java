/*
 * Copyright (c) 2021 Goldman Sachs.
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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(16L, ((Object[]) keys.get(hashMap)).length);
        assertEquals(64L, ((BitSet) values.get(hashMap)).size());
    }

    @Test
    public void newWithInitialCapacity() throws Exception
    {
        Field keys = this.targetClass.getDeclaredField("keys");
        keys.setAccessible(true);
        Field values = this.targetClass.getDeclaredField("values");
        values.setAccessible(true);

        MutableObjectBooleanMap<String> hashMap = this.newMapWithInitialCapacity(3);
        assertEquals(8L, ((Object[]) keys.get(hashMap)).length);
        assertEquals(64L, ((BitSet) values.get(hashMap)).size());

        MutableObjectBooleanMap<String> hashMap2 = this.newMapWithInitialCapacity(15);
        assertEquals(32L, ((Object[]) keys.get(hashMap2)).length);
        assertEquals(64L, ((BitSet) values.get(hashMap)).size());
    }

    @Test
    public void newWithInitialCapacity_negative_throws()
    {
        assertThrows(IllegalArgumentException.class, () -> this.newMapWithInitialCapacity(-1));
    }

    @Test
    public void newMap() throws Exception
    {
        Field keys = this.targetClass.getDeclaredField("keys");
        keys.setAccessible(true);
        Field values = this.targetClass.getDeclaredField("values");
        values.setAccessible(true);

        MutableObjectBooleanMap<String> hashMap = this.getEmptyMap();
        assertEquals(16L, ((Object[]) keys.get(hashMap)).length);
        assertEquals(64L, ((BitSet) values.get(hashMap)).size());
        assertEquals(this.getEmptyMap(), hashMap);
    }

    @Test
    public void removeKeyIfAbsent()
    {
        MutableObjectBooleanMap<String> map0 = this.newWithKeysValues("0", false, "1", true);
        assertTrue(map0.removeKeyIfAbsent("1", false));
        assertEquals(this.newWithKeysValues("0", false), map0);
        assertFalse(map0.removeKeyIfAbsent("0", true));
        assertEquals(this.getEmptyMap(), map0);
        assertFalse(map0.removeKeyIfAbsent("1", false));
        assertTrue(map0.removeKeyIfAbsent("0", true));

        MutableObjectBooleanMap<String> map1 = this.newWithKeysValues("0", true, "1", false);
        assertTrue(map1.removeKeyIfAbsent("0", false));
        assertEquals(this.newWithKeysValues("1", false), map1);
        assertFalse(map1.removeKeyIfAbsent("1", true));
        assertEquals(this.getEmptyMap(), map1);
        assertFalse(map1.removeKeyIfAbsent("0", false));
        assertTrue(map1.removeKeyIfAbsent("1", true));

        assertTrue(this.map.removeKeyIfAbsent("5", true));
        assertEquals(this.newWithKeysValues("0", true, "1", true, "2", false), this.map);
        assertTrue(this.map.removeKeyIfAbsent("0", false));
        assertEquals(this.newWithKeysValues("1", true, "2", false), this.map);
        assertTrue(this.map.removeKeyIfAbsent("1", false));
        assertEquals(this.newWithKeysValues("2", false), this.map);
        assertFalse(this.map.removeKeyIfAbsent("2", true));
        assertEquals(this.getEmptyMap(), this.map);
        assertFalse(this.map.removeKeyIfAbsent("0", false));
        assertFalse(this.map.removeKeyIfAbsent("1", false));
        assertTrue(this.map.removeKeyIfAbsent("2", true));
        assertEquals(this.getEmptyMap(), this.map);
        Verify.assertEmpty(this.map);

        this.map.put(null, true);

        assertTrue(this.map.get(null));
        assertTrue(this.map.removeKeyIfAbsent(null, false));
        assertFalse(this.map.get(null));
    }

    @Test
    public void putWithRehash() throws Exception
    {
        ObjectBooleanHashMap<Integer> hashMap = ObjectBooleanHashMap.newMap();
        for (int i = 2; i < 10; i++)
        {
            assertFalse(hashMap.containsKey(i));
            hashMap.put(i, (i & 1) == 0);
        }

        Field keys = ObjectBooleanHashMap.class.getDeclaredField("keys");
        Field values = ObjectBooleanHashMap.class.getDeclaredField("values");
        keys.setAccessible(true);
        values.setAccessible(true);
        assertEquals(16L, ((Object[]) keys.get(hashMap)).length);
        assertEquals(64L, ((BitSet) values.get(hashMap)).size());
        Verify.assertSize(8, hashMap);
        for (int i = 2; i < 10; i++)
        {
            assertTrue(hashMap.containsKey(i));
        }

        assertTrue(hashMap.containsValue(false));
        assertTrue(hashMap.containsValue(true));
        hashMap.put(10, true);
        assertEquals(32L, ((Object[]) keys.get(hashMap)).length);
        assertEquals(64L, ((BitSet) values.get(hashMap)).size());

        for (int i = 11; i < 75; i++)
        {
            assertFalse(hashMap.containsKey(i), String.valueOf(i));
            hashMap.put(i, (i & 1) == 0);
        }
        assertEquals(256L, ((Object[]) keys.get(hashMap)).length);
        assertEquals(256L, ((BitSet) values.get(hashMap)).size());
    }

    @Test
    public void getIfAbsentPut()
    {
        MutableObjectBooleanMap<Integer> map1 = this.getEmptyMap();
        assertTrue(map1.getIfAbsentPut(0, true));
        assertTrue(map1.getIfAbsentPut(0, false));
        assertEquals(this.newWithKeysValues(0, true), map1);
        assertTrue(map1.getIfAbsentPut(1, true));
        assertTrue(map1.getIfAbsentPut(1, false));
        assertEquals(this.newWithKeysValues(0, true, 1, true), map1);

        MutableObjectBooleanMap<Integer> map2 = this.getEmptyMap();
        assertFalse(map2.getIfAbsentPut(1, false));
        assertFalse(map2.getIfAbsentPut(1, true));
        assertEquals(this.newWithKeysValues(1, false), map2);
        assertFalse(map2.getIfAbsentPut(0, false));
        assertFalse(map2.getIfAbsentPut(0, true));
        assertEquals(this.newWithKeysValues(0, false, 1, false), map2);

        MutableObjectBooleanMap<Integer> map3 = this.getEmptyMap();
        assertTrue(map3.getIfAbsentPut(null, true));
        assertTrue(map3.getIfAbsentPut(null, false));
        assertEquals(this.newWithKeysValues(null, true), map3);
    }

    @Test
    public void updateValue()
    {
        BooleanToBooleanFunction flip = value -> !value;

        MutableObjectBooleanMap<Integer> map1 = this.getEmptyMap();
        assertTrue(map1.updateValue(0, false, flip));
        assertEquals(this.newWithKeysValues(0, true), map1);
        assertFalse(map1.updateValue(0, false, flip));
        assertEquals(this.newWithKeysValues(0, false), map1);
        assertFalse(map1.updateValue(1, true, flip));
        assertEquals(this.newWithKeysValues(0, false, 1, false), map1);
        assertTrue(map1.updateValue(1, true, flip));
        assertEquals(this.newWithKeysValues(0, false, 1, true), map1);

        MutableObjectBooleanMap<Integer> map2 = this.getEmptyMap();
        assertTrue(map2.updateValue(1, false, flip));
        assertEquals(this.newWithKeysValues(1, true), map2);
        assertFalse(map2.updateValue(1, false, flip));
        assertEquals(this.newWithKeysValues(1, false), map2);
        assertFalse(map2.updateValue(0, true, flip));
        assertEquals(this.newWithKeysValues(0, false, 1, false), map2);
        assertTrue(map2.updateValue(0, true, flip));
        assertEquals(this.newWithKeysValues(0, true, 1, false), map2);

        MutableObjectBooleanMap<Integer> map3 = this.getEmptyMap();
        assertFalse(map3.updateValue(null, true, flip));
        assertEquals(this.newWithKeysValues(null, false), map3);
        assertTrue(map3.updateValue(null, true, flip));
        assertEquals(this.newWithKeysValues(null, true), map3);
    }

    @Override
    @Test
    public void withKeysValues()
    {
        super.withKeysValues();
        ObjectBooleanHashMap<Integer> hashMap0 = new ObjectBooleanHashMap<Integer>().withKeysValues(1, true, 2, false);
        ObjectBooleanHashMap<Integer> hashMap1 = new ObjectBooleanHashMap<Integer>().withKeysValues(1, false, 2, false, 3, true);
        ObjectBooleanHashMap<Integer> hashMap2 = new ObjectBooleanHashMap<Integer>().withKeysValues(1, true, 2, true, 3, false, 4, false);
        assertEquals(ObjectBooleanHashMap.newWithKeysValues(1, true, 2, false), hashMap0);
        assertEquals(ObjectBooleanHashMap.newWithKeysValues(1, false, 2, false, 3, true), hashMap1);
        assertEquals(ObjectBooleanHashMap.newWithKeysValues(1, true, 2, true, 3, false, 4, false), hashMap2);
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

        assertEquals(Integer.valueOf(4), total);
    }

    @Test
    public void put_every_slot()
    {
        ObjectBooleanHashMap<String> hashMap = ObjectBooleanHashMap.newMap();
        for (int each = 2; each < 100; each++)
        {
            assertFalse(hashMap.get(String.valueOf(each)));
            hashMap.put(String.valueOf(each), each % 2 == 0);
            assertEquals(each % 2 == 0, hashMap.get(String.valueOf(each)));
            hashMap.remove(String.valueOf(each));
            assertFalse(hashMap.get(String.valueOf(each)));
        }
    }

    @Test
    public void remove_iterator_every_slot()
    {
        ObjectBooleanHashMap<String> hashMap = ObjectBooleanHashMap.newMap();
        for (int each = 2; each < 100; each++)
        {
            assertFalse(hashMap.get(String.valueOf(each)));
            hashMap.put(String.valueOf(each), false);
            Iterator<String> iterator = hashMap.keySet().iterator();
            assertTrue(iterator.hasNext());
            assertEquals(String.valueOf(each), iterator.next());
            iterator.remove();
            assertFalse(hashMap.get(String.valueOf(each)));
        }
    }

    @Test
    public void getIfAbsentPut_every_slot()
    {
        ObjectBooleanHashMap<String> hashMap = ObjectBooleanHashMap.newMap();
        for (int each = 2; each < 100; each++)
        {
            assertFalse(hashMap.get(String.valueOf(each)));
            hashMap.getIfAbsentPut(String.valueOf(each), each % 2 == 0);
            assertEquals(each % 2 == 0, hashMap.get(String.valueOf(each)));
        }
    }

    @Test
    public void getIfAbsentPutWith_every_slot()
    {
        BooleanFunction<String> functionLength = String::isEmpty;

        MutableObjectBooleanMap<String> hashMap = this.getEmptyMap();

        for (int each = 2; each < 100; each++)
        {
            assertFalse(hashMap.get(String.valueOf(each)));
            assertTrue(hashMap.getIfAbsentPutWith(String.valueOf(each), functionLength, ""));
            assertTrue(hashMap.get(String.valueOf(each)));
        }
    }

    @Test
    public void getIfAbsentPutWithKey_every_slot()
    {
        BooleanFunction<Integer> function = (Integer each) -> each % 2 == 0;

        MutableObjectBooleanMap<Integer> hashMap = this.getEmptyMap();

        for (int each = 2; each < 100; each++)
        {
            assertFalse(hashMap.get(each));
            assertEquals(each % 2 == 0, hashMap.getIfAbsentPutWithKey(each, function));
            assertEquals(each % 2 == 0, hashMap.get(each));
        }
    }

    @Test
    public void getIfAbsentPut_Function_every_slot()
    {
        BooleanFunction0 factory = () -> true;

        MutableObjectBooleanMap<String> hashMap = this.getEmptyMap();

        for (int each = 2; each < 100; each++)
        {
            assertFalse(hashMap.get(String.valueOf(each)));
            assertTrue(hashMap.getIfAbsentPut(String.valueOf(each), factory));
            assertTrue(hashMap.get(String.valueOf(each)));
        }
    }

    @Test
    public void updateValue_every_slot()
    {
        BooleanToBooleanFunction function = (boolean value) -> !value;

        ObjectBooleanHashMap<String> hashMap = new ObjectBooleanHashMap<>();

        for (int each = 2; each < 100; each++)
        {
            assertFalse(hashMap.get(String.valueOf(each)));
            assertEquals(each % 2 != 0, hashMap.updateValue(String.valueOf(each), each % 2 == 0, function));
            assertEquals(each % 2 != 0, hashMap.get(String.valueOf(each)));
        }
    }
}
