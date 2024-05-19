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
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FastListCollectIfProcedureTest
{
    @Test
    public void value()
    {
        Predicates<Pair<Integer, String>> predicate =
                Predicates.attributeGreaterThanOrEqualTo(Functions.firstOfPair(), 10);

        FastListCollectIfProcedure<Pair<Integer, String>, String> procedure =
                new FastListCollectIfProcedure<>(FastList.newList(), Functions.secondOfPair(), predicate);

        procedure.value(Tuples.pair(1, "one"));
        procedure.value(Tuples.pair(2, "two"));
        String eleven = "eleven";
        procedure.value(Tuples.pair(11, eleven));

        String twelve = "twelve";
        procedure.value(Tuples.pair(12, twelve));
        assertEquals(Lists.mutable.of(eleven, twelve), procedure.getFastList());
    }
}
