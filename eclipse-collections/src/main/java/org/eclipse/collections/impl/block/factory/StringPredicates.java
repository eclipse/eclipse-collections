/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory;

import org.eclipse.collections.impl.block.predicate.CodePointPredicate;
import org.eclipse.collections.impl.utility.StringIterate;

/**
 * The StringPredicates class is a factory that produces Predicates that work with Strings.
 */
public final class StringPredicates
{
    private static final Predicates<String> EMPTY = new Empty();

    private static final Predicates<String> NOT_EMPTY = new NotEmpty();
    private static final Predicates<String> HAS_LETTERS = new HasLetters();
    private static final Predicates<String> HAS_DIGITS = new HasDigits();
    private static final Predicates<String> HAS_LETTERS_OR_DIGITS = new HasLettersOrDigits();
    private static final Predicates<String> HAS_LETTERS_AND_DIGITS = new HasLettersAndDigits();
    private static final Predicates<String> HAS_SPACES = new HasSpaces();
    private static final Predicates<String> HAS_UPPERCASE = new HasUppercase();
    private static final Predicates<String> HAS_LOWERCASE = new HasLowercase();
    private static final Predicates<String> HAS_UNDEFINED = new HasUndefined();
    private static final Predicates<String> IS_NUMERIC = new IsNumeric();
    private static final Predicates<String> IS_ALPHANUMERIC = new IsAlphanumeric();
    private static final Predicates<String> IS_BLANK = new IsBlank();
    private static final Predicates<String> NOT_BLANK = new NotBlank();
    private static final Predicates<String> IS_ALPHA = new IsAlpha();

    private StringPredicates()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static Predicates<String> empty()
    {
        return EMPTY;
    }

    public static Predicates<String> notEmpty()
    {
        return NOT_EMPTY;
    }

    /**
     * Returns true if a String specified on the predicate is contained within a String passed to the accept
     * method.
     */
    public static Predicates<String> contains(String otherString)
    {
        return new ContainsString(otherString);
    }

    /**
     * Returns true if a String specified on the predicate is contained within a String passed to the accept
     * method.
     *
     * @since 5.0
     */
    public static Predicates<String> notContains(String otherString)
    {
        return new NotContainsString(otherString);
    }

    /**
     * Returns true if a char specified on the predicate is contained within a String passed to the accept
     * method.
     */
    public static Predicates<String> contains(char character)
    {
        return new ContainsCharacter(character);
    }

    /**
     * Returns true if a String passed to the accept method starts with the string specified on the predicate.
     */
    public static Predicates<String> startsWith(String substring)
    {
        return new StartsWith(substring);
    }

    /**
     * Returns true if a String passed to the accept method ends with the string specified on the predicate.
     */
    public static Predicates<String> endsWith(String substring)
    {
        return new EndsWith(substring);
    }

    public static Predicates<String> size(int size)
    {
        return new Size(size);
    }

    public static Predicates<String> equalsIgnoreCase(String otherString)
    {
        return new EqualsIgnoreCase(otherString);
    }

    public static Predicates<String> matches(String regex)
    {
        return new MatchesRegex(regex);
    }

    public static Predicates<String> lessThan(String string)
    {
        return new LessThan(string);
    }

    public static Predicates<String> lessThanOrEqualTo(String string)
    {
        return new LessThanOrEqualTo(string);
    }

    public static Predicates<String> greaterThan(String string)
    {
        return new GreaterThan(string);
    }

    public static Predicates<String> greaterThanOrEqualTo(String string)
    {
        return new GreaterThanOrEqualTo(string);
    }

    public static Predicates<String> hasLetters()
    {
        return HAS_LETTERS;
    }

    public static Predicates<String> hasDigits()
    {
        return HAS_DIGITS;
    }

    public static Predicates<String> hasLettersOrDigits()
    {
        return HAS_LETTERS_OR_DIGITS;
    }

    public static Predicates<String> hasLettersAndDigits()
    {
        return HAS_LETTERS_AND_DIGITS;
    }

    public static Predicates<String> hasSpaces()
    {
        return HAS_SPACES;
    }

    public static Predicates<String> hasUpperCase()
    {
        return HAS_UPPERCASE;
    }

    public static Predicates<String> hasLowerCase()
    {
        return HAS_LOWERCASE;
    }

    public static Predicates<String> hasUndefined()
    {
        return HAS_UNDEFINED;
    }

    public static Predicates<String> isNumeric()
    {
        return IS_NUMERIC;
    }

    public static Predicates<String> isAlphanumeric()
    {
        return IS_ALPHANUMERIC;
    }

    public static Predicates<String> isBlank()
    {
        return IS_BLANK;
    }

    public static Predicates<String> notBlank()
    {
        return NOT_BLANK;
    }

    public static Predicates<String> isAlpha()
    {
        return IS_ALPHA;
    }

