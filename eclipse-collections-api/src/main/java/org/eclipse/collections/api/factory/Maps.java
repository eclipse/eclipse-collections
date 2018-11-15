/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory;

import org.eclipse.collections.api.factory.map.FixedSizeMapFactory;
import org.eclipse.collections.api.factory.map.ImmutableMapFactory;
import org.eclipse.collections.api.factory.map.MutableMapFactory;

/**
 * This class should be used to create instances of MutableMap, ImmutableMap and FixedSizeMap
 * <p>
 * Mutable Examples:
 *
 * <pre>
 * MutableMap&lt;String, String&gt; emptyMap = Maps.mutable.empty();
 * MutableMap&lt;String, String&gt; mapWith = Maps.mutable.with("a", "A", "b", "B", "c", "C");
 * MutableMap&lt;String, String&gt; mapOf = Maps.mutable.of("a", "A", "b", "B", "c", "C");
 * </pre>
 *
 * Immutable Examples:
 *
 * <pre>
 * ImmutableMap&lt;String, String&gt; emptyMap = Maps.immutable.empty();
 * ImmutableMap&lt;String, String&gt; mapWith = Maps.immutable.with("a", "A", "b", "B", "c", "C");
 * ImmutableMap&lt;String, String&gt; mapOf = Maps.immutable.of("a", "A", "b", "B", "c", "C");
 * </pre>
 *
 * FixedSize Examples:
 *
 * <pre>
 * FixedSizeMap&lt;String, String&gt; emptyMap = Maps.fixedSize.empty();
 * FixedSizeMap&lt;String, String&gt; mapWith = Maps.fixedSize.with("a", "A", "b", "B", "c", "C");
 * FixedSizeMap&lt;String, String&gt; mapOf = Maps.fixedSize.of("a", "A", "b", "B", "c", "C");
 * </pre>
 */
@SuppressWarnings("ConstantNamingConvention")
public final class Maps
{
    public static final ImmutableMapFactory immutable = ServiceLoaderUtils.loadServiceClass(ImmutableMapFactory.class);
    public static final FixedSizeMapFactory fixedSize = ServiceLoaderUtils.loadServiceClass(FixedSizeMapFactory.class);
    public static final MutableMapFactory mutable = ServiceLoaderUtils.loadServiceClass(MutableMapFactory.class);

    private Maps()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
