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

import org.eclipse.collections.api.list.MutableList;

public class FastListAsUnmodifiableTest extends UnmodifiableMutableListTestCase
{
    @Override
    protected MutableList<Integer> getCollection()
    {
        return FastList.newListWith(1).asUnmodifiable();
    }
}
