/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.immutable.primitive;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.ImmutableBooleanBag;
import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.block.function.primitive.BooleanToObjectFunction;
import org.eclipse.collections.api.collection.primitive.ImmutableBooleanCollection;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.set.primitive.ImmutableBooleanSet;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.collection.immutable.primitive.AbstractImmutableBooleanCollectionTestCase;
import org.eclipse.collections.impl.factory.primitive.BooleanBags;
import org.eclipse.collections.impl.factory.primitive.BooleanSets;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Abstract JUnit test for {@link ImmutableBooleanBag}.
 */
public abstract class AbstractImmutableBooleanBagTestCase extends AbstractImmutableBooleanCollectionTestCase
{
    @Override
    protected abstract ImmutableBooleanBag classUnderTest();

    @Override
    protected ImmutableBooleanBag newWith(boolean... elements)
    {
        return BooleanBags.immutable.with(elements);
    }

    @Override
    protected MutableBooleanBag newMutableCollectionWith(boolean... elements)
    {
        return BooleanHashBag.newBagWith(elements);
    }

    @Override
    protected MutableBag<Object> newObjectCollectionWith(Object... elements)
    {
        return HashBag.newBagWith(elements);
    }

    @Test
    public void sizeDistinct()
    {
        assertEquals(0L, this.newWith().sizeDistinct());
        assertEquals(1L, this.newWith(true).sizeDistinct());
        assertEquals(1L, this.newWith(true, true, true).sizeDistinct());
        assertEquals(2L, this.newWith(true, false, true, false, true).sizeDistinct());
    }

    @Test
    public void forEachWithOccurrences()
    {
        StringBuilder stringBuilder = new StringBuilder();
        this.classUnderTest().forEachWithOccurrences((argument1, argument2) -> stringBuilder.append(argument1).append(argument2));
        String string = stringBuilder.toString();
        assertTrue("true2false1".equals(string)
                || "false1true2".equals(string));
    }

    @Override
    @Test
    public void size()
    {
        super.size();
        Verify.assertSize(3, this.classUnderTest());
    }

    @Override
    @Test
    public void booleanIterator()
    {
        BooleanHashBag bag = BooleanHashBag.newBagWith();
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        for (int i = 0; i < this.classUnderTest().size(); i++)
        {
            assertTrue(iterator.hasNext());
            bag.add(iterator.next());
        }
        assertEquals(bag, this.classUnderTest());
        assertFalse(iterator.hasNext());
    }

    @Override
    @Test
    public void anySatisfy()
    {
        super.anySatisfy();
        long[] count = {0};
        ImmutableBooleanBag bag = this.newWith(false, true, false);
        assertTrue(bag.anySatisfy(value -> {
            count[0]++;
            return value;
        }));
        assertEquals(2L, count[0]);
    }

    @Override
    @Test
    public void allSatisfy()
    {
        super.allSatisfy();
        int[] count = {0};
        ImmutableBooleanBag bag = this.newWith(false, true, false);
        assertFalse(bag.allSatisfy(value -> {
            count[0]++;
            return !value;
        }));
        assertEquals(2L, count[0]);
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        super.noneSatisfy();
        ImmutableBooleanBag bag = this.newWith(false, true, false);
        assertFalse(bag.noneSatisfy(value -> value));
    }

    @Override
    @Test
    public void collect()
    {
        super.collect();
        ImmutableBooleanBag bag = this.newWith(true, false, false, true, true, true);
        BooleanToObjectFunction<String> stringValueOf = parameter -> parameter ? "true" : "false";
        assertEquals(HashBag.newBagWith("true", "false", "false", "true", "true", "true"), bag.collect(stringValueOf));
        ImmutableBooleanBag bag1 = this.newWith(false, false);
        assertEquals(HashBag.newBagWith("false", "false"), bag1.collect(stringValueOf));
        ImmutableBooleanBag bag2 = this.newWith(true, true);
        assertEquals(HashBag.newBagWith("true", "true"), bag2.collect(stringValueOf));
    }

    @Override
    @Test
    public void testEquals()
    {
        super.testEquals();
        ImmutableBooleanCollection collection1 = this.newWith(true, false, true, false);
        ImmutableBooleanCollection collection2 = this.newWith(true, false, false, true);
        ImmutableBooleanCollection collection3 = this.newWith(true, false);
        ImmutableBooleanCollection collection4 = this.newWith(true, true, false);
        assertEquals(collection1, collection2);
        Verify.assertPostSerializedIdentity(this.newWith());
        assertNotEquals(collection3, collection4);
        assertNotEquals(collection3, BooleanArrayList.newListWith(true, false));
        assertNotEquals(this.newWith(true), BooleanArrayList.newListWith(true));
        assertNotEquals(this.newWith(), BooleanArrayList.newListWith());
    }

    @Override
    @Test
    public void testHashCode()
    {
        super.testHashCode();
        ImmutableBooleanCollection collection1 = this.newWith(true, false, true, false);
        ImmutableBooleanCollection collection2 = this.newWith(true, false, false, true);
        ImmutableBooleanCollection collection3 = this.newWith(true, false);
        ImmutableBooleanCollection collection4 = this.newWith(true, true, false);
        Verify.assertEqualsAndHashCode(collection1, collection2);
        assertNotEquals(collection3.hashCode(), collection4.hashCode());
    }

    @Override
    @Test
    public void testToString()
    {
        super.testToString();
        assertEquals("[true, true, true]", BooleanHashBag.newBagWith(true, true, true).toString());
    }

    @Override
    @Test
    public void makeString()
    {
        super.makeString();
        assertEquals("true, true, true", BooleanHashBag.newBagWith(true, true, true).makeString());
    }

    @Override
    @Test
    public void appendString()
    {
        super.appendString();
        StringBuilder appendable1 = new StringBuilder();
        this.newWith(true, true, true).appendString(appendable1);
        assertEquals("true, true, true", appendable1.toString());

        StringBuilder appendable2 = new StringBuilder();
        ImmutableBooleanBag bag1 = this.newWith(false, false, true);
        bag1.appendString(appendable2);
        assertTrue("false, false, true".equals(appendable2.toString())
                || "true, false, false".equals(appendable2.toString())
                || "false, true, false".equals(appendable2.toString()),
                appendable2.toString());
    }

    @Override
    @Test
    public void toList()
    {
        super.toList();
        MutableBooleanList list = this.newWith(false, false, true).toList();
        assertTrue(list.equals(BooleanArrayList.newListWith(false, false, true))
                || list.equals(BooleanArrayList.newListWith(true, false, false))
                || list.equals(BooleanArrayList.newListWith(false, true, false)));
    }

    @Test
    public void toImmutable()
    {
        assertEquals(this.classUnderTest(), this.classUnderTest().toImmutable());
        ImmutableBooleanBag expected = this.classUnderTest();
        assertSame(expected, expected.toImmutable());
    }

    @Test
    public void selectUnique()
    {
        ImmutableBooleanBag bag = BooleanBags.immutable.with(false, false, true);
        ImmutableBooleanSet expected = BooleanSets.immutable.with(true);
        ImmutableBooleanSet actual = bag.selectUnique();
        assertEquals(expected, actual);

        ImmutableBooleanBag bag2 = BooleanBags.immutable.with(false, false, true, true);
        ImmutableBooleanSet expected2 = BooleanSets.immutable.empty();
        ImmutableBooleanSet actual2 = bag2.selectUnique();
        assertEquals(expected2, actual2);
    }
}
