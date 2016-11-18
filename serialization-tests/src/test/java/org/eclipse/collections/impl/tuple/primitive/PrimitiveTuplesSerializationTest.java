/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.tuple.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class PrimitiveTuplesSerializationTest
{
    @Test
    public void byteObjectPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5CeXRl\n"
                        + "T2JqZWN0UGFpckltcGwAAAAAAAAAAQIAAkIAA29uZUwAA3R3b3QAEkxqYXZhL2xhbmcvT2JqZWN0\n"
                        + "O3hwAHA=",
                PrimitiveTuples.pair((byte) 0, null));
    }

    @Test
    public void booleanObjectPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Cb29s\n"
                        + "ZWFuT2JqZWN0UGFpckltcGwAAAAAAAAAAQIAAloAA29uZUwAA3R3b3QAEkxqYXZhL2xhbmcvT2Jq\n"
                        + "ZWN0O3hwAHA=",
                PrimitiveTuples.pair(false, null));
    }

    @Test
    public void charObjectPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5DaGFy\n"
                        + "T2JqZWN0UGFpckltcGwAAAAAAAAAAQIAAkMAA29uZUwAA3R3b3QAEkxqYXZhL2xhbmcvT2JqZWN0\n"
                        + "O3hwAGFw",
                PrimitiveTuples.pair('a', null));
    }

    @Test
    public void doubleObjectPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Eb3Vi\n"
                        + "bGVPYmplY3RQYWlySW1wbAAAAAAAAAABAgACRAADb25lTAADdHdvdAASTGphdmEvbGFuZy9PYmpl\n"
                        + "Y3Q7eHAAAAAAAAAAAHA=",
                PrimitiveTuples.pair(0.0, null));
    }

    @Test
    public void floatObjectPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5GbG9h\n"
                        + "dE9iamVjdFBhaXJJbXBsAAAAAAAAAAECAAJGAANvbmVMAAN0d290ABJMamF2YS9sYW5nL09iamVj\n"
                        + "dDt4cAAAAABw",
                PrimitiveTuples.pair(0.0f, null));
    }

    @Test
    public void intObjectPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5JbnRP\n"
                        + "YmplY3RQYWlySW1wbAAAAAAAAAABAgACSQADb25lTAADdHdvdAASTGphdmEvbGFuZy9PYmplY3Q7\n"
                        + "eHAAAAAAcA==",
                PrimitiveTuples.pair(0, null));
    }

    @Test
    public void longObjectPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Mb25n\n"
                        + "T2JqZWN0UGFpckltcGwAAAAAAAAAAQIAAkoAA29uZUwAA3R3b3QAEkxqYXZhL2xhbmcvT2JqZWN0\n"
                        + "O3hwAAAAAAAAAABw",
                PrimitiveTuples.pair(0L, null));
    }

    @Test
    public void shortObjectPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5TaG9y\n"
                        + "dE9iamVjdFBhaXJJbXBsAAAAAAAAAAECAAJTAANvbmVMAAN0d290ABJMamF2YS9sYW5nL09iamVj\n"
                        + "dDt4cAAAcA==",
                PrimitiveTuples.pair((short) 0, null));
    }

    @Test
    public void objectBytePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5PYmpl\n"
                        + "Y3RCeXRlUGFpckltcGwAAAAAAAAAAQIAAkIAA3R3b0wAA29uZXQAEkxqYXZhL2xhbmcvT2JqZWN0\n"
                        + "O3hwAHA=",
                PrimitiveTuples.pair(null, (byte) 0));
    }

    @Test
    public void objectBooleanPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5PYmpl\n"
                        + "Y3RCb29sZWFuUGFpckltcGwAAAAAAAAAAQIAAloAA3R3b0wAA29uZXQAEkxqYXZhL2xhbmcvT2Jq\n"
                        + "ZWN0O3hwAHA=",
                PrimitiveTuples.pair(null, false));
    }

    @Test
    public void objectCharPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5PYmpl\n"
                        + "Y3RDaGFyUGFpckltcGwAAAAAAAAAAQIAAkMAA3R3b0wAA29uZXQAEkxqYXZhL2xhbmcvT2JqZWN0\n"
                        + "O3hwAGFw",
                PrimitiveTuples.pair(null, 'a'));
    }

    @Test
    public void objectDoublePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5PYmpl\n"
                        + "Y3REb3VibGVQYWlySW1wbAAAAAAAAAABAgACRAADdHdvTAADb25ldAASTGphdmEvbGFuZy9PYmpl\n"
                        + "Y3Q7eHAAAAAAAAAAAHA=",
                PrimitiveTuples.pair(null, 0.0));
    }

    @Test
    public void objectFloatPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5PYmpl\n"
                        + "Y3RGbG9hdFBhaXJJbXBsAAAAAAAAAAECAAJGAAN0d29MAANvbmV0ABJMamF2YS9sYW5nL09iamVj\n"
                        + "dDt4cAAAAABw",
                PrimitiveTuples.pair(null, 0.0f));
    }

    @Test
    public void objectIntPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5PYmpl\n"
                        + "Y3RJbnRQYWlySW1wbAAAAAAAAAABAgACSQADdHdvTAADb25ldAASTGphdmEvbGFuZy9PYmplY3Q7\n"
                        + "eHAAAAAAcA==",
                PrimitiveTuples.pair(null, 0));
    }

    @Test
    public void objectLongPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5PYmpl\n"
                        + "Y3RMb25nUGFpckltcGwAAAAAAAAAAQIAAkoAA3R3b0wAA29uZXQAEkxqYXZhL2xhbmcvT2JqZWN0\n"
                        + "O3hwAAAAAAAAAABw",
                PrimitiveTuples.pair(null, 0L));
    }

    @Test
    public void objectShortPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5PYmpl\n"
                        + "Y3RTaG9ydFBhaXJJbXBsAAAAAAAAAAECAAJTAAN0d29MAANvbmV0ABJMamF2YS9sYW5nL09iamVj\n"
                        + "dDt4cAAAcA==",
                PrimitiveTuples.pair(null, (short) 0));
    }

    @Test
    public void intIntPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5JbnRJ\n"
                        + "bnRQYWlySW1wbAAAAAAAAAABAgACSQADb25lSQADdHdveHAAAAAAAAAAAA==",
                PrimitiveTuples.pair(0, 0));
    }

    @Test
    public void intFloatPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5JbnRG\n"
                        + "bG9hdFBhaXJJbXBsAAAAAAAAAAECAAJJAANvbmVGAAN0d294cAAAAAAAAAAA",
                PrimitiveTuples.pair(0, 0.0f));
    }

    @Test
    public void intDoublePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5JbnRE\n"
                        + "b3VibGVQYWlySW1wbAAAAAAAAAABAgACSQADb25lRAADdHdveHAAAAAAAAAAAAAAAAA=",
                PrimitiveTuples.pair(0, 0.0));
    }

    @Test
    public void intLongPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5JbnRM\n"
                        + "b25nUGFpckltcGwAAAAAAAAAAQIAAkkAA29uZUoAA3R3b3hwAAAAAAAAAAAAAAAA",
                PrimitiveTuples.pair(0, 0L));
    }

    @Test
    public void intShortPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5JbnRT\n"
                        + "aG9ydFBhaXJJbXBsAAAAAAAAAAECAAJJAANvbmVTAAN0d294cAAAAAAAAA==",
                PrimitiveTuples.pair(0, (short) 0));
    }

    @Test
    public void intBytePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5JbnRC\n"
                        + "eXRlUGFpckltcGwAAAAAAAAAAQIAAkkAA29uZUIAA3R3b3hwAAAAAAA=",
                PrimitiveTuples.pair(0, (byte) 0));
    }

    @Test
    public void intCharPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5JbnRD\n"
                        + "aGFyUGFpckltcGwAAAAAAAAAAQIAAkkAA29uZUMAA3R3b3hwAAAAAAAw",
                PrimitiveTuples.pair(0, '0'));
    }

    @Test
    public void intBooleanPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5JbnRC\n"
                        + "b29sZWFuUGFpckltcGwAAAAAAAAAAQIAAkkAA29uZVoAA3R3b3hwAAAAAAA=",
                PrimitiveTuples.pair(0, false));
    }

    @Test
    public void floatIntPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5GbG9h\n"
                        + "dEludFBhaXJJbXBsAAAAAAAAAAECAAJGAANvbmVJAAN0d294cAAAAAAAAAAA",
                PrimitiveTuples.pair(0.0f, 0));
    }

    @Test
    public void floatFloatPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5GbG9h\n"
                        + "dEZsb2F0UGFpckltcGwAAAAAAAAAAQIAAkYAA29uZUYAA3R3b3hwAAAAAAAAAAA=",
                PrimitiveTuples.pair(0.0f, 0.0f));
    }

    @Test
    public void floatDoublePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5GbG9h\n"
                        + "dERvdWJsZVBhaXJJbXBsAAAAAAAAAAECAAJGAANvbmVEAAN0d294cAAAAAAAAAAAAAAAAA==",
                PrimitiveTuples.pair(0.0f, 0.0));
    }

    @Test
    public void floatLongPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5GbG9h\n"
                        + "dExvbmdQYWlySW1wbAAAAAAAAAABAgACRgADb25lSgADdHdveHAAAAAAAAAAAAAAAAA=",
                PrimitiveTuples.pair(0.0f, 0L));
    }

    @Test
    public void floatShortPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5GbG9h\n"
                        + "dFNob3J0UGFpckltcGwAAAAAAAAAAQIAAkYAA29uZVMAA3R3b3hwAAAAAAAA",
                PrimitiveTuples.pair(0.0f, (short) 0));
    }

    @Test
    public void floatBytePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5GbG9h\n"
                        + "dEJ5dGVQYWlySW1wbAAAAAAAAAABAgACRgADb25lQgADdHdveHAAAAAAAA==",
                PrimitiveTuples.pair(0.0f, (byte) 0));
    }

    @Test
    public void floatCharPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5GbG9h\n"
                        + "dENoYXJQYWlySW1wbAAAAAAAAAABAgACRgADb25lQwADdHdveHAAAAAAADA=",
                PrimitiveTuples.pair(0.0f, '0'));
    }

    @Test
    public void floatBooleanPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5GbG9h\n"
                        + "dEJvb2xlYW5QYWlySW1wbAAAAAAAAAABAgACRgADb25lWgADdHdveHAAAAAAAA==",
                PrimitiveTuples.pair(0.0f, false));
    }

    @Test
    public void doubleIntPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Eb3Vi\n"
                        + "bGVJbnRQYWlySW1wbAAAAAAAAAABAgACRAADb25lSQADdHdveHAAAAAAAAAAAAAAAAA=",
                PrimitiveTuples.pair(0.0, 0));
    }

    @Test
    public void doubleFloatPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Eb3Vi\n"
                        + "bGVGbG9hdFBhaXJJbXBsAAAAAAAAAAECAAJEAANvbmVGAAN0d294cAAAAAAAAAAAAAAAAA==",
                PrimitiveTuples.pair(0.0, 0.0f));
    }

    @Test
    public void doubleDoublePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Eb3Vi\n"
                        + "bGVEb3VibGVQYWlySW1wbAAAAAAAAAABAgACRAADb25lRAADdHdveHAAAAAAAAAAAAAAAAAAAAAA\n",
                PrimitiveTuples.pair(0.0, 0.0));
    }

    @Test
    public void doubleLongPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Eb3Vi\n"
                        + "bGVMb25nUGFpckltcGwAAAAAAAAAAQIAAkQAA29uZUoAA3R3b3hwAAAAAAAAAAAAAAAAAAAAAA==\n",
                PrimitiveTuples.pair(0.0, 0L));
    }

    @Test
    public void doubleShortPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Eb3Vi\n"
                        + "bGVTaG9ydFBhaXJJbXBsAAAAAAAAAAECAAJEAANvbmVTAAN0d294cAAAAAAAAAAAAAA=",
                PrimitiveTuples.pair(0.0, (short) 0));
    }

    @Test
    public void doubleBytePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Eb3Vi\n"
                        + "bGVCeXRlUGFpckltcGwAAAAAAAAAAQIAAkQAA29uZUIAA3R3b3hwAAAAAAAAAAAA",
                PrimitiveTuples.pair(0.0, (byte) 0));
    }

    @Test
    public void doubleCharPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Eb3Vi\n"
                        + "bGVDaGFyUGFpckltcGwAAAAAAAAAAQIAAkQAA29uZUMAA3R3b3hwAAAAAAAAAAAAMA==",
                PrimitiveTuples.pair(0.0, '0'));
    }

    @Test
    public void doubleBooleanPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Eb3Vi\n"
                        + "bGVCb29sZWFuUGFpckltcGwAAAAAAAAAAQIAAkQAA29uZVoAA3R3b3hwAAAAAAAAAAAA",
                PrimitiveTuples.pair(0.0, false));
    }

    @Test
    public void longIntPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Mb25n\n"
                        + "SW50UGFpckltcGwAAAAAAAAAAQIAAkoAA29uZUkAA3R3b3hwAAAAAAAAAAAAAAAA",
                PrimitiveTuples.pair(0L, 0));
    }

    @Test
    public void longFloatPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Mb25n\n"
                        + "RmxvYXRQYWlySW1wbAAAAAAAAAABAgACSgADb25lRgADdHdveHAAAAAAAAAAAAAAAAA=",
                PrimitiveTuples.pair(0L, 0.0f));
    }

    @Test
    public void longDoublePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Mb25n\n"
                        + "RG91YmxlUGFpckltcGwAAAAAAAAAAQIAAkoAA29uZUQAA3R3b3hwAAAAAAAAAAAAAAAAAAAAAA==\n",
                PrimitiveTuples.pair(0L, 0.0));
    }

    @Test
    public void longLongPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Mb25n\n"
                        + "TG9uZ1BhaXJJbXBsAAAAAAAAAAECAAJKAANvbmVKAAN0d294cAAAAAAAAAAAAAAAAAAAAAA=",
                PrimitiveTuples.pair(0L, 0L));
    }

    @Test
    public void longShortPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Mb25n\n"
                        + "U2hvcnRQYWlySW1wbAAAAAAAAAABAgACSgADb25lUwADdHdveHAAAAAAAAAAAAAA",
                PrimitiveTuples.pair(0L, (short) 0));
    }

    @Test
    public void longBytePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Mb25n\n"
                        + "Qnl0ZVBhaXJJbXBsAAAAAAAAAAECAAJKAANvbmVCAAN0d294cAAAAAAAAAAAAA==",
                PrimitiveTuples.pair(0L, (byte) 0));
    }

    @Test
    public void longCharPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Mb25n\n"
                        + "Q2hhclBhaXJJbXBsAAAAAAAAAAECAAJKAANvbmVDAAN0d294cAAAAAAAAAAAADA=",
                PrimitiveTuples.pair(0L, '0'));
    }

    @Test
    public void longBooleanPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Mb25n\n"
                        + "Qm9vbGVhblBhaXJJbXBsAAAAAAAAAAECAAJKAANvbmVaAAN0d294cAAAAAAAAAAAAA==",
                PrimitiveTuples.pair(0L, false));
    }

    @Test
    public void shortIntPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5TaG9y\n"
                        + "dEludFBhaXJJbXBsAAAAAAAAAAECAAJTAANvbmVJAAN0d294cAAAAAAAAA==",
                PrimitiveTuples.pair((short) 0, 0));
    }

    @Test
    public void shortFloatPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5TaG9y\n"
                        + "dEZsb2F0UGFpckltcGwAAAAAAAAAAQIAAlMAA29uZUYAA3R3b3hwAAAAAAAA",
                PrimitiveTuples.pair((short) 0, 0.0f));
    }

    @Test
    public void shortDoublePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5TaG9y\n"
                        + "dERvdWJsZVBhaXJJbXBsAAAAAAAAAAECAAJTAANvbmVEAAN0d294cAAAAAAAAAAAAAA=",
                PrimitiveTuples.pair((short) 0, 0.0));
    }

    @Test
    public void shortLongPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5TaG9y\n"
                        + "dExvbmdQYWlySW1wbAAAAAAAAAABAgACUwADb25lSgADdHdveHAAAAAAAAAAAAAA",
                PrimitiveTuples.pair((short) 0, 0L));
    }

    @Test
    public void shortShortPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5TaG9y\n"
                        + "dFNob3J0UGFpckltcGwAAAAAAAAAAQIAAlMAA29uZVMAA3R3b3hwAAAAAA==",
                PrimitiveTuples.pair((short) 0, (short) 0));
    }

    @Test
    public void shortBytePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5TaG9y\n"
                        + "dEJ5dGVQYWlySW1wbAAAAAAAAAABAgACUwADb25lQgADdHdveHAAAAA=",
                PrimitiveTuples.pair((short) 0, (byte) 0));
    }

    @Test
    public void shortCharPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5TaG9y\n"
                        + "dENoYXJQYWlySW1wbAAAAAAAAAABAgACUwADb25lQwADdHdveHAAAAAw",
                PrimitiveTuples.pair((short) 0, '0'));
    }

    @Test
    public void shortBooleanPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5TaG9y\n"
                        + "dEJvb2xlYW5QYWlySW1wbAAAAAAAAAABAgACUwADb25lWgADdHdveHAAAAA=",
                PrimitiveTuples.pair((short) 0, false));
    }

    @Test
    public void byteIntPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5CeXRl\n"
                        + "SW50UGFpckltcGwAAAAAAAAAAQIAAkIAA29uZUkAA3R3b3hwAAAAAAA=",
                PrimitiveTuples.pair((byte) 0, 0));
    }

    @Test
    public void byteFloatPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5CeXRl\n"
                        + "RmxvYXRQYWlySW1wbAAAAAAAAAABAgACQgADb25lRgADdHdveHAAAAAAAA==",
                PrimitiveTuples.pair((byte) 0, 0.0f));
    }

    @Test
    public void byteDoublePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5CeXRl\n"
                        + "RG91YmxlUGFpckltcGwAAAAAAAAAAQIAAkIAA29uZUQAA3R3b3hwAAAAAAAAAAAA",
                PrimitiveTuples.pair((byte) 0, 0.0));
    }

    @Test
    public void byteLongPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5CeXRl\n"
                        + "TG9uZ1BhaXJJbXBsAAAAAAAAAAECAAJCAANvbmVKAAN0d294cAAAAAAAAAAAAA==",
                PrimitiveTuples.pair((byte) 0, 0L));
    }

    @Test
    public void byteShortPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5CeXRl\n"
                        + "U2hvcnRQYWlySW1wbAAAAAAAAAABAgACQgADb25lUwADdHdveHAAAAA=",
                PrimitiveTuples.pair((byte) 0, (short) 0));
    }

    @Test
    public void byteBytePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5CeXRl\n"
                        + "Qnl0ZVBhaXJJbXBsAAAAAAAAAAECAAJCAANvbmVCAAN0d294cAAA",
                PrimitiveTuples.pair((byte) 0, (byte) 0));
    }

    @Test
    public void byteCharPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5CeXRl\n"
                        + "Q2hhclBhaXJJbXBsAAAAAAAAAAECAAJCAANvbmVDAAN0d294cAAAMA==",
                PrimitiveTuples.pair((byte) 0, '0'));
    }

    @Test
    public void byteBooleanPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5CeXRl\n"
                        + "Qm9vbGVhblBhaXJJbXBsAAAAAAAAAAECAAJCAANvbmVaAAN0d294cAAA",
                PrimitiveTuples.pair((byte) 0, false));
    }

    @Test
    public void charIntPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5DaGFy\n"
                        + "SW50UGFpckltcGwAAAAAAAAAAQIAAkMAA29uZUkAA3R3b3hwADAAAAAA",
                PrimitiveTuples.pair('0', 0));
    }

    @Test
    public void charFloatPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5DaGFy\n"
                        + "RmxvYXRQYWlySW1wbAAAAAAAAAABAgACQwADb25lRgADdHdveHAAMAAAAAA=",
                PrimitiveTuples.pair('0', 0.0f));
    }

    @Test
    public void charDoublePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5DaGFy\n"
                        + "RG91YmxlUGFpckltcGwAAAAAAAAAAQIAAkMAA29uZUQAA3R3b3hwADAAAAAAAAAAAA==",
                PrimitiveTuples.pair('0', 0.0));
    }

    @Test
    public void charLongPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5DaGFy\n"
                        + "TG9uZ1BhaXJJbXBsAAAAAAAAAAECAAJDAANvbmVKAAN0d294cAAwAAAAAAAAAAA=",
                PrimitiveTuples.pair('0', 0L));
    }

    @Test
    public void charShortPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5DaGFy\n"
                        + "U2hvcnRQYWlySW1wbAAAAAAAAAABAgACQwADb25lUwADdHdveHAAMAAA",
                PrimitiveTuples.pair('0', (short) 0));
    }

    @Test
    public void charBytePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5DaGFy\n"
                        + "Qnl0ZVBhaXJJbXBsAAAAAAAAAAECAAJDAANvbmVCAAN0d294cAAwAA==",
                PrimitiveTuples.pair('0', (byte) 0));
    }

    @Test
    public void charCharPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5DaGFy\n"
                        + "Q2hhclBhaXJJbXBsAAAAAAAAAAECAAJDAANvbmVDAAN0d294cAAwADA=",
                PrimitiveTuples.pair('0', '0'));
    }

    @Test
    public void charBooleanPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5DaGFy\n"
                        + "Qm9vbGVhblBhaXJJbXBsAAAAAAAAAAECAAJDAANvbmVaAAN0d294cAAwAA==",
                PrimitiveTuples.pair('0', false));
    }

    @Test
    public void booleanIntPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Cb29s\n"
                        + "ZWFuSW50UGFpckltcGwAAAAAAAAAAQIAAloAA29uZUkAA3R3b3hwAAAAAAA=",
                PrimitiveTuples.pair(false, 0));
    }

    @Test
    public void booleanFloatPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Cb29s\n"
                        + "ZWFuRmxvYXRQYWlySW1wbAAAAAAAAAABAgACWgADb25lRgADdHdveHAAAAAAAA==",
                PrimitiveTuples.pair(false, 0.0f));
    }

    @Test
    public void booleanDoublePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Cb29s\n"
                        + "ZWFuRG91YmxlUGFpckltcGwAAAAAAAAAAQIAAloAA29uZUQAA3R3b3hwAAAAAAAAAAAA",
                PrimitiveTuples.pair(false, 0.0));
    }

    @Test
    public void booleanLongPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Cb29s\n"
                        + "ZWFuTG9uZ1BhaXJJbXBsAAAAAAAAAAECAAJaAANvbmVKAAN0d294cAAAAAAAAAAAAA==",
                PrimitiveTuples.pair(false, 0L));
    }

    @Test
    public void booleanShortPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Cb29s\n"
                        + "ZWFuU2hvcnRQYWlySW1wbAAAAAAAAAABAgACWgADb25lUwADdHdveHAAAAA=",
                PrimitiveTuples.pair(false, (short) 0));
    }

    @Test
    public void booleanBytePair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Cb29s\n"
                        + "ZWFuQnl0ZVBhaXJJbXBsAAAAAAAAAAECAAJaAANvbmVCAAN0d294cAAA",
                PrimitiveTuples.pair(false, (byte) 0));
    }

    @Test
    public void booleanCharPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Cb29s\n"
                        + "ZWFuQ2hhclBhaXJJbXBsAAAAAAAAAAECAAJaAANvbmVDAAN0d294cAAAMA==",
                PrimitiveTuples.pair(false, '0'));
    }

    @Test
    public void booleanBooleanPair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLnByaW1pdGl2ZS5Cb29s\n"
                        + "ZWFuQm9vbGVhblBhaXJJbXBsAAAAAAAAAAECAAJaAANvbmVaAAN0d294cAAA",
                PrimitiveTuples.pair(false, false));
    }
}
