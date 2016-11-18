/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.factory.primitive.CharToCharFunctions;
import org.eclipse.collections.impl.block.factory.primitive.IntToIntFunctions;
import org.eclipse.collections.impl.block.function.MaxFunction;
import org.eclipse.collections.impl.block.function.MaxSizeFunction;
import org.eclipse.collections.impl.block.function.MinFunction;
import org.eclipse.collections.impl.block.function.MinSizeFunction;
import org.eclipse.collections.impl.block.function.SubtractFunction;
import org.eclipse.collections.impl.factory.SortedMaps;
import org.eclipse.collections.impl.factory.primitive.BooleanSets;
import org.eclipse.collections.impl.factory.primitive.BooleanStacks;
import org.eclipse.collections.impl.factory.primitive.ByteBooleanMaps;
import org.eclipse.collections.impl.factory.primitive.CharBooleanMaps;
import org.eclipse.collections.impl.factory.primitive.DoubleBooleanMaps;
import org.eclipse.collections.impl.factory.primitive.FloatBooleanMaps;
import org.eclipse.collections.impl.factory.primitive.IntBooleanMaps;
import org.eclipse.collections.impl.factory.primitive.LongBooleanMaps;
import org.eclipse.collections.impl.factory.primitive.ObjectBooleanMaps;
import org.eclipse.collections.impl.factory.primitive.ShortBooleanMaps;
import org.eclipse.collections.impl.parallel.Combiners;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.eclipse.collections.impl.utility.internal.InternalArrayIterate;
import org.eclipse.collections.impl.utility.internal.IteratorIterate;
import org.eclipse.collections.impl.utility.internal.MutableCollectionIterate;
import org.eclipse.collections.impl.utility.internal.ReflectionHelper;
import org.eclipse.collections.impl.utility.internal.SetIterables;
import org.eclipse.collections.impl.utility.internal.SetIterate;
import org.eclipse.collections.impl.utility.internal.SortedSetIterables;
import org.eclipse.collections.impl.utility.internal.primitive.BooleanIterableIterate;
import org.eclipse.collections.impl.utility.internal.primitive.BooleanIteratorIterate;
import org.eclipse.collections.impl.utility.primitive.LazyBooleanIterate;
import org.junit.Test;

public class NonInstantiableTest
{
    // TODO: Move the test for each of these classes into appropriate generated test classes
    private static final Class<?>[] GENERATED_NON_INSTANTIABLE_CLASSES =
            {
                    BooleanStacks.class,
                    ByteBooleanMaps.class,
                    CharBooleanMaps.class,
                    DoubleBooleanMaps.class,
                    FloatBooleanMaps.class,
                    IntBooleanMaps.class,
                    LongBooleanMaps.class,
                    ShortBooleanMaps.class,
                    ObjectBooleanMaps.class,
                    BooleanIterableIterate.class,
                    BooleanIteratorIterate.class,
                    LazyBooleanIterate.class,
            };

    private static final Class<?>[] HAND_CODED_NON_INSTANTIABLE_CLASSES = // With no dedicated test class
            {
                    PrimitiveFunctions.class,

                    CharToCharFunctions.class,
                    IntToIntFunctions.class,

                    MaxFunction.class,
                    MaxSizeFunction.class,
                    MinFunction.class,
                    MinSizeFunction.class,
                    SubtractFunction.class,

                    BooleanSets.class,

                    Combiners.class,

                    PrimitiveTuples.class,

                    InternalArrayIterate.class,
                    IteratorIterate.class,
                    MutableCollectionIterate.class,
                    ReflectionHelper.class,
                    SetIterables.class,
                    SetIterate.class,
                    SortedMaps.class,
                    SortedSetIterables.class
            };

    @Test
    public void generatedNonInstantiableClassesThrow()
    {
        for (Class<?> aClass : GENERATED_NON_INSTANTIABLE_CLASSES)
        {
            Verify.assertClassNonInstantiable(aClass);
        }
    }

    @Test
    public void handCodedNonInstantiableClassesThrow()
    {
        for (Class<?> aClass : HAND_CODED_NON_INSTANTIABLE_CLASSES)
        {
            Verify.assertClassNonInstantiable(aClass);
        }
    }
}
