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

public class UnmodifiableBooleanListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5wcmltaXRp\n"
                        + "dmUuVW5tb2RpZmlhYmxlQm9vbGVhbkxpc3QAAAAAAAAAAQIAAHhyAF9vcmcuZWNsaXBzZS5jb2xs\n"
                        + "ZWN0aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RVbm1vZGlm\n"
                        + "aWFibGVCb29sZWFuQ29sbGVjdGlvbgAAAAAAAAABAgABTAAKY29sbGVjdGlvbnQAS0xvcmcvZWNs\n"
                        + "aXBzZS9jb2xsZWN0aW9ucy9hcGkvY29sbGVjdGlvbi9wcmltaXRpdmUvTXV0YWJsZUJvb2xlYW5D\n"
                        + "b2xsZWN0aW9uO3hwc3IARG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubGlzdC5tdXRhYmxl\n"
                        + "LnByaW1pdGl2ZS5Cb29sZWFuQXJyYXlMaXN0AAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new UnmodifiableBooleanList(new BooleanArrayList()));
    }
}
