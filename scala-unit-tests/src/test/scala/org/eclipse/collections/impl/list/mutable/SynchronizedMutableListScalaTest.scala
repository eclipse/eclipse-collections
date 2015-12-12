/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable

import org.eclipse.collections.impl.Prelude._
import org.eclipse.collections.impl.collection.mutable.SynchronizedMutableCollectionTestTrait

class SynchronizedMutableListScalaTest extends SynchronizedMutableCollectionTestTrait
{
    import org.junit.Test

    val classUnderTest = FastList.newListWith[String]("1", "2", "3").asSynchronized

    @Test
    override def equals_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.equals(null)
        }
    }

    @Test
    override def hashCode_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.hashCode
        }
    }

    @Test
    def asReversed_synchronized
    {
        this.assertSynchronized
        {
            this.classUnderTest.asReversed()
        }
        val reverseIterable = this.classUnderTest.asReversed()
        this.assertSynchronized
        {
            reverseIterable.forEach{_: String => ()}
        }
    }
}
