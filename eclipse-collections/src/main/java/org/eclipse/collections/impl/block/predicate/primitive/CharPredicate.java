/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.predicate.primitive;

import java.io.Serializable;

/**
 * A Predicate that accepts a char value
 *
 * @deprecated since 3.0. Use {@link org.eclipse.collections.api.block.predicate.primitive.CharPredicate} instead.
 */
@Deprecated
public interface CharPredicate
        extends Serializable
{
    CharPredicate IS_UPPERCASE = new CharPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(char character)
        {
            return Character.isUpperCase(character);
        }
    };

    CharPredicate IS_LOWERCASE = new CharPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(char character)
        {
            return Character.isLowerCase(character);
        }
    };

    CharPredicate IS_DIGIT = new CharPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(char character)
        {
            return Character.isDigit(character);
        }
    };

    CharPredicate IS_DIGIT_OR_DOT = new CharPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(char character)
        {
            return Character.isDigit(character) || character == '.';
        }
    };

    CharPredicate IS_LETTER = new CharPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(char character)
        {
            return Character.isLetter(character);
        }
    };

    CharPredicate IS_LETTER_OR_DIGIT = new CharPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(char character)
        {
            return Character.isLetterOrDigit(character);
        }
    };

    CharPredicate IS_WHITESPACE = new CharPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(char character)
        {
            return Character.isWhitespace(character);
        }
    };

    CharPredicate IS_UNDEFINED = new CharPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(char character)
        {
            return !Character.isDefined(character);
        }
    };

    boolean accept(char character);
}
