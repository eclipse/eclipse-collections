/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.map.builder;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;

/**
 * MapBuilders are used to conveniently construct maps with more than 4 key-value pair entries during declaration.
 * For maps with less than 5 entries, use Maps.[im]mutable.of()/with or Maps.[im]mutable.of()/with().
 * Each builder is safe to reuse. MapBuilders maintains their own state even after calling build() or buildImmutable().
 * Repeated calls of build() or buildMutable() return new copies of appropriately hydrated maps each time.
 */
public interface MapBuilder<K, V>
{
    /**
     * A chained map.put(Key, Value). Subsequent calls with the same key will overwrite the previous entry.
     *
     * @return a reusable builder to chain additional entries into or construct the target map with build()
     */
    MapBuilder<K, V> and(K key, V value);

    /**
     * Builds an ImmutableMap by default as the builder pattern is synonymous with creating immutable objects.
     *
     * @return an ImmutableMap with entries provided to this builder.
     */
    ImmutableMap<K, V> build();

    /**
     * Returns a MutableMap allowing future mutation. Effectively the same as calling build().toMap() but with explicit
     * naming.
     *
     * @return an Mutable copy of the desired map with entries passed to this builder.
     */
    MutableMap<K, V> buildMutable();
}
