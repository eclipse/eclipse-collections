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

import java.util.Optional;

import org.eclipse.collections.impl.block.factory.Functions;
import org.junit.Assert;
import org.junit.Test;

public class MinByProcedureTest
{
    @Test
    public void visitedAtLeastOnce()
    {
        MinByProcedure<Integer, Integer> procedure = new MinByProcedure<>(Functions.getPassThru());
        Assert.assertFalse(procedure.isVisitedAtLeastOnce());
        procedure.value(1);
        Assert.assertTrue(procedure.isVisitedAtLeastOnce());
        procedure.value(2);
        Assert.assertTrue(procedure.isVisitedAtLeastOnce());
    }

    @Test
    public void getResultOptional()
    {
        MinByProcedure<Integer, Integer> procedure = new MinByProcedure<>(Functions.getPassThru());
        Assert.assertFalse(procedure.getResultOptional().isPresent());
        procedure.value(2);
        Optional<Integer> resultOptional = procedure.getResultOptional();
        Assert.assertTrue(resultOptional.isPresent());
        Assert.assertEquals((Integer) 2, resultOptional.get());

        procedure.value(1);
        Optional<Integer> resultOptional2 = procedure.getResultOptional();
        Assert.assertTrue(resultOptional2.isPresent());
        Assert.assertEquals((Integer) 1, resultOptional2.get());
    }

    @Test
    public void value()
    {
        MinByProcedure<Integer, Integer> procedure = new MinByProcedure<>(Functions.getPassThru());
        Integer first = new Integer(1);
        procedure.value(first);
        Assert.assertSame(first, procedure.getResult());
        Integer second = new Integer(1);
        procedure.value(second);
        Assert.assertSame(first, procedure.getResult());
        Integer third = new Integer(3);
        procedure.value(third);
        Assert.assertSame(first, procedure.getResult());
        Integer fourth = new Integer(0);
        procedure.value(fourth);
        Assert.assertSame(fourth, procedure.getResult());
    }
}
