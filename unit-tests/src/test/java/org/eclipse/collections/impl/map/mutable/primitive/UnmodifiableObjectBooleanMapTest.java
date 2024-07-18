/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.impl.factory.Iterables;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableObjectBooleanMapTest extends AbstractMutableObjectBooleanMapTestCase
{
    private final UnmodifiableObjectBooleanMap<String> map = this.classUnderTest();

    @Override
    protected UnmodifiableObjectBooleanMap<String> classUnderTest()
    {
        return new UnmodifiableObjectBooleanMap<>(ObjectBooleanHashMap.newWithKeysValues("0", true, "1", true, "2", false));
    }

    @Override
    protected <T> UnmodifiableObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1)
    {
        return new UnmodifiableObjectBooleanMap<>(ObjectBooleanHashMap.newWithKeysValues(key1, value1));
    }

    @Override
    protected <T> UnmodifiableObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1, T key2, boolean value2)
    {
        return new UnmodifiableObjectBooleanMap<>(ObjectBooleanHashMap.newWithKeysValues(key1, value1, key2, value2));
    }

    @Override
    protected <T> UnmodifiableObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1, T key2, boolean value2, T key3, boolean value3)
    {
        return new UnmodifiableObjectBooleanMap<>(ObjectBooleanHashMap.newWithKeysValues(key1, value1, key2, value2, key3, value3));
    }

    @Override
    protected <T> UnmodifiableObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1, T key2, boolean value2, T key3, boolean value3, T key4, boolean value4)
    {
        return new UnmodifiableObjectBooleanMap<>(ObjectBooleanHashMap.newWithKeysValues(key1, value1, key2, value2, key3, value3, key4, value4));
    }

    @Override
    protected <T> UnmodifiableObjectBooleanMap<T> getEmptyMap()
    {
        return new UnmodifiableObjectBooleanMap<>(new ObjectBooleanHashMap<>());
    }

    @Override
    @Test
    public void clear()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.map.clear());
    }

    @Override
    @Test
    public void removeKey()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.map.removeKey("0"));
    }

    @Override
    @Test
    public void put()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.map.put("0", true));
    }

    @Override
    @Test
    public void getAndPut()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.map.getAndPut("0", true, false));
    }

    @Override
    @Test
    public void withKeysValues()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.map.withKeyValue("1", true));
    }

    @Override
    @Test
    public void withoutKey()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.map.withoutKey("0"));
    }

    @Override
    @Test
    public void withoutAllKeys()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.map.withoutAllKeys(FastList.newListWith("0", "1")));
    }

    @Override
    @Test
    public void withAllKeyValues()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.map.withAllKeyValues(Iterables.iList(PrimitiveTuples.pair("1", true))));
    }

    @Override
    @Test
    public void putDuplicateWithRemovedSlot()
    {
        String collision1 = AbstractMutableObjectBooleanMapTestCase.generateCollisions().getFirst();
        assertThrows(UnsupportedOperationException.class, () -> this.getEmptyMap().put(collision1, true));
    }

    @Override
    @Test
    public void get()
    {
        assertTrue(this.map.get("0"));
        assertTrue(this.map.get("1"));
        assertFalse(this.map.get("2"));

        assertFalse(this.map.get("5"));
    }

    @Override
    @Test
    public void getIfAbsent()
    {
        assertTrue(this.map.getIfAbsent("0", false));
        assertTrue(this.map.getIfAbsent("1", false));
        assertFalse(this.map.getIfAbsent("2", true));

        assertTrue(this.map.getIfAbsent("33", true));
        assertFalse(this.map.getIfAbsent("33", false));
    }

    @Override
    @Test
    public void getIfAbsentPut_Function()
    {
        assertTrue(this.map.getIfAbsentPut("0", () -> false));
    }

    @Test
    public void getIfAbsentPut_FunctionThrowsException()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.map.getIfAbsentPut("10", () -> false));
    }

    @Override
    @Test
    public void getIfAbsentPutWith()
    {
        BooleanFunction<String> functionLengthEven = string -> (string.length() & 1) == 0;

        assertTrue(this.map.getIfAbsentPutWith("0", functionLengthEven, "zeroValue"));
    }

    @Test
    public void getIfAbsentPutWithThrowsException()
    {
        BooleanFunction<String> functionLengthEven = string -> (string.length() & 1) == 0;

        assertThrows(UnsupportedOperationException.class, () -> this.map.getIfAbsentPutWith("10", functionLengthEven, "zeroValue"));
    }

    @Override
    @Test
    public void getIfAbsentPutWithKey()
    {
        BooleanFunction<Integer> function = anObject -> anObject == null || (anObject & 1) == 0;

        assertTrue(this.newWithKeysValues(0, true).getIfAbsentPutWithKey(0, function));
    }

    @Test
    public void getIfAbsentPutWithKeyThrowsException()
    {
        BooleanFunction<Integer> function = anObject -> anObject == null || (anObject & 1) == 0;

        assertThrows(UnsupportedOperationException.class, () -> this.<Integer>getEmptyMap().getIfAbsentPutWithKey(10, function));
    }

    @Override
    @Test
    public void getOrThrow()
    {
        assertTrue(this.map.getOrThrow("0"));
        assertTrue(this.map.getOrThrow("1"));
        assertFalse(this.map.getOrThrow("2"));

        assertThrows(IllegalStateException.class, () -> this.map.getOrThrow("5"));
        assertThrows(IllegalStateException.class, () -> this.map.getOrThrow(null));
    }

    @Override
    @Test
    public void contains()
    {
        assertTrue(this.map.contains(true));
        assertTrue(this.map.contains(false));
        assertFalse(this.getEmptyMap().contains(false));
        assertFalse(this.newWithKeysValues("0", true).contains(false));
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        assertTrue(this.map.containsAll(BooleanArrayList.newListWith(true, false)));
        assertTrue(this.map.containsAll(BooleanArrayList.newListWith(true, true)));
        assertTrue(this.map.containsAll(BooleanArrayList.newListWith(false, false)));
        assertFalse(this.getEmptyMap().containsAll(BooleanArrayList.newListWith(false, true)));
        assertFalse(this.newWithKeysValues("0", true).containsAll(BooleanArrayList.newListWith(false)));
    }

    @Override
    @Test
    public void containsAll()
    {
        assertTrue(this.map.containsAll(true, false));
        assertTrue(this.map.containsAll(true, true));
        assertTrue(this.map.containsAll(false, false));
        assertFalse(this.getEmptyMap().containsAll(false, true));
        assertFalse(this.newWithKeysValues("0", true).containsAll(false));
    }

    @Override
    @Test
    public void containsKey()
    {
        assertTrue(this.map.containsKey("0"));
        assertTrue(this.map.containsKey("1"));
        assertTrue(this.map.containsKey("2"));
        assertFalse(this.map.containsKey("3"));
        assertFalse(this.map.containsKey(null));
    }

    @Override
    @Test
    public void containsValue()
    {
        assertTrue(this.map.containsValue(true));
        assertTrue(this.map.containsValue(false));
        assertFalse(this.getEmptyMap().contains(true));
        assertFalse(this.newWithKeysValues("0", false).contains(true));
    }

    @Override
    @Test
    public void size()
    {
        Verify.assertSize(0, this.getEmptyMap());
        Verify.assertSize(1, this.newWithKeysValues(0, false));
        Verify.assertSize(1, this.newWithKeysValues(1, true));
        Verify.assertSize(1, this.newWithKeysValues(null, false));

        Verify.assertSize(2, this.newWithKeysValues(1, false, 5, false));
        Verify.assertSize(2, this.newWithKeysValues(0, true, 5, true));
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        super.asUnmodifiable();
        assertSame(this.map, this.map.asUnmodifiable());
    }

    @Override
    @Test
    public void iterator_remove()
    {
        UnmodifiableObjectBooleanMap<String> map = this.classUnderTest();
        Verify.assertNotEmpty(map);
        MutableBooleanIterator booleanIterator = map.booleanIterator();
        assertTrue(booleanIterator.hasNext());
        booleanIterator.next();
        assertThrows(UnsupportedOperationException.class, booleanIterator::remove);
    }

    @Override
    @Test
    public void iterator_throws_on_invocation_of_remove_before_next()
    {
        UnmodifiableObjectBooleanMap<String> map = this.classUnderTest();
        MutableBooleanIterator booleanIterator = map.booleanIterator();
        assertTrue(booleanIterator.hasNext());
        assertThrows(UnsupportedOperationException.class, booleanIterator::remove);
    }

    @Override
    @Test
    public void iterator_throws_on_consecutive_invocation_of_remove()
    {
        // Not applicable for Unmodifiable*
    }
}
