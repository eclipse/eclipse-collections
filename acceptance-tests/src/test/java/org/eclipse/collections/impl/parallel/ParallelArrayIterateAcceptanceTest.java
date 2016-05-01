/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import java.util.concurrent.ExecutorService;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.set.mutable.MultiReaderUnifiedSet;
import org.junit.Assert;
import org.junit.Test;

public class ParallelArrayIterateAcceptanceTest
{
    private int count = 0;
    private final MutableSet<String> threadNames = MultiReaderUnifiedSet.newSet();

    @Test
    public void oneLevelCall()
    {
        new RecursiveProcedure().value(1);

        synchronized (this)
        {
            Assert.assertEquals("all iterations completed", 20000, this.count);
        }
    }

    @Test
    public void nestedCall()
    {
        new RecursiveProcedure().value(2);

        synchronized (this)
        {
            Assert.assertEquals("all iterations completed", 419980, this.count);
        }
        Assert.assertTrue("uses multiple threads", this.threadNames.size() > 1);
    }

    private class RecursiveProcedure implements Procedure<Integer>
    {
        private static final long serialVersionUID = 1L;
        private final ExecutorService executorService = ParallelIterate.newPooledExecutor("ParallelArrayIterateAcceptanceTest", false);

        @Override
        public void value(Integer object)
        {
            int level = object.intValue();
            if (level > 0)
            {
                ParallelArrayIterateAcceptanceTest.this.threadNames.add(Thread.currentThread().getName());
                this.executeParallelIterate(level - 1, this.executorService);
            }
            else
            {
                this.simulateWork();
            }
        }

        private void simulateWork()
        {
            synchronized (ParallelArrayIterateAcceptanceTest.this)
            {
                ParallelArrayIterateAcceptanceTest.this.count++;
            }
        }

        private void executeParallelIterate(int level, ExecutorService executorService)
        {
            MutableList<Integer> items = Lists.mutable.of();
            for (int i = 0; i < 20000; i++)
            {
                items.add(i % 1000 == 0 ? level : 0);
            }
            ParallelIterate.forEach(items, new RecursiveProcedure(), executorService);
        }
    }
}
