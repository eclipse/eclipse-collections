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

import org.eclipse.collections.api.factory.set.strategy.ImmutableHashingStrategySetFactory;
import org.eclipse.collections.api.factory.set.strategy.MutableHashingStrategySetFactory;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.test.domain.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashingStrategySetsTest
{
    @Test
    public void immutable()
    {
        ImmutableHashingStrategySetFactory factory = HashingStrategySets.immutable;
        assertEquals(UnifiedSet.newSet(), factory.of(HashingStrategies.defaultStrategy()));
        Verify.assertInstanceOf(ImmutableSet.class, factory.of(HashingStrategies.defaultStrategy()));
        assertEquals(UnifiedSet.newSetWith(1, 2), factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        Verify.assertInstanceOf(ImmutableSet.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableSet.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5, 6), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableSet.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5, 6, 7, 8), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableSet.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(UnifiedSet.newSetWith(), factory.of(HashingStrategies.defaultStrategy(), null));
        Verify.assertInstanceOf(ImmutableSet.class, factory.of(HashingStrategies.defaultStrategy(), null));
        assertEquals(UnifiedSetWithHashingStrategy.newSet(HashingStrategies.defaultStrategy(), 1), factory.ofInitialCapacity(HashingStrategies.defaultStrategy(), 1));
        Verify.assertInstanceOf(ImmutableSet.class, factory.ofInitialCapacity(HashingStrategies.defaultStrategy(), 1));
        assertEquals(UnifiedSetWithHashingStrategy.newSet(HashingStrategies.defaultStrategy(), 3), factory.withInitialCapacity(HashingStrategies.defaultStrategy(), 3));
        Verify.assertInstanceOf(ImmutableSet.class, factory.ofInitialCapacity(HashingStrategies.defaultStrategy(), 3));
    }

    @Test
    public void mutable()
    {
        MutableHashingStrategySetFactory factory = HashingStrategySets.mutable;
        assertEquals(UnifiedSet.newSet(), factory.of(HashingStrategies.defaultStrategy()));
        Verify.assertInstanceOf(MutableSet.class, factory.of(HashingStrategies.defaultStrategy()));
        assertEquals(UnifiedSet.newSetWith(1, 2), factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        Verify.assertInstanceOf(MutableSet.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        Verify.assertInstanceOf(MutableSet.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5, 6), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(MutableSet.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5, 6, 7, 8), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(MutableSet.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5, 6, 7, 8), factory.ofAll(HashingStrategies.defaultStrategy(), FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
        Verify.assertInstanceOf(MutableSet.class, factory.of(HashingStrategies.defaultStrategy(), FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
        assertEquals(UnifiedSetWithHashingStrategy.newSet(HashingStrategies.defaultStrategy(), 1), factory.ofInitialCapacity(HashingStrategies.defaultStrategy(), 1));
        Verify.assertInstanceOf(MutableSet.class, factory.ofInitialCapacity(HashingStrategies.defaultStrategy(), 1));
        assertEquals(UnifiedSetWithHashingStrategy.newSet(HashingStrategies.defaultStrategy(), 3), factory.withInitialCapacity(HashingStrategies.defaultStrategy(), 3));
        Verify.assertInstanceOf(MutableSet.class, factory.ofInitialCapacity(HashingStrategies.defaultStrategy(), 3));

        MutableSet<Person> people =
                Sets.mutable.of(new Person("Alex", "Smith"), new Person("John", "Smith"), new Person("John", "Brown"));

        assertEquals(
                UnifiedSetWithHashingStrategy.newSetWith(HashingStrategies.fromFunction(Person::getLastName)).withAll(people),
                factory.fromFunction(Person::getLastName).withAll(people));

        assertEquals(
                UnifiedSetWithHashingStrategy.newSetWith(HashingStrategies.fromFunction(Person::getLastName)).withAll(people),
                factory.fromFunction(Person::getFirstName).withAll(people));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(HashingStrategySets.class);
    }
}
