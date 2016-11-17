/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.set;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of {@link SynchronizedPutUnifiedSetMultimap}.
 */
public class SynchronizedPutUnifiedSetMultimapTest extends AbstractMutableSetMultimapTestCase
{
    @Override
    protected <K, V> SynchronizedPutUnifiedSetMultimap<K, V> newMultimap()
    {
        return SynchronizedPutUnifiedSetMultimap.newMultimap();
    }

    @Override
    protected <K, V> SynchronizedPutUnifiedSetMultimap<K, V> newMultimapWithKeyValue(K key, V value)
    {
        SynchronizedPutUnifiedSetMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key, value);
        return mutableMultimap;
    }

    @Override
    protected <K, V> SynchronizedPutUnifiedSetMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        SynchronizedPutUnifiedSetMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key1, value1);
        mutableMultimap.put(key2, value2);
        return mutableMultimap;
    }

    @Override
    protected <K, V> SynchronizedPutUnifiedSetMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3)
    {
        SynchronizedPutUnifiedSetMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key1, value1);
        mutableMultimap.put(key2, value2);
        mutableMultimap.put(key3, value3);
        return mutableMultimap;
    }

    @Override
    protected <K, V> SynchronizedPutUnifiedSetMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4)
    {
        SynchronizedPutUnifiedSetMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key1, value1);
        mutableMultimap.put(key2, value2);
        mutableMultimap.put(key3, value3);
        mutableMultimap.put(key4, value4);
        return mutableMultimap;
    }

    @SafeVarargs
    @Override
    protected final <K, V> SynchronizedPutUnifiedSetMultimap<K, V> newMultimap(Pair<K, V>... pairs)
    {
        return SynchronizedPutUnifiedSetMultimap.newMultimap(pairs);
    }

    @Override
    protected <K, V> SynchronizedPutUnifiedSetMultimap<K, V> newMultimapFromPairs(Iterable<Pair<K, V>> inputIterable)
    {
        return SynchronizedPutUnifiedSetMultimap.newMultimap(inputIterable);
    }

    @SafeVarargs
    @Override
    protected final <V> UnifiedSet<V> createCollection(V... args)
    {
        return UnifiedSet.newSetWith(args);
    }

    @Test
    @Override
    public void testToString()
    {
        super.testToString();

        MutableMultimap<String, Integer> multimap =
                this.newMultimapWithKeysValues("One", 1, "One", 2);
        String toString = multimap.toString();
        Assert.assertTrue("{One=[1, 2]}".equals(toString) || "{One=[2, 1]}".equals(toString));
    }
}
