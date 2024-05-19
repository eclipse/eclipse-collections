/*
 * Copyright (c) 2023 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable.primitive;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class BooleanHashSetTest extends AbstractBooleanSetTestCase
{
    @Override
    protected BooleanHashSet classUnderTest()
    {
        return BooleanHashSet.newSetWith(true, false, true);
    }

    @Override
    protected BooleanHashSet newWith(boolean... elements)
    {
        return BooleanHashSet.newSetWith(elements);
    }

    @Test
    public void construction() throws Exception
    {
        Field table = BooleanHashSet.class.getDeclaredField("state");
        table.setAccessible(true);
        assertEquals(0, table.get(new BooleanHashSet()));
    }

    @Test
    public void boxed()
    {
        assertEquals(Sets.mutable.of(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE), this.classUnderTest().boxed());
    }

    @Override
    @Test
    public void newCollection()
    {
        super.newCollection();
        BooleanHashSet set0 = this.newWith();
        BooleanHashSet set1 = this.newWith(false);
        BooleanHashSet set2 = this.newWith(true);
        BooleanHashSet set3 = this.newWith(true, false);

        BooleanHashSet setFromList = BooleanHashSet.newSet(BooleanArrayList.newListWith(true, true, false));
        BooleanHashSet setFromSet0 = BooleanHashSet.newSet(set0);
        BooleanHashSet setFromSet1 = BooleanHashSet.newSet(set1);
        BooleanHashSet setFromSet2 = BooleanHashSet.newSet(set2);
        BooleanHashSet setFromSet3 = BooleanHashSet.newSet(set3);
        assertEquals(set3, setFromList);
        assertEquals(set0, setFromSet0);
        assertEquals(set1, setFromSet1);
        assertEquals(set2, setFromSet2);
        assertEquals(set3, setFromSet3);
    }

    @Override
    @Test
    public void booleanIterator_with_remove()
    {
        super.booleanIterator_with_remove();

        BooleanHashSet falseSet = this.newWith(false);
        MutableBooleanIterator mutableBooleanIterator = falseSet.booleanIterator();
        assertTrue(mutableBooleanIterator.hasNext());
        assertFalse(mutableBooleanIterator.next());
        mutableBooleanIterator.remove();
        Verify.assertEmpty(falseSet);
        assertThrows(NoSuchElementException.class, mutableBooleanIterator::next);
        assertThrows(IllegalStateException.class, mutableBooleanIterator::remove);
        BooleanHashSet trueSet = this.newWith(true);
        mutableBooleanIterator = trueSet.booleanIterator();
        assertTrue(mutableBooleanIterator.hasNext());
        assertTrue(mutableBooleanIterator.next());
        mutableBooleanIterator.remove();
        Verify.assertEmpty(trueSet);
        assertThrows(NoSuchElementException.class, mutableBooleanIterator::next);
        assertThrows(IllegalStateException.class, mutableBooleanIterator::remove);
        MutableBooleanSet emptySet = new BooleanHashSet();
        mutableBooleanIterator = emptySet.booleanIterator();
        assertFalse(mutableBooleanIterator.hasNext());
        Verify.assertEmpty(emptySet);
        assertThrows(NoSuchElementException.class, mutableBooleanIterator::next);
        assertThrows(IllegalStateException.class, mutableBooleanIterator::remove);
    }
}
