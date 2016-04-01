/*
 * Copyright (c) 2016 Bhavana Hindupur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.strategy.mutable;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.bag.mutable.MutableBagTestCase;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.test.domain.Person;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test suite for {@link HashBagWithHashingStrategy}.
 */
public class HashBagWithHashingStrategyTest extends MutableBagTestCase
{
    private static final HashingStrategy<Person> FIRST_NAME_HASHING_STRATEGY = HashingStrategies.fromFunction(Person.TO_FIRST);
    private static final HashingStrategy<Person> LAST_NAME_HASHING_STRATEGY = HashingStrategies.fromFunction(Person.TO_LAST);

    private static final Person JOHNSMITH = new Person("John", "Smith");
    private static final Person JANESMITH = new Person("Jane", "Smith");
    private static final Person JOHNDOE = new Person("John", "Doe");
    private static final Person JANEDOE = new Person("Jane", "Doe");

    @Override
    protected <T> MutableBag<T> newWith(T... littleElements)
    {
        return HashBagWithHashingStrategy.newBagWith(HashingStrategies.nullSafeHashingStrategy(HashingStrategies.defaultStrategy()), littleElements);
    }

    @Override
    protected <T> MutableBag<T> newWithOccurrences(ObjectIntPair<T>... elementsWithOccurrences)
    {
        MutableBag<T> bag = this.newWith();
        for (int i = 0; i < elementsWithOccurrences.length; i++)
        {
            ObjectIntPair<T> itemToAdd = elementsWithOccurrences[i];
            bag.addOccurrences(itemToAdd.getOne(), itemToAdd.getTwo());
        }
        return bag;
    }

