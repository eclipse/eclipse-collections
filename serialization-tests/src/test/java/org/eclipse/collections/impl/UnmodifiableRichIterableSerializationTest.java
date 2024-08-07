/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class UnmodifiableRichIterableSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLlVubW9kaWZpYWJsZVJpY2hJdGVy\n"
                        + "YWJsZQAAAAAAAAABAgABTAAIaXRlcmFibGV0ACpMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBp\n"
                        + "L1JpY2hJdGVyYWJsZTt4cHNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0\n"
                        + "YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4",
                UnmodifiableRichIterable.of(Lists.mutable.of()));
    }
}
