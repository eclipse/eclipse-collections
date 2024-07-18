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

import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.math.MutableInteger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectBooleanIterableTest
{
    private final SelectBooleanIterable iterable = new SelectBooleanIterable(BooleanArrayList.newListWith(true, false, false, true), BooleanPredicates.isTrue());

    @Test
    public void booleanIterator()
    {
        StringBuilder concat = new StringBuilder();
        for (BooleanIterator iterator = this.iterable.booleanIterator(); iterator.hasNext(); )
        {
            concat.append(iterator.next());
        }
        assertEquals("truetrue", concat.toString());
    }

    @Test
    public void forEach()
    {
        String[] concat = new String[1];
        concat[0] = "";
        this.iterable.forEach(each -> concat[0] += each);
        assertEquals("truetrue", concat[0]);
    }

    @Test
    public void injectInto()
    {
        MutableInteger result = this.iterable.injectInto(new MutableInteger(0), (object, value) -> object.add(value ? 1 : 0));
        assertEquals(new MutableInteger(2), result);
    }

    @Test
    public void size()
    {
        assertEquals(2L, this.iterable.size());
    }

    @Test
    public void empty()
    {
        assertTrue(this.iterable.notEmpty());
        assertFalse(this.iterable.isEmpty());
    }

    @Test
    public void count()
    {
        assertEquals(2L, this.iterable.count(BooleanPredicates.isTrue()));
        assertEquals(0L, this.iterable.count(BooleanPredicates.isFalse()));
    }

    @Test
    public void anySatisfy()
    {
        assertTrue(this.iterable.anySatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.iterable.anySatisfy(BooleanPredicates.isFalse()));
    }

    @Test
    public void allSatisfy()
    {
        assertTrue(this.iterable.allSatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.iterable.allSatisfy(BooleanPredicates.isFalse()));
    }

    @Test
    public void select()
    {
        assertEquals(0L, this.iterable.select(BooleanPredicates.isFalse()).size());
        assertEquals(2L, this.iterable.select(BooleanPredicates.equal(true)).size());
    }

    @Test
    public void reject()
    {
        assertEquals(2L, this.iterable.reject(BooleanPredicates.isFalse()).size());
        assertEquals(0L, this.iterable.reject(BooleanPredicates.equal(true)).size());
    }

    @Test
    public void detectIfNone()
    {
        assertTrue(this.iterable.detectIfNone(BooleanPredicates.isTrue(), false));
        assertFalse(this.iterable.detectIfNone(BooleanPredicates.isFalse(), false));
    }

    @Test
    public void collect()
    {
        assertEquals(2L, this.iterable.collect(String::valueOf).size());
    }

    @Test
    public void toArray()
    {
        assertEquals(2L, this.iterable.toArray().length);
        assertTrue(this.iterable.toArray()[0]);
        assertTrue(this.iterable.toArray()[1]);
    }

    @Test
    public void contains()
    {
        assertTrue(this.iterable.contains(true));
        assertFalse(this.iterable.contains(false));
    }

    @Test
    public void containsAll()
    {
        assertTrue(this.iterable.containsAll(true, true));
        assertFalse(this.iterable.containsAll(false, true));
        assertFalse(this.iterable.containsAll(false, false));
    }
}
