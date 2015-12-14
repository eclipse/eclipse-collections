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

import java.util.concurrent.TimeUnit

import org.junit.Assert

trait SynchronizedTestTrait
{
    val classUnderTest: AnyRef

    class Gate
    {
        val latch = new java.util.concurrent.CountDownLatch(1)

        def open()
        {
            this.latch.countDown()
        }

        def await()
        {
            this.latch.await()
        }
    }

    def time(code: => Unit) =
    {
        val before = System.currentTimeMillis
        code
        val after = System.currentTimeMillis
        after - before
    }

    def assertSynchronized(code: => Unit)
    {
        this.assertThreadSafety(threadSafe = true, 10L, TimeUnit.MILLISECONDS)(code)
    }

    def assertNotSynchronized(code: => Unit)
    {
        this.assertThreadSafety(threadSafe = false, 60L, TimeUnit.SECONDS)(code)
    }

    def assertThreadSafety(threadSafe: Boolean, timeout: Long, timeUnit: TimeUnit)(code: => Unit)
    {
        def spawn(code: => Unit) =
        {
            val result = new Thread
            {
                override def run = code
            }
            result.start()
            result
        }

        val gate = new Gate

        val lockHolderThread = spawn
        {
            this.classUnderTest.synchronized
            {
                // Don't let the other thread start until we've got the lock
                gate.open()

                // Hold the lock until interruption
                try
                {
                    Thread.sleep(java.lang.Long.MAX_VALUE)
                }
                catch
                    {
                        case ignore: InterruptedException => Thread.currentThread.interrupt
                    }
            }
        }

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
}
