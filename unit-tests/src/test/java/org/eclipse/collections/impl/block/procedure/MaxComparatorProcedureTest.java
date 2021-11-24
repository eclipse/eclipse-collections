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

import java.util.Comparator;
import java.util.Optional;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MaxComparatorProcedureTest
{
    @Test
    public void value()
    {
        MaxComparatorProcedure<MutableList<Integer>> procedure =
                new MaxComparatorProcedure<>(Comparator.comparingInt(value -> value.size()));

        assertFalse(procedure.isVisitedAtLeastOnce());
        assertFalse(procedure.getResultOptional().isPresent());

        procedure.value(Lists.mutable.of(1));
        assertEquals(Lists.mutable.of(1), procedure.getResult());

        assertTrue(procedure.isVisitedAtLeastOnce());
        Optional<MutableList<Integer>> resultOptional = procedure.getResultOptional();
        assertTrue(resultOptional.isPresent());
        assertEquals(procedure.getResult(), resultOptional.get());

        procedure.value(Lists.mutable.of(2));
        assertEquals(Lists.mutable.of(1), procedure.getResult());

        procedure.value(Lists.mutable.empty());
        assertEquals(Lists.mutable.of(1), procedure.getResult());

        procedure.value(Lists.mutable.of(5, 6));
        assertEquals(Lists.mutable.of(5, 6), procedure.getResult());

        procedure.value(Lists.mutable.of(1));
        assertEquals(Lists.mutable.of(5, 6), procedure.getResult());

        assertTrue(procedure.isVisitedAtLeastOnce());
        Optional<MutableList<Integer>> optional = procedure.getResultOptional();
        assertTrue(optional.isPresent());
        assertEquals(procedure.getResult(), optional.get());
    }
}
