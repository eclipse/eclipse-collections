/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bag;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.UnsortedBag;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.test.UnorderedIterableTestCase;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

public interface UnsortedBagTestCase extends UnorderedIterableTestCase, BagTestCase, TransformsToBagTrait
{
    @Override
    <T> UnsortedBag<T> newWith(T... elements);

    @Override
    default <T> UnsortedBag<T> getExpectedFiltered(T... elements)
    {
        return Bags.immutable.with(elements);
    }

    @Override
    default <T> MutableBag<T> newMutableForFilter(T... elements)
    {
        return Bags.mutable.with(elements);
    }

    @Test
    default void UnsortedBag_forEachWith()
    {
        UnsortedBag<Integer> bag = this.newWith(3, 3, 3, 2, 2, 1);
        MutableBag<Integer> result = Bags.mutable.with();
        Object sentinel = new Object();
        bag.forEachWith((argument1, argument2) -> {
            result.add(argument1);
            assertSame(sentinel, argument2);
        }, sentinel);
        assertEquals(Bags.immutable.with(3, 3, 3, 2, 2, 1), result);
    }

    @Override
    @Test
    default void RichIterable_makeString_appendString()
    {
        RichIterable<Integer> iterable = this.newWith(2, 2, 1);
        assertThat(iterable.makeString(), isOneOf("2, 2, 1", "1, 2, 2"));
        assertThat(iterable.makeString("/"), isOneOf("2/2/1", "1/2/2"));
        assertThat(iterable.makeString("[", "/", "]"), isOneOf("[2/2/1]", "[1/2/2]"));

        StringBuilder builder1 = new StringBuilder();
        iterable.appendString(builder1);
        assertThat(builder1.toString(), isOneOf("2, 2, 1", "1, 2, 2"));

        StringBuilder builder2 = new StringBuilder();
        iterable.appendString(builder2, "/");
        assertThat(builder2.toString(), isOneOf("2/2/1", "1/2/2"));

        StringBuilder builder3 = new StringBuilder();
        iterable.appendString(builder3, "[", "/", "]");
        assertThat(builder3.toString(), isOneOf("[2/2/1]", "[1/2/2]"));
    }

    @Override
    @Test
    default void RichIterable_toString()
    {
        assertThat(this.newWith(2, 2, 1).toString(), isOneOf("[2, 2, 1]", "[1, 2, 2]"));
    }

    @Override
    @Test
    default void RichIterable_toList()
    {
        UnsortedBag<Integer> iterable = this.newWith(2, 2, 1);
        assertThat(
                iterable.toList(),
                isOneOf(
                        Lists.immutable.with(2, 2, 1),
                        Lists.immutable.with(1, 2, 2),
                        Lists.immutable.with(2, 1, 2)));

        MutableList<Integer> target = Lists.mutable.empty();
        iterable.each(target::add);
        assertEquals(
                target,
                iterable.toList());
    }

    @Override
    @Test
    default void RichIterable_into()
    {
        assertThat(
                this.newWith(2, 2, 1).into(Lists.mutable.empty()),
                isOneOf(
                        Lists.immutable.with(2, 2, 1),
                        Lists.immutable.with(1, 2, 2),
                        Lists.immutable.with(2, 1, 2)));
    }
}
