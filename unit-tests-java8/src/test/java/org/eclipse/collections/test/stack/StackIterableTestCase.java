/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.stack;

import java.util.EmptyStackException;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.stack.StackIterable;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.test.OrderedIterableWithDuplicatesTestCase;
import org.junit.Test;

import static org.eclipse.collections.impl.test.Verify.assertThrows;
import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.fail;

public interface StackIterableTestCase extends OrderedIterableWithDuplicatesTestCase, TransformsToStackTrait
{
    @Override
    <T> StackIterable<T> newWith(T... elements);

    @Override
    default void newMutable_sanity()
    {
        // Cannot treat an ArrayStack as a MutableCollection
    }

    @Override
    default <T> StackIterable<T> getExpectedFiltered(T... elements)
    {
        return Stacks.immutable.withReversed(elements);
    }

    @Override
    default <T> MutableList<T> newMutableForFilter(T... elements)
    {
        return Lists.mutable.with(elements);
    }

    @Override
    @Test
    default void InternalIterable_forEach()
    {
        RichIterable<Integer> integers = this.newWith(3, 3, 3, 2, 2, 1);
        MutableStack<Integer> result = Stacks.mutable.with();
        integers.forEach(Procedures.cast(result::push));
        assertEquals(this.newWith(1, 2, 2, 3, 3, 3), result);
    }

    @Override
    @Test
    default void RichIterable_tap()
    {
        Procedure<Object> noop = each -> {
        };

        RichIterable<Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);
        MutableStack<Integer> result = Stacks.mutable.with();
        iterable.tap(result::push).forEach(noop);
        assertEquals(this.newWith(1, 2, 2, 3, 3, 3), result);

        this.newWith().tap(Procedures.cast(each -> fail()));
    }

    @Override
    default void InternalIterable_forEachWith()
    {
        RichIterable<Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);
        MutableStack<Integer> result = Stacks.mutable.with();
        iterable.forEachWith((argument1, argument2) -> result.push(argument1 + argument2), 10);
        assertEquals(this.getExpectedFiltered(11, 12, 12, 13, 13, 13), result);
    }

    @Override
    @Test
    default void RichIterable_getFirst_and_getLast()
    {
        assertThrows(UnsupportedOperationException.class, this.newWith()::getLast);
    }

    @Override
    @Test
    default void RichIterable_getFirst_empty_null()
    {
        assertThrows(EmptyStackException.class, this.newWith()::getFirst);
    }

    @Override
    @Test
    default void RichIterable_getLast_empty_null()
    {
        assertThrows(UnsupportedOperationException.class, this.newWith()::getLast);
    }

    @Override
    @Test
    default void RichIterable_getLast()
    {
        assertThrows(UnsupportedOperationException.class, this.newWith(3, 3, 3, 2, 2, 1)::getLast);
    }

    @Override
    @Test
    default void OrderedIterable_getLast()
    {
        assertThrows(UnsupportedOperationException.class, this.newWith(3, 3, 3, 2, 2, 1)::getLast);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    default void OrderedIterable_getLastOptional()
    {
        ((OrderedIterable<?>) this.newWith(3, 2, 1)).getLastOptional();
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    default void OrderedIterable_getLastOptional_null_element()
    {
        ((OrderedIterable<?>) this.newWith(new Object[]{null})).getLastOptional();
    }

    @Test
    default void StackIterable_peek()
    {
        assertEquals(Integer.valueOf(5), this.newWith(5, 1, 4, 2, 3).peek());
    }

    @Test(expected = EmptyStackException.class)
    default void StackIterable_peek_throws()
    {
        this.newWith().peek();
    }

    @Test
    default void StackIterable_peekAt()
    {
        assertEquals(Integer.valueOf(5), this.newWith(5, 1, 4, 2, 3).peekAt(0));
        assertEquals(Integer.valueOf(1), this.newWith(5, 1, 4, 2, 3).peekAt(1));
        assertEquals(Integer.valueOf(4), this.newWith(5, 1, 4, 2, 3).peekAt(2));
        assertEquals(Integer.valueOf(2), this.newWith(5, 1, 4, 2, 3).peekAt(3));
        assertEquals(Integer.valueOf(3), this.newWith(5, 1, 4, 2, 3).peekAt(4));
    }

    @Test
    default void StackIterable_peekAt_throws()
    {
        StackIterable<Integer> stackIterable = this.newWith(5, 1, 4, 2, 3);
        assertThrows(IllegalArgumentException.class, (Runnable) () -> stackIterable.peekAt(-1));
        assertThrows(IllegalArgumentException.class, (Runnable) () -> stackIterable.peekAt(5));
    }
}
