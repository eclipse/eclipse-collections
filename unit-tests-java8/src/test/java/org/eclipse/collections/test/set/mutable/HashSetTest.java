/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.set.mutable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.eclipse.collections.test.set.SetTestCase;
import org.junit.runner.RunWith;

@RunWith(Java8Runner.class)
public class HashSetTest
        implements SetTestCase
{
    @SafeVarargs
    @Override
    public final <T> Set<T> newWith(T... elements)
    {
        return new HashSet<>(Arrays.asList(elements));
    }
}
