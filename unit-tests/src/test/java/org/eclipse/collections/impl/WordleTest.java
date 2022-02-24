/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.tuple.Triplet;
import org.eclipse.collections.api.tuple.primitive.CharCharPair;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.factory.primitive.CharBags;
import org.eclipse.collections.impl.string.immutable.CharAdapter;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Test;

public class WordleTest
{
    private static final ImmutableList<Triplet<String>> TRIPLES = Lists.immutable.with(
            Tuples.triplet(".....", "aaaaa", "bbbbb"),
            Tuples.triplet("A....", "aaaaa", "abbbb"),
            Tuples.triplet(".A...", "aaaaa", "babbb"),
            Tuples.triplet("..A..", "aaaaa", "bbabb"),
            Tuples.triplet("...A.", "aaaaa", "bbbab"),
            Tuples.triplet("....A", "aaaaa", "bbbba"),
            Tuples.triplet(".a...", "abbbb", "caccc"),
            Tuples.triplet("..a..", "abbbb", "ccacc"),
            Tuples.triplet("...a.", "abbbb", "cccac"),
            Tuples.triplet("....a", "abbbb", "cccca"),
            Tuples.triplet("A....", "abbbb", "accca"),
            Tuples.triplet("A....", "abbbb", "accaa"),
            Tuples.triplet("A..a.", "aabbb", "accaa"),
            Tuples.triplet("AA...", "aabbb", "aacaa"),
            Tuples.triplet("...aa", "aabbb", "cccaa"),
            Tuples.triplet("..A..", "bbabb", "aaaaa"),
            Tuples.triplet("AAAAA", "aaaaa", "aaaaa"),
            Tuples.triplet("BRAVO", "bravo", "bravo"));

    @Test
    public void wordleTest()
    {
        Verify.assertAllSatisfy(
                TRIPLES,
                triple -> triple.getOne().equals(new Wordle(triple.getTwo()).guessInjectIntoIndex(triple.getThree())));
        Verify.assertAllSatisfy(
                TRIPLES,
                triple -> triple.getOne().equals(new Wordle(triple.getTwo()).guessRejectWithIndex(triple.getThree())));
        Verify.assertAllSatisfy(
                TRIPLES,
                triple -> triple.getOne().equals(new Wordle(triple.getTwo()).guessSelectWithIndex(triple.getThree())));
        Verify.assertAllSatisfy(
                TRIPLES,
                triple -> triple.getOne().equals(new Wordle(triple.getTwo()).guessZipCharReject(triple.getThree())));
    }

    class Wordle
    {
        private final String string;

        Wordle(String string)
        {
            this.string = string.toLowerCase();
        }

        public String guessInjectIntoIndex(String guess)
        {
            CharAdapter guessChars = Strings.asChars(guess.toLowerCase());
            CharAdapter hiddenChars = Strings.asChars(this.string);
            MutableCharBag remaining =
                    hiddenChars.injectIntoWithIndex(
                            CharBags.mutable.empty(),
                            (bag, each, i) -> guessChars.get(i) == each ? bag : bag.with(each));
            return guessChars.collectWithIndex((each, i) -> hiddenChars.get(i) == each
                            ? Character.toUpperCase(each) : remaining.remove(each) ? each : '.')
                    .makeString("");
        }

        public String guessRejectWithIndex(String guess)
        {
            CharAdapter guessChars = Strings.asChars(guess.toLowerCase());
            CharAdapter hiddenChars = Strings.asChars(this.string);
            MutableCharBag remaining =
                    hiddenChars.rejectWithIndex((each, i) -> guessChars.get(i) == each, CharBags.mutable.empty());
            return guessChars.collectWithIndex((each, i) -> hiddenChars.get(i) == each
                            ? Character.toUpperCase(each) : remaining.remove(each) ? each : '.')
                    .makeString("");
        }

        public String guessSelectWithIndex(String guess)
        {
            CharAdapter guessChars = Strings.asChars(guess.toLowerCase());
            CharAdapter hiddenChars = Strings.asChars(this.string);
            MutableCharBag remaining =
                    hiddenChars.selectWithIndex((each, i) -> guessChars.get(i) != each, CharBags.mutable.empty());
            return guessChars.collectWithIndex((each, i) -> hiddenChars.get(i) == each
                            ? Character.toUpperCase(each) : remaining.remove(each) ? each : '.')
                    .makeString("");
        }

        public String guessZipCharReject(String guess)
        {
            ImmutableList<CharCharPair> charPairs =
                    Strings.asChars(this.string).zipChar(Strings.asChars(guess.toLowerCase()));
            MutableCharBag remaining =
                    charPairs.asLazy()
                            .reject(pair -> pair.getOne() == pair.getTwo())
                            .collectChar(CharCharPair::getOne)
                            .toBag();
            return charPairs.collectChar(pair -> pair.getOne() == pair.getTwo()
                            ? Character.toUpperCase(pair.getTwo()) : remaining.remove(pair.getTwo()) ? pair.getTwo() : '.')
                    .makeString("");
        }
    }
}
