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

public class InjectIntoProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5Jbmpl\n"
                        + "Y3RJbnRvUHJvY2VkdXJlAAAAAAAAAAECAAJMAAhmdW5jdGlvbnQANkxvcmcvZWNsaXBzZS9jb2xs\n"
                        + "ZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb24yO0wABnJlc3VsdHQAEkxqYXZhL2xh\n"
                        + "bmcvT2JqZWN0O3hwcHA=",
                new InjectIntoProcedure<>(null, null));
    }
}
