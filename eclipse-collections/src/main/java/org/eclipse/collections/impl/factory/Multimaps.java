/*
 * Copyright (c) 2016 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import java.util.Comparator;

import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.multimap.sortedbag.MutableSortedBagMultimap;
import org.eclipse.collections.api.multimap.sortedset.ImmutableSortedSetMultimap;
import org.eclipse.collections.api.multimap.sortedset.MutableSortedSetMultimap;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.multimap.bag.ImmutableBagMultimapImpl;
import org.eclipse.collections.impl.multimap.bag.sorted.mutable.TreeBagMultimap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.multimap.list.ImmutableListMultimapImpl;
import org.eclipse.collections.impl.multimap.set.ImmutableSetMultimapImpl;
import org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap;
import org.eclipse.collections.impl.multimap.set.sorted.ImmutableSortedSetMultimapImpl;
import org.eclipse.collections.impl.multimap.set.sorted.TreeSortedSetMultimap;

@SuppressWarnings("ConstantNamingConvention")
public final class Multimaps
{
    public static final ImmutableMultimaps immutable = new ImmutableMultimaps();
    public static final MutableMultimaps mutable = new MutableMultimaps();

    private Multimaps()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    @SuppressWarnings("PublicField")
    public static final class ImmutableMultimaps
    {
        public final ImmutableListMultimapFactory list = new ImmutableListMultimapFactory();
        public final ImmutableSetMultimapFactory set = new ImmutableSetMultimapFactory();
        public final ImmutableSortedSetMultimapFactory sortedSet = new ImmutableSortedSetMultimapFactory();
        public final ImmutableBagMultimapFactory bag = new ImmutableBagMultimapFactory();

        private ImmutableMultimaps()
        {
        }

        public static final class ImmutableListMultimapFactory
        {
            private static final ImmutableListMultimap<Object, Object> EMPTY = new ImmutableListMultimapImpl<>(Maps.immutable.with());

            private ImmutableListMultimapFactory()
            {
            }

            public <K, V> ImmutableListMultimap<K, V> empty()
            {
                return (ImmutableListMultimap<K, V>) EMPTY;
            }

            public <K, V> ImmutableListMultimap<K, V> of()
            {
                return this.empty();
            }

            public <K, V> ImmutableListMultimap<K, V> with()
            {
                return this.empty();
            }

            public <K, V> ImmutableListMultimap<K, V> of(K key, V value)
            {
                return this.with(key, value);
            }

            public <K, V> ImmutableListMultimap<K, V> with(K key, V value)
            {
                return new ImmutableListMultimapImpl<>(Maps.immutable.with(key, Lists.immutable.with(value)));
            }

            public <K, V> ImmutableListMultimap<K, V> of(K key1, V value1, K key2, V value2)
            {
                return this.with(key1, value1, key2, value2);
            }

            public <K, V> ImmutableListMultimap<K, V> with(K key1, V value1, K key2, V value2)
            {
                FastListMultimap<K, V> fastListMultimap = FastListMultimap.newMultimap();
                fastListMultimap.put(key1, value1);
                fastListMultimap.put(key2, value2);
                return fastListMultimap.toImmutable();
            }

            public <K, V> ImmutableListMultimap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                return this.with(key1, value1, key2, value2, key3, value3);
            }

            public <K, V> ImmutableListMultimap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                FastListMultimap<K, V> fastListMultimap = FastListMultimap.newMultimap();
                fastListMultimap.put(key1, value1);
                fastListMultimap.put(key2, value2);
                fastListMultimap.put(key3, value3);
                return fastListMultimap.toImmutable();
            }
        }

        public static final class ImmutableSetMultimapFactory
        {
            private static final ImmutableSetMultimap<Object, Object> EMPTY = new ImmutableSetMultimapImpl<>(Maps.immutable.with());

            private ImmutableSetMultimapFactory()
            {
            }

            public <K, V> ImmutableSetMultimap<K, V> empty()
            {
                return (ImmutableSetMultimap<K, V>) EMPTY;
            }

            public <K, V> ImmutableSetMultimap<K, V> of()
            {
                return this.empty();
            }

            public <K, V> ImmutableSetMultimap<K, V> with()
            {
                return this.empty();
            }

            public <K, V> ImmutableSetMultimap<K, V> of(K key, V value)
            {
                return this.with(key, value);
            }

            public <K, V> ImmutableSetMultimap<K, V> with(K key, V value)
            {
                return new ImmutableSetMultimapImpl<>(Maps.immutable.with(key, Sets.immutable.with(value)));
            }

            public <K, V> ImmutableSetMultimap<K, V> of(K key1, V value1, K key2, V value2)
            {
                return this.with(key1, value1, key2, value2);
            }

            public <K, V> ImmutableSetMultimap<K, V> with(K key1, V value1, K key2, V value2)
            {
                UnifiedSetMultimap<K, V> unifiedSetMultimap = UnifiedSetMultimap.newMultimap();
                unifiedSetMultimap.put(key1, value1);
                unifiedSetMultimap.put(key2, value2);
                return unifiedSetMultimap.toImmutable();
            }

            public <K, V> ImmutableSetMultimap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                return this.with(key1, value1, key2, value2, key3, value3);
            }

            public <K, V> ImmutableSetMultimap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                UnifiedSetMultimap<K, V> unifiedSetMultimap = UnifiedSetMultimap.newMultimap();
                unifiedSetMultimap.put(key1, value1);
                unifiedSetMultimap.put(key2, value2);
                unifiedSetMultimap.put(key3, value3);
                return unifiedSetMultimap.toImmutable();
            }
        }

        public static final class ImmutableSortedSetMultimapFactory
        {
            private ImmutableSortedSetMultimapFactory()
            {
            }

            public <K, V> ImmutableSortedSetMultimap<K, V> of(Comparator<V> comparator)
            {
                return this.with(comparator);
            }

            public <K, V> ImmutableSortedSetMultimap<K, V> with(Comparator<V> comparator)
            {
                return new ImmutableSortedSetMultimapImpl<>(Maps.immutable.with(), comparator);
            }

            public <K, V> ImmutableSortedSetMultimap<K, V> of(Comparator<V> comparator, K key, V value)
            {
                return this.with(comparator, key, value);
            }

            public <K, V> ImmutableSortedSetMultimap<K, V> with(Comparator<V> comparator, K key, V value)
            {
                return new ImmutableSortedSetMultimapImpl<>(Maps.immutable.with(key, SortedSets.immutable.with(comparator, value)), comparator);
            }

            public <K, V> ImmutableSortedSetMultimap<K, V> of(Comparator<V> comparator, K key1, V value1, K key2, V value2)
            {
                return this.with(comparator, key1, value1, key2, value2);
            }

            public <K, V> ImmutableSortedSetMultimap<K, V> with(Comparator<V> comparator, K key1, V value1, K key2, V value2)
            {
                TreeSortedSetMultimap<K, V> treeSortedSetMultimap = TreeSortedSetMultimap.newMultimap(comparator);
                treeSortedSetMultimap.put(key1, value1);
                treeSortedSetMultimap.put(key2, value2);
                return treeSortedSetMultimap.toImmutable();
            }

            public <K, V> ImmutableSortedSetMultimap<K, V> of(Comparator<V> comparator, K key1, V value1, K key2, V value2, K key3, V value3)
            {
                return this.with(comparator, key1, value1, key2, value2, key3, value3);
            }

            public <K, V> ImmutableSortedSetMultimap<K, V> with(Comparator<V> comparator, K key1, V value1, K key2, V value2, K key3, V value3)
            {
                TreeSortedSetMultimap<K, V> treeSortedSetMultimap = TreeSortedSetMultimap.newMultimap(comparator);
                treeSortedSetMultimap.put(key1, value1);
                treeSortedSetMultimap.put(key2, value2);
                treeSortedSetMultimap.put(key3, value3);
                return treeSortedSetMultimap.toImmutable();
            }
        }

        public static final class ImmutableBagMultimapFactory
        {
            private static final ImmutableBagMultimap<Object, Object> EMPTY = new ImmutableBagMultimapImpl<>(Maps.immutable.with());

            private ImmutableBagMultimapFactory()
            {
            }

            public <K, V> ImmutableBagMultimap<K, V> empty()
            {
                return (ImmutableBagMultimap<K, V>) EMPTY;
            }

            public <K, V> ImmutableBagMultimap<K, V> of()
            {
                return this.empty();
            }

            public <K, V> ImmutableBagMultimap<K, V> with()
            {
                return this.empty();
            }

            public <K, V> ImmutableBagMultimap<K, V> of(K key, V value)
            {
                return this.with(key, value);
            }

            public <K, V> ImmutableBagMultimap<K, V> with(K key, V value)
            {
                return new ImmutableBagMultimapImpl<>(Maps.immutable.with(key, Bags.immutable.with(value)));
            }

            public <K, V> ImmutableBagMultimap<K, V> of(K key1, V value1, K key2, V value2)
            {
                return this.with(key1, value1, key2, value2);
            }

            public <K, V> ImmutableBagMultimap<K, V> with(K key1, V value1, K key2, V value2)
            {
                HashBagMultimap<K, V> hashBagMultimap = HashBagMultimap.newMultimap();
                hashBagMultimap.put(key1, value1);
                hashBagMultimap.put(key2, value2);
                return hashBagMultimap.toImmutable();
            }

            public <K, V> ImmutableBagMultimap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                return this.with(key1, value1, key2, value2, key3, value3);
            }

            public <K, V> ImmutableBagMultimap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                HashBagMultimap<K, V> hashBagMultimap = HashBagMultimap.newMultimap();
                hashBagMultimap.put(key1, value1);
                hashBagMultimap.put(key2, value2);
                hashBagMultimap.put(key3, value3);
                return hashBagMultimap.toImmutable();
            }
        }
    }

    @SuppressWarnings("PublicField")
    public static final class MutableMultimaps
    {
        public final MutableListMultimapFactory list = new MutableListMultimapFactory();
        public final MutableSetMultimapFactory set = new MutableSetMultimapFactory();
        public final MutableSortedSetMultimapFactory sortedSet = new MutableSortedSetMultimapFactory();
        public final MutableBagMultimapFactory bag = new MutableBagMultimapFactory();
        public final MutableSortedBagMultimapFactory sortedBag = new MutableSortedBagMultimapFactory();

        private MutableMultimaps()
        {
        }

        public static final class MutableListMultimapFactory
        {
            private MutableListMultimapFactory()
            {
            }

            public <K, V> MutableListMultimap<K, V> empty()
            {
                return FastListMultimap.newMultimap();
            }

            public <K, V> MutableListMultimap<K, V> of()
            {
                return this.empty();
            }

            public <K, V> MutableListMultimap<K, V> with()
            {
                return this.empty();
            }

            public <K, V> MutableListMultimap<K, V> of(K key, V value)
            {
                return this.with(key, value);
            }

            public <K, V> MutableListMultimap<K, V> with(K key, V value)
            {
                FastListMultimap<K, V> fastListMultimap = FastListMultimap.newMultimap();
                fastListMultimap.put(key, value);
                return fastListMultimap;
            }

            public <K, V> MutableListMultimap<K, V> of(K key1, V value1, K key2, V value2)
            {
                return this.with(key1, value1, key2, value2);
            }

            public <K, V> MutableListMultimap<K, V> with(K key1, V value1, K key2, V value2)
            {
                FastListMultimap<K, V> fastListMultimap = FastListMultimap.newMultimap();
                fastListMultimap.put(key1, value1);
                fastListMultimap.put(key2, value2);
                return fastListMultimap;
            }

            public <K, V> MutableListMultimap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                return this.with(key1, value1, key2, value2, key3, value3);
            }

            public <K, V> MutableListMultimap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                FastListMultimap<K, V> fastListMultimap = FastListMultimap.newMultimap();
                fastListMultimap.put(key1, value1);
                fastListMultimap.put(key2, value2);
                fastListMultimap.put(key3, value3);
                return fastListMultimap;
            }

            public <K, V> MutableListMultimap<K, V> withAll(Multimap<? extends K, ? extends V> multimap)
            {
                return new FastListMultimap<>(multimap);
            }
        }

        public static final class MutableSetMultimapFactory
        {
            private MutableSetMultimapFactory()
            {
            }

            public <K, V> MutableSetMultimap<K, V> empty()
            {
                return UnifiedSetMultimap.newMultimap();
            }

            public <K, V> MutableSetMultimap<K, V> of()
            {
                return this.empty();
            }

            public <K, V> MutableSetMultimap<K, V> with()
            {
                return this.empty();
            }

            public <K, V> MutableSetMultimap<K, V> of(K key, V value)
            {
                return this.with(key, value);
            }

            public <K, V> MutableSetMultimap<K, V> with(K key, V value)
            {
                UnifiedSetMultimap<K, V> unifiedSetMultimap = UnifiedSetMultimap.newMultimap();
                unifiedSetMultimap.put(key, value);
                return unifiedSetMultimap;
            }

            public <K, V> MutableSetMultimap<K, V> of(K key1, V value1, K key2, V value2)
            {
                return this.with(key1, value1, key2, value2);
            }

            public <K, V> MutableSetMultimap<K, V> with(K key1, V value1, K key2, V value2)
            {
                UnifiedSetMultimap<K, V> unifiedSetMultimap = UnifiedSetMultimap.newMultimap();
                unifiedSetMultimap.put(key1, value1);
                unifiedSetMultimap.put(key2, value2);
                return unifiedSetMultimap;
            }

            public <K, V> MutableSetMultimap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                return this.with(key1, value1, key2, value2, key3, value3);
            }

            public <K, V> MutableSetMultimap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                UnifiedSetMultimap<K, V> unifiedSetMultimap = UnifiedSetMultimap.newMultimap();
                unifiedSetMultimap.put(key1, value1);
                unifiedSetMultimap.put(key2, value2);
                unifiedSetMultimap.put(key3, value3);
                return unifiedSetMultimap;
            }

            public <K, V> MutableSetMultimap<K, V> withAll(Multimap<? extends K, ? extends V> multimap)
            {
                return new UnifiedSetMultimap<>(multimap);
            }
        }

        public static final class MutableSortedSetMultimapFactory
        {
            private MutableSortedSetMultimapFactory()
            {
            }

            public <K, V> MutableSortedSetMultimap<K, V> of(Comparator<V> comparator)
            {
                return this.with(comparator);
            }

            public <K, V> MutableSortedSetMultimap<K, V> with(Comparator<V> comparator)
            {
                return TreeSortedSetMultimap.newMultimap(comparator);
            }

            public <K, V> MutableSortedSetMultimap<K, V> of(Comparator<V> comparator, K key, V value)
            {
                return this.with(comparator, key, value);
            }

            public <K, V> MutableSortedSetMultimap<K, V> with(Comparator<V> comparator, K key, V value)
            {
                TreeSortedSetMultimap<K, V> treeSortedSetMultimap = TreeSortedSetMultimap.newMultimap(comparator);
                treeSortedSetMultimap.put(key, value);
                return treeSortedSetMultimap;
            }

            public <K, V> MutableSortedSetMultimap<K, V> of(Comparator<V> comparator, K key1, V value1, K key2, V value2)
            {
                return this.with(comparator, key1, value1, key2, value2);
            }

            public <K, V> MutableSortedSetMultimap<K, V> with(Comparator<V> comparator, K key1, V value1, K key2, V value2)
            {
                TreeSortedSetMultimap<K, V> treeSortedSetMultimap = TreeSortedSetMultimap.newMultimap(comparator);
                treeSortedSetMultimap.put(key1, value1);
                treeSortedSetMultimap.put(key2, value2);
                return treeSortedSetMultimap;
            }

            public <K, V> MutableSortedSetMultimap<K, V> of(Comparator<V> comparator, K key1, V value1, K key2, V value2, K key3, V value3)
            {
                return this.with(comparator, key1, value1, key2, value2, key3, value3);
            }

            public <K, V> MutableSortedSetMultimap<K, V> with(Comparator<V> comparator, K key1, V value1, K key2, V value2, K key3, V value3)
            {
                TreeSortedSetMultimap<K, V> treeSortedSetMultimap = TreeSortedSetMultimap.newMultimap(comparator);
                treeSortedSetMultimap.put(key1, value1);
                treeSortedSetMultimap.put(key2, value2);
                treeSortedSetMultimap.put(key3, value3);
                return treeSortedSetMultimap;
            }

            public <K, V> MutableSortedSetMultimap<K, V> withAll(Multimap<? extends K, ? extends V> multimap)
            {
                return new TreeSortedSetMultimap<>(multimap);
            }
        }

        public static final class MutableBagMultimapFactory
        {
            private MutableBagMultimapFactory()
            {
            }

            public <K, V> MutableBagMultimap<K, V> empty()
            {
                return HashBagMultimap.newMultimap();
            }

            public <K, V> MutableBagMultimap<K, V> of()
            {
                return this.empty();
            }

            public <K, V> MutableBagMultimap<K, V> with()
            {
                return this.empty();
            }

            public <K, V> MutableBagMultimap<K, V> of(K key, V value)
            {
                return this.with(key, value);
            }

            public <K, V> MutableBagMultimap<K, V> with(K key, V value)
            {
                HashBagMultimap<K, V> hashBagMultimap = HashBagMultimap.newMultimap();
                hashBagMultimap.put(key, value);
                return hashBagMultimap;
            }

            public <K, V> MutableBagMultimap<K, V> of(K key1, V value1, K key2, V value2)
            {
                return this.with(key1, value1, key2, value2);
            }

            public <K, V> MutableBagMultimap<K, V> with(K key1, V value1, K key2, V value2)
            {
                HashBagMultimap<K, V> hashBagMultimap = HashBagMultimap.newMultimap();
                hashBagMultimap.put(key1, value1);
                hashBagMultimap.put(key2, value2);
                return hashBagMultimap;
            }

            public <K, V> MutableBagMultimap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                return this.with(key1, value1, key2, value2, key3, value3);
            }

            public <K, V> MutableBagMultimap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                HashBagMultimap<K, V> hashBagMultimap = HashBagMultimap.newMultimap();
                hashBagMultimap.put(key1, value1);
                hashBagMultimap.put(key2, value2);
                hashBagMultimap.put(key3, value3);
                return hashBagMultimap;
            }

            public <K, V> MutableBagMultimap<K, V> withAll(Multimap<? extends K, ? extends V> multimap)
            {
                return new HashBagMultimap<>(multimap);
            }
        }

        public static final class MutableSortedBagMultimapFactory
        {
            private MutableSortedBagMultimapFactory()
            {
            }

            public <K, V> MutableSortedBagMultimap<K, V> empty()
            {
                return TreeBagMultimap.newMultimap();
            }

            public <K, V> MutableSortedBagMultimap<K, V> with()
            {
                return this.empty();
            }

            public <K, V> MutableSortedBagMultimap<K, V> with(K key, V value)
            {
                TreeBagMultimap<K, V> treeBagMultimap = TreeBagMultimap.newMultimap();
                treeBagMultimap.put(key, value);
                return treeBagMultimap;
            }

            public <K, V> MutableSortedBagMultimap<K, V> with(K key1, V value1, K key2, V value2)
            {
                TreeBagMultimap<K, V> treeBagMultimap = TreeBagMultimap.newMultimap();
                treeBagMultimap.put(key1, value1);
                treeBagMultimap.put(key2, value2);
                return treeBagMultimap;
            }

            public <K, V> MutableSortedBagMultimap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3)
            {
                TreeBagMultimap<K, V> treeBagMultimap = TreeBagMultimap.newMultimap();
                treeBagMultimap.put(key1, value1);
                treeBagMultimap.put(key2, value2);
                treeBagMultimap.put(key3, value3);
                return treeBagMultimap;
            }

            public <K, V> MutableSortedBagMultimap<K, V> withAll(Multimap<? extends K, ? extends V> multimap)
            {
                return new TreeBagMultimap<>(multimap);
            }
        }
    }
}
