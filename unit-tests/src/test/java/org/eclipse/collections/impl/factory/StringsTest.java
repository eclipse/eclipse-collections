/*
 * Copyright (c) 2017 BNY Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import org.eclipse.collections.impl.string.immutable.CharAdapter;
import org.eclipse.collections.impl.string.immutable.CodePointAdapter;
import org.junit.Assert;
import org.junit.Test;

public class StringsTest
{
    @Test
    public void asChars()
    {
        CharAdapter adapter = Strings.asChars("The quick brown fox jumps over the lazy dog.");
        Assert.assertTrue(adapter.contains('T'));
    }

    @Test
    public void toChars()
    {
        CharAdapter adapter = Strings.toChars('H', 'e', 'l', 'l', 'o');
        Assert.assertEquals(2, adapter.count(c -> c == 'l'));
    }

    @Test
    public void asCodePoints()
    {
        CodePointAdapter adapter = Strings.asCodePoints("The quick brown fox jumps over the lazy dog.");
        Assert.assertTrue(adapter.contains((int) 'T'));
    }

    @Test
    public void toCodePoints()
    {
        CodePointAdapter adapter = Strings.toCodePoints((int) 'H', (int) 'e', (int) 'l', (int) 'l', (int) 'o');
        Assert.assertEquals(2, adapter.count(i -> i == (int) 'l'));
    }
}
