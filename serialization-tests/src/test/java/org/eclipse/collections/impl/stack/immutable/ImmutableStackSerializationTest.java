/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.immutable;

import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLmltbXV0YWJsZS5JbW11\n"
                        + "dGFibGVTdGFja1NlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3BAAAAAB4",
                Stacks.immutable.empty());
    }

    @Test
    public void serializedForm_with_element()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLmltbXV0YWJsZS5JbW11\n"
                        + "dGFibGVTdGFja1NlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3BAAAAAFweA==",
                Stacks.immutable.with((Object) null));
    }

    @Test
    public void serializedForm_with_elements()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLmltbXV0YWJsZS5JbW11\n"
                        + "dGFibGVTdGFja1NlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3BAAAAAJwcHg=",
                Stacks.immutable.with(null, null));
    }
}
