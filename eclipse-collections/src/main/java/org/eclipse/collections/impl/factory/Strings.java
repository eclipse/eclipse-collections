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

/**
 * This class can be used to create instances of CharAdapter and CodePointAdapter.
 * <p/>
 * <pre>
 * CharAdapter chars1 = Strings.asChars("Hello World!");
 * CharAdapter chars2 = Strings.toChars('a', 'b', 'c');
 *
 * CodePointAdapter codePoints1 = Strings.asCodePoints("Hello World!");
 * CodePointAdapter codePoints2 = Strings.toCodePoints((int)'a', (int)'b', (int)'c');
 * </pre>
 */
public final class Strings
{
    private Strings()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static CharAdapter toChars(char... array)
    {
        return CharAdapter.from(array);
    }

    public static CharAdapter asChars(String string)
    {
        return CharAdapter.adapt(string);
    }

    public static CodePointAdapter toCodePoints(int... array)
    {
        return CodePointAdapter.from(array);
    }

    public static CodePointAdapter asCodePoints(String string)
    {
        return CodePointAdapter.adapt(string);
    }
}
