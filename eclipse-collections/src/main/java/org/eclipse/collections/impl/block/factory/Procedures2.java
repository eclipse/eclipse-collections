/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function3;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.impl.block.procedure.checked.CheckedProcedure2;
import org.eclipse.collections.impl.block.procedure.checked.ThrowingProcedure2;

/**
 * Contains factory methods for creating {@link Procedure2} instances.
 */
public final class Procedures2
{
    public static final Procedure2<?, ?> ADD_TO_COLLECTION = new AddToCollection<>();
    public static final Procedure2<?, ?> REMOVE_FROM_COLLECTION = new RemoveFromCollection<>();

    private Procedures2()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    /**
     * Allows a lambda or anonymous inner class that needs to throw a checked exception to be safely wrapped as a
     * Procedure2 that will throw a RuntimeException, wrapping the checked exception that is the cause.
     */
    public static <T, P> Procedure2<T, P> throwing(ThrowingProcedure2<T, P> throwingProcedure2)
    {
        return new ThrowingProcedure2Adapter<>(throwingProcedure2);
    }

    /**
     * Allows a lambda or anonymous inner class that needs to throw a checked exception to be safely wrapped as a
     * Procedure2 that will throw a user specified RuntimeException based on the provided function. The function
     * is passed the current element and the checked exception that was thrown as context arguments.
     */
    public static <T1, T2> Procedure2<T1, T2> throwing(
            ThrowingProcedure2<T1, T2> throwingProcedure,
            Function3<T1, T2, ? super Throwable, ? extends RuntimeException> rethrow)
    {
        return (one, two) ->
        {
            try
            {
                throwingProcedure.safeValue(one, two);
            }
            catch (RuntimeException e)
            {
                throw e;
            }
            catch (Throwable t)
            {
                throw rethrow.value(one, two, t);
            }
        };
    }

    public static <T, P> Procedure2<T, P> fromProcedure(Procedure<? super T> procedure)
    {
        return new ProcedureAdapter<>(procedure);
    }

    public static <T> Procedure2<T, Collection<T>> addToCollection()
    {
        return (Procedure2<T, Collection<T>>) ADD_TO_COLLECTION;
    }

    public static <T> Procedure2<T, Collection<T>> removeFromCollection()
    {
        return (Procedure2<T, Collection<T>>) REMOVE_FROM_COLLECTION;
    }

    /**
     * Use with {@link org.eclipse.collections.api.RichIterable#aggregateInPlaceBy(Function, Function0, Procedure2)}
     *
     * @since 9.2.
     */
    public static <T> Procedure2<DoubleSummaryStatistics, T> summarizeDouble(DoubleFunction<T> function)
    {
        return (DoubleSummaryStatistics dss, T value) -> dss.accept(function.doubleValueOf(value));
    }

    /**
     * Use with {@link org.eclipse.collections.api.RichIterable#aggregateInPlaceBy(Function, Function0, Procedure2)}
     *
     * @since 9.2.
     */
    public static <T> Procedure2<DoubleSummaryStatistics, T> summarizeFloat(FloatFunction<T> function)
    {
        return (DoubleSummaryStatistics dss, T value) -> dss.accept((double) function.floatValueOf(value));
    }

    /**
     * Use with {@link org.eclipse.collections.api.RichIterable#aggregateInPlaceBy(Function, Function0, Procedure2)}
     *
     * @since 9.2.
     */
    public static <T> Procedure2<IntSummaryStatistics, T> summarizeInt(IntFunction<T> function)
    {
        return (IntSummaryStatistics iss, T value) -> iss.accept(function.intValueOf(value));
    }

    /**
     * Use with {@link org.eclipse.collections.api.RichIterable#aggregateInPlaceBy(Function, Function0, Procedure2)}
     *
     * @since 9.2.
     */
    public static <T> Procedure2<LongSummaryStatistics, T> summarizeLong(LongFunction<T> function)
    {
        return (LongSummaryStatistics lss, T value) -> lss.accept(function.longValueOf(value));
    }

    private static final class ProcedureAdapter<T, P> implements Procedure2<T, P>
    {
        private static final long serialVersionUID = 1L;
        private final Procedure<? super T> procedure;

        private ProcedureAdapter(Procedure<? super T> procedure)
        {
            this.procedure = procedure;
        }

        @Override
        public void value(T each, P parameter)
        {
            this.procedure.value(each);
        }
    }

    private static class AddToCollection<T> implements Procedure2<T, Collection<T>>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void value(T each, Collection<T> target)
        {
            target.add(each);
        }
    }

    private static class RemoveFromCollection<T> implements Procedure2<T, Collection<T>>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void value(T each, Collection<T> target)
        {
            target.remove(each);
        }
    }

    private static final class ThrowingProcedure2Adapter<T, P> extends CheckedProcedure2<T, P>
    {
        private static final long serialVersionUID = 1L;
        private final ThrowingProcedure2<T, P> throwingProcedure2;

        private ThrowingProcedure2Adapter(ThrowingProcedure2<T, P> throwingProcedure2)
        {
            this.throwingProcedure2 = throwingProcedure2;
        }

        @Override
        public void safeValue(T object, P parameter) throws Exception
        {
            this.throwingProcedure2.safeValue(object, parameter);
        }
    }
}
