/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable.primitive;

import java.util.NoSuchElementException;

import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.impl.factory.primitive.BooleanSets;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link BooleanHashBag}.
 */
public class BooleanHashBagTest extends AbstractMutableBooleanBagTestCase
{
    @Override
    protected BooleanHashBag classUnderTest()
    {
        return BooleanHashBag.newBagWith(true, false, true);
    }

    @Override
    protected BooleanHashBag newWith(boolean... elements)
    {
        return BooleanHashBag.newBagWith(elements);
    }

    @Override
    @Test
    public void newCollection()
    {
        super.newCollection();
        assertEquals(
                BooleanHashBag.newBagWith(true, false, true, false, true),
                BooleanHashBag.newBag(BooleanArrayList.newListWith(true, false, true, false, true)));
    }

    @Override
    @Test
    public void size()
    {
        super.size();
        Verify.assertSize(3, BooleanHashBag.newBagWith(true, false, true));
        Verify.assertSize(3, new BooleanHashBag(BooleanHashBag.newBagWith(true, false, true)));
        Verify.assertSize(3, new BooleanHashBag(BooleanArrayList.newListWith(true, false, true)));
    }

    @Override
    @Test
    public void with()
    {
        super.with();
        BooleanHashBag hashBag = new BooleanHashBag().with(true);
        BooleanHashBag emptyBag = new BooleanHashBag();
        BooleanHashBag hashBag0 = emptyBag.with(true, false);
        BooleanHashBag hashBag1 = new BooleanHashBag().with(true, false, true);
        BooleanHashBag hashBag2 = new BooleanHashBag().with(true).with(false).with(true).with(false);
        BooleanHashBag hashBag3 = new BooleanHashBag().with(true).with(false).with(true).with(false).with(true);
        assertSame(emptyBag, hashBag0);
        assertEquals(BooleanHashBag.newBagWith(true), hashBag);
        assertEquals(BooleanHashBag.newBagWith(true, false), hashBag0);
        assertEquals(BooleanHashBag.newBagWith(true, false, true), hashBag1);
        assertEquals(BooleanHashBag.newBagWith(true, false, true, false), hashBag2);
        assertEquals(BooleanHashBag.newBagWith(true, false, true, false, true), hashBag3);
    }

    @Override
    @Test
    public void booleanIterator()
    {
        super.booleanIterator();
        BooleanHashBag bag = this.newWith(true, false, false, true, true, true);
        BooleanIterator iterator = bag.booleanIterator();
        assertTrue(iterator.hasNext());
        assertFalse(iterator.next());
        assertTrue(iterator.hasNext());
        assertFalse(iterator.next());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next());
        assertFalse(iterator.hasNext());

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Override
    @Test
    public void appendString()
    {
        super.appendString();
        StringBuilder appendable2 = new StringBuilder();
        BooleanHashBag bag1 = this.newWith(false, false, true);
        bag1.appendString(appendable2);
        assertEquals(appendable2.toString(), "false, false, true", appendable2.toString());
    }

    @Override
    @Test
    public void toList()
    {
        super.toList();
        MutableBooleanList list = this.newWith(true, true, true, false).toList();
        assertEquals(list, BooleanArrayList.newListWith(false, true, true, true));
    }

    @Override
    @Test
    public void selectUnique()
    {
        super.selectUnique();

        MutableBooleanBag bag = this.classUnderTest();
        MutableBooleanSet expected = BooleanSets.mutable.with(false);
        MutableBooleanSet actual = bag.selectUnique();
        assertEquals(expected, actual);
    }
}
