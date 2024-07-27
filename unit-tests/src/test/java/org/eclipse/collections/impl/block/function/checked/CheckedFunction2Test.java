/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function.checked;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckedFunction2Test
{
    @Test
    public void value()
    {
        assertEquals((Integer) 50, new CheckedFunction2<Integer, Integer, Integer>()
        {
            @Override
            public Integer safeValue(Integer argument1, Integer argument2)
            {
                return argument1 * argument2;
            }
        }.value(10, 5));
    }

    @Test
    public void exceptionHandling()
    {
        assertThrows(RuntimeException.class, () -> new CheckedFunction2<Integer, Integer, Integer>()
        {
            @Override
            public Integer safeValue(Integer argument1, Integer argument2) throws Exception
            {
                throw new Exception("Something bad happened");
            }
        }.value(10, 20));
    }
}
