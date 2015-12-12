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

import org.eclipse.collections.impl.list.mutable.FastList
import org.junit.Test

trait SynchronizedCollectionTestTrait extends SynchronizedTestTrait with IterableTestTrait
{
    val classUnderTest: java.util.Collection[String]

    @Test
    def size_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.size
        }
    }

    @Test
    def isEmpty_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.isEmpty
        }
    }

    @Test
    def contains_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.contains(null)
        }
    }

    @Test
    def iterator_not_synchronized
    {
        this.assertNotSynchronized
        {
            this.classUnderTest.iterator
        }
    }

    @Test
    def toArray_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.toArray
        }
    }

    @Test
    def toArray_with_target_synchronized
    {
        this.assertSynchronized
        {
            val array: Array[String] = null
            this.classUnderTest.toArray(array)
        }
    }

    @Test
    def add_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.add("")
        }
    }

    @Test
    def remove_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.remove("")
        }
    }

    @Test
    def containsAll_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.containsAll(FastList.newList[String])
        }
    }

    @Test
    def addAll_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.addAll(FastList.newList[String])
        }
    }

    @Test
    def removeAll_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.removeAll(FastList.newList[String])
        }
    }

    @Test
    def retainAll_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.retainAll(FastList.newList[String])
        }
    }

    @Test
    def clear_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.clear()
        }
    }

    @Test
    def equals_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.equals(null)
        }
    }

    @Test
    def hashCode_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.hashCode
        }
    }
}
