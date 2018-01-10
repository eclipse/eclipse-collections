/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.mutable.primitive;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.EmptyStackException;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.block.function.primitive.BooleanToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectBooleanIntToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectBooleanToObjectFunction;
import org.eclipse.collections.api.block.predicate.primitive.BooleanPredicate;
import org.eclipse.collections.api.block.procedure.primitive.BooleanIntProcedure;
import org.eclipse.collections.api.block.procedure.primitive.BooleanProcedure;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.list.primitive.BooleanList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.stack.primitive.BooleanStack;
import org.eclipse.collections.api.stack.primitive.ImmutableBooleanStack;
import org.eclipse.collections.api.stack.primitive.MutableBooleanStack;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.factory.primitive.BooleanStacks;
import org.eclipse.collections.impl.iterator.UnmodifiableBooleanIterator;
import org.eclipse.collections.impl.lazy.primitive.LazyBooleanIterableAdapter;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;

/**
 * BooleanArrayStack is similar to {@link ArrayStack}, and is memory-optimized for boolean primitives.
 */
public final class BooleanArrayStack implements MutableBooleanStack, Externalizable
{
    private static final long serialVersionUID = 1L;

    private transient BooleanArrayList delegate;

    public BooleanArrayStack()
    {
        this.delegate = new BooleanArrayList();
    }

    private BooleanArrayStack(int size)
    {
        this.delegate = new BooleanArrayList(size);
    }

    private BooleanArrayStack(boolean... items)
    {
        this.delegate = new BooleanArrayList(items);
    }

    public static BooleanArrayStack newStackFromTopToBottom(boolean... items)
    {
        BooleanArrayStack stack = new BooleanArrayStack(items.length);
        for (int i = items.length - 1; i >= 0; i--)
        {
            stack.push(items[i]);
        }
        return stack;
    }

    public static BooleanArrayStack newStackWith(boolean... items)
    {
        return new BooleanArrayStack(items);
    }

    public static BooleanArrayStack newStack(BooleanIterable items)
    {
        BooleanArrayStack stack = new BooleanArrayStack(items.size());
        stack.delegate = BooleanArrayList.newList(items);
        return stack;
    }

    public static BooleanArrayStack newStackFromTopToBottom(BooleanIterable items)
    {
        BooleanArrayStack stack = new BooleanArrayStack(items.size());
        stack.delegate = BooleanArrayList.newList(items).reverseThis();
        return stack;
    }

    @Override
    public void push(boolean item)
    {
        this.delegate.add(item);
    }

    @Override
    public boolean pop()
    {
        this.checkEmptyStack();
        return this.delegate.removeAtIndex(this.delegate.size() - 1);
    }

    private void checkEmptyStack()
    {
        if (this.delegate.isEmpty())
        {
            throw new EmptyStackException();
        }
    }

    @Override
    public BooleanList pop(int count)
    {
        this.checkPositiveValueForCount(count);
        this.checkSizeLessThanCount(count);
        if (count == 0)
        {
            return new BooleanArrayList(0);
        }
        MutableBooleanList subList = new BooleanArrayList(count);
        while (count > 0)
        {
            subList.add(this.pop());
            count--;
        }
        return subList;
    }

