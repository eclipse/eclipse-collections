/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

/**
 * This package contains implementations of the {@link org.eclipse.collections.api.bag.MutableBag} interface.
 * <p>
 *     A MutableBag is a {@link java.util.Collection} which contains elements that are unordered and may contain duplicate entries. It adds a protocol for
 * adding, removing, and determining the number of occurrences for an item.
 * <p>
 *     This package contains 3 bag implementations:
 * <ul>
 *     <li>
 *          {@link org.eclipse.collections.impl.bag.mutable.HashBag} - a {@link org.eclipse.collections.api.bag.MutableBag} which uses a hashtable as its underlying data store.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.bag.mutable.SynchronizedBag} - a synchronized view of a bag.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.bag.mutable.UnmodifiableBag} - an unmodifiable view of a bag.
 *     </li>
 * </ul>
 * <p>
 *     This package contains one factory implementation:
 * <ul>
 *     <li>
 *         {@link org.eclipse.collections.impl.bag.mutable.MutableBagFactoryImpl} - a factory which creates instances of type {@link org.eclipse.collections.api.bag.MutableBag}.
 *     </li>
 * </ul>
 */
package org.eclipse.collections.impl.bag.mutable;
