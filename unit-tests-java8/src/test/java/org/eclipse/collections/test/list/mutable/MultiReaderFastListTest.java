/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.list.mutable;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.impl.list.mutable.MultiReaderFastList;
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.eclipse.collections.test.IterableTestCase;
import org.eclipse.collections.test.collection.mutable.MultiReaderMutableCollectionTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.eclipse.collections.impl.test.Verify.assertThrows;
import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Java8Runner.class)
public class MultiReaderFastListTest implements MutableListTestCase, MultiReaderMutableCollectionTestCase
{
    @SafeVarargs
    @Override
    public final <T> MultiReaderFastList<T> newWith(T... elements)
    {
        MultiReaderFastList<T> result = MultiReaderFastList.newList();
        IterableTestCase.addAllTo(elements, result);
        return result;
    }

    @Override
    @Test
    public void Iterable_remove()
    {
        MultiReaderMutableCollectionTestCase.super.Iterable_remove();
    }

    @Override
    @Test
    public void RichIterable_iterator_iterationOrder()
    {
        MutableCollection<Integer> iterationOrder = this.newMutableForFilter();
        MultiReaderFastList<Integer> instanceUnderTest = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
        instanceUnderTest.withReadLockAndDelegate(delegate -> {
            Iterator<Integer> iterator = delegate.iterator();
            while (iterator.hasNext())
            {
                iterationOrder.add(iterator.next());
            }
        });
        assertEquals(this.expectedIterationOrder(), iterationOrder);
    }

    @Override
    @Test
    public void OrderedIterable_next()
    {
        // Does not support iterator outside withReadLockAndDelegate

        MultiReaderFastList<Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);

        MutableCollection<Integer> mutableCollection = this.newMutableForFilter();

        iterable.withReadLockAndDelegate(delegate -> {
            Iterator<Integer> iterator = delegate.iterator();
            while (iterator.hasNext())
            {
                Integer integer = iterator.next();
                mutableCollection.add(integer);
            }

            assertEquals(this.getExpectedFiltered(3, 3, 3, 2, 2, 1), mutableCollection);
            assertFalse(iterator.hasNext());
        });
    }

    @Test
    public void MultiReaderFastList_hasNext()
    {
        MultiReaderFastList<Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);
        iterable.withReadLockAndDelegate(delegate -> assertTrue(delegate.iterator().hasNext()));
        MultiReaderFastList<?> emptyIterable = this.newWith();
        emptyIterable.withReadLockAndDelegate(delegate -> assertFalse(delegate.iterator().hasNext()));
    }

    @Test
    public void MultiReaderFastList_next_throws_at_end()
    {
        MultiReaderFastList<Integer> iterable = this.newWith(3, 2, 1);
        iterable.withReadLockAndDelegate(delegate -> {
            Iterator<Integer> iterator = delegate.iterator();
            assertTrue(iterator.hasNext());
            iterator.next();
            assertTrue(iterator.hasNext());
            iterator.next();
            assertTrue(iterator.hasNext());
            iterator.next();
            assertFalse(iterator.hasNext());
            assertThrows(NoSuchElementException.class, (Runnable) iterator::next);
        });
    }

    @Test
    public void MultiReaderFastList_next_throws_on_empty()
    {
        MultiReaderFastList<Object> iterable = this.newWith();
        assertThrows(
                NoSuchElementException.class,
                () -> iterable.withReadLockAndDelegate(delegate -> delegate.iterator().next()));
    }
}
