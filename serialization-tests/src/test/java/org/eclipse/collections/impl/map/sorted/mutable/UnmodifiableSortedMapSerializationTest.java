/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.sorted.mutable;

import java.util.TreeMap;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class UnmodifiableSortedMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5zb3J0ZWQubXV0YWJsZS5V\n"
                        + "bm1vZGlmaWFibGVTb3J0ZWRNYXAAAAAAAAAAAQIAAHhyACxvcmcuZWNsaXBzZS5jb2xsZWN0aW9u\n"
                        + "cy5pbXBsLlVubW9kaWZpYWJsZU1hcAAAAAAAAAABAgABTAAIZGVsZWdhdGV0AA9MamF2YS91dGls\n"
                        + "L01hcDt4cHNyABFqYXZhLnV0aWwuVHJlZU1hcAzB9j4tJWrmAwABTAAKY29tcGFyYXRvcnQAFkxq\n"
                        + "YXZhL3V0aWwvQ29tcGFyYXRvcjt4cHB3BAAAAAB4",
                new UnmodifiableSortedMap<>(new TreeMap<>()));
    }
}
