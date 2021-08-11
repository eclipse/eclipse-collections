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

import org.eclipse.collections.impl.block.factory.StringFunctions;
import org.junit.Assert;
import org.junit.Test;

public class SumOfCharProcedureTest
{
    @Test
    public void resultAfterInitialization()
    {
        SumOfCharProcedure<String> sumOfCharProcedure = new SumOfCharProcedure<>(StringFunctions.toFirstChar());

        Assert.assertEquals(Character.MIN_VALUE, sumOfCharProcedure.getResult());
    }

    @Test
    public void resultAfterInvocation()
    {
        SumOfCharProcedure<String> sumOfCharProcedure = new SumOfCharProcedure<>(StringFunctions.toFirstChar());

        sumOfCharProcedure.value("01234");
        Assert.assertEquals('0', sumOfCharProcedure.getResult());

        sumOfCharProcedure.value("ABCDE");
        Assert.assertEquals('q', sumOfCharProcedure.getResult());
    }
}