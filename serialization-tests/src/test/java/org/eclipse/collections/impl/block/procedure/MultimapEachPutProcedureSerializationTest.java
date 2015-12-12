/*
 * Copyright (c) 2015 Goldman Sachs.
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
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5NdWx0\n"
                        + "aW1hcEVhY2hQdXRQcm9jZWR1cmUAAAAAAAAAAQIAA0wADWVhY2hQcm9jZWR1cmV0ADhMb3JnL2Vj\n"
                        + "bGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3Byb2NlZHVyZS9Qcm9jZWR1cmUyO0wAC2tleUZ1\n"
                        + "bmN0aW9udAA1TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5j\n"
                        + "dGlvbjtMAAhtdWx0aW1hcHQANkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvbXVsdGltYXAv\n"
                        + "TXV0YWJsZU11bHRpbWFwO3hwc3IAR29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2su\n"
                        + "cHJvY2VkdXJlLk11bHRpbWFwRWFjaFB1dFByb2NlZHVyZSQxNw5+OlkSRcgCAAFMAAZ0aGlzJDB0\n"
                        + "AEdMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvaW1wbC9ibG9jay9wcm9jZWR1cmUvTXVsdGltYXBF\n"
                        + "YWNoUHV0UHJvY2VkdXJlO3hwcQB+AARwcA==",
                new MultimapEachPutProcedure<Object, Object>(null, null));
    }
}
