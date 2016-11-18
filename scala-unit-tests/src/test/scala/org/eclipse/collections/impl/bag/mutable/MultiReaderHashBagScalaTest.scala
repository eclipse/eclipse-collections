/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable

import org.eclipse.collections.api.bag.MutableBag
import org.eclipse.collections.impl.Prelude._
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates
import org.eclipse.collections.impl.list.mutable.FastList
import org.junit.Test

class MultiReaderHashBagScalaTest extends MultiReaderHashBagTestTrait
{
    val classUnderTest = MultiReaderHashBag.newBagWith(1, 1, 2)

    @Test
    def newBag_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            MultiReaderHashBag.newBag
        }

    @Test
    def newBagCapacity_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            MultiReaderHashBag.newBag(5)
        }

    @Test
    def newBagIterable_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            MultiReaderHashBag.newBag(FastList.newListWith(1, 2))
        }

    @Test
    def newBagWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            MultiReaderHashBag.newBagWith(1, 2)
        }

    @Test
    def addOccurrences_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.addOccurrences(1, 2)
        }

    @Test
    def removeOccurrences_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.removeOccurrences(1, 1)
        }

    @Test
    def occurrencesOf_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.occurrencesOf(1)
        }

    @Test
    def sizeDistinct_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.sizeDistinct
        }

    @Test
    def toMapOfItemToCount_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toMapOfItemToCount
        }

    @Test
    def toStringOfItemToCount_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toStringOfItemToCount
        }

    @Test
    def iteratorWithReadLock_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.withReadLockAndDelegate((each: MutableBag[Int]) =>
            {
                each.iterator
                ()
            })
        }

    @Test
    def iteratorWithWriteLock_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.withWriteLockAndDelegate((each: MutableBag[Int]) =>
            {
                each.iterator
                ()
            })
        }

    @Test
    def forEachWithOccurrences_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.forEachWithOccurrences((_: Int, _: Int) => ())
        }

    @Test
    def selectByOccurrences_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.selectByOccurrences(IntPredicates.isOdd)
        }
}
