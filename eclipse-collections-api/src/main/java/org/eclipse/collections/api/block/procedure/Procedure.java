/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.block.procedure;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * A Procedure is a single argument lambda which has no return argument.
 */
@FunctionalInterface
public interface Procedure<T>
        extends Consumer<T>, Serializable
{
    void value(T each);

    @Override
    default void accept(T each)
    {
        this.value(each);
    }
}
