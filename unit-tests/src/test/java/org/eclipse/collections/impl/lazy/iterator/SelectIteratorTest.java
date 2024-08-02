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
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectIteratorTest
{
    @Test
    public void iterator()
    {
        MutableList<Boolean> list = FastList.newListWith(
                Boolean.TRUE,
                Boolean.FALSE,
                Boolean.TRUE,
                Boolean.TRUE,
                Boolean.FALSE,
                null,
                null,
                Boolean.FALSE,
                Boolean.TRUE,
                null);
        this.assertElements(new SelectIterator<>(list.iterator(), Boolean.TRUE::equals));
        this.assertElements(new SelectIterator<>(list, Boolean.TRUE::equals));
    }

    private void assertElements(Iterator<Boolean> newIterator)
    {
        for (int i = 0; i < 4; i++)
        {
            assertTrue(newIterator.hasNext());
            assertEquals(Boolean.TRUE, newIterator.next());
        }
        assertFalse(newIterator.hasNext());
    }

    @Test
    public void noSuchElementException()
    {
        assertThrows(NoSuchElementException.class, () -> new SelectIterator<>(Lists.fixedSize.of(), ignored -> true).next());
    }

    @Test
    public void remove()
    {
        assertThrows(UnsupportedOperationException.class, () -> new SelectIterator<>(Lists.fixedSize.of(), ignored -> true).remove());
    }
}
