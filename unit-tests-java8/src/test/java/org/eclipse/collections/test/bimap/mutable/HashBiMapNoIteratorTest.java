/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bimap.mutable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.impl.bimap.mutable.HashBiMap;
import org.eclipse.collections.test.NoIteratorTestCase;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class HashBiMapNoIteratorTest implements MutableBiMapTestCase, NoIteratorTestCase
{
    private static final long CURRENT_TIME_MILLIS = System.currentTimeMillis();

    @Override
    public <T> MutableBiMap<Object, T> newWith(T... elements)
    {
        Random random = new Random(CURRENT_TIME_MILLIS);
        MutableBiMap<Object, T> result = new HashBiMapNoIterator<>();
        for (T each : elements)
        {
            try
            {
                assertNull(result.put(random.nextDouble(), each));
            }
            catch (IllegalArgumentException e)
            {
                throw new IllegalStateException(e);
            }
        }
        return result;
    }

    @Override
    public <K, V> MutableBiMap<K, V> newWithKeysValues(Object... elements)
    {
        if (elements.length % 2 != 0)
        {
            fail(String.valueOf(elements.length));
        }

        MutableBiMap<K, V> result = new HashBiMapNoIterator<>();
        for (int i = 0; i < elements.length; i += 2)
        {
            assertNull(result.put((K) elements[i], (V) elements[i + 1]));
        }
        return result;
    }

    @Override
    public void Iterable_next()
    {
        NoIteratorTestCase.super.Iterable_next();
    }

    @Override
    public void Iterable_remove()
    {
        NoIteratorTestCase.super.Iterable_remove();
    }

    @Override
    public void RichIterable_getFirst()
    {
        NoIteratorTestCase.super.RichIterable_getFirst();
    }

    @Override
    public void RichIterable_getLast()
    {
        NoIteratorTestCase.super.RichIterable_getLast();
    }

    public static class HashBiMapNoIterator<K, V> extends HashBiMap<K, V>
    {
        @Override
        public Iterator<V> iterator()
        {
            throw new AssertionError("No iteration patterns should delegate to iterator()");
        }

        @Override
        public Set<Entry<K, V>> entrySet()
        {
            return new EntrySetNoIterator();
        }

        @Override
        public Set<K> keySet()
        {
            return new KeySetNoIterator();
        }

        @Override
        public Collection<V> values()
        {
            return new ValuesCollectionNoIterator();
        }

        public class EntrySetNoIterator extends EntrySet
        {
            @Override
            public Iterator<Entry<K, V>> iterator()
            {
                throw new AssertionError("No iteration patterns should delegate to iterator()");
            }
        }

        public class KeySetNoIterator extends KeySet
        {
            @Override
            public Iterator<K> iterator()
            {
                throw new AssertionError("No iteration patterns should delegate to iterator()");
            }
        }

        public class ValuesCollectionNoIterator extends ValuesCollection
        {
            @Override
            public Iterator<V> iterator()
            {
                throw new AssertionError("No iteration patterns should delegate to iterator()");
            }
        }
    }
}
