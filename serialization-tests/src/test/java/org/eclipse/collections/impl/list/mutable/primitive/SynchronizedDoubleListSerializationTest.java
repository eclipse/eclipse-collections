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

public class SynchronizedDoubleListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5wcmltaXRp\n"
                        + "dmUuU3luY2hyb25pemVkRG91YmxlTGlzdAAAAAAAAAABAgAAeHIAXm9yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwuY29sbGVjdGlvbi5tdXRhYmxlLnByaW1pdGl2ZS5BYnN0cmFjdFN5bmNocm9u\n"
                        + "aXplZERvdWJsZUNvbGxlY3Rpb24AAAAAAAAAAQIAAkwACmNvbGxlY3Rpb250AEpMb3JnL2VjbGlw\n"
                        + "c2UvY29sbGVjdGlvbnMvYXBpL2NvbGxlY3Rpb24vcHJpbWl0aXZlL011dGFibGVEb3VibGVDb2xs\n"
                        + "ZWN0aW9uO0wABGxvY2t0ABJMamF2YS9sYW5nL09iamVjdDt4cHNyAENvcmcuZWNsaXBzZS5jb2xs\n"
                        + "ZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5wcmltaXRpdmUuRG91YmxlQXJyYXlMaXN0AAAAAAAA\n"
                        + "AAEMAAB4cHcEAAAAAHhxAH4ABA==",
                new SynchronizedDoubleList(new DoubleArrayList()));
    }
}
