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

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.map.primitive.ImmutableObjectBooleanMap;
import org.eclipse.collections.api.map.primitive.ObjectBooleanMap;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractObjectBooleanMapTestCase
{
    protected abstract ObjectBooleanMap<String> classUnderTest();

    protected abstract <T> ObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1);

    protected abstract <T> ObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1, T key2, boolean value2);

    protected abstract <T> ObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1, T key2, boolean value2, T key3, boolean value3);

    protected abstract <T> ObjectBooleanMap<T> newWithKeysValues(T key1, boolean value1, T key2, boolean value2, T key3, boolean value3, T key4, boolean value4);

    protected abstract <T> ObjectBooleanMap<T> getEmptyMap();

    @Test
    public void get()
    {
        assertTrue(this.classUnderTest().get("0"));
        assertTrue(this.classUnderTest().get("1"));
        assertFalse(this.classUnderTest().get("2"));

        assertFalse(this.classUnderTest().get("5"));
    }

    @Test
    public void getIfAbsent()
    {
        assertTrue(this.classUnderTest().getIfAbsent("0", false));
        assertTrue(this.classUnderTest().getIfAbsent("1", false));
        assertFalse(this.classUnderTest().getIfAbsent("2", true));

        assertTrue(this.classUnderTest().getIfAbsent("5", true));
        assertFalse(this.classUnderTest().getIfAbsent("5", false));

        assertTrue(this.classUnderTest().getIfAbsent(null, true));
        assertFalse(this.classUnderTest().getIfAbsent(null, false));
    }

    @Test
    public void getOrThrow()
    {
        assertTrue(this.classUnderTest().getOrThrow("0"));
        assertTrue(this.classUnderTest().getOrThrow("1"));
        assertFalse(this.classUnderTest().getOrThrow("2"));

        assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOrThrow("5"));
        assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOrThrow(null));
    }

    @Test
    public void containsKey()
    {
        assertTrue(this.classUnderTest().containsKey("0"));
        assertTrue(this.classUnderTest().containsKey("1"));
        assertTrue(this.classUnderTest().containsKey("2"));
        assertFalse(this.classUnderTest().containsKey("3"));
        assertFalse(this.classUnderTest().containsKey(null));
    }

    @Test
    public void containsValue()
    {
        assertTrue(this.classUnderTest().containsValue(true));
        assertTrue(this.classUnderTest().containsValue(false));
    }

    @Test
    public void size()
    {
        Verify.assertEmpty(this.getEmptyMap());
        Verify.assertSize(1, this.newWithKeysValues(0, false));
        Verify.assertSize(1, this.newWithKeysValues(1, true));
        Verify.assertSize(1, this.newWithKeysValues(null, false));

        Verify.assertSize(2, this.newWithKeysValues(1, false, 5, false));
        Verify.assertSize(2, this.newWithKeysValues(0, true, 5, true));
    }

    @Test
    public void isEmpty()
    {
        Verify.assertEmpty(this.getEmptyMap());
        assertFalse(this.classUnderTest().isEmpty());
        assertFalse(this.newWithKeysValues(null, false).isEmpty());
        assertFalse(this.newWithKeysValues(1, true).isEmpty());
        assertFalse(this.newWithKeysValues(0, false).isEmpty());
        assertFalse(this.newWithKeysValues(50, true).isEmpty());
    }

    @Test
    public void notEmpty()
    {
        assertFalse(this.getEmptyMap().notEmpty());
        assertTrue(this.classUnderTest().notEmpty());
        assertTrue(this.newWithKeysValues(1, true).notEmpty());
        assertTrue(this.newWithKeysValues(null, false).notEmpty());
        assertTrue(this.newWithKeysValues(0, true).notEmpty());
        assertTrue(this.newWithKeysValues(50, false).notEmpty());
    }

    @Test
    public void testEquals()
    {
        ObjectBooleanMap<Integer> map1 = this.newWithKeysValues(0, true, 1, false, null, false);
        ObjectBooleanMap<Integer> map2 = this.newWithKeysValues(null, false, 0, true, 1, false);
        ObjectBooleanMap<Integer> map3 = this.newWithKeysValues(0, true, 1, true, null, false);
        ObjectBooleanMap<Integer> map4 = this.newWithKeysValues(0, false, 1, false, null, false);
        ObjectBooleanMap<Integer> map5 = this.newWithKeysValues(0, true, 1, false, null, true);
        ObjectBooleanMap<Integer> map6 = this.newWithKeysValues(null, true, 60, false, 70, true);
        ObjectBooleanMap<Integer> map7 = this.newWithKeysValues(null, true, 60, false);
        ObjectBooleanMap<Integer> map8 = this.newWithKeysValues(0, true, 1, false);

        Verify.assertEqualsAndHashCode(map1, map2);
        Verify.assertPostSerializedEqualsAndHashCode(map1);
        assertNotEquals(map1, map3);
        assertNotEquals(map1, map4);
        assertNotEquals(map1, map5);
        assertNotEquals(map7, map6);
        assertNotEquals(map7, map8);
    }

    @Test
    public void testHashCode()
    {
        assertEquals(
                UnifiedMap.newWithKeysValues(0, false, 1, true, 32, true).hashCode(),
                this.newWithKeysValues(32, true, 0, false, 1, true).hashCode());
        assertEquals(
                UnifiedMap.newWithKeysValues(50, true, 60, true, null, false).hashCode(),
                this.newWithKeysValues(50, true, 60, true, null, false).hashCode());
        assertEquals(UnifiedMap.newMap().hashCode(), this.getEmptyMap().hashCode());
    }

    @Test
    public void testToString()
    {
        assertEquals("{}", this.getEmptyMap().toString());
        assertEquals("{0=false}", this.newWithKeysValues(0, false).toString());
        assertEquals("{1=true}", this.newWithKeysValues(1, true).toString());
        assertEquals("{5=true}", this.newWithKeysValues(5, true).toString());

        ObjectBooleanMap<Integer> map1 = this.newWithKeysValues(0, true, 1, false);
        assertTrue(
                "{0=true, 1=false}".equals(map1.toString())
                        || "{1=false, 0=true}".equals(map1.toString()),
                map1.toString());

        ObjectBooleanMap<Integer> map2 = this.newWithKeysValues(1, false, null, true);
        assertTrue(
                "{1=false, null=true}".equals(map2.toString())
                        || "{null=true, 1=false}".equals(map2.toString()),
                map2.toString());

        ObjectBooleanMap<Integer> map3 = this.newWithKeysValues(1, true, null, true);
        assertTrue(
                "{1=true, null=true}".equals(map3.toString())
                        || "{null=true, 1=true}".equals(map3.toString()),
                map3.toString());
    }

    @Test
    public void forEachValue()
    {
        ObjectBooleanMap<Integer> map01 = this.newWithKeysValues(0, true, 1, false);
        String[] sum01 = new String[1];
        sum01[0] = "";
        map01.forEachValue(each -> sum01[0] += String.valueOf(each));
        assertTrue("truefalse".equals(sum01[0]) || "falsetrue".equals(sum01[0]));

        ObjectBooleanMap<Integer> map = this.newWithKeysValues(3, true, 4, true);
        String[] sum = new String[1];
        sum[0] = "";
        map.forEachValue(each -> sum[0] += String.valueOf(each));
        assertEquals("truetrue", sum[0]);

        ObjectBooleanMap<Integer> map1 = this.newWithKeysValues(3, false, null, true);
        String[] sum1 = new String[1];
        sum1[0] = "";
        map1.forEachValue(each -> sum1[0] += String.valueOf(each));
        assertTrue("truefalse".equals(sum1[0]) || "falsetrue".equals(sum1[0]));
    }

    @Test
    public void forEach()
    {
        ObjectBooleanMap<Integer> map01 = this.newWithKeysValues(0, true, 1, false);
        String[] sum01 = new String[1];
        sum01[0] = "";
        map01.forEach(each -> sum01[0] += String.valueOf(each));
        assertTrue("truefalse".equals(sum01[0]) || "falsetrue".equals(sum01[0]));

        ObjectBooleanMap<Integer> map = this.newWithKeysValues(3, true, 4, true);
        String[] sum = new String[1];
        sum[0] = "";
        map.forEach(each -> sum[0] += String.valueOf(each));
        assertEquals("truetrue", sum[0]);

        ObjectBooleanMap<Integer> map1 = this.newWithKeysValues(3, false, null, true);
        String[] sum1 = new String[1];
        sum1[0] = "";
        map1.forEach(each -> sum1[0] += String.valueOf(each));
        assertTrue("truefalse".equals(sum1[0]) || "falsetrue".equals(sum1[0]));
    }

    @Test
    public void forEachKey()
    {
        ObjectBooleanMap<Integer> map01 = this.newWithKeysValues(0, true, 1, false);
        int[] sum01 = new int[1];
        map01.forEachKey(each -> sum01[0] += each);
        assertEquals(1, sum01[0]);

        ObjectBooleanMap<Integer> map = this.newWithKeysValues(3, false, null, true);
        String[] sum = new String[1];
        sum[0] = "";
        map.forEachKey(each -> sum[0] += String.valueOf(each));
        assertTrue("3null".equals(sum[0]) || "null3".equals(sum[0]));
    }

    @Test
    public void forEachKeyValue()
    {
        ObjectBooleanMap<Integer> map01 = this.newWithKeysValues(0, true, 1, false);
        String[] sumValue01 = new String[1];
        sumValue01[0] = "";
        int[] sumKey01 = new int[1];
        map01.forEachKeyValue((eachKey, eachValue) ->
        {
            sumKey01[0] += eachKey;
            sumValue01[0] += eachValue;
        });
        assertEquals(1, sumKey01[0]);
        assertTrue("truefalse".equals(sumValue01[0]) || "falsetrue".equals(sumValue01[0]));

        ObjectBooleanMap<Integer> map = this.newWithKeysValues(3, true, null, false);
        String[] sumKey = new String[1];
        sumKey[0] = "";
        String[] sumValue = new String[1];
        sumValue[0] = "";
        map.forEachKeyValue((eachKey, eachValue) ->
        {
            sumKey[0] += String.valueOf(eachKey);
            sumValue[0] += eachValue;
        });
        assertTrue("3null".equals(sumKey[0]) || "null3".equals(sumKey[0]), sumKey[0]);
        assertTrue("truefalse".equals(sumValue[0]) || "falsetrue".equals(sumValue[0]));
    }

    @Test
    public void makeString()
    {
        assertEquals("", this.<String>getEmptyMap().makeString());
        assertEquals("true", this.newWithKeysValues(0, true).makeString());
        assertEquals("false", this.newWithKeysValues(1, false).makeString());
        assertEquals("true", this.newWithKeysValues(null, true).makeString());

        ObjectBooleanMap<Integer> map2 = this.newWithKeysValues(1, true, 32, false);
        assertTrue(
                "[true/false]".equals(map2.makeString("[", "/", "]"))
                        || "[false/true]".equals(map2.makeString("[", "/", "]")),
                map2.makeString("[", "/", "]"));

        assertTrue(
                "true/false".equals(map2.makeString("/"))
                        || "false/true".equals(map2.makeString("/")),
                map2.makeString("/"));
    }

    @Test
    public void appendString()
    {
        Appendable appendable = new StringBuilder();
        this.getEmptyMap().appendString(appendable);
        assertEquals("", appendable.toString());

        Appendable appendable0 = new StringBuilder();
        this.newWithKeysValues(0, true).appendString(appendable0);
        assertEquals("true", appendable0.toString());

        Appendable appendable1 = new StringBuilder();
        this.newWithKeysValues(1, false).appendString(appendable1);
        assertEquals("false", appendable1.toString());

        Appendable appendable2 = new StringBuilder();
        this.newWithKeysValues(null, false).appendString(appendable2);
        assertEquals("false", appendable2.toString());

        Appendable appendable3 = new StringBuilder();
        ObjectBooleanMap<Integer> map1 = this.newWithKeysValues(0, true, 1, false);
        map1.appendString(appendable3);
        assertTrue(
                "true, false".equals(appendable3.toString())
                        || "false, true".equals(appendable3.toString()),
                appendable3.toString());

        Appendable appendable4 = new StringBuilder();
        map1.appendString(appendable4, "/");
        assertTrue(
                "true/false".equals(appendable4.toString())
                        || "false/true".equals(appendable4.toString()),
                appendable4.toString());

        Appendable appendable5 = new StringBuilder();
        map1.appendString(appendable5, "[", "/", "]");
        assertTrue(
                "[true/false]".equals(appendable5.toString())
                        || "[false/true]".equals(appendable5.toString()),
                appendable5.toString());
    }

    @Test
    public void select()
    {
        assertEquals(BooleanHashBag.newBagWith(true, true), this.classUnderTest().select(BooleanPredicates.isTrue()).toBag());
        assertEquals(BooleanHashBag.newBagWith(false), this.classUnderTest().select(BooleanPredicates.isFalse()).toBag());
        assertEquals(BooleanHashBag.newBagWith(true, true, false), this.classUnderTest().select(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())).toBag());
        assertEquals(new BooleanHashBag(), this.classUnderTest().select(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())).toBag());

        assertEquals(this.newWithKeysValues("0", true), this.classUnderTest().select((object, value) -> (Integer.parseInt(object) & 1) == 0 && value));
        assertEquals(this.newWithKeysValues("2", false), this.classUnderTest().select((object, value) -> (Integer.parseInt(object) & 1) == 0 && !value));
        assertEquals(ObjectBooleanHashMap.newMap(), this.classUnderTest().select((object, value) -> (Integer.parseInt(object) & 1) != 0 && !value));
    }

    @Test
    public void reject()
    {
        assertEquals(BooleanHashBag.newBagWith(false), this.classUnderTest().reject(BooleanPredicates.isTrue()).toBag());
        assertEquals(BooleanHashBag.newBagWith(true, true), this.classUnderTest().reject(BooleanPredicates.isFalse()).toBag());
        assertEquals(new BooleanHashBag(), this.classUnderTest().reject(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())).toBag());
        assertEquals(BooleanHashBag.newBagWith(true, true, false), this.classUnderTest().reject(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())).toBag());

        assertEquals(this.newWithKeysValues("1", true, "2", false), this.classUnderTest().reject((object, value) -> (Integer.parseInt(object) & 1) == 0 && value));
        assertEquals(this.newWithKeysValues("0", true, "1", true), this.classUnderTest().reject((object, value) -> (Integer.parseInt(object) & 1) == 0 && !value));
        assertEquals(this.newWithKeysValues("0", true, "1", true, "2", false), this.classUnderTest().reject((object, value) -> (Integer.parseInt(object) & 1) != 0 && !value));
    }

    @Test
    public void count()
    {
        assertEquals(2L, this.classUnderTest().count(BooleanPredicates.isTrue()));
        assertEquals(1L, this.classUnderTest().count(BooleanPredicates.isFalse()));
        assertEquals(3L, this.classUnderTest().count(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        assertEquals(0L, this.classUnderTest().count(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
    }

    @Test
    public void anySatisfy()
    {
        assertTrue(this.classUnderTest().anySatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.classUnderTest().anySatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.classUnderTest().anySatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        assertFalse(this.classUnderTest().anySatisfy(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
    }

    @Test
    public void allSatisfy()
    {
        assertFalse(this.classUnderTest().allSatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.classUnderTest().allSatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.classUnderTest().allSatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        assertFalse(this.classUnderTest().allSatisfy(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
    }

    @Test
    public void noneSatisfy()
    {
        assertFalse(this.classUnderTest().noneSatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.classUnderTest().noneSatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.classUnderTest().noneSatisfy(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        assertFalse(this.classUnderTest().noneSatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
    }

    @Test
    public void detectIfNone()
    {
        assertTrue(this.classUnderTest().detectIfNone(BooleanPredicates.isTrue(), false));
        assertFalse(this.classUnderTest().detectIfNone(BooleanPredicates.isFalse(), true));
        assertFalse(this.newWithKeysValues("0", true, "1", true).detectIfNone(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse()), false));
    }

    @Test
    public void collect()
    {
        ObjectBooleanMap<String> map1 = this.newWithKeysValues("0", true, "1", false);
        ObjectBooleanMap<String> map2 = this.newWithKeysValues("0", true);
        ObjectBooleanMap<String> map3 = this.newWithKeysValues("0", false);
        assertTrue(FastList.newListWith("true", "false").equals(map1.collect(String::valueOf)) || FastList.newListWith("false", "true").equals(map1.collect(String::valueOf)));
        assertEquals(FastList.newListWith("true"), map2.collect(String::valueOf));
        assertEquals(FastList.newListWith("false"), map3.collect(String::valueOf));
    }

    @Test
    public void toArray()
    {
        ObjectBooleanMap<String> map1 = this.newWithKeysValues(null, true, "1", false);
        ObjectBooleanMap<String> map2 = this.newWithKeysValues("0", true);
        ObjectBooleanMap<String> map3 = this.newWithKeysValues("0", false);

        assertTrue(Arrays.equals(new boolean[]{true, false}, map1.toArray())
                || Arrays.equals(new boolean[]{false, true}, map1.toArray()));
        assertTrue(Arrays.equals(new boolean[]{true}, map2.toArray()));
        assertTrue(Arrays.equals(new boolean[]{false}, map3.toArray()));
    }

    @Test
    public void toArrayWithTargetArray()
    {
        ObjectBooleanMap<String> map1 = this.newWithKeysValues(null, true, "1", false);
        ObjectBooleanMap<String> map2 = this.newWithKeysValues("0", true);
        ObjectBooleanMap<String> map3 = this.newWithKeysValues("0", false);

        assertTrue(Arrays.equals(new boolean[]{true, false}, map1.toArray(new boolean[]{}))
                || Arrays.equals(new boolean[]{false, true}, map1.toArray(new boolean[]{})));
        assertTrue(Arrays.equals(new boolean[]{true, false}, map1.toArray(new boolean[map1.size()]))
                || Arrays.equals(new boolean[]{false, true}, map1.toArray(new boolean[map1.size()])));
        assertTrue(Arrays.equals(new boolean[]{true}, map2.toArray(new boolean[]{})));
        assertTrue(Arrays.equals(new boolean[]{true}, map2.toArray(new boolean[map2.size()])));
        assertTrue(Arrays.equals(new boolean[]{false}, map3.toArray(new boolean[]{})));
        assertTrue(Arrays.equals(new boolean[]{false}, map3.toArray(new boolean[map3.size()])));
    }

    @Test
    public void contains()
    {
        assertTrue(this.classUnderTest().contains(true));
        assertTrue(this.classUnderTest().contains(false));
    }

    @Test
    public void containsAll()
    {
        assertTrue(this.classUnderTest().containsAll(true, false));
        assertTrue(this.classUnderTest().containsAll(true, true));
        assertTrue(this.classUnderTest().containsAll(false, false));
    }

    @Test
    public void containsAllIterable()
    {
        assertTrue(this.classUnderTest().containsAll(BooleanArrayList.newListWith(true, false)));
        assertTrue(this.classUnderTest().containsAll(BooleanArrayList.newListWith(true, true)));
        assertTrue(this.classUnderTest().containsAll(BooleanArrayList.newListWith(false, false)));
    }

    @Test
    public void toList()
    {
        ObjectBooleanMap<String> map1 = this.newWithKeysValues(null, true, "1", false);
        ObjectBooleanMap<String> map2 = this.newWithKeysValues("0", true);
        ObjectBooleanMap<String> map3 = this.newWithKeysValues("0", false);

        assertTrue(BooleanArrayList.newListWith(true, false).equals(map1.toList())
                || BooleanArrayList.newListWith(false, true).equals(map1.toList()), map1.toList().toString());
        assertEquals(BooleanArrayList.newListWith(true), map2.toList());
        assertEquals(BooleanArrayList.newListWith(false), map3.toList());
    }

    @Test
    public void toSet()
    {
        ObjectBooleanMap<String> map1 = this.newWithKeysValues("1", false, null, true, "2", false);
        ObjectBooleanMap<String> map0 = this.newWithKeysValues("1", false, null, true, "2", true);
        ObjectBooleanMap<String> map2 = this.newWithKeysValues("0", true);
        ObjectBooleanMap<String> map3 = this.newWithKeysValues("0", false);

        assertEquals(BooleanHashSet.newSetWith(false, true), map1.toSet());
        assertEquals(BooleanHashSet.newSetWith(false, true), map0.toSet());
        assertEquals(BooleanHashSet.newSetWith(true), map2.toSet());
        assertEquals(BooleanHashSet.newSetWith(false), map3.toSet());
    }

    @Test
    public void toBag()
    {
        ObjectBooleanMap<String> map1 = this.newWithKeysValues("1", false, null, true, "2", false);
        ObjectBooleanMap<String> map0 = this.newWithKeysValues("1", false, null, true, "2", true);
        ObjectBooleanMap<String> map2 = this.newWithKeysValues("0", true);
        ObjectBooleanMap<String> map3 = this.newWithKeysValues("0", false);

        assertEquals(BooleanHashBag.newBagWith(false, false, true), map1.toBag());
        assertEquals(BooleanHashBag.newBagWith(false, true, true), map0.toBag());
        assertEquals(BooleanHashBag.newBagWith(true), map2.toBag());
        assertEquals(BooleanHashBag.newBagWith(false), map3.toBag());
    }

    @Test
    public void asLazy()
    {
        Verify.assertSize(this.classUnderTest().toList().size(), this.classUnderTest().asLazy().toList());
        assertTrue(this.classUnderTest().asLazy().toList().containsAll(this.classUnderTest().toList()));
    }

    @Test
    public void iterator()
    {
        ObjectBooleanMap<String> map1 = this.newWithKeysValues(null, true, "EclipseCollections", false);
        ObjectBooleanMap<String> map2 = this.newWithKeysValues("0", true);
        ObjectBooleanMap<String> map3 = this.newWithKeysValues("0", false);

        BooleanIterator iterator1 = map1.booleanIterator();
        assertTrue(iterator1.hasNext());
        boolean first = iterator1.next();
        assertTrue(iterator1.hasNext());
        boolean second = iterator1.next();
        assertEquals(first, !second);
        assertFalse(iterator1.hasNext());
        assertThrows(NoSuchElementException.class, iterator1::next);

        BooleanIterator iterator2 = map2.booleanIterator();
        assertTrue(iterator2.hasNext());
        assertTrue(iterator2.next());
        assertFalse(iterator2.hasNext());
        assertThrows(NoSuchElementException.class, iterator2::next);

        BooleanIterator iterator3 = map3.booleanIterator();
        assertTrue(iterator3.hasNext());
        assertFalse(iterator3.next());
        assertFalse(iterator3.hasNext());
        assertThrows(NoSuchElementException.class, iterator3::next);
    }

    @Test
    public void toImmutable()
    {
        assertEquals(this.classUnderTest(), this.classUnderTest().toImmutable());
        Verify.assertInstanceOf(ImmutableObjectBooleanMap.class, this.classUnderTest().toImmutable());
    }
}
