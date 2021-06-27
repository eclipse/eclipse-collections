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

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.utility.StringIterate;
import org.junit.Assert;
import org.junit.Test;

public class CounterProcedureTest
{
    @Test
    public void getCount()
    {
        MockProcedure mockProcedure = new MockProcedure();
        CounterProcedure<Integer> procedure = new CounterProcedure<>(mockProcedure);
        Assert.assertNull(mockProcedure.getValue());
        Assert.assertEquals(0, procedure.getCount());
        procedure.value(1);
        Assert.assertEquals(1, (int) mockProcedure.getValue());
        Assert.assertEquals(1, procedure.getCount());

        procedure.value(2);
        Assert.assertEquals(2, (int) mockProcedure.getValue());
        Assert.assertEquals(2, procedure.getCount());
    }

    @Test
    public void toStringTest()
    {
        MockProcedure mockProcedure = new MockProcedure();
        CounterProcedure<Integer> procedure = new CounterProcedure<>(mockProcedure);
        String s = procedure.toString();
        Assert.assertNotNull(s);
        Assert.assertTrue(StringIterate.notEmptyOrWhitespace(s));
    }

    private static class MockProcedure implements Procedure<Integer>
    {
        private Integer value;

        @Override
        public void value(Integer each)
        {
            this.value = each;
        }

        public Integer getValue()
        {
            return this.value;
        }
    }
}
