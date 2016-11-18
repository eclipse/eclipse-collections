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

public class UnmodifiableStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLm11dGFibGUuVW5tb2Rp\n"
                        + "ZmlhYmxlU3RhY2sAAAAAAAAAAQIAAUwADG11dGFibGVTdGFja3QAMExvcmcvZWNsaXBzZS9jb2xs\n"
                        + "ZWN0aW9ucy9hcGkvc3RhY2svTXV0YWJsZVN0YWNrO3hwc3IANW9yZy5lY2xpcHNlLmNvbGxlY3Rp\n"
                        + "b25zLmltcGwuc3RhY2subXV0YWJsZS5BcnJheVN0YWNrAAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                UnmodifiableStack.of(ArrayStack.newStack()));
    }
}
