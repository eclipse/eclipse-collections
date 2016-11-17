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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.block.factory.ObjectIntProcedures;
import org.eclipse.collections.impl.block.procedure.DoNothingProcedure;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Before;
import org.junit.Test;

public class ObjectIntProcedureFJTaskRunnerTest
{
    private ObjectIntProcedureFJTaskRunner<Integer, ObjectIntProcedure<Integer>> undertest;

    @Before
    public void setUp()
    {
        this.undertest = new ObjectIntProcedureFJTaskRunner<>(
                new DoNothingWithFalseCombineOneCombiner(),
                1,
                null,
                new MockLatch());
    }

    @Test
    public void taskCompletedUsingNonCombineOne()
    {
        Verify.assertThrows(CountDownCalledException.class, () -> this.undertest.taskCompleted(null));
    }

    @Test
    public void joinUsingNonCombineOne()
    {
        Verify.assertThrows(
                AwaitDownCalledException.class,
                () -> this.undertest.executeAndCombine(
                        new DoNothingExecutor(),
                        new PassThroughObjectIntProcedureFactory(),
                        FastList.newList()));
    }

    private static class DoNothingWithFalseCombineOneCombiner implements Combiner<ObjectIntProcedure<Integer>>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void combineAll(Iterable<ObjectIntProcedure<Integer>> thingsToCombine)
        {
        }

        @Override
        public void combineOne(ObjectIntProcedure<Integer> thingToCombine)
        {
        }

        @Override
        public boolean useCombineOne()
        {
            return false;
        }
    }

    private static class CountDownCalledException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
    }

    private static class AwaitDownCalledException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
    }

    private static final class MockLatch extends CountDownLatch
    {
        private MockLatch()
        {
            super(1);
        }

        @Override
        public void countDown()
        {
            throw new CountDownCalledException();
        }

        @Override
        public void await()
        {
            throw new AwaitDownCalledException();
        }
    }

    private static class DoNothingExecutor implements Executor
    {
        @Override
        public void execute(Runnable command)
        {
        }
    }

    private static class PassThroughObjectIntProcedureFactory implements ObjectIntProcedureFactory<ObjectIntProcedure<Integer>>
    {
        @Override
        public ObjectIntProcedure<Integer> create()
        {
            return this.getPassThroughObjectIntProcedure();
        }

        private ObjectIntProcedure<Integer> getPassThroughObjectIntProcedure()
        {
            return ObjectIntProcedures.fromProcedure(DoNothingProcedure.DO_NOTHING);
        }
    }
}
