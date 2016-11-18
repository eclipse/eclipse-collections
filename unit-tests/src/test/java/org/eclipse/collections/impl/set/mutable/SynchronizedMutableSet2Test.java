/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link SynchronizedMutableSet}.
 */
public class SynchronizedMutableSet2Test extends AbstractMutableSetTestCase
{
    @Override
    protected <T> MutableSet<T> newWith(T... littleElements)
    {
        return new SynchronizedMutableSet<>(SetAdapter.adapt(new HashSet<>(UnifiedSet.newSetWith(littleElements))));
    }

    @Test(expected = NoSuchElementException.class)
    public void min_empty_throws_without_comparator()
    {
        this.newWith().min();
    }

    @Test(expected = NoSuchElementException.class)
    public void max_empty_throws_without_comparator()
    {
        this.newWith().max();
    }

    @Override
    @Test
    public void testToString()
    {
        MutableSet<Integer> integer = this.newWith(1);
        Assert.assertEquals("[1]", integer.toString());
    }

    @Override
    @Test
    public void makeString()
    {
        MutableSet<Integer> integer = this.newWith(1);
        Assert.assertEquals("{1}", integer.makeString("{", ",", "}"));
    }

    @Override
    @Test
    public void appendString()
    {
        Appendable stringBuilder = new StringBuilder();
        MutableSet<Integer> integer = this.newWith(1);
        integer.appendString(stringBuilder, "{", ",", "}");
        Assert.assertEquals("{1}", stringBuilder.toString());
    }

    @Override
    @Test
    public void removeIf()
    {
        MutableSet<Integer> integers = this.newWith(1, 2, 3, 4);
        integers.remove(3);
        Verify.assertSetsEqual(UnifiedSet.newSetWith(1, 2, 4), integers);
    }

    @Override
    public void selectInstancesOf()
    {
        MutableSet<Number> numbers = new SynchronizedMutableSet<Number>(SetAdapter.adapt(new TreeSet<>((o1, o2) -> Double.compare(o1.doubleValue(), o2.doubleValue())))).withAll(FastList.newListWith(1, 2.0, 3, 4.0, 5));
        MutableSet<Integer> integers = numbers.selectInstancesOf(Integer.class);
        Assert.assertEquals(UnifiedSet.newSetWith(1, 3, 5), integers);
        Assert.assertEquals(FastList.newListWith(1, 3, 5), integers.toList());
    }

    @Test
    @Override
    public void getFirst()
    {
        Assert.assertNotNull(this.newWith(1, 2, 3).getFirst());
        Assert.assertNull(this.newWith().getFirst());
        Assert.assertEquals(Integer.valueOf(1), this.newWith(1).getFirst());
        int first = this.newWith(1, 2).getFirst().intValue();
        Assert.assertTrue(first == 1 || first == 2);
    }

    @Test
    @Override
    public void getLast()
    {
        Assert.assertNotNull(this.newWith(1, 2, 3).getLast());
        Assert.assertNull(this.newWith().getLast());
        Assert.assertEquals(Integer.valueOf(1), this.newWith(1).getLast());
        int last = this.newWith(1, 2).getLast().intValue();
        Assert.assertTrue(last == 1 || last == 2);
    }

    @Test
    @Override
    public void iterator()
    {
        MutableSet<Integer> objects = this.newWith(1, 2, 3);
        MutableBag<Integer> actual = Bags.mutable.of();

        Iterator<Integer> iterator = objects.iterator();
        for (int i = objects.size(); i-- > 0; )
        {
            Assert.assertTrue(iterator.hasNext());
            actual.add(iterator.next());
        }
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(Bags.mutable.of(1, 2, 3), actual);
    }
}
