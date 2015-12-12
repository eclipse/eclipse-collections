/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import java.util.List;

import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;

public final class AddToList implements ObjectIntProcedure<Integer>
{
    private static final long serialVersionUID = 1L;
    private final List<Integer> result;

    public AddToList(List<Integer> result)
    {
        this.result = result;
    }

    @Override
    public void value(Integer each, int index)
    {
        this.result.add(each);
    }
}
