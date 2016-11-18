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
import org.eclipse.collections.api.set.primitive.ImmutableByteSet;
import org.eclipse.collections.api.set.primitive.MutableByteSet;
import org.eclipse.collections.impl.bag.mutable.primitive.ByteHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BytePredicates;
import org.eclipse.collections.impl.collection.immutable.primitive.AbstractImmutableByteCollectionTestCase;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertFalse(this.newWith((byte) 0, (byte) 1, (byte) 31).isEmpty());
    }

    @Override
    @Test
    public void notEmpty()
    {
        Assert.assertTrue(this.newWith((byte) 0, (byte) 1, (byte) 31).notEmpty());
    }

    @Override
    @Test
    public void byteIterator()
    {
        MutableSet<Byte> expected = UnifiedSet.newSetWith((byte) 0, (byte) 1, (byte) 31);
        MutableSet<Byte> actual = UnifiedSet.newSet();
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
        ByteIterator iterator = set.byteIterator();
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
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
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
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
        set.forEach(each -> sum[0] += each);

        Assert.assertEquals(32L, sum[0]);
    }

    @Override
    @Test
    public void count()
    {
        super.count();
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31, (byte) 127, (byte) -1, (byte) -31, (byte) -64, (byte) -65, (byte) -128);
        Assert.assertEquals(3, set.count(BytePredicates.greaterThan((byte) 0)));
        Assert.assertEquals(8, set.count(BytePredicates.lessThan((byte) 32)));
        Assert.assertEquals(1, set.count(BytePredicates.greaterThan((byte) 32)));
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
        Assert.assertEquals((byte) 0, set.detectIfNone(BytePredicates.lessThan((byte) 1), (byte) 9));
        Assert.assertEquals((byte) 31, set.detectIfNone(BytePredicates.greaterThan((byte) 1), (byte) 9));
        Assert.assertEquals((byte) 9, set.detectIfNone(BytePredicates.greaterThan((byte) 31), (byte) 9));
    }

    @Override
    @Test
    public void collect()
    {
        super.collect();
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
        Assert.assertEquals(UnifiedSet.newSetWith((byte) -1, (byte) 0, (byte) 30), set.collect(byteParameter -> (byte) (byteParameter - 1)));
    }

    @Override
    @Test
    public void toSortedArray()
    {
        super.toSortedArray();
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
        Assert.assertArrayEquals(new byte[]{(byte) 0, (byte) 1, (byte) 31}, set.toSortedArray());
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
        ImmutableByteSet set = this.newWith((byte) 0, (byte) 1, (byte) 31);
        Assert.assertEquals(set.toSet(), set.asLazy().toSet());
        Verify.assertInstanceOf(LazyByteIterable.class, set.asLazy());
    }

    @Test
    public void toImmutable()
    {
        Assert.assertEquals(0, this.newWith().toImmutable().size());
        Assert.assertEquals(1, this.newWith((byte) 1).toImmutable().size());
        Assert.assertEquals(3, this.newWith((byte) 1, (byte) 2, (byte) 3).toImmutable().size());
    }
}
