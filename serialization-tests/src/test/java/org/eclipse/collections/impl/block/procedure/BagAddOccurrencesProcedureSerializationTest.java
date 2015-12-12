/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class BagAddOccurrencesProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5CYWdB\n"
                        + "ZGRPY2N1cnJlbmNlc1Byb2NlZHVyZQAAAAAAAAABAgABTAAKbXV0YWJsZUJhZ3QALExvcmcvZWNs\n"
                        + "aXBzZS9jb2xsZWN0aW9ucy9hcGkvYmFnL011dGFibGVCYWc7eHBw",
                BagAddOccurrencesProcedure.on(null));
    }
}
