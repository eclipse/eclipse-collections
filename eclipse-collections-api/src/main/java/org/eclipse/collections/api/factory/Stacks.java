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

import org.eclipse.collections.api.factory.stack.ImmutableStackFactory;
import org.eclipse.collections.api.factory.stack.MutableStackFactory;

@SuppressWarnings("ConstantNamingConvention")
@aQute.bnd.annotation.spi.ServiceConsumer(value = ImmutableStackFactory.class)
@aQute.bnd.annotation.spi.ServiceConsumer(value = MutableStackFactory.class)
public final class Stacks
{
    public static final ImmutableStackFactory immutable = ServiceLoaderUtils.loadServiceClass(ImmutableStackFactory.class);
    public static final MutableStackFactory mutable = ServiceLoaderUtils.loadServiceClass(MutableStackFactory.class);

    private Stacks()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
