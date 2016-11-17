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

public class MaxByProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5NYXhC\n"
                        + "eVByb2NlZHVyZQAAAAAAAAABAgAEWgASdmlzaXRlZEF0TGVhc3RPbmNlTAARY2FjaGVkUmVzdWx0\n"
                        + "VmFsdWV0ABZMamF2YS9sYW5nL0NvbXBhcmFibGU7TAAIZnVuY3Rpb250ADVMb3JnL2VjbGlwc2Uv\n"
                        + "Y29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL0Z1bmN0aW9uO0wABnJlc3VsdHQAEkxqYXZh\n"
                        + "L2xhbmcvT2JqZWN0O3hwAHBwcA==",
                new MaxByProcedure<Integer, Integer>(null));
    }
}
