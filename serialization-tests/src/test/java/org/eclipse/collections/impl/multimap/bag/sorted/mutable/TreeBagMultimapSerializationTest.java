/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.bag.sorted.mutable;

import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class TreeBagMultimapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm11bHRpbWFwLmJhZy5zb3J0ZWQu\n"
                        + "bXV0YWJsZS5UcmVlQmFnTXVsdGltYXAAAAAAAAAAAQwAAHhwcHcEAAAAAHg=",
                TreeBagMultimap.newMultimap());
    }

    @Test
    public void serializedForm_comparator()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm11bHRpbWFwLmJhZy5zb3J0ZWQu\n"
                        + "bXV0YWJsZS5UcmVlQmFnTXVsdGltYXAAAAAAAAAAAQwAAHhwc3IAU29yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmFwaS5ibG9jay5mYWN0b3J5LlNlcmlhbGl6YWJsZUNvbXBhcmF0b3JzJFJldmVyc2VD\n"
                        + "b21wYXJhdG9yAAAAAAAAAAECAAFMAApjb21wYXJhdG9ydAA6TG9yZy9lY2xpcHNlL2NvbGxlY3Rp\n"
                        + "b25zL2FwaS9ibG9jay9TZXJpYWxpemFibGVDb21wYXJhdG9yO3hwc3IAWG9yZy5lY2xpcHNlLmNv\n"
                        + "bGxlY3Rpb25zLmFwaS5ibG9jay5mYWN0b3J5LlNlcmlhbGl6YWJsZUNvbXBhcmF0b3JzJE5hdHVy\n"
                        + "YWxPcmRlckNvbXBhcmF0b3IAAAAAAAAAAQIAAHhwdwQAAAAAeA==",
                TreeBagMultimap.newMultimap(Comparators.reverseNaturalOrder()));
    }

    @Test
    public void serializedForm_comparator_old()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm11bHRpbWFwLmJhZy5zb3J0ZWQu\n"
                        + "bXV0YWJsZS5UcmVlQmFnTXVsdGltYXAAAAAAAAAAAQwAAHhwc3IASG9yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5Db21wYXJhdG9ycyRSZXZlcnNlQ29tcGFyYXRvcgAA\n"
                        + "AAAAAAABAgABTAAKY29tcGFyYXRvcnQAFkxqYXZhL3V0aWwvQ29tcGFyYXRvcjt4cHNyAE1vcmcu\n"
                        + "ZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFyYXRvcnMkTmF0dXJh\n"
                        + "bE9yZGVyQ29tcGFyYXRvcgAAAAAAAAABAgAAeHB3BAAAAAB4",
                TreeBagMultimap.newMultimap(Comparators.originalReverseNaturalOrder()));
    }
}
