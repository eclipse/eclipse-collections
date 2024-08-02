/*
 * Copyright (c) 2021 Goldman Sachs.
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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(3L, count);
        assertEquals(2L, isTrueCount);
    }

    @Test
    public void size()
    {
        assertEquals(3, this.booleanIterable.size());
    }

    @Test
    public void empty()
    {
        assertTrue(this.booleanIterable.notEmpty());
        assertFalse(this.booleanIterable.isEmpty());
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
        assertEquals(3, value[0]);
        assertEquals(2, value[1]);
    }

    @Test
    public void count()
    {
        assertEquals(2, this.booleanIterable.count(BooleanPredicates.equal(true)));
        assertEquals(1, this.booleanIterable.count(BooleanPredicates.equal(false)));
    }

    @Test
    public void anySatisfy()
    {
        assertTrue(this.booleanIterable.anySatisfy(BooleanPredicates.equal(true)));
    }

    @Test
    public void noneSatisfy()
    {
        assertFalse(this.booleanIterable.noneSatisfy(BooleanPredicates.equal(true)));
    }

    @Test
    public void allSatisfy()
    {
        assertFalse(this.booleanIterable.allSatisfy(BooleanPredicates.equal(false)));
    }

    @Test
    public void select()
    {
        assertEquals(2, this.booleanIterable.select(BooleanPredicates.equal(true)).size());
        assertEquals(1, this.booleanIterable.select(BooleanPredicates.equal(false)).size());
    }

    @Test
    public void reject()
    {
        assertEquals(1, this.booleanIterable.reject(BooleanPredicates.equal(true)).size());
        assertEquals(2, this.booleanIterable.reject(BooleanPredicates.equal(false)).size());
    }

    @Test
    public void detectIfNone()
    {
        assertTrue(this.booleanIterable.detectIfNone(BooleanPredicates.equal(true), false));
    }

    @Test
    public void toArray()
    {
        boolean[] actual = Interval.zeroTo(2).collectBoolean(PrimitiveFunctions.integerIsPositive()).toArray();
        assertEquals(3, actual.length);
        assertFalse(actual[0]);
        assertTrue(actual[1]);
        assertTrue(actual[2]);
    }

    @Test
    public void contains()
    {
        assertFalse(Interval.fromTo(-4, 0).collectBoolean(PrimitiveFunctions.integerIsPositive()).contains(true));
        assertTrue(Interval.fromTo(-2, 2).collectBoolean(PrimitiveFunctions.integerIsPositive()).contains(true));
    }

    @Test
    public void containsAllArray()
    {
        BooleanIterable booleanIterable = Interval.oneTo(3).collectBoolean(PrimitiveFunctions.integerIsPositive());
        assertTrue(booleanIterable.containsAll(true));
        assertTrue(booleanIterable.containsAll(true, true));
        assertFalse(booleanIterable.containsAll(false));
        assertFalse(booleanIterable.containsAll(false, false));
    }

    @Test
    public void containsAllIterable()
    {
        BooleanIterable booleanIterable = Interval.oneTo(3).collectBoolean(PrimitiveFunctions.integerIsPositive());
        assertTrue(booleanIterable.containsAll(BooleanArrayList.newListWith(true)));
        assertTrue(booleanIterable.containsAll(BooleanArrayList.newListWith(true, true)));
        assertFalse(booleanIterable.containsAll(BooleanArrayList.newListWith(false)));
        assertFalse(booleanIterable.containsAll(BooleanArrayList.newListWith(false, false)));
    }

    @Test
    public void collect()
    {
        assertEquals(FastList.newListWith("false", "true", "true"), this.booleanIterable.collect(String::valueOf).toList());
    }

    @Test
    public void testToString()
    {
        assertEquals("[false, true, true]", this.booleanIterable.toString());
    }

    @Test
    public void makeString()
    {
        assertEquals("false, true, true", this.booleanIterable.makeString());
        assertEquals("false/true/true", this.booleanIterable.makeString("/"));
        assertEquals("[false, true, true]", this.booleanIterable.makeString("[", ", ", "]"));
    }

    @Test
    public void appendString()
    {
        StringBuilder appendable = new StringBuilder();
        this.booleanIterable.appendString(appendable);
        assertEquals("false, true, true", appendable.toString());
        StringBuilder appendable2 = new StringBuilder();
        this.booleanIterable.appendString(appendable2, "/");
        assertEquals("false/true/true", appendable2.toString());
        StringBuilder appendable3 = new StringBuilder();
        this.booleanIterable.appendString(appendable3, "[", ", ", "]");
        assertEquals(this.booleanIterable.toString(), appendable3.toString());
    }

    @Test
    public void toList()
    {
        assertEquals(BooleanArrayList.newListWith(false, true, true), this.booleanIterable.toList());
    }

    @Test
    public void toSet()
    {
        assertEquals(BooleanHashSet.newSetWith(false, true), this.booleanIterable.toSet());
    }

    @Test
    public void toBag()
    {
        assertEquals(BooleanHashBag.newBagWith(false, true, true), this.booleanIterable.toBag());
    }

    @Test
    public void asLazy()
    {
        assertEquals(this.booleanIterable.toSet(), this.booleanIterable.asLazy().toSet());
        Verify.assertInstanceOf(LazyBooleanIterable.class, this.booleanIterable.asLazy());
    }
}
