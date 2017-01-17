/*
 * Copyright (c) 2017 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collector;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class BigDecimalSummaryStatisticsSeralizationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rvci5CaWdEZWNpbWFs\n"
                        + "U3VtbWFyeVN0YXRpc3RpY3MAAAAAAAAAAQIABEoABWNvdW50TAADbWF4dAAWTGphdmEvbWF0aC9C\n"
                        + "aWdEZWNpbWFsO0wAA21pbnEAfgABTAADc3VtcQB+AAF4cAAAAAAAAAAAcHBzcgAUamF2YS5tYXRo\n"
                        + "LkJpZ0RlY2ltYWxUxxVX+YEoTwMAAkkABXNjYWxlTAAGaW50VmFsdAAWTGphdmEvbWF0aC9CaWdJ\n"
                        + "bnRlZ2VyO3hyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAABzcgAUamF2YS5tYXRo\n"
                        + "LkJpZ0ludGVnZXKM/J8fqTv7HQMABkkACGJpdENvdW50SQAJYml0TGVuZ3RoSQATZmlyc3ROb256\n"
                        + "ZXJvQnl0ZU51bUkADGxvd2VzdFNldEJpdEkABnNpZ251bVsACW1hZ25pdHVkZXQAAltCeHEAfgAF\n"
                        + "///////////////+/////gAAAAB1cgACW0Ks8xf4BghU4AIAAHhwAAAAAHh4",
                new BigDecimalSummaryStatistics());
    }
}
