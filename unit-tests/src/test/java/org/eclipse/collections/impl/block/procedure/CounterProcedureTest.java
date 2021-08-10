/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.junit.Assert;
import org.junit.Test;

public class CounterProcedureTest
{
    @Test
    public void countAfterInitialization()
    {
        CounterProcedure<Object> counterProcedure = new CounterProcedure<>(DoNothingProcedure.DO_NOTHING);

        Assert.assertEquals(0, counterProcedure.getCount());
    }

    @Test
    public void countAfterInvocation()
    {
        CounterProcedure<Object> counterProcedure = new CounterProcedure<>(DoNothingProcedure.DO_NOTHING);
        counterProcedure.value(new Object());

        Assert.assertEquals(1, counterProcedure.getCount());
    }
}
