/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.set.sorted;

import java.util.NoSuchElementException;

import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.set.sorted.ParallelSortedSetIterable;
import org.eclipse.collections.api.set.sorted.SortedSetIterable;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableEmptySortedSetParallelTest extends NonParallelSortedSetIterableTestCase
{
    @Override
    protected SortedSetIterable<Integer> getExpected()
    {
        return TreeSortedSet.newSetWith(Comparators.reverseNaturalOrder());
    }

    @Override
    protected SortedSetIterable<Integer> getExpectedWith(Integer... littleElements)
    {
        return TreeSortedSet.newSetWith(Comparators.reverseNaturalOrder());
    }

    @Override
    protected ParallelSortedSetIterable<Integer> classUnderTest()
    {
        return this.newWith();
    }

    @Override
    protected ParallelSortedSetIterable<Integer> newWith(Integer... littleElements)
    {
        return SortedSets.immutable.with(Comparators.<Integer>reverseNaturalOrder()).asParallel(this.executorService, this.batchSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void asParallel_small_batch()
    {
        SortedSets.immutable.with(Comparators.reverseNaturalOrder()).asParallel(this.executorService, 0);
    }

    @Test(expected = NullPointerException.class)
    public void asParallel_null_executorService()
    {
        SortedSets.immutable.with(Comparators.reverseNaturalOrder()).asParallel(null, 2);
    }

    @Override
    public void allSatisfy()
    {
        Assert.assertTrue(this.classUnderTest().allSatisfy(Predicates.lessThan(0)));
        Assert.assertTrue(this.classUnderTest().allSatisfy(Predicates.greaterThanOrEqualTo(0)));
    }

    @Override
    public void allSatisfyWith()
    {
        Assert.assertTrue(this.classUnderTest().allSatisfyWith(Predicates2.lessThan(), 0));
        Assert.assertTrue(this.classUnderTest().allSatisfyWith(Predicates2.greaterThanOrEqualTo(), 0));
    }

    @Override
    public void anySatisfy()
    {
        Assert.assertFalse(this.classUnderTest().anySatisfy(Predicates.lessThan(0)));
        Assert.assertFalse(this.classUnderTest().anySatisfy(Predicates.greaterThanOrEqualTo(0)));
    }

    @Override
    public void anySatisfyWith()
    {
        Assert.assertFalse(this.classUnderTest().anySatisfyWith(Predicates2.lessThan(), 0));
        Assert.assertFalse(this.classUnderTest().anySatisfyWith(Predicates2.greaterThanOrEqualTo(), 0));
    }

    @Override
    public void noneSatisfy()
    {
        Assert.assertTrue(this.classUnderTest().noneSatisfy(Predicates.lessThan(0)));
        Assert.assertTrue(this.classUnderTest().noneSatisfy(Predicates.greaterThanOrEqualTo(0)));
    }

    @Override
    public void noneSatisfyWith()
    {
        Assert.assertTrue(this.classUnderTest().noneSatisfyWith(Predicates2.lessThan(), 0));
        Assert.assertTrue(this.classUnderTest().noneSatisfyWith(Predicates2.greaterThanOrEqualTo(), 0));
    }

    @Override
    public void appendString_throws()
    {
        // Not applicable for empty collections
    }

    @Override
    public void detect()
    {
        Assert.assertNull(this.classUnderTest().detect(Integer.valueOf(0)::equals));
    }

    @Override
    public void detectIfNone()
    {
        Assert.assertEquals(Integer.valueOf(10), this.classUnderTest().detectIfNone(Integer.valueOf(0)::equals, () -> 10));
    }

    @Override
    public void detectWith()
    {
        Assert.assertNull(this.classUnderTest().detectWith(Object::equals, Integer.valueOf(0)));
    }

    @Override
    public void detectWithIfNone()
    {
        Function0<Integer> function = new PassThruFunction0<>(Integer.valueOf(1000));
        Assert.assertEquals(Integer.valueOf(1000), this.classUnderTest().detectWithIfNone(Object::equals, Integer.valueOf(0), function));
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void min()
    {
        this.classUnderTest().min(Integer::compareTo);
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void max()
    {
        this.classUnderTest().max(Integer::compareTo);
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void minBy()
    {
        this.classUnderTest().minBy(String::valueOf);
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void maxBy()
    {
        this.classUnderTest().maxBy(String::valueOf);
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void min_without_comparator()
    {
        this.classUnderTest().min();
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void max_without_comparator()
    {
        this.classUnderTest().max();
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void minWithEmptyBatch()
    {
        super.minWithEmptyBatch();
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void maxWithEmptyBatch()
    {
        super.minWithEmptyBatch();
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void min_null_throws()
    {
        this.classUnderTest().min(Integer::compareTo);
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void max_null_throws()
    {
        this.classUnderTest().max(Integer::compareTo);
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void minBy_null_throws()
    {
        this.classUnderTest().minBy(Integer::valueOf);
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void maxBy_null_throws()
    {
        this.classUnderTest().maxBy(Integer::valueOf);
    }
}
