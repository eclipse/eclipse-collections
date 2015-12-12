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

import java.util.ListIterator;

import org.eclipse.collections.api.list.MutableList;
import org.junit.Test;

public class MultiReaderFastListAsUnmodifiableTest extends UnmodifiableMutableListTestCase
{
    @Override
    protected MutableList<Integer> getCollection()
    {
        return MultiReaderFastList.newListWith(1).asUnmodifiable();
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void listIterator()
    {
        ListIterator<Integer> it = this.getCollection().listIterator();
        it.next();
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void subListListIterator()
    {
        ListIterator<Integer> it = this.getCollection().subList(0, 1).listIterator();
        it.next();
    }
}