    private void checkPositiveValueForCount(int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be positive but was " + count);
        }
    }

    private void checkSizeLessThanCount(int count)
    {
        if (this.delegate.size() < count)
        {
            throw new IllegalArgumentException("Count must be less than size: Count = " + count + " Size = " + this.delegate.size());
        }
    }

    @Override
    public MutableBooleanStack select(BooleanPredicate predicate)
    {
        return BooleanArrayStack.newStackFromTopToBottom(this.delegate.asReversed().select(predicate));
    }

    @Override
    public MutableBooleanStack reject(BooleanPredicate predicate)
    {
        return BooleanArrayStack.newStackFromTopToBottom(this.delegate.asReversed().reject(predicate));
    }

    @Override
    public MutableBooleanStack asUnmodifiable()
    {
        return new UnmodifiableBooleanStack(this);
    }

    @Override
    public MutableBooleanStack asSynchronized()
    {
        return new SynchronizedBooleanStack(this);
    }

    @Override
    public ImmutableBooleanStack toImmutable()
    {
        return BooleanStacks.immutable.withAll(this.delegate);
    }

    @Override
    public boolean peek()
    {
        this.checkEmptyStack();
        return this.delegate.getLast();
    }

    @Override
    public BooleanList peek(int count)
    {
        this.checkPositiveValueForCount(count);
        this.checkSizeLessThanCount(count);
        if (count == 0)
        {
            return new BooleanArrayList(0);
        }
        MutableBooleanList subList = new BooleanArrayList(count);
        int index = this.delegate.size() - 1;
        for (int i = 0; i < count; i++)
        {
            subList.add(this.delegate.get(index - i));
        }
        return subList;
    }

    @Override
    public boolean peekAt(int index)
    {
        this.rangeCheck(index);
        return this.delegate.get(this.delegate.size() - 1 - index);
    }

    private void rangeCheck(int index)
    {
        if (index < 0 || index > this.delegate.size() - 1)
        {
            throw new IllegalArgumentException("Index " + index + " out of range.Should be between 0 and " + (this.delegate.size() - 1));
        }
    }

    @Override
    public MutableBooleanIterator booleanIterator()
    {
        return new UnmodifiableBooleanIterator(this.delegate.asReversed().booleanIterator());
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
        this.delegate.asReversed().forEach(procedure);
    }

    @Override
    public int size()
    {
        return this.delegate.size();
    }

    @Override
    public boolean isEmpty()
    {
        return this.delegate.isEmpty();
    }

    @Override
    public boolean notEmpty()
    {
        return this.delegate.notEmpty();
    }

    @Override
    public int count(BooleanPredicate predicate)
    {
        return this.delegate.asReversed().count(predicate);
    }

    @Override
    public boolean anySatisfy(BooleanPredicate predicate)
    {
        return this.delegate.asReversed().anySatisfy(predicate);
    }

    @Override
    public boolean allSatisfy(BooleanPredicate predicate)
    {
        return this.delegate.asReversed().allSatisfy(predicate);
    }

    @Override
    public boolean noneSatisfy(BooleanPredicate predicate)
    {
        return this.delegate.asReversed().noneSatisfy(predicate);
    }

    @Override
    public boolean detectIfNone(BooleanPredicate predicate, boolean ifNone)
    {
        return this.delegate.asReversed().detectIfNone(predicate, ifNone);
    }

    @Override
    public <V> MutableStack<V> collect(BooleanToObjectFunction<? extends V> function)
    {
        return ArrayStack.newStackFromTopToBottom(this.delegate.asReversed().collect(function));
    }

    @Override
    public <V> V injectInto(V injectedValue, ObjectBooleanToObjectFunction<? super V, ? extends V> function)
    {
        return this.delegate.asReversed().injectInto(injectedValue, function);
    }

    @Override
    public RichIterable<BooleanIterable> chunk(int size)
    {
        return this.delegate.asReversed().chunk(size);
    }

    @Override
    public boolean[] toArray()
    {
        return this.delegate.asReversed().toArray();
    }

    @Override
    public boolean contains(boolean value)
    {
        return this.delegate.asReversed().contains(value);
    }

    @Override
    public boolean containsAll(boolean... source)
    {
        return this.delegate.asReversed().containsAll(source);
    }

    @Override
    public boolean containsAll(BooleanIterable source)
    {
        return this.delegate.asReversed().containsAll(source);
    }

    @Override
    public void clear()
    {
        this.delegate.clear();
    }

    @Override
    public boolean equals(Object otherStack)
    {
        if (otherStack == this)
        {
            return true;
        }
        if (!(otherStack instanceof BooleanStack))
        {
            return false;
        }
        BooleanStack stack = (BooleanStack) otherStack;
        if (this.size() != stack.size())
        {
            return false;
        }
        for (int i = 0; i < this.size(); i++)
        {
            if (this.peekAt(i) != stack.peekAt(i))
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
        for (int i = this.size() - 1; i >= 0; i--)
        {
            boolean item = this.delegate.get(i);
            hashCode = 31 * hashCode + (item ? 1231 : 1237);
        }
        return hashCode;
    }

    @Override
    public String toString()
    {
        return this.delegate.asReversed().toString();
    }

    @Override
    public String makeString()
    {
        return this.delegate.asReversed().makeString();
    }

    @Override
    public String makeString(String separator)
    {
        return this.delegate.asReversed().makeString(separator);
    }

    @Override
    public String makeString(String start, String separator, String end)
    {
        return this.delegate.asReversed().makeString(start, separator, end);
    }

    @Override
    public void appendString(Appendable appendable)
    {
        this.delegate.asReversed().appendString(appendable);
    }

    @Override
    public void appendString(Appendable appendable, String separator)
    {
        this.delegate.asReversed().appendString(appendable, separator);
    }

    @Override
    public void appendString(
            Appendable appendable,
            String start,
            String separator,
            String end)
    {
        this.delegate.asReversed().appendString(appendable, start, separator, end);
    }

    @Override
    public boolean getFirst()
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".getFirst() not implemented yet");
    }

    @Override
    public int indexOf(boolean value)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".indexOf() not implemented yet");
    }

    @Override
    public <T> T injectIntoWithIndex(T injectedValue, ObjectBooleanIntToObjectFunction<? super T, ? extends T> function)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".injectIntoWithIndex() not implemented yet");
    }

    @Override
    public void forEachWithIndex(BooleanIntProcedure procedure)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".forEachWithIndex() not implemented yet");
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeInt(this.size());
        BooleanIterator iterator = this.delegate.asReversed().booleanIterator();
        while (iterator.hasNext())
        {
            boolean each = iterator.next();
            out.writeBoolean(each);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException
    {
        int size = in.readInt();
        boolean[] array = new boolean[size];
        for (int i = size - 1; i >= 0; i--)
        {
            array[i] = in.readBoolean();
        }
        this.delegate = BooleanArrayList.newListWith(array);
    }
}
