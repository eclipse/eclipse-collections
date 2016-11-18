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

public class MultimapEachPutProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                2L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5NdWx0\n"
                        + "aW1hcEVhY2hQdXRQcm9jZWR1cmUAAAAAAAAAAgIAAkwAC2tleUZ1bmN0aW9udAA1TG9yZy9lY2xp\n"
                        + "cHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjtMAAhtdWx0aW1hcHQA\n"
                        + "NkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvbXVsdGltYXAvTXV0YWJsZU11bHRpbWFwO3hw\n"
                        + "cHA=",
                new MultimapEachPutProcedure<>(null, null));
    }
}
