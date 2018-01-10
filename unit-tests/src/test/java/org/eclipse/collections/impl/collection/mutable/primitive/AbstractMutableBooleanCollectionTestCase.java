/*
 * Copyright (c) 2018 Goldman Sachs.
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
import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertFalse(collection.contains(true));
        Assert.assertFalse(collection.contains(false));

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
        Assert.assertFalse(collection1.contains(false));
        Assert.assertFalse(collection2.contains(true));
        Assert.assertFalse(collection3.contains(true));
        Assert.assertFalse(collection3.contains(false));
        Assert.assertFalse(collection4.contains(false));
        Assert.assertEquals(this.newMutableCollectionWith(), collection0);
        Assert.assertEquals(this.newMutableCollectionWith(), collection1);
        Assert.assertEquals(this.newMutableCollectionWith(), collection2);
        Assert.assertEquals(this.newMutableCollectionWith(), collection3);
        Assert.assertEquals(this.newMutableCollectionWith(), collection4);
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
        Assert.assertFalse(emptyCollection.containsAll(true));
        Assert.assertFalse(emptyCollection.containsAll(false));
        Assert.assertFalse(emptyCollection.containsAll(false, true, false));
        emptyCollection.add(false);
        Assert.assertFalse(emptyCollection.containsAll(true));
        Assert.assertTrue(emptyCollection.containsAll(false));
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        super.containsAllIterable();
        MutableBooleanCollection emptyCollection = this.newWith();
        Assert.assertTrue(emptyCollection.containsAll(new BooleanArrayList()));
        Assert.assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(false)));
        emptyCollection.add(false);
        Assert.assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertTrue(emptyCollection.containsAll(BooleanArrayList.newListWith(false)));
    }

    @Test
    public void add()
    {
        MutableBooleanCollection emptyCollection = this.newWith();
        Assert.assertTrue(emptyCollection.add(true));
        Assert.assertEquals(this.newMutableCollectionWith(true), emptyCollection);
        Assert.assertTrue(emptyCollection.add(false));
        Assert.assertEquals(this.newMutableCollectionWith(true, false), emptyCollection);
        Assert.assertTrue(emptyCollection.add(true));
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true), emptyCollection);
        Assert.assertTrue(emptyCollection.add(false));
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true, false), emptyCollection);
        MutableBooleanCollection collection = this.classUnderTest();
        Assert.assertTrue(collection.add(false));
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true, false), collection);
    }

    @Test
    public void addAllArray()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        Assert.assertFalse(collection.addAll());
        Assert.assertTrue(collection.addAll(false, true, false));
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true, false, true, false), collection);
        Assert.assertTrue(collection.addAll(this.newMutableCollectionWith(true, false, true, false, true)));
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true, false, true, false, true, false, true, false, true), collection);
    }

    @Test
    public void addAllIterable()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        Assert.assertFalse(collection.addAll(this.newMutableCollectionWith()));
        Assert.assertTrue(collection.addAll(this.newMutableCollectionWith(false, true, false)));
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true, false, true, false), collection);
        Assert.assertTrue(collection.addAll(this.newMutableCollectionWith(true, false, true, false, true)));
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true, false, true, false, true, false, true, false, true), collection);

        MutableBooleanCollection emptyCollection = this.newWith();
        Assert.assertTrue(emptyCollection.addAll(BooleanArrayList.newListWith(true, false, true, false, true)));
        Assert.assertFalse(emptyCollection.addAll(new BooleanArrayList()));
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true, false, true), emptyCollection);
    }

    @Test
    public void remove()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        Assert.assertTrue(collection.remove(false));
        Assert.assertEquals(this.newMutableCollectionWith(true, true), collection);
        Assert.assertFalse(collection.remove(false));
        Assert.assertEquals(this.newMutableCollectionWith(true, true), collection);
        Assert.assertTrue(collection.remove(true));
        Assert.assertEquals(this.newMutableCollectionWith(true), collection);

        MutableBooleanCollection collection1 = this.newWith();
        Assert.assertFalse(collection1.remove(false));
        Assert.assertEquals(this.newMutableCollectionWith(), collection1);
        Assert.assertTrue(collection1.add(false));
        Assert.assertTrue(collection1.add(false));
        Assert.assertTrue(collection1.remove(false));
        Assert.assertEquals(this.newMutableCollectionWith(false), collection1);
        Assert.assertTrue(collection1.remove(false));
        Assert.assertEquals(this.newMutableCollectionWith(), collection1);

        MutableBooleanCollection collection2 = this.newWith();
        Assert.assertFalse(collection2.remove(true));
        Assert.assertEquals(this.newMutableCollectionWith(), collection2);
        Assert.assertTrue(collection2.add(true));
        Assert.assertTrue(collection2.add(true));
        Assert.assertTrue(collection2.remove(true));
        Assert.assertEquals(this.newMutableCollectionWith(true), collection2);
        Assert.assertTrue(collection2.remove(true));
        Assert.assertEquals(this.newMutableCollectionWith(), collection2);
    }

    @Test
    public void removeAll()
    {
        Assert.assertFalse(this.newWith().removeAll(true));

        MutableBooleanCollection collection = this.classUnderTest();
        Assert.assertFalse(collection.removeAll());
        Assert.assertTrue(collection.removeAll(true));
        Assert.assertEquals(this.newMutableCollectionWith(false), collection);
        Assert.assertFalse(collection.removeAll(true));
        Assert.assertEquals(this.newMutableCollectionWith(false), collection);
        Assert.assertTrue(collection.removeAll(false, true));
        Assert.assertEquals(this.newMutableCollectionWith(), collection);

        MutableBooleanCollection booleanArrayCollection = this.newWith(false, false);
        Assert.assertFalse(booleanArrayCollection.removeAll(true));
        Assert.assertEquals(this.newMutableCollectionWith(false, false), booleanArrayCollection);
        Assert.assertTrue(booleanArrayCollection.removeAll(false));
        Assert.assertEquals(this.newMutableCollectionWith(), booleanArrayCollection);
        MutableBooleanCollection collection1 = this.classUnderTest();
        Assert.assertFalse(collection1.removeAll());
        Assert.assertTrue(collection1.removeAll(true, false));
        Assert.assertEquals(this.newMutableCollectionWith(), collection1);

        MutableBooleanCollection trueFalseList = this.newWith(true, false);
        Assert.assertTrue(trueFalseList.removeAll(true));
        Assert.assertEquals(this.newMutableCollectionWith(false), trueFalseList);

        MutableBooleanCollection collection2 = this.newWith(true, false, true, false, true);
        Assert.assertFalse(collection2.removeAll());
        Assert.assertTrue(collection2.removeAll(true, true));
        Assert.assertEquals(this.newMutableCollectionWith(false, false), collection2);

        MutableBooleanCollection collection3 = this.newWith(true, false, true, false, true);
        Assert.assertFalse(collection3.removeAll());
        Assert.assertTrue(collection3.removeAll(true, false));
        Assert.assertEquals(this.newMutableCollectionWith(), collection3);

        MutableBooleanCollection collection4 = this.newWith(true, false, true, false, true);
        Assert.assertFalse(collection4.removeAll());
        Assert.assertTrue(collection4.removeAll(false, false));
        Assert.assertEquals(this.newMutableCollectionWith(true, true, true), collection4);
    }

    @Test
    public void removeAll_iterable()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        Assert.assertFalse(collection.removeAll(this.newMutableCollectionWith()));
        Assert.assertTrue(collection.removeAll(this.newMutableCollectionWith(false)));
        Assert.assertEquals(this.newMutableCollectionWith(true, true), collection);
        Assert.assertTrue(collection.removeAll(this.newMutableCollectionWith(true, true)));
        Assert.assertEquals(this.newMutableCollectionWith(), collection);

        MutableBooleanCollection list = this.classUnderTest();
        Assert.assertFalse(list.removeAll(new BooleanArrayList()));
        MutableBooleanCollection booleanArrayList = this.newWith(false, false);
        Assert.assertFalse(booleanArrayList.removeAll(new BooleanArrayList(true)));
        Assert.assertEquals(this.newMutableCollectionWith(false, false), booleanArrayList);
        Assert.assertTrue(booleanArrayList.removeAll(new BooleanArrayList(false)));
        Assert.assertEquals(this.newMutableCollectionWith(), booleanArrayList);
        Assert.assertTrue(list.removeAll(new BooleanArrayList(true)));
        Assert.assertEquals(this.newMutableCollectionWith(false), list);
        Assert.assertTrue(list.removeAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertEquals(this.newMutableCollectionWith(), list);
        Assert.assertFalse(list.removeAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertEquals(this.newMutableCollectionWith(), list);

        MutableBooleanCollection list1 = this.newWith(true, false, true, true);
        Assert.assertFalse(list1.removeAll(new BooleanArrayList()));
        Assert.assertTrue(list1.removeAll(BooleanArrayList.newListWith(true, true)));
        Verify.assertSize(1, list1);
        Assert.assertFalse(list1.contains(true));
        Assert.assertEquals(this.newMutableCollectionWith(false), list1);
        Assert.assertTrue(list1.removeAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertEquals(this.newMutableCollectionWith(), list1);

        MutableBooleanCollection list2 = this.newWith(true, false, true, false, true);
        Assert.assertTrue(list2.removeAll(BooleanHashBag.newBagWith(true, false)));
        Assert.assertEquals(this.newMutableCollectionWith(), list2);
    }

    @Test
    public void retainAll_iterable()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        Assert.assertFalse(collection.retainAll(true, false));
        Assert.assertTrue(collection.retainAll(true));
        Assert.assertEquals(this.newMutableCollectionWith(true, true), collection);
        Assert.assertTrue(collection.retainAll(false, false));
        Assert.assertEquals(this.newMutableCollectionWith(), collection);

        MutableBooleanCollection list = this.classUnderTest();
        Assert.assertFalse(list.retainAll(false, false, true));
        MutableBooleanCollection booleanArrayList = this.newWith(false, false);
        Assert.assertFalse(booleanArrayList.retainAll(false));
        Assert.assertEquals(this.newMutableCollectionWith(false, false), booleanArrayList);
        Assert.assertTrue(booleanArrayList.retainAll(true));
        Assert.assertEquals(this.newMutableCollectionWith(), booleanArrayList);
        Assert.assertTrue(list.retainAll(false));
        Assert.assertEquals(this.newMutableCollectionWith(false), list);
        Assert.assertTrue(list.retainAll());
        Assert.assertEquals(this.newMutableCollectionWith(), list);
        Assert.assertFalse(list.retainAll(true, false));
        Assert.assertEquals(this.newMutableCollectionWith(), list);

        MutableBooleanCollection list1 = this.newWith(true, false, true, true);
        Assert.assertFalse(list1.retainAll(false, false, true));
        Assert.assertTrue(list1.retainAll(false, false));
        Verify.assertSize(1, list1);
        Assert.assertFalse(list1.contains(true));
        Assert.assertEquals(this.newMutableCollectionWith(false), list1);
        Assert.assertTrue(list1.retainAll(true, true));
        Assert.assertEquals(this.newMutableCollectionWith(), list1);

        MutableBooleanCollection list2 = this.newWith(true, false, true, false, true);
        Assert.assertTrue(list2.retainAll());
        Assert.assertEquals(this.newMutableCollectionWith(), list2);
    }

    @Test
    public void retainAll()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        Assert.assertFalse(collection.retainAll(this.newMutableCollectionWith(true, false)));
        Assert.assertTrue(collection.retainAll(this.newMutableCollectionWith(true)));
        Assert.assertEquals(this.newMutableCollectionWith(true, true), collection);
        Assert.assertTrue(collection.retainAll(this.newMutableCollectionWith(false, false)));
        Assert.assertEquals(this.newMutableCollectionWith(), collection);

        MutableBooleanCollection list = this.classUnderTest();
        Assert.assertFalse(list.retainAll(BooleanArrayList.newListWith(false, false, true)));
        MutableBooleanCollection booleanArrayList = this.newWith(false, false);
        Assert.assertFalse(booleanArrayList.retainAll(new BooleanArrayList(false)));
        Assert.assertEquals(this.newMutableCollectionWith(false, false), booleanArrayList);
        Assert.assertTrue(booleanArrayList.retainAll(new BooleanArrayList(true)));
        Assert.assertEquals(this.newMutableCollectionWith(), booleanArrayList);
        Assert.assertTrue(list.retainAll(new BooleanArrayList(false)));
        Assert.assertEquals(this.newMutableCollectionWith(false), list);
        Assert.assertTrue(list.retainAll(new BooleanArrayList()));
        Assert.assertEquals(this.newMutableCollectionWith(), list);
        Assert.assertFalse(list.retainAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertEquals(this.newMutableCollectionWith(), list);

        MutableBooleanCollection list1 = this.newWith(true, false, true, true);
        Assert.assertFalse(list1.retainAll(BooleanArrayList.newListWith(false, false, true)));
        Assert.assertTrue(list1.retainAll(BooleanArrayList.newListWith(false, false)));
        Verify.assertSize(1, list1);
        Assert.assertFalse(list1.contains(true));
        Assert.assertEquals(this.newMutableCollectionWith(false), list1);
        Assert.assertTrue(list1.retainAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertEquals(this.newMutableCollectionWith(), list1);

        MutableBooleanCollection list2 = this.newWith(true, false, true, false, true);
        Assert.assertTrue(list2.retainAll(new BooleanHashBag()));
        Assert.assertEquals(this.newMutableCollectionWith(), list2);
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
        Assert.assertSame(emptyCollection, collection);
        Assert.assertEquals(this.newMutableCollectionWith(true), collection);
        Assert.assertEquals(this.newMutableCollectionWith(true, false), collection0);
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true), collection1);
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true, false), collection2);
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true, false, true), collection3);
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
        Assert.assertSame(emptyCollection, collection);
        Assert.assertEquals(this.newMutableCollectionWith(true), collection);
        Assert.assertEquals(this.newMutableCollectionWith(true, false), collection0);
        Assert.assertEquals(this.classUnderTest(), collection1);
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true, false), collection2);
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true, false, true), collection3);
    }

    @Test
    public void without()
    {
        MutableBooleanCollection collection = this.newWith(true, false, true, false, true);
        Assert.assertEquals(this.newMutableCollectionWith(true, true, false, true), collection.without(false));
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true), collection.without(true));
        Assert.assertEquals(this.newMutableCollectionWith(true, true), collection.without(false));
        Assert.assertEquals(this.newMutableCollectionWith(true), collection.without(true));
        Assert.assertEquals(this.newMutableCollectionWith(true), collection.without(false));
        Assert.assertEquals(this.newMutableCollectionWith(), collection.without(true));
        Assert.assertEquals(this.newMutableCollectionWith(), collection.without(false));

        MutableBooleanCollection collection1 = this.newWith(true, false, true, false, true);
        Assert.assertSame(collection1, collection1.without(false));
        Assert.assertEquals(this.newMutableCollectionWith(true, true, false, true), collection1);
    }

    @Test
    public void withoutAll()
    {
        MutableBooleanCollection mainCollection = this.newWith(true, false, true, false, true);
        Assert.assertEquals(this.newMutableCollectionWith(true, true, true), mainCollection.withoutAll(this.newMutableCollectionWith(false, false)));

        MutableBooleanCollection collection = this.newWith(true, false, true, false, true);
        Assert.assertEquals(this.newMutableCollectionWith(true, true, true), collection.withoutAll(BooleanHashBag.newBagWith(false)));
        Assert.assertEquals(this.newMutableCollectionWith(), collection.withoutAll(BooleanHashBag.newBagWith(true, false)));
        Assert.assertEquals(this.newMutableCollectionWith(), collection.withoutAll(BooleanHashBag.newBagWith(true, false)));

        MutableBooleanCollection trueCollection = this.newWith(true, true, true);
        Assert.assertEquals(this.newMutableCollectionWith(true, true, true), trueCollection.withoutAll(BooleanArrayList.newListWith(false)));
        MutableBooleanCollection mutableBooleanCollection = trueCollection.withoutAll(BooleanArrayList.newListWith(true));
        Assert.assertEquals(this.newMutableCollectionWith(), mutableBooleanCollection);
        Assert.assertSame(trueCollection, mutableBooleanCollection);
    }

    @Test
    public void asSynchronized()
    {
        MutableBooleanCollection collection = this.classUnderTest();
        Verify.assertInstanceOf(this.newWith(true, false, true).asSynchronized().getClass(), collection.asSynchronized());
        Assert.assertEquals(this.newWith(true, false, true).asSynchronized(), collection.asSynchronized());
        Assert.assertEquals(collection, collection.asSynchronized());
    }

    @Test
    public void asUnmodifiable()
    {
        Verify.assertInstanceOf(this.newWith(true, false, true).asUnmodifiable().getClass(), this.classUnderTest().asUnmodifiable());
        Assert.assertEquals(this.newWith(true, false, true).asUnmodifiable(), this.classUnderTest().asUnmodifiable());
        Assert.assertEquals(this.classUnderTest(), this.classUnderTest().asUnmodifiable());
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
            Assert.assertTrue(iterator.hasNext());
            iterator.next();
            iterator.remove();
            Verify.assertSize(iterableSize, booleanIterable);
        }
        Verify.assertEmpty(booleanIterable);
        Assert.assertFalse(iterator.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) iterator::next);
    }

    @Test
    public void iterator_throws_on_invocation_of_remove_before_next()
    {
        MutableBooleanCollection booleanIterable = this.newWith(true, false);
        MutableBooleanIterator iterator = booleanIterable.booleanIterator();
        Assert.assertTrue(iterator.hasNext());
        Verify.assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void iterator_throws_on_consecutive_invocation_of_remove()
    {
        MutableBooleanCollection booleanIterable = this.newWith(true, false);
        MutableBooleanIterator iterator = booleanIterable.booleanIterator();
        iterator.next();
        iterator.remove();
        Verify.assertThrows(IllegalStateException.class, iterator::remove);
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

        Verify.assertThrows(IllegalArgumentException.class, () -> this.classUnderTest().chunk(0));
        Verify.assertThrows(IllegalArgumentException.class, () -> this.classUnderTest().chunk(-1));
    }
}
