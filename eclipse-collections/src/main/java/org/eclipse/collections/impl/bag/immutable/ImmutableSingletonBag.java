/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.immutable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.ObjectIntToObjectFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.procedure.MultimapEachPutProcedure;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * @since 1.0
 */
final class ImmutableSingletonBag<T>
        extends AbstractImmutableBag<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final T value;

    ImmutableSingletonBag(T object)
    {
        this.value = object;
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return predicate.accept(this.value);
    }

    @Override
    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return !predicate.accept(this.value);
    }

    @Override
    public <IV> IV injectInto(IV injectedValue, Function2<? super IV, ? super T, ? extends IV> function)
    {
        return function.value(injectedValue, this.value);
    }

    @Override
    public T min(Comparator<? super T> comparator)
    {
        return this.value;
    }

    @Override
    public T max(Comparator<? super T> comparator)
    {
        return this.value;
    }

    @Override
    public T min()
    {
        return this.value;
    }

    @Override
    public T max()
    {
        return this.value;
    }

    @Override
    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        return this.value;
    }

    @Override
    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        return this.value;
    }

    @Override
    public ImmutableBag<T> newWith(T element)
    {
        return Bags.immutable.with(this.value, element);
    }

    @Override
    public ImmutableBag<T> newWithout(T element)
    {
        return this.emptyIfMatchesOrThis(Predicates.equal(element));
    }

    private ImmutableBag<T> emptyIfMatchesOrThis(Predicate<Object> predicate)
    {
        return predicate.accept(this.value) ? Bags.immutable.empty() : this;
    }

    @Override
    public ImmutableBag<T> newWithAll(Iterable<? extends T> elements)
    {
        MutableBag<T> bag = HashBag.newBag(elements);
        return bag.with(this.value).toImmutable();
    }

    @Override
    public ImmutableBag<T> newWithoutAll(Iterable<? extends T> elements)
    {
        return this.emptyIfMatchesOrThis(Predicates.in(elements));
    }

    @Override
    public int size()
    {
        return 1;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public boolean notEmpty()
    {
        return true;
    }

    @Override
    public T getFirst()
    {
        return this.value;
    }

    @Override
    public T getLast()
    {
        return this.value;
    }

    @Override
    public T getOnly()
    {
        return this.value;
    }

    @Override
    public boolean contains(Object object)
    {
        return Comparators.nullSafeEquals(this.value, object);
    }

    @Override
    public boolean containsAllIterable(Iterable<?> source)
    {
        return Iterate.allSatisfy(source, Predicates.equal(this.value));
    }

    @Override
    public boolean containsAllArguments(Object... elements)
    {
        return ArrayIterate.allSatisfy(elements, Predicates.equal(this.value));
    }

    @Override
    public ImmutableBag<T> selectByOccurrences(IntPredicate predicate)
    {
        return predicate.accept(1)
                ? this
                : Bags.immutable.empty();
    }

    @Override
    public ImmutableBag<T> select(Predicate<? super T> predicate)
    {
        return predicate.accept(this.value)
                ? this
                : Bags.immutable.empty();
    }

    @Override
    public <R extends Collection<T>> R select(Predicate<? super T> predicate, R target)
    {
        if (predicate.accept(this.value))
        {
            target.add(this.value);
        }
        return target;
    }

    @Override
    public <P, R extends Collection<T>> R selectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            R target)
    {
        if (predicate.accept(this.value, parameter))
        {
            target.add(this.value);
        }
        return target;
    }

    @Override
    public ImmutableBag<T> reject(Predicate<? super T> predicate)
    {
        return predicate.accept(this.value)
                ? Bags.immutable.empty()
                : this;
    }

    @Override
    public <R extends Collection<T>> R reject(Predicate<? super T> predicate, R target)
    {
        if (!predicate.accept(this.value))
        {
            target.add(this.value);
        }
        return target;
    }

    @Override
    public <P, R extends Collection<T>> R rejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            R target)
    {
        if (!predicate.accept(this.value, parameter))
        {
            target.add(this.value);
        }
        return target;
    }

    @Override
    public <S> ImmutableBag<S> selectInstancesOf(Class<S> clazz)
    {
        return clazz.isInstance(this.value)
                ? (ImmutableBag<S>) this
                : Bags.immutable.empty();
    }

    @Override
    public <V> ImmutableBag<V> collect(Function<? super T, ? extends V> function)
    {
        return Bags.immutable.with(function.valueOf(this.value));
    }

    @Override
    public <V> ImmutableBag<V> collectIf(
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function)
    {
        return predicate.accept(this.value)
                ? Bags.immutable.with(function.valueOf(this.value))
                : Bags.immutable.empty();
    }

    @Override
    public <V, R extends Collection<V>> R collectIf(
            Predicate<? super T> predicate, Function<? super T, ? extends V> function, R target)
    {
        if (predicate.accept(this.value))
        {
            target.add(function.valueOf(this.value));
        }
        return target;
    }

    @Override
    public <V> ImmutableBag<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.flatCollect(function, HashBag.newBag()).toImmutable();
    }

    @Override
    public <V, R extends Collection<V>> R flatCollect(
            Function<? super T, ? extends Iterable<V>> function, R target)
    {
        Iterate.addAllTo(function.valueOf(this.value), target);
        return target;
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return predicate.accept(this.value)
                ? this.value
                : null;
    }

    @Override
    public Optional<T> detectOptional(Predicate<? super T> predicate)
    {
        return predicate.accept(this.value)
                ? Optional.of(this.value)
                : Optional.empty();
    }

    @Override
    public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
    {
        return predicate.accept(this.value)
                ? this.value
                : function.value();
    }

    @Override
    public int count(Predicate<? super T> predicate)
    {
        return predicate.accept(this.value)
                ? 1
                : 0;
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return predicate.accept(this.value);
    }

    @Override
    public <V> ImmutableBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.groupBy(function, HashBagMultimap.<V, T>newMultimap()).toImmutable();
    }

    @Override
    public <V, R extends MutableMultimap<V, T>> R groupBy(Function<? super T, ? extends V> function, R target)
    {
        target.putAll(function.valueOf(this.value), this);
        return target;
    }

    @Override
    public <V> ImmutableBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.groupByEach(function, HashBagMultimap.newMultimap()).toImmutable();
    }

    @Override
    public <V, R extends MutableMultimap<V, T>> R groupByEach(Function<? super T, ? extends Iterable<V>> function, R target)
    {
        this.forEach(MultimapEachPutProcedure.on(target, function));
        return target;
    }

    @Override
    public int sizeDistinct()
    {
        return 1;
    }

    @Override
    public int occurrencesOf(Object item)
    {
        return Comparators.nullSafeEquals(this.value, item) ? 1 : 0;
    }

    @Override
    public void forEachWithOccurrences(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        objectIntProcedure.value(this.value, 1);
    }

    @Override
    public <V, R extends Collection<V>> R collectWithOccurrences(ObjectIntToObjectFunction<? super T, ? extends V> function, R target)
    {
        target.add(function.valueOf(this.value, 1));
        return target;
    }

    @Override
    public MutableMap<T, Integer> toMapOfItemToCount()
    {
        return UnifiedMap.newWithKeysValues(this.value, 1);
    }

    @Override
    public ImmutableBag<T> toImmutable()
    {
        return this;
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        procedure.value(this.value);
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        objectIntProcedure.value(this.value, 0);
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        procedure.value(this.value, parameter);
    }

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Override
    @Deprecated
    public <S> ImmutableBag<Pair<T, S>> zip(Iterable<S> that)
    {
        Iterator<S> iterator = that.iterator();
        if (!iterator.hasNext())
        {
            return Bags.immutable.empty();
        }
        return Bags.immutable.with(Tuples.pair(this.value, iterator.next()));
    }

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Override
    @Deprecated
    public ImmutableSet<Pair<T, Integer>> zipWithIndex()
    {
        return Sets.immutable.with(Tuples.pair(this.value, 0));
    }

    @Override
    public Iterator<T> iterator()
    {
        return new SingletonIterator();
    }

    private class SingletonIterator
            implements Iterator<T>
    {
        private boolean next = true;

        @Override
        public boolean hasNext()
        {
            return this.next;
        }

        @Override
        public T next()
        {
            if (this.next)
            {
                this.next = false;
                return ImmutableSingletonBag.this.value;
            }
            throw new NoSuchElementException("i=" + this.next);
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Cannot remove from an ImmutableBag");
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Bag))
        {
            return false;
        }
        Bag<?> bag = (Bag<?>) obj;
        if (this.size() != bag.size())
        {
            return false;
        }
        return this.occurrencesOf(this.value) == bag.occurrencesOf(this.value);
    }

    @Override
    public String toString()
    {
        return '[' + this.makeString() + ']';
    }

    @Override
    public int hashCode()
    {
        return (this.value == null ? 0 : this.value.hashCode()) ^ 1;
    }

    private Object writeReplace()
    {
        return new ImmutableBagSerializationProxy<>(this);
    }

    @Override
    public ImmutableSet<T> selectUnique()
    {
        return Sets.immutable.of(this.value);
    }
}
