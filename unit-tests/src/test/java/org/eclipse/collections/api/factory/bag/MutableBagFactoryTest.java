/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.bag;

import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class MutableBagFactoryTest
{
    private final MutableBagFactory mutableBagFactory = Bags.mutable;

    @Test
    public void with()
    {
        Verify.assertEmpty(this.mutableBagFactory.with());
    }

    @Test
    public void of()
    {
        Verify.assertEmpty(this.mutableBagFactory.of());
    }
}
