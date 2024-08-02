/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.block.factory.Functions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlatCollectIteratorTest
{
    @Test
    public void nextIfDoesntHaveAnything()
    {
        assertThrows(NoSuchElementException.class, () -> new FlatCollectIterator<>(Lists.immutable.of(), object -> null).next());
    }

    @Test
    public void removeIsUnsupported()
    {
        assertThrows(UnsupportedOperationException.class, () -> new FlatCollectIterator<>(Lists.immutable.of().iterator(), object -> null).remove());
    }

    @Test
    public void nextAfterEmptyIterable()
    {
        Object expected = new Object();
        Iterator<Object> flattenIterator = new FlatCollectIterator<>(
                Lists.fixedSize.of(
                        Lists.fixedSize.of(),
                        Lists.fixedSize.of(expected)),
                Functions.getPassThru());
        assertSame(expected, flattenIterator.next());
    }
}
