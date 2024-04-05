/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list;

/**
 * This is a utility class for common behaviors between Interval and IntInterval. It is
 * a public class only because Interval and IntInterval are in different packages.
 */
public final class IntervalUtils
{
    private IntervalUtils()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static void checkArguments(long from, long to, long stepBy)
    {
        IntervalUtils.checkStepBy(from, to, stepBy);
        IntervalUtils.checkSize(from, to, stepBy);
    }

    private static void checkSize(long from, long to, long stepBy)
    {
        long rangeSize = (to - from) / stepBy + 1L;
        if ((long) Integer.MAX_VALUE < rangeSize)
        {
            throw new IllegalArgumentException("Range size: "
                    + rangeSize
                    + " exceeds max size() of "
                    + Integer.MAX_VALUE);
        }
    }

    private static void checkStepBy(long from, long to, long stepBy)
    {
        if (stepBy == 0L)
        {
            throw new IllegalArgumentException("Cannot use a step by of 0");
        }
        if (from > to && stepBy > 0L || from < to && stepBy < 0L)
        {
            throw new IllegalArgumentException("Step by is incorrect for the range");
        }
    }

    public static int intSize(long from, long to, long step)
    {
        long result = (to - from) / step + 1L;
        return (int) result;
    }

    public static boolean contains(long value, long from, long to, long step)
    {
        return IntervalUtils.isWithinBoundaries(value, from, to, step)
                && (value - from) % step == 0L;
    }

    public static boolean isWithinBoundaries(long value, long from, long to, long step)
    {
        return step > 0L && from <= value && value <= to
                || step < 0L && to <= value && value <= from;
    }

    public static int indexOf(long value, long from, long to, long step)
    {
        if (!IntervalUtils.isWithinBoundaries(value, from, to, step))
        {
            return -1;
        }
        long diff = value - from;
        if (diff % step == 0L)
        {
            return (int) (diff / step);
        }
        return -1;
    }

    public static long valueAtIndex(int index, long from, long to, long step)
    {
        if (index <= 0)
        {
            return from;
        }
        long value = from + step * (long) index;
        if (step > 0L)
        {
            return Math.min(value, to);
        }
        return Math.max(value, to);
    }

    public static int binarySearch(long value, long from, long to, long step)
    {
        if (step > 0L && from > value || step < 0L && from < value)
        {
            return -1;
        }

        if (step > 0L && to < value || step < 0 && to > value)
        {
            return -1 - IntervalUtils.intSize(from, to, step);
        }

        long diff = value - from;
        int index = (int) (diff / step);
        return diff % step == 0L ? index : (index + 2) * -1;
    }

    public static int calculateAdjustedStep(int from, int to, int stepBy)
    {
        int direction = Integer.signum(to - from);
        return direction == 0 ? stepBy : direction * stepBy;
    }
}

