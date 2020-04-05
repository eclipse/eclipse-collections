/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.MutableBagIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.bag.AbstractBag;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.factory.Procedures2;
import org.eclipse.collections.impl.block.procedure.MutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.NonMutatingAggregationProcedure;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectLongHashMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.internal.IterableIterate;

public abstract class AbstractMutableBagIterable<T>
        extends AbstractBag<T>
        implements MutableBagIterable<T>
{
    protected abstract RichIterable<T> getKeysView();

    @Override
    public boolean addAll(Collection<? extends T> source)
    {
        return this.addAllIterable(source);
    }

    @Override
    public boolean addAllIterable(Iterable<? extends T> iterable)
    {
        if (iterable instanceof Bag)
        {
            return this.addAllBag((Bag<T>) iterable);
        }
        int oldSize = this.size();
        Iterate.forEachWith(iterable, Procedures2.addToCollection(), this);
        return oldSize != this.size();
    }

    protected boolean addAllBag(Bag<? extends T> source)
    {
        source.forEachWithOccurrences(this::addOccurrences);
        return source.notEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> collection)
    {
        return this.removeAllIterable(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection)
    {
        return this.retainAllIterable(collection);
    }

    @Override
    public boolean retainAllIterable(Iterable<?> iterable)
    {
        int oldSize = this.size();
        this.removeIfWith(Predicates2.notIn(), UnifiedSet.newSet(iterable));
        return this.size() != oldSize;
    }

    @Override
    public <P> Twin<MutableList<T>> selectAndRejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return IterableIterate.selectAndRejectWith(this, predicate, parameter);
    }

    @Override
    public T getFirst()
    {
        return this.getKeysView().getFirst();
    }

    @Override
    public T getLast()
    {
        return this.getKeysView().getLast();
    }

    @Override
    public <V> MutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        return this.groupByUniqueKey(function, UnifiedMap.newMap(this.size()));
    }

    @Override
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

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return this.getKeysView().detect(predicate);
    }

    @Override
    public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getKeysView().detectWith(predicate, parameter);
    }

    @Override
    public Optional<T> detectOptional(Predicate<? super T> predicate)
    {
        return this.getKeysView().detectOptional(predicate);
    }

    @Override
    public <P> Optional<T> detectWithOptional(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getKeysView().detectWithOptional(predicate, parameter);
    }

    @Override
    public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
    {
        return this.getKeysView().detectIfNone(predicate, function);
    }

    @Override
    public <P> T detectWithIfNone(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            Function0<? extends T> function)
    {
        return this.getKeysView().detectIfNone(each -> predicate.accept(each, parameter), function);
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return this.getKeysView().anySatisfy(predicate);
    }

    @Override
    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getKeysView().anySatisfyWith(predicate, parameter);
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return this.getKeysView().allSatisfy(predicate);
    }

    @Override
    public <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getKeysView().allSatisfyWith(predicate, parameter);
    }

    @Override
    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return this.getKeysView().noneSatisfy(predicate);
    }

    @Override
    public <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getKeysView().noneSatisfyWith(predicate, parameter);
    }

    /**
     * @since 9.0
     */
    @Override
    public <V> MutableBag<V> countBy(Function<? super T, ? extends V> function)
    {
        return this.countBy(function, Bags.mutable.empty());
    }

    /**
     * @since 9.0
     */
    @Override
    public <V, P> MutableBag<V> countByWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.countByWith(function, parameter, Bags.mutable.empty());
    }

    /**
     * @since 10.0.0
     */
    @Override
    public <V> MutableBag<V> countByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.countByEach(function, Bags.mutable.empty());
    }

    @Override
    public T min()
    {
        return this.getKeysView().min();
    }

    @Override
    public T min(Comparator<? super T> comparator)
    {
        return this.getKeysView().min(comparator);
    }

    @Override
    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        return this.getKeysView().minBy(function);
    }

    @Override
    public T max()
    {
        return this.getKeysView().max();
    }

    @Override
    public T max(Comparator<? super T> comparator)
    {
        return this.getKeysView().max(comparator);
    }

    @Override
    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        return this.getKeysView().maxBy(function);
    }

    @Override
    public <K, V> MutableMap<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new MutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map;
    }

    @Override
    public <K, V> MutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new NonMutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map;
    }

    @Override
    public <V> MutableObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function)
    {
        MutableObjectLongMap<V> result = ObjectLongHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByIntFunction(groupBy, function));
    }

    @Override
    public <V> MutableObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function)
    {
        MutableObjectDoubleMap<V> result = ObjectDoubleHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByFloatFunction(groupBy, function));
    }

    @Override
    public <V> MutableObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function)
    {
        MutableObjectLongMap<V> result = ObjectLongHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByLongFunction(groupBy, function));
    }

    @Override
    public <V> MutableObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function)
    {
        MutableObjectDoubleMap<V> result = ObjectDoubleHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByDoubleFunction(groupBy, function));
    }

    @Override
    public MutableList<ObjectIntPair<T>> topOccurrences(int n)
    {
        return this.occurrencesSortingBy(n, item -> -item.getTwo(), Lists.mutable.empty());
    }

    @Override
    public MutableList<ObjectIntPair<T>> bottomOccurrences(int n)
    {
        return this.occurrencesSortingBy(n, ObjectIntPair::getTwo, Lists.mutable.empty());
    }
}
