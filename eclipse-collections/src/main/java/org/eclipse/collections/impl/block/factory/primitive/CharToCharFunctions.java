/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory.primitive;

import org.eclipse.collections.api.block.function.primitive.CharToCharFunction;

public final class CharToCharFunctions
{
    private static final CharToCharFunction TO_UPPERCASE = new ToUpperCaseCharToCharFunction();

    private static final CharToCharFunction TO_LOWERCASE = new ToLowerCaseCharToCharFunction();

    private CharToCharFunctions()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static CharToCharFunction toUpperCase()
    {
        return TO_UPPERCASE;
    }

    public static CharToCharFunction toLowerCase()
    {
        return TO_LOWERCASE;
    }

    private static class ToUpperCaseCharToCharFunction implements CharToCharFunction
    {
        private static final long serialVersionUID = 1L;

        @Override
        public char valueOf(char character)
        {
            return Character.toUpperCase(character);
        }
    }

    private static class ToLowerCaseCharToCharFunction implements CharToCharFunction
    {
        private static final long serialVersionUID = 1L;

        @Override
        public char valueOf(char character)
        {
            return Character.toLowerCase(character);
        }
    }
}
