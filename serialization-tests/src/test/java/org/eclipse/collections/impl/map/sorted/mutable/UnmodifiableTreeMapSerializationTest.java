/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.sorted.mutable;

import org.eclipse.collections.impl.factory.SortedMaps;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class UnmodifiableTreeMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5zb3J0ZWQubXV0YWJsZS5V\n"
                        + "bm1vZGlmaWFibGVUcmVlTWFwAAAAAAAAAAECAAB4cgBFb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5tYXAuc29ydGVkLm11dGFibGUuVW5tb2RpZmlhYmxlU29ydGVkTWFwAAAAAAAAAAECAAB4\n"
                        + "cgAsb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5Vbm1vZGlmaWFibGVNYXAAAAAAAAAAAQIA\n"
                        + "AUwACGRlbGVnYXRldAAPTGphdmEvdXRpbC9NYXA7eHBzcgA9b3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5tYXAuc29ydGVkLm11dGFibGUuVHJlZVNvcnRlZE1hcAAAAAAAAAABDAAAeHBwdwQA\n"
                        + "AAAAeA==",
                UnmodifiableTreeMap.of(SortedMaps.mutable.of()));
    }
}
