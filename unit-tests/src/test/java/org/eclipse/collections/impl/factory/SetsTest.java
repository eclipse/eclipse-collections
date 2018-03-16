/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.factory.set.FixedSizeSetFactory;
import org.eclipse.collections.api.factory.set.ImmutableSetFactory;
import org.eclipse.collections.api.factory.set.MutableSetFactory;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.FixedSizeSet;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.MultiReaderUnifiedSet;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.test.domain.Key;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.mSet;

public class SetsTest
{
    private final MutableList<UnifiedSet<String>> uniqueSets =
            Lists.mutable.with(this.newUnsortedSet("Tom", "Dick", "Harry", null),
                    this.newUnsortedSet("Jane", "Sarah", "Mary"),
                    this.newUnsortedSet("Fido", "Spike", "Spuds"));

    private final MutableList<UnifiedSet<String>> overlappingSets =
            Lists.mutable.with(this.newUnsortedSet("Tom", "Dick", "Harry"),
                    this.newUnsortedSet("Larry", "Tom", "Dick"),
                    this.newUnsortedSet("Dick", "Larry", "Paul", null));

    private final MutableList<UnifiedSet<String>> identicalSets =
            Lists.mutable.with(this.newUnsortedSet("Tom", null, "Dick", "Harry"),
                    this.newUnsortedSet(null, "Harry", "Tom", "Dick"),
                    this.newUnsortedSet("Dick", "Harry", "Tom", null));

    private final MutableList<TreeSet<String>> uniqueSortedSets =
            Lists.mutable.with(this.newSortedSet("Tom", "Dick", "Harry"),
                    this.newSortedSet("Jane", "Sarah", "Mary"),
                    this.newSortedSet("Fido", "Spike", "Spuds"));

    private final MutableList<TreeSet<String>> overlappingSortedSets =
            Lists.mutable.with(this.newSortedSet("Tom", "Dick", "Harry"),
                    this.newSortedSet("Larry", "Tom", "Dick"),
                    this.newSortedSet("Dick", "Larry", "Paul"));

    private final MutableList<TreeSet<String>> identicalSortedSets =
            Lists.mutable.with(this.newSortedSet("Tom", "Dick", "Harry"),
                    this.newSortedSet("Harry", "Tom", "Dick"),
                    this.newSortedSet("Dick", "Harry", "Tom"));

    private final MutableList<TreeSet<String>> uniqueReverseSortedSets =
            Lists.mutable.with(this.newReverseSortedSet("Tom", "Dick", "Harry"),
                    this.newReverseSortedSet("Jane", "Sarah", "Mary"),
                    this.newReverseSortedSet("Fido", "Spike", "Spuds"));

    private final MutableList<TreeSet<String>> overlappingReverseSortedSets =
            Lists.mutable.with(this.newReverseSortedSet("Tom", "Dick", "Harry"),
                    this.newReverseSortedSet("Larry", "Tom", "Dick"),
                    this.newReverseSortedSet("Dick", "Larry", "Paul"));

    private final MutableList<TreeSet<String>> identicalReverseSortedSets =
            Lists.mutable.with(this.newReverseSortedSet("Tom", "Dick", "Harry"),
                    this.newReverseSortedSet("Harry", "Tom", "Dick"),
                    this.newReverseSortedSet("Dick", "Harry", "Tom"));

    private <E> UnifiedSet<E> newUnsortedSet(E... elements)
    {
        return UnifiedSet.newSetWith(elements);
    }

    private <E> TreeSet<E> newSortedSet(E... elements)
    {
        return new TreeSet<>(UnifiedSet.newSetWith(elements));
    }

    private <E> TreeSet<E> newReverseSortedSet(E... elements)
    {
        TreeSet<E> set = new TreeSet<>(Collections.reverseOrder());
        set.addAll(UnifiedSet.newSetWith(elements));
        return set;
    }

    @Test
    public void unionUnique()
    {
        this.assertUnionProperties(
                this.containsExactlyProcedure(),
                Verify::assertSetsEqual,
                this.uniqueSets.get(0),
                this.uniqueSets.get(1),
                this.uniqueSets.get(2),
                "Tom", "Dick", "Harry", "Jane", "Sarah", "Mary", "Fido", "Spike", "Spuds", null);
    }

    @Test
    public void unionUniqueSorted()
    {
        this.assertUnionProperties(this.containsExactlyInOrderProcedure(),
                this::assertSetsEqualAndSorted,
                this.uniqueSortedSets.get(0),
                this.uniqueSortedSets.get(1),
                this.uniqueSortedSets.get(2),
                "Dick", "Fido", "Harry", "Jane", "Mary", "Sarah", "Spike", "Spuds", "Tom");

        //TODO: union operations on sorted sets will not pass identity test until SortedSetAdapter is implemented
//        this.assertUnionProperties(this.<String>containsExactlyInOrderBlock(),
//                                   this.<String>setsEqualAndSortedBlock(),
//                                   this.uniqueReverseSortedSets.get(0),
//                                   this.uniqueReverseSortedSets.get(1),
//                                   this.uniqueReverseSortedSets.get(2),
//                                   "Tom", "Spuds", "Spike", "Sarah", "Mary", "Jane", "Harry", "Fido", "Dick");
    }

    @Test
    public void unionOverlapping()
    {
        this.assertUnionProperties(this.containsExactlyProcedure(),
                Verify::assertSetsEqual,
                this.overlappingSets.get(0),
                this.overlappingSets.get(1),
                this.overlappingSets.get(2),
                "Tom", "Dick", "Harry", "Larry", "Paul", null);
    }

