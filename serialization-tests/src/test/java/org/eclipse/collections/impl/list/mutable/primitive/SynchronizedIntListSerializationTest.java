/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class SynchronizedIntListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5wcmltaXRp\n"
                        + "dmUuU3luY2hyb25pemVkSW50TGlzdAAAAAAAAAABAgAAeHIAW29yZy5lY2xpcHNlLmNvbGxlY3Rp\n"
                        + "b25zLmltcGwuY29sbGVjdGlvbi5tdXRhYmxlLnByaW1pdGl2ZS5BYnN0cmFjdFN5bmNocm9uaXpl\n"
                        + "ZEludENvbGxlY3Rpb24AAAAAAAAAAQIAAkwACmNvbGxlY3Rpb250AEdMb3JnL2VjbGlwc2UvY29s\n"
                        + "bGVjdGlvbnMvYXBpL2NvbGxlY3Rpb24vcHJpbWl0aXZlL011dGFibGVJbnRDb2xsZWN0aW9uO0wA\n"
                        + "BGxvY2t0ABJMamF2YS9sYW5nL09iamVjdDt4cHNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLmxpc3QubXV0YWJsZS5wcmltaXRpdmUuSW50QXJyYXlMaXN0AAAAAAAAAAEMAAB4cHcEAAAA\n"
                        + "AHhxAH4ABA==",
                new SynchronizedIntList(new IntArrayList()));
    }
}
