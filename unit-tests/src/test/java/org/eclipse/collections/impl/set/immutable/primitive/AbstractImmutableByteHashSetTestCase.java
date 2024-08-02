/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.immutable.primitive;

import java.util.NoSuchElementException;

import org.eclipse.collections.api.LazyByteIterable;
import org.eclipse.collections.api.iterator.ByteIterator;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.ImmutableByteSet;
import org.eclipse.collections.api.set.primitive.MutableByteSet;
import org.eclipse.collections.api.tuple.primitive.ByteBytePair;
import org.eclipse.collections.impl.bag.mutable.primitive.ByteHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BytePredicates;
import org.eclipse.collections.impl.collection.immutable.primitive.AbstractImmutableByteCollectionTestCase;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.map.mutable.primitive.CollisionGeneratorUtil;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.mutable.primitive.ByteHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Abstract JUnit test for {@link ImmutableByteSet}.
 */
public abstract class AbstractImmutableByteHashSetTestCase extends AbstractImmutableByteCollectionTestCase
{
    @Override
    protected abstract ImmutableByteSet classUnderTest();

    @Override
    protected abstract ImmutableByteSet newWith(byte... elements);

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

    protected static ByteArrayList generateCollisions()
    {
        return CollisionGeneratorUtil.generateCollisions();
    }

    @Override
    @Test
    public void size()
    {
        super.size();
        Verify.assertSize(3, this.newWith((byte) 0, (byte) 1, (byte) 31));
    }

    @Override
    @Test
    public void isEmpty()
    {
        super.isEmpty();
        assertFalse(this.newWith((byte) 0, (byte) 1, (byte) 31).isEmpty());
    }

    @Override
    @Test
    public void notEmpty()
    {
        assertTrue(this.newWith((byte) 0, (byte) 1, (byte) 31).notEmpty());
    }

    @Override
    @Test
    public void byteIterator()
    {
        MutableSet<Byte> expected = UnifiedSet.newSetWith((byte) 0, (byte) 1, (byte) 31);
        MutableSet<Byte> actual = UnifiedSet.newSet();
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
        ByteIterator iterator = set.byteIterator();
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
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
        ByteIterator iterator = set.byteIterator();
        while (iterator.hasNext())
        {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Override
    @Test
    public void forEach()
    {
        super.forEach();
        long[] sum = new long[1];
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
        set.forEach(each -> sum[0] += each);

        assertEquals(32L, sum[0]);
    }

    @Override
    @Test
    public void count()
    {
        super.count();
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 127, (byte) -1, (byte) -31, (byte) -64, (byte) -65, (byte) -128);
        assertEquals(3, set.count(BytePredicates.greaterThan((byte) 0)));
        assertEquals(8, set.count(BytePredicates.lessThan((byte) 32)));
        assertEquals(1, set.count(BytePredicates.greaterThan((byte) 32)));
    }

    @Override
    @Test
    public void select()
    {
        super.select();
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 127, (byte) -1, (byte) -31, (byte) -64, (byte) -65, (byte) -128);
        Verify.assertSize(8, set.select(BytePredicates.lessThan((byte) 32)));
        Verify.assertSize(3, set.select(BytePredicates.greaterThan((byte) 0)));
    }

