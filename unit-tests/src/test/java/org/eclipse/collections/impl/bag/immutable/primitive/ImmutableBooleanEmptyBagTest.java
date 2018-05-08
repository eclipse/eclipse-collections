/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.immutable.primitive;

import org.eclipse.collections.api.bag.primitive.ImmutableBooleanBag;
import org.eclipse.collections.api.set.primitive.ImmutableBooleanSet;
import org.eclipse.collections.impl.factory.primitive.BooleanBags;
import org.eclipse.collections.impl.factory.primitive.BooleanSets;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ImmutableBooleanEmptyBag}.
 */
public class ImmutableBooleanEmptyBagTest extends AbstractImmutableBooleanBagTestCase
{
    @Override
    protected final ImmutableBooleanBag classUnderTest()
    {
        return BooleanBags.immutable.of();
    }

    @Override
    @Test
    public void notEmpty()
    {
        Assert.assertFalse(this.classUnderTest().notEmpty());
    }

    @Override
    @Test
    public void isEmpty()
    {
        Verify.assertEmpty(this.newWith());
    }

    @Override
    @Test
    public void size()
    {
        Verify.assertSize(0, this.classUnderTest());
    }

    @Override
    @Test
    public void forEachWithOccurrences()
    {
        StringBuilder stringBuilder = new StringBuilder();
        this.classUnderTest().forEachWithOccurrences((argument1, argument2) -> stringBuilder.append(argument1).append(argument2));
        String string = stringBuilder.toString();
        Assert.assertEquals("", string);
    }

    @Test
    public void occurrencesOf()
    {
        Assert.assertEquals(0, this.classUnderTest().occurrencesOf(true));
        Assert.assertEquals(0, this.classUnderTest().occurrencesOf(false));
    }

    @Override
    @Test
    public void selectUnique()
    {
        super.selectUnique();

        ImmutableBooleanBag bag = this.classUnderTest();
        ImmutableBooleanSet expected = BooleanSets.immutable.empty();
        ImmutableBooleanSet actual = bag.selectUnique();
        Assert.assertEquals(expected, actual);
    }
}
