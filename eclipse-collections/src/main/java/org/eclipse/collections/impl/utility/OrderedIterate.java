/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.utility;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.impl.utility.internal.RandomAccessListIterate;

/**
 * @since 6.0
 */
public final class OrderedIterate
{
    private OrderedIterate()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static <S, T> boolean corresponds(OrderedIterable<T> o1, OrderedIterable<S> o2, Predicate2<? super T, ? super S> predicate)
    {
        if (o1.size() != o2.size())
        {
            return false;
        }
        if (o1 instanceof RandomAccess)
        {
            return RandomAccessListIterate.corresponds((List<T>) o1, o2, predicate);
        }
        if (o2 instanceof RandomAccess)
        {
            List<S> otherList = (List<S>) o2;
            Iterator<T> iterator = o1.iterator();
            for (int index = 0; index < otherList.size(); index++)
            {
                if (!predicate.accept(iterator.next(), otherList.get(index)))
                {
                    return false;
                }
            }
            return true;
        }

        Iterator<T> iterator1 = o1.iterator();
        Iterator<S> iterator2 = o2.iterator();
        while (iterator1.hasNext())
        {
            if (!predicate.accept(iterator1.next(), iterator2.next()))
            {
                return false;
            }
        }
        return true;
    }
}
