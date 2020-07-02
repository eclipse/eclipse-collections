/*
 * Copyright (c) 2016 Goldman Sachs.
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

public class SynchronizedCharObjectMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRDaGFyT2JqZWN0TWFwAAAAAAAAAAECAAJMAARsb2NrdAASTGphdmEvbGFu\n"
                        + "Zy9PYmplY3Q7TAADbWFwdABATG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9tYXAvcHJpbWl0\n"
                        + "aXZlL011dGFibGVDaGFyT2JqZWN0TWFwO3hwcQB+AANzcgBEb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuQ2hhck9iamVjdEhhc2hNYXAAAAAAAAAAAQwA\n"
                        + "AHhwdwQAAAAAeA==",
                new SynchronizedCharObjectMap<>(new CharObjectHashMap<>()));
    }

    @Test
    public void keySetSerializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRDaGFyU2V0AAAAAAAAAAECAAB4cgBcb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5jb2xsZWN0aW9uLm11dGFibGUucHJpbWl0aXZlLkFic3RyYWN0U3luY2hyb25pemVk\n"
                        + "Q2hhckNvbGxlY3Rpb24AAAAAAAAAAQIAAkwACmNvbGxlY3Rpb250AEhMb3JnL2VjbGlwc2UvY29s\n"
                        + "bGVjdGlvbnMvYXBpL2NvbGxlY3Rpb24vcHJpbWl0aXZlL011dGFibGVDaGFyQ29sbGVjdGlvbjtM\n"
                        + "AARsb2NrdAASTGphdmEvbGFuZy9PYmplY3Q7eHBzcgBTb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RNdXRhYmxlQ2hhcktleVNldCRTZXJS\n"
                        + "ZXAAAAAAAAAAAQwAAHhwdwQAAAAAeHNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1h\n"
                        + "cC5tdXRhYmxlLnByaW1pdGl2ZS5TeW5jaHJvbml6ZWRDaGFyT2JqZWN0TWFwAAAAAAAAAAECAAJM\n"
                        + "AARsb2NrcQB+AANMAANtYXB0AEBMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL21hcC9wcmlt\n"
                        + "aXRpdmUvTXV0YWJsZUNoYXJPYmplY3RNYXA7eHBxAH4ACXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2ZS5DaGFyT2JqZWN0SGFzaE1hcAAAAAAAAAAB\n"
                        + "DAAAeHB3BAAAAAB4",
                new SynchronizedCharObjectMap<>(new CharObjectHashMap<>()).keySet());
    }
}
