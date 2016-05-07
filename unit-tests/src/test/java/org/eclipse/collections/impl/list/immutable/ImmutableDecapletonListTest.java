/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;

public class ImmutableDecapletonListTest extends AbstractImmutableListTestCase
{
    @Override
    protected ImmutableList<Integer> classUnderTest()
    {
        return new ImmutableDecapletonList<>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Override
    @Test
    public void toSortedSetBy()
    {
        MutableList<Integer> expected = TreeSortedSet.newSetWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10").collect(Integer::valueOf);
        MutableList<Integer> sortedList = this.classUnderTest().toSortedSetBy(String::valueOf).toList();
        Verify.assertListsEqual(expected, sortedList);
    }

    @Test
    public void selectInstanceOf()
    {
        ImmutableList<Number> numbers = new ImmutableDecapletonList<>(1, 2.0, 3, 4.0, 5, 6.0, 7, 8.0, 9, 10.0);
        Assert.assertEquals(
                iList(1, 3, 5, 7, 9),
                numbers.selectInstancesOf(Integer.class));
    }

    @Test(expected = IllegalStateException.class)
    public void getOnly()
    {
        ImmutableList<Integer> list = this.classUnderTest();
        list.getOnly();
    }
}
