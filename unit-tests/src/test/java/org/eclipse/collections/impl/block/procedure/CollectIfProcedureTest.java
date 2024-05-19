/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectIfProcedureTest
{
    private static final int THE_ANSWER = 42;

    @Test
    public void constructorWithSize()
    {
        CollectIfProcedure<Integer, String> underTestTrue = new CollectIfProcedure<>(10, String::valueOf, ignored -> true);
        CollectIfProcedure<Integer, String> underTestFalse = new CollectIfProcedure<>(10, String::valueOf, ignored -> false);
        underTestTrue.value(THE_ANSWER);
        underTestFalse.value(THE_ANSWER);
        assertTrue(underTestTrue.getCollection().contains("42"));
        assertFalse(underTestFalse.getCollection().contains("42"));
    }
}
