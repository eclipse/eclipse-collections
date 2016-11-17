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

public class UnmodifiableFloatBagSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5Vbm1vZGlmaWFibGVGbG9hdEJhZwAAAAAAAAABAgAAeHIAXW9yZy5lY2xpcHNlLmNvbGxlY3Rp\n"
                        + "b25zLmltcGwuY29sbGVjdGlvbi5tdXRhYmxlLnByaW1pdGl2ZS5BYnN0cmFjdFVubW9kaWZpYWJs\n"
                        + "ZUZsb2F0Q29sbGVjdGlvbgAAAAAAAAABAgABTAAKY29sbGVjdGlvbnQASUxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvY29sbGVjdGlvbi9wcmltaXRpdmUvTXV0YWJsZUZsb2F0Q29sbGVjdGlv\n"
                        + "bjt4cHNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5GbG9hdEhhc2hCYWcAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new UnmodifiableFloatBag(new FloatHashBag()));
    }
}
