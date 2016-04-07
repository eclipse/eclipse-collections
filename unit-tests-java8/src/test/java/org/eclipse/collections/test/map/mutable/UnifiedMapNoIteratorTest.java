/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map.mutable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.eclipse.collections.test.NoIteratorTestCase;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(Java8Runner.class)
public class UnifiedMapNoIteratorTest implements MutableMapTestCase, NoIteratorTestCase
{
    private static final long CURRENT_TIME_MILLIS = System.currentTimeMillis();

    @SafeVarargs
    @Override
    public final <T> MutableMap<Object, T> newWith(T... elements)
    {
        Random random = new Random(CURRENT_TIME_MILLIS);
        MutableMap<Object, T> result = new UnifiedMapNoIterator<>();
        for (T each : elements)
        {
            Assert.assertNull(result.put(random.nextDouble(), each));
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

    @Override
    public void RichIterable_getFirst_and_getLast()
    {
        // Not applicable
    }

    @Override
    public void RichIterable_getLast_empty_null()
    {
        // Not applicable
    }

    public static class UnifiedMapNoIterator<K, V> extends UnifiedMap<K, V>
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
