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

public class UnmodifiableByteListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5wcmltaXRp\n"
                        + "dmUuVW5tb2RpZmlhYmxlQnl0ZUxpc3QAAAAAAAAAAQIAAHhyAFxvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RVbm1vZGlmaWFi\n"
                        + "bGVCeXRlQ29sbGVjdGlvbgAAAAAAAAABAgABTAAKY29sbGVjdGlvbnQASExvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvY29sbGVjdGlvbi9wcmltaXRpdmUvTXV0YWJsZUJ5dGVDb2xsZWN0aW9u\n"
                        + "O3hwc3IAQW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubGlzdC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5CeXRlQXJyYXlMaXN0AAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new UnmodifiableByteList(new ByteArrayList()));
    }
}
