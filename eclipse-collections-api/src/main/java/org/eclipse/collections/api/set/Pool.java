/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.set;

public interface Pool<V>
{
    /**
     * Locates an object in the pool which is equal to {@code key}.
     *
     * @param key the value to look for
     * @return The object reference in the pool equal to key or null.
     */
    V get(V key);

    void clear();

    /**
     * Puts {@code key} into the pool. If there is no existing object that is equal
     * to key, key will be added to the pool and the return value will be the same instance.
     * If there is an existing object in the pool that is equal to {@code key}, the pool will remain unchanged
     * and the pooled instance will be is returned.
     *
     * @param key the value to add if not in the pool
     * @return the object reference in the pool equal to key (either key itself or the existing reference)
     */
    V put(V key);

    int size();

    /**
     * Locates an object in the pool which is equal to {@code key} and removes it.
     *
     * @param key object to remove
     * @return The object reference in the pool equal to key or null.
     */
    V removeFromPool(V key);
}
