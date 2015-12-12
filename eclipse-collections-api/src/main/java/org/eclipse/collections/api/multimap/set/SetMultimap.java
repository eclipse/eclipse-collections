/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.multimap.set;

import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.set.SetIterable;

public interface SetMultimap<K, V>
        extends Multimap<K, V>
{
    SetMultimap<K, V> newEmpty();

    SetIterable<V> get(K key);

    SetMultimap<V, K> flip();

    SetMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate);

    SetMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate);

    SetMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    SetMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);
}
