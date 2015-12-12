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
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleObjectToDoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatObjectToFloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntObjectToIntFunction;
import org.eclipse.collections.api.block.function.primitive.LongObjectToLongFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.lazy.iterator.CollectIterator;
import org.eclipse.collections.impl.utility.Iterate;
import net.jcip.annotations.Immutable;

/**
 * A CollectIterable is an iterable that transforms a source iterable using a function as it iterates.
 */
@Immutable
public class CollectIterable<T, V>
        extends AbstractLazyIterable<V>
{
    private final Iterable<T> adapted;
    private final Function<? super T, ? extends V> function;

    public CollectIterable(Iterable<T> newAdapted, Function<? super T, ? extends V> function)
    {
        this.adapted = newAdapted;
        this.function = function;
    }

    public void each(Procedure<? super V> procedure)
    {
        Iterate.forEach(this.adapted, Functions.bind(procedure, this.function));
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super V> objectIntProcedure)
    {
        Iterate.forEachWithIndex(this.adapted, Functions.bind(objectIntProcedure, this.function));
    }

    @Override
    public <P> void forEachWith(Procedure2<? super V, ? super P> procedure, P parameter)
    {
        Iterate.forEachWith(this.adapted, Functions.bind(procedure, this.function), parameter);
    }

    public Iterator<V> iterator()
    {
        return new CollectIterator<T, V>(this.adapted, this.function);
    }

    @Override
    public int size()
    {
        return Iterate.sizeOf(this.adapted);
    }

    @Override
    public boolean isEmpty()
    {
        return Iterate.isEmpty(this.adapted);
    }

    @Override
    public boolean notEmpty()
    {
        return !this.isEmpty();
    }

    @Override
    public Object[] toArray()
    {
        return this.toList().toArray();
    }

    @Override
    public boolean anySatisfy(Predicate<? super V> predicate)
    {
        return Iterate.anySatisfy(this.adapted, Predicates.attributePredicate(this.function, predicate));
    }

    @Override
    public <P> boolean anySatisfyWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.anySatisfy(Predicates.bind(predicate, parameter));
    }

    @Override
    public boolean allSatisfy(Predicate<? super V> predicate)
    {
        return Iterate.allSatisfy(this.adapted, Predicates.attributePredicate(this.function, predicate));
    }

    @Override
    public <P> boolean allSatisfyWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.allSatisfy(Predicates.bind(predicate, parameter));
    }

    @Override
    public boolean noneSatisfy(Predicate<? super V> predicate)
    {
        return Iterate.noneSatisfy(this.adapted, Predicates.attributePredicate(this.function, predicate));
    }

    @Override
    public <P> boolean noneSatisfyWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.noneSatisfy(Predicates.bind(predicate, parameter));
    }

    @Override
    public V detect(Predicate<? super V> predicate)
    {
        T resultItem = Iterate.detect(this.adapted, Predicates.attributePredicate(this.function, predicate));
        return resultItem == null ? null : this.function.valueOf(resultItem);
    }

    @Override
    public <P> V detectWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.detect(Predicates.bind(predicate, parameter));
    }

    @Override
    public <IV> IV injectInto(IV injectedValue, final Function2<? super IV, ? super V, ? extends IV> f)
    {
        return Iterate.injectInto(injectedValue, this.adapted, new Function2<IV, T, IV>()
        {
            public IV value(IV argument1, T argument2)
            {
                return f.value(argument1, CollectIterable.this.function.valueOf(argument2));
            }
        });
    }

    @Override
    public int injectInto(int injectedValue, final IntObjectToIntFunction<? super V> f)
    {
        return Iterate.injectInto(injectedValue, this.adapted, new IntObjectToIntFunction<T>()
        {
            public int intValueOf(int intParameter, T objectParameter)
            {
                return f.intValueOf(intParameter, CollectIterable.this.function.valueOf(objectParameter));
            }
        });
    }

    @Override
    public long injectInto(long injectedValue, final LongObjectToLongFunction<? super V> f)
    {
        return Iterate.injectInto(injectedValue, this.adapted, new LongObjectToLongFunction<T>()
        {
            public long longValueOf(long intParameter, T objectParameter)
            {
                return f.longValueOf(intParameter, CollectIterable.this.function.valueOf(objectParameter));
            }
        });
    }

    @Override
    public double injectInto(double injectedValue, final DoubleObjectToDoubleFunction<? super V> f)
    {
        return Iterate.injectInto(injectedValue, this.adapted, new DoubleObjectToDoubleFunction<T>()
        {
            public double doubleValueOf(double intParameter, T objectParameter)
            {
                return f.doubleValueOf(intParameter, CollectIterable.this.function.valueOf(objectParameter));
            }
        });
    }

    @Override
    public float injectInto(float injectedValue, final FloatObjectToFloatFunction<? super V> f)
    {
        return Iterate.injectInto(injectedValue, this.adapted, new FloatObjectToFloatFunction<T>()
        {
            public float floatValueOf(float intParameter, T objectParameter)
            {
                return f.floatValueOf(intParameter, CollectIterable.this.function.valueOf(objectParameter));
            }
        });
    }

    @Override
    public V getFirst()
    {
        if (this.isEmpty())
        {
            return null;
        }
        return this.function.valueOf(Iterate.getFirst(this.adapted));
    }

    @Override
    public V getLast()
    {
        if (this.isEmpty())
        {
            return null;
        }
        return this.function.valueOf(Iterate.getLast(this.adapted));
    }
}
