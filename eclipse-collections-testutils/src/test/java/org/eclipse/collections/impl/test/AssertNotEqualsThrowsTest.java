/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.test;

import org.junit.Assert;
import org.junit.Test;

public class AssertNotEqualsThrowsTest
{
    private static final Object OBJECT = new Object();
    private static final String STRING = "1";
    private static final double DOUBLE = 1.0d;
    private static final double DOUBLE_DELTA = 0.5d;
    private static final float FLOAT = 1.0f;
    private static final float FLOAT_DELTA = 0.5f;
    private static final long LONG = 1L;
    private static final boolean BOOLEAN = true;
    private static final byte BYTE = 1;
    private static final char CHAR = '1';
    private static final short SHORT = 1;
    private static final int INT = 1;

    @Test
    public void objectObject()
    {
        try
        {
            Verify.assertNotEquals(OBJECT, OBJECT);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void stringObjectObject()
    {
        try
        {
            Verify.assertNotEquals("items", OBJECT, OBJECT);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void stringString()
    {
        try
        {
            Verify.assertNotEquals(STRING, STRING);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
        try
        {
            Verify.assertNotEquals(null, null);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void stringStringString()
    {
        try
        {
            Verify.assertNotEquals("strings", STRING, STRING);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void doubleDoubleDouble()
    {
        try
        {
            Verify.assertNotEquals(DOUBLE, DOUBLE, DOUBLE_DELTA);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }

        try
        {
            Verify.assertNotEquals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, DOUBLE_DELTA);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void stringDoubleDoubleDouble()
    {
        try
        {
            Verify.assertNotEquals("doubles", DOUBLE, DOUBLE, DOUBLE_DELTA);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void floatFloatFloat()
    {
        try
        {
            Verify.assertNotEquals(FLOAT, FLOAT, FLOAT_DELTA);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }

        try
        {
            Verify.assertNotEquals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, FLOAT_DELTA);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void stringFloatFloatFloat()
    {
        try
        {
            Verify.assertNotEquals("floats", FLOAT, FLOAT, FLOAT_DELTA);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void longLong()
    {
        try
        {
            Verify.assertNotEquals(LONG, LONG);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void stringLongLong()
    {
        try
        {
            Verify.assertNotEquals("longs", LONG, LONG);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void booleanBoolean()
    {
        try
        {
            Verify.assertNotEquals(BOOLEAN, BOOLEAN);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void stringBooleanBoolean()
    {
        try
        {
            Verify.assertNotEquals("booleans", BOOLEAN, BOOLEAN);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void byteByte()
    {
        try
        {
            Verify.assertNotEquals(BYTE, BYTE);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void stringByteByte()
    {
        try
        {
            Verify.assertNotEquals("bytes", BYTE, BYTE);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void charChar()
    {
        try
        {
            Verify.assertNotEquals(CHAR, CHAR);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void stringCharChar()
    {
        try
        {
            Verify.assertNotEquals("chars", CHAR, CHAR);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void shortShort()
    {
        try
        {
            Verify.assertNotEquals(SHORT, SHORT);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void stringShortShort()
    {
        try
        {
            Verify.assertNotEquals("shorts", SHORT, SHORT);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void intInt()
    {
        try
        {
            Verify.assertNotEquals(INT, INT);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void stringIntInt()
    {
        try
        {
            Verify.assertNotEquals("ints", INT, INT);
            Assert.fail("AssertionError expected");
        }
        catch (AssertionError e)
        {
            Verify.assertContains(AssertNotEqualsThrowsTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }
}
