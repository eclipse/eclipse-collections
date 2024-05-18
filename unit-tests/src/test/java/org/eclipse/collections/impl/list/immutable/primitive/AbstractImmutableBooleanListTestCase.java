/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable.primitive;

import org.eclipse.collections.api.collection.primitive.ImmutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.ImmutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.impl.collection.immutable.primitive.AbstractImmutableBooleanCollectionTestCase;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.math.MutableInteger;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Abstract JUnit test for {@link ImmutableBooleanList}.
 */
public abstract class AbstractImmutableBooleanListTestCase extends AbstractImmutableBooleanCollectionTestCase
{
    @Override
    protected abstract ImmutableBooleanList classUnderTest();

    @Override
    protected ImmutableBooleanList newWith(boolean... elements)
    {
        return BooleanLists.immutable.of(elements);
    }

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
    public void newWithOn64ElementCollection()
    {
        MutableBooleanList sixtyFourElementCollection = new BooleanArrayList();
        for (int i = 0; i < 64; i++)
        {
            sixtyFourElementCollection.add(true);
        }
        ImmutableBooleanList immutableBooleanList = sixtyFourElementCollection.toImmutable();
        assertEquals(sixtyFourElementCollection, immutableBooleanList);
        ImmutableBooleanList newImmutableBooleanList = immutableBooleanList.newWith(false);
        assertFalse(newImmutableBooleanList.get(64));
        ImmutableBooleanList newImmutableBooleanList1 = immutableBooleanList.newWith(true);
        assertTrue(newImmutableBooleanList1.get(64));
    }

