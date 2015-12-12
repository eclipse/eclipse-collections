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
 * This package contains interfaces for BiMap API.
 * <p>
 *      A BiMap is a map that allows users to look up key-value pairs from either direction. Uniqueness is enforced on both the keys and values.
 * <p>
 *     This package contains 3 interfaces:
 * <ul>
 *     <li>
 *          {@link org.eclipse.collections.api.bimap.BiMap} - contains the common API for Mutable and Immutable BiMap.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.api.bimap.MutableBiMap} - a BiMap whose contents can be altered after initialization.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.api.bimap.ImmutableBiMap} - a BiMap whose contents cannot be altered after initialization.
 *     </li>
 * </ul>
 */
package org.eclipse.collections.api.bimap;
