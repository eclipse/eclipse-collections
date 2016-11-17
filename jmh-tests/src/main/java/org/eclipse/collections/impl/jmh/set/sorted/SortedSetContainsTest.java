/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.jmh.set.sorted;

import java.util.concurrent.TimeUnit;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.list.Interval;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
public class SortedSetContainsTest
{
    private static final int SIZE = 2_000_000;

    private final MutableSortedSet<Integer> ecMutable = SortedSets.mutable.withAll(Interval.zeroToBy(SIZE, 2));
    private final ImmutableSortedSet<Integer> ecImmutable = SortedSets.immutable.withAll(Interval.zeroToBy(SIZE, 2));

    @Benchmark
    public void contains_mutable_ec()
    {
        int size = SIZE;
        MutableSortedSet<Integer> localEcMutable = this.ecMutable;

        for (int i = 0; i < size; i += 2)
        {
            if (!localEcMutable.contains(i))
            {
                throw new AssertionError(i);
            }
        }

        for (int i = 1; i < size; i += 2)
        {
            if (localEcMutable.contains(i))
            {
                throw new AssertionError(i);
            }
        }
    }

    @Benchmark
    public void contains_immutable_ec()
    {
        int size = SIZE;
        ImmutableSortedSet<Integer> localEcImmutable = this.ecImmutable;

        for (int i = 0; i < size; i += 2)
        {
            if (!localEcImmutable.contains(i))
            {
                throw new AssertionError(i);
            }
        }

        for (int i = 1; i < size; i += 2)
        {
            if (localEcImmutable.contains(i))
            {
                throw new AssertionError(i);
            }
        }
    }

    @Benchmark
    public void contains_mutable_scala()
    {
        SortedSetContainsScalaTest.contains_mutable_scala();
    }

    @Benchmark
    public void contains_immutable_scala()
    {
        SortedSetContainsScalaTest.contains_immutable_scala();
    }
}
