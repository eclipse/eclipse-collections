/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable.primitive;

import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link UnmodifiableBooleanSet}.
 */
public class UnmodifiableBooleanSetTest extends AbstractBooleanSetTestCase
{
    @Override
    protected final UnmodifiableBooleanSet classUnderTest()
    {
        return new UnmodifiableBooleanSet(BooleanHashSet.newSetWith(true, false, true));
    }

    @Override
    protected UnmodifiableBooleanSet newWith(boolean... elements)
    {
        return new UnmodifiableBooleanSet(BooleanHashSet.newSetWith(elements));
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
    public void containsAllArray()
    {
        UnmodifiableBooleanSet collection = this.classUnderTest();
        assertTrue(collection.containsAll(true));
        assertTrue(collection.containsAll(true, false, true));
        assertTrue(collection.containsAll(true, false));
        assertTrue(collection.containsAll(true, true));
        assertTrue(collection.containsAll(false, false));
        UnmodifiableBooleanSet emptyCollection = this.newWith();
        assertFalse(emptyCollection.containsAll(true));
        assertFalse(emptyCollection.containsAll(false));
        assertFalse(emptyCollection.containsAll(false, true, false));
        assertFalse(this.newWith(true, true).containsAll(false, true, false));

        UnmodifiableBooleanSet trueCollection = this.newWith(true, true, true, true);
        assertFalse(trueCollection.containsAll(true, false));
        UnmodifiableBooleanSet falseCollection = this.newWith(false, false, false, false);
        assertFalse(falseCollection.containsAll(true, false));
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        UnmodifiableBooleanSet emptyCollection = this.newWith();
        assertTrue(emptyCollection.containsAll(new BooleanArrayList()));
        assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(true)));
        assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(false)));
        UnmodifiableBooleanSet collection = this.newWith(true, true, false, false, false);
        assertTrue(collection.containsAll(BooleanArrayList.newListWith(true)));
        assertTrue(collection.containsAll(BooleanArrayList.newListWith(false)));
        assertTrue(collection.containsAll(BooleanArrayList.newListWith(true, false)));
        assertTrue(collection.containsAll(BooleanArrayList.newListWith(true, true)));
        assertTrue(collection.containsAll(BooleanArrayList.newListWith(false, false)));
        assertTrue(collection.containsAll(BooleanArrayList.newListWith(true, false, true)));
        assertFalse(this.newWith(true, true).containsAll(BooleanArrayList.newListWith(false, true, false)));

        UnmodifiableBooleanSet trueCollection = this.newWith(true, true, true, true);
        assertFalse(trueCollection.containsAll(BooleanArrayList.newListWith(true, false)));
        UnmodifiableBooleanSet falseCollection = this.newWith(false, false, false, false);
        assertFalse(falseCollection.containsAll(BooleanArrayList.newListWith(true, false)));
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        super.asUnmodifiable();
        MutableBooleanSet set = this.classUnderTest();
        assertSame(set, set.asUnmodifiable());
        assertEquals(set, set.asUnmodifiable());
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
