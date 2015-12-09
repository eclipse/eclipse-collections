/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.lazy;

import java.util.List;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class CompositeIterableTest extends AbstractLazyIterableTestCase
{
    @Override
    protected <T> LazyIterable<T> newWith(T... elements)
    {
        return CompositeIterable.with(FastList.newListWith(elements));
    }

    @Override
    @Test
    public void iterator()
    {
        LazyIterable<Integer> select = Interval.oneTo(3).asLazy().concatenate(Interval.fromTo(4, 5));
        StringBuilder builder = new StringBuilder("");
        for (Integer each : select)
        {
            builder.append(each);
        }
        Assert.assertEquals("12345", builder.toString());
    }

    @Test
    public void emptyIterator()
    {
        LazyIterable<String> list = new CompositeIterable<>();
        Assert.assertFalse(list.iterator().hasNext());
    }

    @Test
    public void iteratorAll()
    {
        LazyIterable<Integer> iterables = CompositeIterable.with(Interval.oneTo(5), Interval.fromTo(6, 10));
        Verify.assertAllSatisfy(iterables, Predicates.greaterThan(0).and(Predicates.lessThan(11)));
    }

    @Test
    public void iteratorAny()
    {
        LazyIterable<Integer> iterables = CompositeIterable.with(Interval.oneTo(5), Interval.fromTo(6, 10));
        Verify.assertAnySatisfy(iterables, Predicates.greaterThan(0).and(Predicates.lessThan(11)));
    }

    @Test
    public void forEach()
    {
        MutableList<Integer> list = Lists.mutable.of();
        LazyIterable<Integer> iterables = CompositeIterable.with(Interval.oneTo(5), Interval.fromTo(6, 10));
        iterables.forEach(CollectionAddProcedure.on(list));
        Verify.assertSize(10, list);
        Verify.assertAllSatisfy(list, Predicates.greaterThan(0).and(Predicates.lessThan(11)));
    }

    @Test
    public void forEachWithIndex()
    {
        MutableList<Integer> list = Lists.mutable.of();
        LazyIterable<Integer> iterables = CompositeIterable.with(Interval.fromTo(6, 10), Interval.oneTo(5));
        iterables.forEachWithIndex((each, index) -> list.add(index, each));
        Verify.assertSize(10, list);
        Verify.assertAllSatisfy(list, Predicates.greaterThan(0).and(Predicates.lessThan(11)));
        Verify.assertStartsWith(list, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5);
    }

    @Test
    public void forEachWith()
    {
        MutableList<Integer> list = Lists.mutable.of();
        LazyIterable<Integer> iterables = CompositeIterable.with(Interval.fromTo(6, 10), Interval.oneTo(5));
        iterables.forEachWith((each, parameter) -> list.add(parameter.intValue(), each), 0);
        Verify.assertSize(10, list);
        Verify.assertAllSatisfy(list, Predicates.greaterThan(0).and(Predicates.lessThan(11)));
        Verify.assertStartsWith(list, 5, 4, 3, 2, 1, 10, 9, 8, 7, 6);
    }

    @Test
    public void ensureLazy()
    {
        CompositeIterable<Integer> iterables = new CompositeIterable<>();
        List<Integer> expected = Interval.oneTo(5);
        iterables.add(expected);
        iterables.add(() -> { throw new RuntimeException("Iterator should not be invoked eagerly"); });
        Assert.assertEquals(expected, iterables.take(expected.size()).toList());
    }

    @Override
    @Test
    public void distinct()
    {
        super.distinct();
        CompositeIterable<Integer> composite = new CompositeIterable<>();
        MutableList<Integer> expected = FastList.newListWith(3, 2, 2, 4, 1, 3, 1, 5);
        composite.add(expected);
        Assert.assertEquals(
                FastList.newListWith(3, 2, 4, 1, 5),
                composite.distinct().toList());
    }
}
