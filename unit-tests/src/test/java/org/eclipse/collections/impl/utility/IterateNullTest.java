/*
 * Copyright (c) 2015 Goldman Sachs.
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
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.utility.internal.IterableIterate;
import org.junit.Assert;
import org.junit.Test;

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
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collect(null, Functions.getPassThru()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collect(null, Functions.getPassThru()));
    }

    @Test
    public void collectBoolean()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectBoolean(null, PrimitiveFunctions.integerIsPositive()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectBoolean(null, PrimitiveFunctions.integerIsPositive()));
    }

    @Test
    public void collectBooleanWithTarget()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectBoolean(null, PrimitiveFunctions.integerIsPositive(), new BooleanArrayList()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectBoolean(null, PrimitiveFunctions.integerIsPositive(), new BooleanArrayList()));
    }

    @Test
    public void collectByte()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectByte(null, PrimitiveFunctions.unboxIntegerToByte()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectByte(null, PrimitiveFunctions.unboxIntegerToByte()));
    }

    @Test
    public void collectByteWithTarget()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectByte(null, PrimitiveFunctions.unboxIntegerToByte(), new ByteArrayList()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectByte(null, PrimitiveFunctions.unboxIntegerToByte(), new ByteArrayList()));
    }

    @Test
    public void collectChar()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectChar(null, PrimitiveFunctions.unboxIntegerToChar()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectChar(null, PrimitiveFunctions.unboxIntegerToChar()));
    }

    @Test
    public void collectCharWithTarget()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectChar(null, PrimitiveFunctions.unboxIntegerToChar(), new CharArrayList()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectChar(null, PrimitiveFunctions.unboxIntegerToChar(), new CharArrayList()));
    }

    @Test
    public void collectDouble()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectDouble(null, PrimitiveFunctions.unboxIntegerToDouble()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectDouble(null, PrimitiveFunctions.unboxIntegerToDouble()));
    }

    @Test
    public void collectDoubleWithTarget()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectDouble(null, PrimitiveFunctions.unboxIntegerToDouble(), new DoubleArrayList()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectDouble(null, PrimitiveFunctions.unboxIntegerToDouble(), new DoubleArrayList()));
    }

    @Test
    public void collectFloat()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectFloat(null, PrimitiveFunctions.unboxIntegerToFloat()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectFloat(null, PrimitiveFunctions.unboxIntegerToFloat()));
    }

    @Test
    public void collectFloatWithTarget()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectFloat(null, PrimitiveFunctions.unboxIntegerToFloat(), new FloatArrayList()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectFloat(null, PrimitiveFunctions.unboxIntegerToFloat(), new FloatArrayList()));
    }

    @Test
    public void collectInt()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectInt(null, PrimitiveFunctions.unboxIntegerToInt()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectInt(null, PrimitiveFunctions.unboxIntegerToInt()));
    }

    @Test
    public void collectIntWithTarget()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectInt(null, PrimitiveFunctions.unboxIntegerToInt(), new IntArrayList()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectInt(null, PrimitiveFunctions.unboxIntegerToInt(), new IntArrayList()));
    }

    @Test
    public void collectLong()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectLong(null, PrimitiveFunctions.unboxIntegerToLong()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectLong(null, PrimitiveFunctions.unboxIntegerToLong()));
    }

    @Test
    public void collectLongWithTarget()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectLong(null, PrimitiveFunctions.unboxIntegerToLong(), new LongArrayList()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectLong(null, PrimitiveFunctions.unboxIntegerToLong(), new LongArrayList()));
    }

    @Test
    public void collectShort()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectShort(null, PrimitiveFunctions.unboxIntegerToShort()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectShort(null, PrimitiveFunctions.unboxIntegerToShort()));
    }

    @Test
    public void collectShortWithTarget()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectShort(null, PrimitiveFunctions.unboxIntegerToShort(), new ShortArrayList()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectShort(null, PrimitiveFunctions.unboxIntegerToShort(), new ShortArrayList()));
    }

    @Test
    public void collectIf()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectIf(null, null, Functions.getPassThru()));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectIf(null, null, Functions.getPassThru()));
    }

    @Test
    public void collectWith()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectWith(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.collectWith(null, null, null));
    }

    @Test
    public void select()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.select(null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.select(null, null));
    }

    @Test
    public void selectAndRejectWith()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.selectAndRejectWith(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.selectAndRejectWith(null, null, null));
    }

    @Test
    public void partition()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.partition(null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.partition(null, null));
    }

    @Test
    public void partitionWith()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.partitionWith(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.partitionWith(null, null, null));
    }

    @Test
    public void selectWith()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.selectWith(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.selectWith(null, null, null));
    }

    @Test
    public void selectInstancesOf()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.selectInstancesOf(null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.selectInstancesOf(null, null));
    }

    @Test
    public void detect()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.detect(null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.detect(null, null));
    }

    @Test
    public void detectIfNone()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.detectIfNone(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.detectIfNone(null, null, null));
    }

    @Test
    public void detectWith()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.detectWith(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.detectWith(null, null, null));
    }

    @Test
    public void detectWithIfNone()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.detectWithIfNone(null, null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.detectWithIfNone(null, null, null, null));
    }

    @Test
    public void detectIndex()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.detectIndex(null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.detectIndex(null, null));
    }

    @Test
    public void detectIndexWith()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.detectIndexWith(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.detectIndexWith(null, null, null));
    }

    @Test
    public void reject()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.reject(null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.reject(null, null));
    }

    @Test
    public void rejectWith()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.rejectWith(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.rejectWith(null, null, null));
    }

    @Test
    public void injectInto()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.injectInto(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.injectInto(null, null, null));
    }

    @Test
    public void injectIntoWith()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.injectIntoWith(null, null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.injectIntoWith(null, null, null, null));
    }

    @Test
    public void forEach()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.forEach(null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.forEach(null, null));
    }

    @Test
    public void forEachWith()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.forEachWith(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.forEachWith(null, null, null));
    }

    @Test
    public void forEachWithIndex()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.forEachWithIndex(null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.forEachWithIndex(null, null));
    }

    @Test
    public void anySatisfy()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.anySatisfy(null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.anySatisfy(null, null));
    }

    @Test
    public void anySatisfyWith()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.anySatisfyWith(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.anySatisfyWith(null, null, null));
    }

    @Test
    public void allSatisfy()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.allSatisfy(null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.allSatisfy(null, null));
    }

    @Test
    public void allSatisfyWith()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.allSatisfyWith(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.allSatisfyWith(null, null, null));
    }

    @Test
    public void noneSatisfy()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.noneSatisfy(null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.noneSatisfy(null, null));
    }

    @Test
    public void noneSatisfyWith()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.noneSatisfyWith(null, null, null));

        Verify.assertThrows(IllegalArgumentException.class, () -> ArrayIterate.noneSatisfyWith(null, null, null));
    }

    // Others

    @Test
    public void collectArrayList()
    {
        Verify.assertThrows(NullPointerException.class, () -> ArrayListIterate.collect(null, Functions.getPassThru()));
    }

    @Test
    public void collectList()
    {
        Verify.assertThrows(NullPointerException.class, () -> ListIterate.collect(null, Functions.getPassThru()));
    }

    @Test
    public void collectIterable()
    {
        Verify.assertThrows(NullPointerException.class, () -> IterableIterate.collect(null, Functions.getPassThru()));
    }

    @Test
    public void selectArrayList()
    {
        Verify.assertThrows(NullPointerException.class, () -> ArrayListIterate.select(null, null));
    }

    @Test
    public void selectList()
    {
        Verify.assertThrows(NullPointerException.class, () -> ListIterate.select(null, null));
    }

    @Test
    public void selectIterable()
    {
        Verify.assertThrows(NullPointerException.class, () -> IterableIterate.select(null, null));
    }

    @Test
    public void detectArrayList()
    {
        Verify.assertThrows(NullPointerException.class, () -> Assert.assertNull(ArrayListIterate.detect(null, null)));
    }

    @Test
    public void detectList()
    {
        Verify.assertThrows(NullPointerException.class, () -> Assert.assertNull(ListIterate.detect(null, null)));
    }

    @Test
    public void detectIterable()
    {
        Verify.assertThrows(NullPointerException.class, () -> Assert.assertNull(IterableIterate.detect(null, null)));
    }

    @Test
    public void rejectArrayList()
    {
        Verify.assertThrows(NullPointerException.class, () -> ArrayListIterate.reject(null, null));
    }

    @Test
    public void rejectList()
    {
        Verify.assertThrows(NullPointerException.class, () -> ListIterate.reject(null, null));
    }

    @Test
    public void rejectIterable()
    {
        Verify.assertThrows(NullPointerException.class, () -> IterableIterate.reject(null, null));
    }

    @Test
    public void injectArrayList()
    {
        Verify.assertThrows(NullPointerException.class, () -> Assert.assertNull(ArrayListIterate.injectInto(null, null, null)));
    }

    @Test
    public void injectList()
    {
        Verify.assertThrows(NullPointerException.class, () -> Assert.assertNull(ListIterate.injectInto(null, null, null)));
    }

    @Test
    public void injectIterable()
    {
        Verify.assertThrows(NullPointerException.class, () -> Assert.assertNull(IterableIterate.injectInto(null, null, null)));
    }

    @Test
    public void forEachArrayList()
    {
        Verify.assertThrows(NullPointerException.class, () -> ArrayListIterate.forEach(null, null));
    }

    @Test
    public void forEachList()
    {
        Verify.assertThrows(NullPointerException.class, () -> ListIterate.forEach(null, null));
    }

    @Test
    public void forEachIterable()
    {
        Verify.assertThrows(NullPointerException.class, () -> IterableIterate.forEach(null, null));
    }

    @Test
    public void takeArrayList()
    {
        Verify.assertThrows(NullPointerException.class, () -> ArrayListIterate.take(null, 0));
    }

    @Test
    public void takeList()
    {
        Verify.assertThrows(NullPointerException.class, () -> ListIterate.take(null, 0));
    }

    @Test
    public void takeIterable()
    {
        Verify.assertThrows(NullPointerException.class, () -> IterableIterate.take(null, 0));
    }

    @Test
    public void dropArrayList()
    {
        Verify.assertThrows(NullPointerException.class, () -> ArrayListIterate.drop(null, 0));
    }

    @Test
    public void dropList()
    {
        Verify.assertThrows(NullPointerException.class, () -> ListIterate.drop(null, 0));
    }

    @Test
    public void dropIterable()
    {
        Verify.assertThrows(NullPointerException.class, () -> IterableIterate.drop(null, 0));
    }

    @Test
    public void removeAllIterable()
    {
        Verify.assertThrows(NullPointerException.class, () -> Iterate.removeAllIterable(null, null));
    }

    @Test
    public void sumOfBigInteger()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.sumOfBigInteger(null, null));
    }

    @Test
    public void sumByBigDecimal()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.sumByBigDecimal(null, null, null));
    }

    @Test
    public void sumByBigInteger()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.sumByBigInteger(null, null, null));
    }

    @Test
    public void sumByInt()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.sumByInt(null, null, null));
    }

    @Test
    public void sumByLong()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.sumByLong(null, null, null));
    }

    @Test
    public void sumByFloat()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.sumByFloat(null, null, null));
    }

    @Test
    public void sumByDouble()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.sumByDouble(null, null, null));
    }
}
