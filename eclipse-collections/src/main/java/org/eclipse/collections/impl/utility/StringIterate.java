/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.utility;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.CharToCharFunction;
import org.eclipse.collections.api.block.predicate.primitive.CharPredicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.CharProcedure;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.primitive.CharPredicates;
import org.eclipse.collections.impl.block.factory.primitive.CharToCharFunctions;
import org.eclipse.collections.impl.block.function.primitive.CharFunction;
import org.eclipse.collections.impl.block.function.primitive.CodePointFunction;
import org.eclipse.collections.impl.block.predicate.CodePointPredicate;
import org.eclipse.collections.impl.block.procedure.primitive.CodePointProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.string.immutable.CharAdapter;
import org.eclipse.collections.impl.string.immutable.CodePointAdapter;
import org.eclipse.collections.impl.string.immutable.CodePointList;
import org.eclipse.collections.impl.tuple.Tuples;

/**
 * A string is essentially an array of characters. In Smalltalk a String is a subclass of ArrayedCollection, which means
 * it supports the Collection protocol. StringIterate implements the methods available on the collection protocol that
 * make sense for Strings. Some of the methods are over-specialized, in the form of englishToUppercase() which is a fast
 * form of uppercase, but does not work for different locales.
 */
public final class StringIterate
{
    private StringIterate()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    /**
     * @since 7.0.
     */
    public static CharAdapter asCharAdapter(String string)
    {
        return CharAdapter.adapt(string);
    }

    /**
     * @since 7.0.
     */
    public static CodePointAdapter asCodePointAdapter(String string)
    {
        return CodePointAdapter.adapt(string);
    }

    /**
     * @since 7.0.
     */
    public static CodePointList toCodePointList(String string)
    {
        return CodePointList.from(string);
    }

    /**
     * Converts a string of tokens separated by the specified separator to a sorted {@link MutableList}.
     *
     * @deprecated in 3.0.  Inlineable.  Poorly named method.  Does not actually deal properly with CSV.  This is better handled in a separate library.
     */
    @Deprecated
    public static MutableList<String> csvTokensToSortedList(String string)
    {
        return StringIterate.tokensToSortedList(string, ",");
    }

    /**
     * Converts a string of tokens separated by the specified separator to a sorted {@link MutableList}.
     *
     * @deprecated in 3.0.  Inlineable.  Poorly named method.  Does not actually deal properly with CSV.  This is better handled in a separate library.
     */
    @Deprecated
    public static MutableList<String> csvTrimmedTokensToSortedList(String string)
    {
        return StringIterate.trimmedTokensToSortedList(string, ",");
    }

    /**
     * Converts a string of tokens separated by the specified separator to a sorted {@link MutableList}.
     */
    public static MutableList<String> tokensToSortedList(String string, String separator)
    {
        return StringIterate.tokensToList(string, separator).sortThis();
    }

    /**
     * Converts a string of tokens separated by the specified separator to a sorted {@link MutableList}.
     */
    public static MutableList<String> trimmedTokensToSortedList(String string, String separator)
    {
        return StringIterate.trimmedTokensToList(string, separator).sortThis();
    }

    /**
     * Converts a string of tokens separated by commas to a {@link MutableList}.
     *
     * @deprecated in 3.0.  Inlineable.  Poorly named method.  Does not actually deal properly with CSV.  This is better handled in a separate library.
     */
    @Deprecated
    public static MutableList<String> csvTokensToList(String string)
    {
        return StringIterate.tokensToList(string, ",");
    }

    /**
     * Converts a string of tokens separated by commas to a {@link MutableList}.
     *
     * @deprecated in 3.0.  Inlineable.  Poorly named method.  Does not actually deal properly with CSV.  This is better handled in a separate library.
     */
    @Deprecated
    public static MutableList<String> csvTrimmedTokensToList(String string)
    {
        return StringIterate.trimmedTokensToList(string, ",");
    }

    /**
     * Converts a string of tokens separated by the specified separator to a {@link MutableList}.
     */
    public static MutableList<String> tokensToList(String string, String separator)
    {
        MutableList<String> list = Lists.mutable.empty();
        for (StringTokenizer stringTokenizer = new StringTokenizer(string, separator); stringTokenizer.hasMoreTokens(); )
        {
            String token = stringTokenizer.nextToken();
            list.add(token);
        }
        return list;
    }

    /**
     * Converts a string of tokens separated by the specified separator to a {@link MutableList}.
     */
    public static MutableList<String> trimmedTokensToList(String string, String separator)
    {
        return StringIterate.trimStringList(StringIterate.tokensToList(string, separator));
    }

