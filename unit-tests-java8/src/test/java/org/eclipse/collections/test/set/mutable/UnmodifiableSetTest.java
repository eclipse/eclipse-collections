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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.collections.test.set.UnmodifiableSetTestCase;

public class UnmodifiableSetTest
        implements UnmodifiableSetTestCase
{
    @SafeVarargs
    @Override
    public final <T> Set<T> newWith(T... elements)
    {
        Set<T> result = new HashSet<>(Arrays.asList(elements));
        return Collections.unmodifiableSet(result);
    }
}
