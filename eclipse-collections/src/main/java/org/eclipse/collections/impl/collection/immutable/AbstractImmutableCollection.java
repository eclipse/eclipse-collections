/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.collection.immutable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.AbstractRichIterable;
import org.eclipse.collections.impl.block.procedure.MutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.NonMutatingAggregationProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.utility.Iterate;

public abstract class AbstractImmutableCollection<T> extends AbstractRichIterable<T> implements ImmutableCollection<T>, Collection<T>
{
    protected abstract MutableCollection<T> newMutable(int size);

    public <K, V> ImmutableMap<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new MutatingAggregationProcedure<T, K, V>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map.toImmutable();
    }

    public <K, V> ImmutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new NonMutatingAggregationProcedure<T, K, V>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map.toImmutable();
    }

    public <V> ImmutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        return Iterate.groupByUniqueKey(this, function).toImmutable();
    }

    public boolean add(T t)
    {
        throw new UnsupportedOperationException("Cannot call add() on " + this.getClass().getSimpleName());
    }

    public boolean remove(Object o)
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    public boolean addAll(Collection<? extends T> collection)
    {
        throw new UnsupportedOperationException("Cannot call addAll() on " + this.getClass().getSimpleName());
    }

    public boolean removeAll(Collection<?> collection)
    {
        throw new UnsupportedOperationException("Cannot call removeAll() on " + this.getClass().getSimpleName());
    }

    public boolean retainAll(Collection<?> collection)
    {
        throw new UnsupportedOperationException("Cannot call retainAll() on " + this.getClass().getSimpleName());
    }

    public void clear()
    {
        throw new UnsupportedOperationException("Cannot call clear() on " + this.getClass().getSimpleName());
    }

    protected void removeAllFrom(Iterable<? extends T> elements, MutableCollection<T> result)
    {
        if (elements instanceof Set)
        {
            result.removeAll((Set<?>) elements);
        }
        else if (elements instanceof List)
        {
            List<T> toBeRemoved = (List<T>) elements;
            if (this.size() * toBeRemoved.size() > 10000)
            {
                result.removeAll(UnifiedSet.newSet(elements));
            }
            else
            {
                result.removeAll(toBeRemoved);
            }
        }
        else
        {
            result.removeAll(UnifiedSet.newSet(elements));
        }
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
            MutableCollection<T> batch = this.newMutable(size);
            for (int i = 0; i < size && iterator.hasNext(); i++)
            {
                batch.add(iterator.next());
            }
            result.add(batch.toImmutable());
        }
        return result.toImmutable();
    }
}