    /**
     * Applies {@link String#trim()} to each String in the {@link List}
     *
     * @return the input list, mutated in place, containing trimmed strings.
     */
    private static <L extends List<String>> L trimStringList(L strings)
    {
        for (ListIterator<String> listIt = strings.listIterator(); listIt.hasNext(); )
        {
            String string = listIt.next().trim();
            listIt.set(string);
        }
        return strings;
    }

    /**
     * Converts a string of tokens separated by commas to a {@link MutableSet}.
     *
     * @deprecated in 3.0.  Inlineable.  Poorly named method.  Does not actually deal properly with CSV.  This is better handled in a separate library.
     */
    @Deprecated
    public static MutableSet<String> csvTokensToSet(String string)
    {
        return StringIterate.tokensToSet(string, ",");
    }

    /**
     * Converts a string of tokens to a {@link MutableSet}.
     */
    public static MutableSet<String> tokensToSet(String string, String separator)
    {
        MutableSet<String> set = UnifiedSet.newSet();
        for (StringTokenizer stringTokenizer = new StringTokenizer(string, separator); stringTokenizer.hasMoreTokens(); )
        {
            String token = stringTokenizer.nextToken();
            set.add(token);
        }
        return set;
    }

    /**
     * Converts a string of tokens to a {@link MutableMap} using a | to separate pairs, and a : to separate key and
     * value. e.g. "1:Sunday|2:Monday|3:Tuesday|4:Wednesday|5:Thursday|6:Friday|7:Saturday"
     */
    public static MutableMap<String, String> tokensToMap(String string)
    {
        return StringIterate.tokensToMap(string, "|", ":");
    }

    /**
     * Converts a string of tokens to a {@link MutableMap} using the specified separators.
     */
    public static MutableMap<String, String> tokensToMap(
            String string,
            String pairSeparator,
            String keyValueSeparator)
    {
        MutableMap<String, String> map = UnifiedMap.newMap();
        for (StringTokenizer tokenizer = new StringTokenizer(string, pairSeparator); tokenizer.hasMoreTokens(); )
        {
            String token = tokenizer.nextToken();
            String key = token.substring(0, token.indexOf(keyValueSeparator));
            String value = token.substring(token.indexOf(keyValueSeparator) + 1, token.length());
            map.put(key, value);
        }
        return map;
    }

