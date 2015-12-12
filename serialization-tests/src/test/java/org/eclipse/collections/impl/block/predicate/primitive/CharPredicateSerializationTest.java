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

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CharPredicateSerializationTest
{
    @Test
    public void isUpperCase()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5wcmlt\n"
                        + "aXRpdmUuQ2hhclByZWRpY2F0ZSQxAAAAAAAAAAECAAB4cA==",
                CharPredicate.IS_UPPERCASE);
    }

    @Test
    public void isLowerCase()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5wcmlt\n"
                        + "aXRpdmUuQ2hhclByZWRpY2F0ZSQyAAAAAAAAAAECAAB4cA==",
                CharPredicate.IS_LOWERCASE);
    }

    @Test
    public void isDigit()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5wcmlt\n"
                        + "aXRpdmUuQ2hhclByZWRpY2F0ZSQzAAAAAAAAAAECAAB4cA==",
                CharPredicate.IS_DIGIT);
    }

    @Test
    public void isDigitOrDot()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5wcmlt\n"
                        + "aXRpdmUuQ2hhclByZWRpY2F0ZSQ0AAAAAAAAAAECAAB4cA==",
                CharPredicate.IS_DIGIT_OR_DOT);
    }

    @Test
    public void isLetter()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5wcmlt\n"
                        + "aXRpdmUuQ2hhclByZWRpY2F0ZSQ1AAAAAAAAAAECAAB4cA==",
                CharPredicate.IS_LETTER);
    }

    @Test
    public void isLetterOrDigit()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5wcmlt\n"
                        + "aXRpdmUuQ2hhclByZWRpY2F0ZSQ2AAAAAAAAAAECAAB4cA==",
                CharPredicate.IS_LETTER_OR_DIGIT);
    }

    @Test
    public void isWhitespace()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5wcmlt\n"
                        + "aXRpdmUuQ2hhclByZWRpY2F0ZSQ3AAAAAAAAAAECAAB4cA==",
                CharPredicate.IS_WHITESPACE);
    }

    @Test
    public void isUndefined()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5wcmlt\n"
                        + "aXRpdmUuQ2hhclByZWRpY2F0ZSQ4AAAAAAAAAAECAAB4cA==",
                CharPredicate.IS_UNDEFINED);
    }
}
