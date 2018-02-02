/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.string.immutable;

import java.util.stream.Collectors;

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates;
import org.eclipse.collections.impl.list.immutable.primitive.AbstractImmutableIntListTestCase;
import org.junit.Assert;
import org.junit.Test;

public class CodePointListTest extends AbstractImmutableIntListTestCase
{
    private static final String UNICODE_STRING = "\u3042\uD840\uDC00\u3044\uD840\uDC03\u3046\uD83D\uDE09";
    private static final String UNICODE_EMOJI_STRING = "\uD83D\uDE09w\uD83D\uDE09i\uD83D\uDE09n\uD83D\uDE09k";
    private static final String UNICODE_EMOJI_STRING2 = "w\uD83D\uDE09i\uD83D\uDE09n\uD83D\uDE09k\uD83D\uDE09";

    @Override
    protected ImmutableIntList classUnderTest()
    {
        return CodePointList.from(1, 2, 3);
    }

    @Override
    protected ImmutableIntList newWith(int... elements)
    {
        return CodePointList.from(elements);
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Test
    public void stringBuilder()
    {
        CodePointList list = CodePointList.from(UNICODE_STRING);
        Assert.assertEquals(UNICODE_STRING, new StringBuilder(list).toString());
        CodePointList list2 = CodePointList.from(UNICODE_EMOJI_STRING);
        Assert.assertEquals(UNICODE_EMOJI_STRING, new StringBuilder(list2).toString());
        CodePointList list3 = CodePointList.from(UNICODE_EMOJI_STRING2);
        Assert.assertEquals(UNICODE_EMOJI_STRING2, new StringBuilder(list3).toString());
        CodePointList list4 = CodePointList.from("Hello World!");
        Assert.assertEquals("Hello World!", new StringBuilder(list4).toString());
    }

    @Test
    public void subSequence()
    {
        CodePointList adapt = CodePointList.from(UNICODE_STRING);
        CharSequence sequence = adapt.subSequence(1, 3);
        Assert.assertEquals(UNICODE_STRING.subSequence(1, 3), sequence);
    }

    @Override
    @Test
    public void max()
    {
        Assert.assertEquals(9L, this.newWith(1, 2, 9).max());
        Assert.assertEquals(32L, this.newWith(1, 0, 9, 30, 31, 32).max());
        Assert.assertEquals(32L, this.newWith(0, 9, 30, 31, 32).max());
        Assert.assertEquals(31L, this.newWith(31, 0, 30).max());
        Assert.assertEquals(39L, this.newWith(32, 39, 35).max());
        Assert.assertEquals(this.classUnderTest().size(), this.classUnderTest().max());
    }

    @Override
    @Test
    public void min()
    {
        Assert.assertEquals(1L, this.newWith(1, 2, 9).min());
        Assert.assertEquals(0L, this.newWith(1, 0, 9, 30, 31, 32).min());
        Assert.assertEquals(31L, this.newWith(31, 32, 33).min());
        Assert.assertEquals(32L, this.newWith(32, 39, 35).min());
        Assert.assertEquals(1L, this.classUnderTest().min());
    }

    @Override
    @Test
    public void allSatisfy()
    {
        Assert.assertFalse(this.newWith(1, 0, 2).allSatisfy(IntPredicates.greaterThan(0)));
        Assert.assertTrue(this.newWith(1, 2, 3).allSatisfy(IntPredicates.greaterThan(0)));
        Assert.assertFalse(this.newWith(1, 0, 31, 32).allSatisfy(IntPredicates.greaterThan(0)));
        Assert.assertFalse(this.newWith(1, 0, 31, 32).allSatisfy(IntPredicates.greaterThan(0)));
        Assert.assertTrue(this.newWith(1, 2, 31, 32).allSatisfy(IntPredicates.greaterThan(0)));
        Assert.assertFalse(this.newWith(32).allSatisfy(IntPredicates.equal(33)));
        IntIterable iterable = this.newWith(0, 1, 2);
        Assert.assertFalse(iterable.allSatisfy(value -> 3 < value));
        Assert.assertTrue(iterable.allSatisfy(IntPredicates.lessThan(3)));

        IntIterable iterable1 = this.classUnderTest();
        int size = iterable1.size();
        Assert.assertEquals(size == 0, iterable1.allSatisfy(IntPredicates.greaterThan(3)));
        Assert.assertEquals(size < 3, iterable1.allSatisfy(IntPredicates.lessThan(3)));
    }

    @Override
    @Test
    public void anySatisfy()
    {
        Assert.assertTrue(this.newWith(1, 2).anySatisfy(IntPredicates.greaterThan(0)));
        Assert.assertFalse(this.newWith(1, 2).anySatisfy(IntPredicates.equal(0)));
        Assert.assertTrue(this.newWith(31, 32).anySatisfy(IntPredicates.greaterThan(0)));
        Assert.assertTrue(this.newWith(2, 31, 32).anySatisfy(IntPredicates.greaterThan(0)));
        Assert.assertFalse(this.newWith(1, 31, 32).anySatisfy(IntPredicates.equal(0)));
        Assert.assertTrue(this.newWith(32).anySatisfy(IntPredicates.greaterThan(0)));
        IntIterable iterable = this.newWith(0, 1, 2);
        Assert.assertTrue(iterable.anySatisfy(value -> value < 3));
        Assert.assertFalse(iterable.anySatisfy(IntPredicates.greaterThan(3)));

        IntIterable iterable1 = this.classUnderTest();
        int size = iterable1.size();
        Assert.assertEquals(size > 3, iterable1.anySatisfy(IntPredicates.greaterThan(3)));
        Assert.assertEquals(size != 0, iterable1.anySatisfy(IntPredicates.lessThan(3)));
    }

    @Override
    @Test
    public void testToString()
    {
        StringBuilder expectedString = new StringBuilder();
        int size = this.classUnderTest().size();
        for (int each = 0; each < size; each++)
        {
            expectedString.appendCodePoint(each + 1);
        }
        Assert.assertEquals(expectedString.toString(), this.classUnderTest().toString());
    }

    @Override
    @Test
    public void makeString()
    {
        ImmutableIntList list = this.classUnderTest();
        StringBuilder expectedString = new StringBuilder("");
        StringBuilder expectedString1 = new StringBuilder("");
        int size = list.size();
        for (int each = 0; each < size; each++)
        {
            expectedString.appendCodePoint(each + 1);
            expectedString1.appendCodePoint(each + 1);
            expectedString.append(each == size - 1 ? "" : ", ");
            expectedString1.append(each == size - 1 ? "" : "/");
        }
        Assert.assertEquals(expectedString.toString(), list.makeString());
        Assert.assertEquals(expectedString1.toString(), list.makeString("/"));
        Assert.assertEquals(this.classUnderTest().toString(), this.classUnderTest().makeString("", "", ""));
    }

    @Override
    @Test
    public void appendString()
    {
        StringBuilder expectedString = new StringBuilder("");
        StringBuilder expectedString1 = new StringBuilder("");
        int size = this.classUnderTest().size();
        for (int each = 0; each < size; each++)
        {
            expectedString.appendCodePoint(each + 1);
            expectedString1.appendCodePoint(each + 1);
            expectedString.append(each == size - 1 ? "" : ", ");
            expectedString1.append(each == size - 1 ? "" : "/");
        }
        ImmutableIntList list = this.classUnderTest();
        StringBuilder appendable2 = new StringBuilder();
        list.appendString(appendable2);
        Assert.assertEquals(expectedString.toString(), appendable2.toString());
        StringBuilder appendable3 = new StringBuilder();
        list.appendString(appendable3, "/");
        Assert.assertEquals(expectedString1.toString(), appendable3.toString());
        StringBuilder appendable4 = new StringBuilder();
        this.classUnderTest().appendString(appendable4, "", "", "");
        Assert.assertEquals(this.classUnderTest().toString(), appendable4.toString());
    }

    @SuppressWarnings("StringBufferMayBeStringBuilder")
    @Test
    public void appendStringStringBuffer()
    {
        StringBuffer expectedString = new StringBuffer("");
        StringBuffer expectedString1 = new StringBuffer("");
        int size = this.classUnderTest().size();
        for (int each = 0; each < size; each++)
        {
            expectedString.appendCodePoint(each + 1);
            expectedString1.appendCodePoint(each + 1);
            expectedString.append(each == size - 1 ? "" : ", ");
            expectedString1.append(each == size - 1 ? "" : "/");
        }
        ImmutableIntList list = this.classUnderTest();
        StringBuffer appendable2 = new StringBuffer();
        list.appendString(appendable2);
        Assert.assertEquals(expectedString.toString(), appendable2.toString());
        StringBuffer appendable3 = new StringBuffer();
        list.appendString(appendable3, "/");
        Assert.assertEquals(expectedString1.toString(), appendable3.toString());
        StringBuffer appendable4 = new StringBuffer();
        this.classUnderTest().appendString(appendable4, "", "", "");
        Assert.assertEquals(this.classUnderTest().toString(), appendable4.toString());
    }

    @Test
    public void appendStringAppendable()
    {
        StringBuilder expectedString = new StringBuilder();
        StringBuilder expectedString1 = new StringBuilder();
        int size = this.classUnderTest().size();
        for (int each = 0; each < size; each++)
        {
            expectedString.appendCodePoint(each + 1);
            expectedString1.appendCodePoint(each + 1);
            expectedString.append(each == size - 1 ? "" : ", ");
            expectedString1.append(each == size - 1 ? "" : "/");
        }
        ImmutableIntList list = this.classUnderTest();
        SBAppendable appendable2 = new SBAppendable();
        list.appendString(appendable2);
        Assert.assertEquals(expectedString.toString(), appendable2.toString());
        SBAppendable appendable3 = new SBAppendable();
        list.appendString(appendable3, "/");
        Assert.assertEquals(expectedString1.toString(), appendable3.toString());
        SBAppendable appendable4 = new SBAppendable();
        this.classUnderTest().appendString(appendable4, "", "", "");
        Assert.assertEquals(this.classUnderTest().toString(), appendable4.toString());
    }

    @Test
    public void collectCodePointUnicode()
    {
        Assert.assertEquals(
                UNICODE_STRING.codePoints().boxed().collect(Collectors.toList()),
                CodePointList.from(UNICODE_STRING).collect(i -> i));
        Assert.assertEquals(
                UNICODE_STRING.codePoints().boxed().collect(Collectors.toList()),
                CodePointList.from(UNICODE_STRING).collect(i -> i));
    }

    @Test
    public void selectCodePointUnicode()
    {
        String string = CodePointList.from(UNICODE_STRING).select(Character::isBmpCodePoint).toString();
        Assert.assertEquals("\u3042\u3044\u3046", string);
    }

    @Test
    public void allSatisfyUnicode()
    {
        Assert.assertTrue(CodePointList.from("\u3042\u3044\u3046").allSatisfy(Character::isBmpCodePoint));
        Assert.assertFalse(CodePointList.from("\uD840\uDC00\uD840\uDC03\uD83D\uDE09").allSatisfy(Character::isBmpCodePoint));
    }

    @Test
    public void anySatisfyUnicode()
    {
        Assert.assertTrue(CodePointList.from("\u3042\u3044\u3046").anySatisfy(Character::isBmpCodePoint));
        Assert.assertFalse(CodePointList.from("\uD840\uDC00\uD840\uDC03\uD83D\uDE09").anySatisfy(Character::isBmpCodePoint));
    }

    @Test
    public void noneSatisfyUnicode()
    {
        Assert.assertFalse(CodePointList.from("\u3042\u3044\u3046").noneSatisfy(Character::isBmpCodePoint));
        Assert.assertTrue(CodePointList.from("\uD840\uDC00\uD840\uDC03\uD83D\uDE09").noneSatisfy(Character::isBmpCodePoint));
    }

    @Test
    public void forEachUnicode()
    {
        StringBuilder builder = new StringBuilder();
        CodePointList.from(UNICODE_STRING).forEach(builder::appendCodePoint);
        Assert.assertEquals(UNICODE_STRING, builder.toString());
    }

    @Test
    public void asReversedForEachUnicode()
    {
        StringBuilder builder = new StringBuilder();
        CodePointList.from(UNICODE_STRING).asReversed().forEach(builder::appendCodePoint);
        Assert.assertEquals("\uD83D\uDE09\u3046\uD840\uDC03\u3044\uD840\uDC00\u3042", builder.toString());

        StringBuilder builder2 = new StringBuilder();
        CodePointList.from("\uD840\uDC00\u3042\uD840\uDC03\u3044\uD83D\uDE09\u3046").asReversed().forEach(builder2::appendCodePoint);
        Assert.assertEquals("\u3046\uD83D\uDE09\u3044\uD840\uDC03\u3042\uD840\uDC00", builder2.toString());

        CodePointList.from("").asReversed().forEach((int codePoint) -> Assert.fail());
    }

    @Test
    public void asReversedForEachInvalidUnicode()
    {
        StringBuilder builder = new StringBuilder();
        CodePointList.from("\u3042\uDC00\uD840\u3044\uDC03\uD840\u3046\uDE09\uD83D").asReversed().forEach(builder::appendCodePoint);
        Assert.assertEquals("\uD83D\uDE09\u3046\uD840\uDC03\u3044\uD840\uDC00\u3042", builder.toString());

        StringBuilder builder2 = new StringBuilder();
        CodePointList.from("\u3042\uD840\u3044\uD840\u3046\uD840").asReversed().forEach(builder2::appendCodePoint);
        Assert.assertEquals("\uD840\u3046\uD840\u3044\uD840\u3042", builder2.toString());

        StringBuilder builder3 = new StringBuilder();
        CodePointList.from("\u3042\uDC00\u3044\uDC03\u3046\uDC06").asReversed().forEach(builder3::appendCodePoint);
        Assert.assertEquals("\uDC06\u3046\uDC03\u3044\uDC00\u3042", builder3.toString());

        CodePointList.from("").asReversed().forEach((int codePoint) -> Assert.fail());
    }

    @Test
    public void toReversedForEachUnicode()
    {
        StringBuilder builder = new StringBuilder();
        CodePointList.from(UNICODE_STRING).toReversed().forEach(builder::appendCodePoint);
        Assert.assertEquals("\uD83D\uDE09\u3046\uD840\uDC03\u3044\uD840\uDC00\u3042", builder.toString());

        StringBuilder builder2 = new StringBuilder();
        CodePointList.from("\uD840\uDC00\u3042\uD840\uDC03\u3044\uD83D\uDE09\u3046").toReversed().forEach(builder2::appendCodePoint);
        Assert.assertEquals("\u3046\uD83D\uDE09\u3044\uD840\uDC03\u3042\uD840\uDC00", builder2.toString());

        CodePointList.from("").toReversed().forEach((int codePoint) -> Assert.fail());
    }

    @Test
    public void toReversedForEachInvalidUnicode()
    {
        StringBuilder builder = new StringBuilder();
        CodePointList.from("\u3042\uDC00\uD840\u3044\uDC03\uD840\u3046\uDE09\uD83D").toReversed().forEach(builder::appendCodePoint);
        Assert.assertEquals("\uD83D\uDE09\u3046\uD840\uDC03\u3044\uD840\uDC00\u3042", builder.toString());

        StringBuilder builder2 = new StringBuilder();
        CodePointList.from("\u3042\uD840\u3044\uD840\u3046\uD840").toReversed().forEach(builder2::appendCodePoint);
        Assert.assertEquals("\uD840\u3046\uD840\u3044\uD840\u3042", builder2.toString());

        StringBuilder builder3 = new StringBuilder();
        CodePointList.from("\u3042\uDC00\u3044\uDC03\u3046\uDC06").toReversed().forEach(builder3::appendCodePoint);
        Assert.assertEquals("\uDC06\u3046\uDC03\u3044\uDC00\u3042", builder3.toString());

        CodePointList.from("").toReversed().forEach((int codePoint) -> Assert.fail());
    }

    @Test
    public void newWithUnicode()
    {
        CodePointList codePointList = CodePointList.from("");
        CodePointList collection = codePointList.newWith(12354);
        CodePointList collection0 = codePointList.newWith(12354).newWith(131072);
        CodePointList collection1 = codePointList.newWith(12354).newWith(131072).newWith(12356);
        CodePointList collection2 = codePointList.newWith(12354).newWith(131072).newWith(12356).newWith(131075);
        CodePointList collection3 = codePointList.newWith(12354).newWith(131072).newWith(12356).newWith(131075).newWith(12358);
        this.assertSizeAndContains(codePointList);
        this.assertSizeAndContains(collection, 12354);
        this.assertSizeAndContains(collection0, 12354, 131072);
        this.assertSizeAndContains(collection1, 12354, 131072, 12356);
        this.assertSizeAndContains(collection2, 12354, 131072, 12356, 131075);
        this.assertSizeAndContains(collection3, 12354, 131072, 12356, 131075, 12358);
    }

    @Test
    public void newWithoutUnicode()
    {
        CodePointList collection0 = CodePointList.from("\u3042\uD840\uDC00\u3044\uD840\uDC03\u3046");
        CodePointList collection1 = collection0.newWithout(12358);
        CodePointList collection2 = collection1.newWithout(131075);
        CodePointList collection3 = collection2.newWithout(12356);
        CodePointList collection4 = collection3.newWithout(131072);
        CodePointList collection5 = collection4.newWithout(12354);
        CodePointList collection6 = collection5.newWithout(131078);

        this.assertSizeAndContains(collection6);
        this.assertSizeAndContains(collection5);
        this.assertSizeAndContains(collection4, 12354);
        this.assertSizeAndContains(collection3, 12354, 131072);
        this.assertSizeAndContains(collection2, 12354, 131072, 12356);
        this.assertSizeAndContains(collection1, 12354, 131072, 12356, 131075);
    }

    @Test
    public void distinctUnicode()
    {
        Assert.assertEquals(
                "\uD840\uDC00\uD840\uDC03\uD83D\uDE09",
                CodePointList.from("\uD840\uDC00\uD840\uDC03\uD83D\uDE09\uD840\uDC00\uD840\uDC03\uD83D\uDE09").distinct().toString());
    }

    @Override
    public void toReversed()
    {
        super.toReversed();
        Assert.assertEquals("cba", CodePointList.from("abc").toReversed().toString());
    }

    private static class SBAppendable implements Appendable
    {
        private final StringBuilder builder = new StringBuilder();

        @Override
        public Appendable append(char c)
        {
            return this.builder.append(c);
        }

        @Override
        public Appendable append(CharSequence csq)
        {
            return this.builder.append(csq);
        }

        @Override
        public Appendable append(CharSequence csq, int start, int end)
        {
            return this.builder.append(csq, start, end);
        }

        @Override
        public String toString()
        {
            return this.builder.toString();
        }
    }
}
