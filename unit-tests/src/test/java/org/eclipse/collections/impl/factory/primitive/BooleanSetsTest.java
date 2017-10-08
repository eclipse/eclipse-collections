/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.primitive;

import java.util.Set;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.factory.set.primitive.ImmutableBooleanSetFactory;
import org.eclipse.collections.api.set.primitive.ImmutableBooleanSet;
import org.eclipse.collections.api.tuple.primitive.BooleanBooleanPair;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

public class BooleanSetsTest
{
    @Test
    public void immutables()
    {
        ImmutableBooleanSetFactory setFactory = BooleanSets.immutable;
        Assert.assertEquals(new BooleanHashSet(), setFactory.with());
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with());
        Assert.assertEquals(BooleanHashSet.newSetWith(true), setFactory.with(true));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), setFactory.with(true, false));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false, true), setFactory.with(true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false, true, false), setFactory.with(true, false, true, false));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true), setFactory.with(true, false, true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false, true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false), setFactory.with(true, false, true, false, true, false));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false, true, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false, true), setFactory.with(true, false, true, false, true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false, true, false, true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false, true, true), setFactory.with(true, false, true, false, true, false, true, true));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false, true, false, true, true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false, true, true, true), setFactory.with(true, false, true, false, true, false, true, true, true));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false, true, false, true, true, true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false, true, true, true, false), setFactory.with(true, false, true, false, true, false, true, true, true, false));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false, true, false, true, true, true, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false, true), setFactory.withAll(BooleanHashSet.newSetWith(true, false, true)));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.withAll(BooleanHashSet.newSetWith(true, false, true)));
    }

    @Test
    public void emptySet()
    {
        Verify.assertEmpty(BooleanSets.immutable.with());
        Assert.assertSame(BooleanSets.immutable.with(), BooleanSets.immutable.with());
        Verify.assertPostSerializedIdentity(BooleanSets.immutable.with());
    }

    @Test
    public void newSetWith()
    {
        ImmutableBooleanSet set = BooleanSets.immutable.with();
        Assert.assertEquals(set, BooleanSets.immutable.with(set.toArray()));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true));
        Assert.assertEquals(set = set.newWith(false), BooleanSets.immutable.with(true, false));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true, false, true));
        Assert.assertEquals(set = set.newWith(false), BooleanSets.immutable.with(true, false, true, false));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true, false, true, false, true));
        Assert.assertEquals(set = set.newWith(false), BooleanSets.immutable.with(true, false, true, false, true, false));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true, false, true, false, true, false, true));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true, false, true, false, true, false, true, true));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true, false, true, false, true, false, true, true, true));
        Assert.assertEquals(set = set.newWith(false), BooleanSets.immutable.with(true, false, true, false, true, false, true, true, true, false));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true, false, true, false, true, false, true, true, true, false, true));
        Assert.assertEquals(set = set.newWith(false), BooleanSets.immutable.with(true, false, true, false, true, false, true, true, true, false, true, false));
    }

    @SuppressWarnings("RedundantArrayCreation")
    @Test
    public void newSetWithArray()
    {
        ImmutableBooleanSet set = BooleanSets.immutable.with();
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true}));
        Assert.assertEquals(set = set.newWith(false), BooleanSets.immutable.with(new boolean[]{true, false}));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true, false, true}));
        Assert.assertEquals(set = set.newWith(false), BooleanSets.immutable.with(new boolean[]{true, false, true, false}));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true}));
        Assert.assertEquals(set = set.newWith(false), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true, false}));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true, false, true}));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true, false, true, true}));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true, false, true, true, true}));
        Assert.assertEquals(set = set.newWith(false), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true, false, true, true, true, false}));
        Assert.assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true, false, true, true, true, false, true}));
    }

    @Test
    public void newSetWithSet()
    {
        ImmutableBooleanSet set = BooleanSets.immutable.with();
        BooleanHashSet booleanHashSet = BooleanHashSet.newSetWith(true);
        Assert.assertEquals(set = set.newWith(true), booleanHashSet.toImmutable());
        Assert.assertEquals(set = set.newWith(false), booleanHashSet.with(false).toImmutable());
        Assert.assertEquals(set = set.newWith(true), booleanHashSet.with(true).toImmutable());
        Assert.assertEquals(set = set.newWith(false), booleanHashSet.with(false).toImmutable());
        Assert.assertEquals(set = set.newWith(true), booleanHashSet.with(true).toImmutable());
        Assert.assertEquals(set = set.newWith(false), booleanHashSet.with(false).toImmutable());
        Assert.assertEquals(set = set.newWith(true), booleanHashSet.with(true).toImmutable());
        Assert.assertEquals(set = set.newWith(true), booleanHashSet.with(true).toImmutable());
        Assert.assertEquals(set = set.newWith(true), booleanHashSet.with(true).toImmutable());
        Assert.assertEquals(set = set.newWith(false), booleanHashSet.with(false).toImmutable());
        Assert.assertEquals(set = set.newWith(true), booleanHashSet.with(true).toImmutable());
    }

    @Test
    public void newSetWithWithSet()
    {
        Assert.assertEquals(new BooleanHashSet(), BooleanSets.immutable.withAll(new BooleanHashSet()));
        Assert.assertEquals(BooleanHashSet.newSetWith(true), BooleanSets.immutable.withAll(BooleanHashSet.newSetWith(true)));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), BooleanSets.immutable.withAll(BooleanHashSet.newSetWith(true, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false, true), BooleanSets.immutable.withAll(BooleanHashSet.newSetWith(true, false, true)));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(BooleanSets.class);
    }

    @Test
    public void cartesianProduct()
    {
        LazyIterable<BooleanBooleanPair> booleanBooleanPairs =
                BooleanSets.cartesianProduct(
                        BooleanSets.mutable.with(true, false),
                        BooleanSets.mutable.with(true, false));

        Set<BooleanBooleanPair> expected = Sets.mutable.with(
                PrimitiveTuples.pair(true, false),
                PrimitiveTuples.pair(true, true),
                PrimitiveTuples.pair(false, true),
                PrimitiveTuples.pair(false, false)
        );

        Assert.assertEquals(expected, booleanBooleanPairs.toSet());
    }
}
