/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable;

public class UnifiedSetOverridesTest extends UnifiedSetTest
{
    public static class UnifiedSetOverriddes<T> extends UnifiedSet<T>
    {
        public UnifiedSetOverriddes(int size)
        {
            super(size);
        }

        @Override
        protected int index(Object key)
        {
            int h = key == null ? 0 : key.hashCode();
            return h & this.table.length - 1;
        }

        @Override
        public UnifiedSetOverriddes<T> newEmpty(int size)
        {
            return new UnifiedSetOverriddes<>(size);
        }
    }

    @Override
    protected <T> UnifiedSet<T> newWith(T... littleElements)
    {
        UnifiedSet<T> set = new UnifiedSetOverriddes<T>(littleElements.length);
        return set.with(littleElements);
    }
}
