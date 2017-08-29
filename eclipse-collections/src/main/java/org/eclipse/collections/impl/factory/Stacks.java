/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import org.eclipse.collections.api.factory.stack.ImmutableStackFactory;
import org.eclipse.collections.api.factory.stack.MutableStackFactory;
import org.eclipse.collections.impl.stack.immutable.ImmutableStackFactoryImpl;
import org.eclipse.collections.impl.stack.mutable.MutableStackFactoryImpl;

@SuppressWarnings("ConstantNamingConvention")
public final class Stacks
{
    public static final ImmutableStackFactory immutable = ImmutableStackFactoryImpl.INSTANCE;
    public static final MutableStackFactory mutable = MutableStackFactoryImpl.INSTANCE;

    private Stacks()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
