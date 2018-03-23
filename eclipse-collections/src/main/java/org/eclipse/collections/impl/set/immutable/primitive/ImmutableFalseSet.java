/*
 * Copyright (c) 2018 Goldman Sachs.
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
import java.util.NoSuchElementException;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.RichIterable;
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
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.lazy.primitive.LazyBooleanIterableAdapter;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;

final class ImmutableFalseSet implements ImmutableBooleanSet, Serializable
{
    static final ImmutableBooleanSet INSTANCE = new ImmutableFalseSet();

    private ImmutableFalseSet()
    {
        // Singleton
    }

    @Override
    public ImmutableBooleanSet newWith(boolean element)
    {
        return element ? ImmutableTrueFalseSet.INSTANCE : this;
    }

    @Override
    public ImmutableBooleanSet newWithout(boolean element)
    {
        return element ? this : ImmutableBooleanEmptySet.INSTANCE;
    }

    @Override
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

    @Override
    public ImmutableBooleanSet newWithoutAll(BooleanIterable elements)
    {
        return elements.contains(false) ? ImmutableBooleanEmptySet.INSTANCE : this;
    }

    @Override
    public BooleanIterator booleanIterator()
    {
        return new FalseIterator();
    }

    @Override
    public void forEach(BooleanProcedure procedure)
    {
        this.each(procedure);
    }

    /**
     * @since 7.0.
     */
    @Override
    public void each(BooleanProcedure procedure)
    {
        procedure.value(false);
    }

    @Override
    public <T> T injectInto(T injectedValue, ObjectBooleanToObjectFunction<? super T, ? extends T> function)
    {
        return function.valueOf(injectedValue, false);
    }

    @Override
    public RichIterable<BooleanIterable> chunk(int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Size for groups must be positive but was: " + size);
        }
        return Lists.immutable.with(this);
    }

    @Override
    public int count(BooleanPredicate predicate)
    {
        return predicate.accept(false) ? 1 : 0;
    }

    @Override
    public boolean anySatisfy(BooleanPredicate predicate)
    {
        return predicate.accept(false);
    }

    @Override
    public boolean allSatisfy(BooleanPredicate predicate)
    {
        return predicate.accept(false);
    }

    @Override
    public boolean noneSatisfy(BooleanPredicate predicate)
    {
        return !predicate.accept(false);
    }

    @Override
    public ImmutableBooleanSet select(BooleanPredicate predicate)
    {
        return predicate.accept(false) ? this : ImmutableBooleanEmptySet.INSTANCE;
    }

    @Override
    public ImmutableBooleanSet reject(BooleanPredicate predicate)
    {
        return predicate.accept(false) ? ImmutableBooleanEmptySet.INSTANCE : this;
    }

    @Override
    public boolean detectIfNone(BooleanPredicate predicate, boolean ifNone)
    {
        return !predicate.accept(false) && ifNone;
    }

    @Override
    public <V> ImmutableSet<V> collect(BooleanToObjectFunction<? extends V> function)
    {
        return Sets.immutable.with(function.valueOf(false));
    }

    @Override
    public boolean[] toArray()
    {
        return new boolean[]{false};
    }

    @Override
    public boolean contains(boolean value)
    {
        return !value;
    }

    @Override
    public boolean containsAll(boolean... source)
    {
        for (boolean item : source)
        {
            if (item)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(BooleanIterable source)
    {
        for (BooleanIterator iterator = source.booleanIterator(); iterator.hasNext(); )
        {
            if (iterator.next())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public BooleanSet freeze()
    {
        return this;
    }

    @Override
    public ImmutableBooleanSet toImmutable()
    {
        return this;
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
        return other.contains(false) && !other.contains(true);
    }

    @Override
    public int hashCode()
    {
        return 1237;
    }

    @Override
    public String toString()
    {
        return "[false]";
    }

    @Override
    public String makeString()
    {
        return "false";
    }

    @Override
    public String makeString(String separator)
    {
        return "false";
    }

    @Override
    public String makeString(String start, String separator, String end)
    {
        return start + "false" + end;
    }

    @Override
    public void appendString(Appendable appendable)
    {
        try
        {
            appendable.append("false");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void appendString(Appendable appendable, String separator)
    {
        try
        {
            appendable.append("false");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        try
        {
            appendable.append(start);
            appendable.append("false");
            appendable.append(end);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MutableBooleanList toList()
    {
        return BooleanArrayList.newList(this);
    }

    @Override
    public MutableBooleanSet toSet()
    {
        return BooleanHashSet.newSet(this);
    }

    @Override
    public MutableBooleanBag toBag()
    {
        return BooleanHashBag.newBag(this);
    }

    @Override
    public LazyBooleanIterable asLazy()
    {
        return new LazyBooleanIterableAdapter(this);
    }

    private static final class FalseIterator implements BooleanIterator
    {
        private int currentIndex;

        @Override
        public boolean next()
        {
            if (this.currentIndex == 0)
            {
                this.currentIndex++;
                return false;
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasNext()
        {
            return this.currentIndex == 0;
        }
    }

    private Object writeReplace()
    {
        return new ImmutableBooleanSetSerializationProxy(this);
    }
}
