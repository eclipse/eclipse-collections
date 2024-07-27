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

import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @Test
    public void addAtIndex()
    {
        assertThrows(UnsupportedOperationException.class, () -> new UnmodifiableBooleanList(new BooleanArrayList()).addAtIndex(0, true));
    }

    @Override
    @Test
    public void addAtIndex_throws_index_greater_than_size()
    {
        assertThrows(UnsupportedOperationException.class, () -> new UnmodifiableBooleanList(new BooleanArrayList()).addAtIndex(1, false));
    }

    @Test
    public void unmodifiableBoxed()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(true, false, true).asUnmodifiable().add(true));
    }

    @Override
    @Test
    public void addAtIndex_throws_index_negative()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.list.addAtIndex(-1, true));
    }

    @Override
    @Test
    public void addAll_throws_index_negative()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.list.addAllAtIndex(-1, true, true));
    }

    @Override
    @Test
    public void addAll_throws_index_greater_than_size()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.list.addAllAtIndex(5, true, true));
    }

    @Override
    @Test
    public void addAll_throws_index_greater_than_size_empty_list()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith().addAllAtIndex(1, false));
    }

    @Test
    public void addAllIterable_throws_index_negative()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.list.addAllAtIndex(-1, BooleanArrayList.newListWith(true, true)));
    }

    @Test
    public void addAllIterable_throws_index_greater_than_size()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.list.addAllAtIndex(5, BooleanArrayList.newListWith(true, true)));
    }

    @Override
    @Test
    public void removeAtIndex()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.list.removeAtIndex(1));
    }

    @Override
    @Test
    public void removeAtIndex_throws_index_greater_than_size()
    {
        MutableBooleanList emptyList = new UnmodifiableBooleanList(new BooleanArrayList());
        assertThrows(UnsupportedOperationException.class, () -> emptyList.removeAtIndex(1));
    }

    @Override
    @Test
    public void removeAtIndex_throws_index_negative()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.list.removeAtIndex(-1));
    }

    @Override
    @Test
    public void set()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.list.set(1, true));
    }

    @Override
    @Test
    public void set_throws_index_greater_than_size()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith().set(1, false));
    }

    @Override
    @Test
    public void clear()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().clear());
    }

    @Override
    @Test
    public void add()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith().add(true));
    }

    @Override
    @Test
    public void addAllArray()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().addAll(true, false, true));
    }

    @Override
    @Test
    public void addAllIterable()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().addAll(this.newMutableCollectionWith()));
    }

    @Override
    @Test
    public void remove()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().remove(false));
    }

    @Override
    @Test
    public void removeIf()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().removeIf(b -> false));
    }

    @Override
    @Test
    public void removeAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().removeAll(true, false));
    }

    @Override
    @Test
    public void removeAll_iterable()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().removeAll(this.newMutableCollectionWith()));
    }

    @Override
    @Test
    public void retainAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().retainAll());
    }

    @Override
    @Test
    public void retainAll_iterable()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().retainAll(this.newMutableCollectionWith()));
    }

    @Override
    @Test
    public void with()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith().with(true));
    }

    @Override
    @Test
    public void withAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith().withAll(this.newMutableCollectionWith(true)));
    }

    @Override
    @Test
    public void without()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(true, false, true, false, true).without(true));
    }

    @Override
    @Test
    public void withoutAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(true, false, true, false, true).withoutAll(this.newMutableCollectionWith(false, false)));
    }

    @Override
    @Test
    public void reverseThis()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(false, false, true, true).reverseThis());
    }

    @Override
    @Test
    public void containsAllArray()
    {
        UnmodifiableBooleanList collection = this.classUnderTest();
        assertTrue(collection.containsAll(true));
        assertTrue(collection.containsAll(true, false, true));
        assertTrue(collection.containsAll(true, false));
        assertTrue(collection.containsAll(true, true));
        assertTrue(collection.containsAll(false, false));
        UnmodifiableBooleanList emptyCollection = this.newWith();
        assertFalse(emptyCollection.containsAll(true));
        assertFalse(emptyCollection.containsAll(false));
        assertFalse(emptyCollection.containsAll(false, true, false));
        assertFalse(this.newWith(true, true).containsAll(false, true, false));

        UnmodifiableBooleanList trueCollection = this.newWith(true, true, true, true);
        assertFalse(trueCollection.containsAll(true, false));
        UnmodifiableBooleanList falseCollection = this.newWith(false, false, false, false);
        assertFalse(falseCollection.containsAll(true, false));
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        UnmodifiableBooleanList emptyCollection = this.newWith();
        assertTrue(emptyCollection.containsAll(new BooleanArrayList()));
        assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(true)));
        assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(false)));
        UnmodifiableBooleanList collection = this.newWith(true, true, false, false, false);
        assertTrue(collection.containsAll(BooleanArrayList.newListWith(true)));
        assertTrue(collection.containsAll(BooleanArrayList.newListWith(false)));
        assertTrue(collection.containsAll(BooleanArrayList.newListWith(true, false)));
        assertTrue(collection.containsAll(BooleanArrayList.newListWith(true, true)));
        assertTrue(collection.containsAll(BooleanArrayList.newListWith(false, false)));
        assertTrue(collection.containsAll(BooleanArrayList.newListWith(true, false, true)));
        assertFalse(this.newWith(true, true).containsAll(BooleanArrayList.newListWith(false, true, false)));

        UnmodifiableBooleanList trueCollection = this.newWith(true, true, true, true);
        assertFalse(trueCollection.containsAll(BooleanArrayList.newListWith(true, false)));
        UnmodifiableBooleanList falseCollection = this.newWith(false, false, false, false);
        assertFalse(falseCollection.containsAll(BooleanArrayList.newListWith(true, false)));
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        super.asUnmodifiable();
        assertSame(this.list, this.list.asUnmodifiable());
        assertEquals(this.list, this.list.asUnmodifiable());
    }

    @Override
    @Test
    public void booleanIterator_with_remove()
    {
        MutableBooleanIterator booleanIterator = this.classUnderTest().booleanIterator();
        assertTrue(booleanIterator.hasNext());
        booleanIterator.next();
        assertThrows(UnsupportedOperationException.class, booleanIterator::remove);
    }

    @Override
    @Test
    public void iterator_throws_on_invocation_of_remove_before_next()
    {
        MutableBooleanIterator booleanIterator = this.classUnderTest().booleanIterator();
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
