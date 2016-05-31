/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.block.function;

import java.io.Serializable;

/**
 * A Function3 is used by injectIntoWith().  In the injectIntoWith() method the block takes the injected
 * argument as the first argument, the current item of the collection as the second argument, and the specified
 * parameter for the third argument. The result of each subsequent iteration is passed in as the first argument.
 */
@FunctionalInterface
public interface Function3<T1, T2, T3, R>
        extends Serializable
{
    R value(T1 argument1, T2 argument2, T3 argument3);
}
