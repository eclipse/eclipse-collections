/*
 * Copyright (c) 2022 Hiroshi Ito and others.
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
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.map.strategy.mutable.UnifiedMapWithHashingStrategy;
import org.eclipse.collections.test.NoIteratorTestCase;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class UnifiedMapWithHashingStrategyNoIteratorTest implements MutableMapTestCase, NoIteratorTestCase
{
    private static final long CURRENT_TIME_MILLIS = System.currentTimeMillis();

    @SafeVarargs
    @Override
    public final <T> MutableMap<Object, T> newWith(T... elements)
    {
        Random random = new Random(CURRENT_TIME_MILLIS);
        MutableMap<Object, T> result = new UnifiedMapWithHashingStrategyNoIterator<>();
        for (T each : elements)
        {
            assertNull(result.put(random.nextDouble(), each));
        }
        return result;
    }

    @Override
    public <K, V> MutableMap<K, V> newWithKeysValues(Object... elements)
    {
        if (elements.length % 2 != 0)
        {
            fail(String.valueOf(elements.length));
        }

        MutableMap<K, V> result = new UnifiedMapWithHashingStrategyNoIterator<>();
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

    @Override
    public void RichIterable_fused_collectMakeString()
    {
        // Not applicable
    }

    @Override
    public void RichIterable_anySatisfy_allSatisfy_noneSatisfy()
    {
        /**
         * TODO: {@link UnifiedMapWithHashingStrategy#anySatisfy(Predicate)} should be optimized to not use an iterator
         */

        /**
         * TODO: {@link UnifiedMapWithHashingStrategy#allSatisfy(Predicate)} should be optimized to not use an iterator
         */

        /**
         * TODO: {@link UnifiedMapWithHashingStrategy#noneSatisfy(Predicate)} should be optimized to not use an iterator
         */
    }

    @Override
    public void MutableMapIterable_updateValue()
    {
        /**
         * TODO: {@link UnifiedMapWithHashingStrategy#KeySet#equals)} should be optimized to not use an iterator
         */

        /**
         * TODO: {@link org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy#addAll(Collection)} should be optimized to not use an iterator when another UnifiedSet is passed in.
         */
    }

    public static class UnifiedMapWithHashingStrategyNoIterator<K, V> extends UnifiedMapWithHashingStrategy<K, V>
    {
        public UnifiedMapWithHashingStrategyNoIterator()
        {
            super(HashingStrategies.nullSafeHashingStrategy(HashingStrategies.defaultStrategy()));
        }

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
