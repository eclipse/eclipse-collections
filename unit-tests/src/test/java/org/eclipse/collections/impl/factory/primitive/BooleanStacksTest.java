/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.primitive;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.stack.primitive.ImmutableBooleanStackFactory;
import org.eclipse.collections.api.stack.primitive.ImmutableBooleanStack;
import org.eclipse.collections.api.stack.primitive.MutableBooleanStack;
import org.eclipse.collections.impl.stack.mutable.primitive.BooleanArrayStack;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanStacksTest
{
    @Test
    public void immutables()
    {
        ImmutableBooleanStackFactory stackFactory = BooleanStacks.immutable;
        assertEquals(BooleanArrayStack.newStackWith(), stackFactory.of());
        Verify.assertInstanceOf(ImmutableBooleanStack.class, stackFactory.of());
        assertEquals(BooleanArrayStack.newStackWith(true), stackFactory.of(true));
        Verify.assertInstanceOf(ImmutableBooleanStack.class, stackFactory.of(true));
        assertEquals(BooleanArrayStack.newStackWith(false), stackFactory.of(false));
        Verify.assertInstanceOf(ImmutableBooleanStack.class, stackFactory.of(false));
        assertEquals(BooleanArrayStack.newStackWith(false, true), stackFactory.of(false, true));
        Verify.assertInstanceOf(ImmutableBooleanStack.class, stackFactory.of(false, true));
        assertEquals(BooleanArrayStack.newStackWith(true, false), stackFactory.of(true, false));
        Verify.assertInstanceOf(ImmutableBooleanStack.class, stackFactory.of(true, false));
        assertEquals(BooleanArrayStack.newStackWith(false, true, false), stackFactory.of(false, true, false));
        Verify.assertInstanceOf(ImmutableBooleanStack.class, stackFactory.of(false, true, false));
        assertEquals(BooleanArrayStack.newStackWith(true, false, true), stackFactory.of(true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanStack.class, stackFactory.of(true, false, true));
        assertEquals(BooleanArrayStack.newStackWith(false, true, false, false), stackFactory.of(false, true, false, false));
        Verify.assertInstanceOf(ImmutableBooleanStack.class, stackFactory.of(false, true, false, false));
        assertEquals(BooleanArrayStack.newStackWith(true, false, true, true), stackFactory.of(true, false, true, true));
        Verify.assertInstanceOf(ImmutableBooleanStack.class, stackFactory.of(true, false, true, true));
    }

    @Test
    public void empty()
    {
        assertTrue(BooleanStacks.immutable.of().isEmpty());
        assertTrue(BooleanStacks.mutable.of().isEmpty());
    }

    @Test
    public void newStackWith_immutable()
    {
        ImmutableBooleanStack stack = BooleanStacks.immutable.of();
        assertEquals(stack, BooleanStacks.immutable.of(stack.toArray()));
        assertEquals(stack = stack.push(true), BooleanStacks.immutable.of(true));
        assertEquals(stack = stack.push(false), BooleanStacks.immutable.of(true, false));
        assertEquals(stack = stack.push(true), BooleanStacks.immutable.of(true, false, true));
        assertEquals(stack = stack.push(true), BooleanStacks.immutable.of(true, false, true, true));
        assertEquals(stack = stack.push(false), BooleanStacks.immutable.of(true, false, true, true, false));
    }

    @Test
    public void newStackWith_mutable()
    {
        MutableBooleanStack stack = BooleanStacks.mutable.of();
        assertEquals(stack, BooleanStacks.mutable.of(stack.toArray()));
        stack.push(true);
        assertEquals(stack, BooleanStacks.mutable.of(true));
        stack.push(false);
        assertEquals(stack, BooleanStacks.mutable.of(true, false));
        stack.push(true);
        assertEquals(stack, BooleanStacks.mutable.of(true, false, true));
        stack.push(true);
        assertEquals(stack, BooleanStacks.mutable.of(true, false, true, true));
        stack.push(false);
        assertEquals(stack, BooleanStacks.mutable.of(true, false, true, true, false));
    }

    @SuppressWarnings("RedundantArrayCreation")
    @Test
    public void newStackWithArray_immutable()
    {
        ImmutableBooleanStack stack = BooleanStacks.immutable.of();
        assertEquals(stack = stack.push(true), BooleanStacks.immutable.of(new boolean[]{true}));
        assertEquals(stack = stack.push(false), BooleanStacks.immutable.of(new boolean[]{true, false}));
        assertEquals(stack = stack.push(true), BooleanStacks.immutable.of(new boolean[]{true, false, true}));
        assertEquals(stack = stack.push(true), BooleanStacks.immutable.of(new boolean[]{true, false, true, true}));
        assertEquals(stack = stack.push(false), BooleanStacks.immutable.of(new boolean[]{true, false, true, true, false}));
    }

    @SuppressWarnings("RedundantArrayCreation")
    @Test
    public void newStackWithArray_mutable()
    {
        MutableBooleanStack stack = BooleanStacks.mutable.of();
        stack.push(true);
        assertEquals(stack, BooleanStacks.mutable.of(new boolean[]{true}));
        stack.push(false);
        assertEquals(stack, BooleanStacks.mutable.of(new boolean[]{true, false}));
        stack.push(true);
        assertEquals(stack, BooleanStacks.mutable.of(new boolean[]{true, false, true}));
        stack.push(true);
        assertEquals(stack, BooleanStacks.mutable.of(new boolean[]{true, false, true, true}));
        stack.push(false);
        assertEquals(stack, BooleanStacks.mutable.of(new boolean[]{true, false, true, true, false}));
    }

    @Test
    public void ofAllBooleanIterable()
    {
        assertEquals(new BooleanArrayStack(), BooleanStacks.immutable.ofAll(BooleanLists.mutable.empty()));
        assertEquals(BooleanArrayStack.newStackWith(true), BooleanStacks.immutable.ofAll(BooleanLists.mutable.with(true)));
        assertEquals(BooleanArrayStack.newStackWith(true, false), BooleanStacks.immutable.ofAll(BooleanLists.mutable.with(true, false)));
        assertEquals(BooleanArrayStack.newStackWith(true, false, false, true), BooleanStacks.immutable.ofAll(BooleanLists.mutable.with(true, false, false, true)));

        assertEquals(new BooleanArrayStack(), BooleanStacks.mutable.ofAll(BooleanLists.mutable.empty()));
        assertEquals(BooleanArrayStack.newStackWith(true), BooleanStacks.mutable.ofAll(BooleanLists.mutable.with(true)));
        assertEquals(BooleanArrayStack.newStackWith(true, false), BooleanStacks.mutable.ofAll(BooleanLists.mutable.with(true, false)));
        assertEquals(BooleanArrayStack.newStackWith(true, false, false, true), BooleanStacks.mutable.ofAll(BooleanLists.mutable.with(true, false, false, true)));
    }

    @Test
    public void ofAllIterable()
    {
        assertEquals(new BooleanArrayStack(), BooleanStacks.immutable.ofAll(Lists.mutable.empty()));
        assertEquals(BooleanArrayStack.newStackWith(true), BooleanStacks.immutable.ofAll(Lists.mutable.with(true)));
        assertEquals(BooleanArrayStack.newStackWith(true, false), BooleanStacks.immutable.ofAll(Lists.mutable.with(true, false)));
        assertEquals(BooleanArrayStack.newStackWith(true, false, false, true), BooleanStacks.immutable.ofAll(Lists.mutable.with(true, false, false, true)));

        assertEquals(new BooleanArrayStack(), BooleanStacks.mutable.ofAll(Lists.mutable.empty()));
        assertEquals(BooleanArrayStack.newStackWith(true), BooleanStacks.mutable.ofAll(Lists.mutable.with(true)));
        assertEquals(BooleanArrayStack.newStackWith(true, false), BooleanStacks.mutable.ofAll(Lists.mutable.with(true, false)));
        assertEquals(BooleanArrayStack.newStackWith(true, false, false, true), BooleanStacks.mutable.ofAll(Lists.mutable.with(true, false, false, true)));
    }

    @Test
    public void ofAllReversed()
    {
        assertEquals(new BooleanArrayStack(), BooleanStacks.immutable.ofAllReversed(BooleanLists.mutable.empty()));
        assertEquals(BooleanArrayStack.newStackFromTopToBottom(true), BooleanStacks.immutable.ofAllReversed(BooleanLists.mutable.with(true)));
        assertEquals(BooleanArrayStack.newStackFromTopToBottom(true, false), BooleanStacks.immutable.ofAllReversed(BooleanLists.mutable.with(true, false)));
        assertEquals(BooleanArrayStack.newStackFromTopToBottom(true, false, false, true), BooleanStacks.immutable.ofAllReversed(BooleanLists.mutable.with(true, false, false, true)));

        assertEquals(new BooleanArrayStack(), BooleanStacks.mutable.ofAllReversed(BooleanLists.mutable.empty()));
        assertEquals(BooleanArrayStack.newStackFromTopToBottom(true), BooleanStacks.mutable.ofAllReversed(BooleanLists.mutable.with(true)));
        assertEquals(BooleanArrayStack.newStackFromTopToBottom(true, false), BooleanStacks.mutable.ofAllReversed(BooleanLists.mutable.with(true, false)));
        assertEquals(BooleanArrayStack.newStackFromTopToBottom(true, false, false, true), BooleanStacks.mutable.ofAllReversed(BooleanLists.mutable.with(true, false, false, true)));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(BooleanStacks.class);
    }
}
