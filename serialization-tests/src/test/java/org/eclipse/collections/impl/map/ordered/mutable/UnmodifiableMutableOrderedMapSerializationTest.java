/*
 * Copyright (c) 2021 Two Sigma.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.ordered.mutable;

import java.util.LinkedHashMap;

import org.eclipse.collections.impl.factory.OrderedMaps;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class UnmodifiableMutableOrderedMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5vcmRlcmVkLm11dGFibGUu\n"
                        + "VW5tb2RpZmlhYmxlTXV0YWJsZU9yZGVyZWRNYXAAAAAAAAAAAQIAAUwACGRlbGVnYXRldAAzTG9y\n"
                        + "Zy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9tYXAvTXV0YWJsZU9yZGVyZWRNYXA7eHBzcgBCb3Jn\n"
                        + "LmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5tYXAub3JkZXJlZC5tdXRhYmxlLk9yZGVyZWRNYXBB\n"
                        + "ZGFwdGVyAAAAAAAAAAECAAFMAAhkZWxlZ2F0ZXQAD0xqYXZhL3V0aWwvTWFwO3hwc3IAF2phdmEu\n"
                        + "dXRpbC5MaW5rZWRIYXNoTWFwNMBOXBBswPsCAAFaAAthY2Nlc3NPcmRlcnhyABFqYXZhLnV0aWwu\n"
                        + "SGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAA\n"
                        + "ABAAAAAAeAA=",
                UnmodifiableMutableOrderedMap.of(OrderedMaps.adapt(new LinkedHashMap<>())));
    }
}
