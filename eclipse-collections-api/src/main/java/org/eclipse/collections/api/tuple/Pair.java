/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.tuple;

import java.io.Serializable;
import java.util.Map;

/**
 * A Pair is a container that holds two related objects.  It is the equivalent of an Association in Smalltalk, or an
 * implementation of Map.Entry in the JDK.
 *
 * An instance of this interface can be created by calling Tuples.pair(Object, Object) or Tuples.twin(Object, Object).
 */
public interface Pair<T1, T2>
        extends Serializable, Comparable<Pair<T1, T2>>
{
    T1 getOne();

    T2 getTwo();

    void put(Map<T1, T2> map);

    Map.Entry<T1, T2> toEntry();

    /**
     * Method used to swap the elements of pair.
     * <p>
     * <pre>e.g.
     * Pair&lt;String, Integer&gt; pair = Tuples.pair("One", 1);
     * Pair&lt;Integer, String&gt; swappedPair = pair.swap();
     * </pre>
     *
     * @since 6.0
     */
    Pair<T2, T1> swap();
}
