/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.block.function;

import java.io.Serializable;
import java.util.function.BiFunction;

/**
 * Function2 is a two argument lambda which takes two arguments and returns a result of a transformation.
 *
 * A Function2 is used by RichIterable.injectInto() and RichIterable.collectWith() methods. See documentation of these
 * methods for more details.
 *
 * @since 1.0
 * @see org.eclipse.collections.api.RichIterable#injectInto
 * @see org.eclipse.collections.api.RichIterable#collectWith
 */
@FunctionalInterface
public interface Function2<T1, T2, R>
        extends BiFunction<T1, T2, R>, Serializable
{
    R value(T1 argument1, T2 argument2);

    @Override
    default R apply(T1 argument1, T2 argument2)
    {
        return this.value(argument1, argument2);
    }
}
