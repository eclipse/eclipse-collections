/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.fixed;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.partition.set.PartitionMutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.SynchronizedMutableSet;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.iSet;
import static org.eclipse.collections.impl.factory.Iterables.mSet;

/**
 * JUnit test for {@link SingletonSet}.
 */
public class SingletonSetTest extends AbstractMemoryEfficientMutableSetTestCase
{
    private SingletonSet<String> set;
    private MutableSet<Integer> intSet;

    @Before
    public void setUp()
    {
        this.set = new SingletonSet<>("1");
        this.intSet = Sets.fixedSize.of(1);
    }

    @Override
    protected MutableSet<String> classUnderTest()
    {
        return new SingletonSet<>("1");
    }

    @Override
    protected MutableSet<String> classUnderTestWithNull()
    {
        return new SingletonSet<>(null);
    }

    @Test
    public void nonUniqueWith()
    {
        Twin<String> twin1 = Tuples.twin("1", "1");
        Twin<String> twin2 = Tuples.twin("1", "1");
        SingletonSet<Twin<String>> set = new SingletonSet<>(twin1);
        set.with(twin2);
        Assert.assertSame(set.getFirst(), twin1);
    }

    @Override
    @Test
    public void asSynchronized()
    {
        super.asSynchronized();
        Verify.assertInstanceOf(SynchronizedMutableSet.class, Sets.fixedSize.of("1").asSynchronized());
    }

    @Test
    public void contains()
    {
        this.assertUnchanged();
    }

    @Override
    @Test
    public void equalsAndHashCode()
    {
        super.equalsAndHashCode();
        MutableSet<String> one = Sets.fixedSize.of("1");
        MutableSet<String> oneA = UnifiedSet.newSet();
        oneA.add("1");
        Verify.assertEqualsAndHashCode(one, oneA);
        Verify.assertPostSerializedEqualsAndHashCode(one);
    }

    @Test
    public void remove()
    {
        try
        {
            this.set.remove("1");
            Assert.fail("Should not allow remove from SingletonSet");
        }
        catch (UnsupportedOperationException ignored)
        {
            this.assertUnchanged();
        }
    }

    @Test
    public void addDuplicate()
    {
        try
        {
            this.set.add("1");
            Assert.fail("Should not allow adding a duplicate to SingletonSet");
        }
        catch (UnsupportedOperationException ignored)
        {
            this.assertUnchanged();
        }
    }

    @Test
    public void add()
    {
        try
        {
            this.set.add("2");
            Assert.fail("Should not allow add to SingletonSet");
        }
        catch (UnsupportedOperationException ignored)
        {
            this.assertUnchanged();
        }
    }

    @Test
    public void addingAllToOtherSet()
    {
        MutableSet<String> newSet = UnifiedSet.newSet(Sets.fixedSize.of("1"));
        newSet.add("2");
        Verify.assertContainsAll(newSet, "1", "2");
    }

    private void assertUnchanged()
    {
        Verify.assertSize(1, this.set);
        Verify.assertContains("1", this.set);
        Verify.assertNotContains("2", this.set);
    }

    @Test
    public void tap()
    {
        MutableList<Integer> tapResult = Lists.mutable.of();
        Assert.assertSame(this.intSet, this.intSet.tap(tapResult::add));
        Assert.assertEquals(this.intSet.toList(), tapResult);
    }

    @Test
    public void forEach()
    {
        MutableList<Integer> result = Lists.mutable.of();
        this.intSet.forEach(CollectionAddProcedure.on(result));
        Verify.assertSize(1, result);
        Verify.assertContainsAll(result, 1);
    }

    @Test
    public void forEachWith()
    {
        MutableList<Integer> result = Lists.mutable.of();
        this.intSet.forEachWith((argument1, argument2) -> result.add(argument1 + argument2), 0);
        Verify.assertSize(1, result);
        Verify.assertContainsAll(result, 1);
    }

