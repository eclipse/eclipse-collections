/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bag.mutable;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.test.IterableTestCase;

public class HashBagTest implements MutableBagTestCase
{
    @SafeVarargs
    @Override
    public final <T> MutableBag<T> newWith(T... elements)
    {
        MutableBag<T> result = new HashBag<>();
        IterableTestCase.addAllTo(elements, result);
        return result;
    }
}