    @Test
    public void newBag_throws()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> HashBagWithHashingStrategy.newBag(null));
        Verify.assertThrows(IllegalArgumentException.class, () -> HashBagWithHashingStrategy.newBag(null, 1));
        Verify.assertThrows(IllegalArgumentException.class, () -> HashBagWithHashingStrategy.newBag(null, Bags.mutable.empty()));
        Verify.assertThrows(IllegalArgumentException.class, () -> HashBagWithHashingStrategy.newBag(null, Lists.mutable.empty()));
        Verify.assertThrows(IllegalArgumentException.class, () -> HashBagWithHashingStrategy.newBag(HashingStrategies.defaultStrategy(), -1));
    }

    @Override
    @Test
    public void removeAllIterable()
    {
        super.removeAllIterable();
        MutableBag<Integer> objects = this.newWith(1, 2, 3);
        Assert.assertTrue(objects.removeAllIterable(Bags.mutable.of(1, 2, 4)));
        Assert.assertEquals(Bags.mutable.of(3), objects.toBag());
    }

    @Override
    @Test
    public void addAll()
    {
        super.addAll();
        MutableBag<Integer> bag1 = this.newWith();
        Assert.assertTrue(bag1.addAll(this.newWith(1, 1, 2, 3)));
        Verify.assertContainsAll(bag1, 1, 2, 3);

        Assert.assertTrue(bag1.addAll(this.newWith(1, 2, 3)));
        Verify.assertSize(7, bag1);
        Assert.assertFalse(bag1.addAll(this.newWith()));
        Verify.assertContainsAll(bag1, 1, 2, 3);

        MutableBag<Integer> bag2 = this.newWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        bag2.addAll(this.newWith(5, 5, 5, 5, 5));

        Verify.assertBagsEqual(this.newWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5), bag2);

        MutableBag<Integer> bag3 = this.newWith(1, 2, 2, 3, 3, 3);
        bag3.addAll(this.newWith(1));

        Verify.assertBagsEqual(this.newWith(1, 1, 2, 2, 3, 3, 3), bag3);
    }

    @Test
    public void hashingStrategy()
    {
        HashBagWithHashingStrategy<Integer> map = HashBagWithHashingStrategy.newBagWith(HashingStrategies.defaultStrategy(), 1, 1, 2, 2);
        Assert.assertSame(HashingStrategies.defaultStrategy(), map.hashingStrategy());
    }

    @Test
    public void contains_with_hashing_strategy()
    {
        HashBagWithHashingStrategy<Person> bag = HashBagWithHashingStrategy.newBagWith(
                LAST_NAME_HASHING_STRATEGY, JOHNDOE, JANEDOE, JANEDOE, JOHNSMITH, JOHNSMITH, JOHNSMITH, JANESMITH, JANESMITH, JANESMITH, JANESMITH);
        Verify.assertContains(JOHNDOE, bag);
        Assert.assertEquals(3, bag.occurrencesOf(JOHNDOE));

        Verify.assertContains(JOHNSMITH, bag);
        Assert.assertEquals(7, bag.occurrencesOf(JOHNSMITH));

        Verify.assertContains(JANEDOE, bag);
        Verify.assertContains(JANESMITH, bag);
    }

    @Test
    public void equals_with_hashing_strategy()
    {
        HashBagWithHashingStrategy<Person> bag1 = HashBagWithHashingStrategy.newBagWith(LAST_NAME_HASHING_STRATEGY, JOHNDOE, JANEDOE, JOHNSMITH, JANESMITH);
        HashBagWithHashingStrategy<Person> bag2 = HashBagWithHashingStrategy.newBagWith(FIRST_NAME_HASHING_STRATEGY, JOHNDOE, JANEDOE, JOHNSMITH, JANESMITH);

        Assert.assertEquals(bag1, bag2);
        Assert.assertEquals(bag2, bag1);
        Assert.assertNotEquals(bag1.hashCode(), bag2.hashCode());

        HashBagWithHashingStrategy<Person> bag3 = HashBagWithHashingStrategy.newBagWith(
                LAST_NAME_HASHING_STRATEGY, JOHNDOE, JANEDOE, JANEDOE, JOHNSMITH, JOHNSMITH, JOHNSMITH, JANESMITH, JANESMITH, JANESMITH, JANESMITH);
        HashBagWithHashingStrategy<Person> bag4 = HashBagWithHashingStrategy.newBag(bag3.hashingStrategy(), bag3);
        MutableBag<Person> hashBag = HashBag.newBag(bag3);

        Verify.assertEqualsAndHashCode(bag3, bag4);
        Assert.assertTrue(bag3.equals(hashBag) && hashBag.equals(bag3) && bag3.hashCode() != hashBag.hashCode());

        HashBag<Person> people = HashBag.newBagWith(
                JOHNDOE, JANEDOE, JANEDOE, JOHNSMITH, JOHNSMITH, JOHNSMITH, JANESMITH, JANESMITH, JANESMITH, JANESMITH);
        HashBagWithHashingStrategy<Person> bag5 = HashBagWithHashingStrategy.newBag(LAST_NAME_HASHING_STRATEGY, people);
        Assert.assertNotEquals(bag5, people);
    }

    @Test
    public void addOccurrences_occurrencesOf_with_hashing_strategy()
    {
        HashBagWithHashingStrategy<String> bag = HashBagWithHashingStrategy.newBagWith(HashingStrategies.defaultStrategy(), "1", "2", "2");
        bag.addOccurrences(null, 5);

        //Testing getting values from no chains
        Assert.assertEquals(1, bag.occurrencesOf("1"));
        Assert.assertEquals(2, bag.occurrencesOf("2"));
        Assert.assertEquals(5, bag.occurrencesOf(null));

        HashBagWithHashingStrategy<Person> bag2 = HashBagWithHashingStrategy.newBag(LAST_NAME_HASHING_STRATEGY);
        bag2.addOccurrences(JOHNSMITH, 1);
        Assert.assertEquals(1, bag2.occurrencesOf(JOHNSMITH));
        bag2.addOccurrences(JANESMITH, 2);
        Assert.assertEquals(3, bag2.occurrencesOf(JOHNSMITH));
    }

    @Test
    public void remove_with_hashing_strategy()
    {
        HashBagWithHashingStrategy<Person> bag = HashBagWithHashingStrategy.newBagWith(
                LAST_NAME_HASHING_STRATEGY, JOHNDOE, JANEDOE, JANEDOE, JOHNSMITH, JOHNSMITH, JOHNSMITH, JANESMITH, JANESMITH, JANESMITH, JANESMITH);

        bag.removeOccurrences(JANEDOE, 3);
        Assert.assertEquals(HashBagWithHashingStrategy.newBagWith(LAST_NAME_HASHING_STRATEGY, JOHNSMITH, JOHNSMITH, JOHNSMITH, JOHNSMITH, JOHNSMITH, JOHNSMITH, JOHNSMITH), bag);
        bag.removeOccurrences(JOHNSMITH, 7);

        Verify.assertEmpty(bag);

        HashBagWithHashingStrategy<Integer> bag2 = HashBagWithHashingStrategy.newBagWith(HashingStrategies.defaultStrategy(), 1, null, null, 3, 3, 3);
        bag2.removeOccurrences(null, 2);
        Assert.assertEquals(HashBagWithHashingStrategy.newBagWith(HashingStrategies.defaultStrategy(), 1, 3, 3, 3), bag2);
    }
}
