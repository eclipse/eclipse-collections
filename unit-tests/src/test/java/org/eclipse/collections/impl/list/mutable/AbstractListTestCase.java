/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableByteList;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableFloatList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.list.primitive.MutableShortList;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.collection.mutable.AbstractCollectionTestCase;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.factory.primitive.ByteLists;
import org.eclipse.collections.impl.factory.primitive.CharLists;
import org.eclipse.collections.impl.factory.primitive.DoubleLists;
import org.eclipse.collections.impl.factory.primitive.FloatLists;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.factory.primitive.LongLists;
import org.eclipse.collections.impl.factory.primitive.ShortLists;
import org.eclipse.collections.impl.lazy.ReverseIterable;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.fixed.ArrayAdapter;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;
import static org.junit.Assert.fail;

/**
 * Abstract JUnit test for {@link MutableList}s.
 */
public abstract class AbstractListTestCase
        extends AbstractCollectionTestCase
{
    @Override
    protected abstract <T> MutableList<T> newWith(T... littleElements);

    @Test
    public void randomAccess_throws()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> new ListAdapter<>(FastList.newListWith(1, 2, 3)));
    }

    @Test
    public void getFirstOptional()
    {
        Assert.assertEquals(Integer.valueOf(1), this.newWith(1, 2, 3).getFirstOptional().get());
        Assert.assertTrue(this.newWith(1, 2, 3).getFirstOptional().isPresent());
        Assert.assertFalse(this.newWith().getFirstOptional().isPresent());
    }

    @Test
    public void getLastOptional()
    {
        Assert.assertEquals(Integer.valueOf(3), this.newWith(1, 2, 3).getLastOptional().get());
        Assert.assertTrue(this.newWith(1, 2, 3).getLastOptional().isPresent());
        Assert.assertFalse(this.newWith().getLastOptional().isPresent());
    }

    @Test
    public void detectIndex()
    {
        Assert.assertEquals(1, this.newWith(1, 2, 3, 4).detectIndex(integer -> integer % 2 == 0));
        Assert.assertEquals(0, this.newWith(1, 2, 3, 4).detectIndex(integer -> integer % 2 != 0));
        Assert.assertEquals(-1, this.newWith(1, 2, 3, 4).detectIndex(integer -> integer % 5 == 0));
        Assert.assertEquals(2, this.newWith(1, 1, 2, 2, 3, 3, 3, 4, 2).detectIndex(integer -> integer == 2));
        Assert.assertEquals(0, this.newWith(1, 1, 2, 2, 3, 3, 3, 4, 2).detectIndex(integer -> integer != 2));
        Assert.assertEquals(-1, this.newWith(1, 1, 2, 2, 3, 3, 3, 4, 2).detectIndex(integer -> integer == 5));
    }

    @Test
    public void detectLastIndex()
    {
        Assert.assertEquals(3, this.newWith(1, 2, 3, 4).detectLastIndex(integer -> integer % 2 == 0));
        Assert.assertEquals(2, this.newWith(1, 2, 3, 4).detectLastIndex(integer -> integer % 2 != 0));
        Assert.assertEquals(-1, this.newWith(1, 2, 3, 4).detectLastIndex(integer -> integer % 5 == 0));
        Assert.assertEquals(8, this.newWith(1, 1, 2, 2, 3, 3, 3, 4, 2).detectLastIndex(integer -> integer == 2));
        Assert.assertEquals(7, this.newWith(1, 1, 2, 2, 3, 3, 3, 4, 2).detectLastIndex(integer -> integer != 2));
        Assert.assertEquals(-1, this.newWith(1, 1, 2, 2, 3, 3, 3, 4, 2).detectLastIndex(integer -> integer == 5));
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithIndex()
    {
        RichIterable<ObjectIntPair<Integer>> pairs =
                this.newWith(3, 2, 1, 0).collectWithIndex(PrimitiveTuples::pair);
        Assert.assertEquals(
                IntLists.mutable.with(0, 1, 2, 3),
                pairs.collectInt(ObjectIntPair::getTwo, IntLists.mutable.empty()));
        Assert.assertEquals(
                Lists.mutable.with(3, 2, 1, 0),
                pairs.collect(ObjectIntPair::getOne, Lists.mutable.empty()));
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithIndexWithTarget()
    {
        RichIterable<ObjectIntPair<Integer>> pairs =
                this.newWith(3, 2, 1, 0).collectWithIndex(PrimitiveTuples::pair, Lists.mutable.empty());
        Assert.assertEquals(
                IntLists.mutable.with(0, 1, 2, 3),
                pairs.collectInt(ObjectIntPair::getTwo, IntLists.mutable.empty()));
        Assert.assertEquals(
                Lists.mutable.with(3, 2, 1, 0),
                pairs.collect(ObjectIntPair::getOne, Lists.mutable.empty()));

        RichIterable<ObjectIntPair<Integer>> setOfPairs =
                this.newWith(3, 2, 1, 0).collectWithIndex(PrimitiveTuples::pair, Sets.mutable.empty());
        Assert.assertEquals(
                IntSets.mutable.with(0, 1, 2, 3),
                setOfPairs.collectInt(ObjectIntPair::getTwo, IntSets.mutable.empty()));
        Assert.assertEquals(
                Sets.mutable.with(3, 2, 1, 0),
                setOfPairs.collect(ObjectIntPair::getOne, Sets.mutable.empty()));
    }

    @Override
    public void collectBoolean()
    {
        super.collectBoolean();
        MutableBooleanList result = this.newWith(-1, 0, 1, 4).collectBoolean(PrimitiveFunctions.integerIsPositive());
        Assert.assertEquals(BooleanLists.mutable.of(false, false, true, true), result);
    }

    @Override
    public void collectByte()
    {
        super.collectByte();
        MutableByteList result = this.newWith(1, 2, 3, 4).collectByte(PrimitiveFunctions.unboxIntegerToByte());
        Assert.assertEquals(ByteLists.mutable.of((byte) 1, (byte) 2, (byte) 3, (byte) 4), result);
    }

    @Override
    public void collectChar()
    {
        super.collectChar();
        MutableCharList result = this.newWith(1, 2, 3, 4).collectChar(PrimitiveFunctions.unboxIntegerToChar());
        Assert.assertEquals(CharLists.mutable.of((char) 1, (char) 2, (char) 3, (char) 4), result);
    }

    @Override
    public void collectDouble()
    {
        super.collectDouble();
        MutableDoubleList result = this.newWith(1, 2, 3, 4).collectDouble(PrimitiveFunctions.unboxIntegerToDouble());
        Assert.assertEquals(DoubleLists.mutable.of(1.0d, 2.0d, 3.0d, 4.0d), result);
    }

    @Override
    public void collectFloat()
    {
        super.collectFloat();
        MutableFloatList result = this.newWith(1, 2, 3, 4).collectFloat(PrimitiveFunctions.unboxIntegerToFloat());
        Assert.assertEquals(FloatLists.mutable.of(1.0f, 2.0f, 3.0f, 4.0f), result);
    }

    @Override
    public void collectInt()
    {
        super.collectInt();
        MutableIntList result = this.newWith(1, 2, 3, 4).collectInt(PrimitiveFunctions.unboxIntegerToInt());
        Assert.assertEquals(IntLists.mutable.of(1, 2, 3, 4), result);
    }

    @Override
    public void collectLong()
    {
        super.collectLong();
        MutableLongList result = this.newWith(1, 2, 3, 4).collectLong(PrimitiveFunctions.unboxIntegerToLong());
        Assert.assertEquals(LongLists.mutable.of(1L, 2L, 3L, 4L), result);
    }

    @Override
    public void collectShort()
    {
        super.collectShort();
        MutableShortList result = this.newWith(1, 2, 3, 4).collectShort(PrimitiveFunctions.unboxIntegerToShort());
        Assert.assertEquals(ShortLists.mutable.of((short) 1, (short) 2, (short) 3, (short) 4), result);
    }

    @Override
    @Test
    public void asSynchronized()
    {
        Verify.assertInstanceOf(SynchronizedMutableList.class, this.newWith().asSynchronized());
    }

    @Override
    @Test
    public void toImmutable()
    {
        super.toImmutable();
        Verify.assertInstanceOf(ImmutableList.class, this.newWith().toImmutable());
        Assert.assertSame(this.newWith().toImmutable(), this.newWith().toImmutable());
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        Verify.assertInstanceOf(UnmodifiableMutableList.class, this.newWith().asUnmodifiable());
    }

    @Test
    public void testClone()
    {
        MutableList<Integer> list = this.newWith(1, 2, 3);
        MutableList<Integer> list2 = list.clone();
        Verify.assertListsEqual(list, list2);
        try
        {
            Verify.assertShallowClone(list);
        }
        catch (Exception e)
        {
            // Suppress if a Java 9 specific exception related to reflection is thrown.
            if (!e.getClass().getCanonicalName().equals("java.lang.reflect.InaccessibleObjectException"))
            {
                throw e;
            }
        }
    }

    @Override
    @Test
    public void equalsAndHashCode()
    {
        MutableCollection<Integer> list1 = this.newWith(1, 2, 3);
        MutableCollection<Integer> list2 = this.newWith(1, 2, 3);
        MutableCollection<Integer> list3 = this.newWith(2, 3, 4);
        MutableCollection<Integer> list4 = this.newWith(1, 2, 3, 4);
        Assert.assertNotEquals(list1, null);
        Verify.assertEqualsAndHashCode(list1, list1);
        Verify.assertEqualsAndHashCode(list1, list2);
        Verify.assertEqualsAndHashCode(new LinkedList<>(Arrays.asList(1, 2, 3)), list1);
        Verify.assertEqualsAndHashCode(new ArrayList<>(Arrays.asList(1, 2, 3)), list1);
        Verify.assertEqualsAndHashCode(ArrayAdapter.newArrayWith(1, 2, 3), list1);
        Assert.assertNotEquals(list2, list3);
        Assert.assertNotEquals(list2, list4);
        Assert.assertNotEquals(new LinkedList<>(Arrays.asList(1, 2, 3)), list4);
        Assert.assertNotEquals(new LinkedList<>(Arrays.asList(1, 2, 3, 3)), list4);
        Assert.assertNotEquals(new ArrayList<>(Arrays.asList(1, 2, 3)), list4);
        Assert.assertNotEquals(new ArrayList<>(Arrays.asList(1, 2, 3, 3)), list4);
        Assert.assertNotEquals(list4, new LinkedList<>(Arrays.asList(1, 2, 3)));
        Assert.assertNotEquals(list4, new LinkedList<>(Arrays.asList(1, 2, 3, 3)));
        Assert.assertNotEquals(list4, new ArrayList<>(Arrays.asList(1, 2, 3)));
        Assert.assertNotEquals(list4, new ArrayList<>(Arrays.asList(1, 2, 3, 3)));
        Assert.assertNotEquals(new LinkedList<>(Arrays.asList(1, 2, 3, 4)), list1);
        Assert.assertNotEquals(new LinkedList<>(Arrays.asList(1, 2, null)), list1);
        Assert.assertNotEquals(new LinkedList<>(Arrays.asList(1, 2)), list1);
        Assert.assertNotEquals(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), list1);
        Assert.assertNotEquals(new ArrayList<>(Arrays.asList(1, 2, null)), list1);
        Assert.assertNotEquals(new ArrayList<>(Arrays.asList(1, 2)), list1);
        Assert.assertNotEquals(ArrayAdapter.newArrayWith(1, 2, 3, 4), list1);
    }

    @Test
    public void newListWithSize()
    {
        MutableList<Integer> list = this.newWith(1, 2, 3);
        Verify.assertContainsAll(list, 1, 2, 3);
    }

    @Test
    public void serialization()
    {
        MutableList<Integer> collection = this.newWith(1, 2, 3, 4, 5);
        MutableList<Integer> deserializedCollection = SerializeTestHelper.serializeDeserialize(collection);
        Verify.assertSize(5, deserializedCollection);
        Verify.assertContainsAll(deserializedCollection, 1, 2, 3, 4, 5);
        Assert.assertEquals(collection, deserializedCollection);
    }

    @Test
    public void corresponds()
    {
        MutableList<Integer> integers1 = this.newWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        MutableList<Integer> integers2 = this.newWith(1, 2, 3, 4);
        Assert.assertFalse(integers1.corresponds(integers2, Predicates2.alwaysTrue()));
        Assert.assertFalse(integers2.corresponds(integers1, Predicates2.alwaysTrue()));

        MutableList<Integer> integers3 = this.newWith(2, 3, 3, 4, 4, 4, 5, 5, 5, 5);
        Assert.assertTrue(integers1.corresponds(integers3, Predicates2.lessThan()));
        Assert.assertFalse(integers1.corresponds(integers3, Predicates2.greaterThan()));

        MutableList<Integer> nonRandomAccess = ListAdapter.adapt(new LinkedList<>(integers3));
        Assert.assertTrue(integers1.corresponds(nonRandomAccess, Predicates2.lessThan()));
        Assert.assertFalse(integers1.corresponds(nonRandomAccess, Predicates2.greaterThan()));
        Assert.assertTrue(nonRandomAccess.corresponds(integers1, Predicates2.greaterThan()));
        Assert.assertFalse(nonRandomAccess.corresponds(integers1, Predicates2.lessThan()));

        MutableList<String> nullBlanks = this.newWith(null, "", " ", null);
        Assert.assertTrue(nullBlanks.corresponds(FastList.newListWith(null, "", " ", null), Comparators::nullSafeEquals));
        Assert.assertFalse(nullBlanks.corresponds(FastList.newListWith("", null, " ", ""), Comparators::nullSafeEquals));
    }

    @Test
    public void forEachFromTo()
    {
        MutableList<Integer> result = FastList.newList();
        MutableList<Integer> collection = FastList.newListWith(1, 2, 3, 4);
        collection.forEach(2, 3, result::add);
        Assert.assertEquals(this.newWith(3, 4), result);

        MutableList<Integer> result2 = FastList.newList();
        collection.forEach(3, 2, CollectionAddProcedure.on(result2));
        Assert.assertEquals(this.newWith(4, 3), result2);

        MutableList<Integer> result3 = FastList.newList();
        collection.forEach(0, 3, CollectionAddProcedure.on(result3));
        Assert.assertEquals(this.newWith(1, 2, 3, 4), result3);

        MutableList<Integer> result4 = FastList.newList();
        collection.forEach(3, 0, CollectionAddProcedure.on(result4));
        Assert.assertEquals(this.newWith(4, 3, 2, 1), result4);

        Verify.assertThrows(IndexOutOfBoundsException.class, () -> collection.forEach(-1, 0, result::add));
        Verify.assertThrows(IndexOutOfBoundsException.class, () -> collection.forEach(0, -1, result::add));
    }

    @Test
    public void forEachFromToInReverse()
    {
        MutableList<Integer> result = Lists.mutable.empty();
        this.newWith(1, 2, 3, 4).forEach(3, 2, result::add);
        Assert.assertEquals(FastList.newListWith(4, 3), result);
    }

    @Test
    public void reverseForEach()
    {
        MutableList<Integer> result = Lists.mutable.empty();
        MutableList<Integer> collection = this.newWith(1, 2, 3, 4);
        collection.reverseForEach(result::add);
        Assert.assertEquals(FastList.newListWith(4, 3, 2, 1), result);
    }

    @Test
    public void reverseForEach_emptyList()
    {
        MutableList<Integer> integers = Lists.mutable.empty();
        MutableList<Integer> results = Lists.mutable.empty();
        integers.reverseForEach(results::add);
        Assert.assertEquals(integers, results);
    }

    @Test
    public void reverseForEachWithIndex()
    {
        MutableList<Integer> result = Lists.mutable.empty();
        MutableList<Integer> collection = this.newWith(1, 2, 3, 4);
        collection.reverseForEachWithIndex((each, index) -> result.add(each + index));
        Assert.assertEquals(FastList.newListWith(7, 5, 3, 1), result);
    }

    @Test
    public void reverseForEachWithIndex_emptyList()
    {
        MutableList<Integer> list = Lists.mutable.empty();
        list.reverseForEachWithIndex((each, index) -> fail());
    }

    @Test
    public void reverseThis()
    {
        MutableList<Integer> original = this.newWith(1, 2, 3, 4);
        MutableList<Integer> reversed = original.reverseThis();
        Assert.assertEquals(FastList.newListWith(4, 3, 2, 1), reversed);
        Assert.assertSame(original, reversed);
    }

    @Test
    public void toReversed()
    {
        MutableList<Integer> original = this.newWith(1, 2, 3, 4);
        MutableList<Integer> actual = original.toReversed();
        MutableList<Integer> expected = this.newWith(4, 3, 2, 1);
        Assert.assertEquals(expected, actual);
        Assert.assertNotSame(original, actual);
    }

    @Test
    public void distinct()
    {
        ListIterable<Integer> list = this.newWith(1, 4, 3, 2, 1, 4, 1);
        ListIterable<Integer> actual = list.distinct();
        Assert.assertEquals(Lists.mutable.with(1, 4, 3, 2), actual);
    }

    @Test
    public void distinctWithHashingStrategy()
    {
        ListIterable<String> list = this.newWith("a", "A", "b", "C", "b", "D", "E", "e");
        ListIterable<String> actual = list.distinct(HashingStrategies.fromFunction(String::toLowerCase));
        Assert.assertEquals(Lists.mutable.with("a", "b", "C", "D", "E"), actual);
    }

    /**
     * @since 9.0.
     */
    @Test
    public void distinctBy()
    {
        ListIterable<String> list = this.newWith("a", "A", "b", "C", "b", "D", "E", "e");
        ListIterable<String> actual = list.distinctBy(String::toLowerCase);
        Assert.assertEquals(Lists.mutable.with("a", "b", "C", "D", "E"), actual);
    }

    @Override
    @Test
    public void removeIf()
    {
        MutableCollection<Integer> objects = this.newWith(1, 2, 3, null);
        objects.removeIf(Predicates.isNull());
        Assert.assertEquals(FastList.newListWith(1, 2, 3), objects);
    }

    @Test
    public void removeIndex()
    {
        MutableList<Integer> objects = this.newWith(1, 2, 3);
        objects.remove(2);
        Assert.assertEquals(FastList.newListWith(1, 2), objects);
    }

    @Test
    public void indexOf()
    {
        MutableList<Integer> objects = this.newWith(1, 2, 2);
        Assert.assertEquals(1, objects.indexOf(2));
        Assert.assertEquals(0, objects.indexOf(1));
        Assert.assertEquals(-1, objects.indexOf(3));
    }

    @Test
    public void lastIndexOf()
    {
        MutableList<Integer> objects = this.newWith(2, 2, 3);
        Assert.assertEquals(1, objects.lastIndexOf(2));
        Assert.assertEquals(2, objects.lastIndexOf(3));
        Assert.assertEquals(-1, objects.lastIndexOf(1));
    }

    @Test
    public void set()
    {
        MutableList<Integer> objects = this.newWith(1, 2, 3);
        Assert.assertEquals(Integer.valueOf(2), objects.set(1, 4));
        Assert.assertEquals(FastList.newListWith(1, 4, 3), objects);
    }

    @Test
    public void addAtIndex()
    {
        MutableList<Integer> objects = this.newWith(1, 2, 3);
        objects.add(0, 0);
        Assert.assertEquals(FastList.newListWith(0, 1, 2, 3), objects);
    }

    @Test
    public void addAllAtIndex()
    {
        MutableList<Integer> objects = this.newWith(1, 2, 3);
        objects.addAll(0, Lists.fixedSize.of(0));
        Integer one = -1;
        objects.addAll(0, new ArrayList<>(Lists.fixedSize.of(one)));
        objects.addAll(0, FastList.newListWith(-2));
        objects.addAll(0, UnifiedSet.newSetWith(-3));
        Assert.assertEquals(FastList.newListWith(-3, -2, -1, 0, 1, 2, 3), objects);
    }

    @Test
    public void withMethods()
    {
        Verify.assertContainsAll(this.newWith().with(1), 1);
        Verify.assertContainsAll(this.newWith(1), 1);
        Verify.assertContainsAll(this.newWith(1).with(2), 1, 2);
    }

    @Test
    public void sortThis_with_null()
    {
        MutableList<Integer> integers = this.newWith(2, null, 3, 4, 1);
        Verify.assertStartsWith(integers.sortThis(Comparators.safeNullsLow(Integer::compareTo)), null, 1, 2, 3, 4);
    }

    @Test
    public void sortThis_small()
    {
        MutableList<Integer> actual = this.newWith(1, 2, 3).shuffleThis();
        MutableList<Integer> sorted = actual.sortThis();
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith(1, 2, 3), actual);
    }

    @Test
    public void sortThis()
    {
        MutableList<Integer> actual = this.newWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).shuffleThis();
        MutableList<Integer> sorted = actual.sortThis();
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), actual);
    }

    @Test
    public void sortThis_large()
    {
        MutableList<Integer> actual = this.newWith(Interval.oneTo(1000).toArray()).shuffleThis();
        MutableList<Integer> sorted = actual.sortThis();
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(Interval.oneTo(1000).toList(), actual);
    }

    @Test
    public void sortThis_with_comparator_small()
    {
        MutableList<Integer> actual = this.newWith(1, 2, 3).shuffleThis();
        MutableList<Integer> sorted = actual.sortThis(Collections.reverseOrder());
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith(3, 2, 1), actual);
    }

    @Test
    public void sortThis_with_comparator()
    {
        MutableList<Integer> actual = this.newWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).shuffleThis();
        MutableList<Integer> sorted = actual.sortThis(Collections.reverseOrder());
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith(10, 9, 8, 7, 6, 5, 4, 3, 2, 1), actual);
    }

    @Test
    public void sortThis_with_comparator_large()
    {
        MutableList<Integer> actual = this.newWith(Interval.oneTo(1000).toArray()).shuffleThis();
        MutableList<Integer> sorted = actual.sortThis(Collections.reverseOrder());
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(Interval.fromToBy(1000, 1, -1).toList(), actual);
    }

    @Test
    public void sortThisBy()
    {
        MutableList<Integer> actual = this.newWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).shuffleThis();
        MutableList<Integer> sorted = actual.sortThisBy(String::valueOf);
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith(1, 10, 2, 3, 4, 5, 6, 7, 8, 9), actual);
    }

    @Test
    public void sortThisByBoolean()
    {
        MutableList<Integer> actual = this.newWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        MutableList<Integer> sorted = actual.sortThisByBoolean(i -> i % 2 == 0);
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith(1, 3, 5, 7, 9, 2, 4, 6, 8, 10), actual);
    }

    @Test
    public void sortThisByInt()
    {
        MutableList<String> actual = this.newWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10").shuffleThis();
        MutableList<String> sorted = actual.sortThisByInt(Integer::parseInt);
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"), actual);
    }

    @Test
    public void sortThisByChar()
    {
        MutableList<String> actual = this.newWith("1", "2", "3", "4", "5", "6", "7", "8", "9").shuffleThis();
        MutableList<String> sorted = actual.sortThisByChar(s -> s.charAt(0));
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith("1", "2", "3", "4", "5", "6", "7", "8", "9"), actual);
    }

    @Test
    public void sortThisByByte()
    {
        MutableList<String> actual = this.newWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10").shuffleThis();
        MutableList<String> sorted = actual.sortThisByByte(Byte::parseByte);
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"), actual);
    }

    @Test
    public void sortThisByShort()
    {
        MutableList<String> actual = this.newWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10").shuffleThis();
        MutableList<String> sorted = actual.sortThisByShort(Short::parseShort);
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"), actual);
    }

    @Test
    public void sortThisByFloat()
    {
        MutableList<String> actual = this.newWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10").shuffleThis();
        MutableList<String> sorted = actual.sortThisByFloat(Float::parseFloat);
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"), actual);
    }

    @Test
    public void sortThisByLong()
    {
        MutableList<String> actual = this.newWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10").shuffleThis();
        MutableList<String> sorted = actual.sortThisByLong(Long::parseLong);
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"), actual);
    }

    @Test
    public void sortThisByDouble()
    {
        MutableList<String> actual = this.newWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10").shuffleThis();
        MutableList<String> sorted = actual.sortThisByDouble(Double::parseDouble);
        Assert.assertSame(actual, sorted);
        Assert.assertEquals(FastList.newListWith("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"), actual);
    }

    @Override
    @Test
    public void newEmpty()
    {
        Verify.assertInstanceOf(MutableList.class, this.newWith().newEmpty());
    }

    @Override
    @Test
    public void testToString()
    {
        MutableList<Object> list = this.newWith(1, 2, 3);
        list.add(list);
        Assert.assertEquals("[1, 2, 3, (this " + list.getClass().getSimpleName() + ")]", list.toString());
    }

    @Override
    @Test
    public void makeString()
    {
        MutableList<Object> list = this.newWith(1, 2, 3);
        list.add(list);
        Assert.assertEquals("1, 2, 3, (this " + list.getClass().getSimpleName() + ')', list.makeString());
    }

    @Override
    @Test
    public void makeStringWithSeparator()
    {
        MutableList<Object> list = this.newWith(1, 2, 3);
        Assert.assertEquals("1/2/3", list.makeString("/"));
    }

    @Override
    @Test
    public void makeStringWithSeparatorAndStartAndEnd()
    {
        MutableList<Object> list = this.newWith(1, 2, 3);
        Assert.assertEquals("[1/2/3]", list.makeString("[", "/", "]"));
    }

    @Override
    @Test
    public void appendString()
    {
        MutableList<Object> list = this.newWith(1, 2, 3);
        list.add(list);

        Appendable builder = new StringBuilder();
        list.appendString(builder);
        Assert.assertEquals("1, 2, 3, (this " + list.getClass().getSimpleName() + ')', builder.toString());
    }

    @Override
    @Test
    public void appendStringWithSeparator()
    {
        MutableList<Object> list = this.newWith(1, 2, 3);

        Appendable builder = new StringBuilder();
        list.appendString(builder, "/");
        Assert.assertEquals("1/2/3", builder.toString());
    }

    @Override
    @Test
    public void appendStringWithSeparatorAndStartAndEnd()
    {
        MutableList<Object> list = this.newWith(1, 2, 3);

        Appendable builder = new StringBuilder();
        list.appendString(builder, "[", "/", "]");
        Assert.assertEquals("[1/2/3]", builder.toString());
    }

    @Test
    public void forEachWithIndexWithFromTo()
    {
        MutableList<Integer> integers = this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
        StringBuilder builder = new StringBuilder();
        integers.forEachWithIndex(5, 7, (each, index) -> builder.append(each).append(index));
        Assert.assertEquals("353627", builder.toString());

        StringBuilder builder2 = new StringBuilder();
        integers.forEachWithIndex(5, 5, (each, index) -> builder2.append(each).append(index));
        Assert.assertEquals("35", builder2.toString());

        StringBuilder builder3 = new StringBuilder();
        integers.forEachWithIndex(0, 9, (each, index) -> builder3.append(each).append(index));
        Assert.assertEquals("40414243343536272819", builder3.toString());

        StringBuilder builder4 = new StringBuilder();
        integers.forEachWithIndex(7, 5, (each, index) -> builder4.append(each).append(index));
        Assert.assertEquals("273635", builder4.toString());

        StringBuilder builder5 = new StringBuilder();
        integers.forEachWithIndex(9, 0, (each, index) -> builder5.append(each).append(index));
        Assert.assertEquals("19282736353443424140", builder5.toString());

        MutableList<Integer> result = Lists.mutable.empty();
        Verify.assertThrows(IndexOutOfBoundsException.class, () -> integers.forEachWithIndex(-1, 0, new AddToList(result)));
        Verify.assertThrows(IndexOutOfBoundsException.class, () -> integers.forEachWithIndex(0, -1, new AddToList(result)));
    }

    @Test
    public void forEachWithIndexWithFromToInReverse()
    {
        MutableList<Integer> result = Lists.mutable.empty();
        this.newWith(1, 2, 3).forEachWithIndex(2, 1, new AddToList(result));
        Assert.assertEquals(FastList.newListWith(3, 2), result);
    }

    @Test(expected = NullPointerException.class)
    public void sortThisWithNullWithNoComparator()
    {
        MutableList<Integer> integers = this.newWith(2, null, 3, 4, 1);
        integers.sortThis();
    }

    @Test(expected = NullPointerException.class)
    public void sortThisWithNullWithNoComparatorOnListWithMoreThan10Elements()
    {
        MutableList<Integer> integers = this.newWith(2, null, 3, 4, 1, 5, 6, 7, 8, 9, 10, 11);
        integers.sortThis();
    }

    @Test(expected = NullPointerException.class)
    public void toSortedListWithNullWithNoComparator()
    {
        MutableList<Integer> integers = this.newWith(2, null, 3, 4, 1);
        integers.toSortedList();
    }

    @Test(expected = NullPointerException.class)
    public void toSortedListWithNullWithNoComparatorOnListWithMoreThan10Elements()
    {
        MutableList<Integer> integers = this.newWith(2, null, 3, 4, 1, 5, 6, 7, 8, 9, 10, 11);
        integers.toSortedList();
    }

    @Test
    public void forEachOnRange()
    {
        MutableList<Integer> list = this.newWith();

        list.addAll(FastList.newListWith(0, 1, 2, 3));
        list.addAll(FastList.newListWith(4, 5, 6));
        list.addAll(FastList.newList());
        list.addAll(FastList.newListWith(7, 8, 9));

        this.validateForEachOnRange(list, 0, 0, FastList.newListWith(0));
        this.validateForEachOnRange(list, 3, 5, FastList.newListWith(3, 4, 5));
        this.validateForEachOnRange(list, 4, 6, FastList.newListWith(4, 5, 6));
        this.validateForEachOnRange(list, 9, 9, FastList.newListWith(9));
        this.validateForEachOnRange(list, 0, 9, FastList.newListWith(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

        Verify.assertThrows(
                IndexOutOfBoundsException.class,
                () -> this.validateForEachOnRange(list, 10, 10, FastList.newList()));
    }

    protected void validateForEachOnRange(MutableList<Integer> list, int from, int to, List<Integer> expectedOutput)
    {
        List<Integer> outputList = Lists.mutable.empty();
        list.forEach(from, to, outputList::add);

        Assert.assertEquals(expectedOutput, outputList);
    }

    @Test
    public void forEachWithIndexOnRange()
    {
        MutableList<Integer> list = this.newWith();

        list.addAll(FastList.newListWith(0, 1, 2, 3));
        list.addAll(FastList.newListWith(4, 5, 6));
        list.addAll(FastList.newList());
        list.addAll(FastList.newListWith(7, 8, 9));

        this.validateForEachWithIndexOnRange(list, 0, 0, FastList.newListWith(0));
        this.validateForEachWithIndexOnRange(list, 3, 5, FastList.newListWith(3, 4, 5));
        this.validateForEachWithIndexOnRange(list, 4, 6, FastList.newListWith(4, 5, 6));
        this.validateForEachWithIndexOnRange(list, 9, 9, FastList.newListWith(9));
        this.validateForEachWithIndexOnRange(list, 0, 9, FastList.newListWith(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Verify.assertThrows(
                IndexOutOfBoundsException.class,
                () -> this.validateForEachWithIndexOnRange(list, 10, 10, FastList.newList()));
    }

    protected void validateForEachWithIndexOnRange(
            MutableList<Integer> list,
            int from,
            int to,
            List<Integer> expectedOutput)
    {
        MutableList<Integer> outputList = Lists.mutable.empty();
        list.forEachWithIndex(from, to, (each, index) -> outputList.add(each));

        Assert.assertEquals(expectedOutput, outputList);
    }

    @Test
    public void subList()
    {
        MutableList<String> list = this.newWith("A", "B", "C", "D");
        MutableList<String> sublist = list.subList(1, 3);
        Verify.assertPostSerializedEqualsAndHashCode(sublist);
        Verify.assertSize(2, sublist);
        Verify.assertContainsAll(sublist, "B", "C");
        sublist.add("X");
        Verify.assertSize(3, sublist);
        Verify.assertContainsAll(sublist, "B", "C", "X");
        Verify.assertSize(5, list);
        Verify.assertContainsAll(list, "A", "B", "C", "X", "D");
        sublist.remove("X");
        Verify.assertContainsAll(sublist, "B", "C");
        Verify.assertContainsAll(list, "A", "B", "C", "D");
        Assert.assertEquals("C", sublist.set(1, "R"));
        Verify.assertContainsAll(sublist, "B", "R");
        Verify.assertContainsAll(list, "A", "B", "R", "D");
        sublist.addAll(Arrays.asList("W", "G"));
        Verify.assertContainsAll(sublist, "B", "R", "W", "G");
        Verify.assertContainsAll(list, "A", "B", "R", "W", "G", "D");
        sublist.clear();
        Verify.assertEmpty(sublist);
        Assert.assertFalse(sublist.remove("X"));
        Verify.assertEmpty(sublist);
        Verify.assertContainsAll(list, "A", "D");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subListFromOutOfBoundsException()
    {
        this.newWith(1).subList(-1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subListToGreaterThanSizeException()
    {
        this.newWith(1).subList(0, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void subListFromGreaterThanToException()
    {
        this.newWith(1).subList(1, 0);
    }

    @Test
    public void getWithIndexOutOfBoundsException()
    {
        Object item = new Object();

        Verify.assertThrows(IndexOutOfBoundsException.class, () -> this.newWith(item).get(1));
    }

    @Test
    public void getWithArrayIndexOutOfBoundsException()
    {
        Object item = new Object();

        try
        {
            this.newWith(item).get(-1);
            fail("Should not reach here! Exception should be thrown on previous line.");
        }
        catch (Exception e)
        {
            Assert.assertTrue((e instanceof ArrayIndexOutOfBoundsException) || (e instanceof IndexOutOfBoundsException));
        }
    }

    @Test
    public void listIterator()
    {
        int sum = 0;
        MutableList<Integer> integers = this.newWith(1, 2, 3, 4);
        for (Iterator<Integer> iterator = integers.listIterator(); iterator.hasNext(); )
        {
            Integer each = iterator.next();
            sum += each.intValue();
        }
        for (ListIterator<Integer> iterator = integers.listIterator(4); iterator.hasPrevious(); )
        {
            Integer each = iterator.previous();
            sum += each.intValue();
        }
        Assert.assertEquals(20, sum);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorIndexTooSmall()
    {
        this.newWith(1).listIterator(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorIndexTooBig()
    {
        this.newWith(1).listIterator(2);
    }

    @Override
    @Test
    public void chunk()
    {
        super.chunk();

        MutableCollection<String> collection = this.newWith("1", "2", "3", "4", "5", "6", "7");
        RichIterable<RichIterable<String>> groups = collection.chunk(2);
        Assert.assertEquals(
                FastList.<RichIterable<String>>newListWith(
                        FastList.newListWith("1", "2"),
                        FastList.newListWith("3", "4"),
                        FastList.newListWith("5", "6"),
                        FastList.newListWith("7")),
                groups);
    }

    @Test
    public void toStack()
    {
        MutableStack<Integer> stack = this.newWith(1, 2, 3, 4).toStack();
        Assert.assertEquals(Stacks.mutable.of(1, 2, 3, 4), stack);
    }

    @Test
    public void take()
    {
        MutableList<Integer> mutableList = this.newWith(1, 2, 3, 4, 5);
        Assert.assertEquals(iList(), mutableList.take(0));
        Assert.assertEquals(iList(1, 2, 3), mutableList.take(3));
        Assert.assertEquals(iList(1, 2, 3, 4), mutableList.take(mutableList.size() - 1));

        ImmutableList<Integer> expectedList = iList(1, 2, 3, 4, 5);
        Assert.assertEquals(expectedList, mutableList.take(mutableList.size()));
        Assert.assertEquals(expectedList, mutableList.take(10));
        Assert.assertEquals(expectedList, mutableList.take(Integer.MAX_VALUE));
        Assert.assertNotSame(mutableList, mutableList.take(Integer.MAX_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void take_throws()
    {
        this.newWith(1, 2, 3, 4, 5).take(-1);
    }

    @Test
    public void takeWhile()
    {
        Assert.assertEquals(
                iList(1, 2, 3),
                this.newWith(1, 2, 3, 4, 5).takeWhile(Predicates.lessThan(4)));

        Assert.assertEquals(
                iList(1, 2, 3, 4, 5),
                this.newWith(1, 2, 3, 4, 5).takeWhile(Predicates.lessThan(10)));

        Assert.assertEquals(
                iList(),
                this.newWith(1, 2, 3, 4, 5).takeWhile(Predicates.lessThan(0)));
    }

    @Test
    public void drop()
    {
        MutableList<Integer> mutableList = this.newWith(1, 2, 3, 4, 5);
        Assert.assertEquals(iList(1, 2, 3, 4, 5), mutableList.drop(0));
        Assert.assertNotSame(mutableList, mutableList.drop(0));
        Assert.assertEquals(iList(4, 5), mutableList.drop(3));
        Assert.assertEquals(iList(5), mutableList.drop(mutableList.size() - 1));
        Assert.assertEquals(iList(), mutableList.drop(mutableList.size()));
        Assert.assertEquals(iList(), mutableList.drop(10));
        Assert.assertEquals(iList(), mutableList.drop(Integer.MAX_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void drop_throws()
    {
        this.newWith(1, 2, 3, 4, 5).drop(-1);
    }

    @Test
    public void dropWhile()
    {
        Assert.assertEquals(
                iList(4, 5),
                this.newWith(1, 2, 3, 4, 5).dropWhile(Predicates.lessThan(4)));

        Assert.assertEquals(
                iList(),
                this.newWith(1, 2, 3, 4, 5).dropWhile(Predicates.lessThan(10)));

        Assert.assertEquals(
                iList(1, 2, 3, 4, 5),
                this.newWith(1, 2, 3, 4, 5).dropWhile(Predicates.lessThan(0)));
    }

    @Test
    public void partitionWhile()
    {
        PartitionMutableList<Integer> partition1 = this.newWith(1, 2, 3, 4, 5).partitionWhile(Predicates.lessThan(4));
        Assert.assertEquals(iList(1, 2, 3), partition1.getSelected());
        Assert.assertEquals(iList(4, 5), partition1.getRejected());

        PartitionMutableList<Integer> partition2 = this.newWith(1, 2, 3, 4, 5).partitionWhile(Predicates.lessThan(0));
        Assert.assertEquals(iList(), partition2.getSelected());
        Assert.assertEquals(iList(1, 2, 3, 4, 5), partition2.getRejected());

        PartitionMutableList<Integer> partition3 = this.newWith(1, 2, 3, 4, 5).partitionWhile(Predicates.lessThan(10));
        Assert.assertEquals(iList(1, 2, 3, 4, 5), partition3.getSelected());
        Assert.assertEquals(iList(), partition3.getRejected());
    }

    @Test
    public void asReversed()
    {
        Verify.assertInstanceOf(ReverseIterable.class, this.newWith().asReversed());

        Verify.assertIterablesEqual(iList(4, 3, 2, 1), this.newWith(1, 2, 3, 4).asReversed());
    }

    @Test
    public void binarySearch()
    {
        MutableList<Integer> sortedList = this.newWith(1, 2, 3, 4, 5, 7);
        Assert.assertEquals(1, sortedList.binarySearch(2));
        Assert.assertEquals(-6, sortedList.binarySearch(6));
        for (Integer integer : sortedList)
        {
            Assert.assertEquals(
                    Collections.binarySearch(sortedList, integer),
                    sortedList.binarySearch(integer));
        }
    }

    @Test
    public void binarySearchWithComparator()
    {
        MutableList<Integer> sortedList = this.newWith(7, 5, 4, 3, 2, 1);
        Assert.assertEquals(4, sortedList.binarySearch(2, Comparators.reverseNaturalOrder()));
        Assert.assertEquals(-2, sortedList.binarySearch(6, Comparators.reverseNaturalOrder()));
        for (Integer integer : sortedList)
        {
            Assert.assertEquals(
                    Collections.binarySearch(sortedList, integer, Comparators.reverseNaturalOrder()),
                    sortedList.binarySearch(integer, Comparators.reverseNaturalOrder()));
        }
    }

    @Override
    public void forEachWithIndex()
    {
        super.forEachWithIndex();

        MutableList<Integer> elements = FastList.newList();
        IntArrayList indexes = new IntArrayList();
        MutableList<Integer> collection = this.newWith(1, 2, 3, 4);
        collection.forEachWithIndex((Integer object, int index) ->
        {
            elements.add(object);
            indexes.add(index);
        });
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4), elements);
        Assert.assertEquals(IntArrayList.newListWith(0, 1, 2, 3), indexes);
    }
}
