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
 * This package contains implementations of the {@link org.eclipse.collections.api.list.MutableList} interface.
 * <p>
 *     A MutableList is an implementation of a {@link java.util.List} which provides methods matching the Smalltalk Collection protocol.
 * <p>
 *     This package contains the following implementations:
 * <ul>
 *     <li>
 *          {@link org.eclipse.collections.impl.list.mutable.FastList} - an array-backed list which provides optimized internal iterators.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.list.mutable.ArrayListAdapter} - a MutableList wrapper around an {@link java.util.ArrayList} instance.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.list.mutable.CompositeFastList} - behaves like a list, but is composed of one or more lists.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.list.mutable.ListAdapter} - a MutableList wrapper around a {@link java.util.List} interface instance.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.list.mutable.RandomAccessListAdapter} - a MutableList wrapper around a {@link java.util.List} interface instance.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.list.mutable.MultiReaderFastList} - provides a thread-safe wrapper around a FastList, using a {@link java.util.concurrent.locks.ReentrantReadWriteLock}.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.list.mutable.SynchronizedMutableList} - a synchronized view of a list.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.list.mutable.UnmodifiableMutableList} - an unmodifiable view of a list.
 *     </li>
 * </ul>
 * <p>
 *     This package contains one factory implementation:
 * <ul>
 *     <li>
 *         {@link org.eclipse.collections.impl.list.mutable.MutableListFactoryImpl} - a factory which creates instances of type {@link org.eclipse.collections.api.list.MutableList}.
 *     </li>
 * </ul>
 */
package org.eclipse.collections.impl.list.mutable;
