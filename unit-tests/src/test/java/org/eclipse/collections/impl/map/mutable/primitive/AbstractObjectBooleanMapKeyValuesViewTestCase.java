/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.ObjectBooleanMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.partition.PartitionIterable;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.primitive.ObjectBooleanPair;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

/**
 * Abstract JUnit test for {@link ObjectBooleanMap#keyValuesView()}.
 */
public abstract class AbstractObjectBooleanMapKeyValuesViewTestCase
{
    public abstract <K> ObjectBooleanMap<K> newWithKeysValues(K key1, boolean value1, K key2, boolean value2, K key3, boolean value3);

    public abstract <K> ObjectBooleanMap<K> newWithKeysValues(K key1, boolean value1, K key2, boolean value2);

    public abstract <K> ObjectBooleanMap<K> newWithKeysValues(K key1, boolean value1);

    public abstract <K> ObjectBooleanMap<K> newEmpty();

    public RichIterable<ObjectBooleanPair<Object>> newWith()
    {
        return this.newEmpty().keyValuesView();
    }

    public <K> RichIterable<ObjectBooleanPair<K>> newWith(K key1, boolean value1)
    {
        return this.newWithKeysValues(key1, value1).keyValuesView();
    }

    public <K> RichIterable<ObjectBooleanPair<K>> newWith(K key1, boolean value1, K key2, boolean value2)
    {
        return this.newWithKeysValues(key1, value1, key2, value2).keyValuesView();
    }

    public <K> RichIterable<ObjectBooleanPair<K>> newWith(K key1, boolean value1, K key2, boolean value2, K key3, boolean value3)
    {
        return this.newWithKeysValues(key1, value1, key2, value2, key3, value3).keyValuesView();
    }

