/*
 * Copyright (c) 2023 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable.primitive;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.ImmutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.tuple.primitive.BooleanIntPair;
import org.eclipse.collections.impl.collection.mutable.primitive.AbstractMutableBooleanCollectionTestCase;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.math.MutableInteger;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Abstract JUnit test for {@link MutableBooleanList}.
 */
public abstract class AbstractBooleanListTestCase extends AbstractMutableBooleanCollectionTestCase
{
    @Override
    protected abstract MutableBooleanList classUnderTest();

    @Override
    protected abstract MutableBooleanList newWith(boolean... elements);

    @Override
    protected MutableBooleanList newMutableCollectionWith(boolean... elements)
    {
        return BooleanArrayList.newListWith(elements);
    }

    @Override
    protected MutableList<Object> newObjectCollectionWith(Object... elements)
    {
        return FastList.newListWith(elements);
    }

    @Test
    public void get()
    {
        MutableBooleanList list = this.classUnderTest();
        assertTrue(list.get(0));
        assertFalse(list.get(1));
        assertTrue(list.get(2));
    }

    @Test
    public void get_throws_index_greater_than_size()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.classUnderTest().get(3));
    }

    @Test
    public void get_throws_empty_list()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.newWith().get(0));
    }

    @Test
    public void get_throws_index_negative()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.classUnderTest().get(-1));
    }

    @Test
    public void getFirst()
    {
        MutableBooleanList singleItemList = this.newWith(true);
        assertTrue(singleItemList.getFirst());
        assertTrue(this.classUnderTest().getFirst());
    }

    @Test
    public void getFirst_emptyList_throws()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.newWith().getFirst());
    }

    @Test
    public void getLast()
    {
        MutableBooleanList singleItemList = this.newWith(true);
        assertTrue(singleItemList.getLast());
        assertTrue(this.classUnderTest().getLast());
        assertFalse(this.newWith(true, true, false).getLast());
    }

    @Test
    public void getLast_emptyList_throws()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.newWith().getLast());
    }

    @Test
    public void subList()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().subList(0, 1));
    }

    @Test
    public void indexOf()
    {
        MutableBooleanList arrayList = this.newWith(true, false, true);
        assertEquals(0L, arrayList.indexOf(true));
        assertEquals(1L, arrayList.indexOf(false));
        assertEquals(-1L, this.newWith(false, false).indexOf(true));
        MutableBooleanList emptyList = this.newWith();
        assertEquals(-1L, emptyList.indexOf(true));
        assertEquals(-1L, emptyList.indexOf(false));
    }

    @Test
    public void boxed()
    {
        MutableBooleanList list = this.newWith(true, false, true);
        assertEquals(Lists.mutable.of(true, false, true), list.boxed());
    }

    @Test
    public void lastIndexOf()
    {
        MutableBooleanList list = this.newWith(true, false, true);
        assertEquals(2L, list.lastIndexOf(true));
        assertEquals(1L, list.lastIndexOf(false));
        assertEquals(-1L, this.newWith(false, false).lastIndexOf(true));
        MutableBooleanList emptyList = this.newWith();
        assertEquals(-1L, emptyList.lastIndexOf(true));
        assertEquals(-1L, emptyList.lastIndexOf(false));
    }

    @Test
    public void addAtIndex()
    {
        MutableBooleanList emptyList = this.newWith();
        emptyList.addAtIndex(0, false);
        assertEquals(BooleanArrayList.newListWith(false), emptyList);
        MutableBooleanList list = this.classUnderTest();
        list.addAtIndex(3, true);
        assertEquals(BooleanArrayList.newListWith(true, false, true, true), list);
        list.addAtIndex(2, false);
        assertEquals(BooleanArrayList.newListWith(true, false, false, true, true), list);
    }

    @Test
    public void addAtIndex_throws_index_greater_than_size()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.newWith().addAtIndex(1, false));
    }

    @Test
    public void addAtIndex_throws_index_negative()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.classUnderTest().addAtIndex(-1, true));
    }

    @Override
    @Test
    public void addAllArray()
    {
        super.addAllArray();
        MutableBooleanList list = this.classUnderTest();
        assertFalse(list.addAllAtIndex(1));
        assertTrue(list.addAll(false, true, false));
        assertTrue(list.addAllAtIndex(4, true, true));
        assertEquals(BooleanArrayList.newListWith(true, false, true, false, true, true, true, false), list);
    }

    @Override
    @Test
    public void addAllIterable()
    {
        super.addAllIterable();
        MutableBooleanList list = this.classUnderTest();
        assertFalse(list.addAllAtIndex(1, new BooleanArrayList()));
        assertTrue(list.addAll(BooleanArrayList.newListWith(false, true, false)));
        assertTrue(list.addAllAtIndex(4, BooleanArrayList.newListWith(true, true)));
        assertEquals(BooleanArrayList.newListWith(true, false, true, false, true, true, true, false), list);
    }

    @Test
    public void addAll_throws_index_negative()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.classUnderTest().addAllAtIndex(-1, false, true));
    }

    @Test
    public void addAll_throws_index_greater_than_size()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.classUnderTest().addAllAtIndex(5, false, true));
    }

    @Test
    public void addAll_throws_index_greater_than_size_empty_list()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.newWith().addAllAtIndex(1, false));
    }

    @Override
    @Test
    public void remove()
    {
        super.remove();
        assertFalse(this.newWith(true, true).remove(false));
        MutableBooleanList list = this.classUnderTest();
        assertTrue(list.remove(true));
        assertEquals(BooleanArrayList.newListWith(false, true), list);
    }

    @Test
    public void removeIf()
    {
        assertFalse(this.newWith(true, true).removeIf(b -> !b));

        MutableBooleanList list1 = this.classUnderTest();
        assertTrue(list1.removeIf(b -> b));
        assertEquals(BooleanArrayList.newListWith(false), list1);

        MutableBooleanList list2 = this.classUnderTest();
        assertTrue(list2.removeIf(b -> !b));

        assertEquals(BooleanArrayList.newListWith(true, true), list2);
    }

    @Test
    public void removeAtIndex()
    {
        MutableBooleanList list = this.classUnderTest();
        list.removeAtIndex(1);
        assertEquals(BooleanArrayList.newListWith(true, true), list);
        list.removeAtIndex(1);
        assertEquals(BooleanArrayList.newListWith(true), list);
        list.removeAtIndex(0);
        assertEquals(BooleanArrayList.newListWith(), list);
    }

    @Test
    public void removeAtIndex_throws_index_greater_than_size()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.newWith().removeAtIndex(1));
    }

    @Test
    public void removeAtIndex_throws_index_negative()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.classUnderTest().removeAtIndex(-1));
    }

    @Test
    public void set()
    {
        MutableBooleanList list = this.classUnderTest();
        list.set(1, false);
        assertEquals(BooleanArrayList.newListWith(true, false, true), list);
        list.set(1, true);
        assertEquals(BooleanArrayList.newListWith(true, true, true), list);
    }

    @Test
    public void set_throws_index_greater_than_size()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> this.newWith().set(1, false));
    }

    @Override
    @Test
    public void booleanIterator()
    {
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next());
        assertTrue(iterator.hasNext());
        assertFalse(iterator.next());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Override
    @Test
    public void forEach()
    {
        super.forEach();
        String[] sum = new String[2];
        sum[0] = "";
        sum[1] = "";
        this.classUnderTest().forEach(each -> sum[0] += each + " ");
        this.newWith().forEach(each -> sum[1] += each);
        assertEquals("true false true ", sum[0]);
        assertEquals("", sum[1]);
    }

    @Override
    @Test
    public void size()
    {
        super.size();
        Verify.assertSize(3, this.classUnderTest());
    }

    @Override
    @Test
    public void toArray()
    {
        super.toArray();
        MutableBooleanList list = this.classUnderTest();
        assertEquals(3L, (long) list.toArray().length);
        assertTrue(list.toArray()[0]);
        assertFalse(list.toArray()[1]);
        assertTrue(list.toArray()[2]);
    }

    @Test
    public void reverseThis()
    {
        assertEquals(BooleanArrayList.newListWith(true, true, false, false), this.newWith(false, false, true, true).reverseThis());
        MutableBooleanList originalList = this.newWith(true, true, false, false);
        assertSame(originalList, originalList.reverseThis());
        MutableBooleanList originalList2 = this.newWith(true, false, false);
        originalList2.removeAtIndex(2);
        assertEquals(originalList2, BooleanArrayList.newListWith(true, false));
        assertEquals(originalList2.reverseThis(), BooleanArrayList.newListWith(false, true));
    }

    @Test
    public void toReversed()
    {
        assertEquals(BooleanArrayList.newListWith(true, true, false, false), this.newWith(false, false, true, true).toReversed());
        MutableBooleanList originalList = this.newWith(true, true, false, false);
        assertNotSame(originalList, originalList.toReversed());
    }

    @Test
    public void distinct()
    {
        assertEquals(BooleanArrayList.newListWith(true, false), this.newWith(true, true, false, false).distinct());
        assertEquals(BooleanArrayList.newListWith(false, true), this.newWith(false, false, true, true).distinct());
        assertEquals(BooleanArrayList.newListWith(false), this.newWith(false).distinct());
        assertEquals(BooleanArrayList.newListWith(true), this.newWith(true).distinct());
    }

    @Test
    public void injectIntoWithIndex()
    {
        MutableBooleanList list = this.newWith(true, false, true);
        MutableInteger result = list.injectIntoWithIndex(new MutableInteger(0), (object, value, index) -> object.add((value ? 1 : 0) + index));
        assertEquals(new MutableInteger(5), result);
    }

    @Test
    public void forEachWithIndex()
    {
        String[] sum = new String[2];
        sum[0] = "";
        sum[1] = "";
        this.classUnderTest().forEachWithIndex((each, index) -> sum[0] += index + ":" + each);
        this.newWith().forEachWithIndex((each, index) -> sum[1] += index + ":" + each);
        assertEquals("0:true1:false2:true", sum[0]);
        assertEquals("", sum[1]);
    }

    @Override
    @Test
    public void testEquals()
    {
        super.testEquals();
        MutableBooleanList list1 = this.newWith(true, false, true, true);
        MutableBooleanList list2 = this.newWith(true, true, false, true);
        assertNotEquals(list1, list2);
    }

    @Override
    @Test
    public void testToString()
    {
        super.testToString();
        assertEquals("[true, false, true]", this.classUnderTest().toString());
        assertEquals("[]", this.newWith().toString());
    }

    @Override
    @Test
    public void makeString()
    {
        super.makeString();
        assertEquals("true, false, true", this.classUnderTest().makeString());
        assertEquals("true", this.newWith(true).makeString("/"));
        assertEquals("true/false/true", this.classUnderTest().makeString("/"));
        assertEquals(this.classUnderTest().toString(), this.classUnderTest().makeString("[", ", ", "]"));
        assertEquals("", this.newWith().makeString());
    }

    @Test
    public void newWithNValues()
    {
        assertEquals(this.newWith(true, true, true), BooleanArrayList.newWithNValues(3, true));
        assertEquals(this.newWith(false, false), BooleanArrayList.newWithNValues(2, false));
        assertEquals(this.newWith(), BooleanArrayList.newWithNValues(0, false));
        assertEquals(this.newWith(), BooleanArrayList.newWithNValues(0, true));
    }

    @Test
    public void newWithNValues_throws_negative_size()
    {
        assertThrows(NegativeArraySizeException.class, () -> BooleanArrayList.newWithNValues(-1, true));
    }

    @Override
    @Test
    public void appendString()
    {
        super.appendString();
        StringBuilder appendable = new StringBuilder();
        this.newWith().appendString(appendable);
        assertEquals("", appendable.toString());
        StringBuilder appendable2 = new StringBuilder();
        this.classUnderTest().appendString(appendable2);
        assertEquals("true, false, true", appendable2.toString());
        StringBuilder appendable3 = new StringBuilder();
        this.classUnderTest().appendString(appendable3, "/");
        assertEquals("true/false/true", appendable3.toString());
        StringBuilder appendable4 = new StringBuilder();
        this.classUnderTest().appendString(appendable4, "[", ", ", "]");
        assertEquals(this.classUnderTest().toString(), appendable4.toString());
    }

    @Override
    @Test
    public void toList()
    {
        super.toList();
        assertEquals(BooleanArrayList.newListWith(true, false, true), this.classUnderTest().toList());
    }

    @Test
    public void toImmutable()
    {
        ImmutableBooleanList immutable = this.classUnderTest().toImmutable();
        assertEquals(BooleanArrayList.newListWith(true, false, true), immutable);
    }

    @Test
    public void tap()
    {
        MutableBooleanList list = BooleanLists.mutable.empty();
        this.classUnderTest().tap(list::add);
        assertEquals(this.classUnderTest(), list);
    }

    @Test
    public void collectWithIndex()
    {
        MutableList<BooleanIntPair> pairs =
                this.classUnderTest().collectWithIndex(PrimitiveTuples::pair);
        MutableBooleanList list1 = pairs.collectBoolean(BooleanIntPair::getOne);
        assertEquals(this.classUnderTest(), list1);
        MutableIntList list2 = pairs.collectInt(BooleanIntPair::getTwo);
        assertEquals(IntInterval.zeroTo(this.classUnderTest().size() - 1), list2);
    }
}
