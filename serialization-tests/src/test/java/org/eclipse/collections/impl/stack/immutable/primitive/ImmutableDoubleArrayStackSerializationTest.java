/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.immutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableDoubleArrayStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAHdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLmltbXV0YWJsZS5wcmlt\n"
                        + "aXRpdmUuSW1tdXRhYmxlRG91YmxlQXJyYXlTdGFjayRJbW11dGFibGVEb3VibGVTdGFja1Nlcmlh\n"
                        + "bGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3HAAAAANACAAAAAAAAEAAAAAAAAAAP/AAAAAAAAB4\n",
                ImmutableDoubleArrayStack.newStackWith(1.0, 2.0, 3.0));
    }
}
