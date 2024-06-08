/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.list.mutable;

import java.util.List;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.eclipse.collections.test.IterableTestCase;
import org.eclipse.collections.test.NoIteratorTestCase;
import org.junit.runner.RunWith;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;

@RunWith(Java8Runner.class)
public class FastListNoIteratorTest implements MutableListTestCase, NoIteratorTestCase
{
    @SafeVarargs
    @Override
    public final <T> MutableList<T> newWith(T... elements)
    {
        MutableList<T> result = new FastListNoIterator<>();
        IterableTestCase.addAllTo(elements, result);
        return result;
    }

    @Override
    public void Iterable_remove()
    {
        NoIteratorTestCase.super.Iterable_remove();
    }

    @Override
    public void List_subList_subList_iterator_add_remove()
    {
        // Not applicable
    }

    @Override
    public void List_subList_subList_addAll()
    {
        List<String> list = this.newWith("A", "B", "C", "D");
        List<String> sublist = list.subList(0, 3);
        List<String> sublist2 = sublist.subList(0, 2);
        assertEquals(Lists.immutable.with("A", "B", "C"), sublist);
        assertEquals(Lists.immutable.with("A", "B"), sublist2);

        sublist2.addAll(Lists.mutable.of("D", "E"));
        assertEquals(Lists.immutable.with("A", "B", "D", "E", "C"), sublist);
        assertEquals(Lists.immutable.with("A", "B", "D", "E"), sublist2);

        // TODO: Support FastList.subList().clear() without delegating to iterator()
    }

    @Override
    public void OrderedIterable_next()
    {
        // Not applicable
    }
}
