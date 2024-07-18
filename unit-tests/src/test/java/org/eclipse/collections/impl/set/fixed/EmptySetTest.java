/*
 * Copyright (c) 2024 Goldman Sachs and others.
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

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class EmptySetTest extends AbstractMemoryEfficientMutableSetTestCase
{
    private EmptySet<Object> emptySet;

    @BeforeEach
    public void setUp()
    {
        this.emptySet = new EmptySet<>();
    }

    @Override
    protected MutableSet<String> classUnderTest()
    {
        return new EmptySet<>();
    }

    @Override
    protected MutableSet<String> classUnderTestWithNull()
    {
        throw new AssertionError();
    }

    @Test
    public void testEmpty()
    {
        assertTrue(this.emptySet.isEmpty());
        assertFalse(this.emptySet.notEmpty());
        assertTrue(Sets.fixedSize.of().isEmpty());
        assertFalse(Sets.fixedSize.of().notEmpty());
    }

    @Test
    public void testSize()
    {
        Verify.assertSize(0, this.emptySet);
    }

    @Test
    public void testContains()
    {
        assertFalse(this.emptySet.contains("Something"));
        assertFalse(this.emptySet.contains(null));
    }

    @Test
    public void testGetFirstLast()
    {
        assertNull(this.emptySet.getFirst());
        assertNull(this.emptySet.getLast());
    }

    @Test
    public void testReadResolve()
    {
        Verify.assertInstanceOf(EmptySet.class, Sets.fixedSize.of());
        Verify.assertPostSerializedIdentity(Sets.fixedSize.of());
    }

    @Override
    @Test
    public void testClone()
    {
        assertSame(Sets.fixedSize.of().clone(), Sets.fixedSize.of());
    }

    @Test
    public void testForEach()
    {
        this.emptySet.forEach(Procedures.cast(each -> fail()));
    }

    @Test
    public void testForEachWithIndex()
    {
        this.emptySet.forEachWithIndex((each, index) -> fail());
    }

    @Test
    public void testForEachWith()
    {
        this.emptySet.forEachWith((argument1, argument2) -> fail(), "param");
    }

    @Test
    public void testIterator()
    {
        Iterator<Object> it = this.emptySet.iterator();
        assertFalse(it.hasNext());

        assertThrows(NoSuchElementException.class, it::next);

        assertThrows(UnsupportedOperationException.class, it::remove);
    }

    @Test
    @Override
    public void groupBy()
    {
        MutableSetMultimap<Class<?>, String> multimap = this.classUnderTest().groupBy(Object::getClass);
        Verify.assertSize(this.classUnderTest().size(), multimap);
        assertTrue(multimap.keysView().isEmpty());
        assertEquals(this.classUnderTest(), multimap.get(String.class));
    }

    @Test
    @Override
    public void min()
    {
        assertThrows(NoSuchElementException.class, () -> this.classUnderTest().min(String::compareTo));
    }

    @Test
    @Override
    public void max()
    {
        assertThrows(NoSuchElementException.class, () -> this.classUnderTest().max(String::compareTo));
    }

    @Test
    @Override
    public void min_null_throws()
    {
        // Not applicable for empty collections
    }

    @Test
    @Override
    public void max_null_throws()
    {
        // Not applicable for empty collections
    }

    @Test
    @Override
    public void min_without_comparator()
    {
        assertThrows(NoSuchElementException.class, () -> this.classUnderTest().min());
    }

    @Test
    @Override
    public void max_without_comparator()
    {
        assertThrows(NoSuchElementException.class, () -> this.classUnderTest().max());
    }

    @Test
    @Override
    public void min_null_throws_without_comparator()
    {
        // Not applicable for empty collections
    }

    @Test
    @Override
    public void max_null_throws_without_comparator()
    {
        // Not applicable for empty collections
    }

    @Override
    @Test
    public void minBy()
    {
        assertThrows(NoSuchElementException.class, () -> this.classUnderTest().minBy(String::valueOf));
    }

    @Override
    @Test
    public void maxBy()
    {
        assertThrows(NoSuchElementException.class, () -> this.classUnderTest().maxBy(String::valueOf));
    }

    @Override
    @Test
    public void zip()
    {
        MutableSet<String> set = this.classUnderTest();
        List<Object> nulls = Collections.nCopies(set.size(), null);
        List<Object> nullsPlusOne = Collections.nCopies(set.size() + 1, null);

        MutableSet<Pair<String, Object>> pairs = set.zip(nulls);
        assertEquals(set, pairs.collect((Function<Pair<String, ?>, String>) Pair::getOne));
        assertEquals(nulls, pairs.collect((Function<Pair<?, Object>, Object>) Pair::getTwo, Lists.mutable.of()));

        MutableSet<Pair<String, Object>> pairsPlusOne = set.zip(nullsPlusOne);
        assertEquals(set, pairsPlusOne.collect((Function<Pair<String, ?>, String>) Pair::getOne));
        assertEquals(nulls, pairsPlusOne.collect((Function<Pair<?, Object>, Object>) Pair::getTwo, Lists.mutable.of()));

        assertEquals(
                set.zip(nulls),
                set.zip(nulls, UnifiedSet.newSet()));
    }

    @Override
    @Test
    public void zipWithIndex()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<Pair<String, Integer>> pairs = set.zipWithIndex();

        assertEquals(
                set,
                pairs.collect((Function<Pair<String, ?>, String>) Pair::getOne));
        assertEquals(
                UnifiedSet.newSet(),
                pairs.collect((Function<Pair<?, Integer>, Integer>) Pair::getTwo));

        assertEquals(
                set.zipWithIndex(),
                set.zipWithIndex(UnifiedSet.newSet()));
    }

    @Override
    @Test
    public void chunk_large_size()
    {
        assertEquals(Lists.mutable.of(), this.classUnderTest().chunk(10));
    }

    @Override
    @Test
    public void union()
    {
        assertEquals(
                UnifiedSet.newSetWith("a", "b", "c"),
                this.classUnderTest().union(UnifiedSet.newSetWith("a", "b", "c")));
    }

    @Override
    @Test
    public void unionInto()
    {
        assertEquals(
                UnifiedSet.newSetWith("a", "b", "c"),
                this.classUnderTest().unionInto(UnifiedSet.newSetWith("a", "b", "c"), UnifiedSet.newSet()));
    }

    @Override
    @Test
    public void intersect()
    {
        assertEquals(
                UnifiedSet.<String>newSet(),
                this.classUnderTest().intersect(UnifiedSet.newSetWith("1", "2", "3")));
    }

    @Override
    @Test
    public void intersectInto()
    {
        assertEquals(
                UnifiedSet.<String>newSet(),
                this.classUnderTest().intersectInto(UnifiedSet.newSetWith("1", "2", "3"), UnifiedSet.newSet()));
    }

    @Override
    @Test
    public void difference()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<String> difference = set.difference(UnifiedSet.newSetWith("2", "3", "4", "not present"));
        assertEquals(UnifiedSet.<String>newSet(), difference);
        assertEquals(set, set.difference(UnifiedSet.newSetWith("not present")));
    }

    @Override
    @Test
    public void differenceInto()
    {
        MutableSet<String> set = this.classUnderTest();
        MutableSet<String> difference = set.differenceInto(UnifiedSet.newSetWith("2", "3", "4", "not present"), UnifiedSet.newSet());
        assertEquals(UnifiedSet.<String>newSet(), difference);
        assertEquals(set, set.differenceInto(UnifiedSet.newSetWith("not present"), UnifiedSet.newSet()));
    }

    @Override
    @Test
    public void symmetricDifference()
    {
        assertEquals(
                UnifiedSet.newSetWith("not present"),
                this.classUnderTest().symmetricDifference(UnifiedSet.newSetWith("not present")));
    }

    @Override
    @Test
    public void symmetricDifferenceInto()
    {
        assertEquals(
                UnifiedSet.newSetWith("not present"),
                this.classUnderTest().symmetricDifferenceInto(UnifiedSet.newSetWith("not present"), UnifiedSet.newSet()));
    }

    @Test
    public void getOnly()
    {
        assertThrows(IllegalStateException.class, () -> this.emptySet.getOnly());
    }
}
