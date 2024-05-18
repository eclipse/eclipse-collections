/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.set;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.multimap.set.SetMultimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.multimap.AbstractMutableMultimapTestCase;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.Iterate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public abstract class AbstractMutableSetMultimapTestCase extends AbstractMutableMultimapTestCase
{
    @Override
    protected abstract <K, V> MutableSetMultimap<K, V> newMultimap();

    @Override
    protected abstract <K, V> MutableSetMultimap<K, V> newMultimapWithKeyValue(K key, V value);

    @Override
    protected abstract <K, V> MutableSetMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2);

    @Override
    protected abstract <K, V> MutableSetMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3);

    @Override
    protected abstract <K, V> MutableSetMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4);

    @Override
    protected abstract <K, V> MutableSetMultimap<K, V> newMultimap(Pair<K, V>... pairs);

    @Override
    protected abstract <K, V> MutableSetMultimap<K, V> newMultimapFromPairs(Iterable<Pair<K, V>> inputIterable);

    @Override
    protected abstract <V> MutableSet<V> createCollection(V... args);

    @Test
    public void forEachKeyMutableSet()
    {
        MutableSet<Pair<Integer, MutableSet<String>>> collection = UnifiedSet.newSet();
        MutableSetMultimap<Integer, String> multimap =
                this.newMultimapWithKeysValues(2, "2", 2, "1", 3, "3", 3, "3");
        multimap.forEachKeyMutableSet((key, values) -> collection.add(Tuples.pair(key, values)));
        MutableSet<Pair<Integer, MutableSet<String>>> expected = Sets.mutable.with(
                Tuples.pair(2, this.createCollection("2", "1")),
                Tuples.pair(3, this.createCollection("3", "3")));
        assertEquals(expected, collection);
    }

    @Test
    @Override
    public void putAllPairs()
    {
        super.putAllPairs();

        MutableMultimap<Integer, String> multimap = this.newMultimapWithKeysValues(1, "One", 2, "2");
        MutableList<Pair<Integer, String>> pairs = Lists.mutable.of(Tuples.pair(1, "One"));
        assertFalse(multimap.putAllPairs(pairs));
        MutableMultimap<Integer, String> expected = this.newMultimapWithKeysValues(1, "One", 2, "2");
        assertEquals(expected, multimap);
    }

    @Override
    @Test
    public void flip()
    {
        SetMultimap<String, Integer> multimap = this.newMultimapWithKeysValues("Less than 2", 1, "Less than 3", 1, "Less than 3", 2, "Less than 3", 2);
        SetMultimap<Integer, String> flipped = multimap.flip();
        assertEquals(Sets.immutable.with("Less than 3"), flipped.get(2));
        assertEquals(Sets.immutable.with("Less than 2", "Less than 3"), flipped.get(1));
    }

    @Override
    @Test
    public void selectKeysValues()
    {
        MutableSetMultimap<String, Integer> multimap = this.newMultimap();
        multimap.putAll("One", FastList.newListWith(1, 1, 2, 3, 4));
        multimap.putAll("Two", FastList.newListWith(2, 2, 3, 4, 5));
        MutableSetMultimap<String, Integer> selectedMultimap = multimap.selectKeysValues((key, value) -> "Two".equals(key) && (value % 2 == 0));
        MutableSetMultimap<String, Integer> expectedMultimap = UnifiedSetMultimap.newMultimap();
        expectedMultimap.putAll("Two", FastList.newListWith(2, 4));
        Verify.assertSetMultimapsEqual(expectedMultimap, selectedMultimap);
    }

    @Override
    @Test
    public void rejectKeysValues()
    {
        MutableSetMultimap<String, Integer> multimap = this.newMultimap();
        multimap.putAll("One", FastList.newListWith(1, 1, 2, 3, 4));
        multimap.putAll("Two", FastList.newListWith(2, 2, 3, 4, 5));
        MutableSetMultimap<String, Integer> rejectedMultimap = multimap.rejectKeysValues((key, value) -> "Two".equals(key) || (value % 2 == 0));
        MutableSetMultimap<String, Integer> expectedMultimap = UnifiedSetMultimap.newMultimap();
        expectedMultimap.putAll("One", FastList.newListWith(1, 3));
        Verify.assertSetMultimapsEqual(expectedMultimap, rejectedMultimap);
    }

    @Override
    @Test
    public void selectKeysMultiValues()
    {
        super.selectKeysMultiValues();

        MutableSetMultimap<Integer, String> multimap = this.newMultimap();
        multimap.putAll(1, FastList.newListWith("1", "3", "4"));
        multimap.putAll(2, FastList.newListWith("2", "3", "4", "5", "2"));
        multimap.putAll(3, FastList.newListWith("2", "3", "4", "5", "2"));
        multimap.putAll(4, FastList.newListWith("1", "3", "4"));
        MutableSetMultimap<Integer, String> selectedMultimap = multimap.selectKeysMultiValues((key, values) -> key % 2 == 0 && Iterate.sizeOf(values) > 3);
        MutableSetMultimap<Integer, String> expectedMultimap = UnifiedSetMultimap.newMultimap();
        expectedMultimap.putAll(2, FastList.newListWith("2", "3", "4", "5", "2"));
        Verify.assertSetMultimapsEqual(expectedMultimap, selectedMultimap);
    }

    @Override
    @Test
    public void rejectKeysMultiValues()
    {
        super.rejectKeysMultiValues();

        MutableSetMultimap<Integer, String> multimap = this.newMultimap();
        multimap.putAll(1, FastList.newListWith("1", "2", "3", "4", "5", "1"));
        multimap.putAll(2, FastList.newListWith("2", "3", "4", "5", "1"));
        multimap.putAll(3, FastList.newListWith("2", "3", "4", "2"));
        multimap.putAll(4, FastList.newListWith("1", "3", "4", "5"));
        MutableSetMultimap<Integer, String> rejectedMultimap = multimap.rejectKeysMultiValues((key, values) -> key % 2 == 0 || Iterate.sizeOf(values) > 4);
        MutableSetMultimap<Integer, String> expectedMultimap = UnifiedSetMultimap.newMultimap();
        expectedMultimap.putAll(3, FastList.newListWith("2", "3", "4", "2"));
        Verify.assertSetMultimapsEqual(expectedMultimap, rejectedMultimap);
    }

    @Override
    @Test
    public void collectKeysValues()
    {
        MutableSetMultimap<String, Integer> multimap = this.newMultimap();
        multimap.putAll("1", FastList.newListWith(1, 2, 3, 4));
        multimap.putAll("2", FastList.newListWith(2, 3, 4, 5));
        MutableBagMultimap<Integer, String> collectedMultimap1 = multimap.collectKeysValues((key, value) -> Tuples.pair(Integer.valueOf(key), value + "Value"));
        MutableBagMultimap<Integer, String> expectedMultimap1 = HashBagMultimap.newMultimap();
        expectedMultimap1.putAll(1, FastList.newListWith("1Value", "2Value", "3Value", "4Value"));
        expectedMultimap1.putAll(2, FastList.newListWith("2Value", "3Value", "4Value", "5Value"));
        Verify.assertBagMultimapsEqual(expectedMultimap1, collectedMultimap1);

        MutableBagMultimap<Integer, String> collectedMultimap2 = multimap.collectKeysValues((key, value) -> Tuples.pair(1, value + "Value"));
        MutableBagMultimap<Integer, String> expectedMultimap2 = HashBagMultimap.newMultimap();
        expectedMultimap2.putAll(1, FastList.newListWith("1Value", "2Value", "3Value", "4Value"));
        expectedMultimap2.putAll(1, FastList.newListWith("2Value", "3Value", "4Value", "5Value"));
        Verify.assertBagMultimapsEqual(expectedMultimap2, collectedMultimap2);
    }

    @Override
    @Test
    public void collectKeyMultiValues()
    {
        MutableSetMultimap<String, Integer> multimap1 = this.newMultimap();
        multimap1.putAll("1", FastList.newListWith(1, 2, 3, 4));
        multimap1.putAll("2", FastList.newListWith(2, 3, 4, 5));
        MutableBagMultimap<Integer, String> collectedMultimap1 = multimap1.collectKeyMultiValues(Integer::valueOf, value -> value + "Value");
        MutableBagMultimap<Integer, String> expectedMultimap1 = HashBagMultimap.newMultimap();
        expectedMultimap1.putAll(1, FastList.newListWith("1Value", "2Value", "3Value", "4Value"));
        expectedMultimap1.putAll(2, FastList.newListWith("2Value", "3Value", "4Value", "5Value"));
        Verify.assertBagMultimapsEqual(expectedMultimap1, collectedMultimap1);

        MutableBagMultimap<Integer, String> collectedMultimap2 = multimap1.collectKeyMultiValues(key -> 1, value -> value + "Value");

        MutableBagMultimap<Integer, String> expectedMultimap2 = HashBagMultimap.newMultimap();
        expectedMultimap2.putAll(1, FastList.newListWith("1Value", "2Value", "3Value", "4Value"));
        expectedMultimap2.putAll(1, FastList.newListWith("2Value", "3Value", "4Value", "5Value"));
        Verify.assertBagMultimapsEqual(expectedMultimap2, collectedMultimap2);

        Multimap<String, Integer> multimap2 = this.newMultimap(
                Tuples.pair("1", 1),
                Tuples.pair("1", 1),
                Tuples.pair("1", 12),
                Tuples.pair("2", 2),
                Tuples.pair("2", 2),
                Tuples.pair("3", 3));
        Multimap<Integer, Integer> collectedMultimap3 = multimap2.collectKeyMultiValues(
                key -> 1,
                value -> value % 2 == 0 ? value + 1 : value,
                Multimaps.mutable.set.empty());
        SetMultimap<Integer, Integer> expectedMultimap3 = Multimaps.mutable.set.with(1, 1, 1, 13, 1, 3);
        assertEquals(expectedMultimap3, collectedMultimap3);

        Multimap<Integer, Integer> collectedMultimap4 = multimap2.collectKeyMultiValues(
                key -> 1,
                value -> value % 2 == 0 ? value + 1 : value,
                Multimaps.mutable.list.empty());
        MutableListMultimap<Integer, Integer> expectedMultimap4 = Multimaps.mutable.list.with(1, 1, 1, 3, 1, 13);
        expectedMultimap4.put(1, 3);
        assertEquals(expectedMultimap4.keySet(), collectedMultimap4.keySet());
        assertEquals(expectedMultimap4.get(1).toBag(), collectedMultimap4.get(1).toBag());
    }

    @Override
    @Test
    public void collectValues()
    {
        MutableSetMultimap<String, Integer> multimap = this.newMultimap();
        multimap.putAll("1", FastList.newListWith(1, 2, 3, 4));
        multimap.putAll("2", FastList.newListWith(2, 3, 4, 5));
        MutableBagMultimap<String, String> collectedMultimap = multimap.collectValues(value -> value + "Value");
        MutableBagMultimap<String, String> expectedMultimap = HashBagMultimap.newMultimap();
        expectedMultimap.putAll("1", FastList.newListWith("1Value", "2Value", "3Value", "4Value"));
        expectedMultimap.putAll("2", FastList.newListWith("2Value", "3Value", "4Value", "5Value"));
        Verify.assertBagMultimapsEqual(expectedMultimap, collectedMultimap);
    }
}
