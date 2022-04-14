/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.ByteIterable;
import org.eclipse.collections.api.CharIterable;
import org.eclipse.collections.api.DoubleIterable;
import org.eclipse.collections.api.FloatIterable;
import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.LongIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.ShortIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleObjectToDoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatObjectToFloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntObjectToIntFunction;
import org.eclipse.collections.api.block.function.primitive.LongObjectToLongFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableByteCollection;
import org.eclipse.collections.api.collection.primitive.MutableCharCollection;
import org.eclipse.collections.api.collection.primitive.MutableDoubleCollection;
import org.eclipse.collections.api.collection.primitive.MutableFloatCollection;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.collection.primitive.MutableLongCollection;
import org.eclipse.collections.api.collection.primitive.MutableShortCollection;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.factory.SortedSets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.partition.PartitionIterable;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.Counter;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.ByteHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.CharHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.DoubleHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.FloatHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.IntHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.LongHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.ShortHashBag;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.collector.Collectors2;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.factory.primitive.ByteLists;
import org.eclipse.collections.impl.factory.primitive.CharLists;
import org.eclipse.collections.impl.factory.primitive.DoubleLists;
import org.eclipse.collections.impl.factory.primitive.FloatLists;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.LongLists;
import org.eclipse.collections.impl.factory.primitive.ObjectDoubleMaps;
import org.eclipse.collections.impl.factory.primitive.ObjectLongMaps;
import org.eclipse.collections.impl.factory.primitive.ShortLists;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.eclipse.collections.test.IterableTestCase.assertNotEquals;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@SuppressWarnings("UnnecessaryCodeBlock")
public interface RichIterableTestCase extends IterableTestCase
{
    @Override
    <T> RichIterable<T> newWith(T... elements);

    <T> RichIterable<T> getExpectedFiltered(T... elements);

    <T> RichIterable<T> getExpectedTransformed(T... elements);

    <T> MutableCollection<T> newMutableForFilter(T... elements);

    <T> MutableCollection<T> newMutableForTransform(T... elements);

    @Test
    default void newMutable_sanity()
    {
        assertEquals(this.getExpectedFiltered(3, 2, 1), this.newMutableForFilter(3, 2, 1));
    }

    MutableBooleanCollection newBooleanForTransform(boolean... elements);

    MutableByteCollection newByteForTransform(byte... elements);

    MutableCharCollection newCharForTransform(char... elements);

    MutableDoubleCollection newDoubleForTransform(double... elements);

    MutableFloatCollection newFloatForTransform(float... elements);

    MutableIntCollection newIntForTransform(int... elements);

    MutableLongCollection newLongForTransform(long... elements);

    MutableShortCollection newShortForTransform(short... elements);

    default BooleanIterable getExpectedBoolean(boolean... elements)
    {
        return this.newBooleanForTransform(elements);
    }

    default ByteIterable getExpectedByte(byte... elements)
    {
        return this.newByteForTransform(elements);
    }

    default CharIterable getExpectedChar(char... elements)
    {
        return this.newCharForTransform(elements);
    }

    default DoubleIterable getExpectedDouble(double... elements)
    {
        return this.newDoubleForTransform(elements);
    }

    default FloatIterable getExpectedFloat(float... elements)
    {
        return this.newFloatForTransform(elements);
    }

    default IntIterable getExpectedInt(int... elements)
    {
        return this.newIntForTransform(elements);
    }

    default LongIterable getExpectedLong(long... elements)
    {
        return this.newLongForTransform(elements);
    }

    default ShortIterable getExpectedShort(short... elements)
    {
        return this.newShortForTransform(elements);
    }

    @Test
    default void InternalIterable_forEach()
    {
        {
            RichIterable<Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);
            MutableCollection<Integer> result = this.newMutableForFilter();
            iterable.forEach(Procedures.cast(i -> result.add(i + 10)));
            assertEquals(this.newMutableForFilter(13, 13, 13, 12, 12, 11), result);
        }

        {
            RichIterable<Integer> iterable = this.newWith(2, 2, 1);
            MutableCollection<Integer> result = this.newMutableForFilter();
            iterable.forEach(Procedures.cast(i -> result.add(i + 10)));
            assertEquals(this.newMutableForFilter(12, 12, 11), result);
        }

        {
            RichIterable<Integer> iterable = this.newWith(2, 1);
            MutableCollection<Integer> result = this.newMutableForFilter();
            iterable.forEach(Procedures.cast(i -> result.add(i + 10)));
            assertEquals(this.newMutableForFilter(12, 11), result);
        }

        RichIterable<Integer> iterable = this.newWith(1);
        MutableCollection<Integer> result = this.newMutableForFilter();
        iterable.forEach(Procedures.cast(i -> result.add(i + 10)));
        assertEquals(this.newMutableForFilter(11), result);

