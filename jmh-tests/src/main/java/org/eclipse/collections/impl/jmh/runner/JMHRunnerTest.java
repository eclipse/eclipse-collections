/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.jmh.runner;

import org.junit.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.VerboseMode;

public class JMHRunnerTest
{
    public static void main(String[] args)
    {
        JMHRunnerTest.runAllJMHBenchmarks(VerboseMode.NORMAL);
    }

    private static void runAllJMHBenchmarks(VerboseMode mode)
    {
        runBenchmarks(mode, ".*org.eclipse.collections.impl.jmh.*");
    }

    private static void runBenchmarks(VerboseMode mode, String include)
    {
        Options opts = new OptionsBuilder()
                .include(include)
                .verbosity(mode)
                .forks(1)
                .warmupTime(TimeValue.seconds(2))
                .warmupIterations(5)
                .measurementTime(TimeValue.seconds(2))
                .measurementIterations(5)
                .build();
        try
        {
            new Runner(opts).run();
        }
        catch (RunnerException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void runAllBenchmarks()
    {
        JMHRunnerTest.runAllJMHBenchmarks(VerboseMode.NORMAL);
    }

    @Test
    public void runMapBenchmarks()
    {
        JMHRunnerTest.runBenchmarks(VerboseMode.NORMAL, ".*org.eclipse.collections.impl.jmh.map.*");
    }
}
