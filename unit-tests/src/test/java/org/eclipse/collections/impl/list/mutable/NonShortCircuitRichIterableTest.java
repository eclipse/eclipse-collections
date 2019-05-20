/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class NonShortCircuitRichIterableTest
{
    private AtomicInteger incrementIfEven(AtomicInteger i)
    {
        if ((i.get() & 1) == 0)
        {
            i.getAndIncrement();
        }
        return i;
    }

    private boolean trueIfEvenAndIncrement(AtomicInteger i)
    {
        if ((i.get() & 1) == 0)
        {
            incrementIfEven(i);
            return true;
        }
        return false;
    }

    @Test
    public void testAnySatisfy()
    {
        RichIterable<AtomicInteger> expectedIterable = Interval.oneTo(10).collect(AtomicInteger::new).collect(this::incrementIfEven).toList();
        RichIterable<AtomicInteger> actualIterable = Interval.oneTo(10).collect(AtomicInteger::new).toList();

        boolean result = actualIterable.asNonShortCircuit().anySatisfy(each -> trueIfEvenAndIncrement(each));

        Verify.assertTrue(result);
        Verify.assertEquals(expectedIterable.collect(AtomicInteger::get), actualIterable.collect(AtomicInteger::get));
    }

    @Test
    public void testAnySatisfy_returnsFalse()
    {
        RichIterable<AtomicInteger> expectedIterable = Interval.oddsFromTo(1, 9).collect(AtomicInteger::new).collect(this::incrementIfEven).toList();
        RichIterable<AtomicInteger> actualIterable = Interval.oddsFromTo(1, 9).collect(AtomicInteger::new).toList();

        boolean result = actualIterable.asNonShortCircuit().anySatisfy(each -> trueIfEvenAndIncrement(each));

        Verify.assertFalse(result);
        Verify.assertEquals(expectedIterable.collect(AtomicInteger::get), actualIterable.collect(AtomicInteger::get));
    }

    @Test
    public void testAllSatisfy()
    {
        RichIterable<AtomicInteger> expectedIterable = Interval.evensFromTo(2, 10).collect(AtomicInteger::new).collect(this::incrementIfEven).toList();
        RichIterable<AtomicInteger> actualIterable = Interval.evensFromTo(2, 10).collect(AtomicInteger::new).toList();

        boolean result = actualIterable.asNonShortCircuit().allSatisfy(each -> trueIfEvenAndIncrement(each));

        Verify.assertTrue(result);
        Verify.assertEquals(expectedIterable.collect(AtomicInteger::get), actualIterable.collect(AtomicInteger::get));
    }

    @Test
    public void testAllSatisfy_returnsFalse()
    {
        RichIterable<AtomicInteger> expectedIterable = Interval.oneTo(10).collect(AtomicInteger::new).collect(this::incrementIfEven).toList();
        RichIterable<AtomicInteger> actualIterable = Interval.oneTo(10).collect(AtomicInteger::new).toList();

        boolean result = actualIterable.asNonShortCircuit().allSatisfy(each -> trueIfEvenAndIncrement(each));

        Verify.assertFalse(result);
        Verify.assertEquals(expectedIterable.collect(AtomicInteger::get), actualIterable.collect(AtomicInteger::get));
    }

    @Test
    public void testNoneSatisfy()
    {
        RichIterable<AtomicInteger> expectedIterable = Interval.oddsFromTo(1, 9).collect(AtomicInteger::new).collect(this::incrementIfEven).toList();
        RichIterable<AtomicInteger> actualIterable = Interval.oddsFromTo(1, 9).collect(AtomicInteger::new).toList();

        boolean result = actualIterable.asNonShortCircuit().noneSatisfy(each -> trueIfEvenAndIncrement(each));

        Verify.assertTrue(result);
        Verify.assertEquals(expectedIterable.collect(AtomicInteger::get), actualIterable.collect(AtomicInteger::get));
    }

    @Test
    public void testNoneSatisfy_returnsFalse()
    {
        RichIterable<AtomicInteger> expectedIterable = Interval.oneTo(10).collect(AtomicInteger::new).collect(this::incrementIfEven).toList();
        RichIterable<AtomicInteger> actualIterable = Interval.oneTo(10).collect(AtomicInteger::new).toList();

        boolean result = actualIterable.asNonShortCircuit().noneSatisfy(each -> trueIfEvenAndIncrement(each));

        Verify.assertFalse(result);
        Verify.assertEquals(expectedIterable.collect(AtomicInteger::get), actualIterable.collect(AtomicInteger::get));
    }

    @Test
    public void testDetect()
    {
        RichIterable<AtomicInteger> expectedIterable = Interval.oneTo(10).collect(AtomicInteger::new).collect(this::incrementIfEven).toList();
        RichIterable<AtomicInteger> actualIterable = Interval.oneTo(10).collect(AtomicInteger::new).toList();

        AtomicInteger result = actualIterable.asNonShortCircuit().detect(each -> trueIfEvenAndIncrement(each));

        Verify.assertEquals(3, result.get());
        Verify.assertEquals(expectedIterable.collect(AtomicInteger::get), actualIterable.collect(AtomicInteger::get));
    }

    @Test
    public void testDetect_returnsNull()
    {
        RichIterable<AtomicInteger> expectedIterable = Interval.oddsFromTo(1, 9).collect(AtomicInteger::new).collect(this::incrementIfEven).toList();
        RichIterable<AtomicInteger> actualIterable = Interval.oddsFromTo(1, 9).collect(AtomicInteger::new).toList();

        AtomicInteger actual = actualIterable.asNonShortCircuit().detect(each -> trueIfEvenAndIncrement(each));

        Verify.assertNull(actual);
        Verify.assertEquals(expectedIterable.collect(AtomicInteger::get), actualIterable.collect(AtomicInteger::get));
    }

    @Test
    public void testDetectIfNone()
    {
        RichIterable<AtomicInteger> expectedIterable = Interval.oneTo(10).collect(AtomicInteger::new).collect(this::incrementIfEven).toList();
        RichIterable<AtomicInteger> actualIterable = Interval.oneTo(10).collect(AtomicInteger::new).toList();

        AtomicInteger actual = actualIterable.asNonShortCircuit().detectIfNone(each -> trueIfEvenAndIncrement(each), () -> new AtomicInteger(-1));

        Verify.assertEquals(3, actual.get());
        Verify.assertEquals(expectedIterable.collect(AtomicInteger::get), actualIterable.collect(AtomicInteger::get));
    }

    @Test
    public void testDetectIfNone_returnsDefault()
    {
        RichIterable<AtomicInteger> expectedIterable = Interval.oddsFromTo(1, 9).collect(AtomicInteger::new).collect(this::incrementIfEven).toList();
        RichIterable<AtomicInteger> actualIterable = Interval.oddsFromTo(1, 9).collect(AtomicInteger::new).toList();

        AtomicInteger actual = actualIterable.asNonShortCircuit().detectIfNone(each -> trueIfEvenAndIncrement(each), () -> new AtomicInteger(-1));

        Verify.assertEquals(-1, actual.get());
        Verify.assertEquals(expectedIterable.collect(AtomicInteger::get), actualIterable.collect(AtomicInteger::get));
    }
}
