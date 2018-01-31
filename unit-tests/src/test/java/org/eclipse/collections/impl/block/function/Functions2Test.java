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

import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.impl.block.factory.Functions2;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Functions2Test
{
    @Test
    public void throwing()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () -> Functions2.throwing((a, b) -> { throw new IOException(); }).value(null, null));
    }

    @Test
    public void throwingWithUserSpecifiedException()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () -> {
                    Functions2.throwing(
                            (one, two) -> { throw new IOException(); },
                            (one, two, ce) -> new RuntimeException(ce)).value(null, null);
                });
        Verify.assertThrowsWithCause(
                MyRuntimeException.class,
                IOException.class,
                () -> {
                    Functions2.throwing(
                            (one, two) -> { throw new IOException(); },
                            this::throwMyException).value(null, null);
                });
        Verify.assertThrows(
                NullPointerException.class,
                () -> {
                    Functions2.throwing(
                            (one, two) -> { throw new NullPointerException(); },
                            this::throwMyException).value(null, null);
                });
    }

    private MyRuntimeException throwMyException(Object one, Object two, Throwable exception)
    {
        return new MyRuntimeException(String.valueOf(one) + String.valueOf(two), exception);
    }

    @Test
    public void asFunction2Function()
    {
        Function2<Integer, Object, String> block = Functions2.fromFunction(String::valueOf);
        Assert.assertEquals("1", block.value(1, null));
    }

    @Test
    public void plusInteger()
    {
        Function2<Integer, Integer, Integer> plusInteger = Functions2.integerAddition();
        Assert.assertEquals(Integer.valueOf(5), plusInteger.value(2, 3));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(Functions2.class);
    }

    private static class MyRuntimeException extends RuntimeException
    {
        MyRuntimeException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }
}
