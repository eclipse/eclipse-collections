/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.UnsortedBag;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.UnsortedMapIterable;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.test.UnorderedIterableTestCase;
import org.eclipse.collections.test.bag.TransformsToBagTrait;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertThat;

public interface UnsortedMapIterableTestCase extends MapIterableTestCase, UnorderedIterableTestCase, TransformsToBagTrait
{
    @Override
    <T> UnsortedMapIterable<Object, T> newWith(T... elements);

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

    @Override
    @Test
    default void RichIterable_makeString_appendString()
    {
        RichIterable<Integer> iterable = this.newWith(2, 2, 1);
        assertThat(iterable.makeString(), isOneOf("2, 2, 1", "1, 2, 2", "2, 1, 2"));
        assertThat(iterable.makeString("/"), isOneOf("2/2/1", "1/2/2", "2/1/2"));
        assertThat(iterable.makeString("[", "/", "]"), isOneOf("[2/2/1]", "[1/2/2]", "[2/1/2]"));

        StringBuilder builder1 = new StringBuilder();
        iterable.appendString(builder1);
        assertThat(builder1.toString(), isOneOf("2, 2, 1", "1, 2, 2", "2, 1, 2"));

        StringBuilder builder2 = new StringBuilder();
        iterable.appendString(builder2, "/");
        assertThat(builder2.toString(), isOneOf("2/2/1", "1/2/2", "2/1/2"));

        StringBuilder builder3 = new StringBuilder();
        iterable.appendString(builder3, "[", "/", "]");
        assertThat(builder3.toString(), isOneOf("[2/2/1]", "[1/2/2]", "[2/1/2]"));
    }

    @Override
    @Test
    default void RichIterable_toString()
    {
        String string = this.newWith(2, 2, 1).toString();
        Pattern pattern = Pattern.compile("^\\{\\d\\.\\d+(E-\\d)?=(\\d),"
                + " \\d\\.\\d+(E-\\d)?=(\\d),"
                + " \\d\\.\\d+(E-\\d)?=(\\d)\\}$");
        Matcher matcher = pattern.matcher(string);
        Assert.assertTrue(string, matcher.matches());

        assertEquals(
                Bags.immutable.with("1", "2", "2"),
                Bags.immutable.with(
                        matcher.group(2),
                        matcher.group(4),
                        matcher.group(6)));
    }

    @Override
    @Test
    default void RichIterable_toList()
    {
        UnsortedMapIterable<Object, Integer> iterable = this.newWith(2, 2, 1);
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
