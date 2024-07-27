/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.block.procedure.checked.ThrowingProcedure;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ProceduresTest
{
    @Test
    public void throwing()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () -> Procedures.throwing(a -> { throw new IOException(); }).value(null));
    }

    @Test
    public void throwingWithSuccessfulCompletion()
    {
        StringBuilder builder = new StringBuilder("a");
        ThrowingProcedure<StringBuilder> throwingProcedure = stringBuilder -> stringBuilder.append("Visited");
        Procedures.throwing(throwingProcedure).value(builder);
        assertEquals("aVisited", builder.toString());
    }

    @Test
    public void throwingWithUserSpecifiedException()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () -> Procedures.throwing(
                        a -> { throw new IOException(); },
                        (each, ce) -> new RuntimeException(ce))
                        .value(null));
        Verify.assertThrowsWithCause(
                MyRuntimeException.class,
                IOException.class,
                () -> Procedures.throwing(
                        a -> { throw new IOException(); },
                        this::throwMyException)
                        .value(null));
        assertThrows(
                NullPointerException.class,
                () -> Procedures.throwing(
                        a -> { throw new NullPointerException(); },
                        this::throwMyException)
                        .value(null));
    }

    private MyRuntimeException throwMyException(Object each, Throwable exception)
    {
        return new MyRuntimeException(String.valueOf(each), exception);
    }

    @Test
    public void println()
    {
        MutableList<Integer> newAssertValues = Lists.mutable.of(1);
        try (TestPrintStream stream = new TestPrintStream(newAssertValues))
        {
            Procedure<Integer> result = Procedures.println(stream);
            result.value(1);
            stream.shutdown();
        }
        Verify.assertEmpty(newAssertValues);
    }

    @Test
    public void append()
    {
        StringBuilder appendable = new StringBuilder("init");
        Procedure<Integer> appender = Procedures.append(appendable);
        appender.value(1);
        appender.value(2);
        appender.value(3);
        assertEquals("init123", appendable.toString());
        assertEquals("init123", appender.toString());
    }

    @Test
    public void appendWithException()
    {
        assertThrows(RuntimeException.class, () -> Procedures.append(new Appendable()
        {
            @Override
            public Appendable append(CharSequence csq) throws IOException
            {
                throw new IOException();
            }

            @Override
            public Appendable append(CharSequence csq, int start, int end) throws IOException
            {
                throw new IOException();
            }

            @Override
            public Appendable append(char c) throws IOException
            {
                throw new IOException();
            }
        }).value("abc"));
    }

    @Test
    public void fromObjectIntProcedure()
    {
        ImmutableList<String> expectedResults = Lists.immutable.of("zero0", "one1", "two2");

        MutableList<String> actualResults = Lists.mutable.of();
        ObjectIntProcedure<String> objectIntProcedure = (each, index) -> actualResults.add(each + index);

        ImmutableList<String> numberStrings = Lists.immutable.of("zero", "one", "two");
        Procedure<String> procedure = Procedures.fromObjectIntProcedure(objectIntProcedure);
        numberStrings.forEach(procedure);

        assertEquals(expectedResults, actualResults);
    }

    @Test
    public void fromProcedureWithInt()
    {
        MutableList<ObjectIntPair> list = Lists.mutable.empty();
        Procedure<String> procedure =
                Procedures.fromObjectIntProcedure((each, parameter) -> list.add(PrimitiveTuples.pair(each, parameter)));
        procedure.value("strOne");
        procedure.value("strTwo");
        assertEquals(Lists.mutable.of(PrimitiveTuples.pair("strOne", 0),
                PrimitiveTuples.pair("strTwo", 1)), list);
    }

    @Test
    public void noop()
    {
        Procedure<Object> noop = Procedures.noop();
        noop.value("abc");
        assertNotNull(noop);
    }

    @Test
    public void synchronizedEach()
    {
        MutableList<Integer> integers = Interval.oneTo(10).toList();
        integers.add(null);
        MutableList<Integer> result = Lists.mutable.of();
        integers.forEach(Procedures.synchronizedEach(CollectionAddProcedure.on(result)));
        assertEquals(result, integers);
    }

    @Test
    public void ifElse()
    {
        MutableMap<String, Integer> pathCalled = UnifiedMap.newWithKeysValues("result", 0);
        Procedure<Integer> ifBlock = each -> pathCalled.put("result", 1);
        Procedure<Integer> elseBlock = each -> pathCalled.put("result", -1);

        Procedures.ifElse(ignored -> true, ifBlock, elseBlock).value(1);
        Verify.assertContainsKeyValue("result", 1, pathCalled);

        Procedures.ifElse(ignored -> false, ifBlock, elseBlock).value(1);
        Verify.assertContainsKeyValue("result", -1, pathCalled);
    }

    @Test
    public void caseDefault()
    {
        Procedure<Object> defaultBlock = each -> { throw new BlockCalledException(); };
        CaseProcedure<Object> undertest = Procedures.caseDefault(defaultBlock);
        assertThrows(BlockCalledException.class, () -> undertest.value(1));
    }

    @Test
    public void caseDefaultWithACase()
    {
        Procedure<Object> caseBlock = each -> { throw new BlockCalledException(); };
        CaseProcedure<Object> undertest = Procedures.caseDefault(DoNothingProcedure.DO_NOTHING, ignored -> true, caseBlock);
        assertThrows(BlockCalledException.class, () -> undertest.value(1));
    }

    private static final class TestPrintStream
            extends PrintStream
    {
        private final List<Integer> assertValues;

        private TestPrintStream(List<Integer> newAssertValues)
        {
            super(TestPrintStream.initOutputStream());
            this.assertValues = newAssertValues;
        }

        private static OutputStream initOutputStream()
        {
            try
            {
                return new ObjectOutputStream(new ByteArrayOutputStream());
            }
            catch (IOException ex)
            {
                fail("Failed to marshal an object: " + ex.getMessage());
            }
            return null;
        }

        @Override
        public void println(Object x)
        {
            super.println(x);
            assertEquals(this.assertValues.remove(0), x);
        }

        private void shutdown()
        {
            this.flush();
            this.close();
        }
    }

    private static class BlockCalledException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(Procedures.class);
    }

    private static class MyRuntimeException extends RuntimeException
    {
        MyRuntimeException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }
}
