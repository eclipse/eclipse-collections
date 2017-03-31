/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.junit.Test;

import static org.eclipse.collections.impl.test.Verify.assertThrows;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public interface UnorderedIterableTestCase extends RichIterableTestCase
{
    @Override
    @Test
    default void Iterable_next()
    {
        Iterable<Integer> iterable = this.newWith(3, 3, 3, 2, 2, 1);

        MutableCollection<Integer> mutableCollection = this.newMutableForFilter();

        Iterator<Integer> iterator = iterable.iterator();
        while (iterator.hasNext())
        {
            Integer integer = iterator.next();
            mutableCollection.add(integer);
        }

        IterableTestCase.assertEquals(this.getExpectedFiltered(3, 3, 3, 2, 2, 1), mutableCollection);
        assertFalse(iterator.hasNext());
    }

    @Override
    @Test
    default void RichIterable_getFirst()
    {
        RichIterable<Integer> integers = this.newWith(3, 2, 1);
        Integer first = integers.getFirst();
        assertThat(first, isOneOf(3, 2, 1));
        IterableTestCase.assertEquals(integers.iterator().next(), first);

        assertNotEquals(integers.getLast(), first);
    }

    @Override
    @Test
    default void RichIterable_getLast()
    {
        RichIterable<Integer> integers = this.newWith(3, 2, 1);
        Integer last = integers.getLast();
        assertThat(last, isOneOf(3, 2, 1));

        Iterator<Integer> iterator = integers.iterator();
        Integer iteratorLast = null;
        while (iterator.hasNext())
        {
            iteratorLast = iterator.next();
        }
        IterableTestCase.assertEquals(iteratorLast, last);

        assertNotEquals(integers.getFirst(), last);
    }

    @Override
    @Test
    default void RichIterable_detect()
    {
        assertThat(this.newWith(3, 2, 1).detect(Predicates.greaterThan(0)), isOneOf(3, 2, 1));
        assertThat(this.newWith(3, 2, 1).detect(Predicates.greaterThan(1)), isOneOf(3, 2));
        assertThat(this.newWith(3, 2, 1).detect(Predicates.greaterThan(2)), is(3));
        assertThat(this.newWith(3, 2, 1).detect(Predicates.greaterThan(3)), nullValue());

        assertThat(this.newWith(3, 2, 1).detect(Predicates.lessThan(1)), nullValue());
        assertThat(this.newWith(3, 2, 1).detect(Predicates.lessThan(2)), is(1));
        assertThat(this.newWith(3, 2, 1).detect(Predicates.lessThan(3)), isOneOf(2, 1));
        assertThat(this.newWith(3, 2, 1).detect(Predicates.lessThan(4)), isOneOf(3, 2, 1));

        assertThat(this.newWith(3, 2, 1).detectWith(Predicates2.greaterThan(), 0), isOneOf(3, 2, 1));
        assertThat(this.newWith(3, 2, 1).detectWith(Predicates2.greaterThan(), 1), isOneOf(3, 2));
        assertThat(this.newWith(3, 2, 1).detectWith(Predicates2.greaterThan(), 2), is(3));
        assertThat(this.newWith(3, 2, 1).detectWith(Predicates2.greaterThan(), 3), nullValue());

        assertThat(this.newWith(3, 2, 1).detectWith(Predicates2.lessThan(), 1), nullValue());
        assertThat(this.newWith(3, 2, 1).detectWith(Predicates2.lessThan(), 2), is(1));
        assertThat(this.newWith(3, 2, 1).detectWith(Predicates2.lessThan(), 3), isOneOf(2, 1));
        assertThat(this.newWith(3, 2, 1).detectWith(Predicates2.lessThan(), 4), isOneOf(3, 2, 1));

        assertThat(this.newWith(3, 2, 1).detectIfNone(Predicates.greaterThan(0), () -> 4), isOneOf(3, 2, 1));
        assertThat(this.newWith(3, 2, 1).detectIfNone(Predicates.greaterThan(1), () -> 4), isOneOf(3, 2));
        assertThat(this.newWith(3, 2, 1).detectIfNone(Predicates.greaterThan(2), () -> 4), is(3));
        assertThat(this.newWith(3, 2, 1).detectIfNone(Predicates.greaterThan(3), () -> 4), is(4));

        assertThat(this.newWith(3, 2, 1).detectIfNone(Predicates.lessThan(1), () -> 4), is(4));
        assertThat(this.newWith(3, 2, 1).detectIfNone(Predicates.lessThan(2), () -> 4), is(1));
        assertThat(this.newWith(3, 2, 1).detectIfNone(Predicates.lessThan(3), () -> 4), isOneOf(2, 1));
        assertThat(this.newWith(3, 2, 1).detectIfNone(Predicates.lessThan(4), () -> 4), isOneOf(3, 2, 1));

        assertThat(this.newWith(3, 2, 1).detectWithIfNone(Predicates2.greaterThan(), 0, () -> 4), isOneOf(3, 2, 1));
        assertThat(this.newWith(3, 2, 1).detectWithIfNone(Predicates2.greaterThan(), 1, () -> 4), isOneOf(3, 2));
        assertThat(this.newWith(3, 2, 1).detectWithIfNone(Predicates2.greaterThan(), 2, () -> 4), is(3));
        assertThat(this.newWith(3, 2, 1).detectWithIfNone(Predicates2.greaterThan(), 3, () -> 4), is(4));

        assertThat(this.newWith(3, 2, 1).detectWithIfNone(Predicates2.lessThan(), 1, () -> 4), is(4));
        assertThat(this.newWith(3, 2, 1).detectWithIfNone(Predicates2.lessThan(), 2, () -> 4), is(1));
        assertThat(this.newWith(3, 2, 1).detectWithIfNone(Predicates2.lessThan(), 3, () -> 4), isOneOf(2, 1));
        assertThat(this.newWith(3, 2, 1).detectWithIfNone(Predicates2.lessThan(), 4, () -> 4), isOneOf(3, 2, 1));
    }

    @Override
    @Test
    default void RichIterable_detectOptional()
    {
        assertThat(this.newWith(3, 2, 1).detectOptional(Predicates.greaterThan(0)), isOneOf(Optional.of(3), Optional.of(2), Optional.of(1)));
        assertThat(this.newWith(3, 2, 1).detectOptional(Predicates.greaterThan(1)), isOneOf(Optional.of(3), Optional.of(2)));
        assertThat(this.newWith(3, 2, 1).detectOptional(Predicates.greaterThan(2)), is(Optional.of(3)));
        assertThat(this.newWith(3, 2, 1).detectOptional(Predicates.greaterThan(3)), is(Optional.empty()));

        assertThat(this.newWith(3, 2, 1).detectOptional(Predicates.lessThan(1)), is(Optional.empty()));
        assertThat(this.newWith(3, 2, 1).detectOptional(Predicates.lessThan(2)), is(Optional.of(1)));
        assertThat(this.newWith(3, 2, 1).detectOptional(Predicates.lessThan(3)), isOneOf(Optional.of(2), Optional.of(1)));
        assertThat(this.newWith(3, 2, 1).detectOptional(Predicates.lessThan(4)), isOneOf(Optional.of(3), Optional.of(2), Optional.of(1)));

        assertThat(this.newWith(3, 2, 1).detectWithOptional(Predicates2.greaterThan(), 0), isOneOf(Optional.of(3), Optional.of(2), Optional.of(1)));
        assertThat(this.newWith(3, 2, 1).detectWithOptional(Predicates2.greaterThan(), 1), isOneOf(Optional.of(3), Optional.of(2)));
        assertThat(this.newWith(3, 2, 1).detectWithOptional(Predicates2.greaterThan(), 2), is(Optional.of(3)));
        assertThat(this.newWith(3, 2, 1).detectWithOptional(Predicates2.greaterThan(), 3), is(Optional.empty()));

        assertThat(this.newWith(3, 2, 1).detectWithOptional(Predicates2.lessThan(), 1), is(Optional.empty()));
        assertThat(this.newWith(3, 2, 1).detectWithOptional(Predicates2.lessThan(), 2), is(Optional.of(1)));
        assertThat(this.newWith(3, 2, 1).detectWithOptional(Predicates2.lessThan(), 3), isOneOf(Optional.of(2), Optional.of(1)));
        assertThat(this.newWith(3, 2, 1).detectWithOptional(Predicates2.lessThan(), 4), isOneOf(Optional.of(3), Optional.of(2), Optional.of(1)));
    }

    @Override
    @Test
    default void RichIterable_minBy_maxBy()
    {
        // Without an ordering, min can be either ca or da
        RichIterable<String> minIterable = this.newWith("ed", "da", "ca", "bc", "ab");
        String actualMin = minIterable.minBy(string -> string.charAt(string.length() - 1));
        assertThat(actualMin, isOneOf("ca", "da"));
        IterableTestCase.assertEquals(minIterable.detect(each -> each.equals("ca") || each.equals("da")), actualMin);

        assertThrows(NoSuchElementException.class, () -> this.<String>newWith().minBy(string -> string.charAt(string.length() - 1)));

        // Without an ordering, max can be either ca or da
        RichIterable<String> maxIterable = this.newWith("ew", "dz", "cz", "bx", "ay");
        String actualMax = maxIterable.maxBy(string -> string.charAt(string.length() - 1));
        assertThat(actualMax, isOneOf("cz", "dz"));
        IterableTestCase.assertEquals(maxIterable.detect(each -> each.equals("cz") || each.equals("dz")), actualMax);

        assertThrows(NoSuchElementException.class, () -> this.<String>newWith().maxBy(string -> string.charAt(string.length() - 1)));
    }

    @Override
    @Test
    default void RichIterable_minByOptional_maxByOptional()
    {
        // Without an ordering, min can be either ca or da
        RichIterable<String> minIterable = this.newWith("ed", "da", "ca", "bc", "ab");
        String actualMin = minIterable.minByOptional(string -> string.charAt(string.length() - 1)).get();
        assertThat(actualMin, isOneOf("ca", "da"));
        IterableTestCase.assertEquals(minIterable.detect(each -> each.equals("ca") || each.equals("da")), actualMin);

        assertThat(this.<String>newWith().minByOptional(string -> string.charAt(string.length() - 1)), is(Optional.empty()));

        // Without an ordering, max can be either ca or da
        RichIterable<String> maxIterable = this.newWith("ew", "dz", "cz", "bx", "ay");
        String actualMax = maxIterable.maxByOptional(string -> string.charAt(string.length() - 1)).get();
        assertThat(actualMax, isOneOf("cz", "dz"));
        IterableTestCase.assertEquals(maxIterable.detect(each -> each.equals("cz") || each.equals("dz")), actualMax);

        assertThat(this.<String>newWith().maxByOptional(string -> string.charAt(string.length() - 1)), is(Optional.empty()));
    }
}
