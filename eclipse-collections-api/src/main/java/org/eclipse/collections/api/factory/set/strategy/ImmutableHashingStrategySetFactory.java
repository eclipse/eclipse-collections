/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.set.strategy;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.set.ImmutableSet;

public interface ImmutableHashingStrategySetFactory
{
    /**
     * Same as {@link #with(HashingStrategy)}.
     */
    <T> ImmutableSet<T> of(HashingStrategy<? super T> hashingStrategy);

    <T> ImmutableSet<T> with(HashingStrategy<? super T> hashingStrategy);

    /**
     * Same as {@link #with(HashingStrategy, Object[])}.
     */
    <T> ImmutableSet<T> of(HashingStrategy<? super T> hashingStrategy, T... items);

    <T> ImmutableSet<T> with(HashingStrategy<? super T> hashingStrategy, T... items);

    /**
     * Same as {@link #withAll(HashingStrategy, Iterable)}.
     */
    <T> ImmutableSet<T> ofAll(HashingStrategy<? super T> hashingStrategy, Iterable<? extends T> items);

    <T> ImmutableSet<T> withAll(HashingStrategy<? super T> hashingStrategy, Iterable<? extends T> items);
}
