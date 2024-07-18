/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import java.util.NoSuchElementException;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.lazy.iterator.DistinctIterator;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DistinctIterableTest extends AbstractLazyIterableTestCase
{
    @Override
    protected <T> LazyIterable<T> newWith(T... elements)
    {
        return LazyIterate.distinct(FastList.newListWith(elements));
    }

    @Test
    public void forEach()
    {
        LazyIterable<Integer> distinct = new DistinctIterable<>(FastList.newListWith(3, 1, 2, 2, 3, 4));
        Appendable builder = new StringBuilder();
        Procedure<Integer> appendProcedure = Procedures.append(builder);
        distinct.forEach(appendProcedure);
        assertEquals("3124", builder.toString());
    }

    @Test
    public void forEachWithIndex()
    {
        LazyIterable<Integer> distinct = new DistinctIterable<>(FastList.newListWith(1, 2, 1, 3, 2, 4, 3, 5, 4, 6, 5, 7, 6, 8, 7, 9));
        StringBuilder builder = new StringBuilder();
        distinct.forEachWithIndex((object, index) -> {
            builder.append(object);
            builder.append(index);
        });
        assertEquals("102132435465768798", builder.toString());
    }

    @Override
    @Test
    public void iterator()
    {
        LazyIterable<Integer> distinct = new DistinctIterable<>(FastList.newListWith(3, 1, 2, 2, 3, 4, 2, 5));
        StringBuilder builder = new StringBuilder();
        for (Integer each : distinct)
        {
            builder.append(each);
        }
        assertEquals("31245", builder.toString());
    }

    @Test
    public void forEachWith()
    {
        LazyIterable<Integer> distinct = new DistinctIterable<>(FastList.newListWith(1, 3, 3, 2, 5, 4, 2, 5, 4));
        StringBuilder builder = new StringBuilder();
        distinct.forEachWith((each, aBuilder) -> aBuilder.append(each), builder);
        assertEquals("13254", builder.toString());
    }

    @Test
    public void noSuchElementException()
    {
        assertThrows(NoSuchElementException.class, () -> new DistinctIterator<>(Lists.mutable.<Integer>of()).next());
    }

    @Test
    public void remove()
    {
        assertThrows(UnsupportedOperationException.class, () -> new DistinctIterator<>(Lists.mutable.<Integer>of()).remove());
    }

    @Override
    @Test
    public void distinct()
    {
        super.distinct();
        LazyIterable<Integer> distinct = new DistinctIterable<>(FastList.newListWith(3, 2, 2, 4, 1, 3, 1, 5));
        LazyIterable<Integer> distinctDistinct = distinct.distinct();
        assertSame(distinctDistinct, distinct);
        assertEquals(
                FastList.newListWith(3, 2, 4, 1, 5),
                distinctDistinct.toList());
    }
}
