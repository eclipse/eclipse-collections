/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import org.eclipse.collections.api.factory.map.strategy.ImmutableHashingStrategyMapFactory;
import org.eclipse.collections.api.factory.map.strategy.MutableHashingStrategyMapFactory;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.strategy.mutable.UnifiedMapWithHashingStrategy;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.test.domain.Person;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashingStrategyMapsTest
{
    @Test
    public void immutable()
    {
        ImmutableHashingStrategyMapFactory factory = HashingStrategyMaps.immutable;
        assertEquals(UnifiedMap.newMap(), factory.of(HashingStrategies.defaultStrategy()));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(HashingStrategies.defaultStrategy()));
        assertEquals(UnifiedMap.newWithKeysValues(1, 2), factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6, 7, 8), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
    }

    @Test
    public void mutable()
    {
        MutableHashingStrategyMapFactory factory = HashingStrategyMaps.mutable;
        assertEquals(UnifiedMap.newMap(), factory.of(HashingStrategies.defaultStrategy()));
        Verify.assertInstanceOf(MutableMap.class, factory.of(HashingStrategies.defaultStrategy()));
        assertEquals(UnifiedMap.newWithKeysValues(1, 2), factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        Verify.assertInstanceOf(MutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        Verify.assertInstanceOf(MutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(MutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6, 7, 8), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(MutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));

        MutableList<Pair<Person, String>> pairs = Lists.mutable.of(
                Tuples.pair(new Person("John", "Smith"), "A"),
                Tuples.pair(new Person("Alex", "Smith"), "B"),
                Tuples.pair(new Person("Jeff", "Johnson"), "D"));

        assertEquals(
                new UnifiedMapWithHashingStrategy<Person, String>(HashingStrategies.fromFunction(Person::getLastName)).withAllKeyValues(pairs),
                factory.<Person, String, String>fromFunction(Person::getLastName).withAllKeyValues(pairs));

        assertEquals(
                new UnifiedMapWithHashingStrategy<Person, String>(HashingStrategies.fromFunction(Person::getFirstName)).withAllKeyValues(pairs),
                factory.<Person, String, String>fromFunction(Person::getFirstName).withAllKeyValues(pairs));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(HashingStrategyMaps.class);
    }
}
