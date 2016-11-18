/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.impl.block.procedure.primitive.InjectIntoDoubleProcedure;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class InjectIntoDoubleProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuSW5qZWN0SW50b0RvdWJsZVByb2NlZHVyZQAAAAAAAAABAgACRAAGcmVzdWx0TAAIZnVu\n"
                        + "Y3Rpb250AFNMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1p\n"
                        + "dGl2ZS9Eb3VibGVPYmplY3RUb0RvdWJsZUZ1bmN0aW9uO3hwAAAAAAAAAABw",
                new InjectIntoDoubleProcedure<>(0.0, null));
    }
}
