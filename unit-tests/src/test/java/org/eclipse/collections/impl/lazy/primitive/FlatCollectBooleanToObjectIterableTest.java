/*
 * Copyright (c) 2024 Goldman Sachs and others.
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
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlatCollectBooleanToObjectIterableTest
{
    private LazyIterable<Boolean> newPrimitiveWith(boolean... elements)
    {
        return new FlatCollectBooleanToObjectIterable<>(BooleanArrayList.newListWith(elements), Lists.mutable::with);
    }

    @Test
    public void forEach()
    {
        InternalIterable<Boolean> select = this.newPrimitiveWith(true, false, true, false, true);
        Appendable builder = new StringBuilder();
        Procedure<Boolean> appendProcedure = Procedures.append(builder);
        select.forEach(appendProcedure);
        assertEquals("truefalsetruefalsetrue", builder.toString());
    }

    @Test
    public void forEachWithIndex()
    {
        InternalIterable<Boolean> select = this.newPrimitiveWith(true, false, true, false, true);
        StringBuilder builder = new StringBuilder();
        select.forEachWithIndex((object, index) -> {
            builder.append(object);
            builder.append(index);
        });
        assertEquals("true0false1true2false3true4", builder.toString());
    }

    @Test
    public void iterator()
    {
        InternalIterable<Boolean> select = this.newPrimitiveWith(true, false, true, false, true);
        StringBuilder builder = new StringBuilder();
        for (Boolean each : select)
        {
            builder.append(each);
        }
        assertEquals("truefalsetruefalsetrue", builder.toString());
    }

    @Test
    public void forEachWith()
    {
        InternalIterable<Boolean> select = this.newPrimitiveWith(true, false, true, false, true);
        StringBuilder builder = new StringBuilder();
        select.forEachWith((each, aBuilder) -> aBuilder.append(each), builder);
        assertEquals("truefalsetruefalsetrue", builder.toString());
    }

    @Test
    public void selectInstancesOf()
    {
        assertEquals(
                FastList.newListWith(true, false, true, false, true),
                this.newPrimitiveWith(true, false, true, false, true).selectInstancesOf(Boolean.class).toList());
    }

    @Test
    public void sizeEmptyNotEmpty()
    {
        Verify.assertIterableSize(2, this.newPrimitiveWith(true, false));
        Verify.assertIterableEmpty(this.newPrimitiveWith());
        assertTrue(this.newPrimitiveWith(true, false).notEmpty());
    }

    @Test
    public void removeThrows()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newPrimitiveWith().iterator().remove());
    }

    @Test
    public void detect()
    {
        assertEquals(Boolean.TRUE, this.newPrimitiveWith(true, false).detect(Predicates.equal(Boolean.TRUE)));
        assertNull(this.newPrimitiveWith(true).detect(Predicates.equal(Boolean.FALSE)));
    }

    @Test
    public void detectOptional()
    {
        assertEquals(Boolean.TRUE, this.newPrimitiveWith(true, false).detectOptional(Predicates.equal(Boolean.TRUE)).get());
        assertFalse(this.newPrimitiveWith(true).detectOptional(Predicates.equal(Boolean.FALSE)).isPresent());
    }

    @Test
    public void anySatisfy()
    {
        assertTrue(this.newPrimitiveWith(true).anySatisfy(Predicates.equal(Boolean.TRUE)));
        assertFalse(this.newPrimitiveWith(true).anySatisfy(Predicates.equal(Boolean.FALSE)));
    }

    @Test
    public void anySatisfyWith()
    {
        assertTrue(this.newPrimitiveWith(true).anySatisfyWith(Predicates2.equal(), Boolean.TRUE));
        assertFalse(this.newPrimitiveWith(true).anySatisfyWith(Predicates2.equal(), Boolean.FALSE));
    }

    @Test
    public void allSatisfy()
    {
        assertFalse(this.newPrimitiveWith(true, false).allSatisfy(Predicates.equal(Boolean.TRUE)));
        assertTrue(this.newPrimitiveWith(true).allSatisfy(Predicates.equal(Boolean.TRUE)));
    }

    @Test
    public void allSatisfyWith()
    {
        assertFalse(this.newPrimitiveWith(true, false).allSatisfyWith(Predicates2.equal(), Boolean.TRUE));
        assertTrue(this.newPrimitiveWith(true).allSatisfyWith(Predicates2.equal(), Boolean.TRUE));
    }

    @Test
    public void noneSatisfy()
    {
        assertFalse(this.newPrimitiveWith(true).noneSatisfy(Predicates.equal(Boolean.TRUE)));
        assertTrue(this.newPrimitiveWith(false).noneSatisfy(Predicates.equal(Boolean.TRUE)));
    }

    @Test
    public void noneSatisfyWith()
    {
        assertFalse(this.newPrimitiveWith(true).noneSatisfyWith(Predicates2.equal(), Boolean.TRUE));
        assertTrue(this.newPrimitiveWith(false).noneSatisfyWith(Predicates2.equal(), Boolean.TRUE));
    }
}
