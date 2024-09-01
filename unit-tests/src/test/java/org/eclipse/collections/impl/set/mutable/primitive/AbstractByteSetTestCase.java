/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable.primitive;

import java.util.NoSuchElementException;

import org.eclipse.collections.api.LazyByteIterable;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.iterator.ByteIterator;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.MutableByteSet;
import org.eclipse.collections.api.tuple.primitive.ByteBytePair;
import org.eclipse.collections.impl.bag.mutable.primitive.ByteHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BytePredicates;
import org.eclipse.collections.impl.collection.mutable.primitive.AbstractMutableByteCollectionTestCase;
import org.eclipse.collections.impl.factory.primitive.ByteSets;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.map.mutable.primitive.CollisionGeneratorUtil;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Abstract JUnit test for {@link MutableByteSet}.
 */
public abstract class AbstractByteSetTestCase extends AbstractMutableByteCollectionTestCase
{
    protected static ByteArrayList generateCollisions()
    {
        return CollisionGeneratorUtil.generateCollisions();
    }

    @Override
    protected abstract MutableByteSet classUnderTest();

    @Override
    protected abstract MutableByteSet newWith(byte... elements);

    @Override
    protected MutableByteSet newMutableCollectionWith(byte... elements)
    {
        return ByteHashSet.newSetWith(elements);
    }

    @Override
    protected MutableSet<Byte> newObjectCollectionWith(Byte... elements)
    {
        return UnifiedSet.newSetWith(elements);
    }

