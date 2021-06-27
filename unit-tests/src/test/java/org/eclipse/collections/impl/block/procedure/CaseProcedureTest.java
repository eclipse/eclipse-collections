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

import java.util.List;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class CaseProcedureTest
{
    @Test
    public void noopCaseAndThenDefault()
    {
        List<String> result = Lists.mutable.empty();
        FastList<String> strings = FastList.newListWith("1", "2");
        CaseProcedure<String> procedure = new CaseProcedure<>();
        strings.each(procedure);
        Verify.assertEmpty(result);
        Verify.assertSame(procedure, procedure.setDefault(result::add));
        strings.each(procedure);
        Assert.assertEquals(result, strings);
        Verify.assertContains("CaseProcedure", procedure.toString());
    }

    @Test
    public void oneCaseWithDefault()
    {
        MutableList<String> ifOneList = Lists.mutable.of();
        MutableList<String> defaultList = Lists.mutable.of();
        MutableList<String> list = FastList.newListWith("1", "2");
        CaseProcedure<String> procedure =
                new CaseProcedure<String>(defaultList::add)
                        .addCase("1"::equals, ifOneList::add);
        list.each(procedure);
        Assert.assertEquals(FastList.newListWith("1"), ifOneList);
        Assert.assertEquals(FastList.newListWith("2"), defaultList);
        Verify.assertContains("CaseProcedure", procedure.toString());
    }

    @Test
    public void twoCasesNoDefault()
    {
        MutableList<String> ifOneList = Lists.mutable.of();
        MutableList<String> ifTwoList = Lists.mutable.of();
        MutableList<String> list = FastList.newListWith("1", "2", "3");
        CaseProcedure<String> procedure =
                new CaseProcedure<String>()
                        .addCase("1"::equals, ifOneList::add)
                        .addCase("2"::equals, ifTwoList::add);
        list.each(procedure);
        Assert.assertEquals(FastList.newListWith("1"), ifOneList);
        Assert.assertEquals(FastList.newListWith("2"), ifTwoList);
        Verify.assertContains("CaseProcedure", procedure.toString());
    }

    @Test
    public void twoCasesWithDefault()
    {
        MutableList<String> ifOneList = Lists.mutable.of();
        MutableList<String> ifTwoList = Lists.mutable.of();
        MutableList<String> defaultList = Lists.mutable.of();
        MutableList<String> list = FastList.newListWith("1", "2", "3", "4");
        CaseProcedure<String> procedure =
                new CaseProcedure<String>(defaultList::add)
                        .addCase("1"::equals, ifOneList::add)
                        .addCase("2"::equals, ifTwoList::add);
        list.each(procedure);
        Assert.assertEquals(FastList.newListWith("1"), ifOneList);
        Assert.assertEquals(FastList.newListWith("2"), ifTwoList);
        Assert.assertEquals(FastList.newListWith("3", "4"), defaultList);
        Verify.assertContains("CaseProcedure", procedure.toString());
    }
}
