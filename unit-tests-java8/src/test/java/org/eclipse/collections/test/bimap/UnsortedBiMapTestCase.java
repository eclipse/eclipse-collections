/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bimap;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.collections.api.bimap.BiMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.test.bag.TransformsToBagTrait;
import org.eclipse.collections.test.set.UnsortedSetLikeTestTrait;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertThat;

public interface UnsortedBiMapTestCase extends BiMapTestCase, TransformsToBagTrait, UnsortedSetLikeTestTrait
{
    @Override
    <T> BiMap<Object, T> newWith(T... elements);

    @Test
    @Override
    default void Iterable_remove()
    {
        BiMap<Object, Integer> iterable = this.newWith(3, 2, 1);
        Iterator<Integer> iterator = iterable.iterator();
        iterator.next();
        iterator.remove();
        assertEquals(2, iterable.size());
        MutableSet<Integer> valuesSet = iterable.inverse().keysView().toSet();
        assertThat(
                valuesSet,
                isOneOf(
                        Sets.immutable.with(3, 2),
                        Sets.immutable.with(3, 1),
                        Sets.immutable.with(2, 1)));
    }

    @Override
    @Test
    default void RichIterable_toString()
    {
        String string = this.newWith(3, 2, 1).toString();
        Pattern pattern = Pattern.compile("^\\{\\d\\.\\d+(E-\\d)?=(\\d),"
                + " \\d\\.\\d+(E-\\d)?=(\\d),"
                + " \\d\\.\\d+(E-\\d)?=(\\d)\\}$");
        Matcher matcher = pattern.matcher(string);
        Assert.assertTrue(string, matcher.matches());

        assertEquals(
                Bags.immutable.with("1", "2", "3"),
                Bags.immutable.with(
                        matcher.group(2),
                        matcher.group(4),
                        matcher.group(6)));
    }
}
