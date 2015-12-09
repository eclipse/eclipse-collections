/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.set.immutable.primitive;

import java.io.IOException;
import java.io.Serializable;
import java.util.NoSuchElementException;

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
import org.eclipse.collections.impl.lazy.primitive.LazyBooleanIterableAdapter;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import net.jcip.annotations.Immutable;

@Immutable
final class ImmutableFalseSet implements ImmutableBooleanSet, Serializable
{
    static final ImmutableBooleanSet INSTANCE = new ImmutableFalseSet();

    private ImmutableFalseSet()
    {
        // Singleton
    }

    public ImmutableBooleanSet newWith(boolean element)
    {
        return element ? ImmutableTrueFalseSet.INSTANCE : this;
    }

    public ImmutableBooleanSet newWithout(boolean element)
    {
        return element ? this : ImmutableBooleanEmptySet.INSTANCE;
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
        return elements.contains(false) ? ImmutableBooleanEmptySet.INSTANCE : this;
    }

    public BooleanIterator booleanIterator()
    {
        return new FalseIterator();
    }

    public void forEach(BooleanProcedure procedure)
    {
        this.each(procedure);
    }

    /**
     * @since 7.0.
     */
    public void each(BooleanProcedure procedure)
    {
        procedure.value(false);
    }

    public <T> T injectInto(T injectedValue, ObjectBooleanToObjectFunction<? super T, ? extends T> function)
    {
        return function.valueOf(injectedValue, false);
    }

    public int count(BooleanPredicate predicate)
    {
        return predicate.accept(false) ? 1 : 0;
    }

    public boolean anySatisfy(BooleanPredicate predicate)
    {
        return predicate.accept(false);
    }

    public boolean allSatisfy(BooleanPredicate predicate)
    {
        return predicate.accept(false);
    }

    public boolean noneSatisfy(BooleanPredicate predicate)
    {
        return !predicate.accept(false);
    }

    public ImmutableBooleanSet select(BooleanPredicate predicate)
    {
        return predicate.accept(false) ? this : ImmutableBooleanEmptySet.INSTANCE;
    }

    public ImmutableBooleanSet reject(BooleanPredicate predicate)
    {
        return predicate.accept(false) ? ImmutableBooleanEmptySet.INSTANCE : this;
    }

    public boolean detectIfNone(BooleanPredicate predicate, boolean ifNone)
    {
        return !predicate.accept(false) && ifNone;
    }

    public <V> ImmutableSet<V> collect(BooleanToObjectFunction<? extends V> function)
    {
        return Sets.immutable.with(function.valueOf(false));
    }

    public boolean[] toArray()
    {
        return new boolean[]{false};
    }

    public boolean contains(boolean value)
    {
        return !value;
    }

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
        return 1;
    }

    public boolean isEmpty()
    {
        return false;
    }

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

    public String makeString()
    {
        return "false";
    }

    public String makeString(String separator)
    {
        return "false";
    }

    public String makeString(String start, String separator, String end)
    {
        return start + "false" + end;
    }

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

    private static final class FalseIterator implements BooleanIterator
    {
        private int currentIndex;

        public boolean next()
        {
            if (this.currentIndex == 0)
            {
                this.currentIndex++;
                return false;
            }
            throw new NoSuchElementException();
        }

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
