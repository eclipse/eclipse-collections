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

public class SumOfDoubleProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                2L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5TdW1P\n"
                        + "ZkRvdWJsZVByb2NlZHVyZQAAAAAAAAACAgADRAAMY29tcGVuc2F0aW9uRAAGcmVzdWx0TAAIZnVu\n"
                        + "Y3Rpb250AEVMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1p\n"
                        + "dGl2ZS9Eb3VibGVGdW5jdGlvbjt4cAAAAAAAAAAAAAAAAAAAAABw",
                new SumOfDoubleProcedure<Integer>(null));
    }
}
