/*
 * Copyright (c) 2017 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap;

import java.util.Set;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.Iterate;
import org.junit.Assert;
import org.junit.Test;

/**
 * Helper class for testing {@link Multimap}s.
 */
public abstract class AbstractMultimapTestCase
{
    protected abstract <K, V> Multimap<K, V> newMultimap();

    protected abstract <K, V> Multimap<K, V> newMultimapWithKeyValue(
            K key, V value);

    protected abstract <K, V> Multimap<K, V> newMultimapWithKeysValues(
            K key1, V value1, K
            key2, V value2);

    protected abstract <K, V> Multimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3);

    protected abstract <K, V> Multimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4);

    protected abstract <K, V> Multimap<K, V> newMultimap(Pair<K, V>... pairs);

    protected abstract <K, V> Multimap<K, V> newMultimapFromPairs(Iterable<Pair<K, V>> inputIterable);

    protected abstract <V> MutableCollection<V> createCollection(V... args);

    @Test
    public void testNewMultimap()
    {
        Multimap<Integer, Integer> multimap = this.newMultimap();
        Verify.assertEmpty(multimap);
        Verify.assertSize(0, multimap);
    }

    @Test
    public void testNewMultimapWithKeyValue()
    {
        Multimap<Integer, String> multimap = this.newMultimapWithKeyValue(1, "One");
        Verify.assertNotEmpty(multimap);
        Verify.assertSize(1, multimap);
        Verify.assertContainsEntry(1, "One", multimap);
    }

    @Test
    public void testNewMultimapWithWith()
    {
        Multimap<Integer, String> multimap = this.newMultimapWithKeysValues(1, "One", 2, "Two");
        Verify.assertNotEmpty(multimap);
        Verify.assertSize(2, multimap);
        Verify.assertContainsAllEntries(multimap, 1, "One", 2, "Two");
    }

    @Test
    public void testNewMultimapWithWithWith()
    {
        Multimap<Integer, String> multimap =
                this.newMultimapWithKeysValues(1, "One", 2, "Two", 3, "Three");
        Verify.assertNotEmpty(multimap);
        Verify.assertSize(3, multimap);
        Verify.assertContainsAllEntries(multimap, 1, "One", 2, "Two", 3, "Three");
    }

    @Test
    public void testNewMultimapWithWithWithWith()
    {
        Multimap<Integer, String> multimap =
                this.newMultimapWithKeysValues(1, "One", 2, "Two", 3, "Three", 4, "Four");
        Verify.assertNotEmpty(multimap);
        Verify.assertSize(4, multimap);
        Verify.assertContainsAllEntries(multimap, 1, "One", 2, "Two", 3, "Three", 4, "Four");
    }

    @Test
    public void testNewMultimapWith()
    {
        Pair<Integer, String> pair1 = Tuples.pair(1, "One");
        Pair<Integer, String> pair2 = Tuples.pair(2, "Two");
        Pair<Integer, String> pair3 = Tuples.pair(3, "Three");
        Pair<Integer, String> pair4 = Tuples.pair(4, "Four");
        ListIterable<Pair<Integer, String>> pairs = FastList.newListWith(pair1, pair2, pair3, pair4);

        Multimap<Integer, String> expected = this.newMultimap(pair1, pair2, pair3, pair4);

        Multimap<Integer, String> actual = this.newMultimapFromPairs(pairs);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void newMultimapFromPairs()
    {
        Multimap<Integer, String> expected =
                this.newMultimapWithKeysValues(1, "One", 2, "Two", 3, "Three", 4, "Four");
        Multimap<Integer, String> actual =
                this.newMultimap(Tuples.pair(1, "One"), Tuples.pair(2, "Two"), Tuples.pair(3, "Three"), Tuples.pair(4, "Four"));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isEmpty()
    {
        Verify.assertEmpty(this.newMultimap());
        Verify.assertNotEmpty(this.newMultimapWithKeyValue(1, 1));
        Assert.assertTrue(this.newMultimapWithKeyValue(1, 1).notEmpty());
    }

    @Test
    public void forEachKeyValue()
    {
        MutableBag<String> collection = Bags.mutable.of();
        Multimap<Integer, String> multimap =
                this.newMultimapWithKeysValues(1, "One", 2, "Two", 3, "Three");
        multimap.forEachKeyValue((key, value) -> collection.add(key + value));
        Assert.assertEquals(HashBag.newBagWith("1One", "2Two", "3Three"), collection);
    }

    @Test
    public void forEachKeyMultiValue()
    {
        MutableSet<Pair<Integer, Iterable<String>>> collection = UnifiedSet.newSet();
        Multimap<Integer, String> multimap =
                this.newMultimapWithKeysValues(2, "2", 2, "1", 3, "3", 3, "3");
        multimap.forEachKeyMultiValues((key, values) -> collection.add(Tuples.pair(key, values)));
        Assert.assertEquals(UnifiedSet.newSetWith(Tuples.pair(2, this.createCollection("2", "1")), Tuples.pair(3, this.createCollection("3", "3"))), collection);
    }

    @Test
    public void forEachValue()
    {
        MutableBag<String> collection = Bags.mutable.of();
        Multimap<Integer, String> multimap = this.newMultimapWithKeysValues(1, "1", 2, "2", 3, "3");
        multimap.forEachValue(CollectionAddProcedure.on(collection));
        Assert.assertEquals(HashBag.newBagWith("1", "2", "3"), collection);
    }

    @Test
    public void valuesView()
    {
        Multimap<Integer, String> multimap = this.newMultimapWithKeysValues(1, "1", 2, "2", 3, "3");
        Assert.assertEquals(Bags.mutable.of("1", "2", "3"), multimap.valuesView().toBag());
    }

    @Test
    public void multiValuesView()
    {
        Multimap<Integer, String> multimap = this.newMultimapWithKeysValues(1, "1", 2, "2", 3, "3");
        Assert.assertEquals(Bags.mutable.of("1", "2", "3"),
                multimap.multiValuesView().flatCollect(Functions.getPassThru()).toBag());
    }

    @Test
    public void forEachKey()
    {
        MutableList<Integer> collection = Lists.mutable.of();
        Multimap<Integer, String> multimap = this.newMultimapWithKeysValues(1, "1", 2, "2", 3, "3");
        multimap.forEachKey(CollectionAddProcedure.on(collection));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), collection);
    }

    @Test
    public void notEmpty()
    {
        Assert.assertTrue(this.newMultimap().isEmpty());
        Assert.assertFalse(this.newMultimap().notEmpty());
        Assert.assertTrue(this.newMultimapWithKeysValues(1, "1", 2, "2").notEmpty());
    }

    @Test
    public void keysWithMultiValuesView()
    {
        Multimap<Integer, String> multimap = this.newMultimapWithKeysValues(1, "1", 2, "2", 3, "3");
        Assert.assertEquals(Bags.mutable.of(1, 2, 3),
                multimap.keyMultiValuePairsView().collect(Pair::getOne).toBag());
        Assert.assertEquals(
                Bags.mutable.of("1", "2", "3"),
                multimap.keyMultiValuePairsView().flatCollect(Functions.secondOfPair()).toBag());
    }

    @Test
    public void keyValuePairsView()
    {
        Multimap<Integer, String> multimap = this.newMultimapWithKeysValues(1, "1", 2, "2", 3, "3");
        Assert.assertEquals(Bags.mutable.of(Tuples.pair(1, "1"), Tuples.pair(2, "2"), Tuples.pair(3, "3")),
                multimap.keyValuePairsView().toBag());
    }

    @Test
    public void keyBag()
    {
        Multimap<Integer, String> multimap = this.newMultimapWithKeysValues(1, "1", 2, "2", 2, "2.1");
        Assert.assertEquals(1, multimap.keyBag().occurrencesOf(1));
        Assert.assertEquals(2, multimap.keyBag().occurrencesOf(2));
    }

    @Test
    public void testEquals()
    {
        Multimap<Integer, String> map1 = this.newMultimapWithKeysValues(1, "1", 2, "2", 3, "3");
        Multimap<Integer, String> map2 = this.newMultimapWithKeysValues(1, "1", 2, "2", 3, "3");
        Multimap<Integer, String> map3 = this.newMultimapWithKeysValues(2, "2", 3, "3", 4, "4");
        Assert.assertEquals(map1, map2);
        Assert.assertNotEquals(map2, map3);
    }

    @Test
    public void testHashCode()
    {
        Multimap<Integer, String> map1 = this.newMultimapWithKeysValues(1, "1", 2, "2", 3, "3");
        Multimap<Integer, String> map2 = this.newMultimapWithKeysValues(1, "1", 2, "2", 3, "3");
        Verify.assertEqualsAndHashCode(map1, map2);
    }

    @Test
    public void serialization()
    {
        Multimap<Integer, String> original = this.newMultimapWithKeysValues(1, "1", 2, "2", 3, "3");
        Multimap<Integer, String> copy = SerializeTestHelper.serializeDeserialize(original);
        Verify.assertSize(3, copy);
        Verify.assertEqualsAndHashCode(original, copy);
    }

    @Test
    public void newEmpty()
    {
        Multimap<Object, Object> original = this.newMultimap();
        Multimap<Object, Object> newEmpty = original.newEmpty();
        Verify.assertEmpty(newEmpty);
        Assert.assertSame(original.getClass(), newEmpty.getClass());
        Verify.assertEqualsAndHashCode(original, newEmpty);
    }

    @Test
    public void keysView()
    {
        Multimap<String, Integer> multimap =
                this.newMultimapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        Assert.assertEquals(Bags.mutable.of("One", "Two", "Three"), multimap.keysView().toBag());
    }

    @Test
    public void keySet()
    {
        Multimap<String, Integer> multimap =
                this.newMultimapWithKeysValues("One", 1, "One", 1, "Two", 2, "Three", 3);
        Set<String> keySet = (Set<String>) multimap.keySet();
        Verify.assertThrows(UnsupportedOperationException.class, () -> keySet.add("Four"));
        Verify.assertThrows(UnsupportedOperationException.class, () -> keySet.remove("Four"));
        Assert.assertEquals(Sets.mutable.of("One", "Two", "Three"), keySet);
    }

    @Test
    public void sizeDistinct()
    {
        Multimap<String, Integer> multimap = this.newMultimapWithKeysValues("One", 1, "Two", 2, "Two", 3);
        Verify.assertSize(3, multimap);
        Assert.assertEquals(2, multimap.sizeDistinct());
    }

    @Test
    public void selectKeysValues()
    {
        Multimap<String, Integer> multimap = this.newMultimapWithKeysValues("One", 1, "One", 12, "Two", 2, "Two", 3);
        Multimap<String, Integer> selectedMultimap = multimap.selectKeysValues((key, value) -> "Two".equals(key) && (value % 2 == 0));
        Assert.assertEquals(this.newMultimapWithKeyValue("Two", 2), selectedMultimap);
    }

    @Test
    public void rejectKeysValues()
    {
        Multimap<String, Integer> multimap = this.newMultimapWithKeysValues("One", 1, "One", 12, "Two", 2, "Two", 4);
        Multimap<String, Integer> rejectedMultimap = multimap.rejectKeysValues((key, value) -> "Two".equals(key) || (value % 2 == 0));
        Assert.assertEquals(this.newMultimapWithKeyValue("One", 1), rejectedMultimap);
    }

    @Test
    public void selectKeysMultiValues()
    {
        Multimap<String, Integer> multimap = this.newMultimapWithKeysValues("One", 1, "One", 12, "Two", 2, "Two", 3);
        Multimap<String, Integer> selectedMultimap = multimap.selectKeysMultiValues((key, values) -> "Two".equals(key) && Iterate.contains(values, 2));
        Assert.assertEquals(this.newMultimapWithKeysValues("Two", 2, "Two", 3), selectedMultimap);
    }

    @Test
    public void rejectKeysMultiValues()
    {
        Multimap<String, Integer> multimap = this.newMultimapWithKeysValues("One", 1, "One", 12, "Two", 2, "Two", 3);
        Multimap<String, Integer> rejectedMultimap = multimap.rejectKeysMultiValues((key, values) -> "Two".equals(key) && Iterate.contains(values, 2));
        Assert.assertEquals(this.newMultimapWithKeysValues("One", 1, "One", 12), rejectedMultimap);
    }

    @Test
    public void collectKeysValues()
    {
        Multimap<String, Integer> multimap = this.newMultimapWithKeysValues("1", 1, "1", 12, "2", 2, "3", 3);
        Multimap<Integer, String> collectedMultimap = multimap.collectKeysValues((key, value) -> Tuples.pair(Integer.valueOf(key), value + "Value"));
        Multimap<Integer, String> expectedMultimap = this.newMultimapWithKeysValues(1, "1Value", 1, "12Value", 2, "2Value", 3, "3Value");
        Assert.assertEquals(expectedMultimap, collectedMultimap);
    }

    @Test
    public void collectValues()
    {
        Multimap<String, Integer> multimap = this.newMultimapWithKeysValues("1", 1, "1", 12, "2", 2, "3", 3);
        Multimap<String, String> collectedMultimap = multimap.collectValues(value -> value + "Value");
        Multimap<String, String> expectedMultimap = this.newMultimapWithKeysValues("1", "1Value", "1", "12Value", "2", "2Value", "3", "3Value");
        Assert.assertEquals(expectedMultimap, collectedMultimap);
    }

    @Test
    public abstract void flip();
}
