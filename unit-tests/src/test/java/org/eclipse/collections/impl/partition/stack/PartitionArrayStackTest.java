/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.partition.stack;

import org.eclipse.collections.api.partition.stack.PartitionImmutableStack;
import org.eclipse.collections.api.partition.stack.PartitionMutableStack;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PartitionArrayStackTest
{
    @Test
    public void add()
    {
        assertThrows(UnsupportedOperationException.class, () -> new PartitionArrayStack<Integer>().add(4));
    }

    @Test
    public void toImmutable()
    {
        PartitionMutableStack<Integer> partitionMutableStack =
                ArrayStack.newStackFromTopToBottom(1, 2, 3, 4, 5, 6).partition(Predicates.lessThan(4));

        PartitionImmutableStack<Integer> partitionImmutableStack = partitionMutableStack.toImmutable();
        assertEquals(ArrayStack.newStackFromTopToBottom(1, 2, 3), partitionImmutableStack.getSelected());
        assertEquals(ArrayStack.newStackFromTopToBottom(4, 5, 6), partitionImmutableStack.getRejected());
    }
}
