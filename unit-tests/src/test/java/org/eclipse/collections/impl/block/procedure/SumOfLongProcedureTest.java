/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumOfLongProcedureTest
{
    @Test
    public void getResult()
    {
        SumOfLongProcedure<Long> procedure = new SumOfLongProcedure<>(Long::longValue);
        procedure.value(1L);
        assertEquals(1L, procedure.getResult());

        procedure.value(2L);
        assertEquals(3L, procedure.getResult());

        procedure.value(3L);
        assertEquals(6L, procedure.getResult());
    }
}
