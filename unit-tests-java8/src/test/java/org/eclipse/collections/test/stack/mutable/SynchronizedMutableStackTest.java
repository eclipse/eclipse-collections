/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.stack.mutable;

import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.junit.runner.RunWith;

@RunWith(Java8Runner.class)
public class SynchronizedMutableStackTest implements MutableStackTestCase
{
    @SafeVarargs
    @Override
    public final <T> MutableStack<T> newWith(T... elements)
    {
        return ArrayStack.newStackFromTopToBottom(elements).asSynchronized();
    }
}
