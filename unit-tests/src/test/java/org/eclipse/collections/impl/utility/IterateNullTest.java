/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.utility;

import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.CharArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ShortArrayList;
import org.eclipse.collections.impl.utility.internal.IterableIterate;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

/**
 * JUnit test for the null handling behavior of {@link Iterate}, {@link ArrayIterate}, {@link ArrayListIterate},
 * {@link ListIterate}, {@link IterableIterate}.
 */
public class IterateNullTest
{
    // Iterate

    @Test
    public void collect()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collect(null, Functions.getPassThru()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collect(null, Functions.getPassThru()));
    }

    @Test
    public void collectBoolean()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectBoolean(null, PrimitiveFunctions.integerIsPositive()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectBoolean(null, PrimitiveFunctions.integerIsPositive()));
    }

    @Test
    public void collectBooleanWithTarget()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectBoolean(null, PrimitiveFunctions.integerIsPositive(), new BooleanArrayList()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectBoolean(null, PrimitiveFunctions.integerIsPositive(), new BooleanArrayList()));
    }

    @Test
    public void collectByte()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectByte(null, PrimitiveFunctions.unboxIntegerToByte()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectByte(null, PrimitiveFunctions.unboxIntegerToByte()));
    }

    @Test
    public void collectByteWithTarget()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectByte(null, PrimitiveFunctions.unboxIntegerToByte(), new ByteArrayList()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectByte(null, PrimitiveFunctions.unboxIntegerToByte(), new ByteArrayList()));
    }

    @Test
    public void collectChar()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectChar(null, PrimitiveFunctions.unboxIntegerToChar()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectChar(null, PrimitiveFunctions.unboxIntegerToChar()));
    }

    @Test
    public void collectCharWithTarget()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectChar(null, PrimitiveFunctions.unboxIntegerToChar(), new CharArrayList()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectChar(null, PrimitiveFunctions.unboxIntegerToChar(), new CharArrayList()));
    }

    @Test
    public void collectDouble()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectDouble(null, PrimitiveFunctions.unboxIntegerToDouble()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectDouble(null, PrimitiveFunctions.unboxIntegerToDouble()));
    }

    @Test
    public void collectDoubleWithTarget()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectDouble(null, PrimitiveFunctions.unboxIntegerToDouble(), new DoubleArrayList()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectDouble(null, PrimitiveFunctions.unboxIntegerToDouble(), new DoubleArrayList()));
    }

    @Test
    public void collectFloat()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectFloat(null, PrimitiveFunctions.unboxIntegerToFloat()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectFloat(null, PrimitiveFunctions.unboxIntegerToFloat()));
    }

    @Test
    public void collectFloatWithTarget()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectFloat(null, PrimitiveFunctions.unboxIntegerToFloat(), new FloatArrayList()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectFloat(null, PrimitiveFunctions.unboxIntegerToFloat(), new FloatArrayList()));
    }

    @Test
    public void collectInt()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectInt(null, PrimitiveFunctions.unboxIntegerToInt()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectInt(null, PrimitiveFunctions.unboxIntegerToInt()));
    }

    @Test
    public void collectIntWithTarget()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectInt(null, PrimitiveFunctions.unboxIntegerToInt(), new IntArrayList()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectInt(null, PrimitiveFunctions.unboxIntegerToInt(), new IntArrayList()));
    }

    @Test
    public void collectLong()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectLong(null, PrimitiveFunctions.unboxIntegerToLong()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectLong(null, PrimitiveFunctions.unboxIntegerToLong()));
    }

    @Test
    public void collectLongWithTarget()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectLong(null, PrimitiveFunctions.unboxIntegerToLong(), new LongArrayList()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectLong(null, PrimitiveFunctions.unboxIntegerToLong(), new LongArrayList()));
    }

    @Test
    public void collectShort()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectShort(null, PrimitiveFunctions.unboxIntegerToShort()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectShort(null, PrimitiveFunctions.unboxIntegerToShort()));
    }

    @Test
    public void collectShortWithTarget()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectShort(null, PrimitiveFunctions.unboxIntegerToShort(), new ShortArrayList()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectShort(null, PrimitiveFunctions.unboxIntegerToShort(), new ShortArrayList()));
    }

    @Test
    public void collectIf()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectIf(null, null, Functions.getPassThru()));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectIf(null, null, Functions.getPassThru()));
    }

    @Test
    public void collectWith()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.collectWith(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectWith(null, null, null));
    }

    @Test
    public void select()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.select(null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.select(null, null));
    }

    @Test
    public void selectAndRejectWith()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.selectAndRejectWith(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.selectAndRejectWith(null, null, null));
    }

    @Test
    public void partition()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.partition(null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.partition(null, null));
    }

    @Test
    public void partitionWith()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.partitionWith(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.partitionWith(null, null, null));
    }

    @Test
    public void selectWith()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.selectWith(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.selectWith(null, null, null));
    }

    @Test
    public void selectInstancesOf()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.selectInstancesOf(null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.selectInstancesOf(null, null));
    }

    @Test
    public void detect()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.detect(null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.detect(null, null));
    }

    @Test
    public void detectIfNone()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.detectIfNone(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.detectIfNone(null, null, null));
    }

    @Test
    public void detectWith()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.detectWith(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.detectWith(null, null, null));
    }

    @Test
    public void detectWithIfNone()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.detectWithIfNone(null, null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.detectWithIfNone(null, null, null, null));
    }

    @Test
    public void detectIndex()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.detectIndex(null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.detectIndex(null, null));
    }

    @Test
    public void detectIndexWith()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.detectIndexWith(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.detectIndexWith(null, null, null));
    }

    @Test
    public void reject()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.reject(null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.reject(null, null));
    }

    @Test
    public void rejectWith()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.rejectWith(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.rejectWith(null, null, null));
    }

    @Test
    public void injectInto()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.injectInto(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.injectInto(null, null, null));
    }

    @Test
    public void injectIntoWith()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.injectIntoWith(null, null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.injectIntoWith(null, null, null, null));
    }

    @Test
    public void forEach()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.forEach(null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.forEach(null, null));
    }

    @Test
    public void forEachWith()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.forEachWith(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.forEachWith(null, null, null));
    }

    @Test
    public void forEachWithIndex()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.forEachWithIndex(null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.forEachWithIndex(null, null));
    }

    @Test
    public void anySatisfy()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.anySatisfy(null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.anySatisfy(null, null));
    }

    @Test
    public void anySatisfyWith()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.anySatisfyWith(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.anySatisfyWith(null, null, null));
    }

    @Test
    public void allSatisfy()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.allSatisfy(null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.allSatisfy(null, null));
    }

    @Test
    public void allSatisfyWith()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.allSatisfyWith(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.allSatisfyWith(null, null, null));
    }

    @Test
    public void noneSatisfy()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.noneSatisfy(null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.noneSatisfy(null, null));
    }

    @Test
    public void noneSatisfyWith()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.noneSatisfyWith(null, null, null));

        assertThrows(IllegalArgumentException.class, () -> ArrayIterate.noneSatisfyWith(null, null, null));
    }

    // Others

    @Test
    public void collectArrayList()
    {
        assertThrows(NullPointerException.class, () -> ArrayListIterate.collect(null, Functions.getPassThru()));
    }

    @Test
    public void collectList()
    {
        assertThrows(NullPointerException.class, () -> ListIterate.collect(null, Functions.getPassThru()));
    }

    @Test
    public void collectIterable()
    {
        assertThrows(NullPointerException.class, () -> IterableIterate.collect(null, Functions.getPassThru()));
    }

    @Test
    public void selectArrayList()
    {
        assertThrows(NullPointerException.class, () -> ArrayListIterate.select(null, null));
    }

    @Test
    public void selectList()
    {
        assertThrows(NullPointerException.class, () -> ListIterate.select(null, null));
    }

    @Test
    public void selectIterable()
    {
        assertThrows(NullPointerException.class, () -> IterableIterate.select(null, null));
    }

    @Test
    public void detectArrayList()
    {
        assertThrows(NullPointerException.class, () -> assertNull(ArrayListIterate.detect(null, null)));
    }

    @Test
    public void detectList()
    {
        assertThrows(NullPointerException.class, () -> assertNull(ListIterate.detect(null, null)));
    }

    @Test
    public void detectIterable()
    {
        assertThrows(NullPointerException.class, () -> assertNull(IterableIterate.detect(null, null)));
    }

    @Test
    public void rejectArrayList()
    {
        assertThrows(NullPointerException.class, () -> ArrayListIterate.reject(null, null));
    }

    @Test
    public void rejectList()
    {
        assertThrows(NullPointerException.class, () -> ListIterate.reject(null, null));
    }

    @Test
    public void rejectIterable()
    {
        assertThrows(NullPointerException.class, () -> IterableIterate.reject(null, null));
    }

    @Test
    public void injectArrayList()
    {
        assertThrows(NullPointerException.class, () -> assertNull(ArrayListIterate.injectInto(null, null, null)));
    }

    @Test
    public void injectList()
    {
        assertThrows(NullPointerException.class, () -> assertNull(ListIterate.injectInto(null, null, null)));
    }

    @Test
    public void injectIterable()
    {
        assertThrows(NullPointerException.class, () -> assertNull(IterableIterate.injectInto(null, null, null)));
    }

    @Test
    public void forEachArrayList()
    {
        assertThrows(NullPointerException.class, () -> ArrayListIterate.forEach(null, null));
    }

    @Test
    public void forEachList()
    {
        assertThrows(NullPointerException.class, () -> ListIterate.forEach(null, null));
    }

    @Test
    public void forEachIterable()
    {
        assertThrows(NullPointerException.class, () -> IterableIterate.forEach(null, null));
    }

    @Test
    public void takeArrayList()
    {
        assertThrows(NullPointerException.class, () -> ArrayListIterate.take(null, 0));
    }

    @Test
    public void takeList()
    {
        assertThrows(NullPointerException.class, () -> ListIterate.take(null, 0));
    }

    @Test
    public void takeIterable()
    {
        assertThrows(NullPointerException.class, () -> IterableIterate.take(null, 0));
    }

    @Test
    public void dropArrayList()
    {
        assertThrows(NullPointerException.class, () -> ArrayListIterate.drop(null, 0));
    }

    @Test
    public void dropList()
    {
        assertThrows(NullPointerException.class, () -> ListIterate.drop(null, 0));
    }

    @Test
    public void dropIterable()
    {
        assertThrows(NullPointerException.class, () -> IterableIterate.drop(null, 0));
    }

    @Test
    public void removeAllIterable()
    {
        assertThrows(NullPointerException.class, () -> Iterate.removeAllIterable(null, null));
    }

    @Test
    public void sumOfBigInteger()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.sumOfBigInteger(null, null));
    }

    @Test
    public void sumByBigDecimal()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.sumByBigDecimal(null, null, null));
    }

    @Test
    public void sumByBigInteger()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.sumByBigInteger(null, null, null));
    }

    @Test
    public void sumByInt()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.sumByInt(null, null, null));
    }

    @Test
    public void sumByLong()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.sumByLong(null, null, null));
    }

    @Test
    public void sumByFloat()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.sumByFloat(null, null, null));
    }

    @Test
    public void sumByDouble()
    {
        assertThrows(IllegalArgumentException.class, () -> Iterate.sumByDouble(null, null, null));
    }
}
