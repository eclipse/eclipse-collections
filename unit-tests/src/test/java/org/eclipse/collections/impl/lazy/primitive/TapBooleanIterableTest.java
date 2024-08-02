/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.primitive;

import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.list.primitive.BooleanList;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.math.MutableInteger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TapBooleanIterableTest
{
    private final BooleanList list = BooleanLists.immutable.with(true, false, false, true);

    @Test
    public void booleanIterator()
    {
        StringBuilder concat = new StringBuilder();
        LazyBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        for (BooleanIterator iterator = iterable.booleanIterator(); iterator.hasNext(); )
        {
            iterator.next();
        }
        assertEquals("truefalsefalsetrue", concat.toString());
    }

    @Test
    public void forEach()
    {
        StringBuilder concat = new StringBuilder();
        LazyBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        iterable.forEach(each -> { });
        assertEquals("truefalsefalsetrue", concat.toString());
    }

    @Test
    public void injectInto()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        MutableInteger result = iterable.injectInto(new MutableInteger(0), (object, value) -> object.add(value ? 1 : 0));
        assertEquals(new MutableInteger(2), result);
        assertEquals("truefalsefalsetrue", concat.toString());
    }

    @Test
    public void size()
    {
        StringBuilder concat = new StringBuilder();
        LazyBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        assertEquals(4L, iterable.size());
        assertEquals("truefalsefalsetrue", concat.toString());
    }

    @Test
    public void empty()
    {
        StringBuilder concat = new StringBuilder();
        LazyBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        assertTrue(iterable.notEmpty());
        assertFalse(iterable.isEmpty());
    }

    @Test
    public void count()
    {
        StringBuilder concat = new StringBuilder();
        LazyBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        assertEquals(2L, iterable.count(BooleanPredicates.isTrue()));
        assertEquals(2L, iterable.count(BooleanPredicates.isFalse()));
        assertEquals("truefalsefalsetruetruefalsefalsetrue", concat.toString());
    }

    @Test
    public void anySatisfy()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        assertTrue(iterable.anySatisfy(BooleanPredicates.isTrue()));
        assertTrue(iterable.anySatisfy(BooleanPredicates.isFalse()));
    }

    @Test
    public void allSatisfy()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        assertFalse(iterable.allSatisfy(BooleanPredicates.isTrue()));
        assertFalse(iterable.allSatisfy(BooleanPredicates.isFalse()));
    }

    @Test
    public void select()
    {
        StringBuilder concat = new StringBuilder();
        LazyBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        assertEquals(2L, iterable.select(BooleanPredicates.isFalse()).size());
        assertEquals(2L, iterable.select(BooleanPredicates.equal(true)).size());
        assertEquals("truefalsefalsetruetruefalsefalsetrue", concat.toString());
    }

    @Test
    public void reject()
    {
        StringBuilder concat = new StringBuilder();
        LazyBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        assertEquals(2L, iterable.reject(BooleanPredicates.isFalse()).size());
        assertEquals(2L, iterable.reject(BooleanPredicates.equal(true)).size());
        assertEquals("truefalsefalsetruetruefalsefalsetrue", concat.toString());
    }

    @Test
    public void detectIfNone()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        assertTrue(iterable.detectIfNone(BooleanPredicates.isTrue(), false));
        assertFalse(iterable.detectIfNone(BooleanPredicates.isFalse(), false));
    }

    @Test
    public void collect()
    {
        StringBuilder concat = new StringBuilder();
        LazyBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        assertEquals(4L, iterable.collect(String::valueOf).size());
        assertEquals("truefalsefalsetrue", concat.toString());
    }

    @Test
    public void toArray()
    {
        StringBuilder concat = new StringBuilder();
        LazyBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        assertEquals(4L, iterable.toArray().length);
        assertTrue(iterable.toArray()[0]);
        assertFalse(iterable.toArray()[1]);
    }

    @Test
    public void contains()
    {
        StringBuilder concat = new StringBuilder();
        LazyBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        assertTrue(iterable.contains(true));
        assertTrue(iterable.contains(false));
    }

    @Test
    public void containsAll()
    {
        StringBuilder concat = new StringBuilder();
        LazyBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        assertTrue(iterable.containsAll(true, true));
        assertTrue(iterable.containsAll(false, true));
        assertTrue(iterable.containsAll(false, false));
    }
}
