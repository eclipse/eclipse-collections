/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.api.map;

import java.util.Map;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.partition.PartitionMutableCollection;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 6.0
 */
public interface MutableMapIterable<K, V> extends MapIterable<K, V>, Map<K, V>
{
    /**
     * This method allows mutable map the ability to add an element in the form of Pair<K,V>.
     *
     * @see #put(Object, Object)
     */
    V add(Pair<K, V> keyValuePair);

    /**
     * Remove an entry from the map at the specified {@code key}.
     *
     * @return The value removed from entry at key, or null if not found.
     * @see #remove(Object)
     */
    V removeKey(K key);

    /**
     * Get and return the value in the Map at the specified key.  Alternatively, if there is no value in the map at the key,
     * return the result of evaluating the specified Function0, and put that value in the map at the specified key.
     */
    V getIfAbsentPut(K key, Function0<? extends V> function);

    /**
     * Get and return the value in the Map at the specified key.  Alternatively, if there is no value in the map at the key,
     * return the specified value, and put that value in the map at the specified key.
     *
     * @since 5.0
     */
    V getIfAbsentPut(K key, V value);

    /**
     * Get and return the value in the Map at the specified key.  Alternatively, if there is no value in the map for that key
     * return the result of evaluating the specified Function using the specified key, and put that value in the
     * map at the specified key.
     */
    V getIfAbsentPutWithKey(K key, Function<? super K, ? extends V> function);

    /**
     * Get and return the value in the Map at the specified key.  Alternatively, if there is no value in the map for that key
     * return the result of evaluating the specified Function using the specified parameter, and put that value in the
     * map at the specified key.
     */
    <P> V getIfAbsentPutWith(K key, Function<? super P, ? extends V> function, P parameter);

    /**
     * Looks up the value associated with {@code key}, applies the {@code function} to it, and replaces the value. If there
     * is no value associated with {@code key}, starts it off with a value supplied by {@code factory}.
     */
    V updateValue(K key, Function0<? extends V> factory, Function<? super V, ? extends V> function);

    /**
     * Same as {@link #updateValue(Object, Function0, Function)} with a Function2 and specified parameter which is
     * passed to the function.
     */
    <P> V updateValueWith(K key, Function0<? extends V> factory, Function2<? super V, ? super P, ? extends V> function, P parameter);

    /**
     * This method allows mutable, fixed size, and immutable maps the ability to add elements to their existing
     * elements.  In order to support fixed size maps, a new instance of a map would have to be returned including the
     * keys and values of the original plus the additional key and value.  In the case of mutable maps, the original map
     * is modified and then returned.  In order to use this method properly with mutable and fixed size maps the
     * following approach must be taken:
     * <p>
     * <pre>
     * map = map.withKeyValue("new key", "new value");
     * </pre>
     * In the case of FixedSizeMap, a new instance will be returned by withKeyValue, and any variables that
     * previously referenced the original map will need to be redirected to reference the new instance.  In the case
     * of a FastMap or UnifiedMap, you will be replacing the reference to map with map, since FastMap and UnifiedMap
     * will both return "this" after calling put on themselves.
     *
     * @see #put(Object, Object)
     */
    MutableMapIterable<K, V> withKeyValue(K key, V value);

    /**
     * This method allows mutable, fixed size, and immutable maps the ability to add elements to their existing
     * elements.  In order to support fixed size maps, a new instance of a map would have to be returned including the
     * keys and values of the original plus all of the additional keys and values.  In the case of mutable maps, the
     * original map is modified and then returned.  In order to use this method properly with mutable and fixed size
     * maps the following approach must be taken:
     * <p>
     * <pre>
     * map = map.withAllKeyValues(FastList.newListWith(PairImpl.of("new key", "new value")));
     * </pre>
     * In the case of FixedSizeMap, a new instance will be returned by withAllKeyValues, and any variables that
     * previously referenced the original map will need to be redirected to reference the new instance.  In the case
     * of a FastMap or UnifiedMap, you will be replacing the reference to map with map, since FastMap and UnifiedMap
     * will both return "this" after calling put on themselves.
     *
     * @see #put(Object, Object)
     */
    MutableMapIterable<K, V> withAllKeyValues(Iterable<? extends Pair<? extends K, ? extends V>> keyValues);

    /**
     * Convenience var-args version of withAllKeyValues
     *
     * @see #withAllKeyValues(Iterable)
     */
    MutableMapIterable<K, V> withAllKeyValueArguments(Pair<? extends K, ? extends V>... keyValuePairs);

    /**
     * This method allows mutable, fixed size, and immutable maps the ability to remove elements from their existing
     * elements.  In order to support fixed size maps, a new instance of a map would have to be returned including the
     * keys and values of the original minus the key and value to be removed.  In the case of mutable maps, the original
     * map is modified and then returned.  In order to use this method properly with mutable and fixed size maps the
     * following approach must be taken:
     * <p>
     * <pre>
     * map = map.withoutKey("key");
     * </pre>
     * In the case of FixedSizeMap, a new instance will be returned by withoutKey, and any variables that previously
     * referenced the original map will need to be redirected to reference the new instance.  In the case of a FastMap
     * or UnifiedMap, you will be replacing the reference to map with map, since FastMap and UnifiedMap will both return
     * "this" after calling remove on themselves.
     *
     * @see #remove(Object)
     */
    MutableMapIterable<K, V> withoutKey(K key);

