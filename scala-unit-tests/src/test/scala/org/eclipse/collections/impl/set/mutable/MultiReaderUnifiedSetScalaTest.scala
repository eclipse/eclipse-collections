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

import org.eclipse.collections.api.set.MutableSet
import org.eclipse.collections.impl.Prelude._
import org.eclipse.collections.impl.list.mutable.FastList
import org.junit.Test

class MultiReaderUnifiedSetScalaTest extends MultiReaderUnifiedSetTestTrait
{
    val classUnderTest = MultiReaderUnifiedSet.newSetWith(1, 2, 3)

    @Test
    def newSet_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            MultiReaderUnifiedSet.newSet
        }

    @Test
    def newBagCapacity_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            MultiReaderUnifiedSet.newSet(5)
        }

    @Test
    def newSetIterable_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            MultiReaderUnifiedSet.newSet(new FastList[Int])
        }

    @Test
    def newSetWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            MultiReaderUnifiedSet.newSetWith(1, 2)
        }

    @Test
    def union_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.union(new UnifiedSet[Int])
        }

    @Test
    def unionInto_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.unionInto(new UnifiedSet[Int], new UnifiedSet[Int])
        }

    @Test
    def intersect_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.intersect(new UnifiedSet[Int])
        }

    @Test
    def intersectInto_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.intersectInto(new UnifiedSet[Int], new UnifiedSet[Int])
        }

    @Test
    def difference_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.difference(new UnifiedSet[Int])
        }

    @Test
    def differenceInto_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.differenceInto(new UnifiedSet[Int], new UnifiedSet[Int])
        }

    @Test
    def symmetricDifference_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.symmetricDifference(new UnifiedSet[Int])
        }

    @Test
    def symmetricDifferenceInto_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.symmetricDifferenceInto(new UnifiedSet[Int], new UnifiedSet[Int])
        }

    @Test
    def isSubsetOf_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.isSubsetOf(new UnifiedSet[Int])
        }

    @Test
    def isProperSubsetOf_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.isProperSubsetOf(new UnifiedSet[Int])
        }

    @Test
    def powerSet_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.powerSet
        }

    @Test
    def cartesianProduct_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.cartesianProduct(new UnifiedSet[Int])
        }

    @Test
    def clone_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.clone
        }

    @Test
    def iteratorWithReadLock_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.withReadLockAndDelegate((each: MutableSet[Int]) =>
            {
                each.iterator
                ()
            })
        }

    @Test
    def iteratorWithWriteLock_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.withWriteLockAndDelegate((each: MutableSet[Int]) =>
            {
                each.iterator
                ()
            })
        }
}
