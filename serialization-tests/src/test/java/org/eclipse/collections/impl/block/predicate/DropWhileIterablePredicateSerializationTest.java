/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.predicate;

import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class DropWhileIterablePredicateSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5Ecm9w\n"
                        + "V2hpbGVJdGVyYWJsZVByZWRpY2F0ZQAAAAAAAAABAgACWgAUZG9uZURyb3BwaW5nRWxlbWVudHNM\n"
                        + "AAlwcmVkaWNhdGV0ADdMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0\n"
                        + "ZS9QcmVkaWNhdGU7eHAAc3IAQG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFj\n"
                        + "dG9yeS5QcmVkaWNhdGVzJEFsd2F5c1RydWUAAAAAAAAAAQIAAHhyADVvcmcuZWNsaXBzZS5jb2xs\n"
                        + "ZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHA=",
                new DropWhileIterablePredicate<>(Predicates.alwaysTrue()));
    }
}
