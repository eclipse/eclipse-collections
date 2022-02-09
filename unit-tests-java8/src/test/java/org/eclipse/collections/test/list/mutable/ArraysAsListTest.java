/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.list.mutable;

import java.util.Arrays;
import java.util.List;

import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.eclipse.collections.test.list.FixedSizeListTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThrows;

@RunWith(Java8Runner.class)
public class ArraysAsListTest
        implements FixedSizeListTestCase
{
    @SafeVarargs
    @Override
    public final <T> List<T> newWith(T... elements)
    {
        return Arrays.asList(elements);
    }

    @Override
    @Test
    public void Collection_remove_removeAll()
    {
        // Removing items that aren't present works
        this.newWith("1", "2", "3").remove("4");

        assertThrows(UnsupportedOperationException.class, () -> this.newWith("1").remove("1"));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith("1", "2").remove("1"));
        assertThrows(UnsupportedOperationException.class, () -> this.newWith("1", "2", "3").remove("1"));

        // Removing items that aren't present works
        this.newWith("1", "2", "3").removeAll(Lists.mutable.with("4", "5"));

        assertThrows(
                UnsupportedOperationException.class,
                () -> this.newWith("1").removeAll(Lists.mutable.with("1", "2")));
        assertThrows(
                UnsupportedOperationException.class,
                () -> this.newWith("1", "2").removeAll(Lists.mutable.with("1", "2")));
        assertThrows(
                UnsupportedOperationException.class,
                () -> this.newWith("1", "2", "3").removeAll(Lists.mutable.with("1", "2")));
    }

    @Override
    @Test
    public void Collection_clear()
    {
        // Clearing an empty list works
        this.newWith().clear();

        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1).clear());
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2).clear());
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).clear());
    }
}
