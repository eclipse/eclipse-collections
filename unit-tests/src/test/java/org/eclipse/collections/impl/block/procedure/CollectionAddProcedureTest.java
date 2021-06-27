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
import org.eclipse.collections.impl.utility.StringIterate;
import org.junit.Assert;
import org.junit.Test;

public class CollectionAddProcedureTest
{
    @Test
    public void toStringTest()
    {
        CollectionAddProcedure<Integer> procedure = new CollectionAddProcedure<>(Lists.mutable.empty());
        String s = procedure.toString();
        Assert.assertNotNull(s);
        Assert.assertTrue(StringIterate.notEmptyOrWhitespace(s));
    }

    @Test
    public void getResult()
    {
        CollectionAddProcedure<Integer> procedure = CollectionAddProcedure.on(Lists.mutable.empty());
        Verify.assertEmpty(procedure.getResult());
        procedure.value(1);
        Verify.assertSize(1, procedure.getResult());
        Verify.assertContainsAll(procedure.getResult(), 1);

        procedure.value(2);
        Verify.assertSize(2, procedure.getResult());
        Verify.assertContainsAll(procedure.getResult(), 1, 2);
    }
}
