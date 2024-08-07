/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlatCollectIterableTest extends AbstractLazyIterableTestCase
{
    @Override
    protected <T> LazyIterable<T> newWith(T... elements)
    {
        return LazyIterate.flatCollect(FastList.newListWith(elements), FastList::newListWith);
    }

    @Test
    public void forEach()
    {
        LazyIterable<Integer> select = new FlatCollectIterable<>(Interval.oneTo(5), Interval::oneTo);
        Appendable builder = new StringBuilder();
        Procedure<Integer> appendProcedure = Procedures.append(builder);
        select.forEach(appendProcedure);
        assertEquals("112123123412345", builder.toString());
    }

    @Test
    public void forEachWithIndex()
    {
        LazyIterable<Integer> select = new FlatCollectIterable<>(Interval.oneTo(5), Interval::oneTo);
        StringBuilder builder = new StringBuilder();
        select.forEachWithIndex((object, index) -> {
            builder.append(object);
            builder.append(index);
        });
        assertEquals("10112213243516273849110211312413514", builder.toString());
    }

    @Override
    @Test
    public void iterator()
    {
        LazyIterable<Integer> select = new FlatCollectIterable<>(Interval.oneTo(5), Interval::oneTo);
        StringBuilder builder = new StringBuilder();
        for (Integer each : select)
        {
            builder.append(each);
        }
        assertEquals("112123123412345", builder.toString());
    }

    @Test
    public void forEachWith()
    {
        LazyIterable<Integer> select = new FlatCollectIterable<>(Interval.oneTo(5), Interval::oneTo);
        StringBuilder builder = new StringBuilder();
        select.forEachWith((each, aBuilder) -> aBuilder.append(each), builder);
        assertEquals("112123123412345", builder.toString());
    }

    @Override
    @Test
    public void distinct()
    {
        super.distinct();
        LazyIterable<Integer> iterable = new FlatCollectIterable<>(FastList.newListWith(3, 2, 2, 4, 1, 3, 1, 5), Interval::oneTo);
        assertEquals(
                FastList.newListWith(1, 2, 3, 4, 5),
                iterable.distinct().toList());
    }
}
