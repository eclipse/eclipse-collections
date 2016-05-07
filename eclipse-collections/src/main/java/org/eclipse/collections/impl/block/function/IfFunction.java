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

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;

public class IfFunction<T, V> implements Function<T, V>
{
    private static final long serialVersionUID = 1L;
    private final Predicate<? super T> predicate;
    private final Function<? super T, ? extends V> function;
    private final Function<? super T, ? extends V> elseFunction;

    public IfFunction(
            Predicate<? super T> newPredicate,
            Function<? super T, ? extends V> function)
    {
        this(newPredicate, function, null);
    }

    public IfFunction(Predicate<? super T> predicate,
            Function<? super T, ? extends V> function,
            Function<? super T, ? extends V> elseFunction)
    {
        this.predicate = predicate;
        this.function = function;
        this.elseFunction = elseFunction;
    }

    @Override
    public V valueOf(T object)
    {
        if (this.predicate.accept(object))
        {
            return this.function.valueOf(object);
        }
        if (this.elseFunction != null)
        {
            return this.elseFunction.valueOf(object);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return "new IfFunction(" + this.predicate + ", " + this.function + ", " + this.elseFunction + ')';
    }
}
