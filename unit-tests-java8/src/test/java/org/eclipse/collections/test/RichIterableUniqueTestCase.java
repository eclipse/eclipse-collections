/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
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
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.partition.PartitionIterable;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.factory.primitive.ObjectDoubleMaps;
import org.eclipse.collections.impl.factory.primitive.ObjectLongMaps;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.impl.test.Verify.assertPostSerializedEqualsAndHashCode;
import static org.eclipse.collections.impl.test.Verify.assertThrows;
import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.eclipse.collections.test.IterableTestCase.assertNotEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public interface RichIterableUniqueTestCase extends RichIterableTestCase
{
    @Override
    default boolean allowsDuplicates()
    {
        return false;
    }

    @Override
    @Test
    default void Object_PostSerializedEqualsAndHashCode()
    {
        Iterable<Integer> iterable = this.newWith(3, 2, 1);
        Object deserialized = SerializeTestHelper.serializeDeserialize(iterable);
        Assert.assertNotSame(iterable, deserialized);
    }

    @Override
    @Test
    default void Object_equalsAndHashCode()
    {
        assertPostSerializedEqualsAndHashCode(this.newWith(3, 2, 1));

        assertNotEquals(this.newWith(4, 3, 2, 1), this.newWith(3, 2, 1));
        assertNotEquals(this.newWith(3, 2, 1), this.newWith(4, 3, 2, 1));

        assertNotEquals(this.newWith(2, 1), this.newWith(3, 2, 1));
        assertNotEquals(this.newWith(3, 2, 1), this.newWith(2, 1));

        assertNotEquals(this.newWith(4, 2, 1), this.newWith(3, 2, 1));
        assertNotEquals(this.newWith(3, 2, 1), this.newWith(4, 2, 1));
    }

    @Test
    default void Iterable_sanity_check()
    {
        String s = "";
        assertThrows(IllegalStateException.class, () -> this.newWith(s, s));
    }

    @Override
    @Test
    default void InternalIterable_forEach()
    {
        {
            RichIterable<Integer> iterable = this.newWith(3, 2, 1);
            MutableCollection<Integer> result = this.newMutableForFilter();
            iterable.forEach(Procedures.cast(i -> result.add(i + 10)));
            assertEquals(this.newMutableForFilter(13, 12, 11), result);
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

    @Override
    @Test
    default void RichIterable_tap()
    {
        Procedure<Object> noop = each -> {
        };

        RichIterable<Integer> iterable = this.newWith(3, 2, 1);
        MutableCollection<Integer> result = this.newMutableForFilter();
        iterable.tap(result::add).forEach(noop);
        assertEquals(this.newMutableForFilter(3, 2, 1), result);
        this.newWith().tap(Procedures.cast(each -> fail()));
    }

    @Override
    @Test
    default void InternalIterable_forEachWith()
    {
        RichIterable<Integer> iterable = this.newWith(3, 2, 1);
        MutableCollection<Integer> result = this.newMutableForFilter();
        iterable.forEachWith((argument1, argument2) -> result.add(argument1 + argument2), 10);
        assertEquals(this.newMutableForFilter(13, 12, 11), result);
    }

    @Test
    default void RichIterable_size()
    {
        assertEquals(3, this.newWith(3, 2, 1).size());
    }

    @Override
    @Test
    default void RichIterable_toArray()
    {
        Object[] array = this.newWith(3, 2, 1).toArray();
        assertArrayEquals(new Object[]{3, 2, 1}, array);
    }

    @Override
    @Test
    default void RichIterable_select_reject()
    {
        {
            RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);

            assertEquals(
                    this.getExpectedFiltered(4, 2),
                    iterable.select(IntegerPredicates.isEven()));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.select(IntegerPredicates.isEven(), target);
                assertEquals(this.getExpectedFiltered(4, 2), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(4, 3),
                    iterable.selectWith(Predicates2.greaterThan(), 2));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.selectWith(Predicates2.greaterThan(), 2, target);
                assertEquals(this.getExpectedFiltered(4, 3), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(4, 2),
                    iterable.reject(IntegerPredicates.isOdd()));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.reject(IntegerPredicates.isOdd(), target);
                assertEquals(this.getExpectedFiltered(4, 2), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(4, 3),
                    iterable.rejectWith(Predicates2.lessThan(), 3));

            MutableCollection<Integer> target = this.newMutableForFilter();
            MutableCollection<Integer> result = iterable.rejectWith(Predicates2.lessThan(), 3, target);
            assertEquals(this.getExpectedFiltered(4, 3), result);
            assertSame(target, result);
        }

        {
            RichIterable<Integer> iterable = this.newWith(3, 2, 1);

            assertEquals(
                    this.getExpectedFiltered(2),
                    iterable.select(IntegerPredicates.isEven()));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.select(IntegerPredicates.isEven(), target);
                assertEquals(this.getExpectedFiltered(2), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(3),
                    iterable.selectWith(Predicates2.greaterThan(), 2));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.selectWith(Predicates2.greaterThan(), 2, target);
                assertEquals(this.getExpectedFiltered(3), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(2),
                    iterable.reject(IntegerPredicates.isOdd()));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.reject(IntegerPredicates.isOdd(), target);
                assertEquals(this.getExpectedFiltered(2), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(3),
                    iterable.rejectWith(Predicates2.lessThan(), 3));

            MutableCollection<Integer> target = this.newMutableForFilter();
            MutableCollection<Integer> result = iterable.rejectWith(Predicates2.lessThan(), 3, target);
            assertEquals(this.getExpectedFiltered(3), result);
            assertSame(target, result);
        }

        {
            RichIterable<Integer> iterable = this.newWith(2, 1);

            assertEquals(
                    this.getExpectedFiltered(2),
                    iterable.select(IntegerPredicates.isEven()));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.select(IntegerPredicates.isEven(), target);
                assertEquals(this.getExpectedFiltered(2), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(),
                    iterable.selectWith(Predicates2.greaterThan(), 2));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.selectWith(Predicates2.greaterThan(), 2, target);
                assertEquals(this.getExpectedFiltered(), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(2),
                    iterable.reject(IntegerPredicates.isOdd()));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.reject(IntegerPredicates.isOdd(), target);
                assertEquals(this.getExpectedFiltered(2), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(),
                    iterable.rejectWith(Predicates2.lessThan(), 3));

            MutableCollection<Integer> target = this.newMutableForFilter();
            MutableCollection<Integer> result = iterable.rejectWith(Predicates2.lessThan(), 3, target);
            assertEquals(this.getExpectedFiltered(), result);
            assertSame(target, result);
        }

        {
            RichIterable<Integer> iterable = this.newWith(1);

            assertEquals(
                    this.getExpectedFiltered(),
                    iterable.select(IntegerPredicates.isEven()));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.select(IntegerPredicates.isEven(), target);
                assertEquals(this.getExpectedFiltered(), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(1),
                    iterable.select(IntegerPredicates.isOdd()));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.select(IntegerPredicates.isOdd(), target);
                assertEquals(this.getExpectedFiltered(1), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(),
                    iterable.selectWith(Predicates2.greaterThan(), 2));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.selectWith(Predicates2.greaterThan(), 2, target);
                assertEquals(this.getExpectedFiltered(), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(1),
                    iterable.selectWith(Predicates2.greaterThan(), 0));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.selectWith(Predicates2.greaterThan(), 0, target);
                assertEquals(this.getExpectedFiltered(1), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(),
                    iterable.reject(IntegerPredicates.isOdd()));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.reject(IntegerPredicates.isOdd(), target);
                assertEquals(this.getExpectedFiltered(), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(1),
                    iterable.reject(IntegerPredicates.isEven()));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.reject(IntegerPredicates.isEven(), target);
                assertEquals(this.getExpectedFiltered(1), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(),
                    iterable.rejectWith(Predicates2.lessThan(), 3));

            {
                MutableCollection<Integer> target = this.newMutableForFilter();
                MutableCollection<Integer> result = iterable.rejectWith(Predicates2.lessThan(), 3, target);
                assertEquals(this.getExpectedFiltered(), result);
                assertSame(target, result);
            }

            assertEquals(
                    this.getExpectedFiltered(1),
                    iterable.rejectWith(Predicates2.lessThan(), 0));

            MutableCollection<Integer> target = this.newMutableForFilter();
            MutableCollection<Integer> result = iterable.rejectWith(Predicates2.lessThan(), 0, target);
            assertEquals(this.getExpectedFiltered(1), result);
            assertSame(target, result);
        }

        RichIterable<Integer> iterable = this.newWith();

        assertEquals(
                this.getExpectedFiltered(),
                iterable.select(IntegerPredicates.isEven()));

        {
            MutableCollection<Integer> target = this.newMutableForFilter();
            MutableCollection<Integer> result = iterable.select(IntegerPredicates.isEven(), target);
            assertEquals(this.getExpectedFiltered(), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedFiltered(),
                iterable.selectWith(Predicates2.greaterThan(), 2));

        {
            MutableCollection<Integer> target = this.newMutableForFilter();
            MutableCollection<Integer> result = iterable.selectWith(Predicates2.greaterThan(), 2, target);
            assertEquals(this.getExpectedFiltered(), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedFiltered(),
                iterable.reject(IntegerPredicates.isOdd()));

        {
            MutableCollection<Integer> target = this.newMutableForFilter();
            MutableCollection<Integer> result = iterable.reject(IntegerPredicates.isOdd(), target);
            assertEquals(this.getExpectedFiltered(), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedFiltered(),
                iterable.rejectWith(Predicates2.lessThan(), 3));

        MutableCollection<Integer> target = this.newMutableForFilter();
        MutableCollection<Integer> result = iterable.rejectWith(Predicates2.lessThan(), 3, target);
        assertEquals(this.getExpectedFiltered(), result);
        assertSame(target, result);
    }

    @Override
    @Test
    default void RichIterable_partition()
    {
        RichIterable<Integer> iterable = this.newWith(-3, -2, -1, 0, 1, 2, 3);

        PartitionIterable<Integer> partition = iterable.partition(IntegerPredicates.isEven());
        assertEquals(this.getExpectedFiltered(-2, 0, 2), partition.getSelected());
        assertEquals(this.getExpectedFiltered(-3, -1, 1, 3), partition.getRejected());

        PartitionIterable<Integer> partitionWith = iterable.partitionWith(Predicates2.greaterThan(), 0);
        assertEquals(this.getExpectedFiltered(1, 2, 3), partitionWith.getSelected());
        assertEquals(this.getExpectedFiltered(-3, -2, -1, 0), partitionWith.getRejected());
    }

    @Override
    @Test
    default void RichIterable_selectInstancesOf()
    {
        RichIterable<Number> iterable = this.newWith(1, 2.0, 3, 4.0);

        assertEquals(this.getExpectedFiltered(), iterable.selectInstancesOf(String.class));
        assertEquals(this.getExpectedFiltered(1, 3), iterable.selectInstancesOf(Integer.class));
        assertEquals(this.getExpectedFiltered(1, 2.0, 3, 4.0), iterable.selectInstancesOf(Number.class));
    }

    @Override
    @Test
    default void RichIterable_collect()
    {
        RichIterable<Integer> iterable = this.newWith(13, 12, 11, 3, 2, 1);

        assertEquals(
                this.getExpectedTransformed(3, 2, 1, 3, 2, 1),
                iterable.collect(i -> i % 10));

        {
            MutableCollection<Integer> target = this.newMutableForTransform();
            MutableCollection<Integer> result = iterable.collect(i -> i % 10, target);
            assertEquals(this.getExpectedTransformed(3, 2, 1, 3, 2, 1), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedTransformed(3, 2, 1, 3, 2, 1),
                iterable.collectWith((i, mod) -> i % mod, 10));

        MutableCollection<Integer> target = this.newMutableForTransform();
        MutableCollection<Integer> result = iterable.collectWith((i, mod) -> i % mod, 10, target);
        assertEquals(this.getExpectedTransformed(3, 2, 1, 3, 2, 1), result);
        assertSame(target, result);
    }

    @Override
    @Test
    default void RichIterable_collectIf()
    {
        assertEquals(
                this.getExpectedTransformed(3, 1, 3, 1),
                this.newWith(13, 12, 11, 3, 2, 1).collectIf(i -> i % 2 != 0, i -> i % 10));

        MutableCollection<Integer> target = this.newMutableForTransform();
        MutableCollection<Integer> result = this.newWith(13, 12, 11, 3, 2, 1).collectIf(i -> i % 2 != 0, i -> i % 10, target);
        assertEquals(this.newMutableForTransform(3, 1, 3, 1), result);
        assertSame(target, result);
    }

    @Override
    @Test
    default void RichIterable_collectPrimitive()
    {
        assertEquals(
                this.getExpectedBoolean(false, true, false),
                this.newWith(3, 2, 1).collectBoolean(each -> each % 2 == 0));

        {
            MutableBooleanCollection target = this.newBooleanForTransform();
            MutableBooleanCollection result = this.newWith(3, 2, 1).collectBoolean(each -> each % 2 == 0, target);
            assertEquals(this.getExpectedBoolean(false, true, false), result);
            assertSame(target, result);
        }

        RichIterable<Integer> iterable = this.newWith(13, 12, 11, 3, 2, 1);

        assertEquals(
                this.getExpectedByte((byte) 3, (byte) 2, (byte) 1, (byte) 3, (byte) 2, (byte) 1),
                iterable.collectByte(each -> (byte) (each % 10)));

        {
            MutableByteCollection target = this.newByteForTransform();
            MutableByteCollection result = iterable.collectByte(each -> (byte) (each % 10), target);
            assertEquals(this.getExpectedByte((byte) 3, (byte) 2, (byte) 1, (byte) 3, (byte) 2, (byte) 1), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedChar((char) 3, (char) 2, (char) 1, (char) 3, (char) 2, (char) 1),
                iterable.collectChar(each -> (char) (each % 10)));

        {
            MutableCharCollection target = this.newCharForTransform();
            MutableCharCollection result = iterable.collectChar(each -> (char) (each % 10), target);
            assertEquals(this.getExpectedChar((char) 3, (char) 2, (char) 1, (char) 3, (char) 2, (char) 1), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedDouble(3.0, 2.0, 1.0, 3.0, 2.0, 1.0),
                iterable.collectDouble(each -> (double) (each % 10)));

        {
            MutableDoubleCollection target = this.newDoubleForTransform();
            MutableDoubleCollection result = iterable.collectDouble(each -> (double) (each % 10), target);
            assertEquals(this.getExpectedDouble(3.0, 2.0, 1.0, 3.0, 2.0, 1.0), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedFloat(3.0f, 2.0f, 1.0f, 3.0f, 2.0f, 1.0f),
                iterable.collectFloat(each -> (float) (each % 10)));

        {
            MutableFloatCollection target = this.newFloatForTransform();
            MutableFloatCollection result = iterable.collectFloat(each -> (float) (each % 10), target);
            assertEquals(this.getExpectedFloat(3.0f, 2.0f, 1.0f, 3.0f, 2.0f, 1.0f), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedInt(3, 2, 1, 3, 2, 1),
                iterable.collectInt(each -> each % 10));

        {
            MutableIntCollection target = this.newIntForTransform();
            MutableIntCollection result = iterable.collectInt(each -> each % 10, target);
            assertEquals(this.getExpectedInt(3, 2, 1, 3, 2, 1), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedLong(3, 2, 1, 3, 2, 1),
                iterable.collectLong(each -> each % 10));

        {
            MutableLongCollection target = this.newLongForTransform();
            MutableLongCollection result = iterable.collectLong(each -> each % 10, target);
            assertEquals(this.getExpectedLong(3, 2, 1, 3, 2, 1), result);
            assertSame(target, result);
        }

        assertEquals(
                this.getExpectedShort((short) 3, (short) 2, (short) 1, (short) 3, (short) 2, (short) 1),
                iterable.collectShort(each -> (short) (each % 10)));

        MutableShortCollection target = this.newShortForTransform();
        MutableShortCollection result = iterable.collectShort(each -> (short) (each % 10), target);
        assertEquals(this.getExpectedShort((short) 3, (short) 2, (short) 1, (short) 3, (short) 2, (short) 1), result);
        assertSame(target, result);
    }

    @Override
    @Test
    default void RichIterable_flatCollect()
    {
        assertEquals(
                this.getExpectedTransformed(1, 2, 3, 1, 2, 1),
                this.newWith(3, 2, 1).flatCollect(Interval::oneTo));

        assertEquals(
                this.getExpectedTransformed(1, 2, 3, 1, 2, 1),
                this.newWith(3, 2, 1).flatCollect(Interval::oneTo, this.newMutableForTransform()));
    }

    @Test
    default void RichIterable_flatCollectWith()
    {
        assertEquals(
                this.getExpectedTransformed(3, 2, 1, 2, 1, 1),
                this.newWith(3, 2, 1).flatCollectWith(Interval::fromTo, 1));

        assertEquals(
                this.newMutableForTransform(3, 2, 1, 2, 1, 1),
                this.newWith(3, 2, 1).flatCollectWith(Interval::fromTo, 1, this.newMutableForTransform()));
    }

    @Override
    @Test
    default void RichIterable_count()
    {
        RichIterable<Integer> iterable = this.newWith(3, 2, 1);

        assertEquals(1, iterable.count(Integer.valueOf(3)::equals));
        assertEquals(1, iterable.count(Integer.valueOf(2)::equals));
        assertEquals(1, iterable.count(Integer.valueOf(1)::equals));
        assertEquals(0, iterable.count(Integer.valueOf(0)::equals));
        assertEquals(2, iterable.count(i -> i % 2 != 0));
        assertEquals(3, iterable.count(i -> i > 0));

        assertEquals(1, iterable.countWith(Object::equals, 3));
        assertEquals(1, iterable.countWith(Object::equals, 2));
        assertEquals(1, iterable.countWith(Object::equals, 1));
        assertEquals(0, iterable.countWith(Object::equals, 0));
        assertEquals(3, iterable.countWith(Predicates2.greaterThan(), 0));
    }

    @Override
    @Test
    default void RichIterable_groupBy()
    {
        RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);
        Function<Integer, Boolean> groupByFunction = object -> IntegerPredicates.isOdd().accept(object);

        MutableMap<Boolean, RichIterable<Integer>> groupByExpected =
                UnifiedMap.newWithKeysValues(
                        Boolean.TRUE, this.newMutableForFilter(3, 1),
                        Boolean.FALSE, this.newMutableForFilter(4, 2));

        assertEquals(groupByExpected, iterable.groupBy(groupByFunction).toMap());

        Function<Integer, Boolean> function = (Integer object) -> true;
        MutableMultimap<Boolean, Integer> target = this.<Integer>newWith().groupBy(function).toMutable();
        MutableMultimap<Boolean, Integer> multimap2 = iterable.groupBy(groupByFunction, target);
        assertEquals(groupByExpected, multimap2.toMap());
        assertSame(target, multimap2);

        Function<Integer, Iterable<Integer>> groupByEachFunction = integer -> Interval.fromTo(-1, -integer);

        MutableMap<Integer, RichIterable<Integer>> expectedGroupByEach =
                UnifiedMap.newWithKeysValues(
                        -4, this.newMutableForFilter(4),
                        -3, this.newMutableForFilter(4, 3),
                        -2, this.newMutableForFilter(4, 3, 2),
                        -1, this.newMutableForFilter(4, 3, 2, 1));

        assertEquals(expectedGroupByEach, iterable.groupByEach(groupByEachFunction).toMap());

        MutableMultimap<Integer, Integer> target2 = this.<Integer>newWith().groupByEach(groupByEachFunction).toMutable();
        Multimap<Integer, Integer> actualWithTarget = iterable.groupByEach(groupByEachFunction, target2);
        assertEquals(expectedGroupByEach, actualWithTarget.toMap());
        assertSame(target2, actualWithTarget);
    }

    @Override
    @Test
    default void RichIterable_aggregateBy_aggregateInPlaceBy()
    {
        RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);

        MapIterable<String, Integer> aggregateBy = iterable.aggregateBy(
                Object::toString,
                () -> 0,
                (integer1, integer2) -> integer1 + integer2);

        assertEquals(4, aggregateBy.get("4").intValue());
        assertEquals(3, aggregateBy.get("3").intValue());
        assertEquals(2, aggregateBy.get("2").intValue());
        assertEquals(1, aggregateBy.get("1").intValue());

        MapIterable<String, AtomicInteger> aggregateInPlaceBy = iterable.aggregateInPlaceBy(
                String::valueOf,
                AtomicInteger::new,
                AtomicInteger::addAndGet);
        assertEquals(4, aggregateInPlaceBy.get("4").intValue());
        assertEquals(3, aggregateInPlaceBy.get("3").intValue());
        assertEquals(2, aggregateInPlaceBy.get("2").intValue());
        assertEquals(1, aggregateInPlaceBy.get("1").intValue());
    }

    @Override
    @Test
    default void RichIterable_sumOfPrimitive()
    {
        RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);

        Assert.assertEquals(10.0f, iterable.sumOfFloat(Integer::floatValue), 0.001);
        Assert.assertEquals(10.0, iterable.sumOfDouble(Integer::doubleValue), 0.001);
        Assert.assertEquals(10, iterable.sumOfInt(integer -> integer));
        Assert.assertEquals(10L, iterable.sumOfLong(Integer::longValue));
    }

    @Override
    default void RichIterable_sumByPrimitive()
    {
        RichIterable<String> iterable = this.newWith("4", "3", "2", "1");

        assertEquals(
                ObjectLongMaps.immutable.with(0, 6L).newWithKeyValue(1, 4L),
                iterable.sumByInt(s -> Integer.parseInt(s) % 2, Integer::parseInt));

        assertEquals(
                ObjectLongMaps.immutable.with(0, 6L).newWithKeyValue(1, 4L),
                iterable.sumByLong(s -> Integer.parseInt(s) % 2, Long::parseLong));

        assertEquals(
                ObjectDoubleMaps.immutable.with(0, 6.0d).newWithKeyValue(1, 4.0d),
                iterable.sumByDouble(s -> Integer.parseInt(s) % 2, Double::parseDouble));

        assertEquals(
                ObjectDoubleMaps.immutable.with(0, 6.0d).newWithKeyValue(1, 4.0d),
                iterable.sumByFloat(s -> Integer.parseInt(s) % 2, Float::parseFloat));
    }

    @Override
    @Test
    default void RichIterable_summarizePrimitive()
    {
        RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);

        Assert.assertEquals(10.0f, iterable.summarizeFloat(Integer::floatValue).getSum(), 0.001);
        Assert.assertEquals(10.0, iterable.summarizeDouble(Integer::doubleValue).getSum(), 0.001);
        Assert.assertEquals(10, iterable.summarizeInt(Integer::intValue).getSum());
        Assert.assertEquals(10L, iterable.summarizeLong(Integer::longValue).getSum());
    }

    @Override
    @Test
    default void RichIterable_reduceInPlaceCollector()
    {
        RichIterable<Integer> iterable = this.newWith(1, 2, 3);
        MutableBag<Integer> result = iterable.reduceInPlace(Collectors.toCollection(Bags.mutable::empty));
        Assert.assertEquals(Bags.immutable.with(1, 2, 3), result);

        String joining = result.collect(Object::toString).reduceInPlace(Collectors.joining(","));
        Assert.assertEquals(result.collect(Object::toString).makeString(","), joining);

        String joining2 = result.toImmutable().collect(Object::toString).reduceInPlace(Collectors.joining(","));
        Assert.assertEquals(result.toImmutable().collect(Object::toString).makeString(","), joining2);

        String joining3 = result.asLazy().collect(Object::toString).reduceInPlace(Collectors.joining(","));
        Assert.assertEquals(result.asLazy().collect(Object::toString).makeString(","), joining3);

        Map<Boolean, List<Integer>> expected =
                iterable.toList().stream().collect(Collectors.partitioningBy(each -> each % 2 == 0));
        Map<Boolean, List<Integer>> actual =
                iterable.reduceInPlace(Collectors.partitioningBy(each -> each % 2 == 0));
        Assert.assertEquals(expected, actual);

        Map<String, List<Integer>> groupByJDK =
                iterable.toList().stream().collect(Collectors.groupingBy(Object::toString));
        Map<String, List<Integer>> groupByEC =
                result.reduceInPlace(Collectors.groupingBy(Object::toString));
        Assert.assertEquals(groupByJDK, groupByEC);
    }

    @Override
    @Test
    default void RichIterable_reduceInPlace()
    {
        RichIterable<Integer> iterable = this.newWith(1, 2, 3);
        MutableBag<Integer> result =
                iterable.reduceInPlace(Bags.mutable::empty, MutableBag::add);
        Assert.assertEquals(Bags.immutable.with(1, 2, 3), result);

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

        int atomicAdd = iterable.reduceInPlace(AtomicInteger::new, AtomicInteger::addAndGet).get();
        Assert.assertEquals(6, atomicAdd);
    }

    @Override
    @Test
    default void RichIterable_reduceOptional()
    {
        RichIterable<Integer> iterable = this.newWith(1, 2, 3);
        Optional<Integer> result =
                iterable.reduce(Integer::sum);
        Assert.assertEquals(6, result.get().intValue());

        Optional<Integer> max =
                iterable.reduce(Integer::max);
        Assert.assertEquals(3, max.get().intValue());

        Optional<Integer> min =
                iterable.reduce(Integer::min);
        Assert.assertEquals(1, min.get().intValue());

        RichIterable<Integer> iterableEmpty = this.newWith();
        Optional<Integer> resultEmpty =
                iterableEmpty.reduce(Integer::sum);
        Assert.assertFalse(resultEmpty.isPresent());
    }

    @Override
    @Test
    default void RichIterable_injectInto()
    {
        RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);

        assertEquals(Integer.valueOf(11), iterable.injectInto(1, new Function2<Integer, Integer, Integer>()
        {
            private static final long serialVersionUID = 1L;

            public Integer value(Integer argument1, Integer argument2)
            {
                return argument1 + argument2;
            }
        }));
        assertEquals(Integer.valueOf(10), iterable.injectInto(0, new Function2<Integer, Integer, Integer>()
        {
            private static final long serialVersionUID = 1L;

            public Integer value(Integer argument1, Integer argument2)
            {
                return argument1 + argument2;
            }
        }));
    }

    @Override
    @Test
    default void RichIterable_injectInto_primitive()
    {
        RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);

        Assert.assertEquals(11, iterable.injectInto(1, AddFunction.INTEGER_TO_INT));
        Assert.assertEquals(10, iterable.injectInto(0, AddFunction.INTEGER_TO_INT));

        Assert.assertEquals(11L, iterable.injectInto(1, AddFunction.INTEGER_TO_LONG));
        Assert.assertEquals(10L, iterable.injectInto(0, AddFunction.INTEGER_TO_LONG));

        Assert.assertEquals(11.0d, iterable.injectInto(1, AddFunction.INTEGER_TO_DOUBLE), 0.001);
        Assert.assertEquals(10.0d, iterable.injectInto(0, AddFunction.INTEGER_TO_DOUBLE), 0.001);

        Assert.assertEquals(11.0f, iterable.injectInto(1, AddFunction.INTEGER_TO_FLOAT), 0.001f);
        Assert.assertEquals(10.0f, iterable.injectInto(0, AddFunction.INTEGER_TO_FLOAT), 0.001f);
    }

    @Override
    @Test
    default void RichIterable_makeString_appendString()
    {
        RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);

        assertEquals("4, 3, 2, 1", iterable.makeString());
        assertEquals("4/3/2/1", iterable.makeString("/"));
        assertEquals("[4/3/2/1]", iterable.makeString("[", "/", "]"));

        StringBuilder stringBuilder1 = new StringBuilder();
        iterable.appendString(stringBuilder1);
        assertEquals("4, 3, 2, 1", stringBuilder1.toString());

        StringBuilder stringBuilder2 = new StringBuilder();
        iterable.appendString(stringBuilder2, "/");
        assertEquals("4/3/2/1", stringBuilder2.toString());

        StringBuilder stringBuilder3 = new StringBuilder();
        iterable.appendString(stringBuilder3, "[", "/", "]");
        assertEquals("[4/3/2/1]", stringBuilder3.toString());
    }

    @Override
    @Test
    default void RichIterable_toString()
    {
        assertEquals("[4, 3, 2, 1]", this.newWith(4, 3, 2, 1).toString());
    }

    @Override
    @Test
    default void RichIterable_toList()
    {
        RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);
        assertEquals(
                Lists.immutable.with(4, 3, 2, 1),
                iterable.toList());

        MutableList<Integer> target = Lists.mutable.empty();
        iterable.each(target::add);
        assertEquals(
                target,
                iterable.toList());
    }

    @Override
    @Test
    default void RichIterable_into()
    {
        assertEquals(
                Lists.immutable.with(4, 3, 2, 1),
                this.newWith(4, 3, 2, 1).into(Lists.mutable.empty()));
    }

    @Override
    @Test
    default void RichIterable_toSortedList()
    {
        RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);

        assertEquals(
                Lists.immutable.with(1, 2, 3, 4),
                iterable.toSortedList());

        assertEquals(
                Lists.immutable.with(4, 3, 2, 1),
                iterable.toSortedList(Comparators.reverseNaturalOrder()));

        assertEquals(
                Lists.immutable.with(1, 2, 3, 4),
                iterable.toSortedListBy(Functions.identity()));

        assertEquals(
                Lists.immutable.with(4, 3, 2, 1),
                iterable.toSortedListBy(each -> each * -1));
    }

    @Override
    @Test
    default void RichIterable_toSet()
    {
        assertEquals(
                Sets.immutable.with(4, 3, 2, 1),
                this.newWith(4, 3, 2, 1).toSet());
    }

    @Override
    @Test
    default void RichIterable_toSortedSet()
    {
        RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);

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

    @Override
    @Test
    default void RichIterable_toBag()
    {
        assertEquals(
                Bags.immutable.with(4, 3, 2, 1),
                this.newWith(4, 3, 2, 1).toBag());
    }

    @Override
    @Test
    default void RichIterable_toSortedBag()
    {
        RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);

        assertEquals(
                TreeBag.newBagWith(1, 2, 3, 4),
                iterable.toSortedBag());

        assertEquals(
                TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 4, 3, 2, 1),
                iterable.toSortedBag(Comparators.reverseNaturalOrder()));

        assertEquals(
                TreeBag.newBagWith(Comparators.byFunction(Functions.identity()), 1, 2, 3, 4),
                iterable.toSortedBagBy(Functions.identity()));

        assertEquals(
                TreeBag.newBagWith(Comparators.byFunction((Integer each) -> each * -1), 4, 3, 2, 1),
                iterable.toSortedBagBy(each -> each * -1));
    }

    @Override
    @Test
    default void RichIterable_toMap()
    {
        RichIterable<Integer> iterable = this.newWith(13, 12, 11, 3, 2, 1);

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

    @Override
    @Test
    default void RichIterable_toSortedMap()
    {
        RichIterable<Integer> iterable = this.newWith(13, 12, 11, 3, 2, 1);

        Pair<String, Integer>[] pairs = new Pair[]
                {
                        Tuples.pair("13", 3),
                        Tuples.pair("12", 2),
                        Tuples.pair("11", 1),
                        Tuples.pair("3", 3),
                        Tuples.pair("2", 2),
                        Tuples.pair("1", 1)
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
}
