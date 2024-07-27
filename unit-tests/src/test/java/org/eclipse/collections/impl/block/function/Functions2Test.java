/*
 * Copyright (c) 2022 Goldman Sachs and others.
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
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Functions2;
import org.eclipse.collections.impl.block.function.checked.ThrowingFunction2;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Functions2Test
{
    @Test
    public void throwing()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () -> Functions2.throwing((a, b) ->
                {
                    throw new IOException();
                }).value(null, null));
    }

    @Test
    public void throwingWithSuccessfulCompletion()
    {
        ThrowingFunction2<String, String, String> throwingFunction2 =
                (argument1, argument2) -> argument1.concat(argument2);

        assertEquals("abcdef", Functions2.throwing(throwingFunction2).value("abc", "def"));
    }

    @Test
    public void min()
    {
        Function2<Integer, Integer, Integer> minFunction = Functions2.min(Comparators.naturalOrder());
        assertEquals(Integer.valueOf(1), minFunction.value(5, 1));
        assertEquals(Integer.valueOf(1), minFunction.value(1, 5));
        assertEquals(Integer.valueOf(2), minFunction.value(2, 2));
    }

    @Test
    public void max()
    {
        Function2<Integer, Integer, Integer> maxFunction = Functions2.max(Comparators.naturalOrder());
        assertEquals(Integer.valueOf(5), maxFunction.value(5, 1));
        assertEquals(Integer.valueOf(5), maxFunction.value(1, 5));
        assertEquals(Integer.valueOf(5), maxFunction.value(5, 5));
    }

    @Test
    public void minBy()
    {
        Function2<Twin<Integer>, Twin<Integer>, Twin<Integer>> minBy = Functions2.minBy(Functions.firstOfPair());
        Twin<Integer> twinOne = Tuples.twin(1, 5);
        Twin<Integer> twinTwo = Tuples.twin(0, 10);
        assertEquals(twinTwo, minBy.value(twinOne, twinTwo));
        assertEquals(twinTwo, minBy.value(twinTwo, twinOne));
        assertEquals(twinOne, minBy.value(twinOne, twinOne));
    }

    @Test
    public void maxBy()
    {
        Function2<Twin<Integer>, Twin<Integer>, Twin<Integer>> minBy = Functions2.maxBy(Functions.firstOfPair());
        Twin<Integer> twinOne = Tuples.twin(1, 5);
        Twin<Integer> twinTwo = Tuples.twin(0, 10);
        assertEquals(twinOne, minBy.value(twinOne, twinTwo));
        assertEquals(twinOne, minBy.value(twinTwo, twinOne));
        assertEquals(twinOne, minBy.value(twinOne, twinOne));
    }

    @Test
    public void throwingWithUserSpecifiedException()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () ->
                {
                    Functions2.throwing(
                            (one, two) ->
                            {
                                throw new IOException();
                            },
                            (one, two, ce) -> new RuntimeException(ce)).value(null, null);
                });
        Verify.assertThrowsWithCause(
                MyRuntimeException.class,
                IOException.class,
                () ->
                {
                    Functions2.throwing(
                            (one, two) ->
                            {
                                throw new IOException();
                            },
                            this::throwMyException).value(null, null);
                });
        assertThrows(
                NullPointerException.class,
                () ->
                {
                    Functions2.throwing(
                            (one, two) ->
                            {
                                throw new NullPointerException();
                            },
                            this::throwMyException).value(null, null);
                });
    }

    private MyRuntimeException throwMyException(Object one, Object two, Throwable exception)
    {
        return new MyRuntimeException(String.valueOf(one) + two, exception);
    }

    @Test
    public void asFunction2Function()
    {
        Function2<Integer, Object, String> block = Functions2.fromFunction(String::valueOf);
        assertEquals("1", block.value(1, null));
    }

    @Test
    public void plusInteger()
    {
        Function2<Integer, Integer, Integer> plusInteger = Functions2.integerAddition();
        assertEquals(Integer.valueOf(5), plusInteger.value(2, 3));
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
