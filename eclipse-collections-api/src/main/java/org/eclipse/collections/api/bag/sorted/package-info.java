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
 * This package contains interfaces for SortedBag API.
 * <p>
 *     A sorted bag is a {@link java.util.Collection} which contains elements that are sorted, and may contain duplicate entries. It adds a protocol for
 * adding, removing, and determining the number of occurrences for an item.
 * <p>
 *     This package contains 3 interfaces:
 * <ul>
 *     <li>
 *          {@link org.eclipse.collections.api.bag.sorted.SortedBag} - contains the common API for Mutable and Immutable SortedBag.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.api.bag.sorted.MutableSortedBag} - a SortedBag whose contents can be altered after initialization.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.api.bag.sorted.ImmutableSortedBag} - a SortedBag whose contents cannot be altered after initialization.
 *     </li>
 * </ul>
 */
package org.eclipse.collections.api.bag.sorted;
