/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable;

import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class SynchronizedMutableCollectionSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5T\n"
                        + "eW5jaHJvbml6ZWRDb2xsZWN0aW9uU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHNyADJv\n"
                        + "cmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAAB\n"
                        + "DAAAeHB3BAAAAAB4eA==",
                SynchronizedMutableCollection.of(Lists.mutable.of()));
    }
}
