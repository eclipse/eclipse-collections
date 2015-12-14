/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.checked;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class CheckedProcedureTest
{
    @Test
    public void dateProcedure()
    {
        Procedure<Date> procedure = new CheckedProcedure<Date>()
        {
            @Override
            public void safeValue(Date date)
            {
                Assert.assertNotNull(date.toString());
            }
        };
        procedure.value(new Date());
    }

    @Test
    public void collectionProcedure()
    {
        Procedure<Collection<String>> procedure = new CheckedProcedure<Collection<String>>()
        {
            @Override
            public void safeValue(Collection<String> collection)
            {
                Verify.assertNotEmpty(collection);
            }
        };
        procedure.value(Lists.fixedSize.of("1"));
    }

    @Test
    public void mapProcedure()
    {
        Procedure<Map<String, String>> procedure = new CheckedProcedure<Map<String, String>>()
        {
            @Override
            public void safeValue(Map<String, String> map)
            {
                Verify.assertContainsKey("1", map);
            }
        };
        procedure.value(Maps.fixedSize.of("1", "1"));
    }

    @Test
    public void checkedObjectIntProcedure()
    {
        boolean success = false;
        try
        {
            ObjectIntProcedure<Object> objectIntProcedure = new CheckedObjectIntProcedure<Object>()
            {
                @Override
                public void safeValue(Object object, int index)
                {
                    throw new RuntimeException();
                }
            };
            objectIntProcedure.value(null, 0);
        }
        catch (RuntimeException ignored)
        {
            success = true;
        }
        Assert.assertTrue(success);
    }

    @Test
    public void numberProcedure()
    {
        Procedure<Integer> procedure = new CheckedProcedure<Integer>()
        {
            @Override
            public void safeValue(Integer integer)
            {
                Assert.assertEquals(Integer.valueOf(1), integer);
            }
        };
        procedure.value(1);
    }

    @Test
    public void timestampProcedure()
    {
        Procedure<Timestamp> procedure = new CheckedProcedure<Timestamp>()
        {
            @Override
            public void safeValue(Timestamp timestamp)
            {
                Assert.assertNotNull(timestamp.toString());
            }
        };
        procedure.value(new Timestamp(0));
    }
}
