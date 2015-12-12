/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class UnmodifiableCharDoubleMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5Vbm1vZGlmaWFibGVDaGFyRG91YmxlTWFwAAAAAAAAAAECAAFMAANtYXB0AEBMb3JnL2VjbGlw\n"
                        + "c2UvY29sbGVjdGlvbnMvYXBpL21hcC9wcmltaXRpdmUvTXV0YWJsZUNoYXJEb3VibGVNYXA7eHBz\n"
                        + "cgBEb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuQ2hh\n"
                        + "ckRvdWJsZUhhc2hNYXAAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new UnmodifiableCharDoubleMap(new CharDoubleHashMap()));
    }
}
