/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.fixed;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmptyListTest
{
    @Test
    public void size()
    {
        Verify.assertSize(0, new EmptyList<>());
    }

    @Test
    public void contains()
    {
        assertFalse(new EmptyList<>().contains(null));
        assertFalse(new EmptyList<>().contains(new Object()));
    }

    @Test
    public void get()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> new EmptyList<>().get(0));
    }

    @Test
    public void set()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> new EmptyList<>().set(0, null));
    }

    @Test
    public void empty()
    {
        Verify.assertEmpty(new EmptyList<>());
        assertFalse(new EmptyList<>().notEmpty());
        Verify.assertEmpty(Lists.fixedSize.of());
        assertFalse(Lists.fixedSize.of().notEmpty());
    }

    @Test
    public void getFirstLast()
    {
        assertNull(new EmptyList<>().getFirst());
        assertNull(new EmptyList<>().getLast());
    }

    @Test
    public void getOnly()
    {
        assertThrows(IllegalStateException.class, () -> new EmptyList<>().getOnly());
    }

    @Test
    public void readResolve()
    {
        Verify.assertInstanceOf(EmptyList.class, Lists.fixedSize.of());
        Verify.assertPostSerializedIdentity(Lists.fixedSize.of());
    }

    @Test
    public void testClone()
    {
        assertSame(Lists.fixedSize.of().clone(), Lists.fixedSize.of());
    }

    @Test
    public void min()
    {
        assertThrows(NoSuchElementException.class, () -> Lists.fixedSize.of().min(Comparators.naturalOrder()));
    }

    @Test
    public void max()
    {
        assertThrows(NoSuchElementException.class, () -> Lists.fixedSize.of().max(Comparators.naturalOrder()));
    }

    @Test
    public void min_without_comparator()
    {
        assertThrows(NoSuchElementException.class, () -> Lists.fixedSize.of().min());
    }

    @Test
    public void max_without_comparator()
    {
        assertThrows(NoSuchElementException.class, () -> Lists.fixedSize.of().max());
    }

    @Test
    public void minBy()
    {
        assertThrows(NoSuchElementException.class, () -> Lists.fixedSize.of().minBy(String::valueOf));
    }

    @Test
    public void maxBy()
    {
        assertThrows(NoSuchElementException.class, () -> Lists.fixedSize.of().maxBy(String::valueOf));
    }

    @Test
    public void zip()
    {
        assertEquals(
                Lists.fixedSize.of(),
                Lists.fixedSize.of().zip(FastList.newListWith(1, 2, 3)));
    }

    @Test
    public void zipWithIndex()
    {
        assertEquals(
                Lists.fixedSize.of(),
                Lists.fixedSize.of().zipWithIndex());
    }

    @Test
    public void chunk_large_size()
    {
        assertEquals(Lists.fixedSize.of(), Lists.fixedSize.of().chunk(10));
    }

    @Test
    public void sortThis()
    {
        MutableList<Object> expected = Lists.fixedSize.of();
        MutableList<Object> list = Lists.fixedSize.of();
        MutableList<Object> sortedList = list.sortThis();
        assertEquals(expected, sortedList);
        assertSame(sortedList, list);
    }

    @Test
    public void sortThisBy()
    {
        MutableList<Object> expected = Lists.fixedSize.of();
        MutableList<Object> list = Lists.fixedSize.of();
        MutableList<Object> sortedList = list.sortThisBy(String::valueOf);
        assertEquals(expected, sortedList);
        assertSame(sortedList, list);
    }

    @Test
    public void sortThisByPrimitive()
    {
        MutableList<Object> expected = Lists.fixedSize.of();
        MutableList<Object> list = Lists.fixedSize.of();
        assertEquals(expected, list.sortThisByBoolean(anObject -> true));
        assertSame(list, list.sortThisByBoolean(anObject -> true));
        assertEquals(expected, list.sortThisByByte(anObject -> (byte) 0));
        assertSame(list, list.sortThisByByte(anObject -> (byte) 0));
        assertEquals(expected, list.sortThisByChar(anObject -> (char) 0));
        assertSame(list, list.sortThisByChar(anObject -> (char) 0));
        assertEquals(expected, list.sortThisByDouble(anObject -> 0.0));
        assertSame(list, list.sortThisByDouble(anObject -> 0.0));
        assertEquals(expected, list.sortThisByFloat(anObject -> 0.0f));
        assertSame(list, list.sortThisByFloat(anObject -> 0.0f));
        assertEquals(expected, list.sortThisByInt(anObject -> 0));
        assertSame(list, list.sortThisByInt(anObject -> 0));
        assertEquals(expected, list.sortThisByLong(anObject -> 0L));
        assertSame(list, list.sortThisByLong(anObject -> 0L));
        assertEquals(expected, list.sortThisByShort(anObject -> (short) 0));
        assertSame(list, list.sortThisByShort(anObject -> (short) 0));
    }

    @Test
    public void with()
    {
        MutableList<Integer> list = new EmptyList<Integer>().with(1);
        Verify.assertListsEqual(FastList.newListWith(1), list);
        Verify.assertInstanceOf(SingletonList.class, list);
    }

    @Test
    public void withAll()
    {
        MutableList<Integer> list = new EmptyList<Integer>().withAll(FastList.newListWith(1, 2));
        Verify.assertListsEqual(FastList.newListWith(1, 2), list);
        Verify.assertInstanceOf(DoubletonList.class, list);
    }

    @Test
    public void without()
    {
        MutableList<Integer> list = new EmptyList<>();
        assertSame(list, list.without(2));
    }

    @Test
    public void withoutAll()
    {
        MutableList<Integer> list = new EmptyList<>();
        assertEquals(list, list.withoutAll(FastList.newListWith(1, 2)));
    }

    @Test
    public void collectPrimitives()
    {
        MutableList<Integer> list = new EmptyList<>();
        Verify.assertEmpty(list.collectBoolean(PrimitiveFunctions.integerIsPositive()));
        Verify.assertEmpty(list.collectByte(PrimitiveFunctions.unboxIntegerToByte()));
        Verify.assertEmpty(list.collectChar(PrimitiveFunctions.unboxIntegerToChar()));
        Verify.assertEmpty(list.collectDouble(PrimitiveFunctions.unboxIntegerToDouble()));
        Verify.assertEmpty(list.collectFloat(PrimitiveFunctions.unboxIntegerToFloat()));
        Verify.assertEmpty(list.collectInt(PrimitiveFunctions.unboxIntegerToInt()));
        Verify.assertEmpty(list.collectLong(PrimitiveFunctions.unboxIntegerToLong()));
        Verify.assertEmpty(list.collectShort(PrimitiveFunctions.unboxIntegerToShort()));
    }

    @Test
    public void replaceAll()
    {
        MutableList<String> emptyStrings = new EmptyList<>();
        emptyStrings.replaceAll(s -> "1");
        Verify.assertEmpty(emptyStrings);
    }

    @Test
    public void sort()
    {
        MutableList<String> emptyStrings = new EmptyList<>();
        emptyStrings.sort(Comparator.naturalOrder());
        Verify.assertEmpty(emptyStrings);
    }

    @Test
    public void each()
    {
        AtomicInteger integer = new AtomicInteger();
        new EmptyList<>().each(each -> integer.incrementAndGet());
        assertEquals(0, integer.get());
    }

    @Test
    public void forEachWith()
    {
        AtomicInteger integer = new AtomicInteger();
        new EmptyList<>().forEachWith((argument1, argument2) -> integer.incrementAndGet(), null);
        assertEquals(0, integer.get());
    }

    @Test
    public void iterator()
    {
        Iterator<Object> iterator = new EmptyList<>().iterator();
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void listIterator()
    {
        ListIterator<Object> iterator = new EmptyList<>().listIterator();
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertThrows(NoSuchElementException.class, iterator::next);
        assertThrows(NoSuchElementException.class, iterator::previous);
    }

    @Test
    public void listIteratorWithIndex()
    {
        ListIterator<Object> iterator = new EmptyList<>().listIterator(0);
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertThrows(NoSuchElementException.class, iterator::next);
        assertThrows(NoSuchElementException.class, iterator::previous);
        assertThrows(IndexOutOfBoundsException.class, () -> new EmptyList<>().listIterator(1));
    }

    @Test
    public void toImmutable()
    {
        assertSame(Lists.immutable.empty(), new EmptyList<>().toImmutable());
    }
}
