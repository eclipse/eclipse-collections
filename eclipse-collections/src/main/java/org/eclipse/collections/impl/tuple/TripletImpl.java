/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.tuple;

import org.eclipse.collections.api.tuple.Triplet;

class TripletImpl<T> extends TripleImpl<T, T, T> implements Triplet<T>
{
    private static final long serialVersionUID = 1L;

    TripletImpl(T newOne, T newTwo, T newThree)
    {
        super(newOne, newTwo, newThree);
    }

    @Override
    public TripletImpl<T> reverse()
    {
        return new TripletImpl<>(this.getThree(), this.getTwo(), this.getOne());
    }
}
