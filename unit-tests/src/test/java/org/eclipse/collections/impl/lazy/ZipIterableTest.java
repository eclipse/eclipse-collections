/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import org.eclipse.collections.impl.factory.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ZipIterableTest
{
    private ZipIterable<Character, Integer> zipIterable;

    @Before
    public void setUp()
    {
        this.zipIterable = new ZipIterable<>(
                Lists.immutable.of('a', 'b', 'c'),
                Lists.immutable.of(0, 1, 2));
    }

    @Test
    public void forEachWithIndex()
    {
        StringBuilder sb = new StringBuilder();
        this.zipIterable.forEachWithIndex((each, index) -> {
            sb.append('|');
            sb.append(each.getOne());
            sb.append(each.getTwo());
            sb.append(index);
        });

        Assert.assertEquals("|a00|b11|c22", sb.toString());
    }

    @Test
    public void forEachWith()
    {
        StringBuilder sb = new StringBuilder();
        this.zipIterable.forEachWith((each, argument2) -> {
            sb.append(argument2);
            sb.append(each.getOne());
            sb.append(each.getTwo());
        }, "|");

        Assert.assertEquals("|a0|b1|c2", sb.toString());
    }
}
