/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.util.Collections;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.partition.PartitionIterable;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link UnmodifiableRichIterable}.
 */
public class UnmodifiableRichIterableTest extends AbstractRichIterableTestCase
{
    private static final String METALLICA = "Metallica";
    private static final String BON_JOVI = "Bon Jovi";
    private static final String EUROPE = "Europe";
    private static final String SCORPIONS = "Scorpions";
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    private RichIterable<String> mutableCollection;
    private RichIterable<String> unmodifiableCollection;

    @Override
    protected <T> RichIterable<T> newWith(T... elements)
    {
        return UnmodifiableRichIterable.of(Lists.mutable.of(elements));
    }

    @BeforeEach
    public void setUp()
    {
        this.mutableCollection = Lists.mutable.of(METALLICA, BON_JOVI, EUROPE, SCORPIONS);
        this.unmodifiableCollection = UnmodifiableRichIterable.of(this.mutableCollection);
    }

    @Override
    @Test
    public void chunk_large_size()
    {
        RichIterable<String> collection = this.newWith("1", "2", "3", "4", "5", "6", "7");
        Verify.assertIterablesEqual(collection, collection.chunk(10).getOnly());
    }

    @Test
    public void testDelegatingMethods()
    {
        assertTrue(this.mutableCollection.notEmpty());
        assertTrue(this.unmodifiableCollection.notEmpty());
        assertFalse(this.mutableCollection.isEmpty());
        assertFalse(this.unmodifiableCollection.isEmpty());
        Verify.assertIterableSize(this.mutableCollection.size(), this.unmodifiableCollection);
        assertEquals(this.mutableCollection.getFirst(), this.unmodifiableCollection.getFirst());
        assertEquals(this.mutableCollection.getLast(), this.unmodifiableCollection.getLast());
    }

    @Test
    public void converters()
    {
        assertEquals(
                this.mutableCollection.toBag(),
                this.unmodifiableCollection.toBag());
        assertEquals(
                this.mutableCollection.asLazy().toBag(),
                this.unmodifiableCollection.asLazy().toBag());
        assertArrayEquals(
                this.mutableCollection.toArray(),
                this.unmodifiableCollection.toArray());
        assertArrayEquals(
                this.mutableCollection.toArray(EMPTY_STRING_ARRAY),
                this.unmodifiableCollection.toArray(EMPTY_STRING_ARRAY));
        assertEquals(this.mutableCollection.toList(), this.unmodifiableCollection.toList());
        Verify.assertListsEqual(
                Lists.mutable.of(BON_JOVI, EUROPE, METALLICA, SCORPIONS),
                this.unmodifiableCollection.toSortedList());
        Verify.assertListsEqual(
                Lists.mutable.of(SCORPIONS, METALLICA, EUROPE, BON_JOVI),
                this.unmodifiableCollection.toSortedList(Collections.reverseOrder()));
        Verify.assertListsEqual(
                Lists.mutable.of(BON_JOVI, EUROPE, METALLICA, SCORPIONS),
                this.unmodifiableCollection.toSortedListBy(Functions.getStringPassThru()));
        Verify.assertSize(4, this.unmodifiableCollection.toSet());
        Verify.assertSize(4, this.unmodifiableCollection.toMap(Functions.getStringPassThru(), Functions.getStringPassThru()));
    }

    @Test
    public void nullCheck()
    {
        assertThrows(IllegalArgumentException.class, () -> UnmodifiableRichIterable.of(null));
    }

    @Test
    @Override
    public void equalsAndHashCode()
    {
        assertNotEquals(this.newWith(1, 2, 3).hashCode(), this.newWith(1, 2, 3).hashCode());
        assertNotEquals(this.newWith(1, 2, 3), this.newWith(1, 2, 3));
    }

    @Test
    @Override
    public void partition()
    {
        PartitionIterable<String> partition = this.mutableCollection.partition(ignored -> true);
        PartitionIterable<String> unmodifiablePartition = this.unmodifiableCollection.partition(ignored -> true);
        assertEquals(partition.getSelected(), unmodifiablePartition.getSelected());
        assertEquals(partition.getRejected(), unmodifiablePartition.getRejected());
    }

    @Test
    @Override
    public void partitionWith()
    {
        PartitionIterable<String> partition = this.mutableCollection.partitionWith((ignored1, ignored2) -> true, null);
        PartitionIterable<String> unmodifiablePartition = this.unmodifiableCollection.partitionWith((ignored1, ignored2) -> true, null);
        assertEquals(partition.getSelected(), unmodifiablePartition.getSelected());
        assertEquals(partition.getRejected(), unmodifiablePartition.getRejected());
    }

    @Test
    @Override
    public void groupBy()
    {
        assertEquals(this.mutableCollection.groupBy(Functions.getStringPassThru()), this.unmodifiableCollection.groupBy(Functions.getStringPassThru()));
        assertEquals(this.mutableCollection.groupBy(Functions.getStringPassThru(), FastListMultimap.newMultimap()), this.unmodifiableCollection.groupBy(Functions.getStringPassThru(), FastListMultimap.newMultimap()));
    }
}
