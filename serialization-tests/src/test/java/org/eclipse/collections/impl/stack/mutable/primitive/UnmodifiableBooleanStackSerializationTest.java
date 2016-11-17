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

public class UnmodifiableBooleanStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLm11dGFibGUucHJpbWl0\n"
                        + "aXZlLlVubW9kaWZpYWJsZUJvb2xlYW5TdGFjawAAAAAAAAABAgABTAAFc3RhY2t0AEFMb3JnL2Vj\n"
                        + "bGlwc2UvY29sbGVjdGlvbnMvYXBpL3N0YWNrL3ByaW1pdGl2ZS9NdXRhYmxlQm9vbGVhblN0YWNr\n"
                        + "O3hwc3IARm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuc3RhY2subXV0YWJsZS5wcmltaXRp\n"
                        + "dmUuQm9vbGVhbkFycmF5U3RhY2sAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new UnmodifiableBooleanStack(new BooleanArrayStack()));
    }
}
