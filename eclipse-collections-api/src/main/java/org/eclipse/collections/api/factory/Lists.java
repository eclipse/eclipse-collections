/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory;

import org.eclipse.collections.api.factory.list.FixedSizeListFactory;
import org.eclipse.collections.api.factory.list.ImmutableListFactory;
import org.eclipse.collections.api.factory.list.MutableListFactory;

/**
 * This class should be used to create instances of MutableList, ImmutableList and FixedSizeList
 * <p>
 * Mutable Examples:
 *
 * <pre>
 * MutableList&lt;String&gt; emptyList = Lists.mutable.empty();
 * MutableList&lt;String&gt; listWith = Lists.mutable.with("a", "b", "c");
 * MutableList&lt;String&gt; listOf = Lists.mutable.of("a", "b", "c");
 * </pre>
 *
 * Immutable Examples:
 *
 * <pre>
 * ImmutableList&lt;String&gt; emptyList = Lists.immutable.empty();
 * ImmutableList&lt;String&gt; listWith = Lists.immutable.with("a", "b", "c");
 * ImmutableList&lt;String&gt; listOf = Lists.immutable.of("a", "b", "c");
 * </pre>
 *
 * FixedSize Examples:
 *
 * <pre>
 * FixedSizeList&lt;String&gt; emptyList = Lists.fixedSize.empty();
 * FixedSizeList&lt;String&gt; listWith = Lists.fixedSize.with("a", "b", "c");
 * FixedSizeList&lt;String&gt; listOf = Lists.fixedSize.of("a", "b", "c");
 * </pre>
 */
@SuppressWarnings("ConstantNamingConvention")
public final class Lists
{
    public static final ImmutableListFactory immutable =
            ServiceLoaderUtils.loadServiceClass(ImmutableListFactory.class);
    public static final MutableListFactory mutable =
            ServiceLoaderUtils.loadServiceClass(MutableListFactory.class);
    public static final FixedSizeListFactory fixedSize =
            ServiceLoaderUtils.loadServiceClass(FixedSizeListFactory.class);

    private Lists()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
