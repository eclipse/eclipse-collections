/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class SynchronizedBooleanBagSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRCb29sZWFuQmFnAAAAAAAAAAECAAB4cgBfb3JnLmVjbGlwc2UuY29sbGVj\n"
                        + "dGlvbnMuaW1wbC5jb2xsZWN0aW9uLm11dGFibGUucHJpbWl0aXZlLkFic3RyYWN0U3luY2hyb25p\n"
                        + "emVkQm9vbGVhbkNvbGxlY3Rpb24AAAAAAAAAAQIAAkwACmNvbGxlY3Rpb250AEtMb3JnL2VjbGlw\n"
                        + "c2UvY29sbGVjdGlvbnMvYXBpL2NvbGxlY3Rpb24vcHJpbWl0aXZlL011dGFibGVCb29sZWFuQ29s\n"
                        + "bGVjdGlvbjtMAARsb2NrdAASTGphdmEvbGFuZy9PYmplY3Q7eHBzcgBBb3JnLmVjbGlwc2UuY29s\n"
                        + "bGVjdGlvbnMuaW1wbC5iYWcubXV0YWJsZS5wcmltaXRpdmUuQm9vbGVhbkhhc2hCYWcAAAAAAAAA\n"
                        + "AQwAAHhwdwgAAAAAAAAAAHhxAH4ABA==",
                new SynchronizedBooleanBag(new BooleanHashBag()));
    }
}
