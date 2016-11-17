/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

public final class SpreadFunctions
{
    private SpreadFunctions()
    {
    }

    private static int thirtyTwoBitSpread1(int code)
    {
        int code1 = code;
        code1 ^= code1 >>> 15;
        code1 *= 0xACAB2A4D;
        code1 ^= code1 >>> 15;
        code1 *= 0x5CC7DF53;
        code1 ^= code1 >>> 12;
        return code1;
    }

    private static int thirtyTwoBitSpread2(int code)
    {
        int code1 = code;
        code1 ^= code1 >>> 14;
        code1 *= 0xBA1CCD33;
        code1 ^= code1 >>> 13;
        code1 *= 0x9B6296CB;
        code1 ^= code1 >>> 12;
        return code1;
    }

    private static long sixtyFourBitSpread1(long code)
    {
        long code1 = code;
        code1 ^= code1 >>> 28;
        code1 *= -4254747342703917655L;
        code1 ^= code1 >>> 43;
        code1 *= -908430792394475837L;
        code1 ^= code1 >>> 23;
        return code1;
    }

    private static long sixtyFourBitSpread2(long code)
    {
        long code1 = code;
        code1 ^= code1 >>> 23;
        code1 *= -6261870919139520145L;
        code1 ^= code1 >>> 39;
        code1 *= 2747051607443084853L;
        code1 ^= code1 >>> 37;
        return code1;
    }

    public static long doubleSpreadOne(double element)
    {
        long code = Double.doubleToLongBits(element);
        return SpreadFunctions.sixtyFourBitSpread1(code);
    }

    public static long doubleSpreadTwo(double element)
    {
        long code = Double.doubleToLongBits(element);
        return SpreadFunctions.sixtyFourBitSpread2(code);
    }

    public static long longSpreadOne(long element)
    {
        return SpreadFunctions.sixtyFourBitSpread1(element);
    }

    public static long longSpreadTwo(long element)
    {
        return SpreadFunctions.sixtyFourBitSpread2(element);
    }

    public static int intSpreadOne(int element)
    {
        return SpreadFunctions.thirtyTwoBitSpread1(element);
    }

    public static int intSpreadTwo(int element)
    {
        return SpreadFunctions.thirtyTwoBitSpread2(element);
    }

    public static int floatSpreadOne(float element)
    {
        int code = Float.floatToIntBits(element);
        return SpreadFunctions.thirtyTwoBitSpread1(code);
    }

    public static int floatSpreadTwo(float element)
    {
        int code = Float.floatToIntBits(element);
        return SpreadFunctions.thirtyTwoBitSpread2(code);
    }

    public static int shortSpreadOne(short element)
    {
        int code = (int) element;
        return SpreadFunctions.thirtyTwoBitSpread1(code);
    }

    public static int shortSpreadTwo(short element)
    {
        int code = (int) element;
        return SpreadFunctions.thirtyTwoBitSpread2(code);
    }

    public static int charSpreadOne(char element)
    {
        int code = (int) element;
        return SpreadFunctions.thirtyTwoBitSpread1(code);
    }

    public static int charSpreadTwo(char element)
    {
        int code = (int) element;
        return SpreadFunctions.thirtyTwoBitSpread2(code);
    }
}
