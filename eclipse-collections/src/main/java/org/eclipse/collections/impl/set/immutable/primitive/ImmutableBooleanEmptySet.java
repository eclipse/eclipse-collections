/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.immutable.primitive;

import java.io.IOException;
import java.io.Serializable;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.block.function.primitive.BooleanToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectBooleanToObjectFunction;
import org.eclipse.collections.api.block.predicate.primitive.BooleanPredicate;
import org.eclipse.collections.api.block.procedure.primitive.BooleanProcedure;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.primitive.BooleanSet;
import org.eclipse.collections.api.set.primitive.ImmutableBooleanSet;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.iterator.ImmutableEmptyBooleanIterator;
import org.eclipse.collections.impl.lazy.primitive.LazyBooleanIterableAdapter;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import net.jcip.annotations.Immutable;

@Immutable
final class ImmutableBooleanEmptySet implements ImmutableBooleanSet, Serializable
{
    static final ImmutableBooleanSet INSTANCE = new ImmutableBooleanEmptySet();

    private static final boolean[] TO_ARRAY = new boolean[0];

    private ImmutableBooleanEmptySet()
    {
        // Singleton
    }

    public ImmutableBooleanSet newWith(boolean element)
    {
        return element ? ImmutableTrueSet.INSTANCE : ImmutableFalseSet.INSTANCE;
    }

    public ImmutableBooleanSet newWithout(boolean element)
    {
        return this;
    }

    public ImmutableBooleanSet newWithAll(BooleanIterable elements)
    {
        ImmutableBooleanSet result = this;
        BooleanIterator booleanIterator = elements.booleanIterator();
        while (booleanIterator.hasNext())
        {
            result = result.newWith(booleanIterator.next());
        }
        return result;
    }

    public ImmutableBooleanSet newWithoutAll(BooleanIterable elements)
    {
        return this;
    }

    public BooleanIterator booleanIterator()
    {
        return ImmutableEmptyBooleanIterator.INSTANCE;
    }

    public void forEach(BooleanProcedure procedure)
    {
    }

    /**
     * @since 7.0.
     */
    public void each(BooleanProcedure procedure)
    {
    }

    public <T> T injectInto(T injectedValue, ObjectBooleanToObjectFunction<? super T, ? extends T> function)
    {
        return injectedValue;
    }

    public int count(BooleanPredicate predicate)
    {
        return 0;
    }

    public boolean anySatisfy(BooleanPredicate predicate)
    {
        return false;
    }

    public boolean allSatisfy(BooleanPredicate predicate)
    {
        return true;
    }

    public boolean noneSatisfy(BooleanPredicate predicate)
    {
        return false;
    }

    public ImmutableBooleanSet select(BooleanPredicate predicate)
    {
        return this;
    }

    public ImmutableBooleanSet reject(BooleanPredicate predicate)
    {
        return this;
    }

    public boolean detectIfNone(BooleanPredicate predicate, boolean ifNone)
    {
        return ifNone;
    }

    public <V> ImmutableSet<V> collect(BooleanToObjectFunction<? extends V> function)
    {
        return Sets.immutable.with();
    }

    public boolean[] toArray()
    {
        return TO_ARRAY;
    }

    public boolean contains(boolean value)
    {
        return false;
    }

    public boolean containsAll(boolean... source)
    {
        return source.length == 0;
    }

    public boolean containsAll(BooleanIterable source)
    {
        BooleanIterator iterator = source.booleanIterator();
        return !iterator.hasNext();
    }

    public BooleanSet freeze()
    {
        return this;
    }

    public ImmutableBooleanSet toImmutable()
    {
        return this;
    }

    public int size()
    {
        return 0;
    }

    public boolean isEmpty()
    {
        return true;
    }

    public boolean notEmpty()
    {
        return false;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (!(obj instanceof BooleanSet))
        {
            return false;
        }

        BooleanSet other = (BooleanSet) obj;
        return other.isEmpty();
    }

    @Override
    public int hashCode()
    {
        return 0;
    }

    @Override
    public String toString()
    {
        return "[]";
    }

    public String makeString()
    {
        return "";
    }

    public String makeString(String separator)
    {
        return "";
    }

    public String makeString(String start, String separator, String end)
    {
        return start + end;
    }

    public void appendString(Appendable appendable)
    {
    }

    public void appendString(Appendable appendable, String separator)
    {
    }

    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        try
        {
            appendable.append(start);
            appendable.append(end);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public MutableBooleanList toList()
    {
        return BooleanArrayList.newList(this);
    }

    public MutableBooleanSet toSet()
    {
        return BooleanHashSet.newSet(this);
    }

    public MutableBooleanBag toBag()
    {
        return BooleanHashBag.newBag(this);
    }

    public LazyBooleanIterable asLazy()
    {
        return new LazyBooleanIterableAdapter(this);
    }

    private Object writeReplace()
    {
        return new ImmutableBooleanSetSerializationProxy(this);
    }
}
