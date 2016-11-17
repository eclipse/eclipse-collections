/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.list.mutable;

import java.util.Iterator;
import java.util.ListIterator;

import org.eclipse.collections.impl.list.mutable.FastList;

public final class FastListNoIterator<T> extends FastList<T>
{
    @Override
    public Iterator<T> iterator()
    {
        throw new AssertionError("No iteration patterns should delegate to iterator()");
    }

    @Override
    public ListIterator<T> listIterator()
    {
        throw new AssertionError("No iteration patterns should delegate to listIterator()");
    }

    @Override
    public ListIterator<T> listIterator(int index)
    {
        throw new AssertionError("No iteration patterns should delegate to listIterator()");
    }
}
