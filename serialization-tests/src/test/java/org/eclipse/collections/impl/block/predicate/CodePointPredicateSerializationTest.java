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

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CodePointPredicateSerializationTest
{
    @Test
    public void isUpperCase()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5Db2Rl\n"
                        + "UG9pbnRQcmVkaWNhdGUkMQAAAAAAAAABAgAAeHA=",
                CodePointPredicate.IS_UPPERCASE);
    }

    @Test
    public void isLowerCase()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5Db2Rl\n"
                        + "UG9pbnRQcmVkaWNhdGUkMgAAAAAAAAABAgAAeHA=",
                CodePointPredicate.IS_LOWERCASE);
    }

    @Test
    public void isDigit()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5Db2Rl\n"
                        + "UG9pbnRQcmVkaWNhdGUkMwAAAAAAAAABAgAAeHA=",
                CodePointPredicate.IS_DIGIT);
    }

    @Test
    public void isLetter()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5Db2Rl\n"
                        + "UG9pbnRQcmVkaWNhdGUkNAAAAAAAAAABAgAAeHA=",
                CodePointPredicate.IS_LETTER);
    }

    @Test
    public void isLetterOrDigit()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5Db2Rl\n"
                        + "UG9pbnRQcmVkaWNhdGUkNQAAAAAAAAABAgAAeHA=",
                CodePointPredicate.IS_LETTER_OR_DIGIT);
    }

    @Test
    public void isWhitespace()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5Db2Rl\n"
                        + "UG9pbnRQcmVkaWNhdGUkNgAAAAAAAAABAgAAeHA=",
                CodePointPredicate.IS_WHITESPACE);
    }

    @Test
    public void isDefined()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5Db2Rl\n"
                        + "UG9pbnRQcmVkaWNhdGUkNwAAAAAAAAABAgAAeHA=",
                CodePointPredicate.IS_UNDEFINED);
    }
}
