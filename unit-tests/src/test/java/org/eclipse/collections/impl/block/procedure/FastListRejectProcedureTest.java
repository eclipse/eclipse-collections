/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FastListRejectProcedureTest
{
    @Test
    public void value()
    {
        FastListRejectProcedure<Integer> procedure =
                new FastListRejectProcedure<>(Predicates.greaterThan(5), FastList.newList());
        procedure.value(0);
        procedure.value(1);
        procedure.value(10);
        procedure.value(20);

        assertEquals(Lists.mutable.of(0, 1), procedure.getFastList());
    }
}
