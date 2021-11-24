/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.block.predicate;

import org.eclipse.collections.impl.block.factory.Predicates;
import org.junit.Assert;
import org.junit.Test;

public class PredicateTest
{
    @Test
    public void test()
    {
        Predicate<Object> alwaysTrue = Predicates.alwaysTrue();
        Assert.assertTrue(alwaysTrue.test(Boolean.TRUE));
        Assert.assertTrue(alwaysTrue.test(Boolean.FALSE));

        Predicate<Object> alwaysFalse = Predicates.alwaysFalse();
        Assert.assertFalse(alwaysFalse.test(Boolean.TRUE));
        Assert.assertFalse(alwaysFalse.test(Boolean.FALSE));
    }
}
