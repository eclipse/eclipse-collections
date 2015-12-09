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

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.tuple.Pair;

/**
 * A Read-only Map API, with the minor exception inherited from java.lang.Iterable.  The method map.iterator().remove()
 * will throw an UnsupportedOperationException.
 */
public interface MapIterable<K, V> extends RichIterable<V>
{
    /**
     * @see Map#get(Object)
     */
    V get(Object key);

    /**
     * @see Map#containsKey(Object)
     */
    boolean containsKey(Object key);

    /**
     * @see Map#containsValue(Object)
     */
    boolean containsValue(Object value);

    /**
     * Calls the procedure with each <em>value</em> of the map.
     * <pre>
     *     Set&lt;String&gt; result = UnifiedSet.newSet();
     *     MutableMap&lt;Integer, String&gt; map = this.newMapWithKeysValues(1, "One", 2, "Two", 3, "Three", 4, "Four");
     *     map.<b>forEachValue</b>(new CollectionAddProcedure&lt;String&gt;(result));
     *     Verify.assertSetsEqual(UnifiedSet.newSetWith("One", "Two", "Three", "Four"), result);
     * </pre>
     */
    void forEachValue(Procedure<? super V> procedure);

    /**
     * Executes the Procedure for each value of the map and returns {@code this}.
     * <p>
     * <pre>e.g.
     * return peopleByCity.<b>tap</b>(new Procedure<Person>()
     * {
     *     public void value(Person person)
     *     {
     *         LOGGER.info(person.getName());
     *     }
     * });
     * </pre>
     *
     * @see #forEach(Procedure)
     * @since 6.0
     */
    MapIterable<K, V> tap(Procedure<? super V> procedure);

    /**
     * Calls the {@code procedure} with each <em>key</em> of the map.
     * <pre>
     *     final Collection&lt;Integer&gt; result = new ArrayList&lt;Integer&gt;();
     *     MutableMap&lt;Integer, String&gt; map = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
     *     map.<b>forEachKey</b>(new CollectionAddProcedure&lt;Integer&gt;(result));
     *     Verify.assertContainsAll(result, 1, 2, 3);
     * </pre>
     */
    void forEachKey(Procedure<? super K> procedure);

    /**
     * Calls the {@code procedure} with each <em>key-value</em> pair of the map.
     * <pre>
     *     final Collection&lt;String&gt; collection = new ArrayList&lt;String&gt;();
     *     MutableMap&lt;Integer, String&gt; map = this.newMapWithKeysValues(1, "One", 2, "Two", 3, "Three");
     *     map.<b>forEachKeyValue</b>(new Procedure2&lt;Integer, String&gt;()
     *     {
     *         public void value(final Integer key, final String value)
     *         {
     *             collection.add(String.valueOf(key) + value);
     *         }
     *     });
     *     Verify.assertContainsAll(collection, "1One", "2Two", "3Three");
     * </pre>
     */
    void forEachKeyValue(Procedure2<? super K, ? super V> procedure);

    /**
     * Return the MapIterable that is obtained by flipping the direction of this map and making the associations
     * from value to key.
     * <pre>
     *     MapIterable<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
     *     MapIterable<String, Integer> result = map.flipUniqueValues();
     *     Assert.assertTrue(result.equals(UnifiedMap.newWithKeysValues("1", 1, "2", 2, "3", 3)));
     * </pre>
     *
     * @throws IllegalStateException if the MapIterable contains duplicate values.
     * @since 5.0
     */
    MapIterable<V, K> flipUniqueValues();

    /**
     * Return the value in the Map that corresponds to the specified key, or if there is no value at the key, return the
     * result of evaluating the specified Function0.
     */
    V getIfAbsent(K key, Function0<? extends V> function);

    /**
     * Return the value in the Map that corresponds to the specified key, or if there is no value at the key, return {@code value}.
     */
    V getIfAbsentValue(K key, V value);

    /**
     * Return the value in the Map that corresponds to the specified key, or if there is no value at the key, return the
     * result of evaluating the specified function and parameter.
     */
    <P> V getIfAbsentWith(K key, Function<? super P, ? extends V> function, P parameter);

    /**
     * If there is a value in the Map that corresponds to the specified key return the result of applying the specified
     * Function on the value, otherwise return null.
     */
    <A> A ifPresentApply(K key, Function<? super V, ? extends A> function);

    /**
     * Returns an unmodifiable lazy iterable wrapped around the keySet for the map
     */
    RichIterable<K> keysView();

