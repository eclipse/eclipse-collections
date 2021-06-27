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

import org.eclipse.collections.impl.block.factory.Comparators;
import org.junit.Assert;
import org.junit.Test;

public class MinComparatorProcedureTest
{
    @Test
    public void value()
    {
        MinComparatorProcedure<Integer> procedure = new MinComparatorProcedure<>(Comparators.naturalOrder());
        Integer first = new Integer(1);
        Integer second = new Integer(1);
        Integer third = new Integer(3);
        Integer fourth = new Integer(0);

        procedure.value(first);
        Assert.assertSame(first, procedure.getResult());
        procedure.value(second);
        Assert.assertSame(first, procedure.getResult());
        procedure.value(third);
        Assert.assertSame(first, procedure.getResult());
        procedure.value(fourth);
        Assert.assertSame(fourth, procedure.getResult());
    }
}
