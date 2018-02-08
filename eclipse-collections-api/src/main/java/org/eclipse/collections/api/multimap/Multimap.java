/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.multimap;

import java.util.Collection;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.tuple.Pair;

/**
 * This collection is a type of {@code Map} that can associate multiple values for keys.
 * <p>
 * <p>Unlike {@code Map} however, this interface is read-only so the results of access methods such as {@link
 * #get(Object)} return a view onto the values associated with that key. The {@link MutableMultimap} sub-interface
 * provides methods to mutate the collection.
 * <p>
 * <p>The advantages to using this container over a {@code Map<K, Collection<V>>} is that all of the handling of the
 * value collection can be done automatically.  It also allows implementations to further specialize in how duplicate
 * values will be handled.  Value collections with list semantics would allow duplicate values for a key, while those
 * implementing set semantics would not. The value collections can never be empty.
 * <p>
 * <p>Internal iteration methods for keys and values (singly - {@link #forEachKey(Procedure)}, {@link
 * #forEachValue(Procedure)}, and together - {@link #forEachKeyValue(Procedure2)}), {@link #forEachKeyMultiValues(Procedure2)}) are provided to allow flexible
 * browsing of the collection's contents.  Similarly, views also are provided for keys ({@link #keysView()}), values
 * ({@link #valuesView()}) and the combination thereof ({@link #keyValuePairsView()}, {@link
 * #keyMultiValuePairsView()}).
 * <p>
 *
 * @param <K> the type of keys used
 * @param <V> the type of mapped values
 * @since 1.0
 */
@SuppressWarnings("JavaDoc")
public interface Multimap<K, V>
{
    /**
     * Creates a new instance of the same implementation type, using the default capacity and growth parameters.
     */
    Multimap<K, V> newEmpty();

    /**
     * Returns {@code true} if there are no entries.
     */
    boolean isEmpty();

    /**
     * Returns {@code true} if there is at least one entry.
     */
    boolean notEmpty();

    /**
     * Calls the procedure with each <em>value</em>.
     * <p>
     * Given a Multimap with the contents:
     * <p>
     * {@code { "key1" : ["val1", "val2", "val2"], "key2" : ["val3"] }}
     * <p>
     * The given procedure would be invoked with the parameters:
     * <p>
     * {@code [ "val1", "val2", "val2", "val3" ]}
     */
    void forEachValue(Procedure<? super V> procedure);

    /**
     * Calls the {@code procedure} with each <em>key</em>.
     * <p>
     * Given a Multimap with the contents:
     * <p>
     * {@code { "key1" : ["val1", "val2", "val2"], "key2" : ["val3"] }}
     * <p>
     * The given procedure would be invoked with the parameters:
     * <p>
     * {@code [ "key1", "key2" ]}
     */
    void forEachKey(Procedure<? super K> procedure);

    /**
     * Calls the {@code procedure} with each <em>key-value</em> pair.
     * <p>
     * Given a Multimap with the contents:
     * <p>
     * {@code { "key1" : ["val1", "val2", "val2"], "key2" : ["val3"] }}
     * <p>
     * The given procedure would be invoked with the parameters:
     * <p>
     * {@code [ ["key1", "val1"], ["key1", "val2"], ["key1", "val2"], ["key2", "val3"] ]}
     */
    void forEachKeyValue(Procedure2<? super K, ? super V> procedure);

    /**
     * Calls the {@code procedure} with each <em>key-Iterable[value]</em>.
     * <p>
     * Given a Multimap with the contents:
     * <p>
     * {@code { "key1" : ["val1", "val2", "val2"], "key2" : ["val3"] }}
     * <p>
     * The given procedure would be invoked with the parameters:
     * <p>
     * {@code [ ["key1", {@link RichIterable["val1", "val2", "val2"]}], ["key2", {@link RichIterable["val3"]}] ]}
     *
     * @since 6.0
     */
    void forEachKeyMultiValues(Procedure2<? super K, ? super Iterable<V>> procedure);

    /**
     * Returns the number of key-value entry pairs.
     * <p>
     * This method is implemented with O(1) (constant-time) performance.
     */
    int size();

    /**
     * Returns the number of distinct keys.
     */
    int sizeDistinct();

    /**
     * Returns {@code true} if any values are mapped to the specified key.
     *
     * @param key the key to search for
     */
    boolean containsKey(Object key);

