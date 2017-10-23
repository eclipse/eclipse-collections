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

import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link UnmodifiableBooleanList}.
 */
public class UnmodifiableBooleanListTest extends AbstractBooleanListTestCase
{
    private final UnmodifiableBooleanList list = this.classUnderTest();

    @Override
    protected final UnmodifiableBooleanList classUnderTest()
    {
        return new UnmodifiableBooleanList(BooleanArrayList.newListWith(true, false, true));
    }

    @Override
    protected UnmodifiableBooleanList newWith(boolean... elements)
    {
        return new UnmodifiableBooleanList(BooleanArrayList.newListWith(elements));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void addAtIndex()
    {
        new UnmodifiableBooleanList(new BooleanArrayList()).addAtIndex(0, true);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void addAtIndex_throws_index_greater_than_size()
    {
        new UnmodifiableBooleanList(new BooleanArrayList()).addAtIndex(1, false);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void addAtIndex_throws_index_negative()
    {
        this.list.addAtIndex(-1, true);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void addAll_throws_index_negative()
    {
        this.list.addAllAtIndex(-1, true, true);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void addAll_throws_index_greater_than_size()
    {
        this.list.addAllAtIndex(5, true, true);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void addAll_throws_index_greater_than_size_empty_list()
    {
        this.newWith().addAllAtIndex(1, false);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addAllIterable_throws_index_negative()
    {
        this.list.addAllAtIndex(-1, BooleanArrayList.newListWith(true, true));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addAllIterable_throws_index_greater_than_size()
    {
        this.list.addAllAtIndex(5, BooleanArrayList.newListWith(true, true));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeAtIndex()
    {
        this.list.removeAtIndex(1);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeAtIndex_throws_index_greater_than_size()
    {
        MutableBooleanList emptyList = new UnmodifiableBooleanList(new BooleanArrayList());
        emptyList.removeAtIndex(1);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeAtIndex_throws_index_negative()
    {
        this.list.removeAtIndex(-1);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void set()
    {
        this.list.set(1, true);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void set_throws_index_greater_than_size()
    {
        this.newWith().set(1, false);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void clear()
    {
        this.classUnderTest().clear();
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void add()
    {
        this.newWith().add(true);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void addAllArray()
    {
        this.classUnderTest().addAll(true, false, true);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void addAllIterable()
    {
        this.classUnderTest().addAll(this.newMutableCollectionWith());
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void remove()
    {
        this.classUnderTest().remove(false);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeIf()
    {
        this.classUnderTest().removeIf(b -> false);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeAll()
    {
        this.classUnderTest().removeAll(true, false);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeAll_iterable()
    {
        this.classUnderTest().removeAll(this.newMutableCollectionWith());
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void retainAll()
    {
        this.classUnderTest().retainAll();
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void retainAll_iterable()
    {
        this.classUnderTest().retainAll(this.newMutableCollectionWith());
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void with()
    {
        this.newWith().with(true);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void withAll()
    {
        this.newWith().withAll(this.newMutableCollectionWith(true));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void without()
    {
        this.newWith(true, false, true, false, true).without(true);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void withoutAll()
    {
        this.newWith(true, false, true, false, true).withoutAll(this.newMutableCollectionWith(false, false));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void reverseThis()
    {
        this.newWith(false, false, true, true).reverseThis();
    }

    @Override
    @Test
    public void containsAllArray()
    {
        UnmodifiableBooleanList collection = this.classUnderTest();
        Assert.assertTrue(collection.containsAll(true));
        Assert.assertTrue(collection.containsAll(true, false, true));
        Assert.assertTrue(collection.containsAll(true, false));
        Assert.assertTrue(collection.containsAll(true, true));
        Assert.assertTrue(collection.containsAll(false, false));
        UnmodifiableBooleanList emptyCollection = this.newWith();
        Assert.assertFalse(emptyCollection.containsAll(true));
        Assert.assertFalse(emptyCollection.containsAll(false));
        Assert.assertFalse(emptyCollection.containsAll(false, true, false));
        Assert.assertFalse(this.newWith(true, true).containsAll(false, true, false));

        UnmodifiableBooleanList trueCollection = this.newWith(true, true, true, true);
        Assert.assertFalse(trueCollection.containsAll(true, false));
        UnmodifiableBooleanList falseCollection = this.newWith(false, false, false, false);
        Assert.assertFalse(falseCollection.containsAll(true, false));
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        UnmodifiableBooleanList emptyCollection = this.newWith();
        Assert.assertTrue(emptyCollection.containsAll(new BooleanArrayList()));
        Assert.assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(false)));
        UnmodifiableBooleanList collection = this.newWith(true, true, false, false, false);
        Assert.assertTrue(collection.containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertTrue(collection.containsAll(BooleanArrayList.newListWith(false)));
        Assert.assertTrue(collection.containsAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertTrue(collection.containsAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertTrue(collection.containsAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertTrue(collection.containsAll(BooleanArrayList.newListWith(true, false, true)));
        Assert.assertFalse(this.newWith(true, true).containsAll(BooleanArrayList.newListWith(false, true, false)));

        UnmodifiableBooleanList trueCollection = this.newWith(true, true, true, true);
        Assert.assertFalse(trueCollection.containsAll(BooleanArrayList.newListWith(true, false)));
        UnmodifiableBooleanList falseCollection = this.newWith(false, false, false, false);
        Assert.assertFalse(falseCollection.containsAll(BooleanArrayList.newListWith(true, false)));
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        super.asUnmodifiable();
        Assert.assertSame(this.list, this.list.asUnmodifiable());
        Assert.assertEquals(this.list, this.list.asUnmodifiable());
    }

    @Override
    @Test
    public void booleanIterator_with_remove()
    {
        MutableBooleanIterator booleanIterator = this.classUnderTest().booleanIterator();
        Assert.assertTrue(booleanIterator.hasNext());
        booleanIterator.next();
        Verify.assertThrows(UnsupportedOperationException.class, booleanIterator::remove);
    }

    @Override
    @Test
    public void iterator_throws_on_invocation_of_remove_before_next()
    {
        MutableBooleanIterator booleanIterator = this.classUnderTest().booleanIterator();
        Assert.assertTrue(booleanIterator.hasNext());
        Verify.assertThrows(UnsupportedOperationException.class, booleanIterator::remove);
    }

    @Override
    @Test
    public void iterator_throws_on_consecutive_invocation_of_remove()
    {
        // Not applicable for Unmodifiable*
    }
}