    @Override
    @Test
    public void reject()
    {
        super.reject();
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 127, (byte) -1, (byte) -31, (byte) -64, (byte) -65, (byte) -128);
        Verify.assertSize(6, set.reject(BytePredicates.greaterThan((byte) 0)));
        Verify.assertSize(1, set.reject(BytePredicates.lessThan((byte) 32)));
    }

    @Override
    @Test
    public void detectIfNone()
    {
        super.detectIfNone();
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
        assertEquals((byte) 0, set.detectIfNone(BytePredicates.lessThan((byte) 1), (byte) 9));
        assertEquals((byte) 31, set.detectIfNone(BytePredicates.greaterThan((byte) 1), (byte) 9));
        assertEquals((byte) 9, set.detectIfNone(BytePredicates.greaterThan((byte) 31), (byte) 9));
    }

    @Override
    @Test
    public void collect()
    {
        super.collect();
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
        assertEquals(UnifiedSet.newSetWith((byte) -1, (byte) 0, (byte) 30), set.collect(byteParameter -> (byte) (byteParameter - 1)));
    }

    @Override
    @Test
    public void toSortedArray()
    {
        super.toSortedArray();
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
        assertArrayEquals(new byte[]{(byte) 0, (byte) 1, (byte) 31}, set.toSortedArray());
    }

    @Override
    @Test
    public void testEquals()
    {
        super.testEquals();
        ImmutableByteSet set1 = this.newWith((byte) 1, (byte) 31, (byte) 32);
        ImmutableByteSet set2 = this.newWith((byte) 32, (byte) 31, (byte) 1);
        ImmutableByteSet set3 = this.newWith((byte) 32, (byte) 32, (byte) 31, (byte) 1);
        ImmutableByteSet set4 = this.newWith((byte) 32, (byte) 32, (byte) 31, (byte) 1, (byte) 1);
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
        ImmutableByteSet set1 = this.newWith((byte) 1, (byte) 31, (byte) 32);
        ImmutableByteSet set2 = this.newWith((byte) 32, (byte) 31, (byte) 1);
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
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
        assertEquals(set.toSet(), set.asLazy().toSet());
        Verify.assertInstanceOf(LazyByteIterable.class, set.asLazy());
    }

    @Test
    public void toImmutable()
    {
        assertEquals(0, this.newWith().toImmutable().size());
        assertEquals(1, this.newWith((byte) 1).toImmutable().size());
        assertEquals(3, this.newWith((byte) 1, (byte) 2, (byte) 3).toImmutable().size());
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

    private void assertUnion(ImmutableByteSet set1, ImmutableByteSet set2, ImmutableByteSet expected)
    {
        ImmutableByteSet actual = set1.union(set2);
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

    private void assertIntersect(ImmutableByteSet set1, ImmutableByteSet set2, ImmutableByteSet expected)
    {
        ImmutableByteSet actual = set1.intersect(set2);
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

    private void assertDifference(ImmutableByteSet set1, ImmutableByteSet set2, ImmutableByteSet expected)
    {
        ImmutableByteSet actual = set1.difference(set2);
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

    private void assertSymmetricDifference(ImmutableByteSet set1, ImmutableByteSet set2, ImmutableByteSet expected)
    {
        ImmutableByteSet actual = set1.symmetricDifference(set2);
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

    private void assertIsSubsetOf(ImmutableByteSet set1, ImmutableByteSet set2, boolean expected)
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

    private void assertIsProperSubsetOf(ImmutableByteSet set1, ImmutableByteSet set2, boolean expected)
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
                Sets.immutable.with(
                        PrimitiveTuples.pair((byte) 1, (byte) 3),
                        PrimitiveTuples.pair((byte) 1, (byte) 4),
                        PrimitiveTuples.pair((byte) 2, (byte) 3),
                        PrimitiveTuples.pair((byte) 2, (byte) 4)));

        this.assertCartesianProduct(
                this.newWith((byte) 1, (byte) 2),
                this.newWith((byte) 1, (byte) 2),
                Sets.immutable.with(
                        PrimitiveTuples.pair((byte) 1, (byte) 1),
                        PrimitiveTuples.pair((byte) 1, (byte) 2),
                        PrimitiveTuples.pair((byte) 2, (byte) 1),
                        PrimitiveTuples.pair((byte) 2, (byte) 2)));

        this.assertCartesianProduct(
                this.newWith((byte) 1, (byte) 2),
                this.newWith(),
                Sets.immutable.empty());

        this.assertCartesianProduct(
                this.newWith(),
                this.newWith((byte) 1, (byte) 2),
                Sets.immutable.empty());

        this.assertCartesianProduct(
                this.newWith(),
                this.newWith(),
                Sets.immutable.empty());
    }

    private void assertCartesianProduct(ImmutableByteSet set1, ImmutableByteSet set2, ImmutableSet<ByteBytePair> expected)
    {
        ImmutableSet<ByteBytePair> actual = set1.cartesianProduct(set2).toSet().toImmutable();
        assertEquals(expected, actual);
    }
}
