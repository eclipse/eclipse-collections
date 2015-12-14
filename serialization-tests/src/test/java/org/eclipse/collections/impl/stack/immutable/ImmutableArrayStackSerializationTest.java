/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.immutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableArrayStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAGFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLmltbXV0YWJsZS5JbW11\n"
                        + "dGFibGVBcnJheVN0YWNrJEltbXV0YWJsZVN0YWNrU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEM\n"
                        + "AAB4cHcEAAAAAHg=",
                ImmutableArrayStack.newStack());
    }

    @Test
    public void serializedForm_with_element()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAGFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLmltbXV0YWJsZS5JbW11\n"
                        + "dGFibGVBcnJheVN0YWNrJEltbXV0YWJsZVN0YWNrU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEM\n"
                        + "AAB4cHcEAAAAAXB4",
                ImmutableArrayStack.newStackWith((Object) null));
    }

    @Test
    public void serializedForm_with_elements()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAGFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLmltbXV0YWJsZS5JbW11\n"
                        + "dGFibGVBcnJheVN0YWNrJEltbXV0YWJsZVN0YWNrU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEM\n"
                        + "AAB4cHcEAAAABXBwcHBweA==",
                ImmutableArrayStack.newStackWith(null, null, null, null, null));
    }
}
