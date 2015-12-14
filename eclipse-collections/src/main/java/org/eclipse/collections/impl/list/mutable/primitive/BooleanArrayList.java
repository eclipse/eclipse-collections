/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable.primitive;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.BitSet;
import java.util.NoSuchElementException;

import net.jcip.annotations.NotThreadSafe;
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
import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.BooleanList;
import org.eclipse.collections.api.list.primitive.ImmutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.set.primitive.BooleanSet;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.lazy.primitive.LazyBooleanIterableAdapter;
import org.eclipse.collections.impl.lazy.primitive.ReverseBooleanIterable;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;

/**
 * BooleanArrayList is similar to {@link FastList}, and is memory-optimized for boolean primitives.
 *
 * @since 3.0.
 */
@NotThreadSafe
public final class BooleanArrayList
        implements MutableBooleanList, Externalizable
{
    private static final long serialVersionUID = 1L;
    private int size;
    private transient BitSet items;

    public BooleanArrayList()
    {
    }

    public BooleanArrayList(int initialCapacity)
    {
        if (initialCapacity != 0)
        {
            this.items = new BitSet(initialCapacity);
        }
    }

    public BooleanArrayList(boolean... array)
    {
        this.size = array.length;
        this.items = new BitSet(array.length);
        for (int i = 0; i < array.length; i++)
        {
            if (array[i])
            {
                this.items.set(i);
            }
        }
    }

    private BooleanArrayList(BooleanIterable booleanIterable)
    {
        this(booleanIterable.toArray());
    }

    public static BooleanArrayList newListWith(boolean... array)
    {
        return new BooleanArrayList(array);
    }

    public static BooleanArrayList newList(BooleanIterable source)
    {
        return new BooleanArrayList(source);
    }

    public int size()
    {
        return this.size;
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }

    public boolean notEmpty()
    {
        return this.size > 0;
    }

    public void clear()
    {
        if (this.items != null)
        {
            this.items.clear();
            this.size = 0;
        }
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

    public boolean get(int index)
    {
        if (index < this.size)
        {
            return this.items.get(index);
        }
        throw this.newIndexOutOfBoundsException(index);
    }

    private IndexOutOfBoundsException newIndexOutOfBoundsException(int index)
    {
        return new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size);
    }

    public boolean getFirst()
    {
        this.checkEmpty();
        return this.items.get(0);
    }

    public boolean getLast()
    {
        this.checkEmpty();
        return this.items.get(this.size() - 1);
    }

    private void checkEmpty()
    {
        if (this.isEmpty())
        {
            throw this.newIndexOutOfBoundsException(0);
        }
    }

    public int indexOf(boolean object)
    {
        for (int i = 0; i < this.size; i++)
        {
            if (this.items.get(i) == object)
            {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(boolean object)
    {
        for (int i = this.size - 1; i >= 0; i--)
        {
            if (this.items.get(i) == object)
            {
                return i;
            }
        }
        return -1;
    }

    public boolean add(boolean newItem)
    {
        if (this.size == 0)
        {
            this.items = new BitSet();
        }
        if (newItem)
        {
            this.items.set(this.size);
        }
        this.size++;
        return true;
    }

    public boolean addAll(boolean... source)
    {
        if (source.length < 1)
        {
            return false;
        }

        for (boolean sourceItem : source)
        {
            this.add(sourceItem);
        }
        return true;
    }

    public boolean addAll(BooleanIterable source)
    {
        return this.addAll(source.toArray());
    }

    public void addAtIndex(int index, boolean element)
    {
        if (index > -1 && index < this.size)
        {
            this.addAtIndexLessThanSize(index, element);
        }
        else if (index == this.size)
        {
            this.add(element);
        }
        else
        {
            throw this.newIndexOutOfBoundsException(index);
        }
    }

    private void addAtIndexLessThanSize(int index, boolean element)
    {
        for (int i = this.size + 1; i > index; i--)
        {
            this.items.set(i, this.items.get(i - 1));
        }
        this.items.set(index, element);
        this.size++;
    }

    public boolean addAllAtIndex(int index, boolean... source)
    {
        if (index > this.size || index < 0)
        {
            throw this.newIndexOutOfBoundsException(index);
        }
        if (source.length == 0)
        {
            return false;
        }
        int sourceSize = source.length;
        int newSize = this.size + sourceSize;

        for (int i = newSize; i > index; i--)
        {
            this.items.set(i, this.items.get(i - sourceSize));
        }

        for (int i = 0; i < sourceSize; i++)
        {
            this.items.set(i + index, source[i]);
        }

        this.size = newSize;
        return true;
    }

    public boolean addAllAtIndex(int index, BooleanIterable source)
    {
        return this.addAllAtIndex(index, source.toArray());
    }

    public boolean remove(boolean value)
    {
        int index = this.indexOf(value);
        if (index >= 0)
        {
            this.removeAtIndex(index);
            return true;
        }
        return false;
    }

    public boolean removeAll(BooleanIterable source)
    {
        boolean modified = false;
        for (int i = 0; i < this.size; i++)
        {
            if (source.contains(this.items.get(i)))
            {
                this.removeAtIndex(i);
                i--;
                modified = true;
            }
        }
        return modified;
    }

    public boolean removeAll(boolean... source)
    {
        if (this.isEmpty() || source.length == 0)
        {
            return false;
        }
        BooleanHashSet set = BooleanHashSet.newSetWith(source);
        if (set.size() == 2)
        {
            this.items = null;
            this.size = 0;
            return true;
        }
        int oldSize = this.size;
        int trueCount = this.getTrueCount();
        if (set.contains(true))
        {
            this.size -= trueCount;
            this.items.set(0, this.size, false);
        }
        else
        {
            this.size = trueCount;
            this.items.set(0, this.size, true);
        }
        return oldSize != this.size;
    }

    public boolean retainAll(BooleanIterable source)
    {
        int oldSize = this.size();
        final BooleanSet sourceSet = source instanceof BooleanSet ? (BooleanSet) source : source.toSet();
        BooleanArrayList retained = this.select(new BooleanPredicate()
        {
            public boolean accept(boolean value)
            {
                return sourceSet.contains(value);
            }
        });

        this.size = retained.size;
        this.items = retained.items;
        return oldSize != this.size();
    }

    public boolean retainAll(boolean... source)
    {
        return this.retainAll(BooleanHashSet.newSetWith(source));
    }

    private int getTrueCount()
    {
        int count = 0;
        for (int i = 0; i < this.size; i++)
        {
            if (this.items.get(i))
            {
                count++;
            }
        }
        return count;
    }

    public boolean removeAtIndex(int index)
    {
        boolean previous = this.get(index);
        if (this.size - index > 1)
        {
            for (int i = index; i < this.size; i++)
            {
                this.items.set(i, this.items.get(i + 1));
            }
        }
        --this.size;
        this.items.clear(this.size);
        return previous;
    }

    public boolean set(int index, boolean element)
    {
        boolean previous = this.get(index);
        this.items.set(index, element);
        return previous;
    }

    public BooleanArrayList with(boolean element)
    {
        this.add(element);
        return this;
    }

    public BooleanArrayList without(boolean element)
    {
        this.remove(element);
        return this;
    }

    public BooleanArrayList withAll(BooleanIterable elements)
    {
        this.addAll(elements.toArray());
        return this;
    }

    public BooleanArrayList withoutAll(BooleanIterable elements)
    {
        this.removeAll(elements);
        return this;
    }

    public BooleanArrayList with(boolean element1, boolean element2)
    {
        this.add(element1);
        this.add(element2);
        return this;
    }

    public BooleanArrayList with(boolean element1, boolean element2, boolean element3)
    {
        this.add(element1);
        this.add(element2);
        this.add(element3);
        return this;
    }

    public BooleanArrayList with(boolean element1, boolean element2, boolean element3, boolean... elements)
    {
        this.add(element1);
        this.add(element2);
        this.add(element3);
        this.addAll(elements);
        return this;
    }

    public MutableBooleanIterator booleanIterator()
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

    public BooleanArrayList select(BooleanPredicate predicate)
    {
        BooleanArrayList result = new BooleanArrayList();
        for (int i = 0; i < this.size; i++)
        {
            boolean item = this.items.get(i);
            if (predicate.accept(item))
            {
                result.add(item);
            }
        }
        return result;
    }

    public BooleanArrayList reject(BooleanPredicate predicate)
    {
        BooleanArrayList result = new BooleanArrayList();
        for (int i = 0; i < this.size; i++)
        {
            boolean item = this.items.get(i);
            if (!predicate.accept(item))
            {
                result.add(item);
            }
        }
        return result;
    }

    public LazyBooleanIterable asReversed()
    {
        return new ReverseBooleanIterable(this);
    }

    public BooleanArrayList reverseThis()
    {
        int endIndex = this.size - 1;
        for (int i = 0; i < this.size / 2; i++)
        {
            boolean tempSwapValue = this.items.get(i);
            this.items.set(i, this.items.get(endIndex - i));
            this.items.set(endIndex - i, tempSwapValue);
        }
        return this;
    }

    public MutableBooleanList asUnmodifiable()
    {
        return new UnmodifiableBooleanList(this);
    }

    public MutableBooleanList asSynchronized()
    {
        return new SynchronizedBooleanList(this);
    }

    public ImmutableBooleanList toImmutable()
    {
        if (this.size == 0)
        {
            return BooleanLists.immutable.empty();
        }
        if (this.size == 1)
        {
            return BooleanLists.immutable.with(this.items.get(0));
        }
        return BooleanLists.immutable.with(this.toArray());
    }

    public MutableBooleanList subList(int fromIndex, int toIndex)
    {
        throw new UnsupportedOperationException("subList not yet implemented!");
    }

    public BooleanArrayList toReversed()
    {
        return new BooleanArrayList(this.asReversed());
    }

    /**
     * @since 6.0
     */
    public MutableBooleanList distinct()
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
        return target;
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

    public <V> MutableList<V> collect(BooleanToObjectFunction<? extends V> function)
    {
        FastList<V> target = FastList.newList(this.size);
        for (int i = 0; i < this.size; i++)
        {
            target.add(function.valueOf(this.items.get(i)));
        }
        return target;
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

    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeInt(this.size());
        for (int i = 0; i < this.size; i++)
        {
            out.writeBoolean(this.items.get(i));
        }
    }

    public void readExternal(ObjectInput in) throws IOException
    {
        this.size = in.readInt();
        if (this.size > 0)
        {
            this.items = new BitSet();
            for (int i = 0; i < this.size; i++)
            {
                this.items.set(i, in.readBoolean());
            }
        }
    }

    private class InternalBooleanIterator implements MutableBooleanIterator
    {
        /**
         * Index of element to be returned by subsequent call to next.
         */
        private int currentIndex;
        private int lastIndex = -1;

        public boolean hasNext()
        {
            return this.currentIndex != BooleanArrayList.this.size();
        }

        public boolean next()
        {
            if (!this.hasNext())
            {
                throw new NoSuchElementException();
            }
            boolean next = BooleanArrayList.this.get(this.currentIndex);
            this.lastIndex = this.currentIndex++;
            return next;
        }

        public void remove()
        {
            if (this.lastIndex == -1)
            {
                throw new IllegalStateException();
            }
            BooleanArrayList.this.removeAtIndex(this.lastIndex);
            this.currentIndex--;
            this.lastIndex = -1;
        }
    }
}
