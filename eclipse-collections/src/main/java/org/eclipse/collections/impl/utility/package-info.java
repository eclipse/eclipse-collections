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
 * This package contains static utilities that provide iteration pattern implementations which work with JCF collections.
 * <p>
 *     This package contains 7 Iteration implementations:
 * <ul>
 *     <li>
 *          {@link org.eclipse.collections.impl.utility.ArrayIterate} - provides iteration pattern implementations that work with {@link java.util.Arrays}.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.utility.ArrayListIterate} - provides optimized iteration pattern implementations that work with {@link java.util.ArrayList}.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.utility.Iterate} - a router to other utility classes to provide optimized iteration pattern.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.utility.LazyIterate} - a factory class which creates "deferred" iterables around the specified iterables.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.utility.ListIterate} - used for iterating over lists.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.utility.MapIterate} - used for iterating over maps.
 *     </li>
 *     <li>
 *          {@link org.eclipse.collections.impl.utility.StringIterate} - implements the methods available on the collection protocol that make sense for Strings.
 *     </li>
 * </ul>
 */
package org.eclipse.collections.impl.utility;
