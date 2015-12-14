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
 * This package contains interfaces for list API which enhance the performance and functionality of {@link java.util.List}.
 * <p>
 *      This package contains 4 interfaces:
 * <ul>
 *     <li>
 *         {@link org.eclipse.collections.api.list.ListIterable} - an {@link java.lang.Iterable} which contains items that are ordered and may be accessed directly by index.
 *     </li>
 *     <li>
 *         {@link org.eclipse.collections.api.list.MutableList} - an implementation of a JCF List which provides internal iterator methods matching the Smalltalk Collection protocol.
 *     </li>
 *     <li>
 *         {@link org.eclipse.collections.api.list.ImmutableList} - the non-modifiable equivalent interface to {@link org.eclipse.collections.api.list.MutableList}.
 *     </li>
 *     <li>
 *         {@link org.eclipse.collections.api.list.FixedSizeList} - a list that may be mutated, but cannot grow or shrink in size.
 *     </li>
 * </ul>
 */
package org.eclipse.collections.api.list;
