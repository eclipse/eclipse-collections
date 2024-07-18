/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable;

import java.util.Collections;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.collection.mutable.AbstractCollectionTestCase;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link UnmodifiableMutableSet}.
 */
public class UnmodifiableMutableSetTest extends AbstractCollectionTestCase
{
    private static final String LED_ZEPPELIN = "Led Zeppelin";
    private static final String METALLICA = "Metallica";

    private MutableSet<String> mutableSet;
    private MutableSet<String> unmodifiableSet;

    @BeforeEach
    public void setUp()
    {
        this.mutableSet = UnifiedSet.newSetWith(METALLICA, "Bon Jovi", "Europe", "Scorpions");
        this.unmodifiableSet = this.mutableSet.asUnmodifiable();
    }

    @Override
    protected <T> MutableSet<T> newWith(T... elements)
    {
        return UnifiedSet.newSetWith(elements).asUnmodifiable();
    }

    @Override
    @Test
    public void removeObject()
    {
        assertThrows(UnsupportedOperationException.class, () -> super.removeObject());
    }

    @Override
    @Test
    public void removeIfWith()
    {
        assertThrows(UnsupportedOperationException.class, () -> super.removeIfWith());
    }

    @Override
    @Test
    public void clear()
    {
        assertThrows(UnsupportedOperationException.class, () -> super.clear());
    }

    @Override
    @Test
    public void addAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> super.addAll());
    }

    @Override
    @Test
    public void addAllIterable()
    {
        assertThrows(UnsupportedOperationException.class, () -> super.addAllIterable());
    }

    @Override
    @Test
    public void removeIf()
    {
        assertThrows(UnsupportedOperationException.class, () -> super.removeIf());
    }

    @Override
    @Test
    public void removeAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> super.removeAll());
    }

    @Override
    @Test
    public void removeAllIterable()
    {
        assertThrows(UnsupportedOperationException.class, () -> super.removeAllIterable());
    }

    @Override
    @Test
    public void retainAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> super.retainAll());
    }

    @Override
    @Test
    public void retainAllIterable()
    {
        assertThrows(UnsupportedOperationException.class, () -> super.retainAllIterable());
    }

    @Override
    @Test
    public void testToString()
    {
        MutableCollection<Object> collection = this.newWith(1, 2);
        String string = collection.toString();
        assertTrue("[1, 2]".equals(string) || "[2, 1]".equals(string));
    }

    @Override
    @Test
    public void makeString()
    {
        MutableCollection<Object> collection = this.newWith(1, 2, 3);
        assertEquals(collection.toString(), '[' + collection.makeString() + ']');
    }

    @Override
    @Test
    public void appendString()
    {
        MutableCollection<Object> collection = this.newWith(1, 2, 3);
        Appendable builder = new StringBuilder();
        collection.appendString(builder);
        assertEquals(collection.toString(), '[' + builder.toString() + ']');
    }

    @Override
    @Test
    public void getFirst()
    {
        assertNotNull(this.newWith(1, 2, 3).getFirst());
        assertNull(this.newWith().getFirst());
    }

    @Override
    @Test
    public void getLast()
    {
        assertNotNull(this.newWith(1, 2, 3).getLast());
        assertNull(this.newWith().getLast());
    }

    @Override
    @Test
    public void asSynchronized()
    {
        Verify.assertInstanceOf(SynchronizedMutableSet.class, this.newWith().asSynchronized());
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        Verify.assertInstanceOf(UnmodifiableMutableSet.class, this.newWith().asUnmodifiable());
    }

    @Test
    public void testEqualsAndHashCode()
    {
        Verify.assertEqualsAndHashCode(this.mutableSet, this.unmodifiableSet);
        Verify.assertPostSerializedEqualsAndHashCode(this.unmodifiableSet);
        Verify.assertInstanceOf(UnmodifiableMutableSet.class, SerializeTestHelper.serializeDeserialize(this.unmodifiableSet));
    }

    @Test
    public void testNewEmpty()
    {
        MutableSet<String> set = this.unmodifiableSet.newEmpty();
        set.add(LED_ZEPPELIN);
        Verify.assertContains(LED_ZEPPELIN, set);
    }

    @Test
    public void testClone()
    {
        MutableSet<String> set = this.newWith();
        MutableSet<String> clone = set.clone();
        assertSame(clone, set);
    }

    @Test
    public void min_empty_throws_without_comparator()
    {
        assertThrows(NoSuchElementException.class, () -> this.newWith().min());
    }

    @Test
    public void max_empty_throws_without_comparator()
    {
        assertThrows(NoSuchElementException.class, () -> this.newWith().max());
    }

    @Override
    @Test
    public void with()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).with(4));
    }

    @Override
    @Test
    public void withAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).withAll(FastList.newListWith(4, 5)));
    }

    @Override
    @Test
    public void without()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).without(2));
    }

    @Override
    @Test
    public void withoutAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3, 4, 5).withoutAll(FastList.newListWith(2, 4)));
    }

    @Override
    @Test
    public void toSortedBag_natural_ordering()
    {
        RichIterable<Integer> integers = this.newWith(1, 2, 5, 3, 4);
        MutableSortedBag<Integer> bag = integers.toSortedBag();
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(1, 2, 3, 4, 5), bag);
    }

    @Override
    @Test
    public void toSortedBag_with_comparator()
    {
        RichIterable<Integer> integers = this.newWith(2, 4, 1, 3);
        MutableSortedBag<Integer> bag = integers.toSortedBag(Collections.reverseOrder());
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(Collections.reverseOrder(), 4, 3, 2, 1), bag);
    }

    @Override
    @Test
    public void toImmutableSortedBag_with_comparator()
    {
        RichIterable<Integer> integers = this.newWith(2, 4, 1, 3);
        ImmutableSortedBag<Integer> bag = integers.toImmutableSortedBag(Collections.reverseOrder());
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(Collections.reverseOrder(), 4, 3, 2, 1), bag);
    }

    @Override
    @Test
    public void toSortedBag_with_null()
    {
        assertThrows(NullPointerException.class, () -> this.newWith(3, 4, null, 1, 2).toSortedBag());
    }

    @Override
    @Test
    public void toSortedBagBy()
    {
        RichIterable<Integer> integers = this.newWith(2, 4, 1, 3);
        MutableSortedBag<Integer> bag = integers.toSortedBagBy(String::valueOf);
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(1, 2, 3, 4), bag);
    }
}
