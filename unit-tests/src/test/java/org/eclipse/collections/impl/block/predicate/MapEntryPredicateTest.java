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

import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapEntryPredicateTest
{
    private final Map.Entry<String, Integer> entry = new Map.Entry<String, Integer>()
    {
        @Override
        public String getKey()
        {
            return "1";
        }

        @Override
        public Integer getValue()
        {
            return 1;
        }

        @Override
        public Integer setValue(Integer value)
        {
            return null;
        }
    };

    @Test
    public void accept()
    {
        MapEntryPredicate<String, Integer> mapEntryPredicate = new MapEntryPredicate<String, Integer>()
        {
            @Override
            public boolean accept(String argument1, Integer argument2)
            {
                return String.valueOf(argument2).equals(argument1);
            }
        };
        assertTrue(mapEntryPredicate.accept(this.entry));
    }

    @Test
    public void negate()
    {
        MapEntryPredicate<String, Integer> mapEntryPredicate = new MapEntryPredicate<String, Integer>()
        {
            @Override
            public boolean accept(String argument1, Integer argument2)
            {
                return String.valueOf(argument2).equals(argument1);
            }
        };
        assertFalse(mapEntryPredicate.negate().accept(this.entry));
        assertFalse(mapEntryPredicate.negate().accept("1", 1));
        assertTrue(mapEntryPredicate.negate().accept(new Map.Entry<String, Integer>()
        {
            @Override
            public String getKey()
            {
                return "1";
            }

            @Override
            public Integer getValue()
            {
                return 2;
            }

            @Override
            public Integer setValue(Integer value)
            {
                return null;
            }
        }));
        assertTrue(mapEntryPredicate.negate().accept("1", 2));
    }
}
