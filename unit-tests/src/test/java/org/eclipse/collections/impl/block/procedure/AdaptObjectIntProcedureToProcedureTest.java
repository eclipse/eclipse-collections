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

import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.junit.Assert;
import org.junit.Test;

public class AdaptObjectIntProcedureToProcedureTest
{
    @Test
    public void value()
    {
        MockObjectIntProcedure mockObjectIntProcedure = new MockObjectIntProcedure();
        AdaptObjectIntProcedureToProcedure<Integer> procedure =
                new AdaptObjectIntProcedureToProcedure<>(mockObjectIntProcedure);
        procedure.value(1);
        Assert.assertEquals(1, mockObjectIntProcedure.getEachValue());
        Assert.assertEquals(0, mockObjectIntProcedure.getParameterValue());

        procedure.value(2);
        Assert.assertEquals(2, mockObjectIntProcedure.getEachValue());
        Assert.assertEquals(1, mockObjectIntProcedure.getParameterValue());

        procedure.value(3);
        Assert.assertEquals(3, mockObjectIntProcedure.getEachValue());
        Assert.assertEquals(2, mockObjectIntProcedure.getParameterValue());
    }

    private static class MockObjectIntProcedure
            implements ObjectIntProcedure<Integer>
    {
        private int eachValue;
        private int parameterValue;

        @Override
        public void value(Integer each, int parameter)
        {
            this.eachValue = each;
            this.parameterValue = parameter;
        }

        public int getEachValue()
        {
            return this.eachValue;
        }

        public int getParameterValue()
        {
            return this.parameterValue;
        }
    }
}
