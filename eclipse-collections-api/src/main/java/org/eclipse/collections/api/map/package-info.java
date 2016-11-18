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
 * This package contains interfaces for map API which enhance the performance and functionality of {@link java.util.Map}
 * <p>
 * This package contains the following interfaces:
 * <ul>
 *     <li>
 *         {@link org.eclipse.collections.api.map.MapIterable} - a Read-only Map API, with the minor exception inherited from {@link java.lang.Iterable}.
 *     </li>
 *     <li>
 *         {@link org.eclipse.collections.api.map.MutableMap} - an implementation of a JCF Map which provides methods matching the Smalltalk Collection protocol.
 *     </li>
 *     <li>
 *         {@link org.eclipse.collections.api.map.ImmutableMap} - the non-modifiable equivalent interface to {@link org.eclipse.collections.api.map.MutableMap}.
 *     </li>
 *     <li>
 *         {@link org.eclipse.collections.api.map.FixedSizeMap} - a map that may be mutated, but cannot grow or shrink in size.
 *     </li>
 *     <li>
 *         {@link org.eclipse.collections.api.map.ConcurrentMutableMap} - provides an API which combines and supports both MutableMap and ConcurrentMap.
 *     </li>
 *     <li>
 *         {@link org.eclipse.collections.api.map.UnsortedMapIterable} - a map whose elements are unsorted.
 *     </li>
 * </ul>
 */
package org.eclipse.collections.api.map;
