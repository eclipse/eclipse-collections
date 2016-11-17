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

public class SynchronizedByteStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLm11dGFibGUucHJpbWl0\n"
                        + "aXZlLlN5bmNocm9uaXplZEJ5dGVTdGFjawAAAAAAAAABAgACTAAEbG9ja3QAEkxqYXZhL2xhbmcv\n"
                        + "T2JqZWN0O0wABXN0YWNrdAA+TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9zdGFjay9wcmlt\n"
                        + "aXRpdmUvTXV0YWJsZUJ5dGVTdGFjazt4cHEAfgADc3IAQ29yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwuc3RhY2subXV0YWJsZS5wcmltaXRpdmUuQnl0ZUFycmF5U3RhY2sAAAAAAAAAAQwAAHhw\n"
                        + "dwQAAAAAeA==",
                new SynchronizedByteStack(new ByteArrayStack()));
    }
}
