/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Functions0;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Functions0Test
{
    @Test
    public void getTrue()
    {
        assertTrue(Functions0.getTrue().value());
    }

    @Test
    public void getFalse()
    {
        assertFalse(Functions0.getFalse().value());
    }

    @Test
    public void throwing()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () -> Functions0.throwing(() ->
                {
                    throw new IOException();
                }).value());
    }

    @Test
    public void throwingWithSuccessfulCompletion()
    {
        assertEquals("hello", Functions0.throwing(() -> "hello").value());
    }

    @Test
    public void throwingWithUserSpecifiedException()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () ->
                {
                    Functions0.throwing(
                            () ->
                            {
                                throw new IOException();
                            },
                            RuntimeException::new).value();
                });
        Verify.assertThrowsWithCause(
                MyRuntimeException.class,
                IOException.class,
                () ->
                {
                    Functions0.throwing(
                            () ->
                            {
                                throw new IOException();
                            },
                            this::throwMyException).value();
                });
        assertThrows(
                NullPointerException.class,
                () ->
                {
                    Functions0.throwing(
                            () ->
                            {
                                throw new NullPointerException();
                            },
                            this::throwMyException).value();
                });
    }

    private MyRuntimeException throwMyException(Throwable exception)
    {
        return new MyRuntimeException(exception);
    }

    @Test
    public void newFastList()
    {
        assertEquals(Lists.mutable.of(), Functions0.newFastList().value());
        Verify.assertInstanceOf(FastList.class, Functions0.newFastList().value());
    }

    @Test
    public void newUnifiedSet()
    {
        assertEquals(UnifiedSet.newSet(), Functions0.newUnifiedSet().value());
        Verify.assertInstanceOf(UnifiedSet.class, Functions0.newUnifiedSet().value());
    }

    @Test
    public void newHashBag()
    {
        assertEquals(Bags.mutable.of(), Functions0.newHashBag().value());
        Verify.assertInstanceOf(HashBag.class, Functions0.newHashBag().value());
    }

    @Test
    public void newUnifiedMap()
    {
        assertEquals(UnifiedMap.newMap(), Functions0.newUnifiedMap().value());
        Verify.assertInstanceOf(UnifiedMap.class, Functions0.newUnifiedMap().value());
    }

    @Test
    public void zeroInteger()
    {
        assertEquals(Integer.valueOf(0), Functions0.zeroInteger().value());
        assertEquals(Integer.valueOf(0), Functions0.value(0).value());
    }

    @Test
    public void zeroAtomicInteger()
    {
        Verify.assertInstanceOf(AtomicInteger.class, Functions0.zeroAtomicInteger().value());
        assertEquals(0, Functions0.zeroAtomicInteger().value().get());
    }

    @Test
    public void zeroAtomicLong()
    {
        Verify.assertInstanceOf(AtomicLong.class, Functions0.zeroAtomicLong().value());
        assertEquals(0, Functions0.zeroAtomicLong().value().get());
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(Functions0.class);
    }

    private static class MyRuntimeException extends RuntimeException
    {
        MyRuntimeException(Throwable cause)
        {
            super(cause);
        }
    }
}
