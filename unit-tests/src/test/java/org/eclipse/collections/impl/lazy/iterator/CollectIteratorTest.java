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
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class CollectIteratorTest
{
    @Test
    public void iterator()
    {
        Iterator<String> iterator = new CollectIterator<>(iList(Boolean.TRUE), String::valueOf);
        assertTrue(iterator.hasNext());
        assertEquals("true", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iteratorWithFunctionName()
    {
        Iterator<String> iterator = new CollectIterator<>(iList(Boolean.TRUE), String::valueOf);
        assertTrue(iterator.hasNext());
        assertEquals("true", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iteratorWithFunctionNameAndIterator()
    {
        Iterator<String> iterator = new CollectIterator<>(iList(Boolean.TRUE).iterator(), String::valueOf);
        assertTrue(iterator.hasNext());
        assertEquals("true", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void noSuchElementException()
    {
        assertThrows(NoSuchElementException.class, () -> new CollectIterator<>(Lists.mutable.<Boolean>of(), String::valueOf).next());
    }

    @Test
    public void remove()
    {
        assertThrows(UnsupportedOperationException.class, () -> new CollectIterator<>(Lists.mutable.<Boolean>of(), String::valueOf).remove());
    }
}
