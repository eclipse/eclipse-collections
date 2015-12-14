/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.primitive;

import org.eclipse.collections.api.InternalIterable;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class CollectBooleanToObjectIterableTest
{
    private LazyIterable<Boolean> newPrimitiveWith(boolean... elements)
    {
        return new CollectBooleanToObjectIterable<>(BooleanArrayList.newListWith(elements), Boolean::valueOf);
    }

    @Test
    public void forEach()
    {
        InternalIterable<Boolean> select = this.newPrimitiveWith(true, false, true, false, true);
        Appendable builder = new StringBuilder();
        Procedure<Boolean> appendProcedure = Procedures.append(builder);
        select.forEach(appendProcedure);
        Assert.assertEquals("truefalsetruefalsetrue", builder.toString());
    }

    @Test
    public void forEachWithIndex()
    {
        InternalIterable<Boolean> select = this.newPrimitiveWith(true, false, true, false, true);
        StringBuilder builder = new StringBuilder("");
        select.forEachWithIndex((object, index) -> {
            builder.append(object);
            builder.append(index);
        });
        Assert.assertEquals("true0false1true2false3true4", builder.toString());
    }

    @Test
    public void iterator()
    {
        InternalIterable<Boolean> select = this.newPrimitiveWith(true, false, true, false, true);
        StringBuilder builder = new StringBuilder("");
        for (Boolean each : select)
        {
            builder.append(each);
        }
        Assert.assertEquals("truefalsetruefalsetrue", builder.toString());
    }

    @Test
    public void forEachWith()
    {
        InternalIterable<Boolean> select = this.newPrimitiveWith(true, false, true, false, true);
        StringBuilder builder = new StringBuilder("");
        select.forEachWith((each, aBuilder) -> aBuilder.append(each), builder);
        Assert.assertEquals("truefalsetruefalsetrue", builder.toString());
    }

    @Test
    public void selectInstancesOf()
    {
        Assert.assertEquals(
                FastList.newListWith(true, false, true, false, true),
                this.newPrimitiveWith(true, false, true, false, true).selectInstancesOf(Boolean.class).toList());
    }

    @Test
    public void sizeEmptyNotEmpty()
    {
        Verify.assertIterableSize(2, this.newPrimitiveWith(true, false));
        Verify.assertIterableEmpty(this.newPrimitiveWith());
        Assert.assertTrue(this.newPrimitiveWith(true, false).notEmpty());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeThrows()
    {
        this.newPrimitiveWith().iterator().remove();
    }
}
