/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable;

import java.util.Collection;

import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates;
import org.junit.Test;

public class MultiReaderHashBagThreadSafetyTest extends MultiReaderHashBagTestCase
{
    @Override
    protected MultiReaderHashBag<Integer> getClassUnderTest()
    {
        return MultiReaderHashBag.newBagWith(1, 1, 2);
    }

    @Test
    public void newBag_safe()
    {
        this.assertThat(false, false, MultiReaderHashBag::newBag);
    }

    @Test
    public void newBagCapacity_safe()
    {
        this.assertThat(false, false, () -> MultiReaderHashBag.newBag(5));
    }

    @Test
    public void newBagIterable_safe()
    {
        this.assertThat(false, false, () -> MultiReaderHashBag.newBag(Lists.mutable.with(1, 2)));
    }

    @Test
    public void newBagWith_safe()
    {
        this.assertThat(false, false, () -> MultiReaderHashBag.newBagWith(1, 2));
    }

    @Test
    public void addOccurrences_safe()
    {
        this.assertThat(true, true, () -> this.getClassUnderTest().addOccurrences(1, 2));
    }

    @Test
    public void removeOccurrences_safe()
    {
        this.assertThat(true, true, () -> this.getClassUnderTest().removeOccurrences(1, 1));
    }

    @Test
    public void occurrencesOf_safe()
    {
        this.assertThat(false, true, () -> this.getClassUnderTest().occurrencesOf(1));
    }

    @Test
    public void sizeDistinct_safe()
    {
        this.assertThat(false, true, () -> this.getClassUnderTest().sizeDistinct());
    }

    @Test
    public void toMapOfItemToCount_safe()
    {
        this.assertThat(false, true, () -> this.getClassUnderTest().toMapOfItemToCount());
    }

    @Test
    public void toStringOfItemToCount_safe()
    {
        this.assertThat(false, true, () -> this.getClassUnderTest().toStringOfItemToCount());
    }

    @Test
    public void iteratorWithReadLock_safe()
    {
        this.assertThat(false, true, () -> this.getClassUnderTest().withReadLockAndDelegate(Collection::iterator));
    }

    @Test
    public void iteratorWithWriteLock_safe()
    {
        this.assertThat(true, true, () -> this.getClassUnderTest().withWriteLockAndDelegate(Collection::iterator));
    }

    @Test
    public void forEachWithOccurrences_safe()
    {
        ObjectIntProcedure<Integer> noop = (each, occurrences) ->
        {
        };

        this.assertThat(false, true, () -> this.getClassUnderTest().forEachWithOccurrences(noop));
    }

    @Test
    public void selectByOccurrences_safe()
    {
        this.assertThat(false, true, () -> this.getClassUnderTest().selectByOccurrences(IntPredicates.isOdd()));
    }
}
