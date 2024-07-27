/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory.primitive;

import org.eclipse.collections.api.list.primitive.CharList;
import org.eclipse.collections.impl.block.predicate.primitive.CharPredicate;
import org.eclipse.collections.impl.factory.primitive.CharLists;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Junit test for {@link CharPredicate}.
 *
 * @deprecated in 6.0
 */
@Deprecated
public class CharPredicateTest
{
    @Test
    public void isUpperCase()
    {
        assertTrueHelper(CharLists.mutable.of('A', 'B', 'C'), CharPredicate.IS_UPPERCASE);
        assertFalseHelper(CharLists.mutable.of('a', 'b', 'c', '1', '.'), CharPredicate.IS_UPPERCASE);
    }

    @Test
    public void isLowerCase()
    {
        assertTrueHelper(CharLists.mutable.of('a', 'b', 'c'), CharPredicate.IS_LOWERCASE);
        assertFalseHelper(CharLists.mutable.of('A', 'B', 'C', '1', '.'), CharPredicate.IS_LOWERCASE);
    }

    @Test
    public void isDigit()
    {
        assertTrueHelper(CharLists.mutable.of('0', '1', '2', '3'), CharPredicate.IS_DIGIT);
        assertFalseHelper(CharLists.mutable.of('A', 'B', 'C', '.'), CharPredicate.IS_DIGIT);
        assertFalseHelper(CharLists.mutable.of('a', 'b', 'c', '.'), CharPredicate.IS_DIGIT);
    }

    @Test
    public void isDigitOrDot()
    {
        assertTrueHelper(CharLists.mutable.of('0', '1', '2', '3', '.'), CharPredicate.IS_DIGIT_OR_DOT);
        assertFalseHelper(CharLists.mutable.of('A', 'B', 'C'), CharPredicate.IS_DIGIT_OR_DOT);
        assertFalseHelper(CharLists.mutable.of('a', 'b', 'c'), CharPredicate.IS_DIGIT_OR_DOT);
    }

    @Test
    public void isLetter()
    {
        assertTrueHelper(CharLists.mutable.of('A', 'B', 'C'), CharPredicate.IS_LETTER);
        assertTrueHelper(CharLists.mutable.of('a', 'b', 'c'), CharPredicate.IS_LETTER);
        assertFalseHelper(CharLists.mutable.of('0', '1', '2', '3', '.'), CharPredicate.IS_LETTER);
    }

    @Test
    public void isLetterOrDigit()
    {
        assertTrueHelper(CharLists.mutable.of('A', 'B', 'C', '0', '1', '2', '3'), CharPredicate.IS_LETTER_OR_DIGIT);
        assertTrueHelper(CharLists.mutable.of('a', 'b', 'c', '0', '1', '2', '3'), CharPredicate.IS_LETTER_OR_DIGIT);
        assertFalseHelper(CharLists.mutable.of('.', '$', '*'), CharPredicate.IS_LETTER_OR_DIGIT);
    }

    @Test
    public void isWhitespace()
    {
        assertTrueHelper(CharLists.mutable.of(' '), CharPredicate.IS_WHITESPACE);
        assertFalseHelper(CharLists.mutable.of('A', 'B', 'C', '0', '1', '2', '3'), CharPredicate.IS_WHITESPACE);
        assertFalseHelper(CharLists.mutable.of('a', 'b', 'c', '0', '1', '2', '3'), CharPredicate.IS_WHITESPACE);
        assertFalseHelper(CharLists.mutable.of('.', '$', '*'), CharPredicate.IS_WHITESPACE);
    }

    @Test
    public void isUndefined()
    {
        assertTrue(CharPredicates.isUndefined().accept((char) 888));
        assertFalseHelper(CharLists.mutable.of('A', 'B', 'C', '0', '1', '2', '3'), CharPredicate.IS_UNDEFINED);
        assertFalseHelper(CharLists.mutable.of('a', 'b', 'c', '0', '1', '2', '3'), CharPredicate.IS_UNDEFINED);
        assertFalseHelper(CharLists.mutable.of('.', '$', '*'), CharPredicate.IS_UNDEFINED);
    }

    private static void assertTrueHelper(CharList charList, CharPredicate predicate)
    {
        charList.forEach(element -> assertTrue(predicate.accept(element)));
    }

    private static void assertFalseHelper(CharList charList, CharPredicate predicate)
    {
        charList.forEach(element -> assertFalse(predicate.accept(element)));
    }
}
