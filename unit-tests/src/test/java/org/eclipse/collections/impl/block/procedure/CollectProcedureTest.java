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
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CollectProcedureTest
{
    @Test
    public void getCollection()
    {
        CollectProcedure<Integer, Integer> collectProcedure =
                new CollectProcedure<>(Functions.getIntegerPassThru(), Lists.mutable.empty());

        Verify.assertEmpty(collectProcedure.getCollection());
        collectProcedure.value(1);
        Verify.assertContainsAll(collectProcedure.getCollection(), 1);

        collectProcedure.value(2);
        Verify.assertContainsAll(collectProcedure.getCollection(), 1, 2);
    }
}
