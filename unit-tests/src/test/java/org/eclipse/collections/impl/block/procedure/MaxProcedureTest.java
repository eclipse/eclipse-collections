/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import java.util.Optional;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class MaxProcedureTest
{
    @Test
    public void getResultOptional()
    {
        MaxProcedure<Integer> procedure = new MaxProcedure<>();
        assertFalse(procedure.getResultOptional().isPresent());
        procedure.value(2);
        Optional<Integer> resultOptional = procedure.getResultOptional();
        assertTrue(resultOptional.isPresent());
        assertEquals((Integer) 2, resultOptional.get());

        procedure.value(1);
        Optional<Integer> resultOptional2 = procedure.getResultOptional();
        assertTrue(resultOptional2.isPresent());
        assertEquals((Integer) 2, resultOptional2.get());
    }

    @Test
    public void value()
    {
        MaxProcedure<Integer> procedure = new MaxProcedure<>();
        Integer first = new Integer(1);
        procedure.value(first);
        assertSame(first, procedure.getResult());
        Integer second = new Integer(1);
        procedure.value(second);
        assertSame(first, procedure.getResult());
        Integer third = new Integer(3);
        procedure.value(third);
        assertSame(third, procedure.getResult());
        Integer fourth = new Integer(0);
        procedure.value(fourth);
        assertSame(third, procedure.getResult());
    }
}
