/*
 * Copyright (c) 2015 Goldman Sachs.
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
 * A Function2 is used by injectInto() methods. It takes the injected argument as the first argument, and the
 * current item of the collection as the second argument, for the first item in the collection.  The result of each
 * subsequent iteration is passed in as the first argument.
 *
 * @since 1.0
 */
public interface Function2<T1, T2, R>
        extends Serializable
{
    R value(T1 argument1, T2 argument2);
}
