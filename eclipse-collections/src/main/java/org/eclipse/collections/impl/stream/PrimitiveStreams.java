/*
 * Copyright (c) 2017 BNY Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stream;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.eclipse.collections.api.bag.primitive.ImmutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.ImmutableIntBag;
import org.eclipse.collections.api.bag.primitive.ImmutableLongBag;
import org.eclipse.collections.api.bag.primitive.MutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.bag.primitive.MutableLongBag;
import org.eclipse.collections.api.list.primitive.ImmutableDoubleList;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.list.primitive.ImmutableLongList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.set.primitive.ImmutableDoubleSet;
import org.eclipse.collections.api.set.primitive.ImmutableIntSet;
import org.eclipse.collections.api.set.primitive.ImmutableLongSet;
import org.eclipse.collections.api.set.primitive.MutableDoubleSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.set.primitive.MutableLongSet;
import org.eclipse.collections.api.stack.primitive.ImmutableDoubleStack;
import org.eclipse.collections.api.stack.primitive.ImmutableIntStack;
import org.eclipse.collections.api.stack.primitive.ImmutableLongStack;
import org.eclipse.collections.api.stack.primitive.MutableDoubleStack;
import org.eclipse.collections.api.stack.primitive.MutableIntStack;
import org.eclipse.collections.api.stack.primitive.MutableLongStack;
import org.eclipse.collections.impl.factory.primitive.DoubleBags;
import org.eclipse.collections.impl.factory.primitive.DoubleLists;
import org.eclipse.collections.impl.factory.primitive.DoubleSets;
import org.eclipse.collections.impl.factory.primitive.DoubleStacks;
import org.eclipse.collections.impl.factory.primitive.IntBags;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.factory.primitive.IntStacks;
import org.eclipse.collections.impl.factory.primitive.LongBags;
import org.eclipse.collections.impl.factory.primitive.LongLists;
import org.eclipse.collections.impl.factory.primitive.LongSets;
import org.eclipse.collections.impl.factory.primitive.LongStacks;

/**
 * This class provides interop between primitive streams and primitive collections.
 *
 * @since 9.0
 */
public final class PrimitiveStreams
{
    private PrimitiveStreams()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static MutableIntList mIntList(IntStream stream)
    {
        return IntLists.mutable.withAll(stream);
    }

    public static ImmutableIntList iIntList(IntStream stream)
    {
        return IntLists.immutable.withAll(stream);
    }

    public static MutableIntSet mIntSet(IntStream stream)
    {
        return IntSets.mutable.withAll(stream);
    }

    public static ImmutableIntSet iIntSet(IntStream stream)
    {
        return IntSets.immutable.withAll(stream);
    }

    public static MutableIntBag mIntBag(IntStream stream)
    {
        return IntBags.mutable.withAll(stream);
    }

    public static ImmutableIntBag iIntBag(IntStream stream)
    {
        return IntBags.immutable.withAll(stream);
    }

    public static MutableIntStack mIntStack(IntStream stream)
    {
        return IntStacks.mutable.withAll(stream);
    }

    public static ImmutableIntStack iIntStack(IntStream stream)
    {
        return IntStacks.immutable.withAll(stream);
    }

    public static MutableLongList mLongList(LongStream stream)
    {
        return LongLists.mutable.withAll(stream);
    }

    public static ImmutableLongList iLongList(LongStream stream)
    {
        return LongLists.immutable.withAll(stream);
    }

    public static MutableLongSet mLongSet(LongStream stream)
    {
        return LongSets.mutable.withAll(stream);
    }

    public static ImmutableLongSet iLongSet(LongStream stream)
    {
        return LongSets.immutable.withAll(stream);
    }

    public static MutableLongBag mLongBag(LongStream stream)
    {
        return LongBags.mutable.withAll(stream);
    }

    public static ImmutableLongBag iLongBag(LongStream stream)
    {
        return LongBags.immutable.withAll(stream);
    }

    public static MutableLongStack mLongStack(LongStream stream)
    {
        return LongStacks.mutable.withAll(stream);
    }

    public static ImmutableLongStack iLongStack(LongStream stream)
    {
        return LongStacks.immutable.withAll(stream);
    }

    public static MutableDoubleList mDoubleList(DoubleStream stream)
    {
        return DoubleLists.mutable.withAll(stream);
    }

    public static ImmutableDoubleList iDoubleList(DoubleStream stream)
    {
        return DoubleLists.immutable.withAll(stream);
    }

    public static MutableDoubleSet mDoubleSet(DoubleStream stream)
    {
        return DoubleSets.mutable.withAll(stream);
    }

    public static ImmutableDoubleSet iDoubleSet(DoubleStream stream)
    {
        return DoubleSets.immutable.withAll(stream);
    }

    public static MutableDoubleBag mDoubleBag(DoubleStream stream)
    {
        return DoubleBags.mutable.withAll(stream);
    }

    public static ImmutableDoubleBag iDoubleBag(DoubleStream stream)
    {
        return DoubleBags.immutable.withAll(stream);
    }

    public static MutableDoubleStack mDoubleStack(DoubleStream stream)
    {
        return DoubleStacks.mutable.withAll(stream);
    }

    public static ImmutableDoubleStack iDoubleStack(DoubleStream stream)
    {
        return DoubleStacks.immutable.withAll(stream);
    }
}
