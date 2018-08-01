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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ThrowingInvocationHandler implements InvocationHandler
{
    private final String error;

    public ThrowingInvocationHandler(String error)
    {
        this.error = error;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
    {
        throw new IllegalStateException(this.error);
    }
}
