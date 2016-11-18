/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.primitive;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class CollectBooleanIterableTest
{
    private final BooleanIterable booleanIterable = Interval.zeroTo(2).collectBoolean(PrimitiveFunctions.integerIsPositive());

    @Test
    public void iterator()
    {
        long count = 0;
        long isTrueCount = 0;
        BooleanIterator iterator = this.booleanIterable.booleanIterator();
        while (iterator.hasNext())
        {
            count++;
            if (iterator.next())
            {
                isTrueCount++;
            }
        }
        Assert.assertEquals(3L, count);
        Assert.assertEquals(2L, isTrueCount);
    }

    @Test
    public void size()
    {
        Assert.assertEquals(3, this.booleanIterable.size());
    }

    @Test
    public void empty()
    {
        Assert.assertTrue(this.booleanIterable.notEmpty());
        Assert.assertFalse(this.booleanIterable.isEmpty());
    }

    @Test
    public void forEach()
    {
        long[] value = new long[2];
        this.booleanIterable.forEach(each -> {
            value[0]++;
            if (each)
            {
                value[1]++;
            }
        });
        Assert.assertEquals(3, value[0]);
        Assert.assertEquals(2, value[1]);
    }

    @Test
    public void count()
    {
        Assert.assertEquals(2, this.booleanIterable.count(BooleanPredicates.equal(true)));
        Assert.assertEquals(1, this.booleanIterable.count(BooleanPredicates.equal(false)));
    }

    @Test
    public void anySatisfy()
    {
        Assert.assertTrue(this.booleanIterable.anySatisfy(BooleanPredicates.equal(true)));
    }

    @Test
    public void noneSatisfy()
    {
        Assert.assertFalse(this.booleanIterable.noneSatisfy(BooleanPredicates.equal(true)));
    }

    @Test
    public void allSatisfy()
    {
        Assert.assertFalse(this.booleanIterable.allSatisfy(BooleanPredicates.equal(false)));
    }

    @Test
    public void select()
    {
        Assert.assertEquals(2, this.booleanIterable.select(BooleanPredicates.equal(true)).size());
        Assert.assertEquals(1, this.booleanIterable.select(BooleanPredicates.equal(false)).size());
    }

    @Test
    public void reject()
    {
        Assert.assertEquals(1, this.booleanIterable.reject(BooleanPredicates.equal(true)).size());
        Assert.assertEquals(2, this.booleanIterable.reject(BooleanPredicates.equal(false)).size());
    }

    @Test
    public void detectIfNone()
    {
        Assert.assertTrue(this.booleanIterable.detectIfNone(BooleanPredicates.equal(true), false));
    }

    @Test
    public void toArray()
    {
        boolean[] actual = Interval.zeroTo(2).collectBoolean(PrimitiveFunctions.integerIsPositive()).toArray();
        Assert.assertEquals(3, actual.length);
        Assert.assertFalse(actual[0]);
        Assert.assertTrue(actual[1]);
        Assert.assertTrue(actual[2]);
    }

    @Test
    public void contains()
    {
        Assert.assertFalse(Interval.fromTo(-4, 0).collectBoolean(PrimitiveFunctions.integerIsPositive()).contains(true));
        Assert.assertTrue(Interval.fromTo(-2, 2).collectBoolean(PrimitiveFunctions.integerIsPositive()).contains(true));
    }

    @Test
    public void containsAllArray()
    {
        BooleanIterable booleanIterable = Interval.oneTo(3).collectBoolean(PrimitiveFunctions.integerIsPositive());
        Assert.assertTrue(booleanIterable.containsAll(true));
        Assert.assertTrue(booleanIterable.containsAll(true, true));
        Assert.assertFalse(booleanIterable.containsAll(false));
        Assert.assertFalse(booleanIterable.containsAll(false, false));
    }

    @Test
    public void containsAllIterable()
    {
        BooleanIterable booleanIterable = Interval.oneTo(3).collectBoolean(PrimitiveFunctions.integerIsPositive());
        Assert.assertTrue(booleanIterable.containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertTrue(booleanIterable.containsAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertFalse(booleanIterable.containsAll(BooleanArrayList.newListWith(false)));
        Assert.assertFalse(booleanIterable.containsAll(BooleanArrayList.newListWith(false, false)));
    }

    @Test
    public void collect()
    {
        Assert.assertEquals(FastList.newListWith("false", "true", "true"), this.booleanIterable.collect(String::valueOf).toList());
    }

    @Test
    public void testToString()
    {
        Assert.assertEquals("[false, true, true]", this.booleanIterable.toString());
    }

    @Test
    public void makeString()
    {
        Assert.assertEquals("false, true, true", this.booleanIterable.makeString());
        Assert.assertEquals("false/true/true", this.booleanIterable.makeString("/"));
        Assert.assertEquals("[false, true, true]", this.booleanIterable.makeString("[", ", ", "]"));
    }

    @Test
    public void appendString()
    {
        StringBuilder appendable = new StringBuilder();
        this.booleanIterable.appendString(appendable);
        Assert.assertEquals("false, true, true", appendable.toString());
        StringBuilder appendable2 = new StringBuilder();
        this.booleanIterable.appendString(appendable2, "/");
        Assert.assertEquals("false/true/true", appendable2.toString());
        StringBuilder appendable3 = new StringBuilder();
        this.booleanIterable.appendString(appendable3, "[", ", ", "]");
        Assert.assertEquals(this.booleanIterable.toString(), appendable3.toString());
    }

    @Test
    public void toList()
    {
        Assert.assertEquals(BooleanArrayList.newListWith(false, true, true), this.booleanIterable.toList());
    }

    @Test
    public void toSet()
    {
        Assert.assertEquals(BooleanHashSet.newSetWith(false, true), this.booleanIterable.toSet());
    }

    @Test
    public void toBag()
    {
        Assert.assertEquals(BooleanHashBag.newBagWith(false, true, true), this.booleanIterable.toBag());
    }

    @Test
    public void asLazy()
    {
        Assert.assertEquals(this.booleanIterable.toSet(), this.booleanIterable.asLazy().toSet());
        Verify.assertInstanceOf(LazyBooleanIterable.class, this.booleanIterable.asLazy());
    }
}
