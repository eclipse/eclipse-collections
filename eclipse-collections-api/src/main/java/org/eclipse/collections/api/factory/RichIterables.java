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

/**
 * This class should be used to create instances of RichIterable decorators
*
 * @Since 10.0.
 */
@SuppressWarnings("ConstantNamingConvention")
public final class RichIterables
{
    public static final RichIterableDecoratorFactory DECORATOR =
            ServiceLoaderUtils.loadServiceClass(RichIterableDecoratorFactory.class);

    private RichIterables()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