    @Test
    public void unionOverlappingSorted()
    {
        this.assertUnionProperties(this.containsExactlyInOrderProcedure(),
                this::assertSetsEqualAndSorted,
                this.overlappingSortedSets.get(0),
                this.overlappingSortedSets.get(1),
                this.overlappingSortedSets.get(2),
                "Dick", "Harry", "Larry", "Paul", "Tom");

        //TODO: union operations on sorted sets will not pass identity test until SortedSetAdapter is implemented
//        this.assertUnionProperties(this.<String>containsExactlyInOrderBlock(),
//                                   this.<String>setsEqualAndSortedBlock(),
//                                   this.overlappingReverseSortedSets.get(0),
//                                   this.overlappingReverseSortedSets.get(1),
//                                   this.overlappingReverseSortedSets.get(2),
//                                   "Tom", "Paul", "Larry", "Harry", "Dick");
    }

    @Test
    public void unionIdentical()
    {
        this.assertUnionProperties(this.containsExactlyProcedure(),
                Verify::assertSetsEqual,
                this.identicalSets.get(0),
                this.identicalSets.get(1),
                this.identicalSets.get(2),
                "Tom", "Dick", "Harry", null);
    }

    @Test
    public void unionIdenticalSorted()
    {
        this.assertUnionProperties(this.containsExactlyInOrderProcedure(),
                this::assertSetsEqualAndSorted,
                this.identicalSortedSets.get(0),
                this.identicalSortedSets.get(1),
                this.identicalSortedSets.get(2),
                "Dick", "Harry", "Tom");

        //TODO: union operations on sorted sets will not pass identity test until SortedSetAdapter is implemented
//        this.assertUnionProperties(this.<String>containsExactlyInOrderBlock(),
//                                   this.<String>setsEqualAndSortedBlock(),
//                                   this.identicalReverseSortedSets.get(0),
//                                   this.identicalReverseSortedSets.get(1),
//                                   this.identicalReverseSortedSets.get(2),
//                                   "Tom", "Harry", "Dick");
    }

    @Test
    public void unionAllUnique()
    {
        MutableSet<String> names = Sets.unionAll(this.uniqueSets.get(0), this.uniqueSets.get(1), this.uniqueSets.get(2));
        Assert.assertEquals(UnifiedSet.newSetWith("Tom", "Dick", "Harry", "Jane", "Sarah", "Mary", "Fido", "Spike", "Spuds", null), names);
    }

    @Test
    public void unionAllOverlapping()
    {
        MutableSet<String> names = Sets.unionAll(this.overlappingSets.get(0), this.overlappingSets.get(1), this.overlappingSets.get(2));
        Assert.assertEquals(UnifiedSet.newSetWith("Tom", "Dick", "Harry", "Larry", "Paul", null), names);
    }

    @Test
    public void unionAllIdentical()
    {
        MutableSet<String> names = Sets.unionAll(this.identicalSets.get(0), this.identicalSets.get(1), this.identicalSets.get(2));
        Assert.assertEquals(UnifiedSet.newSetWith("Tom", "Dick", "Harry", null), names);
    }

    @Test
    public void intersectUnique()
    {
        this.assertIntersectionProperties(this.containsExactlyProcedure(),
                Verify::assertSetsEqual,
                this.uniqueSets.get(0),
                this.uniqueSets.get(1),
                this.uniqueSets.get(2));
    }

    @Test
    public void intersectUniqueSorted()
    {
        this.assertIntersectionProperties(this.containsExactlyInOrderProcedure(),
                this::assertSetsEqualAndSorted,
                this.uniqueSortedSets.get(0),
                this.uniqueSortedSets.get(1),
                this.uniqueSortedSets.get(2));

        this.assertIntersectionProperties(this.containsExactlyInOrderProcedure(),
                this::assertSetsEqualAndSorted,
                this.uniqueReverseSortedSets.get(0),
                this.uniqueReverseSortedSets.get(1),
                this.uniqueReverseSortedSets.get(2));
    }

    @Test
    public void intersectOverlapping()
    {
        this.assertIntersectionProperties(this.containsExactlyProcedure(),
                Verify::assertSetsEqual,
                this.overlappingSets.get(0),
                this.overlappingSets.get(1),
                this.overlappingSets.get(2),
                "Dick");
    }

    @Test
    public void intersectOverlappingSorted()
    {
        this.assertIntersectionProperties(this.containsExactlyInOrderProcedure(),
                this::assertSetsEqualAndSorted,
                this.overlappingSortedSets.get(0),
                this.overlappingSortedSets.get(1),
                this.overlappingSortedSets.get(2),
                "Dick");

        this.assertIntersectionProperties(this.containsExactlyInOrderProcedure(),
                this::assertSetsEqualAndSorted,
                this.overlappingReverseSortedSets.get(0),
                this.overlappingReverseSortedSets.get(1),
                this.overlappingReverseSortedSets.get(2),
                "Dick");
    }

    @Test
    public void intersectIdentical()
    {
        this.assertIntersectionProperties(this.containsExactlyProcedure(),
                Verify::assertSetsEqual,
                this.identicalSets.get(0),
                this.identicalSets.get(1),
                this.identicalSets.get(2),
                "Tom", "Dick", "Harry", null);
    }

    @Test
    public void intersectIdenticalSorted()
    {
        this.assertIntersectionProperties(this.containsExactlyInOrderProcedure(),
                this::assertSetsEqualAndSorted,
                this.identicalSortedSets.get(0),
                this.identicalSortedSets.get(1),
                this.identicalSortedSets.get(2),
                "Dick", "Harry", "Tom");

        this.assertIntersectionProperties(this.containsExactlyInOrderProcedure(),
                this::assertSetsEqualAndSorted,
                this.identicalReverseSortedSets.get(0),
                this.identicalReverseSortedSets.get(1),
                this.identicalReverseSortedSets.get(2),
                "Tom", "Harry", "Dick");
    }

