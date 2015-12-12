/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class MultiplyFunctionTest
{
    @Test
    public void integerBlock()
    {
        Assert.assertEquals(Integer.valueOf(20), MultiplyFunction.INTEGER.value(2, 10));
    }

    @Test
    public void doubleBlock()
    {
        Assert.assertEquals(new Double(20), MultiplyFunction.DOUBLE.value(2.0, 10.0));
    }

    @Test
    public void longBlock()
    {
        Assert.assertEquals(Long.valueOf(20), MultiplyFunction.LONG.value(2L, 10L));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(MultiplyFunction.class);
    }
}
