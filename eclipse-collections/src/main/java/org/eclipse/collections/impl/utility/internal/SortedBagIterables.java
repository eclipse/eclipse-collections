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

import java.util.Comparator;
import java.util.Iterator;

import org.eclipse.collections.api.bag.sorted.SortedBag;

public final class SortedBagIterables
{
    private SortedBagIterables()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static <T> int compare(SortedBag<T> bagA, SortedBag<T> bagB)
    {
        Iterator<T> itrA = bagA.iterator();
        Iterator<T> itrB = bagB.iterator();
        if (bagA.comparator() != null)
        {
            Comparator<? super T> comparator = bagA.comparator();
            while (itrA.hasNext())
            {
                if (itrB.hasNext())
                {
                    int val = comparator.compare(itrA.next(), itrB.next());
                    if (val != 0)
                    {
                        return val;
                    }
                }
                else
                {
                    return 1;
                }
            }
            return itrB.hasNext() ? -1 : 0;
        }

        while (itrA.hasNext())
        {
            if (itrB.hasNext())
            {
                int val = ((Comparable<T>) itrA.next()).compareTo(itrB.next());
                if (val != 0)
                {
                    return val;
                }
            }
            else
            {
                return 1;
            }
        }
        return itrB.hasNext() ? -1 : 0;
    }
}
