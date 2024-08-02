/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.immutable.primitive;

import java.util.NoSuchElementException;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.collection.primitive.ImmutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.set.primitive.ImmutableBooleanSet;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.collection.immutable.primitive.AbstractImmutableBooleanCollectionTestCase;
import org.eclipse.collections.impl.factory.primitive.BooleanBags;
import org.eclipse.collections.impl.factory.primitive.BooleanSets;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link ImmutableBooleanEmptySet}.
 */
public class ImmutableBooleanEmptySetTest extends AbstractImmutableBooleanCollectionTestCase
{
    @Override
    protected ImmutableBooleanCollection newWith(boolean... elements)
    {
        return BooleanSets.immutable.with(elements);
    }

    @Override
    protected MutableBooleanCollection newMutableCollectionWith(boolean... elements)
    {
        return BooleanSets.mutable.with(elements);
    }

    @Override
    protected RichIterable<Object> newObjectCollectionWith(Object... elements)
    {
        return Sets.immutable.with(elements);
    }

    @Override
    protected final ImmutableBooleanSet classUnderTest()
    {
        return BooleanSets.immutable.empty();
    }

    @Override
    @Test
    public void notEmpty()
    {
        assertFalse(this.classUnderTest().notEmpty());
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
    public void testNewWith()
    {
        assertEquals(BooleanSets.immutable.with(true), this.classUnderTest().newWith(true));
    }

    @Override
    @Test
    public void newWithAll()
    {
        assertEquals(BooleanSets.immutable.with(true), this.classUnderTest().newWithAll(BooleanSets.mutable.with(true)));
    }

    @Override
    @Test
    public void testEquals()
    {
        Verify.assertEqualsAndHashCode(this.newMutableCollectionWith(), this.classUnderTest());
        Verify.assertPostSerializedIdentity(this.newWith());
        assertNotEquals(this.classUnderTest(), this.newWith(false, false, false, true));
        assertNotEquals(this.classUnderTest(), this.newWith(true));
    }

    @Override
    @Test
    public void forEach()
    {
        this.classUnderTest().forEach(each -> {
            throw new RuntimeException();
        });
    }

    @Override
    @Test
    public void newCollectionWith()
    {
    }

    @Override
    @Test
    public void newWithout()
    {
        assertEquals(BooleanSets.mutable.empty(), this.classUnderTest().newWithout(true));
    }

    @Override
    @Test
    public void injectInto()
    {
        this.classUnderTest().injectInto(null, (object, bool) -> {
            throw new RuntimeException();
        });
    }

    @Override
    @Test
    public void toBag()
    {
        assertEquals(BooleanBags.mutable.empty(), this.classUnderTest().toBag());
    }

    @Override
    @Test
    public void count()
    {
        assertEquals(0, this.classUnderTest().count(BooleanPredicates.alwaysTrue()));
        assertEquals(0, this.classUnderTest().count(BooleanPredicates.alwaysFalse()));
    }

    @Override
    @Test
    public void booleanIterator()
    {
        assertFalse(this.classUnderTest().booleanIterator().hasNext());
        assertThrows(NoSuchElementException.class, () -> this.classUnderTest().booleanIterator().next());
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        assertTrue(this.classUnderTest().noneSatisfy(BooleanPredicates.alwaysTrue()));
        assertTrue(this.classUnderTest().noneSatisfy(BooleanPredicates.alwaysFalse()));
    }

    @Test
    public void cartesianProduct()
    {
        assertEquals(Sets.immutable.empty(), this.classUnderTest().cartesianProduct(BooleanSets.immutable.with(true)).toSet());
        assertEquals(Sets.immutable.empty(), this.classUnderTest().cartesianProduct(BooleanSets.immutable.with(false)).toSet());
        assertEquals(Sets.immutable.empty(), this.classUnderTest().cartesianProduct(BooleanSets.immutable.with(true, false)).toSet());
        assertEquals(Sets.immutable.empty(), this.classUnderTest().cartesianProduct(BooleanSets.immutable.empty()).toSet());
    }
}
