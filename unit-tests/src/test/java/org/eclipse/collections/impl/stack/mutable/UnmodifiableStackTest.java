/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.mutable;

import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.stack.StackIterable;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.StringPredicates;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.stack.StackIterableTestCase;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnmodifiableStackTest extends StackIterableTestCase
{
    private MutableStack<Integer> mutableStack;
    private MutableStack<Integer> unmodifiableStack;
    private MutableStack<String> unmodifiableStackString;

    @Before
    public void setUp()
    {
        this.mutableStack = ArrayStack.newStackFromTopToBottom(1, 2, 3);
        this.unmodifiableStack = new UnmodifiableStack<>(this.mutableStack);
        this.unmodifiableStackString = new UnmodifiableStack<>(ArrayStack.newStackFromTopToBottom("1", "2", "3"));
    }

    @Override
    protected <T> MutableStack<T> newStackWith(T... elements)
    {
        return ArrayStack.newStackWith(elements).asUnmodifiable();
    }

    @Override
    protected <T> MutableStack<T> newStackFromTopToBottom(T... elements)
    {
        return ArrayStack.newStackFromTopToBottom(elements).asUnmodifiable();
    }

    @Override
    protected <T> StackIterable<T> newStackFromTopToBottom(Iterable<T> elements)
    {
        return ArrayStack.newStackFromTopToBottom(elements).asUnmodifiable();
    }

    @Override
    protected <T> StackIterable<T> newStack(Iterable<T> elements)
    {
        return ArrayStack.newStack(elements).asUnmodifiable();
    }

    @Override
    @Test
    public void iterator()
    {
        super.iterator();
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newStackWith(1, 2, 3).iterator().remove());
    }

    @Test
    public void testNullStack()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> UnmodifiableStack.of(null));
    }

    @Test
    public void testPop()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newStackFromTopToBottom(1, 2, 3).pop());

        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newStackFromTopToBottom(1, 2).pop(3));

        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newStackFromTopToBottom(1, 2, 3).pop(3));

        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newStackFromTopToBottom(1, 2, 3).pop(3, FastList.newList()));

        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newStackFromTopToBottom(1, 2, 3).pop(3, ArrayStack.newStack()));
    }

    @Test
    public void testPush()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newStackFromTopToBottom(1, 2, 3).push(4));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testClear()
    {
        this.newStackFromTopToBottom(1, 2, 3).clear();
    }

    @Test
    public void testSelect()
    {
        Assert.assertEquals(ArrayStack.newStackFromTopToBottom(2, 3), this.unmodifiableStack.select(Predicates.greaterThan(1)));
        Verify.assertSize(3, this.unmodifiableStackString.select(ignored -> true, FastList.newList()));
    }

    @Test
    public void testSelectWith()
    {
        Verify.assertSize(
                1,
                this.unmodifiableStackString.selectWith(
                        Object::equals,
                        "2",
                        FastList.newList()));
    }

    @Test
    public void testReject()
    {
        Assert.assertEquals(ArrayStack.newStackFromTopToBottom("2", "3"), this.unmodifiableStackString.reject(StringPredicates.contains("1")));
        Assert.assertEquals(FastList.newListWith("2", "3"),
                this.unmodifiableStackString.reject(StringPredicates.contains("1"), FastList.newList()));
    }

    @Test
    public void testRejectWith()
    {
        Verify.assertSize(
                3,
                this.unmodifiableStackString.rejectWith(
                        Object::equals,
                        3,
                        FastList.newList()));
    }

    @Test
    public void testCollect()
    {
        Assert.assertEquals(this.mutableStack, this.unmodifiableStackString.collect(Integer::valueOf));
    }

    @Test
    public void testSize()
    {
        Assert.assertEquals(this.mutableStack.size(), this.unmodifiableStack.size());
    }

    @Test
    public void testIsEmpty()
    {
        Assert.assertEquals(this.mutableStack.isEmpty(), this.unmodifiableStack.isEmpty());
    }

    @Test
    public void testGetFirst()
    {
        Assert.assertEquals(this.mutableStack.getFirst(), this.unmodifiableStack.getFirst());
    }

    @Test
    public void testCount()
    {
        Assert.assertEquals(this.mutableStack.count(ignored1 -> true),
                this.unmodifiableStack.count(ignored -> true));
    }

    @Test
    public void asSynchronized()
    {
        Verify.assertInstanceOf(SynchronizedStack.class, this.unmodifiableStack.asSynchronized());
    }

    @Test
    public void asUnmodifiable()
    {
        Verify.assertInstanceOf(UnmodifiableStack.class, this.unmodifiableStack.asUnmodifiable());
    }
}
