/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bimap;

import org.eclipse.collections.api.bimap.BiMap;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.test.RichIterableUniqueTestCase;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;

public interface BiMapTestCase extends RichIterableUniqueTestCase
{
    @Override
    <T> BiMap<Object, T> newWith(T... elements);

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
