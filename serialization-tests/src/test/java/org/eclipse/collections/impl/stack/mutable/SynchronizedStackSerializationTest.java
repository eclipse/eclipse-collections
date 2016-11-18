/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.mutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class SynchronizedStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLm11dGFibGUuU3luY2hy\n"
                        + "b25pemVkU3RhY2sAAAAAAAAAAQIAAkwACGRlbGVnYXRldAAwTG9yZy9lY2xpcHNlL2NvbGxlY3Rp\n"
                        + "b25zL2FwaS9zdGFjay9NdXRhYmxlU3RhY2s7TAAEbG9ja3QAEkxqYXZhL2xhbmcvT2JqZWN0O3hw\n"
                        + "c3IANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuc3RhY2subXV0YWJsZS5BcnJheVN0YWNr\n"
                        + "AAAAAAAAAAEMAAB4cHcEAAAAAHhxAH4AAw==",
                SynchronizedStack.of(ArrayStack.newStack()));
    }
}
