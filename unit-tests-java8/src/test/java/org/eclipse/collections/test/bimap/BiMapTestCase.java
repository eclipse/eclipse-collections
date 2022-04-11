/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bimap;

import org.eclipse.collections.api.bimap.BiMap;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.test.RichIterableUniqueTestCase;
import org.eclipse.collections.test.map.MapIterableTestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;

public interface BiMapTestCase extends RichIterableUniqueTestCase, MapIterableTestCase
{
    @Override
    <T> BiMap<Object, T> newWith(T... elements);

    @Override
    <K, V> BiMap<K, V> newWithKeysValues(Object... elements);

    @Override
    default boolean allowsDuplicates()
    {
        return false;
    }

    @Test
    default void Iterable_sanity_check()
    {
        // Intentionally blank
    }

    @Override
    default void Iterable_toString()
    {
        RichIterableUniqueTestCase.super.Iterable_toString();

        BiMap<String, Integer> bimap = this.newWithKeysValues("Two", 2, "One", 1);
        Assert.assertEquals("{Two=2, One=1}", bimap.toString());
        Assert.assertEquals("[Two, One]", bimap.keysView().toString());
        Assert.assertEquals("[2, 1]", bimap.valuesView().toString());
        Assert.assertEquals("[Two:2, One:1]", bimap.keyValuesView().toString());
        Assert.assertEquals("[2, 1]", bimap.asLazy().toString());

        Assert.assertEquals(
                "{10=4, 9=4, 8=4, 7=4, 6=3, 5=3, 4=3, 3=2, 2=2, 1=1}",
                this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1).toString());
        Assert.assertEquals(
                "[10, 9, 8, 7, 6, 5, 4, 3, 2, 1]",
                this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1).keysView().toString());
        Assert.assertEquals(
                "[4, 4, 4, 4, 3, 3, 3, 2, 2, 1]",
                this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1).valuesView().toString());
        Assert.assertEquals(
                "[10:4, 9:4, 8:4, 7:4, 6:3, 5:3, 4:3, 3:2, 2:2, 1:1]",
                this.newWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1).keyValuesView().toString());
    }

    @Override
    default void MapIterable_forEachKeyValue()
    {
        BiMap<Object, Integer> bimap = this.newWith(3, 2, 1);
        MutableCollection<Integer> forEachKeyValue = this.newMutableForFilter();
        bimap.forEachKeyValue((key, value) -> forEachKeyValue.add(value + 10));
        assertEquals(this.newMutableForFilter(13, 13, 13, 12, 12, 11), forEachKeyValue);

        BiMap<Integer, String> bimap2 = this.newWithKeysValues(3, "Three", 2, "Two", 1, "One");
        MutableCollection<String> forEachKeyValue2 = this.newMutableForFilter();
        bimap2.forEachKeyValue((key, value) -> forEachKeyValue2.add(key + value));
        assertEquals(this.newMutableForFilter("3Three", "2Two", "1One"), forEachKeyValue2);

        MutableCollection<Integer> forEachValue = this.newMutableForFilter();
        bimap.forEachValue(value -> forEachValue.add(value + 10));
        assertEquals(this.newMutableForFilter(13, 13, 13, 12, 12, 11), forEachValue);

        MutableCollection<Object> forEachKey = this.newMutableForFilter();
        bimap2.forEachKey(key -> forEachKey.add(key + 1));
        assertEquals(this.newMutableForFilter(4, 3, 2), forEachKey);
    }

    @Override
    default void MapIterable_flipUniqueValues()
    {
        BiMap<String, Integer> bimap = this.newWithKeysValues("Three", 3, "Two", 2, "One", 1);
        BiMap<Integer, String> result = bimap.flipUniqueValues();

        assertEquals(
                this.newWithKeysValues(3, "Three", 2, "Two", 1, "One"),
                result);
    }

    @Override
    default void RichIterable_size()
    {
        RichIterableUniqueTestCase.super.RichIterable_size();
    }

    default void BiMap_toList()
    {
        BiMap<Object, Integer> iterable = this.newWith(4, 3, 2, 1);

        {
            MutableList<Integer> target = Lists.mutable.empty();
            iterable.forEachValue(target::add);
            assertEquals(
                    target,
                    iterable.toList());
        }

        MutableList<Integer> target = Lists.mutable.empty();
        iterable.forEachKeyValue((key, value) -> target.add(value));
        assertEquals(
                target,
                iterable.toList());
    }
}
