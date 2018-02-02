/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.multimap.ImmutableMultimap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.UnmodifiableMutableSet;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.Iterate;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractImmutableMultimapTestCase
{
    protected abstract <K, V> ImmutableMultimap<K, V> classUnderTest();

    protected abstract MutableCollection<String> mutableCollection();

    @Test
    public void size()
    {
        Verify.assertEmpty(this.classUnderTest());
        ImmutableMultimap<String, String> one = this.<String, String>classUnderTest().newWith("1", "1");
        Verify.assertSize(1, one);
        ImmutableMultimap<String, String> two = one.newWith("2", "2");
        Verify.assertSize(2, two);
    }

    @Test
    public void allowDuplicates()
    {
        Verify.assertEmpty(this.classUnderTest());
        ImmutableMultimap<String, String> one = this.<String, String>classUnderTest().newWith("1", "1");
        Verify.assertSize(1, one);
        ImmutableMultimap<String, String> two = one.newWith("1", "1");
        Verify.assertSize(2, two);
    }

    @Test
    public void noDuplicates()
    {
        Verify.assertEmpty(this.classUnderTest());
        ImmutableMultimap<String, String> one = this.<String, String>classUnderTest().newWith("1", "1");
        Verify.assertSize(1, one);
        ImmutableMultimap<String, String> two = one.newWith("1", "1");
        Verify.assertSize(1, two);
    }

    @Test
    public void isEmpty()
    {
        ImmutableMultimap<String, String> empty = this.classUnderTest();
        Verify.assertEmpty(empty);
        Assert.assertTrue(empty.isEmpty());
        Assert.assertFalse(empty.notEmpty());

        ImmutableMultimap<String, String> notEmpty = empty.newWith("1", "1");
        Verify.assertNotEmpty(notEmpty);
        Assert.assertTrue(notEmpty.notEmpty());
        Assert.assertFalse(notEmpty.isEmpty());
    }

    @Test
    public void keySet()
    {
        ImmutableMultimap<String, Integer> multimap = this.<String, Integer>classUnderTest()
                .newWith("One", 1)
                .newWith("One", 1)
                .newWith("Two", 2)
                .newWith("Three", 3);
        Verify.assertInstanceOf(UnmodifiableMutableSet.class, multimap.keySet());
        Assert.assertEquals(Sets.mutable.of("One", "Two", "Three"), multimap.keySet());
    }

    @Test
    public void get()
    {
        ImmutableMultimap<String, String> empty = this.classUnderTest();
        RichIterable<String> emptyView = empty.get("");
        Verify.assertIterableEmpty(emptyView);

        ImmutableMultimap<String, String> notEmpty = empty.newWith("1", "1");
        RichIterable<String> notEmptyView = notEmpty.get("1");
        Verify.assertIterableNotEmpty(notEmptyView);
        Assert.assertEquals(FastList.newListWith("1"), notEmptyView.toList());
    }

    @Test
    public void toMap()
    {
        ImmutableMultimap<String, String> empty = this.classUnderTest();
        Assert.assertEquals(UnifiedMap.<String, RichIterable<String>>newMap(), empty.toMap());

        ImmutableMultimap<String, String> notEmpty = empty.newWith("1", "1");
        MutableCollection<String> strings = this.mutableCollection();
        strings.add("1");
        Assert.assertEquals(
                UnifiedMap.newWithKeysValues("1", (RichIterable<String>) strings),
                notEmpty.toMap());
    }

    @Test
    public void newWithout()
    {
        ImmutableMultimap<String, String> empty = this.classUnderTest();
        Verify.assertEmpty(empty.newWithout("1", "1"));

        ImmutableMultimap<String, String> notEmpty = empty.newWith("1", "1");
        Verify.assertNotEmpty(notEmpty);
        Verify.assertEmpty(notEmpty.newWithout("1", "1"));
    }

    @Test
    public void newWithAll_newWithoutAll()
    {
        ImmutableMultimap<String, String> empty = this.classUnderTest();
        Verify.assertEmpty(empty.newWithoutAll("1"));

        ImmutableMultimap<String, String> notEmpty = empty.newWith("1", "1").newWith("1", "2");
        Verify.assertNotEmpty(notEmpty);

        Assert.assertEquals(empty.newWithAll("1", FastList.newListWith("1", "2")), notEmpty);
        Verify.assertEmpty(notEmpty.newWithoutAll("1"));
    }

    @Test
    public void toImmutable()
    {
        ImmutableMultimap<String, String> empty = this.classUnderTest();
        Assert.assertSame(empty, empty.toImmutable());
    }

    @Test
    public void testSerialization()
    {
        ImmutableMultimap<String, String> original = this.<String, String>classUnderTest()
                .newWith("A", "A")
                .newWith("A", "B")
                .newWith("A", "B")
                .newWith("B", "A");
        ImmutableMultimap<String, String> copy = SerializeTestHelper.serializeDeserialize(original);
        Verify.assertEqualsAndHashCode(original, copy);
    }

    @Test
    public void selectKeysValues()
    {
        ImmutableMultimap<String, String> multimap = this.<String, String>classUnderTest().newWith("One", "1").newWith("Two", "2");
        ImmutableMultimap<String, String> selectedMultimap = multimap.selectKeysValues((key, value) -> "Two".equals(key) && "2".equals(value));
        Assert.assertEquals(this.classUnderTest().newWith("Two", "2"), selectedMultimap);
    }

    @Test
    public void rejectKeysValues()
    {
        ImmutableMultimap<String, String> multimap = this.<String, String>classUnderTest().newWith("One", "1").newWith("Two", "2");
        ImmutableMultimap<String, String> rejectedMultimap = multimap.rejectKeysValues((key, value) -> "Two".equals(key) && "2".equals(value));
        Assert.assertEquals(this.classUnderTest().newWith("One", "1"), rejectedMultimap);
    }

    @Test
    public void selectKeysMultiValues()
    {
        ImmutableMultimap<String, String> multimap1 = this.<String, String>classUnderTest().newWith("One", "1").newWith("Two", "2").newWith("Two", "3");
        ImmutableMultimap<String, String> selectedMultimap1 = multimap1.selectKeysMultiValues((key, values) -> "Two".equals(key) && Iterate.contains(values, "2"));
        Assert.assertEquals(this.classUnderTest().newWith("Two", "2").newWith("Two", "3"), selectedMultimap1);

        ImmutableMultimap<String, String> multimap2 = this.<String, String>classUnderTest().newWith("One", "1").newWith("Two", "3");
        ImmutableMultimap<String, String> selectedMultimap2 = multimap2.selectKeysMultiValues((key, values) -> "Two".equals(key) && Iterate.contains(values, "2"));
        Assert.assertEquals(this.classUnderTest(), selectedMultimap2);
    }

    @Test
    public void rejectKeysMultiValues()
    {
        ImmutableMultimap<String, String> multimap1 = this.<String, String>classUnderTest().newWith("One", "1").newWith("Two", "2").newWith("Two", "3");
        ImmutableMultimap<String, String> rejectedMultimap1 = multimap1.rejectKeysMultiValues((key, values) -> "Two".equals(key) && Iterate.contains(values, "2"));
        Assert.assertEquals(this.classUnderTest().newWith("One", "1"), rejectedMultimap1);

        ImmutableMultimap<String, String> multimap2 = this.<String, String>classUnderTest().newWith("One", "1").newWith("Two", "3");
        ImmutableMultimap<String, String> rejectedMultimap2 = multimap2.rejectKeysMultiValues((key, values) -> "Two".equals(key) && Iterate.contains(values, "2"));
        Assert.assertEquals(this.classUnderTest().newWith("One", "1"), rejectedMultimap2);
    }

    @Test
    public void collectKeysValues()
    {
        Multimap<String, String> multimap = this.<String, String>classUnderTest().newWith("One", "1").newWith("Two", "2");
        Multimap<String, String> collectedMultimap = multimap.collectKeysValues((argument1, argument2) -> Tuples.pair(argument1 + "Key", argument2 + "Value"));
        Assert.assertEquals(this.classUnderTest().newWith("OneKey", "1Value").newWith("TwoKey", "2Value"), collectedMultimap);
    }

    @Test
    public void collectValues()
    {
        Multimap<String, String> multimap = this.<String, String>classUnderTest().newWith("One", "1").newWith("Two", "2");
        Multimap<String, String> collectedMultimap = multimap.collectValues(value -> value + "Value");
        Assert.assertEquals(this.classUnderTest().newWith("One", "1Value").newWith("Two", "2Value"), collectedMultimap);
    }

    @Test
    public abstract void flip();
}
