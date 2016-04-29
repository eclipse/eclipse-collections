/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.impl.partition.stack.PartitionArrayStack;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class PartitionArrayStackPartitionProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnBhcnRpdGlvbi5zdGFjay5QYXJ0\n"
                        + "aXRpb25BcnJheVN0YWNrJFBhcnRpdGlvblByb2NlZHVyZQAAAAAAAAABAgACTAAVcGFydGl0aW9u\n"
                        + "TXV0YWJsZVN0YWNrdABCTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2ltcGwvcGFydGl0aW9uL3N0\n"
                        + "YWNrL1BhcnRpdGlvbkFycmF5U3RhY2s7TAAJcHJlZGljYXRldAA3TG9yZy9lY2xpcHNlL2NvbGxl\n"
                        + "Y3Rpb25zL2FwaS9ibG9jay9wcmVkaWNhdGUvUHJlZGljYXRlO3hwcHA=",
                new PartitionArrayStack.PartitionProcedure<>(null, null));
    }
}
