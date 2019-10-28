/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.collection.mutable.AbstractMultiReaderMutableCollection;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.junit.Assert;
import org.junit.Test;

public abstract class MultiReaderThreadSafetyTestCase
{
    protected abstract AbstractMultiReaderMutableCollection<Integer> getClassUnderTest();

    protected abstract Thread createReadLockHolderThread(Gate gate);

    protected abstract Thread createWriteLockHolderThread(Gate gate);

    protected void sleep(Gate gate)
    {
        gate.open();
        try
        {
            Thread.sleep(Long.MAX_VALUE);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            // Interruption is normal in these tests
        }
    }

    public Thread spawn(Runnable code)
    {
        Thread result = new Thread(code);
        result.start();
        return result;
    }

    public long time(Callable<?> code)
    {
        long before = System.currentTimeMillis();
        try
        {
            code.call();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        long after = System.currentTimeMillis();
        return after - before;
    }

    public void assertThat(boolean readersBlocked, boolean writersBlocked, Runnable code)
    {
        if (readersBlocked)
        {
            this.assertReadersBlocked(code);
        }
        else
        {
            this.assertReadersNotBlocked(code);
        }

        if (writersBlocked)
        {
            this.assertWritersBlocked(code);
        }
        else
        {
            this.assertWritersNotBlocked(code);
        }
    }

    public void assertReadersBlocked(Runnable code)
    {
        this.assertReadSafety(true, 10L, TimeUnit.MILLISECONDS, code);
    }

    public void assertReadersNotBlocked(Runnable code)
    {
        this.assertReadSafety(false, 60L, TimeUnit.SECONDS, code);
    }

    public void assertWritersBlocked(Runnable code)
    {
        this.assertWriteSafety(true, 10L, TimeUnit.MILLISECONDS, code);
    }

    public void assertWritersNotBlocked(Runnable code)
    {
        this.assertWriteSafety(false, 60L, TimeUnit.SECONDS, code);
    }

    public void assertReadSafety(boolean threadSafe, long timeout, TimeUnit timeUnit, Runnable code)
    {
        Gate gate = new Gate();
        this.assertThreadSafety(timeout, timeUnit, gate, code, threadSafe, this.createReadLockHolderThread(gate));
    }

    public void assertWriteSafety(boolean threadSafe, long timeout, TimeUnit timeUnit, Runnable code)
    {
        Gate gate = new Gate();
        this.assertThreadSafety(timeout, timeUnit, gate, code, threadSafe, this.createWriteLockHolderThread(gate));
    }

    public void assertThreadSafety(
            long timeout,
            TimeUnit timeUnit,
            Gate gate,
            Runnable code,
            boolean threadSafe,
            Thread lockHolderThread)
    {
        long millisTimeout = TimeUnit.MILLISECONDS.convert(timeout, timeUnit);
        long measuredTime = this.time(() ->
        {
            // Don't start until the other thread is synchronized on getClassUnderTest()
            gate.await();
            Thread thread = this.spawn(code);
            thread.join(millisTimeout, 0);
            return null;
        });

        Assert.assertEquals(
                "Measured " + measuredTime + " ms but timeout was " + millisTimeout + " ms.",
                threadSafe,
                measuredTime >= millisTimeout);

        lockHolderThread.interrupt();
        try
        {
            lockHolderThread.join();
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    protected static final class Gate
    {
        private final CountDownLatch latch = new CountDownLatch(1);

        public void open()
        {
            this.latch.countDown();
        }

        public void await()
        {
            try
            {
                this.latch.await();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void newEmpty_safe()
    {
        this.assertThat(
                false,
                false,
                () -> this.getClassUnderTest().newEmpty());
    }

    @Test
    public void iterator_safe()
    {
        Runnable runnable = () ->
        {
            try
            {
                this.getClassUnderTest().iterator();
            }
            catch (UnsupportedOperationException e)
            {
                throw new RuntimeException(e);
            }
        };
        this.assertThat(
                false,
                false,
                runnable);
    }

    @Test
    public void add_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().add(4));
    }

    @Test
    public void addAll_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().addAll(Lists.mutable.empty()));
    }

    @Test
    public void addAllIterable_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().addAllIterable(Lists.mutable.empty()));
    }

