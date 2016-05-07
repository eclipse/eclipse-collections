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

/**
 * MultimapKeyValuePutProcedure uses an Functions to calculate the key and value for an object and puts the key and value
 * into the specified {@link MutableMultimap}.
 */
public class MultimapKeyValuePutProcedure<T, K, V> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final MutableMultimap<K, V> mutableMultimap;
    private final Function<? super T, ? extends K> keyFunction;
    private final Function<? super T, ? extends V> valueFunction;

    public MultimapKeyValuePutProcedure(MutableMultimap<K, V> mutableMultimap, Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction)
    {
        this.mutableMultimap = mutableMultimap;
        this.keyFunction = keyFunction;
        this.valueFunction = valueFunction;
    }

    @Override
    public void value(T each)
    {
        K key = this.keyFunction.valueOf(each);
        V value = this.valueFunction.valueOf(each);
        this.mutableMultimap.put(key, value);
    }
}
