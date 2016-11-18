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

public class SynchronizedFloatStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLm11dGFibGUucHJpbWl0\n"
                        + "aXZlLlN5bmNocm9uaXplZEZsb2F0U3RhY2sAAAAAAAAAAQIAAkwABGxvY2t0ABJMamF2YS9sYW5n\n"
                        + "L09iamVjdDtMAAVzdGFja3QAP0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvc3RhY2svcHJp\n"
                        + "bWl0aXZlL011dGFibGVGbG9hdFN0YWNrO3hwcQB+AANzcgBEb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5zdGFjay5tdXRhYmxlLnByaW1pdGl2ZS5GbG9hdEFycmF5U3RhY2sAAAAAAAAAAQwA\n"
                        + "AHhwdwQAAAAAeA==",
                new SynchronizedFloatStack(new FloatArrayStack()));
    }
}
