/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.impl.Counter;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link UnmodifiableMutableList}.
 */
public class UnmodifiableMutableListTest
{
    private static final String LED_ZEPPELIN = "Led Zeppelin";
    private static final String METALLICA = "Metallica";

    private MutableList<String> mutableList;
    private MutableList<String> unmodifiableList;

    @BeforeEach
    public void setUp()
    {
        this.mutableList = Lists.mutable.of(METALLICA, "Bon Jovi", "Europe", "Scorpions");
        this.unmodifiableList = UnmodifiableMutableList.of(this.mutableList);
    }

    @Test
    public void equalsAndHashCode()
    {
        Verify.assertEqualsAndHashCode(this.mutableList, this.unmodifiableList);
        Verify.assertPostSerializedEqualsAndHashCode(this.unmodifiableList);
        Verify.assertInstanceOf(UnmodifiableMutableList.class, SerializeTestHelper.serializeDeserialize(this.unmodifiableList));
    }

    @Test
    public void delegatingMethods()
    {
        Verify.assertItemAtIndex("Europe", 2, this.unmodifiableList);
        assertEquals(2, this.unmodifiableList.indexOf("Europe"));
        assertEquals(0, this.unmodifiableList.lastIndexOf(METALLICA));
    }

    @Test
    public void forEachFromTo()
    {
        Counter counter = new Counter();
        this.unmodifiableList.forEach(1, 2, band -> counter.increment());
        assertEquals(2, counter.getCount());
    }

    @Test
    public void listIterator()
    {
        ListIterator<String> it = this.unmodifiableList.listIterator();
        assertFalse(it.hasPrevious());
        assertEquals(-1, it.previousIndex());
        assertEquals(METALLICA, it.next());
        assertTrue(it.hasNext());
        assertEquals(1, it.nextIndex());

        assertThrows(UnsupportedOperationException.class, () -> it.set("Rick Astley"));

        assertThrows(UnsupportedOperationException.class, it::remove);

        assertThrows(UnsupportedOperationException.class, () -> it.add("Gloria Gaynor"));

        assertEquals(METALLICA, it.previous());
    }

