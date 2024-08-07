/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable;

import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

/**
 * @since 4.2
 */
public class UnmodifiableBagSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAFpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5tdXRhYmxlLlVubW9kaWZp\n"
                        + "YWJsZUJhZyRVbm1vZGlmaWFibGVCYWdTZXJpYWxpemF0aW9uUHJveHkAAAAAAAAAAQwAAHhwc3IA\n"
                        + "MG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmFnLm11dGFibGUuSGFzaEJhZwAAAAAAAAAB\n"
                        + "DAAAeHB3BAAAAAB4eA==",
                UnmodifiableBag.of(Bags.mutable.of()));
    }
}
