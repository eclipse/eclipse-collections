/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class FlatCollectProcedureCombinerSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnBhcmFsbGVsLkZsYXRDb2xsZWN0\n"
                        + "UHJvY2VkdXJlQ29tYmluZXIAAAAAAAAAAQIAAHhyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLnBhcmFsbGVsLkFic3RyYWN0VHJhbnNmb3JtZXJCYXNlZENvbWJpbmVyAAAAAAAAAAECAAFM\n"
                        + "AAZyZXN1bHR0ABZMamF2YS91dGlsL0NvbGxlY3Rpb247eHIAP29yZy5lY2xpcHNlLmNvbGxlY3Rp\n"
                        + "b25zLmltcGwucGFyYWxsZWwuQWJzdHJhY3RQcm9jZWR1cmVDb21iaW5lcgAAAAAAAAABAgABWgAN\n"
                        + "dXNlQ29tYmluZU9uZXhwAHNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0\n"
                        + "YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4",
                new FlatCollectProcedureCombiner<>(null, null, 1, false));
    }
}
