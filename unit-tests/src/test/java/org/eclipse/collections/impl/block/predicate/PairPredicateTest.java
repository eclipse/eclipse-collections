/*
 * Copyright (c) 2021 Gaurav Khurana.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.predicate;

import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PairPredicateTest
{
    @Test
    public void accept()
    {
        PairPredicate<String, Integer> pairPredicate = new PairPredicate<String, Integer>()
        {
            @Override
            public boolean accept(String argument1, Integer argument2)
            {
                return String.valueOf(argument2).equals(argument1);
            }
        };

        assertTrue(pairPredicate.accept(Tuples.pair("1", 1)));
        assertFalse(pairPredicate.accept(Tuples.pair("2", 1)));
    }

    @Test
    public void negate()
    {
        PairPredicate<String, String> pairPredicate = new PairPredicate<String, String>()
        {
            @Override
            public boolean accept(String argument1, String argument2)
            {
                return argument2.equals(argument1);
            }
        };

        PairPredicate<String, String> negatedPredicate = pairPredicate.negate();
        assertFalse(negatedPredicate.accept(Tuples.pair("1", "1")));
        assertFalse(negatedPredicate.accept("1", "1"));
        assertTrue(negatedPredicate.accept(Tuples.pair("2", "1")));
        assertTrue(negatedPredicate.accept("2", "1"));
    }
}
