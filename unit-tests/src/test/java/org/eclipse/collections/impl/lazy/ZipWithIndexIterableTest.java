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

import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ZipWithIndexIterableTest
{
    private ZipWithIndexIterable<Integer> iterableUnderTest;
    private final StringBuilder buffer = new StringBuilder();

    @Before
    public void setUp()
    {
        this.iterableUnderTest = new ZipWithIndexIterable<>(Lists.immutable.of(1, 2, 3, 4));
    }

    private void assertBufferContains(String expected)
    {
        Assert.assertEquals(expected, this.buffer.toString());
    }

    @Test
    public void forEach()
    {
        this.iterableUnderTest.forEach(Procedures.cast(argument1 -> {
            this.buffer.append("(");
            this.buffer.append(argument1);
            this.buffer.append(")");
        }));
        this.assertBufferContains("(1:0)(2:1)(3:2)(4:3)");
    }

    @Test
    public void forEachWIthIndex()
    {
        this.iterableUnderTest.forEachWithIndex((each, index) -> {
            this.buffer.append("|(");
            this.buffer.append(each);
            this.buffer.append("),");
            this.buffer.append(index);
        });
        this.assertBufferContains("|(1:0),0|(2:1),1|(3:2),2|(4:3),3");
    }

    @Test
    public void forEachWith()
    {
        this.iterableUnderTest.forEachWith((argument1, argument2) -> {
            this.buffer.append("|(");
            this.buffer.append(argument1);
            this.buffer.append("),");
            this.buffer.append(argument2);
        }, "A");
        this.assertBufferContains("|(1:0),A|(2:1),A|(3:2),A|(4:3),A");
    }
}
