/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable;

import org.eclipse.collections.impl.collection.mutable.AbstractCollectionTestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class MultiReaderMutableCollectionTestCase extends AbstractCollectionTestCase
{
    @Override
    @Test
    public void iterator_throws()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).iterator());
    }

    @Test
    public void spliterator_throws()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).spliterator());
    }

    @Test
    public void stream_throws()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).stream());
    }

    @Test
    public void parallelStream_throws()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newWith(1, 2, 3).parallelStream());
    }
}
