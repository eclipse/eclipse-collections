/*
 * Copyright (c) 2015 Goldman Sachs.
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
import org.eclipse.collections.api.iterator.ByteIterator;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.MutableByteSet;
import org.eclipse.collections.impl.bag.mutable.primitive.ByteHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BytePredicates;
import org.eclipse.collections.impl.collection.mutable.primitive.AbstractMutableByteCollectionTestCase;
import org.eclipse.collections.impl.factory.primitive.ByteSets;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * Abstract JUnit test for {@link MutableByteSet}.
 */
public abstract class AbstractByteSetTestCase extends AbstractMutableByteCollectionTestCase
{
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
        Assert.assertFalse(this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -128).isEmpty());
    }

    @Override
    @Test
    public void notEmpty()
    {
        Assert.assertTrue(this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -128).notEmpty());
    }

    @Override
    @Test
    public void clear()
    {
        super.clear();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -128);
        set.clear();
        Verify.assertSize(0, set);
        Assert.assertFalse(set.contains((byte) 0));
        Assert.assertFalse(set.contains((byte) 31));
        Assert.assertFalse(set.contains((byte) 1));
        Assert.assertFalse(set.contains((byte) -1));
        Assert.assertFalse(set.contains((byte) -128));
    }

    @Override
    @Test
    public void add()
    {
        super.add();
        MutableByteSet set = this.newWith();
        Assert.assertTrue(set.add((byte) 14));
        Assert.assertFalse(set.add((byte) 14));
        Assert.assertTrue(set.add((byte) 2));
        Assert.assertFalse(set.add((byte) 2));
        Assert.assertTrue(set.add((byte) 35));
        Assert.assertFalse(set.add((byte) 35));
        Assert.assertTrue(set.add((byte) 31));
        Assert.assertFalse(set.add((byte) 31));
        Assert.assertTrue(set.add((byte) 32));
        Assert.assertFalse(set.add((byte) 32));
        Assert.assertTrue(set.add((byte) 0));
        Assert.assertFalse(set.add((byte) 0));
        Assert.assertTrue(set.add((byte) 1));
        Assert.assertFalse(set.add((byte) 1));
    }

    @Override
    @Test
    public void addAllIterable()
    {
        super.addAllIterable();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -128);
        Assert.assertFalse(set.addAll(new ByteArrayList()));
        Assert.assertFalse(set.addAll(ByteArrayList.newListWith((byte) 31, (byte) -1, (byte) -128)));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -128), set);

        Assert.assertTrue(set.addAll(ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 2, (byte) 30, (byte) -1, (byte) -128)));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 2, (byte) 30, (byte) 31, (byte) -1, (byte) -128), set);

        Assert.assertTrue(set.addAll(ByteHashSet.newSetWith((byte) 5)));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 2, (byte) 5, (byte) 30, (byte) 31, (byte) 31, (byte) -1, (byte) -128), set);

        ByteHashSet set1 = new ByteHashSet();
        Assert.assertTrue(set1.addAll((byte) 2, (byte) 35));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 2, (byte) 35), set1);
    }

    @Override
    @Test
    public void remove()
    {
        super.remove();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -8);
        Assert.assertFalse(this.newWith().remove((byte) 15));
        Assert.assertFalse(set.remove((byte) 15));
        Assert.assertTrue(set.remove((byte) 0));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 1, (byte) 31, (byte) -1, (byte) -8), set);
        Assert.assertFalse(set.remove((byte) -10));
        Assert.assertFalse(set.remove((byte) -7));
        Assert.assertTrue(set.remove((byte) -1));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 1, (byte) 31, (byte) -8), set);
        Assert.assertTrue(set.remove((byte) -8));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 1, (byte) 31), set);
        Assert.assertTrue(set.remove((byte) 31));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 1), set);
        Assert.assertTrue(set.remove((byte) 1));
        Assert.assertEquals(ByteHashSet.newSetWith(), set);
    }

    @Override
    @Test
    public void removeAll()
    {
        super.removeAll();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64, (byte) -100, (byte) -128);
        Assert.assertFalse(set.removeAll());
        Assert.assertFalse(set.removeAll((byte) 15, (byte) -5, (byte) -32));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64, (byte) -100, (byte) -128), set);
        Assert.assertTrue(set.removeAll((byte) 0, (byte) 1, (byte) -1, (byte) -128));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -35, (byte) -64, (byte) -100), set);
        Assert.assertTrue(set.removeAll((byte) 31, (byte) 63, (byte) 14, (byte) -100));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 100, (byte) 127, (byte) -35, (byte) -64), set);
        Assert.assertFalse(set.removeAll((byte) -34, (byte) -36, (byte) -63, (byte) -65, (byte) 99, (byte) 101, (byte) 126, (byte) 128));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 100, (byte) 127, (byte) -35, (byte) -64), set);
        Assert.assertTrue(set.removeAll((byte) -35, (byte) -63, (byte) -64, (byte) 100, (byte) 127));
        Assert.assertEquals(new ByteHashSet(), set);
    }

    @Override
    @Test
    public void removeAll_iterable()
    {
        super.removeAll_iterable();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64, (byte) -100, (byte) -128);
        Assert.assertFalse(set.removeAll(new ByteArrayList()));
        Assert.assertFalse(set.removeAll(ByteArrayList.newListWith((byte) 15, (byte) 98, (byte) -98, (byte) -127)));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64, (byte) -100, (byte) -128), set);
        Assert.assertTrue(set.removeAll(ByteHashSet.newSetWith((byte) 0, (byte) 31, (byte) -128, (byte) -100)));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 1, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64), set);
        Assert.assertTrue(set.removeAll(ByteHashSet.newSetWith((byte) 1, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64)));
        Assert.assertEquals(new ByteHashSet(), set);
        Assert.assertFalse(set.removeAll(ByteHashSet.newSetWith((byte) 1)));
        Assert.assertEquals(new ByteHashSet(), set);
    }

    @Override
    @Test
    public void byteIterator()
    {
        MutableSet<Byte> expected = UnifiedSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64, (byte) -100, (byte) -128);
        MutableSet<Byte> actual = UnifiedSet.newSet();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 100, (byte) 127, (byte) -1, (byte) -35, (byte) -64, (byte) -100, (byte) -128);
        ByteIterator iterator = set.byteIterator();
        Assert.assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        actual.add(iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(expected, actual);
        Verify.assertThrows(NoSuchElementException.class, (Runnable) iterator::next);
    }

    @Override
    @Test(expected = NoSuchElementException.class)
    public void byteIterator_throws()
    {
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -31);
        ByteIterator iterator = set.byteIterator();
        while (iterator.hasNext())
        {
            iterator.next();
        }

        iterator.next();
    }

    @Override
    @Test
    public void forEach()
    {
        super.forEach();
        long[] sum = new long[1];
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 65, (byte) 100, (byte) 127, (byte) 12, (byte) -76, (byte) -1, (byte) -54, (byte) -64, (byte) -63, (byte) -95, (byte) -128, (byte) -127);
        set.forEach(each -> sum[0] += each);

        Assert.assertEquals(-209L, sum[0]);
    }

    @Override
    @Test
    public void count()
    {
        super.count();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 65, (byte) 100, (byte) 127, (byte) 12, (byte) -76, (byte) -1, (byte) -54, (byte) -64, (byte) -63, (byte) -95, (byte) -128, (byte) -127);
        Assert.assertEquals(7L, set.count(BytePredicates.greaterThan((byte) 0)));
        Assert.assertEquals(12L, set.count(BytePredicates.lessThan((byte) 32)));
        Assert.assertEquals(4L, set.count(BytePredicates.greaterThan((byte) 32)));
        Assert.assertEquals(1L, set.count(BytePredicates.greaterThan((byte) 100)));
        Assert.assertEquals(14L, set.count(BytePredicates.lessThan((byte) 100)));
        Assert.assertEquals(7L, set.count(BytePredicates.lessThan((byte) -50)));
        Assert.assertEquals(6L, set.count(BytePredicates.lessThan((byte) -54)));
        Assert.assertEquals(15L, set.count(BytePredicates.greaterThan((byte) -128)));
    }

    @Override
    @Test
    public void select()
    {
        super.select();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 63, (byte) 65, (byte) 100, (byte) 127, (byte) 12, (byte) -76, (byte) -1, (byte) -54, (byte) -64, (byte) -63, (byte) -95, (byte) -128, (byte) -127);
        Assert.assertEquals(this.newWith((byte) 63, (byte) 65, (byte) 100, (byte) 127), set.select(BytePredicates.greaterThan((byte) 32)));
        Assert.assertEquals(this.newWith((byte) -76, (byte) -1, (byte) -54, (byte) -64, (byte) -63, (byte) -95, (byte) -128, (byte) -127), set.select(BytePredicates.lessThan((byte) 0)));
    }

    @Override
    @Test
    public void reject()
    {
        super.reject();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -127, (byte) -63);
        Assert.assertEquals(this.newWith((byte) 0, (byte) -1, (byte) -127, (byte) -63), set.reject(BytePredicates.greaterThan((byte) 0)));
        Assert.assertEquals(this.newWith((byte) 0, (byte) 1, (byte) 31), set.reject(BytePredicates.lessThan((byte) 0)));
    }

    @Override
    @Test
    public void detectIfNone()
    {
        super.detectIfNone();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 64, (byte) 127, (byte) -1, (byte) -67, (byte) -128, (byte) -63);
        Assert.assertEquals(127, set.detectIfNone(BytePredicates.greaterThan((byte) 126), (byte) 9));
        Assert.assertEquals(127, set.detectIfNone(BytePredicates.greaterThan((byte) 64), (byte) 9));
        Assert.assertEquals(-128, set.detectIfNone(BytePredicates.lessThan((byte) -68), (byte) 9));

        MutableByteSet set1 = this.newWith((byte) 0, (byte) -1, (byte) 12, (byte) 64);
        Assert.assertEquals(-1, set1.detectIfNone(BytePredicates.lessThan((byte) 0), (byte) 9));
    }

    @Override
    @Test
    public void collect()
    {
        super.collect();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -127, (byte) -63);

        Assert.assertEquals(UnifiedSet.newSetWith((byte) -1, (byte) 0, (byte) 30, (byte) -128, (byte) -64), set.collect(byteParameter -> (byte) (byteParameter - 1)));
    }

    @Override
    @Test
    public void toSortedArray()
    {
        super.toSortedArray();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -123, (byte) -53);
        Assert.assertArrayEquals(new byte[]{(byte) -123, (byte) -53, (byte) -1, (byte) 0, (byte) 1, (byte) 31}, set.toSortedArray());
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
        Assert.assertEquals(set1.hashCode(), set2.hashCode());
    }

    @Override
    @Test
    public void toBag()
    {
        Assert.assertEquals(ByteHashBag.newBagWith((byte) 1, (byte) 2, (byte) 3), this.classUnderTest().toBag());
        Assert.assertEquals(ByteHashBag.newBagWith((byte) 0, (byte) 1, (byte) 31), this.newWith((byte) 0, (byte) 1, (byte) 31).toBag());
        Assert.assertEquals(ByteHashBag.newBagWith((byte) 0, (byte) 1, (byte) 31, (byte) 32), this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 32).toBag());
    }

    @Override
    @Test
    public void asLazy()
    {
        super.asLazy();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -31, (byte) -24);
        Assert.assertEquals(set.toSet(), set.asLazy().toSet());
        Verify.assertInstanceOf(LazyByteIterable.class, set.asLazy());
    }

    @Override
    @Test
    public void asSynchronized()
    {
        super.asSynchronized();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -31, (byte) -24);
        Verify.assertInstanceOf(SynchronizedByteSet.class, set.asSynchronized());
        Assert.assertEquals(new SynchronizedByteSet(set), set.asSynchronized());
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        super.asUnmodifiable();
        MutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -31, (byte) -24);
        Verify.assertInstanceOf(UnmodifiableByteSet.class, set.asUnmodifiable());
        Assert.assertEquals(new UnmodifiableByteSet(set), set.asUnmodifiable());
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(ByteSets.class);
    }
}