    @Override
    @Test
    public void size()
    {
        super.size();
        Verify.assertSize(5, this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -128));
    }

    @Override
    @Test
    public void isEmpty()
    {
        super.isEmpty();
        assertFalse(this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -128).isEmpty());
    }

    @Override
    @Test
    public void notEmpty()
    {
        assertTrue(this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -128).notEmpty());
    }

    @Override
    @Test
    public void clear()
    {
        super.clear();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -128);
        set.clear();
        Verify.assertSize(0, set);
        assertFalse(set.contains((byte) 0));
        assertFalse(set.contains((byte) 31));
        assertFalse(set.contains((byte) 1));
        assertFalse(set.contains((byte) -1));
        assertFalse(set.contains((byte) -128));
    }

    @Override
    @Test
    public void add()
    {
        super.add();
        MutableByteSet set = this.newWith();
        assertTrue(set.add((byte) 14));
        assertFalse(set.add((byte) 14));
        assertTrue(set.add((byte) 2));
        assertFalse(set.add((byte) 2));
        assertTrue(set.add((byte) 35));
        assertFalse(set.add((byte) 35));
        assertTrue(set.add((byte) 31));
        assertFalse(set.add((byte) 31));
        assertTrue(set.add((byte) 32));
        assertFalse(set.add((byte) 32));
        assertTrue(set.add((byte) 0));
        assertFalse(set.add((byte) 0));
        assertTrue(set.add((byte) 1));
        assertFalse(set.add((byte) 1));
    }

    @Override
    @Test
    public void addAllIterable()
    {
        super.addAllIterable();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -128);
        assertFalse(set.addAll(new ByteArrayList()));
        assertFalse(set.addAll(ByteArrayList.newListWith((byte) 31, (byte) -1, (byte) -128)));
        assertEquals(ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -128), set);

        assertTrue(set.addAll(ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 2, (byte) 30, (byte) -1, (byte) -128)));
        assertEquals(ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 2, (byte) 30, (byte) 31, (byte) -1, (byte) -128), set);

        assertTrue(set.addAll(ByteHashSet.newSetWith((byte) 5)));
        assertEquals(ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 2, (byte) 5, (byte) 30, (byte) 31, (byte) 31, (byte) -1, (byte) -128), set);

        MutableByteSet set1 = new ByteHashSet();
        assertTrue(set1.addAll((byte) 2, (byte) 35));
        assertEquals(ByteHashSet.newSetWith((byte) 2, (byte) 35), set1);
    }

    @Override
    @Test
    public void remove()
    {
        super.remove();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -8);
        assertFalse(this.newWith().remove((byte) 15));
        assertFalse(set.remove((byte) 15));
        assertTrue(set.remove((byte) 0));
        assertEquals(ByteHashSet.newSetWith((byte) 1, (byte) 31, (byte) -1, (byte) -8), set);
        assertFalse(set.remove((byte) -10));
        assertFalse(set.remove((byte) -7));
        assertTrue(set.remove((byte) -1));
        assertEquals(ByteHashSet.newSetWith((byte) 1, (byte) 31, (byte) -8), set);
        assertTrue(set.remove((byte) -8));
        assertEquals(ByteHashSet.newSetWith((byte) 1, (byte) 31), set);
        assertTrue(set.remove((byte) 31));
        assertEquals(ByteHashSet.newSetWith((byte) 1), set);
        assertTrue(set.remove((byte) 1));
        assertEquals(ByteHashSet.newSetWith(), set);
    }

    @Override
    @Test
    public void removeAll()
    {
        super.removeAll();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64, (byte) -100, (byte) -128);
        assertFalse(set.removeAll());
        assertFalse(set.removeAll((byte) 15, (byte) -5, (byte) -32));
        assertEquals(ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64, (byte) -100, (byte) -128), set);
        assertTrue(set.removeAll((byte) 0, (byte) 1, (byte) -1, (byte) -128));
        assertEquals(ByteHashSet.newSetWith((byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -35, (byte) -64, (byte) -100), set);
        assertTrue(set.removeAll((byte) 31, (byte) 63, (byte) 14, (byte) -100));
        assertEquals(ByteHashSet.newSetWith((byte) 100, (byte) 127, (byte) -35, (byte) -64), set);
        assertFalse(set.removeAll((byte) -34, (byte) -36, (byte) -63, (byte) -65, (byte) 99, (byte) 101, (byte) 126, (byte) 128));
        assertEquals(ByteHashSet.newSetWith((byte) 100, (byte) 127, (byte) -35, (byte) -64), set);
        assertTrue(set.removeAll((byte) -35, (byte) -63, (byte) -64, (byte) 100, (byte) 127));
        assertEquals(new ByteHashSet(), set);
    }

    @Override
    @Test
    public void removeAll_iterable()
    {
        super.removeAll_iterable();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64, (byte) -100, (byte) -128);
        assertFalse(set.removeAll(new ByteArrayList()));
        assertFalse(set.removeAll(ByteArrayList.newListWith((byte) 15, (byte) 98, (byte) -98, (byte) -127)));
        assertEquals(ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64, (byte) -100, (byte) -128), set);
        assertTrue(set.removeAll(ByteHashSet.newSetWith((byte) 0, (byte) 31, (byte) -128, (byte) -100)));
        assertEquals(ByteHashSet.newSetWith((byte) 1, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64), set);
        assertTrue(set.removeAll(ByteHashSet.newSetWith((byte) 1, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64)));
        assertEquals(new ByteHashSet(), set);
        assertFalse(set.removeAll(ByteHashSet.newSetWith((byte) 1)));
        assertEquals(new ByteHashSet(), set);
    }

    @Override
    @Test
    public void byteIterator()
    {
        MutableSet<Byte> expected = UnifiedSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64, (byte) -100, (byte) -128);
        MutableSet<Byte> actual = UnifiedSet.newSet();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64, (byte) -100, (byte) -128);
        ByteIterator iterator = set.byteIterator();
        assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        assertFalse(iterator.hasNext());
        assertEquals(expected, actual);
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Override
    @Test
    public void byteIterator_throws()
    {
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -31);
        ByteIterator iterator = set.byteIterator();
        while (iterator.hasNext())
        {
            iterator.next();
        }

        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Override
    @Test
    public void forEach()
    {
        super.forEach();
        long[] sum = new long[1];
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 65, (byte) 100, (byte) 127, (byte) 12, (byte) -76, (byte) -1, (byte) -54, (byte) -64, (byte) -63, (byte) -95, (byte) -128, (byte) -127);
        set.forEach(each -> sum[0] += each);

        assertEquals(-209L, sum[0]);
    }

    @Override
    @Test
    public void count()
    {
        super.count();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 65, (byte) 100, (byte) 127, (byte) 12, (byte) -76, (byte) -1, (byte) -54, (byte) -64, (byte) -63, (byte) -95, (byte) -128, (byte) -127);
        assertEquals(7L, set.count(BytePredicates.greaterThan((byte) 0)));
        assertEquals(12L, set.count(BytePredicates.lessThan((byte) 32)));
        assertEquals(4L, set.count(BytePredicates.greaterThan((byte) 32)));
        assertEquals(1L, set.count(BytePredicates.greaterThan((byte) 100)));
        assertEquals(14L, set.count(BytePredicates.lessThan((byte) 100)));
        assertEquals(7L, set.count(BytePredicates.lessThan((byte) -50)));
        assertEquals(6L, set.count(BytePredicates.lessThan((byte) -54)));
        assertEquals(15L, set.count(BytePredicates.greaterThan((byte) -128)));
    }

    @Override
    @Test
    public void select()
    {
        super.select();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 65, (byte) 100, (byte) 127, (byte) 12, (byte) -76, (byte) -1, (byte) -54, (byte) -64, (byte) -63, (byte) -95, (byte) -128, (byte) -127);
        assertEquals(this.newWith((byte) 63, (byte) 65, (byte) 100, (byte) 127), set.select(BytePredicates.greaterThan((byte) 32)));
        assertEquals(this.newWith((byte) -76, (byte) -1, (byte) -54, (byte) -64, (byte) -63, (byte) -95, (byte) -128, (byte) -127), set.select(BytePredicates.lessThan((byte) 0)));
    }

    @Override
    @Test
    public void reject()
    {
        super.reject();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -127, (byte) -63);
        assertEquals(this.newWith((byte) 0, (byte) -1, (byte) -127, (byte) -63), set.reject(BytePredicates.greaterThan((byte) 0)));
        assertEquals(this.newWith((byte) 0, (byte) 1, (byte) 31), set.reject(BytePredicates.lessThan((byte) 0)));
    }

    @Override
    @Test
    public void detectIfNone()
    {
        super.detectIfNone();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 64, (byte) 127, (byte) -1, (byte) -67, (byte) -128, (byte) -63);
        assertEquals(127, set.detectIfNone(BytePredicates.greaterThan((byte) 126), (byte) 9));
        assertEquals(127, set.detectIfNone(BytePredicates.greaterThan((byte) 64), (byte) 9));
        assertEquals(-128, set.detectIfNone(BytePredicates.lessThan((byte) -68), (byte) 9));

        MutableByteSet set1 = this.newWith((byte) 0, (byte) -1, (byte) 12, (byte) 64);
        assertEquals(-1, set1.detectIfNone(BytePredicates.lessThan((byte) 0), (byte) 9));
    }

    @Override
    @Test
    public void collect()
    {
        super.collect();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -127, (byte) -63);

        assertEquals(UnifiedSet.newSetWith((byte) -1, (byte) 0, (byte) 30, (byte) -128, (byte) -64), set.collect(byteParameter -> (byte) (byteParameter - 1)));
    }

    @Override
    @Test
    public void toSortedArray()
    {
        super.toSortedArray();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -123, (byte) -53);
        assertArrayEquals(new byte[]{(byte) -123, (byte) -53, (byte) -1, (byte) 0, (byte) 1, (byte) 31}, set.toSortedArray());
    }

    @Override
    @Test
    public void testEquals()
    {
        super.testEquals();
        MutableByteSet set1 = this.newWith((byte) 1, (byte) 31, (byte) 32);
        MutableByteSet set2 = this.newWith((byte) 32, (byte) 31, (byte) 1);
        MutableByteSet set3 = this.newWith((byte) 32, (byte) 32, (byte) 31, (byte) 1);
        MutableByteSet set4 = this.newWith((byte) 32, (byte) 32, (byte) 31, (byte) 1, (byte) 1);
        Verify.assertEqualsAndHashCode(set1, set2);
        Verify.assertEqualsAndHashCode(set1, set3);
        Verify.assertEqualsAndHashCode(set1, set4);
        Verify.assertEqualsAndHashCode(set2, set3);
        Verify.assertEqualsAndHashCode(set2, set4);
    }

    @Override
    @Test
    public void testHashCode()
    {
        super.testEquals();
        MutableByteSet set1 = this.newWith((byte) 1, (byte) 31, (byte) 32);
        MutableByteSet set2 = this.newWith((byte) 32, (byte) 31, (byte) 1);
        assertEquals(set1.hashCode(), set2.hashCode());
    }

    @Override
    @Test
    public void toBag()
    {
        assertEquals(ByteHashBag.newBagWith((byte) 1, (byte) 2, (byte) 3), this.classUnderTest().toBag());
        assertEquals(ByteHashBag.newBagWith((byte) 0, (byte) 1, (byte) 31), this.newWith((byte) 0, (byte) 1, (byte) 31).toBag());
        assertEquals(ByteHashBag.newBagWith((byte) 0, (byte) 1, (byte) 31, (byte) 32), this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 32).toBag());
    }

    @Override
    @Test
    public void asLazy()
    {
        super.asLazy();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -31, (byte) -24);
        assertEquals(set.toSet(), set.asLazy().toSet());
        Verify.assertInstanceOf(LazyByteIterable.class, set.asLazy());
    }

    @Override
    @Test
    public void asSynchronized()
    {
        super.asSynchronized();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -31, (byte) -24);
        Verify.assertInstanceOf(SynchronizedByteSet.class, set.asSynchronized());
        assertEquals(new SynchronizedByteSet(set), set.asSynchronized());
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        super.asUnmodifiable();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -31, (byte) -24);
        Verify.assertInstanceOf(UnmodifiableByteSet.class, set.asUnmodifiable());
        assertEquals(new UnmodifiableByteSet(set), set.asUnmodifiable());
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(ByteSets.class);
    }

    @Test
    public void union()
    {
        this.assertUnion(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith((byte) 3, (byte) 4, (byte) 5),
                this.newWith((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5));

        this.assertUnion(
                this.newWith((byte) 1, (byte) 2, (byte) 3, (byte) 6),
                this.newWith((byte) 3, (byte) 4, (byte) 5),
                this.newWith((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6));

        this.assertUnion(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith((byte) 3, (byte) 4, (byte) 5, (byte) 6),
                this.newWith((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6));

        this.assertUnion(
                this.newWith(),
                this.newWith(),
                this.newWith());

        this.assertUnion(
                this.newWith(),
                this.newWith((byte) 3, (byte) 4, (byte) 5),
                this.newWith((byte) 3, (byte) 4, (byte) 5));

        this.assertUnion(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith(),
                this.newWith((byte) 1, (byte) 2, (byte) 3));
    }

    private void assertUnion(MutableByteSet set1, MutableByteSet set2, MutableByteSet expected)
    {
        MutableByteSet actual = set1.union(set2);
        assertEquals(expected, actual);
    }

    @Test
    public void intersect()
    {
        this.assertIntersect(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith((byte) 3, (byte) 4, (byte) 5),
                this.newWith((byte) 3));

        this.assertIntersect(
                this.newWith((byte) 1, (byte) 2, (byte) 3, (byte) 6),
                this.newWith((byte) 3, (byte) 4, (byte) 5),
                this.newWith((byte) 3));

        this.assertIntersect(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith((byte) 3, (byte) 4, (byte) 5, (byte) 6),
                this.newWith((byte) 3));

        this.assertIntersect(
                this.newWith(),
                this.newWith(),
                this.newWith());

        this.assertIntersect(
                this.newWith(),
                this.newWith((byte) 3, (byte) 4, (byte) 5),
                this.newWith());

        this.assertIntersect(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith(),
                this.newWith());
    }

    private void assertIntersect(MutableByteSet set1, MutableByteSet set2, MutableByteSet expected)
    {
        MutableByteSet actual = set1.intersect(set2);
        assertEquals(expected, actual);
    }

    @Test
    public void difference()
    {
        this.assertDifference(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith((byte) 3, (byte) 4, (byte) 5),
                this.newWith((byte) 1, (byte) 2));

        this.assertDifference(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith());

        this.assertDifference(
                this.newWith(),
                this.newWith(),
                this.newWith());

        this.assertDifference(
                this.newWith(),
                this.newWith((byte) 3, (byte) 4, (byte) 5),
                this.newWith());

        this.assertDifference(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith(),
                this.newWith((byte) 1, (byte) 2, (byte) 3));
    }

    private void assertDifference(MutableByteSet set1, MutableByteSet set2, MutableByteSet expected)
    {
        MutableByteSet actual = set1.difference(set2);
        assertEquals(expected, actual);
    }

    @Test
    public void symmetricDifference()
    {
        this.assertSymmetricDifference(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith((byte) 2, (byte) 3, (byte) 4),
                this.newWith((byte) 1, (byte) 4));

        this.assertSymmetricDifference(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith());

        this.assertSymmetricDifference(
                this.newWith(),
                this.newWith(),
                this.newWith());

        this.assertSymmetricDifference(
                this.newWith(),
                this.newWith((byte) 3, (byte) 4, (byte) 5),
                this.newWith((byte) 3, (byte) 4, (byte) 5));

        this.assertSymmetricDifference(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith(),
                this.newWith((byte) 1, (byte) 2, (byte) 3));
    }

    private void assertSymmetricDifference(MutableByteSet set1, MutableByteSet set2, MutableByteSet expected)
    {
        MutableByteSet actual = set1.symmetricDifference(set2);
        assertEquals(expected, actual);
    }

    @Test
    public void isSubsetOf()
    {
        this.assertIsSubsetOf(
                this.newWith((byte) 1, (byte) 2),
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                true);

        this.assertIsSubsetOf(
                this.newWith((byte) 1, (byte) 4),
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                false);

        this.assertIsSubsetOf(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                true);

        this.assertIsSubsetOf(
                this.newWith(),
                this.newWith(),
                true);

        this.assertIsSubsetOf(
                this.newWith(),
                this.newWith((byte) 3, (byte) 4, (byte) 5),
                true);

        this.assertIsSubsetOf(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith(),
                false);
    }

    private void assertIsSubsetOf(MutableByteSet set1, MutableByteSet set2, boolean expected)
    {
        boolean actual = set1.isSubsetOf(set2);
        assertEquals(expected, actual);
    }

    @Test
    public void isProperSubsetOf()
    {
        this.assertIsProperSubsetOf(
                this.newWith((byte) 1, (byte) 2),
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                true);

        this.assertIsProperSubsetOf(
                this.newWith((byte) 1, (byte) 4),
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                false);

        this.assertIsProperSubsetOf(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                false);

        this.assertIsProperSubsetOf(
                this.newWith(),
                this.newWith(),
                false);

        this.assertIsProperSubsetOf(
                this.newWith(),
                this.newWith((byte) 3, (byte) 4, (byte) 5),
                true);

        this.assertIsProperSubsetOf(
                this.newWith((byte) 1, (byte) 2, (byte) 3),
                this.newWith(),
                false);
    }

    private void assertIsProperSubsetOf(MutableByteSet set1, MutableByteSet set2, boolean expected)
    {
        boolean actual = set1.isProperSubsetOf(set2);
        assertEquals(expected, actual);
    }

    @Test
    public void isCartesianProduct()
    {
        this.assertCartesianProduct(
                this.newWith((byte) 1, (byte) 2),
                this.newWith((byte) 3, (byte) 4),
                Sets.mutable.with(
                        PrimitiveTuples.pair((byte) 1, (byte) 3),
                        PrimitiveTuples.pair((byte) 1, (byte) 4),
                        PrimitiveTuples.pair((byte) 2, (byte) 3),
                        PrimitiveTuples.pair((byte) 2, (byte) 4)));

        this.assertCartesianProduct(
                this.newWith((byte) 1, (byte) 2),
                this.newWith((byte) 1, (byte) 2),
                Sets.mutable.with(
                        PrimitiveTuples.pair((byte) 1, (byte) 1),
                        PrimitiveTuples.pair((byte) 1, (byte) 2),
                        PrimitiveTuples.pair((byte) 2, (byte) 1),
                        PrimitiveTuples.pair((byte) 2, (byte) 2)));

        this.assertCartesianProduct(
                this.newWith((byte) 1, (byte) 2),
                this.newWith(),
                Sets.mutable.empty());

        this.assertCartesianProduct(
                this.newWith(),
                this.newWith((byte) 1, (byte) 2),
                Sets.mutable.empty());

        this.assertCartesianProduct(
                this.newWith(),
                this.newWith(),
                Sets.mutable.empty());
    }

    private void assertCartesianProduct(MutableByteSet set1, MutableByteSet set2, MutableSet<ByteBytePair> expected)
    {
        MutableSet<ByteBytePair> actual = set1.cartesianProduct(set2).toSet();
        assertEquals(expected, actual);
    }
}
