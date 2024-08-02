/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable.primitive;

import java.util.NoSuchElementException;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Abstract JUnit test for {@link MutableBooleanCollection}s.
 */
public abstract class AbstractMutableBooleanCollectionTestCase extends AbstractBooleanIterableTestCase
{
    @Override
    protected abstract MutableBooleanCollection classUnderTest();

    @Override
    protected abstract MutableBooleanCollection newWith(boolean... elements);

    @Override
    protected abstract MutableBooleanCollection newMutableCollectionWith(boolean... elements);

    @Test
    public void clear()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        collection.clear();
        Verify.assertSize(0, collection);
        Verify.assertEmpty(collection);
        assertFalse(collection.contains(true));
        assertFalse(collection.contains(false));

        MutableBooleanCollection collection0 = this.newWith();
        MutableBooleanCollection collection1 = this.newWith(false);
        MutableBooleanCollection collection2 = this.newWith(true);
        MutableBooleanCollection collection3 = this.newWith(true, false);
        MutableBooleanCollection collection4 = this.newWith(true, false, true, false, true);
        collection0.clear();
        collection1.clear();
        collection2.clear();
        collection3.clear();
        collection4.clear();
        Verify.assertEmpty(collection0);
        Verify.assertEmpty(collection1);
        Verify.assertEmpty(collection2);
        Verify.assertEmpty(collection3);
        Verify.assertEmpty(collection4);
        Verify.assertSize(0, collection0);
        Verify.assertSize(0, collection1);
        Verify.assertSize(0, collection2);
        Verify.assertSize(0, collection3);
        Verify.assertSize(0, collection4);
        assertFalse(collection1.contains(false));
        assertFalse(collection2.contains(true));
        assertFalse(collection3.contains(true));
        assertFalse(collection3.contains(false));
        assertFalse(collection4.contains(false));
        assertEquals(this.newMutableCollectionWith(), collection0);
        assertEquals(this.newMutableCollectionWith(), collection1);
        assertEquals(this.newMutableCollectionWith(), collection2);
        assertEquals(this.newMutableCollectionWith(), collection3);
        assertEquals(this.newMutableCollectionWith(), collection4);
    }

    @Override
    @Test
    public void testEquals()
    {
        super.testEquals();
        Verify.assertPostSerializedEqualsAndHashCode(this.newWith());
    }

    @Override
    @Test
    public void containsAllArray()
    {
        super.containsAllArray();
        MutableBooleanCollection emptyCollection = this.newWith();
        assertFalse(emptyCollection.containsAll(true));
        assertFalse(emptyCollection.containsAll(false));
        assertFalse(emptyCollection.containsAll(false, true, false));
        emptyCollection.add(false);
        assertFalse(emptyCollection.containsAll(true));
        assertTrue(emptyCollection.containsAll(false));
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        super.containsAllIterable();
        MutableBooleanCollection emptyCollection = this.newWith();
        assertTrue(emptyCollection.containsAll(new BooleanArrayList()));
        assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(true)));
        assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(false)));
        emptyCollection.add(false);
        assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(true)));
        assertTrue(emptyCollection.containsAll(BooleanArrayList.newListWith(false)));
    }

    @Test
    public void add()
    {
        MutableBooleanCollection emptyCollection = this.newWith();
        assertTrue(emptyCollection.add(true));
        assertEquals(this.newMutableCollectionWith(true), emptyCollection);
        assertTrue(emptyCollection.add(false));
        assertEquals(this.newMutableCollectionWith(true, false), emptyCollection);
        assertTrue(emptyCollection.add(true));
        assertEquals(this.newMutableCollectionWith(true, false, true), emptyCollection);
        assertTrue(emptyCollection.add(false));
        assertEquals(this.newMutableCollectionWith(true, false, true, false), emptyCollection);
        MutableBooleanCollection collection = this.classUnderTest();
        assertTrue(collection.add(false));
        assertEquals(this.newMutableCollectionWith(true, false, true, false), collection);
    }

    @Test
    public void addAllArray()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        assertFalse(collection.addAll());
        assertTrue(collection.addAll(false, true, false));
        assertEquals(this.newMutableCollectionWith(true, false, true, false, true, false), collection);
        assertTrue(collection.addAll(this.newMutableCollectionWith(true, false, true, false, true)));
        assertEquals(this.newMutableCollectionWith(true, false, true, false, true, false, true, false, true, false, true), collection);
    }

    @Test
    public void addAllIterable()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        assertFalse(collection.addAll(this.newMutableCollectionWith()));
        assertTrue(collection.addAll(this.newMutableCollectionWith(false, true, false)));
        assertEquals(this.newMutableCollectionWith(true, false, true, false, true, false), collection);
        assertTrue(collection.addAll(this.newMutableCollectionWith(true, false, true, false, true)));
        assertEquals(this.newMutableCollectionWith(true, false, true, false, true, false, true, false, true, false, true), collection);

        MutableBooleanCollection emptyCollection = this.newWith();
        assertTrue(emptyCollection.addAll(BooleanArrayList.newListWith(true, false, true, false, true)));
        assertFalse(emptyCollection.addAll(new BooleanArrayList()));
        assertEquals(this.newMutableCollectionWith(true, false, true, false, true), emptyCollection);
    }

    @Test
    public void remove()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        assertTrue(collection.remove(false));
        assertEquals(this.newMutableCollectionWith(true, true), collection);
        assertFalse(collection.remove(false));
        assertEquals(this.newMutableCollectionWith(true, true), collection);
        assertTrue(collection.remove(true));
        assertEquals(this.newMutableCollectionWith(true), collection);

        MutableBooleanCollection collection1 = this.newWith();
        assertFalse(collection1.remove(false));
        assertEquals(this.newMutableCollectionWith(), collection1);
        assertTrue(collection1.add(false));
        assertTrue(collection1.add(false));
        assertTrue(collection1.remove(false));
        assertEquals(this.newMutableCollectionWith(false), collection1);
        assertTrue(collection1.remove(false));
        assertEquals(this.newMutableCollectionWith(), collection1);

        MutableBooleanCollection collection2 = this.newWith();
        assertFalse(collection2.remove(true));
        assertEquals(this.newMutableCollectionWith(), collection2);
        assertTrue(collection2.add(true));
        assertTrue(collection2.add(true));
        assertTrue(collection2.remove(true));
        assertEquals(this.newMutableCollectionWith(true), collection2);
        assertTrue(collection2.remove(true));
        assertEquals(this.newMutableCollectionWith(), collection2);
    }

    @Test
    public void removeAll()
    {
        assertFalse(this.newWith().removeAll(true));

        MutableBooleanCollection collection = this.classUnderTest();
        assertFalse(collection.removeAll());
        assertTrue(collection.removeAll(true));
        assertEquals(this.newMutableCollectionWith(false), collection);
        assertFalse(collection.removeAll(true));
        assertEquals(this.newMutableCollectionWith(false), collection);
        assertTrue(collection.removeAll(false, true));
        assertEquals(this.newMutableCollectionWith(), collection);

        MutableBooleanCollection booleanArrayCollection = this.newWith(false, false);
        assertFalse(booleanArrayCollection.removeAll(true));
        assertEquals(this.newMutableCollectionWith(false, false), booleanArrayCollection);
        assertTrue(booleanArrayCollection.removeAll(false));
        assertEquals(this.newMutableCollectionWith(), booleanArrayCollection);
        MutableBooleanCollection collection1 = this.classUnderTest();
        assertFalse(collection1.removeAll());
        assertTrue(collection1.removeAll(true, false));
        assertEquals(this.newMutableCollectionWith(), collection1);

        MutableBooleanCollection trueFalseList = this.newWith(true, false);
        assertTrue(trueFalseList.removeAll(true));
        assertEquals(this.newMutableCollectionWith(false), trueFalseList);

        MutableBooleanCollection collection2 = this.newWith(true, false, true, false, true);
        assertFalse(collection2.removeAll());
        assertTrue(collection2.removeAll(true, true));
        assertEquals(this.newMutableCollectionWith(false, false), collection2);

        MutableBooleanCollection collection3 = this.newWith(true, false, true, false, true);
        assertFalse(collection3.removeAll());
        assertTrue(collection3.removeAll(true, false));
        assertEquals(this.newMutableCollectionWith(), collection3);

        MutableBooleanCollection collection4 = this.newWith(true, false, true, false, true);
        assertFalse(collection4.removeAll());
        assertTrue(collection4.removeAll(false, false));
        assertEquals(this.newMutableCollectionWith(true, true, true), collection4);
    }

    @Test
    public void removeAll_iterable()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        assertFalse(collection.removeAll(this.newMutableCollectionWith()));
        assertTrue(collection.removeAll(this.newMutableCollectionWith(false)));
        assertEquals(this.newMutableCollectionWith(true, true), collection);
        assertTrue(collection.removeAll(this.newMutableCollectionWith(true, true)));
        assertEquals(this.newMutableCollectionWith(), collection);

        MutableBooleanCollection list = this.classUnderTest();
        assertFalse(list.removeAll(new BooleanArrayList()));
        MutableBooleanCollection booleanArrayList = this.newWith(false, false);
        assertFalse(booleanArrayList.removeAll(new BooleanArrayList(true)));
        assertEquals(this.newMutableCollectionWith(false, false), booleanArrayList);
        assertTrue(booleanArrayList.removeAll(new BooleanArrayList(false)));
        assertEquals(this.newMutableCollectionWith(), booleanArrayList);
        assertTrue(list.removeAll(new BooleanArrayList(true)));
        assertEquals(this.newMutableCollectionWith(false), list);
        assertTrue(list.removeAll(BooleanArrayList.newListWith(true, false)));
        assertEquals(this.newMutableCollectionWith(), list);
        assertFalse(list.removeAll(BooleanArrayList.newListWith(true, false)));
        assertEquals(this.newMutableCollectionWith(), list);

        MutableBooleanCollection list1 = this.newWith(true, false, true, true);
        assertFalse(list1.removeAll(new BooleanArrayList()));
        assertTrue(list1.removeAll(BooleanArrayList.newListWith(true, true)));
        Verify.assertSize(1, list1);
        assertFalse(list1.contains(true));
        assertEquals(this.newMutableCollectionWith(false), list1);
        assertTrue(list1.removeAll(BooleanArrayList.newListWith(false, false)));
        assertEquals(this.newMutableCollectionWith(), list1);

        MutableBooleanCollection list2 = this.newWith(true, false, true, false, true);
        assertTrue(list2.removeAll(BooleanHashBag.newBagWith(true, false)));
        assertEquals(this.newMutableCollectionWith(), list2);
    }

    @Test
    public void retainAll_iterable()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        assertFalse(collection.retainAll(true, false));
        assertTrue(collection.retainAll(true));
        assertEquals(this.newMutableCollectionWith(true, true), collection);
        assertTrue(collection.retainAll(false, false));
        assertEquals(this.newMutableCollectionWith(), collection);

        MutableBooleanCollection list = this.classUnderTest();
        assertFalse(list.retainAll(false, false, true));
        MutableBooleanCollection booleanArrayList = this.newWith(false, false);
        assertFalse(booleanArrayList.retainAll(false));
        assertEquals(this.newMutableCollectionWith(false, false), booleanArrayList);
        assertTrue(booleanArrayList.retainAll(true));
        assertEquals(this.newMutableCollectionWith(), booleanArrayList);
        assertTrue(list.retainAll(false));
        assertEquals(this.newMutableCollectionWith(false), list);
        assertTrue(list.retainAll());
        assertEquals(this.newMutableCollectionWith(), list);
        assertFalse(list.retainAll(true, false));
        assertEquals(this.newMutableCollectionWith(), list);

        MutableBooleanCollection list1 = this.newWith(true, false, true, true);
        assertFalse(list1.retainAll(false, false, true));
        assertTrue(list1.retainAll(false, false));
        Verify.assertSize(1, list1);
        assertFalse(list1.contains(true));
        assertEquals(this.newMutableCollectionWith(false), list1);
        assertTrue(list1.retainAll(true, true));
        assertEquals(this.newMutableCollectionWith(), list1);

        MutableBooleanCollection list2 = this.newWith(true, false, true, false, true);
        assertTrue(list2.retainAll());
        assertEquals(this.newMutableCollectionWith(), list2);
    }

    @Test
    public void retainAll()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        assertFalse(collection.retainAll(this.newMutableCollectionWith(true, false)));
        assertTrue(collection.retainAll(this.newMutableCollectionWith(true)));
        assertEquals(this.newMutableCollectionWith(true, true), collection);
        assertTrue(collection.retainAll(this.newMutableCollectionWith(false, false)));
        assertEquals(this.newMutableCollectionWith(), collection);

        MutableBooleanCollection list = this.classUnderTest();
        assertFalse(list.retainAll(BooleanArrayList.newListWith(false, false, true)));
        MutableBooleanCollection booleanArrayList = this.newWith(false, false);
        assertFalse(booleanArrayList.retainAll(new BooleanArrayList(false)));
        assertEquals(this.newMutableCollectionWith(false, false), booleanArrayList);
        assertTrue(booleanArrayList.retainAll(new BooleanArrayList(true)));
        assertEquals(this.newMutableCollectionWith(), booleanArrayList);
        assertTrue(list.retainAll(new BooleanArrayList(false)));
        assertEquals(this.newMutableCollectionWith(false), list);
        assertTrue(list.retainAll(new BooleanArrayList()));
        assertEquals(this.newMutableCollectionWith(), list);
        assertFalse(list.retainAll(BooleanArrayList.newListWith(true, false)));
        assertEquals(this.newMutableCollectionWith(), list);

        MutableBooleanCollection list1 = this.newWith(true, false, true, true);
        assertFalse(list1.retainAll(BooleanArrayList.newListWith(false, false, true)));
        assertTrue(list1.retainAll(BooleanArrayList.newListWith(false, false)));
        Verify.assertSize(1, list1);
        assertFalse(list1.contains(true));
        assertEquals(this.newMutableCollectionWith(false), list1);
        assertTrue(list1.retainAll(BooleanArrayList.newListWith(true, true)));
        assertEquals(this.newMutableCollectionWith(), list1);

        MutableBooleanCollection list2 = this.newWith(true, false, true, false, true);
        assertTrue(list2.retainAll(new BooleanHashBag()));
        assertEquals(this.newMutableCollectionWith(), list2);
    }

    @Test
    public void with()
    {
        MutableBooleanCollection emptyCollection = this.newWith();
        MutableBooleanCollection collection = emptyCollection.with(true);
        MutableBooleanCollection collection0 = this.newWith().with(true).with(false);
        MutableBooleanCollection collection1 = this.newWith().with(true).with(false).with(true);
        MutableBooleanCollection collection2 = this.newWith().with(true).with(false).with(true).with(false);
        MutableBooleanCollection collection3 = this.newWith().with(true).with(false).with(true).with(false).with(true);
        assertSame(emptyCollection, collection);
        assertEquals(this.newMutableCollectionWith(true), collection);
        assertEquals(this.newMutableCollectionWith(true, false), collection0);
        assertEquals(this.newMutableCollectionWith(true, false, true), collection1);
        assertEquals(this.newMutableCollectionWith(true, false, true, false), collection2);
        assertEquals(this.newMutableCollectionWith(true, false, true, false, true), collection3);
    }

    @Test
    public void withAll()
    {
        MutableBooleanCollection emptyCollection = this.newWith();
        MutableBooleanCollection collection = emptyCollection.withAll(this.newMutableCollectionWith(true));
        MutableBooleanCollection collection0 = this.newWith().withAll(this.newMutableCollectionWith(true, false));
        MutableBooleanCollection collection1 = this.newWith().withAll(this.newMutableCollectionWith(true, false, true));
        MutableBooleanCollection collection2 = this.newWith().withAll(this.newMutableCollectionWith(true, false, true, false));
        MutableBooleanCollection collection3 = this.newWith().withAll(this.newMutableCollectionWith(true, false, true, false, true));
        assertSame(emptyCollection, collection);
        assertEquals(this.newMutableCollectionWith(true), collection);
        assertEquals(this.newMutableCollectionWith(true, false), collection0);
        assertEquals(this.classUnderTest(), collection1);
        assertEquals(this.newMutableCollectionWith(true, false, true, false), collection2);
        assertEquals(this.newMutableCollectionWith(true, false, true, false, true), collection3);
    }

    @Test
    public void without()
    {
        MutableBooleanCollection collection = this.newWith(true, false, true, false, true);
        assertEquals(this.newMutableCollectionWith(true, true, false, true), collection.without(false));
        assertEquals(this.newMutableCollectionWith(true, false, true), collection.without(true));
        assertEquals(this.newMutableCollectionWith(true, true), collection.without(false));
        assertEquals(this.newMutableCollectionWith(true), collection.without(true));
        assertEquals(this.newMutableCollectionWith(true), collection.without(false));
        assertEquals(this.newMutableCollectionWith(), collection.without(true));
        assertEquals(this.newMutableCollectionWith(), collection.without(false));

        MutableBooleanCollection collection1 = this.newWith(true, false, true, false, true);
        assertSame(collection1, collection1.without(false));
        assertEquals(this.newMutableCollectionWith(true, true, false, true), collection1);
    }

    @Test
    public void withoutAll()
    {
        MutableBooleanCollection mainCollection = this.newWith(true, false, true, false, true);
        assertEquals(this.newMutableCollectionWith(true, true, true), mainCollection.withoutAll(this.newMutableCollectionWith(false, false)));

        MutableBooleanCollection collection = this.newWith(true, false, true, false, true);
        assertEquals(this.newMutableCollectionWith(true, true, true), collection.withoutAll(BooleanHashBag.newBagWith(false)));
        assertEquals(this.newMutableCollectionWith(), collection.withoutAll(BooleanHashBag.newBagWith(true, false)));
        assertEquals(this.newMutableCollectionWith(), collection.withoutAll(BooleanHashBag.newBagWith(true, false)));

        MutableBooleanCollection trueCollection = this.newWith(true, true, true);
        assertEquals(this.newMutableCollectionWith(true, true, true), trueCollection.withoutAll(BooleanArrayList.newListWith(false)));
        MutableBooleanCollection mutableBooleanCollection = trueCollection.withoutAll(BooleanArrayList.newListWith(true));
        assertEquals(this.newMutableCollectionWith(), mutableBooleanCollection);
        assertSame(trueCollection, mutableBooleanCollection);
    }

    @Test
    public void asSynchronized()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        Verify.assertInstanceOf(this.newWith(true, false, true).asSynchronized().getClass(), collection.asSynchronized());
        assertEquals(this.newWith(true, false, true).asSynchronized(), collection.asSynchronized());
        assertEquals(collection, collection.asSynchronized());
    }

    @Test
    public void asUnmodifiable()
    {
        Verify.assertInstanceOf(this.newWith(true, false, true).asUnmodifiable().getClass(), this.classUnderTest().asUnmodifiable());
        assertEquals(this.newWith(true, false, true).asUnmodifiable(), this.classUnderTest().asUnmodifiable());
        assertEquals(this.classUnderTest(), this.classUnderTest().asUnmodifiable());
    }

    @Test
    public void booleanIterator_with_remove()
    {
        MutableBooleanCollection booleanIterable = this.classUnderTest();
        MutableBooleanIterator iterator = booleanIterable.booleanIterator();
        int iterationCount = booleanIterable.size();
        int iterableSize = booleanIterable.size();
        for (int i = 0; i < iterationCount; i++)
        {
            Verify.assertSize(iterableSize--, booleanIterable);
            assertTrue(iterator.hasNext());
            iterator.next();
            iterator.remove();
            Verify.assertSize(iterableSize, booleanIterable);
        }
        Verify.assertEmpty(booleanIterable);
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void iterator_throws_on_invocation_of_remove_before_next()
    {
        MutableBooleanCollection booleanIterable = this.newWith(true, false);
        MutableBooleanIterator iterator = booleanIterable.booleanIterator();
        assertTrue(iterator.hasNext());
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void iterator_throws_on_consecutive_invocation_of_remove()
    {
        MutableBooleanCollection booleanIterable = this.newWith(true, false);
        MutableBooleanIterator iterator = booleanIterable.booleanIterator();
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void chunk()
    {
        BooleanIterable iterable1 = this.newWith(true);
        Verify.assertIterablesEqual(
                Lists.mutable.with(this.newMutableCollectionWith(true)).toSet(),
                iterable1.chunk(1).toSet());

        BooleanIterable iterable2 = this.newWith(false);
        Verify.assertIterablesEqual(
                Lists.mutable.with(this.newMutableCollectionWith(false)).toSet(),
                iterable2.chunk(1).toSet());

        BooleanIterable iterable3 = this.newWith(false, true);
        Verify.assertIterablesEqual(
                Lists.mutable.with(this.newMutableCollectionWith(false), this.newMutableCollectionWith(true)).toSet(),
                iterable3.chunk(1).toSet());

        Verify.assertIterablesEqual(
                Lists.mutable.with(this.newMutableCollectionWith(false, true)),
                iterable3.chunk(2));
        Verify.assertIterablesEqual(
                Lists.mutable.with(this.newMutableCollectionWith(false, true)),
                iterable3.chunk(3));

        assertThrows(IllegalArgumentException.class, () -> this.classUnderTest().chunk(0));
        assertThrows(IllegalArgumentException.class, () -> this.classUnderTest().chunk(-1));
    }
}
