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

import org.eclipse.collections.api.map.MapIterable
import org.eclipse.collections.impl.Prelude._
import org.eclipse.collections.impl.tuple.Tuples
import org.junit.Test

trait SynchronizedMapIterableTestTrait extends SynchronizedRichIterableTestTrait
{
    val classUnderTest: MapIterable[String, String]

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

    @Test
    def get_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.get("1")
        }
    }

    @Test
    def containsKey_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.containsKey("One")
        }
    }

    @Test
    def containsValue_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.containsValue("2")
        }
    }

    @Test
    def forEachKey_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.forEachKey
            {
                _: String => ()
            }
        }
    }

    @Test
    def forEachValue_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.forEachValue
            {
                _: String => ()
            }
        }
    }

    @Test
    def forEachKeyValue_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.forEachKeyValue
            {
                (_: String, _: String) => ()
            }
        }
    }

    @Test
    def getIfAbsent_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.getIfAbsent("Nine", () => "foo")
        }
    }

    @Test
    def getIfAbsentWith_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.getIfAbsentWith("Nine", (_: String) => "", "foo")
        }
    }

    @Test
    def ifPresentApply_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.ifPresentApply("1", (_: String) => "foo")
        }
    }

    @Test
    def keysView_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.keysView()
        }
    }

    @Test
    def valuesView_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.valuesView()
        }
    }

    @Test
    def mapSelect_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.select
            {
                (_: String, _: String) => false
            }
        }
    }

    @Test
    def mapCollectValues_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.collectValues
            {
                (_: String, _: String) => "foo"
            }
        }
    }

    @Test
    def mapCollect_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.collect
            {
                (_: String, _: String) => Tuples.pair("foo", "bar")
            }
        }
    }

    @Test
    def mapReject_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.reject
            {
                (_: String, _: String) => false
            }
        }
    }

    @Test
    def mapDetect_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.detect
            {
                (_: String, _: String) => false
            }
        }
    }
}
