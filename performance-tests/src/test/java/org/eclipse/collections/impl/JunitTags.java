/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

public final class JunitTags
{
    public static final String PERFORMANCE_TEST_TAG = "performance-test";
    public static final String PARALLEL_TEST_TAG = "parallel-test";
    public static final String MEMORY_TEST_TAG = "memory-test";

    private JunitTags()
    {
        // Not meant to be instantiated
    }
}
