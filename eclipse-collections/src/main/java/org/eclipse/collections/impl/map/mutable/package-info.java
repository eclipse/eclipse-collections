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
 * This package contains implementations of the {@link org.eclipse.collections.api.map.MutableMap} interface.
 * <p>
 *     A MutableMap is an implementation of a {@link java.util.Map} which provides internal iterator methods matching the Smalltalk Collection protocol.
 * <p>
 *     This package contains the following implementations:
 * <ul>
 *     <li>
 *          {@link org.eclipse.collections.impl.map.mutable.MapAdapter} - a MutableMap wrapper around a {@link java.util.Map} interface instance.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.map.mutable.UnifiedMap} - a map which uses a hashtable as its underlying data store and stores key/value pairs in consecutive locations in a single array.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.map.mutable.SynchronizedMutableMap} - a synchronized view of a map.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.map.mutable.UnmodifiableMutableMap} - an unmodifiable view of a map.
 *     </li>
 * </ul>
 * <p>
 *     This package contains one factory implementation:
 * <ul>
 *     <li>
 *         {@link org.eclipse.collections.impl.map.mutable.MutableMapFactoryImpl} - a factory which creates instances of type {@link org.eclipse.collections.api.map.MutableMap}.
 *     </li>
 * </ul>
 */
package org.eclipse.collections.impl.map.mutable;