    @Test
    public void intersectAllUnique()
    {
        MutableSet<String> names = Sets.intersectAll(this.uniqueSets.get(0), this.uniqueSets.get(1), this.uniqueSets.get(2));
        Assert.assertEquals(UnifiedSet.newSetWith(), names);
    }

    @Test
    public void intersectAllOverlapping()
    {
        MutableSet<String> names = Sets.intersectAll(this.overlappingSets.get(0), this.overlappingSets.get(1), this.overlappingSets.get(2));
        Assert.assertEquals(UnifiedSet.newSetWith("Dick"), names);
    }

    @Test
    public void intersectAllIdentical()
    {
        MutableSet<String> names = Sets.intersectAll(this.identicalSets.get(0), this.identicalSets.get(1), this.identicalSets.get(2));
        Assert.assertEquals(UnifiedSet.newSetWith("Tom", "Dick", "Harry", null), names);
    }

    @Test
    public void differenceUnique()
    {
        this.assertForwardAndBackward(this.containsExactlyProcedure(),
                Sets::difference,
                this.uniqueSets.get(0),
                this.uniqueSets.get(1),
                new String[]{"Tom", "Dick", "Harry", null},
                new String[]{"Jane", "Sarah", "Mary"});
    }

    @Test
    public void differenceUniqueSorted()
    {
        this.assertForwardAndBackward(this.containsExactlyInOrderProcedure(),
                Sets::difference,
                this.uniqueSortedSets.get(0),
                this.uniqueSortedSets.get(1),
                new String[]{"Dick", "Harry", "Tom"},
                new String[]{"Jane", "Mary", "Sarah"});
    }

    @Test
    public void differenceOverlapping()
    {
        this.assertForwardAndBackward(this.containsExactlyProcedure(),
                Sets::difference,
                this.overlappingSets.get(0),
                this.overlappingSets.get(1),
                new String[]{"Harry"},
                new String[]{"Larry"});
    }

    @Test
    public void differenceOverlappingSorted()
    {
        this.assertForwardAndBackward(this.containsExactlyInOrderProcedure(),
                Sets::difference,
                this.overlappingSortedSets.get(0),
                this.overlappingSortedSets.get(1),
                new String[]{"Harry"},
                new String[]{"Larry"});
    }

    @Test
    public void differenceIdentical()
    {
        this.assertForwardAndBackward(this.containsExactlyProcedure(),
                Sets::difference,
                this.identicalSets.get(0),
                this.identicalSets.get(1),
                new String[]{},
                new String[]{});
    }

    @Test
    public void differenceIdenticalSorted()
    {
        this.assertForwardAndBackward(this.containsExactlyInOrderProcedure(),
                Sets::difference,
                this.identicalSortedSets.get(0),
                this.identicalSortedSets.get(1),
                new String[]{},
                new String[]{});
    }

    @Test
    public void differenceAllUnique()
    {
        MutableSet<String> names = Sets.differenceAll(this.uniqueSets.get(0), this.uniqueSets.get(1), this.uniqueSets.get(2));
        Assert.assertEquals(UnifiedSet.newSetWith("Harry", "Tom", "Dick", null), names);
        Verify.assertSetsEqual(
                names,
                Sets.difference(Sets.difference(this.uniqueSets.get(0), this.uniqueSets.get(1)), this.uniqueSets.get(2)));
    }

    @Test
    public void differenceAllOverlapping()
    {
        MutableSet<String> names = Sets.differenceAll(this.overlappingSets.get(0), this.overlappingSets.get(1), this.overlappingSets.get(2));
        Assert.assertEquals(UnifiedSet.newSetWith("Harry"), names);
        Verify.assertSetsEqual(
                names,
                Sets.difference(Sets.difference(this.overlappingSets.get(0), this.overlappingSets.get(1)), this.overlappingSets.get(2)));
    }

    @Test
    public void differenceAllIdentical()
    {
        MutableSet<String> names = Sets.differenceAll(this.identicalSets.get(0), this.identicalSets.get(1), this.identicalSets.get(2));
        Assert.assertEquals(UnifiedSet.newSetWith(), names);
        Verify.assertSetsEqual(
                names,
                Sets.difference(Sets.difference(this.identicalSets.get(0), this.identicalSets.get(1)), this.identicalSets.get(2)));
    }

    @Test
    public void symmetricDifferenceUnique()
    {
        this.assertForwardAndBackward(this.containsExactlyProcedure(),
                Sets::symmetricDifference,
                this.uniqueSets.get(0),
                this.uniqueSets.get(1),
                new String[]{"Tom", "Dick", "Harry", "Jane", "Mary", "Sarah", null},
                new String[]{"Tom", "Dick", "Harry", "Jane", "Mary", "Sarah", null});
    }

    @Test
    public void symmetricDifferenceUniqueSorted()
    {
        this.assertForwardAndBackward(this.containsExactlyInOrderProcedure(),
                Sets::symmetricDifference,
                this.uniqueSortedSets.get(0),
                this.uniqueSortedSets.get(1),
                new String[]{"Dick", "Harry", "Jane", "Mary", "Sarah", "Tom"},
                new String[]{"Dick", "Harry", "Jane", "Mary", "Sarah", "Tom"});
    }