    @Test
    public void forEachWithIndex()
    {
        MutableList<Integer> result = Lists.mutable.of();
        this.intSet.forEachWithIndex((object, index) -> result.add(object + index));
        Verify.assertContainsAll(result, 1);
    }

    @Test
    public void select()
    {
        Verify.assertContainsAll(this.intSet.select(Predicates.lessThan(3)), 1);
        Verify.assertEmpty(this.intSet.select(Predicates.greaterThan(3)));
    }

    @Test
    public void selectWith()
    {
        Verify.assertContainsAll(this.intSet.selectWith(Predicates2.lessThan(), 3), 1);
        Verify.assertEmpty(this.intSet.selectWith(Predicates2.greaterThan(), 3));
    }

    @Test
    public void reject()
    {
        Verify.assertEmpty(this.intSet.reject(Predicates.lessThan(3)));
        Verify.assertContainsAll(this.intSet.reject(
                Predicates.greaterThan(3),
                UnifiedSet.newSet()),
                1);
    }

    @Test
    public void rejectWith()
    {
        Verify.assertEmpty(this.intSet.rejectWith(Predicates2.lessThan(), 3));
        Verify.assertContainsAll(
                this.intSet.rejectWith(
                        Predicates2.greaterThan(),
                        3,
                        UnifiedSet.newSet()),
                1);
    }

    @Test
    public void partition()
    {
        PartitionMutableSet<Integer> partition = this.intSet.partition(Predicates.lessThan(3));
        Assert.assertEquals(mSet(1), partition.getSelected());
        Assert.assertEquals(mSet(), partition.getRejected());
    }

    @Test
    public void partitionWith()
    {
        PartitionMutableSet<Integer> partition = this.intSet.partitionWith(Predicates2.lessThan(), 3);
        Assert.assertEquals(mSet(1), partition.getSelected());
        Assert.assertEquals(mSet(), partition.getRejected());
    }

    @Test
    public void selectInstancesOf()
    {
        MutableSet<Number> numbers = Sets.fixedSize.of(1);
        Assert.assertEquals(iSet(1), numbers.selectInstancesOf(Integer.class));
        Verify.assertEmpty(numbers.selectInstancesOf(Double.class));
    }

    @Test
    public void collect()
    {
        Verify.assertContainsAll(this.intSet.collect(String::valueOf), "1");
        Verify.assertContainsAll(this.intSet.collect(String::valueOf, UnifiedSet.newSet()),
                "1");
    }

    @Test
    public void flatCollect()
    {
        Function<Integer, MutableSet<String>> function =
                object -> UnifiedSet.newSetWith(String.valueOf(object));
        Verify.assertSetsEqual(UnifiedSet.newSetWith("1"), this.intSet.flatCollect(function));
        Verify.assertListsEqual(
                FastList.newListWith("1"),
                this.intSet.flatCollect(function, FastList.newList()));
    }

    @Test
    public void detect()
    {
        Assert.assertEquals(Integer.valueOf(1), this.intSet.detect(Integer.valueOf(1)::equals));
        Assert.assertNull(this.intSet.detect(Integer.valueOf(6)::equals));
    }

    @Test
    public void detectWith()
    {
        Assert.assertEquals(Integer.valueOf(1), this.intSet.detectWith(Object::equals, 1));
        Assert.assertNull(this.intSet.detectWith(Object::equals, 6));
    }

    @Test
    public void detectIfNone()
    {
        Function0<Integer> function = new PassThruFunction0<>(6);
        Assert.assertEquals(Integer.valueOf(1), this.intSet.detectIfNone(Integer.valueOf(1)::equals, function));
        Assert.assertEquals(Integer.valueOf(6), this.intSet.detectIfNone(Integer.valueOf(6)::equals, function));
    }

