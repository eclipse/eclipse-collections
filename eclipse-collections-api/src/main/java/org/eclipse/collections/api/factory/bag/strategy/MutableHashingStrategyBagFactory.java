/*
 * Copyright (c) 2016 Bhavana Hindupur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.bag.strategy;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.HashingStrategy;

public interface MutableHashingStrategyBagFactory
{
    /**
     * Same as {@link #with(HashingStrategy)}.
     */
    <T> MutableBag<T> of(HashingStrategy<? super T> hashingStrategy);

    <T> MutableBag<T> empty(HashingStrategy<? super T> hashingStrategy);

    <T> MutableBag<T> with(HashingStrategy<? super T> hashingStrategy);

    /**
     * Same as {@link #with(HashingStrategy, Object[])}.
     */
    <T> MutableBag<T> of(HashingStrategy<? super T> hashingStrategy, T... items);

    <T> MutableBag<T> with(HashingStrategy<? super T> hashingStrategy, T... items);

    /**
     * Same as {@link #withAll(HashingStrategy, Iterable)}.
     */
    <T> MutableBag<T> ofAll(HashingStrategy<? super T> hashingStrategy, Iterable<? extends T> items);

    <T> MutableBag<T> withAll(HashingStrategy<? super T> hashingStrategy, Iterable<? extends T> items);
}
