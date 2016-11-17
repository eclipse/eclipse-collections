/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable

import org.eclipse.collections.impl.collection.mutable.SynchronizedMutableCollectionTestTrait
import org.eclipse.collections.impl.list.mutable.FastList
import org.junit.Test

class SynchronizedMutableSetScalaTest extends SynchronizedMutableCollectionTestTrait with SynchronizedUnsortedSetIterableTestTrait
{
    val treeSet = new java.util.TreeSet[String](FastList.newListWith[String]("1", "2", "3"))
    val mutableSet = SetAdapter.adapt(treeSet)
    val classUnderTest = mutableSet.asSynchronized

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
}
