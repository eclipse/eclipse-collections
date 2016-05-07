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

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class FlatCollectProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5GbGF0\n"
                        + "Q29sbGVjdFByb2NlZHVyZQAAAAAAAAABAgACTAAKY29sbGVjdGlvbnQAFkxqYXZhL3V0aWwvQ29s\n"
                        + "bGVjdGlvbjtMAAhmdW5jdGlvbnQANUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2sv\n"
                        + "ZnVuY3Rpb24vRnVuY3Rpb247eHBwcA==",
                new FlatCollectProcedure<>(null, null));
    }
}
