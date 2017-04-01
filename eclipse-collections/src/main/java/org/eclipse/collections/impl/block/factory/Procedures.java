/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory;

import java.io.IOException;
import java.io.PrintStream;

import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.block.procedure.CaseProcedure;
import org.eclipse.collections.impl.block.procedure.IfProcedure;
import org.eclipse.collections.impl.block.procedure.checked.CheckedProcedure;
import org.eclipse.collections.impl.block.procedure.checked.ThrowingProcedure;

/**
 * Factory class for commonly used procedures.
 */
public final class Procedures
{
    private Procedures()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    /**
     * Allows a Java 8 lambda and method to be used in a forEach method without requiring a cast.
     */
    public static <T> Procedure<T> cast(Procedure<T> procedure)
    {
        return procedure;
    }

    public static <T> Procedure<T> println(PrintStream stream)
    {
        return new PrintlnProcedure<>(stream);
    }

    public static <T> Procedure<T> append(Appendable appendable)
    {
        return new AppendProcedure<>(appendable);
    }

    /**
     * Allows a lambda or anonymous inner class that needs to throw a checked exception to be safely wrapped as a
     * Procedure that will throw a RuntimeException, wrapping the checked exception that is the cause.
     */
    public static <T> Procedure<T> throwing(ThrowingProcedure<T> throwingProcedure)
    {
        return new ThrowingProcedureAdapter<>(throwingProcedure);
    }

    /**
     * Allows a lambda or anonymous inner class that needs to throw a checked exception to be safely wrapped as a
     * Procedure that will throw a user specified RuntimeException based on the provided function. The function
     * is passed the current element and the checked exception that was thrown as context arguments.
     */
    public static <T> Procedure<T> throwing(
            ThrowingProcedure<T> throwingProcedure,
            Function2<T, ? super Throwable, ? extends RuntimeException> rethrow)
    {
        return each ->
        {
            try
            {
                throwingProcedure.safeValue(each);
            }
            catch (RuntimeException e)
            {
                throw e;
            }
            catch (Throwable t)
            {
                throw rethrow.value(each, t);
            }
        };
    }

    /**
     * @deprecated since 1.2 - Inlineable
     */
    @Deprecated
    public static <T> Procedure<T> fromProcedureWithInt(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        return Procedures.fromObjectIntProcedure(objectIntProcedure);
    }

    public static <T> Procedure<T> fromObjectIntProcedure(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        return new ObjectIntProcedureAdapter<>(objectIntProcedure);
    }

    public static <T> Procedure<T> ifTrue(Predicate<? super T> predicate, Procedure<? super T> block)
    {
        return new IfProcedure<>(predicate, block);
    }

    public static <T> Procedure<T> ifElse(
            Predicate<? super T> predicate,
            Procedure<? super T> trueProcedure,
            Procedure<? super T> falseProcedure)
    {
        return new IfProcedure<>(predicate, trueProcedure, falseProcedure);
    }

    public static <T> CaseProcedure<T> caseDefault(Procedure<? super T> defaultProcedure)
    {
        return new CaseProcedure<>(defaultProcedure);
    }

    public static <T> CaseProcedure<T> caseDefault(
            Procedure<? super T> defaultProcedure,
            Predicate<? super T> predicate,
            Procedure<? super T> procedure)
    {
        return Procedures.<T>caseDefault(defaultProcedure).addCase(predicate, procedure);
    }

    public static <T> Procedure<T> synchronizedEach(Procedure<T> procedure)
    {
        return new Procedures.SynchronizedProcedure<>(procedure);
    }

    public static <T, P> Procedure<T> bind(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        return new BindProcedure<>(procedure, parameter);
    }

    private static final class PrintlnProcedure<T> implements Procedure<T>
    {
        private static final long serialVersionUID = 1L;

        private final PrintStream stream;

        private PrintlnProcedure(PrintStream stream)
        {
            this.stream = stream;
        }

        @Override
        public void value(T each)
        {
            this.stream.println(each);
        }
    }

    private static final class AppendProcedure<T> implements Procedure<T>
    {
        private static final long serialVersionUID = 1L;

        private final Appendable appendable;

        private AppendProcedure(Appendable appendable)
        {
            this.appendable = appendable;
        }

        @Override
        public void value(T each)
        {
            try
            {
                this.appendable.append(String.valueOf(each));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String toString()
        {
            return this.appendable.toString();
        }
    }

    private static final class ObjectIntProcedureAdapter<T> implements Procedure<T>
    {
        private static final long serialVersionUID = 2L;
        private int count;
        private final ObjectIntProcedure<? super T> objectIntProcedure;

        private ObjectIntProcedureAdapter(ObjectIntProcedure<? super T> objectIntProcedure)
        {
            this.objectIntProcedure = objectIntProcedure;
        }

        @Override
        public void value(T each)
        {
            this.objectIntProcedure.value(each, this.count);
            this.count++;
        }
    }

    public static final class SynchronizedProcedure<T> implements Procedure<T>
    {
        private static final long serialVersionUID = 1L;
        private final Procedure<T> procedure;

        private SynchronizedProcedure(Procedure<T> procedure)
        {
            this.procedure = procedure;
        }

        @Override
        public void value(T each)
        {
            if (each == null)
            {
                this.procedure.value(null);
            }
            else
            {
                synchronized (each)
                {
                    this.procedure.value(each);
                }
            }
        }
    }

    private static final class BindProcedure<T, P> implements Procedure<T>
    {
        private static final long serialVersionUID = 1L;
        private final Procedure2<? super T, ? super P> procedure;
        private final P parameter;

        private BindProcedure(Procedure2<? super T, ? super P> procedure, P parameter)
        {
            this.procedure = procedure;
            this.parameter = parameter;
        }

        @Override
        public void value(T each)
        {
            this.procedure.value(each, this.parameter);
        }
    }

    private static final class ThrowingProcedureAdapter<T> extends CheckedProcedure<T>
    {
        private static final long serialVersionUID = 1L;
        private final ThrowingProcedure<T> throwingProcedure;

        private ThrowingProcedureAdapter(ThrowingProcedure<T> throwingProcedure)
        {
            this.throwingProcedure = throwingProcedure;
        }

        @Override
        public void safeValue(T object) throws Exception
        {
            this.throwingProcedure.safeValue(object);
        }
    }
}
