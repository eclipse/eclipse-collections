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
 * This package package contains the implementations of {@link org.eclipse.collections.api.set.ImmutableSet}.
 * <p>
 *     This package contains the following immutable set implementations:
 * <ul>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.immutable.ImmutableEmptySet} - an immutable set with 0 elements.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.immutable.ImmutableSingletonSet} - an immutable set with 1 element.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.immutable.ImmutableDoubletonSet} - an immutable set with 2 elements.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.immutable.ImmutableTripletonSet} - an immutable set with 3 elements.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.immutable.ImmutableQuadrupletonSet} - an immutable set with 4 elements.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.immutable.ImmutableUnifiedSet} - the non-modifiable equivalent of {@link org.eclipse.collections.impl.set.mutable.UnifiedSet}.
 *     </li>
 * </ul>
 * <p>
 *     This package contains one factory implementation:
 * <ul>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.immutable.ImmutableSetFactoryImpl} - a factory which creates instances of type {@link org.eclipse.collections.api.set.ImmutableSet}.
 *     </li>
 * </ul>
 */
package org.eclipse.collections.impl.set.immutable;
