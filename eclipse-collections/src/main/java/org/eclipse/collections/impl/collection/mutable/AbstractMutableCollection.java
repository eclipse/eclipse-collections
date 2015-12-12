/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.Function3;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.AbstractRichIterable;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Procedures2;
import org.eclipse.collections.impl.block.procedure.MutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.NonMutatingAggregationProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.internal.IterableIterate;

public abstract class AbstractMutableCollection<T>
        extends AbstractRichIterable<T>
        implements MutableCollection<T>
{
    public <P> Twin<MutableList<T>> selectAndRejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        return IterableIterate.selectAndRejectWith(this, predicate, parameter);
    }

    public boolean removeIf(Predicate<? super T> predicate)
    {
        return IterableIterate.removeIf(this, predicate);
    }

    public <P> boolean removeIfWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return IterableIterate.removeIfWith(this, predicate, parameter);
    }

    public <IV, P> IV injectIntoWith(
            IV injectValue,
            Function3<? super IV, ? super T, ? super P, ? extends IV> function,
            P parameter)
    {
        return IterableIterate.injectIntoWith(injectValue, this, function, parameter);
    }

    public boolean addAllIterable(Iterable<? extends T> iterable)
    {
        int oldSize = this.size();

        if (iterable instanceof List && iterable instanceof RandomAccess)
        {
            List<T> list = (List<T>) iterable;
            int size = list.size();
            for (int i = 0; i < size; i++)
            {
                this.add(list.get(i));
            }
        }
        else
        {
            Iterate.forEachWith(iterable, Procedures2.<T>addToCollection(), this);
        }
        return oldSize != this.size();
    }

    public boolean removeAllIterable(Iterable<?> iterable)
    {
        return this.removeAll(CollectionAdapter.wrapSet(iterable));
    }

    public boolean retainAllIterable(Iterable<?> iterable)
    {
        return this.retainAll(CollectionAdapter.wrapSet(iterable));
    }

    public RichIterable<RichIterable<T>> chunk(int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Size for groups must be positive but was: " + size);
        }

        Iterator<T> iterator = this.iterator();
        MutableList<RichIterable<T>> result = Lists.mutable.empty();
        while (iterator.hasNext())
        {
            MutableCollection<T> batch = this.newEmpty();
            for (int i = 0; i < size && iterator.hasNext(); i++)
            {
                batch.add(iterator.next());
            }
            result.add(batch);
        }
        return result;
    }

    public <K, V> MutableMap<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new MutatingAggregationProcedure<T, K, V>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map;
    }

    public <K, V> MutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new NonMutatingAggregationProcedure<T, K, V>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map;
    }

    public boolean add(T element)
    {
        throw new UnsupportedOperationException("Cannot call add() on " + this.getClass().getSimpleName());
    }

    public boolean remove(Object o)
    {
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext())
        {
            if (Comparators.nullSafeEquals(o, iterator.next()))
            {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean addAll(Collection<? extends T> source)
    {
        return this.addAllIterable(source);
    }

    public boolean removeAll(Collection<?> source)
    {
        return this.removeAllIterable(source);
    }

    public boolean retainAll(Collection<?> source)
    {
        int oldSize = this.size();
        Iterator<?> iterator = this.iterator();
        while (iterator.hasNext())
        {
            if (!source.contains(iterator.next()))
            {
                iterator.remove();
            }
        }
        return this.size() != oldSize;
    }

    public <V> MutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        return Iterate.groupByUniqueKey(this, function);
    }
}
