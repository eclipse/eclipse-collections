/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.test.junit;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class Java8Runner extends BlockJUnit4ClassRunner
{
    /**
     * Creates a Java8Runner to run {@code klass}
     *
     * @throws InitializationError if the test class is malformed.
     */
    public Java8Runner(Class<?> klass) throws InitializationError
    {
        super(klass);
    }

    @Override
    protected Java8TestClass createTestClass(Class<?> testClass)
    {
        return new Java8TestClass(testClass);
    }
}