    @Test
    public void detectWithIfNone()
    {
        Function0<Integer> function = new PassThruFunction0<>(6);
        Assert.assertEquals(Integer.valueOf(1), this.intSet.detectWithIfNone(Object::equals, Integer.valueOf(1), function));
        Assert.assertEquals(Integer.valueOf(6), this.intSet.detectWithIfNone(Object::equals, Integer.valueOf(6), function));
    }

    @Test
    public void allSatisfy()
    {
        Assert.assertTrue(this.intSet.allSatisfy(Integer.class::isInstance));
        Assert.assertFalse(this.intSet.allSatisfy(Integer.valueOf(2)::equals));
    }

    @Test
    public void allSatisfyWith()
    {
        Assert.assertTrue(this.intSet.allSatisfyWith(Predicates2.instanceOf(), Integer.class));
        Assert.assertFalse(this.intSet.allSatisfyWith(Object::equals, 2));
    }

    @Test
    public void anySatisfy()
    {
        Assert.assertFalse(this.intSet.anySatisfy(String.class::isInstance));
        Assert.assertTrue(this.intSet.anySatisfy(Integer.class::isInstance));
    }

    @Test
    public void anySatisfyWith()
    {
        Assert.assertFalse(this.intSet.anySatisfyWith(Predicates2.instanceOf(), String.class));
        Assert.assertTrue(this.intSet.anySatisfyWith(Predicates2.instanceOf(), Integer.class));
    }

    @Test
    public void noneSatisfy()
    {
        Assert.assertFalse(this.intSet.noneSatisfy(Integer.class::isInstance));
        Assert.assertTrue(this.intSet.noneSatisfy(Integer.valueOf(10)::equals));
    }

    @Test
    public void noneSatisfyWith()
    {
        Assert.assertFalse(this.intSet.noneSatisfyWith(Predicates2.instanceOf(), Integer.class));
        Assert.assertTrue(this.intSet.noneSatisfyWith(Object::equals, 10));
    }

    @Test
    public void count()
    {
        Assert.assertEquals(1, this.intSet.count(Integer.class::isInstance));
        Assert.assertEquals(0, this.intSet.count(String.class::isInstance));
    }

    @Test
    public void countWith()
    {
        Assert.assertEquals(1, this.intSet.countWith(Predicates2.instanceOf(), Integer.class));
        Assert.assertEquals(0, this.intSet.countWith(Predicates2.instanceOf(), String.class));
    }

    @Test
    public void collectIf()
    {
        Verify.assertContainsAll(this.intSet.collectIf(
                Integer.class::isInstance,
                String::valueOf), "1");
        Verify.assertContainsAll(this.intSet.collectIf(
                Integer.class::isInstance,
                String::valueOf,
                FastList.newList()), "1");
    }

    @Test
    public void collectWith()
    {
        Assert.assertEquals(
                UnifiedSet.newSetWith(2),
                this.intSet.collectWith(AddFunction.INTEGER, 1));
        Assert.assertEquals(
                FastList.newListWith(2),
                this.intSet.collectWith(AddFunction.INTEGER, 1, FastList.newList()));
    }

    @Test
    public void getFirst()
    {
        Assert.assertEquals(Integer.valueOf(1), this.intSet.getFirst());
    }

    @Test
    public void getLast()
    {
        Assert.assertEquals(Integer.valueOf(1), this.intSet.getLast());
    }

    @Test
    public void getOnly()
    {
        Assert.assertEquals(Integer.valueOf(1), this.intSet.getOnly());
    }

    @Test
    public void isEmpty()
    {
        Verify.assertNotEmpty(this.intSet);
        Assert.assertTrue(this.intSet.notEmpty());
    }

