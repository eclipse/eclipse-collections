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

import org.eclipse.collections.impl.block.procedure.primitive.InjectIntoIntProcedure;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class InjectIntoIntProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuSW5qZWN0SW50b0ludFByb2NlZHVyZQAAAAAAAAABAgACSQAGcmVzdWx0TAAIZnVuY3Rp\n"
                        + "b250AE1Mb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1pdGl2\n"
                        + "ZS9JbnRPYmplY3RUb0ludEZ1bmN0aW9uO3hwAAAAAHA=",
                new InjectIntoIntProcedure<>(0, null));
    }
}
