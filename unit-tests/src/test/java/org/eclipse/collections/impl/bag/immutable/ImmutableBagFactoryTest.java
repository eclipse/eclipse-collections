/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.immutable;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImmutableBagFactoryTest
{
    @Test
    public void immutables()
    {
        ImmutableBag<Object> immutableBag = Bags.immutable.of();
        Verify.assertIterableSize(0, immutableBag);
        Verify.assertIterableSize(4, Bags.immutable.of(1, 2, 2, 3));
        ImmutableBag<Object> actual = Bags.immutable.ofAll(immutableBag);
        assertSame(immutableBag, actual);
        assertEquals(immutableBag, actual);
    }

    private static class StringIntPair implements ObjectIntPair<String>
    {
        private final String value;
        private final int counter;

        StringIntPair(String value, int counter)
        {
            this.value = value;
            this.counter = counter;
        }

        @Override
        public String getOne()
        {
            return this.value;
        }

        @Override
        public int getTwo()
        {
            return this.counter;
        }

        @Override
        public int compareTo(ObjectIntPair<String> o)
        {
            int result = this.getOne().compareTo(o.getOne());
            return result == 0 ? Integer.compare(this.getTwo(), o.getTwo()) : result;
        }
    }

    @Test
    public void ofOccurrences()
    {
        ImmutableBag<String> immutableBag =
                Bags.immutable.ofOccurrences(new StringIntPair("a", 5),
                        new StringIntPair("b", 10));

        assertTrue(immutableBag.contains("a"));
        assertTrue(immutableBag.contains("b"));
        assertFalse(immutableBag.contains("c"));
    }

    @Test
    public void withOccurrences()
    {
        ImmutableBag<String> immutableBag =
                Bags.immutable.withOccurrences(new StringIntPair("a", 5),
                        new StringIntPair("b", 10));

        assertTrue(immutableBag.contains("a"));
        assertTrue(immutableBag.contains("b"));
        assertFalse(immutableBag.contains("c"));
    }

    @Test
    public void singletonBagCreation()
    {
        Bag<String> singleton = Bags.immutable.of("a");
        Verify.assertInstanceOf(ImmutableSingletonBag.class, singleton);
    }
}
