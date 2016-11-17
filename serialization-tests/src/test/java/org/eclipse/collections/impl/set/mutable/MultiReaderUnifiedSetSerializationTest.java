/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class MultiReaderUnifiedSetSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLk11bHRpUmVh\n"
                        + "ZGVyVW5pZmllZFNldAAAAAAAAAABDAAAeHBzcgAzb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5zZXQubXV0YWJsZS5VbmlmaWVkU2V0AAAAAAAAAAEMAAB4cHcIAAAAAD9AAAB4eA==",
                MultiReaderUnifiedSet.newSet());
    }
}
