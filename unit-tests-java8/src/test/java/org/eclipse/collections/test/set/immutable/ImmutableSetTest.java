/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.set.immutable;

import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.eclipse.collections.test.IterableTestCase;
import org.junit.runner.RunWith;

@RunWith(Java8Runner.class)
public class ImmutableSetTest implements ImmutableSetTestCase
{
    @SafeVarargs
    @Override
    public final <T> ImmutableSet<T> newWith(T... elements)
    {
        MutableSet<T> result = new UnifiedSet<>();
        IterableTestCase.addAllTo(elements, result);
        return result.toImmutable();
    }
}
