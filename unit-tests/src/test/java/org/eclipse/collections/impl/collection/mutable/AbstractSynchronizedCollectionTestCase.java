/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable;

import org.eclipse.collections.api.collection.MutableCollection;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractSynchronizedCollectionTestCase extends AbstractCollectionTestCase
{
    @Override
    @Test
    public void testToString()
    {
        MutableCollection<Object> collection = this.newWith(1, 2);
        String string = collection.toString();
        Assert.assertTrue("[1, 2]".equals(string) || "[2, 1]".equals(string));
    }

    @Override
    @Test
    public void makeString()
    {
        MutableCollection<Object> collection = this.newWith(1, 2, 3);
        Assert.assertEquals(collection.toString(), '[' + collection.makeString() + ']');
    }

    @Override
    @Test
    public void appendString()
    {
        MutableCollection<Object> collection = this.newWith(1, 2, 3);
        Appendable builder = new StringBuilder();
        collection.appendString(builder);
        Assert.assertEquals(collection.toString(), '[' + builder.toString() + ']');
    }

    @Override
    @Test
    public void asSynchronized()
    {
        MutableCollection<Object> collection = this.newWith();
        Assert.assertSame(collection, collection.asSynchronized());
    }
}
