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

public class SynchronizedIntStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLm11dGFibGUucHJpbWl0\n"
                        + "aXZlLlN5bmNocm9uaXplZEludFN0YWNrAAAAAAAAAAECAAJMAARsb2NrdAASTGphdmEvbGFuZy9P\n"
                        + "YmplY3Q7TAAFc3RhY2t0AD1Mb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL3N0YWNrL3ByaW1p\n"
                        + "dGl2ZS9NdXRhYmxlSW50U3RhY2s7eHBxAH4AA3NyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLnN0YWNrLm11dGFibGUucHJpbWl0aXZlLkludEFycmF5U3RhY2sAAAAAAAAAAQwAAHhwdwQA\n"
                        + "AAAAeA==",
                new SynchronizedIntStack(new IntArrayStack()));
    }
}
