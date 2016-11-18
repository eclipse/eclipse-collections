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

public class SynchronizedCharBagSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRDaGFyQmFnAAAAAAAAAAECAAB4cgBcb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5jb2xsZWN0aW9uLm11dGFibGUucHJpbWl0aXZlLkFic3RyYWN0U3luY2hyb25pemVk\n"
                        + "Q2hhckNvbGxlY3Rpb24AAAAAAAAAAQIAAkwACmNvbGxlY3Rpb250AEhMb3JnL2VjbGlwc2UvY29s\n"
                        + "bGVjdGlvbnMvYXBpL2NvbGxlY3Rpb24vcHJpbWl0aXZlL011dGFibGVDaGFyQ29sbGVjdGlvbjtM\n"
                        + "AARsb2NrdAASTGphdmEvbGFuZy9PYmplY3Q7eHBzcgA+b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5iYWcubXV0YWJsZS5wcmltaXRpdmUuQ2hhckhhc2hCYWcAAAAAAAAAAQwAAHhwdwQAAAAA\n"
                        + "eHEAfgAE",
                new SynchronizedCharBag(new CharHashBag()));
    }
}