    @Test
    public void get()
    {
        ImmutableBooleanList list = this.classUnderTest();
        for (int i = 0; i < list.size(); i++)
        {
            assertEquals((i & 1) == 0, list.get(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_throws_index_greater_than_size()
    {
        ImmutableBooleanList list = this.classUnderTest();
        list.get(list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_throws_index_negative()
    {
        this.classUnderTest().get(-1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void subList()
    {
        this.classUnderTest().subList(0, 1);
    }

    @Test
    public void getFirst()
    {
        assertTrue(this.classUnderTest().getFirst());
    }

    @Test
    public void getLast()
    {
        assertEquals((this.classUnderTest().size() & 1) != 0, this.classUnderTest().getLast());
    }

    @Test
    public void indexOf()
    {
        ImmutableBooleanList list = this.classUnderTest();
        assertEquals(0L, list.indexOf(true));
        assertEquals(list.size() > 2 ? 1L : -1L, list.indexOf(false));

        MutableBooleanList mutableList = this.newMutableCollectionWith();
        for (int i = 0; i < list.size(); i++)
        {
            mutableList.add(false);
        }
        assertEquals(-1L, mutableList.toImmutable().indexOf(true));
    }

    @Test
    public void lastIndexOf()
    {
        ImmutableBooleanList list = this.classUnderTest();
        int size = list.size();
        assertEquals((size & 1) == 0 ? size - 2 : size - 1, list.lastIndexOf(true));
        assertEquals((size & 1) == 0 ? size - 1 : size - 2, list.lastIndexOf(false));

        MutableBooleanList mutableList = this.newMutableCollectionWith();
        for (int i = 0; i < list.size(); i++)
        {
            mutableList.add(false);
        }
        assertEquals(-1L, mutableList.toImmutable().lastIndexOf(true));
    }

    @Override
    @Test
    public void booleanIterator()
    {
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        for (int i = 0; iterator.hasNext(); i++)
        {
            assertEquals(i % 2 == 0, iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Override
    @Test
    public void forEach()
    {
        super.forEach();
        String[] sum = new String[1];
        sum[0] = "";
        this.classUnderTest().forEach(each -> sum[0] += each);

        StringBuilder expectedString = new StringBuilder();
        for (int i = 0; i < this.classUnderTest().size(); i++)
        {
            expectedString.append((i & 1) == 0);
        }
        assertEquals(expectedString.toString(), sum[0]);
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

    @Test
    public void toReversed()
    {
        assertEquals(BooleanArrayList.newListWith(true, true, false, false), this.newWith(false, false, true, true).toReversed());
        ImmutableBooleanList originalList = this.newWith(true, true, false, false);
        assertNotSame(originalList, originalList.toReversed());
    }

    @Override
    @Test
    public void toArray()
    {
        super.toArray();
        ImmutableBooleanList list = this.classUnderTest();
        assertEquals(this.classUnderTest().size(), list.toArray().length);
        for (int i = 0; i < this.classUnderTest().size(); i++)
        {
            assertEquals((i & 1) == 0, list.toArray()[i]);
        }
    }

    @Test
    public void injectIntoWithIndex()
    {
        ImmutableBooleanList list = this.newWith(true, false, true);
        MutableInteger result = list.injectIntoWithIndex(new MutableInteger(0), (object, value, index) -> object.add((value ? 1 : 0) + index));
        assertEquals(new MutableInteger(5), result);
    }

    @Override
    @Test
    public void testEquals()
    {
        super.testEquals();
        ImmutableBooleanList list1 = this.newWith(true, false, true, true);
        ImmutableBooleanList list2 = this.newWith(true, true, false, true);
        assertNotEquals(list1, list2);
    }

    @Override
    @Test
    public void testToString()
    {
        super.testToString();
        StringBuilder expectedString = new StringBuilder("[");
        int size = this.classUnderTest().size();
        for (int i = 0; i < size; i++)
        {
            expectedString.append((i & 1) == 0);
            expectedString.append(i == size - 1 ? "" : ", ");
        }
        expectedString.append(']');
        assertEquals(expectedString.toString(), this.classUnderTest().toString());
    }

    @Override
    @Test
    public void makeString()
    {
        super.makeString();
        StringBuilder expectedString = new StringBuilder();
        StringBuilder expectedString1 = new StringBuilder();
        int size = this.classUnderTest().size();
        for (int i = 0; i < size; i++)
        {
            boolean isEven = (i & 1) == 0;
            expectedString.append(isEven);
            expectedString1.append(isEven);
            expectedString.append(i == size - 1 ? "" : ", ");
            expectedString1.append(i == size - 1 ? "" : "/");
        }
        assertEquals(expectedString.toString(), this.classUnderTest().makeString());
        assertEquals(expectedString1.toString(), this.classUnderTest().makeString("/"));
        assertEquals(this.classUnderTest().toString(), this.classUnderTest().makeString("[", ", ", "]"));
    }

    @Override
    @Test
    public void appendString()
    {
        super.appendString();
        StringBuilder expectedString = new StringBuilder();
        StringBuilder expectedString1 = new StringBuilder();
        int size = this.classUnderTest().size();
        for (int i = 0; i < size; i++)
        {
            boolean isEven = (i & 1) == 0;
            expectedString.append(isEven);
            expectedString1.append(isEven);
            expectedString.append(i == size - 1 ? "" : ", ");
            expectedString1.append(i == size - 1 ? "" : "/");
        }
        StringBuilder appendable2 = new StringBuilder();
        this.classUnderTest().appendString(appendable2);
        assertEquals(expectedString.toString(), appendable2.toString());
        StringBuilder appendable3 = new StringBuilder();
        this.classUnderTest().appendString(appendable3, "/");
        assertEquals(expectedString1.toString(), appendable3.toString());
        StringBuilder appendable4 = new StringBuilder();
        this.classUnderTest().appendString(appendable4, "[", ", ", "]");
        assertEquals(this.classUnderTest().toString(), appendable4.toString());
    }

    @Override
    @Test
    public void toList()
    {
        super.toList();
        MutableBooleanList list = this.classUnderTest().toList();
        Verify.assertEqualsAndHashCode(this.classUnderTest(), list);
        assertNotSame(this.classUnderTest(), list);
    }

    @Override
    @Test
    public void testNewWith()
    {
        ImmutableBooleanCollection booleanCollection = this.classUnderTest();
        MutableBooleanList list = booleanCollection.toList();
        ImmutableBooleanCollection collection = booleanCollection.newWith(true);
        ImmutableBooleanCollection collection0 = booleanCollection.newWith(true).newWith(false);
        ImmutableBooleanCollection collection1 = booleanCollection.newWith(true).newWith(false).newWith(true);
        ImmutableBooleanCollection collection2 = booleanCollection.newWith(true).newWith(false).newWith(true).newWith(false);
        ImmutableBooleanCollection collection3 = booleanCollection.newWith(true).newWith(false).newWith(true).newWith(false).newWith(true);
        ImmutableBooleanCollection collection4 = collection3.newWith(true).newWith(false).newWith(true).newWith(false).newWith(true);
        assertEquals(list, booleanCollection);
        assertEquals(list.with(true), collection);
        assertEquals(list.with(false), collection0);
        assertEquals(list.with(true), collection1);
        assertEquals(list.with(false), collection2);
        assertEquals(list.with(true), collection3);
        list.addAll(true, false, true, false, true);
        assertEquals(list, collection4);
    }

    @Override
    @Test
    public void newWithAll()
    {
        ImmutableBooleanCollection booleanCollection = this.classUnderTest();
        MutableBooleanList list = booleanCollection.toList();
        ImmutableBooleanCollection collection = booleanCollection.newWithAll(this.newMutableCollectionWith(true));
        ImmutableBooleanCollection collection0 = booleanCollection.newWithAll(this.newMutableCollectionWith(true, false));
        ImmutableBooleanCollection collection1 = booleanCollection.newWithAll(this.newMutableCollectionWith(true, false, true));
        ImmutableBooleanCollection collection2 = booleanCollection.newWithAll(this.newMutableCollectionWith(true, false, true, false));
        ImmutableBooleanCollection collection3 = booleanCollection.newWithAll(this.newMutableCollectionWith(true, false, true, false, true));
        ImmutableBooleanCollection collection4 = collection3.newWithAll(this.newMutableCollectionWith(true, false, true, false, true));
        assertEquals(list, booleanCollection);
        assertEquals(list.with(true), collection);
        assertEquals(list.with(false), collection0);
        assertEquals(list.with(true), collection1);
        assertEquals(list.with(false), collection2);
        assertEquals(list.with(true), collection3);
        list.addAll(true, false, true, false, true);
        assertEquals(list, collection4);
    }

    @Override
    @Test
    public void newWithout()
    {
        ImmutableBooleanCollection trueCollection = this.getTrueCollection(this.classUnderTest()).toImmutable();
        assertSame(trueCollection, trueCollection.newWithout(false));
        assertNotSame(trueCollection, trueCollection.newWithout(true));

        ImmutableBooleanCollection collection = this.classUnderTest();
        MutableBooleanList list = collection.toList();
        assertEquals(list.without(true), collection.newWithout(true));
        MutableBooleanList list1 = collection.toList();
        assertEquals(list1.without(false), collection.newWithout(false));
        assertEquals(this.classUnderTest(), collection);
    }

    @Override
    @Test
    public void newWithoutAll()
    {
        ImmutableBooleanCollection immutableBooleanCollection = this.classUnderTest();
        MutableBooleanCollection mutableTrueCollection = this.getTrueCollection(immutableBooleanCollection);
        ImmutableBooleanCollection trueCollection = mutableTrueCollection.toImmutable();
        assertEquals(this.newMutableCollectionWith(), trueCollection.newWithoutAll(this.newMutableCollectionWith(true, false)));
        assertEquals(mutableTrueCollection, trueCollection);

        MutableBooleanList list = immutableBooleanCollection.toList();
        list.removeAll(true);
        assertEquals(list, immutableBooleanCollection.newWithoutAll(this.newMutableCollectionWith(true)));
        assertEquals(this.newMutableCollectionWith(), immutableBooleanCollection.newWithoutAll(this.newMutableCollectionWith(true, false)));

        ImmutableBooleanCollection collection = this.newWith(true, false, true, false, true);
        assertEquals(this.newMutableCollectionWith(false, false), collection.newWithoutAll(this.newMutableCollectionWith(true, true)));
        assertEquals(this.newMutableCollectionWith(), collection.newWithoutAll(this.newMutableCollectionWith(true, false)));
    }

    private MutableBooleanCollection getTrueCollection(ImmutableBooleanCollection collection)
    {
        MutableBooleanCollection trueCollection = this.newMutableCollectionWith();
        for (int i = 0; i < collection.size(); i++)
        {
            trueCollection.add(true);
        }
        return trueCollection;
    }
}