    @Test
    public void symmetricDifferenceOverlapping()
    {
        this.assertForwardAndBackward(this.containsExactlyProcedure(),
                Sets::symmetricDifference,
                this.overlappingSets.get(0),
                this.overlappingSets.get(1),
                new String[]{"Larry", "Harry"},
                new String[]{"Larry", "Harry"});
    }

    @Test
    public void symmetricDifferenceOverlappingSorted()
    {
        this.assertForwardAndBackward(this.containsExactlyInOrderProcedure(),
                Sets::symmetricDifference,
                this.overlappingSortedSets.get(0),
                this.overlappingSortedSets.get(1),
                new String[]{"Harry", "Larry"},
                new String[]{"Harry", "Larry"});
    }

    @Test
    public void symmetricDifferenceIdentical()
    {
        this.assertForwardAndBackward(this.containsExactlyProcedure(),
                Sets::symmetricDifference,
                this.identicalSets.get(0),
                this.identicalSets.get(1),
                new String[]{},
                new String[]{});
    }

    @Test
    public void symmetricDifferenceIdenticalSorted()
    {
        this.assertForwardAndBackward(this.containsExactlyInOrderProcedure(),
                Sets::symmetricDifference,
                this.identicalSortedSets.get(0),
                this.identicalSortedSets.get(1),
                new String[]{},
                new String[]{});
    }

    @Test
    public void subsetEmpty()
    {
        MutableSet<String> emptySet = mSet();
        MutableSet<String> singletonSet = mSet("Bertha");
        Assert.assertTrue(Sets.isSubsetOf(emptySet, singletonSet));
        Assert.assertFalse(Sets.isSubsetOf(singletonSet, emptySet));
    }

    @Test
    public void subsetNotEmpty()
    {
        MutableSet<String> singletonSet = UnifiedSet.newSetWith("Bertha");
        MutableSet<String> doubletonSet = UnifiedSet.newSetWith("Bertha", "Myra");
        Assert.assertTrue(Sets.isSubsetOf(singletonSet, doubletonSet));
        Assert.assertFalse(Sets.isSubsetOf(doubletonSet, singletonSet));
    }

    @Test
    public void subsetEqual()
    {
        MutableSet<String> setA = UnifiedSet.newSetWith("Bertha", null, "Myra");
        MutableSet<String> setB = UnifiedSet.newSetWith("Myra", "Bertha", null);
        Assert.assertTrue(Sets.isSubsetOf(setA, setB));
        Assert.assertTrue(Sets.isSubsetOf(setB, setA));
    }

    @Test
    public void properSubsetEmpty()
    {
        MutableSet<String> emptySet = mSet();
        MutableSet<String> singletonSet = UnifiedSet.newSetWith("Bertha");
        Assert.assertTrue(Sets.isProperSubsetOf(emptySet, singletonSet));
        Assert.assertFalse(Sets.isProperSubsetOf(singletonSet, emptySet));
    }

    @Test
    public void properSubsetNotEmpty()
    {
        MutableSet<String> singletonSet = UnifiedSet.newSetWith("Bertha");
        MutableSet<String> doubletonSet = UnifiedSet.newSetWith("Bertha", "Myra");
        Assert.assertTrue(Sets.isProperSubsetOf(singletonSet, doubletonSet));
        Assert.assertFalse(Sets.isProperSubsetOf(doubletonSet, singletonSet));
    }

    @Test
    public void properSubsetEqual()
    {
        MutableSet<String> setA = UnifiedSet.newSetWith("Bertha", null, "Myra");
        MutableSet<String> setB = UnifiedSet.newSetWith("Myra", "Bertha", null);
        Assert.assertFalse(Sets.isProperSubsetOf(setA, setB));
        Assert.assertFalse(Sets.isProperSubsetOf(setB, setA));
    }

    private <E> void assertUnionProperties(
            Procedure2<Set<E>, E[]> setContainsProcedure,
            Procedure2<Set<E>, Set<E>> setsEqualProcedure,
            Set<E> setA,
            Set<E> setB,
            Set<E> setC,
            E... elements)
    {
        Function2<Set<E>, Set<E>, Set<E>> union = Sets::union;
        Function2<Set<E>, Set<E>, Set<E>> intersect = Sets::intersect;
        this.assertCommutativeProperty(setContainsProcedure, union, setA, setB, setC, elements);
        this.assertAssociativeProperty(setContainsProcedure, union, setA, setB, setC, elements);
        this.assertDistributiveProperty(setsEqualProcedure, union, intersect, setA, setB, setC);
        this.assertIdentityProperty(setContainsProcedure, union, setA, setB, setC, elements);
    }

    private <E> void assertIntersectionProperties(
            Procedure2<Set<E>, E[]> setContainsProcedure,
            Procedure2<Set<E>, Set<E>> setsEqualProcedure,
            Set<E> setA,
            Set<E> setB,
            Set<E> setC,
            E... elements)
    {
        Function2<Set<E>, Set<E>, Set<E>> intersect = Sets::intersect;
        Function2<Set<E>, Set<E>, Set<E>> union = Sets::union;
        this.assertCommutativeProperty(setContainsProcedure, intersect, setA, setB, setC, elements);
        this.assertAssociativeProperty(setContainsProcedure, intersect, setA, setB, setC, elements);
        this.assertDistributiveProperty(setsEqualProcedure, intersect, union, setA, setB, setC);
    }

