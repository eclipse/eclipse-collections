/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.block;

import java.io.Serializable;

/**
 * Interface for supporting user defined hashing strategies in Sets and Maps
 */
public interface HashingStrategy<E>
        extends Serializable
{
    /**
     * Computes the hashCode of the object as defined by the user.
     */
    int computeHashCode(E object);

    /**
     * Checks two objects for equality. The equality check can use the objects own equals() method or
     * a custom method defined by the user. It should be consistent with the computeHashCode() method.
     */
    boolean equals(E object1, E object2);
}