    @Test
    public void containsAllIterable()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Assert.assertTrue(collection.containsAllIterable(FastList.newListWith(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), false))));
        Assert.assertFalse(collection.containsAllIterable(FastList.newListWith(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(1.0, Integer.valueOf(5)))));
    }

    @Test
    public void containsAllArray()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Assert.assertTrue(collection.containsAllArguments(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), false)));
        Assert.assertFalse(collection.containsAllArguments(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(1.0, Integer.valueOf(5))));
    }

    @Test
    public void forEach()
    {
        MutableList<ObjectBooleanPair<Integer>> result = Lists.mutable.of();
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        collection.forEach(CollectionAddProcedure.on(result));
        Verify.assertSize(3, result);
        Verify.assertContainsAll(result, PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), false), PrimitiveTuples.pair(Integer.valueOf(3), true));
    }

    @Test
    public void forEachWith()
    {
        MutableBag<ObjectBooleanPair<Integer>> result = Bags.mutable.of();
        MutableBag<Integer> result2 = Bags.mutable.of();
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        collection.forEachWith((argument1, argument2) -> {
            result.add(argument1);
            result2.add(argument2);
        }, 0);

        Assert.assertEquals(Bags.immutable.of(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), false), PrimitiveTuples.pair(Integer.valueOf(3), true)), result);
        Assert.assertEquals(Bags.immutable.of(0, 0, 0), result2);
    }

    @Test
    public void forEachWithIndex()
    {
        MutableBag<ObjectBooleanPair<Integer>> elements = Bags.mutable.of();
        MutableBag<Integer> indexes = Bags.mutable.of();
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        collection.forEachWithIndex((object, index) -> {
            elements.add(object);
            indexes.add(index);
        });
        Assert.assertEquals(Bags.mutable.of(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), false), PrimitiveTuples.pair(Integer.valueOf(3), true)), elements);
        Assert.assertEquals(Bags.mutable.of(0, 1, 2), indexes);
    }

    @Test
    public void select()
    {
        MutableList<ObjectBooleanPair<Integer>> result = this.newWith(1, true, 2, false, 3, true).select(PrimitiveTuples.pair(Integer.valueOf(2), false)::equals).toList();
        Verify.assertContains(PrimitiveTuples.pair(Integer.valueOf(2), false), result);
        Verify.assertNotContains(PrimitiveTuples.pair(Integer.valueOf(1), true), result);
        Verify.assertNotContains(PrimitiveTuples.pair(Integer.valueOf(3), true), result);
    }

    @Test
    public void selectWith()
    {
        MutableList<ObjectBooleanPair<Integer>> result = this.newWith(1, true, 2, false, 3, true).selectWith(Object::equals, PrimitiveTuples.pair(Integer.valueOf(2), false)).toList();
        Verify.assertContains(PrimitiveTuples.pair(Integer.valueOf(2), false), result);
        Verify.assertNotContains(PrimitiveTuples.pair(Integer.valueOf(1), true), result);
        Verify.assertNotContains(PrimitiveTuples.pair(Integer.valueOf(3), true), result);
    }

    @Test
    public void selectWith_target()
    {
        HashBag<ObjectBooleanPair<Integer>> result = this.newWith(1, true, 2, false, 3, true).selectWith(Predicates2.notEqual(), PrimitiveTuples.pair(Integer.valueOf(2), false), HashBag.newBag());
        Assert.assertEquals(Bags.immutable.of(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(3), true)), result);
    }

    @Test
    public void reject()
    {
        MutableList<ObjectBooleanPair<Integer>> result = this.newWith(1, true, 2, false, 3, true).reject(Predicates.notEqual(PrimitiveTuples.pair(Integer.valueOf(2), false))).toList();
        Verify.assertContains(PrimitiveTuples.pair(Integer.valueOf(2), false), result);
        Verify.assertNotContains(PrimitiveTuples.pair(Integer.valueOf(1), true), result);
        Verify.assertNotContains(PrimitiveTuples.pair(Integer.valueOf(3), true), result);
    }

    @Test
    public void rejectWith()
    {
        MutableList<ObjectBooleanPair<Integer>> result = this.newWith(1, true, 2, false, 3, true).rejectWith(Predicates2.notEqual(), PrimitiveTuples.pair(Integer.valueOf(2), false)).toList();
        Verify.assertContains(PrimitiveTuples.pair(Integer.valueOf(2), false), result);
        Verify.assertNotContains(PrimitiveTuples.pair(Integer.valueOf(1), true), result);
        Verify.assertNotContains(PrimitiveTuples.pair(Integer.valueOf(3), true), result);
    }

    @Test
    public void rejectWith_target()
    {
        HashBag<ObjectBooleanPair<Integer>> result = this.newWith(1, true, 2, false, 3, true).rejectWith(Object::equals, PrimitiveTuples.pair(Integer.valueOf(2), false), HashBag.newBag());
        Assert.assertEquals(Bags.immutable.of(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(3), true)), result);
    }

    @Test
    public void selectInstancesOf()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(1, true, 2, false, 3, true);
        Verify.assertIterableEmpty(pairs.selectInstancesOf(Integer.class));
        Verify.assertContainsAll(pairs.selectInstancesOf(ObjectBooleanPair.class), PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(3), true), PrimitiveTuples.pair(Integer.valueOf(2), false));
    }

    @Test
    public void collect()
    {
        RichIterable<Integer> result1 = this.newWith(2, true, 3, false, 4, true).collect(ObjectBooleanPair::getOne);

        Assert.assertEquals(HashBag.newBagWith(2, 3, 4), result1.toBag());
    }

    @Test
    public void flatCollect()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Function<ObjectBooleanPair<Integer>, MutableList<String>> function =
                object -> FastList.newListWith(String.valueOf(object));

        Verify.assertListsEqual(
                FastList.newListWith("1:true", "2:false", "3:true"),
                collection.flatCollect(function).toSortedList());

        Verify.assertSetsEqual(
                UnifiedSet.newSetWith("1:true", "2:false", "3:true"),
                collection.flatCollect(function, UnifiedSet.newSet()));
    }

    @Test
    public void detect()
    {
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(2), false), this.newWith(1, true, 2, false, 3, true).detect(PrimitiveTuples.pair(Integer.valueOf(2), false)::equals));
        Assert.assertNull(this.newWith(1, true, 2, false, 3, true).detect(PrimitiveTuples.pair(true, Integer.valueOf(4))::equals));
    }

    @Test(expected = NoSuchElementException.class)
    public void min_empty_throws()
    {
        this.newWith().min(ObjectBooleanPair::compareTo);
    }

    @Test(expected = NoSuchElementException.class)
    public void max_empty_throws()
    {
        this.newWith().max(ObjectBooleanPair::compareTo);
    }

    @Test
    public void min()
    {
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(1), true), this.newWith(1, true, 2, false, 3, true).min(ObjectBooleanPair::compareTo));
    }

    @Test
    public void max()
    {
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(3), true), this.newWith(1, true, 2, false, 3, true).max(ObjectBooleanPair::compareTo));
    }

    @Test
    public void min_without_comparator()
    {
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(1), true), this.newWith(1, true, 2, false, 3, true).min(ObjectBooleanPair::compareTo));
    }

    @Test
    public void max_without_comparator()
    {
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(3), true), this.newWith(1, true, 2, false, 3, true).max(ObjectBooleanPair::compareTo));
    }

    @Test
    public void minBy()
    {
        Function<ObjectBooleanPair<Integer>, Integer> function = ObjectBooleanPair::getOne;
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(2), true), this.newWith(2, true, 3, false, 4, true).minBy(function));
    }

    @Test
    public void maxBy()
    {
        Function<ObjectBooleanPair<Integer>, Integer> function = object -> object.getOne() & 1;
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(3), false), this.newWith(2, true, 3, false, 4, true).maxBy(function));
    }

    @Test
    public void detectWith()
    {
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(2), false), this.newWith(1, true, 2, false, 3, true).detectWith(Object::equals, PrimitiveTuples.pair(Integer.valueOf(2), false)));
        Assert.assertNull(this.newWith(1, true, 2, false, 3, true).detectWith(Object::equals, PrimitiveTuples.pair(true, Integer.valueOf(4))));
    }

    @Test
    public void detectIfNone()
    {
        Function0<ObjectBooleanPair<Integer>> function = () -> PrimitiveTuples.pair(Integer.valueOf(5), true);
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(2), false), this.newWith(1, true, 2, false, 3, true).detectIfNone(PrimitiveTuples.pair(Integer.valueOf(2), false)::equals, function));
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(5), true), this.newWith(1, true, 2, false, 3, true).detectIfNone(PrimitiveTuples.pair(true, Integer.valueOf(4))::equals, function));
    }

    @Test
    public void detectWithIfNoneBlock()
    {
        Function0<ObjectBooleanPair<Integer>> function = () -> PrimitiveTuples.pair(Integer.valueOf(5), true);
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(2), false), this.newWith(1, true, 2, false, 3, true).detectWithIfNone(
                Object::equals,
                PrimitiveTuples.pair(Integer.valueOf(2), false),
                function));
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(5), true), this.newWith(1, true, 2, false, 3, true).detectWithIfNone(
                Object::equals,
                PrimitiveTuples.pair(true, Integer.valueOf(4)),
                function));
    }

    @Test
    public void allSatisfy()
    {
        Assert.assertTrue(this.newWith(1, true, 2, false, 3, true).allSatisfy(ObjectBooleanPair.class::isInstance));
        Assert.assertFalse(this.newWith(1, true, 2, false, 3, true).allSatisfy(PrimitiveTuples.pair(Integer.valueOf(2), false)::equals));
    }

    @Test
    public void allSatisfyWith()
    {
        Assert.assertTrue(this.newWith(1, true, 2, false, 3, true).allSatisfyWith(Predicates2.instanceOf(), ObjectBooleanPair.class));
        Assert.assertFalse(this.newWith(1, true, 2, false, 3, true).allSatisfyWith(Object::equals, PrimitiveTuples.pair(Integer.valueOf(2), false)));
    }

    @Test
    public void noneSatisfy()
    {
        Assert.assertTrue(this.newWith(1, true, 2, false, 3, true).noneSatisfy(Boolean.class::isInstance));
        Assert.assertFalse(this.newWith(1, true, 2, false, 3, true).noneSatisfy(PrimitiveTuples.pair(Integer.valueOf(2), false)::equals));
    }

    @Test
    public void noneSatisfyWith()
    {
        Assert.assertTrue(this.newWith(1, true, 2, false, 3, true).noneSatisfyWith(Predicates2.instanceOf(), Boolean.class));
        Assert.assertFalse(this.newWith(1, true, 2, false, 3, true).noneSatisfyWith(Object::equals, PrimitiveTuples.pair(Integer.valueOf(2), false)));
    }

    @Test
    public void anySatisfy()
    {
        Assert.assertTrue(this.newWith(1, true, 2, false, 3, true).anySatisfy(PrimitiveTuples.pair(Integer.valueOf(2), false)::equals));
        Assert.assertFalse(this.newWith(1, true, 2, false, 3, true).anySatisfy(PrimitiveTuples.pair(true, Integer.valueOf(5))::equals));
    }

    @Test
    public void anySatisfyWith()
    {
        Assert.assertTrue(this.newWith(1, true, 2, false, 3, true).anySatisfyWith(Object::equals, PrimitiveTuples.pair(Integer.valueOf(2), false)));
        Assert.assertFalse(this.newWith(1, true, 2, false, 3, true).anySatisfyWith(Object::equals, PrimitiveTuples.pair(true, Integer.valueOf(5))));
    }

    @Test
    public void count()
    {
        Assert.assertEquals(0, this.newWith(1, true, 2, false, 3, true).count(Boolean.class::isInstance));
        Assert.assertEquals(3, this.newWith(1, true, 2, false, 3, true).count(ObjectBooleanPair.class::isInstance));
        Assert.assertEquals(1, this.newWith(1, true, 2, false, 3, true).count(PrimitiveTuples.pair(Integer.valueOf(2), false)::equals));
    }

    @Test
    public void countWith()
    {
        Assert.assertEquals(0, this.newWith(1, true, 2, false, 3, true).countWith(Predicates2.instanceOf(), Boolean.class));
        Assert.assertEquals(3, this.newWith(1, true, 2, false, 3, true).countWith(Predicates2.instanceOf(), ObjectBooleanPair.class));
        Assert.assertEquals(1, this.newWith(1, true, 2, false, 3, true).countWith(Object::equals, PrimitiveTuples.pair(Integer.valueOf(2), false)));
    }

    @Test
    public void collectIf()
    {
        Verify.assertContainsAll(
                this.newWith(1, true, 2, false, 3, true).collectIf(
                        ObjectBooleanPair.class::isInstance,
                        String::valueOf),
                "1:true", "2:false", "3:true");
        Verify.assertContainsAll(
                this.newWith(1, true, 2, false, 3, true).collectIf(
                        ObjectBooleanPair.class::isInstance,
                        String::valueOf,
                        UnifiedSet.newSet()),
                "1:true", "2:false", "3:true");
    }

    @Test
    public void collectWith()
    {
        Assert.assertEquals(
                Bags.mutable.of(5L, 7L, 9L),
                this.newWith(2, true, 3, false, 4, true).collectWith((argument1, argument2) -> argument1.getOne() + argument1.getOne() + argument2, 1L).toBag());
    }

    @Test
    public void collectWith_target()
    {
        Assert.assertEquals(
                Bags.mutable.of(5L, 7L, 9L),
                this.newWith(2, true, 3, false, 4, true).collectWith((argument1, argument2) -> argument1.getOne() + argument1.getOne() + argument2, 1L, HashBag.newBag()));
    }

    @Test
    public void getFirst()
    {
        ObjectBooleanPair<Integer> first = this.newWith(1, true, 2, false, 3, true).getFirst();
        Assert.assertTrue(PrimitiveTuples.pair(Integer.valueOf(1), true).equals(first)
                || PrimitiveTuples.pair(Integer.valueOf(2), false).equals(first)
                || PrimitiveTuples.pair(Integer.valueOf(3), true).equals(first));
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(1), true), this.newWith(1, true).getFirst());
    }

    @Test
    public void getLast()
    {
        ObjectBooleanPair<Integer> last = this.newWith(1, true, 2, false, 3, true).getLast();
        Assert.assertTrue(PrimitiveTuples.pair(Integer.valueOf(1), true).equals(last)
                || PrimitiveTuples.pair(Integer.valueOf(2), false).equals(last)
                || PrimitiveTuples.pair(Integer.valueOf(3), true).equals(last));
        Assert.assertEquals(PrimitiveTuples.pair(Integer.valueOf(1), true), this.newWith(1, true).getLast());
    }

    @Test
    public void isEmpty()
    {
        Verify.assertIterableEmpty(this.newWith());
        Verify.assertIterableNotEmpty(this.newWith(1, true));
        Assert.assertTrue(this.newWith(1, true).notEmpty());
    }

    @Test
    public void iterator()
    {
        RichIterable<ObjectBooleanPair<Integer>> objects = this.newWith(1, true, 2, false, 3, true);
        MutableBag<ObjectBooleanPair<Integer>> actual = Bags.mutable.of();
        Iterator<ObjectBooleanPair<Integer>> iterator = objects.iterator();
        for (int i = objects.size(); i-- > 0; )
        {
            Assert.assertTrue(iterator.hasNext());
            actual.add(iterator.next());
        }

        Assert.assertFalse(iterator.hasNext());
        Verify.assertThrows(UnsupportedOperationException.class, iterator::remove);
        Assert.assertEquals(objects.toBag(), actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator_throws()
    {
        RichIterable<ObjectBooleanPair<Integer>> objects = this.newWith(1, true, 2, false, 3, true);
        Iterator<ObjectBooleanPair<Integer>> iterator = objects.iterator();
        for (int i = objects.size(); i-- > 0; )
        {
            Assert.assertTrue(iterator.hasNext());
            iterator.next();
        }
        Assert.assertFalse(iterator.hasNext());
        iterator.next();
    }

    @Test
    public void injectInto()
    {
        RichIterable<ObjectBooleanPair<Integer>> objects = this.newWith(2, true, 3, false, 4, true);
        Long result = objects.injectInto(1L, (Long argument1, ObjectBooleanPair<Integer> argument2) -> argument1 + argument2.getOne() + argument2.getOne());
        Assert.assertEquals(Long.valueOf(19), result);
    }

    @Test
    public void injectIntoInt()
    {
        RichIterable<ObjectBooleanPair<Integer>> objects = this.newWith(2, true, 3, false, 4, true);
        int result = objects.injectInto(1, (int intParameter, ObjectBooleanPair<Integer> argument2) -> intParameter + argument2.getOne() + argument2.getOne());
        Assert.assertEquals(19, result);
    }

    @Test
    public void injectIntoLong()
    {
        RichIterable<ObjectBooleanPair<Integer>> objects = this.newWith(2, true, 3, false, 4, true);
        long result = objects.injectInto(1L, (long parameter, ObjectBooleanPair<Integer> argument2) -> parameter + argument2.getOne() + argument2.getOne());
        Assert.assertEquals(19, result);
    }

    @Test
    public void injectIntoDouble()
    {
        RichIterable<ObjectBooleanPair<Integer>> objects = this.newWith(2, true, 3, false, 4, true);
        double result = objects.injectInto(1.0, (parameter, argument2) -> parameter + argument2.getOne() + argument2.getOne());
        Assert.assertEquals(19.0, result, 0.0);
    }

    @Test
    public void injectIntoFloat()
    {
        RichIterable<ObjectBooleanPair<Integer>> objects = this.newWith(2, true, 3, false, 4, true);
        float result = objects.injectInto(1.0f, (float parameter, ObjectBooleanPair<Integer> argument2) -> parameter + argument2.getOne() + argument2.getOne());
        Assert.assertEquals(19.0, result, 0.0);
    }

    @Test
    public void sumFloat()
    {
        RichIterable<ObjectBooleanPair<Integer>> objects = this.newWith(2, true, 3, false, 4, true);
        double actual = objects.sumOfFloat(each -> (float) (each.getOne() + each.getOne()));
        Assert.assertEquals(18.0, actual, 0.0);
    }

    @Test
    public void sumDouble()
    {
        RichIterable<ObjectBooleanPair<Integer>> objects = this.newWith(2, true, 3, false, 4, true);
        double actual = objects.sumOfDouble(each -> (double) (each.getOne() + each.getOne()));
        Assert.assertEquals(18.0, actual, 0.0);
    }

    @Test
    public void sumInteger()
    {
        RichIterable<ObjectBooleanPair<Integer>> objects = this.newWith(2, true, 3, false, 4, true);
        long actual = objects.sumOfInt(each -> each.getOne() + each.getOne());
        Assert.assertEquals(18, actual);
    }

    @Test
    public void sumLong()
    {
        RichIterable<ObjectBooleanPair<Integer>> objects = this.newWith(2, true, 3, false, 4, true);
        long actual = objects.sumOfLong(each -> (long) (each.getOne() + each.getOne()));
        Assert.assertEquals(18, actual);
    }

    @Test
    public void toArray()
    {
        RichIterable<ObjectBooleanPair<Integer>> objects = this.newWith(1, true, 2, false, 3, true);
        Object[] array = objects.toArray();
        Verify.assertSize(3, array);
        ObjectBooleanPair<Integer>[] array2 = objects.toArray(new ObjectBooleanPair[3]);
        Verify.assertSize(3, array2);
    }

    @Test
    public void partition()
    {
        PartitionIterable<ObjectBooleanPair<Integer>> result = this.newWith(1, true, 2, false, 3, true).partition(PrimitiveTuples.pair(Integer.valueOf(2), false)::equals);
        Verify.assertContains(PrimitiveTuples.pair(Integer.valueOf(2), false), result.getSelected().toList());
        Verify.assertIterableSize(1, result.getSelected());
        Verify.assertContains(PrimitiveTuples.pair(Integer.valueOf(1), true), result.getRejected().toList());
        Verify.assertContains(PrimitiveTuples.pair(Integer.valueOf(3), true), result.getRejected().toList());
        Verify.assertIterableSize(2, result.getRejected());
    }

    @Test
    public void toList()
    {
        MutableList<ObjectBooleanPair<Integer>> list = this.newWith(1, true, 2, false, 3, true).toList();
        Verify.assertContainsAll(list, PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), false), PrimitiveTuples.pair(Integer.valueOf(3), true));
    }

    @Test
    public void toBag()
    {
        MutableBag<ObjectBooleanPair<Integer>> bag = this.newWith(1, true, 2, false, 3, true).toBag();
        Verify.assertContainsAll(bag, PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), false), PrimitiveTuples.pair(Integer.valueOf(3), true));
    }

    @Test
    public void toSortedList_natural_ordering()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(5, false, 1, true, 2, true);
        MutableList<ObjectBooleanPair<Integer>> list = pairs.toSortedList();
        Assert.assertEquals(Lists.mutable.of(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), true), PrimitiveTuples.pair(Integer.valueOf(5), false)), list);
    }

    @Test
    public void toSortedList_with_comparator()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(5, false, 1, true, 2, true);
        MutableList<ObjectBooleanPair<Integer>> list = pairs.toSortedList(Comparators.reverseNaturalOrder());
        Assert.assertEquals(Lists.mutable.of(PrimitiveTuples.pair(Integer.valueOf(5), false), PrimitiveTuples.pair(Integer.valueOf(2), true), PrimitiveTuples.pair(Integer.valueOf(1), true)), list);
    }

    @Test
    public void toSortedListBy()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(5, false, 1, true, 2, true);
        MutableList<ObjectBooleanPair<Integer>> list = pairs.toSortedListBy(String::valueOf);
        Assert.assertEquals(Lists.mutable.of(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), true), PrimitiveTuples.pair(Integer.valueOf(5), false)), list);
    }

    @Test
    public void toSortedBag_natural_ordering()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(5, false, 1, true, 2, true);
        MutableSortedBag<ObjectBooleanPair<Integer>> bag = pairs.toSortedBag();
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), true), PrimitiveTuples.pair(Integer.valueOf(5), false)), bag);
    }

    @Test
    public void toSortedBag_with_comparator()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(5, false, 1, true, 2, true);
        MutableSortedBag<ObjectBooleanPair<Integer>> bag = pairs.toSortedBag(Comparators.reverseNaturalOrder());
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), PrimitiveTuples.pair(Integer.valueOf(5), false), PrimitiveTuples.pair(Integer.valueOf(2), true), PrimitiveTuples.pair(Integer.valueOf(1), true)), bag);
    }

    @Test
    public void toSortedBagBy()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(5, false, 1, true, 2, true);
        MutableSortedBag<ObjectBooleanPair<Integer>> bag = pairs.toSortedBagBy(String::valueOf);
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), true), PrimitiveTuples.pair(Integer.valueOf(5), false)), bag);
    }

    @Test
    public void toSortedSet_natural_ordering()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(5, false, 1, true, 2, true);
        MutableSortedSet<ObjectBooleanPair<Integer>> set = pairs.toSortedSet();
        Verify.assertSortedSetsEqual(TreeSortedSet.newSetWith(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), true), PrimitiveTuples.pair(Integer.valueOf(5), false)), set);
    }

    @Test
    public void toSortedSet_with_comparator()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(5, false, 1, true, 2, true);
        MutableSortedSet<ObjectBooleanPair<Integer>> set = pairs.toSortedSet(Comparators.reverseNaturalOrder());
        Verify.assertSortedSetsEqual(TreeSortedSet.newSetWith(Comparators.reverseNaturalOrder(),
                PrimitiveTuples.pair(Integer.valueOf(5), false),
                PrimitiveTuples.pair(Integer.valueOf(2), true),
                PrimitiveTuples.pair(Integer.valueOf(1), true)),
                set);
    }

    @Test
    public void toSortedSetBy()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(5, false, 1, true, 2, true);
        MutableSortedSet<ObjectBooleanPair<Integer>> set = pairs.toSortedSetBy(String::valueOf);
        Verify.assertSortedSetsEqual(TreeSortedSet.newSetWith(PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), true), PrimitiveTuples.pair(Integer.valueOf(5), false)), set);
    }

    @Test
    public void toSet()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(1, true, 2, false, 3, true);
        MutableSet<ObjectBooleanPair<Integer>> set = pairs.toSet();
        Verify.assertContainsAll(set, PrimitiveTuples.pair(Integer.valueOf(1), true), PrimitiveTuples.pair(Integer.valueOf(2), false), PrimitiveTuples.pair(Integer.valueOf(3), true));
    }

    @Test
    public void toMap()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(1, true, 2, false, 3, true);
        MutableMap<String, String> map =
                pairs.toMap(String::valueOf, String::valueOf);
        Assert.assertEquals(UnifiedMap.newWithKeysValues("1:true", "1:true", "2:false", "2:false", "3:true", "3:true"), map);
    }

    @Test
    public void toSortedMap()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(1, true, 2, false, 3, true);
        MutableSortedMap<String, String> map =
                pairs.toSortedMap(String::valueOf, String::valueOf);
        Assert.assertEquals(TreeSortedMap.newMapWith("1:true", "1:true", "2:false", "2:false", "3:true", "3:true"), map);
    }

    @Test
    public void toSortedMap_with_comparator()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(1, true, 2, false, 3, true);
        MutableSortedMap<String, String> map =
                pairs.toSortedMap(Comparators.reverseNaturalOrder(), String::valueOf, String::valueOf);
        Assert.assertEquals(TreeSortedMap.newMapWith(Comparators.reverseNaturalOrder(), "1:true", "1:true", "2:false", "2:false", "3:true", "3:true"), map);
    }

    @Test
    public void toSortedMapBy()
    {
        RichIterable<ObjectBooleanPair<Integer>> pairs = this.newWith(1, true, 2, false, 3, true);
        MutableSortedMap<String, String> map =
                pairs.toSortedMapBy(String::valueOf, String::valueOf, String::valueOf);
        Assert.assertEquals(TreeSortedMap.newMapWith(Comparators.naturalOrder(), "1:true", "1:true", "2:false", "2:false", "3:true", "3:true"), map);
    }

    @Test
    public void testToString()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false);
        Assert.assertTrue("[1:true, 2:false]".equals(collection.toString())
                || "[2:false, 1:true]".equals(collection.toString()));
    }

    @Test
    public void makeString()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Assert.assertEquals(collection.toString(), '[' + collection.makeString() + ']');
    }

    @Test
    public void makeStringWithSeparator()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Assert.assertEquals(collection.toString(), '[' + collection.makeString(", ") + ']');
    }

    @Test
    public void makeStringWithSeparatorAndStartAndEnd()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Assert.assertEquals(collection.toString(), collection.makeString("[", ", ", "]"));
    }

    @Test
    public void appendString()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Appendable builder = new StringBuilder();
        collection.appendString(builder);
        Assert.assertEquals(collection.toString(), '[' + builder.toString() + ']');
    }

    @Test
    public void appendStringWithSeparator()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Appendable builder = new StringBuilder();
        collection.appendString(builder, ", ");
        Assert.assertEquals(collection.toString(), '[' + builder.toString() + ']');
    }

    @Test
    public void appendStringWithSeparatorAndStartAndEnd()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Appendable builder = new StringBuilder();
        collection.appendString(builder, "[", ", ", "]");
        Assert.assertEquals(collection.toString(), builder.toString());
    }

    @Test
    public void groupBy()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Function<ObjectBooleanPair<Integer>, Boolean> function = object -> PrimitiveTuples.pair(Integer.valueOf(1), true).equals(object);

        Multimap<Boolean, ObjectBooleanPair<Integer>> multimap = collection.groupBy(function);
        Assert.assertEquals(3, multimap.size());
        Assert.assertTrue(multimap.containsKeyAndValue(Boolean.TRUE, PrimitiveTuples.pair(Integer.valueOf(1), true)));
        Assert.assertTrue(multimap.containsKeyAndValue(Boolean.FALSE, PrimitiveTuples.pair(Integer.valueOf(2), false)));
        Assert.assertTrue(multimap.containsKeyAndValue(Boolean.FALSE, PrimitiveTuples.pair(Integer.valueOf(3), true)));
    }

    @Test
    public void groupByEach()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Function<ObjectBooleanPair<Integer>, MutableList<Boolean>> function = object -> Lists.mutable.of(PrimitiveTuples.pair(Integer.valueOf(1), true).equals(object));

        Multimap<Boolean, ObjectBooleanPair<Integer>> multimap = collection.groupByEach(function);
        Assert.assertEquals(3, multimap.size());
        Assert.assertTrue(multimap.containsKeyAndValue(Boolean.TRUE, PrimitiveTuples.pair(Integer.valueOf(1), true)));
        Assert.assertTrue(multimap.containsKeyAndValue(Boolean.FALSE, PrimitiveTuples.pair(Integer.valueOf(2), false)));
        Assert.assertTrue(multimap.containsKeyAndValue(Boolean.FALSE, PrimitiveTuples.pair(Integer.valueOf(3), true)));
    }

    @Test
    public void zip()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false);
        RichIterable<Pair<ObjectBooleanPair<Integer>, Integer>> result = collection.zip(Interval.oneTo(5));

        Assert.assertTrue(Bags.mutable.of(Tuples.pair(PrimitiveTuples.pair(Integer.valueOf(1), true), 1), Tuples.pair(PrimitiveTuples.pair(Integer.valueOf(2), false), 2)).equals(result.toBag())
                || Bags.mutable.of(Tuples.pair(PrimitiveTuples.pair(Integer.valueOf(2), false), 1), Tuples.pair(PrimitiveTuples.pair(Integer.valueOf(1), true), 2)).equals(result.toBag()));
    }

    @Test
    public void zipWithIndex()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false);
        RichIterable<Pair<ObjectBooleanPair<Integer>, Integer>> result = collection.zipWithIndex();
        Assert.assertTrue(Bags.mutable.of(Tuples.pair(PrimitiveTuples.pair(Integer.valueOf(1), true), 0), Tuples.pair(PrimitiveTuples.pair(Integer.valueOf(2), false), 1)).equals(result.toBag())
                || Bags.mutable.of(Tuples.pair(PrimitiveTuples.pair(Integer.valueOf(2), false), 0), Tuples.pair(PrimitiveTuples.pair(Integer.valueOf(1), true), 1)).equals(result.toBag()));
    }

    @Test
    public void chunk()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Assert.assertEquals(Bags.immutable.of(FastList.newListWith(PrimitiveTuples.pair(Integer.valueOf(1), true)),
                FastList.newListWith(PrimitiveTuples.pair(Integer.valueOf(2), false)),
                FastList.newListWith(PrimitiveTuples.pair(Integer.valueOf(3), true))),
                collection.chunk(1).toBag());
    }

    @Test(expected = IllegalArgumentException.class)
    public void chunk_zero_throws()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        collection.chunk(0);
    }

    @Test
    public void chunk_large_size()
    {
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, true, 2, false, 3, true);
        Verify.assertIterableSize(3, collection.chunk(10).getFirst());
    }

    @Test
    public void empty()
    {
        Verify.assertIterableEmpty(this.newWith());
        Assert.assertTrue(this.newWith().isEmpty());
        Assert.assertFalse(this.newWith().notEmpty());
    }

    @Test
    public void notEmpty()
    {
        RichIterable<ObjectBooleanPair<Integer>> notEmpty = this.newWith(1, true);
        Verify.assertIterableNotEmpty(notEmpty);
    }

    @Test
    public void aggregateByMutating()
    {
        Procedure2<AtomicInteger, ObjectBooleanPair<Integer>> sumAggregator = (aggregate, value) -> aggregate.addAndGet(value.getOne());
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(2, true, 3, false, 4, true);
        MapIterable<String, AtomicInteger> aggregation = collection.aggregateInPlaceBy(String::valueOf, AtomicInteger::new, sumAggregator);
        Assert.assertEquals(4, aggregation.get("4:true").intValue());
        Assert.assertEquals(3, aggregation.get("3:false").intValue());
        Assert.assertEquals(2, aggregation.get("2:true").intValue());
    }

    @Test
    public void aggregateByNonMutating()
    {
        Function2<Integer, ObjectBooleanPair<Integer>, Integer> sumAggregator = (aggregate, value) -> aggregate + value.getOne();
        RichIterable<ObjectBooleanPair<Integer>> collection = this.newWith(1, false, 1, false, 2, true);
        MapIterable<String, Integer> aggregation = collection.aggregateBy(String::valueOf, () -> 0, sumAggregator);
        Assert.assertEquals(2, aggregation.get("2:true").intValue());
        Assert.assertEquals(1, aggregation.get("1:false").intValue());
    }
}
