/*
 * Copyright (c) 2022 Bhavana Hindupur and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.factory.bag.strategy.MutableHashingStrategyBagFactory;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.bag.strategy.mutable.HashBagWithHashingStrategy;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.test.domain.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashingStrategyBagsTest
{
    @Test
    public void mutable()
    {
        MutableHashingStrategyBagFactory factory = HashingStrategyBags.mutable;
        assertEquals(HashBag.newBag(), factory.of(HashingStrategies.defaultStrategy()));
        Verify.assertInstanceOf(MutableBag.class, factory.of(HashingStrategies.defaultStrategy()));
        assertEquals(HashBag.newBag(), factory.empty(HashingStrategies.defaultStrategy()));
        Verify.assertInstanceOf(MutableBag.class, factory.empty(HashingStrategies.defaultStrategy()));
        assertEquals(HashBag.newBagWith(1, 2), factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        Verify.assertInstanceOf(MutableBag.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        assertEquals(HashBag.newBagWith(1, 2, 3, 4), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        Verify.assertInstanceOf(MutableBag.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        assertEquals(HashBag.newBagWith(1, 2, 3, 4, 5, 6), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(MutableBag.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        assertEquals(HashBag.newBagWith(1, 2, 3, 4, 5, 6, 7, 8), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(MutableBag.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(HashBag.newBagWith(1, 2, 3, 4, 5, 6, 7, 8), factory.ofAll(HashingStrategies.defaultStrategy(), FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
        Verify.assertInstanceOf(MutableBag.class, factory.of(HashingStrategies.defaultStrategy(), FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));

        MutableList<Person> people =
                Lists.mutable.of(new Person("Alex", "Smith"), new Person("John", "Smith"), new Person("John", "Brown"));

        assertEquals(HashBagWithHashingStrategy.newBagWith(HashingStrategies.fromFunction(Person::getLastName)).withAll(people),
                factory.fromFunction(Person::getLastName).withAll(people));

        assertEquals(HashBagWithHashingStrategy.newBagWith(HashingStrategies.fromFunction(Person::getFirstName)).withAll(people),
                factory.fromFunction(Person::getFirstName).withAll(people));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(HashingStrategyBags.class);
    }
}
