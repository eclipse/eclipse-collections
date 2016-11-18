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
 * This package contains implementations of the {@link org.eclipse.collections.api.multimap.set.SetMultimap} interface.
 * <p>
 *     A {@link org.eclipse.collections.api.multimap.set.SetMultimap} is a type of {@code Multimap} that uses a set as its underlying store for the multiple values of a given key.
 * <p>
 *     This package contains the following implementations:
 * <ul>
 *     <li>
 *          {@link org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap} - a {@code MutableSetMultimap} which uses a {@link org.eclipse.collections.impl.set.mutable.UnifiedSet} as its underlying store for the multiple values of a given key.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.multimap.set.ImmutableSetMultimapImpl} - the default {@code ImmutableSetMultimap} implementation.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.multimap.set.SynchronizedPutUnifiedSetMultimap} - a {@code MutableSetMultimap} that is optimized for parallel writes, but is not protected for concurrent reads.
 *     </li>
 */
package org.eclipse.collections.impl.multimap.set;
