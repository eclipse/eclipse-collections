/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map.mutable;

import java.util.Iterator;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.test.MutableUnorderedIterableTestCase;
import org.eclipse.collections.test.map.UnsortedMapIterableTestCase;

import static org.eclipse.collections.test.IterableTestCase.assertIterablesEqual;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isOneOf;

public interface MutableMapTestCase extends UnsortedMapIterableTestCase, MutableUnorderedIterableTestCase, MutableMapIterableTestCase
{
    @Override
    <T> MutableMap<Object, T> newWith(T... elements);

    @Override
    <K, V> MutableMap<K, V> newWithKeysValues(Object... elements);

    @Override
    default void Iterable_toString()
    {
        UnsortedMapIterableTestCase.super.Iterable_toString();

        MutableMap<String, Integer> map = this.newWithKeysValues("Two", 2, "One", 1);
        assertThat(map.keySet().toString(), isOneOf("[One, Two]", "[Two, One]"));
        assertThat(map.values().toString(), isOneOf("[1, 2]", "[2, 1]"));
        assertThat(map.entrySet().toString(), isOneOf("[One=1, Two=2]", "[Two=2, One=1]"));
    }

    @Override
    default void Iterable_remove()
    {
        MutableMap<Object, Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);
        Iterator<Integer> iterator = iterable.iterator();
        iterator.next();
        iterator.remove();
        assertIterablesEqual(this.allowsDuplicates() ? 5 : 2, Iterate.sizeOf(iterable));
        assertThat(iterable.toBag(), isOneOf(
                this.getExpectedFiltered(3, 3, 3, 2, 2),
                this.getExpectedFiltered(3, 3, 3, 2, 1),
                this.getExpectedFiltered(3, 3, 2, 2, 1)));
    }
}
