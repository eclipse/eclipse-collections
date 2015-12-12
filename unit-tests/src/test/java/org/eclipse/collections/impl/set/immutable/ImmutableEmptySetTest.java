/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.immutable;

import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableEmptySetTest extends AbstractImmutableEmptySetTestCase
{
    @Override
    protected ImmutableSet<Integer> classUnderTest()
    {
        return Sets.immutable.of();
    }

    @Override
    @Test
    public void newWithout()
    {
        Assert.assertSame(Sets.immutable.of(), Sets.immutable.of().newWithout(1));
        Assert.assertSame(Sets.immutable.of(), Sets.immutable.of().newWithoutAll(Interval.oneTo(3)));
    }

    @Override
    @Test
    public void equalsAndHashCode()
    {
        ImmutableSet<Integer> immutable = this.classUnderTest();
        MutableSet<Integer> mutable = UnifiedSet.newSet(immutable);
        Verify.assertEqualsAndHashCode(mutable, immutable);
        Verify.assertPostSerializedIdentity(immutable);
        Assert.assertNotEquals(FastList.newList(mutable), immutable);
    }
}
