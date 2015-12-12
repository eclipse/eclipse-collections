/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.mutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class UnmodifiableShortStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLm11dGFibGUucHJpbWl0\n"
                        + "aXZlLlVubW9kaWZpYWJsZVNob3J0U3RhY2sAAAAAAAAAAQIAAUwABXN0YWNrdAA/TG9yZy9lY2xp\n"
                        + "cHNlL2NvbGxlY3Rpb25zL2FwaS9zdGFjay9wcmltaXRpdmUvTXV0YWJsZVNob3J0U3RhY2s7eHBz\n"
                        + "cgBEb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5zdGFjay5tdXRhYmxlLnByaW1pdGl2ZS5T\n"
                        + "aG9ydEFycmF5U3RhY2sAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new UnmodifiableShortStack(new ShortArrayStack()));
    }
}
