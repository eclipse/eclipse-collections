/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.bag;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.bag.mutable.MultiReaderHashBag;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of {@link MultiReaderHashBagMultimap}.
 */
public class MultiReaderHashBagMultimapTest extends AbstractMutableBagMultimapTestCase
{
    @Override
    public <K, V> MultiReaderHashBagMultimap<K, V> newMultimap()
    {
        return MultiReaderHashBagMultimap.newMultimap();
    }

    @Override
    public <K, V> MultiReaderHashBagMultimap<K, V> newMultimapWithKeyValue(K key, V value)
    {
        MultiReaderHashBagMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key, value);
        return mutableMultimap;
    }

    @Override
    public <K, V> MultiReaderHashBagMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        MultiReaderHashBagMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key1, value1);
        mutableMultimap.put(key2, value2);
        return mutableMultimap;
    }

    @Override
    public <K, V> MultiReaderHashBagMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3)
    {
        MultiReaderHashBagMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key1, value1);
        mutableMultimap.put(key2, value2);
        mutableMultimap.put(key3, value3);
        return mutableMultimap;
    }

    @Override
    public <K, V> MultiReaderHashBagMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4)
    {
        MultiReaderHashBagMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key1, value1);
        mutableMultimap.put(key2, value2);
        mutableMultimap.put(key3, value3);
        mutableMultimap.put(key4, value4);
        return mutableMultimap;
    }

    @SafeVarargs
    @Override
    public final <K, V> MultiReaderHashBagMultimap<K, V> newMultimap(Pair<K, V>... pairs)
    {
        return MultiReaderHashBagMultimap.newMultimap(pairs);
    }

    @Override
    public <K, V> MultiReaderHashBagMultimap<K, V> newMultimapFromPairs(Iterable<Pair<K, V>> inputIterable)
    {
        return MultiReaderHashBagMultimap.newMultimap(inputIterable);
    }

    @SafeVarargs
    @Override
    protected final <V> MultiReaderHashBag<V> createCollection(V... args)
    {
        return MultiReaderHashBag.newBagWith(args);
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

        MutableBag<Pair<Integer, String>> testBag = HashBag.newBagWith(pair1, pair2, pair3, pair4, pair11, pair22, pair33, pair44, pair111, pair222, pair333, pair444);

        MultiReaderHashBagMultimap<Integer, String> actual = MultiReaderHashBagMultimap.newMultimap(testBag);

        Assert.assertEquals(HashBag.newBagWith(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4)), actual.keysView().toBag());
        Assert.assertEquals(HashBag.newBagWith("One", "OneOne", "One"), actual.get(Integer.valueOf(1)));
        Assert.assertEquals(HashBag.newBagWith("Two", "TwoTwo", "Two"), actual.get(Integer.valueOf(2)));
        Assert.assertEquals(HashBag.newBagWith("Three", "ThreeThree", "Three"), actual.get(Integer.valueOf(3)));
        Assert.assertEquals(HashBag.newBagWith("Four", "FourFour", "Four"), actual.get(Integer.valueOf(4)));
    }
}
