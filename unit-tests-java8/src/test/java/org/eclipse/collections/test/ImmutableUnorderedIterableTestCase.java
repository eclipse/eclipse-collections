/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test;

import java.util.Iterator;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public interface ImmutableUnorderedIterableTestCase extends UnorderedIterableTestCase
{
    @Override
    @Test
    default void Iterable_remove()
    {
        Iterator<Integer> iterator = this.newWith(3, 2, 1).iterator();
        iterator.next();
        Verify.assertThrows(UnsupportedOperationException.class, iterator::remove);
    }
}
