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
 * This package contains interfaces for set API which enhance the performance and functionality of {@link java.util.Set}.
 * <p>
 *      This package contains 6 interfaces:
 * <ul>
 *   <li>
 *       {@link org.eclipse.collections.api.set.FixedSizeSet} - a set that may be mutated, but cannot grow or shrink in size.
 *   </li>
 *   <li>
 *       {@link org.eclipse.collections.api.set.ImmutableSet} - the non-modifiable equivalent interface to {@link org.eclipse.collections.api.set.MutableSet}.
 *   </li>
 *   <li>
 *       {@link org.eclipse.collections.api.set.MutableSet} - an implementation of a JCF Set which provides internal iterator methods matching the Smalltalk Collection protocol.
 *   </li>
 *   <li>
 *       {@link org.eclipse.collections.api.set.Pool} - locates an object in the pool which is equal to {@code key}.
 *   </li>
 *   <li>
 *       {@link org.eclipse.collections.api.set.SetIterable} - a read-only Set API, with the minor exception inherited from {@link java.lang.Iterable} (iterable.iterator().remove()).
 *   </li>
 *   <li>
 *       {@link org.eclipse.collections.api.set.UnsortedSetIterable} - an iterable whose items are unique.
 *   </li>
 * </ul>
 */
package org.eclipse.collections.api.set;
