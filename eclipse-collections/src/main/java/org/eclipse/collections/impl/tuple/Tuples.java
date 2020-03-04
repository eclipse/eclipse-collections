/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.tuple;

import java.util.Map;

import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.Triple;
import org.eclipse.collections.api.tuple.Triplet;
import org.eclipse.collections.api.tuple.Twin;

/**
 * A Pair is a container that holds two related objects. It is the equivalent of an Association in Smalltalk, or an
 * implementation of Map.Entry in the JDK. A Twin is a Pair with the same types. This class is a factory class
 * for Pairs and Twins.
 *
 * A Triple is a container that holds three related objects. Similar to Haskell a Tuple is container that can contain 2 or more objects.
 * The Triple is the implementation of the 3-tuple. A Triplet is a Triple with the same types. This class holds factory methods for Triples and Triplets
 *
 * The equivalent class for primitive and object combinations is {@link org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples}
 */
public final class Tuples
{
    private Tuples()
    {
    }

    public static <K, V> Pair<K, V> pairFrom(Map.Entry<K, V> entry)
    {
        return Tuples.pair(entry.getKey(), entry.getValue());
    }

    public static <T1, T2> Pair<T1, T2> pair(T1 one, T2 two)
    {
        return new PairImpl<>(one, two);
    }

    public static <T> Twin<T> twin(T one, T two)
    {
        return new TwinImpl<>(one, two);
    }

    public static <T1, T2, T3> Triple<T1, T2, T3> triple(T1 one, T2 two, T3 three)
    {
        return new TripleImpl<>(one, two, three);
    }

    public static <T> Triplet<T> triplet(T one, T two, T three)
    {
        return new TripletImpl<>(one, two, three);
    }
}
