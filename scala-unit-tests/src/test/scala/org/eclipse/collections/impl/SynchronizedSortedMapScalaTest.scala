/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl

import org.eclipse.collections.api.map.sorted.MutableSortedMap
import org.eclipse.collections.impl.Prelude._
import org.eclipse.collections.impl.block.factory.Functions
import org.eclipse.collections.impl.list.mutable.FastList
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap
import org.eclipse.collections.impl.tuple.Tuples
import org.junit.Test

class SynchronizedSortedMapScalaTest extends SynchronizedMapIterableTestTrait
{
    val classUnderTest: MutableSortedMap[String, String] = TreeSortedMap.newMapWith("A", "1", "B", "2", "C", "3").asSynchronized()

    @Test
    def newEmpty_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.newEmpty
        }
    }

    @Test
    def removeKey_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.remove("1")
        }
    }

    @Test
    def getIfAbsentPut_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.getIfAbsentPut("Nine", () => "foo")
        }
    }

    @Test
    def getIfAbsentPutWith_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.getIfAbsentPutWith("Nine", Functions.getPassThru[String], "foo")
        }
    }

    @Test
    def asUnmodifiable_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.asUnmodifiable
        }
    }

    @Test
    def toImmutable_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.toImmutable
        }
    }

    @Test
    def collectKeysAndValues_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.collectKeysAndValues(FastList.newListWith[java.lang.Integer](4, 5, 6),
            {
                _: java.lang.Integer => ""
            },
            {
                _: java.lang.Integer => ""
            })
        }
    }

    @Test
    def comparator_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.comparator
        }
    }

    @Test
    def values_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.values
        }
    }

    @Test
    def keySet_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.keySet
        }
    }

    @Test
    def entrySet_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.entrySet
        }
    }

    @Test
    def headMap_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.headMap("B")
        }
    }

    @Test
    def tailMap_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.tailMap("B")
        }
    }

    @Test
    def subMap_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.subMap("A", "C")
        }
    }

    @Test
    def firstKey_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.firstKey
        }
    }

    @Test
    def lastKey_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.lastKey
        }
    }

    @Test
    def with_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.`with`(Tuples.pair("D", "4"))
        }
    }
}
