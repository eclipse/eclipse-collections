/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function.primitive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public final class DoubleFunctionImplTest
{
    private static final Object JUNK = new Object();

    @Test
    public void testValueOf()
    {
        assertSame(new TestDoubleFunctionImpl(0.0d).valueOf(JUNK), new TestDoubleFunctionImpl(0.0d).valueOf(JUNK));
        assertEquals(Double.valueOf(1.0d), new TestDoubleFunctionImpl(1.0d).valueOf(JUNK));
    }

    private static final class TestDoubleFunctionImpl extends DoubleFunctionImpl<Object>
    {
        private static final long serialVersionUID = 1L;
        private final double toReturn;

        private TestDoubleFunctionImpl(double toReturn)
        {
            this.toReturn = toReturn;
        }

        @Override
        public double doubleValueOf(Object anObject)
        {
            return this.toReturn;
        }
    }
}
