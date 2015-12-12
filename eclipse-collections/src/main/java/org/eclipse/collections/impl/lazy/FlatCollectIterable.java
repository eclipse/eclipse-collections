/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import java.util.Iterator;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.Counter;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.lazy.iterator.FlatCollectIterator;
import org.eclipse.collections.impl.utility.Iterate;
import net.jcip.annotations.Immutable;

@Immutable
public class FlatCollectIterable<T, V>
        extends AbstractLazyIterable<V>
{
    private final Iterable<T> adapted;
    private final Function<? super T, ? extends Iterable<V>> function;

    public FlatCollectIterable(Iterable<T> newAdapted, Function<? super T, ? extends Iterable<V>> function)
    {
        this.adapted = newAdapted;
        this.function = function;
    }

    public void each(final Procedure<? super V> procedure)
    {
        Iterate.forEach(this.adapted, new Procedure<T>()
        {
            public void value(T each)
            {
                Iterate.forEach(FlatCollectIterable.this.function.valueOf(each), procedure);
            }
        });
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super V> objectIntProcedure)
    {
        final Procedure<V> innerProcedure = new AdaptObjectIntProcedureToProcedure<V>(objectIntProcedure);
        Iterate.forEach(this.adapted, new Procedure<T>()
        {
            public void value(T each)
            {
                Iterable<V> iterable = FlatCollectIterable.this.function.valueOf(each);
                Iterate.forEach(iterable, innerProcedure);
            }
        });
    }

    @Override
    public <P> void forEachWith(final Procedure2<? super V, ? super P> procedure, final P parameter)
    {
        Iterate.forEach(this.adapted, new Procedure<T>()
        {
            public void value(T each)
            {
                Iterate.forEachWith(FlatCollectIterable.this.function.valueOf(each), procedure, parameter);
            }
        });
    }

    @Override
    public V detect(final Predicate<? super V> predicate)
    {
        final V[] result = (V[]) new Object[1];
        Iterate.detect(this.adapted, new Predicate<T>()
        {
            public boolean accept(T each)
            {
                Iterable<V> iterable = FlatCollectIterable.this.function.valueOf(each);
                return Iterate.anySatisfy(iterable, new Predicate<V>()
                {
                    public boolean accept(V each)
                    {
                        if (predicate.accept(each))
                        {
                            result[0] = each;
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
        return result[0];
    }

    @Override
    public boolean anySatisfy(final Predicate<? super V> predicate)
    {
        return Iterate.anySatisfy(this.adapted, new Predicate<T>()
        {
            public boolean accept(T each)
            {
                return Iterate.anySatisfy(FlatCollectIterable.this.function.valueOf(each), predicate);
            }
        });
    }

    @Override
    public <P> boolean anySatisfyWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.anySatisfy(Predicates.bind(predicate, parameter));
    }

    @Override
    public boolean allSatisfy(final Predicate<? super V> predicate)
    {
        return Iterate.allSatisfy(this.adapted, new Predicate<T>()
        {
            public boolean accept(T each)
            {
                return Iterate.allSatisfy(FlatCollectIterable.this.function.valueOf(each), predicate);
            }
        });
    }

    @Override
    public <P> boolean allSatisfyWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.allSatisfy(Predicates.bind(predicate, parameter));
    }

    @Override
    public boolean noneSatisfy(final Predicate<? super V> predicate)
    {
        return Iterate.noneSatisfy(this.adapted, new Predicate<T>()
        {
            public boolean accept(T each)
            {
                return Iterate.anySatisfy(FlatCollectIterable.this.function.valueOf(each), predicate);
            }
        });
    }

    @Override
    public <P> boolean noneSatisfyWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.noneSatisfy(Predicates.bind(predicate, parameter));
    }

    public Iterator<V> iterator()
    {
        return new FlatCollectIterator<T, V>(this.adapted, this.function);
    }

    private static final class AdaptObjectIntProcedureToProcedure<V> implements Procedure<V>
    {
        private static final long serialVersionUID = 1L;

        private final Counter index;
        private final ObjectIntProcedure<? super V> objectIntProcedure;

        private AdaptObjectIntProcedureToProcedure(ObjectIntProcedure<? super V> objectIntProcedure)
        {
            this.objectIntProcedure = objectIntProcedure;
            this.index = new Counter();
        }

        public void value(V each)
        {
            this.objectIntProcedure.value(each, this.index.getCount());
            this.index.increment();
        }
    }
}
