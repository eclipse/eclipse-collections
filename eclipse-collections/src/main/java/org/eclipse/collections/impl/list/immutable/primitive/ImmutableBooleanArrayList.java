/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.list.immutable.primitive;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.BitSet;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.block.function.primitive.BooleanToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectBooleanIntToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectBooleanToObjectFunction;
import org.eclipse.collections.api.block.predicate.primitive.BooleanPredicate;
import org.eclipse.collections.api.block.procedure.primitive.BooleanIntProcedure;
import org.eclipse.collections.api.block.procedure.primitive.BooleanProcedure;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.primitive.BooleanList;
import org.eclipse.collections.api.list.primitive.ImmutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.lazy.primitive.LazyBooleanIterableAdapter;
import org.eclipse.collections.impl.lazy.primitive.ReverseBooleanIterable;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import net.jcip.annotations.Immutable;

/**
 * ImmutableBooleanArrayList is the non-modifiable equivalent of {@link BooleanArrayList}.
 * It is backed by a {@link BitSet}.
 *
 * @since 3.2.
 */
@Immutable
final class ImmutableBooleanArrayList
        implements ImmutableBooleanList, Serializable
{
    private static final long serialVersionUID = 1L;
    private final int size;
    private final BitSet items;

    private ImmutableBooleanArrayList(boolean[] newElements)
    {
        if (newElements.length <= 1)
        {
            throw new IllegalArgumentException("Use BooleanLists.immutable.with() to instantiate an optimized collection");
        }
        this.size = newElements.length;
        this.items = new BitSet(newElements.length);
        for (int i = 0; i < newElements.length; i++)
        {
            if (newElements[i])
            {
                this.items.set(i);
            }
        }
    }

    private ImmutableBooleanArrayList(BitSet newItems, int size)
    {
        this.size = size;
        this.items = newItems;
    }

    public static ImmutableBooleanArrayList newList(BooleanIterable iterable)
    {
        return new ImmutableBooleanArrayList(iterable.toArray());
    }

    public static ImmutableBooleanArrayList newListWith(boolean... elements)
    {
        return new ImmutableBooleanArrayList(elements);
    }

    private IndexOutOfBoundsException newIndexOutOfBoundsException(int index)
    {
        return new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size);
    }

    public boolean get(int index)
    {
        if (index < this.size)
        {
            return this.items.get(index);
        }
        throw this.newIndexOutOfBoundsException(index);
    }

    public boolean getFirst()
    {
        return this.items.get(0);
    }

    public boolean getLast()
    {
        return this.items.get(this.size - 1);
    }

    public int indexOf(boolean value)
    {
        for (int i = 0; i < this.size; i++)
        {
            if (this.items.get(i) == value)
            {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(boolean value)
    {
        for (int i = this.size - 1; i >= 0; i--)
        {
            if (this.items.get(i) == value)
            {
                return i;
            }
        }
        return -1;
    }

    public BooleanIterator booleanIterator()
    {
        return new InternalBooleanIterator();
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
        for (int i = 0; i < this.size; i++)
        {
            procedure.value(this.items.get(i));
        }
    }

    public void forEachWithIndex(BooleanIntProcedure procedure)
    {
        for (int i = 0; i < this.size; i++)
        {
            procedure.value(this.items.get(i), i);
        }
    }

    public int count(BooleanPredicate predicate)
    {
        int count = 0;
        for (int i = 0; i < this.size; i++)
        {
            if (predicate.accept(this.items.get(i)))
            {
                count++;
            }
        }
        return count;
    }

    public boolean anySatisfy(BooleanPredicate predicate)
    {
        for (int i = 0; i < this.size; i++)
        {
            if (predicate.accept(this.items.get(i)))
            {
                return true;
            }
        }
        return false;
    }

    public boolean allSatisfy(BooleanPredicate predicate)
    {
        for (int i = 0; i < this.size; i++)
        {
            if (!predicate.accept(this.items.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    public boolean noneSatisfy(BooleanPredicate predicate)
    {
        for (int i = 0; i < this.size; i++)
        {
            if (predicate.accept(this.items.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    public ImmutableBooleanList select(BooleanPredicate predicate)
    {
        MutableBooleanList result = BooleanLists.mutable.empty();
        for (int i = 0; i < this.size; i++)
        {
            boolean item = this.items.get(i);
            if (predicate.accept(item))
            {
                result.add(item);
            }
        }
        return result.toImmutable();
    }

    public ImmutableBooleanList reject(BooleanPredicate predicate)
    {
        MutableBooleanList result = BooleanLists.mutable.empty();
        for (int i = 0; i < this.size; i++)
        {
            boolean item = this.items.get(i);
            if (!predicate.accept(item))
            {
                result.add(item);
            }
        }
        return result.toImmutable();
    }

    public boolean detectIfNone(BooleanPredicate predicate, boolean ifNone)
    {
        for (int i = 0; i < this.size; i++)
        {
            boolean item = this.items.get(i);
            if (predicate.accept(item))
            {
                return item;
            }
        }
        return ifNone;
    }

    public <V> ImmutableList<V> collect(BooleanToObjectFunction<? extends V> function)
    {
        FastList<V> target = FastList.newList(this.size);
        for (int i = 0; i < this.size; i++)
        {
            target.add(function.valueOf(this.items.get(i)));
        }
        return target.toImmutable();
    }

    public boolean[] toArray()
    {
        boolean[] newItems = new boolean[this.size];
        for (int i = 0; i < this.size; i++)
        {
            newItems[i] = this.items.get(i);
        }
        return newItems;
    }

    public boolean contains(boolean value)
    {
        for (int i = 0; i < this.size; i++)
        {
            if (this.items.get(i) == value)
            {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(boolean... source)
    {
        for (boolean value : source)
        {
            if (!this.contains(value))
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
            if (!this.contains(iterator.next()))
            {
                return false;
            }
        }
        return true;
    }

    public LazyBooleanIterable asReversed()
    {
        return ReverseBooleanIterable.adapt(this);
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

    public ImmutableBooleanList toImmutable()
    {
        return this;
    }

    public ImmutableBooleanArrayList toReversed()
    {
        return ImmutableBooleanArrayList.newList(this.asReversed());
    }

    /**
     * @since 6.0
     */
    public ImmutableBooleanList distinct()
    {
        BooleanArrayList target = new BooleanArrayList();
        MutableBooleanSet seenSoFar = new BooleanHashSet();
        for (int i = 0; i < this.size; i++)
        {
            boolean each = this.get(i);
            if (seenSoFar.add(each))
            {
                target.add(each);
            }
        }
        return target.toImmutable();
    }

    public ImmutableBooleanList newWith(boolean element)
    {
        BitSet newItems = (BitSet) this.items.clone();
        if (element)
        {
            newItems.set(this.size);
        }
        return new ImmutableBooleanArrayList(newItems, this.size + 1);
    }

    public ImmutableBooleanList newWithout(boolean element)
    {
        int index = this.indexOf(element);
        if (index != -1)
        {
            boolean[] newItems = new boolean[this.size - 1];
            for (int i = 0; i < index; i++)
            {
                newItems[i] = this.items.get(i);
            }
            for (int i = index + 1; i < this.size; i++)
            {
                newItems[i - 1] = this.items.get(i);
            }
            return BooleanLists.immutable.with(newItems);
        }
        return this;
    }

    public ImmutableBooleanList newWithAll(BooleanIterable elements)
    {
        BitSet newItems = (BitSet) this.items.clone();
        int index = 0;
        for (BooleanIterator booleanIterator = elements.booleanIterator(); booleanIterator.hasNext(); index++)
        {
            if (booleanIterator.next())
            {
                newItems.set(this.size + index);
            }
        }
        return new ImmutableBooleanArrayList(newItems, this.size + elements.size());
    }

    public ImmutableBooleanList newWithoutAll(BooleanIterable elements)
    {
        MutableBooleanList list = this.toList();
        list.removeAll(elements);
        return list.toImmutable();
    }

    public int size()
    {
        return this.size;
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
    public boolean equals(Object otherList)
    {
        if (otherList == this)
        {
            return true;
        }
        if (!(otherList instanceof BooleanList))
        {
            return false;
        }
        BooleanList list = (BooleanList) otherList;
        if (this.size != list.size())
        {
            return false;
        }
        for (int i = 0; i < this.size; i++)
        {
            if (this.items.get(i) != list.get(i))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hashCode = 1;
        for (int i = 0; i < this.size; i++)
        {
            boolean item = this.items.get(i);
            hashCode = 31 * hashCode + (item ? 1231 : 1237);
        }
        return hashCode;
    }

    public <T> T injectInto(T injectedValue, ObjectBooleanToObjectFunction<? super T, ? extends T> function)
    {
        T result = injectedValue;
        for (int i = 0; i < this.size; i++)
        {
            result = function.valueOf(result, this.items.get(i));
        }
        return result;
    }

    public <T> T injectIntoWithIndex(T injectedValue, ObjectBooleanIntToObjectFunction<? super T, ? extends T> function)
    {
        T result = injectedValue;
        for (int i = 0; i < this.size; i++)
        {
            result = function.valueOf(result, this.items.get(i), i);
        }
        return result;
    }

    @Override
    public String toString()
    {
        return this.makeString("[", ", ", "]");
    }

    public String makeString()
    {
        return this.makeString(", ");
    }

    public String makeString(String separator)
    {
        return this.makeString("", separator, "");
    }

    public String makeString(String start, String separator, String end)
    {
        Appendable stringBuilder = new StringBuilder();
        this.appendString(stringBuilder, start, separator, end);
        return stringBuilder.toString();
    }

    public void appendString(Appendable appendable)
    {
        this.appendString(appendable, ", ");
    }

    public void appendString(Appendable appendable, String separator)
    {
        this.appendString(appendable, "", separator, "");
    }

    public void appendString(
            Appendable appendable,
            String start,
            String separator,
            String end)
    {
        try
        {
            appendable.append(start);
            for (int i = 0; i < this.size; i++)
            {
                if (i > 0)
                {
                    appendable.append(separator);
                }
                boolean value = this.items.get(i);
                appendable.append(String.valueOf(value));
            }
            appendable.append(end);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ImmutableBooleanList subList(int fromIndex, int toIndex)
    {
        throw new UnsupportedOperationException("subList not yet implemented!");
    }

    private Object writeReplace()
    {
        return new ImmutableBooleanListSerializationProxy(this);
    }

    private static class ImmutableBooleanListSerializationProxy implements Externalizable
    {
        private static final long serialVersionUID = 1L;
        private ImmutableBooleanList list;

        public ImmutableBooleanListSerializationProxy()
        {
            // Empty constructor for Externalizable class
        }

        private ImmutableBooleanListSerializationProxy(ImmutableBooleanList list)
        {
            this.list = list;
        }

        public void writeExternal(ObjectOutput out) throws IOException
        {
            out.writeInt(this.list.size());
            for (int i = 0; i < this.list.size(); i++)
            {
                out.writeBoolean(this.list.get(i));
            }
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
        {
            int inputSize = in.readInt();
            BitSet newItems = new BitSet(inputSize);

            for (int i = 0; i < inputSize; i++)
            {
                newItems.set(i, in.readBoolean());
            }

            this.list = new ImmutableBooleanArrayList(newItems, inputSize);
        }

        protected Object readResolve()
        {
            return this.list;
        }
    }

    private class InternalBooleanIterator implements BooleanIterator
    {
        /**
         * Index of element to be returned by subsequent call to next.
         */
        private int currentIndex;

        public boolean hasNext()
        {
            return this.currentIndex != ImmutableBooleanArrayList.this.size;
        }

        public boolean next()
        {
            if (!this.hasNext())
            {
                throw new NoSuchElementException();
            }
            boolean next = ImmutableBooleanArrayList.this.get(this.currentIndex);
            this.currentIndex++;
            return next;
        }
    }
}
