/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap;

import java.util.Map;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.UnmodifiableRichIterable;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.tuple.Tuples;

public abstract class AbstractMultimap<K, V, C extends RichIterable<V>>
        implements Multimap<K, V>
{
    private static final Function<AbstractMultimap<?, ?, ?>, ?> CREATE_COLLECTION_BLOCK = new Function<AbstractMultimap<?, ?, ?>, RichIterable<?>>()
    {
        public RichIterable<?> valueOf(AbstractMultimap<?, ?, ?> multimap)
        {
            return multimap.createCollection();
        }
    };

    protected abstract MapIterable<K, C> getMap();

    /**
     * Creates the collection of values for a single key.
     * <p>
     * Collections with weak, soft, or phantom references are not supported.
     * Each call to {@code createCollection} should create a new instance.
     * <p>
     * The returned collection class determines whether duplicate key-value
     * pairs are allowed.
     *
     * @return an empty collection of values
     */
    protected abstract C createCollection();

    protected Function<AbstractMultimap<K, V, C>, C> createCollectionBlock()
    {
        return (Function<AbstractMultimap<K, V, C>, C>) (Function<?, ?>) CREATE_COLLECTION_BLOCK;
    }

    // Query Operations

    public boolean containsKey(Object key)
    {
        return this.getMap().containsKey(key);
    }

    public boolean containsValue(final Object value)
    {
        return this.getMap().anySatisfy(new Predicate<C>()
        {
            public boolean accept(C collection)
            {
                return collection.contains(value);
            }
        });
    }

    public boolean containsKeyAndValue(Object key, Object value)
    {
        C collection = this.getMap().get(key);
        return collection != null && collection.contains(value);
    }

    // Views

    public RichIterable<K> keysView()
    {
        return this.getMap().keysView();
    }

    public RichIterable<RichIterable<V>> multiValuesView()
    {
        return this.getMap().valuesView().collect(new Function<C, RichIterable<V>>()
        {
            public RichIterable<V> valueOf(C multiValue)
            {
                return UnmodifiableRichIterable.of(multiValue);
            }
        });
    }

    public Bag<K> keyBag()
    {
        final MutableBag<K> bag = Bags.mutable.empty();
        this.getMap().forEachKeyValue(new Procedure2<K, C>()
        {
            public void value(K key, C value)
            {
                bag.addOccurrences(key, value.size());
            }
        });
        return bag;
    }

    public RichIterable<V> valuesView()
    {
        return this.getMap().valuesView().flatCollect(Functions.<Iterable<V>>identity());
    }

    public RichIterable<Pair<K, RichIterable<V>>> keyMultiValuePairsView()
    {
        return this.getMap().keyValuesView().collect(new Function<Pair<K, C>, Pair<K, RichIterable<V>>>()
        {
            public Pair<K, RichIterable<V>> valueOf(Pair<K, C> pair)
            {
                return Tuples.<K, RichIterable<V>>pair(pair.getOne(), UnmodifiableRichIterable.of(pair.getTwo()));
            }
        });
    }

    public RichIterable<Pair<K, V>> keyValuePairsView()
    {
        return this.keyMultiValuePairsView().flatCollect(new Function<Pair<K, RichIterable<V>>, Iterable<Pair<K, V>>>()
        {
            public Iterable<Pair<K, V>> valueOf(Pair<K, RichIterable<V>> pair)
            {
                return pair.getTwo().collect(new KeyValuePairFunction<V, K>(pair.getOne()));
            }
        });
    }

    // Comparison and hashing

    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (object instanceof Multimap)
        {
            Multimap<?, ?> that = (Multimap<?, ?>) object;
            return this.getMap().equals(that.toMap());
        }
        return false;
    }

    /**
     * Returns the hash code for this multimap.
     * <p>
     * The hash code of a multimap is defined as the hash code of the map view,
     * as returned by {@link Multimap#toMap()}.
     *
     * @see Map#hashCode()
     */
    @Override
    public int hashCode()
    {
        return this.getMap().hashCode();
    }

    /**
     * Returns a string representation of the multimap, generated by calling
     * {@code toString} on the map returned by {@link Multimap#toMap()}.
     *
     * @return a string representation of the multimap
     */
    @Override
    public String toString()
    {
        return this.getMap().toString();
    }

    public boolean notEmpty()
    {
        return !this.isEmpty();
    }

    public void forEachValue(final Procedure<? super V> procedure)
    {
        this.getMap().forEachValue(new Procedure<C>()
        {
            public void value(C collection)
            {
                collection.forEach(procedure);
            }
        });
    }

    public void forEachKey(Procedure<? super K> procedure)
    {
        this.getMap().forEachKey(procedure);
    }

    public void forEachKeyValue(final Procedure2<? super K, ? super V> procedure)
    {
        final Procedure2<V, K> innerProcedure = new Procedure2<V, K>()
        {
            public void value(V value, K key)
            {
                procedure.value(key, value);
            }
        };

        this.getMap().forEachKeyValue(new Procedure2<K, C>()
        {
            public void value(K key, C collection)
            {
                collection.forEachWith(innerProcedure, key);
            }
        });
    }

    public void forEachKeyMultiValues(Procedure2<K, ? super Iterable<V>> procedure)
    {
        this.getMap().forEachKeyValue(procedure);
    }

    public <R extends MutableMultimap<K, V>> R selectKeysValues(final Predicate2<? super K, ? super V> predicate, final R target)
    {
        this.getMap().forEachKeyValue(new Procedure2<K, C>()
        {
            public void value(final K key, C collection)
            {
                RichIterable<V> selectedValues = collection.select(new Predicate<V>()
                {
                    public boolean accept(V value)
                    {
                        return predicate.accept(key, value);
                    }
                });
                target.putAll(key, selectedValues);
            }
        });
        return target;
    }

    public <R extends MutableMultimap<K, V>> R rejectKeysValues(final Predicate2<? super K, ? super V> predicate, final R target)
    {
        this.getMap().forEachKeyValue(new Procedure2<K, C>()
        {
            public void value(final K key, C collection)
            {
                RichIterable<V> selectedValues = collection.reject(new Predicate<V>()
                {
                    public boolean accept(V value)
                    {
                        return predicate.accept(key, value);
                    }
                });
                target.putAll(key, selectedValues);
            }
        });
        return target;
    }

    public <R extends MutableMultimap<K, V>> R selectKeysMultiValues(final Predicate2<? super K, ? super Iterable<V>> predicate, final R target)
    {
        this.forEachKeyMultiValues(new Procedure2<K, Iterable<V>>()
        {
            public void value(K key, Iterable<V> collection)
            {
                if (predicate.accept(key, collection))
                {
                    target.putAll(key, collection);
                }
            }
        });
        return target;
    }

    public <R extends MutableMultimap<K, V>> R rejectKeysMultiValues(final Predicate2<? super K, ? super Iterable<V>> predicate, final R target)
    {
        this.forEachKeyMultiValues(new Procedure2<K, Iterable<V>>()
        {
            public void value(K key, Iterable<V> collection)
            {
                if (!predicate.accept(key, collection))
                {
                    target.putAll(key, collection);
                }
            }
        });
        return target;
    }

    private static final class KeyValuePairFunction<V, K> implements Function<V, Pair<K, V>>
    {
        private static final long serialVersionUID = 1L;

        private final K key;

        private KeyValuePairFunction(K key)
        {
            this.key = key;
        }

        public Pair<K, V> valueOf(V value)
        {
            return Tuples.pair(this.key, value);
        }
    }

    public <K2, V2, R extends MutableMultimap<K2, V2>> R collectKeysValues(final Function2<? super K, ? super V, Pair<K2, V2>> function, final R target)
    {
        this.getMap().forEachKeyValue(new Procedure2<K, C>()
        {
            public void value(final K key, C collection)
            {
                collection.forEach(new Procedure<V>()
                {
                    public void value(V value)
                    {
                        Pair<K2, V2> pair = function.value(key, value);
                        target.add(pair);
                    }
                });
            }
        });
        return target;
    }

    public <V2, R extends MutableMultimap<K, V2>> R collectValues(final Function<? super V, ? extends V2> function, final R target)
    {
        this.getMap().forEachKeyValue(new Procedure2<K, C>()
        {
            public void value(K key, C collection)
            {
                target.putAll(key, collection.collect(function));
            }
        });
        return target;
    }
}
