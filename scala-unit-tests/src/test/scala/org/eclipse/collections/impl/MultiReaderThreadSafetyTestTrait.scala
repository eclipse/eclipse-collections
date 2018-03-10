/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl

import java.lang.StringBuilder
import java.util.concurrent.TimeUnit

import org.eclipse.collections.api.collection.MutableCollection
import org.eclipse.collections.api.list.MutableList
import org.eclipse.collections.api.multimap.MutableMultimap
import org.eclipse.collections.api.tuple.Pair
import org.eclipse.collections.impl.Prelude._
import org.eclipse.collections.impl.collection.mutable.AbstractMultiReaderMutableCollection
import org.eclipse.collections.impl.list.mutable.FastList
import org.eclipse.collections.impl.multimap.list.FastListMultimap
import org.junit.{Assert, Test}

trait MultiReaderThreadSafetyTestTrait
{
    val classUnderTest: AbstractMultiReaderMutableCollection[Int]

    def createReadLockHolderThread(gate: Gate): Thread

    def createWriteLockHolderThread(gate: Gate): Thread

    def sleep(gate: Gate): Unit =
    {
        gate.open()

        try
        {
            Thread.sleep(java.lang.Long.MAX_VALUE)
        }
        catch
            {
                case ignore: InterruptedException => Thread.currentThread.interrupt()
            }
    }

    def spawn(code: => Unit) =
    {
        val result = new Thread
        {
            override def run() = code
        }
        result.start()
        result
    }

    class Gate
    {
        val latch = new java.util.concurrent.CountDownLatch(1)

        def open(): Unit = this.latch.countDown()

        def await(): Unit = this.latch.await()
    }

    def time(code: => Unit) =
    {
        val before = System.currentTimeMillis
        code
        val after = System.currentTimeMillis
        after - before
    }

    def assert(readersBlocked: Boolean, writersBlocked: Boolean)(code: => Any): Unit =
    {
        if (readersBlocked)
        {
            assertReadersBlocked(code)
        }
        else
        {
            assertReadersNotBlocked(code)
        }

        if (writersBlocked)
        {
            assertWritersBlocked(code)
        }
        else
        {
            assertWritersNotBlocked(code)
        }
    }

    def assertReadersBlocked(code: => Unit): Unit =
    {
        this.assertReadSafety(threadSafe = true, 10L, TimeUnit.MILLISECONDS, code)
    }

    def assertReadersNotBlocked(code: => Unit): Unit =
    {
        this.assertReadSafety(threadSafe = false, 60L, TimeUnit.SECONDS, code)
    }

    def assertWritersBlocked(code: => Unit): Unit =
    {
        this.assertWriteSafety(threadSafe = true, 10L, TimeUnit.MILLISECONDS, code)
    }

    def assertWritersNotBlocked(code: => Unit): Unit =
    {
        this.assertWriteSafety(threadSafe = false, 60L, TimeUnit.SECONDS, code)
    }

    def assertReadSafety(threadSafe: Boolean, timeout: Long, timeUnit: TimeUnit, code: => Unit): Unit =
    {
        val gate = new Gate
        assertThreadSafety(timeout, timeUnit, gate, code, threadSafe, createReadLockHolderThread(gate))
    }

    def assertWriteSafety(threadSafe: Boolean, timeout: Long, timeUnit: TimeUnit, code: => Unit): Unit =
    {
        val gate = new Gate
        assertThreadSafety(timeout, timeUnit, gate, code, threadSafe, createWriteLockHolderThread(gate))
    }

    def assertThreadSafety(timeout: Long, timeUnit: TimeUnit, gate: MultiReaderThreadSafetyTestTrait.this.type#Gate, code: => Unit, threadSafe: Boolean, lockHolderThread: Thread): Unit =
    {
        val millisTimeout = TimeUnit.MILLISECONDS.convert(timeout, timeUnit)
        val measuredTime = time
        {
            // Don't start until the other thread is synchronized on classUnderTest
            gate.await()
            spawn(code).join(millisTimeout, 0)
        }

        Assert.assertEquals(
            "Measured " + measuredTime + " ms but timeout was " + millisTimeout + " ms.",
            threadSafe,
            measuredTime >= millisTimeout)

        lockHolderThread.interrupt()
        lockHolderThread.join()
    }

    @Test
    def newEmpty_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            this.classUnderTest.newEmpty
        }

