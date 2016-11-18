/*
 * Copyright (c) 2015 Goldman Sachs.
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
import org.junit.Assert;
import org.junit.Test;

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
        assertTrue(CharLists.mutable.of('A', 'B', 'C'), CharPredicate.IS_UPPERCASE);
        assertFalse(CharLists.mutable.of('a', 'b', 'c', '1', '.'), CharPredicate.IS_UPPERCASE);
    }

    @Test
    public void isLowerCase()
    {
        assertTrue(CharLists.mutable.of('a', 'b', 'c'), CharPredicate.IS_LOWERCASE);
        assertFalse(CharLists.mutable.of('A', 'B', 'C', '1', '.'), CharPredicate.IS_LOWERCASE);
    }

    @Test
    public void isDigit()
    {
        assertTrue(CharLists.mutable.of('0', '1', '2', '3'), CharPredicate.IS_DIGIT);
        assertFalse(CharLists.mutable.of('A', 'B', 'C', '.'), CharPredicate.IS_DIGIT);
        assertFalse(CharLists.mutable.of('a', 'b', 'c', '.'), CharPredicate.IS_DIGIT);
    }

    @Test
    public void isDigitOrDot()
    {
        assertTrue(CharLists.mutable.of('0', '1', '2', '3', '.'), CharPredicate.IS_DIGIT_OR_DOT);
        assertFalse(CharLists.mutable.of('A', 'B', 'C'), CharPredicate.IS_DIGIT_OR_DOT);
        assertFalse(CharLists.mutable.of('a', 'b', 'c'), CharPredicate.IS_DIGIT_OR_DOT);
    }

    @Test
    public void isLetter()
    {
        assertTrue(CharLists.mutable.of('A', 'B', 'C'), CharPredicate.IS_LETTER);
        assertTrue(CharLists.mutable.of('a', 'b', 'c'), CharPredicate.IS_LETTER);
        assertFalse(CharLists.mutable.of('0', '1', '2', '3', '.'), CharPredicate.IS_LETTER);
    }

    @Test
    public void isLetterOrDigit()
    {
        assertTrue(CharLists.mutable.of('A', 'B', 'C', '0', '1', '2', '3'), CharPredicate.IS_LETTER_OR_DIGIT);
        assertTrue(CharLists.mutable.of('a', 'b', 'c', '0', '1', '2', '3'), CharPredicate.IS_LETTER_OR_DIGIT);
        assertFalse(CharLists.mutable.of('.', '$', '*'), CharPredicate.IS_LETTER_OR_DIGIT);
    }

    @Test
    public void isWhitespace()
    {
        assertTrue(CharLists.mutable.of(' '), CharPredicate.IS_WHITESPACE);
        assertFalse(CharLists.mutable.of('A', 'B', 'C', '0', '1', '2', '3'), CharPredicate.IS_WHITESPACE);
        assertFalse(CharLists.mutable.of('a', 'b', 'c', '0', '1', '2', '3'), CharPredicate.IS_WHITESPACE);
        assertFalse(CharLists.mutable.of('.', '$', '*'), CharPredicate.IS_WHITESPACE);
    }

    @Test
    public void isUndefined()
    {
        Assert.assertTrue(CharPredicates.isUndefined().accept((char) 888));
        assertFalse(CharLists.mutable.of('A', 'B', 'C', '0', '1', '2', '3'), CharPredicate.IS_UNDEFINED);
        assertFalse(CharLists.mutable.of('a', 'b', 'c', '0', '1', '2', '3'), CharPredicate.IS_UNDEFINED);
        assertFalse(CharLists.mutable.of('.', '$', '*'), CharPredicate.IS_UNDEFINED);
    }

    private static void assertTrue(CharList charList, CharPredicate predicate)
    {
        charList.forEach(element -> Assert.assertTrue(predicate.accept(element)));
    }

    private static void assertFalse(CharList charList, CharPredicate predicate)
    {
        charList.forEach(element -> Assert.assertFalse(predicate.accept(element)));
    }
}
