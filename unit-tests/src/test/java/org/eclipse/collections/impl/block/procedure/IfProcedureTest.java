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

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.StringIterate;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IfProcedureTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IfProcedureTest.class);

    @Test
    public void procedure()
    {
        MutableList<String> list1 = Lists.mutable.of();
        MutableList<String> list2 = Lists.mutable.of();
        Procedure<String> ifProcedure = new IfProcedure<>("1"::equals, list1::add, list2::add);
        LOGGER.info("{}", ifProcedure);
        MutableList<String> list = FastList.newListWith("1", "2");
        Iterate.forEach(list, ifProcedure);
        Assert.assertEquals(1, list1.size());
        Verify.assertContains("1", list1);
        Assert.assertEquals(1, list2.size());
        Verify.assertContains("2", list2);
    }

    @Test
    public void toStringTest()
    {
        MutableList<String> list1 = Lists.mutable.of();
        MutableList<String> list2 = Lists.mutable.of();
        Procedure<String> ifProcedure = new IfProcedure<>("1"::equals, list1::add, list2::add);
        String s = ifProcedure.toString();
        Assert.assertNotNull(s);
        Assert.assertTrue(StringIterate.notEmptyOrWhitespace(s));
    }
}
