/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api;

import java.util.function.Consumer;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.ordered.OrderedIterable;

/**
 * The base interface for all Eclipse Collections.  All Eclipse Collections are internally iterable, and this interface provides
 * the base set of internal iterators that every Eclipse collection should implement.
 */
public interface InternalIterable<T>
        extends Iterable<T>
{
    /**
     * The procedure is executed for each element in the iterable.
     * <p>
     * Example using a Java 8 lambda:
     * <pre>
     * people.forEach(Procedures.cast(person -> LOGGER.info(person.getName())));
     * </pre>
     * <p>
     * Example using an anonymous inner class:
     * <pre>
     * people.forEach(new Procedure&lt;Person&gt;()
     * {
     *     public void value(Person person)
     *     {
     *         LOGGER.info(person.getName());
     *     }
     * });
     * </pre>
     * NOTE: This method started to conflict with {@link Iterable#forEach(java.util.function.Consumer)}
     * since Java 1.8. It is recommended to use {@link RichIterable#each(Procedure)} instead to avoid casting to Procedure.
     *
     * @see RichIterable#each(Procedure)
     * @see Iterable#forEach(java.util.function.Consumer)
     */
    @SuppressWarnings("UnnecessaryFullyQualifiedName")
    void forEach(Procedure<? super T> procedure);

    @Override
    default void forEach(Consumer<? super T> consumer)
    {
        Procedure<? super T> procedure = consumer::accept;
        this.forEach(procedure);
    }

    /**
     * Iterates over the iterable passing each element and the current relative int index to the specified instance of
     * ObjectIntProcedure.
     * <p>
     * Example using a Java 8 lambda:
     * <pre>
     * people.forEachWithIndex((Person person, int index) -> LOGGER.info("Index: " + index + " person: " + person.getName()));
     * </pre>
     * <p>
     * Example using an anonymous inner class:
     * <pre>
     * people.forEachWithIndex(new ObjectIntProcedure&lt;Person&gt;()
     * {
     *     public void value(Person person, int index)
     *     {
     *         LOGGER.info("Index: " + index + " person: " + person.getName());
     *     }
     * });
     * </pre>
     *
     * @deprecated in 6.0. Use {@link OrderedIterable#forEachWithIndex(ObjectIntProcedure)} instead.
     */
    @Deprecated
    void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure);

    /**
     * The procedure2 is evaluated for each element in the iterable with the specified parameter provided
     * as the second argument.
     * <p>
     * Example using a Java 8 lambda:
     * <pre>
     * people.forEachWith((Person person, Person other) ->
     *     {
     *         if (person.isRelatedTo(other))
     *         {
     *              LOGGER.info(person.getName());
     *         }
     *     }, fred);
     * </pre>
     * <p>
     * Example using an anonymous inner class:
     * <pre>
     * people.forEachWith(new Procedure2&lt;Person, Person&gt;()
     * {
     *     public void value(Person person, Person other)
     *     {
     *         if (person.isRelatedTo(other))
     *         {
     *              LOGGER.info(person.getName());
     *         }
     *     }
     * }, fred);
     * </pre>
     */
    <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter);
}
