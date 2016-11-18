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

import org.eclipse.collections.api.list.MutableList
import org.eclipse.collections.impl.Prelude._
import org.junit.Test

class MultiReaderFastListScalaTest extends MultiReaderFastListTestTrait
{
    override val classUnderTest = MultiReaderFastList.newListWith(1, 2, 3)

    @Test
    def listIterator_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            try
            {
                this.classUnderTest.listIterator
            }
            catch
                {
                    case e: Exception => ()
                }
        }

    @Test
    def listIteratorIndex_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            try
            {
                this.classUnderTest.listIterator(1)
            }
            catch
                {
                    case e: Exception => ()
                }
        }

    @Test
    def iteratorWithReadLock_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.withReadLockAndDelegate((each: MutableList[Int]) =>
            {
                each.iterator
                ()
            })
        }

    @Test
    def iteratorWithWriteLock_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.withWriteLockAndDelegate((each: MutableList[Int]) =>
            {
                each.iterator
                ()
            })
        }

    @Test
    def newList_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            MultiReaderFastList.newList
        }

    @Test
    def newListCapacity_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            MultiReaderFastList.newList(5)
        }

    @Test
    def newListIterable_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            MultiReaderFastList.newList(new FastList[Int])
        }

    @Test
    def newListWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            MultiReaderFastList.newListWith(1, 2)
        }

    @Test
    def clone_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.clone
        }

    @Test
    def addWithIndex_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.add(1, 4)
        }

    @Test
    def addAllWithIndex_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.addAll(1, FastList.newListWith(3, 4, 5))
        }

    @Test
    def removeWithIndex_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.remove(1)
        }

    @Test
    def set_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.set(1, 4)
        }

    @Test
    def reverseThis_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.reverseThis
        }

    @Test
    def sortThis_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.sortThis
        }

    @Test
    def sortThis_withComparator_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.sortThis(null)
        }

    @Test
    def sortThisBy_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.sortThisBy[String]((_: Int) => "")
        }

    @Test
    def distinct_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.distinct
        }

    @Test
    def subList_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.subList(0, 1)
        }

    @Test
    def get_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.get(1)
        }

    @Test
    def indexOf_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.get(1)
        }

    @Test
    def lastIndexOf_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.get(1)
        }

    @Test
    def reverseForEach_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.reverseForEach((_: Int) => ())
        }

    @Test
    def asReversed_safe(): Unit =
    {
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.asReversed()
        }

        val reverseIterable = this.classUnderTest.asReversed()
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            reverseIterable.forEach((_: Int) => ())
        }
    }

    @Test
    def forEachWithIndex_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.forEachWithIndex(0, 2, (_: Int, _: Int) => ())
        }

    @Test
    def toReversed_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toReversed
        }

    @Test
    def toStack_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toStack
        }

    @Test
    def takeWhile_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.takeWhile((_: Int) => true)
        }

    @Test
    def dropWhile_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.dropWhile((_: Int) => true)
        }

    @Test
    def partitionWhile_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.partitionWhile((_: Int) => true)
        }

}