    private <E> void assertCommutativeProperty(
            Procedure2<Set<E>, E[]> setContainsProcedure,
            Function2<Set<E>, Set<E>, Set<E>> function,
            Set<E> setA,
            Set<E> setB,
            Set<E> setC,
            E... elements)
    {
        Set<E> aXbXc = function.value(function.value(setA, setB), setC);
        Set<E> aXcXb = function.value(function.value(setA, setC), setB);
        Set<E> bXaXc = function.value(function.value(setB, setA), setC);
        Set<E> bXcXa = function.value(function.value(setB, setC), setA);
        Set<E> cXaXb = function.value(function.value(setC, setA), setB);
        Set<E> cXbXa = function.value(function.value(setC, setB), setA);
        this.assertAllContainExactly(setContainsProcedure, Lists.mutable.with(aXbXc, aXcXb, bXaXc, bXcXa, cXaXb, cXbXa), elements);
    }

    private <E> void assertAssociativeProperty(
            Procedure2<Set<E>, E[]> setContainsProcedure,
            Function2<Set<E>, Set<E>, Set<E>> function,
            Set<E> setA,
            Set<E> setB,
            Set<E> setC,
            E... elements)
    {
        setContainsProcedure.value(function.value(function.value(setA, setB), setC), elements);
        setContainsProcedure.value(function.value(setA, function.value(setB, setC)), elements);
    }

    private <E> void assertDistributiveProperty(
            Procedure2<Set<E>, Set<E>> setsEqualProcedure,
            Function2<Set<E>, Set<E>, Set<E>> function1,
            Function2<Set<E>, Set<E>, Set<E>> function2,
            Set<E> setA,
            Set<E> setB,
            Set<E> setC)
    {
        Set<E> set1 = function1.value(setA, function2.value(setB, setC));
        Set<E> set2 = function2.value(function1.value(setA, setB), function1.value(setA, setC));
        //TODO: setsEqual will fail on some sorted sets until SortableSetAdapter is implemented
        //setsEqualProcedure.value(set1, set2);
    }

    private <E> void assertIdentityProperty(
            Procedure2<Set<E>, E[]> setContainsProcedure,
            Function2<Set<E>, Set<E>, Set<E>> function,
            Set<E> setA,
            Set<E> setB,
            Set<E> setC,
            E... elements)
    {
        Set<E> aXbXc = function.value(function.value(setA, setB), setC);
        Set<E> empty = new TreeSet<>();
        setContainsProcedure.value(function.value(aXbXc, empty), elements);
        setContainsProcedure.value(function.value(empty, aXbXc), elements);
    }

    private <E> void assertAllContainExactly(
            Procedure2<Set<E>, E[]> setContainsProcedure,
            Collection<Set<E>> sets,
            E... elements)
    {
        for (Set<E> set : sets)
        {
            setContainsProcedure.value(set, elements);
        }
    }

    private <E> void assertSetsEqualAndSorted(Set<E> setA, Set<E> setB)
    {
        Verify.assertSetsEqual(setA, setB);
        Object[] expectedItems = setB.toArray((E[]) new Object[setB.size()]);
        Assert.assertEquals(Lists.mutable.with(expectedItems), FastList.newList(setA));
    }

    private <E> void assertForwardAndBackward(
            Procedure2<Set<E>, E[]> setContainsProcedure,
            Function2<Set<E>, Set<E>, Set<E>> function,
            Set<E> setA,
            Set<E> setB,
            E[] forwardResults,
            E[] backwardResults)
    {
        Set<E> results1 = function.value(setA, setB);
        setContainsProcedure.value(results1, forwardResults);
        Set<E> results2 = function.value(setB, setA);
        setContainsProcedure.value(results2, backwardResults);
    }

    private <E> Procedure2<Set<E>, E[]> containsExactlyProcedure()
    {
        return (set, elements) -> Assert.assertEquals(UnifiedSet.newSetWith(elements), set);
    }

    private <E> Procedure2<Set<E>, E[]> containsExactlyInOrderProcedure()
    {
        return (set, elements) -> Assert.assertEquals(Lists.mutable.with((Object[]) elements), FastList.newList(set));
    }

