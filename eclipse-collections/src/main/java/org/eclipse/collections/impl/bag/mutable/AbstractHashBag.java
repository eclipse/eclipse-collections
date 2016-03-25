/*
 * Copyright (c) 2016 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableObjectIntMap;
import org.eclipse.collections.impl.Counter;
import org.eclipse.collections.impl.block.factory.primitive.IntToIntFunctions;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;

public abstract class AbstractHashBag<T> extends AbstractMutableBag<T>
{
    protected MutableObjectIntMap<T> items;
    protected int size;

    @Override
    public boolean addAll(Collection<? extends T> source)
    {
        if (source instanceof Bag)
        {
            return this.addAllBag((Bag<T>) source);
        }
        return super.addAll(source);
    }

    protected boolean addAllBag(Bag<? extends T> source)
    {
        source.forEachWithOccurrences(new ObjectIntProcedure<T>()
        {
            public void value(T each, int occurrences)
            {
                AbstractHashBag.this.addOccurrences(each, occurrences);
            }
        });
        return source.notEmpty();
    }

    public void addOccurrences(T item, int occurrences)
    {
        if (occurrences < 0)
        {
            throw new IllegalArgumentException("Cannot add a negative number of occurrences");
        }
        if (occurrences > 0)
        {
            this.items.updateValue(item, 0, IntToIntFunctions.add(occurrences));
            this.size += occurrences;
        }
    }

    public abstract MutableBag<T> selectByOccurrences(IntPredicate predicate);

    @Override
    protected RichIterable<T> getKeysView()
    {
        return this.items.keysView();
    }

    public int sizeDistinct()
    {
        return this.items.size();
    }

    public int occurrencesOf(Object item)
    {
        return this.items.get(item);
    }

    public void forEachWithOccurrences(ObjectIntProcedure<? super T> procedure)
    {
        this.items.forEachKeyValue(procedure);
    }

    public MutableMap<T, Integer> toMapOfItemToCount()
    {
        final MutableMap<T, Integer> map = UnifiedMap.newMap(this.items.size());
        this.forEachWithOccurrences(new ObjectIntProcedure<T>()
        {
            public void value(T item, int count)
            {
                map.put(item, count);
            }
        });
        return map;
    }

    public boolean remove(Object item)
    {
        int newValue = this.items.updateValue((T) item, 0, IntToIntFunctions.decrement());
        if (newValue <= 0)
        {
            this.items.removeKey((T) item);
            if (newValue == -1)
            {
                return false;
            }
        }
        this.size--;
        return true;
    }

    public void clear()
    {
        this.items.clear();
        this.size = 0;
    }

    @Override
    public boolean isEmpty()
    {
        return this.items.isEmpty();
    }

    public int size()
    {
        return this.size;
    }

    public void each(final Procedure<? super T> procedure)
    {
        this.items.forEachKeyValue(new ObjectIntProcedure<T>()
        {
            public void value(T key, int count)
            {
                for (int i = 0; i < count; i++)
                {
                    procedure.value(key);
                }
            }
        });
    }

    @Override
    public void forEachWithIndex(final ObjectIntProcedure<? super T> objectIntProcedure)
    {
        final Counter index = new Counter();
        this.items.forEachKeyValue(new ObjectIntProcedure<T>()
        {
            public void value(T key, int count)
            {
                for (int i = 0; i < count; i++)
                {
                    objectIntProcedure.value(key, index.getCount());
                    index.increment();
                }
            }
        });
    }

    @Override
    public <P> void forEachWith(final Procedure2<? super T, ? super P> procedure, final P parameter)
    {
        this.items.forEachKeyValue(new ObjectIntProcedure<T>()
        {
            public void value(T key, int count)
            {
                for (int i = 0; i < count; i++)
                {
                    procedure.value(key, parameter);
                }
            }
        });
    }

    public Iterator<T> iterator()
    {
        return new InternalIterator();
    }

    public boolean removeOccurrences(Object item, int occurrences)
    {
        if (occurrences < 0)
        {
            throw new IllegalArgumentException("Cannot remove a negative number of occurrences");
        }

        if (occurrences == 0)
        {
            return false;
        }

        int newValue = this.items.updateValue((T) item, 0, IntToIntFunctions.subtract(occurrences));

        if (newValue <= 0)
        {
            this.size -= occurrences + newValue;
            this.items.remove(item);
            return newValue + occurrences != 0;
        }

        this.size -= occurrences;
        return true;
    }

    public boolean setOccurrences(T item, int occurrences)
    {
        if (occurrences < 0)
        {
            throw new IllegalArgumentException("Cannot set a negative number of occurrences");
        }

        int originalOccurrences = this.items.get(item);

        if (originalOccurrences == occurrences)
        {
            return false;
        }

        if (occurrences == 0)
        {
            this.items.remove(item);
        }
        else
        {
            this.items.put(item, occurrences);
        }

        this.size -= originalOccurrences - occurrences;
        return true;
    }

    public boolean removeIf(Predicate<? super T> predicate)
    {
        boolean changed = false;
        for (Iterator<T> iterator = this.items.keySet().iterator(); iterator.hasNext(); )
        {
            T key = iterator.next();
            if (predicate.accept(key))
            {
                this.size -= this.items.get(key);
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    public <P> boolean removeIfWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        boolean changed = false;
        for (Iterator<T> iterator = this.items.keySet().iterator(); iterator.hasNext(); )
        {
            T key = iterator.next();
            if (predicate.accept(key, parameter))
            {
                this.size -= this.items.get(key);
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    public boolean removeAllIterable(Iterable<?> iterable)
    {
        int oldSize = this.size;
        if (iterable instanceof Bag)
        {
            Bag<?> source = (Bag<?>) iterable;
            source.forEachWithOccurrences(new ObjectIntProcedure<Object>()
            {
                public void value(Object each, int parameter)
                {
                    int removed = AbstractHashBag.this.items.removeKeyIfAbsent((T) each, 0);
                    AbstractHashBag.this.size -= removed;
                }
            });
        }
        else
        {
            for (Object each : iterable)
            {
                int removed = this.items.removeKeyIfAbsent((T) each, 0);
                this.size -= removed;
            }
        }
        return this.size != oldSize;
    }

    @Override
    public boolean contains(Object o)
    {
        return this.items.containsKey(o);
    }

    public <V> HashBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.groupBy(function, HashBagMultimap.<V, T>newMultimap());
    }

    public <V> HashBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.groupByEach(function, HashBagMultimap.<V, T>newMultimap());
    }

    private class InternalIterator implements Iterator<T>
    {
        private final Iterator<T> iterator = AbstractHashBag.this.items.keySet().iterator();

        private T currentItem;
        private int occurrences;
        private boolean canRemove;

        public boolean hasNext()
        {
            return this.occurrences > 0 || this.iterator.hasNext();
        }

        public T next()
        {
            if (this.occurrences == 0)
            {
                this.currentItem = this.iterator.next();
                this.occurrences = AbstractHashBag.this.occurrencesOf(this.currentItem);
            }
            this.occurrences--;
            this.canRemove = true;
            return this.currentItem;
        }

        public void remove()
        {
            if (!this.canRemove)
            {
                throw new IllegalStateException();
            }
            if (this.occurrences == 0)
            {
                this.iterator.remove();
                AbstractHashBag.this.size--;
            }
            else
            {
                AbstractHashBag.this.remove(this.currentItem);
            }
            this.canRemove = false;
        }
    }
}
