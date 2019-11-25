/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.bag;

import java.util.stream.Stream;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class MutableBagsTest
{
    @Test
    public void of()
    {
        Verify.assertBagsEqual(HashBag.newBag(), MutableBag.of());
        Verify.assertInstanceOf(MutableBag.class, MutableBag.of());
        Verify.assertBagsEqual(HashBag.newBagWith(1), MutableBag.of(1));
        Verify.assertInstanceOf(MutableBag.class, MutableBag.of(1));
        Verify.assertBagsEqual(HashBag.newBagWith(1, 2), MutableBag.of(1, 2));
        Verify.assertInstanceOf(MutableBag.class, MutableBag.of(1, 2));
        Verify.assertBagsEqual(HashBag.newBagWith(1, 2, 3), MutableBag.of(1, 2, 3));
        Verify.assertInstanceOf(MutableBag.class, MutableBag.of(1, 2, 3));
        Verify.assertBagsEqual(HashBag.newBagWith(1, 2, 3, 4), MutableBag.of(1, 2, 3, 4));
        Verify.assertInstanceOf(MutableBag.class, MutableBag.of(1, 2, 3, 4));
        Verify.assertBagsEqual(HashBag.newBagWith(1, 2, 3, 4, 5), MutableBag.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(MutableBag.class, MutableBag.of(1, 2, 3, 4, 5));

        Bag<Integer> bag = HashBag.newBagWith(1, 2, 2, 3, 3, 3);
        Verify.assertBagsEqual(HashBag.newBagWith(1, 2, 2, 3, 3, 3), MutableBag.ofAll(bag));
        Verify.assertInstanceOf(MutableBag.class, MutableBag.ofAll(bag));
        Assert.assertNotSame(MutableBag.ofAll(bag), bag);
        Verify.assertBagsEqual(HashBag.newBagWith(1, 2, 2, 3, 3, 3), MutableBag.ofAll(FastList.newListWith(1, 2, 2, 3, 3, 3)));
        Verify.assertInstanceOf(MutableBag.class, MutableBag.ofAll(FastList.newListWith(1, 2, 2, 3, 3, 3)));
        Verify.assertBagsEqual(HashBag.newBagWith(1, 2, 3, 4, 5), MutableBag.ofAll(UnifiedSet.newSetWith(1, 2, 3, 4, 5)));
        Verify.assertInstanceOf(MutableBag.class, MutableBag.ofAll(UnifiedSet.newSetWith(1, 2, 3, 4, 5)));
        Verify.assertBagsEqual(HashBag.newBagWith(1, 2, 2, 3, 3, 3), MutableBag.fromStream(Stream.of(1, 2, 2, 3, 3, 3)));
        Verify.assertInstanceOf(MutableBag.class, MutableBag.fromStream(Stream.of(1, 2, 2, 3, 3, 3)));
    }
}
