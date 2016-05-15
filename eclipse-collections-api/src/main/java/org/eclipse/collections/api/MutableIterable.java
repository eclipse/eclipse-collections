/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.PartitionMutableIterable;
import org.eclipse.collections.api.tuple.Pair;

public interface MutableIterable<T> extends RichIterable<T>
{
    @Override
    MutableIterable<T> tap(Procedure<? super T> procedure);

    @Override
    MutableIterable<T> select(Predicate<? super T> predicate);

    @Override
    <P> MutableIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    MutableIterable<T> reject(Predicate<? super T> predicate);

    @Override
    <P> MutableIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    PartitionMutableIterable<T> partition(Predicate<? super T> predicate);

    @Override
    <P> PartitionMutableIterable<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> MutableIterable<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V> MutableIterable<V> collect(Function<? super T, ? extends V> function);

    /*
    @Override
    MutableBooleanIterable collectBoolean(BooleanFunction<? super T> booleanFunction);

    @Override
    MutableByteIterable collectByte(ByteFunction<? super T> byteFunction);

    @Override
    MutableCharIterable collectChar(CharFunction<? super T> charFunction);

    @Override
    MutableDoubleIterable collectDouble(DoubleFunction<? super T> doubleFunction);

    @Override
    MutableFloatIterable collectFloat(FloatFunction<? super T> floatFunction);

    @Override
    MutableIntIterable collectInt(IntFunction<? super T> intFunction);

    @Override
    MutableLongIterable collectLong(LongFunction<? super T> longFunction);

    @Override
    MutableShortIterable collectShort(ShortFunction<? super T> shortFunction);
    */

    @Override
    <P, V> MutableIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    /**
     * Returns a new MutableIterable with the results of applying the specified function to each element of the source
     * iterable, but only for elements that evaluate to true for the specified predicate.
     * <p>
     * <pre>e.g.
     * Lists.mutable.of().with(1, 2, 3).collectIf(Predicates.notNull(), Functions.getToString())
     * </pre>
     */
    @Override
    <V> MutableIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    @Override
    <V> MutableIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    /**
     * Returns an unmodifiable view of this iterable.  This method allows modules to provide users with "read-only"
     * access to internal iterables.  Query operations on the returned iterable "read through" to this iterable,
     * and attempts to modify the returned iterable, whether direct or via its iterator, result in an
     * <tt>UnsupportedOperationException</tt>.
     * <p>
     * The returned iterable does <i>not</i> pass the hashCode and equals operations through to the backing
     * iterable, but relies on <tt>Object</tt>'s <tt>equals</tt> and <tt>hashCode</tt> methods.  This is necessary to
     * preserve the contracts of these operations in the case that the backing iterable is a set or a list.<p>
     * <p>
     * The returned iterable will be serializable if this iterable is serializable.
     *
     * @return an unmodifiable view of this iterable.
     * @since 1.0 on MutableCollection
     * @since 8.0 on MutableIterable
     */
    MutableIterable<T> asUnmodifiable();

    /**
     * Returns a synchronized (thread-safe) iterable backed by this iterable.  In order to guarantee serial access,
     * it is critical that <strong>all</strong> access to the backing iterable is accomplished through the returned
     * iterable.
     * <p>
     * It is imperative that the user manually synchronize on the returned iterable when iterating over it using the
     * standard JDK iterator or JDK 5 for loop.
     * <pre>
     *  MutableIterable iterable = myIterable.asSynchronized();
     *     ...
     *  synchronized(iterable)
     *  {
     *      Iterator i = c.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *         foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     * <p>
     * The preferred way of iterating over a synchronized iterable is to use the iterable.forEach() method which is
     * properly synchronized internally.
     * <pre>
     *  MutableIterable iterable = myIterable.asSynchronized();
     *     ...
     *  iterable.forEach(new Procedure()
     *  {
     *      public void value(Object each)
     *      {
     *          ...
     *      }
     *  });
     * </pre>
     * <p>
     * The returned iterable does <i>not</i> pass the <tt>hashCode</tt> and <tt>equals</tt> operations through to the
     * backing iterable, but relies on <tt>Object</tt>'s equals and hashCode methods.  This is necessary to preserve
     * the contracts of these operations in the case that the backing iterable is a set or a list.
     * <p>
     * The returned iterable will be serializable if this iterable is serializable.
     *
     * @return a synchronized view of this iterable.
     * @since 1.0 on MutableCollection
     * @since 8.0 on MutableIterable
     */
    MutableIterable<T> asSynchronized();

    /**
     * Converts this MutableIterable to an ImmutableIterable.
     *
     * @since 1.0 on MutableCollection
     * @since 8.0 on MutableIterable
     */
    ImmutableIterable<T> toImmutable();

    @Override
    <V> MutableObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function);

    @Override
    <V> MutableObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function);

    @Override
    <V> MutableObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function);

    @Override
    <V> MutableObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function);

    @Override
    <V> MutableMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> MutableMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    @Override
    <V> MutableMapIterable<V, T> groupByUniqueKey(Function<? super T, ? extends V> function);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Override
    @Deprecated
    <S> MutableIterable<Pair<T, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Override
    @Deprecated
    MutableIterable<Pair<T, Integer>> zipWithIndex();

    @Override
    <K, V> MutableMap<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator);

    @Override
    <K, V> MutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator);
}
