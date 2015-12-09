/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.test;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.collection.MutableCollection;
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
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;

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
        RichIterable<Integer> iterable = this.newWith(3, 2, 1);
        MutableCollection<Integer> result = this.newMutableForFilter();
        iterable.forEach(Procedures.cast(i -> result.add(i + 10)));
        assertEquals(this.newMutableForFilter(13, 12, 11), result);
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
        RichIterable<Integer> iterable = this.newWith(4, 3, 2, 1);

        assertEquals(
                this.getExpectedFiltered(4, 2),
                iterable.select(IntegerPredicates.isEven()));

        assertEquals(
                this.getExpectedFiltered(4, 2),
                iterable.select(IntegerPredicates.isEven(), this.<Integer>newMutableForFilter()));

        assertEquals(
                this.getExpectedFiltered(4, 3),
                iterable.selectWith(Predicates2.greaterThan(), 2));

        assertEquals(
                this.getExpectedFiltered(4, 3),
                iterable.selectWith(Predicates2.<Integer>greaterThan(), 2, this.<Integer>newMutableForFilter()));

        assertEquals(
                this.getExpectedFiltered(4, 2),
                iterable.reject(IntegerPredicates.isOdd()));

        assertEquals(
                this.getExpectedFiltered(4, 2),
                iterable.reject(IntegerPredicates.isOdd(), this.<Integer>newMutableForFilter()));

        assertEquals(
                this.getExpectedFiltered(4, 3),
                iterable.rejectWith(Predicates2.lessThan(), 3));

        assertEquals(
                this.getExpectedFiltered(4, 3),
                iterable.rejectWith(Predicates2.<Integer>lessThan(), 3, this.<Integer>newMutableForFilter()));
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
        RichIterable<Number> iterable = this.<Number>newWith(1, 2.0, 3, 4.0);

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

        assertEquals(
                this.getExpectedTransformed(3, 2, 1, 3, 2, 1),
                iterable.collect(i -> i % 10, this.newMutableForTransform()));

        assertEquals(
                this.getExpectedTransformed(3, 2, 1, 3, 2, 1),
                iterable.collectWith((i, mod) -> i % mod, 10));

        assertEquals(
                this.getExpectedTransformed(3, 2, 1, 3, 2, 1),
                iterable.collectWith((i, mod) -> i % mod, 10, this.newMutableForTransform()));
    }

    @Override
    @Test
    default void RichIterable_collectIf()
    {
        assertEquals(
                this.getExpectedTransformed(3, 1, 3, 1),
                this.newWith(13, 12, 11, 3, 2, 1).collectIf(i -> i % 2 != 0, i -> i % 10));

        assertEquals(
                this.newMutableForTransform(3, 1, 3, 1),
                this.newWith(13, 12, 11, 3, 2, 1).collectIf(i -> i % 2 != 0, i -> i % 10, this.newMutableForTransform()));
    }

    @Override
    @Test
    default void RichIterable_collectPrimitive()
    {
        assertEquals(
                this.getExpectedBoolean(false, true, false),
                this.newWith(3, 2, 1).collectBoolean(each -> each % 2 == 0));

        assertEquals(
                this.getExpectedBoolean(false, true, false),
                this.newWith(3, 2, 1).collectBoolean(each -> each % 2 == 0, this.newBooleanForTransform()));

        RichIterable<Integer> iterable = this.newWith(13, 12, 11, 3, 2, 1);

        assertEquals(
                this.getExpectedByte((byte) 3, (byte) 2, (byte) 1, (byte) 3, (byte) 2, (byte) 1),
                iterable.collectByte(each -> (byte) (each % 10)));

        assertEquals(
                this.getExpectedByte((byte) 3, (byte) 2, (byte) 1, (byte) 3, (byte) 2, (byte) 1),
                iterable.collectByte(each -> (byte) (each % 10), this.newByteForTransform()));

        assertEquals(
                this.getExpectedChar((char) 3, (char) 2, (char) 1, (char) 3, (char) 2, (char) 1),
                iterable.collectChar(each -> (char) (each % 10)));

        assertEquals(
                this.getExpectedChar((char) 3, (char) 2, (char) 1, (char) 3, (char) 2, (char) 1),
                iterable.collectChar(each -> (char) (each % 10), this.newCharForTransform()));

        assertEquals(
                this.getExpectedDouble(3.0, 2.0, 1.0, 3.0, 2.0, 1.0),
                iterable.collectDouble(each -> (double) (each % 10)));

        assertEquals(
                this.getExpectedDouble(3.0, 2.0, 1.0, 3.0, 2.0, 1.0),
                iterable.collectDouble(each -> (double) (each % 10), this.newDoubleForTransform()));

        assertEquals(
                this.getExpectedFloat(3.0f, 2.0f, 1.0f, 3.0f, 2.0f, 1.0f),
                iterable.collectFloat(each -> (float) (each % 10)));

        assertEquals(
                this.getExpectedFloat(3.0f, 2.0f, 1.0f, 3.0f, 2.0f, 1.0f),
                iterable.collectFloat(each -> (float) (each % 10), this.newFloatForTransform()));

        assertEquals(
                this.getExpectedInt(3, 2, 1, 3, 2, 1),
                iterable.collectInt(each -> each % 10));

        assertEquals(
                this.getExpectedInt(3, 2, 1, 3, 2, 1),
                iterable.collectInt(each -> each % 10, this.newIntForTransform()));

        assertEquals(
                this.getExpectedLong(3, 2, 1, 3, 2, 1),
                iterable.collectLong(each -> each % 10));

        assertEquals(
                this.getExpectedLong(3, 2, 1, 3, 2, 1),
                iterable.collectLong(each -> each % 10, this.newLongForTransform()));

        assertEquals(
                this.getExpectedShort((short) 3, (short) 2, (short) 1, (short) 3, (short) 2, (short) 1),
                iterable.collectShort(each -> (short) (each % 10)));

        assertEquals(
                this.getExpectedShort((short) 3, (short) 2, (short) 1, (short) 3, (short) 2, (short) 1),
                iterable.collectShort(each -> (short) (each % 10), this.newShortForTransform()));
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
        MutableMultimap<Boolean, Integer> multimap2 = iterable.groupBy(
                groupByFunction,
                this.<Integer>newWith().groupBy(function).toMutable());
        assertEquals(groupByExpected, multimap2.toMap());

        Function<Integer, Iterable<Integer>> groupByEachFunction = integer -> Interval.fromTo(-1, -integer);

        MutableMap<Integer, RichIterable<Integer>> expectedGroupByEach =
                UnifiedMap.newWithKeysValues(
                        -4, this.newMutableForFilter(4),
                        -3, this.newMutableForFilter(4, 3),
                        -2, this.newMutableForFilter(4, 3, 2),
                        -1, this.newMutableForFilter(4, 3, 2, 1));

        assertEquals(expectedGroupByEach, iterable.groupByEach(groupByEachFunction).toMap());

        Multimap<Integer, Integer> actualWithTarget =
                iterable.groupByEach(groupByEachFunction, this.<Integer>newWith().groupByEach(groupByEachFunction).toMutable());
        assertEquals(expectedGroupByEach, actualWithTarget.toMap());
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
        assertEquals(
                Lists.immutable.with(4, 3, 2, 1),
                this.newWith(4, 3, 2, 1).toList());
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
    }
}
