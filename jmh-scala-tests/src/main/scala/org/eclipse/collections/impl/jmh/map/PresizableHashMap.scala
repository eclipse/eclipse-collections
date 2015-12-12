/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.jmh.map

class PresizableHashMap[K, V](val _initialSize: Int) extends scala.collection.mutable.HashMap[K, V]
{
    private def initialCapacity =
        if (_initialSize == 0) 1
        else smallestPowerOfTwoGreaterThan((_initialSize.toLong * 1000 / _loadFactor).asInstanceOf[Int])

    private def smallestPowerOfTwoGreaterThan(n: Int): Int =
        if (n > 1) Integer.highestOneBit(n - 1) << 1 else 1

    table = new Array(initialCapacity)
    threshold = ((initialCapacity.toLong * _loadFactor) / 1000).toInt
}
