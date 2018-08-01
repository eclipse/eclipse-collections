/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory;

import org.eclipse.collections.api.factory.set.FixedSizeSetFactory;
import org.eclipse.collections.api.factory.set.ImmutableSetFactory;
import org.eclipse.collections.api.factory.set.MutableSetFactory;

/**
 * This class should be used to create instances of MutableSet, ImmutableSet and FixedSizeSet
 * <p>
 * Mutable Examples:
 *
 * <pre>
 * MutableSet&lt;String&gt; emptySet = Sets.mutable.empty();
 * MutableSet&lt;String&gt; setWith = Sets.mutable.with("a", "b", "c");
 * MutableSet&lt;String&gt; setOf = Sets.mutable.of("a", "b", "c");
 * </pre>
 *
 * Immutable Examples:
 *
 * <pre>
 * ImmutableSet&lt;String&gt; emptySet = Sets.immutable.empty();
 * ImmutableSet&lt;String&gt; setWith = Sets.immutable.with("a", "b", "c");
 * ImmutableSet&lt;String&gt; setOf = Sets.immutable.of("a", "b", "c");
 * </pre>
 *
 * FixedSize Examples:
 *
 * <pre>
 * FixedSizeSet&lt;String&gt; emptySet = Sets.fixedSize.empty();
 * FixedSizeSet&lt;String&gt; setWith = Sets.fixedSize.with("a", "b", "c");
 * FixedSizeSet&lt;String&gt; setOf = Sets.fixedSize.of("a", "b", "c");
 * </pre>
 */
@SuppressWarnings("ConstantNamingConvention")
public final class Sets
{
    public static final ImmutableSetFactory immutable =
            ServiceLoaderUtils.loadServiceClass(ImmutableSetFactory.class);
    public static final MutableSetFactory mutable =
            ServiceLoaderUtils.loadServiceClass(MutableSetFactory.class);
    public static final FixedSizeSetFactory fixedSize =
            ServiceLoaderUtils.loadServiceClass(FixedSizeSetFactory.class);

    private Sets()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
