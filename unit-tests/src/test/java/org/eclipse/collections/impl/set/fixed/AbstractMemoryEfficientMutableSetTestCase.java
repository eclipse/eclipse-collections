/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.fixed;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.set.FixedSizeSet;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.UnsortedSetIterable;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.block.function.NegativeIntervalFunction;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap;
import org.eclipse.collections.impl.set.mutable.SynchronizedMutableSet;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.mutable.UnmodifiableMutableSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.utility.Iterate;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.mList;

/**
 * JUnit test for {@link AbstractMemoryEfficientMutableSet}.
 */
public abstract class AbstractMemoryEfficientMutableSetTestCase
{
    protected abstract MutableSet<String> classUnderTest();

    @Test
    public void asSynchronized()
    {
        Verify.assertInstanceOf(SynchronizedMutableSet.class, this.classUnderTest().asSynchronized());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void remove_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.remove("1");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addAll_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.addAll(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addAllIterable_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.addAllIterable(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void add_duplicate_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.add("1");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void add_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.add(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeAll_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.removeAll(mList("1", "2"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeAllIterable_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.removeAllIterable(mList("1", "2"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void retainAll_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.retainAll(mList("1", "2"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void retainAllIterable_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.retainAllIterable(mList("1", "2"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void clear_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeIf_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.removeIf(Predicates.equal("1"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeIfWith_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.removeIfWith(Object::equals, "1");
    }

    @Test
    public void iterator()
    {
        MutableSet<String> set = this.classUnderTest();
        int size = set.size();

        Iterator<String> iterator = set.iterator();
        for (int i = size; i-- > 0; )
        {
            String integerString = iterator.next();
            Assert.assertEquals(size, Integer.parseInt(integerString) + i);
        }

        Verify.assertThrows(NoSuchElementException.class, (Runnable) iterator::next);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void iteratorRemove_throws()
    {
        MutableSet<String> set = this.classUnderTest();
        set.iterator().remove();
    }

    @Test(expected = NullPointerException.class)
    public void min_null_throws()
    {
        this.classUnderTestWithNull().min(String::compareTo);
    }

    @Test(expected = NullPointerException.class)
    public void max_null_throws()
    {
        this.classUnderTestWithNull().max(String::compareTo);
    }

    @Test(expected = NullPointerException.class)
    public void min_null_throws_without_comparator()
    {
        this.classUnderTestWithNull().min();
    }

    @Test(expected = NullPointerException.class)
    public void max_null_throws_without_comparator()
    {
        this.classUnderTestWithNull().max();
    }

    protected abstract MutableSet<String> classUnderTestWithNull();

    @Test
    public void iterationWithIterator()
    {
        Iterator<String> iterator = this.classUnderTest().iterator();
        while (iterator.hasNext())
        {
            this.classUnderTest().contains(iterator.next());
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorWillGetUpsetIfYouPushItTooFar()
    {
        Iterator<String> iterator = this.classUnderTest().iterator();
        for (int i = 0; i < this.classUnderTest().size() + 1; i++)
        {
            iterator.next();
        }
    }

    @Test
    public void equalsAndHashCode()
    {
        Verify.assertEqualsAndHashCode(UnifiedSet.newSet(this.classUnderTest()), this.classUnderTest());
    }

    @Test
    public void groupBy()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSetMultimap<Boolean, String> multimap =
                set.groupBy(object -> IntegerPredicates.isOdd().accept(Integer.parseInt(object)));

        MutableMap<Boolean, RichIterable<String>> actualMap = multimap.toMap();
        int halfSize = this.classUnderTest().size() / 2;
        boolean odd = this.classUnderTest().size() % 2 != 0;
        Assert.assertEquals(halfSize, Iterate.sizeOf(actualMap.getIfAbsent(false, FastList::new)));
        Assert.assertEquals(halfSize + (odd ? 1 : 0), Iterate.sizeOf(actualMap.get(true)));
    }

    @Test
    public void groupByEach()
    {
        MutableSet<Integer> set = this.classUnderTest().collect(Integer::valueOf);

        MutableMultimap<Integer, Integer> expected = UnifiedSetMultimap.newMultimap();
        set.forEach(Procedures.cast(value -> expected.putAll(-value, Interval.fromTo(value, set.size()))));

        Multimap<Integer, Integer> actual =
                set.groupByEach(new NegativeIntervalFunction());
        Assert.assertEquals(expected, actual);

        Multimap<Integer, Integer> actualWithTarget =
                set.groupByEach(new NegativeIntervalFunction(), UnifiedSetMultimap.newMultimap());
        Assert.assertEquals(expected, actualWithTarget);
    }

    @Test
    public void zip()
    {
        MutableSet<String> set = this.classUnderTest();
        List<Object> nulls = Collections.nCopies(set.size(), null);
        List<Object> nullsPlusOne = Collections.nCopies(set.size() + 1, null);
        List<Object> nullsMinusOne = Collections.nCopies(set.size() - 1, null);

        MutableSet<Pair<String, Object>> pairs = set.zip(nulls);
        Assert.assertEquals(set, pairs.collect((Function<Pair<String, ?>, String>) Pair::getOne));
        Assert.assertEquals(nulls, pairs.collect((Function<Pair<?, Object>, Object>) Pair::getTwo, Lists.mutable.of()));

        MutableSet<Pair<String, Object>> pairsPlusOne = set.zip(nullsPlusOne);
        Assert.assertEquals(set, pairsPlusOne.collect((Function<Pair<String, ?>, String>) Pair::getOne));
        Assert.assertEquals(nulls, pairsPlusOne.collect((Function<Pair<?, Object>, Object>) Pair::getTwo, Lists.mutable.of()));

        MutableSet<Pair<String, Object>> pairsMinusOne = set.zip(nullsMinusOne);
        Assert.assertEquals(set.size() - 1, pairsMinusOne.size());
        Assert.assertTrue(set.containsAll(pairsMinusOne.collect((Function<Pair<String, ?>, String>) Pair::getOne)));

        Assert.assertEquals(
                set.zip(nulls),
                set.zip(nulls, UnifiedSet.newSet()));
    }

    @Test
    public void zipWithIndex()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<Pair<String, Integer>> pairs = set.zipWithIndex();

        Assert.assertEquals(
                set,
                pairs.collect((Function<Pair<String, ?>, String>) Pair::getOne));
        Assert.assertEquals(
                Interval.zeroTo(set.size() - 1).toSet(),
                pairs.collect((Function<Pair<?, Integer>, Integer>) Pair::getTwo));

        Assert.assertEquals(
                set.zipWithIndex(),
                set.zipWithIndex(UnifiedSet.newSet()));
    }

    @Test
    public void testClone()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<String> clone = set.clone();
        Assert.assertNotSame(clone, set);
        Verify.assertEqualsAndHashCode(clone, set);
    }

    @Test
    public void asUnmodifiable()
    {
        Verify.assertInstanceOf(UnmodifiableMutableSet.class, this.classUnderTest().asUnmodifiable());
    }

    @Test
    public void toImmutable()
    {
        Verify.assertInstanceOf(ImmutableSet.class, this.classUnderTest().toImmutable());
    }

    @Test
    public void min()
    {
        Assert.assertEquals("1", this.classUnderTest().min(String::compareTo));
    }

    @Test
    public void max()
    {
        Assert.assertEquals("1", this.classUnderTest().max(Comparators.reverse(String::compareTo)));
    }

    @Test
    public void min_without_comparator()
    {
        Assert.assertEquals("1", this.classUnderTest().min());
    }

    @Test
    public void max_without_comparator()
    {
        Assert.assertEquals(String.valueOf(this.classUnderTest().size()), this.classUnderTest().max());
    }

    @Test
    public void minBy()
    {
        Assert.assertEquals("1", this.classUnderTest().minBy(String::valueOf));
    }

    @Test
    public void maxBy()
    {
        Assert.assertEquals(String.valueOf(this.classUnderTest().size()), this.classUnderTest().maxBy(String::valueOf));
    }

    @Test
    public void chunk()
    {
        MutableSet<String> set = this.classUnderTest();
        RichIterable<RichIterable<String>> chunks = set.chunk(2);
        MutableList<Integer> sizes = chunks.collect(RichIterable::size, FastList.newList());
        MutableBag<Integer> hashBag = Bags.mutable.of();
        hashBag.addOccurrences(2, this.classUnderTest().size() / 2);
        if (this.classUnderTest().size() % 2 != 0)
        {
            hashBag.add(1);
        }
        Assert.assertEquals(hashBag, sizes.toBag());
    }

    @Test(expected = IllegalArgumentException.class)
    public void chunk_zero_throws()
    {
        this.classUnderTest().chunk(0);
    }

    @Test
    public void chunk_large_size()
    {
        MutableSet<String> set = this.classUnderTest();
        Assert.assertEquals(set, set.chunk(10).getFirst());
    }

    @Test
    public void union()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<String> union = set.union(UnifiedSet.newSetWith("a", "b", "c", "1"));
        Verify.assertSize(set.size() + 3, union);
        Assert.assertTrue(union.containsAllIterable(Interval.oneTo(set.size()).collect(String::valueOf)));
        Verify.assertContainsAll(union, "a", "b", "c");

        Assert.assertEquals(set, set.union(UnifiedSet.newSetWith("1")));
    }

    @Test
    public void unionInto()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<String> union = set.unionInto(UnifiedSet.newSetWith("a", "b", "c", "1"), UnifiedSet.newSet());
        Verify.assertSize(set.size() + 3, union);
        Assert.assertTrue(union.containsAllIterable(Interval.oneTo(set.size()).collect(String::valueOf)));
        Verify.assertContainsAll(union, "a", "b", "c");

        Assert.assertEquals(set, set.unionInto(UnifiedSet.newSetWith("1"), UnifiedSet.newSet()));
    }

    @Test
    public void intersect()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<String> intersect = set.intersect(UnifiedSet.newSetWith("a", "b", "c", "1"));
        Verify.assertSize(1, intersect);
        Assert.assertEquals(UnifiedSet.newSetWith("1"), intersect);

        Verify.assertEmpty(set.intersect(UnifiedSet.newSetWith("not present")));
    }

    @Test
    public void intersectInto()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<String> intersect = set.intersectInto(UnifiedSet.newSetWith("a", "b", "c", "1"), UnifiedSet.newSet());
        Verify.assertSize(1, intersect);
        Assert.assertEquals(UnifiedSet.newSetWith("1"), intersect);

        Verify.assertEmpty(set.intersectInto(UnifiedSet.newSetWith("not present"), UnifiedSet.newSet()));
    }

    @Test
    public void difference()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<String> difference = set.difference(UnifiedSet.newSetWith("2", "3", "4", "not present"));
        Assert.assertEquals(UnifiedSet.newSetWith("1"), difference);
        Assert.assertEquals(set, set.difference(UnifiedSet.newSetWith("not present")));
    }

    @Test
    public void differenceInto()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<String> difference = set.differenceInto(UnifiedSet.newSetWith("2", "3", "4", "not present"), UnifiedSet.newSet());
        Assert.assertEquals(UnifiedSet.newSetWith("1"), difference);
        Assert.assertEquals(set, set.differenceInto(UnifiedSet.newSetWith("not present"), UnifiedSet.newSet()));
    }

    @Test
    public void symmetricDifference()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<String> difference = set.symmetricDifference(UnifiedSet.newSetWith("2", "3", "4", "5", "not present"));
        Verify.assertContains("1", difference);
        Assert.assertTrue(difference.containsAllIterable(Interval.fromTo(set.size() + 1, 5).collect(String::valueOf)));
        for (int i = 2; i <= set.size(); i++)
        {
            Verify.assertNotContains(String.valueOf(i), difference);
        }

        Verify.assertSize(set.size() + 1, set.symmetricDifference(UnifiedSet.newSetWith("not present")));
    }

    @Test
    public void symmetricDifferenceInto()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<String> difference = set.symmetricDifferenceInto(
                UnifiedSet.newSetWith("2", "3", "4", "5", "not present"),
                UnifiedSet.newSet());
        Verify.assertContains("1", difference);
        Assert.assertTrue(difference.containsAllIterable(Interval.fromTo(set.size() + 1, 5).collect(String::valueOf)));
        for (int i = 2; i <= set.size(); i++)
        {
            Verify.assertNotContains(String.valueOf(i), difference);
        }

        Verify.assertSize(
                set.size() + 1,
                set.symmetricDifferenceInto(UnifiedSet.newSetWith("not present"), UnifiedSet.newSet()));
    }

    @Test
    public void isSubsetOf()
    {
        MutableSet<String> set = this.classUnderTest();
        Assert.assertTrue(set.isSubsetOf(UnifiedSet.newSetWith("1", "2", "3", "4", "5")));
    }

    @Test
    public void isProperSubsetOf()
    {
        MutableSet<String> set = this.classUnderTest();
        Assert.assertTrue(set.isProperSubsetOf(UnifiedSet.newSetWith("1", "2", "3", "4", "5")));
        Assert.assertFalse(set.isProperSubsetOf(set));
    }

    @Test
    public void powerSet()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<UnsortedSetIterable<String>> powerSet = set.powerSet();
        Verify.assertSize((int) StrictMath.pow(2, set.size()), powerSet);
        Verify.assertContains(UnifiedSet.<String>newSet(), powerSet);
        Verify.assertContains(set, powerSet);
    }

    @Test
    public void cartesianProduct()
    {
        MutableSet<String> set = this.classUnderTest();
        LazyIterable<Pair<String, String>> cartesianProduct = set.cartesianProduct(UnifiedSet.newSetWith("One", "Two"));
        Verify.assertIterableSize(set.size() * 2, cartesianProduct);
        Assert.assertEquals(
                set,
                cartesianProduct
                        .select(Predicates.attributeEqual((Function<Pair<?, String>, String>) Pair::getTwo, "One"))
                        .collect((Function<Pair<String, ?>, String>) Pair::getOne).toSet());
    }

    @Test
    public void with()
    {
        MutableSet<String> set = this.classUnderTest();
        Assert.assertFalse(set.contains("11"));
        MutableSet<String> setWith = set.with("11");
        Assert.assertTrue(setWith.containsAll(set));
        Assert.assertTrue(setWith.contains("11"));
        Assert.assertSame(setWith, setWith.with("11"));
        assertSetType(set, setWith);
    }

    @Test
    public void withAll()
    {
        MutableSet<String> set = this.classUnderTest();
        Verify.assertContainsNone(set, "11", "12");
        MutableSet<String> setWith = set.withAll(FastList.newListWith("11", "12"));
        Assert.assertTrue(setWith.containsAll(set));
        Verify.assertContainsAll(setWith, "11", "12");
        assertSetType(set, setWith);
        Assert.assertSame(setWith, setWith.withAll(FastList.newList()));
    }

    @Test
    public void without()
    {
        MutableSet<String> set = this.classUnderTest();
        Assert.assertSame(set, set.without("11"));
        MutableList<String> list = set.toList();
        list.forEach(Procedures.cast(each -> {
            MutableSet<String> setWithout = set.without(each);
            Assert.assertFalse(setWithout.contains(each));
            assertSetType(set, setWithout);
        }));
    }

    @Test
    public void withoutAll()
    {
        MutableSet<String> set = this.classUnderTest().with("11").with("12");
        MutableSet<String> setWithout = set.withoutAll(FastList.newListWith("11", "12"));
        Assert.assertTrue(setWithout.containsAll(this.classUnderTest()));
        Verify.assertContainsNone(setWithout, "11", "12");
        assertSetType(set, setWithout);
        Assert.assertSame(setWithout, setWithout.withoutAll(FastList.newList()));
    }

    protected static void assertSetType(MutableSet<?> original, MutableSet<?> modified)
    {
        if (original instanceof FixedSizeSet && modified.size() < 5)
        {
            Verify.assertInstanceOf(FixedSizeSet.class, modified);
        }
        else
        {
            Verify.assertInstanceOf(UnifiedSet.class, modified);
        }
    }
}
