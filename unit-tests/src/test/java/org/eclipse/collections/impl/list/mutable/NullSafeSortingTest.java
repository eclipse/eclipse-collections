/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.junit.Test;

public class NullSafeSortingTest
{
    @Test
    public void emptyFastList()
    {
        Lists.mutable.of().sortThis(null);
    }

    @Test
    public void fastListWithOneElement()
    {
        FastList.newListWith(1).sortThis(null);
    }

    @Test
    public void fastListWithThreeElements()
    {
        FastList.newListWith(1, 2, 3).sortThis(null);
    }

    @Test
    public void arrayIterate()
    {
        Object[] array0 = {};
        ArrayIterate.sort(array0, 0, null);

        Object[] array1 = {1};
        ArrayIterate.sort(array1, 1, null);

        Object[] array2 = {1, 2};
        ArrayIterate.sort(array2, 2, null);
    }

    @Test
    public void emptyArrayListAdapter()
    {
        ListAdapter.adapt(new ArrayList<>()).sortThis(null);
    }

    @Test
    public void arrayListAdapterWithOneElement()
    {
        MutableList<Integer> list = ListAdapter.adapt(new ArrayList<>());
        list.add(1);
        list.sortThis(null);
    }

    @Test
    public void arrayListAdapterWithThreeElements()
    {
        MutableList<Integer> list = ListAdapter.adapt(new ArrayList<>());
        list.add(1);
        list.add(2);
        list.add(3);
        list.sortThis(null);
    }

    @Test
    public void adaptedSingletonList()
    {
        ListAdapter.adapt(Collections.singletonList(1)).sortThis(null);
    }

    @Test
    public void adaptedCustomList()
    {
        MutableList<Integer> adapt = ListAdapter.adapt(new CustomList<>(1, 2));
        adapt.sortThis(null);
    }

    @Test
    public void emptyList()
    {
        Lists.fixedSize.of().sortThis(null);
    }

    @Test
    public void singletonList()
    {
        Lists.fixedSize.of(1).sortThis(null);
    }

    @Test
    public void doubletonList()
    {
        Lists.fixedSize.of(1, 2).sortThis(null);
    }

    private static final class CustomList<E>
            extends AbstractList<E>
            implements RandomAccess, Serializable
    {
        private static final long serialVersionUID = 1L;
        private final List<E> delegate;

        private CustomList(E obj1, E obj2)
        {
            this.delegate = Lists.fixedSize.of(obj1, obj2);
        }

        @Override
        public int size()
        {
            return this.delegate.size();
        }

        @Override
        public E get(int index)
        {
            return this.delegate.get(index);
        }

        @Override
        public E set(int index, E element)
        {
            return this.delegate.set(index, element);
        }
    }
}