    /**
     * Returns {@code true} if any key is mapped to the specified value.
     *
     * @param value the value to search for
     */
    boolean containsValue(Object value);

    /**
     * Returns {@code true} if the specified key-value pair is mapped.
     *
     * @param key   the key to search for
     * @param value the value to search for
     */
    boolean containsKeyAndValue(Object key, Object value);

    /**
     * Returns a view of all values associated with the given key.
     * <p>
     * If the given key does not exist, an empty {@link RichIterable} is returned.
     *
     * @param key the key to search for
     */
    RichIterable<V> get(K key);

    /**
     * Returns a lazy view of the unique keys.
     */
    RichIterable<K> keysView();

    /**
     * Returns a unmodifiable {@link SetIterable} of keys with O(1) complexity.
     */
    SetIterable<K> keySet();

    /**
     * Returns a {@link Bag} of keys with the count corresponding to the number of mapped values.
     */
    Bag<K> keyBag();

    /**
     * Returns an unmodifiable view of all of the values mapped to each key.
     */
    RichIterable<RichIterable<V>> multiValuesView();

    /**
     * Returns a lazy flattened view of all the values.
     */
    RichIterable<V> valuesView();

    /**
     * Returns a lazy view of the pair of a key and and a lazy view of the values mapped to that key.
     */
    RichIterable<Pair<K, RichIterable<V>>> keyMultiValuePairsView();

    /**
     * Returns a lazy view of all of the key/value pairs.
     */
    RichIterable<Pair<K, V>> keyValuePairsView();

    /**
     * Returns a new {@link MutableMap} of keys from this Multimap to the mapped values as a {@link RichIterable}.
     */
    MutableMap<K, RichIterable<V>> toMap();

    /**
     * Returns a new {@link MutableMap} of keys from this Multimap to the mapped values as a {@link RichIterable}.
     *
     * @param collectionFactory used to create the collections that hold the values and affects the return type
     */
    <R extends Collection<V>> MutableMap<K, R> toMap(Function0<R> collectionFactory);

    /**
     * Compares the specified object with this Multimap for equality.
     * <p>
     * Two Multimaps are equal when their map views (as returned by {@link #toMap}) are also equal.
     * <p>
     * In general, two Multimaps with identical key-value mappings may or may not be equal, depending on the type of
     * the collections holding the values. If the backing collections are Sets, then two instances with the same
     * key-value mappings are equal, but if the backing collections are Lists, equality depends on the ordering of the
     * values for each key.
     * <p>
     * Any two empty Multimaps are equal, because they both have empty {@link #toMap} views.
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns the hash code for this Multimap.
     * <p>
     * The hash code of a Multimap is defined as the hash code of the map view, as returned by {@link #toMap}.
     */
    @Override
    int hashCode();

    /**
     * Returns a mutable <em>copy</em> of this Multimap.
     */
    MutableMultimap<K, V> toMutable();

    /**
     * Returns an immutable copy of this Multimap <em>if it is not already immutable</em>. If the Multimap is immutable,
     * it will return itself.
     * <p>
     * The returned Multimap will be {@code Serializable} if this Multimap is {@code Serializable}.
     */
    ImmutableMultimap<K, V> toImmutable();

    /**
     * Given a Multimap from Domain -> Range return a multimap from Range -> Domain.
     *
     * @since 6.0
     */
    Multimap<V, K> flip();

