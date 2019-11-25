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

import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableSetsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(UnifiedSet.newSet(), ImmutableSet.empty());
        Assert.assertEquals(UnifiedSet.newSet(), ImmutableSet.of());
        Verify.assertInstanceOf(ImmutableSet.class, ImmutableSet.of());
        Assert.assertEquals(UnifiedSet.newSetWith(1), ImmutableSet.of(1));
        Verify.assertInstanceOf(ImmutableSet.class, ImmutableSet.of(1));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2), ImmutableSet.of(1, 2));
        Verify.assertInstanceOf(ImmutableSet.class, ImmutableSet.of(1, 2));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3), ImmutableSet.of(1, 2, 3));
        Verify.assertInstanceOf(ImmutableSet.class, ImmutableSet.of(1, 2, 3));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), ImmutableSet.of(1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableSet.class, ImmutableSet.of(1, 2, 3, 4));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5), ImmutableSet.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(ImmutableSet.class, ImmutableSet.of(1, 2, 3, 4, 5));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5), ImmutableSet.ofAll(UnifiedSet.newSetWith(1, 2, 3, 4, 5)));
        Verify.assertInstanceOf(ImmutableSet.class, ImmutableSet.ofAll(UnifiedSet.newSetWith(1, 2, 3, 4, 5)));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5), ImmutableSet.fromStream(Stream.of(1, 2, 3, 4, 5)));
        Verify.assertInstanceOf(ImmutableSet.class, ImmutableSet.fromStream(Stream.of(1, 2, 3, 4, 5)));
    }

    @Test
    public void castToSet()
    {
        Set<Object> set = ImmutableSet.of().castToSet();
        Assert.assertNotNull(set);
        Assert.assertSame(ImmutableSet.of(), set);
    }

    @Test
    public void ofAll()
    {
        for (int i = 1; i <= 5; i++)
        {
            Interval interval = Interval.oneTo(i);
            Verify.assertEqualsAndHashCode(UnifiedSet.newSet(interval), ImmutableSet.ofAll(interval));
        }
    }

    @Test
    public void copySet()
    {
        Verify.assertInstanceOf(ImmutableSet.class, ImmutableSet.ofAll(Sets.fixedSize.of()));
        MutableSet<Integer> set = Sets.fixedSize.of(1);
        ImmutableSet<Integer> immutableSet = set.toImmutable();
        Verify.assertInstanceOf(ImmutableSet.class, ImmutableSet.ofAll(set));
        Assert.assertSame(ImmutableSet.ofAll(immutableSet.castToSet()), immutableSet);
    }

    @Test
    public void emptySet()
    {
        Assert.assertTrue(ImmutableSet.of().isEmpty());
        Assert.assertSame(ImmutableSet.of(), ImmutableSet.of());
        Verify.assertPostSerializedIdentity(ImmutableSet.of());
    }

    @Test
    public void newSetWith()
    {
        Assert.assertSame(ImmutableSet.empty(), ImmutableSet.of(ImmutableSet.empty().toArray()));
        Verify.assertSize(1, ImmutableSet.of(1).castToSet());
        Verify.assertSize(1, ImmutableSet.of(1, 1).castToSet());
        Verify.assertSize(1, ImmutableSet.of(1, 1, 1).castToSet());
        Verify.assertSize(1, ImmutableSet.of(1, 1, 1, 1).castToSet());
        Verify.assertSize(1, ImmutableSet.of(1, 1, 1, 1, 1).castToSet());
        Verify.assertSize(2, ImmutableSet.of(1, 1, 1, 1, 2).castToSet());
        Verify.assertSize(2, ImmutableSet.of(2, 1, 1, 1, 1).castToSet());
        Verify.assertSize(2, ImmutableSet.of(1, 2, 1, 1, 1).castToSet());
        Verify.assertSize(2, ImmutableSet.of(1, 1, 2, 1, 1).castToSet());
        Verify.assertSize(2, ImmutableSet.of(1, 1, 1, 2, 1).castToSet());
        Verify.assertSize(2, ImmutableSet.of(1, 1, 1, 2).castToSet());
        Verify.assertSize(2, ImmutableSet.of(2, 1, 1, 1).castToSet());
        Verify.assertSize(2, ImmutableSet.of(1, 2, 1, 1).castToSet());
        Verify.assertSize(2, ImmutableSet.of(1, 1, 2, 1).castToSet());
        Verify.assertSize(2, ImmutableSet.of(1, 1, 2).castToSet());
        Verify.assertSize(2, ImmutableSet.of(2, 1, 1).castToSet());
        Verify.assertSize(2, ImmutableSet.of(1, 2, 1).castToSet());
        Verify.assertSize(2, ImmutableSet.of(1, 2).castToSet());
        Verify.assertSize(3, ImmutableSet.of(1, 2, 3).castToSet());
        Verify.assertSize(3, ImmutableSet.of(1, 2, 3, 1).castToSet());
        Verify.assertSize(3, ImmutableSet.of(2, 1, 3, 1).castToSet());
        Verify.assertSize(3, ImmutableSet.of(2, 3, 1, 1).castToSet());
        Verify.assertSize(3, ImmutableSet.of(2, 1, 1, 3).castToSet());
        Verify.assertSize(3, ImmutableSet.of(1, 1, 2, 3).castToSet());
        Verify.assertSize(4, ImmutableSet.of(1, 2, 3, 4).castToSet());
        Verify.assertSize(4, ImmutableSet.of(1, 2, 3, 4, 1).castToSet());
    }
}
