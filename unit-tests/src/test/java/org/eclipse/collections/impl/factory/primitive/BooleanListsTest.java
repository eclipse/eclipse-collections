/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.primitive;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.list.primitive.ImmutableBooleanListFactory;
import org.eclipse.collections.api.factory.list.primitive.MutableBooleanListFactory;
import org.eclipse.collections.api.list.primitive.ImmutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class BooleanListsTest
{
    @Test
    public void mutables()
    {
        MutableBooleanListFactory listFactory = BooleanLists.mutable;
        assertEquals(new BooleanArrayList(), listFactory.empty());
        Verify.assertInstanceOf(MutableBooleanList.class, listFactory.empty());

        assertEquals(new BooleanArrayList(), listFactory.of());
        Verify.assertInstanceOf(MutableBooleanList.class, listFactory.of());

        assertEquals(new BooleanArrayList(), listFactory.with());
        Verify.assertInstanceOf(MutableBooleanList.class, listFactory.with());

        assertEquals(new BooleanArrayList(true, true, false), listFactory.with(true, true, false));
        assertEquals(new BooleanArrayList(false, true, false), listFactory.of(false, true, false));
        assertEquals(new BooleanArrayList(false, true, false), listFactory.wrapCopy(false, true, false));

        assertEquals(new BooleanArrayList(false, true, false),
                listFactory.ofAll(new BooleanArrayList(false, true, false)));
        assertEquals(new BooleanArrayList(false, true, false, true),
                listFactory.withAll(new BooleanArrayList(false, true, false, true)));

        assertEquals(new BooleanArrayList(false, true, false),
                listFactory.ofAll(Lists.mutable.of(false, true, false)));
        assertEquals(new BooleanArrayList(false, true, false, true),
                listFactory.withAll(Lists.mutable.of(false, true, false, true)));
    }

    @Test
    public void immutables()
    {
        ImmutableBooleanListFactory listFactory = BooleanLists.immutable;
        assertEquals(new BooleanArrayList(), listFactory.of());
        Verify.assertInstanceOf(ImmutableBooleanList.class, listFactory.of());
        assertEquals(BooleanArrayList.newListWith(true), listFactory.of(true));
        Verify.assertInstanceOf(ImmutableBooleanList.class, listFactory.of(true));
        assertEquals(BooleanArrayList.newListWith(true, false), listFactory.of(true, false));
        Verify.assertInstanceOf(ImmutableBooleanList.class, listFactory.of(true, false));
        assertEquals(BooleanArrayList.newListWith(true, false, true), listFactory.of(true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanList.class, listFactory.of(true, false, true));
        assertEquals(BooleanArrayList.newListWith(true, false, true, false), listFactory.of(true, false, true, false));
        Verify.assertInstanceOf(ImmutableBooleanList.class, listFactory.of(true, false, true, false));
        assertEquals(BooleanArrayList.newListWith(true, false, true, false, true), listFactory.of(true, false, true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanList.class, listFactory.of(true, false, true, false, true));
        assertEquals(BooleanArrayList.newListWith(true, false, true, false, true, false), listFactory.of(true, false, true, false, true, false));
        Verify.assertInstanceOf(ImmutableBooleanList.class, listFactory.of(true, false, true, false, true, false));
        assertEquals(BooleanArrayList.newListWith(true, false, true, false, true, false, true), listFactory.of(true, false, true, false, true, false, true));
        Verify.assertInstanceOf(ImmutableBooleanList.class, listFactory.of(true, false, true, false, true, false, true));
        assertEquals(BooleanArrayList.newListWith(true, false, true, false, true, false, true, true), listFactory.of(true, false, true, false, true, false, true, true));
        Verify.assertInstanceOf(ImmutableBooleanList.class, listFactory.of(true, false, true, false, true, false, true, true));
        assertEquals(BooleanArrayList.newListWith(true, false, true, false, true, false, true, true, true), listFactory.of(true, false, true, false, true, false, true, true, true));
        Verify.assertInstanceOf(ImmutableBooleanList.class, listFactory.of(true, false, true, false, true, false, true, true, true));
        assertEquals(BooleanArrayList.newListWith(true, false, true, false, true, false, true, true, true, false), listFactory.of(true, false, true, false, true, false, true, true, true, false));
        Verify.assertInstanceOf(ImmutableBooleanList.class, listFactory.of(true, false, true, false, true, false, true, true, true, false));
        assertEquals(BooleanArrayList.newListWith(true, false, true), listFactory.ofAll(BooleanArrayList.newListWith(true, false, true)));
        Verify.assertInstanceOf(ImmutableBooleanList.class, listFactory.ofAll(BooleanArrayList.newListWith(true, false, true)));
    }

    @Test
    public void emptyList()
    {
        Verify.assertEmpty(BooleanLists.immutable.of());
        assertSame(BooleanLists.immutable.of(), BooleanLists.immutable.of());
        Verify.assertPostSerializedIdentity(BooleanLists.immutable.of());
    }

    @Test
    public void newListWith()
    {
        ImmutableBooleanList list = BooleanLists.immutable.of();
        assertEquals(list, BooleanLists.immutable.of(list.toArray()));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(true));
        assertEquals(list = list.newWith(false), BooleanLists.immutable.of(true, false));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(true, false, true));
        assertEquals(list = list.newWith(false), BooleanLists.immutable.of(true, false, true, false));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(true, false, true, false, true));
        assertEquals(list = list.newWith(false), BooleanLists.immutable.of(true, false, true, false, true, false));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(true, false, true, false, true, false, true));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(true, false, true, false, true, false, true, true));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(true, false, true, false, true, false, true, true, true));
        assertEquals(list = list.newWith(false), BooleanLists.immutable.of(true, false, true, false, true, false, true, true, true, false));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(true, false, true, false, true, false, true, true, true, false, true));
        assertEquals(list = list.newWith(false), BooleanLists.immutable.of(true, false, true, false, true, false, true, true, true, false, true, false));
    }

    @SuppressWarnings("RedundantArrayCreation")
    @Test
    public void newListWithArray()
    {
        ImmutableBooleanList list = BooleanLists.immutable.of();
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(new boolean[]{true}));
        assertEquals(list = list.newWith(false), BooleanLists.immutable.of(new boolean[]{true, false}));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(new boolean[]{true, false, true}));
        assertEquals(list = list.newWith(false), BooleanLists.immutable.of(new boolean[]{true, false, true, false}));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(new boolean[]{true, false, true, false, true}));
        assertEquals(list = list.newWith(false), BooleanLists.immutable.of(new boolean[]{true, false, true, false, true, false}));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(new boolean[]{true, false, true, false, true, false, true}));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(new boolean[]{true, false, true, false, true, false, true, true}));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(new boolean[]{true, false, true, false, true, false, true, true, true}));
        assertEquals(list = list.newWith(false), BooleanLists.immutable.of(new boolean[]{true, false, true, false, true, false, true, true, true, false}));
        assertEquals(list = list.newWith(true), BooleanLists.immutable.of(new boolean[]{true, false, true, false, true, false, true, true, true, false, true}));
    }

    @Test
    public void newListWithList()
    {
        ImmutableBooleanList list = BooleanLists.immutable.of();
        BooleanArrayList booleanArrayList = BooleanArrayList.newListWith(true);
        assertEquals(list = list.newWith(true), booleanArrayList.toImmutable());
        assertEquals(list = list.newWith(false), booleanArrayList.with(false).toImmutable());
        assertEquals(list = list.newWith(true), booleanArrayList.with(true).toImmutable());
        assertEquals(list = list.newWith(false), booleanArrayList.with(false).toImmutable());
        assertEquals(list = list.newWith(true), booleanArrayList.with(true).toImmutable());
        assertEquals(list = list.newWith(false), booleanArrayList.with(false).toImmutable());
        assertEquals(list = list.newWith(true), booleanArrayList.with(true).toImmutable());
        assertEquals(list = list.newWith(true), booleanArrayList.with(true).toImmutable());
        assertEquals(list = list.newWith(true), booleanArrayList.with(true).toImmutable());
        assertEquals(list = list.newWith(false), booleanArrayList.with(false).toImmutable());
        assertEquals(list = list.newWith(true), booleanArrayList.with(true).toImmutable());
    }

    @Test
    public void newListWithWithList()
    {
        assertEquals(new BooleanArrayList(), BooleanLists.immutable.ofAll(new BooleanArrayList()));
        assertEquals(BooleanArrayList.newListWith(true), BooleanLists.immutable.ofAll(BooleanArrayList.newListWith(true)));
        assertEquals(BooleanArrayList.newListWith(true, false), BooleanLists.immutable.ofAll(BooleanArrayList.newListWith(true, false)));
        assertEquals(BooleanArrayList.newListWith(true, false, true), BooleanLists.immutable.ofAll(BooleanArrayList.newListWith(true, false, true)));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(BooleanLists.class);
    }
}
