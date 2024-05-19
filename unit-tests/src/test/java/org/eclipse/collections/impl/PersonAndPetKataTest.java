/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.ImmutableIntBag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.partition.PartitionIterable;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.IntSet;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.bag.mutable.primitive.IntHashBag;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.eclipse.collections.impl.utility.StringIterate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PersonAndPetKataTest
{
    private MutableList<Person> people;

    @Before
    public void setUp()
    {
        this.people = Lists.mutable.with(
                new Person("Mary", "Smith").addPet(PetType.CAT, "Tabby", 2),
                new Person("Bob", "Smith")
                        .addPet(PetType.CAT, "Dolly", 3)
                        .addPet(PetType.DOG, "Spot", 2),
                new Person("Ted", "Smith").addPet(PetType.DOG, "Spike", 4),
                new Person("Jake", "Snake").addPet(PetType.SNAKE, "Serpy", 1),
                new Person("Barry", "Bird").addPet(PetType.BIRD, "Tweety", 2),
                new Person("Terry", "Turtle").addPet(PetType.TURTLE, "Speedy", 1),
                new Person("Harry", "Hamster")
                        .addPet(PetType.HAMSTER, "Fuzzy", 1)
                        .addPet(PetType.HAMSTER, "Wuzzy", 1));
    }

    @Test
    public void doAnyPeopleHaveCats()
    {
        boolean resultEager = this.people.anySatisfy(person -> person.hasPet(PetType.CAT));
        assertTrue(resultEager);

        boolean resultEagerMR = this.people.anySatisfyWith(Person::hasPet, PetType.CAT);
        assertTrue(resultEagerMR);

        boolean resultLazy = this.people.asLazy()
                .anySatisfy(person -> person.hasPet(PetType.CAT));
        assertTrue(resultLazy);

        boolean resultLazyMR = this.people.asLazy()
                .anySatisfyWith(Person::hasPet, PetType.CAT);
        assertTrue(resultLazyMR);
    }

    @Test
    public void doAnyPeopleHaveCatsUsingStreams()
    {
        boolean result = this.people.stream()
                .anyMatch(person -> person.hasPet(PetType.CAT));
        assertTrue(result);
    }

    @Test
    public void doAllPeopleHaveCats()
    {
        boolean resultEager = this.people.allSatisfy(person -> person.hasPet(PetType.CAT));
        assertFalse(resultEager);

        boolean resultEagerMR = this.people.allSatisfyWith(Person::hasPet, PetType.CAT);
        assertFalse(resultEagerMR);

        boolean resultLazy = this.people.asLazy()
                .allSatisfy(person -> person.hasPet(PetType.CAT));
        assertFalse(resultLazy);

        boolean resultLazyMR = this.people.asLazy()
                .allSatisfyWith(Person::hasPet, PetType.CAT);
        assertFalse(resultLazyMR);
    }

    @Test
    public void doAllPeopleHaveCatsUsingStreams()
    {
        boolean resultStream = this.people.stream()
                .allMatch(person -> person.hasPet(PetType.CAT));
        assertFalse(resultStream);
    }

    @Test
    public void doNoPeopleHaveCats()
    {
        boolean resultEager = this.people.noneSatisfy(person -> person.hasPet(PetType.CAT));
        assertFalse(resultEager);

        boolean resultEagerMR = this.people.noneSatisfyWith(Person::hasPet, PetType.CAT);
        assertFalse(resultEagerMR);

        boolean resultLazy = this.people.asLazy()
                .noneSatisfy(person -> person.hasPet(PetType.CAT));
        assertFalse(resultLazy);

        boolean resultLazyMR = this.people.asLazy()
                .noneSatisfyWith(Person::hasPet, PetType.CAT);
        assertFalse(resultLazyMR);
    }

    @Test
    public void doNoPeopleHaveCatsUsingStreams()
    {
        boolean resultStream = this.people.stream()
                .noneMatch(person -> person.hasPet(PetType.CAT));
        assertFalse(resultStream);
    }

    @Test
    public void howManyPeopleHaveCats()
    {
        int countEager = this.people.count(person -> person.hasPet(PetType.CAT));
        assertEquals(2, countEager);

        int countEagerMR = this.people.countWith(Person::hasPet, PetType.CAT);
        assertEquals(2, countEagerMR);

        int countLazy = this.people.asLazy()
                .count(person -> person.hasPet(PetType.CAT));
        assertEquals(2, countLazy);

        int countLazyMR = this.people.asLazy()
                .countWith(Person::hasPet, PetType.CAT);
        assertEquals(2, countLazyMR);
    }

    @Test
    public void howManyPeopleHaveCatsUsingStreams()
    {
        long countStream = this.people.stream()
                .filter(person -> person.hasPet(PetType.CAT))
                .count();
        assertEquals(2, countStream);
    }

    @Test
    public void getPeopleWithCats()
    {
        MutableList<Person> peopleWithCatsEager =
                this.people.select(person -> person.hasPet(PetType.CAT));
        Verify.assertSize(2, peopleWithCatsEager);

        MutableList<Person> peopleWithCatsEagerMR =
                this.people.selectWith(Person::hasPet, PetType.CAT);
        Verify.assertSize(2, peopleWithCatsEagerMR);

        MutableList<Person> peopleWithCatsLazy =
                this.people.asLazy()
                        .select(person -> person.hasPet(PetType.CAT))
                        .toList();
        Verify.assertSize(2, peopleWithCatsLazy);

        MutableList<Person> peopleWithCatsLazyMR =
                this.people.asLazy()
                        .selectWith(Person::hasPet, PetType.CAT)
                        .toList();
        Verify.assertSize(2, peopleWithCatsLazyMR);
    }

    @Test
    public void getPeopleWithCatsUsingStreams()
    {
        List<Person> peopleWithCatsStream = this.people.stream()
                .filter(person -> person.hasPet(PetType.CAT))
                .collect(Collectors.toList());
        Verify.assertSize(2, peopleWithCatsStream);
    }

    @Test
    public void getPeopleWhoDontHaveCats()
    {
        MutableList<Person> peopleWithNoCatsEager =
                this.people.reject(person -> person.hasPet(PetType.CAT));
        Verify.assertSize(5, peopleWithNoCatsEager);

        MutableList<Person> peopleWithNoCatsEagerMR =
                this.people.rejectWith(Person::hasPet, PetType.CAT);
        Verify.assertSize(5, peopleWithNoCatsEagerMR);

        MutableList<Person> peopleWithNoCatsLazy = this.people.asLazy()
                .reject(person -> person.hasPet(PetType.CAT))
                .toList();
        Verify.assertSize(5, peopleWithNoCatsLazy);

        MutableList<Person> peopleWithNoCatsLazyMR = this.people.asLazy()
                .rejectWith(Person::hasPet, PetType.CAT)
                .toList();
        Verify.assertSize(5, peopleWithNoCatsLazyMR);
    }

    @Test
    public void getPeopleWhoDontHaveCatsUsingStreams()
    {
        List<Person> peopleWithNoCatsStream = this.people.stream()
                .filter(person -> !person.hasPet(PetType.CAT))
                .collect(Collectors.toList());
        Verify.assertSize(5, peopleWithNoCatsStream);
    }

    @Test
    public void partitionPeopleByCatOwnersAndNonCatOwners()
    {
        PartitionMutableList<Person> catsAndNoCatsEager =
                this.people.partition(person -> person.hasPet(PetType.CAT));
        Verify.assertSize(2, catsAndNoCatsEager.getSelected());
        Verify.assertSize(5, catsAndNoCatsEager.getRejected());

        PartitionMutableList<Person> catsAndNoCatsEagerMR =
                this.people.partitionWith(Person::hasPet, PetType.CAT);
        Verify.assertSize(2, catsAndNoCatsEagerMR.getSelected());
        Verify.assertSize(5, catsAndNoCatsEagerMR.getRejected());

        PartitionIterable<Person> catsAndNoCatsLazy = this.people.asLazy()
                .partition(person -> person.hasPet(PetType.CAT));
        Verify.assertSize(2, catsAndNoCatsLazy.getSelected());
        Verify.assertSize(5, catsAndNoCatsLazy.getRejected());

        PartitionIterable<Person> catsAndNoCatsLazyMR = this.people.asLazy()
                .partitionWith(Person::hasPet, PetType.CAT);
        Verify.assertSize(2, catsAndNoCatsLazyMR.getSelected());
        Verify.assertSize(5, catsAndNoCatsLazyMR.getRejected());
    }

    @Test
    public void partitionPeopleByCatOwnersAndNonCatOwnersUsingStreams()
    {
        Map<Boolean, List<Person>> catsAndNoCatsStream = this.people.stream()
                .collect(Collectors.partitioningBy(person -> person.hasPet(PetType.CAT)));
        Verify.assertSize(2, catsAndNoCatsStream.get(true));
        Verify.assertSize(5, catsAndNoCatsStream.get(false));
    }

    @Test
    public void findPersonNamedMarySmith()
    {
        Person resultEager = this.people.detect(person -> person.named("Mary Smith"));
        assertEquals("Mary", resultEager.getFirstName());
        assertEquals("Smith", resultEager.getLastName());

        Person resultEagerMR = this.people.detectWith(Person::named, "Mary Smith");
        assertEquals("Mary", resultEagerMR.getFirstName());
        assertEquals("Smith", resultEagerMR.getLastName());

        Person resultLazy = this.people.asLazy()
                .detect(person -> person.named("Mary Smith"));
        assertEquals("Mary", resultLazy.getFirstName());
        assertEquals("Smith", resultLazy.getLastName());

        Person resultLazyMR = this.people.asLazy()
                .detectWith(Person::named, "Mary Smith");
        assertEquals("Mary", resultLazyMR.getFirstName());
        assertEquals("Smith", resultLazyMR.getLastName());
    }

    @Test
    public void findPersonNamedMarySmithUsingStreams()
    {
        Person resultStream = this.people.stream()
                .filter(person -> person.named("Mary Smith"))
                .findFirst()
                .orElse(null);
        assertEquals("Mary", resultStream.getFirstName());
        assertEquals("Smith", resultStream.getLastName());
    }

    @Test
    public void getTheNamesOfBobSmithPets()
    {
        Person personEager = this.people.detectWith(Person::named, "Bob Smith");
        MutableList<String> names = personEager.getPets().collect(Pet::getName);

        MutableList<String> expected = Lists.mutable.with("Dolly", "Spot");
        assertEquals(expected, names);
        assertEquals("Dolly & Spot", names.makeString(" & "));
    }

    @Test
    public void getTheNamesOfBobSmithPetsUsingStreams()
    {
        Person personStream = this.people.stream()
                .filter(each -> each.named("Bob Smith"))
                .findFirst()
                .orElse(null);
        List<String> names = personStream.getPets().stream()
                .map(Pet::getName)
                .collect(Collectors.toList());
        assertEquals(Lists.mutable.with("Dolly", "Spot"), names);
        assertEquals("Dolly & Spot", names.stream().collect(Collectors.joining(" & ")));
    }

    @Test
    public void getAllPetTypes()
    {
        MutableSet<PetType> allPetTypesEager =
                this.people.flatCollect(Person::getPetTypes).toSet();

        MutableSet<PetType> expected = Sets.mutable.with(PetType.values());
        assertEquals(expected, allPetTypesEager);

        MutableSet<PetType> allPetTypesEagerTarget =
                this.people.flatCollect(Person::getPetTypes, Sets.mutable.empty());

        assertEquals(expected, allPetTypesEagerTarget);

        MutableSet<PetType> allPetTypesLazy = this.people.asLazy()
                .flatCollect(Person::getPetTypes).toSet();

        assertEquals(expected, allPetTypesLazy);

        MutableSet<PetType> allPetTypesLazyTarget = this.people.asLazy()
                .flatCollect(Person::getPetTypes, Sets.mutable.empty());

        assertEquals(expected, allPetTypesLazyTarget);
    }

    @Test
    public void getAllPetTypesUsingStreams()
    {
        Set<PetType> allPetTypesStream = this.people.stream()
                .flatMap(person -> person.getPetTypes().stream())
                .collect(Collectors.toSet());
        assertEquals(
                new HashSet<>(Arrays.asList(PetType.values())),
                allPetTypesStream);
    }

    @Test
    public void groupPeopleByLastName()
    {
        MutableListMultimap<String, Person> byLastNameEager =
                this.people.groupBy(Person::getLastName);
        Verify.assertIterableSize(3, byLastNameEager.get("Smith"));

        MutableBagMultimap<String, Person> byLastNameEagerTargetBag =
                this.people.groupBy(Person::getLastName, Multimaps.mutable.bag.empty());
        Verify.assertIterableSize(3, byLastNameEagerTargetBag.get("Smith"));

        Multimap<String, Person> byLastNameLazy = this.people.asLazy()
                .groupBy(Person::getLastName);
        Verify.assertIterableSize(3, byLastNameLazy.get("Smith"));

        MutableBagMultimap<String, Person> byLastNameLazyTargetBag = this.people.asLazy()
                .groupBy(Person::getLastName, Multimaps.mutable.bag.empty());
        Verify.assertIterableSize(3, byLastNameLazyTargetBag.get("Smith"));
    }

    @Test
    public void groupPeopleByLastNameUsingStreams()
    {
        Map<String, List<Person>> byLastNameStream = this.people.stream()
                .collect(Collectors.groupingBy(Person::getLastName));
        Verify.assertIterableSize(3, byLastNameStream.get("Smith"));

        Map<String, MutableBag<Person>> byLastNameStreamTargetBag = this.people.stream()
                .collect(Collectors.groupingBy(
                        Person::getLastName,
                        Collectors.toCollection(Bags.mutable::empty)));
        Verify.assertIterableSize(3, byLastNameStreamTargetBag.get("Smith"));
    }

    @Test
    public void groupPeopleByTheirPets()
    {
        Multimap<PetType, Person> peopleByPetsEager =
                this.people.groupByEach(Person::getPetTypes);
        RichIterable<Person> catPeople = peopleByPetsEager.get(PetType.CAT);
        assertEquals(
                "Mary, Bob",
                catPeople.collect(Person::getFirstName).makeString());
        RichIterable<Person> dogPeople = peopleByPetsEager.get(PetType.DOG);
        assertEquals(
                "Bob, Ted",
                dogPeople.collect(Person::getFirstName).makeString());
    }

    @Test
    public void groupPeopleByTheirPetsUsingStreams()
    {
        Map<PetType, List<Person>> peopleByPetsStream = new HashMap<>();
        this.people.stream().forEach(
                person -> person.getPetTypes().stream().forEach(
                        petType -> peopleByPetsStream.computeIfAbsent(petType, e -> new ArrayList<>()).add(person)));
        List<Person> catPeople = peopleByPetsStream.get(PetType.CAT);
        assertEquals(
                "Mary, Bob",
                catPeople.stream().map(Person::getFirstName).collect(Collectors.joining(", ")));
        List<Person> dogPeople = peopleByPetsStream.get(PetType.DOG);
        assertEquals(
                "Bob, Ted",
                dogPeople.stream().map(Person::getFirstName).collect(Collectors.joining(", ")));
    }

    @Test
    public void getTotalNumberOfPets()
    {
        long numberOfPetsEager = this.people.sumOfInt(Person::getNumberOfPets);
        assertEquals(9, numberOfPetsEager);

        long numberOfPetsLazy = this.people.asLazy()
                .sumOfInt(Person::getNumberOfPets);
        assertEquals(9, numberOfPetsLazy);
    }

    @Test
    public void getTotalNumberOfPetsUsingStreams()
    {
        int numberOfPetsStream = this.people.stream()
                .mapToInt(Person::getNumberOfPets)
                .sum();
        assertEquals(9, numberOfPetsStream);
    }

    @Test
    public void testStrings()
    {
        assertEquals(
                "HELLO",
                "h1e2l3l4o"
                        .chars()
                        .filter(Character::isLetter)
                        .map(Character::toUpperCase)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
        assertEquals(
                "HELLO",
                "h1e2l3l4o"
                        .codePoints()
                        .filter(Character::isLetter)
                        .map(Character::toUpperCase)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
        assertEquals(
                "HELLO",
                StringIterate.asCharAdapter("h1e2l3l4o")
                        .select(Character::isLetter)
                        .collectChar(Character::toUpperCase)
                        .toString());
        assertEquals(
                "HELLO",
                StringIterate.asCodePointAdapter("h1e2l3l4o")
                        .select(Character::isLetter)
                        .collectInt(Character::toUpperCase)
                        .toString());
    }

    @Test
    public void getAgeStatisticsOfPets()
    {
        IntList ages = this.people.flatCollectInt(Person::getPetAges, IntLists.mutable.empty());
        IntSet uniqueAges = ages.toSet();
        IntSummaryStatistics stats = ages.summaryStatistics();
        assertEquals(IntSets.mutable.with(1, 2, 3, 4), uniqueAges);
        assertEquals(stats.getMin(), ages.minIfEmpty(0));
        assertEquals(stats.getMax(), ages.maxIfEmpty(0));
        assertEquals(stats.getSum(), ages.sum());
        assertEquals(stats.getAverage(), ages.averageIfEmpty(0.0), 0.0);
        assertEquals(stats.getCount(), ages.size());
        assertTrue(ages.allSatisfy(IntPredicates.greaterThan(0)));
        assertTrue(ages.allSatisfy(i -> i > 0));
        assertFalse(ages.anySatisfy(IntPredicates.equal(0)));
        assertFalse(ages.anySatisfy(i -> i == 0));
        assertTrue(ages.noneSatisfy(IntPredicates.lessThan(0)));
        assertTrue(ages.noneSatisfy(i -> i < 0));
        assertEquals(2.0d, ages.median(), 0.0);
    }

    @Test
    public void getAgeStatisticsOfPetsUsingStreams()
    {
        List<Integer> agesStream = this.people.stream()
                .flatMap(person -> person.getPets().stream())
                .map(Pet::getAge)
                .collect(Collectors.toList());
        Set<Integer> uniqueAges = new HashSet<>(agesStream);
        IntSummaryStatistics stats = agesStream.stream()
                .collect(Collectors.summarizingInt(i -> i));
        assertEquals(Sets.mutable.with(1, 2, 3, 4), uniqueAges);
        assertEquals(stats.getMin(), agesStream.stream().mapToInt(i -> i).min().orElse(0));
        assertEquals(stats.getMax(), agesStream.stream().mapToInt(i -> i).max().orElse(0));
        assertEquals(stats.getSum(), agesStream.stream().mapToInt(i -> i).sum());
        assertEquals(stats.getAverage(), agesStream.stream().mapToInt(i -> i).average().orElse(0.0), 0.0);
        assertEquals(stats.getCount(), agesStream.size());
        assertTrue(agesStream.stream().allMatch(i -> i > 0));
        assertFalse(agesStream.stream().anyMatch(i -> i == 0));
        assertTrue(agesStream.stream().noneMatch(i -> i < 0));
    }

    @Test
    public void getCountsByPetType()
    {
        Bag<PetType> counts = this.people.countByEach(Person::getPetTypes)
                .toImmutable();
        assertEquals(2, counts.occurrencesOf(PetType.CAT));
        assertEquals(2, counts.occurrencesOf(PetType.DOG));
        assertEquals(2, counts.occurrencesOf(PetType.HAMSTER));
        assertEquals(1, counts.occurrencesOf(PetType.SNAKE));
        assertEquals(1, counts.occurrencesOf(PetType.TURTLE));
        assertEquals(1, counts.occurrencesOf(PetType.BIRD));
    }

    @Test
    public void getCountsByPetTypeUsingStreams()
    {
        Map<PetType, Long> countsStream =
                Collections.unmodifiableMap(
                        this.people.stream()
                                .flatMap(person -> person.getPets().stream())
                                .collect(Collectors.groupingBy(
                                        Pet::getType,
                                        Collectors.counting())));
        assertEquals(Long.valueOf(2L), countsStream.get(PetType.CAT));
        assertEquals(Long.valueOf(2L), countsStream.get(PetType.DOG));
        assertEquals(Long.valueOf(2L), countsStream.get(PetType.HAMSTER));
        assertEquals(Long.valueOf(1L), countsStream.get(PetType.SNAKE));
        assertEquals(Long.valueOf(1L), countsStream.get(PetType.TURTLE));
        assertEquals(Long.valueOf(1L), countsStream.get(PetType.BIRD));
    }

    @Test
    public void getTop3Pets()
    {
        ListIterable<ObjectIntPair<PetType>> top3 = this.people.countByEach(Person::getPetTypes)
                .topOccurrences(3);
        Verify.assertSize(3, top3);
        assertTrue(top3.containsAllArguments(
                PrimitiveTuples.pair(PetType.CAT, 2),
                PrimitiveTuples.pair(PetType.DOG, 2),
                PrimitiveTuples.pair(PetType.HAMSTER, 2)));
    }

    @Test
    public void getTop3PetsUsingStreams()
    {
        List<Map.Entry<PetType, Long>> favoritesStream =
                this.people.stream()
                        .flatMap(p -> p.getPets().stream())
                        .collect(Collectors.groupingBy(Pet::getType, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparingLong(e -> -e.getValue()))
                        .limit(3)
                        .collect(Collectors.toList());
        Verify.assertSize(3, favoritesStream);
        Verify.assertContains(new AbstractMap.SimpleEntry<>(PetType.CAT, Long.valueOf(2)), favoritesStream);
        Verify.assertContains(new AbstractMap.SimpleEntry<>(PetType.DOG, Long.valueOf(2)), favoritesStream);
        Verify.assertContains(new AbstractMap.SimpleEntry<>(PetType.HAMSTER, Long.valueOf(2)), favoritesStream);
    }

    @Test
    public void getBottom3Pets()
    {
        MutableList<ObjectIntPair<PetType>> leastFavoritesLazy = this.people.asLazy()
                .flatCollect(Person::getPets)
                .collect(Pet::getType)
                .toBag()
                .bottomOccurrences(3);
        Verify.assertSize(3, leastFavoritesLazy);
        Verify.assertContains(PrimitiveTuples.pair(PetType.SNAKE, 1), leastFavoritesLazy);
        Verify.assertContains(PrimitiveTuples.pair(PetType.TURTLE, 1), leastFavoritesLazy);
        Verify.assertContains(PrimitiveTuples.pair(PetType.BIRD, 1), leastFavoritesLazy);
    }

    @Test
    public void getCountsByPetAge()
    {
        ImmutableIntBag countsLazy =
                this.people.flatCollectInt(Person::getPetAges, new IntHashBag())
                        .toImmutable();
        assertEquals(4, countsLazy.occurrencesOf(1));
        assertEquals(3, countsLazy.occurrencesOf(2));
        assertEquals(1, countsLazy.occurrencesOf(3));
        assertEquals(1, countsLazy.occurrencesOf(4));
        assertEquals(0, countsLazy.occurrencesOf(5));
    }

    @Test
    public void getCountsByPetAgeUsingStreams()
    {
        Map<Integer, Long> countsStream =
                Collections.unmodifiableMap(
                        this.people.stream()
                                .flatMap(person -> person.getPets().stream())
                                .collect(Collectors.groupingBy(
                                        Pet::getAge,
                                        Collectors.counting())));
        assertEquals(Long.valueOf(4), countsStream.get(1));
        assertEquals(Long.valueOf(3), countsStream.get(2));
        assertEquals(Long.valueOf(1), countsStream.get(3));
        assertEquals(Long.valueOf(1), countsStream.get(4));
        assertNull(countsStream.get(5));
    }

    public static final class Person
    {
        private final String firstName;
        private final String lastName;
        private final MutableList<Pet> pets = FastList.newList();

        private Person(String firstName, String lastName)
        {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName()
        {
            return this.firstName;
        }

        public String getLastName()
        {
            return this.lastName;
        }

        public boolean named(String name)
        {
            return name.equals(this.firstName + ' ' + this.lastName);
        }

        public boolean hasPet(PetType petType)
        {
            return this.pets.anySatisfyWith(Predicates2.attributeEqual(Pet::getType), petType);
        }

        public MutableList<Pet> getPets()
        {
            return this.pets;
        }

        public MutableBag<PetType> getPetTypes()
        {
            return this.pets.countBy(Pet::getType);
        }

        public Person addPet(PetType petType, String name, int age)
        {
            this.pets.add(new Pet(petType, name, age));
            return this;
        }

        public int getNumberOfPets()
        {
            return this.pets.size();
        }

        public IntIterable getPetAges()
        {
            return this.getPets().asLazy().collectInt(Pet::getAge);
        }
    }

    public static class Pet
    {
        private final PetType type;
        private final String name;
        private final int age;

        public Pet(PetType type, String name, int age)
        {
            this.type = type;
            this.name = name;
            this.age = age;
        }

        public PetType getType()
        {
            return this.type;
        }

        public String getName()
        {
            return this.name;
        }

        public int getAge()
        {
            return this.age;
        }
    }

    public enum PetType
    {
        CAT, DOG, HAMSTER, TURTLE, BIRD, SNAKE
    }
}
