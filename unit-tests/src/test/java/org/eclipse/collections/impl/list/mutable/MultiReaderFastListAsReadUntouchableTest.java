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

import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.list.MutableList;
import org.junit.Assert;
import org.junit.Test;

public class MultiReaderFastListAsReadUntouchableTest extends UnmodifiableMutableListTestCase
{
    @Override
    protected MutableList<Integer> getCollection()
    {
        return MultiReaderFastList.newListWith(1).asReadUntouchable();
    }

    @Override
    @Test
    public void serialization()
    {
        MutableCollection<Integer> collection = this.getCollection();
        Assert.assertFalse(collection instanceof Serializable);
    }
}
