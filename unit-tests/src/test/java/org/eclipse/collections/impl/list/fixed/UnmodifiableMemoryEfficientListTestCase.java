/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.fixed;

import java.util.ListIterator;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.collection.mutable.UnmodifiableMutableCollectionTestCase;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * Abstract JUnit test to check that {@link AbstractMemoryEfficientMutableList}s are Unmodifiable.
 */
public abstract class UnmodifiableMemoryEfficientListTestCase<T> extends UnmodifiableMutableCollectionTestCase<T>
{
    @Override
    protected abstract MutableList<T> getCollection();

    @Test
    public void listIterator()
    {
        MutableList<T> collection = this.getCollection();
        ListIterator<T> it = collection.listIterator();
        Assert.assertFalse(it.hasPrevious());
        Assert.assertEquals(-1, it.previousIndex());
        Assert.assertEquals(0, it.nextIndex());
        it.next();
        Assert.assertEquals(1, it.nextIndex());

        Verify.assertThrows(UnsupportedOperationException.class, it::remove);

        Verify.assertThrows(UnsupportedOperationException.class, () -> it.add(null));

        it.set(null);
        Assert.assertNotEquals(this.getCollection(), collection);
    }

    @Test
    public void addAllAtIndex()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().addAll(0, FastList.<T>newList().with((T) null)));
    }

    @Test
    public void addAtIndex()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().add(0, null));
    }

    @Test
    public void removeFromIndex()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().remove(0));
    }

    @Test
    public void subList()
    {
        MutableList<T> subList = this.getCollection().subList(0, 1);
        Verify.assertThrows(UnsupportedOperationException.class, subList::clear);
    }

    @Override
    @Test
    public void newEmpty()
    {
        MutableList<T> list = this.getCollection().newEmpty();
        list.add(null);
        Verify.assertContains(null, list);
    }

    @Test
    public void corresponds()
    {
        MutableList<T> mutableList1 = this.getCollection();
        MutableList<Integer> mutableList2 = mutableList1.collect(element -> Integer.valueOf(element.toString()) + 1);
        Assert.assertTrue(mutableList1.corresponds(mutableList2, (argument1, argument2) -> Integer.valueOf(argument1.toString()) < argument2));
        Assert.assertFalse(mutableList1.corresponds(mutableList2, (argument1, argument2) -> Integer.valueOf(argument1.toString()) > argument2));

        MutableList<Integer> mutableList3 = this.getCollection().collect(element -> Integer.valueOf(element.toString()));
        mutableList3.add(0);
        Assert.assertFalse(mutableList1.corresponds(mutableList3, Predicates2.alwaysTrue()));
    }

    @Test
    public void detectIndex()
    {
        MutableList<T> mutableList = this.getCollection();
        Assert.assertEquals(0, mutableList.detectIndex(element -> Integer.valueOf(element.toString()) == 1));
        Assert.assertEquals(-1, mutableList.detectIndex(element -> Integer.valueOf(element.toString()) == 0));
    }

    @Test
    public void detectLastIndex()
    {
        MutableList<T> mutableList = this.getCollection();
        Assert.assertEquals(0, mutableList.detectLastIndex(element -> Integer.valueOf(element.toString()) == 1));
        Assert.assertEquals(-1, mutableList.detectLastIndex(element -> Integer.valueOf(element.toString()) == 0));
    }
}
