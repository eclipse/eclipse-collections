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
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.block.factory.Procedures2;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * JUnit test for {@link TripletonSet}.
 */
public class TripletonSetTest extends AbstractMemoryEfficientMutableSetTestCase
{
    private TripletonSet<String> set;

    @BeforeEach
    public void setUp()
    {
        this.set = new TripletonSet<>("1", "2", "3");
    }

    @Override
    protected MutableSet<String> classUnderTest()
    {
        return new TripletonSet<>("1", "2", "3");
    }

    @Override
    protected MutableSet<String> classUnderTestWithNull()
    {
        return new TripletonSet<>(null, "2", "3");
    }

    @Test
    public void nonUniqueWith()
    {
        Twin<String> twin1 = Tuples.twin("1", "1");
        Twin<String> twin2 = Tuples.twin("2", "2");
        Twin<String> twin3 = Tuples.twin("3", "3");
        TripletonSet<Twin<String>> set = new TripletonSet<>(twin1, twin2, twin3);

        set.with(Tuples.twin("1", "1"));
        set.with(Tuples.twin("2", "2"));
        set.with(Tuples.twin("3", "3"));

        assertSame(set.getFirst(), twin1);
        assertSame(set.getSecond(), twin2);
        assertSame(set.getLast(), twin3);
    }

    @Override
    @Test
    public void equalsAndHashCode()
    {
        super.equalsAndHashCode();
        MutableSet<String> one = Sets.fixedSize.of("1", "2", "3");
        MutableSet<String> oneA = UnifiedSet.newSetWith("1", "2", "3");
        Verify.assertEqualsAndHashCode(one, oneA);
        Verify.assertPostSerializedEqualsAndHashCode(one);
    }

    @Test
    public void contains()
    {
        Verify.assertContainsAll(this.set, "1", "2", "3");
        Verify.assertNotContains("4", this.set);
    }

    @Test
    public void remove()
    {
        try
        {
            this.set.remove("1");
            fail("Cannot remove from TripletonSet");
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
            fail("Cannot add to TripletonSet");
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
            this.set.add("4");
            fail("Cannot add to TripletonSet");
        }
        catch (UnsupportedOperationException ignored)
        {
            this.assertUnchanged();
        }
    }

    @Test
    public void addingAllToOtherSet()
    {
        MutableSet<String> newSet = UnifiedSet.newSet(Sets.fixedSize.of("1", "2", "3"));
        newSet.add("4");
        Verify.assertContainsAll(newSet, "1", "2", "3", "4");
    }

    private void assertUnchanged()
    {
        Verify.assertEqualsAndHashCode(UnifiedSet.newSetWith("1", "2", "3"), this.set);
    }

    @Test
    public void serializable()
    {
        MutableSet<String> copyOfSet = SerializeTestHelper.serializeDeserialize(this.set);
        Verify.assertSetsEqual(this.set, copyOfSet);
        assertNotSame(this.set, copyOfSet);
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
        assertEquals("3", this.set.getLast());
    }

    @Test
    public void forEach()
    {
        MutableList<String> result = Lists.mutable.of();
        MutableSet<String> source = Sets.fixedSize.of("1", "2", "3");
        source.forEach(CollectionAddProcedure.on(result));
        assertEquals(FastList.newListWith("1", "2", "3"), result);
    }

    @Test
    public void forEachWithIndex()
    {
        int[] indexSum = new int[1];
        MutableList<String> result = Lists.mutable.of();
        MutableSet<String> source = Sets.fixedSize.of("1", "2", "3");
        source.forEachWithIndex((each, index) ->
        {
            result.add(each);
            indexSum[0] += index;
        });
        assertEquals(FastList.newListWith("1", "2", "3"), result);
        assertEquals(3, indexSum[0]);
    }

    @Test
    public void forEachWith()
    {
        MutableList<String> result = Lists.mutable.of();
        MutableSet<String> source = Sets.fixedSize.of("1", "2", "3");
        source.forEachWith(Procedures2.fromProcedure(CollectionAddProcedure.on(result)), null);
        assertEquals(FastList.newListWith("1", "2", "3"), result);
    }

    @Test
    public void getFirstGetLast()
    {
        MutableSet<String> source = Sets.fixedSize.of("1", "2", "3");
        assertEquals("1", source.getFirst());
        assertEquals("3", source.getLast());
    }

    @Test
    public void getOnly()
    {
        assertThrows(IllegalStateException.class, () -> this.set.getOnly());
    }
}
