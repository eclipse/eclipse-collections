/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.tuple;

import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbstractImmutableEntryTest
{
    @Test
    public void getKeyFunction()
    {
        Map.Entry<String, Integer> entry = new ImmutableEntry<>("foo", 2);
        assertEquals("foo", AbstractImmutableEntry.<String>getKeyFunction().valueOf(entry));
    }

    @Test
    public void getValueFunction()
    {
        Map.Entry<String, Integer> entry = new ImmutableEntry<>("foo", 2);
        assertEquals(Integer.valueOf(2), AbstractImmutableEntry.<Integer>getValueFunction().valueOf(entry));
    }
}
