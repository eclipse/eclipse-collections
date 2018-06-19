/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CollectShortProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuQ29sbGVjdFNob3J0UHJvY2VkdXJlAAAAAAAAAAECAAJMAA9zaG9ydENvbGxlY3Rpb250\n"
                        + "AElMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2NvbGxlY3Rpb24vcHJpbWl0aXZlL011dGFi\n"
                        + "bGVTaG9ydENvbGxlY3Rpb247TAANc2hvcnRGdW5jdGlvbnQARExvcmcvZWNsaXBzZS9jb2xsZWN0\n"
                        + "aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZlL1Nob3J0RnVuY3Rpb247eHBwcA==",
                new CollectShortProcedure(null, null));
    }
}