    @Test
    public void sortThis()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.sortThis());
    }

    @Test
    public void sortThisWithComparator()
    {
        assertThrows(
                UnsupportedOperationException.class,
                () -> this.unmodifiableList.sortThis(String::compareTo));
    }

    @Test
    public void sortThisBy()
    {
        assertThrows(
                UnsupportedOperationException.class,
                () -> this.unmodifiableList.sortThisBy(Functions.getStringToInteger()));
    }

    @Test
    public void sortThisByBoolean()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.sortThisByBoolean(null));
    }

    @Test
    public void sortThisByChar()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.sortThisByChar(null));
    }

    @Test
    public void sortThisByByte()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.sortThisByByte(null));
    }

    @Test
    public void sortThisByShort()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.sortThisByShort(null));
    }

    @Test
    public void sortThisByInt()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.sortThisByInt(null));
    }

    @Test
    public void sortThisByFloat()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.sortThisByFloat(null));
    }

    @Test
    public void sortThisByLong()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.sortThisByLong(null));
    }

    @Test
    public void sortThisByDouble()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.sortThisByDouble(null));
    }

    @Test
    public void shuffleThis()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.shuffleThis(null));
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.shuffleThis(new Random(4)));
    }

    @Test
    public void reverseThis()
    {
        assertThrows(
                UnsupportedOperationException.class,
                () -> this.unmodifiableList.reverseThis());
    }

    @Test
    public void addAllAtIndex()
    {
        assertThrows(
                UnsupportedOperationException.class,
                () -> this.unmodifiableList.addAll(0, Lists.mutable.of("Madonna")));
    }

    @Test
    public void set()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.set(0, "Madonna"));
    }

    @Test
    public void addAtIndex()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.add(0, "Madonna"));
    }

    @Test
    public void removeFromIndex()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.remove(0));
    }

    @Test
    public void replaceAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.replaceAll(e -> e));
    }

    @Test
    public void sort()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.sort(Comparator.naturalOrder()));
    }

    @Test
    public void subList()
    {
        MutableList<String> subList = this.unmodifiableList.subList(1, 3);
        assertEquals(Lists.immutable.of("Bon Jovi", "Europe"), subList);
        assertThrows(UnsupportedOperationException.class, subList::clear);
    }

    @Test
    public void newEmpty()
    {
        MutableList<String> list = this.unmodifiableList.newEmpty();
        list.add(LED_ZEPPELIN);
        Verify.assertContains(LED_ZEPPELIN, list);
    }

    @Test
    public void toImmutable()
    {
        Verify.assertInstanceOf(ImmutableList.class, this.unmodifiableList.toImmutable());
        assertEquals(this.unmodifiableList, this.unmodifiableList.toImmutable());
    }

    @Test
    public void asUnmodifiable()
    {
        assertSame(this.unmodifiableList, this.unmodifiableList.asUnmodifiable());
    }

    @Test
    public void asSynchronized()
    {
        MutableList<String> synchronizedList = this.unmodifiableList.asSynchronized();
        Verify.assertInstanceOf(SynchronizedMutableList.class, synchronizedList);
        assertThrows(UnsupportedOperationException.class, () ->
        {
            Iterator<String> iterator = synchronizedList.iterator();
            iterator.next();
            iterator.remove();
        });
    }

    @Test
    public void asReversed()
    {
        LazyIterable<String> lazyIterable = this.unmodifiableList.asReversed();
        assertThrows(UnsupportedOperationException.class, () ->
        {
            Iterator<String> iterator = lazyIterable.iterator();
            iterator.next();
            iterator.remove();
        });
    }

    @Test
    public void toReversed()
    {
        assertEquals(Lists.mutable.ofAll(this.unmodifiableList).toReversed(), this.unmodifiableList.toReversed());
    }

    @Test
    public void selectInstancesOf()
    {
        MutableList<Number> numbers = UnmodifiableMutableList.of(FastList.newListWith(1, 2.0, 3, 4.0, 5));
        assertEquals(iList(1, 3, 5), numbers.selectInstancesOf(Integer.class));
        assertEquals(iList(1, 2.0, 3, 4.0, 5), numbers.selectInstancesOf(Number.class));
    }

    @Test
    public void distinct()
    {
        MutableList<Integer> list = UnmodifiableMutableList.of(Lists.mutable.with(3, 1, 2, 2, 1, 3));
        Verify.assertListsEqual(FastList.newListWith(3, 1, 2), list.distinct());
    }

    @Test
    public void distinctWithHashingStrategy()
    {
        MutableList<String> letters = UnmodifiableMutableList.of(Lists.mutable.with("a", "A", "b", "C", "b", "D", "E", "e"));
        MutableList<String> expectedLetters = UnmodifiableMutableList.of(FastList.newListWith("a", "b", "C", "D", "E"));
        Verify.assertListsEqual(letters.distinct(HashingStrategies.fromFunction(String::toLowerCase)), expectedLetters);
    }

    /**
     * @since 9.0.
     */
    @Test
    public void distinctBy()
    {
        MutableList<String> letters = UnmodifiableMutableList.of(Lists.mutable.with("a", "A", "b", "C", "b", "D", "E", "e"));
        MutableList<String> expectedLetters = UnmodifiableMutableList.of(Lists.mutable.with("a", "b", "C", "D", "E"));
        Verify.assertListsEqual(letters.distinctBy(String::toLowerCase), expectedLetters);
    }

    @Test
    public void take()
    {
        UnmodifiableMutableList<Integer> unmodifiableList = UnmodifiableMutableList.of(FastList.newListWith(1, 2, 3, 4, 5));
        assertEquals(iList(), unmodifiableList.take(0));
        assertEquals(iList(1, 2, 3), unmodifiableList.take(3));
        assertEquals(iList(1, 2, 3, 4), unmodifiableList.take(unmodifiableList.size() - 1));
        assertEquals(iList(1, 2, 3, 4, 5), unmodifiableList.take(unmodifiableList.size()));
        assertEquals(iList(1, 2, 3, 4, 5), unmodifiableList.take(10));
        assertEquals(iList(1, 2, 3, 4, 5), unmodifiableList.take(Integer.MAX_VALUE));
        assertNotSame(unmodifiableList, unmodifiableList.take(Integer.MAX_VALUE));
    }

    @Test
    public void take_throws()
    {
        assertThrows(IllegalArgumentException.class, () -> UnmodifiableMutableList.of(FastList.newListWith(1, 2, 3, 4, 5)).take(-1));
    }

    @Test
    public void takeWhile()
    {
        assertEquals(
                iList(1, 2, 3),
                UnmodifiableMutableList.of(FastList.newListWith(1, 2, 3, 4, 5)).takeWhile(Predicates.lessThan(4)));
    }

    @Test
    public void drop()
    {
        UnmodifiableMutableList<Integer> unmodifiableList = UnmodifiableMutableList.of(FastList.newListWith(1, 2, 3, 4, 5));
        assertEquals(iList(1, 2, 3, 4, 5), unmodifiableList.drop(0));
        assertNotSame(unmodifiableList, unmodifiableList.drop(0));
        assertEquals(iList(4, 5), unmodifiableList.drop(3));
        assertEquals(iList(5), unmodifiableList.drop(unmodifiableList.size() - 1));
        assertEquals(iList(), unmodifiableList.drop(unmodifiableList.size()));
        assertEquals(iList(), unmodifiableList.drop(10));
        assertEquals(iList(), unmodifiableList.drop(Integer.MAX_VALUE));
    }

    @Test
    public void drop_throws()
    {
        assertThrows(IllegalArgumentException.class, () -> UnmodifiableMutableList.of(FastList.newListWith(1, 2, 3, 4, 5)).drop(-1));
    }

    @Test
    public void dropWhile()
    {
        assertEquals(
                iList(4, 5),
                UnmodifiableMutableList.of(FastList.newListWith(1, 2, 3, 4, 5)).dropWhile(Predicates.lessThan(4)));
    }

    @Test
    public void partitionWhile()
    {
        PartitionMutableList<Integer> partition = UnmodifiableMutableList.of(FastList.newListWith(1, 2, 3, 4, 5)).partitionWhile(Predicates.lessThan(4));
        MutableList<Integer> selected = partition.getSelected();
        MutableList<Integer> rejected = partition.getRejected();

        assertEquals(iList(1, 2, 3), selected);
        assertEquals(iList(4, 5), rejected);
    }

    @Test
    public void binarySearch()
    {
        UnmodifiableMutableList<Integer> sortedList = UnmodifiableMutableList.of(FastList.newListWith(1, 2, 3, 4, 5, 7));
        assertEquals(1, sortedList.binarySearch(2));
        assertEquals(-6, sortedList.binarySearch(6));

        for (Integer integer : sortedList)
        {
            assertEquals(
                    Collections.binarySearch(sortedList, integer),
                    sortedList.binarySearch(integer));
        }
    }

    @Test
    public void binarySearchWithComparator()
    {
        UnmodifiableMutableList<Integer> sortedList = UnmodifiableMutableList.of(FastList.newListWith(1, 2, 3, 4, 5, 7)
                .toSortedList(Comparators.reverseNaturalOrder()));
        assertEquals(sortedList.size() - 1, sortedList.binarySearch(1, Comparators.reverseNaturalOrder()));
        assertEquals(-1 - sortedList.size(), sortedList.binarySearch(-1, Comparators.reverseNaturalOrder()));

        for (Integer integer : sortedList)
        {
            assertEquals(
                    Collections.binarySearch(sortedList, integer, Comparators.reverseNaturalOrder()),
                    sortedList.binarySearch(integer, Comparators.reverseNaturalOrder()));
        }
    }
}
