/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import java.util.ListIterator;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.list.fixed.UnmodifiableMemoryEfficientListTestCase;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * Abstract JUnit test for {@link UnmodifiableMutableList}.
 */
public abstract class UnmodifiableMutableListTestCase extends UnmodifiableMemoryEfficientListTestCase<Integer>
{
    @Test
    public void testClone()
    {
        Assert.assertEquals(this.getCollection(), this.getCollection().clone());
        Assert.assertNotSame(this.getCollection(), this.getCollection().clone());
    }

    @Test
    public void serialization()
    {
        Verify.assertPostSerializedEqualsAndHashCode(this.getCollection());
    }

    @Override
    @Test
    public void subList()
    {
        super.subList();
        MutableList<Integer> subList = this.getCollection().subList(0, 1);
        Assert.assertThrows(UnsupportedOperationException.class, subList::clear);
        Assert.assertThrows(UnsupportedOperationException.class, () -> subList.set(0, null));
        Assert.assertThrows(UnsupportedOperationException.class, () -> subList.add(0, null));
        Assert.assertThrows(UnsupportedOperationException.class, () -> subList.add(null));
        Assert.assertThrows(UnsupportedOperationException.class, () -> subList.remove(0));
        Assert.assertThrows(UnsupportedOperationException.class, () -> subList.remove(null));
    }

    @Override
    @Test
    public void listIterator()
    {
        ListIterator<Integer> it = this.getCollection().listIterator();
        Assert.assertFalse(it.hasPrevious());
        Assert.assertEquals(-1, it.previousIndex());
        Assert.assertEquals(0, it.nextIndex());
        it.next();
        Assert.assertEquals(1, it.nextIndex());

        Assert.assertThrows(UnsupportedOperationException.class, () -> it.set(null));

        Assert.assertThrows(UnsupportedOperationException.class, it::remove);

        Assert.assertThrows(UnsupportedOperationException.class, () -> it.add(null));
    }

    @Test
    public void subListListIterator()
    {
        ListIterator<Integer> it = this.getCollection().subList(0, 1).listIterator();
        Assert.assertFalse(it.hasPrevious());
        Assert.assertEquals(-1, it.previousIndex());
        Assert.assertEquals(0, it.nextIndex());
        it.next();
        Assert.assertEquals(1, it.nextIndex());

        Assert.assertThrows(UnsupportedOperationException.class, () -> it.set(null));

        Assert.assertThrows(UnsupportedOperationException.class, it::remove);

        Assert.assertThrows(UnsupportedOperationException.class, () -> it.add(null));
    }

    @Test
    public void set()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().set(0, null));
    }

    @Override
    @Test
    public void addAtIndex()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().add(0, null));
    }

    @Override
    @Test
    public void addAllAtIndex()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().addAll(0, null));
    }

    @Test
    public void removeAtIndex()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().remove(0));
    }

    @Test
    public void setAtIndex()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().set(0, null));
    }

    @Test
    public void sortThis()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().sortThis());
    }

    @Test
    public void sortThisWithComparator()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().sortThis(Comparators.naturalOrder()));
    }

    @Test
    public void sortThisBy()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().sortThisBy(String::valueOf));
    }

    @Test
    public void sortThisByBoolean()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().sortThisByBoolean(null));
    }

    @Test
    public void sortThisByChar()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().sortThisByChar(null));
    }

    @Test
    public void sortThisByByte()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().sortThisByByte(null));
    }

    @Test
    public void sortThisByShort()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().sortThisByShort(null));
    }

    @Test
    public void sortThisByInt()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().sortThisByInt(null));
    }

    @Test
    public void sortThisByFloat()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().sortThisByFloat(null));
    }

    @Test
    public void sortThisByLong()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().sortThisByLong(null));
    }

    @Test
    public void sortThisByDouble()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().sortThisByDouble(null));
    }

    @Test
    public void reverseThis()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.getCollection().reverseThis());
    }

    @Test
    public void testEquals()
    {
        Assert.assertEquals(this.getCollection(), this.getCollection());
    }
}
