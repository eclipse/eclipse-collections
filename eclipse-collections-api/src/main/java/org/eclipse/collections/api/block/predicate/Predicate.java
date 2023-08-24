/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.block.predicate;

import java.io.Serializable;

/*
 A Predicate is an interface used to define a condition that can be evaluated
 to a boolean result. Classes implementing the org.eclipse.collections.api.block.predicate.Predicate
 interface allow users to specify their custom criteria for filtering or discriminating
 objects in a collection.
 */
@FunctionalInterface
public interface Predicate<T>
        extends java.util.function.Predicate<T>, Serializable
{
    boolean accept(T each);

    @Override
    default boolean test(T each)
    {
        return this.accept(each);
    }
}
