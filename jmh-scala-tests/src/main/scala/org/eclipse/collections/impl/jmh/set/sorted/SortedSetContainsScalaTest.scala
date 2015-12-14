/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.jmh.set.sorted

import scala.collection.{immutable, mutable}

object SortedSetContainsScalaTest
{
    private val SIZE = 2000000

    val scalaMutable: mutable.TreeSet[Int] = new mutable.TreeSet[Int]() ++ (0 to SIZE by 2)
    val scalaImmutable: immutable.TreeSet[Int] = immutable.TreeSet.empty[Int] ++ (0 to SIZE by 2)

    def contains_mutable_scala(): Unit =
    {
        val size = SIZE
        val localScalaMutable = this.scalaMutable

        var i = 0
        while (i < size)
        {
            if (!localScalaMutable.contains(i))
            {
                throw new AssertionError
            }

            i += 2
        }


        i = 1
        while (i < size)
        {
            if (localScalaMutable.contains(i))
            {
                throw new AssertionError
            }
            i += 2
        }
    }

    def contains_immutable_scala(): Unit =
    {
        val size = SIZE
        val localScalaImmutable = this.scalaImmutable

        var i = 0
        while (i < size)
        {
            if (!localScalaImmutable.contains(i))
            {
                throw new AssertionError
            }

            i += 2
        }

        i = 1
        while (i < size)
        {
            if (localScalaImmutable.contains(i))
            {
                throw new AssertionError
            }
            i += 2
        }
    }
}