    private static class Empty extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && anObject.isEmpty();
        }

        @Override
        public String toString()
        {
            return "StringPredicates.empty()";
        }
    }

    private static class NotEmpty extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && !anObject.isEmpty();
        }

        @Override
        public String toString()
        {
            return "StringPredicates.notEmpty()";
        }
    }

    private static class HasLetters extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return StringIterate.anySatisfy(anObject, CodePointPredicate.IS_LETTER);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.hasLetters()";
        }
    }

    private static class HasDigits extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return StringIterate.anySatisfy(anObject, CodePointPredicate.IS_DIGIT);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.hasDigits()";
        }
    }

    private static class HasLettersOrDigits extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return StringIterate.anySatisfy(anObject, CodePointPredicate.IS_LETTER_OR_DIGIT);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.hasLettersOrDigits()";
        }
    }

    private static class HasLettersAndDigits extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String string)
        {
            boolean hasLetter = false;
            boolean hasDigit = false;
            for (int i = 0; i < string.length(); i++)
            {
                if (Character.isLetter(string.codePointAt(i)))
                {
                    hasLetter = true;
                }
                if (Character.isDigit(string.codePointAt(i)))
                {
                    hasDigit = true;
                }
                if (hasLetter && hasDigit)
                {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString()
        {
            return "StringPredicates.hasLettersAndDigits()";
        }
    }

    private static class HasSpaces extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return StringIterate.anySatisfy(anObject, CodePointPredicate.IS_WHITESPACE);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.hasSpaces()";
        }
    }

    private static class HasUppercase extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return StringIterate.anySatisfy(anObject, CodePointPredicate.IS_UPPERCASE);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.hasUpperCase()";
        }
    }

    private static class HasLowercase extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return StringIterate.anySatisfy(anObject, CodePointPredicate.IS_LOWERCASE);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.hasLowerCase()";
        }
    }

    private static class HasUndefined extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return StringIterate.anySatisfy(anObject, CodePointPredicate.IS_UNDEFINED);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.hasUndefined()";
        }
    }

    private static class IsNumeric extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return StringIterate.allSatisfy(anObject, CodePointPredicate.IS_DIGIT);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.isNumeric()";
        }
    }

    private static class IsAlphanumeric extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return StringIterate.allSatisfy(anObject, CodePointPredicate.IS_LETTER_OR_DIGIT);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.isAlphanumeric()";
        }
    }

    private static class IsBlank extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String string)
        {
            return StringIterate.isEmptyOrWhitespace(string);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.isBlank()";
        }
    }

    private static class NotBlank extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String string)
        {
            return StringIterate.notEmptyOrWhitespace(string);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.notBlank()";
        }
    }

    private static class IsAlpha extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && StringIterate.allSatisfy(anObject, CodePointPredicate.IS_LETTER);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.isAlpha()";
        }
    }

    private static final class ContainsString extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;
        private final String otherString;

        private ContainsString(String newOtherString)
        {
            this.otherString = newOtherString;
        }

        @Override
        public boolean accept(String string)
        {
            return StringIterate.notEmpty(string) && string.contains(this.otherString);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.contains(\"" + this.otherString + "\")";
        }
    }

    private static final class NotContainsString extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;
        private final String otherString;

        private NotContainsString(String newOtherString)
        {
            this.otherString = newOtherString;
        }

        @Override
        public boolean accept(String string)
        {
            return StringIterate.isEmpty(string) || !string.contains(this.otherString);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.notContains(\"" + this.otherString + "\")";
        }
    }

    private static final class ContainsCharacter extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;
        private final char character;

        private ContainsCharacter(char newCharacter)
        {
            this.character = newCharacter;
        }

        @Override
        public boolean accept(String string)
        {
            return StringIterate.notEmpty(string) && string.indexOf(this.character) > -1;
        }

        @Override
        public String toString()
        {
            return "StringPredicates.contains(\"" + this.character + "\")";
        }
    }

    private static final class StartsWith extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;
        private final String substring;

        private StartsWith(String newSubstring)
        {
            this.substring = newSubstring;
        }

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && anObject.startsWith(this.substring);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.startsWith(\"" + this.substring + "\")";
        }
    }

    private static final class EndsWith extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;
        private final String substring;

        private EndsWith(String newSubstring)
        {
            this.substring = newSubstring;
        }

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && anObject.endsWith(this.substring);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.endsWith(\"" + this.substring + "\")";
        }
    }

    private static final class Size extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;
        private final int size;

        private Size(int newSize)
        {
            this.size = newSize;
        }

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && anObject.length() == this.size;
        }

        @Override
        public String toString()
        {
            return "StringPredicates.size(" + this.size + ')';
        }
    }

    private static final class EqualsIgnoreCase extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;
        private final String otherString;

        private EqualsIgnoreCase(String newOtherString)
        {
            this.otherString = newOtherString;
        }

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && anObject.equalsIgnoreCase(this.otherString);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.equalsIgnoreCase(\"" + this.otherString + "\")";
        }
    }

    private static final class MatchesRegex extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;
        private final String regex;

        private MatchesRegex(String newRegex)
        {
            this.regex = newRegex;
        }

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && anObject.matches(this.regex);
        }

        @Override
        public String toString()
        {
            return "StringPredicates.matches(\"" + this.regex + "\")";
        }
    }

    private static final class LessThan extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;
        private final String string;

        private LessThan(String newString)
        {
            this.string = newString;
        }

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && anObject.compareTo(this.string) < 0;
        }

        @Override
        public String toString()
        {
            return "StringPredicates.lessThan(\"" + this.string + "\")";
        }
    }

    private static final class LessThanOrEqualTo extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;
        private final String string;

        private LessThanOrEqualTo(String newString)
        {
            this.string = newString;
        }

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && anObject.compareTo(this.string) <= 0;
        }

        @Override
        public String toString()
        {
            return "StringPredicates.lessThanOrEqualTo(\"" + this.string + "\")";
        }
    }

    private static final class GreaterThan extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;
        private final String string;

        private GreaterThan(String newString)
        {
            this.string = newString;
        }

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && anObject.compareTo(this.string) > 0;
        }

        @Override
        public String toString()
        {
            return "StringPredicates.greaterThan(\"" + this.string + "\")";
        }
    }

    private static final class GreaterThanOrEqualTo extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;
        private final String string;

        private GreaterThanOrEqualTo(String newString)
        {
            this.string = newString;
        }

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && anObject.compareTo(this.string) >= 0;
        }

        @Override
        public String toString()
        {
            return "StringPredicates.greaterThanOrEqualTo(\"" + this.string + "\")";
        }
    }
}
