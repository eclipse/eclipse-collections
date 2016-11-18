/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.immutable.primitive;

import org.eclipse.collections.api.collection.primitive.ImmutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.impl.collection.mutable.primitive.AbstractBooleanIterableTestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Abstract JUnit test for {@link ImmutableBooleanCollection}s.
 */
public abstract class AbstractImmutableBooleanCollectionTestCase extends AbstractBooleanIterableTestCase
{
    @Override
    protected abstract ImmutableBooleanCollection classUnderTest();

    @Override
    protected abstract ImmutableBooleanCollection newWith(boolean... elements);

    @Override
    protected abstract MutableBooleanCollection newMutableCollectionWith(boolean... elements);

    protected void assertSizeAndOccurrences(ImmutableBooleanCollection collection, int expectedTrueCount, int expectedFalseCount)
    {
        int trueCount = 0;
        int falseCount = 0;
        for (boolean b : collection.toArray())
        {
            if (b)
            {
                trueCount++;
            }
            else
            {
                falseCount++;
            }
        }
        Assert.assertEquals(expectedTrueCount, trueCount);
        Assert.assertEquals(expectedFalseCount, falseCount);
    }

    @Test
    public void testNewWith()
    {
        ImmutableBooleanCollection immutableCollection = this.newWith();
        ImmutableBooleanCollection collection = immutableCollection.newWith(true);
        ImmutableBooleanCollection collection0 = immutableCollection.newWith(true).newWith(false);
        ImmutableBooleanCollection collection1 = immutableCollection.newWith(true).newWith(false).newWith(true);
        ImmutableBooleanCollection collection2 = immutableCollection.newWith(true).newWith(false).newWith(true).newWith(false);
        ImmutableBooleanCollection collection3 = immutableCollection.newWith(true).newWith(false).newWith(true).newWith(false).newWith(true);
        this.assertSizeAndOccurrences(immutableCollection, 0, 0);
        this.assertSizeAndOccurrences(collection, 1, 0);
        this.assertSizeAndOccurrences(collection0, 1, 1);
        this.assertSizeAndOccurrences(collection1, 2, 1);
        this.assertSizeAndOccurrences(collection2, 2, 2);
        this.assertSizeAndOccurrences(collection3, 3, 2);
    }

    @Test
    public void newWithAll()
    {
        ImmutableBooleanCollection immutableCollection = this.newWith();
        ImmutableBooleanCollection collection = immutableCollection.newWithAll(this.newMutableCollectionWith(true));
        ImmutableBooleanCollection collection0 = collection.newWithAll(this.newMutableCollectionWith(false));
        ImmutableBooleanCollection collection1 = collection0.newWithAll(this.newMutableCollectionWith(true));
        ImmutableBooleanCollection collection2 = immutableCollection.newWithAll(this.newMutableCollectionWith(true, false, true, false));
        ImmutableBooleanCollection collection3 = immutableCollection.newWithAll(this.newMutableCollectionWith(true, false, true, false, true));
        this.assertSizeAndOccurrences(immutableCollection, 0, 0);
        this.assertSizeAndOccurrences(collection, 1, 0);
        this.assertSizeAndOccurrences(collection0, 1, 1);
        this.assertSizeAndOccurrences(collection1, 2, 1);
        this.assertSizeAndOccurrences(collection2, 2, 2);
        this.assertSizeAndOccurrences(collection3, 3, 2);
    }

    @Test
    public void newWithout()
    {
        ImmutableBooleanCollection collection3 = this.newWith(true, false, true, false, true);
        ImmutableBooleanCollection collection2 = collection3.newWithout(true);
        ImmutableBooleanCollection collection1 = collection2.newWithout(false);
        ImmutableBooleanCollection collection0 = collection1.newWithout(true);
        ImmutableBooleanCollection collection4 = collection0.newWithout(false);
        ImmutableBooleanCollection collection5 = collection4.newWithout(true);
        ImmutableBooleanCollection collection6 = collection5.newWithout(false);

        this.assertSizeAndOccurrences(collection6, 0, 0);
        this.assertSizeAndOccurrences(collection5, 0, 0);
        this.assertSizeAndOccurrences(collection4, 1, 0);
        this.assertSizeAndOccurrences(collection0, 1, 1);
        this.assertSizeAndOccurrences(collection1, 2, 1);
        this.assertSizeAndOccurrences(collection2, 2, 2);
    }

    @Test
    public void newWithoutAll()
    {
        ImmutableBooleanCollection collection3 = this.newWith(true, false, true, true);
        ImmutableBooleanCollection collection2 = collection3.newWithoutAll(this.newMutableCollectionWith(true));
        ImmutableBooleanCollection collection1 = collection2.newWithoutAll(this.newMutableCollectionWith(false));
        ImmutableBooleanCollection collection0 = collection1.newWithoutAll(this.newMutableCollectionWith(true));
        ImmutableBooleanCollection collection4 = collection0.newWithoutAll(this.newMutableCollectionWith(false));

        this.assertSizeAndOccurrences(collection2, 0, 1);
        this.assertSizeAndOccurrences(collection1, 0, 0);
        this.assertSizeAndOccurrences(collection0, 0, 0);
        this.assertSizeAndOccurrences(collection4, 0, 0);
    }
}
