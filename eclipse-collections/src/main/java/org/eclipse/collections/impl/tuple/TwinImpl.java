/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.tuple;

import org.eclipse.collections.api.tuple.Twin;

/**
 * A TwinImpl is a PairImpl that has the same type for both items. This is a convenience class
 */
final class TwinImpl<T>
        extends PairImpl<T, T> implements Twin<T>
{
    private static final long serialVersionUID = 1L;

    TwinImpl(T newOne, T newTwo)
    {
        super(newOne, newTwo);
    }

    @Override
    public TwinImpl<T> swap()
    {
        return new TwinImpl<>(this.getTwo(), this.getOne());
    }
}
