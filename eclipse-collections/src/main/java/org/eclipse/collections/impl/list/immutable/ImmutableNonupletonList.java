/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable;

import java.io.Serializable;
import java.util.RandomAccess;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

/**
 * This is a nine element immutable List which is created by calling
 * Lists.immutable.with(one, two, three, four, five, six, seven, eight, nine) method.
 */
final class ImmutableNonupletonList<T>
        extends AbstractImmutableList<T>
        implements Serializable, RandomAccess
{
    private static final long serialVersionUID = 1L;

    private final T element1;
    private final T element2;
    private final T element3;
    private final T element4;
    private final T element5;
    private final T element6;
    private final T element7;
    private final T element8;
    private final T element9;

    ImmutableNonupletonList(T obj1, T obj2, T obj3, T obj4, T obj5, T obj6, T obj7, T obj8, T obj9)
    {
        this.element1 = obj1;
        this.element2 = obj2;
        this.element3 = obj3;
        this.element4 = obj4;
        this.element5 = obj5;
        this.element6 = obj6;
        this.element7 = obj7;
        this.element8 = obj8;
        this.element9 = obj9;
    }

    @Override
    public int size()
    {
        return 9;
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        procedure.value(this.element1);
        procedure.value(this.element2);
        procedure.value(this.element3);
        procedure.value(this.element4);
        procedure.value(this.element5);
        procedure.value(this.element6);
        procedure.value(this.element7);
        procedure.value(this.element8);
        procedure.value(this.element9);
    }

    @Override
    public T get(int index)
    {
        switch (index)
        {
            case 0:
                return this.element1;
            case 1:
                return this.element2;
            case 2:
                return this.element3;
            case 3:
                return this.element4;
            case 4:
                return this.element5;
            case 5:
                return this.element6;
            case 6:
                return this.element7;
            case 7:
                return this.element8;
            case 8:
                return this.element9;
            default:
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
        }
    }

    @Override
    public T getOnly()
    {
        throw new IllegalStateException("Size must be 1 but was " + this.size());
    }

    @Override
    public ImmutableList<T> newWith(T newItem)
    {
        return Lists.immutable.with(this.get(0),
                this.get(1),
                this.get(2),
                this.get(3),
                this.get(4),
                this.get(5),
                this.get(6),
                this.get(7),
                this.get(8),
                newItem);
    }
}