    @Test
    public void removeAll()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.intSet.removeAll(Lists.fixedSize.of(1, 2)));
    }

    @Test
    public void retainAll()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.intSet.retainAll(Lists.fixedSize.of(2)));
    }

    @Test
    public void clear()
    {
        Verify.assertThrows(UnsupportedOperationException.class, this.intSet::clear);
    }

    @Override
    @Test
    public void iterator()
    {
        super.iterator();
        Iterator<Integer> iterator = this.intSet.iterator();
        for (int i = this.intSet.size(); i-- > 0; )
        {
            Integer integer = iterator.next();
            Assert.assertEquals(1, integer.intValue() + i);
        }
    }

    @Test
    public void injectInto()
    {
        Integer result = this.intSet.injectInto(1, AddFunction.INTEGER);
        Assert.assertEquals(Integer.valueOf(2), result);
    }

    @Test
    public void injectIntoWith()
    {
        Integer result = this.intSet.injectIntoWith(
                1,
                (injectedValued, item, parameter) -> injectedValued + item + parameter,
                0);
        Assert.assertEquals(Integer.valueOf(2), result);
    }

    @Test
    public void toArray()
    {
        Object[] array = this.intSet.toArray();
        Verify.assertSize(1, array);
        Integer[] array2 = this.intSet.toArray(new Integer[1]);
        Verify.assertSize(1, array2);
    }

    @Test
    public void selectAndRejectWith()
    {
        Twin<MutableList<Integer>> result =
                this.intSet.selectAndRejectWith(Object::equals, 1);
        Verify.assertSize(1, result.getOne());
        Verify.assertEmpty(result.getTwo());
    }

    @Test
    public void removeWithPredicate()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.intSet.removeIf(Predicates.isNull()));
    }

    @Test
    public void toList()
    {
        MutableList<Integer> list = this.intSet.toList();
        list.add(2);
        list.add(3);
        list.add(4);
        Verify.assertContainsAll(list, 1, 2, 3, 4);
    }

    @Test
    public void toSortedList()
    {
        Assert.assertEquals(FastList.newListWith(1), this.intSet.toSortedList(Collections.reverseOrder()));
    }

    @Test
    public void toSortedListBy()
    {
        Assert.assertEquals(FastList.newListWith(1), this.intSet.toSortedListBy(Functions.getIntegerPassThru()));
    }

    @Test
    public void toSet()
    {
        MutableSet<Integer> set = this.intSet.toSet();
        Verify.assertContainsAll(set, 1);
    }

    @Test
    public void toMap()
    {
        MutableMap<Integer, Integer> map =
                this.intSet.toMap(Functions.getIntegerPassThru(), Functions.getIntegerPassThru());
        Verify.assertContainsAll(map.keySet(), 1);
        Verify.assertContainsAll(map.values(), 1);
    }

    @Override
    @Test
    public void testClone()
    {
        try
        {
            Verify.assertShallowClone(this.set);
        }
        catch (Exception e)
        {
            // Suppress if a Java 9 specific exception related to reflection is thrown.
            if (!e.getClass().getCanonicalName().equals("java.lang.reflect.InaccessibleObjectException"))
            {
                throw e;
            }
        }
        MutableSet<String> cloneSet = this.set.clone();
        Assert.assertNotSame(cloneSet, this.set);
        Verify.assertEqualsAndHashCode(UnifiedSet.newSetWith("1"), cloneSet);
    }

    @Test
    public void newEmpty()
    {
        MutableSet<String> newEmpty = this.set.newEmpty();
        Verify.assertInstanceOf(UnifiedSet.class, newEmpty);
        Verify.assertEmpty(newEmpty);
    }

    @Test
    @Override
    public void min_null_throws()
    {
        // Collections with one element should not throw to emulate the JDK Collections behavior
        super.min_null_throws();
    }

    @Test
    @Override
    public void max_null_throws()
    {
        // Collections with one element should not throw to emulate the JDK Collections behavior
        super.max_null_throws();
    }

    @Test
    @Override
    public void min_null_throws_without_comparator()
    {
        // Collections with one element should not throw to emulate the JDK Collections behavior
        super.min_null_throws_without_comparator();
    }

    @Test
    @Override
    public void max_null_throws_without_comparator()
    {
        // Collections with one element should not throw to emulate the JDK Collections behavior
        super.max_null_throws_without_comparator();
    }
}
