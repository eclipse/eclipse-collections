/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.utility;

import java.util.ArrayList;

import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

/**
 * JUnit test for {@link ArrayListIterate}.
 */
public class ArrayListIterateAcceptanceTest
{
    @Test
    public void testSortOnListWithMoreThan10Elements()
    {
        ArrayList<Integer> integers = new ArrayList<>(Interval.toReverseList(1, 10000));
        Verify.assertStartsWith(ArrayListIterate.sortThis(integers), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Verify.assertEndsWith(integers, 9997, 9998, 9999, 10000);
    }
}
