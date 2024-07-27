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

import java.util.NoSuchElementException;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link ReverseBooleanIterable}.
 */
public class ReverseBooleanIterableTest
{
    @Test
    public void isEmpty()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        Verify.assertEmpty(new BooleanArrayList().asReversed());
        Verify.assertNotEmpty(iterable);
    }

    @Test
    public void contains()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false).asReversed();
        assertTrue(iterable.contains(false));
        assertFalse(iterable.contains(true));
    }

    @Test
    public void containsAll()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(true, false, true).asReversed();
        assertTrue(iterable.containsAll(true));
        assertTrue(iterable.containsAll(true, false));
        assertFalse(BooleanArrayList.newListWith(false, false).asReversed().containsAll(true));
        assertFalse(BooleanArrayList.newListWith(false, false).asReversed().containsAll(BooleanArrayList.newListWith(true, false)));
        assertTrue(BooleanArrayList.newListWith(false, false, true).asReversed().containsAll(BooleanArrayList.newListWith(true, false)));
    }

    @Test
    public void iterator()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        BooleanIterator iterator = iterable.booleanIterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next());
        assertTrue(iterator.hasNext());
        assertFalse(iterator.next());
        assertTrue(iterator.hasNext());
        assertFalse(iterator.next());
    }

    @Test
    public void iterator_throws()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        BooleanIterator iterator = iterable.booleanIterator();
        while (iterator.hasNext())
        {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void forEach()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        boolean[] result = {true};
        iterable.forEach(each -> result[0] &= each);

        assertFalse(result[0]);
    }

    @Test
    public void size()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        Verify.assertSize(0, new BooleanArrayList().asReversed());
        Verify.assertSize(3, iterable);
    }

    @Test
    public void empty()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        assertTrue(iterable.notEmpty());
        Verify.assertNotEmpty(iterable);
    }

    @Test
    public void count()
    {
        assertEquals(2L, BooleanArrayList.newListWith(false, false, true).asReversed().count(BooleanPredicates.equal(false)));
    }

    @Test
    public void anySatisfy()
    {
        assertTrue(BooleanArrayList.newListWith(true, false).asReversed().anySatisfy(BooleanPredicates.equal(false)));
        assertFalse(BooleanArrayList.newListWith(true).asReversed().anySatisfy(BooleanPredicates.equal(false)));
    }

    @Test
    public void allSatisfy()
    {
        assertFalse(BooleanArrayList.newListWith(true, false).asReversed().allSatisfy(BooleanPredicates.equal(false)));
        assertTrue(BooleanArrayList.newListWith(false, false).asReversed().allSatisfy(BooleanPredicates.equal(false)));
    }

    @Test
    public void noneSatisfy()
    {
        assertFalse(BooleanArrayList.newListWith(true, false).asReversed().noneSatisfy(BooleanPredicates.equal(false)));
        assertTrue(BooleanArrayList.newListWith(false, false).asReversed().noneSatisfy(BooleanPredicates.equal(true)));
    }

    @Test
    public void select()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        Verify.assertSize(2, iterable.select(BooleanPredicates.equal(false)));
        Verify.assertSize(1, iterable.select(BooleanPredicates.equal(true)));
    }

    @Test
    public void reject()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        Verify.assertSize(1, iterable.reject(BooleanPredicates.equal(false)));
        Verify.assertSize(2, iterable.reject(BooleanPredicates.equal(true)));
    }

    @Test
    public void detectIfNone()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false).asReversed();
        assertFalse(iterable.detectIfNone(BooleanPredicates.equal(false), true));
        assertTrue(iterable.detectIfNone(BooleanPredicates.equal(true), true));
    }

    @Test
    public void collect()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        Verify.assertIterablesEqual(FastList.newListWith(false, true, true), iterable.collect(parameter -> !parameter));
    }

    @Test
    public void toArray()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        assertTrue(iterable.toArray()[0]);
        assertFalse(iterable.toArray()[1]);
        assertFalse(iterable.toArray()[2]);
    }

    @Test
    public void testToString()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        assertEquals("[true, false, false]", iterable.toString());
        assertEquals("[]", new BooleanArrayList().asReversed().toString());
    }

    @Test
    public void makeString()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        assertEquals("true, false, false", iterable.makeString());
        assertEquals("true", BooleanArrayList.newListWith(true).makeString("/"));
        assertEquals("true/false/false", iterable.makeString("/"));
        assertEquals(iterable.toString(), iterable.makeString("[", ", ", "]"));
        assertEquals("", new BooleanArrayList().asReversed().makeString());
    }

    @Test
    public void appendString()
    {
        BooleanIterable iterable = BooleanArrayList.newListWith(false, false, true).asReversed();
        StringBuilder appendable = new StringBuilder();
        new BooleanArrayList().asReversed().appendString(appendable);
        assertEquals("", appendable.toString());
        StringBuilder appendable2 = new StringBuilder();
        iterable.appendString(appendable2);
        assertEquals("true, false, false", appendable2.toString());
        StringBuilder appendable3 = new StringBuilder();
        iterable.appendString(appendable3, "/");
        assertEquals("true/false/false", appendable3.toString());
        StringBuilder appendable4 = new StringBuilder();
        iterable.appendString(appendable4, "[", ", ", "]");
        assertEquals(iterable.toString(), appendable4.toString());
    }

    @Test
    public void toList()
    {
        assertEquals(BooleanArrayList.newListWith(false, true), BooleanArrayList.newListWith(true, false).asReversed().toList());
    }

    @Test
    public void toSet()
    {
        assertEquals(BooleanHashSet.newSetWith(true, false), BooleanArrayList.newListWith(true, false).asReversed().toSet());
    }

    @Test
    public void toBag()
    {
        assertEquals(BooleanHashBag.newBagWith(true, false), BooleanArrayList.newListWith(true, false).asReversed().toBag());
    }

    @Test
    public void asLazy()
    {
        assertEquals(BooleanArrayList.newListWith(false, true), BooleanArrayList.newListWith(true, false).asReversed().asLazy().toList());
    }
}
