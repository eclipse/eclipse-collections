/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.immutable;

import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.api.set.ImmutableSet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ImmutableSingletonSetTest
        extends AbstractImmutableSetTestCase
{
    @Override
    protected ImmutableSet<Integer> classUnderTest()
    {
        return new ImmutableSingletonSet<>(1);
    }

    @Test
    @Override
    public void min_null_throws()
    {
        // Collections with one element should not throw to emulate the JDK Collections behavior
        assertDoesNotThrow(() -> this.classUnderTestWithNull().min(Integer::compareTo));
    }

    @Test
    @Override
    public void max_null_throws()
    {
        // Collections with one element should not throw to emulate the JDK Collections behavior
        assertDoesNotThrow(() -> this.classUnderTestWithNull().max(Integer::compareTo));
    }

    @Test
    @Override
    public void min_null_throws_without_comparator()
    {
        // Collections with one element should not throw to emulate the JDK Collections behavior
        assertDoesNotThrow(() -> this.classUnderTestWithNull().min());
    }

    @Test
    @Override
    public void max_null_throws_without_comparator()
    {
        // Collections with one element should not throw to emulate the JDK Collections behavior
        assertDoesNotThrow(() -> this.classUnderTestWithNull().max());
    }

    @Test
    public void getOnly()
    {
        ImmutableCollection<Integer> integers = this.classUnderTest();
        assertEquals(Integer.valueOf(1), integers.getOnly());
    }
}
