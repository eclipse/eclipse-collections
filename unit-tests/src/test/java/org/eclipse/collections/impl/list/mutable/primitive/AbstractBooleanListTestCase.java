/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable.primitive;

import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.ImmutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.impl.collection.mutable.primitive.AbstractMutableBooleanCollectionTestCase;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.math.MutableInteger;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertTrue(list.get(0));
        Assert.assertFalse(list.get(1));
        Assert.assertTrue(list.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_throws_index_greater_than_size()
    {
        this.classUnderTest().get(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_throws_empty_list()
    {
        this.newWith().get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_throws_index_negative()
    {
        this.classUnderTest().get(-1);
    }

    @Test
    public void getFirst()
    {
        MutableBooleanList singleItemList = this.newWith(true);
        Assert.assertTrue(singleItemList.getFirst());
        Assert.assertTrue(this.classUnderTest().getFirst());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getFirst_emptyList_throws()
    {
        this.newWith().getFirst();
    }

    @Test
    public void getLast()
    {
        MutableBooleanList singleItemList = this.newWith(true);
        Assert.assertTrue(singleItemList.getLast());
        Assert.assertTrue(this.classUnderTest().getLast());
        Assert.assertFalse(this.newWith(true, true, false).getLast());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getLast_emptyList_throws()
    {
        this.newWith().getLast();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void subList()
    {
        this.classUnderTest().subList(0, 1);
    }

    @Test
    public void indexOf()
    {
        MutableBooleanList arrayList = this.newWith(true, false, true);
        Assert.assertEquals(0L, arrayList.indexOf(true));
        Assert.assertEquals(1L, arrayList.indexOf(false));
        Assert.assertEquals(-1L, this.newWith(false, false).indexOf(true));
        MutableBooleanList emptyList = this.newWith();
        Assert.assertEquals(-1L, emptyList.indexOf(true));
        Assert.assertEquals(-1L, emptyList.indexOf(false));
    }

    @Test
    public void lastIndexOf()
    {
        MutableBooleanList list = this.newWith(true, false, true);
        Assert.assertEquals(2L, list.lastIndexOf(true));
        Assert.assertEquals(1L, list.lastIndexOf(false));
        Assert.assertEquals(-1L, this.newWith(false, false).lastIndexOf(true));
        MutableBooleanList emptyList = this.newWith();
        Assert.assertEquals(-1L, emptyList.lastIndexOf(true));
        Assert.assertEquals(-1L, emptyList.lastIndexOf(false));
    }

    @Test
    public void addAtIndex()
    {
        MutableBooleanList emptyList = this.newWith();
        emptyList.addAtIndex(0, false);
        Assert.assertEquals(BooleanArrayList.newListWith(false), emptyList);
        MutableBooleanList list = this.classUnderTest();
        list.addAtIndex(3, true);
        Assert.assertEquals(BooleanArrayList.newListWith(true, false, true, true), list);
        list.addAtIndex(2, false);
        Assert.assertEquals(BooleanArrayList.newListWith(true, false, false, true, true), list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtIndex_throws_index_greater_than_size()
    {
        this.newWith().addAtIndex(1, false);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtIndex_throws_index_negative()
    {
        this.classUnderTest().addAtIndex(-1, true);
    }

    @Override
    @Test
    public void addAllArray()
    {
        super.addAllArray();
        MutableBooleanList list = this.classUnderTest();
        Assert.assertFalse(list.addAllAtIndex(1));
        Assert.assertTrue(list.addAll(false, true, false));
        Assert.assertTrue(list.addAllAtIndex(4, true, true));
        Assert.assertEquals(BooleanArrayList.newListWith(true, false, true, false, true, true, true, false), list);
    }

    @Override
    @Test
    public void addAllIterable()
    {
        super.addAllIterable();
        MutableBooleanList list = this.classUnderTest();
        Assert.assertFalse(list.addAllAtIndex(1, new BooleanArrayList()));
        Assert.assertTrue(list.addAll(BooleanArrayList.newListWith(false, true, false)));
        Assert.assertTrue(list.addAllAtIndex(4, BooleanArrayList.newListWith(true, true)));
        Assert.assertEquals(BooleanArrayList.newListWith(true, false, true, false, true, true, true, false), list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAll_throws_index_negative()
    {
        this.classUnderTest().addAllAtIndex(-1, false, true);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAll_throws_index_greater_than_size()
    {
        this.classUnderTest().addAllAtIndex(5, false, true);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAll_throws_index_greater_than_size_empty_list()
    {
        this.newWith().addAllAtIndex(1, false);
    }

    @Override
    @Test
    public void remove()
    {
        super.remove();
        Assert.assertFalse(this.newWith(true, true).remove(false));
        MutableBooleanList list = this.classUnderTest();
        Assert.assertTrue(list.remove(true));
        Assert.assertEquals(BooleanArrayList.newListWith(false, true), list);
    }

    @Test
    public void removeIf()
    {
        Assert.assertFalse(this.newWith(true, true).removeIf(b -> !b));
        MutableBooleanList list = this.classUnderTest();
        Assert.assertTrue(list.removeIf(b -> b));
        Assert.assertEquals(BooleanArrayList.newListWith(false), list);
    }

    @Test
    public void removeAtIndex()
    {
        MutableBooleanList list = this.classUnderTest();
        list.removeAtIndex(1);
        Assert.assertEquals(BooleanArrayList.newListWith(true, true), list);
        list.removeAtIndex(1);
        Assert.assertEquals(BooleanArrayList.newListWith(true), list);
        list.removeAtIndex(0);
        Assert.assertEquals(BooleanArrayList.newListWith(), list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAtIndex_throws_index_greater_than_size()
    {
        this.newWith().removeAtIndex(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAtIndex_throws_index_negative()
    {
        this.classUnderTest().removeAtIndex(-1);
    }

    @Test
    public void set()
    {
        MutableBooleanList list = this.classUnderTest();
        list.set(1, false);
        Assert.assertEquals(BooleanArrayList.newListWith(true, false, true), list);
        list.set(1, true);
        Assert.assertEquals(BooleanArrayList.newListWith(true, true, true), list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set_throws_index_greater_than_size()
    {
        this.newWith().set(1, false);
    }

    @Override
    @Test
    public void booleanIterator()
    {
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertFalse(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.next());
        Assert.assertFalse(iterator.hasNext());
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
        Assert.assertEquals("true false true ", sum[0]);
        Assert.assertEquals("", sum[1]);
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
        Assert.assertEquals(3L, (long) list.toArray().length);
        Assert.assertTrue(list.toArray()[0]);
        Assert.assertFalse(list.toArray()[1]);
        Assert.assertTrue(list.toArray()[2]);
    }

    @Test
    public void reverseThis()
    {
        Assert.assertEquals(BooleanArrayList.newListWith(true, true, false, false), this.newWith(false, false, true, true).reverseThis());
        MutableBooleanList originalList = this.newWith(true, true, false, false);
        Assert.assertSame(originalList, originalList.reverseThis());
        MutableBooleanList originalList2 = this.newWith(true, false, false);
        originalList2.removeAtIndex(2);
        Assert.assertEquals(originalList2, BooleanArrayList.newListWith(true, false));
        Assert.assertEquals(originalList2.reverseThis(), BooleanArrayList.newListWith(false, true));
    }

    @Test
    public void toReversed()
    {
        Assert.assertEquals(BooleanArrayList.newListWith(true, true, false, false), this.newWith(false, false, true, true).toReversed());
        MutableBooleanList originalList = this.newWith(true, true, false, false);
        Assert.assertNotSame(originalList, originalList.toReversed());
    }

    @Test
    public void distinct()
    {
        Assert.assertEquals(BooleanArrayList.newListWith(true, false), this.newWith(true, true, false, false).distinct());
        Assert.assertEquals(BooleanArrayList.newListWith(false, true), this.newWith(false, false, true, true).distinct());
        Assert.assertEquals(BooleanArrayList.newListWith(false), this.newWith(false).distinct());
        Assert.assertEquals(BooleanArrayList.newListWith(true), this.newWith(true).distinct());
    }

    @Test
    public void injectIntoWithIndex()
    {
        MutableBooleanList list = this.newWith(true, false, true);
        MutableInteger result = list.injectIntoWithIndex(new MutableInteger(0), (object, value, index) -> object.add((value ? 1 : 0) + index));
        Assert.assertEquals(new MutableInteger(5), result);
    }

    @Test
    public void forEachWithIndex()
    {
        String[] sum = new String[2];
        sum[0] = "";
        sum[1] = "";
        this.classUnderTest().forEachWithIndex((each, index) -> sum[0] += index + ":" + each);
        this.newWith().forEachWithIndex((each, index) -> sum[1] += index + ":" + each);
        Assert.assertEquals("0:true1:false2:true", sum[0]);
        Assert.assertEquals("", sum[1]);
    }

    @Override
    @Test
    public void testEquals()
    {
        super.testEquals();
        MutableBooleanList list1 = this.newWith(true, false, true, true);
        MutableBooleanList list2 = this.newWith(true, true, false, true);
        Assert.assertNotEquals(list1, list2);
    }

    @Override
    @Test
    public void testToString()
    {
        super.testToString();
        Assert.assertEquals("[true, false, true]", this.classUnderTest().toString());
        Assert.assertEquals("[]", this.newWith().toString());
    }

    @Override
    @Test
    public void makeString()
    {
        super.makeString();
        Assert.assertEquals("true, false, true", this.classUnderTest().makeString());
        Assert.assertEquals("true", this.newWith(true).makeString("/"));
        Assert.assertEquals("true/false/true", this.classUnderTest().makeString("/"));
        Assert.assertEquals(this.classUnderTest().toString(), this.classUnderTest().makeString("[", ", ", "]"));
        Assert.assertEquals("", this.newWith().makeString());
    }

    @Test
    public void newWithNValues()
    {
        Assert.assertEquals(this.newWith(true, true, true), BooleanArrayList.newWithNValues(3, true));
        Assert.assertEquals(this.newWith(false, false), BooleanArrayList.newWithNValues(2, false));
        Assert.assertEquals(this.newWith(), BooleanArrayList.newWithNValues(0, false));
        Assert.assertEquals(this.newWith(), BooleanArrayList.newWithNValues(0, true));
    }

    @Test(expected = NegativeArraySizeException.class)
    public void newWithNValues_throws_negative_size()
    {
        BooleanArrayList.newWithNValues(-1, true);
    }

    @Override
    @Test
    public void appendString()
    {
        super.appendString();
        StringBuilder appendable = new StringBuilder();
        this.newWith().appendString(appendable);
        Assert.assertEquals("", appendable.toString());
        StringBuilder appendable2 = new StringBuilder();
        this.classUnderTest().appendString(appendable2);
        Assert.assertEquals("true, false, true", appendable2.toString());
        StringBuilder appendable3 = new StringBuilder();
        this.classUnderTest().appendString(appendable3, "/");
        Assert.assertEquals("true/false/true", appendable3.toString());
        StringBuilder appendable4 = new StringBuilder();
        this.classUnderTest().appendString(appendable4, "[", ", ", "]");
        Assert.assertEquals(this.classUnderTest().toString(), appendable4.toString());
    }

    @Override
    @Test
    public void toList()
    {
        super.toList();
        Assert.assertEquals(BooleanArrayList.newListWith(true, false, true), this.classUnderTest().toList());
    }

    @Test
    public void toImmutable()
    {
        ImmutableBooleanList immutable = this.classUnderTest().toImmutable();
        Assert.assertEquals(BooleanArrayList.newListWith(true, false, true), immutable);
    }
}
