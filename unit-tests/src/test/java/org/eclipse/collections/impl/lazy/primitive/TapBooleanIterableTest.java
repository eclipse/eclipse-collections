/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.primitive;

import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.list.primitive.BooleanList;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.math.MutableInteger;
import org.junit.Assert;
import org.junit.Test;

public class TapBooleanIterableTest
{
    private final BooleanList list = BooleanLists.immutable.with(true, false, false, true);

    @Test
    public void booleanIterator()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        for (BooleanIterator iterator = iterable.booleanIterator(); iterator.hasNext(); )
        {
            iterator.next();
        }
        Assert.assertEquals("truefalsefalsetrue", concat.toString());
    }

    @Test
    public void forEach()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        iterable.forEach(each -> { });
        Assert.assertEquals("truefalsefalsetrue", concat.toString());
    }

    @Test
    public void injectInto()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        MutableInteger result = iterable.injectInto(new MutableInteger(0), (object, value) -> object.add(value ? 1 : 0));
        Assert.assertEquals(new MutableInteger(2), result);
        Assert.assertEquals("truefalsefalsetrue", concat.toString());
    }

    @Test
    public void size()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        Assert.assertEquals(4L, iterable.size());
        Assert.assertEquals("truefalsefalsetrue", concat.toString());
    }

    @Test
    public void empty()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        Assert.assertTrue(iterable.notEmpty());
        Assert.assertFalse(iterable.isEmpty());
    }

    @Test
    public void count()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        Assert.assertEquals(2L, iterable.count(BooleanPredicates.isTrue()));
        Assert.assertEquals(2L, iterable.count(BooleanPredicates.isFalse()));
        Assert.assertEquals("truefalsefalsetruetruefalsefalsetrue", concat.toString());
    }

    @Test
    public void anySatisfy()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        Assert.assertTrue(iterable.anySatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(iterable.anySatisfy(BooleanPredicates.isFalse()));
    }

    @Test
    public void allSatisfy()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        Assert.assertFalse(iterable.allSatisfy(BooleanPredicates.isTrue()));
        Assert.assertFalse(iterable.allSatisfy(BooleanPredicates.isFalse()));
    }

    @Test
    public void select()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        Assert.assertEquals(2L, iterable.select(BooleanPredicates.isFalse()).size());
        Assert.assertEquals(2L, iterable.select(BooleanPredicates.equal(true)).size());
        Assert.assertEquals("truefalsefalsetruetruefalsefalsetrue", concat.toString());
    }

    @Test
    public void reject()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        Assert.assertEquals(2L, iterable.reject(BooleanPredicates.isFalse()).size());
        Assert.assertEquals(2L, iterable.reject(BooleanPredicates.equal(true)).size());
        Assert.assertEquals("truefalsefalsetruetruefalsefalsetrue", concat.toString());
    }

    @Test
    public void detectIfNone()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        Assert.assertTrue(iterable.detectIfNone(BooleanPredicates.isTrue(), false));
        Assert.assertFalse(iterable.detectIfNone(BooleanPredicates.isFalse(), false));
    }

    @Test
    public void collect()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        Assert.assertEquals(4L, iterable.collect(String::valueOf).size());
        Assert.assertEquals("truefalsefalsetrue", concat.toString());
    }

    @Test
    public void toArray()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        Assert.assertEquals(4L, iterable.toArray().length);
        Assert.assertTrue(iterable.toArray()[0]);
        Assert.assertFalse(iterable.toArray()[1]);
    }

    @Test
    public void contains()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        Assert.assertTrue(iterable.contains(true));
        Assert.assertTrue(iterable.contains(false));
    }

    @Test
    public void containsAll()
    {
        StringBuilder concat = new StringBuilder();
        TapBooleanIterable iterable = new TapBooleanIterable(this.list, concat::append);

        Assert.assertTrue(iterable.containsAll(true, true));
        Assert.assertTrue(iterable.containsAll(false, true));
        Assert.assertTrue(iterable.containsAll(false, false));
    }
}
