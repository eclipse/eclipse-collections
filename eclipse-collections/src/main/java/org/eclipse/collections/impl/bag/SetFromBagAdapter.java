/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag;

import java.util.Iterator;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.set.immutable.AbstractImmutableSet;

public class SetFromBagAdapter<T>
        extends AbstractImmutableSet<T>
{
    private final Bag<T> bag;

    public SetFromBagAdapter(Bag<T> bag)
    {
        this.bag = bag;
    }

    @Override
    public int size()
    {
        return this.bag.sizeDistinct();
    }

    @Override
    public T getFirst()
    {
        return this.bag.getFirst();
    }

    @Override
    public T getLast()
    {
        return this.bag.getLast();
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        this.bag.forEachWithOccurrences((each, parameter) -> procedure.accept(each));
    }

    @Override
    public Iterator<T> iterator()
    {
        return this.bag.distinctView().iterator();
    }
}
