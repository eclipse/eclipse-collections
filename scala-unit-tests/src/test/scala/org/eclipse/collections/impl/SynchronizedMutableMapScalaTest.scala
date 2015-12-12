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

import org.eclipse.collections.api.map.MutableMap
import org.eclipse.collections.impl.Prelude._
import org.eclipse.collections.impl.block.factory.Functions
import org.eclipse.collections.impl.list.mutable.FastList
import org.eclipse.collections.impl.map.mutable.UnifiedMap
import org.eclipse.collections.impl.tuple.Tuples
import org.junit.Test

class SynchronizedMutableMapScalaTest extends SynchronizedMapIterableTestTrait
{
    val classUnderTest: MutableMap[String, String] = UnifiedMap.newWithKeysValues("One", "1", "Two", "2", "Three", "3").asSynchronized()

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
    def withKeyValue_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.withKeyValue("foo", "bar")
        }
    }

    @Test
    def withAllKeyValues_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.withAllKeyValues(FastList.newListWith(Tuples.pair("foo", "bar")));
        }
    }

    @Test
    def withAllKeyValueArguments_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.withAllKeyValueArguments(Tuples.pair("foo", "bar"))
        }
    }

    @Test
    def withoutKey_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.withoutKey("foo")
        }
    }

    @Test
    def withoutAllKeys_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.withoutAllKeys(FastList.newListWith("foo"))
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
}
