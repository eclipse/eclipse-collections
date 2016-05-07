/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.comparator.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class DoubleFunctionComparatorSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmNvbXBhcmF0b3IucHJp\n"
                        + "bWl0aXZlLkRvdWJsZUZ1bmN0aW9uQ29tcGFyYXRvcgAAAAAAAAABAgABTAAIZnVuY3Rpb250AEVM\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1pdGl2ZS9Eb3Vi\n"
                        + "bGVGdW5jdGlvbjt4cHA=",
                new DoubleFunctionComparator<>(null));
    }
}
