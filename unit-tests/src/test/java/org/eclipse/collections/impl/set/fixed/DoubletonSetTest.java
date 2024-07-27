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

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.block.factory.Procedures2;
import org.eclipse.collections.impl.block.function.NegativeIntervalFunction;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.eclipse.collections.impl.factory.Iterables.mSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * JUnit test for {@link DoubletonSet}.
 */
public class DoubletonSetTest extends AbstractMemoryEfficientMutableSetTestCase
{
    private DoubletonSet<String> set;

    @BeforeEach
    public void setUp()
    {
        this.set = new DoubletonSet<>("1", "2");
    }

    @Override
    protected MutableSet<String> classUnderTest()
    {
        return new DoubletonSet<>("1", "2");
    }

    @Override
    protected MutableSet<String> classUnderTestWithNull()
    {
        return new DoubletonSet<>(null, "2");
    }

    @Test
    public void nonUniqueWith()
    {
        Twin<String> twin1 = Tuples.twin("1", "1");
        Twin<String> twin2 = Tuples.twin("2", "2");
        DoubletonSet<Twin<String>> set = new DoubletonSet<>(twin1, twin2);

        Twin<String> twin3 = Tuples.twin("1", "1");
        set.with(twin3);
        assertSame(set.getFirst(), twin1);

        Twin<String> twin4 = Tuples.twin("2", "2");
        set.with(twin4);
        assertSame(set.getLast(), twin2);
    }

    @Override
    @Test
    public void equalsAndHashCode()
    {
        super.equalsAndHashCode();
        MutableSet<String> one = Sets.fixedSize.of("1", "2");
        Verify.assertEqualsAndHashCode(one, mSet("1", "2"));
        Verify.assertPostSerializedEqualsAndHashCode(one);
    }

    @Test
    public void remove()
    {
        try
        {
            this.set.remove("1");
            fail("Cannot remove from DoubletonSet");
        }
        catch (UnsupportedOperationException ignored)
        {
            this.assertUnchanged();
        }
    }

    @Test
    public void addDuplicate()
    {
        try
        {
            this.set.add("1");
            fail("Cannot add to DoubletonSet");
        }
        catch (UnsupportedOperationException ignored)
        {
            this.assertUnchanged();
        }
    }

    @Test
    public void add()
    {
        try
        {
            this.set.add("3");
            fail("Cannot add to DoubletonSet");
        }
        catch (UnsupportedOperationException ignored)
        {
            this.assertUnchanged();
        }
    }

    @Test
    public void addingAllToOtherSet()
    {
        MutableSet<String> newSet = UnifiedSet.newSet(Sets.fixedSize.of("1", "2"));
        newSet.add("3");
        Verify.assertContainsAll(newSet, "1", "2", "3");
    }

    private void assertUnchanged()
    {
        Verify.assertSize(2, this.set);
        Verify.assertContainsAll(this.set, "1", "2");
        Verify.assertNotContains("3", this.set);
    }

    @Override
    @Test
    public void testClone()
    {
        try
        {
            Verify.assertShallowClone(this.set);
        }
        catch (Exception e)
        {
            // Suppress if a Java 9 specific exception related to reflection is thrown.
            if (!e.getClass().getCanonicalName().equals("java.lang.reflect.InaccessibleObjectException"))
            {
                throw e;
            }
        }
        MutableSet<String> cloneSet = this.set.clone();
        assertNotSame(cloneSet, this.set);
        assertEquals(UnifiedSet.newSetWith("1", "2"), cloneSet);
    }

    @Test
    public void newEmpty()
    {
        MutableSet<String> newEmpty = this.set.newEmpty();
        Verify.assertInstanceOf(UnifiedSet.class, newEmpty);
        Verify.assertEmpty(newEmpty);
    }

    @Test
    public void getLast()
    {
        assertEquals("2", this.set.getLast());
    }

    @Test
    public void forEach()
    {
        MutableList<String> result = Lists.mutable.of();
        MutableSet<String> source = Sets.fixedSize.of("1", "2");
        source.forEach(CollectionAddProcedure.on(result));
        assertEquals(FastList.newListWith("1", "2"), result);
    }

    @Test
    public void forEachWithIndex()
    {
        int[] indexSum = new int[1];
        MutableList<String> result = Lists.mutable.of();
        MutableSet<String> source = Sets.fixedSize.of("1", "2");
        source.forEachWithIndex((each, index) ->
        {
            result.add(each);
            indexSum[0] += index;
        });
        assertEquals(FastList.newListWith("1", "2"), result);
        assertEquals(1, indexSum[0]);
    }

    @Test
    public void forEachWith()
    {
        MutableList<String> result = Lists.mutable.of();
        MutableSet<String> source = Sets.fixedSize.of("1", "2");
        source.forEachWith(Procedures2.fromProcedure(CollectionAddProcedure.on(result)), null);
        assertEquals(FastList.newListWith("1", "2"), result);
    }

    @Test
    public void getFirstGetLast()
    {
        MutableSet<String> source = Sets.fixedSize.of("1", "2");
        assertEquals("1", source.getFirst());
        assertEquals("2", source.getLast());
    }

    @Override
    @Test
    public void groupByEach()
    {
        super.groupByEach();

        MutableSet<Integer> set = Sets.fixedSize.of(1, 2);
        MutableMultimap<Integer, Integer> expected = UnifiedSetMultimap.newMultimap();
        set.forEach(Procedures.cast(value -> expected.putAll(-value, Interval.fromTo(value, set.size()))));
        Multimap<Integer, Integer> actual =
                set.groupByEach(new NegativeIntervalFunction());
        assertEquals(expected, actual);

        Multimap<Integer, Integer> actualWithTarget =
                set.groupByEach(new NegativeIntervalFunction(), UnifiedSetMultimap.newMultimap());
        assertEquals(expected, actualWithTarget);
    }

    @Test
    public void getOnly()
    {
        assertThrows(IllegalStateException.class, () -> this.set.getOnly());
    }
}
