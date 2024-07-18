/*
 * Copyright (c) 2023 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable.primitive;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoxedMutableBooleanSetTest
{
    private BoxedMutableBooleanSet classUnderTest()
    {
        return new BoxedMutableBooleanSet(new BooleanHashSet(true, false));
    }

    @Test
    public void setCreationValidation()
    {
        assertThrows(NullPointerException.class, () -> new BoxedMutableBooleanSet(null));
    }

    @Test
    public void size()
    {
        BoxedMutableBooleanSet set = this.classUnderTest();
        Verify.assertSize(2, set);
        set.remove("name");
        Verify.assertSize(2, set);
        set.remove(false);
        Verify.assertSize(1, set);
    }

    @Test
    public void getFirst()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().getFirst());
    }

    @Test
    public void getLast()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().getLast());
    }

    @Test
    public void each()
    {
        MutableSet<Boolean> result = Sets.mutable.empty();
        this.classUnderTest().forEach(result::add);
        assertEquals(Sets.mutable.of(true, false), result);
    }

    @Test
    public void add()
    {
        BoxedMutableBooleanSet set = new BoxedMutableBooleanSet(new BooleanHashSet(true));
        assertTrue(set.add(Boolean.FALSE));
        assertEquals(Sets.mutable.of(Boolean.TRUE, Boolean.FALSE), set);

        assertFalse(set.add(Boolean.FALSE));
        assertEquals(Sets.mutable.of(Boolean.TRUE, Boolean.FALSE), set);
    }

    @Test
    public void remove()
    {
        BoxedMutableBooleanSet set = this.classUnderTest();
        assertFalse(set.remove("abc"));
        assertEquals(Sets.mutable.of(Boolean.TRUE, Boolean.FALSE), set);

        assertTrue(set.remove(Boolean.TRUE));
        assertEquals(Sets.mutable.of(Boolean.FALSE), set);

        assertFalse(set.remove(Boolean.TRUE));
        assertEquals(Sets.mutable.of(Boolean.FALSE), set);
    }

    @Test
    public void contains()
    {
        BoxedMutableBooleanSet set = new BoxedMutableBooleanSet(new BooleanHashSet(true));
        assertTrue(set.contains(Boolean.TRUE));
        assertFalse(set.contains(Boolean.FALSE));
        assertFalse(set.contains("abc"));
    }

    @Test
    public void iterator()
    {
        MutableSet<Boolean> result = Sets.mutable.empty();
        this.classUnderTest().iterator().forEachRemaining(result::add);
        assertEquals(Sets.mutable.of(Boolean.TRUE, Boolean.FALSE), result);
    }

    @Test
    public void clear()
    {
        BoxedMutableBooleanSet set = this.classUnderTest();
        set.clear();
        Verify.assertEmpty(set);
    }

    @Test
    public void asParallel()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().asParallel(null, 1));
    }

    @Test
    public void hashCodeEquals()
    {
        BoxedMutableBooleanSet set = this.classUnderTest();
        Verify.assertEqualsAndHashCode(Sets.mutable.of(Boolean.TRUE, Boolean.FALSE), set);
        Verify.assertEqualsAndHashCode(new HashSet<>(Arrays.asList(Boolean.TRUE, Boolean.FALSE)), set);
    }

    @Test
    public void mutationOfOriginalSet()
    {
        BooleanHashSet originalSet = new BooleanHashSet(true, false);
        BoxedMutableBooleanSet set = new BoxedMutableBooleanSet(originalSet);

        originalSet.remove(true);
        assertEquals(Sets.mutable.of(Boolean.FALSE), set);

        originalSet.clear();
        Verify.assertEmpty(set);

        originalSet.add(true);
        assertEquals(Sets.mutable.of(Boolean.TRUE), set);
    }
}
