/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ObjectIntProceduresSerializationTest
{
    @Test
    public void fromProcedure()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuT2JqZWN0\n"
                        + "SW50UHJvY2VkdXJlcyRQcm9jZWR1cmVBZGFwdGVyAAAAAAAAAAECAAFMAAlwcm9jZWR1cmV0ADdM\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3Byb2NlZHVyZS9Qcm9jZWR1cmU7eHBw\n",
                ObjectIntProcedures.fromProcedure(null));
    }
}
