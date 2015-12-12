/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.utility.internal;

import java.util.Iterator;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;

public final class MutableCollectionIterate
{
    private MutableCollectionIterate()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static <T> RichIterable<RichIterable<T>> chunk(MutableCollection<T> collection, int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Size for groups must be positive but was: " + size);
        }
        Iterator<T> iterator = collection.iterator();
        MutableList<RichIterable<T>> result = Lists.mutable.empty();
        while (iterator.hasNext())
        {
            MutableCollection<T> batch = collection.newEmpty();
            for (int i = 0; i < size && iterator.hasNext(); i++)
            {
                batch.add(iterator.next());
            }
            result.add(batch);
        }
        return result;
    }
}
