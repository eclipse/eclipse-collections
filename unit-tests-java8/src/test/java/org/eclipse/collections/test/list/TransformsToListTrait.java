/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.list;

import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableByteList;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableFloatList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.list.primitive.MutableShortList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.CharArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ShortArrayList;
import org.eclipse.collections.test.RichIterableTestCase;

public interface TransformsToListTrait extends RichIterableTestCase
{
    @Override
    default <T> ListIterable<T> getExpectedTransformed(T... elements)
    {
        return Lists.immutable.with(elements);
    }

    @Override
    default <T> MutableList<T> newMutableForTransform(T... elements)
    {
        return Lists.mutable.with(elements);
    }

    @Override
    default MutableBooleanList newBooleanForTransform(boolean... elements)
    {
        return new BooleanArrayList(elements);
    }

    @Override
    default MutableByteList newByteForTransform(byte... elements)
    {
        return new ByteArrayList(elements);
    }

    @Override
    default MutableCharList newCharForTransform(char... elements)
    {
        return new CharArrayList(elements);
    }

    @Override
    default MutableDoubleList newDoubleForTransform(double... elements)
    {
        return new DoubleArrayList(elements);
    }

    @Override
    default MutableFloatList newFloatForTransform(float... elements)
    {
        return new FloatArrayList(elements);
    }

    @Override
    default MutableIntList newIntForTransform(int... elements)
    {
        return new IntArrayList(elements);
    }

    @Override
    default MutableLongList newLongForTransform(long... elements)
    {
        return new LongArrayList(elements);
    }

    @Override
    default MutableShortList newShortForTransform(short... elements)
    {
        return new ShortArrayList(elements);
    }
}
