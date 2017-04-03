/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.util.Arrays;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class tests various algorithms for calculating anagrams from a list of words.
 */
public class AnagramTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AnagramTest.class);

    private static final int SIZE_THRESHOLD = 10;

    private MutableList<String> getWords()
    {
        return FastList.newListWith(
                "alerts", "alters", "artels", "estral", "laster", "ratels", "salter", "slater", "staler", "stelar", "talers",
                "least", "setal", "slate", "stale", "steal", "stela", "taels", "tales", "teals", "tesla");
    }

    @Test
    public void anagramsWithMultimapInlined()
    {
        MutableList<RichIterable<String>> results = this.getWords()
                .groupBy(Alphagram::new)
                .multiValuesView()
                .select(iterable -> iterable.size() >= SIZE_THRESHOLD)
                .toSortedList(Functions.toIntComparator(RichIterable::size));
        results.asReversed()
                .collect(iterable -> iterable.size() + ": " + iterable)
                .each(LOGGER::info);
        Verify.assertIterableSize(SIZE_THRESHOLD, results.getFirst());
    }

    @Test
    public void anagramsWithMultimapEclipseCollections1()
    {
        MutableList<RichIterable<String>> results = this.getWords().groupBy(Alphagram::new)
                .multiValuesView()
                .select(iterable -> iterable.size() >= SIZE_THRESHOLD)
                .toSortedList(Functions.toIntComparator(iterable -> -iterable.size()));
        results.collect(iterable -> iterable.size() + ": " + iterable)
                .each(LOGGER::info);
        Verify.assertIterableSize(SIZE_THRESHOLD, results.getLast());
    }

    private boolean listContainsTestGroupAtElementsOneOrTwo(MutableList<MutableList<String>> list)
    {
        return list.get(1).containsAll(this.getTestAnagramGroup())
                || list.get(2).containsAll(this.getTestAnagramGroup());
    }

    @Test
    public void anagramsWithMultimapEclipseCollections3()
    {
        MutableList<RichIterable<String>> results = this.getWords().groupBy(Alphagram::new)
                .multiValuesView()
                .toSortedList(Functions.toIntComparator(iterable -> -iterable.size()));
        results.collectIf(iterable -> iterable.size() >= SIZE_THRESHOLD, iterable -> iterable.size() + ": " + iterable)
                .each(LOGGER::info);
        Verify.assertIterableSize(SIZE_THRESHOLD, results.getLast());
    }

    @Test
    public void anagramsWithMultimapEclipseCollections4()
    {
        MutableList<RichIterable<String>> results = this.getWords().groupBy(Alphagram::new)
                .multiValuesView()
                .toSortedList(Functions.toIntComparator(iterable -> -iterable.size()));
        results.forEach(
                Procedures.ifTrue(
                        iterable -> iterable.size() >= SIZE_THRESHOLD,
                        Functions.bind(Procedures.cast(LOGGER::info), iterable -> iterable.size() + ": " + iterable)));
        Verify.assertIterableSize(SIZE_THRESHOLD, results.getLast());
    }

    @Test
    public void anagramsWithMultimapLazyIterable1()
    {
        MutableList<RichIterable<String>> results = this.getWords().groupBy(Alphagram::new)
                .multiValuesView()
                .toSortedList(Functions.toIntComparator(RichIterable::size));
        results.asReversed()
                .collectIf(iterable -> iterable.size() >= SIZE_THRESHOLD, iterable -> iterable.size() + ": " + iterable)
                .forEach(Procedures.cast(LOGGER::info));
        Verify.assertIterableSize(SIZE_THRESHOLD, results.getFirst());
    }

    @Test
    public void anagramsWithMultimapForEachMultiValue()
    {
        MutableList<RichIterable<String>> results = Lists.mutable.of();
        this.getWords().groupBy(Alphagram::new)
                .multiValuesView().forEach(Procedures.ifTrue(iterable -> iterable.size() >= SIZE_THRESHOLD, results::add));
        Procedure<String> procedure = Procedures.cast(LOGGER::info);
        results.sortThisByInt(iterable -> -iterable.size())
                .forEach(Functions.bind(procedure, iterable -> iterable.size() + ": " + iterable));
        Verify.assertIterableSize(SIZE_THRESHOLD, results.getLast());
    }

    @Test
    public void anagramsUsingMapGetIfAbsentPutInsteadOfGroupBy()
    {
        MutableMap<Alphagram, MutableList<String>> map = UnifiedMap.newMap();
        this.getWords().each(word -> map.getIfAbsentPut(new Alphagram(word), FastList::new).add(word));
        MutableList<MutableList<String>> results =
                map.select(iterable -> iterable.size() >= SIZE_THRESHOLD, Lists.mutable.of())
                        .sortThisByInt(iterable -> -iterable.size());
        Procedure<String> procedure = Procedures.cast(LOGGER::info);
        results.forEach(Functions.bind(procedure, iterable -> iterable.size() + ": " + iterable));
        Assert.assertTrue(this.listContainsTestGroupAtElementsOneOrTwo(results));
        Verify.assertSize(SIZE_THRESHOLD, results.getLast());
    }

    private MutableList<String> getTestAnagramGroup()
    {
        return FastList.newListWith("least", "setal", "slate", "stale", "steal", "stela", "taels", "tales", "teals", "tesla");
    }

    private static final class Alphagram
    {
        private final char[] key;

        private Alphagram(String string)
        {
            this.key = string.toCharArray();
            Arrays.sort(this.key);
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || this.getClass() != o.getClass())
            {
                return false;
            }
            Alphagram alphagram = (Alphagram) o;
            return Arrays.equals(this.key, alphagram.key);
        }

        @Override
        public int hashCode()
        {
            return Arrays.hashCode(this.key);
        }

        @Override
        public String toString()
        {
            return new String(this.key);
        }
    }
}