    /**
     * This method allows mutable, fixed size, and immutable maps the ability to remove elements from their existing
     * elements.  In order to support fixed size maps, a new instance of a map would have to be returned including the
     * keys and values of the original minus all of the keys and values to be removed.  In the case of mutable maps, the
     * original map is modified and then returned.  In order to use this method properly with mutable and fixed size
     * maps the following approach must be taken:
     * <p>
     * <pre>
     * map = map.withoutAllKeys(FastList.newListWith("key1", "key2"));
     * </pre>
     * In the case of FixedSizeMap, a new instance will be returned by withoutAllKeys, and any variables that previously
     * referenced the original map will need to be redirected to reference the new instance.  In the case of a FastMap
     * or UnifiedMap, you will be replacing the reference to map with map, since FastMap and UnifiedMap will both return
     * "this" after calling remove on themselves.
     *
     * @see #remove(Object)
     */
    MutableMapIterable<K, V> withoutAllKeys(Iterable<? extends K> keys);

    /**
     * Creates a new instance of the same type, using the default capacity and growth parameters.
     */
    MutableMapIterable<K, V> newEmpty();

    /**
     * Returns an unmodifiable view of this map. This method allows modules to provide users with "read-only" access to
     * internal maps. Any query operations on the returned map that "read through" to this map and attempt to modify the
     * returned map, whether direct or via its iterator, result in an {@link UnsupportedOperationException}.
     * The returned map will be <tt>Serializable</tt> if this map is <tt>Serializable</tt>.
     *
     * @return an unmodifiable view of this map.
     */
    MutableMapIterable<K, V> asUnmodifiable();

    /**
     * Returns a synchronized (thread-safe) map backed by the specified map.  In order to guarantee serial access, it is
     * critical that <strong>all</strong> access to the backing map is accomplished through the returned map.<p>
     * <p>
     * It is imperative that the user manually synchronize on the returned map when iterating over any of its collection
     * views:
     * <pre>
     *  MutableMap map = myMutableMap.asSynchronized();
     *      ...
     *  Set set = map.keySet();  // Needn't be in synchronized block
     *      ...
     *  synchronized(map)
     *  {  // Synchronizing on map, not set!
     *      Iterator i = s.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     * <p>
     * The preferred way of iterating over a synchronized collection is to use the collection.forEach() method which is
     * properly synchronized internally.
     * <pre>
     *  MutableMap map = myMutableMap.asSynchronized();
     *      ...
     *  Set set = map.keySet();  // Needn't be in synchronized block
     *     ...
     *  Iterate.forEach(set, new Procedure()
     *  {
     *      public void value(Object each)
     *      {
     *          ...
     *      }
     *  });
     * </pre>
     * <p>
     * The returned map will be serializable if the specified map is serializable.
     */
    MutableMapIterable<K, V> asSynchronized();

    /**
     * Returns an immutable copy of this map.
     * If the map is immutable, it returns itself.
     */
    ImmutableMapIterable<K, V> toImmutable();

    // TODO
    // MutableSetIterable<K> keySet();

    MutableMapIterable<K, V> tap(Procedure<? super V> procedure);

    MutableMapIterable<V, K> flipUniqueValues();

    MutableMultimap<V, K> flip();

    MutableMapIterable<K, V> select(Predicate2<? super K, ? super V> predicate);

    MutableMapIterable<K, V> reject(Predicate2<? super K, ? super V> predicate);

    <K2, V2> MutableMapIterable<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function);

    <R> MutableMapIterable<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    MutableCollection<V> select(Predicate<? super V> predicate);

    <P> MutableCollection<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    MutableCollection<V> reject(Predicate<? super V> predicate);

    <P> MutableCollection<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    PartitionMutableCollection<V> partition(Predicate<? super V> predicate);

    <S> MutableCollection<S> selectInstancesOf(Class<S> clazz);

    <V1> MutableMultimap<V1, V> groupBy(Function<? super V, ? extends V1> function);

    <V1> MutableMultimap<V1, V> groupByEach(Function<? super V, ? extends Iterable<V1>> function);

    <V1> MutableMapIterable<V1, V> groupByUniqueKey(Function<? super V, ? extends V1> function);

    <S> MutableCollection<Pair<V, S>> zip(Iterable<S> that);

    MutableCollection<Pair<V, Integer>> zipWithIndex();

    <KK, VV> MutableMapIterable<KK, VV> aggregateInPlaceBy(Function<? super V, ? extends KK> groupBy, Function0<? extends VV> zeroValueFactory, Procedure2<? super VV, ? super V> mutatingAggregator);

    <KK, VV> MutableMapIterable<KK, VV> aggregateBy(Function<? super V, ? extends KK> groupBy, Function0<? extends VV> zeroValueFactory, Function2<? super VV, ? super V, ? extends VV> nonMutatingAggregator);
}
