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

import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.junit.Assert;
import org.junit.Test;

public class SumOfShortProcedureTest
{
    @Test
    public void resultAfterInitialization()
    {
        SumOfShortProcedure<Integer> sumOfShortProcedure =
                new SumOfShortProcedure<>(PrimitiveFunctions.unboxIntegerToShort());

        Assert.assertEquals(0, sumOfShortProcedure.getResult());
    }

    @Test
    public void resultAfterInvocation()
    {
        SumOfShortProcedure<Integer> sumOfShortProcedure =
                new SumOfShortProcedure<>(PrimitiveFunctions.unboxIntegerToShort());

        sumOfShortProcedure.value(1);
        Assert.assertEquals(1, sumOfShortProcedure.getResult());

        sumOfShortProcedure.value(2);
        Assert.assertEquals(3, sumOfShortProcedure.getResult());
    }
}
