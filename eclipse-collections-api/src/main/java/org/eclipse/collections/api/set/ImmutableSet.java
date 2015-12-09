/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.api.set;

import java.util.Set;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.ByteFunction;
import org.eclipse.collections.api.block.function.primitive.CharFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.set.PartitionImmutableSet;
import org.eclipse.collections.api.set.primitive.ImmutableBooleanSet;
import org.eclipse.collections.api.set.primitive.ImmutableByteSet;
import org.eclipse.collections.api.set.primitive.ImmutableCharSet;
import org.eclipse.collections.api.set.primitive.ImmutableDoubleSet;
import org.eclipse.collections.api.set.primitive.ImmutableFloatSet;
import org.eclipse.collections.api.set.primitive.ImmutableIntSet;
import org.eclipse.collections.api.set.primitive.ImmutableLongSet;
import org.eclipse.collections.api.set.primitive.ImmutableShortSet;
import org.eclipse.collections.api.tuple.Pair;
import net.jcip.annotations.Immutable;

/**
 * ImmutableSet is the non-modifiable equivalent interface to {@link MutableSet}. {@link MutableSet#toImmutable()} will
 * give you an appropriately trimmed implementation of ImmutableSet.  All ImmutableSet implementations must implement
 * the java.util.Set interface so they can satisfy the equals() contract and be compared against other set structures
 * like UnifiedSet or HashSet.
 */
@Immutable
public interface ImmutableSet<T>
        extends UnsortedSetIterable<T>, ImmutableSetIterable<T>
{
    ImmutableSet<T> newWith(T element);

    ImmutableSet<T> newWithout(T element);

    ImmutableSet<T> newWithAll(Iterable<? extends T> elements);

    ImmutableSet<T> newWithoutAll(Iterable<? extends T> elements);

    ImmutableSet<T> tap(Procedure<? super T> procedure);

    ImmutableSet<T> select(Predicate<? super T> predicate);

    <P> ImmutableSet<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    ImmutableSet<T> reject(Predicate<? super T> predicate);

    <P> ImmutableSet<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionImmutableSet<T> partition(Predicate<? super T> predicate);

    <P> PartitionImmutableSet<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <S> ImmutableSet<S> selectInstancesOf(Class<S> clazz);

    <V> ImmutableSet<V> collect(Function<? super T, ? extends V> function);

    ImmutableBooleanSet collectBoolean(BooleanFunction<? super T> booleanFunction);

    ImmutableByteSet collectByte(ByteFunction<? super T> byteFunction);

    ImmutableCharSet collectChar(CharFunction<? super T> charFunction);

    ImmutableDoubleSet collectDouble(DoubleFunction<? super T> doubleFunction);

    ImmutableFloatSet collectFloat(FloatFunction<? super T> floatFunction);

    ImmutableIntSet collectInt(IntFunction<? super T> intFunction);

    ImmutableLongSet collectLong(LongFunction<? super T> longFunction);

    ImmutableShortSet collectShort(ShortFunction<? super T> shortFunction);

    <P, V> ImmutableSet<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> ImmutableSet<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> ImmutableSet<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    <V> ImmutableSetMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> ImmutableSetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Deprecated
    <S> ImmutableSet<Pair<T, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Deprecated
    ImmutableSet<Pair<T, Integer>> zipWithIndex();

    Set<T> castToSet();

    ImmutableSet<T> union(SetIterable<? extends T> set);

    ImmutableSet<T> intersect(SetIterable<? extends T> set);

    ImmutableSet<T> difference(SetIterable<? extends T> subtrahendSet);

    ImmutableSet<T> symmetricDifference(SetIterable<? extends T> setB);

    ImmutableSet<UnsortedSetIterable<T>> powerSet();
}
