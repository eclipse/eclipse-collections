/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.checked;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class MultimapKeyValuesSerializingProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5jaGVj\n"
                        + "a2VkLk11bHRpbWFwS2V5VmFsdWVzU2VyaWFsaXppbmdQcm9jZWR1cmUAAAAAAAAAAQIAAUwAA291\n"
                        + "dHQAFkxqYXZhL2lvL09iamVjdE91dHB1dDt4cgBGb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5ibG9jay5wcm9jZWR1cmUuY2hlY2tlZC5DaGVja2VkUHJvY2VkdXJlMgAAAAAAAAABAgAAeHBw\n",
                new MultimapKeyValuesSerializingProcedure<>(null));
    }
}
