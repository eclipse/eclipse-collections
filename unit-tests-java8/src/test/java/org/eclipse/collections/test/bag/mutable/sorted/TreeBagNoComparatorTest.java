/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bag.mutable.sorted;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.eclipse.collections.test.IterableTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.eclipse.collections.impl.test.Verify.assertThrows;
import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(Java8Runner.class)
public class TreeBagNoComparatorTest implements MutableSortedBagNoComparatorTestCase
{
    @SafeVarargs
    @Override
    public final <T> MutableSortedBag<T> newWith(T... elements)
    {
        MutableSortedBag<T> result = new TreeBag<>();
        IterableTestCase.addAllTo(elements, result);
        return result;
    }

    @Override
    @Test
    public void OrderedIterable_getFirstOptional()
    {
        assertEquals(Optional.of(Integer.valueOf(1)), ((OrderedIterable<?>) this.newWith(3, 3, 3, 2, 2, 1)).getFirstOptional());
    }

    @Override
    @Test
    public void OrderedIterable_getLastOptional()
    {
        assertEquals(Optional.of(Integer.valueOf(3)), ((OrderedIterable<?>) this.newWith(3, 3, 3, 2, 2, 1)).getLastOptional());
    }

    @Override
    @Test
    public void RichIterable_minByOptional_maxByOptional()
    {
        assertEquals(Optional.of("ca"), this.newWith("ed", "da", "ca", "bc", "ab").minByOptional(string -> string.charAt(string.length() - 1)));
        assertSame(Optional.empty(), this.<String>newWith().minByOptional(string -> string.charAt(string.length() - 1)));
        assertThrows(NullPointerException.class, () -> this.newWith(new Object[]{null}).minByOptional(Objects::isNull));

        assertEquals(Optional.of("cz"), this.newWith("ew", "dz", "cz", "bx", "ay").maxByOptional(string -> string.charAt(string.length() - 1)));
        assertSame(Optional.empty(), this.<String>newWith().maxByOptional(string -> string.charAt(string.length() - 1)));
        assertThrows(NullPointerException.class, () -> this.newWith(new Object[]{null}).maxByOptional(Objects::isNull));
    }
}