    /**
     * Returns an unmodifiable lazy iterable wrapped around the values for the map
     */
    RichIterable<V> valuesView();

    /**
     * Returns an unmodifiable lazy iterable of key/value pairs wrapped around the entrySet for the map
     */
    RichIterable<Pair<K, V>> keyValuesView();

    /**
     * Given a map from Domain -> Range return a multimap from Range -> Domain. We chose the name 'flip'
     * rather than 'invert' or 'transpose' since this method does not have the property of applying twice
     * returns the original.
     * <p>
     * Since the keys in the input are unique, the values in the output are unique, so the return type should
     * be a SetMultimap. However since SetMultimap and SortedSetMultimap don't inherit from one another, SetMultimap
     * here does not allow SortedMapIterable to have a SortedSetMultimap return. Thus we compromise and call this
     * Multimap, even though all implementations will be a SetMultimap or SortedSetMultimap.
     *
     * @since 5.0
     */
    Multimap<V, K> flip();

    /**
     * For each key and value of the map the predicate is evaluated, if the result of the evaluation is true,
     * that key and value are returned in a new map.
     * <p>
     * <pre>e.g.
     * peopleByCity.select(new Predicate2&lt;City, Person&gt;()
     * {
     *     public boolean accept(City city, Person person)
     *     {
     *         return city.getName().equals("Anytown") && person.getLastName().equals("Smith");
     *     }
     * });
     * </pre>
     */
    MapIterable<K, V> select(Predicate2<? super K, ? super V> predicate);

    /**
     * For each key and value of the map the predicate is evaluated, if the result of the evaluation is false,
     * that key and value are returned in a new map.
     * <p>
     * <pre>e.g.
     * peopleByCity.reject(new Predicate2&lt;City, Person&gt;()
     * {
     *     public boolean accept(City city, Person person)
     *     {
     *         return city.getName().equals("Anytown") && person.getLastName().equals("Smith");
     *     }
     * });
     * </pre>
     */
    MapIterable<K, V> reject(Predicate2<? super K, ? super V> predicate);

    /**
     * For each key and value of the map the function is evaluated.  The results of these evaluations are returned in
     * a new map.  The map returned will use the values projected from the function rather than the original values.
     * <p>
     * <pre>e.g.
     * peopleByCity.collect(new Function2&lt;City, Person, String&gt;()
     * {
     *     public String value(City city, Person person)
     *     {
     *         return Pair.of(city.getCountry(), person.getAddress().getCity());
     *     }
     * });
     * </pre>
     */
    <K2, V2> MapIterable<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function);

    /**
     * For each key and value of the map the function is evaluated.  The results of these evaluations are returned in
     * a new map.  The map returned will use the values projected from the function rather than the original values.
     * <p>
     * <pre>e.g.
     * peopleByCity.collectValues(new Function2&lt;City, Person, String&gt;()
     * {
     *     public String value(City city, Person person)
     *     {
     *         return person.getFirstName() + " " + person.getLastName();
     *     }
     * });
     * </pre>
     */
    <R> MapIterable<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    /**
     * Return the first key and value of the map for which the predicate evaluates to true when they are given
     * as arguments. The predicate will only be evaluated until such pair is found or until all of the keys and
     * values of the map have been used as arguments. That is, there may be keys and values of the map that are
     * never used as arguments to the predicate. The result is null if predicate does not evaluate to true for
     * any key/value combination.
     * <p>
     * <pre>e.g.
     * peopleByCity.detect(new Predicate2&lt;City, Person&gt;()
     * {
     *     public boolean accept(City city, Person person)
     *     {
     *         return city.getName().equals("Anytown") && person.getLastName().equals("Smith");
     *     }
     * });
     * </pre>
     */
    Pair<K, V> detect(Predicate2<? super K, ? super V> predicate);

    /**
     * Follows the same general contract as {@link Map#equals(Object)}.
     */
    boolean equals(Object o);

    /**
     * Follows the same general contract as {@link Map#hashCode()}.
     */
    int hashCode();

    /**
     * Returns a string representation of this MapIterable. The string representation consists of a list of the
     * map's key-value pairs in the order they are returned by its iterator. The key and value in each key-value pair are separated
     * by a colon (<tt>":"</tt>) and each pair is enclosed in square brackets (<tt>"[]"</tt>). Adjacent key-value pairs
     * are separated by the characters <tt>", "</tt> (comma and space). Keys and values are converted to strings as by
     * {@link String#valueOf(Object)}.
     *
     * @return a string representation of this MapIterable
     */
    String toString();

    ImmutableMapIterable<K, V> toImmutable();
}
