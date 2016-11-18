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

public class SynchronizedBooleanStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLm11dGFibGUucHJpbWl0\n"
                        + "aXZlLlN5bmNocm9uaXplZEJvb2xlYW5TdGFjawAAAAAAAAABAgACTAAEbG9ja3QAEkxqYXZhL2xh\n"
                        + "bmcvT2JqZWN0O0wABXN0YWNrdABBTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9zdGFjay9w\n"
                        + "cmltaXRpdmUvTXV0YWJsZUJvb2xlYW5TdGFjazt4cHEAfgADc3IARm9yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwuc3RhY2subXV0YWJsZS5wcmltaXRpdmUuQm9vbGVhbkFycmF5U3RhY2sAAAAA\n"
                        + "AAAAAQwAAHhwdwQAAAAAeA==",
                new SynchronizedBooleanStack(new BooleanArrayStack()));
    }
}
