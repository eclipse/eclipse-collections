/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function;

import org.eclipse.collections.api.block.function.Function0;

/**
 * A passthru Function0 which returns the value specified.
 */
public final class PassThruFunction0<T>
        implements Function0<T>
{
    private static final long serialVersionUID = 1L;
    private final T result;

    public PassThruFunction0(T newResult)
    {
        this.result = newResult;
    }

    @Override
    public T value()
    {
        return this.result;
    }
}
