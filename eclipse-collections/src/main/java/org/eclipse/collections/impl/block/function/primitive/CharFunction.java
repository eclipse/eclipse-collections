/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function.primitive;

import java.io.Serializable;

import org.eclipse.collections.api.block.function.primitive.CharToCharFunction;

/**
 * A CharFunction can be used to convert one character to another.
 *
 * @deprecated since 3.0. Use {@link CharToCharFunction} instead.
 */
@Deprecated
public interface CharFunction
        extends Serializable
{
    CharFunction TO_UPPERCASE = new CharFunction()
    {
        private static final long serialVersionUID = 1L;

        public char valueOf(char character)
        {
            return Character.toUpperCase(character);
        }
    };

    CharFunction TO_LOWERCASE = new CharFunction()
    {
        private static final long serialVersionUID = 1L;

        public char valueOf(char character)
        {
            return Character.toLowerCase(character);
        }
    };

    char valueOf(char character);
}
