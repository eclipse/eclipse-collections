/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable;

import org.eclipse.collections.impl.collection.mutable.AbstractCollectionTestCase;
import org.junit.Test;

public abstract class MultiReaderMutableCollectionTestCase extends AbstractCollectionTestCase
{
    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void iterator_throws()
    {
        this.newWith(1, 2, 3).iterator();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void spliterator_throws()
    {
        this.newWith(1, 2, 3).spliterator();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void stream_throws()
    {
        this.newWith(1, 2, 3).stream();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void parallelStream_throws()
    {
        this.newWith(1, 2, 3).parallelStream();
    }
}
