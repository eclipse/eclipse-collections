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

import org.eclipse.collections.impl.block.procedure.primitive.InjectIntoLongProcedure;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class InjectIntoLongProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuSW5qZWN0SW50b0xvbmdQcm9jZWR1cmUAAAAAAAAAAQIAAkoABnJlc3VsdEwACGZ1bmN0\n"
                        + "aW9udABPTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRp\n"
                        + "dmUvTG9uZ09iamVjdFRvTG9uZ0Z1bmN0aW9uO3hwAAAAAAAAAABw",
                new InjectIntoLongProcedure<>(0L, null));
    }
}
