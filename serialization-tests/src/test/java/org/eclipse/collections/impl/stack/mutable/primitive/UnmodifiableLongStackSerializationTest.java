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

public class UnmodifiableLongStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLm11dGFibGUucHJpbWl0\n"
                        + "aXZlLlVubW9kaWZpYWJsZUxvbmdTdGFjawAAAAAAAAABAgABTAAFc3RhY2t0AD5Mb3JnL2VjbGlw\n"
                        + "c2UvY29sbGVjdGlvbnMvYXBpL3N0YWNrL3ByaW1pdGl2ZS9NdXRhYmxlTG9uZ1N0YWNrO3hwc3IA\n"
                        + "Q29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuc3RhY2subXV0YWJsZS5wcmltaXRpdmUuTG9u\n"
                        + "Z0FycmF5U3RhY2sAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new UnmodifiableLongStack(new LongArrayStack()));
    }
}