        this.newWith().forEach(Procedures.cast(each -> fail()));
    }

    @Test
    default void RichIterable_tap()
    {
        RichIterable<Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);
        MutableCollection<Integer> result = this.newMutableForFilter();
        iterable.tap(result::add).forEach(Procedures.noop());
        assertEquals(this.newMutableForFilter(3, 3, 3, 2, 2, 1), result);
        this.newWith().tap(Procedures.cast(each -> fail()));
    }

    @Test
    default void InternalIterable_forEachWith()
    {
        RichIterable<Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);
        MutableCollection<Integer> result = this.newMutableForFilter();
        iterable.forEachWith((argument1, argument2) -> result.add(argument1 + argument2), 10);
        assertEquals(this.newMutableForFilter(13, 13, 13, 12, 12, 11), result);
    }

    @Test
    default void RichIterable_size_empty()
    {
        assertEquals(0, this.newWith().size());
    }

    @Test
    default void RichIterable_isEmpty()
    {
        assertFalse(this.newWith(3, 2, 1).isEmpty());
        assertTrue(this.newWith().isEmpty());
    }

    @Test
    default void RichIterable_notEmpty()
    {
        assertTrue(this.newWith(3, 2, 1).notEmpty());
        assertFalse(this.newWith().notEmpty());
    }

    @Test
    default void RichIterable_getFirst_empty_null()
    {
        assertNull(this.newWith().getFirst());
    }

    @Test
    default void RichIterable_getLast_empty_null()
    {
        assertNull(this.newWith().getLast());
    }

    @Test
    default void RichIterable_getFirst()
    {
        RichIterable<Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);
        Integer first = iterable.getFirst();
        assertThat(first, isOneOf(3, 2, 1));
        assertEquals(iterable.iterator().next(), first);
    }

    @Test
    default void RichIterable_getLast()
    {
        RichIterable<Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);
        Integer last = iterable.getLast();
        assertThat(last, isOneOf(3, 2, 1));
        Iterator<Integer> iterator = iterable.iterator();
        Integer iteratorLast = null;
        while (iterator.hasNext())
        {
            iteratorLast = iterator.next();
        }
        assertEquals(iteratorLast, last);
    }

    @Test
    default void RichIterable_getOnly()
    {
        RichIterable<Integer> iterable = this.newWith(3);
        Integer only = iterable.getOnly();
        assertThat(only, is(3));

        Iterator<Integer> iterator = iterable.iterator();
        assertThat(iterator.next(), is(only));
        assertThat(iterator.hasNext(), is(false));

        assertThrows(IllegalStateException.class, () -> this.newWith().getOnly());
        assertThrows(IllegalStateException.class, () -> this.newWith(1, 2).getOnly());

        if (this.allowsDuplicates())
        {
            assertThrows(IllegalStateException.class, () -> this.newWith(1, 1).getOnly());
        }
    }

    @Test
    default void RichIterable_getFirst_and_getLast()
    {
        RichIterable<Integer> iterable = this.newWith(3, 2, 1);
        assertNotEquals(iterable.getFirst(), iterable.getLast());
    }

    @Test
    default void RichIterable_contains()
    {
        RichIterable<Integer> iterable3 = this.newWith(3, 2, 1);
        assertTrue(iterable3.contains(3));
        assertTrue(iterable3.contains(2));
        assertTrue(iterable3.contains(1));
        assertFalse(iterable3.contains(0));

        RichIterable<Integer> iterable2 = this.newWith(2, 1);
        assertTrue(iterable2.contains(2));
        assertTrue(iterable2.contains(1));
        assertFalse(iterable2.contains(0));

        RichIterable<Integer> iterable1 = this.newWith(1);
        assertTrue(iterable1.contains(1));
        assertFalse(iterable1.contains(0));

        RichIterable<Integer> iterable0 = this.newWith();
        assertFalse(iterable0.contains(0));
    }

    @Test
    default void RichIterable_containsAllIterable()
    {
        RichIterable<Integer> iterable3 = this.newWith(3, 2, 1);

        assertTrue(iterable3.containsAllIterable(Lists.immutable.of(3)));
        assertTrue(iterable3.containsAllIterable(Lists.immutable.of(3, 2, 1)));
        assertTrue(iterable3.containsAllIterable(Lists.immutable.of(3, 3, 3)));
        assertTrue(iterable3.containsAllIterable(Lists.immutable.of(3, 3, 3, 3, 2, 2, 2, 1, 1)));
        assertFalse(iterable3.containsAllIterable(Lists.immutable.of(4)));
        assertFalse(iterable3.containsAllIterable(Lists.immutable.of(4, 4, 5)));
        assertFalse(iterable3.containsAllIterable(Lists.immutable.of(3, 2, 1, 0)));
        assertTrue(iterable3.containsAllIterable(Lists.immutable.empty()));

        RichIterable<Integer> iterable2 = this.newWith(2, 1);

        assertTrue(iterable2.containsAllIterable(Lists.immutable.of(2)));
        assertTrue(iterable2.containsAllIterable(Lists.immutable.of(2, 1)));
        assertTrue(iterable2.containsAllIterable(Lists.immutable.of(2, 2, 2)));
        assertTrue(iterable2.containsAllIterable(Lists.immutable.of(2, 2, 2, 1, 1)));
        assertFalse(iterable2.containsAllIterable(Lists.immutable.of(4)));
        assertFalse(iterable2.containsAllIterable(Lists.immutable.of(4, 4, 5)));
        assertFalse(iterable2.containsAllIterable(Lists.immutable.of(2, 1, 0)));
        assertTrue(iterable2.containsAllIterable(Lists.immutable.empty()));

        RichIterable<Integer> iterable1 = this.newWith(1);

        assertTrue(iterable1.containsAllIterable(Lists.immutable.of(1)));
        assertTrue(iterable1.containsAllIterable(Lists.immutable.of(1, 1, 1)));
        assertFalse(iterable1.containsAllIterable(Lists.immutable.of(4)));
        assertFalse(iterable1.containsAllIterable(Lists.immutable.of(4, 4, 5)));
        assertFalse(iterable1.containsAllIterable(Lists.immutable.of(2, 1, 0)));
        assertTrue(iterable1.containsAllIterable(Lists.immutable.empty()));

        RichIterable<Integer> iterable0 = this.newWith();

        assertFalse(iterable0.containsAllIterable(Lists.immutable.of(1)));
        assertFalse(iterable0.containsAllIterable(Lists.immutable.of(1, 1, 1)));
        assertFalse(iterable0.containsAllIterable(Lists.immutable.of(4, 4, 5)));
        assertTrue(iterable0.containsAllIterable(Lists.immutable.empty()));
    }

    @Test
    default void RichIterable_containsAll()
    {
        RichIterable<Integer> iterable3 = this.newWith(3, 2, 1);

        assertTrue(iterable3.containsAll(Lists.mutable.of(3)));
        assertTrue(iterable3.containsAll(Lists.mutable.of(3, 2, 1)));
        assertTrue(iterable3.containsAll(Lists.mutable.of(3, 3, 3)));
        assertTrue(iterable3.containsAll(Lists.mutable.of(3, 3, 3, 3, 2, 2, 2, 1, 1)));
        assertFalse(iterable3.containsAll(Lists.mutable.of(4)));
        assertFalse(iterable3.containsAll(Lists.mutable.of(4, 4, 5)));
        assertFalse(iterable3.containsAll(Lists.mutable.of(3, 2, 1, 0)));
        assertTrue(iterable3.containsAll(Lists.mutable.empty()));

        RichIterable<Integer> iterable2 = this.newWith(2, 1);

        assertTrue(iterable2.containsAll(Lists.mutable.of(2)));
        assertTrue(iterable2.containsAll(Lists.mutable.of(2, 1)));
        assertTrue(iterable2.containsAll(Lists.mutable.of(2, 2, 2)));
        assertTrue(iterable2.containsAll(Lists.mutable.of(2, 2, 2, 1, 1)));
        assertFalse(iterable2.containsAll(Lists.mutable.of(4)));
        assertFalse(iterable2.containsAll(Lists.mutable.of(4, 4, 5)));
        assertFalse(iterable2.containsAll(Lists.mutable.of(2, 1, 0)));
        assertTrue(iterable2.containsAll(Lists.mutable.empty()));

        RichIterable<Integer> iterable1 = this.newWith(1);

        assertTrue(iterable1.containsAll(Lists.mutable.of(1)));
        assertTrue(iterable1.containsAll(Lists.mutable.of(1, 1, 1)));
        assertFalse(iterable1.containsAll(Lists.mutable.of(4)));
        assertFalse(iterable1.containsAll(Lists.mutable.of(4, 4, 5)));
        assertFalse(iterable1.containsAll(Lists.mutable.of(2, 1, 0)));
        assertTrue(iterable1.containsAll(Lists.mutable.empty()));

        RichIterable<Integer> iterable0 = this.newWith();

        assertFalse(iterable0.containsAll(Lists.mutable.of(1)));
        assertFalse(iterable0.containsAll(Lists.mutable.of(1, 1, 1)));
        assertFalse(iterable0.containsAll(Lists.mutable.of(4, 4, 5)));
        assertTrue(iterable0.containsAll(Lists.mutable.empty()));
    }

    @Test
    default void RichIterable_containsAllArguments()
    {
        RichIterable<Integer> iterable3 = this.newWith(3, 2, 1);

        assertTrue(iterable3.containsAllArguments(3));
        assertTrue(iterable3.containsAllArguments(3, 2, 1));
        assertTrue(iterable3.containsAllArguments(3, 3, 3));
        assertTrue(iterable3.containsAllArguments(3, 3, 3, 3, 2, 2, 2, 1, 1));
        assertFalse(iterable3.containsAllArguments(4));
        assertFalse(iterable3.containsAllArguments(4, 4, 5));
        assertFalse(iterable3.containsAllArguments(3, 2, 1, 0));
        assertTrue(iterable3.containsAllArguments());

        RichIterable<Integer> iterable2 = this.newWith(2, 1);

        assertTrue(iterable2.containsAllArguments(2));
        assertTrue(iterable2.containsAllArguments(2, 1));
        assertTrue(iterable2.containsAllArguments(2, 2, 2));
        assertTrue(iterable2.containsAllArguments(2, 2, 2, 1, 1));
        assertFalse(iterable2.containsAllArguments(4));
        assertFalse(iterable2.containsAllArguments(4, 4, 5));
        assertFalse(iterable2.containsAllArguments(2, 1, 0));
        assertTrue(iterable2.containsAllArguments());

        RichIterable<Integer> iterable1 = this.newWith(1);

        assertTrue(iterable1.containsAllArguments(1));
        assertTrue(iterable1.containsAllArguments(1, 1, 1));
        assertFalse(iterable1.containsAllArguments(4));
        assertFalse(iterable1.containsAllArguments(4, 4, 5));
        assertFalse(iterable1.containsAllArguments(2, 1, 0));
        assertTrue(iterable1.containsAllArguments());

        RichIterable<Integer> iterable0 = this.newWith();

        assertFalse(iterable0.containsAllArguments(1));
        assertFalse(iterable0.containsAllArguments(1, 1, 1));
        assertFalse(iterable0.containsAllArguments(4, 4, 5));
        assertTrue(iterable0.containsAllArguments());
    }

    @Test
    default void RichIterable_iterator_iterationOrder()
    {
        MutableCollection<Integer> iterationOrder = this.newMutableForFilter();
        Iterator<Integer> iterator = this.getInstanceUnderTest().iterator();
        while (iterator.hasNext())
        {
            iterationOrder.add(iterator.next());
        }
        assertEquals(this.expectedIterationOrder(), iterationOrder);

        MutableCollection<Integer> expectedIterationOrder = this.expectedIterationOrder();
        MutableCollection<Integer> forEachWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().forEachWith((each, param) -> forEachWithIterationOrder.add(each), null);
        assertEquals(expectedIterationOrder, forEachWithIterationOrder);

        MutableCollection<Integer> forEachWithIndexIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().forEachWithIndex((each, index) -> forEachWithIndexIterationOrder.add(each));
        assertEquals(expectedIterationOrder, forEachWithIndexIterationOrder);
    }

    @Test
    default void RichIterable_iterationOrder()
    {
        MutableCollection<Integer> expectedIterationOrder = this.expectedIterationOrder();

        Procedure<Object> noop = Procedures.noop();

        MutableCollection<Integer> selectIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().select(selectIterationOrder::add).forEach(noop);
        assertEquals(expectedIterationOrder, selectIterationOrder);

        MutableCollection<Integer> selectTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().select(selectTargetIterationOrder::add, new HashBag<>());
        assertEquals(expectedIterationOrder, selectTargetIterationOrder);

        MutableCollection<Integer> selectWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().selectWith((each, param) -> selectWithIterationOrder.add(each), null).forEach(noop);
        assertEquals(expectedIterationOrder, selectWithIterationOrder);

        MutableCollection<Integer> selectWithTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().selectWith((each, param) -> selectWithTargetIterationOrder.add(each), null, new HashBag<>());
        assertEquals(expectedIterationOrder, selectWithTargetIterationOrder);

        MutableCollection<Integer> rejectIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().reject(rejectIterationOrder::add).forEach(noop);
        assertEquals(expectedIterationOrder, rejectIterationOrder);

        MutableCollection<Integer> rejectTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().reject(rejectTargetIterationOrder::add, new HashBag<>());
        assertEquals(expectedIterationOrder, rejectTargetIterationOrder);

        MutableCollection<Integer> rejectWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().rejectWith((each, param) -> rejectWithIterationOrder.add(each), null).forEach(noop);
        assertEquals(expectedIterationOrder, rejectWithIterationOrder);

        MutableCollection<Integer> rejectWithTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().rejectWith((each, param) -> rejectWithTargetIterationOrder.add(each), null, new HashBag<>());
        assertEquals(expectedIterationOrder, rejectWithTargetIterationOrder);

        MutableCollection<Integer> partitionIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().partition(partitionIterationOrder::add);
        assertEquals(expectedIterationOrder, partitionIterationOrder);

        MutableCollection<Integer> partitionWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().partitionWith((each, param) -> partitionWithIterationOrder.add(each), null);
        assertEquals(expectedIterationOrder, partitionWithIterationOrder);

        MutableCollection<Integer> collectIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collect(collectIterationOrder::add).forEach(noop);
        assertEquals(expectedIterationOrder, collectIterationOrder);

        MutableCollection<Integer> collectTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collect(collectTargetIterationOrder::add, new HashBag<>());
        assertEquals(expectedIterationOrder, collectTargetIterationOrder);

        MutableCollection<Integer> collectWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectWith((each, param) -> collectWithIterationOrder.add(each), null).forEach(noop);
        assertEquals(expectedIterationOrder, collectWithIterationOrder);

        MutableCollection<Integer> collectWithTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectWith((each, param) -> collectWithTargetIterationOrder.add(each), null, new HashBag<>());
        assertEquals(expectedIterationOrder, collectWithTargetIterationOrder);

        MutableCollection<Integer> collectIfPredicateIterationOrder = this.newMutableForFilter();
        MutableCollection<Integer> collectIfFunctionIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectIf(collectIfPredicateIterationOrder::add, collectIfFunctionIterationOrder::add).forEach(noop);
        assertEquals(expectedIterationOrder, collectIfPredicateIterationOrder);
        assertEquals(expectedIterationOrder, collectIfFunctionIterationOrder);

        MutableCollection<Integer> collectIfPredicateTargetIterationOrder = this.newMutableForFilter();
        MutableCollection<Integer> collectIfFunctionTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectIf(collectIfPredicateTargetIterationOrder::add, collectIfFunctionTargetIterationOrder::add, new HashBag<>());
        assertEquals(expectedIterationOrder, collectIfPredicateTargetIterationOrder);
        assertEquals(expectedIterationOrder, collectIfFunctionTargetIterationOrder);

        MutableCollection<Integer> collectBooleanIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectBoolean(collectBooleanIterationOrder::add).forEach(each -> {
        });
        assertEquals(expectedIterationOrder, collectBooleanIterationOrder);

        MutableCollection<Integer> collectBooleanTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectBoolean(collectBooleanTargetIterationOrder::add, new BooleanHashBag());
        assertEquals(expectedIterationOrder, collectBooleanTargetIterationOrder);

        MutableCollection<Integer> collectByteIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectByte((Integer each) ->
        {
            collectByteIterationOrder.add(each);
            return (byte) 0;
        }).forEach(each -> {
        });
        assertEquals(expectedIterationOrder, collectByteIterationOrder);

        MutableCollection<Integer> collectByteTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectByte((Integer each) ->
        {
            collectByteTargetIterationOrder.add(each);
            return (byte) 0;
        }, new ByteHashBag());
        assertEquals(expectedIterationOrder, collectByteTargetIterationOrder);

        MutableCollection<Integer> collectCharIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectChar((Integer each) ->
        {
            collectCharIterationOrder.add(each);
            return ' ';
        }).forEach(each -> {
        });
        assertEquals(expectedIterationOrder, collectCharIterationOrder);

        MutableCollection<Integer> collectCharTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectChar((Integer each) ->
        {
            collectCharTargetIterationOrder.add(each);
            return ' ';
        }, new CharHashBag());
        assertEquals(expectedIterationOrder, collectCharTargetIterationOrder);

        MutableCollection<Integer> collectDoubleIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectDouble((Integer each) ->
        {
            collectDoubleIterationOrder.add(each);
            return 0.0;
        }).forEach(each -> {
        });
        assertEquals(expectedIterationOrder, collectDoubleIterationOrder);

        MutableCollection<Integer> collectDoubleTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectDouble((Integer each) ->
        {
            collectDoubleTargetIterationOrder.add(each);
            return 0.0;
        }, new DoubleHashBag());
        assertEquals(expectedIterationOrder, collectDoubleTargetIterationOrder);

        MutableCollection<Integer> collectFloatIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectFloat((Integer each) ->
        {
            collectFloatIterationOrder.add(each);
            return 0.0f;
        }).forEach(each -> {
        });
        assertEquals(expectedIterationOrder, collectFloatIterationOrder);

        MutableCollection<Integer> collectFloatTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectFloat((Integer each) ->
        {
            collectFloatTargetIterationOrder.add(each);
            return 0.0f;
        }, new FloatHashBag());
        assertEquals(expectedIterationOrder, collectFloatTargetIterationOrder);

        MutableCollection<Integer> collectIntIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectInt((Integer each) ->
        {
            collectIntIterationOrder.add(each);
            return 0;
        }).forEach(each -> {
        });
        assertEquals(expectedIterationOrder, collectIntIterationOrder);

        MutableCollection<Integer> collectIntTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectInt((Integer each) ->
        {
            collectIntTargetIterationOrder.add(each);
            return 0;
        }, new IntHashBag());
        assertEquals(expectedIterationOrder, collectIntTargetIterationOrder);

        MutableCollection<Integer> collectLongIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectLong((Integer each) ->
        {
            collectLongIterationOrder.add(each);
            return 0L;
        }).forEach(each -> {
        });
        assertEquals(expectedIterationOrder, collectLongIterationOrder);

        MutableCollection<Integer> collectLongTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectLong((Integer each) ->
        {
            collectLongTargetIterationOrder.add(each);
            return 0L;
        }, new LongHashBag());
        assertEquals(expectedIterationOrder, collectLongTargetIterationOrder);

        MutableCollection<Integer> collectShortIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectShort((ShortFunction<Integer>) each -> {
            collectShortIterationOrder.add(each);
            return (short) 0;
        }).forEach(each -> {
        });
        assertEquals(expectedIterationOrder, collectShortIterationOrder);

        MutableCollection<Integer> collectShortTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().collectShort((ShortFunction<Integer>) each -> {
            collectShortTargetIterationOrder.add(each);
            return (short) 0;
        }, new ShortHashBag());
        assertEquals(expectedIterationOrder, collectShortTargetIterationOrder);

        MutableCollection<Integer> flatCollectIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().flatCollect(each -> Lists.immutable.with(flatCollectIterationOrder.add(each))).forEach(noop);
        assertEquals(expectedIterationOrder, flatCollectIterationOrder);

        MutableCollection<Integer> flatCollectTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().flatCollect(each -> Lists.immutable.with(flatCollectTargetIterationOrder.add(each)), new HashBag<>());
        assertEquals(expectedIterationOrder, flatCollectTargetIterationOrder);

        MutableCollection<Integer> countIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().count(countIterationOrder::add);
        assertEquals(expectedIterationOrder, countIterationOrder);

        MutableCollection<Integer> countWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().countWith((each, param) -> countWithIterationOrder.add(each), null);
        assertEquals(expectedIterationOrder, countWithIterationOrder);

        MutableCollection<Integer> anySatisfyIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().anySatisfy(each -> {
            anySatisfyIterationOrder.add(each);
            return false;
        });
        assertEquals(expectedIterationOrder, anySatisfyIterationOrder);

        MutableCollection<Integer> anySatisfyWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().anySatisfyWith((each, param) -> {
            anySatisfyWithIterationOrder.add(each);
            return false;
        }, null);
        assertEquals(expectedIterationOrder, anySatisfyWithIterationOrder);

        MutableCollection<Integer> allSatisfyIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().allSatisfy(each -> {
            allSatisfyIterationOrder.add(each);
            return true;
        });
        assertEquals(expectedIterationOrder, allSatisfyIterationOrder);

        MutableCollection<Integer> allSatisfyWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().allSatisfyWith((each, param) -> {
            allSatisfyWithIterationOrder.add(each);
            return true;
        }, null);
        assertEquals(expectedIterationOrder, allSatisfyWithIterationOrder);

        MutableCollection<Integer> noneSatisfyIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().noneSatisfy(each -> {
            noneSatisfyIterationOrder.add(each);
            return false;
        });
        assertEquals(expectedIterationOrder, noneSatisfyIterationOrder);

        MutableCollection<Integer> noneSatisfyWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().noneSatisfyWith((each, param) -> {
            noneSatisfyWithIterationOrder.add(each);
            return false;
        }, null);
        assertEquals(expectedIterationOrder, noneSatisfyWithIterationOrder);

        MutableCollection<Integer> detectIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().detect(each -> {
            detectIterationOrder.add(each);
            return false;
        });
        assertEquals(expectedIterationOrder, detectIterationOrder);

        MutableCollection<Integer> detectWithIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().detectWith((each, param) -> {
            detectWithIterationOrder.add(each);
            return false;
        }, null);
        assertEquals(expectedIterationOrder, detectWithIterationOrder);

        MutableCollection<Integer> detectIfNoneIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().detectIfNone(each -> {
            detectIfNoneIterationOrder.add(each);
            return false;
        }, () -> 0);
        assertEquals(expectedIterationOrder, detectIfNoneIterationOrder);

        MutableCollection<Integer> detectWithIfNoneIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().detectWithIfNone((each, param) -> {
            detectWithIfNoneIterationOrder.add(each);
            return false;
        }, null, () -> 0);
        assertEquals(expectedIterationOrder, detectWithIfNoneIterationOrder);

        MutableCollection<Integer> minComparatorIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().min((o1, o2) -> {
            if (minComparatorIterationOrder.isEmpty())
            {
                minComparatorIterationOrder.add(o2);
            }
            minComparatorIterationOrder.add(o1);
            return 0;
        });
        assertEquals(expectedIterationOrder, minComparatorIterationOrder);

        MutableCollection<Integer> maxComparatorIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().max((o1, o2) -> {
            if (maxComparatorIterationOrder.isEmpty())
            {
                maxComparatorIterationOrder.add(o2);
            }
            maxComparatorIterationOrder.add(o1);
            return 0;
        });
        assertEquals(expectedIterationOrder, maxComparatorIterationOrder);

        MutableCollection<Integer> minByIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().minBy(minByIterationOrder::add);
        assertEquals(expectedIterationOrder, minByIterationOrder);

        MutableCollection<Integer> maxByIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().maxBy(maxByIterationOrder::add);
        assertEquals(expectedIterationOrder, maxByIterationOrder);

        MutableCollection<Integer> groupByIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().groupBy(groupByIterationOrder::add);
        assertEquals(expectedIterationOrder, groupByIterationOrder);

        MutableCollection<Integer> groupByTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().groupBy(groupByTargetIterationOrder::add, new HashBagMultimap<>());
        assertEquals(expectedIterationOrder, groupByTargetIterationOrder);

        MutableCollection<Integer> groupByEachIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().groupByEach(each -> {
            groupByEachIterationOrder.add(each);
            return Lists.immutable.with(each);
        });
        assertEquals(expectedIterationOrder, groupByEachIterationOrder);

        MutableCollection<Integer> groupByEachTargetIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().groupByEach(each -> {
            groupByEachTargetIterationOrder.add(each);
            return Lists.immutable.with(each);
        }, new HashBagMultimap<>());
        assertEquals(expectedIterationOrder, groupByEachTargetIterationOrder);

        MutableCollection<Integer> sumOfFloatIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().sumOfFloat(each -> {
            sumOfFloatIterationOrder.add(each);
            return each.floatValue();
        });
        assertEquals(expectedIterationOrder, sumOfFloatIterationOrder);

        MutableCollection<Integer> sumOfDoubleIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().sumOfDouble(each -> {
            sumOfDoubleIterationOrder.add(each);
            return each.doubleValue();
        });
        assertEquals(expectedIterationOrder, sumOfDoubleIterationOrder);

        MutableCollection<Integer> sumOfIntIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().sumOfInt(each -> {
            sumOfIntIterationOrder.add(each);
            return each.intValue();
        });
        assertEquals(expectedIterationOrder, sumOfIntIterationOrder);

        MutableCollection<Integer> sumOfLongIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().sumOfLong(each -> {
            sumOfLongIterationOrder.add(each);
            return each.longValue();
        });
        assertEquals(expectedIterationOrder, sumOfLongIterationOrder);

        /*
         * TODO: Fix sumByDouble and sumByFloat methods for bags, to only iterate once per item, not per occurrence.
        MutableCollection<Integer> sumByDoubleIterationOrder1 = this.newMutableForFilter();
        MutableCollection<Integer> sumByDoubleIterationOrder2 = this.newMutableForFilter();
        this.getInstanceUnderTest().sumByDouble(
                each -> {
                    sumByDoubleIterationOrder1.add(each);
                    return each;
                },
                each -> {
                    sumByDoubleIterationOrder2.add(each);
                    return 0.0;
                });
        assertEquals(expectedIterationOrder, sumByDoubleIterationOrder1);
        assertEquals(expectedIterationOrder, sumByDoubleIterationOrder2);

        MutableCollection<Integer> sumByFloatIterationOrder1 = this.newMutableForFilter();
        MutableCollection<Integer> sumByFloatIterationOrder2 = this.newMutableForFilter();
        this.getInstanceUnderTest().sumByFloat(
                each -> {
                    sumByFloatIterationOrder1.add(each);
                    return each;
                },
                each -> {
                    sumByFloatIterationOrder2.add(each);
                    return 0.0f;
                });
        assertEquals(expectedIterationOrder, sumByFloatIterationOrder1);
        assertEquals(expectedIterationOrder, sumByFloatIterationOrder2);
        */

        MutableCollection<Integer> sumByIntIterationOrder1 = this.newMutableForFilter();
        MutableCollection<Integer> sumByIntIterationOrder2 = this.newMutableForFilter();
        this.getInstanceUnderTest().sumByInt(
                each -> {
                    sumByIntIterationOrder1.add(each);
                    return each;
                },
                each -> {
                    sumByIntIterationOrder2.add(each);
                    return 0;
                });
        assertEquals(expectedIterationOrder, sumByIntIterationOrder1);
        assertEquals(expectedIterationOrder, sumByIntIterationOrder2);

        MutableCollection<Integer> sumByLongIterationOrder1 = this.newMutableForFilter();
        MutableCollection<Integer> sumByLongIterationOrder2 = this.newMutableForFilter();
        this.getInstanceUnderTest().sumByLong(
                each -> {
                    sumByLongIterationOrder1.add(each);
                    return each;
                },
                each -> {
                    sumByLongIterationOrder2.add(each);
                    return 0L;
                });
        assertEquals(expectedIterationOrder, sumByLongIterationOrder1);
        assertEquals(expectedIterationOrder, sumByLongIterationOrder2);

        MutableCollection<Integer> expectedInjectIntoIterationOrder = this.allowsDuplicates()
                ? this.newMutableForFilter(4, 4, 4, 4, 3, 3, 3, 2, 2, 1)
                : expectedIterationOrder;

        MutableCollection<Integer> injectIntoIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().injectInto(0, (Function2<Integer, Integer, Integer>) (argument1, argument2) -> {
            injectIntoIterationOrder.add(argument2);
            return argument1 + argument2;
        });
        assertEquals(expectedInjectIntoIterationOrder, injectIntoIterationOrder);

        MutableCollection<Integer> injectIntoIntIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().injectInto(0, (IntObjectToIntFunction<Integer>) (intParameter, objectParameter) -> {
            injectIntoIntIterationOrder.add(objectParameter);
            return intParameter + objectParameter;
        });
        assertEquals(expectedInjectIntoIterationOrder, injectIntoIntIterationOrder);

        MutableCollection<Integer> injectIntoLongIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().injectInto(0L, (LongObjectToLongFunction<Integer>) (longParameter, objectParameter) -> {
            injectIntoLongIterationOrder.add(objectParameter);
            return longParameter + objectParameter;
        });
        assertEquals(expectedInjectIntoIterationOrder, injectIntoLongIterationOrder);

        MutableCollection<Integer> injectIntoDoubleIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().injectInto(0L, (DoubleObjectToDoubleFunction<Integer>) (doubleParameter, objectParameter) -> {
            injectIntoDoubleIterationOrder.add(objectParameter);
            return doubleParameter + objectParameter;
        });
        assertEquals(expectedInjectIntoIterationOrder, injectIntoDoubleIterationOrder);

        MutableCollection<Integer> injectIntoFloatIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().injectInto(0L, (FloatObjectToFloatFunction<Integer>) (floatParameter, objectParameter) -> {
            injectIntoFloatIterationOrder.add(objectParameter);
            return floatParameter + objectParameter;
        });
        assertEquals(expectedInjectIntoIterationOrder, injectIntoFloatIterationOrder);

        Counter toSortedListCount = new Counter();
        this.getInstanceUnderTest().toSortedList((o1, o2) -> {
            toSortedListCount.increment();
            return 0;
        });
        assertEquals(expectedIterationOrder.size() - 1, toSortedListCount.getCount());

        /*
        MutableCollection<Integer> toSortedListByIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().toSortedListBy(toSortedListByIterationOrder::add);
        assertEquals(expectedIterationOrder.size(), toSortedListByIterationOrder.size());
        */

        Counter toSortedSetCount = new Counter();
        this.getInstanceUnderTest().toSortedSet((o1, o2) -> {
            toSortedSetCount.increment();
            return 0;
        });
        assertEquals(expectedIterationOrder.size(), toSortedSetCount.getCount());

        /*
        MutableCollection<Integer> toSortedSetByIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().toSortedSetBy(toSortedSetByIterationOrder::add);
        assertEquals(expectedIterationOrder.size(), toSortedSetByIterationOrder.size());
        */

        Counter toSortedBagCount = new Counter();
        this.getInstanceUnderTest().toSortedBag((o1, o2) -> {
            toSortedBagCount.increment();
            return 0;
        });
        assertEquals(expectedIterationOrder.size(), toSortedBagCount.getCount());

        /*
        MutableCollection<Integer> toSortedBagByIterationOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().toSortedBagBy(toSortedBagByIterationOrder::add);
        assertEquals(expectedIterationOrder.size(), toSortedBagByIterationOrder.size());
        */

        MutableCollection<Integer> summarizeIntOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().summarizeInt(each -> {
            summarizeIntOrder.add(each);
            return 0;
        });
        assertEquals(expectedIterationOrder, summarizeIntOrder);

        MutableCollection<Integer> summarizeFloatOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().summarizeFloat(each -> {
            summarizeFloatOrder.add(each);
            return 0;
        });
        assertEquals(expectedIterationOrder, summarizeFloatOrder);

        MutableCollection<Integer> summarizeLongOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().summarizeLong(each -> {
            summarizeLongOrder.add(each);
            return 0;
        });
        assertEquals(expectedIterationOrder, summarizeLongOrder);

        MutableCollection<Integer> summarizeDoubleOrder = this.newMutableForFilter();
        this.getInstanceUnderTest().summarizeDouble(each -> {
            summarizeDoubleOrder.add(each);
            return 0;
        });
        assertEquals(expectedIterationOrder, summarizeDoubleOrder);
    }

    default MutableCollection<Integer> expectedIterationOrder()
    {
        MutableCollection<Integer> forEach = this.newMutableForFilter();
        this.getInstanceUnderTest().each(forEach::add);
        return forEach;
    }

    default RichIterable<Integer> getInstanceUnderTest()
    {
        return this.allowsDuplicates()
                ? this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1)
                : this.newWith(4, 3, 2, 1);
    }

    @Test
    default void RichIterable_select_reject()
    {
        RichIterable<Integer> iterable = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);

        assertEquals(
                this.getExpectedFiltered(4, 4, 4, 4, 2, 2),
                iterable.select(IntegerPredicates.isEven()));

        {
            MutableCollection<Integer> target = this.newMutableForFilter();
            MutableCollection<Integer> result = iterable.select(IntegerPredicates.isEven(), target);
            assertEquals(this.newMutableForFilter(4, 4, 4, 4, 2, 2), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedFiltered(4, 4, 4, 4, 3, 3, 3),
                iterable.selectWith(Predicates2.greaterThan(), 2));

        {
            MutableCollection<Integer> target = this.newMutableForFilter();
            MutableCollection<Integer> result = iterable.selectWith(Predicates2.greaterThan(), 2, target);
            assertEquals(this.newMutableForFilter(4, 4, 4, 4, 3, 3, 3), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedFiltered(4, 4, 4, 4, 2, 2),
                iterable.reject(IntegerPredicates.isOdd()));

        {
            MutableCollection<Integer> target = this.newMutableForFilter();
            MutableCollection<Integer> result = iterable.reject(IntegerPredicates.isOdd(), target);
            assertEquals(this.newMutableForFilter(4, 4, 4, 4, 2, 2), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedFiltered(4, 4, 4, 4, 3, 3, 3),
                iterable.rejectWith(Predicates2.lessThan(), 3));

        MutableCollection<Integer> target = this.newMutableForFilter();
        MutableCollection<Integer> result = iterable.rejectWith(Predicates2.lessThan(), 3, target);
        assertEquals(this.newMutableForFilter(4, 4, 4, 4, 3, 3, 3), result);
        assertSame(target, result);
    }

    @Test
    default void RichIterable_partition()
    {
        RichIterable<Integer> iterable = this.newWith(-3, -3, -3, -2, -2, -1, 0, 1, 2, 2, 3, 3, 3);
        PartitionIterable<Integer> partition = iterable.partition(IntegerPredicates.isEven());
        assertEquals(this.getExpectedFiltered(-2, -2, 0, 2, 2), partition.getSelected());
        assertEquals(this.getExpectedFiltered(-3, -3, -3, -1, 1, 3, 3, 3), partition.getRejected());

        PartitionIterable<Integer> partitionWith = iterable.partitionWith(Predicates2.greaterThan(), 0);
        assertEquals(this.getExpectedFiltered(1, 2, 2, 3, 3, 3), partitionWith.getSelected());
        assertEquals(this.getExpectedFiltered(-3, -3, -3, -2, -2, -1, 0), partitionWith.getRejected());
    }

    @Test
    default void RichIterable_selectInstancesOf()
    {
        RichIterable<Number> iterable = this.newWith(1, 2.0, 2.0, 3, 3, 3, 4.0, 4.0, 4.0, 4.0);
        assertEquals(this.getExpectedFiltered(1, 3, 3, 3), iterable.selectInstancesOf(Integer.class));
        assertEquals(this.getExpectedFiltered(1, 2.0, 2.0, 3, 3, 3, 4.0, 4.0, 4.0, 4.0), iterable.selectInstancesOf(Number.class));
    }

    @Test
    default void RichIterable_collect()
    {
        RichIterable<Integer> iterable = this.newWith(13, 13, 12, 12, 11, 11, 3, 3, 2, 2, 1, 1);

        assertEquals(
                this.getExpectedTransformed(3, 3, 2, 2, 1, 1, 3, 3, 2, 2, 1, 1),
                iterable.collect(i -> i % 10));

        {
            MutableCollection<Integer> target = this.newMutableForTransform();
            MutableCollection<Integer> result = iterable.collect(i -> i % 10, target);
            assertEquals(this.newMutableForTransform(3, 3, 2, 2, 1, 1, 3, 3, 2, 2, 1, 1), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedTransformed(3, 3, 2, 2, 1, 1, 3, 3, 2, 2, 1, 1),
                iterable.collectWith((i, mod) -> i % mod, 10));

        MutableCollection<Integer> target = this.newMutableForTransform();
        MutableCollection<Integer> result = iterable.collectWith((i, mod) -> i % mod, 10, target);
        assertEquals(this.newMutableForTransform(3, 3, 2, 2, 1, 1, 3, 3, 2, 2, 1, 1), result);
        assertSame(target, result);
    }

    @Test
    default void RichIterable_collectIf()
    {
        RichIterable<Integer> iterable = this.newWith(13, 13, 12, 12, 11, 11, 3, 3, 2, 2, 1, 1);

        assertEquals(
                this.getExpectedTransformed(3, 3, 1, 1, 3, 3, 1, 1),
                iterable.collectIf(i -> i % 2 != 0, i -> i % 10));

        MutableCollection<Integer> target = this.newMutableForTransform();
        MutableCollection<Integer> result = iterable.collectIf(i -> i % 2 != 0, i -> i % 10, target);
        assertEquals(this.newMutableForTransform(3, 3, 1, 1, 3, 3, 1, 1), result);
        assertSame(target, result);
    }

    @Test
    default void RichIterable_collectPrimitive()
    {
        assertEquals(
                this.getExpectedBoolean(false, false, true, true, false, false),
                this.newWith(3, 3, 2, 2, 1, 1).collectBoolean(each -> each % 2 == 0));

        {
            MutableBooleanCollection target = this.newBooleanForTransform();
            MutableBooleanCollection result = this.newWith(3, 3, 2, 2, 1, 1).collectBoolean(each -> each % 2 == 0, target);
            assertEquals(this.newBooleanForTransform(false, false, true, true, false, false), result);
            assertSame(target, result);
        }

        RichIterable<Integer> iterable = this.newWith(13, 13, 12, 12, 11, 11, 3, 3, 2, 2, 1, 1);

        assertEquals(
                this.getExpectedByte((byte) 3, (byte) 3, (byte) 2, (byte) 2, (byte) 1, (byte) 1, (byte) 3, (byte) 3, (byte) 2, (byte) 2, (byte) 1, (byte) 1),
                iterable.collectByte(each -> (byte) (each % 10)));

        {
            MutableByteCollection target = this.newByteForTransform();
            MutableByteCollection result = iterable.collectByte(each -> (byte) (each % 10), target);
            assertEquals(this.newByteForTransform((byte) 3, (byte) 3, (byte) 2, (byte) 2, (byte) 1, (byte) 1, (byte) 3, (byte) 3, (byte) 2, (byte) 2, (byte) 1, (byte) 1), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedChar((char) 3, (char) 3, (char) 2, (char) 2, (char) 1, (char) 1, (char) 3, (char) 3, (char) 2, (char) 2, (char) 1, (char) 1),
                iterable.collectChar(each -> (char) (each % 10)));

        {
            MutableCharCollection target = this.newCharForTransform();
            MutableCharCollection result = iterable.collectChar(each -> (char) (each % 10), target);
            assertEquals(this.newCharForTransform((char) 3, (char) 3, (char) 2, (char) 2, (char) 1, (char) 1, (char) 3, (char) 3, (char) 2, (char) 2, (char) 1, (char) 1), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedDouble(3.0, 3.0, 2.0, 2.0, 1.0, 1.0, 3.0, 3.0, 2.0, 2.0, 1.0, 1.0),
                iterable.collectDouble(each -> (double) (each % 10)));

        {
            MutableDoubleCollection target = this.newDoubleForTransform();
            MutableDoubleCollection result = iterable.collectDouble(each -> (double) (each % 10), target);
            assertEquals(this.newDoubleForTransform(3.0, 3.0, 2.0, 2.0, 1.0, 1.0, 3.0, 3.0, 2.0, 2.0, 1.0, 1.0), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedFloat(3.0f, 3.0f, 2.0f, 2.0f, 1.0f, 1.0f, 3.0f, 3.0f, 2.0f, 2.0f, 1.0f, 1.0f),
                iterable.collectFloat(each -> (float) (each % 10)));

        {
            MutableFloatCollection target = this.newFloatForTransform();
            MutableFloatCollection result = iterable.collectFloat(each -> (float) (each % 10), target);
            assertEquals(this.newFloatForTransform(3.0f, 3.0f, 2.0f, 2.0f, 1.0f, 1.0f, 3.0f, 3.0f, 2.0f, 2.0f, 1.0f, 1.0f), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedInt(3, 3, 2, 2, 1, 1, 3, 3, 2, 2, 1, 1),
                iterable.collectInt(each -> each % 10));

        {
            MutableIntCollection target = this.newIntForTransform();
            MutableIntCollection result = iterable.collectInt(each -> each % 10, target);
            assertEquals(this.newIntForTransform(3, 3, 2, 2, 1, 1, 3, 3, 2, 2, 1, 1), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedLong(3, 3, 2, 2, 1, 1, 3, 3, 2, 2, 1, 1),
                iterable.collectLong(each -> each % 10));

        {
            MutableLongCollection target = this.newLongForTransform();
            MutableLongCollection result = iterable.collectLong(each -> each % 10, target);
            assertEquals(this.newLongForTransform(3, 3, 2, 2, 1, 1, 3, 3, 2, 2, 1, 1), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedShort((short) 3, (short) 3, (short) 2, (short) 2, (short) 1, (short) 1, (short) 3, (short) 3, (short) 2, (short) 2, (short) 1, (short) 1),
                iterable.collectShort(each -> (short) (each % 10)));

        MutableShortCollection target = this.newShortForTransform();
        MutableShortCollection result = iterable.collectShort(each -> (short) (each % 10), target);
        assertEquals(this.newShortForTransform((short) 3, (short) 3, (short) 2, (short) 2, (short) 1, (short) 1, (short) 3, (short) 3, (short) 2, (short) 2, (short) 1, (short) 1), result);
        assertSame(target, result);
    }

    @Test
    default void RichIterable_flatCollect()
    {
        assertEquals(
                this.getExpectedTransformed(1, 2, 3, 1, 2, 1, 2, 1),
                this.newWith(3, 2, 2, 1).flatCollect(Interval::oneTo));

        assertEquals(
                this.newMutableForTransform(1, 2, 3, 1, 2, 1, 2, 1),
                this.newWith(3, 2, 2, 1).flatCollect(Interval::oneTo, this.newMutableForTransform()));

        assertEquals(
                this.getExpectedTransformed(3, 4, 5, 2, 3, 4, 5, 2, 3, 4, 5, 1, 2, 3, 4, 5),
                this.newWith(3, 2, 2, 1).flatCollectWith(Interval::fromTo, 5));

        assertEquals(
                this.newMutableForTransform(3, 2, 1, 2, 1, 2, 1, 1),
                this.newWith(3, 2, 2, 1).flatCollectWith(Interval::fromTo, 1, this.newMutableForTransform()));
    }

    @Test
    default void RichIterable_flatCollect_primitive()
    {
        {
            MutableBooleanCollection target = this.newBooleanForTransform();
            MutableBooleanCollection result = this.newWith(3, 3, 2, 2, 1, 1).flatCollectBoolean(
                    each -> BooleanLists.immutable.with(each % 2 == 0, each % 2 == 0),
                    target);
            assertEquals(this.newBooleanForTransform(false, false, false, false, true, true, true, true, false, false, false, false), result);
            assertSame(target, result);
        }

        RichIterable<Integer> iterable = this.newWith(13, 13, 12, 12, 11, 11, 3, 3, 2, 2, 1, 1);

        {
            MutableByteCollection target = this.newByteForTransform();
            MutableByteCollection result = iterable.flatCollectByte(
                    each -> ByteLists.immutable.with((byte) (each % 10), (byte) (each % 10)),
                    target);
            assertEquals(
                    this.newByteForTransform((byte) 3, (byte) 3, (byte) 3, (byte) 3, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 3, (byte) 3, (byte) 3, (byte) 3, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 1, (byte) 1, (byte) 1, (byte) 1),
                    result);
            assertSame(target, result);
        }

        {
            MutableCharCollection target = this.newCharForTransform();
            MutableCharCollection result = iterable.flatCollectChar(
                    each -> CharLists.immutable.with((char) (each % 10), (char) (each % 10)),
                    target);
            assertEquals(
                    this.newCharForTransform((char) 3, (char) 3, (char) 3, (char) 3, (char) 2, (char) 2, (char) 2, (char) 2, (char) 1, (char) 1, (char) 1, (char) 1, (char) 3, (char) 3, (char) 3, (char) 3, (char) 2, (char) 2, (char) 2, (char) 2, (char) 1, (char) 1, (char) 1, (char) 1),
                    result);
            assertSame(target, result);
        }

        {
            MutableDoubleCollection target = this.newDoubleForTransform();
            MutableDoubleCollection result = iterable.flatCollectDouble(
                    each -> DoubleLists.immutable.with((double) (each % 10), (double) (each % 10)),
                    target);
            assertEquals(
                    this.newDoubleForTransform(3.0, 3.0, 3.0, 3.0, 2.0, 2.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 3.0, 3.0, 3.0, 3.0, 2.0, 2.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0),
                    result);
            assertSame(target, result);
        }

        {
            MutableFloatCollection target = this.newFloatForTransform();
            MutableFloatCollection result = iterable.flatCollectFloat(
                    each -> FloatLists.immutable.with((float) (each % 10), (float) (each % 10)),
                    target);
            assertEquals(
                    this.newFloatForTransform(3.0f, 3.0f, 3.0f, 3.0f, 2.0f, 2.0f, 2.0f, 2.0f, 1.0f, 1.0f, 1.0f, 1.0f, 3.0f, 3.0f, 3.0f, 3.0f, 2.0f, 2.0f, 2.0f, 2.0f, 1.0f, 1.0f, 1.0f, 1.0f),
                    result);
            assertSame(target, result);
        }

        {
            MutableIntCollection target = this.newIntForTransform();
            MutableIntCollection result = iterable.flatCollectInt(
                    each -> IntLists.immutable.with(each % 10, each % 10),
                    target);
            assertEquals(
                    this.newIntForTransform(3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1),
                    result);
            assertSame(target, result);
        }

        {
            MutableLongCollection target = this.newLongForTransform();
            MutableLongCollection result = iterable.flatCollectLong(
                    each -> LongLists.immutable.with(each % 10, each % 10),
                    target);
            assertEquals(
                    this.newLongForTransform(3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1),
                    result);
            assertSame(target, result);
        }

        {
            MutableShortCollection target = this.newShortForTransform();
            MutableShortCollection result = iterable.flatCollectShort(
                    each -> ShortLists.immutable.with((short) (each % 10), (short) (each % 10)),
                    target);
            assertEquals(
                    this.newShortForTransform((short) 3, (short) 3, (short) 3, (short) 3, (short) 2, (short) 2, (short) 2, (short) 2, (short) 1, (short) 1, (short) 1, (short) 1, (short) 3, (short) 3, (short) 3, (short) 3, (short) 2, (short) 2, (short) 2, (short) 2, (short) 1, (short) 1, (short) 1, (short) 1),
                    result);
            assertSame(target, result);
        }
    }

    @Test
    default void RichIterable_count()
    {
        RichIterable<Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);

        assertEquals(3, iterable.count(Integer.valueOf(3)::equals));
        assertEquals(2, iterable.count(Integer.valueOf(2)::equals));
        assertEquals(1, iterable.count(Integer.valueOf(1)::equals));
        assertEquals(0, iterable.count(Integer.valueOf(0)::equals));
        assertEquals(4, iterable.count(i -> i % 2 != 0));
        assertEquals(6, iterable.count(i -> i > 0));

        assertEquals(3, iterable.countWith(Object::equals, 3));
        assertEquals(2, iterable.countWith(Object::equals, 2));
        assertEquals(1, iterable.countWith(Object::equals, 1));
        assertEquals(0, iterable.countWith(Object::equals, 0));
        assertEquals(6, iterable.countWith(Predicates2.greaterThan(), 0));
    }

    @Test
    default void RichIterable_anySatisfy_allSatisfy_noneSatisfy()
    {
        {
            RichIterable<Integer> iterable = this.newWith(3, 2, 1);

            assertTrue(iterable.anySatisfy(Predicates.greaterThan(0)));
            assertTrue(iterable.anySatisfy(Predicates.greaterThan(1)));
            assertTrue(iterable.anySatisfy(Predicates.greaterThan(2)));
            assertFalse(iterable.anySatisfy(Predicates.greaterThan(3)));

            assertTrue(iterable.anySatisfyWith(Predicates2.greaterThan(), 0));
            assertTrue(iterable.anySatisfyWith(Predicates2.greaterThan(), 1));
            assertTrue(iterable.anySatisfyWith(Predicates2.greaterThan(), 2));
            assertFalse(iterable.anySatisfyWith(Predicates2.greaterThan(), 3));

            assertTrue(iterable.allSatisfy(Predicates.greaterThan(0)));
            assertFalse(iterable.allSatisfy(Predicates.greaterThan(1)));
            assertFalse(iterable.allSatisfy(Predicates.greaterThan(2)));
            assertFalse(iterable.allSatisfy(Predicates.greaterThan(3)));

            assertTrue(iterable.allSatisfyWith(Predicates2.greaterThan(), 0));
            assertFalse(iterable.allSatisfyWith(Predicates2.greaterThan(), 1));
            assertFalse(iterable.allSatisfyWith(Predicates2.greaterThan(), 2));
            assertFalse(iterable.allSatisfyWith(Predicates2.greaterThan(), 3));

            assertFalse(iterable.noneSatisfy(Predicates.greaterThan(0)));
            assertFalse(iterable.noneSatisfy(Predicates.greaterThan(1)));
            assertFalse(iterable.noneSatisfy(Predicates.greaterThan(2)));
            assertTrue(iterable.noneSatisfy(Predicates.greaterThan(3)));

            assertFalse(iterable.noneSatisfyWith(Predicates2.greaterThan(), 0));
            assertFalse(iterable.noneSatisfyWith(Predicates2.greaterThan(), 1));
            assertFalse(iterable.noneSatisfyWith(Predicates2.greaterThan(), 2));
            assertTrue(iterable.noneSatisfyWith(Predicates2.greaterThan(), 3));
        }

        {
            RichIterable<Integer> iterable = this.newWith(2, 1);

            assertTrue(iterable.anySatisfy(Predicates.greaterThan(0)));
            assertTrue(iterable.anySatisfy(Predicates.greaterThan(1)));
            assertFalse(iterable.anySatisfy(Predicates.greaterThan(2)));

            assertTrue(iterable.anySatisfyWith(Predicates2.greaterThan(), 0));
            assertTrue(iterable.anySatisfyWith(Predicates2.greaterThan(), 1));
            assertFalse(iterable.anySatisfyWith(Predicates2.greaterThan(), 2));

            assertTrue(iterable.allSatisfy(Predicates.greaterThan(0)));
            assertFalse(iterable.allSatisfy(Predicates.greaterThan(1)));
            assertFalse(iterable.allSatisfy(Predicates.greaterThan(2)));

            assertTrue(iterable.allSatisfyWith(Predicates2.greaterThan(), 0));
            assertFalse(iterable.allSatisfyWith(Predicates2.greaterThan(), 1));
            assertFalse(iterable.allSatisfyWith(Predicates2.greaterThan(), 2));

            assertFalse(iterable.noneSatisfy(Predicates.greaterThan(0)));
            assertFalse(iterable.noneSatisfy(Predicates.greaterThan(1)));
            assertTrue(iterable.noneSatisfy(Predicates.greaterThan(2)));

            assertFalse(iterable.noneSatisfyWith(Predicates2.greaterThan(), 0));
            assertFalse(iterable.noneSatisfyWith(Predicates2.greaterThan(), 1));
            assertTrue(iterable.noneSatisfyWith(Predicates2.greaterThan(), 2));
        }

        {
            RichIterable<Integer> iterable = this.newWith(1);

            assertTrue(iterable.anySatisfy(Predicates.greaterThan(0)));
            assertFalse(iterable.anySatisfy(Predicates.greaterThan(1)));

            assertTrue(iterable.anySatisfyWith(Predicates2.greaterThan(), 0));
            assertFalse(iterable.anySatisfyWith(Predicates2.greaterThan(), 1));

            assertTrue(iterable.allSatisfy(Predicates.greaterThan(0)));
            assertFalse(iterable.allSatisfy(Predicates.greaterThan(1)));

            assertTrue(iterable.allSatisfyWith(Predicates2.greaterThan(), 0));
            assertFalse(iterable.allSatisfyWith(Predicates2.greaterThan(), 1));

            assertFalse(iterable.noneSatisfy(Predicates.greaterThan(0)));
            assertTrue(iterable.noneSatisfy(Predicates.greaterThan(1)));

            assertFalse(iterable.noneSatisfyWith(Predicates2.greaterThan(), 0));
            assertTrue(iterable.noneSatisfyWith(Predicates2.greaterThan(), 1));
        }

        RichIterable<Integer> iterable = this.newWith();

        Predicate<Integer> throwPredicate = each -> {
            throw new AssertionError();
        };
        Predicate2<Integer, Object> throwPredicate2 = (each, parameter) -> {
            throw new AssertionError();
        };

        assertFalse(iterable.anySatisfy(throwPredicate));
        assertFalse(iterable.anySatisfyWith(throwPredicate2, null));

        assertTrue(iterable.allSatisfy(throwPredicate));
        assertTrue(iterable.allSatisfyWith(throwPredicate2, null));

        assertTrue(iterable.noneSatisfy(throwPredicate));
        assertTrue(iterable.noneSatisfyWith(throwPredicate2, null));
    }

    @Test
    default void RichIterable_detect()
    {
        RichIterable<Integer> iterable = this.newWith(3, 2, 1);

        assertThat(iterable.detect(Predicates.greaterThan(0)), is(3));
        assertThat(iterable.detect(Predicates.greaterThan(1)), is(3));
        assertThat(iterable.detect(Predicates.greaterThan(2)), is(3));
        assertThat(iterable.detect(Predicates.greaterThan(3)), nullValue());

        assertThat(iterable.detect(Predicates.lessThan(1)), nullValue());
        assertThat(iterable.detect(Predicates.lessThan(2)), is(1));
        assertThat(iterable.detect(Predicates.lessThan(3)), is(2));
        assertThat(iterable.detect(Predicates.lessThan(4)), is(3));

        assertThat(iterable.detectWith(Predicates2.greaterThan(), 0), is(3));
        assertThat(iterable.detectWith(Predicates2.greaterThan(), 1), is(3));
        assertThat(iterable.detectWith(Predicates2.greaterThan(), 2), is(3));
        assertThat(iterable.detectWith(Predicates2.greaterThan(), 3), nullValue());

        assertThat(iterable.detectWith(Predicates2.lessThan(), 1), nullValue());
        assertThat(iterable.detectWith(Predicates2.lessThan(), 2), is(1));
        assertThat(iterable.detectWith(Predicates2.lessThan(), 3), is(2));
        assertThat(iterable.detectWith(Predicates2.lessThan(), 4), is(3));

        assertThat(iterable.detectIfNone(Predicates.greaterThan(0), () -> 4), is(3));
        assertThat(iterable.detectIfNone(Predicates.greaterThan(1), () -> 4), is(3));
        assertThat(iterable.detectIfNone(Predicates.greaterThan(2), () -> 4), is(3));
        assertThat(iterable.detectIfNone(Predicates.greaterThan(3), () -> 4), is(4));

        assertThat(iterable.detectIfNone(Predicates.lessThan(1), () -> 4), is(4));
        assertThat(iterable.detectIfNone(Predicates.lessThan(2), () -> 4), is(1));
        assertThat(iterable.detectIfNone(Predicates.lessThan(3), () -> 4), is(2));
        assertThat(iterable.detectIfNone(Predicates.lessThan(4), () -> 4), is(3));

        assertThat(iterable.detectWithIfNone(Predicates2.greaterThan(), 0, () -> 4), is(3));
        assertThat(iterable.detectWithIfNone(Predicates2.greaterThan(), 1, () -> 4), is(3));
        assertThat(iterable.detectWithIfNone(Predicates2.greaterThan(), 2, () -> 4), is(3));
        assertThat(iterable.detectWithIfNone(Predicates2.greaterThan(), 3, () -> 4), is(4));

        assertThat(iterable.detectWithIfNone(Predicates2.lessThan(), 1, () -> 4), is(4));
        assertThat(iterable.detectWithIfNone(Predicates2.lessThan(), 2, () -> 4), is(1));
        assertThat(iterable.detectWithIfNone(Predicates2.lessThan(), 3, () -> 4), is(2));
        assertThat(iterable.detectWithIfNone(Predicates2.lessThan(), 4, () -> 4), is(3));

        assertThat(iterable.detectOptional(Predicates.greaterThan(0)), is(Optional.of(3)));
        assertThat(iterable.detectOptional(Predicates.greaterThan(1)), is(Optional.of(3)));
        assertThat(iterable.detectOptional(Predicates.greaterThan(2)), is(Optional.of(3)));
        assertThat(iterable.detectOptional(Predicates.greaterThan(3)), is(Optional.empty()));

        assertThat(iterable.detectOptional(Predicates.lessThan(1)), is(Optional.empty()));
        assertThat(iterable.detectOptional(Predicates.lessThan(2)), is(Optional.of(1)));
        assertThat(iterable.detectOptional(Predicates.lessThan(3)), is(Optional.of(2)));
        assertThat(iterable.detectOptional(Predicates.lessThan(4)), is(Optional.of(3)));

        assertThat(iterable.detectWithOptional(Predicates2.greaterThan(), 0), is(Optional.of(3)));
        assertThat(iterable.detectWithOptional(Predicates2.greaterThan(), 1), is(Optional.of(3)));
        assertThat(iterable.detectWithOptional(Predicates2.greaterThan(), 2), is(Optional.of(3)));
        assertThat(iterable.detectWithOptional(Predicates2.greaterThan(), 3), is(Optional.empty()));

        assertThat(iterable.detectWithOptional(Predicates2.lessThan(), 1), is(Optional.empty()));
        assertThat(iterable.detectWithOptional(Predicates2.lessThan(), 2), is(Optional.of(1)));
        assertThat(iterable.detectWithOptional(Predicates2.lessThan(), 3), is(Optional.of(2)));
        assertThat(iterable.detectWithOptional(Predicates2.lessThan(), 4), is(Optional.of(3)));
    }

    @Test
    default void RichIterable_detectOptionalNull()
    {
        RichIterable<Integer> iterable = this.newWith(1, null, 3);

        assertThrows(NullPointerException.class, () -> iterable.detectOptional(Objects::isNull));
        assertThrows(NullPointerException.class, () -> iterable.detectWithOptional((i, object) -> i == object, null));
    }

    @Test
    default void RichIterable_min_max()
    {
        assertEquals(Integer.valueOf(-1), this.newWith(-1, 0, 1).min());
        assertEquals(Integer.valueOf(-1), this.newWith(1, 0, -1).min());
        assertThrows(NoSuchElementException.class, () -> this.newWith().min());

        assertEquals(Integer.valueOf(1), this.newWith(-1, 0, 1).max());
        assertEquals(Integer.valueOf(1), this.newWith(1, 0, -1).max());
        assertThrows(NoSuchElementException.class, () -> this.newWith().max());

        assertEquals(Integer.valueOf(1), this.newWith(-1, 0, 1).min(Comparators.reverseNaturalOrder()));
        assertEquals(Integer.valueOf(1), this.newWith(1, 0, -1).min(Comparators.reverseNaturalOrder()));
        assertThrows(NoSuchElementException.class, () -> this.newWith().min(Comparators.reverseNaturalOrder()));

        assertEquals(Integer.valueOf(-1), this.newWith(-1, 0, 1).max(Comparators.reverseNaturalOrder()));
        assertEquals(Integer.valueOf(-1), this.newWith(1, 0, -1).max(Comparators.reverseNaturalOrder()));
        assertThrows(NoSuchElementException.class, () -> this.newWith().max(Comparators.reverseNaturalOrder()));
    }

    @Test
    default void RichIterable_min_max_non_comparable()
    {
        Object sentinel = new Object();

        assertSame(sentinel, this.newWith(sentinel).min());
        assertThrows(ClassCastException.class, () -> this.newWith(new Object(), new Object()).min());

        assertSame(sentinel, this.newWith(sentinel).max());
        assertThrows(ClassCastException.class, () -> this.newWith(new Object(), new Object()).max());

        assertSame(sentinel, this.newWith(sentinel).min(Comparators.reverseNaturalOrder()));
        assertThrows(ClassCastException.class, () -> this.newWith(new Object(), new Object()).min(Comparators.reverseNaturalOrder()));

        assertSame(sentinel, this.newWith(sentinel).max(Comparators.reverseNaturalOrder()));
        assertThrows(ClassCastException.class, () -> this.newWith(new Object(), new Object()).max(Comparators.reverseNaturalOrder()));
    }

    @Test
    default void RichIterable_minOptional_maxOptional()
    {
        assertEquals(Optional.of(Integer.valueOf(-1)), this.newWith(-1, 0, 1).minOptional());
        assertEquals(Optional.of(Integer.valueOf(-1)), this.newWith(1, 0, -1).minOptional());
        assertSame(Optional.empty(), this.newWith().minOptional());
        assertThrows(NullPointerException.class, () -> this.newWith(new Object[]{null}).minOptional());

        assertEquals(Optional.of(Integer.valueOf(1)), this.newWith(-1, 0, 1).maxOptional());
        assertEquals(Optional.of(Integer.valueOf(1)), this.newWith(1, 0, -1).maxOptional());
        assertSame(Optional.empty(), this.newWith().maxOptional());
        assertThrows(NullPointerException.class, () -> this.newWith(new Object[]{null}).maxOptional());

        assertEquals(Optional.of(Integer.valueOf(1)), this.newWith(-1, 0, 1).minOptional(Comparators.reverseNaturalOrder()));
        assertEquals(Optional.of(Integer.valueOf(1)), this.newWith(1, 0, -1).minOptional(Comparators.reverseNaturalOrder()));
        assertSame(Optional.empty(), this.newWith().minOptional(Comparators.reverseNaturalOrder()));
        assertThrows(NullPointerException.class, () -> this.newWith(new Object[]{null}).minOptional(Comparators.reverseNaturalOrder()));

        assertEquals(Optional.of(Integer.valueOf(-1)), this.newWith(-1, 0, 1).maxOptional(Comparators.reverseNaturalOrder()));
        assertEquals(Optional.of(Integer.valueOf(-1)), this.newWith(1, 0, -1).maxOptional(Comparators.reverseNaturalOrder()));
        assertSame(Optional.empty(), this.newWith().maxOptional(Comparators.reverseNaturalOrder()));
        assertThrows(NullPointerException.class, () -> this.newWith(new Object[]{null}).maxOptional(Comparators.reverseNaturalOrder()));
    }

    @Test
    default void RichIterable_minOptional_maxOptional_non_comparable()
    {
        Object sentinel = new Object();

        assertEquals(Optional.of(sentinel), this.newWith(sentinel).minOptional());
        assertThrows(ClassCastException.class, () -> this.newWith(new Object(), new Object()).minOptional());

        assertEquals(Optional.of(sentinel), this.newWith(sentinel).maxOptional());
        assertThrows(ClassCastException.class, () -> this.newWith(new Object(), new Object()).maxOptional());

        assertEquals(Optional.of(sentinel), this.newWith(sentinel).minOptional(Comparators.reverseNaturalOrder()));
        assertThrows(ClassCastException.class, () -> this.newWith(new Object(), new Object()).minOptional(Comparators.reverseNaturalOrder()));

        assertEquals(Optional.of(sentinel), this.newWith(sentinel).maxOptional(Comparators.reverseNaturalOrder()));
        assertThrows(ClassCastException.class, () -> this.newWith(new Object(), new Object()).maxOptional(Comparators.reverseNaturalOrder()));
    }

    @Test
    default void RichIterable_minBy_maxBy()
    {
        assertEquals("da", this.newWith("ed", "da", "ca", "bc", "ab").minBy(string -> string.charAt(string.length() - 1)));
        assertThrows(NoSuchElementException.class, () -> this.<String>newWith().minBy(string -> string.charAt(string.length() - 1)));

        assertEquals("dz", this.newWith("ew", "dz", "cz", "bx", "ay").maxBy(string -> string.charAt(string.length() - 1)));
        assertThrows(NoSuchElementException.class, () -> this.<String>newWith().maxBy(string -> string.charAt(string.length() - 1)));
    }

    @Test
    default void RichIterable_minByOptional_maxByOptional()
    {
        assertEquals(Optional.of("da"), this.newWith("ed", "da", "ca", "bc", "ab").minByOptional(string -> string.charAt(string.length() - 1)));
        assertSame(Optional.empty(), this.<String>newWith().minByOptional(string -> string.charAt(string.length() - 1)));
        assertThrows(NullPointerException.class, () -> this.newWith(new Object[]{null}).minByOptional(Objects::isNull));

        assertEquals(Optional.of("dz"), this.newWith("ew", "dz", "cz", "bx", "ay").maxByOptional(string -> string.charAt(string.length() - 1)));
        assertSame(Optional.empty(), this.<String>newWith().maxByOptional(string -> string.charAt(string.length() - 1)));
        assertThrows(NullPointerException.class, () -> this.newWith(new Object[]{null}).maxByOptional(Objects::isNull));
    }

    @Test
    default void RichIterable_groupBy()
    {
        RichIterable<Integer> iterable = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
        Function<Integer, Boolean> groupByFunction = object -> IntegerPredicates.isOdd().accept(object);

        MutableMap<Boolean, RichIterable<Integer>> expectedGroupBy =
                UnifiedMap.newWithKeysValues(
                        Boolean.TRUE, this.newMutableForFilter(3, 3, 3, 1),
                        Boolean.FALSE, this.newMutableForFilter(4, 4, 4, 4, 2, 2));

        assertEquals(expectedGroupBy, iterable.groupBy(groupByFunction).toMap());

        Function<Integer, Boolean> function = (Integer object) -> true;
        MutableMultimap<Boolean, Integer> target = this.<Integer>newWith().groupBy(function).toMutable();
        MutableMultimap<Boolean, Integer> multimap2 = iterable.groupBy(groupByFunction, target);
        assertEquals(expectedGroupBy, multimap2.toMap());
        assertSame(target, multimap2);

        Function<Integer, Iterable<Integer>> groupByEachFunction = integer -> Interval.fromTo(-1, -integer);

        MutableMap<Integer, RichIterable<Integer>> expectedGroupByEach =
                UnifiedMap.newWithKeysValues(
                        -4, this.newMutableForFilter(4, 4, 4, 4),
                        -3, this.newMutableForFilter(4, 4, 4, 4, 3, 3, 3),
                        -2, this.newMutableForFilter(4, 4, 4, 4, 3, 3, 3, 2, 2),
                        -1, this.newMutableForFilter(4, 4, 4, 4, 3, 3, 3, 2, 2, 1));

        assertEquals(expectedGroupByEach, iterable.groupByEach(groupByEachFunction).toMap());

        MutableMultimap<Integer, Integer> target2 = this.<Integer>newWith().groupByEach(groupByEachFunction).toMutable();
        Multimap<Integer, Integer> actualWithTarget = iterable.groupByEach(groupByEachFunction, target2);
        assertEquals(expectedGroupByEach, actualWithTarget.toMap());
        assertSame(target2, actualWithTarget);
    }

    /**
     * @since 9.0
     */
    @Test
    default void RichIterable_countBy()
    {
        RichIterable<Integer> integers = this.newWith(1, 2, 3, 4, 5, 6);
        Bag<Integer> evensAndOdds = integers.countBy(each -> Integer.valueOf(each % 2));
        Assert.assertEquals(3, evensAndOdds.occurrencesOf(1));
        Assert.assertEquals(3, evensAndOdds.occurrencesOf(0));
        Bag<Integer> evensAndOdds2 = integers.countBy(each -> Integer.valueOf(each % 2), Bags.mutable.empty());
        Assert.assertEquals(3, evensAndOdds2.occurrencesOf(1));
        Assert.assertEquals(3, evensAndOdds2.occurrencesOf(0));
    }

    /**
     * @since 9.0
     */
    @Test
    default void RichIterable_countByWith()
    {
        RichIterable<Integer> integers = this.newWith(1, 2, 3, 4, 5, 6);
        Bag<Integer> evensAndOdds = integers.countByWith((each, parm) -> Integer.valueOf(each % parm), 2);
        Assert.assertEquals(3, evensAndOdds.occurrencesOf(1));
        Assert.assertEquals(3, evensAndOdds.occurrencesOf(0));
        Bag<Integer> evensAndOdds2 = integers.countByWith((each, parm) -> Integer.valueOf(each % parm), 2, Bags.mutable.empty());
        Assert.assertEquals(3, evensAndOdds2.occurrencesOf(1));
        Assert.assertEquals(3, evensAndOdds2.occurrencesOf(0));
    }

    /**
     * @since 10.0.0
     */
    @Test
    default void RichIterable_countByEach()
    {
        RichIterable<Integer> integerList = this.newWith(1, 2, 4);
        Bag<Integer> integerBag1 = integerList.countByEach(each -> IntInterval.oneTo(5).collect(i -> each * i));
        assertEquals(1, integerBag1.occurrencesOf(1));
        assertEquals(2, integerBag1.occurrencesOf(2));
        assertEquals(3, integerBag1.occurrencesOf(4));
        assertEquals(2, integerBag1.occurrencesOf(8));
        assertEquals(1, integerBag1.occurrencesOf(12));
        Bag<Integer> integerBag2 = integerList.countByEach(each -> IntInterval.oneTo(5).collect(i -> each * i), Bags.mutable.empty());
        assertEquals(1, integerBag2.occurrencesOf(1));
        assertEquals(2, integerBag2.occurrencesOf(2));
        assertEquals(3, integerBag2.occurrencesOf(4));
        assertEquals(2, integerBag2.occurrencesOf(8));
        assertEquals(1, integerBag2.occurrencesOf(12));
    }

    @Test
    default void RichIterable_aggregateBy_aggregateInPlaceBy()
    {
        RichIterable<Integer> iterable = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);

        MapIterable<String, Integer> aggregateBy = iterable.aggregateBy(
                Object::toString,
                () -> 0,
                (integer1, integer2) -> integer1 + integer2);

        assertEquals(16, aggregateBy.get("4").intValue());
        assertEquals(9, aggregateBy.get("3").intValue());
        assertEquals(4, aggregateBy.get("2").intValue());
        assertEquals(1, aggregateBy.get("1").intValue());

        MapIterable<String, AtomicInteger> aggregateInPlaceBy = iterable.aggregateInPlaceBy(
                String::valueOf,
                AtomicInteger::new,
                AtomicInteger::addAndGet);
        assertEquals(16, aggregateInPlaceBy.get("4").intValue());
        assertEquals(9, aggregateInPlaceBy.get("3").intValue());
        assertEquals(4, aggregateInPlaceBy.get("2").intValue());
        assertEquals(1, aggregateInPlaceBy.get("1").intValue());
    }

    @Test
    default void RichIterable_sumOfPrimitive()
    {
        RichIterable<Integer> iterable = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);

        Assert.assertEquals(30.0f, iterable.sumOfFloat(Integer::floatValue), 0.001);
        Assert.assertEquals(30.0, iterable.sumOfDouble(Integer::doubleValue), 0.001);
        Assert.assertEquals(30, iterable.sumOfInt(Integer::intValue));
        Assert.assertEquals(30L, iterable.sumOfLong(Integer::longValue));
    }

    @Test
    default void RichIterable_sumByPrimitive()
    {
        RichIterable<String> iterable = this.newWith("4", "4", "4", "4", "3", "3", "3", "2", "2", "1");

        assertEquals(
                ObjectLongMaps.immutable.with(0, 20L).newWithKeyValue(1, 10L),
                iterable.sumByInt(s -> Integer.parseInt(s) % 2, Integer::parseInt));

        assertEquals(
                ObjectLongMaps.immutable.with(0, 20L).newWithKeyValue(1, 10L),
                iterable.sumByLong(s -> Integer.parseInt(s) % 2, Long::parseLong));

        assertEquals(
                ObjectDoubleMaps.immutable.with(0, 20.0d).newWithKeyValue(1, 10.0d),
                iterable.sumByDouble(s -> Integer.parseInt(s) % 2, Double::parseDouble));

        assertEquals(
                ObjectDoubleMaps.immutable.with(0, 20.0d).newWithKeyValue(1, 10.0d),
                iterable.sumByFloat(s -> Integer.parseInt(s) % 2, Float::parseFloat));
    }

    @Test
    default void RichIterable_summarizePrimitive()
    {
        RichIterable<Integer> bigIterable = this.newWith(5, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 2, 2, 1);

        Assert.assertEquals(55.0f, bigIterable.summarizeFloat(Integer::floatValue).getSum(), 0.001);
        Assert.assertEquals(55.0, bigIterable.summarizeDouble(Integer::doubleValue).getSum(), 0.001);
        Assert.assertEquals(55, bigIterable.summarizeInt(Integer::intValue).getSum());
        Assert.assertEquals(55L, bigIterable.summarizeLong(Integer::longValue).getSum());

        RichIterable<Integer> littleIterable = this.newWith(5, 4, 3, 2, 1);

        Assert.assertEquals(15.0f, littleIterable.summarizeFloat(Integer::floatValue).getSum(), 0.001);
        Assert.assertEquals(15.0, littleIterable.summarizeDouble(Integer::doubleValue).getSum(), 0.001);
        Assert.assertEquals(15, littleIterable.summarizeInt(Integer::intValue).getSum());
        Assert.assertEquals(15L, littleIterable.summarizeLong(Integer::longValue).getSum());
    }

    @Test
    default void RichIterable_reduceInPlaceCollector()
    {
        RichIterable<Integer> littleIterable = this.newWith(1, 2, 3, 1, 2, 3);
        MutableBag<Integer> result =
                littleIterable.reduceInPlace(Collectors.toCollection(Bags.mutable::empty));
        Assert.assertEquals(Bags.immutable.with(1, 1, 2, 2, 3, 3), result);

        RichIterable<Integer> bigIterable = this.newWith(Interval.oneTo(20).toArray());
        MutableBag<Integer> bigResult =
                bigIterable.reduceInPlace(Collectors.toCollection(Bags.mutable::empty));
        Assert.assertEquals(Interval.oneTo(20).toBag(), bigResult);

        String joining =
                result.collect(Object::toString).reduceInPlace(Collectors.joining(","));
        Assert.assertEquals(result.collect(Object::toString).makeString(","), joining);

        ImmutableBag<Integer> immutableBag = result.toImmutable();
        String joining2 =
                immutableBag.collect(Object::toString).reduceInPlace(Collectors.joining(","));
        Assert.assertEquals(immutableBag.collect(Object::toString).makeString(","), joining2);

        String joining3 =
                result.asLazy().collect(Object::toString).reduceInPlace(Collectors.joining(","));
        Assert.assertEquals(result.asLazy().collect(Object::toString).makeString(","), joining3);

        Map<Boolean, List<Integer>> expected =
                littleIterable.toList().stream().collect(Collectors.partitioningBy(each -> each % 2 == 0));
        Map<Boolean, List<Integer>> actual =
                littleIterable.reduceInPlace(Collectors.partitioningBy(each -> each % 2 == 0));
        Assert.assertEquals(expected, actual);

        Map<String, List<Integer>> groupByJDK =
                littleIterable.toList().stream().collect(Collectors.groupingBy(Object::toString));
        Map<String, List<Integer>> groupByEC =
                result.reduceInPlace(Collectors.groupingBy(Object::toString));
        Assert.assertEquals(groupByJDK, groupByEC);
    }

    @Test
    default void RichIterable_reduceInPlace()
    {
        RichIterable<Integer> littleIterable = this.newWith(1, 2, 3, 1, 2, 3);
        MutableBag<Integer> result =
                littleIterable.reduceInPlace(Bags.mutable::empty, MutableBag::add);
        Assert.assertEquals(Bags.immutable.with(1, 1, 2, 2, 3, 3), result);

        RichIterable<Integer> bigIterable = this.newWith(Interval.oneTo(20).toArray());
        MutableBag<Integer> bigResult =
                bigIterable.reduceInPlace(Bags.mutable::empty, MutableBag::add);
        Assert.assertEquals(Interval.oneTo(20).toBag(), bigResult);

        String joining =
                result.collect(Object::toString).reduceInPlace(StringBuilder::new, StringBuilder::append).toString();
        Assert.assertEquals(result.collect(Object::toString).makeString(""), joining);

        ImmutableBag<Integer> immutableBag = result.toImmutable();
        String joining2 =
                immutableBag.collect(Object::toString).reduceInPlace(StringBuilder::new, StringBuilder::append).toString();
        Assert.assertEquals(immutableBag.collect(Object::toString).makeString(""), joining2);

        String joining3 =
                result.asLazy().collect(Object::toString).reduceInPlace(StringBuilder::new, StringBuilder::append).toString();
        Assert.assertEquals(result.asLazy().collect(Object::toString).makeString(""), joining3);

        int atomicAdd = littleIterable.reduceInPlace(AtomicInteger::new, AtomicInteger::addAndGet).get();
        Assert.assertEquals(12, atomicAdd);
    }

    @Test
    default void RichIterable_reduceOptional()
    {
        RichIterable<Integer> littleIterable = this.newWith(1, 2, 3, 1, 2, 3);
        Optional<Integer> result =
                littleIterable.reduce(Integer::sum);
        Assert.assertEquals(12, result.get().intValue());

        RichIterable<Integer> bigIterable = this.newWith(Interval.oneTo(20).toArray());
        Optional<Integer> bigResult =
                bigIterable.reduce(Integer::max);
        Assert.assertEquals(20, bigResult.get().intValue());

        Optional<Integer> max =
                littleIterable.reduce(Integer::max);
        Assert.assertEquals(3, max.get().intValue());

        Optional<Integer> min =
                littleIterable.reduce(Integer::min);
        Assert.assertEquals(1, min.get().intValue());

        RichIterable<Integer> iterableEmpty = this.newWith();
        Optional<Integer> resultEmpty =
                iterableEmpty.reduce(Integer::sum);
        assertFalse(resultEmpty.isPresent());
    }

    @Test
    default void RichIterable_injectInto()
    {
        RichIterable<Integer> iterable = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);

        assertEquals(Integer.valueOf(31), iterable.injectInto(1, AddFunction.INTEGER));
        assertEquals(Integer.valueOf(30), iterable.injectInto(0, AddFunction.INTEGER));
    }

    @Test
    default void RichIterable_injectInto_primitive()
    {
        RichIterable<Integer> iterable = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);

        Assert.assertEquals(31, iterable.injectIntoInt(1, AddFunction.INTEGER_TO_INT));
        Assert.assertEquals(30, iterable.injectIntoInt(0, AddFunction.INTEGER_TO_INT));

        Assert.assertEquals(31L, iterable.injectIntoLong(1, AddFunction.INTEGER_TO_LONG));
        Assert.assertEquals(30L, iterable.injectIntoLong(0, AddFunction.INTEGER_TO_LONG));

        Assert.assertEquals(31.0d, iterable.injectIntoDouble(1, AddFunction.INTEGER_TO_DOUBLE), 0.001);
        Assert.assertEquals(30.0d, iterable.injectIntoDouble(0, AddFunction.INTEGER_TO_DOUBLE), 0.001);

        Assert.assertEquals(31.0f, iterable.injectIntoFloat(1, AddFunction.INTEGER_TO_FLOAT), 0.001f);
        Assert.assertEquals(30.0f, iterable.injectIntoFloat(0, AddFunction.INTEGER_TO_FLOAT), 0.001f);
    }

    @Test
    default void RichIterable_fused_collectMakeString()
    {
        RichIterable<Integer> iterable = this.newWith(0, 1, 8);

        assertEquals(
                iterable.asLazy().collect(Integer::toUnsignedString).makeString("[", ",", "]"),
                iterable.makeString(Integer::toUnsignedString, "[", ",", "]"));
    }

    @Test
    default void RichIterable_makeString_appendString()
    {
        RichIterable<Integer> iterable = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
        assertEquals(
                "4, 4, 4, 4, 3, 3, 3, 2, 2, 1",
                iterable.makeString());

        assertEquals(
                iterable.makeString(),
                iterable.reduceInPlace(Collectors2.makeString()));

        assertEquals(
                "4/4/4/4/3/3/3/2/2/1",
                iterable.makeString("/"));

        assertEquals(
                iterable.makeString("/"),
                iterable.reduceInPlace(Collectors2.makeString("/")));

        assertEquals(
                "[4/4/4/4/3/3/3/2/2/1]",
                iterable.makeString("[", "/", "]"));

        assertEquals(
                iterable.makeString("[", "/", "]"),
                iterable.reduceInPlace(Collectors2.makeString("[", "/", "]")));

        StringBuilder builder1 = new StringBuilder();
        iterable.appendString(builder1);
        assertEquals(
                "4, 4, 4, 4, 3, 3, 3, 2, 2, 1",
                builder1.toString());

        StringBuilder builder2 = new StringBuilder();
        iterable.appendString(builder2, "/");
        assertEquals(
                "4/4/4/4/3/3/3/2/2/1",
                builder2.toString());

        StringBuilder builder3 = new StringBuilder();
        iterable.appendString(builder3, "[", "/", "]");
        assertEquals(
                "[4/4/4/4/3/3/3/2/2/1]",
                builder3.toString());
    }

    @Test
    default void Iterable_toString()
    {
        RichIterable<Integer> iterable = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
        assertEquals(
                "[4, 4, 4, 4, 3, 3, 3, 2, 2, 1]",
                iterable.toString());
        assertEquals(
                "[4, 4, 4, 4, 3, 3, 3, 2, 2, 1]",
                iterable.asLazy().toString());
    }

    @Test
    default void RichIterable_toList()
    {
        RichIterable<Integer> iterable = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
        assertEquals(
                Lists.immutable.with(4, 4, 4, 4, 3, 3, 3, 2, 2, 1),
                iterable.toList());

        MutableList<Integer> target = Lists.mutable.empty();
        iterable.each(target::add);
        assertEquals(
                target,
                iterable.toList());
    }

    @Test
    default void RichIterable_into()
    {
        assertEquals(
                Lists.immutable.with(0, 4, 4, 4, 4, 3, 3, 3, 2, 2, 1),
                this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1).into(Lists.mutable.with(0)));
    }

    @Test
    default void RichIterable_toSortedList()
    {
        RichIterable<Integer> iterable = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);

        assertEquals(
                Lists.immutable.with(1, 2, 2, 3, 3, 3, 4, 4, 4, 4),
                iterable.toSortedList());

        assertEquals(
                Lists.immutable.with(4, 4, 4, 4, 3, 3, 3, 2, 2, 1),
                iterable.toSortedList(Comparators.reverseNaturalOrder()));

        assertEquals(
                Lists.immutable.with(1, 2, 2, 3, 3, 3, 4, 4, 4, 4),
                iterable.toSortedListBy(Functions.identity()));

        assertEquals(
                Lists.immutable.with(4, 4, 4, 4, 3, 3, 3, 2, 2, 1),
                iterable.toSortedListBy(each -> each * -1));
    }

    @Test
    default void RichIterable_toSet()
    {
        assertEquals(
                Sets.immutable.with(4, 3, 2, 1),
                this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1).toSet());
    }

    @Test
    default void RichIterable_toSortedSet()
    {
        RichIterable<Integer> iterable = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);

        assertEquals(
                SortedSets.immutable.with(1, 2, 3, 4),
                iterable.toSortedSet());

        assertEquals(
                SortedSets.immutable.with(Comparators.reverseNaturalOrder(), 4, 3, 2, 1),
                iterable.toSortedSet(Comparators.reverseNaturalOrder()));

        assertEquals(
                SortedSets.immutable.with(Comparators.byFunction(Functions.identity()), 1, 2, 3, 4),
                iterable.toSortedSetBy(Functions.identity()));

        assertEquals(
                SortedSets.immutable.with(Comparators.byFunction((Integer each) -> each * -1), 4, 3, 2, 1),
                iterable.toSortedSetBy(each -> each * -1));
    }

    @Test
    default void RichIterable_toBag()
    {
        assertEquals(
                Bags.immutable.with(4, 4, 4, 4, 3, 3, 3, 2, 2, 1),
                this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1).toBag());
    }

    @Test
    default void RichIterable_toSortedBag()
    {
        RichIterable<Integer> iterable = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);

        assertEquals(
                TreeBag.newBagWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4),
                iterable.toSortedBag());

        assertEquals(
                TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 4, 4, 4, 4, 3, 3, 3, 2, 2, 1),
                iterable.toSortedBag(Comparators.reverseNaturalOrder()));

        assertEquals(
                TreeBag.newBagWith(Comparators.byFunction(Functions.identity()), 1, 2, 2, 3, 3, 3, 4, 4, 4, 4),
                iterable.toSortedBagBy(Functions.identity()));

        assertEquals(
                TreeBag.newBagWith(Comparators.byFunction((Integer each) -> each * -1), 4, 4, 4, 4, 3, 3, 3, 2, 2, 1),
                iterable.toSortedBagBy(each -> each * -1));
    }

    @Test
    default void RichIterable_toMap()
    {
        RichIterable<Integer> iterable = this.newWith(13, 13, 12, 12, 11, 11, 3, 3, 2, 2, 1, 1);

        assertEquals(
                UnifiedMap.newMapWith(
                        Tuples.pair("13", 3),
                        Tuples.pair("12", 2),
                        Tuples.pair("11", 1),
                        Tuples.pair("3", 3),
                        Tuples.pair("2", 2),
                        Tuples.pair("1", 1)),
                iterable.toMap(Object::toString, each -> each % 10));
    }

    @Test
    default void RichIterable_toMapTarget()
    {
        RichIterable<Integer> iterable = this.newWith(13, 12, 11, 3, 2, 1);

        Map<String, Integer> jdkMap = new HashMap<>();
        jdkMap.put("13", 3);
        jdkMap.put("12", 2);
        jdkMap.put("11", 1);
        jdkMap.put("3", 3);
        jdkMap.put("2", 2);
        jdkMap.put("1", 1);

        assertEquals(
                jdkMap,
                iterable.toMap(Object::toString, each -> each % 10, new HashMap<>()));
    }

    @Test
    default void RichIterable_toSortedMap()
    {
        RichIterable<Integer> iterable = this.newWith(13, 13, 12, 12, 11, 11, 3, 3, 2, 2, 1, 1);

        Pair<String, Integer>[] pairs = new Pair[]
                {
                        Tuples.pair("13", 3),
                        Tuples.pair("12", 2),
                        Tuples.pair("11", 1),
                        Tuples.pair("3", 3),
                        Tuples.pair("2", 2),
                        Tuples.pair("1", 1),
                };
        assertEquals(
                TreeSortedMap.newMapWith(pairs),
                iterable.toSortedMap(Object::toString, each -> each % 10));

        assertEquals(
                TreeSortedMap.newMapWith(
                        Comparators.reverseNaturalOrder(),
                        pairs),
                iterable.toSortedMap(Comparators.reverseNaturalOrder(), Object::toString, each -> each % 10));

        assertEquals(
                TreeSortedMap.newMapWith(
                        Comparators.naturalOrder(),
                        pairs),
                iterable.toSortedMapBy(Functions.getStringPassThru(), Object::toString, each -> each % 10));
    }

    @Test
    default void RichIterable_toArray()
    {
        Object[] array = this.newWith(3, 3, 3, 2, 2, 1).toArray();
        assertEquals(Bags.immutable.with(3, 3, 3, 2, 2, 1), HashBag.newBagWith(array));
    }

    @Test
    default void RichIterable_groupByAndCollect()
    {
        RichIterable<Integer> iterable = this.newWith(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        Function<Integer, Boolean> groupByFunction = integer -> IntegerPredicates.isOdd().accept(integer);
        Function<Integer, Integer> collectFunction = integer -> integer + 2;

        FastList<Integer> expectedOddNumberList = FastList.newListWith(3, 5, 7, 9, 11);
        FastList<Integer> expectedEvenNumberList = FastList.newListWith(4, 6, 8, 10, 12);

        MutableListMultimap<Boolean, Integer> targetResult = iterable.groupByAndCollect(groupByFunction, collectFunction, Multimaps.mutable.list.empty());

        assertTrue(expectedOddNumberList.containsAll(targetResult.get(Boolean.TRUE)));
        assertTrue(expectedEvenNumberList.containsAll(targetResult.get(Boolean.FALSE)));
    }

    class Holder<T extends Comparable<? super T>> implements Comparable<Holder<T>>
    {
        private final T field;

        Holder(T field)
        {
            this.field = field;
        }

        @Override
        public int compareTo(Holder<T> other)
        {
            return this.field.compareTo(other.field);
        }
    }
}
