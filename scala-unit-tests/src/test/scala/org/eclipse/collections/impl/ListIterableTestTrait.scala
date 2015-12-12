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

import org.eclipse.collections.api.list.ListIterable
import org.eclipse.collections.impl.Prelude._
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure
import org.eclipse.collections.impl.list.mutable.FastList
import org.junit.{Assert, Test}

trait ListIterableTestTrait extends InternalIterableTestTrait
{
    val classUnderTest: ListIterable[String]

    @Test
    abstract override def forEach
    {
        super.forEach

        val result = FastList.newList[String]
        classUnderTest.forEach(CollectionAddProcedure.on(result))
        Assert.assertEquals(FastList.newListWith("1", "2", "3"), result)
    }

    @Test
    abstract override def forEachWithIndex
    {
        super.forEachWithIndex

        var count = 0
        classUnderTest.forEachWithIndex
        {
            (each: String, index: Int) =>
                Assert.assertEquals(index, count)
                count += 1
                Assert.assertEquals(String.valueOf(count), each)
        }

        Assert.assertEquals(3, count)
    }

    @Test
    abstract override def forEachWith
    {
        super.forEachWith

        val unique = new AnyRef
        var count = 0

        classUnderTest.forEachWith(
            (each: String, parameter: AnyRef) =>
            {
                count += 1
                Assert.assertEquals(String.valueOf(count), each)
                Assert.assertSame(unique, parameter)
                ()
            },
            unique)

        Assert.assertEquals(3, count)
    }
}
