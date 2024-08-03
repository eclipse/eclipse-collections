/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bimap;

import java.util.Iterator;

import org.eclipse.collections.api.bimap.BiMap;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.test.bag.TransformsToBagTrait;
import org.eclipse.collections.test.set.UnsortedSetLikeTestTrait;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertIterablesEqual;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isOneOf;

public interface UnsortedBiMapTestCase extends BiMapTestCase, TransformsToBagTrait, UnsortedSetLikeTestTrait
{
    @Override
    <T> BiMap<Object, T> newWith(T... elements);

    @Override
    default void Iterable_toString()
    {
        BiMap<String, Integer> bimap = this.newWithKeysValues("Two", 2, "One", 1);

        assertThat(bimap.toString(), isOneOf("{One=1, Two=2}", "{Two=2, One=1}"));
        assertThat(bimap.keysView().toString(), isOneOf("[One, Two]", "[Two, One]"));
        assertThat(bimap.valuesView().toString(), isOneOf("[1, 2]", "[2, 1]"));
        assertThat(bimap.keyValuesView().toString(), isOneOf("[One:1, Two:2]", "[Two:2, One:1]"));
        assertThat(bimap.asLazy().toString(), isOneOf("[1, 2]", "[2, 1]"));
    }

    @Test
    @Override
    default void Iterable_remove()
    {
        BiMap<Object, Integer> iterable = this.newWith(3, 2, 1);
        Iterator<Integer> iterator = iterable.iterator();
        iterator.next();
        iterator.remove();
        assertIterablesEqual(2, iterable.size());
        MutableSet<Integer> valuesSet = iterable.inverse().keysView().toSet();
        assertThat(
                valuesSet,
                isOneOf(
                        Sets.immutable.with(3, 2),
                        Sets.immutable.with(3, 1),
                        Sets.immutable.with(2, 1)));
    }
}
