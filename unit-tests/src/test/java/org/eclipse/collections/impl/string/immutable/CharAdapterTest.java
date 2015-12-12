/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.string.immutable;

import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.list.primitive.ImmutableCharList;
import org.eclipse.collections.impl.factory.primitive.CharBags;
import org.eclipse.collections.impl.list.immutable.primitive.AbstractImmutableCharListTestCase;
import org.junit.Assert;
import org.junit.Test;

public class CharAdapterTest extends AbstractImmutableCharListTestCase
{
    private static final String UNICODE_STRING = "\u3042\uD840\uDC00\u3044\uD840\uDC03\u3046\uD83D\uDE09";

    @Override
    protected ImmutableCharList classUnderTest()
    {
        return CharAdapter.from((char) 1, (char) 2, (char) 3);
    }

    @Override
    protected ImmutableCharList newWith(char... elements)
    {
        return CharAdapter.from(elements);
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Test
    public void stringBuilder()
    {
        CharAdapter adapt = CharAdapter.adapt(UNICODE_STRING);
        Assert.assertEquals(UNICODE_STRING, new StringBuilder(adapt).toString());
    }

    @Test
    public void subSequence()
    {
        CharAdapter adapt = CharAdapter.adapt(UNICODE_STRING);
        CharSequence sequence = adapt.subSequence(1, 3);
        Assert.assertEquals(UNICODE_STRING.subSequence(1, 3), sequence);
    }

    @Override
    public void toBag()
    {
        super.toBag();
        MutableCharBag expected = CharBags.mutable.empty();
        expected.addOccurrences('a', 3);
        expected.addOccurrences('b', 3);
        expected.addOccurrences('c', 3);
        Assert.assertEquals(expected, CharAdapter.adapt("aaabbbccc").toBag());
    }

    @Override
    @Test
    public void makeString()
    {
        ImmutableCharList list = this.classUnderTest();
        StringBuilder expectedString = new StringBuilder("");
        StringBuilder expectedString1 = new StringBuilder("");
        int size = list.size();
        for (char each = 0; each < size; each++)
        {
            expectedString.append((char) (each + (char) 1));
            expectedString1.append((char) (each + (char) 1));
            expectedString.append(each == size - 1 ? "" : ", ");
            expectedString1.append(each == size - 1 ? "" : "/");
        }
        Assert.assertEquals(expectedString.toString(), list.makeString());
        Assert.assertEquals(expectedString1.toString(), list.makeString("/"));
    }

    @Override
    @Test
    public void appendString()
    {
        StringBuilder expectedString = new StringBuilder("");
        StringBuilder expectedString1 = new StringBuilder("");
        int size = this.classUnderTest().size();
        for (char each = 0; each < size; each++)
        {
            expectedString.append((char) (each + (char) 1));
            expectedString1.append((char) (each + (char) 1));
            expectedString.append(each == size - 1 ? "" : ", ");
            expectedString1.append(each == size - 1 ? "" : "/");
        }
        ImmutableCharList list = this.classUnderTest();
        StringBuilder appendable2 = new StringBuilder();
        list.appendString(appendable2);
        Assert.assertEquals(expectedString.toString(), appendable2.toString());
        StringBuilder appendable3 = new StringBuilder();
        list.appendString(appendable3, "/");
        Assert.assertEquals(expectedString1.toString(), appendable3.toString());
    }

    @Override
    @Test
    public void testToString()
    {
        StringBuilder expectedString = new StringBuilder();
        int size = this.classUnderTest().size();
        for (char each = 0; each < size; each++)
        {
            expectedString.append((char) (each + (char) 1));
        }
        Assert.assertEquals(expectedString.toString(), this.classUnderTest().toString());
    }
}
