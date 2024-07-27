/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.primitive;

import org.eclipse.collections.api.bag.primitive.ImmutableBooleanBag;
import org.eclipse.collections.api.factory.bag.primitive.ImmutableBooleanBagFactory;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class BooleanBagsTest
{
    @Test
    public void immutables()
    {
        ImmutableBooleanBagFactory bagFactory = BooleanBags.immutable;
        assertEquals(new BooleanHashBag(), bagFactory.of());
        Verify.assertInstanceOf(ImmutableBooleanBag.class, bagFactory.of());
        assertEquals(BooleanHashBag.newBagWith(true), bagFactory.of(true));
        Verify.assertInstanceOf(ImmutableBooleanBag.class, bagFactory.of(true));
        assertEquals(BooleanHashBag.newBagWith(true, false), bagFactory.of(true, false));
        Verify.assertInstanceOf(ImmutableBooleanBag.class, bagFactory.of(true, false));
        assertEquals(BooleanHashBag.newBagWith(true, false, true), bagFactory.of(true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanBag.class, bagFactory.of(true, false, true));
        assertEquals(BooleanHashBag.newBagWith(true, false, true, false), bagFactory.of(true, false, true, false));
        Verify.assertInstanceOf(ImmutableBooleanBag.class, bagFactory.of(true, false, true, false));
        assertEquals(BooleanHashBag.newBagWith(true, false, true, false, true), bagFactory.of(true, false, true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanBag.class, bagFactory.of(true, false, true, false, true));
        assertEquals(BooleanHashBag.newBagWith(true, false, true, false, true, false), bagFactory.of(true, false, true, false, true, false));
        Verify.assertInstanceOf(ImmutableBooleanBag.class, bagFactory.of(true, false, true, false, true, false));
        assertEquals(BooleanHashBag.newBagWith(true, false, true, false, true, false, true), bagFactory.of(true, false, true, false, true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanBag.class, bagFactory.of(true, false, true, false, true, false, true));
        assertEquals(BooleanHashBag.newBagWith(true, false, true, false, true, false, true, true), bagFactory.of(true, false, true, false, true, false, true, true));
        Verify.assertInstanceOf(ImmutableBooleanBag.class, bagFactory.of(true, false, true, false, true, false, true, true));
        assertEquals(BooleanHashBag.newBagWith(true, false, true, false, true, false, true, true, true), bagFactory.of(true, false, true, false, true, false, true, true, true));
        Verify.assertInstanceOf(ImmutableBooleanBag.class, bagFactory.of(true, false, true, false, true, false, true, true, true));
        assertEquals(BooleanHashBag.newBagWith(true, false, true, false, true, false, true, true, true, false), bagFactory.of(true, false, true, false, true, false, true, true, true, false));
        Verify.assertInstanceOf(ImmutableBooleanBag.class, bagFactory.of(true, false, true, false, true, false, true, true, true, false));
        assertEquals(BooleanHashBag.newBagWith(true, false, true), bagFactory.ofAll(BooleanHashBag.newBagWith(true, false, true)));
        Verify.assertInstanceOf(ImmutableBooleanBag.class, bagFactory.ofAll(BooleanHashBag.newBagWith(true, false, true)));
    }

    @Test
    public void emptyBag()
    {
        Verify.assertEmpty(BooleanBags.immutable.of());
        assertSame(BooleanBags.immutable.of(), BooleanBags.immutable.of());
        Verify.assertPostSerializedIdentity(BooleanBags.immutable.of());
    }

    @Test
    public void newBagWith()
    {
        ImmutableBooleanBag bag = BooleanBags.immutable.of();
        assertEquals(bag, BooleanBags.immutable.of(bag.toArray()));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(true));
        assertEquals(bag = bag.newWith(false), BooleanBags.immutable.of(true, false));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(true, false, true));
        assertEquals(bag = bag.newWith(false), BooleanBags.immutable.of(true, false, true, false));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(true, false, true, false, true));
        assertEquals(bag = bag.newWith(false), BooleanBags.immutable.of(true, false, true, false, true, false));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(true, false, true, false, true, false, true));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(true, false, true, false, true, false, true, true));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(true, false, true, false, true, false, true, true, true));
        assertEquals(bag = bag.newWith(false), BooleanBags.immutable.of(true, false, true, false, true, false, true, true, true, false));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(true, false, true, false, true, false, true, true, true, false, true));
        assertEquals(bag = bag.newWith(false), BooleanBags.immutable.of(true, false, true, false, true, false, true, true, true, false, true, false));
    }

    @SuppressWarnings("RedundantArrayCreation")
    @Test
    public void newBagWithArray()
    {
        ImmutableBooleanBag bag = BooleanBags.immutable.of();
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(new boolean[]{true}));
        assertEquals(bag = bag.newWith(false), BooleanBags.immutable.of(new boolean[]{true, false}));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(new boolean[]{true, false, true}));
        assertEquals(bag = bag.newWith(false), BooleanBags.immutable.of(new boolean[]{true, false, true, false}));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(new boolean[]{true, false, true, false, true}));
        assertEquals(bag = bag.newWith(false), BooleanBags.immutable.of(new boolean[]{true, false, true, false, true, false}));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(new boolean[]{true, false, true, false, true, false, true}));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(new boolean[]{true, false, true, false, true, false, true, true}));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(new boolean[]{true, false, true, false, true, false, true, true, true}));
        assertEquals(bag = bag.newWith(false), BooleanBags.immutable.of(new boolean[]{true, false, true, false, true, false, true, true, true, false}));
        assertEquals(bag = bag.newWith(true), BooleanBags.immutable.of(new boolean[]{true, false, true, false, true, false, true, true, true, false, true}));
    }

    @Test
    public void newBagWithBag()
    {
        ImmutableBooleanBag bag = BooleanBags.immutable.of();
        BooleanHashBag booleanHashBag = BooleanHashBag.newBagWith(true);
        assertEquals(bag = bag.newWith(true), booleanHashBag.toImmutable());
        assertEquals(bag = bag.newWith(false), booleanHashBag.with(false).toImmutable());
        assertEquals(bag = bag.newWith(true), booleanHashBag.with(true).toImmutable());
        assertEquals(bag = bag.newWith(false), booleanHashBag.with(false).toImmutable());
        assertEquals(bag = bag.newWith(true), booleanHashBag.with(true).toImmutable());
        assertEquals(bag = bag.newWith(false), booleanHashBag.with(false).toImmutable());
        assertEquals(bag = bag.newWith(true), booleanHashBag.with(true).toImmutable());
        assertEquals(bag = bag.newWith(true), booleanHashBag.with(true).toImmutable());
        assertEquals(bag = bag.newWith(true), booleanHashBag.with(true).toImmutable());
        assertEquals(bag = bag.newWith(false), booleanHashBag.with(false).toImmutable());
        assertEquals(bag = bag.newWith(true), booleanHashBag.with(true).toImmutable());
    }

    @Test
    public void newBagWithWithBag()
    {
        assertEquals(new BooleanHashBag(), BooleanBags.immutable.ofAll(new BooleanHashBag()));
        assertEquals(BooleanHashBag.newBagWith(true), BooleanBags.immutable.ofAll(BooleanHashBag.newBagWith(true)));
        assertEquals(BooleanHashBag.newBagWith(true, false), BooleanBags.immutable.ofAll(BooleanHashBag.newBagWith(true, false)));
        assertEquals(BooleanHashBag.newBagWith(true, false, true), BooleanBags.immutable.ofAll(BooleanHashBag.newBagWith(true, false, true)));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(BooleanBags.class);
    }
}
