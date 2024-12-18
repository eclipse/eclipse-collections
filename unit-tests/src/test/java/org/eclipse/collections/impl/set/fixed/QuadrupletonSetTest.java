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

public class QuadrupletonSetTest extends AbstractMemoryEfficientMutableSetTestCase
{
    private QuadrupletonSet<String> set;

    @BeforeEach
    public void setUp()
    {
        this.set = new QuadrupletonSet<>("1", "2", "3", "4");
    }

    @Override
    protected MutableSet<String> classUnderTest()
    {
        return new QuadrupletonSet<>("1", "2", "3", "4");
    }

    @Override
    protected MutableSet<String> classUnderTestWithNull()
    {
        return new QuadrupletonSet<>(null, "2", "3", "4");
    }

    @Test
    public void nonUniqueWith()
    {
        Twin<String> twin1 = Tuples.twin("1", "1");
        Twin<String> twin2 = Tuples.twin("2", "2");
        Twin<String> twin3 = Tuples.twin("3", "3");
        Twin<String> twin4 = Tuples.twin("4", "4");
        QuadrupletonSet<Twin<String>> set = new QuadrupletonSet<>(twin1, twin2, twin3, twin4);

        set.with(Tuples.twin("1", "1"));
        set.with(Tuples.twin("2", "2"));
        set.with(Tuples.twin("3", "3"));
        set.with(Tuples.twin("4", "4"));

        assertSame(set.getFirst(), twin1);
        assertSame(set.getSecond(), twin2);
        assertSame(set.getThird(), twin3);
        assertSame(set.getLast(), twin4);
    }

    @Test
    public void contains()
    {
        Verify.assertContains("1", this.set);
        Verify.assertContainsAll(this.set, "2", "3", "4");
        Verify.assertNotContains("5", this.set);
    }

    @Test
    public void remove()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.set.remove("1"));
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

    @Override
    @Test
    public void equalsAndHashCode()
    {
        super.equalsAndHashCode();
        MutableSet<String> quadrupletonSet = Sets.fixedSize.of("1", "2", "3", "4");
        MutableSet<String> set = UnifiedSet.newSetWith("1", "2", "3", "4");
        Verify.assertEqualsAndHashCode(quadrupletonSet, set);
        Verify.assertPostSerializedEqualsAndHashCode(quadrupletonSet);
    }

    @Test
    public void addingAllToOtherSet()
    {
        MutableSet<String> newSet = UnifiedSet.newSet(Sets.fixedSize.of("1", "2", "3", "4"));
        newSet.add("5");
        Verify.assertContainsAll(newSet, "1", "2", "3", "4", "5");
    }

    private void assertUnchanged()
    {
        Verify.assertSize(4, this.set);
        Verify.assertContainsAll(this.set, "1", "2", "3", "4");
        Verify.assertNotContains("5", this.set);
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
    public void getFirstGetLast()
    {
        MutableSet<String> list5 = Sets.fixedSize.of("1", "2", "3", "4");
        assertEquals("1", list5.getFirst());
        assertEquals("4", list5.getLast());
    }

    @Test
    public void forEach()
    {
        MutableList<String> result = Lists.mutable.of();
        MutableSet<String> source = Sets.fixedSize.of("1", "2", "3", "4");
        source.forEach(CollectionAddProcedure.on(result));
        assertEquals(FastList.newListWith("1", "2", "3", "4"), result);
    }

    @Test
    public void forEachWithIndex()
    {
        int[] indexSum = new int[1];
        MutableList<String> result = Lists.mutable.of();
        MutableSet<String> source = Sets.fixedSize.of("1", "2", "3", "4");
        source.forEachWithIndex((each, index) ->
        {
            result.add(each);
            indexSum[0] += index;
        });
        assertEquals(FastList.newListWith("1", "2", "3", "4"), result);
        assertEquals(6, indexSum[0]);
    }

    @Test
    public void forEachWith()
    {
        MutableList<String> result = Lists.mutable.of();
        MutableSet<String> source = Sets.fixedSize.of("1", "2", "3", "4");
        source.forEachWith(Procedures2.fromProcedure(CollectionAddProcedure.on(result)), null);
        assertEquals(FastList.newListWith("1", "2", "3", "4"), result);
    }

    @Test
    public void getOnly()
    {
        assertThrows(IllegalStateException.class, () -> this.set.getOnly());
    }
}
