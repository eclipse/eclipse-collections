/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function.checked;

import org.eclipse.collections.api.block.function.Function;

public abstract class CheckedFunction<T, V>
        implements Function<T, V>, ThrowingFunction<T, V>
{
    private static final long serialVersionUID = 1L;

    @Override
    public final V valueOf(T anObject)
    {
        try
        {
            return this.safeValueOf(anObject);
        }
        catch (RuntimeException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Checked exception caught in Function", e);
        }
    }
}
