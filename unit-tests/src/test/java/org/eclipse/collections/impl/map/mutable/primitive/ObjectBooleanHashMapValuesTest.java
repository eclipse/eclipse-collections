/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.map.mutable.primitive;

import java.util.NoSuchElementException;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.block.function.primitive.BooleanToObjectFunction;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.collection.mutable.primitive.AbstractMutableBooleanCollectionTestCase;
import org.eclipse.collections.impl.collection.mutable.primitive.SynchronizedBooleanCollection;
import org.eclipse.collections.impl.collection.mutable.primitive.UnmodifiableBooleanCollection;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ObjectBooleanHashMap#values()}.
 */
public class ObjectBooleanHashMapValuesTest extends ObjectBooleanHashMapValuesTestCase
{
    @Override
    protected MutableBooleanCollection classUnderTest()
    {
        return ObjectBooleanHashMap.newWithKeysValues(1, true, 2, false, 3, true).values();
    }

    @Override
    protected MutableBooleanCollection newWith(boolean... elements)
    {
        ObjectBooleanHashMap<Integer> map = new ObjectBooleanHashMap<>();
        for (int i = 0; i < elements.length; i++)
        {
            map.put(i, elements[i]);
        }
        return map.values();
    }

    @Override
    protected MutableBooleanCollection newMutableCollectionWith(boolean... elements)
    {
        return this.newWith(elements);
    }

    @Override
    protected MutableList<Object> newObjectCollectionWith(Object... elements)
    {
        return FastList.newListWith(elements);
    }
}
