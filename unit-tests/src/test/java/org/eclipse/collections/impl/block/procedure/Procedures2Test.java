/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.block.procedure;

import java.io.IOException;

import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.impl.block.factory.Procedures2;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Procedures2Test
{
    @Test
    public void throwing()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () -> Procedures2.throwing((a, b) -> { throw new IOException(); }).value(null, null));
    }

    @Test
    public void asProcedure2()
    {
        CollectionAddProcedure<Integer> procedure = CollectionAddProcedure.on(FastList.<Integer>newList());
        Procedure2<Integer, Object> procedure2 = Procedures2.fromProcedure(procedure);
        procedure2.value(1, null);
        Assert.assertEquals(FastList.newListWith(1), procedure.getResult());
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(Procedures2.class);
    }
}
