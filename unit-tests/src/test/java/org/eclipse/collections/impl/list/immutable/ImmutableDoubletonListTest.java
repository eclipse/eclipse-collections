/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable;

import org.eclipse.collections.api.list.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableDoubletonListTest extends AbstractImmutableListTestCase
{
    @Override
    protected ImmutableList<Integer> classUnderTest()
    {
        return new ImmutableDoubletonList<>(1, 2);
    }

    @Test
    public void getOnly()
    {
        ImmutableList<Integer> list = this.classUnderTest();
        assertThrows(IllegalStateException.class, () -> list.getOnly());
    }
}
