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

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of {@link UnifiedSetMultimap}.
 */
public class UnifiedSetMultimapTest extends AbstractMutableSetMultimapTestCase
{
    @Override
    protected <K, V> MutableSetMultimap<K, V> newMultimap()
    {
        return UnifiedSetMultimap.newMultimap();
    }

    @Override
    protected <K, V> MutableSetMultimap<K, V> newMultimapWithKeyValue(K key, V value)
    {
        MutableSetMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key, value);
        return mutableMultimap;
    }

    @Override
    protected <K, V> MutableSetMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        MutableSetMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key1, value1);
        mutableMultimap.put(key2, value2);
        return mutableMultimap;
    }

    @Override
    protected <K, V> MutableSetMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3)
    {
        MutableSetMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key1, value1);
        mutableMultimap.put(key2, value2);
        mutableMultimap.put(key3, value3);
        return mutableMultimap;
    }

    @Override
    protected <K, V> MutableSetMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4)
    {
        MutableSetMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key1, value1);
        mutableMultimap.put(key2, value2);
        mutableMultimap.put(key3, value3);
        mutableMultimap.put(key4, value4);
        return mutableMultimap;
    }

    @SafeVarargs
    @Override
    protected final <K, V> UnifiedSetMultimap<K, V> newMultimap(Pair<K, V>... pairs)
    {
        return UnifiedSetMultimap.newMultimap(pairs);
    }

    @Override
    protected <K, V> UnifiedSetMultimap<K, V> newMultimapFromPairs(Iterable<Pair<K, V>> inputIterable)
    {
        return UnifiedSetMultimap.newMultimap(inputIterable);
    }

    @SafeVarargs
    @Override
    protected final <V> UnifiedSet<V> createCollection(V... args)
    {
        return UnifiedSet.newSetWith(args);
    }

    @Test
    public void pairIterableConstructorTest()
    {
        Pair<Integer, String> pair1 = Tuples.pair(Integer.valueOf(1), "One");
        Pair<Integer, String> pair2 = Tuples.pair(Integer.valueOf(2), "Two");
        Pair<Integer, String> pair3 = Tuples.pair(Integer.valueOf(3), "Three");
        Pair<Integer, String> pair4 = Tuples.pair(Integer.valueOf(4), "Four");

        Pair<Integer, String> pair11 = Tuples.pair(Integer.valueOf(1), "OneOne");
        Pair<Integer, String> pair22 = Tuples.pair(Integer.valueOf(2), "TwoTwo");
        Pair<Integer, String> pair33 = Tuples.pair(Integer.valueOf(3), "ThreeThree");
        Pair<Integer, String> pair44 = Tuples.pair(Integer.valueOf(4), "FourFour");

        Pair<Integer, String> pair111 = Tuples.pair(Integer.valueOf(1), "One");
        Pair<Integer, String> pair222 = Tuples.pair(Integer.valueOf(2), "Two");
        Pair<Integer, String> pair333 = Tuples.pair(Integer.valueOf(3), "Three");
        Pair<Integer, String> pair444 = Tuples.pair(Integer.valueOf(4), "Four");

        MutableList<Pair<Integer, String>> testList = FastList.newListWith(pair1, pair2, pair3, pair4, pair11, pair22, pair33, pair44, pair111, pair222, pair333, pair444);

        UnifiedSetMultimap<Integer, String> actual = UnifiedSetMultimap.newMultimap(testList);

        Assert.assertEquals(FastList.newListWith(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4)), actual.keysView().toList());
        Assert.assertEquals(FastList.newListWith("OneOne", "One"), actual.get(Integer.valueOf(1)).toList());
        Assert.assertEquals(FastList.newListWith("TwoTwo", "Two"), actual.get(Integer.valueOf(2)).toList());
        Assert.assertEquals(FastList.newListWith("ThreeThree", "Three"), actual.get(Integer.valueOf(3)).toList());
        Assert.assertEquals(FastList.newListWith("FourFour", "Four"), actual.get(Integer.valueOf(4)).toList());
    }
}
