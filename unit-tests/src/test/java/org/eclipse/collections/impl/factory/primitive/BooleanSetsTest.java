/*
 * Copyright (c) 2024 Goldman Sachs and others.
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
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.factory.set.primitive.ImmutableBooleanSetFactory;
import org.eclipse.collections.api.factory.set.primitive.MutableBooleanSetFactory;
import org.eclipse.collections.api.set.primitive.ImmutableBooleanSet;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.api.tuple.primitive.BooleanBooleanPair;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class BooleanSetsTest
{
    @Test
    public void immutables()
    {
        this.assertImmutableSetFactory(BooleanSets.immutable);
        this.assertImmutableSetFactory(org.eclipse.collections.api.factory.primitive.BooleanSets.immutable);
    }

    private void assertImmutableSetFactory(ImmutableBooleanSetFactory setFactory)
    {
        assertEquals(new BooleanHashSet(), setFactory.with());
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with());
        assertEquals(BooleanHashSet.newSetWith(true), setFactory.with(true));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true));
        assertEquals(BooleanHashSet.newSetWith(true, false), setFactory.with(true, false));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false));
        assertEquals(BooleanHashSet.newSetWith(true, false, true), setFactory.with(true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false), setFactory.with(true, false, true, false));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true), setFactory.with(true, false, true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false, true));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false), setFactory.with(true, false, true, false, true, false));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false, true, false));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false, true), setFactory.with(true, false, true, false, true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false, true, false, true));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false, true, true), setFactory.with(true, false, true, false, true, false, true, true));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false, true, false, true, true));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false, true, true, true), setFactory.with(true, false, true, false, true, false, true, true, true));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false, true, false, true, true, true));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false, true, true, true, false), setFactory.with(true, false, true, false, true, false, true, true, true, false));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.with(true, false, true, false, true, false, true, true, true, false));
        assertEquals(BooleanHashSet.newSetWith(true, false, true), setFactory.withAll(BooleanHashSet.newSetWith(true, false, true)));
        Verify.assertInstanceOf(ImmutableBooleanSet.class, setFactory.withAll(BooleanHashSet.newSetWith(true, false, true)));
    }

    @Test
    public void mutables()
    {
        this.assertMutableSetFactory(BooleanSets.mutable);
        this.assertMutableSetFactory(org.eclipse.collections.api.factory.primitive.BooleanSets.mutable);
    }

    private void assertMutableSetFactory(MutableBooleanSetFactory setFactory)
    {
        assertEquals(new BooleanHashSet(), setFactory.with());
        Verify.assertInstanceOf(MutableBooleanSet.class, setFactory.with());
        assertEquals(BooleanHashSet.newSetWith(true), setFactory.with(true));
        Verify.assertInstanceOf(MutableBooleanSet.class, setFactory.with(true));
        assertEquals(BooleanHashSet.newSetWith(true, false), setFactory.with(true, false));
        Verify.assertInstanceOf(MutableBooleanSet.class, setFactory.with(true, false));
        assertEquals(BooleanHashSet.newSetWith(true, false, true), setFactory.with(true, false, true));
        Verify.assertInstanceOf(MutableBooleanSet.class, setFactory.with(true, false, true));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false), setFactory.with(true, false, true, false));
        Verify.assertInstanceOf(MutableBooleanSet.class, setFactory.with(true, false, true, false));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true), setFactory.with(true, false, true, false, true));
        Verify.assertInstanceOf(MutableBooleanSet.class, setFactory.with(true, false, true, false, true));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false), setFactory.with(true, false, true, false, true, false));
        Verify.assertInstanceOf(MutableBooleanSet.class, setFactory.with(true, false, true, false, true, false));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false, true), setFactory.with(true, false, true, false, true, false, true));
        Verify.assertInstanceOf(MutableBooleanSet.class, setFactory.with(true, false, true, false, true, false, true));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false, true, true), setFactory.with(true, false, true, false, true, false, true, true));
        Verify.assertInstanceOf(MutableBooleanSet.class, setFactory.with(true, false, true, false, true, false, true, true));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false, true, true, true), setFactory.with(true, false, true, false, true, false, true, true, true));
        Verify.assertInstanceOf(MutableBooleanSet.class, setFactory.with(true, false, true, false, true, false, true, true, true));
        assertEquals(BooleanHashSet.newSetWith(true, false, true, false, true, false, true, true, true, false), setFactory.with(true, false, true, false, true, false, true, true, true, false));
        Verify.assertInstanceOf(MutableBooleanSet.class, setFactory.with(true, false, true, false, true, false, true, true, true, false));
        assertEquals(BooleanHashSet.newSetWith(true, false, true), setFactory.withAll(BooleanHashSet.newSetWith(true, false, true)));
        Verify.assertInstanceOf(MutableBooleanSet.class, setFactory.withAll(BooleanHashSet.newSetWith(true, false, true)));
    }

    @Test
    public void emptySet()
    {
        Verify.assertEmpty(BooleanSets.immutable.with());
        assertSame(BooleanSets.immutable.with(), BooleanSets.immutable.with());
        Verify.assertPostSerializedIdentity(BooleanSets.immutable.with());
    }

    @Test
    public void newSetWith()
    {
        ImmutableBooleanSet set = BooleanSets.immutable.with();
        assertEquals(set, BooleanSets.immutable.with(set.toArray()));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true));
        assertEquals(set = set.newWith(false), BooleanSets.immutable.with(true, false));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true, false, true));
        assertEquals(set = set.newWith(false), BooleanSets.immutable.with(true, false, true, false));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true, false, true, false, true));
        assertEquals(set = set.newWith(false), BooleanSets.immutable.with(true, false, true, false, true, false));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true, false, true, false, true, false, true));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true, false, true, false, true, false, true, true));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true, false, true, false, true, false, true, true, true));
        assertEquals(set = set.newWith(false), BooleanSets.immutable.with(true, false, true, false, true, false, true, true, true, false));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(true, false, true, false, true, false, true, true, true, false, true));
        assertEquals(set = set.newWith(false), BooleanSets.immutable.with(true, false, true, false, true, false, true, true, true, false, true, false));
    }

    @SuppressWarnings("RedundantArrayCreation")
    @Test
    public void newSetWithArray()
    {
        ImmutableBooleanSet set = BooleanSets.immutable.with();
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true}));
        assertEquals(set = set.newWith(false), BooleanSets.immutable.with(new boolean[]{true, false}));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true, false, true}));
        assertEquals(set = set.newWith(false), BooleanSets.immutable.with(new boolean[]{true, false, true, false}));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true}));
        assertEquals(set = set.newWith(false), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true, false}));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true, false, true}));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true, false, true, true}));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true, false, true, true, true}));
        assertEquals(set = set.newWith(false), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true, false, true, true, true, false}));
        assertEquals(set = set.newWith(true), BooleanSets.immutable.with(new boolean[]{true, false, true, false, true, false, true, true, true, false, true}));
    }

    @Test
    public void newSetWithSet()
    {
        ImmutableBooleanSet set = BooleanSets.immutable.with();
        BooleanHashSet booleanHashSet = BooleanHashSet.newSetWith(true);
        assertEquals(set = set.newWith(true), booleanHashSet.toImmutable());
        assertEquals(set = set.newWith(false), booleanHashSet.with(false).toImmutable());
        assertEquals(set = set.newWith(true), booleanHashSet.with(true).toImmutable());
        assertEquals(set = set.newWith(false), booleanHashSet.with(false).toImmutable());
        assertEquals(set = set.newWith(true), booleanHashSet.with(true).toImmutable());
        assertEquals(set = set.newWith(false), booleanHashSet.with(false).toImmutable());
        assertEquals(set = set.newWith(true), booleanHashSet.with(true).toImmutable());
        assertEquals(set = set.newWith(true), booleanHashSet.with(true).toImmutable());
        assertEquals(set = set.newWith(true), booleanHashSet.with(true).toImmutable());
        assertEquals(set = set.newWith(false), booleanHashSet.with(false).toImmutable());
        assertEquals(set = set.newWith(true), booleanHashSet.with(true).toImmutable());
    }

    @Test
    public void newSetWithWithSet()
    {
        assertEquals(new BooleanHashSet(), BooleanSets.immutable.withAll(new BooleanHashSet()));
        assertEquals(BooleanHashSet.newSetWith(true), BooleanSets.immutable.withAll(BooleanHashSet.newSetWith(true)));
        assertEquals(BooleanHashSet.newSetWith(true, false), BooleanSets.immutable.withAll(BooleanHashSet.newSetWith(true, false)));
        assertEquals(BooleanHashSet.newSetWith(true, false, true), BooleanSets.immutable.withAll(BooleanHashSet.newSetWith(true, false, true)));
    }

    @Test
    public void ofAllBooleanIterable()
    {
        assertEquals(new BooleanHashSet(), BooleanSets.immutable.ofAll(BooleanLists.mutable.empty()));
        assertEquals(BooleanHashSet.newSetWith(true), BooleanSets.immutable.ofAll(BooleanLists.mutable.with(true)));
        assertEquals(BooleanHashSet.newSetWith(true, false), BooleanSets.immutable.ofAll(BooleanLists.mutable.with(true, false)));
        assertEquals(BooleanHashSet.newSetWith(true, false, true), BooleanSets.immutable.ofAll(BooleanLists.mutable.with(true, false, true)));

        assertEquals(new BooleanHashSet(), BooleanSets.mutable.ofAll(BooleanLists.mutable.empty()));
        assertEquals(BooleanHashSet.newSetWith(true), BooleanSets.mutable.ofAll(BooleanLists.mutable.with(true)));
        assertEquals(BooleanHashSet.newSetWith(true, false), BooleanSets.mutable.ofAll(BooleanLists.mutable.with(true, false)));
        assertEquals(BooleanHashSet.newSetWith(true, false, true), BooleanSets.mutable.ofAll(BooleanLists.mutable.with(true, false, true)));
    }

    @Test
    public void ofAllIterable()
    {
        assertEquals(new BooleanHashSet(), BooleanSets.immutable.ofAll(Lists.mutable.empty()));
        assertEquals(BooleanHashSet.newSetWith(true), BooleanSets.immutable.ofAll(Lists.mutable.with(true)));
        assertEquals(BooleanHashSet.newSetWith(true, false), BooleanSets.immutable.ofAll(Lists.mutable.with(true, false)));
        assertEquals(BooleanHashSet.newSetWith(true, false, true), BooleanSets.immutable.ofAll(Lists.mutable.with(true, false, true)));

        assertEquals(new BooleanHashSet(), BooleanSets.mutable.ofAll(Lists.mutable.empty()));
        assertEquals(BooleanHashSet.newSetWith(true), BooleanSets.mutable.ofAll(Lists.mutable.with(true)));
        assertEquals(BooleanHashSet.newSetWith(true, false), BooleanSets.mutable.ofAll(Lists.mutable.with(true, false)));
        assertEquals(BooleanHashSet.newSetWith(true, false, true), BooleanSets.mutable.ofAll(Lists.mutable.with(true, false, true)));
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
                PrimitiveTuples.pair(false, false));

        assertEquals(expected, booleanBooleanPairs.toSet());
    }
}
