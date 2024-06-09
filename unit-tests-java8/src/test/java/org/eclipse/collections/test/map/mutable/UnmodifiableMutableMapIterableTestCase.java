/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map.mutable;

import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.test.map.UnmodifiableMapIterableTestCase;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertThrows;

public interface UnmodifiableMutableMapIterableTestCase
        extends UnmodifiableMapIterableTestCase, MutableMapIterableTestCase
{
    @Override
    default void MutableMapIterable_removeKey()
    {
        MutableMapIterable<Object, Object> map = this.newWith();
        assertThrows(UnsupportedOperationException.class, () -> map.removeKey(2));
    }

    @Override
    default void MutableMapIterable_removeIf()
    {
        MutableMapIterable<Object, String> map = this.newWith("Three", "Two", "One");
        assertThrows(UnsupportedOperationException.class, () -> map.removeIf(Predicates2.alwaysFalse()));
    }

    @Override
    @Test
    default void MutableMapIterable_getIfAbsentPut()
    {
        MutableMapIterable<String, Integer> map = this.newWithKeysValues("3", 3, "2", 2, "1", 1);
        assertEquals(3, map.getIfAbsentPut("3", () -> {
            throw new AssertionError();
        }));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1), map);

        assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPut("4", () -> {
            throw new AssertionError();
        }));

        assertEquals(3, map.getIfAbsentPut("3", 4));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1), map);

        assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPut("4", 4));

        assertEquals(3, map.getIfAbsentPutWithKey("3", key -> {
            throw new AssertionError();
        }));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1), map);

        assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPutWithKey("4", key -> {
            throw new AssertionError();
        }));

        assertEquals(3, map.getIfAbsentPutWith("3", x -> x + 10, 4));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1), map);

        assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPutWith("4", x -> x + 10, 4));
    }

    @Override
    default void MutableMapIterable_updateValue()
    {
        MutableMapIterable<String, Integer> map = this.newWithKeysValues("One", 1);
        assertThrows(
                UnsupportedOperationException.class,
                () -> map.updateValue("One", () -> 0, ignored -> {
                    throw new AssertionError();
                }));
        assertThrows(
                UnsupportedOperationException.class,
                () -> map.updateValue("Two", () -> 0, ignored -> {
                    throw new AssertionError();
                }));

        assertThrows(
                UnsupportedOperationException.class,
                () -> map.updateValueWith("One", () -> 0, (ignored, parameter) -> {
                    throw new AssertionError();
                }, "test"));
        assertThrows(
                UnsupportedOperationException.class,
                () -> map.updateValueWith("Two", () -> 0, (ignored, parameter) -> {
                    throw new AssertionError();
                }, "test"));
    }
}
