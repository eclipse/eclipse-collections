/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.set;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.collections.api.set.FixedSizeSet;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class FixedSizeSetsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(UnifiedSet.newSet(), FixedSizeSet.of());
        Assert.assertEquals(UnifiedSet.newSet(), FixedSizeSet.empty());
        Verify.assertInstanceOf(FixedSizeSet.class, FixedSizeSet.of());
        Verify.assertInstanceOf(FixedSizeSet.class, FixedSizeSet.empty());
        Assert.assertEquals(UnifiedSet.newSetWith(1), FixedSizeSet.of(1));
        Verify.assertInstanceOf(FixedSizeSet.class, FixedSizeSet.of(1));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2), FixedSizeSet.of(1, 2));
        Verify.assertInstanceOf(FixedSizeSet.class, FixedSizeSet.of(1, 2));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3), FixedSizeSet.of(1, 2, 3));
        Verify.assertInstanceOf(FixedSizeSet.class, FixedSizeSet.of(1, 2, 3));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), FixedSizeSet.of(1, 2, 3, 4));
        Verify.assertInstanceOf(FixedSizeSet.class, FixedSizeSet.of(1, 2, 3, 4));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), FixedSizeSet.ofAll(Sets.mutable.of(1, 2, 3, 4)));
        Verify.assertInstanceOf(FixedSizeSet.class, FixedSizeSet.ofAll(Sets.mutable.of(1, 2, 3, 4)));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), FixedSizeSet.fromStream(Stream.of(1, 2, 3, 4)));
        Verify.assertInstanceOf(FixedSizeSet.class, FixedSizeSet.fromStream(Stream.of(1, 2, 3, 4)));
    }

    @Test
    public void ofAll()
    {
        for (int i = 1; i <= 5; i++)
        {
            Interval interval = Interval.oneTo(i);
            Verify.assertEqualsAndHashCode(UnifiedSet.newSet(interval), FixedSizeSet.ofAll(interval));
            Stream<Integer> stream = IntStream.rangeClosed(1, i).boxed();
            Verify.assertEqualsAndHashCode(UnifiedSet.newSet(interval), FixedSizeSet.fromStream(stream));
        }
    }

    @Test
    public void emptyMap()
    {
        Assert.assertTrue(FixedSizeSet.of().isEmpty());
        Assert.assertSame(FixedSizeSet.of(), FixedSizeSet.of());
        Verify.assertPostSerializedIdentity(FixedSizeSet.of());
    }
}
