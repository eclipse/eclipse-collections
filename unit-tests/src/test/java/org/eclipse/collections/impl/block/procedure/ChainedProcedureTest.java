/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.StringIterate;
import org.junit.Assert;
import org.junit.Test;

public class ChainedProcedureTest
{
    @Test
    public void procedure()
    {
        MutableList<String> list1 = Lists.mutable.of();
        MutableList<String> list2 = Lists.mutable.of();
        Procedure<String> procedure1 = new CollectionAddProcedure<>(list1);
        Procedure<String> procedure2 = new CollectionAddProcedure<>(list2);
        ChainedProcedure<String> chainedProcedure = ChainedProcedure.with(procedure1, procedure2);

        MutableList<String> list = FastList.newListWith("1", "2");
        Iterate.forEach(list, chainedProcedure);

        Assert.assertEquals(list, list1);
        Assert.assertEquals(list, list2);
    }

    @Test
    public void toStringTest()
    {
        Procedure<String> procedure = new CollectionAddProcedure<>(Lists.mutable.of());
        String s = procedure.toString();
        Assert.assertNotNull(s);
        Assert.assertTrue(StringIterate.notEmptyOrWhitespace(s));
    }
}
