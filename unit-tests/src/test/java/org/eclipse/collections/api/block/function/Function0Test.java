/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.block.function;

import org.eclipse.collections.impl.block.factory.Functions0;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Function0Test
{
    @Test
    public void get()
    {
        Function0<Boolean> alwaysTrueFunction = Functions0.getTrue();
        assertTrue(alwaysTrueFunction.get());

        Function0<Boolean> alwaysFalseFunction = Functions0.getFalse();
        assertFalse(alwaysFalseFunction.get());
    }
}