    @Test
    public void remove_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().remove(1));
    }

    @Test
    public void removeAll_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().removeAll(Lists.mutable.empty()));
    }

    @Test
    public void removeAllIterable_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().removeAllIterable(Lists.mutable.empty()));
    }

    @Test
    public void retainAll_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().retainAll(Lists.mutable.empty()));
    }

    @Test
    public void retainAllIterable_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().retainAllIterable(Lists.mutable.empty()));
    }

    @Test
    public void removeIf_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().removeIf(each -> true));
    }

    @Test
    public void removeIfWith_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().removeIfWith((first, second) -> true, 0));
    }

    @Test
    public void with_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().with(4));
    }

    @Test
    public void without_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().without(1));
    }

    @Test
    public void withAll_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().withAll(Lists.mutable.empty()));
    }

    @Test
    public void withoutAll_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().withoutAll(Lists.mutable.empty()));
    }

    @Test
    public void clear_safe()
    {
        this.assertThat(
                true,
                true,
                () -> this.getClassUnderTest().clear());
    }

    @Test
    public void size_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().size());
    }

    @Test
    public void getFirst_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().getFirst());
    }

    @Test
    public void getLast_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().getLast());
    }

    @Test
    public void isEmpty_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().isEmpty());
    }

    @Test
    public void notEmpty_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().notEmpty());
    }

    @Test
    public void contains_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().contains(1));
    }

    @Test
    public void containsAll_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().containsAll(Lists.mutable.empty()));
    }

    @Test
    public void containsAllIterable_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().containsAll(Lists.mutable.empty()));
    }

    @Test
    public void containsAllArguments_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().containsAllArguments("1", "2"));
    }

    @Test
    public void equals_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().equals(null));
    }

    @Test
    public void hashCode_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().hashCode());
    }

    @Test
    public void forEach_safe()
    {
        Procedure<Integer> noop = each ->
        {
        };
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().forEach(noop));
    }

    @Test
    public void forEachWith_safe()
    {
        Procedure2<Integer, Integer> noop = (first, second) ->
        {
        };

        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().forEachWith(noop, 0));
    }

    @Test
    public void collect_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().collect(each -> ""));
    }

    @Test
    public void collect_withTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().collect(each -> "", Lists.mutable.empty()));
    }

    @Test
    public void collectWith_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().collectWith((each, parameter) -> "", ""));
    }

    @Test
    public void collectWith_withTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().collectWith((each, parameter) -> "", "", Lists.mutable.empty()));
    }

    @Test
    public void flatCollect_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().flatCollect(each -> Lists.mutable.empty()));
    }

    @Test
    public void flatCollect_withTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().flatCollect(each -> Lists.mutable.empty(), Lists.mutable.empty()));
    }

    @Test
    public void collectIf_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().collectIf(each -> true, each -> ""));
    }

    @Test
    public void collectIf_withTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().collectIf(each -> true, each -> "", Lists.mutable.empty()));
    }

    @Test
    public void select_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().select(each -> true));
    }

    @Test
    public void select_withTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().select(each -> true, Lists.mutable.empty()));
    }

    @Test
    public void selectWith_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().selectWith((first, second) -> true, 1));
    }

    @Test
    public void selectWith_withTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().selectWith((first, second) -> true, 1, Lists.mutable.empty()));
    }

    @Test
    public void reject_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().reject(each -> true));
    }

    @Test
    public void reject_withTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().reject(each -> true, Lists.mutable.empty()));
    }

    @Test
    public void rejectWith_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().rejectWith((first, second) -> true, 1));
    }

    @Test
    public void rejectWith_withTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().rejectWith((first, second) -> true, 1, Lists.mutable.empty()));
    }

    @Test
    public void selectInstancesOf_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().selectInstancesOf(Integer.class));
    }

    @Test
    public void partition_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().partition(each -> true));
    }

    @Test
    public void partitionWith_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().partitionWith((first, second) -> true, 1));
    }

    @Test
    public void selectAndRejectWith_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().selectAndRejectWith((first, second) -> true, 1));
    }

    @Test
    public void count_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().count(each -> true));
    }

    @Test
    public void countWith_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().countWith((first, second) -> true, 1));
    }

    @Test
    public void min_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().min());
    }

    @Test
    public void max_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().max());
    }

    @Test
    public void min_withComparator_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().min((first, second) -> 0));
    }

    @Test
    public void max_withComparator_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().max((first, second) -> 0));
    }

    @Test
    public void minBy_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().minBy(each -> ""));
    }

    @Test
    public void maxBy_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().maxBy(each -> ""));
    }

    @Test
    public void injectInto_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().injectInto(Integer.valueOf(0), (first, second) -> 0));
    }

    @Test
    public void injectIntoWith_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().injectIntoWith(0, (accumulator, each, parameter) -> 0, 0));
    }

    @Test
    public void sumOfInt_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().sumOfInt(each -> 0));
    }

    @Test
    public void sumOfLong_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().sumOfLong(each -> 0L));
    }

    @Test
    public void sumOfDouble_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().sumOfDouble(each -> 0.0));
    }

    @Test
    public void sumOfFloat_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().sumOfFloat(each -> 0.0f));
    }

    @Test
    public void toString_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toString());
    }

    @Test
    public void makeString_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().makeString());
    }

    @Test
    public void makeString_withSeparator_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().makeString(", "));
    }

    @Test
    public void makeString_withStartEndSeparator_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().makeString("[", ", ", "]"));
    }

    @Test
    public void appendString_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().appendString(new StringBuilder()));
    }

    @Test
    public void appendString_withSeparator_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().appendString(new StringBuilder(), ", "));
    }

    @Test
    public void appendString_withStartEndSeparator_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().appendString(new StringBuilder(), "[", ", ", "]"));
    }

    @Test
    public void groupBy_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().groupBy(each -> ""));
    }

    @Test
    public void groupBy_withTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().groupBy(each -> "", Multimaps.mutable.list.empty()));
    }

    @Test
    public void groupByEach_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().groupByEach(each -> Lists.mutable.empty()));
    }

    @Test
    public void groupByEach_withTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this
                        .getClassUnderTest()
                        .groupByEach(each -> Lists.mutable.empty(), Multimaps.mutable.list.empty()));
    }

    @Test
    public void aggregateBy_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().aggregateBy(each -> "", () -> 0, (first, second) -> 0));
    }

    @Test
    public void aggregateInPlaceBy_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().aggregateInPlaceBy(each -> "", () -> 0, (first, second) ->
                {
                }));
    }

    @Test
    public void zip_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().zip(FastList.newListWith("1", "1", "2")));
    }

    @Test
    public void zip_withTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().zip(FastList.newListWith("1", "1", "2"), Lists.mutable.empty()));
    }

    @Test
    public void zipByIndex_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().zipWithIndex());
    }

    @Test
    public void zipByIndex_withTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().zipWithIndex(Lists.mutable.empty()));
    }

    @Test
    public void chunk_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().chunk(2));
    }

    @Test
    public void anySatisfy_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().anySatisfy(each -> true));
    }

    @Test
    public void anySatisfyWith_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().anySatisfyWith((first, second) -> true, 1));
    }

    @Test
    public void allSatisfy_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().allSatisfy(each -> true));
    }

    @Test
    public void allSatisfyWith_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().allSatisfyWith((first, second) -> true, 1));
    }

    @Test
    public void noneSatisfy_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().noneSatisfy(each -> true));
    }

    @Test
    public void noneSatisfyWith_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().noneSatisfyWith((first, second) -> true, 1));
    }

    @Test
    public void detect_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().detect(each -> true));
    }

    @Test
    public void detectIfNone_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().detectIfNone(each -> true, () -> 1));
    }

    @Test
    public void detectWith_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().detectWith((first, second) -> true, 1));
    }

    @Test
    public void detectWithIfNone_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().detectWithIfNone((first, second) -> true, 1, () -> 1));
    }

    @Test
    public void asLazy_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().asLazy());
    }

    @Test
    public void asUnmodifiable_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().asUnmodifiable());
    }

    @Test
    public void asSynchronized_safe()
    {
        {
            this.assertThat(
                    false,
                    true,
                    () -> this.getClassUnderTest().asSynchronized());
        }
    }

    @Test
    public void toImmutable_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toImmutable());
    }

    @Test
    public void toList_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toList());
    }

    @Test
    public void toSortedList_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toSortedList());
    }

    @Test
    public void toSortedList_withComparator_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toSortedList((first, second) -> 0));
    }

    @Test
    public void toSortedListBy_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toSortedListBy(each -> ""));
    }

    @Test
    public void toSet_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toSet());
    }

    @Test
    public void toSortedSet_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toSortedSet());
    }

    @Test
    public void toSortedSet_withComparator_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toSortedSet((first, second) -> 0));
    }

    @Test
    public void toSortedSetBy_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toSortedSetBy(each -> ""));
    }

    @Test
    public void toBag_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toBag());
    }

    @Test
    public void toSortedBag_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toSortedBag());
    }

    @Test
    public void toSortedBag_withComparator_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toSortedBag((first, second) -> 0));
    }

    @Test
    public void toSortedBagBy_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toSortedBagBy(each -> ""));
    }

    @Test
    public void toMap_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toMap(each -> 0, each -> 0));
    }

    @Test
    public void toSortedMap_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toSortedMap(each -> 0, each -> 0));
    }

    @Test
    public void toSortedMap_withComparator_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toSortedMap((first, second) -> 0, each -> 0, each -> 0));
    }

    @Test
    public void toSortedMap_withFunction_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().<Integer, Integer, Integer>toSortedMapBy(
                        each -> Integer.valueOf(0),
                        each -> 0,
                        each -> 0));
    }

    @Test
    public void toArray_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toArray());
    }

    @Test
    public void toArrayWithTarget_safe()
    {
        this.assertThat(
                false,
                true,
                () -> this.getClassUnderTest().toArray(new Integer[10]));
    }
}
