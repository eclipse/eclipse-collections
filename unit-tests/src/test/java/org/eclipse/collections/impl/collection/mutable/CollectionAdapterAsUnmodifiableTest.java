/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.partition.PartitionMutableCollection;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Functions2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CollectionAdapterAsUnmodifiableTest extends UnmodifiableMutableCollectionTestCase<Integer>
{
    @Override
    protected MutableCollection<Integer> getCollection()
    {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        return new CollectionAdapter<>(list).asUnmodifiable();
    }

    @Override
    @Test
    public void select()
    {
        assertEquals(this.getCollection().toList(), this.getCollection().select(ignored -> true));
        assertNotEquals(this.getCollection().toList(), this.getCollection().select(ignored -> false));
    }

    @Override
    @Test
    public void selectWith()
    {
        assertEquals(this.getCollection().toList(), this.getCollection().selectWith((ignored1, ignored2) -> true, null));
        assertNotEquals(this.getCollection().toList(), this.getCollection().selectWith((ignored1, ignored2) -> false, null));
    }

    @Override
    @Test
    public void reject()
    {
        assertEquals(this.getCollection().toList(), this.getCollection().reject(ignored1 -> false));
        assertNotEquals(this.getCollection().toList(), this.getCollection().reject(ignored -> true));
    }

    @Override
    @Test
    public void rejectWith()
    {
        assertEquals(this.getCollection().toList(), this.getCollection().rejectWith((ignored11, ignored21) -> false, null));
        assertNotEquals(this.getCollection().toList(), this.getCollection().rejectWith((ignored1, ignored2) -> true, null));
    }

    @Override
    @Test
    public void partition()
    {
        PartitionMutableCollection<?> partition = this.getCollection().partition(ignored -> true);
        assertEquals(this.getCollection().toList(), partition.getSelected());
        assertNotEquals(this.getCollection().toList(), partition.getRejected());
    }

    @Override
    @Test
    public void partitionWith()
    {
        PartitionMutableCollection<?> partition = this.getCollection().partitionWith((ignored1, ignored2) -> true, null);
        assertEquals(this.getCollection().toList(), partition.getSelected());
        assertNotEquals(this.getCollection().toList(), partition.getRejected());
    }

    @Override
    @Test
    public void collect()
    {
        assertEquals(this.getCollection().toList(), this.getCollection().collect(Functions.getPassThru()));
        assertNotEquals(this.getCollection().toList(), this.getCollection().collect(Object::getClass));
    }

    @Override
    @Test
    public void collectWith()
    {
        assertEquals(this.getCollection().toList(), this.getCollection().collectWith(Functions2.fromFunction(Functions.getPassThru()), null));
        assertNotEquals(this.getCollection().toList(), this.getCollection().collectWith(Functions2.fromFunction(Object::getClass), null));
    }

    @Override
    @Test
    public void collectIf()
    {
        assertEquals(this.getCollection().toList(), this.getCollection().collectIf(ignored -> true, Functions.getPassThru()));
        assertNotEquals(this.getCollection().toList(), this.getCollection().collectIf(ignored -> false, Object::getClass));
    }
}
