/*
 * Copyright (c) 2015 Goldman Sachs.
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

public abstract class AbstractJMHTestRunner
{
    @Test
    public void runTests() throws RunnerException
    {
        int warmupCount = this.warmUpCount();
        int runCount = this.runCount();
        Options opts = new OptionsBuilder()
                .include(".*" + this.getClass().getName() + ".*")
                .warmupTime(TimeValue.seconds(2))
                .warmupIterations(warmupCount)
                .measurementTime(TimeValue.seconds(2))
                .measurementIterations(runCount)
                .verbosity(VerboseMode.EXTRA)
                .forks(2)
                .build();

        new Runner(opts).run();
    }

    protected int runCount()
    {
        return 10;
    }

    protected int warmUpCount()
    {
        return 20;
    }
}
