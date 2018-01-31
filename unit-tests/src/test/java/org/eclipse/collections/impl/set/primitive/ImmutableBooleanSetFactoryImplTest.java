/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.primitive;

import org.eclipse.collections.api.set.primitive.ImmutableBooleanSet;
import org.eclipse.collections.impl.factory.primitive.BooleanSets;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableBooleanSetFactoryImplTest
{
    @Test
    public void of()
    {
        Verify.assertEmpty(BooleanSets.immutable.of());
        Assert.assertEquals(BooleanHashSet.newSetWith(true).toImmutable(), BooleanSets.immutable.of(true));
    }

    @Test
    public void with()
    {
        Verify.assertEmpty(BooleanSets.immutable.with(null));
        Assert.assertEquals(BooleanHashSet.newSetWith(false).toImmutable(), BooleanSets.immutable.with(new boolean[]{false}));
    }

    @Test
    public void ofAll()
    {
        ImmutableBooleanSet set = BooleanSets.immutable.of(true, false);
        Assert.assertEquals(BooleanHashSet.newSet(set).toImmutable(), BooleanSets.immutable.ofAll(set));
        Assert.assertEquals(BooleanHashSet.newSet(BooleanArrayList.newListWith(true, false, true)).toImmutable(), BooleanSets.immutable.ofAll(BooleanArrayList.newListWith(true, false)));
    }
}
