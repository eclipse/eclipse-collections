/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.mutable

import org.eclipse.collections.impl.SynchronizedRichIterableTestTrait
import org.junit.Test

class SynchronizedStackScalaTest extends SynchronizedRichIterableTestTrait
{
    val classUnderTest = ArrayStack.newStackFromTopToBottom[String]("1", "2", "3").asSynchronized

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
    def push
    {
        this.assertSynchronized
        {
            this.classUnderTest.push("4")
        }
    }

    @Test
    def pop
    {
        this.assertSynchronized
        {
            this.classUnderTest.pop
        }
    }

    @Test
    def pop_int
    {
        this.assertSynchronized
        {
            this.classUnderTest.pop(2)
        }
    }

    @Test
    def peek
    {
        this.assertSynchronized
        {
            this.classUnderTest.peek()
        }
    }

    @Test
    def peek_int
    {
        this.assertSynchronized
        {
            this.classUnderTest.peek(2)
        }
    }

    @Test
    def clear
    {
        this.assertSynchronized
        {
            this.classUnderTest.clear
        }
    }

    @Test
    def to_immutable
    {
        this.assertSynchronized
        {
            this.classUnderTest.toImmutable
        }
    }
}