    @Test
    def iterator_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = false)
        {
            try
            {
                this.classUnderTest.iterator
            }
            catch
                {
                    case e: Exception => ()
                }
        }

    @Test
    def add_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.add(4)
        }

    @Test
    def addAll_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.addAll(new FastList[Int])
        }

    @Test
    def addAllIterable_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.addAllIterable(new FastList[Int])
        }

    @Test
    def remove_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.remove(1)
        }

    @Test
    def removeAll_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.removeAll(new FastList[Int])
        }

    @Test
    def removeAllIterable_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.removeAllIterable(new FastList[Int])
        }

    @Test
    def retainAll_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.retainAll(new FastList[Int])
        }

    @Test
    def retainAllIterable_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.retainAllIterable(new FastList[Int])
        }

    @Test
    def removeIf_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.removeIf((_: Int) => true)
        }

    @Test
    def removeIfWith_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.removeIfWith((_: Int, _: Int) => true, 0)
        }

    @Test
    def with_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.`with`(4)
        }

    @Test
    def without_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.without(1)
        }

    @Test
    def withAll_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.withAll(new FastList[Int])
        }

    @Test
    def withoutAll_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.withoutAll(new FastList[Int])
        }

    @Test
    def clear_safe(): Unit =
        this.assert(readersBlocked = true, writersBlocked = true)
        {
            this.classUnderTest.clear()
        }

    @Test
    def size_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.size
        }

    @Test
    def getFirst_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.getFirst
        }

    @Test
    def getLast_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.getLast
        }

    @Test
    def isEmpty_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.isEmpty
        }

    @Test
    def notEmpty_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.notEmpty
        }

    @Test
    def contains_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.contains(1)
        }

    @Test
    def containsAll_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.containsAll(new FastList[Int])
        }

    @Test
    def containsAllIterable_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.containsAll(new FastList[Int])
        }

    @Test
    def containsAllArguments_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.containsAllArguments("1", "2")
        }

    @Test
    def equals_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.equals(null)
        }

    @Test
    def hashCode_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.hashCode
        }

    @Test
    def forEach_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.forEach((_: Int) => ())
        }

    @Test
    def forEachWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.forEachWith((_: Int, _: Int) => (), 0)
        }

    @Test
    def collect_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.collect[String]((_: Int) => "")
        }

    @Test
    def collect_withTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.collect[String, MutableCollection[String]]((_: Int) => "", new FastList[String])
        }

    @Test
    def collectWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.collectWith[String, String]((_: Int, _: String) => "", "")
        }

    @Test
    def collectWith_withTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.collectWith[String, String, MutableList[String]]((_: Int, _: String) => "", "", new FastList[String])
        }

    @Test
    def flatCollect_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.flatCollect[String]((_: Int) => new FastList[String])
        }

    @Test
    def flatCollect_withTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.flatCollect[Int, MutableList[Int]]((_: Int) => new FastList[Int], new FastList[Int])
        }

    @Test
    def collectIf_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.collectIf[String]((_: Int) => true, (_: Int) => "")
        }

    @Test
    def collectIf_withTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.collectIf[String, MutableCollection[String]]((_: Int) => true, (num: Int) => "", new FastList[String])
        }

    @Test
    def select_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.select((_: Int) => true)
        }

    @Test
    def select_withTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.select((_: Int) => true, new FastList[Int])
        }

    @Test
    def selectWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.selectWith((_: Int, _: Int) => true, 1)
        }

    @Test
    def selectWith_withTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.selectWith((_: Int, _: Int) => true, 1, new FastList[Int])
        }

    @Test
    def reject_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.reject((_: Int) => true)
        }

    @Test
    def reject_withTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.reject((_: Int) => true, new FastList[Int])
        }

    @Test
    def rejectWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.rejectWith((_: Int, _: Int) => true, 1)
        }

    @Test
    def rejectWith_withTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.rejectWith((_: Int, _: Int) => true, 1, new FastList[Int])
        }

    @Test
    def selectInstancesOf_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.selectInstancesOf(Int.getClass)
        }

    @Test
    def partition_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.partition((_: Int) => true)
        }

    @Test
    def partitionWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.partitionWith((_: Int, _: Int) => true, 1)
        }

    @Test
    def selectAndRejectWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.selectAndRejectWith((_: Int, _: Int) => true, 1)
        }

    @Test
    def count_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.count((_: Int) => true)
        }

    @Test
    def countWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.countWith((_: Int, _: Int) => true, 1)
        }

    @Test
    def min_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.min
        }

    @Test
    def max_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.max
        }

    @Test
    def min_withComparator_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.min((_: Int, _: Int) => 0)
        }

    @Test
    def max_withComparator_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.max((_: Int, _: Int) => 0)
        }

    @Test
    def minBy_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.minBy[String]((_: Int) => "")
        }

    @Test
    def maxBy_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.maxBy[String]((_: Int) => "")
        }

    @Test
    def injectInto_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.injectInto[Int](0, (_: Int, _: Int) => 0)
        }

    @Test
    def injectIntoWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.injectIntoWith[Int, Int](0, (_: Int, _: Int, _: Int) => 0, 0)
        }

    @Test
    def sumOfInt_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.sumOfInt((_: Int) => 0)
        }

    @Test
    def sumOfLong_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.sumOfLong((_: Int) => 0L)
        }

    @Test
    def sumOfDouble_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.sumOfDouble((_: Int) => 0.0)
        }

    @Test
    def sumOfFloat_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.sumOfFloat((_: Int) => 0.0f)
        }

    @Test
    def toString_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toString
        }

    @Test
    def makeString_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.makeString
        }

    @Test
    def makeString_withSeparator_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.makeString(", ")
        }

    @Test
    def makeString_withStartEndSeparator_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.makeString("[", ", ", "]")
        }

    @Test
    def appendString_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.appendString(new StringBuilder)
        }

    @Test
    def appendString_withSeparator_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.appendString(new StringBuilder, ", ")
        }

    @Test
    def appendString_withStartEndSeparator_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.appendString(new StringBuilder, "[", ", ", "]")
        }

    @Test
    def groupBy_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.groupBy[String]((_: Int) => "")
        }

    @Test
    def groupBy_withTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.groupBy[String, MutableMultimap[String, Int]]((_: Int) => "", new FastListMultimap[String, Int])
        }

    @Test
    def groupByEach_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.groupByEach((_: Int) => new FastList[String])
        }

    @Test
    def groupByEach_withTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.groupByEach[Int, MutableMultimap[Int, Int]]((_: Int) => new FastList[Int], new FastListMultimap[Int, Int])
        }

    @Test
    def aggregateBy_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.aggregateBy[String, Int]((_: Int) => "", () => 0, (_: Int, _: Int) => 0)
        }

    @Test
    def aggregateInPlaceBy_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.aggregateInPlaceBy[String, Int]((_: Int) => "", () => 0, (_: Int, _: Int) => ())
        }

    @Test
    def zip_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.zip(FastList.newListWith("1", "1", "2"))
        }

    @Test
    def zip_withTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.zip(FastList.newListWith[String]("1", "1", "2"), new FastList[Pair[Int, String]])
        }

    @Test
    def zipByIndex_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.zipWithIndex()
        }

    @Test
    def zipByIndex_withTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.zipWithIndex(new FastList[Pair[Int, java.lang.Integer]])
        }

    @Test
    def chunk_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.chunk(2)
        }

    @Test
    def anySatisfy_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.anySatisfy((_: Int) => true)
        }

    @Test
    def anySatisfyWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.anySatisfyWith((_: Int, _: Int) => true, 1)
        }

    @Test
    def allSatisfy_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.allSatisfy((_: Int) => true)
        }

    @Test
    def allSatisfyWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.allSatisfyWith((_: Int, _: Int) => true, 1)
        }

    @Test
    def noneSatisfy_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.noneSatisfy((_: Int) => true)
        }

    @Test
    def noneSatisfyWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.noneSatisfyWith((_: Int, _: Int) => true, 1)
        }

    @Test
    def detect_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.detect((_: Int) => true)
        }

    @Test
    def detectIfNone_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.detectIfNone((_: Int) => true, () => 1)
        }

    @Test
    def detectWith_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.detectWith((_: Int, _: Int) => true, 1)
        }

    @Test
    def detectWithIfNone_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.detectWithIfNone((_: Int, _: Int) => true, 1, () => 1)
        }

    @Test
    def asLazy_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.asLazy
        }

    @Test
    def asUnmodifiable_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.asUnmodifiable
        }

    @Test
    def asSynchronized_safe(): Unit =
    {
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.asSynchronized
        }
    }

    @Test
    def toImmutable_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toImmutable
        }

    @Test
    def toList_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toList
        }

    @Test
    def toSortedList_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSortedList
        }

    @Test
    def toSortedList_withComparator_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSortedList((_: Int, _: Int) => 0)
        }

    @Test
    def toSortedListBy_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSortedListBy[String]((_: Int) => "")
        }

    @Test
    def toSet_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSet
        }

    @Test
    def toSortedSet_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSortedSet
        }

    @Test
    def toSortedSet_withComparator_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSortedSet((_: Int, _: Int) => 0)
        }

    @Test
    def toSortedSetBy_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSortedSetBy[String]((_: Int) => "")
        }

    @Test
    def toBag_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toBag
        }

    @Test
    def toSortedBag_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSortedBag
        }

    @Test
    def toSortedBag_withComparator_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSortedBag((_: Int, _: Int) => 0)
        }

    @Test
    def toSortedBagBy_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSortedBagBy[String]((_: Int) => "")
        }

    @Test
    def toMap_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toMap((_: Int) => 0, (_: Int) => 0)
        }

    @Test
    def toSortedMap_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSortedMap((_: Int) => 0, (_: Int) => 0)
        }

    @Test
    def toSortedMap_withComparator_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSortedMap[Int, Int]((_: Int, _: Int) => 0, (_: Int) => 0, (_: Int) => 0)
        }

    @Test
    def toSortedMap_withFunction_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toSortedMapBy[Integer, Int, Int]((_: Int) => Integer.valueOf(0), (_: Int) => 0, (_: Int) => 0)
        }

    @Test
    def toArray_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toArray
        }

    @Test
    def toArrayWithTarget_safe(): Unit =
        this.assert(readersBlocked = false, writersBlocked = true)
        {
            this.classUnderTest.toArray(new Array[java.lang.Integer](10))
        }
}
