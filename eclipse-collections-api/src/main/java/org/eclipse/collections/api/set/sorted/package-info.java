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
 * This package contains interfaces for sorted set API.
 * <p>
 *     A sorted set is an {@link java.lang.Iterable} which contains elements in sorted order. It allows for faster retrievals.
 * <p>
 *   This package contains 3 interfaces:
 * <ul>
 *   <li>
 *      {@link org.eclipse.collections.api.set.sorted.ImmutableSortedSet} - the non-modifiable equivalent interface to {@link org.eclipse.collections.api.set.sorted.MutableSortedSet}.
 *   </li>
 *   <li>
 *      {@link org.eclipse.collections.api.set.sorted.MutableSortedSet} - an implementation of a JCF SortedSet which provides internal iterator methods matching the Smalltalk Collection protocol.
 *   </li>
 *   <li>
 *      {@link org.eclipse.collections.api.set.sorted.SortedSetIterable} - an iterable whose items are unique and sorted.
 *   </li>
 * </ul>
 */
package org.eclipse.collections.api.set.sorted;
