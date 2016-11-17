/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * MultimapEachPutProcedure uses an Function to calculate the keys for an object and puts the object with each of
 * the keys into the specified {@link MutableMultimap}.
 */
public final class MultimapEachPutProcedure<K, V> implements Procedure<V>
{
    private static final long serialVersionUID = 2L;
    private final MutableMultimap<K, V> multimap;
    private final Function<? super V, ? extends Iterable<K>> keyFunction;

    public MultimapEachPutProcedure(
            MutableMultimap<K, V> multimap,
            Function<? super V, ? extends Iterable<K>> keyFunction)
    {
        this.multimap = multimap;
        this.keyFunction = keyFunction;
    }

    public static <K, V> MultimapEachPutProcedure<K, V> on(
            MutableMultimap<K, V> multimap,
            Function<? super V, ? extends Iterable<K>> keyFunction)
    {
        return new MultimapEachPutProcedure<>(multimap, keyFunction);
    }

    @Override
    public void value(V each)
    {
        Iterate.forEachWith(this.keyFunction.valueOf(each), this.multimap::put, each);
    }
}
