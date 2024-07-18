/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.primitive;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.stack.primitive.BooleanStack;
import org.eclipse.collections.api.stack.primitive.ImmutableBooleanStack;
import org.eclipse.collections.impl.collection.mutable.primitive.AbstractBooleanIterableTestCase;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Abstract JUnit test for {@link BooleanStack}.
 */
public abstract class AbstractBooleanStackTestCase extends AbstractBooleanIterableTestCase
{
    @Override
    protected abstract BooleanStack classUnderTest();

    @Override
    protected abstract BooleanStack newWith(boolean... elements);

    @Override
    protected abstract BooleanStack newMutableCollectionWith(boolean... elements);

    @Override
    protected RichIterable<Object> newObjectCollectionWith(Object... elements)
    {
        return ArrayStack.newStackWith(elements);
    }

    protected abstract BooleanStack newWithTopToBottom(boolean... elements);

    @Override
    @Test
    public void booleanIterator()
    {
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        int size = this.classUnderTest().size();
        for (int i = 0; i < size; i++)
        {
            assertTrue(iterator.hasNext());
            boolean sizeEven = (size & 1) == 0;
            boolean iEven = (i & 1) == 0;
            assertEquals(sizeEven != iEven, iterator.next());
        }
        assertFalse(iterator.hasNext());
        assertEquals((this.classUnderTest().size() & 1) != 0, this.classUnderTest().booleanIterator().next());
    }

    @Test
    public void peek()
    {
        assertEquals((this.classUnderTest().size() & 1) != 0, this.classUnderTest().peek());
        assertEquals(BooleanArrayList.newListWith(), this.classUnderTest().peek(0));
        assertEquals(
                BooleanArrayList.newListWith((this.classUnderTest().size() & 1) != 0, (this.classUnderTest().size() & 1) == 0),
                this.classUnderTest().peek(2));
    }

    @Test
    public void peekAtIndex()
    {
        int size = this.classUnderTest().size();
        for (int i = 0; i < size; i++)
        {
            boolean sizeEven = (this.classUnderTest().size() & 1) == 0;
            boolean iEven = (i & 1) == 0;
            assertEquals(sizeEven != iEven, this.classUnderTest().peekAt(i));
        }
    }

    @Test
    public void peek_at_index_less_than_zero_throws_exception()
    {
        assertThrows(IllegalArgumentException.class, () -> this.classUnderTest().peekAt(-1));
    }

    @Test
    public void peek_at_index_greater_than_size_throws_exception()
    {
        assertThrows(IllegalArgumentException.class, () -> this.classUnderTest().peekAt(this.classUnderTest().size() + 1));
    }

    @Test
    public void peek_at_index_equal_to_size_throws_exception()
    {
        assertThrows(IllegalArgumentException.class, () -> this.classUnderTest().peekAt(this.classUnderTest().size()));
    }

    @Override
    @Test
    public void testToString()
    {
        super.testToString();
        assertEquals(this.createExpectedString("[", ", ", "]"), this.classUnderTest().toString());
    }

    @Override
    @Test
    public void toList()
    {
        super.toList();
        MutableBooleanList list = new BooleanArrayList();
        int size = this.classUnderTest().size();
        for (int i = 0; i < size; i++)
        {
            list.add((i & 1) != 0);
        }
        assertEquals(list, this.classUnderTest().toList());
    }

    @Override
    @Test
    public void makeString()
    {
        super.makeString();
        assertEquals(this.createExpectedString("", ", ", ""), this.classUnderTest().makeString());
        assertEquals(this.createExpectedString("", "|", ""), this.classUnderTest().makeString("|"));
        assertEquals(this.createExpectedString("{", "|", "}"), this.classUnderTest().makeString("{", "|", "}"));
    }

    protected String createExpectedString(String start, String sep, String end)
    {
        StringBuilder expectedString = new StringBuilder(start);
        int size = this.classUnderTest().size();
        for (int i = 0; i < size; i++)
        {
            boolean sizeEven = (this.classUnderTest().size() & 1) == 0;
            boolean iEven = (i & 1) == 0;
            expectedString.append(sizeEven != iEven);
            expectedString.append(i == size - 1 ? "" : sep);
        }
        expectedString.append(end);
        return expectedString.toString();
    }

    @Override
    @Test
    public void appendString()
    {
        super.appendString();
        StringBuilder appendable1 = new StringBuilder();
        this.classUnderTest().appendString(appendable1);
        assertEquals(this.createExpectedString("", ", ", ""), appendable1.toString());

        StringBuilder appendable2 = new StringBuilder();
        this.classUnderTest().appendString(appendable2, "|");
        assertEquals(this.createExpectedString("", "|", ""), appendable2.toString());

        StringBuilder appendable3 = new StringBuilder();
        this.classUnderTest().appendString(appendable3, "{", "|", "}");
        assertEquals(this.createExpectedString("{", "|", "}"), appendable3.toString());
    }

    @Test
    public void toImmutable()
    {
        assertEquals(this.classUnderTest(), this.classUnderTest().toImmutable());
        Verify.assertInstanceOf(ImmutableBooleanStack.class, this.classUnderTest().toImmutable());
    }
}
