/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.api.multimap.bag;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.Multimap;

public interface BagMultimap<K, V>
        extends Multimap<K, V>
{
    BagMultimap<K, V> newEmpty();

    Bag<V> get(K key);

    BagMultimap<V, K> flip();

    BagMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate);

    BagMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate);

    BagMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    BagMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);
}
