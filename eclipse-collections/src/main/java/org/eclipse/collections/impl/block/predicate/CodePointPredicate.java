/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.predicate;

import java.io.Serializable;

/**
 * A Predicate that accepts an int value
 */
public interface CodePointPredicate
        extends Serializable
{
    CodePointPredicate IS_UPPERCASE = new CodePointPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(int codePoint)
        {
            return Character.isUpperCase(codePoint);
        }
    };

    CodePointPredicate IS_LOWERCASE = new CodePointPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(int codePoint)
        {
            return Character.isLowerCase(codePoint);
        }
    };

    CodePointPredicate IS_DIGIT = new CodePointPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(int codePoint)
        {
            return Character.isDigit(codePoint);
        }
    };

    CodePointPredicate IS_LETTER = new CodePointPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(int codePoint)
        {
            return Character.isLetter(codePoint);
        }
    };

    CodePointPredicate IS_LETTER_OR_DIGIT = new CodePointPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(int codePoint)
        {
            return Character.isLetterOrDigit(codePoint);
        }
    };

    CodePointPredicate IS_WHITESPACE = new CodePointPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(int codePoint)
        {
            return Character.isWhitespace(codePoint);
        }
    };

    CodePointPredicate IS_UNDEFINED = new CodePointPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(int codePoint)
        {
            return !Character.isDefined(codePoint);
        }
    };

    CodePointPredicate IS_BMP = new CodePointPredicate()
    {
        private static final long serialVersionUID = 1L;

        public boolean accept(int codePoint)
        {
            return Character.isBmpCodePoint(codePoint);
        }
    };

    boolean accept(int codePoint);
}
