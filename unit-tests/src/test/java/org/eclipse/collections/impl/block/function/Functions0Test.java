/*
 * Copyright (c) 2015 Goldman Sachs.
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

import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Functions0;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Functions0Test
{
    @Test
    public void getTrue()
    {
        Assert.assertTrue(Functions0.getTrue().value());
    }

    @Test
    public void getFalse()
    {
        Assert.assertFalse(Functions0.getFalse().value());
    }

    @Test
    public void throwing()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () -> Functions0.throwing(() -> { throw new IOException(); }).value());
    }

    @Test
    public void throwingWithUserSpecifiedException()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () -> {
                    Functions0.throwing(
                            () -> { throw new IOException(); },
                            RuntimeException::new).value();
                });
        Verify.assertThrowsWithCause(
                MyRuntimeException.class,
                IOException.class,
                () -> {
                    Functions0.throwing(
                            () -> { throw new IOException(); },
                            this::throwMyException).value();
                });
        Verify.assertThrows(
                NullPointerException.class,
                () -> {
                    Functions0.throwing(
                            () -> { throw new NullPointerException(); },
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
        Assert.assertEquals(Lists.mutable.of(), Functions0.newFastList().value());
        Verify.assertInstanceOf(FastList.class, Functions0.newFastList().value());
    }

    @Test
    public void newUnifiedSet()
    {
        Assert.assertEquals(UnifiedSet.newSet(), Functions0.newUnifiedSet().value());
        Verify.assertInstanceOf(UnifiedSet.class, Functions0.newUnifiedSet().value());
    }

    @Test
    public void newHashBag()
    {
        Assert.assertEquals(Bags.mutable.of(), Functions0.newHashBag().value());
        Verify.assertInstanceOf(HashBag.class, Functions0.newHashBag().value());
    }

    @Test
    public void newUnifiedMap()
    {
        Assert.assertEquals(UnifiedMap.newMap(), Functions0.newUnifiedMap().value());
        Verify.assertInstanceOf(UnifiedMap.class, Functions0.newUnifiedMap().value());
    }

    @Test
    public void zeroInteger()
    {
        Assert.assertEquals(Integer.valueOf(0), Functions0.zeroInteger().value());
        Assert.assertEquals(Integer.valueOf(0), Functions0.value(0).value());
    }

    @Test
    public void zeroAtomicInteger()
    {
        Verify.assertInstanceOf(AtomicInteger.class, Functions0.zeroAtomicInteger().value());
        Assert.assertEquals(0, Functions0.zeroAtomicInteger().value().get());
    }

    @Test
    public void zeroAtomicLong()
    {
        Verify.assertInstanceOf(AtomicLong.class, Functions0.zeroAtomicLong().value());
        Assert.assertEquals(0, Functions0.zeroAtomicLong().value().get());
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
