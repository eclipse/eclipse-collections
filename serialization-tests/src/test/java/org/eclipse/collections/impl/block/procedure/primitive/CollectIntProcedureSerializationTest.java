/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class CollectIntProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuQ29sbGVjdEludFByb2NlZHVyZQAAAAAAAAABAgACTAANaW50Q29sbGVjdGlvbnQAR0xv\n"
                        + "cmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvY29sbGVjdGlvbi9wcmltaXRpdmUvTXV0YWJsZUlu\n"
                        + "dENvbGxlY3Rpb247TAALaW50RnVuY3Rpb250AEJMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBp\n"
                        + "L2Jsb2NrL2Z1bmN0aW9uL3ByaW1pdGl2ZS9JbnRGdW5jdGlvbjt4cHBw",
                new CollectIntProcedure(null, null));
    }
}
