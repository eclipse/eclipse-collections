/*
 * Copyright (c) 2016 Bhavana Hindupur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.strategy.mutable;

import java.io.Serializable;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.predicate.primitive.ObjectIntPredicate;
import org.eclipse.collections.api.map.primitive.MutableObjectIntMap;
import org.eclipse.collections.impl.bag.mutable.AbstractHashBag;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectIntHashMapWithHashingStrategy;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.Iterate;

public class HashBagWithHashingStrategy<T>
        extends AbstractHashBag<T>
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final HashingStrategy<? super T> hashingStrategy;

    public HashBagWithHashingStrategy(HashingStrategy<? super T> hashingStrategy)
    {
        if (hashingStrategy == null)
        {
            throw new IllegalArgumentException("Cannot Instantiate HashBagWithHashingStrategy with null HashingStrategy");
        }
        this.hashingStrategy = hashingStrategy;
        this.items = ObjectIntHashMapWithHashingStrategy.newMap(hashingStrategy);
    }

    public HashBagWithHashingStrategy(HashingStrategy<? super T> hashingStrategy, int size)
    {
        if (hashingStrategy == null)
        {
            throw new IllegalArgumentException("Cannot Instantiate HashBagWithHashingStrategy with null HashingStrategy");
        }
        this.hashingStrategy = hashingStrategy;
        this.items = new ObjectIntHashMapWithHashingStrategy<T>(hashingStrategy, size);
    }

    private HashBagWithHashingStrategy(HashingStrategy<? super T> hashingStrategy, MutableObjectIntMap<T> map)
    {
        this.hashingStrategy = hashingStrategy;
        this.items = map;
        this.size = (int) map.sum();
    }

    public static <E> HashBagWithHashingStrategy<E> newBag(HashingStrategy<? super E> hashingStrategy)
    {
        return new HashBagWithHashingStrategy<E>(hashingStrategy);
    }

    public static <E> HashBagWithHashingStrategy<E> newBag(HashingStrategy<? super E> hashingStrategy, int size)
    {
        return new HashBagWithHashingStrategy<E>(hashingStrategy, size);
    }

    public static <E> HashBagWithHashingStrategy<E> newBag(HashingStrategy<? super E> hashingStrategy, Bag<? extends E> source)
    {
        HashBagWithHashingStrategy<E> result = HashBagWithHashingStrategy.newBag(hashingStrategy, source.sizeDistinct());
        result.addAllBag(source);
        return result;
    }

    public static <E> HashBagWithHashingStrategy<E> newBag(HashingStrategy<? super E> hashingStrategy, Iterable<? extends E> source)
    {
        if (source instanceof Bag)
        {
            return HashBagWithHashingStrategy.newBag(hashingStrategy, (Bag<E>) source);
        }
        return HashBagWithHashingStrategy.newBagWith(hashingStrategy, (E[]) Iterate.toArray(source));
    }

    public static <E> HashBagWithHashingStrategy<E> newBagWith(HashingStrategy<? super E> hashingStrategy, E... elements)
    {
        HashBagWithHashingStrategy<E> result = HashBagWithHashingStrategy.newBag(hashingStrategy);
        ArrayIterate.addAllTo(elements, result);
        return result;
    }

    public HashingStrategy<? super T> hashingStrategy()
    {
        return this.hashingStrategy;
    }

    @Override
    protected int computeHashCode(T item)
    {
        return this.hashingStrategy.computeHashCode(item);
    }

    @Override
    public MutableBag<T> selectByOccurrences(final IntPredicate predicate)
    {
        MutableObjectIntMap<T> map = this.items.select(new ObjectIntPredicate<T>()
        {
            public boolean accept(T each, int occurrences)
            {
                return predicate.accept(occurrences);
            }
        });
        return new HashBagWithHashingStrategy<T>(this.hashingStrategy, map);
    }

    protected Object writeReplace()
    {
        return new HashBagWithHashingStrategySerializationProxy<T>(this);
    }

    public MutableBag<T> with(T element)
    {
        this.add(element);
        return this;
    }

    public MutableBag<T> without(T element)
    {
        this.remove(element);
        return this;
    }

    public MutableBag<T> withAll(Iterable<? extends T> elements)
    {
        this.addAllIterable(elements);
        return this;
    }

    public MutableBag<T> withoutAll(Iterable<? extends T> elements)
    {
        this.removeAllIterable(elements);
        return this;
    }

    public MutableBag<T> newEmpty()
    {
        return HashBagWithHashingStrategy.newBag(this.hashingStrategy);
    }
}
