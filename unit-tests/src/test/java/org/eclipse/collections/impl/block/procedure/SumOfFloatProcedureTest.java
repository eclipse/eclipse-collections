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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumOfFloatProcedureTest
{
    @Test
    public void getResult()
    {
        SumOfFloatProcedure<Float> procedure = new SumOfFloatProcedure<>(Float::floatValue);
        procedure.value(1.0f);
        assertEquals(1.0, procedure.getResult(), 0.0);
        assertEquals(0.0, procedure.getCompensation(), 0.0);

        procedure.value(2.5f);
        assertEquals(3.5, procedure.getResult(), 0.0);
        assertEquals(0.0, procedure.getCompensation(), 0.0);

        procedure.value(3.5f);
        assertEquals(7.0, procedure.getResult(), 0.0);
        assertEquals(0.0, procedure.getCompensation(), 0.0);
    }
}
