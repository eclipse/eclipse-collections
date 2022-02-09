/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.list.mutable;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.fixed.ArrayAdapter;
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.eclipse.collections.test.list.FixedSizeListTestCase;
import org.junit.runner.RunWith;

@RunWith(Java8Runner.class)
public class ArrayAdapterTest implements FixedSizeListTestCase
{
    @SafeVarargs
    @Override
    public final <T> MutableList<T> newWith(T... elements)
    {
        return ArrayAdapter.adapt(elements);
    }
}