    /**
     * Converts a string of tokens to a {@link MutableMap} using the specified 'key' and 'value'
     * Functions. e.g. "1:2,2:1,3:3" with both functions as Functions.getStringToInteger(), will be
     * translated a map {[1,2],[2,1],[3,3]}
     */
    public static <K, V> MutableMap<K, V> tokensToMap(
            String string,
            String separator,
            String keyValueSeparator,
            Function<String, K> keyFunction,
            Function<String, V> valueFunction)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        for (StringTokenizer tokenizer = new StringTokenizer(string, separator); tokenizer.hasMoreTokens(); )
        {
            String token = tokenizer.nextToken();
            String key = token.substring(0, token.indexOf(keyValueSeparator));
            String value = token.substring(token.indexOf(keyValueSeparator) + 1, token.length());
            map.put(keyFunction.valueOf(key), valueFunction.valueOf(value));
        }
        return map;
    }

    /**
     * Converts a string of tokens separated by the specified separator to a reverse sorted {@link MutableList}.
     *
     * @deprecated in 3.0.  Inlineable.  Poorly named method.  Does not actually deal properly with CSV.  This is better handled in a separate library.
     */
    @Deprecated
    public static MutableList<String> csvTokensToReverseSortedList(String string)
    {
        return StringIterate.tokensToReverseSortedList(string, ",");
    }

    /**
     * Converts a string of tokens separated by the specified separator to a reverse sorted {@link MutableList}.
     */
    public static MutableList<String> tokensToReverseSortedList(String string, String separator)
    {
        return StringIterate.tokensToList(string, separator).sortThis(Collections.reverseOrder());
    }

    /**
     * For each token in a string separated by the specified separator, execute the specified StringProcedure
     * by calling the valueOfString method.
     */
    public static void forEachToken(String string, String separator, Procedure<String> procedure)
    {
        for (StringTokenizer stringTokenizer = new StringTokenizer(string, separator); stringTokenizer.hasMoreTokens(); )
        {
            String token = stringTokenizer.nextToken();
            procedure.value(token);
        }
    }

    /**
     * For each token in a string separated by the specified separator, execute the specified Function2,
     * returning the result value from the function. For more information, see
     * {@link Iterate#injectInto(Object, Iterable, Function2)}
     */
    public static <T, R> R injectIntoTokens(
            String string,
            String separator,
            R injectedValue,
            Function2<R, String, R> function)
    {
        R result = injectedValue;
        for (StringTokenizer stringTokenizer = new StringTokenizer(string, separator); stringTokenizer.hasMoreTokens(); )
        {
            String token = stringTokenizer.nextToken();
            result = function.value(result, token);
        }
        return result;
    }

    /**
     * For each token in a {@code string} separated by the specified {@code separator}, execute the specified
     * {@link Procedure}.
     */
    public static void forEachTrimmedToken(String string, String separator, Procedure<String> procedure)
    {
        for (StringTokenizer stringTokenizer = new StringTokenizer(string, separator); stringTokenizer.hasMoreTokens(); )
        {
            String token = stringTokenizer.nextToken().trim();
            procedure.value(token);
        }
    }

    /**
     * For each character in the {@code string}, execute the {@link CharProcedure}.
     *
     * @deprecated since 3.0. Use {@link #forEach(String, CharProcedure)} instead.
     */
    @Deprecated
    public static void forEach(String string, org.eclipse.collections.impl.block.procedure.primitive.CharProcedure procedure)
    {
        StringIterate.forEachChar(string, procedure::value);
    }

    /**
     * For each character in the {@code string}, execute the {@link CharProcedure}.
     *
     * @deprecated since 7.0. Use {@link #forEachChar(String, CharProcedure)} instead.
     */
    @Deprecated
    public static void forEach(String string, CharProcedure procedure)
    {
        StringIterate.forEachChar(string, procedure);
    }

    /**
     * For each char in the {@code string}, execute the {@link CharProcedure}.
     *
     * @since 7.0
     */
    public static void forEachChar(String string, CharProcedure procedure)
    {
        int size = string.length();
        for (int i = 0; i < size; i++)
        {
            procedure.value(string.charAt(i));
        }
    }

    /**
     * For each int code point in the {@code string}, execute the {@link CodePointProcedure}.
     *
     * @deprecated since 7.0. Use {@link #forEachCodePoint(String, CodePointProcedure)} instead.
     */
    @Deprecated
    public static void forEach(String string, CodePointProcedure procedure)
    {
        StringIterate.forEachCodePoint(string, procedure);
    }

    /**
     * For each int code point in the {@code string}, execute the {@link CodePointProcedure}.
     *
     * @since 7.0
     */
    public static void forEachCodePoint(String string, CodePointProcedure procedure)
    {
        int size = string.length();
        for (int i = 0; i < size; )
        {
            int codePoint = string.codePointAt(i);
            procedure.value(codePoint);
            i += Character.charCount(codePoint);
        }
    }

    /**
     * For each char in the {@code string} in reverse order, execute the {@link CharProcedure}.
     *
     * @deprecated since 7.0. Use {@link #reverseForEachChar(String, CharProcedure)} instead.
     */
    @Deprecated
    public static void reverseForEach(String string, CharProcedure procedure)
    {
        StringIterate.reverseForEachChar(string, procedure);
    }

    /**
     * For each char in the {@code string} in reverse order, execute the {@link CharProcedure}.
     *
     * @since 7.0
     */
    public static void reverseForEachChar(String string, CharProcedure procedure)
    {
        for (int i = string.length() - 1; i >= 0; i--)
        {
            procedure.value(string.charAt(i));
        }
    }

    /**
     * For each int code point in the {@code string} in reverse order, execute the {@link CodePointProcedure}.
     *
     * @deprecated since 7.0. Use {@link #reverseForEachCodePoint(String, CodePointProcedure)} instead.
     */
    @Deprecated
    public static void reverseForEach(String string, CodePointProcedure procedure)
    {
        StringIterate.reverseForEachCodePoint(string, procedure);
    }

    /**
     * For each int code point in the {@code string} in reverse order, execute the {@link CodePointProcedure}.
     *
     * @since 7.0
     */
    public static void reverseForEachCodePoint(String string, CodePointProcedure procedure)
    {
        for (int i = StringIterate.lastIndex(string); i >= 0; )
        {
            int codePoint = string.codePointAt(i);
            procedure.value(codePoint);
            if (i == 0)
            {
                i--;
            }
            else
            {
                i -= StringIterate.numberOfChars(string, i);
            }
        }
    }

    private static int lastIndex(String string)
    {
        if (StringIterate.isEmpty(string))
        {
            return -1;
        }
        int size = string.length();
        if (size > 1)
        {
            return size - StringIterate.numberOfChars(string, size);
        }
        return 0;
    }

    public static int numberOfChars(String string, int i)
    {
        return StringIterate.isSurrogate(string, i) ? 2 : 1;
    }

    public static boolean isSurrogate(String string, int i)
    {
        return Character.isLowSurrogate(string.charAt(i - 1)) && Character.isHighSurrogate(string.charAt(i - 2));
    }

    /**
     * Count the number of elements that return true for the specified {@code predicate}.
     *
     * @deprecated since 3.0.
     */
    @Deprecated
    public static int count(String string, org.eclipse.collections.impl.block.predicate.primitive.CharPredicate predicate)
    {
        return StringIterate.countChar(string, predicate::accept);
    }

    /**
     * Count the number of elements that return true for the specified {@code predicate}.
     *
     * @deprecated since 7.0. Use {@link #countChar(String, CharPredicate)} instead.
     */
    @Deprecated
    public static int count(String string, CharPredicate predicate)
    {
        return StringIterate.countChar(string, predicate);
    }

    /**
     * Count the number of elements that return true for the specified {@code predicate}.
     *
     * @since 7.0
     */
    public static int countChar(String string, CharPredicate predicate)
    {
        int count = 0;
        int size = string.length();
        for (int i = 0; i < size; i++)
        {
            if (predicate.accept(string.charAt(i)))
            {
                count++;
            }
        }
        return count;
    }

    /**
     * Count the number of elements that return true for the specified {@code predicate}.
     *
     * @deprecated since 7.0. Use {@link #countCodePoint(String, CodePointPredicate)} instead.
     */
    @Deprecated
    public static int count(String string, CodePointPredicate predicate)
    {
        return StringIterate.countCodePoint(string, predicate);
    }

    /**
     * Count the number of elements that return true for the specified {@code predicate}.
     *
     * @since 7.0
     */
    public static int countCodePoint(String string, CodePointPredicate predicate)
    {
        int count = 0;
        int size = string.length();
        for (int i = 0; i < size; )
        {
            int codePoint = string.codePointAt(i);
            if (predicate.accept(codePoint))
            {
                count++;
            }
            i += Character.charCount(codePoint);
        }
        return count;
    }

    /**
     * @deprecated since 3.0. Use {@link #collect(String, CharToCharFunction)} instead.
     */
    @Deprecated
    public static String collect(String string, CharFunction function)
    {
        return StringIterate.collectChar(string, function::valueOf);
    }

    /**
     * Transform the char elements to a new string using the specified function {@code function}.
     *
     * @deprecated since 7.0. Use {@link #collectChar(String, CharToCharFunction)} instead.
     */
    @Deprecated
    public static String collect(String string, CharToCharFunction function)
    {
        return StringIterate.collectChar(string, function);
    }

    /**
     * Transform the char elements to a new string using the specified function {@code function}.
     *
     * @since 7.0
     */
    public static String collectChar(String string, CharToCharFunction function)
    {
        int size = string.length();
        StringBuilder builder = new StringBuilder(size);
        for (int i = 0; i < size; i++)
        {
            builder.append(function.valueOf(string.charAt(i)));
        }
        return builder.toString();
    }

    /**
     * Transform the int code point elements to a new string using the specified function {@code function}.
     *
     * @deprecated since 7.0. Use {@link #collectCodePoint(String, CodePointFunction)} instead.
     */
    @Deprecated
    public static String collect(String string, CodePointFunction function)
    {
        return StringIterate.collectCodePoint(string, function);
    }

    /**
     * Transform the int code point elements to a new string using the specified function {@code function}.
     *
     * @since 7.0
     */
    public static String collectCodePoint(String string, CodePointFunction function)
    {
        int size = string.length();
        StringBuilder builder = new StringBuilder(size);
        for (int i = 0; i < size; )
        {
            int codePoint = string.codePointAt(i);
            builder.appendCodePoint(function.valueOf(codePoint));
            i += Character.charCount(codePoint);
        }
        return builder.toString();
    }

    public static String englishToUpperCase(String string)
    {
        if (StringIterate.anySatisfyChar(string, CharPredicates.isLowerCase()))
        {
            return StringIterate.collectChar(string, CharToCharFunctions.toUpperCase());
        }
        return string;
    }

    public static String englishToLowerCase(String string)
    {
        if (StringIterate.anySatisfyChar(string, CharPredicates.isUpperCase()))
        {
            return StringIterate.collectChar(string, CharToCharFunctions.toLowerCase());
        }
        return string;
    }

    /**
     * Find the first element that returns true for the specified {@code predicate}.
     *
     * @deprecated since 7.0. Use {@link #detectChar(String, CharPredicate)} instead.
     */
    @Deprecated
    public static Character detect(String string, CharPredicate predicate)
    {
        return StringIterate.detectChar(string, predicate);
    }

    /**
     * Find the first element that returns true for the specified {@code predicate}.
     *
     * @since 7.0
     */
    public static Character detectChar(String string, CharPredicate predicate)
    {
        int size = string.length();
        for (int i = 0; i < size; i++)
        {
            char character = string.charAt(i);
            if (predicate.accept(character))
            {
                return character;
            }
        }
        return null;
    }

    /**
     * Find the first element that returns true for the specified {@code predicate}.  Return the default char if
     * no value is found.
     *
     * @deprecated since 7.0. Use {@link #detectCharIfNone(String, CharPredicate, char)} instead.
     */
    @Deprecated
    public static Character detectIfNone(String string, CharPredicate predicate, char resultIfNone)
    {
        return StringIterate.detectCharIfNone(string, predicate, resultIfNone);
    }

    /**
     * Find the first element that returns true for the specified {@code predicate}.  Return the default char if
     * no value is found.
     */
    public static Character detectCharIfNone(String string, CharPredicate predicate, char resultIfNone)
    {
        Character result = StringIterate.detectChar(string, predicate);
        return result == null ? Character.valueOf(resultIfNone) : result;
    }

    /**
     * Find the first element that returns true for the specified {@code predicate}.  Return the first char of the
     * default string if no value is found.
     *
     * @deprecated since 7.0. Use {@link #detectCharIfNone(String, CharPredicate, String)} instead.
     */
    @Deprecated
    public static Character detectIfNone(String string, CharPredicate predicate, String resultIfNone)
    {
        return StringIterate.detectCharIfNone(string, predicate, resultIfNone);
    }

    /**
     * Find the first element that returns true for the specified {@code predicate}.  Return the first char of the
     * default string if no value is found.
     */
    public static Character detectCharIfNone(String string, CharPredicate predicate, String resultIfNone)
    {
        Character result = StringIterate.detectChar(string, predicate);
        return result == null ? Character.valueOf(resultIfNone.charAt(0)) : result;
    }

    /**
     * Count the number of occurrences of the specified char.
     *
     * @deprecated since 7.0. Use {@link #occurrencesOfChar(String, char)} instead.
     */
    @Deprecated
    public static int occurrencesOf(String string, char value)
    {
        return StringIterate.occurrencesOfChar(string, value);
    }

    /**
     * Count the number of occurrences of the specified char.
     *
     * @since 7.0
     */
    public static int occurrencesOfChar(String string, char value)
    {
        return StringIterate.countChar(string, character -> value == character);
    }

    /**
     * Count the number of occurrences of the specified int code point.
     *
     * @deprecated since 7.0. Use {@link #occurrencesOfCodePoint(String, int)} instead.
     */
    @Deprecated
    public static int occurrencesOf(String string, int value)
    {
        return StringIterate.occurrencesOfCodePoint(string, value);
    }

    /**
     * Count the number of occurrences of the specified int code point.
     *
     * @since 7.0
     */
    public static int occurrencesOfCodePoint(String string, int value)
    {
        return StringIterate.countCodePoint(string, codePoint -> value == codePoint);
    }

    /**
     * Count the number of occurrences of the specified {@code string}.
     */
    public static int occurrencesOf(String string, String singleCharacter)
    {
        if (singleCharacter.length() != 1)
        {
            throw new IllegalArgumentException("Argument should be a single character: " + singleCharacter);
        }
        return StringIterate.occurrencesOfChar(string, singleCharacter.charAt(0));
    }

    /**
     * @return true if any of the characters in the {@code string} answer true for the specified {@code predicate}.
     * @deprecated since 7.0. Use {@link #anySatisfyChar(String, CharPredicate)} instead.
     */
    @Deprecated
    public static boolean anySatisfy(String string, CharPredicate predicate)
    {
        return StringIterate.anySatisfyChar(string, predicate);
    }

    /**
     * @return true if any of the characters in the {@code string} answer true for the specified {@code predicate}.
     * @since 7.0
     */
    public static boolean anySatisfyChar(String string, CharPredicate predicate)
    {
        int size = string.length();
        for (int i = 0; i < size; i++)
        {
            if (predicate.accept(string.charAt(i)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @return true if any of the code points in the {@code string} answer true for the specified {@code predicate}.
     * @deprecated since 7.0. Use {@link #anySatisfyCodePoint(String, CodePointPredicate)} instead.
     */
    @Deprecated
    public static boolean anySatisfy(String string, CodePointPredicate predicate)
    {
        return StringIterate.anySatisfyCodePoint(string, predicate);
    }

    /**
     * @return true if any of the code points in the {@code string} answer true for the specified {@code predicate}.
     * @since 7.0
     */
    public static boolean anySatisfyCodePoint(String string, CodePointPredicate predicate)
    {
        int size = string.length();
        for (int i = 0; i < size; )
        {
            int codePoint = string.codePointAt(i);
            if (predicate.accept(codePoint))
            {
                return true;
            }
            i += Character.charCount(codePoint);
        }
        return false;
    }

    /**
     * @return true if all of the characters in the {@code string} answer true for the specified {@code predicate}.
     * @deprecated since 7.0. Use {@link #allSatisfyChar(String, CharPredicate)} instead.
     */
    @Deprecated
    public static boolean allSatisfy(String string, CharPredicate predicate)
    {
        return StringIterate.allSatisfyChar(string, predicate);
    }

    /**
     * @return true if all of the characters in the {@code string} answer true for the specified {@code predicate}.
     * @since 7.0
     */
    public static boolean allSatisfyChar(String string, CharPredicate predicate)
    {
        int size = string.length();
        for (int i = 0; i < size; i++)
        {
            if (!predicate.accept(string.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if all of the code points in the {@code string} answer true for the specified {@code predicate}.
     * @deprecated since 7.0. Use {@link #allSatisfyCodePoint(String, CodePointPredicate)} instead.
     */
    @Deprecated
    public static boolean allSatisfy(String string, CodePointPredicate predicate)
    {
        return StringIterate.allSatisfyCodePoint(string, predicate);
    }

    /**
     * @return true if all of the code points in the {@code string} answer true for the specified {@code predicate}.
     * @since 7.0
     */
    public static boolean allSatisfyCodePoint(String string, CodePointPredicate predicate)
    {
        int size = string.length();
        for (int i = 0; i < size; )
        {
            int codePoint = string.codePointAt(i);
            if (!predicate.accept(codePoint))
            {
                return false;
            }
            i += Character.charCount(codePoint);
        }
        return true;
    }

    /**
     * @return true if none of the characters in the {@code string} answer true for the specified {@code predicate}.
     * @deprecated since 7.0. Use {@link #noneSatisfyChar(String, CharPredicate)} instead.
     */
    @Deprecated
    public static boolean noneSatisfy(String string, CharPredicate predicate)
    {
        return StringIterate.noneSatisfyChar(string, predicate);
    }

    /**
     * @return true if none of the characters in the {@code string} answer true for the specified {@code predicate}.
     * @since 7.0
     */
    public static boolean noneSatisfyChar(String string, CharPredicate predicate)
    {
        int size = string.length();
        for (int i = 0; i < size; i++)
        {
            if (predicate.accept(string.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if none of the code points in the {@code string} answer true for the specified {@code predicate}.
     * @deprecated since 7.0. Use {@link #noneSatisfyCodePoint(String, CodePointPredicate)} instead.
     */
    @Deprecated
    public static boolean noneSatisfy(String string, CodePointPredicate predicate)
    {
        return StringIterate.noneSatisfyCodePoint(string, predicate);
    }

    /**
     * @return true if none of the code points in the {@code string} answer true for the specified {@code predicate}.
     * @since 7.0
     */
    public static boolean noneSatisfyCodePoint(String string, CodePointPredicate predicate)
    {
        int size = string.length();
        for (int i = 0; i < size; )
        {
            int codePoint = string.codePointAt(i);
            if (predicate.accept(codePoint))
            {
                return false;
            }
            i += Character.charCount(codePoint);
        }
        return true;
    }

    /**
     * @return a new string with all of the characters that return true for the specified {@code predicate}.
     * @deprecated since 7.0. Use {@link #selectChar(String, CharPredicate)} instead.
     */
    @Deprecated
    public static String select(String string, CharPredicate predicate)
    {
        return StringIterate.selectChar(string, predicate);
    }

    /**
     * @return a new string with all of the characters that return true for the specified {@code predicate}.
     * @since 7.0
     */
    public static String selectChar(String string, CharPredicate predicate)
    {
        int size = string.length();
        StringBuilder buffer = new StringBuilder(string.length());
        for (int i = 0; i < size; i++)
        {
            char character = string.charAt(i);
            if (predicate.accept(character))
            {
                buffer.append(character);
            }
        }
        return buffer.toString();
    }

    /**
     * @return a new string with all of the code points that return true for the specified {@code predicate}.
     * @deprecated since 7.0. Use {@link #selectCodePoint(String, CodePointPredicate)} instead.
     */
    @Deprecated
    public static String select(String string, CodePointPredicate predicate)
    {
        return StringIterate.selectCodePoint(string, predicate);
    }

    /**
     * @return a new string with all of the code points that return true for the specified {@code predicate}.
     * @since 7.0
     */
    public static String selectCodePoint(String string, CodePointPredicate predicate)
    {
        int size = string.length();
        StringBuilder buffer = new StringBuilder(string.length());
        for (int i = 0; i < size; )
        {
            int codePoint = string.codePointAt(i);
            if (predicate.accept(codePoint))
            {
                buffer.appendCodePoint(codePoint);
            }
            i += Character.charCount(codePoint);
        }
        return buffer.toString();
    }

    /**
     * @return a new string excluding all of the characters that return true for the specified {@code predicate}.
     * @deprecated since 7.0. Use {@link #rejectChar(String, CharPredicate)} instead.
     */
    @Deprecated
    public static String reject(String string, CharPredicate predicate)
    {
        return StringIterate.rejectChar(string, predicate);
    }

    /**
     * @return a new string excluding all of the characters that return true for the specified {@code predicate}.
     * @since 7.0
     */
    public static String rejectChar(String string, CharPredicate predicate)
    {
        int size = string.length();
        StringBuilder buffer = new StringBuilder(string.length());
        for (int i = 0; i < size; i++)
        {
            char character = string.charAt(i);
            if (!predicate.accept(character))
            {
                buffer.append(character);
            }
        }
        return buffer.toString();
    }

    /**
     * @return a new string excluding all of the code points that return true for the specified {@code predicate}.
     * @deprecated since 7.0. Use {@link #rejectCodePoint(String, CodePointPredicate)} instead.
     */
    @Deprecated
    public static String reject(String string, CodePointPredicate predicate)
    {
        return StringIterate.rejectCodePoint(string, predicate);
    }

    /**
     * @return a new string excluding all of the code points that return true for the specified {@code predicate}.
     * @since 7.0
     */
    public static String rejectCodePoint(String string, CodePointPredicate predicate)
    {
        int size = string.length();
        StringBuilder buffer = new StringBuilder(string.length());
        for (int i = 0; i < size; )
        {
            int codePoint = string.codePointAt(i);
            if (!predicate.accept(codePoint))
            {
                buffer.appendCodePoint(codePoint);
            }
            i += Character.charCount(codePoint);
        }
        return buffer.toString();
    }

    public static String getLastToken(String value, String separator)
    {
        if (StringIterate.notEmpty(value))
        {
            int lastIndex = value.lastIndexOf(separator);
            if (lastIndex > -1)
            {
                return value.substring(lastIndex + separator.length());
            }
            return value;
        }
        return value == null ? null : "";
    }

    public static String getFirstToken(String value, String separator)
    {
        if (StringIterate.notEmpty(value))
        {
            int firstIndex = value.indexOf(separator);
            if (firstIndex > -1)
            {
                return value.substring(0, firstIndex);
            }
            return value;
        }
        return value == null ? null : "";
    }

    public static boolean isEmpty(String string)
    {
        return string == null || string.length() == 0;
    }

    public static boolean isEmptyOrWhitespace(String string)
    {
        return StringIterate.isEmpty(string) || StringIterate.isWhitespace(string);
    }

    private static boolean isWhitespace(String string)
    {
        return StringIterate.allSatisfyCodePoint(string, CodePointPredicate.IS_WHITESPACE);
    }

    public static boolean isNumber(String string)
    {
        return StringIterate.charactersSatisfy(string, CodePointPredicate.IS_DIGIT);
    }

    public static boolean isAlphaNumeric(String string)
    {
        return StringIterate.charactersSatisfy(string, CodePointPredicate.IS_LETTER_OR_DIGIT);
    }

    private static boolean charactersSatisfy(String string, CodePointPredicate predicate)
    {
        return !"".equals(string) && StringIterate.allSatisfyCodePoint(string, predicate);
    }

    public static boolean notEmpty(String string)
    {
        return !StringIterate.isEmpty(string);
    }

    public static boolean notEmptyOrWhitespace(String string)
    {
        return !StringIterate.isEmptyOrWhitespace(string);
    }

    public static String repeat(String template, int repeatTimes)
    {
        StringBuilder buf = new StringBuilder(template.length() * repeatTimes);
        for (int i = 0; i < repeatTimes; i++)
        {
            buf.append(template);
        }
        return buf.toString();
    }

    public static String repeat(char c, int repeatTimes)
    {
        StringBuilder buf = new StringBuilder(repeatTimes);
        for (int i = 0; i < repeatTimes; i++)
        {
            buf.append(c);
        }
        return buf.toString();
    }

    public static String padOrTrim(String message, int targetLength)
    {
        int messageLength = message.length();
        if (messageLength >= targetLength)
        {
            return message.substring(0, targetLength);
        }
        return message + StringIterate.repeat(' ', targetLength - messageLength);
    }

    public static MutableList<Character> toList(String string)
    {
        MutableList<Character> characters = FastList.newList(string.length());
        StringIterate.forEachChar(string, new AddCharacterToCollection(characters));
        return characters;
    }

    public static MutableList<Character> toLowercaseList(String string)
    {
        MutableList<Character> characters = FastList.newList();
        StringIterate.forEachChar(string, new AddLowercaseCharacterToCollection(characters));
        return characters;
    }

    public static MutableList<Character> toUppercaseList(String string)
    {
        MutableList<Character> characters = FastList.newList();
        StringIterate.forEachChar(string, new AddUppercaseCharacterToCollection(characters));
        return characters;
    }

    public static MutableBag<Character> toBag(String string)
    {
        MutableBag<Character> characters = HashBag.newBag();
        StringIterate.forEachChar(string, new AddCharacterToCollection(characters));
        return characters;
    }

    public static MutableBag<Character> toLowercaseBag(String string)
    {
        MutableBag<Character> characters = HashBag.newBag();
        StringIterate.forEachChar(string, new AddLowercaseCharacterToCollection(characters));
        return characters;
    }

    public static MutableBag<Character> toUppercaseBag(String string)
    {
        MutableBag<Character> characters = HashBag.newBag();
        StringIterate.forEachChar(string, new AddUppercaseCharacterToCollection(characters));
        return characters;
    }

    public static MutableSet<Character> toSet(String string)
    {
        MutableSet<Character> characters = UnifiedSet.newSet();
        StringIterate.forEachChar(string, new AddCharacterToCollection(characters));
        return characters;
    }

    /**
     * Partitions String in fixed size chunks.
     *
     * @param size the number of characters per chunk
     * @return A {@code MutableList} containing {@code String}s of size {@code size}, except the last will be
     * truncated (i.e. shorter) if the characters don't divide evenly.
     * @since 5.2
     */
    public static MutableList<String> chunk(String string, int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Size for groups must be positive but was: " + size);
        }

        int length = string.length();

        if (length == 0)
        {
            return FastList.newList();
        }

        MutableList<String> result = FastList.newList((length + size - 1) / size);

        int startOffset = 0;
        while (startOffset < length)
        {
            result.add(string.substring(startOffset, Math.min(startOffset + size, length)));
            startOffset += size;
        }

        return result;
    }

    /**
     * @deprecated in 3.0. Inlineable.
     */
    @Deprecated
    public static MutableSet<Character> asUppercaseSet(String string)
    {
        return StringIterate.toUppercaseSet(string);
    }

    public static MutableSet<Character> toUppercaseSet(String string)
    {
        MutableSet<Character> characters = UnifiedSet.newSet();
        StringIterate.forEachChar(string, new AddUppercaseCharacterToCollection(characters));
        return characters;
    }

    /**
     * @deprecated in 3.0. Inlineable.
     */
    @Deprecated
    public static MutableSet<Character> asLowercaseSet(String string)
    {
        return StringIterate.toLowercaseSet(string);
    }

    public static MutableSet<Character> toLowercaseSet(String string)
    {
        MutableSet<Character> characters = UnifiedSet.newSet();
        StringIterate.forEachChar(string, new AddLowercaseCharacterToCollection(characters));
        return characters;
    }

    public static Twin<String> splitAtIndex(String aString, int index)
    {
        return Tuples.twin(aString.substring(0, index), aString.substring(index, aString.length()));
    }

    private static final class AddCharacterToCollection implements CharProcedure
    {
        private final MutableCollection<Character> characters;

        private AddCharacterToCollection(MutableCollection<Character> characters)
        {
            this.characters = characters;
        }

        @Override
        public void value(char character)
        {
            this.characters.add(Character.valueOf(character));
        }
    }

    private static final class AddLowercaseCharacterToCollection implements CharProcedure
    {
        private final MutableCollection<Character> characters;

        private AddLowercaseCharacterToCollection(MutableCollection<Character> characters)
        {
            this.characters = characters;
        }

        @Override
        public void value(char character)
        {
            this.characters.add(Character.valueOf(Character.toLowerCase(character)));
        }
    }

    private static final class AddUppercaseCharacterToCollection implements CharProcedure
    {
        private final MutableCollection<Character> characters;

        private AddUppercaseCharacterToCollection(MutableCollection<Character> characters)
        {
            this.characters = characters;
        }

        @Override
        public void value(char character)
        {
            this.characters.add(Character.valueOf(Character.toUpperCase(character)));
        }
    }
}