    /**
     * Returns all elements of the source multimap that satisfies the predicate.  This method is also
     * commonly called filter.
     * <p>
     * <pre>e.g.
     * return multimap.<b>selectKeysValues</b>(new Predicate2&lt;Integer, Person&gt;()
     * {
     *     public boolean accept(Integer age, Person person)
     *     {
     *         return (age >= 18)
     *                  && (person.getAddress().getCity().equals("Metuchen"));
     *     }
     * });
     * </pre>
     * <p>
     *
     * @param predicate a {@link Predicate2} to use as the select criteria
     * @return {@code Multimap}, which contains elements as a result of the select criteria
     * @since 6.0
     */
    Multimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate);

    /**
     * Same as the select method but uses the specified target multimap for the results.
     * <p>
     * <pre>e.g.
     * return multimap.<b>selectKeysValues</b>(new Predicate2&lt;Integer, Person&gt;()
     * {
     *     public boolean accept(Integer age, Person person)
     *     {
     *         return (age >= 18)
     *                  && (person.getAddress().getCity().equals("Metuchen"));
     *     }
     * }, FastListMultimap.newMultimap());
     * </pre>
     * <p>
     *
     * @param predicate a {@link Predicate2} to use as the select criteria
     * @param target    the Multimap to append to for all elements in this {@code Multimap} that satisfy the {@code predicate}
     * @return {@code target}, which contains appended elements as a result of the select criteria
     * @since 6.0
     */
    <R extends MutableMultimap<K, V>> R selectKeysValues(Predicate2<? super K, ? super V> predicate, R target);

    /**
     * Returns all elements of the source multimap that don't satisfy the predicate.
     * <p>
     * <pre>e.g.
     * return multimap.<b>rejectKeysValues</b>(new Predicate2&lt;Integer, Person&gt;()
     * {
     *     public boolean accept(Integer age, Person person)
     *     {
     *         return (age >= 18)
     *                  && (person.getAddress().getCity().equals("Metuchen"));
     *     }
     * });
     * </pre>
     * <p>
     *
     * @param predicate a {@link Predicate2} to use as the reject criteria
     * @return {@code Multimap}, which contains elements that don't satisfy the {@code predicate}
     * @since 6.0
     */
    Multimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate);

    /**
     * Same as the reject method but uses the specified target multimap for the results.
     * <p>
     * <pre>e.g.
     * return multimap.<b>rejectKeysValues</b>(new Predicate2&lt;Integer, Person&gt;()
     * {
     *     public boolean accept(Integer age, Person person)
     *     {
     *         return (age >= 18)
     *                  && (person.getAddress().getCity().equals("Metuchen"));
     *     }
     * }, FastListMultimap.newMultimap());
     * </pre>
     * <p>
     *
     * @param predicate a {@link Predicate2} to use as the reject criteria
     * @param target    the Multimap to append to for all elements in this {@code Multimap} that don't satisfy the {@code predicate}
     * @return {@code target}, which contains appended elements that don't satisfy the {@code predicate}
     * @since 6.0
     */
    <R extends MutableMultimap<K, V>> R rejectKeysValues(Predicate2<? super K, ? super V> predicate, R target);

    /**
     * Returns all elements of the source multimap that satisfies the predicate.  This method is also
     * commonly called filter.
     * <p>
     * <pre>e.g.
     * return multimap.<b>selectKeysMultiValues</b>(new Predicate2&lt;Integer, Iterable&lt;Person&gt&gt;()
     * {
     *     public boolean accept(Integer age, Iterable&lt;Person&gt; values)
     *     {
     *         return (age >= 18)
     *                  && ((RichIterable&lt;Person&gt;)values.size() >= 2);
     *     }
     * });
     * </pre>
     * <p>
     *
     * @param predicate a {@link Predicate2} to use as the select criteria
     * @return {@code Multimap}, which contains elements as a result of the select criteria
     * @since 6.0
     */
    Multimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    /**
     * Same as the select method but uses the specified target multimap for the results.
     * <p>
     * <pre>e.g.
     * return multimap.<b>selectKeysMultiValues</b>(new Predicate2&lt;Integer, Iterable&lt;Person&gt&gt;()
     * {
     *     public boolean accept(Integer age, Iterable&lt;Person&gt; values)
     *     {
     *         return (age >= 18)
     *                  && ((RichIterable&lt;Person&gt;)values.size() >= 2);
     *     }
     * }, FastListMultimap.newMultimap());
     * </pre>
     * <p>
     *
     * @param predicate a {@link Predicate2} to use as the select criteria
     * @param target    the Multimap to append to for all elements in this {@code Multimap} that satisfy the {@code predicate}
     * @return {@code target}, which contains appended elements as a result of the select criteria
     * @since 6.0
     */
    <R extends MutableMultimap<K, V>> R selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate, R target);

    /**
     * Returns all elements of the source multimap that don't satisfy the predicate.
     * <p>
     * <pre>e.g.
     * return multimap.<b>rejectKeysMultiValues</b>(new Predicate2&lt;Integer, Iterable&lt;Person&gt&gt;()
     * {
     *     public boolean accept(Integer age, Iterable&lt;Person&gt; values)
     *     {
     *         return (age >= 18)
     *                  && ((RichIterable&lt;Person&gt;)values.size() >= 2);
     *     }
     * });
     * </pre>
     * <p>
     *
     * @param predicate a {@link Predicate2} to use as the reject criteria
     * @return {@code Multimap}, which contains elements that don't satisfy the {@code predicate}
     * @since 6.0
     */
    Multimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    /**
     * Same as the reject method but uses the specified target multimap for the results.
     * <p>
     * <pre>e.g.
     * return multimap.<b>rejectKeysMultiValues</b>(new Predicate2&lt;Integer, Iterable&lt;Person&gt&gt;()
     * {
     *     public boolean accept(Integer age, Iterable&lt;Person&gt; values)
     *     {
     *         return (age >= 18)
     *                  && ((RichIterable&lt;Person&gt;)values.size() >= 2);
     *     }
     * }, FastListMultimap.newMultimap());
     * </pre>
     * <p>
     *
     * @param predicate a {@link Predicate2} to use as the reject criteria
     * @param target    the Multimap to append to for all elements in this {@code Multimap} that don't satisfy the {@code predicate}
     * @return {@code target}, which contains appended elements that don't satisfy the {@code predicate}
     * @since 6.0
     */
    <R extends MutableMultimap<K, V>> R rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate, R target);

    /**
     * Returns a new multimap with the results of applying the specified function on each key and value of the source
     * multimap.  This method is also commonly called transform or map.
     * <p>
     * <pre>e.g.
     * return multimap.collectKeysValues(new Function2&lt;Integer, Person, Pair&lt;String, String&gt&gt;()
     * {
     *     public Pair&lt;String, String&gt; valueOf(Integer age, Person person)
     *     {
     *         return Tuples.pair(age.toString(), person.getLastName());
     *     }
     * });
     * </pre>
     *
     * @param function a {@link Function2} to use for transformation
     * @return {@code Multimap}, which contains elements as a result of the transformation
     * @since 6.0
     */
    <K2, V2> Multimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function);

    /**
     * Same as the collect method but uses the specified target multimap for the results.
     * <p>
     * <pre>e.g.
     * return multimap.collectKeysValues(new Function2&lt;Integer, Person, Pair&lt;String, String&gt;&gt;()
     * {
     *     public Pair&lt;String, String&gt; valueOf(Integer age, Person person)
     *     {
     *         return Tuples.pair(age.toString(), person.getLastName());
     *     }
     * }, HashBagMultimap.&lt;String, String&gt;newMultimap());
     * </pre>
     *
     * @param function a {@link Function2} to use for transformation
     * @param target   the Multimap to append for all elements in this {@code Multimap} that are evaluated in {@code function}
     * @return {@code target}, which contains appended elements as a result of the transformation
     * @since 6.0
     */
    <K2, V2, R extends MutableMultimap<K2, V2>> R collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function, R target);

    /**
     * Returns a new multimap with the results of applying the specified function on each value of the source
     * multimap.  This method is also commonly called transform or map.
     * <p>
     * <pre>e.g.
     * return multimap.collectValues(new Function&lt;Person, String&gt;()
     * {
     *     public String valueOf(Person person)
     *     {
     *         return person.getLastName();
     *     }
     * });
     * </pre>
     *
     * @param function a {@link Function} to use for transformation
     * @return {@code Multimap}, which contains elements as a result of the transformation
     * @since 6.0
     */
    <V2> Multimap<K, V2> collectValues(Function<? super V, ? extends V2> function);

    /**
     * Same as the collect method but uses the specified target multimap for the results.
     * <p>
     * <pre>e.g.
     * return multimap.collectValues(new Function&lt;Person, String&gt;()
     * {
     *     public String valueOf(Person person)
     *     {
     *         return person.getLastName();
     *     }
     * }, FastListMultimap.&lt;Integer, String&gt;newMultimap());
     * </pre>
     *
     * @param function a {@link Function} to use for transformation
     * @param target   the Multimap to append for all elements in this {@code Multimap} that are evaluated in {@code function}
     * @return {@code target}, which contains appended elements as a result of the transformation
     * @since 6.0
     */
    <V2, R extends MutableMultimap<K, V2>> R collectValues(Function<? super V, ? extends V2> function, R target);
}
