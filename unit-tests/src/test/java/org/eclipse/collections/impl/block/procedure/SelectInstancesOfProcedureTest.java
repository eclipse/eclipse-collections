/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class SelectInstancesOfProcedureTest
{
    @Test
    public void getCollection()
    {
        SelectInstancesOfProcedure<Integer> procedure = new SelectInstancesOfProcedure<>(Integer.class, Lists.mutable.empty());
        Verify.assertEmpty(procedure.getCollection());
        procedure.value("test");
        Verify.assertEmpty(procedure.getCollection());
        procedure.value(1);
        Verify.assertSize(1, procedure.getCollection());
        Verify.assertContainsAll(procedure.getCollection(), 1);

        procedure.value(2);
        Verify.assertSize(2, procedure.getCollection());
        Verify.assertContainsAll(procedure.getCollection(), 1, 2);
    }
}
