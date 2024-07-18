/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.iterator;

import java.util.NoSuchElementException;

import org.eclipse.collections.api.iterator.BooleanIterator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class SingletonBooleanIteratorTest
{
    @Test
    public void hasNext()
    {
        BooleanIterator iterator = new SingletonBooleanIterator(false);
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void next()
    {
        SingletonBooleanIterator iterator = new SingletonBooleanIterator(false);
        assertTrue(iterator.hasNext());
        assertEquals(false, iterator.next());
        assertFalse(iterator.hasNext());
        try
        {
            iterator.next();
            fail("NoSuchElementException should have been thrown");
        }
        catch (NoSuchElementException e)
        {
            //Expected
        }
    }
}
