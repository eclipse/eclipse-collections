/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.sorted.mutable;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link SynchronizedSortedSet}.
 */
public class SynchronizedSortedSet2Test extends AbstractSortedSetTestCase
{
    @Override
    protected <T> MutableSortedSet<T> newWith(T... elements)
    {
        return new SynchronizedSortedSet<>(TreeSortedSet.newSetWith(elements));
    }

    @Override
    protected <T> MutableSortedSet<T> newWith(Comparator<? super T> comparator, T... elements)
    {
        return new SynchronizedSortedSet<>(TreeSortedSet.newSetWith(comparator, elements));
    }

    @Override
    public void asSynchronized()
    {
        MutableSortedSet<Object> synchronizedSet = this.newWith();
        Assert.assertSame(synchronizedSet, synchronizedSet.asSynchronized());
    }

    @Override
    public void asUnmodifiable()
    {
        Verify.assertInstanceOf(UnmodifiableSortedSet.class, this.newWith().asUnmodifiable());
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
    @Test(expected = UnsupportedOperationException.class)
    public void detectLastIndex()
    {
        this.newWith(1, 2, 3).detectLastIndex(each -> each % 2 == 0);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void reverseForEach()
    {
        this.newWith(1, 2, 3).reverseForEach(each -> Assert.fail("Should not be evaluated"));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void reverseForEachWithIndex()
    {
        this.newWith(1, 2, 3).reverseForEachWithIndex((each, index) -> Assert.fail("Should not be evaluated"));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void toReversed()
    {
        this.newWith(1, 2, 3).toReversed();
    }
}
