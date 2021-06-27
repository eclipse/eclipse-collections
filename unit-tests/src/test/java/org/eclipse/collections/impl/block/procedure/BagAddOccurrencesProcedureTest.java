/*
 * Copyright (c) 2021 The Bank of New York Mellon and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.impl.utility.StringIterate;
import org.junit.Assert;
import org.junit.Test;

public class BagAddOccurrencesProcedureTest
{
    @Test
    public void basicCase()
    {
        MutableBag<String> targetCollection = Bags.mutable.empty();
        BagAddOccurrencesProcedure<String> procedure = new BagAddOccurrencesProcedure<>(targetCollection);

        procedure.value("fred", 1);
        procedure.value("fred", 1);
        procedure.value("mary", 3);

        Assert.assertEquals(2, procedure.getResult().occurrencesOf("fred"));
        Assert.assertEquals(3, procedure.getResult().occurrencesOf("mary"));
        Assert.assertEquals(0, procedure.getResult().occurrencesOf("other"));
    }

    @Test
    public void basicCaseUsingFactoryMethod()
    {
        MutableBag<String> targetCollection = Bags.mutable.empty();
        BagAddOccurrencesProcedure<String> procedure = BagAddOccurrencesProcedure.on(targetCollection);

        procedure.value("fred", 1);
        procedure.value("fred", 1);
        procedure.value("mary", 3);

        Assert.assertEquals(2, procedure.getResult().occurrencesOf("fred"));
        Assert.assertEquals(3, procedure.getResult().occurrencesOf("mary"));
        Assert.assertEquals(0, procedure.getResult().occurrencesOf("other"));
    }

    @Test
    public void toStringTest()
    {
        MutableBag<String> targetCollection = Bags.mutable.empty();
        BagAddOccurrencesProcedure<String> procedure = BagAddOccurrencesProcedure.on(targetCollection);
        String toString = procedure.toString();
        Assert.assertNotNull(toString);
        Assert.assertTrue(StringIterate.notEmptyOrWhitespace(toString));
    }
}
