/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.set.sorted;

import java.util.Comparator;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.sortedset.MutableSortedSetMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test of {@link SynchronizedPutTreeSortedSetMultimap}.
 */
public class SynchronizedPutTreeSortedSetMultimapTest extends AbstractMutableSortedSetMultimapTestCase
{
    @Override
    protected <K, V> MutableSortedSetMultimap<K, V> newMultimap()
    {
        return SynchronizedPutTreeSortedSetMultimap.newMultimap(Comparators.reverseNaturalOrder());
    }

    @Override
    protected <K, V> MutableSortedSetMultimap<K, V> newMultimap(Comparator<? super V> comparator)
    {
        return SynchronizedPutTreeSortedSetMultimap.newMultimap(comparator);
    }

    @Override
    protected <K, V> MutableSortedSetMultimap<K, V> newMultimapWithKeyValue(K key, V value)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key, value);
    }

    @Override
    protected <K, V> MutableSortedSetMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2);
    }

    @Override
    protected <K, V> MutableSortedSetMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2)
                .withKeyValue(key3, value3);
    }

    @Override
    protected <K, V> MutableSortedSetMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2)
                .withKeyValue(key3, value3)
                .withKeyValue(key4, value4);
    }

    @SafeVarargs
    @Override
    protected final <K, V> MutableSortedSetMultimap<K, V> newMultimap(Pair<K, V>... pairs)
    {
        MutableSortedSetMultimap<K, V> result = this.newMultimap();
        for (Pair<K, V> pair : pairs)
        {
            result.add(pair);
        }
        return result;
    }

    @Override
    protected <K, V> MutableSortedSetMultimap<K, V> newMultimapFromPairs(Iterable<Pair<K, V>> inputIterable)
    {
        MutableSortedSetMultimap<K, V> result = this.newMultimap();
        for (Pair<K, V> pair : inputIterable)
        {
            result.add(pair);
        }
        return result;
    }

    @SafeVarargs
    @Override
    protected final <V> TreeSortedSet<V> createCollection(V... args)
    {
        return TreeSortedSet.newSetWith(Comparators.reverseNaturalOrder(), args);
    }

    @Test
    @Override
    public void testToString()
    {
        super.testToString();

        MutableMultimap<String, Integer> multimap =
                this.newMultimapWithKeysValues("One", 1, "One", 2);
        String toString = multimap.toString();
        assertTrue("{One=[1, 2]}".equals(toString) || "{One=[2, 1]}".equals(toString));
    }
}
