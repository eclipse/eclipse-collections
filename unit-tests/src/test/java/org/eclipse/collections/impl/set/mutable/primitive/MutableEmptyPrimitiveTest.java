/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable.primitive;

import org.eclipse.collections.impl.factory.primitive.BooleanSets;
import org.eclipse.collections.impl.factory.primitive.ByteSets;
import org.eclipse.collections.impl.factory.primitive.CharSets;
import org.eclipse.collections.impl.factory.primitive.DoubleSets;
import org.eclipse.collections.impl.factory.primitive.FloatSets;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.factory.primitive.LongSets;
import org.eclipse.collections.impl.factory.primitive.ShortSets;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

/**
 * JUnit test for empty() methods of primitive classes
 */
public class MutableEmptyPrimitiveTest
{
    @Test
    public void isEmptyMutable()
    {
        Verify.assertEmpty(BooleanSets.mutable.empty());
        Verify.assertEmpty(BooleanSets.mutable.of());
        Verify.assertEmpty(BooleanSets.mutable.with());

        Verify.assertEmpty(ByteSets.mutable.empty());
        Verify.assertEmpty(ByteSets.mutable.of());
        Verify.assertEmpty(ByteSets.mutable.with());

        Verify.assertEmpty(CharSets.mutable.empty());
        Verify.assertEmpty(CharSets.mutable.of());
        Verify.assertEmpty(CharSets.mutable.with());

        Verify.assertEmpty(DoubleSets.mutable.empty());
        Verify.assertEmpty(DoubleSets.mutable.of());
        Verify.assertEmpty(DoubleSets.mutable.with());

        Verify.assertEmpty(FloatSets.mutable.empty());
        Verify.assertEmpty(FloatSets.mutable.of());
        Verify.assertEmpty(FloatSets.mutable.with());

        Verify.assertEmpty(IntSets.mutable.empty());
        Verify.assertEmpty(IntSets.mutable.of());
        Verify.assertEmpty(IntSets.mutable.with());

        Verify.assertEmpty(LongSets.mutable.empty());
        Verify.assertEmpty(LongSets.mutable.of());
        Verify.assertEmpty(LongSets.mutable.with());

        Verify.assertEmpty(ShortSets.mutable.empty());
        Verify.assertEmpty(ShortSets.mutable.of());
        Verify.assertEmpty(ShortSets.mutable.with());
    }
}
