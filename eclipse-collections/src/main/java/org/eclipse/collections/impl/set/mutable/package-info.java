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
 * This package package contains implementations of {@link org.eclipse.collections.api.set.MutableSet}.
 * <p>
 *     This package contains the following mutable set implementations:
 * <ul>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.mutable.MultiReaderUnifiedSet} -  a thread safe wrapper around UnifiedSet.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.mutable.SetAdapter} - a MutableSet wrapper around a JDK Collections Set interface instance.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.mutable.SynchronizedMutableSet} - a synchronized view of a set.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.mutable.UnifiedSet} - an implementation of a JCF Set which provides methods matching the Smalltalk Collection protocol.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.mutable.UnmodifiableMutableSet} - an unmodifiable view of a set.
 *     </li>
 * </ul>
 * <p>
 *     This package contains 1 factory implementation:
 * <ul>
 *     <li>
 *          {@link org.eclipse.collections.impl.set.mutable.MutableSetFactoryImpl} - a factory which creates instances of type {@link org.eclipse.collections.api.set.MutableSet}.
 *     </li>
 * </ul>
 */
package org.eclipse.collections.impl.set.mutable;
