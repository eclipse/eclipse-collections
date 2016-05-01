/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import org.eclipse.collections.api.block.predicate.Predicate;

final class AllSatisfyPredicate<T> implements Predicate<T>
{
    private final Predicate<? super T> left;
    private final Predicate<? super T> right;

    AllSatisfyPredicate(Predicate<? super T> left, Predicate<? super T> right)
    {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean accept(T each)
    {
        boolean leftResult = this.left.accept(each);
        return !leftResult || this.right.accept(each);
    }
}