    @Test
    public void immutables()
    {
        ImmutableSetFactory setFactory = Sets.immutable;
        Assert.assertEquals(UnifiedSet.newSet(), setFactory.empty());
        Assert.assertEquals(UnifiedSet.newSet(), setFactory.of());
        Assert.assertEquals(UnifiedSet.newSet(), setFactory.with());
        Verify.assertInstanceOf(ImmutableSet.class, setFactory.of());
        Assert.assertEquals(UnifiedSet.newSetWith(1), setFactory.of(1));
        Verify.assertInstanceOf(ImmutableSet.class, setFactory.of(1));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2), setFactory.of(1, 2));
        Verify.assertInstanceOf(ImmutableSet.class, setFactory.of(1, 2));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3), setFactory.of(1, 2, 3));
        Verify.assertInstanceOf(ImmutableSet.class, setFactory.of(1, 2, 3));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), setFactory.of(1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableSet.class, setFactory.of(1, 2, 3, 4));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5), setFactory.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(ImmutableSet.class, setFactory.of(1, 2, 3, 4, 5));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5), setFactory.ofAll(UnifiedSet.newSetWith(1, 2, 3, 4, 5)));
        Verify.assertInstanceOf(ImmutableSet.class, setFactory.ofAll(UnifiedSet.newSetWith(1, 2, 3, 4, 5)));
    }

    @Test
    public void mutables()
    {
        MutableSetFactory setFactory = Sets.mutable;
        Assert.assertEquals(UnifiedSet.newSet(), setFactory.empty());
        Assert.assertEquals(UnifiedSet.newSet(), setFactory.of());
        Assert.assertEquals(UnifiedSet.newSet(), setFactory.with());
        Verify.assertInstanceOf(MutableSet.class, setFactory.of());
        Assert.assertEquals(UnifiedSet.newSetWith(1), setFactory.of(1));
        Verify.assertInstanceOf(MutableSet.class, setFactory.of(1));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2), setFactory.of(1, 2));
        Verify.assertInstanceOf(MutableSet.class, setFactory.of(1, 2));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3), setFactory.of(1, 2, 3));
        Verify.assertInstanceOf(MutableSet.class, setFactory.of(1, 2, 3));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), setFactory.of(1, 2, 3, 4));
        Verify.assertInstanceOf(MutableSet.class, setFactory.of(1, 2, 3, 4));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5), setFactory.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(MutableSet.class, setFactory.of(1, 2, 3, 4, 5));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5), setFactory.ofAll(UnifiedSet.newSetWith(1, 2, 3, 4, 5)));
        Verify.assertInstanceOf(MutableSet.class, setFactory.ofAll(UnifiedSet.newSetWith(1, 2, 3, 4, 5)));
    }

    @Test
    public void fixedSize()
    {
        FixedSizeSetFactory setFactory = Sets.fixedSize;
        Assert.assertEquals(UnifiedSet.newSet(), setFactory.of());
        Verify.assertInstanceOf(FixedSizeSet.class, setFactory.of());
        Assert.assertEquals(UnifiedSet.newSetWith(1), setFactory.of(1));
        Verify.assertInstanceOf(FixedSizeSet.class, setFactory.of(1));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2), setFactory.of(1, 2));
        Verify.assertInstanceOf(FixedSizeSet.class, setFactory.of(1, 2));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3), setFactory.of(1, 2, 3));
        Verify.assertInstanceOf(FixedSizeSet.class, setFactory.of(1, 2, 3));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), setFactory.of(1, 2, 3, 4));
        Verify.assertInstanceOf(FixedSizeSet.class, setFactory.of(1, 2, 3, 4));
    }

    @Test
    public void multiReader()
    {
        MutableSetFactory setFactory = Sets.multiReader;
        Assert.assertEquals(MultiReaderUnifiedSet.newSet(), setFactory.of());
        Verify.assertInstanceOf(MultiReaderUnifiedSet.class, setFactory.of());
        Assert.assertEquals(MultiReaderUnifiedSet.newSet(), setFactory.with());
        Verify.assertInstanceOf(MultiReaderUnifiedSet.class, setFactory.with());
        Assert.assertEquals(MultiReaderUnifiedSet.newSet(), setFactory.ofInitialCapacity(1));
        Verify.assertInstanceOf(MultiReaderUnifiedSet.class, setFactory.ofInitialCapacity(1));
        Verify.assertThrows(IllegalArgumentException.class, () -> setFactory.ofInitialCapacity(-1));
        Assert.assertEquals(MultiReaderUnifiedSet.newSetWith(1), setFactory.of(1));
        Verify.assertInstanceOf(MultiReaderUnifiedSet.class, setFactory.of(1));
        Assert.assertEquals(MultiReaderUnifiedSet.newSetWith(1, 2, 3), setFactory.ofAll(UnifiedSet.newSetWith(1, 2, 3)));
        Verify.assertInstanceOf(MultiReaderUnifiedSet.class, setFactory.ofAll(UnifiedSet.newSetWith(1, 2, 3)));
    }

    @Test
    public void powerSet()
    {
        MutableSet<Integer> set = UnifiedSet.newSetWith(1, 2, 3);
        MutableSet<MutableSet<Integer>> expectedPowerSet = UnifiedSet.newSetWith(
                UnifiedSet.newSet(),
                UnifiedSet.newSetWith(1),
                UnifiedSet.newSetWith(2),
                UnifiedSet.newSetWith(3),
                UnifiedSet.newSetWith(1, 2),
                UnifiedSet.newSetWith(1, 3),
                UnifiedSet.newSetWith(2, 3),
                UnifiedSet.newSetWith(1, 2, 3));
        Assert.assertEquals(expectedPowerSet, Sets.powerSet(set));
    }

    @Test
    public void powerSet_empty()
    {
        Assert.assertEquals(
                UnifiedSet.newSetWith(UnifiedSet.newSet()),
                Sets.powerSet(UnifiedSet.newSet()));
    }

    @Test
    public void cartesianProduct()
    {
        MutableSet<Integer> set1 = Sets.mutable.with(1, 2);
        MutableSet<Integer> set2 = Sets.mutable.with(2, 3, 4);
        MutableBag<Pair<Integer, Integer>> expectedCartesianProduct1 = Bags.mutable.of(
                Tuples.pair(1, 2),
                Tuples.pair(2, 2),
                Tuples.pair(1, 3),
                Tuples.pair(2, 3),
                Tuples.pair(1, 4),
                Tuples.pair(2, 4));
        Assert.assertEquals(expectedCartesianProduct1, Sets.cartesianProduct(set1, set2).toBag());
        MutableBag<Pair<Integer, Integer>> expectedCartesianProduct2 = Bags.mutable.of(
                Tuples.pair(2, 1),
                Tuples.pair(3, 1),
                Tuples.pair(4, 1),
                Tuples.pair(2, 2),
                Tuples.pair(3, 2),
                Tuples.pair(4, 2));
        Assert.assertEquals(expectedCartesianProduct2, Sets.cartesianProduct(set2, set1).toBag());
    }

    @Test
    public void cartesianProductSameElements()
    {
        MutableSet<Integer> set1 = Sets.mutable.with(1, 2);
        MutableSet<Integer> set2 = Sets.mutable.with(1, 2);
        MutableBag<Pair<Integer, Integer>> expectedCartesianProduct = Bags.mutable.of(
                Tuples.pair(1, 1),
                Tuples.pair(1, 2),
                Tuples.pair(2, 1),
                Tuples.pair(2, 2)
        );
        Assert.assertEquals(expectedCartesianProduct, Sets.cartesianProduct(set1, set2).toBag());
    }

    @Test
    public void cartesianProductWithFunction()
    {
        MutableSet<Integer> set1 = Sets.mutable.with(1, 2);
        MutableSet<Integer> set2 = Sets.mutable.with(2, 3, 4);
        MutableBag<Pair<Integer, Integer>> expectedCartesianProduct = Bags.mutable.of(
                Tuples.pair(1, 2),
                Tuples.pair(2, 2),
                Tuples.pair(1, 3),
                Tuples.pair(2, 3),
                Tuples.pair(1, 4),
                Tuples.pair(2, 4));
        Assert.assertEquals(expectedCartesianProduct, Sets.cartesianProduct(set1, set2, Tuples::pair).toBag());
        MutableBag<List<Integer>> expectedCartesianProduct2 = Bags.mutable.of(
                Lists.mutable.with(2, 1),
                Lists.mutable.with(3, 1),
                Lists.mutable.with(4, 1),
                Lists.mutable.with(2, 2),
                Lists.mutable.with(3, 2),
                Lists.mutable.with(4, 2));
        Assert.assertEquals(
                expectedCartesianProduct2,
                Sets.cartesianProduct(set2, set1, (one, two) -> Lists.mutable.with(one, two)).toBag());
    }

    @Test
    public void cartesianProduct_empty()
    {
        Assert.assertEquals(
                Bags.mutable.of(),
                HashBag.newBag(Sets.cartesianProduct(
                        Sets.mutable.with(1, 2),
                        UnifiedSet.newSet())));
    }

    @Test
    public void castToSet()
    {
        Set<Object> set = Sets.immutable.of().castToSet();
        Assert.assertNotNull(set);
        Assert.assertSame(Sets.immutable.of(), set);
    }

    @Test
    public void copySet()
    {
        Verify.assertInstanceOf(ImmutableSet.class, Sets.immutable.ofAll(Sets.fixedSize.of()));
        MutableSet<Integer> set = Sets.fixedSize.of(1);
        ImmutableSet<Integer> immutableSet = set.toImmutable();
        Verify.assertInstanceOf(ImmutableSet.class, Sets.immutable.ofAll(set));
        Verify.assertInstanceOf(ImmutableSet.class, Sets.immutable.ofAll(Sets.mutable.with(1, 2, 3, 4, 5)));
        Assert.assertSame(Sets.immutable.ofAll(immutableSet.castToSet()), immutableSet);
    }

    @Test
    public void newSet()
    {
        for (int i = 1; i <= 5; i++)
        {
            Interval interval = Interval.oneTo(i);
            Verify.assertEqualsAndHashCode(UnifiedSet.newSet(interval), Sets.immutable.ofAll(interval));
        }
    }

    @Test
    public void emptySet()
    {
        Assert.assertTrue(Sets.immutable.empty().isEmpty());
        Assert.assertSame(Sets.immutable.empty(), Sets.immutable.empty());
        Verify.assertPostSerializedIdentity(Sets.immutable.empty());
    }

    @Test
    public void newSetWith()
    {
        Assert.assertSame(Sets.immutable.empty(), Sets.immutable.of(Sets.immutable.empty().toArray()));
        Verify.assertSize(1, Sets.immutable.of(1).castToSet());
        Verify.assertSize(1, Sets.immutable.of(1, 1).castToSet());
        Verify.assertSize(1, Sets.immutable.of(1, 1, 1).castToSet());
        Verify.assertSize(1, Sets.immutable.of(1, 1, 1, 1).castToSet());
        Verify.assertSize(1, Sets.immutable.of(1, 1, 1, 1, 1).castToSet());
        Verify.assertSize(2, Sets.immutable.of(1, 1, 1, 1, 2).castToSet());
        Verify.assertSize(2, Sets.immutable.of(2, 1, 1, 1, 1).castToSet());
        Verify.assertSize(2, Sets.immutable.of(1, 2, 1, 1, 1).castToSet());
        Verify.assertSize(2, Sets.immutable.of(1, 1, 2, 1, 1).castToSet());
        Verify.assertSize(2, Sets.immutable.of(1, 1, 1, 2, 1).castToSet());
        Verify.assertSize(2, Sets.immutable.of(1, 1, 1, 2).castToSet());
        Verify.assertSize(2, Sets.immutable.of(2, 1, 1, 1).castToSet());
        Verify.assertSize(2, Sets.immutable.of(1, 2, 1, 1).castToSet());
        Verify.assertSize(2, Sets.immutable.of(1, 1, 2, 1).castToSet());
        Verify.assertSize(2, Sets.immutable.of(1, 1, 2).castToSet());
        Verify.assertSize(2, Sets.immutable.of(2, 1, 1).castToSet());
        Verify.assertSize(2, Sets.immutable.of(1, 2, 1).castToSet());
        Verify.assertSize(2, Sets.immutable.of(1, 2).castToSet());
        Verify.assertSize(3, Sets.immutable.of(1, 2, 3).castToSet());
        Verify.assertSize(3, Sets.immutable.of(1, 2, 3, 1).castToSet());
        Verify.assertSize(3, Sets.immutable.of(2, 1, 3, 1).castToSet());
        Verify.assertSize(3, Sets.immutable.of(2, 3, 1, 1).castToSet());
        Verify.assertSize(3, Sets.immutable.of(2, 1, 1, 3).castToSet());
        Verify.assertSize(3, Sets.immutable.of(1, 1, 2, 3).castToSet());
        Verify.assertSize(4, Sets.immutable.of(1, 2, 3, 4).castToSet());
        Verify.assertSize(4, Sets.immutable.of(1, 2, 3, 4, 1).castToSet());
    }

    @Test
    public void setKeyPreservation()
    {
        Key key = new Key("key");

        Key duplicateKey1 = new Key("key");
        ImmutableSet<Key> set1 = Sets.immutable.of(key, duplicateKey1);
        Verify.assertSize(1, set1);
        Verify.assertContains(key, set1);
        Assert.assertSame(key, set1.getFirst());

        Key duplicateKey2 = new Key("key");
        ImmutableSet<Key> set2 = Sets.immutable.of(key, duplicateKey1, duplicateKey2);
        Verify.assertSize(1, set2);
        Verify.assertContains(key, set2);
        Assert.assertSame(key, set2.getFirst());

        Key duplicateKey3 = new Key("key");
        ImmutableSet<Key> set3 = Sets.immutable.of(key, new Key("not a dupe"), duplicateKey3);
        Verify.assertSize(2, set3);
        Verify.assertContainsAll("immutable set", set3, key, new Key("not a dupe"));
        Assert.assertSame(key, set3.detect(key::equals));

        Key duplicateKey4 = new Key("key");
        ImmutableSet<Key> set4 = Sets.immutable.of(key, new Key("not a dupe"), duplicateKey3, duplicateKey4);
        Verify.assertSize(2, set4);
        Verify.assertContainsAll("immutable set", set4, key, new Key("not a dupe"));
        Assert.assertSame(key, set4.detect(key::equals));

        ImmutableSet<Key> set5 = Sets.immutable.of(key, new Key("not a dupe"), new Key("me neither"), duplicateKey4);
        Verify.assertSize(3, set5);
        Verify.assertContainsAll("immutable set", set5, key, new Key("not a dupe"), new Key("me neither"));
        Assert.assertSame(key, set5.detect(key::equals));

        ImmutableSet<Key> set6 = Sets.immutable.of(key, duplicateKey2, duplicateKey3, duplicateKey4);
        Verify.assertSize(1, set6);
        Verify.assertContains(key, set6);
        Assert.assertSame(key, set6.detect(key::equals));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(Sets.class);
    }

    @Test
    public void ofInitialCapacity()
    {
        MutableSet<String> set1 = Sets.mutable.ofInitialCapacity(0);
        this.assertPresizedSetSizeEquals(0, (UnifiedSet<String>) set1);

        MutableSet<String> set2 = Sets.mutable.ofInitialCapacity(5);
        this.assertPresizedSetSizeEquals(5, (UnifiedSet<String>) set2);

        MutableSet<String> set3 = Sets.mutable.ofInitialCapacity(20);
        this.assertPresizedSetSizeEquals(20, (UnifiedSet<String>) set3);

        MutableSet<String> set4 = Sets.mutable.ofInitialCapacity(60);
        this.assertPresizedSetSizeEquals(60, (UnifiedSet<String>) set4);

        MutableSet<String> set5 = Sets.mutable.ofInitialCapacity(64);
        this.assertPresizedSetSizeEquals(60, (UnifiedSet<String>) set5);

        MutableSet<String> set6 = Sets.mutable.ofInitialCapacity(65);
        this.assertPresizedSetSizeEquals(65, (UnifiedSet<String>) set6);

        Verify.assertThrows(IllegalArgumentException.class, () -> Sets.mutable.ofInitialCapacity(-12));
    }

    @Test
    public void withInitialCapacity()
    {
        MutableSet<String> set1 = Sets.mutable.withInitialCapacity(0);
        this.assertPresizedSetSizeEquals(0, (UnifiedSet<String>) set1);

        MutableSet<String> set2 = Sets.mutable.withInitialCapacity(14);
        this.assertPresizedSetSizeEquals(14, (UnifiedSet<String>) set2);

        MutableSet<String> set3 = Sets.mutable.withInitialCapacity(17);
        this.assertPresizedSetSizeEquals(17, (UnifiedSet<String>) set3);

        MutableSet<String> set4 = Sets.mutable.withInitialCapacity(25);
        this.assertPresizedSetSizeEquals(25, (UnifiedSet<String>) set4);

        MutableSet<String> set5 = Sets.mutable.withInitialCapacity(32);
        this.assertPresizedSetSizeEquals(32, (UnifiedSet<String>) set5);

        Verify.assertThrows(IllegalArgumentException.class, () -> Sets.mutable.withInitialCapacity(-6));
    }

    private void assertPresizedSetSizeEquals(int initialCapacity, UnifiedSet<String> set)
    {
        try
        {
            Field tableField = UnifiedSet.class.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] table = (Object[]) tableField.get(set);

            int size = (int) Math.ceil(initialCapacity / 0.75f);
            int capacity = 1;
            while (capacity < size)
            {
                capacity <<= 1;
            }
            Assert.assertEquals(capacity, table.length);
        }
        catch (SecurityException ignored)
        {
            Assert.fail("Unable to modify the visibility of the field 'table' on UnifiedSet");
        }
        catch (NoSuchFieldException ignored)
        {
            Assert.fail("No field named 'table' in UnifiedSet");
        }
        catch (IllegalAccessException ignored)
        {
            Assert.fail("No access to the field 'table' in UnifiedSet");
        }
    }
}
