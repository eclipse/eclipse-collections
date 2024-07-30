/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.sorted.mutable;

import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

/**
 * @since 4.2
 */
public class UnmodifiableSortedBagSerializationTest
{
    @Test
    public void serializedForm_comparator()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5V\n"
                        + "bm1vZGlmaWFibGVDb2xsZWN0aW9uU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHNyADdv\n"
                        + "cmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5zb3J0ZWQubXV0YWJsZS5UcmVlQmFnAAAA\n"
                        + "AAAAAAEMAAB4cHNyAFNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5hcGkuYmxvY2suZmFjdG9yeS5T\n"
                        + "ZXJpYWxpemFibGVDb21wYXJhdG9ycyRSZXZlcnNlQ29tcGFyYXRvcgAAAAAAAAABAgABTAAKY29t\n"
                        + "cGFyYXRvcnQAOkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svU2VyaWFsaXphYmxl\n"
                        + "Q29tcGFyYXRvcjt4cHNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5hcGkuYmxvY2suZmFjdG9y\n"
                        + "eS5TZXJpYWxpemFibGVDb21wYXJhdG9ycyROYXR1cmFsT3JkZXJDb21wYXJhdG9yAAAAAAAAAAEC\n"
                        + "AAB4cHcEAAAAAHh4",
                UnmodifiableSortedBag.of(TreeBag.newBag(Comparators.reverseNaturalOrder())));
    }

    @Test
    public void serializedForm_comparator_old()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5V\n"
                        + "bm1vZGlmaWFibGVDb2xsZWN0aW9uU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHNyADdv\n"
                        + "cmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5zb3J0ZWQubXV0YWJsZS5UcmVlQmFnAAAA\n"
                        + "AAAAAAEMAAB4cHNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3Rvcnku\n"
                        + "Q29tcGFyYXRvcnMkUmV2ZXJzZUNvbXBhcmF0b3IAAAAAAAAAAQIAAUwACmNvbXBhcmF0b3J0ABZM\n"
                        + "amF2YS91dGlsL0NvbXBhcmF0b3I7eHBzcgBNb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5i\n"
                        + "bG9jay5mYWN0b3J5LkNvbXBhcmF0b3JzJE5hdHVyYWxPcmRlckNvbXBhcmF0b3IAAAAAAAAAAQIA\n"
                        + "AHhwdwQAAAAAeHg=",
                UnmodifiableSortedBag.of(TreeBag.newBag(Comparators.originalReverseNaturalOrder())));
    }
}
