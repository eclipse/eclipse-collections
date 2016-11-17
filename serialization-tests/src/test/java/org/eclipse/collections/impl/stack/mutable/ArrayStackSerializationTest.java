/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.mutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ArrayStackSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLm11dGFibGUuQXJyYXlT\n"
                        + "dGFjawAAAAAAAAABDAAAeHB3BAAAAAB4",
                ArrayStack.newStack());
    }

    @Test
    public void serializedForm_with_element()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLm11dGFibGUuQXJyYXlT\n"
                        + "dGFjawAAAAAAAAABDAAAeHB3BAAAAAFweA==",
                ArrayStack.newStackWith((Object) null));
    }

    @Test
    public void serializedForm_with_elements()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0YWNrLm11dGFibGUuQXJyYXlT\n"
                        + "dGFjawAAAAAAAAABDAAAeHB3BAAAAAVwcHBwcHg=",
                ArrayStack.newStackWith(null, null, null, null, null));
    }
}
