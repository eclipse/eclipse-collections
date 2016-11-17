/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.immutable.primitive;

import org.eclipse.collections.impl.factory.primitive.BooleanSets;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableBooleanHashSetSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAFpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5pbW11dGFibGUucHJpbWl0\n"
                        + "aXZlLkltbXV0YWJsZUJvb2xlYW5TZXRTZXJpYWxpemF0aW9uUHJveHkAAAAAAAAAAQwAAHhwdwQA\n"
                        + "AAAAeA==",
                BooleanSets.immutable.with());

        Verify.assertSerializedForm(
                "rO0ABXNyAFpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5pbW11dGFibGUucHJpbWl0\n"
                        + "aXZlLkltbXV0YWJsZUJvb2xlYW5TZXRTZXJpYWxpemF0aW9uUHJveHkAAAAAAAAAAQwAAHhwdwUA\n"
                        + "AAABAHg=",
                BooleanSets.immutable.with(false));

        Verify.assertSerializedForm(
                "rO0ABXNyAFpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5pbW11dGFibGUucHJpbWl0\n"
                        + "aXZlLkltbXV0YWJsZUJvb2xlYW5TZXRTZXJpYWxpemF0aW9uUHJveHkAAAAAAAAAAQwAAHhwdwUA\n"
                        + "AAABAXg=",
                BooleanSets.immutable.with(true));

        Verify.assertSerializedForm(
                "rO0ABXNyAFpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5pbW11dGFibGUucHJpbWl0\n"
                        + "aXZlLkltbXV0YWJsZUJvb2xlYW5TZXRTZXJpYWxpemF0aW9uUHJveHkAAAAAAAAAAQwAAHhwdwYA\n"
                        + "AAACAAF4",
                BooleanSets.immutable.with(false, true));
    }
}
